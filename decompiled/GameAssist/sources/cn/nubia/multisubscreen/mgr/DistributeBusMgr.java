package cn.nubia.multisubscreen.mgr;

import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cn.nubia.multisubscreen.CastRole;
import cn.nubia.multisubscreen.callback.ScreenCastServiceCallback;
import cn.nubia.multisubscreen.callback.StatusCallback;
import cn.nubia.multisubscreen.data.TransferData;
import cn.nubia.multisubscreen.primary.PrimaryDeviceDataMgr;
import cn.nubia.multisubscreen.secondary.SecDeviceDataMgr;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import com.google.gson.Gson;
import com.zte.distbus.DistributeBus;
import com.zte.distbus.basetransfer.servicemanager.ServiceUtil;
import com.zte.distbus.basetransfer.servicemanager.model.ListedDevice;
import com.zte.distbus.basetransfer.servicemanager.model.Profile;
import com.zte.distbus.basetransfer.servicemanager.model.PublishServiceParam;
import com.zte.distbus.basetransfer.servicemanager.model.ServiceParam;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.common.GameCheck;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class DistributeBusMgr implements GameMonitor.Callback {
    private static final String TAG = "MultiSubScreen_DistributeBusMgr";
    public static final int VERSION_CODE = 7;
    private static volatile DistributeBusMgr sDistributeBusMgr;

    @NonNull
    private CastRole mCastRole;

    @NonNull
    Context mContext;
    private String mDeviceId;
    private int mDeviceType;
    private boolean mIsScanNeeded;
    private Handler mMainHandler;
    private SharedPreferencesUtil mSharedPreferencesUtil;
    private DistributeBus mSinkDistributeBus;

    @NonNull
    private DistributeBus mSourceDistributeBus;
    private StatusCallback mStatusCallback = new StatusCallback() { // from class: cn.nubia.multisubscreen.mgr.DistributeBusMgr.1
        @Override // cn.nubia.multisubscreen.callback.StatusCallback
        public void b(String str, int i2) {
            GaLog.e(DistributeBusMgr.TAG, "DistributeBusMgr connect state change to " + i2 + ", deviceId = " + str);
            if (DistributeBusMgr.this.mSharedPreferencesUtil != null) {
                SharedPreferencesUtil sharedPreferencesUtil = DistributeBusMgr.this.mSharedPreferencesUtil;
                if (i2 != 2) {
                    str = "";
                }
                sharedPreferencesUtil.a0(str);
            }
        }
    };

    @NonNull
    private String mUuid;

    private DistributeBusMgr() {
    }

    private void disConnectDeviceInternal(String str) {
        if (MultiSubScreenUtils.v()) {
            ConnectCodeMgr.h().x("NOTIFY_DISCONNECT_CODE");
        }
        MultiSubScreenUtils.o(this.mContext).disconnectDevice(str);
        MultiSubScreenUtils.H(null);
        MultiSubScreenUtils.w(str, 0);
        MultiSubScreenUtils.F(CastRole.UN_KNOW);
    }

    public static DistributeBusMgr getInstance() {
        if (sDistributeBusMgr == null) {
            synchronized (DistributeBusMgr.class) {
                try {
                    if (sDistributeBusMgr == null) {
                        sDistributeBusMgr = new DistributeBusMgr();
                    }
                } finally {
                }
            }
        }
        return sDistributeBusMgr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setScanNeeded$1(boolean z) {
        if (this.mIsScanNeeded == z) {
            return;
        }
        GaLog.e(TAG, "scan needed change to " + z);
        this.mIsScanNeeded = z;
        lambda$onGameSceneStateChanged$0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reConnectBeforeDevice(String str) {
        ListedDevice device = ServiceUtil.getDevice(str);
        GaLog.a(TAG, "reConnectBeforeDevice listedDevice = " + device);
        if (device != null && MultiSubScreenUtils.f8174d != 2 && MultiSubScreenUtils.k() == null && TextUtils.isEmpty(getSinkDeviceId()) && device.getStatus() == 102) {
            GaLog.a(TAG, "reConnectBeforeDevice need reconnected");
            setSinkDistributeBus(str);
            MultiSubScreenUtils.w(str, 2);
        }
    }

    private void sendCommMsg(String str) {
        GaLog.a(TAG, "sendCommMsg msg " + str);
        GaLog.a(TAG, "sendCommMsg mSinkDistributeBus " + this.mSinkDistributeBus);
        DistributeBus distributeBus = this.mSinkDistributeBus;
        if (distributeBus != null) {
            distributeBus.W(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateScanState, reason: merged with bridge method [inline-methods] */
    public void lambda$onGameSceneStateChanged$0() {
        Settings.Global.putInt(this.mContext.getContentResolver(), "game_scene_disable_ble_scan", (!SystemMgr.H() || this.mIsScanNeeded) ? 0 : 1);
    }

    public void connectDevice(ListedDevice listedDevice) {
        GaLog.a(TAG, "connectDevice device = " + listedDevice);
        if (listedDevice == null) {
            return;
        }
        MultiSubScreenUtils.F(CastRole.SOURCE);
        MultiSubScreenUtils.o(this.mContext).connectDevice(listedDevice.getDeviceId());
        MultiSubScreenUtils.H(listedDevice);
        MultiSubScreenUtils.w(listedDevice.getDeviceId(), 1);
    }

    public void disConnectDevice(ListedDevice listedDevice) {
        GaLog.a(TAG, "disConnectDevice device = " + listedDevice);
        if (listedDevice == null) {
            disConnectDevice(this.mDeviceId);
        } else {
            disConnectDeviceInternal(listedDevice.getDeviceId());
        }
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("MultiSubScreen: ");
        printWriter.append("  scan need:").println(this.mIsScanNeeded);
        if (MultiSubScreenUtils.f8174d != 0) {
            printWriter.append("  connect status:").println(MultiSubScreenUtils.f8174d);
            printWriter.append("  cast role:").println(MultiSubScreenUtils.h());
            PrimaryDeviceDataMgr.C().A(fileDescriptor, printWriter, strArr, "  ");
            SecDeviceDataMgr.f().e(fileDescriptor, printWriter, strArr, "  ");
        }
    }

    public CastRole getCastRole() {
        return this.mCastRole;
    }

    public String getSinkDeviceId() {
        return this.mDeviceId;
    }

    public DistributeBus getSinkDistributeBus() {
        return this.mSinkDistributeBus;
    }

    public DistributeBus getSourceDistributeBus() {
        return this.mSourceDistributeBus;
    }

    public void init(Context context) {
        this.mContext = context;
        this.mSharedPreferencesUtil = SharedPreferencesUtil.k(context);
        SystemMgr.y(context).h(this);
        MultiSubScreenUtils.C(this.mStatusCallback);
        this.mSourceDistributeBus = new DistributeBus(this.mContext, "ceb574816000", null);
        PrimaryDeviceDataMgr.C();
        SecDeviceDataMgr.f();
        this.mMainHandler = new Handler(Looper.getMainLooper());
        final String r2 = this.mSharedPreferencesUtil.r();
        GaLog.a(TAG, "DistributeBusMgr beforeDeviceId = " + r2);
        if (TextUtils.isEmpty(r2)) {
            return;
        }
        GaLog.a(TAG, "DistributeBusMgr has connect device and gameassist crash,so need reconnected!");
        this.mMainHandler.postDelayed(new Runnable() { // from class: cn.nubia.multisubscreen.mgr.DistributeBusMgr.2
            @Override // java.lang.Runnable
            public void run() {
                DistributeBusMgr.this.reConnectBeforeDevice(r2);
            }
        }, 500L);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    public /* bridge */ /* synthetic */ void onFocuesWindowChanged(AbsGameAssistToken.FocuesWindow focuesWindow) {
        super.onFocuesWindowChanged(focuesWindow);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onFullscreenActivityChange */
    public /* bridge */ /* synthetic */ void p(ComponentName componentName) {
        super.p(componentName);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameSceneStateChanged */
    public void m0(boolean z) {
        this.mMainHandler.post(new Runnable() { // from class: cn.nubia.multisubscreen.mgr.a
            @Override // java.lang.Runnable
            public final void run() {
                DistributeBusMgr.this.lambda$onGameSceneStateChanged$0();
            }
        });
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public /* bridge */ /* synthetic */ void y() {
        super.y();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public /* bridge */ /* synthetic */ void z() {
        super.z();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameUpdate */
    public /* bridge */ /* synthetic */ void A() {
        super.A();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    public /* bridge */ /* synthetic */ void onLauncherFirstPackage(String str) {
        super.onLauncherFirstPackage(str);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    public /* bridge */ /* synthetic */ void onProjectionActivityResumed(ComponentName componentName, int i2) {
        super.onProjectionActivityResumed(componentName, i2);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    public /* bridge */ /* synthetic */ void onResumeFullscreenActivityPidChanged() {
        super.onResumeFullscreenActivityPidChanged();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    public /* bridge */ /* synthetic */ void onShowTipAnimation(GameCheck.GameAppInfo gameAppInfo) {
        super.onShowTipAnimation(gameAppInfo);
    }

    public void publishService(boolean z) {
        GaLog.a(TAG, "publishService enabled = " + z);
        PublishServiceParam publishServiceParam = new PublishServiceParam("GameService", "ceb574816000", ScreenCastServiceCallback.class, new Gson().toJson(new Profile(Build.PRODUCT, Build.MODEL)), 7);
        publishServiceParam.setEnable(z);
        DistributeBus distributeBus = this.mSourceDistributeBus;
        if (distributeBus != null) {
            distributeBus.S(publishServiceParam);
        }
    }

    public void sendTransferData(TransferData transferData) {
        sendCommMsg(new Gson().toJson(transferData));
    }

    public void setCastRole(CastRole castRole) {
        this.mCastRole = castRole;
        MultiSubScreenUtils.F(castRole);
    }

    public void setScanNeeded(final boolean z) {
        this.mMainHandler.post(new Runnable() { // from class: cn.nubia.multisubscreen.mgr.b
            @Override // java.lang.Runnable
            public final void run() {
                DistributeBusMgr.this.lambda$setScanNeeded$1(z);
            }
        });
    }

    public void setSinkDistributeBus(String str) {
        GaLog.a(TAG, "setSinkDistributeBus deviceId = " + str);
        if (TextUtils.isEmpty(str)) {
            this.mDeviceId = null;
            this.mSinkDistributeBus = null;
        } else if (!str.equals(this.mDeviceId) || this.mSinkDistributeBus == null) {
            this.mDeviceId = str;
            this.mSinkDistributeBus = new DistributeBus(this.mContext, "ceb574816000", str);
        }
    }

    public void startService(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        GaLog.a(TAG, "startService -> uuid = " + str2 + ", profile = " + str3);
        DistributeBus distributeBus = this.mSourceDistributeBus;
        if (distributeBus != null) {
            distributeBus.a0(new ServiceParam(str2, str3));
        }
    }

    public void stopService(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        DistributeBus distributeBus = this.mSourceDistributeBus;
        if (distributeBus != null) {
            distributeBus.b0(new ServiceParam(str2, str3));
        }
    }

    public void subscribeService() {
        GaLog.a(TAG, "subscribeService");
        PublishServiceParam publishServiceParam = new PublishServiceParam("GameService", "ceb574816000", ScreenCastServiceCallback.class, new Gson().toJson(new Profile(Build.PRODUCT, Build.MODEL)), 7);
        DistributeBus distributeBus = this.mSourceDistributeBus;
        if (distributeBus != null) {
            distributeBus.c0(publishServiceParam);
        }
    }

    public void sendTransferData(String str, TransferData transferData) {
        sendCommMsg(str, new Gson().toJson(transferData));
    }

    public void disConnectDevice(String str) {
        GaLog.a(TAG, "disConnectDevice deviceId = " + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        disConnectDeviceInternal(str);
    }

    private void sendCommMsg(String str, String str2) {
        GaLog.a(TAG, "sendCommMsg msg " + str2);
        GaLog.a(TAG, "sendCommMsg deviceId " + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        new DistributeBus(this.mContext, "ceb574816000", str).W(str2);
    }

    public void connectDevice(String str) {
        GaLog.a(TAG, "connectDevice deviceId = " + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        MultiSubScreenUtils.F(CastRole.SOURCE);
        MultiSubScreenUtils.o(this.mContext).connectDevice(str);
        ListedDevice l2 = MultiSubScreenUtils.l(str);
        if (l2 != null) {
            MultiSubScreenUtils.H(l2);
        } else {
            setSinkDistributeBus(str);
        }
        MultiSubScreenUtils.w(str, 1);
    }
}
