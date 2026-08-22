package cn.nubia.systemwrapper;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.UserHandle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import cn.nubia.common.util.SharedPreferencesUtil;
import cn.nubia.gamelauncher.util.Util;
import com.android.systemui.shared.system.NubiaActivityManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class ActivityManagerWrapper {
    private static final String TAG = "ActivityManagerWrapper";
    private List<TaskChangeCallback> mCallbacks;
    private List<DataChangedListener> mDataChangedListeners;
    private NubiaActivityManager mManager;
    private ArrayList<PhoneStateCallback> mPhoneCallbacks;
    private PhoneStateListener mPhoneListener;
    private TelephonyManager mPhoneManager;
    private int mState;

    public interface DataChangedListener {
        void onDataChanged();
    }

    private static class InstanceHolder {
        private static ActivityManagerWrapper sInstance = new ActivityManagerWrapper();

        private InstanceHolder() {
        }
    }

    public interface PhoneStateCallback {
        void onCallStateChange(int i);
    }

    public interface TaskChangeCallback extends NubiaActivityManager.ITaskChangeCallback {
        @Override // com.android.systemui.shared.system.NubiaActivityManager.ITaskChangeCallback
        void onTopActivityChange(String str);
    }

    private ActivityManagerWrapper() {
        this.mCallbacks = new ArrayList();
        this.mDataChangedListeners = new ArrayList();
        this.mPhoneCallbacks = new ArrayList<>();
        this.mState = 0;
        this.mPhoneListener = new PhoneStateListener() { // from class: cn.nubia.systemwrapper.ActivityManagerWrapper.1
            @Override // android.telephony.PhoneStateListener
            public void onCallStateChanged(int i, String str) {
                ActivityManagerWrapper.this.mState = i;
                Log.i(ActivityManagerWrapper.TAG, "mState=" + ActivityManagerWrapper.this.mState);
                for (int i2 = 0; i2 < ActivityManagerWrapper.this.mPhoneCallbacks.size(); i2++) {
                    ((PhoneStateCallback) ActivityManagerWrapper.this.mPhoneCallbacks.get(i2)).onCallStateChange(i);
                }
            }
        };
        this.mManager = NubiaActivityManager.getInstance();
    }

    public static ActivityManagerWrapper getInstance() {
        return InstanceHolder.sInstance;
    }

    public void addPhoneListener(PhoneStateCallback phoneStateCallback) {
        if (phoneStateCallback == null || this.mPhoneCallbacks.contains(phoneStateCallback)) {
            return;
        }
        this.mPhoneCallbacks.add(phoneStateCallback);
        phoneStateCallback.onCallStateChange(this.mState);
    }

    public void addTaskListener(TaskChangeCallback taskChangeCallback) {
        this.mManager.addTaskListener(taskChangeCallback);
    }

    public String getCurrentPkg() {
        return this.mManager.getCurrentPkg();
    }

    public int getState() {
        return this.mState;
    }

    public String getTopPackageName() {
        return this.mManager.getCurrentPkg();
    }

    public UserHandle getUserHandle(Context context, int i) {
        return null;
    }

    public boolean isUnable(Context context) {
        return false;
    }

    public void putSetting(Context context, List<String> list, List<String> list2) {
        if (Util.isZte()) {
            String join = TextUtils.join(",", list2);
            SharedPreferencesUtil.getInstance(context).setGameCcCustomTiles(join);
            Log.i(TAG, "putSettings() specs : " + join);
        }
        Iterator<DataChangedListener> it = this.mDataChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataChanged();
            Log.d(TAG, "onDataChanged");
        }
    }

    public void registerDataChangedListener(DataChangedListener dataChangedListener) {
        List<DataChangedListener> list = this.mDataChangedListeners;
        if (list.contains(list)) {
            return;
        }
        this.mDataChangedListeners.add(dataChangedListener);
        Log.d(TAG, "registerDataChangedListener");
    }

    public void removePhoneListener(PhoneStateCallback phoneStateCallback) {
        if (phoneStateCallback == null || !this.mPhoneCallbacks.contains(phoneStateCallback)) {
            return;
        }
        this.mPhoneCallbacks.remove(phoneStateCallback);
    }

    public void start(Looper looper, Context context) {
        this.mManager.start(looper);
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        this.mPhoneManager = telephonyManager;
        telephonyManager.listen(this.mPhoneListener, 32);
    }

    public void startPipActivity(Intent intent, Context context) {
    }

    public void startPipActivityAsUser(Intent intent, Context context, UserHandle userHandle) {
    }

    public void unregisterDataChangedListener(DataChangedListener dataChangedListener) {
        this.mDataChangedListeners.remove(dataChangedListener);
    }
}
