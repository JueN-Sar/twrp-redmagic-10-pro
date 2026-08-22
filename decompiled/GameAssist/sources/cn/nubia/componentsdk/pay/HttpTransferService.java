package cn.nubia.componentsdk.pay;

import android.content.Context;
import android.os.Message;
import android.util.SparseArray;
import cn.nubia.componentsdk.until.PayLog;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/* loaded from: classes.dex */
class HttpTransferService {

    /* renamed from: b, reason: collision with root package name */
    private static SparseArray f5960b = new SparseArray();

    /* renamed from: c, reason: collision with root package name */
    private static SparseArray f5961c = new SparseArray();

    /* renamed from: d, reason: collision with root package name */
    private static SparseArray f5962d = new SparseArray();

    /* renamed from: e, reason: collision with root package name */
    private static SparseArray f5963e = new SparseArray();

    /* renamed from: f, reason: collision with root package name */
    private static SparseArray f5964f = new SparseArray();

    /* renamed from: g, reason: collision with root package name */
    private static boolean f5965g = false;

    /* renamed from: h, reason: collision with root package name */
    private static int f5966h = 100;

    /* renamed from: a, reason: collision with root package name */
    private String f5967a = getClass().getSimpleName();

    HttpTransferService() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(int i2, Map map) {
        if (f5965g) {
            f5964f.put(i2, map);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(HttpURLConnection httpURLConnection, int i2, MessageHandler messageHandler) {
        if (httpURLConnection == null) {
            return;
        }
        synchronized (httpURLConnection) {
            try {
                try {
                    Thread thread = (Thread) f5962d.get(i2);
                    if (thread != null) {
                        thread.interrupt();
                        f5962d.remove(i2);
                    }
                    httpURLConnection.disconnect();
                    f5960b.remove(i2);
                    if (messageHandler != null) {
                        t(-2, i2, 0, null, 50L, messageHandler);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private HashMap l(HashMap hashMap) {
        HashMap hashMap2 = new HashMap();
        hashMap2.put("ACCEPT", "*/*");
        hashMap2.put("CONNECTION", "Keep-Alive");
        hashMap2.put("CONTENT-TYPE", "application/x-www-form-urlencoded");
        hashMap2.put("ACCEPT-LANGUAGE", "zh-cn");
        hashMap2.put("ACCEPT-CHARSET", "UTF-8");
        if (hashMap != null && !hashMap.isEmpty()) {
            for (Map.Entry entry : hashMap.entrySet()) {
                hashMap2.put(((String) entry.getKey()).toUpperCase(), entry.getValue());
            }
        }
        return hashMap2;
    }

    private int m() {
        int random = (int) (Math.random() * 1000.0d);
        while (true) {
            if (f5960b.get(random) == null && random != 0) {
                return random;
            }
            random++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HttpURLConnection o(String str, HashMap hashMap, int i2, MessageHandler messageHandler) {
        URL url = new URL(v(str));
        PayLog.a(this.f5967a, "URL: " + url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        if (httpURLConnection == null) {
            t(0, i2, 116, null, 0L, messageHandler);
            return null;
        }
        t(0, i2, 0, null, 0L, messageHandler);
        httpURLConnection.setConnectTimeout(Constant.f5929a);
        httpURLConnection.setReadTimeout(Constant.f5930b);
        HashMap l2 = l(hashMap);
        for (Map.Entry entry : l2.entrySet()) {
            Object value = entry.getValue();
            String upperCase = ((String) entry.getKey()).toUpperCase();
            if (!(value instanceof Boolean)) {
                httpURLConnection.setRequestProperty(upperCase, (String) value);
            } else if ("DOINPUT".equals(upperCase)) {
                httpURLConnection.setDoInput(Boolean.valueOf(value.toString()).booleanValue());
            } else if ("DOOUTPUT".equals(upperCase)) {
                httpURLConnection.setDoOutput(Boolean.valueOf(value.toString()).booleanValue());
            } else if ("USECACHES".equals(upperCase)) {
                httpURLConnection.setUseCaches(Boolean.valueOf(value.toString()).booleanValue());
            } else if ("ALLOWUSERINTERACTION".equals(upperCase)) {
                httpURLConnection.setAllowUserInteraction(Boolean.valueOf(value.toString()).booleanValue());
            }
        }
        f5963e.put(i2, l2);
        return httpURLConnection;
    }

    private void p(long j2, long j3, int i2, MessageHandler messageHandler) {
        t(4, i2, 0, new Long[]{Long.valueOf(j2), Long.valueOf(j3)}, 0L, messageHandler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(OutputStream outputStream, InputStream inputStream, int i2, long j2, boolean z, MessageHandler messageHandler) {
        int i3;
        f5963e.remove(i2);
        if (outputStream != null) {
            byte[] bArr = new byte[1024];
            long currentTimeMillis = System.currentTimeMillis();
            long j3 = 0;
            InputStream inputStream2 = inputStream;
            long j4 = 0;
            int i4 = 0;
            while (true) {
                int read = inputStream2.read(bArr);
                if (read == -1) {
                    break;
                }
                outputStream.write(bArr, 0, read);
                if (messageHandler.f5977c != null || messageHandler.f5981g != null) {
                    long j5 = j3 + read;
                    int i5 = (int) ((f5966h * j5) / j2);
                    if (i5 > i4) {
                        if (z) {
                            i3 = i5;
                            t(2, i2, i5, null, 0L, messageHandler);
                        } else {
                            i3 = i5;
                        }
                        if (messageHandler.f5981g != null) {
                            p(currentTimeMillis, j5 - j4, i2, messageHandler);
                            j4 = j5;
                        }
                        i4 = i3;
                    }
                    inputStream2 = inputStream;
                    j3 = j5;
                }
            }
            PayLog.a(this.f5967a, "outputStream.flush()");
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            if (z) {
                t(3, i2, 0, null, 0L, messageHandler);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t(int i2, int i3, int i4, Object obj, long j2, MessageHandler messageHandler) {
        if (messageHandler != null) {
            Message obtainMessage = messageHandler.obtainMessage(i2, i3, i4, obj);
            if (j2 == 0) {
                j2 = 10;
            }
            messageHandler.sendMessageDelayed(obtainMessage, j2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(int i2, Exception exc, int i3, MessageHandler messageHandler) {
        if (f5962d.get(i2) != null) {
            exc.printStackTrace();
            t(-1, i2, i3, exc, 0L, messageHandler);
        }
    }

    private String v(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt < 0 || charAt > 127) {
                byte[] bArr = new byte[0];
                try {
                    bArr = Character.toString(charAt).getBytes("UTF-8");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                for (int i3 = 0; i3 < bArr.length; i3++) {
                    int i4 = bArr[i3];
                    if (i4 < 0) {
                        i4 += 256;
                    }
                    bArr[i3] = (byte) i4;
                }
                stringBuffer.append(URLEncoder.encode(new String(bArr)));
            } else {
                stringBuffer.append(charAt);
            }
        }
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w(int i2, HttpURLConnection httpURLConnection, int i3, BufferData bufferData, long j2, MessageHandler messageHandler) {
        boolean z;
        InputStream inputStream;
        boolean z2;
        byte[] bArr;
        f5963e.remove(i3);
        if (i2 != 200 && i2 != 206 && i2 != 301 && i2 != 302) {
            t(1, i3, i2, null, 0L, messageHandler);
            return;
        }
        InputStream inputStream2 = httpURLConnection.getInputStream();
        String contentEncoding = httpURLConnection.getContentEncoding();
        int available = inputStream2.available();
        if (contentEncoding == null || contentEncoding.toLowerCase().indexOf("gzip") == -1 || available <= 0) {
            z = true;
            inputStream = inputStream2;
        } else {
            inputStream = new GZIPInputStream(inputStream2);
            z = false;
        }
        if (j2 < 1) {
            z2 = false;
        } else {
            f5961c.put(i3, Long.valueOf(j2));
            z2 = z;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr2 = new byte[8192];
        long j3 = 0;
        int i4 = 0;
        while (true) {
            int read = inputStream.read(bArr2);
            if (read <= 0) {
                break;
            }
            PayLog.a(this.f5967a, "start get inputStream!");
            byteArrayOutputStream.write(bArr2, 0, read);
            if (!z2 || (messageHandler.f5977c == null && messageHandler.f5981g == null)) {
                bArr = bArr2;
            } else {
                long j4 = j3 + read;
                int i5 = (int) ((f5966h * j4) / j2);
                if (i5 > i4) {
                    bArr = bArr2;
                    t(2, i3, i5, null, 0L, messageHandler);
                    if (messageHandler.f5981g != null) {
                        p(j4, j2, i3, messageHandler);
                    }
                    i4 = i5;
                } else {
                    bArr = bArr2;
                }
                j3 = j4;
            }
            bArr2 = bArr;
        }
        if (byteArrayOutputStream.size() > 0) {
            bufferData.b(byteArrayOutputStream.toByteArray());
        }
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        inputStream.close();
        t(3, i3, 0, null, 0L, messageHandler);
    }

    public HttpURLConnection n(int i2) {
        return (HttpURLConnection) f5960b.get(i2);
    }

    protected void q(int i2, MessageHandler messageHandler) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) f5960b.get(i2);
        if (httpURLConnection != null) {
            try {
                k(httpURLConnection, i2, messageHandler);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    protected int r(final String str, final byte[] bArr, final BufferData bufferData, final HashMap hashMap, final MessageHandler messageHandler, Context context) {
        final int m2 = m();
        if (!NetUtil.a(context)) {
            t(0, m2, 116, new ConnectException("Network is not available"), 0L, messageHandler);
            return m2;
        }
        Thread thread = new Thread(new Runnable() { // from class: cn.nubia.componentsdk.pay.HttpTransferService.1
            @Override // java.lang.Runnable
            public void run() {
                OutOfMemoryError outOfMemoryError;
                HttpURLConnection httpURLConnection;
                try {
                    try {
                        httpURLConnection = HttpTransferService.this.o(str, hashMap, m2, messageHandler);
                    } catch (Throwable th) {
                        th = th;
                        HttpTransferService.this.k(null, m2, null);
                        throw th;
                    }
                } catch (ProtocolException e2) {
                    e = e2;
                    httpURLConnection = null;
                } catch (SocketException e3) {
                    e = e3;
                    httpURLConnection = null;
                } catch (SocketTimeoutException e4) {
                    e = e4;
                    httpURLConnection = null;
                } catch (IOException e5) {
                    e = e5;
                    httpURLConnection = null;
                } catch (Exception e6) {
                    e = e6;
                    httpURLConnection = null;
                } catch (OutOfMemoryError e7) {
                    outOfMemoryError = e7;
                    httpURLConnection = null;
                } catch (Throwable th2) {
                    th = th2;
                    HttpTransferService.this.k(null, m2, null);
                    throw th;
                }
                if (httpURLConnection == null) {
                    HttpTransferService.this.k(httpURLConnection, m2, null);
                    return;
                }
                try {
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setUseCaches(false);
                    byte[] bArr2 = bArr;
                    if (bArr2 != null && bArr2.length > 0) {
                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
                        long length = bArr.length;
                        httpURLConnection.setRequestProperty("CONTENT-LENGTH", String.valueOf(length));
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        PayLog.a(HttpTransferService.this.f5967a, "getOutputStream() start");
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                        PayLog.a(HttpTransferService.this.f5967a, "postData start");
                        HttpTransferService.this.s(bufferedOutputStream, byteArrayInputStream, m2, length, false, messageHandler);
                    }
                    HttpTransferService.f5960b.put(m2, httpURLConnection);
                    PayLog.a(HttpTransferService.this.f5967a, "start get response");
                    int responseCode = httpURLConnection.getResponseCode();
                    PayLog.a("TAG", "end get responseCode = " + responseCode);
                    HttpTransferService.this.j(m2, httpURLConnection.getHeaderFields());
                    HttpTransferService.this.t(1, m2, responseCode, null, 0L, messageHandler);
                    HttpTransferService.this.w(responseCode, httpURLConnection, m2, bufferData, (long) httpURLConnection.getContentLength(), messageHandler);
                } catch (ProtocolException e8) {
                    e = e8;
                    HttpTransferService.this.u(m2, e, 112, messageHandler);
                    HttpTransferService.this.k(httpURLConnection, m2, null);
                } catch (SocketException e9) {
                    e = e9;
                    HttpTransferService.this.u(m2, e, 110, messageHandler);
                    HttpTransferService.this.k(httpURLConnection, m2, null);
                } catch (SocketTimeoutException e10) {
                    e = e10;
                    HttpTransferService.this.u(m2, e, 122, messageHandler);
                    HttpTransferService.this.k(httpURLConnection, m2, null);
                } catch (IOException e11) {
                    e = e11;
                    HttpTransferService.this.u(m2, e, 110, messageHandler);
                    HttpTransferService.this.k(httpURLConnection, m2, null);
                } catch (Exception e12) {
                    e = e12;
                    HttpTransferService.this.u(m2, e, 118, messageHandler);
                    HttpTransferService.this.k(httpURLConnection, m2, null);
                } catch (OutOfMemoryError e13) {
                    outOfMemoryError = e13;
                    HttpTransferService.this.t(-1, m2, 114, outOfMemoryError, 0L, messageHandler);
                    HttpTransferService.this.k(httpURLConnection, m2, null);
                }
                HttpTransferService.this.k(httpURLConnection, m2, null);
            }
        });
        thread.start();
        f5962d.put(m2, thread);
        return m2;
    }
}
