package androidx.profileinstaller;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import androidx.annotation.RequiresApi;
import androidx.startup.Initializer;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class ProfileInstallerInitializer implements Initializer<Result> {

    @RequiresApi
    private static class Handler28Impl {
        public static Handler a(Looper looper) {
            return Handler.createAsync(looper);
        }
    }

    public static class Result {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(Context context, long j2) {
        f(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void j(final Context context) {
        new ThreadPoolExecutor(0, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue()).execute(new Runnable() { // from class: androidx.profileinstaller.f
            @Override // java.lang.Runnable
            public final void run() {
                ProfileInstaller.i(context);
            }
        });
    }

    @Override // androidx.startup.Initializer
    public List a() {
        return Collections.emptyList();
    }

    @Override // androidx.startup.Initializer
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public Result create(Context context) {
        final Context applicationContext = context.getApplicationContext();
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() { // from class: androidx.profileinstaller.d
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j2) {
                ProfileInstallerInitializer.this.g(applicationContext, j2);
            }
        });
        return new Result();
    }

    void f(final Context context) {
        Handler28Impl.a(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: androidx.profileinstaller.e
            @Override // java.lang.Runnable
            public final void run() {
                ProfileInstallerInitializer.j(context);
            }
        }, new Random().nextInt(Math.max(1000, 1)) + 5000);
    }
}
