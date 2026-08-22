package com.bumptech.glide.load.model;

import com.bumptech.glide.load.data.DataFetcher;

/* loaded from: classes2.dex */
public interface ModelLoader<T, Y> {
    DataFetcher<Y> getResourceFetcher(T t, int i, int i2);
}
