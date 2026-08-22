package com.zte.timeutil.formatter;

import com.zte.timeutil.converter.DateTimeConverterUtil;
import com.zte.timeutil.enums.CommonTimeEnum;
import com.zte.timeutil.enums.ZoneIdEnum;
import com.zte.timeutil.utils.StringUtil;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/* loaded from: classes2.dex */
public class DateTimeFormatterUtil {
    public static final DateTimeFormatter A;
    public static final DateTimeFormatter A0;
    public static final DateTimeFormatter B;
    public static final DateTimeFormatter B0;
    public static final DateTimeFormatter C;
    public static final DateTimeFormatter C0;
    public static final DateTimeFormatter D;
    public static final DateTimeFormatter D0;
    public static final DateTimeFormatter E;
    public static final DateTimeFormatter E0;
    public static DateTimeFormatter F;
    public static final DateTimeFormatter F0;
    public static final DateTimeFormatter G;
    public static final DateTimeFormatter G0;
    public static DateTimeFormatter H;
    public static final DateTimeFormatter H0;
    public static DateTimeFormatter I;
    public static final DateTimeFormatter I0;
    public static DateTimeFormatter J;
    public static final DateTimeFormatter J0;
    public static DateTimeFormatter K;
    public static final DateTimeFormatter K0;
    public static DateTimeFormatter L;
    public static final DateTimeFormatter L0;
    public static DateTimeFormatter M;
    public static final DateTimeFormatter M0;
    public static DateTimeFormatter N;
    public static final DateTimeFormatter N0;
    public static DateTimeFormatter O;
    public static final DateTimeFormatter O0;
    public static DateTimeFormatter P;
    public static final DateTimeFormatter P0;
    public static final DateTimeFormatter Q;
    public static final DateTimeFormatter Q0;
    public static final DateTimeFormatter R;
    public static final DateTimeFormatter S;
    public static final DateTimeFormatter T;
    public static final DateTimeFormatter U;
    public static final DateTimeFormatter V;
    public static final DateTimeFormatter W;
    public static final DateTimeFormatter X;
    public static final DateTimeFormatter Y;
    public static final DateTimeFormatter Z;

    /* renamed from: a, reason: collision with root package name */
    private static final ZoneId f18140a;
    public static final DateTimeFormatter a0;

    /* renamed from: b, reason: collision with root package name */
    public static final String f18141b;
    public static final DateTimeFormatter b0;

    /* renamed from: c, reason: collision with root package name */
    public static final ZoneId f18142c;
    public static final DateTimeFormatter c0;

    /* renamed from: d, reason: collision with root package name */
    public static final DateTimeFormatter f18143d;
    public static final DateTimeFormatter d0;

    /* renamed from: e, reason: collision with root package name */
    public static final DateTimeFormatter f18144e;
    public static final DateTimeFormatter e0;

    /* renamed from: f, reason: collision with root package name */
    public static final DateTimeFormatter f18145f;
    public static final DateTimeFormatter f0;

    /* renamed from: g, reason: collision with root package name */
    public static final DateTimeFormatter f18146g;
    public static final DateTimeFormatter g0;

    /* renamed from: h, reason: collision with root package name */
    public static final DateTimeFormatter f18147h;
    public static final DateTimeFormatter h0;

    /* renamed from: i, reason: collision with root package name */
    public static final DateTimeFormatter f18148i;
    public static final DateTimeFormatter i0;

    /* renamed from: j, reason: collision with root package name */
    public static final DateTimeFormatter f18149j;
    public static final DateTimeFormatter j0;

    /* renamed from: k, reason: collision with root package name */
    public static final DateTimeFormatter f18150k;
    public static final DateTimeFormatter k0;

    /* renamed from: l, reason: collision with root package name */
    public static final DateTimeFormatter f18151l;
    public static final DateTimeFormatter l0;

    /* renamed from: m, reason: collision with root package name */
    public static final DateTimeFormatter f18152m;
    public static final DateTimeFormatter m0;

    /* renamed from: n, reason: collision with root package name */
    public static final DateTimeFormatter f18153n;
    public static final DateTimeFormatter n0;

    /* renamed from: o, reason: collision with root package name */
    public static final DateTimeFormatter f18154o;
    public static final DateTimeFormatter o0;

    /* renamed from: p, reason: collision with root package name */
    public static final DateTimeFormatter f18155p;
    public static final DateTimeFormatter p0;

    /* renamed from: q, reason: collision with root package name */
    public static final DateTimeFormatter f18156q;
    public static final DateTimeFormatter q0;

    /* renamed from: r, reason: collision with root package name */
    public static final DateTimeFormatter f18157r;
    public static final DateTimeFormatter r0;

