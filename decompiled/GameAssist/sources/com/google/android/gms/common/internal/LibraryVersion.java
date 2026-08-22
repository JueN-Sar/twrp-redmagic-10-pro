package com.google.android.gms.common.internal;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.ConcurrentHashMap;

@KeepForSdk
@Deprecated
/* loaded from: classes.dex */
public class LibraryVersion {

    /* renamed from: b, reason: collision with root package name */
    private static final GmsLogger f11008b = new GmsLogger("LibraryVersion", "");

    /* renamed from: c, reason: collision with root package name */
    private static final LibraryVersion f11009c = new LibraryVersion();

    /* renamed from: a, reason: collision with root package name */
    private final ConcurrentHashMap f11010a = new ConcurrentHashMap();

    @VisibleForTesting
    protected LibraryVersion() {
    }

    public static LibraryVersion a() {
        return f11009c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0095  */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String b(java.lang.String r9) {
        /*
            r8 = this;
            java.lang.String r0 = "Failed to get app version for libraryName: "
            java.lang.String r1 = "LibraryVersion"
            java.lang.String r2 = "Please provide a valid libraryName"
            com.google.android.gms.common.internal.Preconditions.g(r9, r2)
            java.util.concurrent.ConcurrentHashMap r2 = r8.f11010a
            boolean r2 = r2.containsKey(r9)
            if (r2 == 0) goto L1a
            java.util.concurrent.ConcurrentHashMap r8 = r8.f11010a
            java.lang.Object r8 = r8.get(r9)
            java.lang.String r8 = (java.lang.String) r8
            return r8
        L1a:
            java.util.Properties r2 = new java.util.Properties
            r2.<init>()
            r3 = 0
            java.lang.String r4 = "/%s.properties"
            java.lang.Object[] r5 = new java.lang.Object[]{r9}     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L75
            java.lang.String r4 = java.lang.String.format(r4, r5)     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L75
            java.lang.Class<com.google.android.gms.common.internal.LibraryVersion> r5 = com.google.android.gms.common.internal.LibraryVersion.class
            java.io.InputStream r4 = r5.getResourceAsStream(r4)     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L75
            if (r4 == 0) goto L5c
            r2.load(r4)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            java.lang.String r5 = "version"
            java.lang.String r3 = r2.getProperty(r5, r3)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            com.google.android.gms.common.internal.GmsLogger r2 = com.google.android.gms.common.internal.LibraryVersion.f11008b     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r5.<init>()     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r5.append(r9)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            java.lang.String r6 = " version is "
            r5.append(r6)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r5.append(r3)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r2.f(r1, r5)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            goto L8e
        L55:
            r8 = move-exception
            goto L71
        L57:
            r2 = move-exception
            r7 = r4
            r4 = r3
            r3 = r7
            goto L77
        L5c:
            com.google.android.gms.common.internal.GmsLogger r2 = com.google.android.gms.common.internal.LibraryVersion.f11008b     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r5.<init>()     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r5.append(r0)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r5.append(r9)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r2.g(r1, r5)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            goto L8e
        L71:
            r3 = r4
            goto La4
        L73:
            r8 = move-exception
            goto La4
        L75:
            r2 = move-exception
            r4 = r3
        L77:
            com.google.android.gms.common.internal.GmsLogger r5 = com.google.android.gms.common.internal.LibraryVersion.f11008b     // Catch: java.lang.Throwable -> L73
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L73
            r6.<init>()     // Catch: java.lang.Throwable -> L73
            r6.append(r0)     // Catch: java.lang.Throwable -> L73
            r6.append(r9)     // Catch: java.lang.Throwable -> L73
            java.lang.String r0 = r6.toString()     // Catch: java.lang.Throwable -> L73
            r5.d(r1, r0, r2)     // Catch: java.lang.Throwable -> L73
            r7 = r4
            r4 = r3
            r3 = r7
        L8e:
            if (r4 == 0) goto L93
            com.google.android.gms.common.util.IOUtils.a(r4)
        L93:
            if (r3 != 0) goto L9e
            com.google.android.gms.common.internal.GmsLogger r0 = com.google.android.gms.common.internal.LibraryVersion.f11008b
            java.lang.String r2 = ".properties file is dropped during release process. Failure to read app version is expected during Google internal testing where locally-built libraries are used"
            r0.b(r1, r2)
            java.lang.String r3 = "UNKNOWN"
        L9e:
            java.util.concurrent.ConcurrentHashMap r8 = r8.f11010a
            r8.put(r9, r3)
            return r3
        La4:
            if (r3 == 0) goto La9
            com.google.android.gms.common.util.IOUtils.a(r3)
        La9:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.LibraryVersion.b(java.lang.String):java.lang.String");
    }
}
