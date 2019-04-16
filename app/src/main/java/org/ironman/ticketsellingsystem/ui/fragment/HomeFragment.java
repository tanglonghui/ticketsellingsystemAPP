package org.ironman.ticketsellingsystem.ui.fragment;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import org.ironman.ticketsellingsystem.R;
import org.ironman.ticketsellingsystem.event.ChosePlaceEvent;
import org.ironman.ticketsellingsystem.ui.activity.CityPickerActivity;
import org.ironman.ticketsellingsystem.ui.activity.TrainListActivity;
import org.ironman.ticketsellingsystem.util.CommonUtil;

import java.util.Calendar;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import io.reactivex.functions.Consumer;


public class HomeFragment extends XFragment implements View.OnClickListener {

    @BindView(R.id.tv_start_place)
    TextView tvStartPlace;
    @BindView(R.id.tv_end_place)
    TextView tvEndPlace;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_query)
    TextView tvQuery;
    @BindView(R.id.tv_history)
    TextView tvHistory;
    @BindView(R.id.tv_clean)
    TextView tvClean;
    @BindView(R.id.cb_is_fast)
    CheckBox cbIsFast;
    @BindView(R.id.cb_is_student)
    CheckBox cbIsStudent;
    private SharedPref sp;
    private String startPlace;
    private String endPlace;
    private Intent intent;

    @Override
    public void initData(Bundle savedInstanceState) {
        sp = SharedPref.getInstance(context);
        startPlace = sp.getString("tvStartPlace", "北京");
        endPlace = sp.getString("tvEndPlace", "上海");
        tvStartPlace.setText(startPlace);
        tvEndPlace.setText(endPlace);
        tvHistory.setText(startPlace + "--" + endPlace);

        tvQuery.setOnClickListener(this);
        tvClean.setOnClickListener(this);
        tvStartPlace.setOnClickListener(this);
        tvEndPlace.setOnClickListener(this);
        tvDate.setOnClickListener(this);
        BusProvider.getBus().toFlowable(ChosePlaceEvent.class).subscribe(new Consumer<ChosePlaceEvent>() {
            @Override
            public void accept(ChosePlaceEvent chosePlaceEvent) throws Exception {
                switch (chosePlaceEvent.getState()) {
                    case "startPlace":
                        tvStartPlace.setText(chosePlaceEvent.getMsg());
                        break;
                    case "endPlace":
                        tvEndPlace.setText(chosePlaceEvent.getMsg());
                        break;
                }

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    protected void oneLogin(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_query:
                //查询火车列表
                intent=new Intent(getActivity(), TrainListActivity.class);
                if (cbIsFast.isChecked()) {
                    intent.putExtra("isFast", "1");
                }
                if (cbIsStudent.isChecked()) {
                    intent.putExtra("isStudent", "1");
                }
                startActivity(intent);
                break;
            case R.id.tv_clean:
                //清空历史记录
                break;
            case R.id.tv_start_place:
                //选择起点
                intent = new Intent(getActivity(), CityPickerActivity.class);
                intent.putExtra("state", "startPlace");
                startActivity(intent);
                break;
            case R.id.tv_end_place:
                //选择终点
                intent = new Intent(getActivity(), CityPickerActivity.class);
                intent.putExtra("state", "endPlace");
                startActivity(intent);
                break;
            case R.id.tv_date:
                //选择时间
                showDatePickDlg();
                break;
        }
    }

    //生日选择
    protected void showDatePickDlg() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                tvDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                tvDate.setText((monthOfYear + 1) + "月" + dayOfMonth + "日");
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }
}
