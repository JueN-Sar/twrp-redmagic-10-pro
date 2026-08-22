package com.google.android.gms.common.images;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zam;
import com.google.android.gms.internal.base.zau;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public final class ImageManager {

    /* renamed from: h, reason: collision with root package name */
    private static final Object f10917h = new Object();

    /* renamed from: i, reason: collision with root package name */
    private static final HashSet f10918i = new HashSet();

    /* renamed from: a, reason: collision with root package name */
    private final Context f10919a;

    /* renamed from: b, reason: collision with root package name */
    private final Handler f10920b;

    /* renamed from: c, reason: collision with root package name */
    private final ExecutorService f10921c;

    /* renamed from: d, reason: collision with root package name */
    private final zam f10922d;

    /* renamed from: e, reason: collision with root package name */
    private final Map f10923e;

    /* renamed from: f, reason: collision with root package name */
    private final Map f10924f;

    /* renamed from: g, reason: collision with root package name */
    private final Map f10925g;

    /* JADX INFO: Access modifiers changed from: private */
    @KeepName
    final class ImageReceiver extends ResultReceiver {

        /* renamed from: c, reason: collision with root package name */
        private final Uri f10926c;

        /* renamed from: h, reason: collision with root package name */
        private final ArrayList f10927h;

        ImageReceiver(Uri uri) {
            super(new zau(Looper.getMainLooper()));
            this.f10926c = uri;
            this.f10927h = new ArrayList();
        }

        public final void b(zag zagVar) {
            Asserts.a("ImageReceiver.addImageRequest() must be called in the main thread");
            this.f10927h.add(zagVar);
        }

        public final void d(zag zagVar) {
            Asserts.a("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.f10927h.remove(zagVar);
        }

        public final void f() {
            Intent intent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
            intent.setPackage("com.google.android.gms");
            intent.putExtra("com.google.android.gms.extras.uri", this.f10926c);
            intent.putExtra("com.google.android.gms.extras.resultReceiver", this);
            intent.putExtra("com.google.android.gms.extras.priority", 3);
            ImageManager.this.f10919a.sendBroadcast(intent);
        }

        @Override // android.os.ResultReceiver
        public final void onReceiveResult(int i2, Bundle bundle) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) bundle.getParcelable("com.google.android.gms.extra.fileDescriptor");
            ImageManager imageManager = ImageManager.this;
            imageManager.f10921c.execute(new zaa(imageManager, this.f10926c, parcelFileDescriptor));
        }
    }

    public interface OnImageLoadedListener {
        void a(Uri uri, Drawable drawable, boolean z);
    }
}
