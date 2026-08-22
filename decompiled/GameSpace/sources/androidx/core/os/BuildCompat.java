package androidx.core.os;

import android.os.Build;
import java.util.Locale;

/* loaded from: classes.dex */
public class BuildCompat {

    public @interface PrereleaseSdkCheck {
    }

    private BuildCompat() {
    }

    @Deprecated
    public static boolean isAtLeastN() {
        return true;
    }

    @Deprecated
    public static boolean isAtLeastNMR1() {
        return true;
    }

    @Deprecated
    public static boolean isAtLeastO() {
        return true;
    }

    @Deprecated
    public static boolean isAtLeastOMR1() {
        return true;
    }

    @Deprecated
    public static boolean isAtLeastP() {
        return true;
    }

    protected static boolean isAtLeastPreReleaseCodename(String str, String str2) {
        return !"REL".equals(str2) && str2.toUpperCase(Locale.ROOT).compareTo(str.toUpperCase(Locale.ROOT)) >= 0;
    }

    @Deprecated
    public static boolean isAtLeastQ() {
        return true;
    }

    @Deprecated
    public static boolean isAtLeastR() {
        return true;
    }

    @Deprecated
    public static boolean isAtLeastS() {
        return true;
    }

    @Deprecated
    public static boolean isAtLeastSv2() {
        return true;
    }

    public static boolean isAtLeastT() {
        return true;
    }

    public static boolean isAtLeastU() {
        return isAtLeastPreReleaseCodename("UpsideDownCake", Build.VERSION.CODENAME);
    }
}
