package kotlin.text;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
public final class Charsets {

    /* renamed from: a, reason: collision with root package name */
    public static final Charsets f18760a = new Charsets();

    /* renamed from: b, reason: collision with root package name */
    public static final Charset f18761b;

    /* renamed from: c, reason: collision with root package name */
    public static final Charset f18762c;

    /* renamed from: d, reason: collision with root package name */
    public static final Charset f18763d;

    /* renamed from: e, reason: collision with root package name */
    public static final Charset f18764e;

    /* renamed from: f, reason: collision with root package name */
    public static final Charset f18765f;

    /* renamed from: g, reason: collision with root package name */
    public static final Charset f18766g;

    static {
        Charset forName = Charset.forName("UTF-8");
        Intrinsics.d(forName, "forName(\"UTF-8\")");
        f18761b = forName;
        Charset forName2 = Charset.forName("UTF-16");
        Intrinsics.d(forName2, "forName(\"UTF-16\")");
        f18762c = forName2;
        Charset forName3 = Charset.forName("UTF-16BE");
        Intrinsics.d(forName3, "forName(\"UTF-16BE\")");
        f18763d = forName3;
        Charset forName4 = Charset.forName("UTF-16LE");
        Intrinsics.d(forName4, "forName(\"UTF-16LE\")");
        f18764e = forName4;
        Charset forName5 = Charset.forName("US-ASCII");
        Intrinsics.d(forName5, "forName(\"US-ASCII\")");
        f18765f = forName5;
        Charset forName6 = Charset.forName("ISO-8859-1");
        Intrinsics.d(forName6, "forName(\"ISO-8859-1\")");
        f18766g = forName6;
    }

    private Charsets() {
    }
}
