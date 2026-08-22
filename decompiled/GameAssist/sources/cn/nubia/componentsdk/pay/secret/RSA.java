package cn.nubia.componentsdk.pay.secret;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;

/* loaded from: classes.dex */
public class RSA {

    /* renamed from: a, reason: collision with root package name */
    private static String f6047a = "RSA/ECB/PKCS1Padding";

    /* renamed from: b, reason: collision with root package name */
    private static String f6048b = "RSA";

    public static byte[] a(Key key, byte[] bArr) {
        try {
            Cipher cipher = Cipher.getInstance(f6047a);
            cipher.init(1, key);
            int blockSize = cipher.getBlockSize();
            int outputSize = cipher.getOutputSize(bArr.length);
            byte[] bArr2 = new byte[(bArr.length % blockSize != 0 ? (bArr.length / blockSize) + 1 : bArr.length / blockSize) * outputSize];
            int i2 = 0;
            while (true) {
                int i3 = i2 * blockSize;
                if (bArr.length - i3 <= 0) {
                    return bArr2;
                }
                if (bArr.length - i3 > blockSize) {
                    cipher.doFinal(bArr, i3, blockSize, bArr2, i2 * outputSize);
                } else {
                    cipher.doFinal(bArr, i3, bArr.length - i3, bArr2, i2 * outputSize);
                }
                i2++;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static RSAPublicKey b(byte[] bArr, byte[] bArr2) {
        try {
            return (RSAPublicKey) KeyFactory.getInstance(f6048b).generatePublic(new RSAPublicKeySpec(new BigInteger(bArr), new BigInteger(bArr2)));
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        } catch (InvalidKeySpecException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static RSAPublicKey c(String str, String str2) {
        return b(new BigInteger(str, 16).toByteArray(), new BigInteger(str2, 16).toByteArray());
    }
}
