package com.google.android.datatransport.cct;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import androidx.annotation.VisibleForTesting;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.backend.cct.BuildConfig;
import com.google.android.datatransport.cct.internal.AndroidClientInfo;
import com.google.android.datatransport.cct.internal.BatchedLogRequest;
import com.google.android.datatransport.cct.internal.ClientInfo;
import com.google.android.datatransport.cct.internal.LogEvent;
import com.google.android.datatransport.cct.internal.LogRequest;
import com.google.android.datatransport.cct.internal.LogResponse;
import com.google.android.datatransport.cct.internal.NetworkConnectionInfo;
import com.google.android.datatransport.cct.internal.QosTier;
import com.google.android.datatransport.runtime.EncodedPayload;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.backends.BackendRequest;
import com.google.android.datatransport.runtime.backends.BackendResponse;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.retries.Retries;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.EncodingException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes.dex */
final class CctTransportBackend implements TransportBackend {

    @VisibleForTesting
    static final String KEY_MOBILE_SUBTYPE = "mobile-subtype";

    @VisibleForTesting
    static final String KEY_NETWORK_TYPE = "net-type";

    /* renamed from: a, reason: collision with root package name */
    private final DataEncoder f10064a;

    /* renamed from: b, reason: collision with root package name */
    private final ConnectivityManager f10065b;

    /* renamed from: c, reason: collision with root package name */
    private final Context f10066c;

    /* renamed from: d, reason: collision with root package name */
    final URL f10067d;

    /* renamed from: e, reason: collision with root package name */
    private final Clock f10068e;

    /* renamed from: f, reason: collision with root package name */
    private final Clock f10069f;

    /* renamed from: g, reason: collision with root package name */
    private final int f10070g;

    static final class HttpRequest {

        /* renamed from: a, reason: collision with root package name */
        final URL f10073a;

        /* renamed from: b, reason: collision with root package name */
        final BatchedLogRequest f10074b;

        /* renamed from: c, reason: collision with root package name */
        final String f10075c;

        HttpRequest(URL url, BatchedLogRequest batchedLogRequest, String str) {
            this.f10073a = url;
            this.f10074b = batchedLogRequest;
            this.f10075c = str;
        }

        HttpRequest a(URL url) {
            return new HttpRequest(url, this.f10074b, this.f10075c);
        }
    }

    static final class HttpResponse {

        /* renamed from: a, reason: collision with root package name */
        final int f10076a;

        /* renamed from: b, reason: collision with root package name */
        final URL f10077b;

        /* renamed from: c, reason: collision with root package name */
        final long f10078c;

        HttpResponse(int i2, URL url, long j2) {
            this.f10076a = i2;
            this.f10077b = url;
            this.f10078c = j2;
        }
    }

