package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StateSet;
import android.util.TypedValue;
import android.util.Xml;
import androidx.annotation.RestrictTo;
import androidx.core.R;
import androidx.core.math.MathUtils;
import com.zte.distbus.basetransfer.Constants;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo
/* loaded from: classes.dex */
public final class ColorStateListInflaterCompat {

    /* renamed from: a, reason: collision with root package name */
    private static final ThreadLocal f2873a = new ThreadLocal();

    public static ColorStateList a(Resources resources, XmlPullParser xmlPullParser, Resources.Theme theme) {
        int next;
        AttributeSet asAttributeSet = Xml.asAttributeSet(xmlPullParser);
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            return b(resources, xmlPullParser, asAttributeSet, theme);
        }
        throw new XmlPullParserException("No start tag found");
    }

    public static ColorStateList b(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        String name = xmlPullParser.getName();
        if (name.equals("selector")) {
            return e(resources, xmlPullParser, attributeSet, theme);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid color state list tag " + name);
    }

    private static TypedValue c() {
        ThreadLocal threadLocal = f2873a;
        TypedValue typedValue = (TypedValue) threadLocal.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        threadLocal.set(typedValue2);
        return typedValue2;
    }

    public static ColorStateList d(Resources resources, int i2, Resources.Theme theme) {
        try {
            return a(resources, resources.getXml(i2), theme);
        } catch (Exception e2) {
            Log.e("CSLCompat", "Failed to inflate ColorStateList.", e2);
            return null;
        }
    }

    private static ColorStateList e(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        int depth;
        int color;
        Resources resources2 = resources;
        int i2 = 1;
        int depth2 = xmlPullParser.getDepth() + 1;
        int[][] iArr = new int[20][];
        int[] iArr2 = new int[20];
        int i3 = 0;
        while (true) {
            int next = xmlPullParser.next();
            if (next == i2 || ((depth = xmlPullParser.getDepth()) < depth2 && next == 3)) {
                break;
            }
            if (next == 2 && depth <= depth2 && xmlPullParser.getName().equals(Constants.EXTRA_ITEM)) {
                TypedArray h2 = h(resources2, theme, attributeSet, R.styleable.ColorStateListItem);
                int resourceId = h2.getResourceId(R.styleable.ColorStateListItem_android_color, -1);
                if (resourceId == -1 || f(resources2, resourceId)) {
                    color = h2.getColor(R.styleable.ColorStateListItem_android_color, -65281);
                } else {
                    try {
                        color = a(resources2, resources2.getXml(resourceId), theme).getDefaultColor();
                    } catch (Exception unused) {
                        color = h2.getColor(R.styleable.ColorStateListItem_android_color, -65281);
                    }
                }
                float f2 = 1.0f;
                if (h2.hasValue(R.styleable.ColorStateListItem_android_alpha)) {
                    f2 = h2.getFloat(R.styleable.ColorStateListItem_android_alpha, 1.0f);
                } else if (h2.hasValue(R.styleable.ColorStateListItem_alpha)) {
                    f2 = h2.getFloat(R.styleable.ColorStateListItem_alpha, 1.0f);
                }
                float f3 = h2.hasValue(R.styleable.ColorStateListItem_android_lStar) ? h2.getFloat(R.styleable.ColorStateListItem_android_lStar, -1.0f) : h2.getFloat(R.styleable.ColorStateListItem_lStar, -1.0f);
                h2.recycle();
                int attributeCount = attributeSet.getAttributeCount();
                int[] iArr3 = new int[attributeCount];
                int i4 = 0;
                for (int i5 = 0; i5 < attributeCount; i5++) {
                    int attributeNameResource = attributeSet.getAttributeNameResource(i5);
                    if (attributeNameResource != 16843173 && attributeNameResource != 16843551 && attributeNameResource != R.attr.alpha && attributeNameResource != R.attr.lStar) {
                        int i6 = i4 + 1;
                        if (!attributeSet.getAttributeBooleanValue(i5, false)) {
                            attributeNameResource = -attributeNameResource;
                        }
                        iArr3[i4] = attributeNameResource;
                        i4 = i6;
                    }
                }
                int[] trimStateSet = StateSet.trimStateSet(iArr3, i4);
                iArr2 = GrowingArrayUtils.a(iArr2, i3, g(color, f2, f3));
                iArr = (int[][]) GrowingArrayUtils.b(iArr, i3, trimStateSet);
                i3++;
            }
            i2 = 1;
            resources2 = resources;
        }
        int[] iArr4 = new int[i3];
        int[][] iArr5 = new int[i3][];
        System.arraycopy(iArr2, 0, iArr4, 0, i3);
        System.arraycopy(iArr, 0, iArr5, 0, i3);
        return new ColorStateList(iArr5, iArr4);
    }

    private static boolean f(Resources resources, int i2) {
        TypedValue c2 = c();
        resources.getValue(i2, c2, true);
        int i3 = c2.type;
        return i3 >= 28 && i3 <= 31;
    }

    private static int g(int i2, float f2, float f3) {
        boolean z = f3 >= 0.0f && f3 <= 100.0f;
        if (f2 == 1.0f && !z) {
            return i2;
        }
        int b2 = MathUtils.b((int) ((Color.alpha(i2) * f2) + 0.5f), 0, 255);
        if (z) {
            CamColor c2 = CamColor.c(i2);
            i2 = CamColor.m(c2.j(), c2.i(), f3);
        }
        return (i2 & 16777215) | (b2 << 24);
    }

    private static TypedArray h(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        return theme == null ? resources.obtainAttributes(attributeSet, iArr) : theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }
}
