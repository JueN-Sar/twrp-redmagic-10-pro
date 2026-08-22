package androidx.lifecycle;

import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import java.util.concurrent.Executor;
import kotlin.Metadata;

@Metadata
@RestrictTo
/* loaded from: classes.dex */
public abstract class ComputableLiveData<T> {

    /* renamed from: a, reason: collision with root package name */
    private final Executor f4277a;

    /* renamed from: b, reason: collision with root package name */
    public final Runnable f4278b;

    @VisibleForTesting
    public static /* synthetic */ void getInvalidationRunnable$lifecycle_livedata_release$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void getRefreshRunnable$lifecycle_livedata_release$annotations() {
    }

    public final Executor a() {
        return this.f4277a;
    }
}
