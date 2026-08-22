package com.google.mlkit.vision.common.internal;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_vision_common.zzlx;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.odml.image.MlImage;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.MLTask;
import com.google.mlkit.vision.common.InputImage;
import java.io.Closeable;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

@KeepForSdk
/* loaded from: classes.dex */
public class MobileVisionBase<DetectionResultT> implements Closeable, LifecycleObserver {

    /* renamed from: l, reason: collision with root package name */
    private static final GmsLogger f16062l = new GmsLogger("MobileVisionBase", "");

    /* renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ int f16063m = 0;

    /* renamed from: c, reason: collision with root package name */
    private final AtomicBoolean f16064c = new AtomicBoolean(false);

    /* renamed from: h, reason: collision with root package name */
    private final MLTask f16065h;

    /* renamed from: i, reason: collision with root package name */
    private final CancellationTokenSource f16066i;

    /* renamed from: j, reason: collision with root package name */
    private final Executor f16067j;

    /* renamed from: k, reason: collision with root package name */
    private final Task f16068k;

    public MobileVisionBase(MLTask mLTask, Executor executor) {
        this.f16065h = mLTask;
        CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        this.f16066i = cancellationTokenSource;
        this.f16067j = executor;
        mLTask.b();
        this.f16068k = mLTask.a(executor, new Callable() { // from class: com.google.mlkit.vision.common.internal.zzb
            @Override // java.util.concurrent.Callable
            public final Object call() {
                int i2 = MobileVisionBase.f16063m;
                return null;
            }
        }, cancellationTokenSource.b()).d(new OnFailureListener() { // from class: com.google.mlkit.vision.common.internal.zzc
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void d(Exception exc) {
                MobileVisionBase.f16062l.d("MobileVisionBase", "Error preloading model resource", exc);
            }
        });
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    @KeepForSdk
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public synchronized void close() {
        if (this.f16064c.getAndSet(true)) {
            return;
        }
        this.f16066i.a();
        this.f16065h.d(this.f16067j);
    }

    public synchronized Task p(final InputImage inputImage) {
        Preconditions.j(inputImage, "InputImage can not be null");
        if (this.f16064c.get()) {
            return Tasks.b(new MlKitException("This detector is already closed!", 14));
        }
        if (inputImage.l() < 32 || inputImage.h() < 32) {
            return Tasks.b(new MlKitException("InputImage width and height should be at least 32!", 3));
        }
        return this.f16065h.a(this.f16067j, new Callable() { // from class: com.google.mlkit.vision.common.internal.zza
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return MobileVisionBase.this.s(inputImage);
            }
        }, this.f16066i.b());
    }

    final /* synthetic */ Object s(InputImage inputImage) {
        zzlx h2 = zzlx.h("detectorTaskWithResource#run");
        h2.c();
        try {
            Object h3 = this.f16065h.h(inputImage);
            h2.close();
            return h3;
        } catch (Throwable th) {
            try {
                h2.close();
            } catch (Throwable th2) {
                try {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                } catch (Exception unused) {
                }
            }
            throw th;
        }
    }

    final /* synthetic */ Object t(MlImage mlImage) {
        InputImage a2 = CommonConvertUtils.a(mlImage);
        if (a2 != null) {
            return this.f16065h.h(a2);
        }
        throw new MlKitException("Current type of MlImage is not supported.", 13);
    }
}
