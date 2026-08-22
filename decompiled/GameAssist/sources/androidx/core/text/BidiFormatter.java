package androidx.core.text;

import android.text.SpannableStringBuilder;
import com.google.mlkit.common.MlKitException;
import java.util.Locale;

/* loaded from: classes.dex */
public final class BidiFormatter {

    /* renamed from: d, reason: collision with root package name */
    static final TextDirectionHeuristicCompat f3193d;

    /* renamed from: e, reason: collision with root package name */
    private static final String f3194e;

    /* renamed from: f, reason: collision with root package name */
    private static final String f3195f;

    /* renamed from: g, reason: collision with root package name */
    static final BidiFormatter f3196g;

    /* renamed from: h, reason: collision with root package name */
    static final BidiFormatter f3197h;

    /* renamed from: a, reason: collision with root package name */
    private final boolean f3198a;

    /* renamed from: b, reason: collision with root package name */
    private final int f3199b;

    /* renamed from: c, reason: collision with root package name */
    private final TextDirectionHeuristicCompat f3200c;

    public static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        private boolean f3201a;

        /* renamed from: b, reason: collision with root package name */
        private int f3202b;

        /* renamed from: c, reason: collision with root package name */
        private TextDirectionHeuristicCompat f3203c;

        public Builder() {
            c(BidiFormatter.e(Locale.getDefault()));
        }

        private static BidiFormatter b(boolean z) {
            return z ? BidiFormatter.f3197h : BidiFormatter.f3196g;
        }

        private void c(boolean z) {
            this.f3201a = z;
            this.f3203c = BidiFormatter.f3193d;
            this.f3202b = 2;
        }

