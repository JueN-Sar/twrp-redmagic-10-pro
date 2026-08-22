package cn.nubia.screensaver.system;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.HardwareBuffer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.SystemClock;
import cn.nubia.screensaver.GameScreensaverManager;
import cn.nubia.screensaver.common.IController;
import cn.nubia.screensaver.common.ScreensaverToken;
import cn.nubia.screensaver.power.GSPowerController;
import cn.nubia.screensaver.system.GSSystemController;
import cn.nubia.screensaver.system.ISnapshotKeyguard;
import cn.nubia.screensaver.view.ScreensaverRootView;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.TraceWrapper;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class GSSystemController implements IController {

    /* renamed from: c, reason: collision with root package name */
    private IBinder f9153c;

    /* renamed from: h, reason: collision with root package name */
    private GameScreensaverManager f9154h;

    /* renamed from: i, reason: collision with root package name */
    private GSPowerController f9155i;

    public class SnapshotKeyguard implements ISnapshotKeyguard, ScreensaverToken.SystemCallback {

        /* renamed from: a, reason: collision with root package name */
        private ISnapshotKeyguard.Callback f9156a;

        public SnapshotKeyguard() {
        }

        @Override // cn.nubia.screensaver.common.ScreensaverToken.SystemCallback
        public void a(Bundle bundle) {
            GaLog.e("GameScreensaver.System", "onSnapshotKeyguard bundle=" + bundle + "  " + bundle.containsKey("buffer"));
            GSSystemController.this.f9154h.M().k("onSnapshotKeyguard", this);
            HardwareBuffer hardwareBuffer = (HardwareBuffer) bundle.getParcelable("buffer", HardwareBuffer.class);
            ISnapshotKeyguard.Callback callback = this.f9156a;
            if (callback == null || hardwareBuffer == null) {
                callback.a(null);
            } else {
                callback.a(new KeyguardShade(GSSystemController.this.f9154h.H(), Bitmap.wrapHardwareBuffer(hardwareBuffer, null)));
            }
        }

        @Override // cn.nubia.screensaver.system.ISnapshotKeyguard
        public void b(ISnapshotKeyguard.Callback callback) {
            this.f9156a = callback;
            GSSystemController.this.f9154h.M().k("onSnapshotKeyguard", null);
            GSSystemController.this.f9154h.M().e("onSnapshotKeyguard", this);
            Bundle bundle = new Bundle();
            Point point = new Point();
            GSSystemController.this.f9154h.H().getDisplay().getRealSize(point);
            bundle.putParcelable("size", point);
            bundle.putFloat("scaleFraction", 0.5f);
            GSSystemController.this.i("snapshotKeyguard", bundle);
            GaLog.e("GameScreensaver.System", "invake snapshot " + bundle);
        }
    }

    public class SystemPowerProxy implements ISystemPower {
        public SystemPowerProxy() {
        }

        @Override // cn.nubia.screensaver.system.ISystemPower
        public void a(boolean z) {
            Bundle bundle = new Bundle();
            bundle.putInt("bundle_key_doze_screen_state", z ? 3 : 0);
            GSSystemController.this.i("setDozeOverrideBrightness", bundle);
            GaLog.e("GameScreensaver.System", "invake setAodEnable aod=" + z);
        }

        @Override // cn.nubia.screensaver.system.ISystemPower
        public void b() {
            Bundle bundle = new Bundle();
            bundle.putLong("bundle_key_time", SystemClock.uptimeMillis());
            bundle.putInt(AbsGameAssistToken.BUNDLE_KEY_REASON, 7);
            bundle.putString("bundle_key_details", ScreensaverRootView.TAG);
            GSSystemController.this.i("wakeUp", bundle);
            GaLog.e("GameScreensaver.System", "invake wakeUp");
        }

        @Override // cn.nubia.screensaver.system.ISystemPower
        public void userActivity() {
            Bundle bundle = new Bundle();
            bundle.putLong("bundle_key_time", SystemClock.uptimeMillis());
            bundle.putInt("bundle_key_flags", 0);
            GSSystemController.this.i("userActivity", bundle);
            GaLog.e("GameScreensaver.System", "invake userActivity");
        }
    }

    public GSSystemController(GameScreensaverManager gameScreensaverManager) {
        this.f9154h = gameScreensaverManager;
        gameScreensaverManager.M().e("onConnected", new ScreensaverToken.SystemCallback() { // from class: n.a
            @Override // cn.nubia.screensaver.common.ScreensaverToken.SystemCallback
            public final void a(Bundle bundle) {
                GSSystemController.this.n(bundle);
            }
        });
    }

    private void e() {
        if (this.f9155i == null) {
            this.f9155i = (GSPowerController) this.f9154h.I(GSPowerController.class);
        }
        if (this.f9155i.y()) {
            return;
        }
        this.f9155i.r();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle i(String str, Bundle bundle) {
        return k(str, bundle, false);
    }

    private Bundle k(String str, Bundle bundle, boolean z) {
        IBinder iBinder = this.f9153c;
        if (iBinder == null || !iBinder.isBinderAlive()) {
            GaLog.b("GameScreensaver.System", "---mServer == null--- , actionName = " + str);
            return new Bundle();
        }
        e();
        Bundle bundle2 = new Bundle();
        try {
            TraceWrapper.traceBegin(8L, "gameassist_" + str);
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                try {
                    obtain.writeInterfaceToken("gameassist.gamescreensaver");
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        obtain.writeBundle(bundle);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f9153c.transact(6, obtain, obtain2, z ? 1 : 0);
                    if (!z) {
                        obtain2.readException();
                        if (obtain2.readInt() == 1) {
                            bundle2 = obtain2.readBundle();
                        }
                    }
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain.recycle();
                    obtain2.recycle();
                    throw th;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                obtain.recycle();
            }
            obtain2.recycle();
            return bundle2;
        } finally {
            TraceWrapper.traceEnd(8L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(Bundle bundle) {
        IBinder binder = bundle.getBinder("controller");
        GaLog.e("GameScreensaver.System", "onConnectedSystem controller=" + binder);
        p(binder);
    }

    @Override // cn.nubia.screensaver.common.IController
    public void a(FileDescriptor fileDescriptor, PrintWriter printWriter, String str) {
        printWriter.println(str + "GameScreensaver.System");
        printWriter.println((str + "  ") + "mServer=" + this.f9153c);
        IBinder iBinder = this.f9153c;
        if (iBinder == null || !iBinder.isBinderAlive()) {
            return;
        }
        try {
            this.f9153c.dump(fileDescriptor, new String[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public ISnapshotKeyguard g() {
        return new SnapshotKeyguard();
    }

    public ISystemPower h() {
        return new SystemPowerProxy();
    }

    public boolean l() {
        IBinder iBinder = this.f9153c;
        return iBinder != null && iBinder.isBinderAlive();
    }

    public boolean m(int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("userId", i2);
        return i("isLockScreenDisabled", bundle).getBoolean("isLockScreenDisabled", false);
    }

    public void p(IBinder iBinder) {
        try {
            this.f9153c = (IBinder) Binder.class.getMethod("allowBlocking", IBinder.class).invoke(null, iBinder);
        } catch (Exception e2) {
            e2.printStackTrace();
            this.f9153c = iBinder;
        }
    }
}
