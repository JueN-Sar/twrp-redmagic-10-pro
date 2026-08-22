package androidx.core.graphics.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.charset.Charset;

/* loaded from: classes.dex */
public class IconCompat extends CustomVersionedParcelable {

    @VisibleForTesting
    static final String EXTRA_INT1 = "int1";

    @VisibleForTesting
    static final String EXTRA_INT2 = "int2";

    @VisibleForTesting
    static final String EXTRA_OBJ = "obj";

    @VisibleForTesting
    static final String EXTRA_STRING1 = "string1";

    @VisibleForTesting
    static final String EXTRA_TINT_LIST = "tint_list";

    @VisibleForTesting
    static final String EXTRA_TINT_MODE = "tint_mode";

    @VisibleForTesting
    static final String EXTRA_TYPE = "type";

    /* renamed from: k, reason: collision with root package name */
    static final PorterDuff.Mode f2972k = PorterDuff.Mode.SRC_IN;

    /* renamed from: a, reason: collision with root package name */
    public int f2973a;

    /* renamed from: b, reason: collision with root package name */
    Object f2974b;

    /* renamed from: c, reason: collision with root package name */
    public byte[] f2975c;

    /* renamed from: d, reason: collision with root package name */
    public Parcelable f2976d;

    /* renamed from: e, reason: collision with root package name */
    public int f2977e;

    /* renamed from: f, reason: collision with root package name */
    public int f2978f;

    /* renamed from: g, reason: collision with root package name */
    public ColorStateList f2979g;

    /* renamed from: h, reason: collision with root package name */
    PorterDuff.Mode f2980h;

    /* renamed from: i, reason: collision with root package name */
    public String f2981i;

    /* renamed from: j, reason: collision with root package name */
    public String f2982j;

    @RequiresApi
    static class Api23Impl {
        static IconCompat a(Object obj) {
            Preconditions.h(obj);
            int d2 = d(obj);
            if (d2 == 2) {
                return IconCompat.j(null, c(obj), b(obj));
            }
            if (d2 == 4) {
                return IconCompat.g(e(obj));
            }
            if (d2 == 6) {
                return IconCompat.d(e(obj));
            }
            IconCompat iconCompat = new IconCompat(-1);
            iconCompat.f2974b = obj;
            return iconCompat;
        }

        static int b(Object obj) {
            return Api28Impl.a(obj);
        }

        static String c(Object obj) {
            return Api28Impl.b(obj);
        }

        static int d(Object obj) {
            return Api28Impl.c(obj);
        }

        @Nullable
        @DoNotInline
        static Uri e(@NonNull Object obj) {
            return Api28Impl.d(obj);
        }

        @DoNotInline
        static Drawable f(Icon icon, Context context) {
            return icon.loadDrawable(context);
        }

        @DoNotInline
        static Icon g(IconCompat iconCompat, Context context) {
            Icon createWithBitmap;
            switch (iconCompat.f2973a) {
                case -1:
                    return (Icon) iconCompat.f2974b;
                case 0:
                default:
                    throw new IllegalArgumentException("Unknown type");
                case 1:
                    createWithBitmap = Icon.createWithBitmap((Bitmap) iconCompat.f2974b);
                    break;
                case 2:
                    createWithBitmap = Icon.createWithResource(iconCompat.l(), iconCompat.f2977e);
                    break;
                case 3:
                    createWithBitmap = Icon.createWithData((byte[]) iconCompat.f2974b, iconCompat.f2977e, iconCompat.f2978f);
                    break;
                case 4:
                    createWithBitmap = Icon.createWithContentUri((String) iconCompat.f2974b);
                    break;
                case 5:
                    createWithBitmap = Api26Impl.b((Bitmap) iconCompat.f2974b);
                    break;
                case 6:
                    createWithBitmap = Api30Impl.a(iconCompat.n());
                    break;
            }
            ColorStateList colorStateList = iconCompat.f2979g;
            if (colorStateList != null) {
                createWithBitmap.setTintList(colorStateList);
            }
            PorterDuff.Mode mode = iconCompat.f2980h;
            if (mode != IconCompat.f2972k) {
                createWithBitmap.setTintMode(mode);
            }
            return createWithBitmap;
        }
    }

