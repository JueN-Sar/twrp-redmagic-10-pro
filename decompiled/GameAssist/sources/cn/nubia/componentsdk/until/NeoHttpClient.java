package cn.nubia.componentsdk.until;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;

/* loaded from: classes.dex */
public class NeoHttpClient {

    /* renamed from: cn.nubia.componentsdk.until.NeoHttpClient$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f6055c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ HashMap f6056h;

        /* renamed from: i, reason: collision with root package name */
        final /* synthetic */ Context f6057i;

        /* renamed from: j, reason: collision with root package name */
        final /* synthetic */ HttpCallbackLister f6058j;

        /* renamed from: k, reason: collision with root package name */
        final /* synthetic */ NeoHttpClient f6059k;

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:39:0x010d  */
        /* JADX WARN: Removed duplicated region for block: B:41:? A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:42:0x0103 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 273
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.nubia.componentsdk.until.NeoHttpClient.AnonymousClass1.run():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Context context, Runnable runnable) {
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(runnable);
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(HttpURLConnection httpURLConnection, HashMap hashMap) {
        if (hashMap != null) {
            StringBuilder sb = new StringBuilder();
            for (String str : hashMap.keySet()) {
                String str2 = (String) hashMap.get(str);
                if (sb.length() < 1) {
                    sb.append(str);
                    sb.append("=");
                    sb.append(str2);
                } else {
                    sb.append("&");
                    sb.append(str);
                    sb.append("=");
                    sb.append(str2);
                }
            }
            PayLog.a("NeoHttpClient", "request params: " + ((Object) sb));
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(sb.toString().getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();
        }
    }
}
