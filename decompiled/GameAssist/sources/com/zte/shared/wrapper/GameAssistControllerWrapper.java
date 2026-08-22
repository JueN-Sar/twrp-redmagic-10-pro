package com.zte.shared.wrapper;

import android.app.ActivityThread;
import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.Settings;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.GameAssistWrapper;
import com.zte.gameassist.aidl.ICallback;
import com.zte.gameassist.aidl.IGameAssistController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public class GameAssistControllerWrapper {
    private static final Map<Object, SystemCallback> mSystemCallbackMap = new HashMap();

    public static abstract class Callback {
        private final GameAssistWrapper.Callback mToken = new GameAssistWrapper.Callback() { // from class: com.zte.shared.wrapper.GameAssistControllerWrapper.Callback.1
            public void callback(Bundle bundle) {
                Callback.this.onCallback(bundle);
            }
        };

        protected abstract void onCallback(Bundle bundle);
    }

    private static final class FileCallback {
        private static final List<FileCallback> mCallbacks = new ArrayList();
        private final ICallback mToken = new ICallback.Stub() { // from class: com.zte.shared.wrapper.GameAssistControllerWrapper.FileCallback.1
            @Override // com.zte.gameassist.aidl.ICallback
            public void callback(String str, Bundle bundle) {
                if ("read_file".equals(str)) {
                    String string = bundle.getString(AbsGameAssistToken.BUNDLE_KEY_VALUE, "");
                    if (FileCallback.this.returnFuntion != null) {
                        FileCallback.this.returnFuntion.accept(string);
                    }
                    if (FileCallback.mCallbacks.contains(this)) {
                        FileCallback.mCallbacks.remove(this);
                    }
                }
            }
        };
        private final Consumer<String> returnFuntion;

        public FileCallback(Consumer<String> consumer) {
            this.returnFuntion = consumer;
            List<FileCallback> list = mCallbacks;
            if (list.contains(this)) {
                return;
            }
            list.add(this);
        }
    }

    public interface FocusWindowCallback {
        void onFocusWindowChanged(String str, int i2, Rect rect, Bundle bundle);
    }

    public interface FullActivityFirstCreateCallback {
        void onFullActivityFirstCreate(ComponentName componentName, ActivityInfo activityInfo, int i2, int i3, int i4, int i5, int i6, int i7, Bundle bundle);
    }

    public interface FullActivityResumedCallback {
        void onFullActivityResumed(ComponentName componentName, ActivityInfo activityInfo, int i2, int i3, int i4, int i5, int i6, int i7, Bundle bundle);
    }

    public static abstract class MutexCallback {
        private final GameAssistWrapper.MutexCallback mToken = new GameAssistWrapper.MutexCallback() { // from class: com.zte.shared.wrapper.GameAssistControllerWrapper.MutexCallback.1
            public void onMutexCallback(String str, ArrayList<String> arrayList) {
                MutexCallback.this.onMutexCallback(str, arrayList);
            }
        };

        protected abstract void onMutexCallback(String str, ArrayList<String> arrayList);
    }

    public static class SystemCallback extends ICallback.Stub {
        private final FocusWindowCallback mFocusWindowCallback;
        private final FullActivityFirstCreateCallback mFullActivityFirstCreateCallback;
        private final FullActivityResumedCallback mFullActivityResumedCallback;
        private final SystemWindowCallback mSystemWindowCallback;

        public SystemCallback(SystemWindowCallback systemWindowCallback, FocusWindowCallback focusWindowCallback, FullActivityFirstCreateCallback fullActivityFirstCreateCallback, FullActivityResumedCallback fullActivityResumedCallback) {
            this.mSystemWindowCallback = systemWindowCallback;
            this.mFocusWindowCallback = focusWindowCallback;
            this.mFullActivityFirstCreateCallback = fullActivityFirstCreateCallback;
            this.mFullActivityResumedCallback = fullActivityResumedCallback;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.zte.gameassist.aidl.ICallback
        public void callback(String str, Bundle bundle) {
            char c2;
            if (bundle == null || str == null) {
                return;
            }
            switch (str.hashCode()) {
                case 500814517:
                    if (str.equals("systemWindowChanged")) {
                        c2 = 3;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 547798587:
                    if (str.equals("focuesWindowChanged")) {
                        c2 = 2;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 838878766:
                    if (str.equals("fullActivityFirstCreate")) {
                        c2 = 1;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 1623335880:
                    if (str.equals("activityResumed")) {
                        c2 = 0;
                        break;
                    }
                    c2 = 65535;
                    break;
                default:
                    c2 = 65535;
                    break;
            }
            if (c2 == 0) {
                if (this.mFullActivityResumedCallback != null && bundle.containsKey("window_mode") && bundle.containsKey("activity_type") && bundle.containsKey("display_id") && bundle.containsKey("pid") && bundle.containsKey("user_id") && bundle.containsKey("activity") && bundle.containsKey("info")) {
                    this.mFullActivityResumedCallback.onFullActivityResumed((ComponentName) bundle.getParcelable("activity", ComponentName.class), (ActivityInfo) bundle.getParcelable("info", ActivityInfo.class), bundle.getInt("task_id", -1), bundle.getInt("user_id"), bundle.getInt("pid"), bundle.getInt("display_id"), bundle.getInt("activity_type"), bundle.getInt("window_mode"), bundle);
                    return;
                }
                return;
            }
            if (c2 == 1) {
                if (this.mFullActivityFirstCreateCallback != null && bundle.containsKey("window_mode") && bundle.containsKey("activity_type") && bundle.containsKey("display_id") && bundle.containsKey("pid") && bundle.containsKey("user_id") && bundle.containsKey("activity") && bundle.containsKey("info")) {
                    this.mFullActivityFirstCreateCallback.onFullActivityFirstCreate((ComponentName) bundle.getParcelable("activity", ComponentName.class), (ActivityInfo) bundle.getParcelable("info", ActivityInfo.class), bundle.getInt("task_id", -1), bundle.getInt("user_id"), bundle.getInt("pid"), bundle.getInt("display_id"), bundle.getInt("activity_type"), bundle.getInt("window_mode"), bundle);
                    return;
                }
                return;
            }
            if (c2 == 2) {
                if (this.mFocusWindowCallback != null && bundle.containsKey("package_name") && bundle.containsKey("window_type") && bundle.containsKey("vis_rect")) {
                    this.mFocusWindowCallback.onFocusWindowChanged(bundle.getString("package_name"), bundle.getInt("window_type"), (Rect) bundle.getParcelable("vis_rect", Rect.class), bundle);
                    return;
                }
                return;
            }
            if (c2 == 3 && this.mSystemWindowCallback != null && bundle.containsKey("show") && bundle.containsKey("title") && bundle.containsKey("package_name") && bundle.containsKey("window_type")) {
                this.mSystemWindowCallback.onSystemWindowChanged(bundle.getBoolean("show"), bundle.getString("title"), bundle.getString("package_name"), bundle.getInt("window_type"), bundle);
            }
        }

        public void registerAppCallback() {
            try {
                GameAssistControllerWrapper.getGameAssistController().invake("register_app_callback", null, this);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public void unregisterAppCallback() {
            try {
                GameAssistControllerWrapper.getGameAssistController().invake("unregister_app_callback", null, this);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public interface SystemWindowCallback {
        void onSystemWindowChanged(boolean z, String str, String str2, int i2, Bundle bundle);
    }

    public static IGameAssistController getGameAssistController() {
        if (!ZteFeatureWrapper.ZTE_FEATURE_MAGIC_GAME_ASSIST) {
            return null;
        }
        GameAssistWrapper.getInstance();
        return GameAssistWrapper.getGameAssistController();
    }

    public static List<String> getMutexTags(String str) {
        GameAssistWrapper.getInstance();
        return GameAssistWrapper.getMutexTags(str);
    }

    public static void invake(String str, Bundle bundle, Callback callback) {
        GameAssistWrapper.getInstance().invake(str, bundle, callback.mToken);
    }

    public static boolean isGameScene() {
        return Settings.Global.getInt(ActivityThread.currentApplication().getContentResolver(), "nubia_game_scene", 0) == 1;
    }

    public static void monitorMutexTag(boolean z, String str, MutexCallback mutexCallback) {
        GameAssistWrapper.getInstance().monitorMutexTag(z, str, mutexCallback.mToken);
    }

    public static void mutexTag(boolean z, String str, String str2) {
        GameAssistWrapper.getInstance().mutexTag(z, str, str2);
    }

    public static void readNode(String str, Consumer<String> consumer) {
        if (consumer != null) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString(AbsGameAssistToken.BUNDLE_KEY_NAME, str);
                getGameAssistController().invake("read_file", bundle, new FileCallback(consumer).mToken);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void registerFocusWindowCallback(FocusWindowCallback focusWindowCallback) {
        Map<Object, SystemCallback> map = mSystemCallbackMap;
        if (map.containsKey(focusWindowCallback)) {
            return;
        }
        SystemCallback systemCallback = new SystemCallback(null, focusWindowCallback, null, null);
        map.put(focusWindowCallback, systemCallback);
        systemCallback.registerAppCallback();
    }

    public static void registerFullActivityFirstCreateCallback(FullActivityFirstCreateCallback fullActivityFirstCreateCallback) {
        Map<Object, SystemCallback> map = mSystemCallbackMap;
        if (map.containsKey(fullActivityFirstCreateCallback)) {
            return;
        }
        SystemCallback systemCallback = new SystemCallback(null, null, fullActivityFirstCreateCallback, null);
        map.put(fullActivityFirstCreateCallback, systemCallback);
        systemCallback.registerAppCallback();
    }

    public static void registerFullActivityResumedCallback(FullActivityResumedCallback fullActivityResumedCallback) {
        Map<Object, SystemCallback> map = mSystemCallbackMap;
        if (map.containsKey(fullActivityResumedCallback)) {
            return;
        }
        SystemCallback systemCallback = new SystemCallback(null, null, null, fullActivityResumedCallback);
        map.put(fullActivityResumedCallback, systemCallback);
        systemCallback.registerAppCallback();
    }

    public static void registerSystemWindowCallback(SystemWindowCallback systemWindowCallback) {
        Map<Object, SystemCallback> map = mSystemCallbackMap;
        if (map.containsKey(systemWindowCallback)) {
            return;
        }
        SystemCallback systemCallback = new SystemCallback(systemWindowCallback, null, null, null);
        map.put(systemWindowCallback, systemCallback);
        systemCallback.registerAppCallback();
    }

    public static boolean sendToGameAssistServices(String str, Bundle bundle) {
        return GameAssistWrapper.getInstance().sendToGameAssistServices(str, bundle);
    }

    public static void unregisterFocusWindowCallback(FocusWindowCallback focusWindowCallback) {
        Map<Object, SystemCallback> map = mSystemCallbackMap;
        if (map.containsKey(focusWindowCallback)) {
            map.remove(focusWindowCallback).unregisterAppCallback();
        }
    }

    public static void unregisterFullActivityFirstCreateCallback(FullActivityFirstCreateCallback fullActivityFirstCreateCallback) {
        Map<Object, SystemCallback> map = mSystemCallbackMap;
        if (map.containsKey(fullActivityFirstCreateCallback)) {
            map.remove(fullActivityFirstCreateCallback).unregisterAppCallback();
        }
    }

    public static void unregisterFullActivityResumedCallback(FullActivityResumedCallback fullActivityResumedCallback) {
        Map<Object, SystemCallback> map = mSystemCallbackMap;
        if (map.containsKey(fullActivityResumedCallback)) {
            map.remove(fullActivityResumedCallback).unregisterAppCallback();
        }
    }

    public static void unregisterSystemWindowCallback(SystemWindowCallback systemWindowCallback) {
        Map<Object, SystemCallback> map = mSystemCallbackMap;
        if (map.containsKey(systemWindowCallback)) {
            map.remove(systemWindowCallback).unregisterAppCallback();
        }
    }

    public static void writeNode(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(AbsGameAssistToken.BUNDLE_KEY_NAME, str);
        bundle.putString(AbsGameAssistToken.BUNDLE_KEY_VALUE, str2);
        GameAssistWrapper.getInstance().invake("write_file", bundle, (GameAssistWrapper.Callback) null);
    }

    public static boolean sendToGameAssistServices(String str, Bundle bundle, Callback callback) {
        return GameAssistWrapper.getInstance().sendToGameAssistServices(str, bundle, callback.mToken);
    }
}
