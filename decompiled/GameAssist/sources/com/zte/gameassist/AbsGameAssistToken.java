package com.zte.gameassist;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.text.TextUtils;
import cn.nubia.gameassist.view.NubiaTextClock;
import com.zte.gameassist.aidl.ICallback;
import com.zte.gameassist.aidl.IGameAssist;
import com.zte.gameassist.aidl.IGameAssistController;
import com.zte.shared.common.GameLauncherHelper;
import com.zte.shared.wrapper.InputManagerWrapper;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class AbsGameAssistToken extends IGameAssist.Stub {
    public static final String BUNDLE_KEY_NAME = "bundle_key_name";
    public static final String BUNDLE_KEY_REASON = "bundle_key_reason";
    public static final String BUNDLE_KEY_RETURN = "bundle_key_return";
    public static final String BUNDLE_KEY_TYPE = "bundle_key_type";
    public static final String BUNDLE_KEY_VALUE = "bundle_key_value";
    private ICommander mCommand;
    protected final Context mContext;
    protected final Handler mHandler;

    public static class ActivityEntity {
        public final ComponentName mActivity;
        public final int mActivityType;
        public final Bundle mData;
        public final int mDisplayId;
        public final boolean mIsInGameList;
        public final int mPid;
        public final int mStackId;
        public final int mUserId;
        public final int mWindowMode;

        public ActivityEntity(ComponentName componentName, boolean z, int i2, int i3, int i4, int i5, int i6, int i7, Bundle bundle) {
            this.mActivity = componentName;
            this.mIsInGameList = z;
            this.mWindowMode = i2;
            this.mActivityType = i3;
            this.mDisplayId = i4;
            this.mUserId = i5;
            this.mPid = i6;
            this.mStackId = i7;
            this.mData = bundle;
        }

        public String toString() {
            return "ActivityEntity{mActivity=" + this.mActivity + ", mIsInGameList=" + this.mIsInGameList + ", mWindowMode=" + this.mWindowMode + ", mActivityType=" + this.mActivityType + ", mDisplayId=" + this.mDisplayId + ", mUserId=" + this.mUserId + ", mStackId=" + this.mStackId + ", mPid=" + this.mPid + '}';
        }
    }

    public static class Callback {
        private final ICallback mICallback;

        public Callback(ICallback iCallback) {
            this.mICallback = iCallback;
        }

        public void callback(String str, Bundle bundle) {
            ICallback iCallback = this.mICallback;
            if (iCallback != null) {
                try {
                    iCallback.callback(str, bundle);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public static abstract class CallbackStub extends ICallback.Stub {
        protected String mAction;
        protected Bundle mData;

        @Override // com.zte.gameassist.aidl.ICallback
        public void callback(String str, Bundle bundle) {
            this.mAction = str;
            this.mData = bundle;
            onCallback(str, bundle);
        }

        public abstract void onCallback(String str, Bundle bundle);
    }

    public static class FocuesWindow {
        public final Bundle mData;
        public final String mPackageName;
        public final Rect mVisRect;
        public final int mWindowType;

        FocuesWindow(String str, int i2, Rect rect, Bundle bundle) {
            this.mPackageName = str;
            this.mWindowType = i2;
            this.mVisRect = rect;
            this.mData = bundle;
        }

        public String toString() {
            return "FocuesWindow{mPackageName='" + this.mPackageName + NubiaTextClock.QUOTE + ", mWindowType=" + this.mWindowType + ", mVisRect=" + this.mVisRect + '}';
        }
    }

    public static class GameAssistControllerWrapper {
        protected final Context mContext;
        private final IGameAssistController mIGameAssistController;

        public GameAssistControllerWrapper(Context context, IGameAssistController iGameAssistController) {
            this.mContext = context;
            this.mIGameAssistController = iGameAssistController;
        }

        public InputManagerWrapper.ZteInputEventReceiver createInputEventReceiver(Context context, String str, Looper looper, InputManagerWrapper.InputEventListener inputEventListener) {
            try {
                IGameAssistController iGameAssistController = this.mIGameAssistController;
                if (iGameAssistController != null) {
                    return new InputManagerWrapper.ZteInputEventReceiver(iGameAssistController.getInputChannel(str, context.getDisplayId()), looper, inputEventListener);
                }
                return null;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }

        public List<String> getGameLauncherAppNameList() {
            try {
                IGameAssistController iGameAssistController = this.mIGameAssistController;
                if (iGameAssistController != null) {
                    return iGameAssistController.getGameLauncherAppNameList();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return GameLauncherHelper.c(this.mContext);
        }

        public List<String> getMutexTags(String str) {
            try {
                return this.mIGameAssistController.getMutexTags(str);
            } catch (Exception e2) {
                e2.printStackTrace();
                return new ArrayList();
            }
        }

        public void invake(String str, Bundle bundle, CallbackStub callbackStub) {
            try {
                IGameAssistController iGameAssistController = this.mIGameAssistController;
                if (iGameAssistController != null) {
                    iGameAssistController.invake(str, bundle, callbackStub);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public boolean isInGameListForResumedActivity() {
            try {
                IGameAssistController iGameAssistController = this.mIGameAssistController;
                if (iGameAssistController != null) {
                    return iGameAssistController.isInGameListForResumedActivity();
                }
                return false;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }

        public void mutexTag(boolean z, String str, String str2, MutexCallback mutexCallback) {
            try {
                this.mIGameAssistController.mutexTag(z, str, str2, mutexCallback);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public void setOverrideScreenBrightness(float f2) {
            try {
                IGameAssistController iGameAssistController = this.mIGameAssistController;
                if (iGameAssistController != null) {
                    iGameAssistController.setOverrideScreenBrightness(f2);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public interface ICommander {
        void executive(String str, Bundle bundle, Callback callback);
    }

    public static abstract class MutexCallback extends ICallback.Stub {
        @Override // com.zte.gameassist.aidl.ICallback
        public void callback(String str, Bundle bundle) {
            String[] stringArray = (bundle == null || !bundle.containsKey("mutextNames")) ? null : bundle.getStringArray("mutextNames");
            if (stringArray == null) {
                stringArray = new String[0];
            }
            onMutexCallback(str, stringArray);
        }

        public abstract void onMutexCallback(String str, String[] strArr);
    }

    public static class SystemWindow {
        public final Bundle mData;
        public final String mPackageName;
        public final String mTitle;
        public final int mWindowType;

        SystemWindow(String str, String str2, int i2, Bundle bundle) {
            this.mTitle = str;
            this.mPackageName = str2;
            this.mWindowType = i2;
            this.mData = bundle;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SystemWindow systemWindow = (SystemWindow) obj;
            if (this.mWindowType == systemWindow.mWindowType && this.mTitle.equals(systemWindow.mTitle)) {
                return this.mPackageName.equals(systemWindow.mPackageName);
            }
            return false;
        }

        public int hashCode() {
            return (((this.mTitle.hashCode() * 31) + this.mPackageName.hashCode()) * 31) + this.mWindowType;
        }

        public String toString() {
            return "SystemWindow{mTitle='" + this.mTitle + NubiaTextClock.QUOTE + ", mPackageName='" + this.mPackageName + NubiaTextClock.QUOTE + ", mWindowType=" + this.mWindowType + '}';
        }
    }

    public AbsGameAssistToken(Context context, Handler handler) {
        this.mContext = context;
        this.mHandler = handler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$invake$1(String str, Bundle bundle, ICallback iCallback) {
        this.mCommand.executive(str, bundle, iCallback != null ? new Callback(iCallback) : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$notifyActivityResumed$3(ComponentName componentName, boolean z, int i2, int i3, int i4, int i5, int i6, int i7, Bundle bundle) {
        onActivityResumed(new ActivityEntity(componentName, z, i2, i3, i4, i5, i6, i7, bundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$notifyFocuesWindowChanged$4(String str, int i2, Rect rect, Bundle bundle) {
        onFocuesWindowChanged(new FocuesWindow(str, i2, rect, bundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$notifyFullActivityFirstCreate$2(ComponentName componentName, boolean z, int i2, int i3, int i4, int i5, int i6, int i7, Bundle bundle) {
        onFullActivityFirstCreate(new ActivityEntity(componentName, z, i2, i3, i4, i5, i6, i7, bundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$notifySystemWindowChanged$5(boolean z, String str, String str2, int i2, Bundle bundle) {
        onSystemWindowChanged(z, new SystemWindow(str, str2, i2, bundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onControllerConnected$0(IGameAssistController iGameAssistController) {
        init(new GameAssistControllerWrapper(this.mContext, iGameAssistController));
    }

    protected abstract void init(GameAssistControllerWrapper gameAssistControllerWrapper);

    @Override // com.zte.gameassist.aidl.IGameAssist
    public void invake(final String str, Bundle bundle, final ICallback iCallback) {
        Parcel obtain = Parcel.obtain();
        final Bundle bundle2 = new Bundle(getClass().getClassLoader());
        try {
            try {
                bundle.writeToParcel(obtain, 0);
                obtain.setDataPosition(0);
                bundle2.readFromParcel(obtain);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (TextUtils.equals("notifySystemWindowChanged", str)) {
                if (bundle.containsKey("show")) {
                    notifySystemWindowChanged(bundle.getBoolean("show"), bundle.getString("title"), bundle.getString("package_name"), bundle.getInt("window_type"), bundle);
                    return;
                }
                return;
            }
            if (TextUtils.equals("notifyFocuesWindowChanged", str)) {
                if (bundle.containsKey("package_name")) {
                    notifyFocuesWindowChanged(bundle.getString("package_name"), bundle.getInt("window_type"), (Rect) bundle.getParcelable("vis_rect", Rect.class), bundle);
                    return;
                }
                return;
            }
            if (TextUtils.equals("notifyFullActivityFirstCreate", str)) {
                if (bundle.containsKey("isInGameList")) {
                    notifyFullActivityFirstCreate((ComponentName) bundle.getParcelable("activity", ComponentName.class), bundle.getBoolean("isInGameList"), bundle.getInt("window_mode"), bundle.getInt("activity_type"), bundle.getInt("display_id"), bundle.getInt("user_id"), bundle.getInt("pid"), bundle.getInt("stack_id"), bundle);
                }
            } else {
                if (TextUtils.equals("notifyActivityResumed", str)) {
                    if (bundle.containsKey("isInGameList")) {
                        notifyActivityResumed((ComponentName) bundle.getParcelable("activity", ComponentName.class), bundle.getBoolean("isInGameList"), bundle.getInt("window_mode"), bundle.getInt("activity_type"), bundle.getInt("display_id"), bundle.getInt("user_id"), bundle.getInt("pid"), bundle.getInt("stack_id"), bundle);
                        return;
                    }
                    return;
                }
                ICommander iCommander = this.mCommand;
                if (iCommander != null) {
                    Handler handler = this.mHandler;
                    if (handler == null) {
                        iCommander.executive(str, bundle2, iCallback != null ? new Callback(iCallback) : null);
                    } else {
                        handler.post(new Runnable() { // from class: com.zte.gameassist.b
                            @Override // java.lang.Runnable
                            public final void run() {
                                AbsGameAssistToken.this.lambda$invake$1(str, bundle2, iCallback);
                            }
                        });
                    }
                }
            }
        } finally {
            obtain.recycle();
        }
    }

    @Override // com.zte.gameassist.aidl.IGameAssist
    public void notifyActivityResumed(ComponentName componentName, boolean z, int i2, int i3, int i4, int i5, int i6) {
        notifyActivityResumed(componentName, z, i2, i3, i4, i5, i6, -1, null);
    }

    @Override // com.zte.gameassist.aidl.IGameAssist
    public void notifyFocuesWindowChanged(String str, int i2, Rect rect) {
        notifyFocuesWindowChanged(str, i2, rect, null);
    }

    @Override // com.zte.gameassist.aidl.IGameAssist
    public void notifyFullActivityFirstCreate(ComponentName componentName, boolean z, int i2, int i3, int i4, int i5, int i6) {
        notifyFullActivityFirstCreate(componentName, z, i2, i3, i4, i5, i6, -1, null);
    }

    @Override // com.zte.gameassist.aidl.IGameAssist
    public void notifySystemWindowChanged(boolean z, String str, String str2, int i2) {
        notifySystemWindowChanged(z, str, str2, i2, null);
    }

    protected abstract void onActivityResumed(ActivityEntity activityEntity);

    @Override // com.zte.gameassist.aidl.IGameAssist
    public void onControllerConnected(final IGameAssistController iGameAssistController) {
        Handler handler = this.mHandler;
        if (handler == null) {
            init(new GameAssistControllerWrapper(this.mContext, iGameAssistController));
        } else {
            handler.post(new Runnable() { // from class: com.zte.gameassist.f
                @Override // java.lang.Runnable
                public final void run() {
                    AbsGameAssistToken.this.lambda$onControllerConnected$0(iGameAssistController);
                }
            });
        }
    }

    protected abstract void onFocuesWindowChanged(FocuesWindow focuesWindow);

    protected abstract void onFullActivityFirstCreate(ActivityEntity activityEntity);

    protected abstract void onSystemWindowChanged(boolean z, SystemWindow systemWindow);

    public void setCommander(ICommander iCommander) {
        this.mCommand = iCommander;
    }

    private void notifyActivityResumed(final ComponentName componentName, final boolean z, final int i2, final int i3, final int i4, final int i5, final int i6, final int i7, final Bundle bundle) {
        Handler handler = this.mHandler;
        if (handler == null) {
            onActivityResumed(new ActivityEntity(componentName, z, i2, i3, i4, i5, i6, i7, bundle));
        } else {
            handler.post(new Runnable() { // from class: com.zte.gameassist.c
                @Override // java.lang.Runnable
                public final void run() {
                    AbsGameAssistToken.this.lambda$notifyActivityResumed$3(componentName, z, i2, i3, i4, i5, i6, i7, bundle);
                }
            });
        }
    }

    private void notifyFocuesWindowChanged(final String str, final int i2, final Rect rect, final Bundle bundle) {
        Handler handler = this.mHandler;
        if (handler == null) {
            onFocuesWindowChanged(new FocuesWindow(str, i2, rect, bundle));
        } else {
            handler.post(new Runnable() { // from class: com.zte.gameassist.d
                @Override // java.lang.Runnable
                public final void run() {
                    AbsGameAssistToken.this.lambda$notifyFocuesWindowChanged$4(str, i2, rect, bundle);
                }
            });
        }
    }

    private void notifyFullActivityFirstCreate(final ComponentName componentName, final boolean z, final int i2, final int i3, final int i4, final int i5, final int i6, final int i7, final Bundle bundle) {
        Handler handler = this.mHandler;
        if (handler == null) {
            onFullActivityFirstCreate(new ActivityEntity(componentName, z, i2, i3, i4, i5, i6, i7, bundle));
        } else {
            handler.post(new Runnable() { // from class: com.zte.gameassist.e
                @Override // java.lang.Runnable
                public final void run() {
                    AbsGameAssistToken.this.lambda$notifyFullActivityFirstCreate$2(componentName, z, i2, i3, i4, i5, i6, i7, bundle);
                }
            });
        }
    }

    public void notifySystemWindowChanged(final boolean z, final String str, final String str2, final int i2, final Bundle bundle) {
        Handler handler = this.mHandler;
        if (handler == null) {
            onSystemWindowChanged(z, new SystemWindow(str, str2, i2, bundle));
        } else {
            handler.post(new Runnable() { // from class: com.zte.gameassist.a
                @Override // java.lang.Runnable
                public final void run() {
                    AbsGameAssistToken.this.lambda$notifySystemWindowChanged$5(z, str, str2, i2, bundle);
                }
            });
        }
    }

    public AbsGameAssistToken(Context context, Handler handler, ICommander iCommander) {
        this(context, handler);
        this.mCommand = iCommander;
    }
}
