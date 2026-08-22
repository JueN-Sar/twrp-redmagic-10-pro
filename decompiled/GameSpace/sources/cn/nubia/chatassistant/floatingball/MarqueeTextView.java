package cn.nubia.chatassistant.floatingball;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.Scroller;
import android.widget.TextView;

/* loaded from: classes.dex */
public class MarqueeTextView extends TextView implements Runnable {
    public static final String TAG = "MarqueeTextView";
    private int currentScrollX;
    private int dx;
    private boolean isMeasure;
    private boolean isStop;
    private Scroller mScroller;
    private int textWidth;

    public MarqueeTextView(Context context) {
        super(context);
        this.isStop = false;
        this.isMeasure = false;
        setScroller(context);
    }

    public MarqueeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isStop = false;
        this.isMeasure = false;
        setScroller(context);
    }

    public MarqueeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isStop = false;
        this.isMeasure = false;
        setScroller(context);
    }

    private void setScroller(Context context) {
        Scroller scroller = new Scroller(context);
        this.mScroller = scroller;
        setScroller(scroller);
    }

    public int getCurrentScrollX() {
        return this.mScroller.getCurrX();
    }

    public int getTextWidth() {
        int measureText = (int) getPaint().measureText(getText().toString());
        this.textWidth = measureText;
        return measureText;
    }

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.isMeasure) {
            return;
        }
        getTextWidth();
        this.isMeasure = true;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.currentScrollX = getWidth();
    }

    public void resetScroll() {
        this.mScroller.setFinalX(0);
        invalidate();
    }

    @Override // java.lang.Runnable
    public void run() {
        scroll(getCurrentScrollX(), 0, this.dx, 0);
    }

    public void scroll(int i, int i2, int i3, int i4) {
        this.mScroller.startScroll(i, i2, i3, i4, 1000);
        invalidate();
    }

    public void startFromHead() {
        this.currentScrollX = 0;
    }

    public void startScroll(int i) {
        this.isStop = false;
        this.dx = i;
        removeCallbacks(this);
        post(this);
    }

    public void stopScroll() {
        this.isStop = true;
    }
}
