package cn.nubia.gamelauncher.aimhelper;

import android.text.TextUtils;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.util.CommonUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public class GameWhiteList {
    private static final String TAG = "GameWhiteList";
    private static final Object mLock = new Object();
    private static Set<String> PACKAGES = new HashSet(Arrays.asList("com.tencent.af", "com.tencent.tmgp.cf", HighLightsUtils.CJZC_PACKAGE_NAME, HighLightsUtils.PUBG_PACKAGE_NAME));
    private static Set<String> ADAPTER_PACKAGES = new HashSet(Arrays.asList("com.tencent.af", "com.tencent.tmgp.cf", HighLightsUtils.CJZC_PACKAGE_NAME, HighLightsUtils.PUBG_PACKAGE_NAME));

    public static boolean isGameActivity(String str) {
        return true;
    }

    public static boolean isSupportGame(String str) {
        boolean contains;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        synchronized (mLock) {
            contains = PACKAGES.contains(str);
        }
        return contains;
    }

    public static void syncPackages(List<AppListItemBean> list) {
        StringBuilder sb = new StringBuilder("syncPackages=[");
        synchronized (mLock) {
            ArrayList arrayList = new ArrayList();
            if (list != null) {
                for (AppListItemBean appListItemBean : list) {
                    sb.append(appListItemBean.getComponentName());
                    arrayList.add(CommonUtil.convertPackageName(appListItemBean.getComponentName()));
                }
            }
            PACKAGES.clear();
            PACKAGES.addAll(ADAPTER_PACKAGES);
            PACKAGES.addAll(arrayList);
        }
        sb.append("]");
        LogUtil.d(TAG, sb.toString());
    }
}
