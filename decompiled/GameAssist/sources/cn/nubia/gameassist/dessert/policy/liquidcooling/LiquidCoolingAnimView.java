package cn.nubia.gameassist.dessert.policy.liquidcooling;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import cn.nubia.gameassist.R;

/* loaded from: classes.dex */
public class LiquidCoolingAnimView extends View {
    private static final int FADE_FRAMES = 5;
    private static final int ORIGINAL_ALPHA = 255;
    private Paint mBackgroundPaint;
    private RectF mBackgroundRect;
    private Bitmap mBgBitmap;
    private Context mContext;
    private Bitmap mCurrentBitmap;
    private int mCurrentFrameIndex;

    public LiquidCoolingAnimView(Context context) {
        this(context, null);
    }

    private void a(Canvas canvas) {
        canvas.drawBitmap(this.mCurrentBitmap, 0.0f, 0.0f, (Paint) null);
        canvas.restore();
    }

    private void drawBackground(Canvas canvas) {
        int i2;
        int i3 = this.mCurrentFrameIndex;
        if (i3 >= 5) {
            if (i3 <= 165) {
                i2 = ORIGINAL_ALPHA;
                this.mBackgroundPaint.setAlpha((int) ((i2 / 255.0f) * 255.0f));
                canvas.save();
                canvas.translate((getWidth() - this.mCurrentBitmap.getWidth()) / 2.0f, (getHeight() - this.mCurrentBitmap.getHeight()) / 2.0f);
                canvas.drawBitmap(this.mBgBitmap, 0.0f, 0.0f, this.mBackgroundPaint);
            }
            i3 = 170 - i3;
        }
        i2 = (int) ((i3 / 5.0f) * 255.0f);
        this.mBackgroundPaint.setAlpha((int) ((i2 / 255.0f) * 255.0f));
        canvas.save();
        canvas.translate((getWidth() - this.mCurrentBitmap.getWidth()) / 2.0f, (getHeight() - this.mCurrentBitmap.getHeight()) / 2.0f);
        canvas.drawBitmap(this.mBgBitmap, 0.0f, 0.0f, this.mBackgroundPaint);
    }

    public void b(Bitmap bitmap, int i2) {
        if (bitmap != null) {
            Bitmap bitmap2 = this.mCurrentBitmap;
            if (bitmap2 != null && !bitmap2.isRecycled()) {
                this.mCurrentBitmap.recycle();
            }
            this.mCurrentBitmap = bitmap;
            this.mCurrentFrameIndex = i2;
            if (this.mBackgroundRect == null) {
                this.mBackgroundRect = new RectF(0.0f, 0.0f, this.mCurrentBitmap.getWidth(), this.mCurrentBitmap.getHeight());
            }
            invalidate();
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (!isAttachedToWindow() || this.mCurrentBitmap == null) {
            return;
        }
        drawBackground(canvas);
        a(canvas);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Bitmap bitmap = this.mCurrentBitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.mCurrentBitmap.recycle();
            this.mCurrentBitmap = null;
        }
        Bitmap bitmap2 = this.mBgBitmap;
        if (bitmap2 == null || bitmap2.isRecycled()) {
            return;
        }
        this.mBgBitmap.recycle();
        this.mBgBitmap = null;
    }

    public LiquidCoolingAnimView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LiquidCoolingAnimView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mContext = context;
        Paint paint = new Paint(1);
        this.mBackgroundPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.mBgBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.liquid_cooling_anim_bg);
    }
}