    CctTransportBackend(Context context, Clock clock, Clock clock2, int i2) {
        this.f10064a = BatchedLogRequest.b();
        this.f10066c = context;
        this.f10065b = (ConnectivityManager) context.getSystemService("connectivity");
        this.f10067d = l(CCTDestination.f10056c);
        this.f10068e = clock2;
        this.f10069f = clock;
        this.f10070g = i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HttpResponse d(HttpRequest httpRequest) {
        Logging.a("CctTransportBackend", "Making request to: %s", httpRequest.f10073a);
        HttpURLConnection httpURLConnection = (HttpURLConnection) httpRequest.f10073a.openConnection();
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(this.f10070g);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("User-Agent", String.format("datatransport/%s android/", BuildConfig.VERSION_NAME));
        httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
        String str = httpRequest.f10075c;
        if (str != null) {
            httpURLConnection.setRequestProperty("X-Goog-Api-Key", str);
        }
        try {
            OutputStream outputStream = httpURLConnection.getOutputStream();
            try {
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
                try {
                    this.f10064a.a(httpRequest.f10074b, new BufferedWriter(new OutputStreamWriter(gZIPOutputStream)));
                    gZIPOutputStream.close();
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    int responseCode = httpURLConnection.getResponseCode();
                    Logging.e("CctTransportBackend", "Status Code: " + responseCode);
                    Logging.e("CctTransportBackend", "Content-Type: " + httpURLConnection.getHeaderField("Content-Type"));
                    Logging.e("CctTransportBackend", "Content-Encoding: " + httpURLConnection.getHeaderField("Content-Encoding"));
                    if (responseCode == 302 || responseCode == 301 || responseCode == 307) {
                        return new HttpResponse(responseCode, new URL(httpURLConnection.getHeaderField("Location")), 0L);
                    }
                    if (responseCode != 200) {
                        return new HttpResponse(responseCode, null, 0L);
                    }
                    InputStream inputStream = httpURLConnection.getInputStream();
                    try {
                        InputStream k2 = k(inputStream, httpURLConnection.getHeaderField("Content-Encoding"));
                        try {
                            HttpResponse httpResponse = new HttpResponse(responseCode, null, LogResponse.b(new BufferedReader(new InputStreamReader(k2))).c());
                            if (k2 != null) {
                                k2.close();
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            return httpResponse;
                        } catch (Throwable th) {
                            if (k2 != null) {
                                try {
                                    k2.close();
                                } catch (Throwable unused) {
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Throwable unused2) {
                            }
                        }
                        throw th2;
                    }
                } catch (Throwable th3) {
                    try {
                        gZIPOutputStream.close();
                    } catch (Throwable unused3) {
                    }
                    throw th3;
                }
            } catch (Throwable th4) {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable unused4) {
                    }
                }
                throw th4;
            }
        } catch (EncodingException | IOException e2) {
            Logging.c("CctTransportBackend", "Couldn't encode request, returning with 400", e2);
            return new HttpResponse(400, null, 0L);
        } catch (ConnectException | UnknownHostException e3) {
            Logging.c("CctTransportBackend", "Couldn't open connection, returning with 500", e3);
            return new HttpResponse(500, null, 0L);
        }
    }

    private static int e(NetworkInfo networkInfo) {
        if (networkInfo == null) {
            return NetworkConnectionInfo.MobileSubtype.UNKNOWN_MOBILE_SUBTYPE.d();
        }
        int subtype = networkInfo.getSubtype();
        if (subtype == -1) {
            return NetworkConnectionInfo.MobileSubtype.COMBINED.d();
        }
        if (NetworkConnectionInfo.MobileSubtype.c(subtype) != null) {
            return subtype;
        }
        return 0;
    }

    private static int f(NetworkInfo networkInfo) {
        return networkInfo == null ? NetworkConnectionInfo.NetworkType.NONE.d() : networkInfo.getType();
    }

    private static int g(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e2) {
            Logging.c("CctTransportBackend", "Unable to find version code for package", e2);
            return -1;
        }
    }

    @VisibleForTesting
    static long getTzOffset() {
        Calendar.getInstance();
        return TimeZone.getDefault().getOffset(Calendar.getInstance().getTimeInMillis()) / 1000;
    }

