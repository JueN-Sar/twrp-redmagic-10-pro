package com.bumptech.glide.load.model;

import android.util.Log;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.util.ByteArrayPool;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public class StreamEncoder implements Encoder<InputStream> {
    private static final String TAG = "StreamEncoder";

    @Override // com.bumptech.glide.load.Encoder
    public boolean encode(InputStream inputStream, OutputStream outputStream) {
        byte[] bytes = ByteArrayPool.get().getBytes();
        while (true) {
            try {
                try {
                    int read = inputStream.read(bytes);
                    if (read == -1) {
                        ByteArrayPool.get().releaseBytes(bytes);
                        return true;
                    }
                    outputStream.write(bytes, 0, read);
                } catch (IOException e) {
                    if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "Failed to encode data onto the OutputStream", e);
                    }
                    ByteArrayPool.get().releaseBytes(bytes);
                    return false;
                }
            } catch (Throwable th) {
                ByteArrayPool.get().releaseBytes(bytes);
                throw th;
            }
        }
    }

    @Override // com.bumptech.glide.load.Encoder
    public String getId() {
        return "";
    }
}
