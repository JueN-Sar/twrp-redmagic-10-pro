package cn.nubia.componentsdk.pay;

import android.os.Handler;
import android.os.Message;

/* loaded from: classes.dex */
public class MessageHandler extends Handler {

    /* renamed from: a, reason: collision with root package name */
    private OnBuildConnectListener f5975a;

    /* renamed from: b, reason: collision with root package name */
    private OnHTTPStatusListener f5976b;

    /* renamed from: c, reason: collision with root package name */
    public OnProcessUpdateListener f5977c;

    /* renamed from: d, reason: collision with root package name */
    private OnProcessCompleteListener f5978d;

    /* renamed from: e, reason: collision with root package name */
    private OnProcessErrorListener f5979e;

    /* renamed from: f, reason: collision with root package name */
    private OnProcessCancelListener f5980f;

    /* renamed from: g, reason: collision with root package name */
    public OnSpeedListener f5981g;

    /* renamed from: h, reason: collision with root package name */
    public OnReDirectListener f5982h;

    public interface OnBuildConnectListener {
        void a(int i2, int i3);
    }

    public interface OnHTTPStatusListener {
        void a(int i2, int i3);
    }

    public interface OnProcessCancelListener {
        void a(int i2);
    }

    public interface OnProcessCompleteListener {
        void a(int i2, int i3);
    }

    public interface OnProcessErrorListener {
        void a(int i2, int i3, Exception exc);
    }

    public interface OnProcessUpdateListener {
        void a(int i2, int i3);
    }

    public interface OnReDirectListener {
        void a(int i2, String str);
    }

    public interface OnSpeedListener {
        void a(int i2, long j2, long j3);
    }

    public void a(OnBuildConnectListener onBuildConnectListener) {
        this.f5975a = onBuildConnectListener;
    }

    public void b(OnHTTPStatusListener onHTTPStatusListener) {
        this.f5976b = onHTTPStatusListener;
    }

    public void c(OnProcessCompleteListener onProcessCompleteListener) {
        this.f5978d = onProcessCompleteListener;
    }

    public void d(OnProcessErrorListener onProcessErrorListener) {
        this.f5979e = onProcessErrorListener;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        Object obj;
        Long[] lArr;
        if (message == null) {
        }
        switch (message.what) {
            case -2:
                OnProcessCancelListener onProcessCancelListener = this.f5980f;
                if (onProcessCancelListener != null) {
                    onProcessCancelListener.a(message.arg1);
                    break;
                }
                break;
            case -1:
                OnProcessErrorListener onProcessErrorListener = this.f5979e;
                if (onProcessErrorListener != null && (obj = message.obj) != null && (obj instanceof Exception)) {
                    onProcessErrorListener.a(message.arg1, message.arg2, (Exception) obj);
                    break;
                }
                break;
            case 0:
                OnBuildConnectListener onBuildConnectListener = this.f5975a;
                if (onBuildConnectListener != null) {
                    onBuildConnectListener.a(message.arg1, message.arg2);
                    break;
                }
                break;
            case 1:
                OnHTTPStatusListener onHTTPStatusListener = this.f5976b;
                if (onHTTPStatusListener != null) {
                    onHTTPStatusListener.a(message.arg1, message.arg2);
                    break;
                }
                break;
            case 2:
                if (this.f5977c != null && CNetHttpTransfer.b().a(message.arg1) != null) {
                    this.f5977c.a(message.arg1, message.arg2);
                    break;
                }
                break;
            case 3:
                OnProcessCompleteListener onProcessCompleteListener = this.f5978d;
                if (onProcessCompleteListener != null) {
                    onProcessCompleteListener.a(message.arg1, message.arg2);
                    break;
                }
                break;
            case 4:
                if (this.f5981g != null && CNetHttpTransfer.b().a(message.arg1) != null && (lArr = (Long[]) message.obj) != null && lArr.length >= 2) {
                    this.f5981g.a(message.arg1, lArr[0].longValue(), lArr[1].longValue());
                    break;
                }
                break;
            case 5:
                OnReDirectListener onReDirectListener = this.f5982h;
                if (onReDirectListener != null) {
                    onReDirectListener.a(message.arg1, (String) message.obj);
                    break;
                }
                break;
        }
    }
}
