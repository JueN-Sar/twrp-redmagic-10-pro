package cn.nubia.common.helper;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import cn.nubia.common.CommonApplication;
import cn.nubia.common.util.WorkThread;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class HideAppsHelper {
    public static final String AUTHORITY_PRIVACY = "com.zte.heartyservice.apphide.provider";
    public static final Uri HIDE_APPS_URL = Uri.parse("content://com.zte.heartyservice.apphide.provider/app/hide_apps_str");
    private static final String TAG = "HideApps";
    private ArrayList<Runnable> mCallbacks;
    Handler mHandler;
    private CopyOnWriteArrayList<String> mHideApps;
    private String mHideAppsString;

    private static class HideAppsHolder {
        public static final HideAppsHelper INSTANCE = new HideAppsHelper();

        private HideAppsHolder() {
        }
    }

    private HideAppsHelper() {
        this.mCallbacks = new ArrayList<>();
        this.mHideApps = new CopyOnWriteArrayList<>();
        this.mHideAppsString = "";
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    private void addAppsToList() {
        this.mHideApps.clear();
        if (TextUtils.isEmpty(this.mHideAppsString)) {
            return;
        }
        for (String str : this.mHideAppsString.split(";")) {
            if (!TextUtils.isEmpty(str)) {
                this.mHideApps.add(str);
                Log.d(TAG, "updateHideApps() pkg : " + str);
            }
        }
    }

    private Context getContext() {
        return CommonApplication.getInstance().getAppContext();
    }

    public static HideAppsHelper getInstance() {
        return HideAppsHolder.INSTANCE;
    }

    private void notifyDataChanged() {
        Log.d(TAG, "notifyDataChanged()");
        Iterator<Runnable> it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            this.mHandler.post(it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateHideApps() {
        try {
            this.mHideAppsString = getContext().getContentResolver().getType(HIDE_APPS_URL);
        } catch (Exception e) {
            Log.w(TAG, "updateHideApps() Exception : " + e.getMessage());
        }
        Log.d(TAG, "updateHideApps() mHideAppsString : " + this.mHideAppsString);
        addAppsToList();
        notifyDataChanged();
    }

    public void addCallback(Runnable runnable) {
        Log.d(TAG, "addCallback() runnable : " + runnable);
        if (this.mCallbacks.contains(runnable)) {
            return;
        }
        this.mCallbacks.add(runnable);
        update();
    }

    public void exit() {
        Log.d(TAG, "exit()");
        this.mCallbacks.clear();
    }

    public CopyOnWriteArrayList getHideApps() {
        return this.mHideApps;
    }

    public boolean isHideApp(String str) {
        Iterator<String> it = this.mHideApps.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next.contains(str) && !next.contains("#999")) {
                return true;
            }
        }
        return false;
    }

    public boolean isHideCloneApp(String str) {
        if (TextUtils.isEmpty(this.mHideAppsString)) {
            return false;
        }
        String str2 = str + "#999";
        boolean contains = this.mHideAppsString.contains(str2);
        Log.d(TAG, "isHideCloneApp() cloneAppPkg : " + str2 + ", isHideCloneApp : " + contains);
        return contains;
    }

    public void removeCallback(Runnable runnable) {
        if (this.mCallbacks.contains(runnable)) {
            this.mCallbacks.remove(runnable);
        }
    }

    public void update() {
        Log.d(TAG, "update()");
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.common.helper.HideAppsHelper$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                HideAppsHelper.this.updateHideApps();
            }
        });
    }
}
