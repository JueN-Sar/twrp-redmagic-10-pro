package cn.nubia.gameassist.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;
import cn.nubia.gameassist.test.GameAssistTestActivity;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.config.ZteFeature;
import java.util.Locale;

/* loaded from: classes.dex */
public class RotationTextView extends TextView {
    private boolean mIsFoldScreen;
    private Matrix mMatrix;
    private Paint mPaint;
    private RotationMgr.Callback mRotationCallback;

    public RotationTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPaint = new Paint(197);
        this.mMatrix = new Matrix();
        this.mRotationCallback = new RotationMgr.Callback() { // from class: cn.nubia.gameassist.view.RotationTextView.1
            @Override // com.zte.gameassist.common.RotationMgr.Callback
            /* renamed from: onRotationChanged */
            public void y(int i2) {
                RotationTextView.this.postInvalidate();
            }
        };
    }

    private void a(Canvas canvas) {
        int save = canvas.save();
        canvas.setMatrix(this.mMatrix);
        this.mPaint.setColor(getCurrentTextColor());
        this.mPaint.setTextSize(getTextSize());
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        int width = getWidth();
        int height = getHeight();
        String charSequence = getText().toString();
        this.mPaint.getTextBounds(charSequence, 0, 1, new Rect());
        int textSize = (int) (getTextSize() * 1.2f);
        int length = charSequence.length() * textSize;
        int paddingBottom = (height / 2) + (getPaddingBottom() / 2);
        int i2 = ((width - length) / 2) + textSize;
        int i3 = 0;
        while (i3 < charSequence.length()) {
            int i4 = i3 + 1;
            canvas.drawText(charSequence, i3, i4, paddingBottom, i2, this.mPaint);
            i2 += textSize;
            i3 = i4;
        }
        canvas.restoreToCount(save);
    }

    private boolean b() {
        return ZteFeature.isSupportFoldBig() && FoldMgr.c().e();
    }

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        RotationMgr.e(getContext()).c(this.mRotationCallback);
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        RotationMgr.e(getContext()).p(this.mRotationCallback);
    }

    @Override // android.widget.TextView, android.view.View
    @VisibleForTesting
    protected void onDraw(Canvas canvas) {
        if (this.mIsFoldScreen) {
            super.onDraw(canvas);
            return;
        }
        if (GameAssistTestActivity.f7433c) {
            return;
        }
        setPadding(20, 0, 20, 0);
        if (!RotationMgr.k() || !"zh".equals(Locale.getDefault().getLanguage())) {
            super.onDraw(canvas);
            return;
        }
        if (getBackground() != null) {
            getBackground().draw(canvas);
        }
        a(canvas);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        this.mIsFoldScreen = b();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        Matrix matrix = new Matrix();
        this.mMatrix = matrix;
        matrix.setRotate(-90.0f);
        if (i3 < i2) {
            i2 = i3;
        }
        float f2 = (-i2) / 2;
        this.mMatrix.preTranslate(f2, f2);
        float f3 = i2 / 2;
        this.mMatrix.postTranslate(f3, f3);
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        super.setBackground(drawable);
        postInvalidate();
    }

    public RotationTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mPaint = new Paint(197);
        this.mMatrix = new Matrix();
        this.mRotationCallback = new RotationMgr.Callback() { // from class: cn.nubia.gameassist.view.RotationTextView.1
            @Override // com.zte.gameassist.common.RotationMgr.Callback
            /* renamed from: onRotationChanged */
            public void y(int i22) {
                RotationTextView.this.postInvalidate();
            }
        };
    }

    public RotationTextView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mPaint = new Paint(197);
        this.mMatrix = new Matrix();
        this.mRotationCallback = new RotationMgr.Callback() { // from class: cn.nubia.gameassist.view.RotationTextView.1
            @Override // com.zte.gameassist.common.RotationMgr.Callback
            /* renamed from: onRotationChanged */
            public void y(int i22) {
                RotationTextView.this.postInvalidate();
            }
        };
    }
}
