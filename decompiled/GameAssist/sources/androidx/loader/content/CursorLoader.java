package androidx.loader.content;

import android.database.Cursor;
import android.net.Uri;
import androidx.core.content.ContentResolverCompat;
import androidx.core.os.CancellationSignal;
import androidx.core.os.OperationCanceledException;
import androidx.loader.content.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;

/* loaded from: classes.dex */
public class CursorLoader extends AsyncTaskLoader<Cursor> {

    /* renamed from: p, reason: collision with root package name */
    final Loader.ForceLoadContentObserver f4450p;

    /* renamed from: q, reason: collision with root package name */
    Uri f4451q;

    /* renamed from: r, reason: collision with root package name */
    String[] f4452r;

    /* renamed from: s, reason: collision with root package name */
    String f4453s;
    String[] t;
    String u;
    Cursor v;
    CancellationSignal w;

    @Override // androidx.loader.content.Loader
    /* renamed from: H, reason: merged with bridge method [inline-methods] */
    public void f(Cursor cursor) {
        if (k()) {
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        Cursor cursor2 = this.v;
        this.v = cursor;
        if (l()) {
            super.f(cursor);
        }
        if (cursor2 == null || cursor2 == cursor || cursor2.isClosed()) {
            return;
        }
        cursor2.close();
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    /* renamed from: I, reason: merged with bridge method [inline-methods] */
    public Cursor E() {
        synchronized (this) {
            if (D()) {
                throw new OperationCanceledException();
            }
            this.w = new CancellationSignal();
        }
        try {
            Cursor b2 = ContentResolverCompat.b(i().getContentResolver(), this.f4451q, this.f4452r, this.f4453s, this.t, this.u, this.w);
            if (b2 != null) {
                try {
                    b2.getCount();
                    b2.registerContentObserver(this.f4450p);
                } catch (RuntimeException e2) {
                    b2.close();
                    throw e2;
                }
            }
            synchronized (this) {
                this.w = null;
            }
            return b2;
        } catch (Throwable th) {
            synchronized (this) {
                this.w = null;
                throw th;
            }
        }
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    /* renamed from: J, reason: merged with bridge method [inline-methods] */
    public void F(Cursor cursor) {
        if (cursor == null || cursor.isClosed()) {
            return;
        }
        cursor.close();
    }

    @Override // androidx.loader.content.AsyncTaskLoader, androidx.loader.content.Loader
    public void g(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.g(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("mUri=");
        printWriter.println(this.f4451q);
        printWriter.print(str);
        printWriter.print("mProjection=");
        printWriter.println(Arrays.toString(this.f4452r));
        printWriter.print(str);
        printWriter.print("mSelection=");
        printWriter.println(this.f4453s);
        printWriter.print(str);
        printWriter.print("mSelectionArgs=");
        printWriter.println(Arrays.toString(this.t));
        printWriter.print(str);
        printWriter.print("mSortOrder=");
        printWriter.println(this.u);
        printWriter.print(str);
        printWriter.print("mCursor=");
        printWriter.println(this.v);
        printWriter.print(str);
        printWriter.print("mContentChanged=");
        printWriter.println(this.f4461h);
    }

    @Override // androidx.loader.content.Loader
    protected void q() {
        super.q();
        s();
        Cursor cursor = this.v;
        if (cursor != null && !cursor.isClosed()) {
            this.v.close();
        }
        this.v = null;
    }

    @Override // androidx.loader.content.Loader
    protected void r() {
        Cursor cursor = this.v;
        if (cursor != null) {
            f(cursor);
        }
        if (x() || this.v == null) {
            h();
        }
    }

    @Override // androidx.loader.content.Loader
    protected void s() {
        b();
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public void z() {
        super.z();
        synchronized (this) {
            try {
                CancellationSignal cancellationSignal = this.w;
                if (cancellationSignal != null) {
                    cancellationSignal.a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
