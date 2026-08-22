package cn.nubia.gameassist.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import cn.nubia.gameassist.R;

/* loaded from: classes.dex */
public class CircleDrawable extends BitmapDrawable {

    /* renamed from: a, reason: collision with root package name */
    private final Paint f7649a;

    /* renamed from: b, reason: collision with root package name */
    private final int f7650b;

    public CircleDrawable(Context context, Bitmap bitmap) {
        super(context.getResources(), bitmap);
        Paint paint = new Paint();
        this.f7649a = paint;
        this.f7650b = context.getResources().getDimensionPixelSize(R.dimen.pip_icon_gap_size);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new BitmapShader(bitmap, tileMode, tileMode));
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int width = getBitmap().getWidth();
        int height = getBitmap().getHeight();
        canvas.drawCircle(width / 2, height / 2, (Math.min(width, height) / 2) - this.f7650b, this.f7649a);
    }
}
