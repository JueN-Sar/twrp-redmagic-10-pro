package com.google.android.gms.common.images;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.google.android.gms.common.internal.Asserts;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes.dex */
final class zaa implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    private final Uri f10935c;

    /* renamed from: h, reason: collision with root package name */
    private final ParcelFileDescriptor f10936h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ ImageManager f10937i;

    public zaa(ImageManager imageManager, Uri uri, ParcelFileDescriptor parcelFileDescriptor) {
        this.f10937i = imageManager;
        this.f10935c = uri;
        this.f10936h = parcelFileDescriptor;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Handler handler;
        Asserts.b("LoadBitmapFromDiskRunnable can't be executed in the main thread");
        ParcelFileDescriptor parcelFileDescriptor = this.f10936h;
        Bitmap bitmap = null;
        boolean z = false;
        if (parcelFileDescriptor != null) {
            try {
                bitmap = BitmapFactory.decodeFileDescriptor(parcelFileDescriptor.getFileDescriptor());
            } catch (OutOfMemoryError e2) {
                Log.e("ImageManager", "OOM while loading bitmap for uri: ".concat(String.valueOf(this.f10935c)), e2);
                z = true;
            }
            try {
                this.f10936h.close();
            } catch (IOException e3) {
                Log.e("ImageManager", "closed failed", e3);
            }
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ImageManager imageManager = this.f10937i;
        Uri uri = this.f10935c;
        handler = imageManager.f10920b;
        handler.post(new zac(imageManager, uri, bitmap, z, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException unused) {
            Log.w("ImageManager", "Latch interrupted while posting ".concat(String.valueOf(this.f10935c)));
        }
    }
}
