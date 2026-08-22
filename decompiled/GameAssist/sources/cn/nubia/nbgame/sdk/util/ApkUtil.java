package cn.nubia.nbgame.sdk.util;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes.dex */
final class ApkUtil {
    private static void a(ByteBuffer byteBuffer) {
        if (byteBuffer.order() != ByteOrder.LITTLE_ENDIAN) {
            throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
        }
    }

    public static Pair b(FileChannel fileChannel) {
        return c(fileChannel, d(fileChannel));
    }

    public static Pair c(FileChannel fileChannel, long j2) {
        NeoLog.l("TAG", "getchannel findApkSigningBlock start..");
        if (j2 < 32) {
            PackageUtil.f8324b = true;
            NeoLog.l("TAG", "getchannel APK too small for APK Signing Block. ZIP Central Directory offset:");
            throw new SignatureNotFoundException("APK too small for APK Signing Block. ZIP Central Directory offset: " + j2);
        }
        fileChannel.position(j2 - 24);
        ByteBuffer allocate = ByteBuffer.allocate(24);
        fileChannel.read(allocate);
        ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
        allocate.order(byteOrder);
        if (allocate.getLong(8) != 2334950737559900225L || allocate.getLong(16) != 3617552046287187010L) {
            PackageUtil.f8324b = true;
            NeoLog.l("TAG", "getchannel No APK Signing Block before ZIP Central Directory");
            throw new SignatureNotFoundException("No APK Signing Block before ZIP Central Directory");
        }
        long j3 = allocate.getLong(0);
        if (j3 < allocate.capacity() || j3 > 2147483639) {
            PackageUtil.f8324b = true;
            NeoLog.l("TAG", "getchannel APK Signing Block size out of range: " + j3);
            throw new SignatureNotFoundException("APK Signing Block size out of range: " + j3);
        }
        int i2 = (int) (8 + j3);
        long j4 = j2 - i2;
        if (j4 < 0) {
            PackageUtil.f8324b = true;
            NeoLog.l("TAG", "getchannel APK Signing Block size out of range: " + j3);
            throw new SignatureNotFoundException("APK Signing Block offset out of range: " + j4);
        }
        fileChannel.position(j4);
        ByteBuffer allocate2 = ByteBuffer.allocate(i2);
        fileChannel.read(allocate2);
        allocate2.order(byteOrder);
        long j5 = allocate2.getLong(0);
        if (j5 == j3) {
            NeoLog.f("getchannel findApkSigningBlock end..");
            return Pair.b(allocate2, Long.valueOf(j4));
        }
        PackageUtil.f8324b = true;
        NeoLog.l("TAG", "getchannel APK Signing Block sizes in header and footer do not match: " + j5 + " vs " + j3);
        throw new SignatureNotFoundException("APK Signing Block sizes in header and footer do not match: " + j5 + " vs " + j3);
    }

    public static long d(FileChannel fileChannel) {
        return e(fileChannel, h(fileChannel));
    }

    public static long e(FileChannel fileChannel, long j2) {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        fileChannel.position((fileChannel.size() - j2) - 6);
        fileChannel.read(allocate);
        return allocate.getInt(0);
    }

    public static Map f(ByteBuffer byteBuffer) {
        a(byteBuffer);
        NeoLog.l("TAG", "getchannel findIdValues start..");
        ByteBuffer i2 = i(byteBuffer, 8, byteBuffer.capacity() - 24);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i3 = 0;
        while (i2.hasRemaining()) {
            i3++;
            if (i2.remaining() < 8) {
                PackageUtil.f8324b = true;
                NeoLog.l("TAG", "getchannel Insufficient data to read size of APK Signing Block entry #" + i3);
                throw new SignatureNotFoundException("Insufficient data to read size of APK Signing Block entry #" + i3);
            }
            long j2 = i2.getLong();
            if (j2 < 4 || j2 > 2147483647L) {
                PackageUtil.f8324b = true;
                NeoLog.l("TAG", "getchannel APK Signing Block entry #" + i3 + " size out of range: " + j2);
                throw new SignatureNotFoundException("APK Signing Block entry #" + i3 + " size out of range: " + j2);
            }
            int i4 = (int) j2;
            int position = i2.position() + i4;
            if (i4 > i2.remaining()) {
                PackageUtil.f8324b = true;
                NeoLog.l("TAG", "getchannel APK Signing Block entry #" + i3 + " size out of range: " + i4 + ", available: " + i2.remaining());
                throw new SignatureNotFoundException("APK Signing Block entry #" + i3 + " size out of range: " + i4 + ", available: " + i2.remaining());
            }
            linkedHashMap.put(Integer.valueOf(i2.getInt()), g(i2, i4 - 4));
            i2.position(position);
        }
        NeoLog.f("getchannel findIdValues end..");
        return linkedHashMap;
    }

    private static ByteBuffer g(ByteBuffer byteBuffer, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("size: " + i2);
        }
        int limit = byteBuffer.limit();
        int position = byteBuffer.position();
        int i3 = i2 + position;
        if (i3 < position || i3 > limit) {
            throw new BufferUnderflowException();
        }
        byteBuffer.limit(i3);
        try {
            ByteBuffer slice = byteBuffer.slice();
            slice.order(byteBuffer.order());
            byteBuffer.position(i3);
            return slice;
        } finally {
            byteBuffer.limit(limit);
        }
    }

    public static long h(FileChannel fileChannel) {
        long size = fileChannel.size();
        if (size < 22) {
            throw new IOException("APK too small for ZIP End of Central Directory (EOCD) record");
        }
        long j2 = size - 22;
        long min = Math.min(j2, 65535L);
        int i2 = 0;
        while (true) {
            long j3 = i2;
            if (j3 > min) {
                throw new IOException("ZIP End of Central Directory (EOCD) record not found");
            }
            long j4 = j2 - j3;
            ByteBuffer allocate = ByteBuffer.allocate(4);
            fileChannel.position(j4);
            fileChannel.read(allocate);
            ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
            allocate.order(byteOrder);
            if (allocate.getInt(0) == 101010256) {
                ByteBuffer allocate2 = ByteBuffer.allocate(2);
                fileChannel.position(j4 + 20);
                fileChannel.read(allocate2);
                allocate2.order(byteOrder);
                short s2 = allocate2.getShort(0);
                if (s2 == i2) {
                    return s2;
                }
            }
            i2++;
        }
    }

    private static ByteBuffer i(ByteBuffer byteBuffer, int i2, int i3) {
        if (i2 < 0) {
            throw new IllegalArgumentException("start: " + i2);
        }
        if (i3 < i2) {
            throw new IllegalArgumentException("end < start: " + i3 + " < " + i2);
        }
        int capacity = byteBuffer.capacity();
        if (i3 > byteBuffer.capacity()) {
            throw new IllegalArgumentException("end > capacity: " + i3 + " > " + capacity);
        }
        int limit = byteBuffer.limit();
        int position = byteBuffer.position();
        try {
            byteBuffer.position(0);
            byteBuffer.limit(i3);
            byteBuffer.position(i2);
            ByteBuffer slice = byteBuffer.slice();
            slice.order(byteBuffer.order());
            return slice;
        } finally {
            byteBuffer.position(0);
            byteBuffer.limit(limit);
            byteBuffer.position(position);
        }
    }
}
