package com.zte.mifavor.alphavideo;

import android.opengl.GLES20;
import android.util.Log;

/* loaded from: classes2.dex */
public class OpenGlUtils {

    /* renamed from: a, reason: collision with root package name */
    public static final float[] f17081a = {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f};

    /* renamed from: b, reason: collision with root package name */
    public static final float[] f17082b = {-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};

    private static void a(String str) {
        Log.e("OpenGlUtils", "--" + str);
    }

    private static void b(String str) {
        Log.i("OpenGlUtils", "--" + str);
    }

    public static void c(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError == 0) {
            return;
        }
        a(str + ": glError " + glGetError);
        throw new RuntimeException(str + ": glError " + glGetError);
    }

    public static int d() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        GLES20.glBindTexture(36197, iArr[0]);
        GLES20.glTexParameterf(36197, 10240, 9729.0f);
        GLES20.glTexParameterf(36197, 10241, 9729.0f);
        GLES20.glTexParameterf(36197, 10242, 33071.0f);
        GLES20.glTexParameterf(36197, 10243, 33071.0f);
        return iArr[0];
    }

    public static int e(String str, String str2) {
        int[] iArr = new int[1];
        int f2 = f(str, 35633);
        if (f2 == 0) {
            b("Load Program Vertex Shader Failed");
            return 0;
        }
        int f3 = f(str2, 35632);
        if (f3 == 0) {
            b("Load Program Fragment Shader Failed");
            return 0;
        }
        int glCreateProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(glCreateProgram, f2);
        GLES20.glAttachShader(glCreateProgram, f3);
        GLES20.glLinkProgram(glCreateProgram);
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] <= 0) {
            b("Load Program Linking Failed");
            GLES20.glDeleteProgram(glCreateProgram);
            return 0;
        }
        GLES20.glDeleteShader(f2);
        GLES20.glDeleteShader(f3);
        return glCreateProgram;
    }

    public static int f(String str, int i2) {
        int[] iArr = new int[1];
        int glCreateShader = GLES20.glCreateShader(i2);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        b("Load Shader Failed Compilation\n" + GLES20.glGetShaderInfoLog(glCreateShader));
        return 0;
    }
}
