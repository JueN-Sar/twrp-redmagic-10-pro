package cn.nubia.nbgame.sdk.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes.dex */
public class VoHelp {
    private static short a(byte[] bArr, int i2) {
        ByteBuffer allocate = ByteBuffer.allocate(2);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.put(bArr[i2]);
        allocate.put(bArr[i2 + 1]);
        return allocate.getShort(0);
    }

    public static String b(String str) {
        PackageUtil.f8324b = false;
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(new File(str), "r");
            byte[] bArr = new byte[2];
            long length = randomAccessFile.length() - 2;
            randomAccessFile.seek(length);
            randomAccessFile.readFully(bArr);
            int a2 = a(bArr, 0);
            byte[] bArr2 = new byte[a2];
            randomAccessFile.seek(length - a2);
            randomAccessFile.readFully(bArr2);
            NeoLog.l("V0Help", "readApk getchannel bytes != null");
            String str2 = new String(bArr2, "utf-8");
            NeoLog.l("V0Help", "readApk getchannel result is:" + str2);
            return str2;
        } catch (FileNotFoundException e2) {
            NeoLog.l("V0Help", "readApk getchannel FileNotFoundException");
            PackageUtil.f8324b = true;
            e2.printStackTrace();
            return "";
        } catch (Exception e3) {
            NeoLog.l("V0Help", "readApk getchannel Exception");
            PackageUtil.f8324b = true;
            e3.printStackTrace();
            return "";
        }
    }
}
