package com.airbnb.lottie.manager;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import com.airbnb.lottie.ImageAssetDelegate;
import com.airbnb.lottie.LottieImageAsset;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import java.io.IOException;
import java.util.Map;

/* loaded from: classes.dex */
public class ImageAssetManager {

    /* renamed from: e, reason: collision with root package name */
    private static final Object f9581e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private final Context f9582a;

    /* renamed from: b, reason: collision with root package name */
    private final String f9583b;

    /* renamed from: c, reason: collision with root package name */
    private ImageAssetDelegate f9584c;

    /* renamed from: d, reason: collision with root package name */
    private final Map f9585d;

    public ImageAssetManager(Drawable.Callback callback, String str, ImageAssetDelegate imageAssetDelegate, Map map) {
        if (TextUtils.isEmpty(str) || str.charAt(str.length() - 1) == '/') {
            this.f9583b = str;
        } else {
            this.f9583b = str + '/';
        }
        this.f9585d = map;
        d(imageAssetDelegate);
        if (callback instanceof View) {
            this.f9582a = ((View) callback).getContext().getApplicationContext();
        } else {
            this.f9582a = null;
        }
    }

    private Bitmap c(String str, Bitmap bitmap) {
        synchronized (f9581e) {
            ((LottieImageAsset) this.f9585d.get(str)).g(bitmap);
        }
        return bitmap;
    }

    public Bitmap a(String str) {
        LottieImageAsset lottieImageAsset = (LottieImageAsset) this.f9585d.get(str);
        if (lottieImageAsset == null) {
            return null;
        }
        Bitmap b2 = lottieImageAsset.b();
        if (b2 != null) {
            return b2;
        }
        ImageAssetDelegate imageAssetDelegate = this.f9584c;
        if (imageAssetDelegate != null) {
            Bitmap a2 = imageAssetDelegate.a(lottieImageAsset);
            if (a2 != null) {
                c(str, a2);
            }
            return a2;
        }
        Context context = this.f9582a;
        if (context == null) {
            return null;
        }
        String c2 = lottieImageAsset.c();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = true;
        options.inDensity = 160;
        if (c2.startsWith("data:") && c2.indexOf("base64,") > 0) {
            try {
                byte[] decode = Base64.decode(c2.substring(c2.indexOf(44) + 1), 0);
                return c(str, BitmapFactory.decodeByteArray(decode, 0, decode.length, options));
            } catch (IllegalArgumentException e2) {
                Logger.d("data URL did not have correct base64 format.", e2);
                return null;
            }
        }
        try {
            if (TextUtils.isEmpty(this.f9583b)) {
                throw new IllegalStateException("You must set an images folder before loading an image. Set it with LottieComposition#setImagesFolder or LottieDrawable#setImagesFolder");
            }
            try {
                Bitmap decodeStream = BitmapFactory.decodeStream(context.getAssets().open(this.f9583b + c2), null, options);
                if (decodeStream != null) {
                    return c(str, Utils.l(decodeStream, lottieImageAsset.f(), lottieImageAsset.d()));
                }
                Logger.c("Decoded image `" + str + "` is null.");
                return null;
            } catch (IllegalArgumentException e3) {
                Logger.d("Unable to decode image `" + str + "`.", e3);
                return null;
            }
        } catch (IOException e4) {
            Logger.d("Unable to open asset.", e4);
            return null;
        }
    }

    public boolean b(Context context) {
        if (this.f9582a instanceof Application) {
            context = context.getApplicationContext();
        }
        return context == this.f9582a;
    }

    public void d(ImageAssetDelegate imageAssetDelegate) {
        this.f9584c = imageAssetDelegate;
    }
}