        public BidiFormatter a() {
            return (this.f3202b == 2 && this.f3203c == BidiFormatter.f3193d) ? b(this.f3201a) : new BidiFormatter(this.f3201a, this.f3202b, this.f3203c);
        }
    }

    private static class DirectionalityEstimator {

        /* renamed from: f, reason: collision with root package name */
        private static final byte[] f3204f = new byte[1792];

        /* renamed from: a, reason: collision with root package name */
        private final CharSequence f3205a;

        /* renamed from: b, reason: collision with root package name */
        private final boolean f3206b;

        /* renamed from: c, reason: collision with root package name */
        private final int f3207c;

        /* renamed from: d, reason: collision with root package name */
        private int f3208d;

        /* renamed from: e, reason: collision with root package name */
        private char f3209e;

        static {
            for (int i2 = 0; i2 < 1792; i2++) {
                f3204f[i2] = Character.getDirectionality(i2);
            }
        }

        DirectionalityEstimator(CharSequence charSequence, boolean z) {
            this.f3205a = charSequence;
            this.f3206b = z;
            this.f3207c = charSequence.length();
        }

        private static byte c(char c2) {
            return c2 < 1792 ? f3204f[c2] : Character.getDirectionality(c2);
        }

        private byte f() {
            char charAt;
            int i2 = this.f3208d;
            do {
                int i3 = this.f3208d;
                if (i3 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f3205a;
                int i4 = i3 - 1;
                this.f3208d = i4;
                charAt = charSequence.charAt(i4);
                this.f3209e = charAt;
                if (charAt == '&') {
                    return (byte) 12;
                }
            } while (charAt != ';');
            this.f3208d = i2;
            this.f3209e = ';';
            return (byte) 13;
        }

        private byte g() {
            char charAt;
            do {
                int i2 = this.f3208d;
                if (i2 >= this.f3207c) {
                    return (byte) 12;
                }
                CharSequence charSequence = this.f3205a;
                this.f3208d = i2 + 1;
                charAt = charSequence.charAt(i2);
                this.f3209e = charAt;
            } while (charAt != ';');
            return (byte) 12;
        }

        private byte h() {
            char charAt;
            int i2 = this.f3208d;
            while (true) {
                int i3 = this.f3208d;
                if (i3 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f3205a;
                int i4 = i3 - 1;
                this.f3208d = i4;
                char charAt2 = charSequence.charAt(i4);
                this.f3209e = charAt2;
                if (charAt2 == '<') {
                    return (byte) 12;
                }
                if (charAt2 == '>') {
                    break;
                }
                if (charAt2 == '\"' || charAt2 == '\'') {
                    do {
                        int i5 = this.f3208d;
                        if (i5 > 0) {
                            CharSequence charSequence2 = this.f3205a;
                            int i6 = i5 - 1;
                            this.f3208d = i6;
                            charAt = charSequence2.charAt(i6);
                            this.f3209e = charAt;
                        }
                    } while (charAt != charAt2);
                }
            }
            this.f3208d = i2;
            this.f3209e = '>';
            return (byte) 13;
        }

        private byte i() {
            char charAt;
            int i2 = this.f3208d;
            while (true) {
                int i3 = this.f3208d;
                if (i3 >= this.f3207c) {
                    this.f3208d = i2;
                    this.f3209e = '<';
                    return (byte) 13;
                }
                CharSequence charSequence = this.f3205a;
                this.f3208d = i3 + 1;
                char charAt2 = charSequence.charAt(i3);
                this.f3209e = charAt2;
                if (charAt2 == '>') {
                    return (byte) 12;
                }
                if (charAt2 == '\"' || charAt2 == '\'') {
                    do {
                        int i4 = this.f3208d;
                        if (i4 < this.f3207c) {
                            CharSequence charSequence2 = this.f3205a;
                            this.f3208d = i4 + 1;
                            charAt = charSequence2.charAt(i4);
                            this.f3209e = charAt;
                        }
                    } while (charAt != charAt2);
                }
            }
        }

        byte a() {
            char charAt = this.f3205a.charAt(this.f3208d - 1);
            this.f3209e = charAt;
            if (Character.isLowSurrogate(charAt)) {
                int codePointBefore = Character.codePointBefore(this.f3205a, this.f3208d);
                this.f3208d -= Character.charCount(codePointBefore);
                return Character.getDirectionality(codePointBefore);
            }
            this.f3208d--;
            byte c2 = c(this.f3209e);
            if (!this.f3206b) {
                return c2;
            }
            char c3 = this.f3209e;
            return c3 == '>' ? h() : c3 == ';' ? f() : c2;
        }

        byte b() {
            char charAt = this.f3205a.charAt(this.f3208d);
            this.f3209e = charAt;
            if (Character.isHighSurrogate(charAt)) {
                int codePointAt = Character.codePointAt(this.f3205a, this.f3208d);
                this.f3208d += Character.charCount(codePointAt);
                return Character.getDirectionality(codePointAt);
            }
            this.f3208d++;
            byte c2 = c(this.f3209e);
            if (!this.f3206b) {
                return c2;
            }
            char c3 = this.f3209e;
            return c3 == '<' ? i() : c3 == '&' ? g() : c2;
        }

        int d() {
            this.f3208d = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (this.f3208d < this.f3207c && i2 == 0) {
                byte b2 = b();
                if (b2 != 0) {
                    if (b2 == 1 || b2 == 2) {
                        if (i4 == 0) {
                            return 1;
                        }
                    } else if (b2 != 9) {
                        switch (b2) {
                            case 14:
                            case 15:
                                i4++;
                                i3 = -1;
                                continue;
                            case 16:
                            case MlKitException.NETWORK_ISSUE /* 17 */:
                                i4++;
                                i3 = 1;
                                continue;
                            case MlKitException.UNSUPPORTED /* 18 */:
                                i4--;
                                i3 = 0;
                                continue;
                        }
                    }
                } else if (i4 == 0) {
                    return -1;
                }
                i2 = i4;
            }
            if (i2 == 0) {
                return 0;
            }
            if (i3 != 0) {
                return i3;
            }
            while (this.f3208d > 0) {
                switch (a()) {
                    case 14:
                    case 15:
                        if (i2 == i4) {
                            return -1;
                        }
                        break;
                    case 16:
                    case MlKitException.NETWORK_ISSUE /* 17 */:
                        if (i2 == i4) {
                            return 1;
                        }
                        break;
                    case MlKitException.UNSUPPORTED /* 18 */:
                        i4++;
                        continue;
                }
                i4--;
            }
            return 0;
        }

        int e() {
            this.f3208d = this.f3207c;
            int i2 = 0;
            while (true) {
                int i3 = i2;
                while (this.f3208d > 0) {
                    byte a2 = a();
                    if (a2 != 0) {
                        if (a2 == 1 || a2 == 2) {
                            if (i2 == 0) {
                                return 1;
                            }
                            if (i3 == 0) {
                                break;
                            }
                        } else if (a2 != 9) {
                            switch (a2) {
                                case 14:
                                case 15:
                                    if (i3 == i2) {
                                        return -1;
                                    }
                                    i2--;
                                    break;
                                case 16:
                                case MlKitException.NETWORK_ISSUE /* 17 */:
                                    if (i3 == i2) {
                                        return 1;
                                    }
                                    i2--;
                                    break;
                                case MlKitException.UNSUPPORTED /* 18 */:
                                    i2++;
                                    break;
                                default:
                                    if (i3 != 0) {
                                        break;
                                    } else {
                                        break;
                                    }
                            }
                        } else {
                            continue;
                        }
                    } else {
                        if (i2 == 0) {
                            return -1;
                        }
                        if (i3 == 0) {
                            break;
                        }
                    }
                }
                return 0;
            }
        }
    }

    static {
        TextDirectionHeuristicCompat textDirectionHeuristicCompat = TextDirectionHeuristicsCompat.f3224c;
        f3193d = textDirectionHeuristicCompat;
        f3194e = Character.toString((char) 8206);
        f3195f = Character.toString((char) 8207);
        f3196g = new BidiFormatter(false, 2, textDirectionHeuristicCompat);
        f3197h = new BidiFormatter(true, 2, textDirectionHeuristicCompat);
    }

    BidiFormatter(boolean z, int i2, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        this.f3198a = z;
        this.f3199b = i2;
        this.f3200c = textDirectionHeuristicCompat;
    }

    private static int a(CharSequence charSequence) {
        return new DirectionalityEstimator(charSequence, false).d();
    }

    private static int b(CharSequence charSequence) {
        return new DirectionalityEstimator(charSequence, false).e();
    }

    public static BidiFormatter c() {
        return new Builder().a();
    }

    static boolean e(Locale locale) {
        return TextUtilsCompat.a(locale) == 1;
    }

    private String f(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        boolean isRtl = textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
        return (this.f3198a || !(isRtl || b(charSequence) == 1)) ? this.f3198a ? (!isRtl || b(charSequence) == -1) ? f3195f : "" : "" : f3194e;
    }

    private String g(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        boolean isRtl = textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
        return (this.f3198a || !(isRtl || a(charSequence) == 1)) ? this.f3198a ? (!isRtl || a(charSequence) == -1) ? f3195f : "" : "" : f3194e;
    }

    public boolean d() {
        return (this.f3199b & 2) != 0;
    }

    public CharSequence h(CharSequence charSequence) {
        return i(charSequence, this.f3200c, true);
    }

    public CharSequence i(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat, boolean z) {
        if (charSequence == null) {
            return null;
        }
        boolean isRtl = textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (d() && z) {
            spannableStringBuilder.append((CharSequence) g(charSequence, isRtl ? TextDirectionHeuristicsCompat.f3223b : TextDirectionHeuristicsCompat.f3222a));
        }
        if (isRtl != this.f3198a) {
            spannableStringBuilder.append(isRtl ? (char) 8235 : (char) 8234);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append((char) 8236);
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (z) {
            spannableStringBuilder.append((CharSequence) f(charSequence, isRtl ? TextDirectionHeuristicsCompat.f3223b : TextDirectionHeuristicsCompat.f3222a));
        }
        return spannableStringBuilder;
    }

    public String j(String str) {
        return k(str, this.f3200c, true);
    }

    public String k(String str, TextDirectionHeuristicCompat textDirectionHeuristicCompat, boolean z) {
        if (str == null) {
            return null;
        }
        return i(str, textDirectionHeuristicCompat, z).toString();
    }
}
