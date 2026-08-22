package cn.nubia.componentsdk.pay.secret;

import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/* loaded from: classes.dex */
public class ThreeDes {
    public static byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            SecretKey generateSecret = SecretKeyFactory.getInstance("desede").generateSecret(new DESedeKeySpec(bArr));
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            cipher.init(2, generateSecret, new IvParameterSpec("01234567".getBytes()));
            return cipher.doFinal(bArr2);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e3) {
            e3.printStackTrace();
            return null;
        } catch (Exception e4) {
            e4.printStackTrace();
            return null;
        }
    }

    public static String b(String str) {
        byte[] bytes = str.getBytes();
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b2 : bytes) {
            sb.append("0123456789ABCDEF".charAt((b2 & 240) >> 4));
            sb.append("0123456789ABCDEF".charAt(b2 & 15));
        }
        return sb.toString();
    }

    public static byte[] c(byte[] bArr, byte[] bArr2) {
        try {
            SecretKey generateSecret = SecretKeyFactory.getInstance("desede").generateSecret(new DESedeKeySpec(bArr));
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            cipher.init(1, generateSecret, new IvParameterSpec("01234567".getBytes()));
            return cipher.doFinal(bArr2);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e3) {
            e3.printStackTrace();
            return null;
        } catch (Exception e4) {
            e4.printStackTrace();
            return null;
        }
    }

    public static byte[] d(String str) {
        byte[] bArr = new byte[24];
        System.arraycopy(Md5.b((str + "test").getBytes()).getBytes(), 0, bArr, 0, 24);
        return bArr;
    }
}