    private BatchedLogRequest h(BackendRequest backendRequest) {
        LogEvent.Builder j2;
        HashMap hashMap = new HashMap();
        for (EventInternal eventInternal : backendRequest.b()) {
            String j3 = eventInternal.j();
            if (hashMap.containsKey(j3)) {
                ((List) hashMap.get(j3)).add(eventInternal);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(eventInternal);
                hashMap.put(j3, arrayList);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry entry : hashMap.entrySet()) {
            EventInternal eventInternal2 = (EventInternal) ((List) entry.getValue()).get(0);
            LogRequest.Builder b2 = LogRequest.a().f(QosTier.DEFAULT).g(this.f10069f.a()).h(this.f10068e.a()).b(ClientInfo.a().c(ClientInfo.ClientType.ANDROID_FIREBASE).b(AndroidClientInfo.a().m(Integer.valueOf(eventInternal2.g("sdk-version"))).j(eventInternal2.b("model")).f(eventInternal2.b("hardware")).d(eventInternal2.b("device")).l(eventInternal2.b("product")).k(eventInternal2.b("os-uild")).h(eventInternal2.b("manufacturer")).e(eventInternal2.b("fingerprint")).c(eventInternal2.b("country")).g(eventInternal2.b("locale")).i(eventInternal2.b("mcc_mnc")).b(eventInternal2.b("application_build")).a()).a());
            try {
                b2.i(Integer.parseInt((String) entry.getKey()));
            } catch (NumberFormatException unused) {
                b2.j((String) entry.getKey());
            }
            ArrayList arrayList3 = new ArrayList();
            for (EventInternal eventInternal3 : (List) entry.getValue()) {
                EncodedPayload e2 = eventInternal3.e();
                Encoding b3 = e2.b();
                if (b3.equals(Encoding.b("proto"))) {
                    j2 = LogEvent.j(e2.a());
                } else if (b3.equals(Encoding.b("json"))) {
                    j2 = LogEvent.i(new String(e2.a(), Charset.forName("UTF-8")));
                } else {
                    Logging.f("CctTransportBackend", "Received event of unsupported encoding %s. Skipping...", b3);
                }
                j2.c(eventInternal3.f()).d(eventInternal3.k()).h(eventInternal3.h("tz-offset")).e(NetworkConnectionInfo.a().c(NetworkConnectionInfo.NetworkType.c(eventInternal3.g(KEY_NETWORK_TYPE))).b(NetworkConnectionInfo.MobileSubtype.c(eventInternal3.g(KEY_MOBILE_SUBTYPE))).a());
                if (eventInternal3.d() != null) {
                    j2.b(eventInternal3.d());
                }
                arrayList3.add(j2.a());
            }
            b2.c(arrayList3);
            arrayList2.add(b2.a());
        }
        return BatchedLogRequest.a(arrayList2);
    }

    private static TelephonyManager i(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    static /* synthetic */ HttpRequest j(HttpRequest httpRequest, HttpResponse httpResponse) {
        URL url = httpResponse.f10077b;
        if (url == null) {
            return null;
        }
        Logging.a("CctTransportBackend", "Following redirect to: %s", url);
        return httpRequest.a(httpResponse.f10077b);
    }

    private static InputStream k(InputStream inputStream, String str) {
        return "gzip".equals(str) ? new GZIPInputStream(inputStream) : inputStream;
    }

    private static URL l(String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException e2) {
            throw new IllegalArgumentException("Invalid url: " + str, e2);
        }
    }

    @Override // com.google.android.datatransport.runtime.backends.TransportBackend
    public EventInternal a(EventInternal eventInternal) {
        NetworkInfo activeNetworkInfo = this.f10065b.getActiveNetworkInfo();
        return eventInternal.l().a("sdk-version", Build.VERSION.SDK_INT).c("model", Build.MODEL).c("hardware", Build.HARDWARE).c("device", Build.DEVICE).c("product", Build.PRODUCT).c("os-uild", Build.ID).c("manufacturer", Build.MANUFACTURER).c("fingerprint", Build.FINGERPRINT).b("tz-offset", getTzOffset()).a(KEY_NETWORK_TYPE, f(activeNetworkInfo)).a(KEY_MOBILE_SUBTYPE, e(activeNetworkInfo)).c("country", Locale.getDefault().getCountry()).c("locale", Locale.getDefault().getLanguage()).c("mcc_mnc", i(this.f10066c).getSimOperator()).c("application_build", Integer.toString(g(this.f10066c))).d();
    }

    @Override // com.google.android.datatransport.runtime.backends.TransportBackend
    public BackendResponse b(BackendRequest backendRequest) {
        BatchedLogRequest h2 = h(backendRequest);
        URL url = this.f10067d;
        if (backendRequest.c() != null) {
            try {
                CCTDestination c2 = CCTDestination.c(backendRequest.c());
                r3 = c2.d() != null ? c2.d() : null;
                if (c2.e() != null) {
                    url = l(c2.e());
                }
            } catch (IllegalArgumentException unused) {
                return BackendResponse.a();
            }
        }
        try {
            HttpResponse httpResponse = (HttpResponse) Retries.a(5, new HttpRequest(url, h2, r3), CctTransportBackend$$Lambda$1.a(this), CctTransportBackend$$Lambda$4.b());
            int i2 = httpResponse.f10076a;
            if (i2 == 200) {
                return BackendResponse.d(httpResponse.f10078c);
            }
            if (i2 < 500 && i2 != 404) {
                return BackendResponse.a();
            }
            return BackendResponse.e();
        } catch (IOException e2) {
            Logging.c("CctTransportBackend", "Could not make request to the backend", e2);
            return BackendResponse.e();
        }
    }

    CctTransportBackend(Context context, Clock clock, Clock clock2) {
        this(context, clock, clock2, 40000);
    }
}
