package com.google.android.material.animation;

import android.graphics.drawable.Drawable;
import android.util.Property;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public class DrawableAlphaProperty extends Property<Drawable, Integer> {

    /* renamed from: b, reason: collision with root package name */
    public static final Property f13821b = new DrawableAlphaProperty();

    /* renamed from: a, reason: collision with root package name */
    private final WeakHashMap f13822a;

    private DrawableAlphaProperty() {
        super(Integer.class, "drawableAlphaCompat");
        this.f13822a = new WeakHashMap();
    }

    @Override // android.util.Property
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer get(Drawable drawable) {
        return Integer.valueOf(drawable.getAlpha());
    }

    @Override // android.util.Property
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void set(Drawable drawable, Integer num) {
        drawable.setAlpha(num.intValue());
    }
}
