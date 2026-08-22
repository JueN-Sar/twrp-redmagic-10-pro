package cn.nubia.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import cn.nubia.common.CommonApplication;
import cn.nubia.common.R;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class BitmapUtils {
    private static Bitmap adapterBottomIcon(Bitmap bitmap) {
        Bitmap copy = BitmapFactory.decodeResource(getAppContext().getResources(), R.drawable.download_icon_bottom).copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(copy);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return copy;
    }

    public static Bitmap bitmapRound(Bitmap bitmap, float f) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Rect rect = new Rect(0, 0, width, height);
        RectF rectF = new RectF(rect);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    public static Bitmap centerCrop(Bitmap bitmap, int i, int i2, int i3, int i4, boolean z) {
        if (bitmap == null || i <= 0 || i2 <= 0) {
            return null;
        }
        if (z) {
            bitmap = getZoomImage(bitmap, i, i2, true);
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setTranslate(-((width - i) / 2), -((height - i2) / 2));
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(1);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        float f = i4;
        RectF rectF = new RectF(f, f, i - i4, i2 - i4);
        float f2 = i3;
        canvas.drawRoundRect(rectF, f2, f2, paint);
        return createBitmap;
    }

    public static byte[] convertBitmapToBytes(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Drawable convertBitmapToDrawable(Bitmap bitmap) {
        return new BitmapDrawable(getAppContext().getResources(), bitmap);
    }

    public static Bitmap convertDrawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (!(drawable instanceof AdaptiveIconDrawable)) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public static Bitmap createBitmapWithProcess(Bitmap bitmap, float f) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        if (f > 0.0f) {
            f /= 100.0f;
        }
        Bitmap copy = BitmapFactory.decodeResource(getAppContext().getResources(), R.drawable.download_mask_icon).copy(Bitmap.Config.ARGB_8888, true);
        int width = copy.getWidth();
        int height = copy.getHeight();
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(createScaledBitmap, 0.0f, 0.0f, (Paint) null);
        Rect rect = new Rect();
        rect.set(0, (int) (f * height), width, height);
        canvas.drawBitmap(copy, rect, rect, (Paint) null);
        return adapterBottomIcon(createBitmap);
    }

    public static byte[] flattenBitmap(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bitmap.getWidth() * bitmap.getHeight() * 4);
        try {
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused) {
                }
            }
        } catch (IOException e) {
            Log.i("BitmapUtils", "flattenBitmap ", e);
            try {
                byteArrayOutputStream.close();
                return null;
            } catch (IOException unused2) {
                return null;
            }
        }
    }

    public static Context getAppContext() {
        return CommonApplication.getInstance().getAppContext();
    }

    public static Bitmap getCropBitmapCenter(Bitmap bitmap, int i, int i2, int i3, int i4) {
        return centerCrop(bitmap, i, i2, i3, i4, true);
    }

    public static Bitmap getCropBitmapTop(Bitmap bitmap, int i, int i2, int i3, int i4) {
        if (bitmap == null) {
            return null;
        }
        Bitmap zoomImage = getZoomImage(bitmap, i, i2, true);
        int width = zoomImage.getWidth();
        int height = zoomImage.getHeight();
        Matrix matrix = new Matrix();
        int i5 = (height - i2) / 2;
        matrix.setTranslate(-((width - i) / 2), 0.0f);
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(1);
        BitmapShader bitmapShader = new BitmapShader(zoomImage, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        float f = i4;
        RectF rectF = new RectF(f, f, i - i4, i2 - i4);
        float f2 = i3;
        canvas.drawRoundRect(rectF, f2, f2, paint);
        return createBitmap;
    }

    public static Bitmap getHexagonBitmap(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        Bitmap zoomImage = getZoomImage(bitmap, i, i2, true);
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        float f = i;
        float tan = ((float) Math.tan(Math.toRadians(15.0d))) * 0.25f * f;
        Paint paint = new Paint(1);
        paint.setColor(SupportMenu.CATEGORY_MASK);
        paint.setPathEffect(new CornerPathEffect(6.0f));
        Path path = new Path();
        float f2 = 0.5f * f;
        path.moveTo(f2, 0.0f);
        float f3 = i2;
        float f4 = 0.25f * f3;
        path.lineTo(tan, f4);
        float f5 = 0.75f * f3;
        path.lineTo(tan, f5);
        path.lineTo(f2, f3);
        float f6 = f - tan;
        path.lineTo(f6, f5);
        path.lineTo(f6, f4);
        path.close();
        canvas.drawPath(path, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(zoomImage, (Rect) null, new RectF(0.0f, 0.0f, f, f3), paint);
        return createBitmap;
    }

    public static Bitmap getParallelogramBitmap(Bitmap bitmap, int i, Rect rect) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = (width - rect.width()) / 2;
        int height2 = (height - rect.height()) / 2;
        Bitmap createBitmap = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawARGB(0, 0, 0, 0);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        Path path = new Path();
        path.moveTo(i, 0.0f);
        path.lineTo(0.0f, rect.height());
        path.lineTo(rect.width() - i, rect.height());
        path.lineTo(rect.width(), 0.0f);
        path.close();
        canvas.drawPath(path, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, -width2, -height2, paint);
        return createBitmap;
    }

    public static Bitmap getRoundCropBitmapByShader(Bitmap bitmap, int i, int i2, int i3, int i4, int i5) {
        float f;
        if (bitmap == null) {
            return null;
        }
        float f2 = i;
        float width = bitmap.getWidth();
        float f3 = (f2 * 1.0f) / width;
        float f4 = i2;
        float height = bitmap.getHeight();
        float f5 = (1.0f * f4) / height;
        if (f3 <= f5) {
            f3 = f5;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(f3, f3);
        float f6 = ((width * f3) - f2) / 2.0f;
        float f7 = (height * f3) - f4;
        if (i5 >= 0) {
            f = i5;
            if (f7 <= f) {
                f = 0.0f;
            }
        } else {
            f = f7 / 2.0f;
        }
        matrix.postTranslate(-f6, -f);
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(1);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        float f8 = i4;
        RectF rectF = new RectF(f8, f8, i - i4, i2 - i4);
        float f9 = i3;
        canvas.drawRoundRect(rectF, f9, f9, paint);
        canvas.setBitmap(null);
        return createBitmap;
    }

    private static Paint getRoundPaint(int i) {
        Context appContext = getAppContext();
        LinearGradient linearGradient = new LinearGradient(188.0f, -127.0f, 916.0f, 697.0f, new int[]{appContext.getColor(R.color.color_gradient_start), appContext.getColor(R.color.color_gradient_mid), appContext.getColor(R.color.color_gradient_end)}, new float[]{0.0f, 0.7f, 1.0f}, Shader.TileMode.CLAMP);
        Paint paint = new Paint(1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(i);
        paint.setShader(linearGradient);
        return paint;
    }

    public static Bitmap getZoomBitmap(Bitmap bitmap, float f) {
        if (bitmap == null || bitmap.isRecycled() || f <= 0.0f) {
            return null;
        }
        float width = bitmap.getWidth();
        float height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        return Bitmap.createBitmap(bitmap, 0, 0, (int) width, (int) height, matrix, true);
    }

    public static Bitmap getZoomImage(Bitmap bitmap, double d, double d2, boolean z) {
        if (bitmap == null || bitmap.isRecycled() || d <= 0.0d || d2 <= 0.0d) {
            return null;
        }
        float width = bitmap.getWidth();
        float height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float f = ((float) d) / width;
        float f2 = ((float) d2) / height;
        if (z) {
            if (f <= f2) {
                f = f2;
            }
            matrix.postScale(f, f);
        } else {
            matrix.postScale(f, f2);
        }
        return Bitmap.createBitmap(bitmap, 0, 0, (int) width, (int) height, matrix, true);
    }

    public static Bitmap overlayBitmaps(Bitmap bitmap, Bitmap bitmap2, float f) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(bitmap2, (int) (width * f), (int) (height * f), false), width - r3, height - r9, (Paint) null);
        return createBitmap;
    }
}
