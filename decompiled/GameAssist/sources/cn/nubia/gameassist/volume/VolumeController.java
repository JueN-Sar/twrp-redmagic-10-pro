package cn.nubia.gameassist.volume;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioDeviceCallback;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import cn.nubia.componentcenter.api.volume.IVolumeController;
import cn.nubia.componentcenter.api.volume.VolumeListener;
import cn.nubia.gameassist.volume.VolumeController;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.common.AbsSlideProxy;
import com.zte.gameassist.common.IGameAssistCommander;
import com.zte.gameassist.common.ModuleProxyContext;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.shared.wrapper.TraceWrapper;
import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.io.PrintWriter;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class VolumeController extends AbsSlideProxy<VolumeListener> implements IVolumeController, IGameAssistCommander {

    /* renamed from: m, reason: collision with root package name */
    private final VolumeReceiver f7736m;

    /* renamed from: n, reason: collision with root package name */
    private final Context f7737n;

    /* renamed from: o, reason: collision with root package name */
    private final AudioManager f7738o;

    /* renamed from: p, reason: collision with root package name */
    private final Handler f7739p;

    /* renamed from: q, reason: collision with root package name */
    private final Handler f7740q;

    /* renamed from: r, reason: collision with root package name */
    private int f7741r;

    /* renamed from: s, reason: collision with root package name */
    private int f7742s;
    private String t;

    /* JADX INFO: Access modifiers changed from: private */
    class VolumeReceiver extends BroadcastReceiver implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        private boolean f7743c;

        /* renamed from: i, reason: collision with root package name */
        private AudioDeviceCallback f7745i = new AudioDeviceCallback() { // from class: cn.nubia.gameassist.volume.VolumeController.VolumeReceiver.1
            @Override // android.media.AudioDeviceCallback
            public void onAudioDevicesAdded(AudioDeviceInfo[] audioDeviceInfoArr) {
                VolumeReceiver.this.m(20L);
            }

            @Override // android.media.AudioDeviceCallback
            public void onAudioDevicesRemoved(AudioDeviceInfo[] audioDeviceInfoArr) {
                VolumeReceiver.this.m(20L);
            }
        };

        /* renamed from: h, reason: collision with root package name */
        private final Runnable f7744h = new Runnable() { // from class: cn.nubia.gameassist.volume.d
            @Override // java.lang.Runnable
            public final void run() {
                VolumeController.VolumeReceiver.this.n();
            }
        };

        public VolumeReceiver() {
        }

        private String e() {
            String str = "";
            for (AudioDeviceInfo audioDeviceInfo : VolumeController.this.f7738o.getDevices(2)) {
                int type = audioDeviceInfo.getType();
                if (type == 3) {
                    str = str + " WIRED_HEADSET_" + audioDeviceInfo.getId();
                } else if (type == 4) {
                    str = str + " WIRED_HEADPHONES_" + audioDeviceInfo.getId();
                } else if (type == 7) {
                    str = str + " BLUETOOTH_SCO_" + audioDeviceInfo.getId();
                } else if (type == 8) {
                    str = str + " BLUETOOTH_A2DP_" + audioDeviceInfo.getId();
                }
            }
            return str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void g() {
            VolumeController.this.f7738o.registerAudioDeviceCallback(this.f7745i, VolumeController.this.f7740q);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void h() {
            VolumeController.this.f7738o.unregisterAudioDeviceCallback(this.f7745i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void i(VolumeListener volumeListener) {
            volumeListener.onVolumeChanged(VolumeController.this.f7742s, VolumeController.this.f7741r);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void n() {
            if (!VolumeController.this.f7740q.getLooper().isCurrentThread()) {
                VolumeController.this.f7740q.post(this.f7744h);
                return;
            }
            VolumeController.this.f(new Consumer() { // from class: cn.nubia.gameassist.volume.e
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    VolumeController.VolumeReceiver.this.i((VolumeListener) obj);
                }
            });
            GaLog.j("VolumeController", "updateVolumeSeekbar mVolume=" + VolumeController.this.f7742s + " mMaxVolume=" + VolumeController.this.f7741r + " device:" + VolumeController.this.t);
        }

        public boolean f() {
            return this.f7743c;
        }

        public void j(int i2, int i3, int i4, String str) {
            if (this.f7743c && i2 == 3) {
                VolumeController.this.f7742s = i3;
                n();
                GaLog.j("VolumeController", "onVolumeChanged index=" + i3 + ",stream=" + i2 + ",device=" + i4 + ",caller=" + str);
            }
        }

        public synchronized void k() {
            if (!this.f7743c) {
                this.f7743c = true;
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.media.VOLUME_CHANGED_ACTION");
                VolumeController.this.f7737n.registerReceiver(this, intentFilter, 2);
                VolumeController.this.f7739p.post(new Runnable() { // from class: cn.nubia.gameassist.volume.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        VolumeController.VolumeReceiver.this.g();
                    }
                });
            }
        }

        public synchronized void l() {
            if (this.f7743c) {
                this.f7743c = false;
                VolumeController.this.f7737n.unregisterReceiver(this);
                VolumeController.this.f7739p.post(new Runnable() { // from class: cn.nubia.gameassist.volume.c
                    @Override // java.lang.Runnable
                    public final void run() {
                        VolumeController.VolumeReceiver.this.h();
                    }
                });
            }
        }

        public void m(long j2) {
            VolumeController.this.f7739p.removeCallbacks(this);
            VolumeController.this.f7739p.postDelayed(this, j2);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.media.VOLUME_CHANGED_ACTION".equals(intent.getAction())) {
                m(20L);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            TraceWrapper.traceBegin(8L, "updateStreamVolume");
            if (VolumeController.this.f7741r == 0) {
                VolumeController volumeController = VolumeController.this;
                volumeController.f7741r = volumeController.f7738o.getStreamMaxVolume(3);
            }
            VolumeController.this.t = e();
            VolumeController volumeController2 = VolumeController.this;
            volumeController2.f7742s = volumeController2.f7738o.getStreamVolume(3);
            VolumeController.this.f7740q.removeCallbacks(this.f7744h);
            VolumeController.this.f7740q.post(this.f7744h);
            TraceWrapper.traceEnd(8L);
        }
    }

    public VolumeController(ModuleProxyContext moduleProxyContext) {
        super(moduleProxyContext);
        this.f7736m = new VolumeReceiver();
        this.t = "";
        Context a2 = moduleProxyContext.a();
        this.f7737n = a2;
        this.f7739p = moduleProxyContext.b();
        this.f7740q = new Handler(Looper.getMainLooper());
        this.f7738o = (AudioManager) a2.getSystemService("audio");
        SystemMgr.y(a2).o(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void D(int i2) {
        this.f7738o.setStreamVolume(3, i2, 0);
        E();
    }

    private void E() {
        NubiaTrackManager.p().z("cn.nubia.gamelauncher", "game_assistant_volume_used", "app_name", SystemMgr.t());
    }

    @Override // cn.nubia.componentcenter.api.volume.IVolumeController
    public void dump(PrintWriter printWriter, String str) {
        printWriter.println(str + "VolumeController:");
        printWriter.println(str + "  mMaxVolume=" + this.f7741r);
        printWriter.println(str + "  mHeadsetType=" + this.t);
        AudioManager audioManager = this.f7738o;
        if (audioManager != null) {
            printWriter.println(str + "  volume=" + audioManager.getStreamVolume(3));
        }
    }

    @Override // com.zte.gameassist.common.IGameAssistCommander, com.zte.gameassist.AbsGameAssistToken.ICommander
    public void executive(String str, Bundle bundle, AbsGameAssistToken.Callback callback) {
        if ("onVolumeUpdate".equals(str) && bundle != null && bundle.containsKey("stream") && bundle.containsKey(VirtualHandleWrapper.KEY_INDEX) && bundle.containsKey("device") && bundle.containsKey("caller")) {
            String string = bundle.getString("caller");
            if (this.f7737n.getPackageName().equals(string)) {
                return;
            }
            this.f7736m.j(bundle.getInt("stream"), bundle.getInt(VirtualHandleWrapper.KEY_INDEX), bundle.getInt("device"), string);
        }
    }

    @Override // com.zte.gameassist.common.AbsModuleProxy
    protected void g() {
        this.f7736m.m(0L);
        this.f7736m.k();
    }

    @Override // cn.nubia.componentcenter.api.volume.IVolumeController
    public String getHeadsetType() {
        return this.t;
    }

    @Override // com.zte.gameassist.common.AbsSlideProxy, com.zte.gameassist.common.ISliderProxy
    public int getMax() {
        return this.f7736m.f() ? this.f7741r : this.f7738o.getStreamMaxVolume(3);
    }

    @Override // cn.nubia.componentcenter.api.volume.IVolumeController
    public int getMaxVolume() {
        return this.f7741r;
    }

    @Override // com.zte.gameassist.common.AbsSlideProxy, com.zte.gameassist.common.ISliderProxy
    public int getProgress() {
        return this.f7736m.f() ? this.f7742s : this.f7738o.getStreamVolume(3);
    }

    @Override // cn.nubia.componentcenter.api.volume.IVolumeController
    public int getVolume() {
        return this.f7742s;
    }

    @Override // com.zte.gameassist.common.AbsModuleProxy
    protected void i() {
        this.f7736m.l();
    }

    @Override // com.zte.gameassist.common.AbsSlideProxy
    public void k(int i2, boolean z) {
        if (z) {
            setVolume(i2);
        }
    }

    @Override // com.zte.gameassist.common.AbsSlideProxy
    protected void l() {
    }

    @Override // com.zte.gameassist.common.AbsSlideProxy
    protected void m() {
    }

    @Override // cn.nubia.componentcenter.api.volume.IVolumeController
    public void setVolume(final int i2) {
        this.f7739p.post(new Runnable() { // from class: cn.nubia.gameassist.volume.a
            @Override // java.lang.Runnable
            public final void run() {
                VolumeController.this.D(i2);
            }
        });
    }
}
