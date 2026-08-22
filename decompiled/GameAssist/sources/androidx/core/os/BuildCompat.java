package androidx.core.os;

import android.os.Build;
import android.os.ext.SdkExtensions;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.RequiresOptIn;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes.dex */
public final class BuildCompat {

    /* renamed from: a, reason: collision with root package name */
    public static final BuildCompat f3099a = new BuildCompat();

    /* renamed from: b, reason: collision with root package name */
    public static final int f3100b;

    /* renamed from: c, reason: collision with root package name */
    public static final int f3101c;

    /* renamed from: d, reason: collision with root package name */
    public static final int f3102d;

    /* renamed from: e, reason: collision with root package name */
    public static final int f3103e;

    @RequiresApi
    @Metadata
    private static final class Api30Impl {

        /* renamed from: a, reason: collision with root package name */
        public static final Api30Impl f3104a = new Api30Impl();

        private Api30Impl() {
        }

        @DoNotInline
        public final int a(int i2) {
            return SdkExtensions.getExtensionVersion(i2);
        }
    }

    @Metadata
    @RequiresOptIn
    @Retention(RetentionPolicy.CLASS)
    @kotlin.annotation.Retention
    public @interface PrereleaseSdkCheck {
    }

    static {
        Api30Impl api30Impl = Api30Impl.f3104a;
        f3100b = api30Impl.a(30);
        f3101c = api30Impl.a(31);
        f3102d = api30Impl.a(33);
        f3103e = api30Impl.a(1000000);
    }

    private BuildCompat() {
    }

    public static final boolean a() {
        return true;
    }

    public static final boolean b() {
        if (Build.VERSION.SDK_INT >= 34) {
            String CODENAME = Build.VERSION.CODENAME;
            Intrinsics.d(CODENAME, "CODENAME");
            if (isAtLeastPreReleaseCodename("VanillaIceCream", CODENAME)) {
                return true;
            }
        }
        return false;
    }

    @JvmStatic
    @RestrictTo
    @VisibleForTesting
    public static final boolean isAtLeastPreReleaseCodename(@NotNull String codename, @NotNull String buildCodename) {
        Intrinsics.e(codename, "codename");
        Intrinsics.e(buildCodename, "buildCodename");
        if (Intrinsics.a("REL", buildCodename)) {
            return false;
        }
        Locale locale = Locale.ROOT;
        String upperCase = buildCodename.toUpperCase(locale);
        Intrinsics.d(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        String upperCase2 = codename.toUpperCase(locale);
        Intrinsics.d(upperCase2, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        return upperCase.compareTo(upperCase2) >= 0;
    }
}
