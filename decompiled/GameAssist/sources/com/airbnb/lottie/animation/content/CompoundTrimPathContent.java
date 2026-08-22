package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import com.airbnb.lottie.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class CompoundTrimPathContent {

    /* renamed from: a, reason: collision with root package name */
    private final List f9362a = new ArrayList();

    void a(TrimPathContent trimPathContent) {
        this.f9362a.add(trimPathContent);
    }

    public void b(Path path) {
        for (int size = this.f9362a.size() - 1; size >= 0; size--) {
            Utils.b(path, (TrimPathContent) this.f9362a.get(size));
        }
    }
}
