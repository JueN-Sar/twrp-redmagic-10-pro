package com.zte.timeutil.utils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

/* loaded from: classes2.dex */
public class RegexResourceUtil {
    public static Pattern a(String str) {
        InputStream resourceAsStream = RegexResourceUtil.class.getClassLoader().getResourceAsStream(str);
        try {
            Pattern compile = Pattern.compile(((Pattern) new ObjectInputStream(new BufferedInputStream(new GZIPInputStream(resourceAsStream))).readObject()).pattern());
            if (resourceAsStream != null) {
                resourceAsStream.close();
            }
            return compile;
        } catch (Throwable th) {
            if (resourceAsStream != null) {
                try {
                    resourceAsStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
