package com.zte.timeutil.utils;

import java.util.function.Supplier;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class RegexCache {

    /* renamed from: a, reason: collision with root package name */
    private static volatile BaseCache f18192a;

    private static class Regex {

        /* renamed from: a, reason: collision with root package name */
        private String f18195a;

        /* renamed from: b, reason: collision with root package name */
        private String f18196b;

        /* renamed from: c, reason: collision with root package name */
        private int f18197c;

        /* renamed from: d, reason: collision with root package name */
        private String f18198d;

        public Regex(String str, int i2) {
            this(null, str, i2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Regex regex = (Regex) obj;
            if (this.f18197c != regex.f18197c) {
                return false;
            }
            String str = this.f18196b;
            if (str == null) {
                if (regex.f18196b != null) {
                    return false;
                }
            } else if (!str.equals(regex.f18196b)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i2 = (this.f18197c + 31) * 31;
            String str = this.f18196b;
            return i2 + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            return "Regex [name=" + this.f18195a + ", rule=" + this.f18196b + ", flags=" + this.f18197c + ", desc=" + this.f18198d + "]";
        }

        public Regex(String str, String str2, int i2) {
            this(str, str2, i2, null);
        }

        public Regex(String str, String str2, int i2, String str3) {
            this.f18195a = str;
            this.f18196b = str2;
            this.f18197c = i2;
            this.f18198d = str3;
        }
    }

    public static Pattern a(String str) {
        return b(str, 0);
    }

    public static Pattern b(final String str, final int i2) {
        return (Pattern) c().b(new Regex(str, i2), new Supplier<Pattern>() { // from class: com.zte.timeutil.utils.RegexCache.1
            @Override // java.util.function.Supplier
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Pattern get() {
                return Pattern.compile(str, i2);
            }
        });
    }

    private static BaseCache c() {
        if (f18192a == null) {
            synchronized (RegexCache.class) {
                try {
                    if (f18192a == null) {
                        f18192a = new BaseCache();
                    }
                } finally {
                }
            }
        }
        return f18192a;
    }
}
