package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import androidx.annotation.RestrictTo;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo
/* loaded from: classes.dex */
public final class ComplexColorCompat {

    /* renamed from: a, reason: collision with root package name */
    private final Shader f2874a;

    /* renamed from: b, reason: collision with root package name */
    private final ColorStateList f2875b;

    /* renamed from: c, reason: collision with root package name */
    private int f2876c;

    private ComplexColorCompat(Shader shader, ColorStateList colorStateList, int i2) {
        this.f2874a = shader;
        this.f2875b = colorStateList;
        this.f2876c = i2;
    }

    private static ComplexColorCompat a(Resources resources, int i2, Resources.Theme theme) {
        int next;
        XmlResourceParser xml = resources.getXml(i2);
        AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
        do {
            next = xml.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        String name = xml.getName();
        name.hashCode();
        if (name.equals("gradient")) {
            return d(GradientColorInflaterCompat.b(resources, xml, asAttributeSet, theme));
        }
        if (name.equals("selector")) {
            return c(ColorStateListInflaterCompat.b(resources, xml, asAttributeSet, theme));
        }
        throw new XmlPullParserException(xml.getPositionDescription() + ": unsupported complex color tag " + name);
    }

    static ComplexColorCompat b(int i2) {
        return new ComplexColorCompat(null, null, i2);
    }

    static ComplexColorCompat c(ColorStateList colorStateList) {
        return new ComplexColorCompat(null, colorStateList, colorStateList.getDefaultColor());
    }

    static ComplexColorCompat d(Shader shader) {
        return new ComplexColorCompat(shader, null, 0);
    }

    public static ComplexColorCompat g(Resources resources, int i2, Resources.Theme theme) {
        try {
            return a(resources, i2, theme);
        } catch (Exception e2) {
            Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", e2);
            return null;
        }
    }

    public int e() {
        return this.f2876c;
    }

    public Shader f() {
        return this.f2874a;
    }

    public boolean h() {
        return this.f2874a != null;
    }

    public boolean i() {
        ColorStateList colorStateList;
        return this.f2874a == null && (colorStateList = this.f2875b) != null && colorStateList.isStateful();
    }

    public boolean j(int[] iArr) {
        if (i()) {
            ColorStateList colorStateList = this.f2875b;
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (colorForState != this.f2876c) {
                this.f2876c = colorForState;
                return true;
            }
        }
        return false;
    }

    public void k(int i2) {
        this.f2876c = i2;
    }

    public boolean l() {
        return h() || this.f2876c != 0;
    }
}
