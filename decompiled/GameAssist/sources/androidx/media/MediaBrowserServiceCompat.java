package androidx.media;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.collection.ArrayMap;
import androidx.core.app.BundleCompat;
import androidx.core.util.Pair;
import androidx.media.MediaBrowserServiceCompatApi21;
import androidx.media.MediaBrowserServiceCompatApi23;
import androidx.media.MediaBrowserServiceCompatApi26;
import androidx.media.MediaSessionManager;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public abstract class MediaBrowserServiceCompat extends Service {

    /* renamed from: l, reason: collision with root package name */
    static final boolean f4503l = Log.isLoggable("MBServiceCompat", 3);

    /* renamed from: c, reason: collision with root package name */
    private MediaBrowserServiceImpl f4504c;

    /* renamed from: i, reason: collision with root package name */
    ConnectionRecord f4506i;

    /* renamed from: k, reason: collision with root package name */
    MediaSessionCompat.Token f4508k;

    /* renamed from: h, reason: collision with root package name */
    final ArrayMap f4505h = new ArrayMap();

    /* renamed from: j, reason: collision with root package name */
    final ServiceHandler f4507j = new ServiceHandler();

    public static final class BrowserRoot {

        /* renamed from: a, reason: collision with root package name */
        private final String f4520a;

        /* renamed from: b, reason: collision with root package name */
        private final Bundle f4521b;

        public Bundle a() {
            return this.f4521b;
        }

        public String b() {
            return this.f4520a;
        }
    }

    private class ConnectionRecord implements IBinder.DeathRecipient {

        /* renamed from: c, reason: collision with root package name */
        public final String f4522c;

        /* renamed from: h, reason: collision with root package name */
        public final int f4523h;

        /* renamed from: i, reason: collision with root package name */
        public final int f4524i;

        /* renamed from: j, reason: collision with root package name */
        public final MediaSessionManager.RemoteUserInfo f4525j;

        /* renamed from: k, reason: collision with root package name */
        public final Bundle f4526k;

        /* renamed from: l, reason: collision with root package name */
        public final ServiceCallbacks f4527l;

        /* renamed from: m, reason: collision with root package name */
        public final HashMap f4528m = new HashMap();

        /* renamed from: n, reason: collision with root package name */
        public BrowserRoot f4529n;

        ConnectionRecord(String str, int i2, int i3, Bundle bundle, ServiceCallbacks serviceCallbacks) {
            this.f4522c = str;
            this.f4523h = i2;
            this.f4524i = i3;
            this.f4525j = new MediaSessionManager.RemoteUserInfo(str, i2, i3);
            this.f4526k = bundle;
            this.f4527l = serviceCallbacks;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            MediaBrowserServiceCompat.this.f4507j.post(new Runnable() { // from class: androidx.media.MediaBrowserServiceCompat.ConnectionRecord.1
                @Override // java.lang.Runnable
                public void run() {
                    ConnectionRecord connectionRecord = ConnectionRecord.this;
                    MediaBrowserServiceCompat.this.f4505h.remove(connectionRecord.f4527l.asBinder());
                }
            });
        }
    }

    interface MediaBrowserServiceImpl {
        IBinder e(Intent intent);

        void onCreate();
    }

    @RequiresApi
    class MediaBrowserServiceImplApi21 implements MediaBrowserServiceImpl, MediaBrowserServiceCompatApi21.ServiceCompatProxy {

        /* renamed from: a, reason: collision with root package name */
        final List f4532a = new ArrayList();

        /* renamed from: b, reason: collision with root package name */
        Object f4533b;

        /* renamed from: c, reason: collision with root package name */
        Messenger f4534c;

        /* renamed from: androidx.media.MediaBrowserServiceCompat$MediaBrowserServiceImplApi21$1, reason: invalid class name */
        class AnonymousClass1 implements Runnable {

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ MediaSessionCompat.Token f4536c;

            /* renamed from: h, reason: collision with root package name */
            final /* synthetic */ MediaBrowserServiceImplApi21 f4537h;

            @Override // java.lang.Runnable
            public void run() {
                if (!this.f4537h.f4532a.isEmpty()) {
                    IMediaSession extraBinder = this.f4536c.getExtraBinder();
                    if (extraBinder != null) {
                        Iterator it = this.f4537h.f4532a.iterator();
                        while (it.hasNext()) {
                            BundleCompat.b((Bundle) it.next(), "extra_session_binder", extraBinder.asBinder());
                        }
                    }
                    this.f4537h.f4532a.clear();
                }
                MediaBrowserServiceCompatApi21.d(this.f4537h.f4533b, this.f4536c.getToken());
            }
        }

        /* renamed from: androidx.media.MediaBrowserServiceCompat$MediaBrowserServiceImplApi21$3, reason: invalid class name */
        class AnonymousClass3 implements Runnable {

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ String f4540c;

            /* renamed from: h, reason: collision with root package name */
            final /* synthetic */ Bundle f4541h;

            /* renamed from: i, reason: collision with root package name */
            final /* synthetic */ MediaBrowserServiceImplApi21 f4542i;

            @Override // java.lang.Runnable
            public void run() {
                Iterator it = MediaBrowserServiceCompat.this.f4505h.keySet().iterator();
                while (it.hasNext()) {
                    this.f4542i.f((ConnectionRecord) MediaBrowserServiceCompat.this.f4505h.get((IBinder) it.next()), this.f4540c, this.f4541h);
                }
            }
        }

        /* renamed from: androidx.media.MediaBrowserServiceCompat$MediaBrowserServiceImplApi21$4, reason: invalid class name */
        class AnonymousClass4 implements Runnable {

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ MediaSessionManager.RemoteUserInfo f4543c;

            /* renamed from: h, reason: collision with root package name */
            final /* synthetic */ String f4544h;

            /* renamed from: i, reason: collision with root package name */
            final /* synthetic */ Bundle f4545i;

            /* renamed from: j, reason: collision with root package name */
            final /* synthetic */ MediaBrowserServiceImplApi21 f4546j;

            @Override // java.lang.Runnable
            public void run() {
                for (int i2 = 0; i2 < MediaBrowserServiceCompat.this.f4505h.size(); i2++) {
                    ConnectionRecord connectionRecord = (ConnectionRecord) MediaBrowserServiceCompat.this.f4505h.j(i2);
                    if (connectionRecord.f4525j.equals(this.f4543c)) {
                        this.f4546j.f(connectionRecord, this.f4544h, this.f4545i);
                    }
                }
            }
        }

        MediaBrowserServiceImplApi21() {
        }

        @Override // androidx.media.MediaBrowserServiceCompatApi21.ServiceCompatProxy
        public MediaBrowserServiceCompatApi21.BrowserRoot c(String str, int i2, Bundle bundle) {
            Bundle bundle2;
            if (bundle == null || bundle.getInt("extra_client_version", 0) == 0) {
                bundle2 = null;
            } else {
                bundle.remove("extra_client_version");
                this.f4534c = new Messenger(MediaBrowserServiceCompat.this.f4507j);
                bundle2 = new Bundle();
                bundle2.putInt("extra_service_version", 2);
                BundleCompat.b(bundle2, "extra_messenger", this.f4534c.getBinder());
                MediaSessionCompat.Token token = MediaBrowserServiceCompat.this.f4508k;
                if (token != null) {
                    IMediaSession extraBinder = token.getExtraBinder();
                    BundleCompat.b(bundle2, "extra_session_binder", extraBinder == null ? null : extraBinder.asBinder());
                } else {
                    this.f4532a.add(bundle2);
                }
            }
            MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
            mediaBrowserServiceCompat.f4506i = mediaBrowserServiceCompat.new ConnectionRecord(str, -1, i2, bundle, null);
            BrowserRoot e2 = MediaBrowserServiceCompat.this.e(str, i2, bundle);
            MediaBrowserServiceCompat.this.f4506i = null;
            if (e2 == null) {
                return null;
            }
            if (bundle2 == null) {
                bundle2 = e2.a();
            } else if (e2.a() != null) {
                bundle2.putAll(e2.a());
            }
            return new MediaBrowserServiceCompatApi21.BrowserRoot(e2.b(), bundle2);
        }

        @Override // androidx.media.MediaBrowserServiceCompatApi21.ServiceCompatProxy
        public void d(String str, final MediaBrowserServiceCompatApi21.ResultWrapper resultWrapper) {
            MediaBrowserServiceCompat.this.f(str, new Result<List<MediaBrowserCompat.MediaItem>>(str) { // from class: androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImplApi21.2
                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // androidx.media.MediaBrowserServiceCompat.Result
                /* renamed from: h, reason: merged with bridge method [inline-methods] */
                public void d(List list) {
                    ArrayList arrayList;
                    if (list != null) {
                        arrayList = new ArrayList();
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            MediaBrowserCompat.MediaItem mediaItem = (MediaBrowserCompat.MediaItem) it.next();
                            Parcel obtain = Parcel.obtain();
                            mediaItem.writeToParcel(obtain, 0);
                            arrayList.add(obtain);
                        }
                    } else {
                        arrayList = null;
                    }
                    resultWrapper.b(arrayList);
                }
            });
        }

        @Override // androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImpl
        public IBinder e(Intent intent) {
            return MediaBrowserServiceCompatApi21.b(this.f4533b, intent);
        }

        void f(ConnectionRecord connectionRecord, String str, Bundle bundle) {
            List<Pair> list = (List) connectionRecord.f4528m.get(str);
            if (list != null) {
                for (Pair pair : list) {
                    if (MediaBrowserCompatUtils.b(bundle, (Bundle) pair.f3271b)) {
                        MediaBrowserServiceCompat.this.m(str, connectionRecord, (Bundle) pair.f3271b, bundle);
                    }
                }
            }
        }

        @Override // androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImpl
        public void onCreate() {
            Object a2 = MediaBrowserServiceCompatApi21.a(MediaBrowserServiceCompat.this, this);
            this.f4533b = a2;
            MediaBrowserServiceCompatApi21.c(a2);
        }
    }

    @RequiresApi
    class MediaBrowserServiceImplApi23 extends MediaBrowserServiceImplApi21 implements MediaBrowserServiceCompatApi23.ServiceCompatProxy {
        MediaBrowserServiceImplApi23() {
            super();
        }

        @Override // androidx.media.MediaBrowserServiceCompatApi23.ServiceCompatProxy
        public void a(String str, final MediaBrowserServiceCompatApi21.ResultWrapper resultWrapper) {
            MediaBrowserServiceCompat.this.h(str, new Result<MediaBrowserCompat.MediaItem>(str) { // from class: androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImplApi23.1
                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // androidx.media.MediaBrowserServiceCompat.Result
                /* renamed from: h, reason: merged with bridge method [inline-methods] */
                public void d(MediaBrowserCompat.MediaItem mediaItem) {
                    if (mediaItem == null) {
                        resultWrapper.b(null);
                        return;
                    }
                    Parcel obtain = Parcel.obtain();
                    mediaItem.writeToParcel(obtain, 0);
                    resultWrapper.b(obtain);
                }
            });
        }

        @Override // androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImplApi21, androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImpl
        public void onCreate() {
            Object a2 = MediaBrowserServiceCompatApi23.a(MediaBrowserServiceCompat.this, this);
            this.f4533b = a2;
            MediaBrowserServiceCompatApi21.c(a2);
        }
    }

    @RequiresApi
    class MediaBrowserServiceImplApi26 extends MediaBrowserServiceImplApi23 implements MediaBrowserServiceCompatApi26.ServiceCompatProxy {
        MediaBrowserServiceImplApi26() {
            super();
        }

        @Override // androidx.media.MediaBrowserServiceCompatApi26.ServiceCompatProxy
        public void b(String str, final MediaBrowserServiceCompatApi26.ResultWrapper resultWrapper, Bundle bundle) {
            MediaBrowserServiceCompat.this.g(str, new Result<List<MediaBrowserCompat.MediaItem>>(str) { // from class: androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImplApi26.1
                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // androidx.media.MediaBrowserServiceCompat.Result
                /* renamed from: h, reason: merged with bridge method [inline-methods] */
                public void d(List list) {
                    ArrayList arrayList;
                    if (list != null) {
                        arrayList = new ArrayList();
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            MediaBrowserCompat.MediaItem mediaItem = (MediaBrowserCompat.MediaItem) it.next();
                            Parcel obtain = Parcel.obtain();
                            mediaItem.writeToParcel(obtain, 0);
                            arrayList.add(obtain);
                        }
                    } else {
                        arrayList = null;
                    }
                    resultWrapper.b(arrayList, a());
                }
            }, bundle);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImplApi23, androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImplApi21, androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImpl
        public void onCreate() {
            Object a2 = MediaBrowserServiceCompatApi26.a(MediaBrowserServiceCompat.this, this);
            this.f4533b = a2;
            MediaBrowserServiceCompatApi21.c(a2);
        }
    }

    @RequiresApi
    class MediaBrowserServiceImplApi28 extends MediaBrowserServiceImplApi26 {
        MediaBrowserServiceImplApi28() {
            super();
        }
    }

    class MediaBrowserServiceImplBase implements MediaBrowserServiceImpl {

        /* renamed from: a, reason: collision with root package name */
        private Messenger f4554a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ MediaBrowserServiceCompat f4555b;

        /* renamed from: androidx.media.MediaBrowserServiceCompat$MediaBrowserServiceImplBase$1, reason: invalid class name */
        class AnonymousClass1 implements Runnable {

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ MediaSessionCompat.Token f4556c;

            /* renamed from: h, reason: collision with root package name */
            final /* synthetic */ MediaBrowserServiceImplBase f4557h;

            @Override // java.lang.Runnable
            public void run() {
                Iterator it = this.f4557h.f4555b.f4505h.values().iterator();
                while (it.hasNext()) {
                    ConnectionRecord connectionRecord = (ConnectionRecord) it.next();
                    try {
                        connectionRecord.f4527l.b(connectionRecord.f4529n.b(), this.f4556c, connectionRecord.f4529n.a());
                    } catch (RemoteException unused) {
                        Log.w("MBServiceCompat", "Connection for " + connectionRecord.f4522c + " is no longer valid.");
                        it.remove();
                    }
                }
            }
        }

        /* renamed from: androidx.media.MediaBrowserServiceCompat$MediaBrowserServiceImplBase$2, reason: invalid class name */
        class AnonymousClass2 implements Runnable {

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ String f4558c;

            /* renamed from: h, reason: collision with root package name */
            final /* synthetic */ Bundle f4559h;

            /* renamed from: i, reason: collision with root package name */
            final /* synthetic */ MediaBrowserServiceImplBase f4560i;

            @Override // java.lang.Runnable
            public void run() {
                Iterator it = this.f4560i.f4555b.f4505h.keySet().iterator();
                while (it.hasNext()) {
                    this.f4560i.a((ConnectionRecord) this.f4560i.f4555b.f4505h.get((IBinder) it.next()), this.f4558c, this.f4559h);
                }
            }
        }

        /* renamed from: androidx.media.MediaBrowserServiceCompat$MediaBrowserServiceImplBase$3, reason: invalid class name */
        class AnonymousClass3 implements Runnable {

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ MediaSessionManager.RemoteUserInfo f4561c;

            /* renamed from: h, reason: collision with root package name */
            final /* synthetic */ String f4562h;

            /* renamed from: i, reason: collision with root package name */
            final /* synthetic */ Bundle f4563i;

            /* renamed from: j, reason: collision with root package name */
            final /* synthetic */ MediaBrowserServiceImplBase f4564j;

            @Override // java.lang.Runnable
            public void run() {
                for (int i2 = 0; i2 < this.f4564j.f4555b.f4505h.size(); i2++) {
                    ConnectionRecord connectionRecord = (ConnectionRecord) this.f4564j.f4555b.f4505h.j(i2);
                    if (connectionRecord.f4525j.equals(this.f4561c)) {
                        this.f4564j.a(connectionRecord, this.f4562h, this.f4563i);
                        return;
                    }
                }
            }
        }

        void a(ConnectionRecord connectionRecord, String str, Bundle bundle) {
            List<Pair> list = (List) connectionRecord.f4528m.get(str);
            if (list != null) {
                for (Pair pair : list) {
                    if (MediaBrowserCompatUtils.b(bundle, (Bundle) pair.f3271b)) {
                        this.f4555b.m(str, connectionRecord, (Bundle) pair.f3271b, bundle);
                    }
                }
            }
        }

        @Override // androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImpl
        public IBinder e(Intent intent) {
            if ("android.media.browse.MediaBrowserService".equals(intent.getAction())) {
                return this.f4554a.getBinder();
            }
            return null;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImpl
        public void onCreate() {
            this.f4554a = new Messenger(this.f4555b.f4507j);
        }
    }

    public static class Result<T> {

        /* renamed from: a, reason: collision with root package name */
        private final Object f4565a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f4566b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f4567c;

        /* renamed from: d, reason: collision with root package name */
        private boolean f4568d;

        /* renamed from: e, reason: collision with root package name */
        private int f4569e;

        Result(Object obj) {
            this.f4565a = obj;
        }

        int a() {
            return this.f4569e;
        }

        boolean b() {
            return this.f4566b || this.f4567c || this.f4568d;
        }

        void c(Bundle bundle) {
            throw new UnsupportedOperationException("It is not supported to send an error for " + this.f4565a);
        }

        void d(Object obj) {
        }

        public void e(Bundle bundle) {
            if (!this.f4567c && !this.f4568d) {
                this.f4568d = true;
                c(bundle);
            } else {
                throw new IllegalStateException("sendError() called when either sendResult() or sendError() had already been called for: " + this.f4565a);
            }
        }

        public void f(Object obj) {
            if (!this.f4567c && !this.f4568d) {
                this.f4567c = true;
                d(obj);
            } else {
                throw new IllegalStateException("sendResult() called when either sendResult() or sendError() had already been called for: " + this.f4565a);
            }
        }

        void g(int i2) {
            this.f4569e = i2;
        }
    }

    private class ServiceBinderImpl {
        ServiceBinderImpl() {
        }

        public void a(final String str, final IBinder iBinder, final Bundle bundle, final ServiceCallbacks serviceCallbacks) {
            MediaBrowserServiceCompat.this.f4507j.a(new Runnable() { // from class: androidx.media.MediaBrowserServiceCompat.ServiceBinderImpl.3
                @Override // java.lang.Runnable
                public void run() {
                    ConnectionRecord connectionRecord = (ConnectionRecord) MediaBrowserServiceCompat.this.f4505h.get(serviceCallbacks.asBinder());
                    if (connectionRecord != null) {
                        MediaBrowserServiceCompat.this.a(str, connectionRecord, iBinder, bundle);
                        return;
                    }
                    Log.w("MBServiceCompat", "addSubscription for callback that isn't registered id=" + str);
                }
            });
        }

        public void b(final String str, final int i2, final int i3, final Bundle bundle, final ServiceCallbacks serviceCallbacks) {
            if (MediaBrowserServiceCompat.this.c(str, i3)) {
                MediaBrowserServiceCompat.this.f4507j.a(new Runnable() { // from class: androidx.media.MediaBrowserServiceCompat.ServiceBinderImpl.1
                    @Override // java.lang.Runnable
                    public void run() {
                        IBinder asBinder = serviceCallbacks.asBinder();
                        MediaBrowserServiceCompat.this.f4505h.remove(asBinder);
                        ConnectionRecord connectionRecord = MediaBrowserServiceCompat.this.new ConnectionRecord(str, i2, i3, bundle, serviceCallbacks);
                        MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
                        mediaBrowserServiceCompat.f4506i = connectionRecord;
                        BrowserRoot e2 = mediaBrowserServiceCompat.e(str, i3, bundle);
                        connectionRecord.f4529n = e2;
                        MediaBrowserServiceCompat mediaBrowserServiceCompat2 = MediaBrowserServiceCompat.this;
                        mediaBrowserServiceCompat2.f4506i = null;
                        if (e2 != null) {
                            try {
                                mediaBrowserServiceCompat2.f4505h.put(asBinder, connectionRecord);
                                asBinder.linkToDeath(connectionRecord, 0);
                                if (MediaBrowserServiceCompat.this.f4508k != null) {
                                    serviceCallbacks.b(connectionRecord.f4529n.b(), MediaBrowserServiceCompat.this.f4508k, connectionRecord.f4529n.a());
                                    return;
                                }
                                return;
                            } catch (RemoteException unused) {
                                Log.w("MBServiceCompat", "Calling onConnect() failed. Dropping client. pkg=" + str);
                                MediaBrowserServiceCompat.this.f4505h.remove(asBinder);
                                return;
                            }
                        }
                        Log.i("MBServiceCompat", "No root for client " + str + " from service " + getClass().getName());
                        try {
                            serviceCallbacks.onConnectFailed();
                        } catch (RemoteException unused2) {
                            Log.w("MBServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=" + str);
                        }
                    }
                });
                return;
            }
            throw new IllegalArgumentException("Package/uid mismatch: uid=" + i3 + " package=" + str);
        }

        public void c(final ServiceCallbacks serviceCallbacks) {
            MediaBrowserServiceCompat.this.f4507j.a(new Runnable() { // from class: androidx.media.MediaBrowserServiceCompat.ServiceBinderImpl.2
                @Override // java.lang.Runnable
                public void run() {
                    ConnectionRecord connectionRecord = (ConnectionRecord) MediaBrowserServiceCompat.this.f4505h.remove(serviceCallbacks.asBinder());
                    if (connectionRecord != null) {
                        connectionRecord.f4527l.asBinder().unlinkToDeath(connectionRecord, 0);
                    }
                }
            });
        }

        public void d(final String str, final ResultReceiver resultReceiver, final ServiceCallbacks serviceCallbacks) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.f4507j.a(new Runnable() { // from class: androidx.media.MediaBrowserServiceCompat.ServiceBinderImpl.5
                @Override // java.lang.Runnable
                public void run() {
                    ConnectionRecord connectionRecord = (ConnectionRecord) MediaBrowserServiceCompat.this.f4505h.get(serviceCallbacks.asBinder());
                    if (connectionRecord != null) {
                        MediaBrowserServiceCompat.this.n(str, connectionRecord, resultReceiver);
                        return;
                    }
                    Log.w("MBServiceCompat", "getMediaItem for callback that isn't registered id=" + str);
                }
            });
        }

        public void e(final ServiceCallbacks serviceCallbacks, final String str, final int i2, final int i3, final Bundle bundle) {
            MediaBrowserServiceCompat.this.f4507j.a(new Runnable() { // from class: androidx.media.MediaBrowserServiceCompat.ServiceBinderImpl.6
                @Override // java.lang.Runnable
                public void run() {
                    IBinder asBinder = serviceCallbacks.asBinder();
                    MediaBrowserServiceCompat.this.f4505h.remove(asBinder);
                    ConnectionRecord connectionRecord = MediaBrowserServiceCompat.this.new ConnectionRecord(str, i2, i3, bundle, serviceCallbacks);
                    MediaBrowserServiceCompat.this.f4505h.put(asBinder, connectionRecord);
                    try {
                        asBinder.linkToDeath(connectionRecord, 0);
                    } catch (RemoteException unused) {
                        Log.w("MBServiceCompat", "IBinder is already dead.");
                    }
                }
            });
        }

        public void f(final String str, final IBinder iBinder, final ServiceCallbacks serviceCallbacks) {
            MediaBrowserServiceCompat.this.f4507j.a(new Runnable() { // from class: androidx.media.MediaBrowserServiceCompat.ServiceBinderImpl.4
                @Override // java.lang.Runnable
                public void run() {
                    ConnectionRecord connectionRecord = (ConnectionRecord) MediaBrowserServiceCompat.this.f4505h.get(serviceCallbacks.asBinder());
                    if (connectionRecord == null) {
                        Log.w("MBServiceCompat", "removeSubscription for callback that isn't registered id=" + str);
                        return;
                    }
                    if (MediaBrowserServiceCompat.this.p(str, connectionRecord, iBinder)) {
                        return;
                    }
                    Log.w("MBServiceCompat", "removeSubscription called for " + str + " which is not subscribed");
                }
            });
        }

        public void g(final String str, final Bundle bundle, final ResultReceiver resultReceiver, final ServiceCallbacks serviceCallbacks) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.f4507j.a(new Runnable() { // from class: androidx.media.MediaBrowserServiceCompat.ServiceBinderImpl.8
                @Override // java.lang.Runnable
                public void run() {
                    ConnectionRecord connectionRecord = (ConnectionRecord) MediaBrowserServiceCompat.this.f4505h.get(serviceCallbacks.asBinder());
                    if (connectionRecord != null) {
                        MediaBrowserServiceCompat.this.o(str, bundle, connectionRecord, resultReceiver);
                        return;
                    }
                    Log.w("MBServiceCompat", "search for callback that isn't registered query=" + str);
                }
            });
        }

        public void h(final String str, final Bundle bundle, final ResultReceiver resultReceiver, final ServiceCallbacks serviceCallbacks) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.f4507j.a(new Runnable() { // from class: androidx.media.MediaBrowserServiceCompat.ServiceBinderImpl.9
                @Override // java.lang.Runnable
                public void run() {
                    ConnectionRecord connectionRecord = (ConnectionRecord) MediaBrowserServiceCompat.this.f4505h.get(serviceCallbacks.asBinder());
                    if (connectionRecord != null) {
                        MediaBrowserServiceCompat.this.l(str, bundle, connectionRecord, resultReceiver);
                        return;
                    }
                    Log.w("MBServiceCompat", "sendCustomAction for callback that isn't registered action=" + str + ", extras=" + bundle);
                }
            });
        }

        public void i(final ServiceCallbacks serviceCallbacks) {
            MediaBrowserServiceCompat.this.f4507j.a(new Runnable() { // from class: androidx.media.MediaBrowserServiceCompat.ServiceBinderImpl.7
                @Override // java.lang.Runnable
                public void run() {
                    IBinder asBinder = serviceCallbacks.asBinder();
                    ConnectionRecord connectionRecord = (ConnectionRecord) MediaBrowserServiceCompat.this.f4505h.remove(asBinder);
                    if (connectionRecord != null) {
                        asBinder.unlinkToDeath(connectionRecord, 0);
                    }
                }
            });
        }
    }

    private interface ServiceCallbacks {
        void a(String str, List list, Bundle bundle, Bundle bundle2);

        IBinder asBinder();

        void b(String str, MediaSessionCompat.Token token, Bundle bundle);

        void onConnectFailed();
    }

    private static class ServiceCallbacksCompat implements ServiceCallbacks {

        /* renamed from: a, reason: collision with root package name */
        final Messenger f4610a;

        ServiceCallbacksCompat(Messenger messenger) {
            this.f4610a = messenger;
        }

        private void c(int i2, Bundle bundle) {
            Message obtain = Message.obtain();
            obtain.what = i2;
            obtain.arg1 = 2;
            obtain.setData(bundle);
            this.f4610a.send(obtain);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.ServiceCallbacks
        public void a(String str, List list, Bundle bundle, Bundle bundle2) {
            Bundle bundle3 = new Bundle();
            bundle3.putString("data_media_item_id", str);
            bundle3.putBundle("data_options", bundle);
            bundle3.putBundle("data_notify_children_changed_options", bundle2);
            if (list != null) {
                bundle3.putParcelableArrayList("data_media_item_list", list instanceof ArrayList ? (ArrayList) list : new ArrayList<>(list));
            }
            c(3, bundle3);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.ServiceCallbacks
        public IBinder asBinder() {
            return this.f4610a.getBinder();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.ServiceCallbacks
        public void b(String str, MediaSessionCompat.Token token, Bundle bundle) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putInt("extra_service_version", 2);
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_media_item_id", str);
            bundle2.putParcelable("data_media_session_token", token);
            bundle2.putBundle("data_root_hints", bundle);
            c(1, bundle2);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.ServiceCallbacks
        public void onConnectFailed() {
            c(2, null);
        }
    }

    private final class ServiceHandler extends Handler {

        /* renamed from: a, reason: collision with root package name */
        private final ServiceBinderImpl f4611a;

        ServiceHandler() {
            this.f4611a = MediaBrowserServiceCompat.this.new ServiceBinderImpl();
        }

        public void a(Runnable runnable) {
            if (Thread.currentThread() == getLooper().getThread()) {
                runnable.run();
            } else {
                post(runnable);
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Bundle data = message.getData();
            switch (message.what) {
                case 1:
                    Bundle bundle = data.getBundle("data_root_hints");
                    MediaSessionCompat.ensureClassLoader(bundle);
                    this.f4611a.b(data.getString("data_package_name"), data.getInt("data_calling_pid"), data.getInt("data_calling_uid"), bundle, new ServiceCallbacksCompat(message.replyTo));
                    break;
                case 2:
                    this.f4611a.c(new ServiceCallbacksCompat(message.replyTo));
                    break;
                case 3:
                    Bundle bundle2 = data.getBundle("data_options");
                    MediaSessionCompat.ensureClassLoader(bundle2);
                    this.f4611a.a(data.getString("data_media_item_id"), BundleCompat.a(data, "data_callback_token"), bundle2, new ServiceCallbacksCompat(message.replyTo));
                    break;
                case 4:
                    this.f4611a.f(data.getString("data_media_item_id"), BundleCompat.a(data, "data_callback_token"), new ServiceCallbacksCompat(message.replyTo));
                    break;
                case 5:
                    this.f4611a.d(data.getString("data_media_item_id"), (ResultReceiver) data.getParcelable("data_result_receiver"), new ServiceCallbacksCompat(message.replyTo));
                    break;
                case 6:
                    Bundle bundle3 = data.getBundle("data_root_hints");
                    MediaSessionCompat.ensureClassLoader(bundle3);
                    this.f4611a.e(new ServiceCallbacksCompat(message.replyTo), data.getString("data_package_name"), data.getInt("data_calling_pid"), data.getInt("data_calling_uid"), bundle3);
                    break;
                case 7:
                    this.f4611a.i(new ServiceCallbacksCompat(message.replyTo));
                    break;
                case 8:
                    Bundle bundle4 = data.getBundle("data_search_extras");
                    MediaSessionCompat.ensureClassLoader(bundle4);
                    this.f4611a.g(data.getString("data_search_query"), bundle4, (ResultReceiver) data.getParcelable("data_result_receiver"), new ServiceCallbacksCompat(message.replyTo));
                    break;
                case 9:
                    Bundle bundle5 = data.getBundle("data_custom_action_extras");
                    MediaSessionCompat.ensureClassLoader(bundle5);
                    this.f4611a.h(data.getString("data_custom_action"), bundle5, (ResultReceiver) data.getParcelable("data_result_receiver"), new ServiceCallbacksCompat(message.replyTo));
                    break;
                default:
                    Log.w("MBServiceCompat", "Unhandled message: " + message + "\n  Service version: 2\n  Client version: " + message.arg1);
                    break;
            }
        }

        @Override // android.os.Handler
        public boolean sendMessageAtTime(Message message, long j2) {
            Bundle data = message.getData();
            data.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            data.putInt("data_calling_uid", Binder.getCallingUid());
            data.putInt("data_calling_pid", Binder.getCallingPid());
            return super.sendMessageAtTime(message, j2);
        }
    }

    void a(String str, ConnectionRecord connectionRecord, IBinder iBinder, Bundle bundle) {
        List<Pair> list = (List) connectionRecord.f4528m.get(str);
        if (list == null) {
            list = new ArrayList();
        }
        for (Pair pair : list) {
            if (iBinder == pair.f3270a && MediaBrowserCompatUtils.a(bundle, (Bundle) pair.f3271b)) {
                return;
            }
        }
        list.add(new Pair(iBinder, bundle));
        connectionRecord.f4528m.put(str, list);
        m(str, connectionRecord, bundle, null);
        this.f4506i = connectionRecord;
        j(str, bundle);
        this.f4506i = null;
    }

    List b(List list, Bundle bundle) {
        if (list == null) {
            return null;
        }
        int i2 = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
        int i3 = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
        if (i2 == -1 && i3 == -1) {
            return list;
        }
        int i4 = i3 * i2;
        int i5 = i4 + i3;
        if (i2 < 0 || i3 < 1 || i4 >= list.size()) {
            return Collections.emptyList();
        }
        if (i5 > list.size()) {
            i5 = list.size();
        }
        return list.subList(i4, i5);
    }

    boolean c(String str, int i2) {
        if (str == null) {
            return false;
        }
        for (String str2 : getPackageManager().getPackagesForUid(i2)) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void d(String str, Bundle bundle, Result result) {
        result.e(null);
    }

    @Override // android.app.Service
    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public abstract BrowserRoot e(String str, int i2, Bundle bundle);

    public abstract void f(String str, Result result);

    public void g(String str, Result result, Bundle bundle) {
        result.g(1);
        f(str, result);
    }

    public void h(String str, Result result) {
        result.g(2);
        result.f(null);
    }

    public void i(String str, Bundle bundle, Result result) {
        result.g(4);
        result.f(null);
    }

    public void j(String str, Bundle bundle) {
    }

    public void k(String str) {
    }

    void l(String str, Bundle bundle, ConnectionRecord connectionRecord, final ResultReceiver resultReceiver) {
        Result<Bundle> result = new Result<Bundle>(str) { // from class: androidx.media.MediaBrowserServiceCompat.4
            @Override // androidx.media.MediaBrowserServiceCompat.Result
            void c(Bundle bundle2) {
                resultReceiver.send(-1, bundle2);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.media.MediaBrowserServiceCompat.Result
            /* renamed from: h, reason: merged with bridge method [inline-methods] */
            public void d(Bundle bundle2) {
                resultReceiver.send(0, bundle2);
            }
        };
        this.f4506i = connectionRecord;
        d(str, bundle, result);
        this.f4506i = null;
        if (result.b()) {
            return;
        }
        throw new IllegalStateException("onCustomAction must call detach() or sendResult() or sendError() before returning for action=" + str + " extras=" + bundle);
    }

    void m(final String str, final ConnectionRecord connectionRecord, final Bundle bundle, final Bundle bundle2) {
        Result<List<MediaBrowserCompat.MediaItem>> result = new Result<List<MediaBrowserCompat.MediaItem>>(str) { // from class: androidx.media.MediaBrowserServiceCompat.1
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.media.MediaBrowserServiceCompat.Result
            /* renamed from: h, reason: merged with bridge method [inline-methods] */
            public void d(List list) {
                if (MediaBrowserServiceCompat.this.f4505h.get(connectionRecord.f4527l.asBinder()) != connectionRecord) {
                    if (MediaBrowserServiceCompat.f4503l) {
                        Log.d("MBServiceCompat", "Not sending onLoadChildren result for connection that has been disconnected. pkg=" + connectionRecord.f4522c + " id=" + str);
                        return;
                    }
                    return;
                }
                if ((a() & 1) != 0) {
                    list = MediaBrowserServiceCompat.this.b(list, bundle);
                }
                try {
                    connectionRecord.f4527l.a(str, list, bundle, bundle2);
                } catch (RemoteException unused) {
                    Log.w("MBServiceCompat", "Calling onLoadChildren() failed for id=" + str + " package=" + connectionRecord.f4522c);
                }
            }
        };
        this.f4506i = connectionRecord;
        if (bundle == null) {
            f(str, result);
        } else {
            g(str, result, bundle);
        }
        this.f4506i = null;
        if (result.b()) {
            return;
        }
        throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + connectionRecord.f4522c + " id=" + str);
    }

    void n(String str, ConnectionRecord connectionRecord, final ResultReceiver resultReceiver) {
        Result<MediaBrowserCompat.MediaItem> result = new Result<MediaBrowserCompat.MediaItem>(str) { // from class: androidx.media.MediaBrowserServiceCompat.2
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.media.MediaBrowserServiceCompat.Result
            /* renamed from: h, reason: merged with bridge method [inline-methods] */
            public void d(MediaBrowserCompat.MediaItem mediaItem) {
                if ((a() & 2) != 0) {
                    resultReceiver.send(-1, null);
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putParcelable("media_item", mediaItem);
                resultReceiver.send(0, bundle);
            }
        };
        this.f4506i = connectionRecord;
        h(str, result);
        this.f4506i = null;
        if (result.b()) {
            return;
        }
        throw new IllegalStateException("onLoadItem must call detach() or sendResult() before returning for id=" + str);
    }

    void o(String str, Bundle bundle, ConnectionRecord connectionRecord, final ResultReceiver resultReceiver) {
        Result<List<MediaBrowserCompat.MediaItem>> result = new Result<List<MediaBrowserCompat.MediaItem>>(str) { // from class: androidx.media.MediaBrowserServiceCompat.3
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.media.MediaBrowserServiceCompat.Result
            /* renamed from: h, reason: merged with bridge method [inline-methods] */
            public void d(List list) {
                if ((a() & 4) != 0 || list == null) {
                    resultReceiver.send(-1, null);
                    return;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putParcelableArray("search_results", (Parcelable[]) list.toArray(new MediaBrowserCompat.MediaItem[0]));
                resultReceiver.send(0, bundle2);
            }
        };
        this.f4506i = connectionRecord;
        i(str, bundle, result);
        this.f4506i = null;
        if (result.b()) {
            return;
        }
        throw new IllegalStateException("onSearch must call detach() or sendResult() before returning for query=" + str);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f4504c.e(intent);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        MediaBrowserServiceImplApi28 mediaBrowserServiceImplApi28 = new MediaBrowserServiceImplApi28();
        this.f4504c = mediaBrowserServiceImplApi28;
        mediaBrowserServiceImplApi28.onCreate();
    }

    boolean p(String str, ConnectionRecord connectionRecord, IBinder iBinder) {
        boolean z = false;
        try {
            if (iBinder != null) {
                List list = (List) connectionRecord.f4528m.get(str);
                if (list != null) {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        if (iBinder == ((Pair) it.next()).f3270a) {
                            it.remove();
                            z = true;
                        }
                    }
                    if (list.size() == 0) {
                        connectionRecord.f4528m.remove(str);
                    }
                }
            } else if (connectionRecord.f4528m.remove(str) != null) {
                z = true;
            }
            return z;
        } finally {
            this.f4506i = connectionRecord;
            k(str);
            this.f4506i = null;
        }
    }
}
