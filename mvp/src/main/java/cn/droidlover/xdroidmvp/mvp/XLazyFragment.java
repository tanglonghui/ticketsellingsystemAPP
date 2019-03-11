package cn.droidlover.xdroidmvp.mvp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.XDroidConf;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xdroidmvp.net.NetError;

/**
 * Created by wanglei on 2017/1/26.
 */

public abstract class XLazyFragment<P extends IPresent>
        extends LazyFragment implements IView<P> {

    private VDelegate vDelegate;
    private P p;

    private RxPermissions rxPermissions;
    private Unbinder unbinder;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            bindUI(getRealRootView());
        }
        if (useEventBus()) {
            BusProvider.getBus().register(this);
        }
        bindEvent();
        initData(savedInstanceState);
    }

    @Override
    public void bindUI(View rootView) {
        unbinder = KnifeKit.bind(this, rootView);
    }

    @Override
    public void bindEvent() {

    }

    public void showError(NetError error) {
        if (error != null) {
            String msg = "";
            switch (error.getType()) {
                case NetError.ParseError:
                    msg = "数据解析异常";
                    break;
                case NetError.AuthError:
                    msg = "身份验证异常";
                    break;
                case NetError.BusinessError:
                    msg = error.getMessage();
                    break;
                case NetError.NoConnectError:
                    msg = "网络无连接";
                    break;
                case NetError.NoDataError:
                    msg = "数据为空";
                    break;
                case NetError.OtherError:
                    msg = "服务器连接异常";
                    break;
            }
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            Log.d("XLazyFragment", error.getMessage());
        }
    }


    public VDelegate getvDelegate() {
        if (vDelegate == null) {
            vDelegate = VDelegateBase.create(context);
        }
        return vDelegate;
    }

    protected P getP() {
        if (p == null) {
            p = newP();
            if (p != null) {
                p.attachV(this);
            }
        }
        return p;
    }

    @Override
    protected void onDestoryLazy() {
        super.onDestoryLazy();
        if (useEventBus()) {
            BusProvider.getBus().unregister(this);
        }
        if (getP() != null) {
            getP().detachV();
        }
        getvDelegate().destory();

        p = null;
        vDelegate = null;
    }


    protected RxPermissions getRxPermissions() {
        rxPermissions = new RxPermissions(getActivity());
        rxPermissions.setLogging(XDroidConf.DEV);
        return rxPermissions;
    }


    @Override
    public int getOptionsMenuId() {
        return 0;
    }


    @Override
    public boolean useEventBus() {
        return false;
    }


}
