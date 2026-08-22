package cn.nubia.common;

import android.content.Context;
import cn.nubia.common.util.CommonUtil;

/* loaded from: classes.dex */
public class CommonApplication {
    public Context mContext;

    private static class CommonApplicationHolder {
        public static final CommonApplication INSTANCE = new CommonApplication();

        private CommonApplicationHolder() {
        }
    }

    private CommonApplication() {
    }

    public static CommonApplication getInstance() {
        return CommonApplicationHolder.INSTANCE;
    }

    public Context getAppContext() {
        return this.mContext;
    }

    public void onCreate(Context context) {
        this.mContext = context.getApplicationContext();
        CommonUtil.notifyHandheldModeChanged(false);
    }
}
