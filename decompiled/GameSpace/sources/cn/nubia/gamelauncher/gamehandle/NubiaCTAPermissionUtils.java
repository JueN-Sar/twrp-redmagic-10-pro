package cn.nubia.gamelauncher.gamehandle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;
import cn.nubia.gamelauncher.commoninterface.ConstantVariable;
import cn.nubia.gamelauncher.util.Util;

/* loaded from: classes.dex */
public class NubiaCTAPermissionUtils {
    private static final int CTA_OPEN = 0;
    private static final String CTA_PERMISSION = "cta_permission";
    private static final String CTA_PERSIST = "persist.sys.cta.disable";
    public static final String HAS_PERMISSION = "has_permission";
    public static final String SHARED_PREFERENCES_NAME = "data";
    private static final String VIRTUAL_GAME_KEY = "virtual_game_key";
    public static Runnable mCallback;

    public static void addCallback(Runnable runnable) {
        mCallback = runnable;
    }

    public static void agreeCtaPermission(Context context) {
        Log.d("zte", " agreeCtaPermission()");
        SharedPreferences.Editor edit = context.getSharedPreferences("data", 0).edit();
        edit.putBoolean(CTA_PERMISSION, true).apply();
        ConstantVariable.HAS_PERMISSION = true;
        edit.putBoolean("has_permission", true).apply();
        Runnable runnable = mCallback;
        if (runnable != null) {
            runnable.run();
        }
    }

    public static boolean isCTAOK(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("data", 0);
        Log.d("zte", " CTASetting 1 = " + sharedPreferences.getBoolean(CTA_PERMISSION, false));
        return sharedPreferences.getBoolean(CTA_PERMISSION, false);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [cn.nubia.gamelauncher.gamehandle.NubiaCTAPermissionUtils$1] */
    public static void rejectCtaPermission(final Context context) {
        Log.d("zte", " rejectCtaPermission()");
        new AsyncTask<Void, Void, Void>() { // from class: cn.nubia.gamelauncher.gamehandle.NubiaCTAPermissionUtils.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                Settings.Global.putInt(context.getContentResolver(), NubiaCTAPermissionUtils.VIRTUAL_GAME_KEY, 0);
                if (!Util.supportVirtualGameKey() && !Util.isSwitchGameKeyToOtherFunctions()) {
                    return null;
                }
                Context context2 = context;
                if (!(context2 instanceof Activity)) {
                    return null;
                }
                ((Activity) context2).finish();
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
    }

    public static void showPermissionDialogHome(Context context) {
    }

    public static void startActivity(Context context, Intent intent) {
        if (intent == null || context == null) {
            return;
        }
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((Activity) context).finish();
    }
}
