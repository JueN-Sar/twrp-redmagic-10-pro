package androidx.media;

import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.media.MediaBrowserServiceCompatApi23;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiresApi
/* loaded from: classes.dex */
class MediaBrowserServiceCompatApi26 {

    /* renamed from: a, reason: collision with root package name */
    static Field f4617a;

    static class MediaBrowserServiceAdaptor extends MediaBrowserServiceCompatApi23.MediaBrowserServiceAdaptor {
        MediaBrowserServiceAdaptor(Context context, ServiceCompatProxy serviceCompatProxy) {
            super(context, serviceCompatProxy);
        }

        @Override // android.service.media.MediaBrowserService
        public void onLoadChildren(String str, MediaBrowserService.Result result, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            ((ServiceCompatProxy) this.f4615c).b(str, new ResultWrapper(result), bundle);
        }
    }

    static class ResultWrapper {

        /* renamed from: a, reason: collision with root package name */
        MediaBrowserService.Result f4618a;

        ResultWrapper(MediaBrowserService.Result result) {
            this.f4618a = result;
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

        public void b(List list, int i2) {
            try {
                MediaBrowserServiceCompatApi26.f4617a.setInt(this.f4618a, i2);
            } catch (IllegalAccessException e2) {
                Log.w("MBSCompatApi26", e2);
            }
            this.f4618a.sendResult(a(list));
        }
    }

    public interface ServiceCompatProxy extends MediaBrowserServiceCompatApi23.ServiceCompatProxy {
        void b(String str, ResultWrapper resultWrapper, Bundle bundle);
    }

    static {
        try {
            Field declaredField = MediaBrowserService.Result.class.getDeclaredField("mFlags");
            f4617a = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e2) {
            Log.w("MBSCompatApi26", e2);
        }
    }

    public static Object a(Context context, ServiceCompatProxy serviceCompatProxy) {
        return new MediaBrowserServiceAdaptor(context, serviceCompatProxy);
    }
}
