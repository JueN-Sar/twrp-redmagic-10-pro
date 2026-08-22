package cn.nubia.screensaver.common;

import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.util.ArrayMap;
import cn.nubia.screensaver.common.ScreensaverToken;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.TraceWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class ScreensaverToken extends Binder {

    /* renamed from: a, reason: collision with root package name */
    private final Handler f9026a;

    /* renamed from: b, reason: collision with root package name */
    private Map f9027b = new ArrayMap();

    public interface SystemCallback {
        void a(Bundle bundle);
    }

    public ScreensaverToken(Handler handler, String str, SystemCallback systemCallback) {
        this.f9026a = handler;
        e(str, systemCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(SystemCallback systemCallback, String str) {
        if (systemCallback != null) {
            List list = (List) this.f9027b.get(str);
            if (list == null) {
                list = new ArrayList();
                this.f9027b.put(str, list);
            }
            if (list.contains(systemCallback)) {
                return;
            }
            list.add(systemCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(String str, final Bundle bundle) {
        if (str != null) {
            List list = (List) this.f9027b.get(str);
            if (list != null) {
                list.forEach(new Consumer() { // from class: cn.nubia.screensaver.common.g
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((ScreensaverToken.SystemCallback) obj).a(bundle);
                    }
                });
                return;
            }
            GaLog.e("GameScreensaver.Token", "callback action=" + str + " callback is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(String str, SystemCallback systemCallback) {
        List list;
        if (str == null || (list = (List) this.f9027b.get(str)) == null) {
            return;
        }
        if (systemCallback == null) {
            this.f9027b.remove(str);
        } else if (list.contains(systemCallback)) {
            list.remove(systemCallback);
        }
    }

    private void j(final String str, final Bundle bundle) {
        this.f9026a.post(new Runnable() { // from class: cn.nubia.screensaver.common.f
            @Override // java.lang.Runnable
            public final void run() {
                ScreensaverToken.this.h(str, bundle);
            }
        });
    }

    public void e(final String str, final SystemCallback systemCallback) {
        this.f9026a.post(new Runnable() { // from class: cn.nubia.screensaver.common.h
            @Override // java.lang.Runnable
            public final void run() {
                ScreensaverToken.this.f(systemCallback, str);
            }
        });
    }

    public void k(final String str, final SystemCallback systemCallback) {
        this.f9026a.post(new Runnable() { // from class: cn.nubia.screensaver.common.i
            @Override // java.lang.Runnable
            public final void run() {
                ScreensaverToken.this.i(str, systemCallback);
            }
        });
    }

    @Override // android.os.Binder
    protected boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 != 6) {
            return super.onTransact(i2, parcel, parcel2, i3);
        }
        parcel.enforceInterface("gameassist.gamescreensaver");
        String readString = parcel.readString();
        try {
            TraceWrapper.traceBegin(8L, "gameassist_" + readString);
            j(readString, parcel.readInt() == 1 ? parcel.readBundle() : new Bundle());
            TraceWrapper.traceEnd(8L);
            return true;
        } catch (Throwable th) {
            TraceWrapper.traceEnd(8L);
            throw th;
        }
    }
}
