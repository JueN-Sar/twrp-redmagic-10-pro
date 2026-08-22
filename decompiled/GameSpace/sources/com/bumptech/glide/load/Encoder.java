package com.bumptech.glide.load;

import java.io.OutputStream;

/* loaded from: classes2.dex */
public interface Encoder<T> {
    boolean encode(T t, OutputStream outputStream);

    String getId();
}
