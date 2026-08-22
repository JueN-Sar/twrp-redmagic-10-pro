package androidx.core.text.util;

import android.text.Spannable;
import android.text.util.Linkify;
import android.widget.TextView;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.text.util.LinkifyCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Comparator;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class LinkifyCompat {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f3245a = new String[0];

    /* renamed from: b, reason: collision with root package name */
    private static final Comparator f3246b = new Comparator() { // from class: androidx.core.text.util.a
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            int b2;
            b2 = LinkifyCompat.b((LinkifyCompat.LinkSpec) obj, (LinkifyCompat.LinkSpec) obj2);
            return b2;
        }
    };

    @RequiresApi
    static class Api24Impl {
        @DoNotInline
        static void a(TextView textView, Pattern pattern, String str, String[] strArr, Linkify.MatchFilter matchFilter, Linkify.TransformFilter transformFilter) {
            Linkify.addLinks(textView, pattern, str, strArr, matchFilter, transformFilter);
        }

        @DoNotInline
        static boolean b(Spannable spannable, Pattern pattern, String str, String[] strArr, Linkify.MatchFilter matchFilter, Linkify.TransformFilter transformFilter) {
            return Linkify.addLinks(spannable, pattern, str, strArr, matchFilter, transformFilter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class LinkSpec {

        /* renamed from: a, reason: collision with root package name */
        int f3247a;

        /* renamed from: b, reason: collision with root package name */
        int f3248b;
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface LinkifyMask {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int b(LinkSpec linkSpec, LinkSpec linkSpec2) {
        int i2 = linkSpec.f3247a;
        int i3 = linkSpec2.f3247a;
        if (i2 < i3) {
            return -1;
        }
        if (i2 > i3) {
            return 1;
        }
        return Integer.compare(linkSpec2.f3248b, linkSpec.f3248b);
    }
}
