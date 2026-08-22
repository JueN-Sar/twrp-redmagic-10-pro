package com.android.systemui.shared.system;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.ActivityTaskManager;
import android.app.AppGlobals;
import android.app.IAssistDataReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.UserInfo;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.IRecentsAnimationController;
import android.view.IRecentsAnimationRunner;
import android.view.RemoteAnimationTarget;
import com.android.internal.app.IVoiceInteractionManagerService;
import com.android.systemui.shared.recents.model.Task;
import com.android.systemui.shared.recents.model.ThumbnailData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public class ActivityManagerWrapper {
    public static final String CLOSE_SYSTEM_WINDOWS_REASON_HOME_KEY = "homekey";
    public static final String CLOSE_SYSTEM_WINDOWS_REASON_RECENTS = "recentapps";
    private static final String TAG = "ActivityManagerWrapper";
    private static final String TILES_SETTING = "cc_tiles";
    private static final ActivityManagerWrapper sInstance = new ActivityManagerWrapper();
    private final PackageManager mPackageManager = AppGlobals.getInitialApplication().getPackageManager();
    private final BackgroundExecutor mBackgroundExecutor = BackgroundExecutor.get();
    private final TaskStackChangeListeners mTaskStackChangeListeners = new TaskStackChangeListeners(Looper.getMainLooper());

    private ActivityManagerWrapper() {
    }

    private String getBadgedLabel(String str, int i) {
        return i != UserHandle.myUserId() ? this.mPackageManager.getUserBadgedLabel(str, new UserHandle(i)).toString() : str;
    }

    public static ActivityManagerWrapper getInstance() {
        return sInstance;
    }

    public static List<ResolveInfo> getServices(Context context) {
        return context.getPackageManager().queryIntentServicesAsUser(new Intent("android.service.quicksettings.action.QS_TILE"), 0, ActivityManager.getCurrentUser());
    }

    public static ArrayList<String> getSetting(Context context) {
        String string = Settings.System.getString(context.getContentResolver(), "cc_tiles");
        Log.i("game_custom", "getspec:" + string);
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str : string.split(",")) {
            String trim = str.trim();
            if (!trim.isEmpty()) {
                arrayList.add(trim);
            }
        }
        arrayList.remove("game_custom");
        return arrayList;
    }

    public static boolean isHomeTask(ActivityManager.RunningTaskInfo runningTaskInfo) {
        return runningTaskInfo.configuration.windowConfiguration.getActivityType() == 2;
    }

    public void cancelRecentsAnimation(boolean z) {
        try {
            ActivityTaskManager.getService().cancelRecentsAnimation(z);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to cancel recents animation", e);
        }
    }

    public void cancelWindowTransition(int i) {
        try {
            ActivityTaskManager.getService().cancelTaskWindowTransition(i);
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to cancel window transition for task=" + i, e);
        }
    }

    public Future<?> closeSystemWindows(final String str) {
        return this.mBackgroundExecutor.submit(new Runnable() { // from class: com.android.systemui.shared.system.ActivityManagerWrapper.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ActivityManager.getService().closeSystemDialogs(str);
                } catch (RemoteException e) {
                    Log.w(ActivityManagerWrapper.TAG, "Failed to close system windows", e);
                }
            }
        });
    }

    public String getBadgedActivityLabel(ActivityInfo activityInfo, int i) {
        return getBadgedLabel(activityInfo.loadLabel(this.mPackageManager).toString(), i);
    }

    public String getBadgedApplicationLabel(ApplicationInfo applicationInfo, int i) {
        return getBadgedLabel(applicationInfo.loadLabel(this.mPackageManager).toString(), i);
    }

    public String getBadgedContentDescription(ActivityInfo activityInfo, int i, ActivityManager.TaskDescription taskDescription) {
        String obj = (taskDescription == null || taskDescription.getLabel() == null) ? activityInfo.loadLabel(this.mPackageManager).toString() : taskDescription.getLabel();
        String obj2 = activityInfo.applicationInfo.loadLabel(this.mPackageManager).toString();
        String badgedLabel = getBadgedLabel(obj2, i);
        return obj2.equals(obj) ? badgedLabel : badgedLabel + " " + obj;
    }

    public int getCurrentUserId() {
        try {
            UserInfo currentUser = ActivityManager.getService().getCurrentUser();
            if (currentUser != null) {
                return currentUser.id;
            }
            return 0;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<ActivityManager.RecentTaskInfo> getRecentTasks(int i, int i2) {
        try {
            return ActivityTaskManager.getService().getRecentTasks(i, 2, i2).getList();
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to get recent tasks", e);
            return new ArrayList();
        }
    }

    public ActivityManager.RunningTaskInfo getRunningTask() {
        return getRunningTask(false);
    }

    public ActivityManager.RunningTaskInfo getRunningTask(boolean z) {
        try {
            List filteredTasks = ActivityTaskManager.getService().getFilteredTasks(1, z);
            if (filteredTasks.isEmpty()) {
                return null;
            }
            return (ActivityManager.RunningTaskInfo) filteredTasks.get(0);
        } catch (RemoteException unused) {
            return null;
        }
    }

    public ThumbnailData getTaskThumbnail(int i, boolean z) {
        ActivityManager.TaskSnapshot taskSnapshot;
        try {
            taskSnapshot = ActivityTaskManager.getService().getTaskSnapshot(i, z);
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to retrieve task snapshot", e);
            taskSnapshot = null;
        }
        return taskSnapshot != null ? new ThumbnailData(taskSnapshot) : new ThumbnailData();
    }

    public UserHandle getUserHandle(Context context, int i) {
        int i2;
        UserManager userManager = (UserManager) context.getSystemService("user");
        Iterator it = userManager.getProfiles(userManager.getUserHandle()).iterator();
        while (true) {
            if (!it.hasNext()) {
                i2 = -1;
                break;
            }
            UserInfo userInfo = (UserInfo) it.next();
            if (userInfo.isManagedProfile() && userInfo.id == i) {
                i2 = userInfo.id;
                break;
            }
        }
        UserHandle userHandle = i2 != -1 ? new UserHandle(i2) : null;
        Log.i("SysShared", "getDoubleAppsProfile: userHandle" + i2);
        return userHandle;
    }

    public void invalidateHomeTaskSnapshot(final Activity activity) {
        this.mBackgroundExecutor.submit(new Runnable() { // from class: com.android.systemui.shared.system.ActivityManagerWrapper.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ActivityTaskManager.getService().invalidateHomeTaskSnapshot(activity.getActivityToken());
                } catch (RemoteException e) {
                    Log.w(ActivityManagerWrapper.TAG, "Failed to invalidate home snapshot", e);
                }
            }
        });
    }

    public boolean isLockTaskKioskModeActive() {
        try {
            return ActivityTaskManager.getService().getLockTaskModeState() == 1;
        } catch (RemoteException unused) {
            return false;
        }
    }

    public boolean isLockToAppActive() {
        try {
            return ActivityTaskManager.getService().getLockTaskModeState() != 0;
        } catch (RemoteException unused) {
            return false;
        }
    }

    public boolean isScreenPinningActive() {
        try {
            return ActivityTaskManager.getService().getLockTaskModeState() == 2;
        } catch (RemoteException unused) {
            return false;
        }
    }

    public boolean isScreenPinningEnabled() {
        return Settings.System.getInt(AppGlobals.getInitialApplication().getContentResolver(), "lock_to_app_enabled", 0) != 0;
    }

    public boolean isUnable(Context context) {
        int activeDisplayState = ((DisplayManager) context.getSystemService("display")).getWifiDisplayStatus().getActiveDisplayState();
        return activeDisplayState == 2 || activeDisplayState == 1;
    }

    public void putSetting(Context context, List<String> list, List<String> list2) {
        String join = TextUtils.join(",", list2);
        Log.i("game_custom", "savespec:" + join);
        Settings.System.putStringForUser(context.getContentResolver(), "cc_tiles", join, ActivityManager.getCurrentUser());
    }

    public void rearrangeRecentTask(int i, int i2) {
        try {
            ActivityTaskManager.getService().rearrangeRecentTask(i, i2);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to rearrange recent tasks", e);
        }
    }

    public void registerTaskStackListener(TaskStackChangeListener taskStackChangeListener) {
        synchronized (this.mTaskStackChangeListeners) {
            this.mTaskStackChangeListeners.addListener(ActivityManager.getService(), taskStackChangeListener);
        }
    }

    public void removeAllRecentTasks() {
        this.mBackgroundExecutor.submit(new Runnable() { // from class: com.android.systemui.shared.system.ActivityManagerWrapper.9
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ActivityTaskManager.getService().removeAllVisibleRecentTasks();
                } catch (RemoteException e) {
                    Log.w(ActivityManagerWrapper.TAG, "Failed to remove all tasks", e);
                }
            }
        });
    }

    public void removeTask(final int i) {
        this.mBackgroundExecutor.submit(new Runnable() { // from class: com.android.systemui.shared.system.ActivityManagerWrapper.8
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ActivityTaskManager.getService().removeTask(i);
                } catch (RemoteException e) {
                    Log.w(ActivityManagerWrapper.TAG, "Failed to remove task=" + i, e);
                }
            }
        });
    }

    public boolean setTaskWindowingModeSplitScreenPrimary(int i, int i2, Rect rect) {
        try {
            return ActivityTaskManager.getService().setTaskWindowingModeSplitScreenPrimary(i, true);
        } catch (RemoteException unused) {
            return false;
        }
    }

    public boolean showVoiceSession(IBinder iBinder, Bundle bundle, int i) {
        IVoiceInteractionManagerService asInterface = IVoiceInteractionManagerService.Stub.asInterface(ServiceManager.getService("voiceinteraction"));
        if (asInterface == null) {
            return false;
        }
        try {
            return asInterface.showSessionFromSession(iBinder, bundle, i);
        } catch (RemoteException unused) {
            return false;
        }
    }

    public boolean startActivityFromRecents(int i, ActivityOptions activityOptions) {
        Bundle bundle;
        if (activityOptions == null) {
            bundle = null;
        } else {
            try {
                bundle = activityOptions.toBundle();
            } catch (Exception e) {
                Log.e(TAG, "startActivityFromRecent error=" + e);
                return false;
            }
        }
        ActivityTaskManager.getService().startActivityFromRecents(i, bundle);
        return true;
    }

    public void startActivityFromRecentsAsync(Task.TaskKey taskKey, ActivityOptions activityOptions, int i, int i2, final Consumer<Boolean> consumer, Handler handler) {
        final boolean z;
        if (taskKey.windowingMode == 3) {
            if (activityOptions == null) {
                activityOptions = ActivityOptions.makeBasic();
            }
            activityOptions.setLaunchWindowingMode(4);
        } else if (i != 0 || i2 != 0) {
            if (activityOptions == null) {
                activityOptions = ActivityOptions.makeBasic();
            }
            activityOptions.setLaunchWindowingMode(i);
            activityOptions.setLaunchActivityType(i2);
        }
        try {
            z = startActivityFromRecents(taskKey.id, activityOptions);
        } catch (Exception unused) {
            z = false;
        }
        if (consumer != null) {
            handler.post(new Runnable() { // from class: com.android.systemui.shared.system.ActivityManagerWrapper.6
                @Override // java.lang.Runnable
                public void run() {
                    consumer.accept(Boolean.valueOf(z));
                }
            });
        }
    }

    public void startActivityFromRecentsAsync(Task.TaskKey taskKey, ActivityOptions activityOptions, Consumer<Boolean> consumer, Handler handler) {
        startActivityFromRecentsAsync(taskKey, activityOptions, 0, 0, consumer, handler);
    }

    public void startPipActivity(Intent intent, Context context) {
        ActivityOptions makeBasic = ActivityOptions.makeBasic();
        makeBasic.setLaunchWindowingMode(5);
        context.startActivity(intent, makeBasic.toBundle());
    }

    public void startPipActivityAsUser(Intent intent, Context context, UserHandle userHandle) {
        ActivityOptions makeBasic = ActivityOptions.makeBasic();
        makeBasic.setLaunchWindowingMode(5);
        context.startActivityAsUser(intent, makeBasic.toBundle(), userHandle);
    }

    public void startRecentsActivity(Intent intent, final AssistDataReceiver assistDataReceiver, final RecentsAnimationListener recentsAnimationListener, final Consumer<Boolean> consumer, Handler handler) {
        IAssistDataReceiver iAssistDataReceiver;
        if (assistDataReceiver != null) {
            try {
                iAssistDataReceiver = new IAssistDataReceiver.Stub() { // from class: com.android.systemui.shared.system.ActivityManagerWrapper.2
                    public void onHandleAssistData(Bundle bundle) {
                        assistDataReceiver.onHandleAssistData(bundle);
                    }

                    public void onHandleAssistScreenshot(Bitmap bitmap) {
                        assistDataReceiver.onHandleAssistScreenshot(bitmap);
                    }
                };
            } catch (Exception unused) {
                if (consumer != null) {
                    handler.post(new Runnable() { // from class: com.android.systemui.shared.system.ActivityManagerWrapper.5
                        @Override // java.lang.Runnable
                        public void run() {
                            consumer.accept(false);
                        }
                    });
                    return;
                }
                return;
            }
        } else {
            iAssistDataReceiver = null;
        }
        ActivityTaskManager.getService().startRecentsActivity(intent, iAssistDataReceiver, recentsAnimationListener != null ? new IRecentsAnimationRunner.Stub() { // from class: com.android.systemui.shared.system.ActivityManagerWrapper.3
            public void onAnimationCanceled(ActivityManager.TaskSnapshot taskSnapshot) {
                recentsAnimationListener.onAnimationCanceled(false);
            }

            public void onAnimationStart(IRecentsAnimationController iRecentsAnimationController, RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, Rect rect, Rect rect2) {
                RecentsAnimationControllerCompat recentsAnimationControllerCompat = new RecentsAnimationControllerCompat(iRecentsAnimationController);
                RemoteAnimationTargetCompat[] wrap = RemoteAnimationTargetCompat.wrap(remoteAnimationTargetArr);
                RemoteAnimationTargetCompat.wrap(remoteAnimationTargetArr2);
                recentsAnimationListener.onAnimationStart(recentsAnimationControllerCompat, wrap, rect, rect2);
            }

            public void onTaskAppeared(RemoteAnimationTarget remoteAnimationTarget) {
            }
        } : null);
        if (consumer != null) {
            handler.post(new Runnable() { // from class: com.android.systemui.shared.system.ActivityManagerWrapper.4
                @Override // java.lang.Runnable
                public void run() {
                    consumer.accept(true);
                }
            });
        }
    }

    public boolean supportsFreeformMultiWindow(Context context) {
        boolean z = Settings.Global.getInt(context.getContentResolver(), "enable_freeform_support", 0) != 0;
        if (ActivityTaskManager.supportsMultiWindow(context)) {
            return context.getPackageManager().hasSystemFeature("android.software.freeform_window_management") || z;
        }
        return false;
    }

    public void unregisterTaskStackListener(TaskStackChangeListener taskStackChangeListener) {
        synchronized (this.mTaskStackChangeListeners) {
            this.mTaskStackChangeListeners.removeListener(taskStackChangeListener);
        }
    }
}
