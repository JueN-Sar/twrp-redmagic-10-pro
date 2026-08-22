package androidx.loader.app;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.collection.SparseArrayCompat;
import androidx.core.util.DebugUtils;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
class LoaderManagerImpl extends LoaderManager {

    /* renamed from: c, reason: collision with root package name */
    static boolean f4426c = false;

    /* renamed from: a, reason: collision with root package name */
    private final LifecycleOwner f4427a;

    /* renamed from: b, reason: collision with root package name */
    private final LoaderViewModel f4428b;

    public static class LoaderInfo<D> extends MutableLiveData<D> implements Loader.OnLoadCompleteListener<D> {

        /* renamed from: l, reason: collision with root package name */
        private final int f4429l;

        /* renamed from: m, reason: collision with root package name */
        private final Bundle f4430m;

        /* renamed from: n, reason: collision with root package name */
        private final Loader f4431n;

        /* renamed from: o, reason: collision with root package name */
        private LifecycleOwner f4432o;

        /* renamed from: p, reason: collision with root package name */
        private LoaderObserver f4433p;

        /* renamed from: q, reason: collision with root package name */
        private Loader f4434q;

        @Override // androidx.loader.content.Loader.OnLoadCompleteListener
        public void a(Loader loader, Object obj) {
            if (LoaderManagerImpl.f4426c) {
                Log.v("LoaderManager", "onLoadComplete: " + this);
            }
            if (Looper.myLooper() == Looper.getMainLooper()) {
                o(obj);
                return;
            }
            if (LoaderManagerImpl.f4426c) {
                Log.w("LoaderManager", "onLoadComplete was incorrectly called on a background thread");
            }
            m(obj);
        }

        @Override // androidx.lifecycle.LiveData
        protected void k() {
            if (LoaderManagerImpl.f4426c) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            this.f4431n.v();
        }

        @Override // androidx.lifecycle.LiveData
        protected void l() {
            if (LoaderManagerImpl.f4426c) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            this.f4431n.w();
        }

        @Override // androidx.lifecycle.LiveData
        public void n(Observer observer) {
            super.n(observer);
            this.f4432o = null;
            this.f4433p = null;
        }

        @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
        public void o(Object obj) {
            super.o(obj);
            Loader loader = this.f4434q;
            if (loader != null) {
                loader.t();
                this.f4434q = null;
            }
        }

        Loader p(boolean z) {
            if (LoaderManagerImpl.f4426c) {
                Log.v("LoaderManager", "  Destroying: " + this);
            }
            this.f4431n.b();
            this.f4431n.a();
            LoaderObserver loaderObserver = this.f4433p;
            if (loaderObserver != null) {
                n(loaderObserver);
                if (z) {
                    loaderObserver.d();
                }
            }
            this.f4431n.y(this);
            if ((loaderObserver == null || loaderObserver.c()) && !z) {
                return this.f4431n;
            }
            this.f4431n.t();
            return this.f4434q;
        }

        public void q(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.f4429l);
            printWriter.print(" mArgs=");
            printWriter.println(this.f4430m);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.f4431n);
            this.f4431n.g(str + "  ", fileDescriptor, printWriter, strArr);
            if (this.f4433p != null) {
                printWriter.print(str);
                printWriter.print("mCallbacks=");
                printWriter.println(this.f4433p);
                this.f4433p.b(str + "  ", printWriter);
            }
            printWriter.print(str);
            printWriter.print("mData=");
            printWriter.println(r().d(f()));
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.println(h());
        }

        Loader r() {
            return this.f4431n;
        }

        void s() {
            LifecycleOwner lifecycleOwner = this.f4432o;
            LoaderObserver loaderObserver = this.f4433p;
            if (lifecycleOwner == null || loaderObserver == null) {
                return;
            }
            super.n(loaderObserver);
            i(lifecycleOwner, loaderObserver);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.f4429l);
            sb.append(" : ");
            DebugUtils.a(this.f4431n, sb);
            sb.append("}}");
            return sb.toString();
        }
    }

    static class LoaderObserver<D> implements Observer<D> {

        /* renamed from: a, reason: collision with root package name */
        private final Loader f4435a;

        /* renamed from: b, reason: collision with root package name */
        private final LoaderManager.LoaderCallbacks f4436b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f4437c;

        @Override // androidx.lifecycle.Observer
        public void a(Object obj) {
            if (LoaderManagerImpl.f4426c) {
                Log.v("LoaderManager", "  onLoadFinished in " + this.f4435a + ": " + this.f4435a.d(obj));
            }
            this.f4436b.a(this.f4435a, obj);
            this.f4437c = true;
        }

        public void b(String str, PrintWriter printWriter) {
            printWriter.print(str);
            printWriter.print("mDeliveredData=");
            printWriter.println(this.f4437c);
        }

        boolean c() {
            return this.f4437c;
        }

        void d() {
            if (this.f4437c) {
                if (LoaderManagerImpl.f4426c) {
                    Log.v("LoaderManager", "  Resetting: " + this.f4435a);
                }
                this.f4436b.b(this.f4435a);
            }
        }

        public String toString() {
            return this.f4436b.toString();
        }
    }

    static class LoaderViewModel extends ViewModel {

        /* renamed from: f, reason: collision with root package name */
        private static final ViewModelProvider.Factory f4438f = new ViewModelProvider.Factory() { // from class: androidx.loader.app.LoaderManagerImpl.LoaderViewModel.1
            @Override // androidx.lifecycle.ViewModelProvider.Factory
            public ViewModel b(Class cls) {
                return new LoaderViewModel();
            }
        };

        /* renamed from: d, reason: collision with root package name */
        private SparseArrayCompat f4439d = new SparseArrayCompat();

        /* renamed from: e, reason: collision with root package name */
        private boolean f4440e = false;

        LoaderViewModel() {
        }

        static LoaderViewModel g(ViewModelStore viewModelStore) {
            return (LoaderViewModel) new ViewModelProvider(viewModelStore, f4438f).a(LoaderViewModel.class);
        }

        @Override // androidx.lifecycle.ViewModel
        protected void d() {
            super.d();
            int j2 = this.f4439d.j();
            for (int i2 = 0; i2 < j2; i2++) {
                ((LoaderInfo) this.f4439d.k(i2)).p(true);
            }
            this.f4439d.b();
        }

        public void f(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.f4439d.j() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i2 = 0; i2 < this.f4439d.j(); i2++) {
                    LoaderInfo loaderInfo = (LoaderInfo) this.f4439d.k(i2);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.f4439d.h(i2));
                    printWriter.print(": ");
                    printWriter.println(loaderInfo.toString());
                    loaderInfo.q(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }

        void h() {
            int j2 = this.f4439d.j();
            for (int i2 = 0; i2 < j2; i2++) {
                ((LoaderInfo) this.f4439d.k(i2)).s();
            }
        }
    }

    LoaderManagerImpl(LifecycleOwner lifecycleOwner, ViewModelStore viewModelStore) {
        this.f4427a = lifecycleOwner;
        this.f4428b = LoaderViewModel.g(viewModelStore);
    }

    @Override // androidx.loader.app.LoaderManager
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.f4428b.f(str, fileDescriptor, printWriter, strArr);
    }

    @Override // androidx.loader.app.LoaderManager
    public void c() {
        this.f4428b.h();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        DebugUtils.a(this.f4427a, sb);
        sb.append("}}");
        return sb.toString();
    }
}
