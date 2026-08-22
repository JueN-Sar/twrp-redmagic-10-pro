package cn.nubia.screensaver.view;

import android.app.Dialog;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.Looper;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import com.zte.gameassist.utils.GaLog;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Objects;

/* loaded from: classes.dex */
public class GamePresentation extends Dialog {

    /* renamed from: c, reason: collision with root package name */
    private final Display f9185c;

    /* renamed from: h, reason: collision with root package name */
    private final DisplayManager f9186h;

    /* renamed from: i, reason: collision with root package name */
    private final Handler f9187i;

    /* renamed from: j, reason: collision with root package name */
    private final DisplayManager.DisplayListener f9188j;

    public GamePresentation(Context context, Display display, int i2, int i3) {
        super(d(context, display, i2, i3), i2);
        Looper myLooper = Looper.myLooper();
        Objects.requireNonNull(myLooper, "Presentation must be constructed on a looper thread.");
        this.f9187i = new Handler(myLooper);
        this.f9188j = new DisplayManager.DisplayListener() { // from class: cn.nubia.screensaver.view.GamePresentation.1
            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayAdded(int i4) {
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayChanged(int i4) {
                if (i4 == GamePresentation.this.f9185c.getDisplayId()) {
                    GamePresentation.this.h();
                }
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayRemoved(int i4) {
                if (i4 == GamePresentation.this.f9185c.getDisplayId()) {
                    GamePresentation.this.i();
                }
            }
        };
        this.f9185c = display;
        this.f9186h = (DisplayManager) getContext().getSystemService(DisplayManager.class);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        window.setTitle("GamePresentation");
        window.setAttributes(attributes);
        window.setGravity(119);
        window.setType(g(i3, display));
        setCanceledOnTouchOutside(false);
    }

    private static Context d(Context context, Display display, int i2, int i3) {
        if (context == null) {
            throw new IllegalArgumentException("outerContext must not be null");
        }
        if (display != null) {
            return context.createDisplayContext(display).createWindowContext(g(i3, display), null);
        }
        throw new IllegalArgumentException("display must not be null");
    }

    private static int g(int i2, Display display) {
        return i2 != -1 ? i2 : (display.getFlags() & 4) != 0 ? 2030 : 2037;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        k();
        cancel();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        try {
            try {
                super.dismiss();
                if (!isShowing()) {
                    return;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                GaLog.b("Presentation", "dismiss presentation error " + e2);
                if (!isShowing()) {
                    return;
                }
            }
            GaLog.a("Presentation", "finally dismiss");
            super.dismiss();
        } catch (Throwable th) {
            if (isShowing()) {
                GaLog.a("Presentation", "finally dismiss");
                super.dismiss();
            }
            throw th;
        }
    }

    public void e(FileDescriptor fileDescriptor, PrintWriter printWriter, String str) {
    }

    public Display f() {
        return this.f9185c;
    }

    public void j() {
    }

    public void k() {
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        this.f9186h.registerDisplayListener(this.f9188j, this.f9187i);
    }

    @Override // android.app.Dialog
    protected void onStop() {
        this.f9186h.unregisterDisplayListener(this.f9188j);
        super.onStop();
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
    }
}
