package androidx.arch.core.executor;

import androidx.annotation.RestrictTo;
import java.util.concurrent.Executor;

@RestrictTo
/* loaded from: classes.dex */
public class ArchTaskExecutor extends TaskExecutor {

    /* renamed from: c, reason: collision with root package name */
    private static volatile ArchTaskExecutor f1099c;

    /* renamed from: d, reason: collision with root package name */
    private static final Executor f1100d = new Executor() { // from class: androidx.arch.core.executor.a
        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            ArchTaskExecutor.g(runnable);
        }
    };

    /* renamed from: e, reason: collision with root package name */
    private static final Executor f1101e = new Executor() { // from class: androidx.arch.core.executor.b
        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            ArchTaskExecutor.h(runnable);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private TaskExecutor f1102a;

    /* renamed from: b, reason: collision with root package name */
    private final TaskExecutor f1103b;

    private ArchTaskExecutor() {
        DefaultTaskExecutor defaultTaskExecutor = new DefaultTaskExecutor();
        this.f1103b = defaultTaskExecutor;
        this.f1102a = defaultTaskExecutor;
    }

    public static ArchTaskExecutor f() {
        if (f1099c != null) {
            return f1099c;
        }
        synchronized (ArchTaskExecutor.class) {
            try {
                if (f1099c == null) {
                    f1099c = new ArchTaskExecutor();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return f1099c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void g(Runnable runnable) {
        f().c(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void h(Runnable runnable) {
        f().a(runnable);
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public void a(Runnable runnable) {
        this.f1102a.a(runnable);
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public boolean b() {
        return this.f1102a.b();
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public void c(Runnable runnable) {
        this.f1102a.c(runnable);
    }
}
