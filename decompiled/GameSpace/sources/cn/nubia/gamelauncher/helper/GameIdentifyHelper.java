package cn.nubia.gamelauncher.helper;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class GameIdentifyHelper {
    public static ArrayList<String> BLACK_APP_LIST = null;
    private static final String TAG = "GameIdentifyHelper";
    private CopyOnWriteArrayList<AppListItemBean> mList;

    private static class GameIdentifyHolder {
        public static final GameIdentifyHelper INSTANCE = new GameIdentifyHelper();

        private GameIdentifyHolder() {
        }
    }

    static {
        ArrayList<String> arrayList = new ArrayList<>();
        BLACK_APP_LIST = arrayList;
        arrayList.add("com.antutu.benchmark.full");
        BLACK_APP_LIST.add("com.antutu.benchmark.full.lite");
        BLACK_APP_LIST.add("com.tencent.mobileqq");
        BLACK_APP_LIST.add("com.youdao.dict");
        BLACK_APP_LIST.add("com.qiyi.video");
        BLACK_APP_LIST.add("com.qiyi.video.pad");
        BLACK_APP_LIST.add("com.lofter.android");
        BLACK_APP_LIST.add("com.netease.gl");
        BLACK_APP_LIST.add("com.rytong.airchina");
        BLACK_APP_LIST.add("com.jd.jdhealth");
        BLACK_APP_LIST.add("com.netease.mobimail");
        BLACK_APP_LIST.add("com.netease.mail");
        BLACK_APP_LIST.add("com.mi.car.mobile");
        BLACK_APP_LIST.add("com.youdao.translator");
        BLACK_APP_LIST.add("com.qihoo.dr");
        BLACK_APP_LIST.add("com.jxedt");
        BLACK_APP_LIST.add("com.tencent.wetype");
        BLACK_APP_LIST.add("com.agc.gcam_tools");
        BLACK_APP_LIST.add("com.v2ray.ang");
        BLACK_APP_LIST.add("com.mfcloudcalculate.networkdisk");
        BLACK_APP_LIST.add("com.ffffstudio.kojicam");
        BLACK_APP_LIST.add("com.baidu.netdisk");
        BLACK_APP_LIST.add("tv.danmaku.bili");
        BLACK_APP_LIST.add("com.ss.android.ugc.aweme");
        BLACK_APP_LIST.add("com.larus.nova");
        BLACK_APP_LIST.add("com.zhishudongli.inkako");
        BLACK_APP_LIST.add("com.autonavi.minimap");
        BLACK_APP_LIST.add("com.dengziwl.bk");
        BLACK_APP_LIST.add("com.vivo.easyshare");
        BLACK_APP_LIST.add("com.lemon.lv");
        BLACK_APP_LIST.add("com.coolapk.market");
        BLACK_APP_LIST.add("com.quark.browser");
        BLACK_APP_LIST.add("com.zdbq.ljtq.ljweather");
        BLACK_APP_LIST.add("com.mt.mtxx.mtxx");
        BLACK_APP_LIST.add("photo.editor.polarr");
        BLACK_APP_LIST.add("com.twtapp");
        BLACK_APP_LIST.add("com.agc.gcam.nanren");
        BLACK_APP_LIST.add("com.netease.cloudmusic");
        BLACK_APP_LIST.add("com.sina.weibo");
        BLACK_APP_LIST.add("com.vivo.gallery");
        BLACK_APP_LIST.add("com.agc.gcam85");
        BLACK_APP_LIST.add("com.agc.gcam88");
        BLACK_APP_LIST.add("com.agc.gcam92");
        BLACK_APP_LIST.add("com.xiangtian.pixcake");
        BLACK_APP_LIST.add("com.xingin.xhs");
        BLACK_APP_LIST.add("com.xiaomi.account");
        BLACK_APP_LIST.add("ccom.xiaomi.smarthome");
        BLACK_APP_LIST.add("com.movie.yqs20251128030446");
        BLACK_APP_LIST.add("com.tencent.qqmusiclite.universal");
        BLACK_APP_LIST.add("www.imxiaoyu.com.musiceditor");
        BLACK_APP_LIST.add("io.legado.app.release");
        BLACK_APP_LIST.add("com.iudesk.android.photo.editor");
        BLACK_APP_LIST.add("com.adobe.psmobile");
        BLACK_APP_LIST.add("editingapp.pictureeditor.photoeditor");
        BLACK_APP_LIST.add("com.xt.retouchoversea");
        BLACK_APP_LIST.add("com.adobe.lrmobile");
        BLACK_APP_LIST.add("com.tencent.mobileqq");
        BLACK_APP_LIST.add("com.accordion.pro.camera");
        BLACK_APP_LIST.add("com.niksoftware.snapseed");
        BLACK_APP_LIST.add("com.UCMobile");
        BLACK_APP_LIST.add("mark.via");
        BLACK_APP_LIST.add("com.tencent.mm");
        BLACK_APP_LIST.add("com.masstest");
        BLACK_APP_LIST.add("com.duolingo");
        BLACK_APP_LIST.add("com.handsgo.jiakao.android");
    }

    private GameIdentifyHelper() {
        init();
    }

    private ApplicationInfo getApplicationInfo(String str) {
        try {
            return getContext().getPackageManager().getPackageInfo(str, 0).applicationInfo;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Failed to get application info", e);
            return null;
        }
    }

    private Context getContext() {
        return GameLauncherApplication.getAppContext();
    }

    public static GameIdentifyHelper getInstance() {
        return GameIdentifyHolder.INSTANCE;
    }

    private boolean hasGameEngine(String str) {
        if (str == null) {
            return false;
        }
        return hasGameEngineByAppInfo(getApplicationInfo(str));
    }

    private boolean hasGameEngineByAppInfo(ApplicationInfo applicationInfo) {
        if (applicationInfo == null || applicationInfo.nativeLibraryDir == null) {
            Log.d(TAG, "hasGameEngineSo() nativeLibraryDir is null !");
            return false;
        }
        String[] list = new File(applicationInfo.nativeLibraryDir).list();
        if (list == null || list.length == 0) {
            return false;
        }
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        int i = 0;
        for (String str : list) {
            if (str.contains("libunity.so") || str.contains("unity.so")) {
                Log.d(TAG, "=====>hasGameEngineByAppInfo() unity.so");
                z = true;
            }
            if (str.contains("libmono.so")) {
                Log.d(TAG, "=====>hasGameEngineByAppInfo() mono.so");
                z2 = true;
            }
            if (str.contains("libil2cpp.so")) {
                Log.d(TAG, "=====>hasGameEngineByAppInfo() il2cpp.so");
                z3 = true;
            }
            if (str.contains("libmain.so")) {
                Log.d(TAG, "=====>hasGameEngineByAppInfo() main.so");
                z4 = true;
            }
            if (str.contains("libUE4.so") || str.contains("libUE5.so")) {
                Log.d(TAG, "=====>hasGameEngineByAppInfo() find case 2");
            } else if (str.contains("cocos2d.so")) {
                Log.d(TAG, "=====>hasGameEngineByAppInfo() find case 3");
            } else if (str.contains("libgame.so") || str.contains("libGame.so") || str.contains("libhegame.so")) {
                Log.d(TAG, "=====>hasGameEngineByAppInfo() find case 4");
            }
            i++;
        }
        if (z && (z2 || z3 || z4)) {
            String str2 = applicationInfo.packageName;
            if (str2 == null || !isUnityLauncherApp(str2)) {
                Log.d(TAG, "=====>hasGameEngineByAppInfo() unity libs found but launcher not unity, skip");
            } else {
                Log.d(TAG, "=====>hasGameEngineByAppInfo() find case 1 ");
                i++;
            }
        }
        Log.d(TAG, "=====>hasGameEngineByAppInfo() findCount : " + i + " package : " + applicationInfo.packageName);
        return i > 0;
    }

    private void init() {
    }

    private boolean isGameByAppCategory(String str) {
        try {
            ApplicationInfo applicationInfo = getContext().getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                int i = applicationInfo.category;
                Log.i(TAG, "isGameByAppCategory pkgName " + str + " 's applicationInfo.category = " + i);
                return i == 0;
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "NameNotFoundException", e);
        }
        return false;
    }

    private boolean isGameByFlag(String str) {
        try {
            PackageInfo packageInfo = getContext().getPackageManager().getPackageInfo(str, 0);
            if (packageInfo == null) {
                return false;
            }
            boolean z = (packageInfo.applicationInfo.flags & WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_CONSUME_IME_INSETS) != 0;
            if (str != null && str.contains("com.antutu")) {
                return false;
            }
            Log.d(TAG, "isGameApp(" + str + ") isGame : " + z);
            return z;
        } catch (Exception e) {
            Log.e(TAG, "isGameApp(" + str + ") Exception : ", e);
            return false;
        }
    }

    private boolean isUnityLauncherApp(String str) {
        String className;
        try {
            Intent launchIntentForPackage = getContext().getPackageManager().getLaunchIntentForPackage(str);
            if (launchIntentForPackage == null || launchIntentForPackage.getComponent() == null || (className = launchIntentForPackage.getComponent().getClassName()) == null) {
                return false;
            }
            boolean contains = className.toLowerCase().contains("unity");
            Log.d(TAG, "isUnityLauncherApp(" + str + ") launcher = " + className + ", result = " + contains);
            return contains;
        } catch (Exception e) {
            Log.e(TAG, "isUnityLauncherApp(" + str + ") exception", e);
            return false;
        }
    }

    public void doCheck(CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList) {
        if (copyOnWriteArrayList == null) {
            Log.e(TAG, "List is null");
            return;
        }
        this.mList = copyOnWriteArrayList;
        Iterator<AppListItemBean> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            String packageName = next.getPackageName();
            Log.d(TAG, "---->check(" + next.getName() + ") pkg : " + packageName);
            hasGameEngineByAppInfo(getApplicationInfo(packageName));
        }
    }

    public boolean isGameByFeatures(AppListItemBean appListItemBean) {
        if (appListItemBean == null) {
            return false;
        }
        return isGameByFeatures(appListItemBean.getPackageName(), appListItemBean.getName());
    }

    public boolean isGameByFeatures(String str, String str2) {
        if (str != null && !isInBlackAppList(str)) {
            if (hasGameEngine(str)) {
                Log.d(TAG, "---->isGameByFeatures(" + str2 + ") - has Game Engine!");
                return true;
            }
            if (isGameByFlag(str)) {
                Log.d(TAG, "---->isGameByFeatures(" + str2 + ") - has Game Flag!");
                return true;
            }
            if (isGameByAppCategory(str)) {
                Log.d(TAG, "---->isGameByFeatures(" + str2 + ") - has Game App Category!");
                return true;
            }
            Log.d(TAG, "---->isGameByFeatures(" + str2 + ") - false!");
        }
        return false;
    }

    public boolean isInBlackAppList(String str) {
        if (str == null) {
            return true;
        }
        Iterator<String> it = BLACK_APP_LIST.iterator();
        while (it.hasNext()) {
            if (it.next().equals(str)) {
                return true;
            }
        }
        return false;
    }
}
