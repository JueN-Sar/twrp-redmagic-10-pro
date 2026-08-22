package androidx.core.app;

import android.app.RemoteInput;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class RemoteInput {

    /* renamed from: a, reason: collision with root package name */
    private final String f2828a;

    /* renamed from: b, reason: collision with root package name */
    private final CharSequence f2829b;

    /* renamed from: c, reason: collision with root package name */
    private final CharSequence[] f2830c;

    /* renamed from: d, reason: collision with root package name */
    private final boolean f2831d;

    /* renamed from: e, reason: collision with root package name */
    private final int f2832e;

    /* renamed from: f, reason: collision with root package name */
    private final Bundle f2833f;

    /* renamed from: g, reason: collision with root package name */
    private final Set f2834g;

    @RequiresApi
    static class Api20Impl {
        @DoNotInline
        static void a(Object obj, Intent intent, Bundle bundle) {
            android.app.RemoteInput.addResultsToIntent((android.app.RemoteInput[]) obj, intent, bundle);
        }

        public static android.app.RemoteInput b(RemoteInput remoteInput) {
            RemoteInput.Builder addExtras = new RemoteInput.Builder(remoteInput.i()).setLabel(remoteInput.h()).setChoices(remoteInput.e()).setAllowFreeFormInput(remoteInput.c()).addExtras(remoteInput.g());
            Set d2 = remoteInput.d();
            if (d2 != null) {
                Iterator it = d2.iterator();
                while (it.hasNext()) {
                    Api26Impl.d(addExtras, (String) it.next(), true);
                }
            }
            Api29Impl.b(addExtras, remoteInput.f());
            return addExtras.build();
        }

        @DoNotInline
        static Bundle c(Intent intent) {
            return android.app.RemoteInput.getResultsFromIntent(intent);
        }
    }

    @RequiresApi
    static class Api26Impl {
        @DoNotInline
        static void a(RemoteInput remoteInput, Intent intent, Map<String, Uri> map) {
            android.app.RemoteInput.addDataResultToIntent(RemoteInput.a(remoteInput), intent, map);
        }

        @DoNotInline
        static Set<String> b(Object obj) {
            return ((android.app.RemoteInput) obj).getAllowedDataTypes();
        }

        @DoNotInline
        static Map<String, Uri> c(Intent intent, String str) {
            return android.app.RemoteInput.getDataResultsFromIntent(intent, str);
        }

        @DoNotInline
        static RemoteInput.Builder d(RemoteInput.Builder builder, String str, boolean z) {
            return builder.setAllowDataType(str, z);
        }
    }

    @RequiresApi
    static class Api28Impl {
        @DoNotInline
        static int a(Intent intent) {
            return android.app.RemoteInput.getResultsSource(intent);
        }

        @DoNotInline
        static void b(Intent intent, int i2) {
            android.app.RemoteInput.setResultsSource(intent, i2);
        }
    }

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static int a(Object obj) {
            return ((android.app.RemoteInput) obj).getEditChoicesBeforeSending();
        }

        @DoNotInline
        static RemoteInput.Builder b(RemoteInput.Builder builder, int i2) {
            return builder.setEditChoicesBeforeSending(i2);
        }
    }

    public static final class Builder {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface EditChoicesBeforeSending {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface Source {
    }

    RemoteInput(String str, CharSequence charSequence, CharSequence[] charSequenceArr, boolean z, int i2, Bundle bundle, Set set) {
        this.f2828a = str;
        this.f2829b = charSequence;
        this.f2830c = charSequenceArr;
        this.f2831d = z;
        this.f2832e = i2;
        this.f2833f = bundle;
        this.f2834g = set;
        if (f() == 2 && !c()) {
            throw new IllegalArgumentException("setEditChoicesBeforeSending requires setAllowFreeFormInput");
        }
    }

    static android.app.RemoteInput a(RemoteInput remoteInput) {
        return Api20Impl.b(remoteInput);
    }

    static android.app.RemoteInput[] b(RemoteInput[] remoteInputArr) {
        if (remoteInputArr == null) {
            return null;
        }
        android.app.RemoteInput[] remoteInputArr2 = new android.app.RemoteInput[remoteInputArr.length];
        for (int i2 = 0; i2 < remoteInputArr.length; i2++) {
            remoteInputArr2[i2] = a(remoteInputArr[i2]);
        }
        return remoteInputArr2;
    }

    public boolean c() {
        return this.f2831d;
    }

    public Set d() {
        return this.f2834g;
    }

    public CharSequence[] e() {
        return this.f2830c;
    }

    public int f() {
        return this.f2832e;
    }

    public Bundle g() {
        return this.f2833f;
    }

    public CharSequence h() {
        return this.f2829b;
    }

    public String i() {
        return this.f2828a;
    }

    public boolean j() {
        return (c() || (e() != null && e().length != 0) || d() == null || d().isEmpty()) ? false : true;
    }
}
