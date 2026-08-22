package com.airbnb.lottie.network;

import androidx.annotation.RestrictTo;
import com.airbnb.lottie.utils.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

@RestrictTo
/* loaded from: classes.dex */
public class DefaultLottieFetchResult implements LottieFetchResult {

    /* renamed from: c, reason: collision with root package name */
    private final HttpURLConnection f9786c;

    public DefaultLottieFetchResult(HttpURLConnection httpURLConnection) {
        this.f9786c = httpURLConnection;
    }

    private String a(HttpURLConnection httpURLConnection) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                    sb.append('\n');
                } else {
                    try {
                        break;
                    } catch (Exception unused) {
                    }
                }
            } finally {
                try {
                    bufferedReader.close();
                } catch (Exception unused2) {
                }
            }
        }
        return sb.toString();
    }

    @Override // com.airbnb.lottie.network.LottieFetchResult
    public boolean H() {
        try {
            return this.f9786c.getResponseCode() / 100 == 2;
        } catch (IOException unused) {
            return false;
        }
    }

    @Override // com.airbnb.lottie.network.LottieFetchResult
    public String Z() {
        try {
            if (H()) {
                return null;
            }
            return "Unable to fetch " + this.f9786c.getURL() + ". Failed with " + this.f9786c.getResponseCode() + "\n" + a(this.f9786c);
        } catch (IOException e2) {
            Logger.d("get error failed ", e2);
            return e2.getMessage();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f9786c.disconnect();
    }

    @Override // com.airbnb.lottie.network.LottieFetchResult
    public String u() {
        return this.f9786c.getContentType();
    }

    @Override // com.airbnb.lottie.network.LottieFetchResult
    public InputStream x() {
        return this.f9786c.getInputStream();
    }
}