    @RequiresApi
    static class Api26Impl {
        @DoNotInline
        static Drawable a(Drawable drawable, Drawable drawable2) {
            return new AdaptiveIconDrawable(drawable, drawable2);
        }

        @DoNotInline
        static Icon b(Bitmap bitmap) {
            return Icon.createWithAdaptiveBitmap(bitmap);
        }
    }

    @RequiresApi
    static class Api28Impl {
        @DoNotInline
        static int a(Object obj) {
            return ((Icon) obj).getResId();
        }

        @DoNotInline
        static String b(Object obj) {
            return ((Icon) obj).getResPackage();
        }

        @DoNotInline
        static int c(Object obj) {
            return ((Icon) obj).getType();
        }

        @DoNotInline
        static Uri d(Object obj) {
            return ((Icon) obj).getUri();
        }
    }

    @RequiresApi
    static class Api30Impl {
        @DoNotInline
        static Icon a(Uri uri) {
            return Icon.createWithAdaptiveBitmapContentUri(uri);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface IconType {
    }

    @RestrictTo
    public IconCompat() {
        this.f2973a = -1;
        this.f2975c = null;
        this.f2976d = null;
        this.f2977e = 0;
        this.f2978f = 0;
        this.f2979g = null;
        this.f2980h = f2972k;
        this.f2981i = null;
    }

    public static IconCompat a(Icon icon) {
        return Api23Impl.a(icon);
    }

    public static IconCompat b(Icon icon) {
        if (Api23Impl.d(icon) == 2 && Api23Impl.b(icon) == 0) {
            return null;
        }
        return Api23Impl.a(icon);
    }

    public static IconCompat c(Bitmap bitmap) {
        ObjectsCompat.c(bitmap);
        IconCompat iconCompat = new IconCompat(5);
        iconCompat.f2974b = bitmap;
        return iconCompat;
    }

    @VisibleForTesting
    static Bitmap createLegacyIconFromAdaptiveIcon(Bitmap bitmap, boolean z) {
        int min = (int) (Math.min(bitmap.getWidth(), bitmap.getHeight()) * 0.6666667f);
        Bitmap createBitmap = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(3);
        float f2 = min;
        float f3 = 0.5f * f2;
        float f4 = 0.9166667f * f3;
        if (z) {
            float f5 = 0.010416667f * f2;
            paint.setColor(0);
            paint.setShadowLayer(f5, 0.0f, f2 * 0.020833334f, 1023410176);
            canvas.drawCircle(f3, f3, f4, paint);
            paint.setShadowLayer(f5, 0.0f, 0.0f, 503316480);
            canvas.drawCircle(f3, f3, f4, paint);
            paint.clearShadowLayer();
        }
        paint.setColor(-16777216);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        Matrix matrix = new Matrix();
        matrix.setTranslate((-(bitmap.getWidth() - min)) / 2.0f, (-(bitmap.getHeight() - min)) / 2.0f);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        canvas.drawCircle(f3, f3, f4, paint);
        canvas.setBitmap(null);
        return createBitmap;
    }

    public static IconCompat d(Uri uri) {
        ObjectsCompat.c(uri);
        return e(uri.toString());
    }

    public static IconCompat e(String str) {
        ObjectsCompat.c(str);
        IconCompat iconCompat = new IconCompat(6);
        iconCompat.f2974b = str;
        return iconCompat;
    }

    public static IconCompat f(Bitmap bitmap) {
        ObjectsCompat.c(bitmap);
        IconCompat iconCompat = new IconCompat(1);
        iconCompat.f2974b = bitmap;
        return iconCompat;
    }

    public static IconCompat g(Uri uri) {
        ObjectsCompat.c(uri);
        return h(uri.toString());
    }

    public static IconCompat h(String str) {
        ObjectsCompat.c(str);
        IconCompat iconCompat = new IconCompat(4);
        iconCompat.f2974b = str;
        return iconCompat;
    }

    public static IconCompat i(Context context, int i2) {
        ObjectsCompat.c(context);
        return j(context.getResources(), context.getPackageName(), i2);
    }

    public static IconCompat j(Resources resources, String str, int i2) {
        ObjectsCompat.c(str);
        if (i2 == 0) {
            throw new IllegalArgumentException("Drawable resource ID must not be 0");
        }
        IconCompat iconCompat = new IconCompat(2);
        iconCompat.f2977e = i2;
        if (resources != null) {
            try {
                iconCompat.f2974b = resources.getResourceName(i2);
            } catch (Resources.NotFoundException unused) {
                throw new IllegalArgumentException("Icon resource cannot be found");
            }
        } else {
            iconCompat.f2974b = str;
        }
        iconCompat.f2982j = str;
        return iconCompat;
    }

    private static String u(int i2) {
        switch (i2) {
            case 1:
                return "BITMAP";
            case 2:
                return "RESOURCE";
            case 3:
                return "DATA";
            case 4:
                return "URI";
            case 5:
                return "BITMAP_MASKABLE";
            case 6:
                return "URI_MASKABLE";
            default:
                return "UNKNOWN";
        }
    }

    public int k() {
        int i2 = this.f2973a;
        if (i2 == -1) {
            return Api23Impl.b(this.f2974b);
        }
        if (i2 == 2) {
            return this.f2977e;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    public String l() {
        int i2 = this.f2973a;
        if (i2 == -1) {
            return Api23Impl.c(this.f2974b);
        }
        if (i2 == 2) {
            String str = this.f2982j;
            return (str == null || TextUtils.isEmpty(str)) ? ((String) this.f2974b).split(":", -1)[0] : this.f2982j;
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    public int m() {
        int i2 = this.f2973a;
        return i2 == -1 ? Api23Impl.d(this.f2974b) : i2;
    }

    public Uri n() {
        int i2 = this.f2973a;
        if (i2 == -1) {
            return Api23Impl.e(this.f2974b);
        }
        if (i2 == 4 || i2 == 6) {
            return Uri.parse((String) this.f2974b);
        }
        throw new IllegalStateException("called getUri() on " + this);
    }

    public InputStream o(Context context) {
        Uri n2 = n();
        String scheme = n2.getScheme();
        if ("content".equals(scheme) || "file".equals(scheme)) {
            try {
                return context.getContentResolver().openInputStream(n2);
            } catch (Exception e2) {
                Log.w("IconCompat", "Unable to load image from URI: " + n2, e2);
                return null;
            }
        }
        try {
            return new FileInputStream(new File((String) this.f2974b));
        } catch (FileNotFoundException e3) {
            Log.w("IconCompat", "Unable to load image from path: " + n2, e3);
            return null;
        }
    }

    public void p() {
        this.f2980h = PorterDuff.Mode.valueOf(this.f2981i);
        switch (this.f2973a) {
            case -1:
                Parcelable parcelable = this.f2976d;
                if (parcelable == null) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                this.f2974b = parcelable;
                return;
            case 0:
            default:
                return;
            case 1:
            case 5:
                Parcelable parcelable2 = this.f2976d;
                if (parcelable2 != null) {
                    this.f2974b = parcelable2;
                    return;
                }
                byte[] bArr = this.f2975c;
                this.f2974b = bArr;
                this.f2973a = 3;
                this.f2977e = 0;
                this.f2978f = bArr.length;
                return;
            case 2:
            case 4:
            case 6:
                String str = new String(this.f2975c, Charset.forName("UTF-16"));
                this.f2974b = str;
                if (this.f2973a == 2 && this.f2982j == null) {
                    this.f2982j = str.split(":", -1)[0];
                    return;
                }
                return;
            case 3:
                this.f2974b = this.f2975c;
                return;
        }
    }

    public void q(boolean z) {
        this.f2981i = this.f2980h.name();
        switch (this.f2973a) {
            case -1:
                if (z) {
                    throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
                }
                this.f2976d = (Parcelable) this.f2974b;
                return;
            case 0:
            default:
                return;
            case 1:
            case 5:
                if (!z) {
                    this.f2976d = (Parcelable) this.f2974b;
                    return;
                }
                Bitmap bitmap = (Bitmap) this.f2974b;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
                this.f2975c = byteArrayOutputStream.toByteArray();
                return;
            case 2:
                this.f2975c = ((String) this.f2974b).getBytes(Charset.forName("UTF-16"));
                return;
            case 3:
                this.f2975c = (byte[]) this.f2974b;
                return;
            case 4:
            case 6:
                this.f2975c = this.f2974b.toString().getBytes(Charset.forName("UTF-16"));
                return;
        }
    }

    public Bundle r() {
        Bundle bundle = new Bundle();
        switch (this.f2973a) {
            case -1:
                bundle.putParcelable(EXTRA_OBJ, (Parcelable) this.f2974b);
                break;
            case 0:
            default:
                throw new IllegalArgumentException("Invalid icon");
            case 1:
            case 5:
                bundle.putParcelable(EXTRA_OBJ, (Bitmap) this.f2974b);
                break;
            case 2:
            case 4:
            case 6:
                bundle.putString(EXTRA_OBJ, (String) this.f2974b);
                break;
            case 3:
                bundle.putByteArray(EXTRA_OBJ, (byte[]) this.f2974b);
                break;
        }
        bundle.putInt(EXTRA_TYPE, this.f2973a);
        bundle.putInt(EXTRA_INT1, this.f2977e);
        bundle.putInt(EXTRA_INT2, this.f2978f);
        bundle.putString(EXTRA_STRING1, this.f2982j);
        ColorStateList colorStateList = this.f2979g;
        if (colorStateList != null) {
            bundle.putParcelable(EXTRA_TINT_LIST, colorStateList);
        }
        PorterDuff.Mode mode = this.f2980h;
        if (mode != f2972k) {
            bundle.putString(EXTRA_TINT_MODE, mode.name());
        }
        return bundle;
    }

    public Icon s() {
        return t(null);
    }

    public Icon t(Context context) {
        return Api23Impl.g(this, context);
    }

    public String toString() {
        if (this.f2973a == -1) {
            return String.valueOf(this.f2974b);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        sb.append(u(this.f2973a));
        switch (this.f2973a) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.f2974b).getWidth());
                sb.append("x");
                sb.append(((Bitmap) this.f2974b).getHeight());
                break;
            case 2:
                sb.append(" pkg=");
                sb.append(this.f2982j);
                sb.append(" id=");
                sb.append(String.format("0x%08x", Integer.valueOf(k())));
                break;
            case 3:
                sb.append(" len=");
                sb.append(this.f2977e);
                if (this.f2978f != 0) {
                    sb.append(" off=");
                    sb.append(this.f2978f);
                    break;
                }
                break;
            case 4:
            case 6:
                sb.append(" uri=");
                sb.append(this.f2974b);
                break;
        }
        if (this.f2979g != null) {
            sb.append(" tint=");
            sb.append(this.f2979g);
        }
        if (this.f2980h != f2972k) {
            sb.append(" mode=");
            sb.append(this.f2980h);
        }
        sb.append(")");
        return sb.toString();
    }

    IconCompat(int i2) {
        this.f2975c = null;
        this.f2976d = null;
        this.f2977e = 0;
        this.f2978f = 0;
        this.f2979g = null;
        this.f2980h = f2972k;
        this.f2981i = null;
        this.f2973a = i2;
    }
}
