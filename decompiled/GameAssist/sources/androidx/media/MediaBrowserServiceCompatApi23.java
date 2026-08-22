package androidx.media;

import android.content.Context;
import android.service.media.MediaBrowserService;
import androidx.annotation.RequiresApi;
import androidx.media.MediaBrowserServiceCompatApi21;

@RequiresApi
/* loaded from: classes.dex */
class MediaBrowserServiceCompatApi23 {

    static class MediaBrowserServiceAdaptor extends MediaBrowserServiceCompatApi21.MediaBrowserServiceAdaptor {
        MediaBrowserServiceAdaptor(Context context, ServiceCompatProxy serviceCompatProxy) {
            super(context, serviceCompatProxy);
        }

        @Override // android.service.media.MediaBrowserService
        public void onLoadItem(String str, MediaBrowserService.Result result) {
            ((ServiceCompatProxy) this.f4615c).a(str, new MediaBrowserServiceCompatApi21.ResultWrapper(result));
        }
    }

    public interface ServiceCompatProxy extends MediaBrowserServiceCompatApi21.ServiceCompatProxy {
        void a(String str, MediaBrowserServiceCompatApi21.ResultWrapper resultWrapper);
    }

    public static Object a(Context context, ServiceCompatProxy serviceCompatProxy) {
        return new MediaBrowserServiceAdaptor(context, serviceCompatProxy);
    }
}
