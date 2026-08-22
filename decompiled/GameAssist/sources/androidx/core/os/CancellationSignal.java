package androidx.core.os;

@Deprecated
/* loaded from: classes.dex */
public final class CancellationSignal {

    /* renamed from: a, reason: collision with root package name */
    private boolean f3106a;

    /* renamed from: b, reason: collision with root package name */
    private OnCancelListener f3107b;

    /* renamed from: c, reason: collision with root package name */
    private Object f3108c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f3109d;

    public interface OnCancelListener {
        void onCancel();
    }

    private void d() {
        while (this.f3109d) {
            try {
                wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    public void a() {
        synchronized (this) {
            try {
                if (this.f3106a) {
                    return;
                }
                this.f3106a = true;
                this.f3109d = true;
                OnCancelListener onCancelListener = this.f3107b;
                Object obj = this.f3108c;
                if (onCancelListener != null) {
                    try {
                        onCancelListener.onCancel();
                    } catch (Throwable th) {
                        synchronized (this) {
                            this.f3109d = false;
                            notifyAll();
                            throw th;
                        }
                    }
                }
                if (obj != null) {
                    ((android.os.CancellationSignal) obj).cancel();
                }
                synchronized (this) {
                    this.f3109d = false;
                    notifyAll();
                }
            } finally {
            }
        }
    }

    public Object b() {
        Object obj;
        synchronized (this) {
            try {
                if (this.f3108c == null) {
                    android.os.CancellationSignal cancellationSignal = new android.os.CancellationSignal();
                    this.f3108c = cancellationSignal;
                    if (this.f3106a) {
                        cancellationSignal.cancel();
                    }
                }
                obj = this.f3108c;
            } catch (Throwable th) {
                throw th;
            }
        }
        return obj;
    }

    public void c(OnCancelListener onCancelListener) {
        synchronized (this) {
            try {
                d();
                if (this.f3107b == onCancelListener) {
                    return;
                }
                this.f3107b = onCancelListener;
                if (this.f3106a && onCancelListener != null) {
                    onCancelListener.onCancel();
                }
            } finally {
            }
        }
    }
}
