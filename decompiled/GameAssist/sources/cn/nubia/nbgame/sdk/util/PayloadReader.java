package cn.nubia.nbgame.sdk.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes.dex */
public final class PayloadReader {
    public static byte[] a(File file, int i2) {
        Map b2 = b(file);
        if (b2 == null) {
            NeoLog.l("PayloadReader", "getchannel dValues == null");
            return null;
        }
        NeoLog.f("getchannel PackageUtil.isException is:" + PackageUtil.f8324b);
        ByteBuffer byteBuffer = (ByteBuffer) b2.get(Integer.valueOf(i2));
        if (byteBuffer != null) {
            return c(byteBuffer);
        }
        NeoLog.l("PayloadReader", "getchannel idValues.get(id) byteBuffer is null");
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x008a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:? A[Catch: SignatureNotFoundException -> 0x0068, SYNTHETIC, TRY_LEAVE, TryCatch #2 {SignatureNotFoundException -> 0x0068, blocks: (B:54:0x007f, B:47:0x008a, B:51:0x0093, B:50:0x008e, B:56:0x0083, B:39:0x0064, B:35:0x0071, B:37:0x0075, B:41:0x006a), top: B:2:0x0008, inners: #1, #5, #6, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x007f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10, types: [java.lang.Object, java.util.Map] */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r6v9, types: [java.lang.StringBuilder] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.Map b(java.io.File r9) {
        /*
            java.lang.String r0 = "getAll getchannel IOException1"
            java.lang.String r1 = "getAll getchannel IOException"
            java.lang.String r2 = "PayloadReader"
            r3 = 1
            r4 = 0
            java.io.RandomAccessFile r5 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5e
            java.lang.String r6 = "r"
            r5.<init>(r9, r6)     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5e
            java.nio.channels.FileChannel r9 = r5.getChannel()     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L58
            cn.nubia.nbgame.sdk.util.Pair r6 = cn.nubia.nbgame.sdk.util.ApkUtil.b(r9)     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L51
            java.lang.Object r6 = r6.a()     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L51
            java.nio.ByteBuffer r6 = (java.nio.ByteBuffer) r6     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L51
            java.util.Map r4 = cn.nubia.nbgame.sdk.util.ApkUtil.f(r6)     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L51
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L51
            r6.<init>()     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L51
            java.lang.String r7 = "getchannel idValues is："
            r6.append(r7)     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L51
            r6.append(r4)     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L51
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L51
            cn.nubia.nbgame.sdk.util.NeoLog.l(r2, r6)     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L51
            if (r9 == 0) goto L40
            r9.close()     // Catch: java.io.IOException -> L3b cn.nubia.nbgame.sdk.util.SignatureNotFoundException -> L94
            goto L40
        L3b:
            cn.nubia.nbgame.sdk.util.NeoLog.l(r2, r1)     // Catch: cn.nubia.nbgame.sdk.util.SignatureNotFoundException -> L94
            cn.nubia.nbgame.sdk.util.PackageUtil.f8324b = r3     // Catch: cn.nubia.nbgame.sdk.util.SignatureNotFoundException -> L94
        L40:
            r5.close()     // Catch: java.io.IOException -> L45 cn.nubia.nbgame.sdk.util.SignatureNotFoundException -> L94
            goto L9b
        L45:
            cn.nubia.nbgame.sdk.util.NeoLog.l(r2, r0)     // Catch: cn.nubia.nbgame.sdk.util.SignatureNotFoundException -> L94
            cn.nubia.nbgame.sdk.util.PackageUtil.f8324b = r3     // Catch: cn.nubia.nbgame.sdk.util.SignatureNotFoundException -> L94
            goto L9b
        L4c:
            r6 = move-exception
            r8 = r4
            r4 = r9
            r9 = r8
            goto L7d
        L51:
            r8 = r4
            r4 = r9
            r9 = r8
            goto L60
        L55:
            r6 = move-exception
            r9 = r4
            goto L7d
        L58:
            r9 = r4
            goto L60
        L5a:
            r6 = move-exception
            r9 = r4
            r5 = r9
            goto L7d
        L5e:
            r9 = r4
            r5 = r9
        L60:
            cn.nubia.nbgame.sdk.util.PackageUtil.f8324b = r3     // Catch: java.lang.Throwable -> L7c
            if (r4 == 0) goto L6f
            r4.close()     // Catch: cn.nubia.nbgame.sdk.util.SignatureNotFoundException -> L68 java.io.IOException -> L6a
            goto L6f
        L68:
            r4 = r9
            goto L94
        L6a:
            cn.nubia.nbgame.sdk.util.NeoLog.l(r2, r1)     // Catch: cn.nubia.nbgame.sdk.util.SignatureNotFoundException -> L68
            cn.nubia.nbgame.sdk.util.PackageUtil.f8324b = r3     // Catch: cn.nubia.nbgame.sdk.util.SignatureNotFoundException -> L68
        L6f:
            if (r5 == 0) goto L7a
            r5.close()     // Catch: cn.nubia.nbgame.sdk.util.SignatureNotFoundException -> L68 java.io.IOException -> L75
            goto L7a
        L75:
            cn.nubia.nbgame.sdk.util.NeoLog.l(r2, r0)     // Catch: cn.nubia.nbgame.sdk.util.SignatureNotFoundException -> L68
            cn.nubia.nbgame.sdk.util.PackageUtil.f8324b = r3     // Catch: cn.nubia.nbgame.sdk.util.SignatureNotFoundException -> L68
        L7a:
            r4 = r9
            goto L9b
        L7c:
            r6 = move-exception
        L7d:
            if (r4 == 0) goto L88
            r4.close()     // Catch: cn.nubia.nbgame.sdk.util.SignatureNotFoundException -> L68 java.io.IOException -> L83
            goto L88
        L83:
            cn.nubia.nbgame.sdk.util.NeoLog.l(r2, r1)     // Catch: cn.nubia.nbgame.sdk.util.SignatureNotFoundException -> L68
            cn.nubia.nbgame.sdk.util.PackageUtil.f8324b = r3     // Catch: cn.nubia.nbgame.sdk.util.SignatureNotFoundException -> L68
        L88:
            if (r5 == 0) goto L93
            r5.close()     // Catch: cn.nubia.nbgame.sdk.util.SignatureNotFoundException -> L68 java.io.IOException -> L8e
            goto L93
        L8e:
            cn.nubia.nbgame.sdk.util.NeoLog.l(r2, r0)     // Catch: cn.nubia.nbgame.sdk.util.SignatureNotFoundException -> L68
            cn.nubia.nbgame.sdk.util.PackageUtil.f8324b = r3     // Catch: cn.nubia.nbgame.sdk.util.SignatureNotFoundException -> L68
        L93:
            throw r6     // Catch: cn.nubia.nbgame.sdk.util.SignatureNotFoundException -> L68
        L94:
            java.lang.String r9 = "getchannel get all SignatureNotFoundException  has error"
            cn.nubia.nbgame.sdk.util.NeoLog.f(r9)
            cn.nubia.nbgame.sdk.util.PackageUtil.f8324b = r3
        L9b:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.nbgame.sdk.util.PayloadReader.b(java.io.File):java.util.Map");
    }

    private static byte[] c(ByteBuffer byteBuffer) {
        byte[] array = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset();
        return Arrays.copyOfRange(array, byteBuffer.position() + arrayOffset, arrayOffset + byteBuffer.limit());
    }

    public static String d(File file, int i2) {
        byte[] a2 = a(file, i2);
        if (a2 == null) {
            NeoLog.f("getchannel bytes getString is null");
            return null;
        }
        try {
            return new String(a2, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
