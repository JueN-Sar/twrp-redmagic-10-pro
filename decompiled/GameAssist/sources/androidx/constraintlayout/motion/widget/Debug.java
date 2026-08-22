package androidx.constraintlayout.motion.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import java.nio.CharBuffer;

@SuppressLint({"LogConditional"})
/* loaded from: classes.dex */
public class Debug {
    public static String a() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        return ".(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName() + "()";
    }

    public static String b() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        return ".(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")";
    }

    public static String c(Context context, int i2) {
        if (i2 == -1) {
            return "UNKNOWN";
        }
        try {
            return context.getResources().getResourceEntryName(i2);
        } catch (Exception unused) {
            return "?" + i2;
        }
    }

    public static String d(View view) {
        try {
            return view.getContext().getResources().getResourceEntryName(view.getId());
        } catch (Exception unused) {
            return "UNKNOWN";
        }
    }

    public static String e(MotionLayout motionLayout, int i2) {
        return f(motionLayout, i2, -1);
    }

    public static String f(MotionLayout motionLayout, int i2, int i3) {
        int length;
        if (i2 == -1) {
            return "UNDEFINED";
        }
        String resourceEntryName = motionLayout.getContext().getResources().getResourceEntryName(i2);
        if (i3 == -1) {
            return resourceEntryName;
        }
        if (resourceEntryName.length() > i3) {
            resourceEntryName = resourceEntryName.replaceAll("([^_])[aeiou]+", "$1");
        }
        if (resourceEntryName.length() <= i3 || (length = resourceEntryName.replaceAll("[^_]", "").length()) <= 0) {
            return resourceEntryName;
        }
        return resourceEntryName.replaceAll(CharBuffer.allocate((resourceEntryName.length() - i3) / length).toString().replace((char) 0, '.') + "_", "_");
    }

    public static void g(String str, String str2, int i2) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int min = Math.min(i2, stackTrace.length - 1);
        String str3 = " ";
        for (int i3 = 1; i3 <= min; i3++) {
            StackTraceElement stackTraceElement = stackTrace[i3];
            str3 = str3 + " ";
            Log.v(str, str2 + str3 + (".(" + stackTrace[i3].getFileName() + ":" + stackTrace[i3].getLineNumber() + ") " + stackTrace[i3].getMethodName()) + str3);
        }
    }
}
