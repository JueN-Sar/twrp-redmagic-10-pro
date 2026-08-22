package androidx.media;

import androidx.annotation.RestrictTo;
import androidx.media.VolumeProviderCompatApi21;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public abstract class VolumeProviderCompat {

    /* renamed from: a, reason: collision with root package name */
    private final int f4627a;

    /* renamed from: b, reason: collision with root package name */
    private final int f4628b;

    /* renamed from: c, reason: collision with root package name */
    private int f4629c;

    /* renamed from: d, reason: collision with root package name */
    private Callback f4630d;

    /* renamed from: e, reason: collision with root package name */
    private Object f4631e;

    public static abstract class Callback {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface ControlType {
    }

    public final int a() {
        return this.f4629c;
    }

    public final int b() {
        return this.f4628b;
    }

    public final int c() {
        return this.f4627a;
    }

    public Object d() {
        if (this.f4631e == null) {
            this.f4631e = VolumeProviderCompatApi21.a(this.f4627a, this.f4628b, this.f4629c, new VolumeProviderCompatApi21.Delegate() { // from class: androidx.media.VolumeProviderCompat.1
                @Override // androidx.media.VolumeProviderCompatApi21.Delegate
                public void a(int i2) {
                    VolumeProviderCompat.this.f(i2);
                }

                @Override // androidx.media.VolumeProviderCompatApi21.Delegate
                public void b(int i2) {
                    VolumeProviderCompat.this.e(i2);
                }
            });
        }
        return this.f4631e;
    }

    public void e(int i2) {
    }

    public void f(int i2) {
    }

    public void g(Callback callback) {
        this.f4630d = callback;
    }
}
