package androidx.core.app;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.io.Closeable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes.dex */
public final class PendingIntentCompat {

    @RequiresApi
    private static class Api23Impl {
        @DoNotInline
        public static void a(@NonNull PendingIntent pendingIntent, @NonNull Context context, int i2, @NonNull Intent intent, @Nullable PendingIntent.OnFinished onFinished, @Nullable Handler handler, @Nullable String str, @Nullable Bundle bundle) {
            pendingIntent.send(context, i2, intent, onFinished, handler, str, bundle);
        }
    }

    @RequiresApi
    private static class Api26Impl {
        @DoNotInline
        public static PendingIntent a(Context context, int i2, Intent intent, int i3) {
            return PendingIntent.getForegroundService(context, i2, intent, i3);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface Flags {
    }

    private static class GatedCallback implements Closeable {

        /* renamed from: c, reason: collision with root package name */
        private final CountDownLatch f2805c;

        /* renamed from: h, reason: collision with root package name */
        private PendingIntent.OnFinished f2806h;

        /* renamed from: i, reason: collision with root package name */
        private boolean f2807i;

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (!this.f2807i) {
                this.f2806h = null;
            }
            this.f2805c.countDown();
        }
    }
}
