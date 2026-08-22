package androidx.core.text;

import com.google.mlkit.common.MlKitException;
import java.util.Locale;

/* loaded from: classes.dex */
public final class TextDirectionHeuristicsCompat {

    /* renamed from: a, reason: collision with root package name */
    public static final TextDirectionHeuristicCompat f3222a = new TextDirectionHeuristicInternal(null, false);

    /* renamed from: b, reason: collision with root package name */
    public static final TextDirectionHeuristicCompat f3223b = new TextDirectionHeuristicInternal(null, true);

    /* renamed from: c, reason: collision with root package name */
    public static final TextDirectionHeuristicCompat f3224c;

    /* renamed from: d, reason: collision with root package name */
    public static final TextDirectionHeuristicCompat f3225d;

    /* renamed from: e, reason: collision with root package name */
    public static final TextDirectionHeuristicCompat f3226e;

    /* renamed from: f, reason: collision with root package name */
    public static final TextDirectionHeuristicCompat f3227f;

    private static class AnyStrong implements TextDirectionAlgorithm {

        /* renamed from: b, reason: collision with root package name */
        static final AnyStrong f3228b = new AnyStrong(true);

        /* renamed from: a, reason: collision with root package name */
        private final boolean f3229a;

        private AnyStrong(boolean z) {
            this.f3229a = z;
        }

        @Override // androidx.core.text.TextDirectionHeuristicsCompat.TextDirectionAlgorithm
        public int a(CharSequence charSequence, int i2, int i3) {
            int i4 = i3 + i2;
            boolean z = false;
            while (i2 < i4) {
                int a2 = TextDirectionHeuristicsCompat.a(Character.getDirectionality(charSequence.charAt(i2)));
                if (a2 != 0) {
                    if (a2 != 1) {
                        continue;
                        i2++;
                        z = z;
                    } else if (!this.f3229a) {
                        return 1;
                    }
                } else if (this.f3229a) {
                    return 0;
                }
                z = true;
                i2++;
                z = z;
            }
            if (z) {
                return this.f3229a ? 1 : 0;
            }
            return 2;
        }
    }

    private static class FirstStrong implements TextDirectionAlgorithm {

        /* renamed from: a, reason: collision with root package name */
        static final FirstStrong f3230a = new FirstStrong();

        private FirstStrong() {
        }

        @Override // androidx.core.text.TextDirectionHeuristicsCompat.TextDirectionAlgorithm
        public int a(CharSequence charSequence, int i2, int i3) {
            int i4 = i3 + i2;
            int i5 = 2;
            while (i2 < i4 && i5 == 2) {
                i5 = TextDirectionHeuristicsCompat.b(Character.getDirectionality(charSequence.charAt(i2)));
                i2++;
            }
            return i5;
        }
    }

    private interface TextDirectionAlgorithm {
        int a(CharSequence charSequence, int i2, int i3);
    }

    private static abstract class TextDirectionHeuristicImpl implements TextDirectionHeuristicCompat {

        /* renamed from: a, reason: collision with root package name */
        private final TextDirectionAlgorithm f3231a;

        TextDirectionHeuristicImpl(TextDirectionAlgorithm textDirectionAlgorithm) {
            this.f3231a = textDirectionAlgorithm;
        }

        private boolean b(CharSequence charSequence, int i2, int i3) {
            int a2 = this.f3231a.a(charSequence, i2, i3);
            if (a2 == 0) {
                return true;
            }
            if (a2 != 1) {
                return a();
            }
            return false;
        }

        protected abstract boolean a();

        @Override // androidx.core.text.TextDirectionHeuristicCompat
        public boolean isRtl(CharSequence charSequence, int i2, int i3) {
            if (charSequence == null || i2 < 0 || i3 < 0 || charSequence.length() - i3 < i2) {
                throw new IllegalArgumentException();
            }
            return this.f3231a == null ? a() : b(charSequence, i2, i3);
        }
    }

    private static class TextDirectionHeuristicInternal extends TextDirectionHeuristicImpl {

        /* renamed from: b, reason: collision with root package name */
        private final boolean f3232b;

        TextDirectionHeuristicInternal(TextDirectionAlgorithm textDirectionAlgorithm, boolean z) {
            super(textDirectionAlgorithm);
            this.f3232b = z;
        }

        @Override // androidx.core.text.TextDirectionHeuristicsCompat.TextDirectionHeuristicImpl
        protected boolean a() {
            return this.f3232b;
        }
    }

    private static class TextDirectionHeuristicLocale extends TextDirectionHeuristicImpl {

        /* renamed from: b, reason: collision with root package name */
        static final TextDirectionHeuristicLocale f3233b = new TextDirectionHeuristicLocale();

        TextDirectionHeuristicLocale() {
            super(null);
        }

        @Override // androidx.core.text.TextDirectionHeuristicsCompat.TextDirectionHeuristicImpl
        protected boolean a() {
            return TextUtilsCompat.a(Locale.getDefault()) == 1;
        }
    }

    static {
        FirstStrong firstStrong = FirstStrong.f3230a;
        f3224c = new TextDirectionHeuristicInternal(firstStrong, false);
        f3225d = new TextDirectionHeuristicInternal(firstStrong, true);
        f3226e = new TextDirectionHeuristicInternal(AnyStrong.f3228b, false);
        f3227f = TextDirectionHeuristicLocale.f3233b;
    }

    static int a(int i2) {
        if (i2 != 0) {
            return (i2 == 1 || i2 == 2) ? 0 : 2;
        }
        return 1;
    }

    static int b(int i2) {
        if (i2 != 0) {
            if (i2 == 1 || i2 == 2) {
                return 0;
            }
            switch (i2) {
                case 14:
                case 15:
                    break;
                case 16:
                case MlKitException.NETWORK_ISSUE /* 17 */:
                    return 0;
                default:
                    return 2;
            }
        }
        return 1;
    }
}
