package androidx.constraintlayout.core.dsl;

import java.util.Arrays;

/* loaded from: classes.dex */
public class Keys {
    protected void a(StringBuilder sb, String str, float f2) {
        if (Float.isNaN(f2)) {
            return;
        }
        sb.append(str);
        sb.append(":");
        sb.append(f2);
        sb.append(",\n");
    }

    protected void b(StringBuilder sb, String str, String str2) {
        if (str2 != null) {
            sb.append(str);
            sb.append(":'");
            sb.append(str2);
            sb.append("',\n");
        }
    }

    protected void c(StringBuilder sb, String str, float[] fArr) {
        if (fArr != null) {
            sb.append(str);
            sb.append("percentWidth:");
            sb.append(Arrays.toString(fArr));
            sb.append(",\n");
        }
    }

    protected void d(StringBuilder sb, String str, String[] strArr) {
        if (strArr != null) {
            sb.append(str);
            sb.append(":");
            sb.append(e(strArr));
            sb.append(",\n");
        }
    }

    protected String e(String[] strArr) {
        StringBuilder sb = new StringBuilder("[");
        int i2 = 0;
        while (i2 < strArr.length) {
            sb.append(i2 == 0 ? "'" : ",'");
            sb.append(strArr[i2]);
            sb.append("'");
            i2++;
        }
        sb.append("]");
        return sb.toString();
    }
}
