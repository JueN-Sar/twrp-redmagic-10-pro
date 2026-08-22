package cn.nubia.componentsdk.pay.secret;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* loaded from: classes.dex */
public class RebuildKey {

    /* renamed from: a, reason: collision with root package name */
    static String f6049a = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDrmav30i8/RSX1+uxrOtVXa368s6tuY1aG166qqdVdYwEEt2zqWiidl/G9m8XBP321NvuWpgtiPB+rTd18PtUKeG1GT2O8lA27GrJFHgGWiqk30C4AGS9GXk7lED3d/gcnjWrUG2jY4JutqdAVm1HB7Y5sV50ZrAuCwhcvvZpXsQIDAQAB";

    public static byte[] a(byte[] bArr) {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64Utils.a(f6049a));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey generatePublic = keyFactory.generatePublic(x509EncodedKeySpec);
        keyFactory.getAlgorithm();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, generatePublic);
        int length = bArr.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = length - i2;
            if (i4 <= 0) {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            }
            byte[] doFinal = i4 > 117 ? cipher.doFinal(bArr, i2, 117) : cipher.doFinal(bArr, i2, i4);
            byteArrayOutputStream.write(doFinal, 0, doFinal.length);
            i3++;
            i2 = i3 * 117;
        }
    }
}
