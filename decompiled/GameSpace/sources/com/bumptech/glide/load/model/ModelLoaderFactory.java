package com.bumptech.glide.load.model;

import android.content.Context;

/* loaded from: classes2.dex */
public interface ModelLoaderFactory<T, Y> {
    ModelLoader<T, Y> build(Context context, GenericLoaderFactory genericLoaderFactory);

    void teardown();
}
