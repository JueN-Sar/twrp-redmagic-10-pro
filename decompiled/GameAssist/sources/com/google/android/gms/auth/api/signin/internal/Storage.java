package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

@KeepForSdk
/* loaded from: classes.dex */
public class Storage {

    /* renamed from: c, reason: collision with root package name */
    private static final Lock f10478c = new ReentrantLock();

    /* renamed from: d, reason: collision with root package name */
    private static Storage f10479d;

    /* renamed from: a, reason: collision with root package name */
    private final Lock f10480a = new ReentrantLock();

    /* renamed from: b, reason: collision with root package name */
    private final SharedPreferences f10481b;

    @VisibleForTesting
    Storage(Context context) {
        this.f10481b = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    public static Storage a(Context context) {
        Preconditions.i(context);
        Lock lock = f10478c;
        lock.lock();
        try {
            if (f10479d == null) {
                f10479d = new Storage(context.getApplicationContext());
            }
            Storage storage = f10479d;
            lock.unlock();
            return storage;
        } catch (Throwable th) {
            f10478c.unlock();
            throw th;
        }
    }

    private static final String f(String str, String str2) {
        return str + ":" + str2;
    }

    public GoogleSignInAccount b() {
        String c2;
        String c3 = c("defaultGoogleSignInAccount");
        if (TextUtils.isEmpty(c3) || (c2 = c(f("googleSignInAccount", c3))) == null) {
            return null;
        }
        try {
            return GoogleSignInAccount.j0(c2);
        } catch (JSONException unused) {
            return null;
        }
    }

    protected final String c(String str) {
        this.f10480a.lock();
        try {
            return this.f10481b.getString(str, null);
        } finally {
            this.f10480a.unlock();
        }
    }

    protected final void d(String str) {
        this.f10480a.lock();
        try {
            this.f10481b.edit().remove(str).apply();
        } finally {
            this.f10480a.unlock();
        }
    }

    public final void e() {
        String c2 = c("defaultGoogleSignInAccount");
        d("defaultGoogleSignInAccount");
        if (TextUtils.isEmpty(c2)) {
            return;
        }
        d(f("googleSignInAccount", c2));
        d(f("googleSignInOptions", c2));
    }
}
