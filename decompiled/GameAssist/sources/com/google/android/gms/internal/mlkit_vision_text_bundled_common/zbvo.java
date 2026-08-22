package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes.dex */
final class zbvo {

    /* renamed from: a, reason: collision with root package name */
    private static final char[] f13003a;

    static {
        char[] cArr = new char[80];
        f13003a = cArr;
        Arrays.fill(cArr, ' ');
    }

    static String a(zbvm zbvmVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        d(zbvmVar, sb, 0);
        return sb.toString();
    }

    static void b(StringBuilder sb, int i2, String str, Object obj) {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                b(sb, i2, str, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                b(sb, i2, str, (Map.Entry) it2.next());
            }
            return;
        }
        sb.append('\n');
        c(i2, sb);
        if (!str.isEmpty()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(Character.toLowerCase(str.charAt(0)));
            for (int i3 = 1; i3 < str.length(); i3++) {
                char charAt = str.charAt(i3);
                if (Character.isUpperCase(charAt)) {
                    sb2.append("_");
                }
                sb2.append(Character.toLowerCase(charAt));
            }
            str = sb2.toString();
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"");
            sb.append(zbwj.a(new zbtb(((String) obj).getBytes(zbuo.f12984a))));
            sb.append('\"');
            return;
        }
        if (obj instanceof zbtc) {
            sb.append(": \"");
            sb.append(zbwj.a((zbtc) obj));
            sb.append('\"');
            return;
        }
        if (obj instanceof zbuf) {
            sb.append(" {");
            d((zbuf) obj, sb, i2 + 2);
            sb.append("\n");
            c(i2, sb);
            sb.append("}");
            return;
        }
        if (!(obj instanceof Map.Entry)) {
            sb.append(": ");
            sb.append(obj);
            return;
        }
        int i4 = i2 + 2;
        sb.append(" {");
        Map.Entry entry = (Map.Entry) obj;
        b(sb, i4, "key", entry.getKey());
        b(sb, i4, "value", entry.getValue());
        sb.append("\n");
        c(i2, sb);
        sb.append("}");
    }

    private static void c(int i2, StringBuilder sb) {
        while (i2 > 0) {
            int i3 = 80;
            if (i2 <= 80) {
                i3 = i2;
            }
            sb.append(f13003a, 0, i3);
            i2 -= i3;
        }
    }

    private static void d(zbvm zbvmVar, StringBuilder sb, int i2) {
        int i3;
        boolean equals;
        Method method;
        Method method2;
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        TreeMap treeMap = new TreeMap();
        Method[] declaredMethods = zbvmVar.getClass().getDeclaredMethods();
        int length = declaredMethods.length;
        int i4 = 0;
        while (true) {
            i3 = 3;
            if (i4 >= length) {
                break;
            }
            Method method3 = declaredMethods[i4];
            if (!Modifier.isStatic(method3.getModifiers()) && method3.getName().length() >= 3) {
                if (method3.getName().startsWith("set")) {
                    hashSet.add(method3.getName());
                } else if (Modifier.isPublic(method3.getModifiers()) && method3.getParameterTypes().length == 0) {
                    if (method3.getName().startsWith("has")) {
                        hashMap.put(method3.getName(), method3);
                    } else if (method3.getName().startsWith("get")) {
                        treeMap.put(method3.getName(), method3);
                    }
                }
            }
            i4++;
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            String substring = ((String) entry.getKey()).substring(i3);
            if (substring.endsWith("List") && !substring.endsWith("OrBuilderList") && !substring.equals("List") && (method2 = (Method) entry.getValue()) != null && method2.getReturnType().equals(List.class)) {
                b(sb, i2, substring.substring(0, substring.length() - 4), zbuf.D(method2, zbvmVar, new Object[0]));
            } else if (substring.endsWith("Map") && !substring.equals("Map") && (method = (Method) entry.getValue()) != null && method.getReturnType().equals(Map.class) && !method.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method.getModifiers())) {
                b(sb, i2, substring.substring(0, substring.length() - 3), zbuf.D(method, zbvmVar, new Object[0]));
            } else if (hashSet.contains("set".concat(substring)) && (!substring.endsWith("Bytes") || !treeMap.containsKey("get".concat(String.valueOf(substring.substring(0, substring.length() - 5)))))) {
                Method method4 = (Method) entry.getValue();
                Method method5 = (Method) hashMap.get("has".concat(substring));
                if (method4 != null) {
                    Object D = zbuf.D(method4, zbvmVar, new Object[0]);
                    if (method5 != null) {
                        if (!((Boolean) zbuf.D(method5, zbvmVar, new Object[0])).booleanValue()) {
                        }
                        b(sb, i2, substring, D);
                    } else if (D instanceof Boolean) {
                        if (!((Boolean) D).booleanValue()) {
                        }
                        b(sb, i2, substring, D);
                    } else if (D instanceof Integer) {
                        if (((Integer) D).intValue() == 0) {
                        }
                        b(sb, i2, substring, D);
                    } else if (D instanceof Float) {
                        if (Float.floatToRawIntBits(((Float) D).floatValue()) == 0) {
                        }
                        b(sb, i2, substring, D);
                    } else if (D instanceof Double) {
                        if (Double.doubleToRawLongBits(((Double) D).doubleValue()) == 0) {
                        }
                        b(sb, i2, substring, D);
                    } else {
                        if (D instanceof String) {
                            equals = D.equals("");
                        } else if (D instanceof zbtc) {
                            equals = D.equals(zbtc.zbb);
                        } else if (D instanceof zbvm) {
                            if (D == ((zbvm) D).f()) {
                            }
                            b(sb, i2, substring, D);
                        } else {
                            if ((D instanceof Enum) && ((Enum) D).ordinal() == 0) {
                            }
                            b(sb, i2, substring, D);
                        }
                        if (equals) {
                        }
                        b(sb, i2, substring, D);
                    }
                }
            }
            i3 = 3;
        }
        if (zbvmVar instanceof zbub) {
            Iterator g2 = ((zbub) zbvmVar).zbb.g();
            while (g2.hasNext()) {
                Map.Entry entry2 = (Map.Entry) g2.next();
                b(sb, i2, "[32149011]", entry2.getValue());
            }
        }
        zbwm zbwmVar = ((zbuf) zbvmVar).zbc;
        if (zbwmVar != null) {
            zbwmVar.i(sb, i2);
        }
    }
}
