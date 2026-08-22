package com.google.android.gms.common.images;

import android.content.Context;
import android.net.Uri;
import android.os.SystemClock;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.common.images.ImageManager.ImageReceiver;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zam;
import java.util.HashSet;
import java.util.Map;

/* loaded from: classes.dex */
final class zab implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    private final zag f10938c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ ImageManager f10939h;

    @Override // java.lang.Runnable
    public final void run() {
        Map map;
        zam zamVar;
        Map map2;
        Map map3;
        Object obj;
        HashSet hashSet;
        HashSet hashSet2;
        Map map4;
        Map map5;
        Map map6;
        zam zamVar2;
        Map map7;
        Asserts.a("LoadImageRunnable must be executed on the main thread");
        map = this.f10939h.f10923e;
        ImageManager.ImageReceiver imageReceiver = (ImageManager.ImageReceiver) map.get(this.f10938c);
        if (imageReceiver != null) {
            ImageManager imageManager = this.f10939h;
            zag zagVar = this.f10938c;
            map7 = imageManager.f10923e;
            map7.remove(zagVar);
            imageReceiver.d(this.f10938c);
        }
        zag zagVar2 = this.f10938c;
        zad zadVar = zagVar2.f10947a;
        Uri uri = zadVar.f10944a;
        if (uri == null) {
            ImageManager imageManager2 = this.f10939h;
            Context context = imageManager2.f10919a;
            zamVar = imageManager2.f10922d;
            zagVar2.b(context, zamVar, true);
            return;
        }
        map2 = this.f10939h.f10925g;
        Long l2 = (Long) map2.get(uri);
        if (l2 != null) {
            if (SystemClock.elapsedRealtime() - l2.longValue() < 3600000) {
                zag zagVar3 = this.f10938c;
                ImageManager imageManager3 = this.f10939h;
                Context context2 = imageManager3.f10919a;
                zamVar2 = imageManager3.f10922d;
                zagVar3.b(context2, zamVar2, true);
                return;
            }
            ImageManager imageManager4 = this.f10939h;
            Uri uri2 = zadVar.f10944a;
            map6 = imageManager4.f10925g;
            map6.remove(uri2);
        }
        this.f10938c.a(null, false, true, false);
        ImageManager imageManager5 = this.f10939h;
        Uri uri3 = zadVar.f10944a;
        map3 = imageManager5.f10924f;
        ImageManager.ImageReceiver imageReceiver2 = (ImageManager.ImageReceiver) map3.get(uri3);
        if (imageReceiver2 == null) {
            ImageManager.ImageReceiver imageReceiver3 = this.f10939h.new ImageReceiver(zadVar.f10944a);
            ImageManager imageManager6 = this.f10939h;
            Uri uri4 = zadVar.f10944a;
            map5 = imageManager6.f10924f;
            map5.put(uri4, imageReceiver3);
            imageReceiver2 = imageReceiver3;
        }
        imageReceiver2.b(this.f10938c);
        zag zagVar4 = this.f10938c;
        if (!(zagVar4 instanceof zaf)) {
            map4 = this.f10939h.f10923e;
            map4.put(zagVar4, imageReceiver2);
        }
        obj = ImageManager.f10917h;
        synchronized (obj) {
            try {
                hashSet = ImageManager.f10918i;
                if (!hashSet.contains(zadVar.f10944a)) {
                    hashSet2 = ImageManager.f10918i;
                    hashSet2.add(zadVar.f10944a);
                    imageReceiver2.f();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
