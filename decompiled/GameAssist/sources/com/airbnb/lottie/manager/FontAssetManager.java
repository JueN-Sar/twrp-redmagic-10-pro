package com.airbnb.lottie.manager;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.airbnb.lottie.FontAssetDelegate;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.MutablePair;
import com.airbnb.lottie.utils.Logger;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class FontAssetManager {

    /* renamed from: d, reason: collision with root package name */
    private final AssetManager f9578d;

    /* renamed from: e, reason: collision with root package name */
    private FontAssetDelegate f9579e;

    /* renamed from: a, reason: collision with root package name */
    private final MutablePair f9575a = new MutablePair();

    /* renamed from: b, reason: collision with root package name */
    private final Map f9576b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    private final Map f9577c = new HashMap();

    /* renamed from: f, reason: collision with root package name */
    private String f9580f = ".ttf";

    public FontAssetManager(Drawable.Callback callback, FontAssetDelegate fontAssetDelegate) {
        this.f9579e = fontAssetDelegate;
        if (callback instanceof View) {
            this.f9578d = ((View) callback).getContext().getAssets();
        } else {
            Logger.c("LottieDrawable must be inside of a view for images to work.");
            this.f9578d = null;
        }
    }

    private Typeface a(Font font) {
        Typeface typeface;
        String a2 = font.a();
        Typeface typeface2 = (Typeface) this.f9577c.get(a2);
        if (typeface2 != null) {
            return typeface2;
        }
        String c2 = font.c();
        String b2 = font.b();
        FontAssetDelegate fontAssetDelegate = this.f9579e;
        if (fontAssetDelegate != null) {
            typeface = fontAssetDelegate.b(a2, c2, b2);
            if (typeface == null) {
                typeface = this.f9579e.a(a2);
            }
        } else {
            typeface = null;
        }
        FontAssetDelegate fontAssetDelegate2 = this.f9579e;
        if (fontAssetDelegate2 != null && typeface == null) {
            String d2 = fontAssetDelegate2.d(a2, c2, b2);
            if (d2 == null) {
                d2 = this.f9579e.c(a2);
            }
            if (d2 != null) {
                typeface = Typeface.createFromAsset(this.f9578d, d2);
            }
        }
        if (font.d() != null) {
            return font.d();
        }
        if (typeface == null) {
            typeface = Typeface.createFromAsset(this.f9578d, "fonts/" + a2 + this.f9580f);
        }
        this.f9577c.put(a2, typeface);
        return typeface;
    }

    private Typeface e(Typeface typeface, String str) {
        boolean contains = str.contains("Italic");
        boolean contains2 = str.contains("Bold");
        int i2 = (contains && contains2) ? 3 : contains ? 2 : contains2 ? 1 : 0;
        return typeface.getStyle() == i2 ? typeface : Typeface.create(typeface, i2);
    }

    public Typeface b(Font font) {
        this.f9575a.b(font.a(), font.c());
        Typeface typeface = (Typeface) this.f9576b.get(this.f9575a);
        if (typeface != null) {
            return typeface;
        }
        Typeface e2 = e(a(font), font.c());
        this.f9576b.put(this.f9575a, e2);
        return e2;
    }

    public void c(String str) {
        this.f9580f = str;
    }

    public void d(FontAssetDelegate fontAssetDelegate) {
        this.f9579e = fontAssetDelegate;
    }
}
