package cn.nubia.gamelauncher.neostore;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.neostore.api.NubiaNgcApi;
import cn.nubia.neostore.api.callback.ICallback;
import cn.nubia.neostore.api.model.CallbackInfo;
import cn.nubia.neostore.api.model.NubiaGameCardInfo;
import cn.nubia.neostore.api.model.NubiaGameNotice;
import java.util.List;

/* loaded from: classes.dex */
public class NeoHelper {
    private static boolean isInit = false;

    public static void checkUnionNewData(Context context, ICallback<Boolean> iCallback) {
        if (isNotSupportNubiaNgcApi()) {
            return;
        }
        initIfNeed();
        NubiaNgcApi.checkUnionNewData(context, iCallback);
    }

    public static void getCardList(Context context, ICallback<List<NubiaGameCardInfo>> iCallback) {
        if (isNotSupportNubiaNgcApi()) {
            return;
        }
        NubiaNgcApi.getCardList(context, iCallback);
    }

    public static void getDialogNotice(Context context, ICallback<NubiaGameNotice> iCallback) {
        if (isNotSupportNubiaNgcApi()) {
            return;
        }
        initIfNeed();
        NubiaNgcApi.getDialogNotice(context, iCallback);
    }

    public static void getGiftListForPackage(String str, String str2, int i, ICallback<CallbackInfo> iCallback) {
        if (isNotSupportNubiaNgcApi()) {
            return;
        }
        initIfNeed();
        Log.d("Full", "--------->getGiftListForPackage() packageName : " + str2);
        NubiaNgcApi.getGiftListForPackage(str, str2, i, iCallback);
    }

    public static void getRevelantAppByPackageName(String str, int i, ICallback<CallbackInfo> iCallback) {
        if (isNotSupportNubiaNgcApi()) {
            return;
        }
        initIfNeed();
        Log.d("Full", "--------->getRevelantAppByPackageName() packageName : " + str);
        NubiaNgcApi.getRevelantAppByPackageName(str, i, iCallback);
    }

    public static void init(Application application) {
        if (isNotSupportNubiaNgcApi()) {
            return;
        }
        LogUtil.d("neo", "NeoHelper.init()");
        NubiaNgcApi.init(application);
        isInit = true;
    }

    public static void initIfNeed() {
        if (isInit) {
            return;
        }
        init((Application) GameLauncherApplication.getAppContext());
    }

    public static boolean isNotSupportNubiaNgcApi() {
        return CommonUtil.isInternalVersion();
    }

    public static void isVipGame(String str, ICallback<CallbackInfo> iCallback) {
        if (isNotSupportNubiaNgcApi()) {
            return;
        }
        initIfNeed();
        Log.d("Full", "--------->isVipGame() packageName : " + str);
        NubiaNgcApi.isVipGame(str, iCallback);
    }

    public static void startDialogNotice(Activity activity) {
        if (isNotSupportNubiaNgcApi()) {
            return;
        }
        initIfNeed();
        NubiaNgcApi.startDialogNotice(activity);
    }

    public static void startGameCardSofts(Activity activity, int i, String str) {
        if (isNotSupportNubiaNgcApi()) {
            return;
        }
        NubiaNgcApi.startGameCardSofts(activity, i, str);
    }

    public static void startRedMagicUnionActivity(Activity activity, int i) {
        if (isNotSupportNubiaNgcApi()) {
            return;
        }
        NubiaNgcApi.startRedMagicUnionActivity(activity, i);
    }
}
