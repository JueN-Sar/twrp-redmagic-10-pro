package androidx.profileinstaller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

/* loaded from: classes.dex */
class Encoding {
    static int a(int i2) {
        return ((i2 + 7) & (-8)) / 8;
    }

    static byte[] b(byte[] bArr) {
        Deflater deflater = new Deflater(1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
            try {
                deflaterOutputStream.write(bArr);
                deflaterOutputStream.close();
                deflater.end();
                return byteArrayOutputStream.toByteArray();
            } finally {
            }
        } catch (Throwable th) {
            deflater.end();
            throw th;
        }
    }

    static RuntimeException c(String str) {
        return new IllegalStateException(str);
    }

    static byte[] d(InputStream inputStream, int i2) {
        byte[] bArr = new byte[i2];
        int i3 = 0;
        while (i3 < i2) {
            int read = inputStream.read(bArr, i3, i2 - i3);
            if (read < 0) {
                throw c("Not enough bytes to read: " + i2);
            }
            i3 += read;
        }
        return bArr;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x005b, code lost:
    
        if (r0.finished() == false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0060, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0067, code lost:
    
        throw c("Inflater did not finish");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static byte[] e(java.io.InputStream r8, int r9, int r10) {
        /*
            java.util.zip.Inflater r0 = new java.util.zip.Inflater
            r0.<init>()
            byte[] r1 = new byte[r10]     // Catch: java.lang.Throwable -> L2e
            r2 = 2048(0x800, float:2.87E-42)
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L2e
            r3 = 0
            r4 = r3
            r5 = r4
        Le:
            boolean r6 = r0.finished()     // Catch: java.lang.Throwable -> L2e
            if (r6 != 0) goto L55
            boolean r6 = r0.needsDictionary()     // Catch: java.lang.Throwable -> L2e
            if (r6 != 0) goto L55
            if (r4 >= r9) goto L55
            int r6 = r8.read(r2)     // Catch: java.lang.Throwable -> L2e
            if (r6 < 0) goto L3a
            r0.setInput(r2, r3, r6)     // Catch: java.lang.Throwable -> L2e
            int r7 = r10 - r5
            int r7 = r0.inflate(r1, r5, r7)     // Catch: java.lang.Throwable -> L2e java.util.zip.DataFormatException -> L30
            int r5 = r5 + r7
            int r4 = r4 + r6
            goto Le
        L2e:
            r8 = move-exception
            goto L86
        L30:
            r8 = move-exception
            java.lang.String r8 = r8.getMessage()     // Catch: java.lang.Throwable -> L2e
            java.lang.RuntimeException r8 = c(r8)     // Catch: java.lang.Throwable -> L2e
            throw r8     // Catch: java.lang.Throwable -> L2e
        L3a:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2e
            r8.<init>()     // Catch: java.lang.Throwable -> L2e
            java.lang.String r10 = "Invalid zip data. Stream ended after $totalBytesRead bytes. Expected "
            r8.append(r10)     // Catch: java.lang.Throwable -> L2e
            r8.append(r9)     // Catch: java.lang.Throwable -> L2e
            java.lang.String r9 = " bytes"
            r8.append(r9)     // Catch: java.lang.Throwable -> L2e
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L2e
            java.lang.RuntimeException r8 = c(r8)     // Catch: java.lang.Throwable -> L2e
            throw r8     // Catch: java.lang.Throwable -> L2e
        L55:
            if (r4 != r9) goto L68
            boolean r8 = r0.finished()     // Catch: java.lang.Throwable -> L2e
            if (r8 == 0) goto L61
            r0.end()
            return r1
        L61:
            java.lang.String r8 = "Inflater did not finish"
            java.lang.RuntimeException r8 = c(r8)     // Catch: java.lang.Throwable -> L2e
            throw r8     // Catch: java.lang.Throwable -> L2e
        L68:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2e
            r8.<init>()     // Catch: java.lang.Throwable -> L2e
            java.lang.String r10 = "Didn't read enough bytes during decompression. expected="
            r8.append(r10)     // Catch: java.lang.Throwable -> L2e
            r8.append(r9)     // Catch: java.lang.Throwable -> L2e
            java.lang.String r9 = " actual="
            r8.append(r9)     // Catch: java.lang.Throwable -> L2e
            r8.append(r4)     // Catch: java.lang.Throwable -> L2e
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L2e
            java.lang.RuntimeException r8 = c(r8)     // Catch: java.lang.Throwable -> L2e
            throw r8     // Catch: java.lang.Throwable -> L2e
        L86:
            r0.end()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.profileinstaller.Encoding.e(java.io.InputStream, int, int):byte[]");
    }

    static String f(InputStream inputStream, int i2) {
        return new String(d(inputStream, i2), StandardCharsets.UTF_8);
    }

    static long g(InputStream inputStream, int i2) {
        byte[] d2 = d(inputStream, i2);
        long j2 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            j2 += (d2[i3] & 255) << (i3 * 8);
        }
        return j2;
    }

    static int h(InputStream inputStream) {
        return (int) g(inputStream, 2);
    }

    static long i(InputStream inputStream) {
        return g(inputStream, 4);
    }

    static int j(InputStream inputStream) {
        return (int) g(inputStream, 1);
    }

    static int k(String str) {
        return str.getBytes(StandardCharsets.UTF_8).length;
    }

    static void l(InputStream inputStream, OutputStream outputStream, FileLock fileLock) {
        if (fileLock == null || !fileLock.isValid()) {
            throw new IOException("Unable to acquire a lock on the underlying file channel.");
        }
        byte[] bArr = new byte[512];
        while (true) {
            int read = inputStream.read(bArr);
            if (read <= 0) {
                return;
            } else {
                outputStream.write(bArr, 0, read);
            }
        }
    }

    static void m(OutputStream outputStream, byte[] bArr) {
        q(outputStream, bArr.length);
        byte[] b2 = b(bArr);
        q(outputStream, b2.length);
        outputStream.write(b2);
    }

    static void n(OutputStream outputStream, String str) {
        outputStream.write(str.getBytes(StandardCharsets.UTF_8));
    }

    static void o(OutputStream outputStream, long j2, int i2) {
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = (byte) ((j2 >> (i3 * 8)) & 255);
        }
        outputStream.write(bArr);
    }

    static void p(OutputStream outputStream, int i2) {
        o(outputStream, i2, 2);
    }

    static void q(OutputStream outputStream, long j2) {
        o(outputStream, j2, 4);
    }

    static void r(OutputStream outputStream, int i2) {
        o(outputStream, i2, 1);
    }
}