    /* renamed from: s, reason: collision with root package name */
    public static final DateTimeFormatter f18158s;
    public static final DateTimeFormatter s0;
    public static final DateTimeFormatter t;
    public static final DateTimeFormatter t0;
    public static final DateTimeFormatter u;
    public static final DateTimeFormatter u0;
    public static final DateTimeFormatter v;
    public static final DateTimeFormatter v0;
    public static final DateTimeFormatter w;
    public static final DateTimeFormatter w0;
    public static final DateTimeFormatter x;
    public static final DateTimeFormatter x0;
    public static final DateTimeFormatter y;
    public static final DateTimeFormatter y0;
    public static final DateTimeFormatter z;
    public static final DateTimeFormatter z0;

    /* renamed from: com.zte.timeutil.formatter.DateTimeFormatterUtil$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f18159a;

        static {
            int[] iArr = new int[CommonTimeEnum.values().length];
            f18159a = iArr;
            try {
                iArr[CommonTimeEnum.TODAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f18159a[CommonTimeEnum.TOMORROW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f18159a[CommonTimeEnum.NEXTWEEK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f18159a[CommonTimeEnum.NEXTMONTH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f18159a[CommonTimeEnum.NEXTYEAR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f18159a[CommonTimeEnum.YESTERDAY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f18159a[CommonTimeEnum.LASTWEEK.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f18159a[CommonTimeEnum.LASTMONTH.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f18159a[CommonTimeEnum.LASTYEAR.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    static {
        ZoneId systemDefault = ZoneId.systemDefault();
        f18140a = systemDefault;
        String d2 = ZoneIdEnum.CTT.d();
        f18141b = d2;
        f18142c = ZoneId.of(d2);
        f18143d = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(systemDefault);
        f18144e = DateTimeFormatter.ofPattern("yyyy-M-d").withZone(systemDefault);
        f18145f = DateTimeFormatter.ofPattern("yyyyMMdd").withZone(systemDefault);
        f18146g = DateTimeFormatter.ofPattern("yyyy/MM/dd").withZone(systemDefault);
        f18147h = DateTimeFormatter.ofPattern("yyyy/M/d").withZone(systemDefault);
        f18148i = DateTimeFormatter.ofPattern("yyyy年MM月dd日").withZone(systemDefault);
        f18149j = DateTimeFormatter.ofPattern("yyyy年M月d日").withZone(systemDefault);
        f18150k = DateTimeFormatter.ofPattern("yyyy.MM.dd").withZone(systemDefault);
        f18151l = DateTimeFormatter.ofPattern("yyyy.M.d").withZone(systemDefault);
        f18152m = DateTimeFormatter.ofPattern("yy/MM/dd").withZone(systemDefault);
        f18153n = DateTimeFormatter.ofPattern("yy/M/d").withZone(systemDefault);
        f18154o = DateTimeFormatter.ofPattern("MM/dd/yy").withZone(systemDefault);
        f18155p = DateTimeFormatter.ofPattern("M/d/yy").withZone(systemDefault);
        f18156q = DateTimeFormatter.ofPattern("yyyy-MM-dd E").withZone(systemDefault);
        f18157r = DateTimeFormatter.ofPattern("yy").withZone(systemDefault);
        f18158s = DateTimeFormatter.ofPattern("yyyy").withZone(systemDefault);
        t = DateTimeFormatter.ofPattern("yyyy-MM").withZone(systemDefault);
        u = DateTimeFormatter.ofPattern("yyyyMM").withZone(systemDefault);
        v = DateTimeFormatter.ofPattern("yyyy/MM").withZone(systemDefault);
        w = DateTimeFormatter.ofPattern("yyyy年MM月").withZone(systemDefault);
        x = DateTimeFormatter.ofPattern("yyyy年M月").withZone(systemDefault);
        y = DateTimeFormatter.ofPattern("MM-dd").withZone(systemDefault);
        z = DateTimeFormatter.ofPattern("MMdd").withZone(systemDefault);
        A = DateTimeFormatter.ofPattern("MM/dd").withZone(systemDefault);
        B = DateTimeFormatter.ofPattern("M/d").withZone(systemDefault);
        C = DateTimeFormatter.ofPattern("MM月dd日").withZone(systemDefault);
        D = DateTimeFormatter.ofPattern("M月d日").withZone(systemDefault);
        E = DateTimeFormatter.ofPattern(DateFormatPattern.f18129a).withZone(systemDefault);
        F = DateTimeFormatter.ofPattern(DateFormatPattern.f18130b).withZone(systemDefault);
        G = DateTimeFormatter.ofPattern(DateFormatPattern.f18131c).withZone(systemDefault);
        H = DateTimeFormatter.ofPattern(DateFormatPattern.f18132d).withZone(systemDefault);
        I = DateTimeFormatter.ofPattern(DateFormatPattern.f18133e).withZone(systemDefault);
        J = DateTimeFormatter.ofPattern(DateFormatPattern.f18134f).withZone(systemDefault);
        K = DateTimeFormatter.ofPattern(DateFormatPattern.f18135g).withZone(systemDefault);
        L = DateTimeFormatter.ofPattern(DateFormatPattern.f18136h).withZone(systemDefault);
        String str = DateFormatPattern.f18136h;
        Locale locale = Locale.ENGLISH;
        M = DateTimeFormatter.ofPattern(str, locale).withZone(systemDefault);
        N = DateTimeFormatter.ofPattern(DateFormatPattern.f18137i).withZone(systemDefault);
        O = DateTimeFormatter.ofPattern(DateFormatPattern.f18138j).withZone(systemDefault);
        P = DateTimeFormatter.ofPattern(DateFormatPattern.f18139k).withZone(systemDefault);
        Q = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(systemDefault);
        R = DateTimeFormatter.ofPattern("yyyy-M-d H:m:s").withZone(systemDefault);
        S = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").withZone(systemDefault);
        T = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").withZone(systemDefault);
        U = DateTimeFormatter.ofPattern("yyyy/M/d H:m:s").withZone(systemDefault);
        V = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss").withZone(systemDefault);
        W = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒").withZone(systemDefault);
        X = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(systemDefault);
        Y = DateTimeFormatter.ofPattern("yyyy-M-d H:m").withZone(systemDefault);
        Z = DateTimeFormatter.ofPattern("yyyyMMddHHmm").withZone(systemDefault);
        a0 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm").withZone(systemDefault);
        b0 = DateTimeFormatter.ofPattern("yyyy/M/d H:m").withZone(systemDefault);
        c0 = DateTimeFormatter.ofPattern("yyyy/M/d h:m a").withZone(systemDefault);
        d0 = DateTimeFormatter.ofPattern("yyyy/M/d h:m a", locale).withZone(systemDefault);
        e0 = DateTimeFormatter.ofPattern("MM-dd HH:mm").withZone(systemDefault);
        f0 = DateTimeFormatter.ofPattern("MM月dd日 HH:mm").withZone(systemDefault);
        g0 = DateTimeFormatter.ofPattern("MM-dd HH:mm:ss").withZone(systemDefault);
        h0 = DateTimeFormatter.ofPattern("MM月dd日 HH:mm:ss").withZone(systemDefault);
        i0 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm:ss a").withZone(systemDefault);
        j0 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm:ss a", locale).withZone(systemDefault);
        k0 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh时mm分ss秒 a").withZone(systemDefault);
        l0 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh时mm分ss秒 a", locale).withZone(systemDefault);
        m0 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").withZone(systemDefault);
        n0 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS").withZone(systemDefault);
        o0 = new DateTimeFormatterBuilder().appendPattern("yyyyMMddHHmmss").appendValue(ChronoField.MILLI_OF_SECOND, 3).toFormatter().withZone(systemDefault);
        p0 = DateTimeFormatter.ofPattern("yyyy-M-d H:m:s.SSS").withZone(systemDefault);
        q0 = DateTimeFormatter.ofPattern("yyyy/M/d H:m:s.SSS").withZone(systemDefault);
        r0 = DateTimeFormatter.ofPattern("yyyy-M-d H:m:s,SSS").withZone(systemDefault);
        s0 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS").withZone(systemDefault);
        t0 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS").withZone(systemDefault);
        u0 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
        v0 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssxxx");
        w0 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        x0 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        y0 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSxxx");
        z0 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        A0 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ");
        B0 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSxxx");
        C0 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");
        D0 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSZ");
        E0 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSxxx");
        F0 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSXXX");
        G0 = DateTimeFormatter.ISO_DATE;
        H0 = DateTimeFormatter.ISO_DATE_TIME;
        I0 = DateTimeFormatter.ISO_INSTANT;
        J0 = DateTimeFormatter.ISO_LOCAL_DATE;
        K0 = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        L0 = DateTimeFormatter.ISO_LOCAL_TIME;
        M0 = DateTimeFormatter.ISO_TIME;
        N0 = DateTimeFormatter.ISO_WEEK_DATE;
        O0 = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        P0 = DateTimeFormatter.BASIC_ISO_DATE;
        Q0 = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", locale);
    }

    private static String a(String str) {
        return StringUtil.b(str) ? str.replace("年", "-").replace("月", "-").replace("日", "").replace("时", ":").replace("分", ":").replace("秒", "") : str;
    }

    private static String b(String str) {
        return StringUtil.a(str, ".") == 2 ? str.replace(".", "-") : str;
    }

    private static String c(String str) {
        return !str.contains("[") ? str.replace("/", "-") : str;
    }

    public static String d(LocalDateTime localDateTime, String str) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        Objects.requireNonNull(str, "dateFormatPattern");
        return e(localDateTime, DateTimeFormatter.ofPattern(str).withZone(f18140a));
    }

    public static String e(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        Objects.requireNonNull(dateTimeFormatter, "formatter");
        return DateTimeConverterUtil.m(localDateTime).format(dateTimeFormatter);
    }

    public static String f(Date date, String str) {
        Objects.requireNonNull(str, "dateFormatPattern");
        return g(date, DateTimeFormatter.ofPattern(str).withZone(f18140a));
    }

    public static String g(Date date, DateTimeFormatter dateTimeFormatter) {
        Objects.requireNonNull(dateTimeFormatter, "formatter");
        return DateTimeConverterUtil.p(date).format(dateTimeFormatter);
    }

    public static String h(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        return localDateTime.format(f18143d);
    }

    public static LocalDateTime i(String str) {
        if (StringUtil.c(str)) {
            throw new DateTimeException("text is null");
        }
        if (str.contains("T")) {
            String trim = str.trim();
            int length = trim.length();
            return !trim.contains("[") ? trim.contains("Z") ? k(trim, H0) : length == 24 ? k(trim, u0) : length == 28 ? k(trim, x0) : k(trim, H0) : length == 24 ? DateTimeConverterUtil.h(l(trim, u0)) : length == 28 ? DateTimeConverterUtil.h(l(trim, x0)) : DateTimeConverterUtil.h(l(trim, H0));
        }
        throw new DateTimeException("text is not supported! " + str);
    }

    public static LocalDateTime j(String str) {
        Objects.requireNonNull(str, "text");
        String trim = str.trim();
        if (!trim.contains(".")) {
            throw new DateTimeException("text is not supported! " + trim);
        }
        String[] split = trim.split("\\.");
        String str2 = split[0];
        String str3 = split[1];
        int length = str2.length();
        int length2 = str3.length();
        if (length != 19) {
            throw new DateTimeException("text is not supported! " + trim);
        }
        if (length2 > 9) {
            throw new DateTimeException("text is not supported! " + trim);
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < 9 - length2; i2++) {
            sb.append("0");
        }
        return k(str2 + "." + (str3 + sb.toString()), t0);
    }

    public static LocalDateTime k(String str, DateTimeFormatter dateTimeFormatter) {
        Objects.requireNonNull(dateTimeFormatter, "formatter");
        try {
            return DateTimeConverterUtil.i(dateTimeFormatter.parse(str));
        } catch (DateTimeException e2) {
            if (e2.getMessage().startsWith("Unable to obtain")) {
                return DateTimeConverterUtil.g(LocalDate.parse(str, dateTimeFormatter));
            }
            throw e2;
        }
    }

    public static ZonedDateTime l(String str, DateTimeFormatter dateTimeFormatter) {
        return ZonedDateTime.parse(str, dateTimeFormatter);
    }

    private static String m(String str) {
        return a(b(c(str)));
    }

    public static ParseResult n(String str) {
        if (StringUtil.c(str)) {
            throw new DateTimeException("text is null");
        }
        String trim = str.trim();
        int length = trim.length();
        if (length < 8) {
            throw new DateTimeException("text is not supported! " + trim);
        }
        String m2 = m(trim);
        if (StringUtil.f(m2)) {
            return new ParseResult(k(m2, Q0), 6);
        }
        if (!StringUtil.e(m2)) {
            int a2 = StringUtil.a(m2, ":");
            if (a2 == 0) {
                return new ParseResult(k(m2, f18144e), 3);
            }
            if (m2.contains("T")) {
                return new ParseResult(i(m2), 6);
            }
            if (a2 > 0 && m2.contains(".")) {
                return m2.split("\\.")[1].length() == 3 ? new ParseResult(k(m2, p0), 6) : new ParseResult(j(m2), 6);
            }
            if (a2 > 0 && m2.contains(",")) {
                return new ParseResult(k(m2, r0), 6);
            }
            if (a2 > 0) {
                if (a2 == 2) {
                    return new ParseResult(k(m2, R), 6);
                }
                if (a2 == 1) {
                    return new ParseResult(k(m2, Y), 5);
                }
            }
        } else {
            if (length == 14) {
                return new ParseResult(k(m2, S), 6);
            }
            if (length == 17) {
                return new ParseResult(k(m2, o0), 6);
            }
            if (length == 8) {
                return new ParseResult(k(m2, f18145f), 3);
            }
            if (length == 12) {
                return new ParseResult(k(m2, Z), 5);
            }
        }
        throw new DateTimeException("text is not supported! " + m2);
    }
}
