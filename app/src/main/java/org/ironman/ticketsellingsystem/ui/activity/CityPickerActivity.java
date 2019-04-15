package org.ironman.ticketsellingsystem.ui.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import org.ironman.ticketsellingsystem.R;
import org.ironman.ticketsellingsystem.adapter.CityListAdapter;
import org.ironman.ticketsellingsystem.app.Constans;
import org.ironman.ticketsellingsystem.event.ChosePlaceEvent;
import org.ironman.ticketsellingsystem.model.bean.AreasBean;
import org.ironman.ticketsellingsystem.model.bean.City;
import org.ironman.ticketsellingsystem.model.bean.CityPickerBean;
import org.ironman.ticketsellingsystem.model.bean.LocateState;
import org.ironman.ticketsellingsystem.util.CommonUtil;
import org.ironman.ticketsellingsystem.util.GsonUtil;
import org.ironman.ticketsellingsystem.util.PinyinUtils;
import org.ironman.ticketsellingsystem.util.ReadAssetsFileUtil;
import org.ironman.ticketsellingsystem.widget.SideLetterBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.mvp.XActivity;

public class CityPickerActivity extends XActivity {
    @BindView(R.id.content_back)
    TextView contentBack;
    @BindView(R.id.content_title)
    TextView contentTitle;
    private ListView mListView;
    private SideLetterBar mLetterBar;
    private CityListAdapter mCityAdapter;
    private String state = "";

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        initData();
        checkSelfPermission();
        getLocation();
    }

    @Override
    public boolean useEventBus() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.cp_activity_city_list;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    protected void oneLogin(String msg) {

    }

    protected void initView() {
        mListView = (ListView) findViewById(R.id.listview_all_city);
        TextView overlay = (TextView) findViewById(R.id.tv_letter_overlay);
        mLetterBar = (SideLetterBar) findViewById(R.id.side_letter_bar);
        mLetterBar.setOverlay(overlay);
        mLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                int position = mCityAdapter.getLetterPosition(letter);
                mListView.setSelection(position);
            }
        });
        mCityAdapter = new CityListAdapter(this);
        mListView.setAdapter(mCityAdapter);
        contentTitle.setText("选择车站");
        contentBack.setVisibility(View.VISIBLE);
    }

    public void getCityData() {
        String json = ReadAssetsFileUtil.getJson(this, "city.json");
        CityPickerBean bean = GsonUtil.getBean(json, CityPickerBean.class);
        HashSet<City> citys = new HashSet<>();
        for (AreasBean areasBean : bean.data.areas) {
            String name = areasBean.name.replace("　", "");
            citys.add(new City(areasBean.id, name, PinyinUtils.getPinYin(name), areasBean.is_hot == 1));
            for (AreasBean.ChildrenBeanX childrenBeanX : areasBean.children) {
                citys.add(new City(childrenBeanX.id, childrenBeanX.name, PinyinUtils.getPinYin(childrenBeanX.name), childrenBeanX.is_hot == 1));
            }
        }
        //set转换list
        ArrayList<City> cities = new ArrayList<>(citys);
        //按照字母排序
        Collections.sort(cities, new Comparator<City>() {
            @Override
            public int compare(City city, City t1) {
                return city.getPinyin().compareTo(t1.getPinyin());
            }
        });
        mCityAdapter.setData(cities);
    }

    protected void initData() {
        getCityData();
        state =getIntent().getStringExtra("state");
        mCityAdapter.setOnCityClickListener(new CityListAdapter.OnCityClickListener() {
            @Override
            public void onCityClick(String name) {//选择城市
//                Toast.makeText(CityPickerActivity.this, name, Toast.LENGTH_SHORT).show();
                ChosePlaceEvent event=new ChosePlaceEvent();
                event.setMsg(name);
                event.setState(state);
                BusProvider.getBus().post(event);
                finish();
            }

            @Override
            public void onLocateClick() {//点击定位按钮
                mCityAdapter.updateLocateState(LocateState.LOCATING, null);
                getLocation();//重新定位
            }
        });
        contentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 得到位置信息(高德地图)
     */
    private void getLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(this);
        //设置定位回调监听
        mLocationClient.setLocationListener(mAMapLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //获取一次定位结果：
        mLocationOption.setOnceLocation(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    //声明定位回调监听器
    private AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    if (amapLocation.getCity() != null && !amapLocation.getCity().equals("")) {
                        mCityAdapter.updateLocateState(LocateState.SUCCESS, amapLocation.getCity().replace("市", ""));
                    } else {
                        mCityAdapter.updateLocateState(LocateState.FAILED, null);
                    }
                    mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
                } else {
                    mCityAdapter.updateLocateState(LocateState.FAILED, null);
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("高德地图", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null) {
            //销毁定位客户端之后，若要重新开启定位请重新New一个AMapLocationClient对象。
            mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
        }
    }

    /**
     * @Description: TODO 申请定位权限及获得手机状态的权限 后获得用户手机信息
     */
    private void checkSelfPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Constans.REQ_ACCESS_FINE_LOCATION);
            return;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constans.REQ_ACCESS_FINE_LOCATION) {
            // 定位权限申请
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 获得授权
                checkSelfPermission();
            } else {
                // 被禁止授权
                CommonUtil.showMsg("请至权限中心打开本应用的获得手机GPS定位的权限");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
