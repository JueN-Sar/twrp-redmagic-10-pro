package com.google.android.odml.image;

import java.io.Closeable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class MlImage implements Closeable {

    /* renamed from: c, reason: collision with root package name */
    private final zzg f15775c;

    /* renamed from: h, reason: collision with root package name */
    private final int f15776h;

    /* renamed from: i, reason: collision with root package name */
    private final int f15777i;

    /* renamed from: j, reason: collision with root package name */
    private final int f15778j;

    /* renamed from: k, reason: collision with root package name */
    private int f15779k;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ImageFormat {
    }

    public static final class Internal {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface StorageType {
    }

    public List a() {
        return Collections.singletonList(this.f15775c.zzb());
    }

    public int c() {
        return this.f15778j;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        int i2 = this.f15779k - 1;
        this.f15779k = i2;
        if (i2 == 0) {
            this.f15775c.zzc();
        }
    }

    public int d() {
        return this.f15776h;
    }

    public int e() {
        return this.f15777i;
    }

    final zzg h() {
        return this.f15775c;
    }
}
