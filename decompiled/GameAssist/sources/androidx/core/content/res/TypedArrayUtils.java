package androidx.core.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.annotation.RestrictTo;
import org.xmlpull.v1.XmlPullParser;

@RestrictTo
/* loaded from: classes.dex */
public class TypedArrayUtils {
    public static int a(Context context, int i2, int i3) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i2, typedValue, true);
        return typedValue.resourceId != 0 ? i2 : i3;
    }

    public static boolean b(TypedArray typedArray, int i2, int i3, boolean z) {
        return typedArray.getBoolean(i2, typedArray.getBoolean(i3, z));
    }

    public static Drawable c(TypedArray typedArray, int i2, int i3) {
        Drawable drawable = typedArray.getDrawable(i2);
        return drawable == null ? typedArray.getDrawable(i3) : drawable;
    }

    public static int d(TypedArray typedArray, int i2, int i3, int i4) {
        return typedArray.getInt(i2, typedArray.getInt(i3, i4));
    }

    public static boolean e(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, boolean z) {
        return !r(xmlPullParser, str) ? z : typedArray.getBoolean(i2, z);
    }

    public static int f(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, int i3) {
        return !r(xmlPullParser, str) ? i3 : typedArray.getColor(i2, i3);
    }

    public static ColorStateList g(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int i2) {
        if (!r(xmlPullParser, str)) {
            return null;
        }
        TypedValue typedValue = new TypedValue();
        typedArray.getValue(i2, typedValue);
        int i3 = typedValue.type;
        if (i3 != 2) {
            return (i3 < 28 || i3 > 31) ? ColorStateListInflaterCompat.d(typedArray.getResources(), typedArray.getResourceId(i2, 0), theme) : h(typedValue);
        }
        throw new UnsupportedOperationException("Failed to resolve attribute at index " + i2 + ": " + typedValue);
    }

    private static ColorStateList h(TypedValue typedValue) {
        return ColorStateList.valueOf(typedValue.data);
    }

    public static ComplexColorCompat i(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int i2, int i3) {
        if (r(xmlPullParser, str)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(i2, typedValue);
            int i4 = typedValue.type;
            if (i4 >= 28 && i4 <= 31) {
                return ComplexColorCompat.b(typedValue.data);
            }
            ComplexColorCompat g2 = ComplexColorCompat.g(typedArray.getResources(), typedArray.getResourceId(i2, 0), theme);
            if (g2 != null) {
                return g2;
            }
        }
        return ComplexColorCompat.b(i3);
    }

    public static float j(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, float f2) {
        return !r(xmlPullParser, str) ? f2 : typedArray.getFloat(i2, f2);
    }

    public static int k(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, int i3) {
        return !r(xmlPullParser, str) ? i3 : typedArray.getInt(i2, i3);
    }

    public static int l(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, int i3) {
        return !r(xmlPullParser, str) ? i3 : typedArray.getResourceId(i2, i3);
    }

    public static String m(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2) {
        if (r(xmlPullParser, str)) {
            return typedArray.getString(i2);
        }
        return null;
    }

    public static int n(TypedArray typedArray, int i2, int i3, int i4) {
        return typedArray.getResourceId(i2, typedArray.getResourceId(i3, i4));
    }

    public static String o(TypedArray typedArray, int i2, int i3) {
        String string = typedArray.getString(i2);
        return string == null ? typedArray.getString(i3) : string;
    }

    public static CharSequence p(TypedArray typedArray, int i2, int i3) {
        CharSequence text = typedArray.getText(i2);
        return text == null ? typedArray.getText(i3) : text;
    }

    public static CharSequence[] q(TypedArray typedArray, int i2, int i3) {
        CharSequence[] textArray = typedArray.getTextArray(i2);
        return textArray == null ? typedArray.getTextArray(i3) : textArray;
    }

    public static boolean r(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str) != null;
    }

    public static TypedArray s(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        return theme == null ? resources.obtainAttributes(attributeSet, iArr) : theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }
}
