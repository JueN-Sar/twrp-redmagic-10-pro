package androidx.constraintlayout.core.parser;

import java.util.Arrays;
import java.util.Objects;

/* loaded from: classes.dex */
public class CLElement implements Cloneable {

    /* renamed from: c, reason: collision with root package name */
    private final char[] f1893c;

    /* renamed from: h, reason: collision with root package name */
    protected long f1894h;

    /* renamed from: i, reason: collision with root package name */
    protected long f1895i;

    /* renamed from: j, reason: collision with root package name */
    protected CLContainer f1896j;

    /* renamed from: k, reason: collision with root package name */
    private int f1897k;

    @Override // 
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public CLElement clone() {
        try {
            return (CLElement) super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new AssertionError();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CLElement)) {
            return false;
        }
        CLElement cLElement = (CLElement) obj;
        if (this.f1894h == cLElement.f1894h && this.f1895i == cLElement.f1895i && this.f1897k == cLElement.f1897k && Arrays.equals(this.f1893c, cLElement.f1893c)) {
            return Objects.equals(this.f1896j, cLElement.f1896j);
        }
        return false;
    }

    public String f() {
        String str = new String(this.f1893c);
        if (str.length() < 1) {
            return "";
        }
        long j2 = this.f1895i;
        if (j2 != Long.MAX_VALUE) {
            long j3 = this.f1894h;
            if (j2 >= j3) {
                return str.substring((int) j3, ((int) j2) + 1);
            }
        }
        long j4 = this.f1894h;
        return str.substring((int) j4, ((int) j4) + 1);
    }

    public int g() {
        return this.f1897k;
    }

    protected String h() {
        String cls = getClass().toString();
        return cls.substring(cls.lastIndexOf(46) + 1);
    }

    public int hashCode() {
        int hashCode = Arrays.hashCode(this.f1893c) * 31;
        long j2 = this.f1894h;
        int i2 = (hashCode + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        long j3 = this.f1895i;
        int i3 = (i2 + ((int) (j3 ^ (j3 >>> 32)))) * 31;
        CLContainer cLContainer = this.f1896j;
        return ((i3 + (cLContainer != null ? cLContainer.hashCode() : 0)) * 31) + this.f1897k;
    }

    public boolean i() {
        char[] cArr = this.f1893c;
        return cArr != null && cArr.length >= 1;
    }

    public void j(CLContainer cLContainer) {
        this.f1896j = cLContainer;
    }

    public String toString() {
        long j2 = this.f1894h;
        long j3 = this.f1895i;
        if (j2 > j3 || j3 == Long.MAX_VALUE) {
            return getClass() + " (INVALID, " + this.f1894h + "-" + this.f1895i + ")";
        }
        return h() + " (" + this.f1894h + " : " + this.f1895i + ") <<" + new String(this.f1893c).substring((int) this.f1894h, ((int) this.f1895i) + 1) + ">>";
    }
}
