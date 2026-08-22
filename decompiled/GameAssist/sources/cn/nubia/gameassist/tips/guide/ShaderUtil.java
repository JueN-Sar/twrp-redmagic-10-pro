package cn.nubia.gameassist.tips.guide;

import android.opengl.GLES30;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class ShaderUtil {
    public static void a(String str) {
        int glGetError = GLES30.glGetError();
        if (glGetError == 0) {
            return;
        }
        GaLog.b("GELE20 ERROR", str + " : glError " + glGetError);
        throw new RuntimeException(str + ": glError " + glGetError);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x005c, code lost:
    
        if (r3 != false) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int b(java.lang.String r6, java.lang.String r7) {
        /*
            r0 = 35633(0x8b31, float:4.9932E-41)
            int r6 = c(r0, r6)
            r0 = 0
            if (r6 != 0) goto Lb
            return r0
        Lb:
            r1 = 35632(0x8b30, float:4.9931E-41)
            int r7 = c(r1, r7)
            if (r7 != 0) goto L18
            android.opengl.GLES30.glDeleteShader(r6)
            return r0
        L18:
            int r1 = android.opengl.GLES30.glCreateProgram()
            r2 = 1
            if (r1 != 0) goto L21
            r3 = r2
            goto L22
        L21:
            r3 = r0
        L22:
            if (r3 != 0) goto L5c
            android.opengl.GLES30.glAttachShader(r1, r6)
            java.lang.String r4 = "glAttachShader"
            a(r4)
            android.opengl.GLES30.glAttachShader(r1, r7)
            a(r4)
            android.opengl.GLES30.glLinkProgram(r1)
            int[] r4 = new int[r2]
            r5 = 35714(0x8b82, float:5.0046E-41)
            android.opengl.GLES30.glGetProgramiv(r1, r5, r4, r0)
            r4 = r4[r0]
            if (r4 == r2) goto L5c
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Cound not link program:\n"
            r2.append(r3)
            java.lang.String r3 = android.opengl.GLES30.glGetProgramInfoLog(r1)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "GELE20 ERROR"
            com.zte.gameassist.utils.GaLog.b(r3, r2)
            goto L5e
        L5c:
            if (r3 == 0) goto L6a
        L5e:
            android.opengl.GLES30.glDeleteShader(r6)
            android.opengl.GLES30.glDeleteShader(r7)
            if (r1 == 0) goto L6a
            android.opengl.GLES30.glDeleteProgram(r1)
            goto L6b
        L6a:
            r0 = r1
        L6b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.tips.guide.ShaderUtil.b(java.lang.String, java.lang.String):int");
    }

    public static int c(int i2, String str) {
        int glCreateShader = GLES30.glCreateShader(i2);
        if (glCreateShader == 0) {
            return glCreateShader;
        }
        GLES30.glShaderSource(glCreateShader, str);
        GLES30.glCompileShader(glCreateShader);
        int[] iArr = new int[1];
        GLES30.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        GaLog.b("GELE20 ERROR", "Cound not compile sharder " + i2 + " version=" + GLES30.glGetString(7938) + "\n" + GLES30.glGetShaderInfoLog(glCreateShader));
        GLES30.glDeleteShader(glCreateShader);
        return 0;
    }
}
