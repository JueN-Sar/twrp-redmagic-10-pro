package androidx.loader.content;

import android.content.Context;
import android.database.ContentObserver;
import androidx.core.util.DebugUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class Loader<D> {

    /* renamed from: a, reason: collision with root package name */
    int f4454a;

    /* renamed from: b, reason: collision with root package name */
    OnLoadCompleteListener f4455b;

    /* renamed from: c, reason: collision with root package name */
    OnLoadCanceledListener f4456c;

    /* renamed from: d, reason: collision with root package name */
    Context f4457d;

    /* renamed from: e, reason: collision with root package name */
    boolean f4458e;

    /* renamed from: f, reason: collision with root package name */
    boolean f4459f;

    /* renamed from: g, reason: collision with root package name */
    boolean f4460g;

    /* renamed from: h, reason: collision with root package name */
    boolean f4461h;

    /* renamed from: i, reason: collision with root package name */
    boolean f4462i;

    public final class ForceLoadContentObserver extends ContentObserver {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Loader f4463a;

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            this.f4463a.o();
        }
    }

    public interface OnLoadCanceledListener<D> {
        void a(Loader loader);
    }

    public interface OnLoadCompleteListener<D> {
        void a(Loader loader, Object obj);
    }

    public void a() {
        this.f4459f = true;
        m();
    }

    public boolean b() {
        return n();
    }

    public void c() {
        this.f4462i = false;
    }

    public String d(Object obj) {
        StringBuilder sb = new StringBuilder(64);
        DebugUtils.a(obj, sb);
        sb.append("}");
        return sb.toString();
    }

    public void e() {
        OnLoadCanceledListener onLoadCanceledListener = this.f4456c;
        if (onLoadCanceledListener != null) {
            onLoadCanceledListener.a(this);
        }
    }

    public void f(Object obj) {
        OnLoadCompleteListener onLoadCompleteListener = this.f4455b;
        if (onLoadCompleteListener != null) {
            onLoadCompleteListener.a(this, obj);
        }
    }

    public void g(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mId=");
        printWriter.print(this.f4454a);
        printWriter.print(" mListener=");
        printWriter.println(this.f4455b);
        if (this.f4458e || this.f4461h || this.f4462i) {
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.print(this.f4458e);
            printWriter.print(" mContentChanged=");
            printWriter.print(this.f4461h);
            printWriter.print(" mProcessingChange=");
            printWriter.println(this.f4462i);
        }
        if (this.f4459f || this.f4460g) {
            printWriter.print(str);
            printWriter.print("mAbandoned=");
            printWriter.print(this.f4459f);
            printWriter.print(" mReset=");
            printWriter.println(this.f4460g);
        }
    }

    public void h() {
        p();
    }

    public Context i() {
        return this.f4457d;
    }

    public boolean j() {
        return this.f4459f;
    }

    public boolean k() {
        return this.f4460g;
    }

    public boolean l() {
        return this.f4458e;
    }

    protected void m() {
    }

    protected boolean n() {
        return false;
    }

    public void o() {
        if (this.f4458e) {
            h();
        } else {
            this.f4461h = true;
        }
    }

    protected void p() {
    }

    protected void q() {
    }

    protected void r() {
    }

    protected void s() {
    }

    public void t() {
        q();
        this.f4460g = true;
        this.f4458e = false;
        this.f4459f = false;
        this.f4461h = false;
        this.f4462i = false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        DebugUtils.a(this, sb);
        sb.append(" id=");
        sb.append(this.f4454a);
        sb.append("}");
        return sb.toString();
    }

    public void u() {
        if (this.f4462i) {
            o();
        }
    }

    public final void v() {
        this.f4458e = true;
        this.f4460g = false;
        this.f4459f = false;
        r();
    }

    public void w() {
        this.f4458e = false;
        s();
    }

    public boolean x() {
        boolean z = this.f4461h;
        this.f4461h = false;
        this.f4462i |= z;
        return z;
    }

    public void y(OnLoadCompleteListener onLoadCompleteListener) {
        OnLoadCompleteListener onLoadCompleteListener2 = this.f4455b;
        if (onLoadCompleteListener2 == null) {
            throw new IllegalStateException("No listener register");
        }
        if (onLoadCompleteListener2 != onLoadCompleteListener) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }
        this.f4455b = null;
    }
}
