package cn.nubia.chatassistant.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: classes.dex */
public class MusicLineView extends View {
    private static final String TAG = "MusicLineView";
    private float drawWidth;
    private float duration;
    private boolean isPause;
    private int playTime;
    private Paint proPaint;
    private float process;

    public MusicLineView(Context context) {
        super(context);
        this.process = 0.0f;
        this.duration = 0.0f;
        this.drawWidth = 0.0f;
        this.playTime = 0;
        this.isPause = false;
        init();
    }

    public MusicLineView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.process = 0.0f;
        this.duration = 0.0f;
        this.drawWidth = 0.0f;
        this.playTime = 0;
        this.isPause = false;
        init();
    }

    private void init() {
        Paint paint = new Paint();
        this.proPaint = paint;
        paint.setAntiAlias(true);
        this.proPaint.setDither(true);
        this.proPaint.setStrokeWidth(16.0f);
        this.proPaint.setColor(Color.parseColor("#FF666E8C"));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0.0f, 0.0f, this.drawWidth, 0.0f, this.proPaint);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    public void pause() {
        this.isPause = true;
    }

    public void reStart(float f, float f2) {
        this.isPause = false;
        this.playTime = (int) f;
        this.duration = f2;
    }

    public void reset() {
        this.process = 0.0f;
        this.duration = 0.0f;
        this.drawWidth = 0.0f;
        this.isPause = true;
        invalidate();
    }

    public void setDuration(float f) {
        this.duration = f;
        this.process = 0.0f;
        this.drawWidth = 0.0f;
        this.playTime = 0;
        this.isPause = false;
    }

    public void setPlayTime(float f) {
        this.process = f / this.duration;
        this.drawWidth = getMeasuredWidth() * this.process;
        invalidate();
    }

    public void setState(float f, float f2) {
        this.process = f / f2;
        this.drawWidth = getMeasuredWidth() * this.process;
        invalidate();
    }

    public void startupdateProcess() {
        if (this.playTime >= this.duration || this.isPause) {
            return;
        }
        post(new Runnable() { // from class: cn.nubia.chatassistant.ui.MusicLineView.1
            @Override // java.lang.Runnable
            public void run() {
                MusicLineView.this.playTime += 50;
                MusicLineView.this.setPlayTime(r2.playTime);
            }
        });
    }
}
