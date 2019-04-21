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
import org.ironman.ticketsellingsystem.model.dbBean.HistoryBean;
import org.ironman.ticketsellingsystem.ui.activity.CityPickerActivity;
import org.ironman.ticketsellingsystem.ui.activity.TrainListActivity;
import org.ironman.ticketsellingsystem.util.GsonUtil;
import org.ironman.ticketsellingsystem.util.TimeUtil;

import java.util.Calendar;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.log.XLog;
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
    private String date;
    private Intent intent;
    private HistoryBean historyBean;

    @Override
    public void initData(Bundle savedInstanceState) {
        sp = SharedPref.getInstance(context);
        String history = sp.getString("history", "");
        if (!history.isEmpty()) {
            XLog.e(history);
            historyBean = GsonUtil.getBean(history, HistoryBean.class);
//          historyBean = new Gson().fromJson(history, new TypeToken<HistoryBean>() {}.getType());
            startPlace = historyBean.getStartPlace();
            endPlace = historyBean.getEndPlace();
            date = historyBean.getDate();
        } else {
            historyBean = new HistoryBean();
            startPlace = "北京";
            endPlace = "上海";
            date = Kits.Date.getYmd(System.currentTimeMillis());
        }
        tvDate.setText(TimeUtil.dateConver(date, "yyyy-MM-dd", "MM月dd日"));
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
                startPlace = tvStartPlace.getText().toString();
                endPlace = tvEndPlace.getText().toString();

                intent = new Intent(getActivity(), TrainListActivity.class);
                if (cbIsFast.isChecked()) {
                    intent.putExtra("isFast", "g");
                }else {
                    intent.putExtra("isFast", "");
                }
                intent.putExtra("startPlace", startPlace);
                intent.putExtra("endPlace", endPlace);
                intent.putExtra("date", date);
                //保存历史记录
                historyBean.setStartPlace(startPlace);
                historyBean.setEndPlace(endPlace);
                historyBean.setDate(date);
                historyBean.setTvDate("shit");
                sp.putString("history", GsonUtil.getString(historyBean));
                startActivity(intent);
                break;
            case R.id.tv_clean:
                //清空历史记录
                sp.putString("history", "");
                tvHistory.setText("");
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
                date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }
}
