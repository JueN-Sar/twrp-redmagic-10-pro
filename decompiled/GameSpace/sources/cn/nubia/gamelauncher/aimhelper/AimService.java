package cn.nubia.gamelauncher.aimhelper;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.commoninterface.IGetAppStatusDataCallBack;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils;
import cn.nubia.gamelauncher.model.AppAddModel;
import cn.nubia.gamelauncher.receiver.TopGameReceiver;
import cn.nubia.gamepi.IGameSceneCallback;
import cn.nubia.gamepi.IGameSceneInterface;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class AimService extends Service implements IGetAppStatusDataCallBack {
    public static final String ACTION_CHANGE_SWITCH = "ACTION_CHANGE_SWITCH";
    public static final String ACTION_GAMEMODE_CHANGE = "cn.nubia.gamelauncher.action.GAMEMODE_CHANGE";
    private static final String ACTION_START_AIM_HELPER = "cn.nubia.gamelauncher.action.START_HELPER";
    public static final String GAME_MODE_EXTRA_ISRUNNING = "isRunning";
    private static final String TAG = "AimService";
    private GameHelperController mGameHelperController;
    private Handler mHandler = new Handler();
    private IGameSceneInterface gamepiService = null;
    private IGameSceneCallback mGameSceneCallback = new IGameSceneCallback.Stub() { // from class: cn.nubia.gamelauncher.aimhelper.AimService.1
        @Override // cn.nubia.gamepi.IGameSceneCallback
        public void onSceneChange(int i) throws RemoteException {
            LogUtil.d(AimService.TAG, "onSceneChange value=" + i);
            AimService.this.updateSceneValue(i);
        }
    };
    private ServiceConnection mServiceConnection = new ServiceConnection() { // from class: cn.nubia.gamelauncher.aimhelper.AimService.2
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtil.i(AimService.TAG, "onServiceConnected");
            AimService.this.gamepiService = IGameSceneInterface.Stub.asInterface(iBinder);
            try {
                AimService.this.gamepiService.setCallback(AimService.this.mGameSceneCallback);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtil.i(AimService.TAG, "onServiceDisconnected");
            if (AimService.this.gamepiService != null) {
                AimService.this.gamepiService = null;
            }
        }
    };

    private class AppDbObserver extends ContentObserver {
        public AppDbObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            super.onChange(z);
            LogUtil.d(AimService.TAG, "AppDbObserver onChange");
            AimService.this.syncGameList();
        }
    }

    public static void changeSwitch(Context context, String str, boolean z) {
        Intent intent = new Intent(context, (Class<?>) AimService.class);
        intent.setAction(ACTION_CHANGE_SWITCH);
        intent.putExtra("packageName", str);
        intent.putExtra("enable", z);
        context.startService(intent);
    }

    public static void enterGameMode(Context context) {
        Intent intent = new Intent(context, (Class<?>) AimService.class);
        intent.setAction(ACTION_GAMEMODE_CHANGE);
        intent.putExtra("isRunning", true);
        context.startService(intent);
    }

    public static void kill(Context context) {
        LogUtil.i(TAG, "kill AimService");
        context.stopService(new Intent(context, (Class<?>) AimService.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void syncGameList() {
        ArrayList<AppListItemBean> appAddedList = AppAddModel.getInstance().getAppAddedList();
        if (appAddedList == null) {
            LogUtil.i(TAG, "syncGameList empty");
        } else {
            LogUtil.i(TAG, "syncGameList ok, size=" + appAddedList.size());
            GameWhiteList.syncPackages(appAddedList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSceneValue(final int i) {
        this.mHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.aimhelper.AimService.3
            @Override // java.lang.Runnable
            public void run() {
                AimService.this.mGameHelperController.handlerSceneValueChange(i);
            }
        });
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        LogUtil.i(TAG, "AimService onCreate");
        super.onCreate();
        this.mGameHelperController = new GameHelperController(this);
        AppAddModel.getInstance().resisterGetAppStatusDataCallBack(this);
        this.mGameHelperController.onCreate(this);
        if (Utils.isAppExist(this, "cn.nubia.gamepi")) {
            Intent intent = new Intent("cn.nubia.gamelauncher.ACTION_BIND_GAMEPI");
            intent.setComponent(new ComponentName("cn.nubia.gamepi", "cn.nubia.gamepi.GamePerformanceService"));
            bindService(intent, this.mServiceConnection, 1);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        try {
            IGameSceneInterface iGameSceneInterface = this.gamepiService;
            if (iGameSceneInterface != null) {
                iGameSceneInterface.setCallback(null);
                unbindService(this.mServiceConnection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppAddModel.getInstance().unResisterGetAppStatusDataCallBack(this);
        this.mGameHelperController.onDestroy();
        this.mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
        LogUtil.i(TAG, "AimService onDestroy");
    }

    @Override // cn.nubia.gamelauncher.commoninterface.IGetAppStatusDataCallBack
    public void onLoadAddAppListDone(ArrayList<AppListItemBean> arrayList, int i) {
        LogUtil.i(TAG, "onLoadAddAppListDone");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        String action = intent != null ? intent.getAction() : "";
        LogUtil.i(TAG, "onStartCommand action " + action);
        if (action == null) {
            return super.onStartCommand(intent, i, i2);
        }
        if (ACTION_START_AIM_HELPER.equals(action)) {
            this.mGameHelperController.handleStart();
        } else if (action.startsWith("cn.nubia.gamelauncher.action.delay_close_aim_helper_for_package")) {
            this.mGameHelperController.handleStop(intent.hasExtra("packagename") ? intent.getStringExtra("packagename") : "");
        } else if (TopGameReceiver.ACTION_TOP_GAME_CHANGE.equals(action)) {
            this.mGameHelperController.onActivityChange(intent.getStringExtra("packageName"));
        } else if (ACTION_GAMEMODE_CHANGE.equals(action)) {
            this.mGameHelperController.handleGameModeChange(intent.getBooleanExtra("isRunning", false));
        } else if (ACTION_CHANGE_SWITCH.equals(action)) {
            this.mGameHelperController.handleGameAssistSwitchChange(intent.getStringExtra("packageName"), intent.getBooleanExtra("enable", false));
        }
        return super.onStartCommand(intent, i, i2);
    }
}
