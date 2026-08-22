package cn.nubia.componentsdk.pay;

import android.content.Context;
import java.net.HttpURLConnection;
import java.util.HashMap;

/* loaded from: classes.dex */
public class CNetHttpTransfer {

    /* renamed from: a, reason: collision with root package name */
    private static CNetHttpTransfer f5923a;

    /* renamed from: b, reason: collision with root package name */
    private static HttpTransferService f5924b;

    private CNetHttpTransfer() {
        f5924b = new HttpTransferService();
    }

    public static CNetHttpTransfer b() {
        if (f5923a == null) {
            f5923a = new CNetHttpTransfer();
        }
        return f5923a;
    }

    public HttpURLConnection a(int i2) {
        return f5924b.n(i2);
    }

    public void c(int i2, MessageHandler messageHandler) {
        f5924b.q(i2, messageHandler);
    }

    public int d(String str, byte[] bArr, BufferData bufferData, HashMap hashMap, MessageHandler messageHandler, Context context) {
        return f5924b.r(str, bArr, bufferData, hashMap, messageHandler, context);
    }
}
