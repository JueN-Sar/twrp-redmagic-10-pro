package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.SystemClock;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zam;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes.dex */
final class zac implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    private final Uri f10940c;

    /* renamed from: h, reason: collision with root package name */
    private final Bitmap f10941h;

    /* renamed from: i, reason: collision with root package name */
    private final CountDownLatch f10942i;

    /* renamed from: j, reason: collision with root package name */
    final /* synthetic */ ImageManager f10943j;

    public zac(ImageManager imageManager, Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
        this.f10943j = imageManager;
        this.f10940c = uri;
        this.f10941h = bitmap;
        this.f10942i = countDownLatch;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Map map;
        Object obj;
        HashSet hashSet;
        ArrayList arrayList;
        Map map2;
        zam zamVar;
        Map map3;
        Asserts.a("OnBitmapLoadedRunnable must be executed in the main thread");
        map = this.f10943j.f10924f;
        ImageManager.ImageReceiver imageReceiver = (ImageManager.ImageReceiver) map.remove(this.f10940c);
        if (imageReceiver != null) {
            arrayList = imageReceiver.f10927h;
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                zag zagVar = (zag) arrayList.get(i2);
                Bitmap bitmap = this.f10941h;
                if (bitmap != null) {
                    zagVar.c(this.f10943j.f10919a, bitmap, false);
                } else {
                    ImageManager imageManager = this.f10943j;
                    Uri uri = this.f10940c;
                    map2 = imageManager.f10925g;
                    map2.put(uri, Long.valueOf(SystemClock.elapsedRealtime()));
                    ImageManager imageManager2 = this.f10943j;
                    Context context = imageManager2.f10919a;
                    zamVar = imageManager2.f10922d;
                    zagVar.b(context, zamVar, false);
                }
                if (!(zagVar instanceof zaf)) {
                    map3 = this.f10943j.f10923e;
                    map3.remove(zagVar);
                }
            }
        }
        this.f10942i.countDown();
        obj = ImageManager.f10917h;
        synchronized (obj) {
            hashSet = ImageManager.f10918i;
            hashSet.remove(this.f10940c);
        }
    }
}
