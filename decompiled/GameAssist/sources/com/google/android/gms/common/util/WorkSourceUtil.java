package com.google.android.gms.common.util;

import android.os.Process;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.lang.reflect.Method;

@KeepForSdk
/* loaded from: classes.dex */
public class WorkSourceUtil {

    /* renamed from: a, reason: collision with root package name */
    private static final int f11268a = Process.myUid();

    /* renamed from: b, reason: collision with root package name */
    private static final Method f11269b;

    /* renamed from: c, reason: collision with root package name */
    private static final Method f11270c;

    /* renamed from: d, reason: collision with root package name */
    private static final Method f11271d;

    /* renamed from: e, reason: collision with root package name */
    private static final Method f11272e;

    /* renamed from: f, reason: collision with root package name */
    private static final Method f11273f;

    /* renamed from: g, reason: collision with root package name */
    private static final Method f11274g;

    /* renamed from: h, reason: collision with root package name */
    private static final Method f11275h;

    /* renamed from: i, reason: collision with root package name */
    private static final Method f11276i;

    /* renamed from: j, reason: collision with root package name */
    private static Boolean f11277j;

    /* JADX WARN: Can't wrap try/catch for region: R(26:0|1|(2:2|3)|4|(22:55|56|7|8|9|10|11|12|13|(13:47|48|16|(10:42|43|19|(7:37|38|22|(7:28|29|30|31|32|25|26)|24|25|26)|21|22|(0)|24|25|26)|18|19|(0)|21|22|(0)|24|25|26)|15|16|(0)|18|19|(0)|21|22|(0)|24|25|26)|6|7|8|9|10|11|12|13|(0)|15|16|(0)|18|19|(0)|21|22|(0)|24|25|26) */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0046, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0036, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:28:0x009f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0067 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static {
        /*
            java.lang.String r0 = "add"
            java.lang.Class<android.os.WorkSource> r1 = android.os.WorkSource.class
            int r2 = android.os.Process.myUid()
            com.google.android.gms.common.util.WorkSourceUtil.f11268a = r2
            r2 = 0
            java.lang.Class r3 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L16
            java.lang.Class[] r3 = new java.lang.Class[]{r3}     // Catch: java.lang.Exception -> L16
            java.lang.reflect.Method r3 = r1.getMethod(r0, r3)     // Catch: java.lang.Exception -> L16
            goto L17
        L16:
            r3 = r2
        L17:
            com.google.android.gms.common.util.WorkSourceUtil.f11269b = r3
            boolean r3 = com.google.android.gms.common.util.PlatformVersion.b()
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            if (r3 == 0) goto L2c
            java.lang.Class r3 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L2c
            java.lang.Class[] r3 = new java.lang.Class[]{r3, r4}     // Catch: java.lang.Exception -> L2c
            java.lang.reflect.Method r0 = r1.getMethod(r0, r3)     // Catch: java.lang.Exception -> L2c
            goto L2d
        L2c:
            r0 = r2
        L2d:
            com.google.android.gms.common.util.WorkSourceUtil.f11270c = r0
            java.lang.String r0 = "size"
            java.lang.reflect.Method r0 = r1.getMethod(r0, r2)     // Catch: java.lang.Exception -> L36
            goto L37
        L36:
            r0 = r2
        L37:
            com.google.android.gms.common.util.WorkSourceUtil.f11271d = r0
            java.lang.String r0 = "get"
            java.lang.Class r3 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L46
            java.lang.Class[] r3 = new java.lang.Class[]{r3}     // Catch: java.lang.Exception -> L46
            java.lang.reflect.Method r0 = r1.getMethod(r0, r3)     // Catch: java.lang.Exception -> L46
            goto L47
        L46:
            r0 = r2
        L47:
            com.google.android.gms.common.util.WorkSourceUtil.f11272e = r0
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.b()
            if (r0 == 0) goto L5c
            java.lang.String r0 = "getName"
            java.lang.Class r3 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L5c
            java.lang.Class[] r3 = new java.lang.Class[]{r3}     // Catch: java.lang.Exception -> L5c
            java.lang.reflect.Method r0 = r1.getMethod(r0, r3)     // Catch: java.lang.Exception -> L5c
            goto L5d
        L5c:
            r0 = r2
        L5d:
            com.google.android.gms.common.util.WorkSourceUtil.f11273f = r0
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.g()
            java.lang.String r3 = "WorkSourceUtil"
            if (r0 == 0) goto L74
            java.lang.String r0 = "createWorkChain"
            java.lang.reflect.Method r0 = r1.getMethod(r0, r2)     // Catch: java.lang.Exception -> L6e
            goto L75
        L6e:
            r0 = move-exception
            java.lang.String r5 = "Missing WorkChain API createWorkChain"
            android.util.Log.w(r3, r5, r0)
        L74:
            r0 = r2
        L75:
            com.google.android.gms.common.util.WorkSourceUtil.f11274g = r0
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.g()
            if (r0 == 0) goto L96
            java.lang.String r0 = "android.os.WorkSource$WorkChain"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch: java.lang.Exception -> L90
            java.lang.String r5 = "addNode"
            java.lang.Class r6 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L90
            java.lang.Class[] r4 = new java.lang.Class[]{r6, r4}     // Catch: java.lang.Exception -> L90
            java.lang.reflect.Method r0 = r0.getMethod(r5, r4)     // Catch: java.lang.Exception -> L90
            goto L97
        L90:
            r0 = move-exception
            java.lang.String r4 = "Missing WorkChain class"
            android.util.Log.w(r3, r4, r0)
        L96:
            r0 = r2
        L97:
            com.google.android.gms.common.util.WorkSourceUtil.f11275h = r0
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.g()
            if (r0 == 0) goto Laa
            java.lang.String r0 = "isEmpty"
            java.lang.reflect.Method r0 = r1.getMethod(r0, r2)     // Catch: java.lang.Exception -> Laa
            r1 = 1
            r0.setAccessible(r1)     // Catch: java.lang.Exception -> Lab
            goto Lab
        Laa:
            r0 = r2
        Lab:
            com.google.android.gms.common.util.WorkSourceUtil.f11276i = r0
            com.google.android.gms.common.util.WorkSourceUtil.f11277j = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.util.WorkSourceUtil.<clinit>():void");
    }
}
