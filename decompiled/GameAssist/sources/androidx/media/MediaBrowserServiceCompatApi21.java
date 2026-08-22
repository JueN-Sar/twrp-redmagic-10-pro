package androidx.media;

import android.content.Context;
import android.content.Intent;
import android.media.browse.MediaBrowser;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiresApi
/* loaded from: classes.dex */
class MediaBrowserServiceCompatApi21 {

    static class BrowserRoot {

        /* renamed from: a, reason: collision with root package name */
        final String f4613a;

        /* renamed from: b, reason: collision with root package name */
        final Bundle f4614b;

        BrowserRoot(String str, Bundle bundle) {
            this.f4613a = str;
            this.f4614b = bundle;
        }
    }

    static class MediaBrowserServiceAdaptor extends MediaBrowserService {

        /* renamed from: c, reason: collision with root package name */
        final ServiceCompatProxy f4615c;

        MediaBrowserServiceAdaptor(Context context, ServiceCompatProxy serviceCompatProxy) {
            attachBaseContext(context);
            this.f4615c = serviceCompatProxy;
        }

        @Override // android.service.media.MediaBrowserService
        public MediaBrowserService.BrowserRoot onGetRoot(String str, int i2, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            BrowserRoot c2 = this.f4615c.c(str, i2, bundle == null ? null : new Bundle(bundle));
            if (c2 == null) {
                return null;
            }
            return new MediaBrowserService.BrowserRoot(c2.f4613a, c2.f4614b);
        }

        @Override // android.service.media.MediaBrowserService
        public void onLoadChildren(String str, MediaBrowserService.Result result) {
            this.f4615c.d(str, new ResultWrapper(result));
        }
    }

    static class ResultWrapper<T> {

        /* renamed from: a, reason: collision with root package name */
        MediaBrowserService.Result f4616a;

        ResultWrapper(MediaBrowserService.Result result) {
            this.f4616a = result;
        }

        List a(List list) {
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Parcel parcel = (Parcel) it.next();
                parcel.setDataPosition(0);
                arrayList.add(MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel));
                parcel.recycle();
            }
            return arrayList;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void b(Object obj) {
            if (obj instanceof List) {
                this.f4616a.sendResult(a((List) obj));
                return;
            }
            if (!(obj instanceof Parcel)) {
                this.f4616a.sendResult(null);
                return;
            }
            Parcel parcel = (Parcel) obj;
            parcel.setDataPosition(0);
            this.f4616a.sendResult(MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel));
            parcel.recycle();
        }
    }

    public interface ServiceCompatProxy {
        BrowserRoot c(String str, int i2, Bundle bundle);

        void d(String str, ResultWrapper resultWrapper);
    }

    public static Object a(Context context, ServiceCompatProxy serviceCompatProxy) {
        return new MediaBrowserServiceAdaptor(context, serviceCompatProxy);
    }

    public static IBinder b(Object obj, Intent intent) {
        return ((MediaBrowserService) obj).onBind(intent);
    }

    public static void c(Object obj) {
        ((MediaBrowserService) obj).onCreate();
    }

    public static void d(Object obj, Object obj2) {
        ((MediaBrowserService) obj).setSessionToken((MediaSession.Token) obj2);
    }
}
