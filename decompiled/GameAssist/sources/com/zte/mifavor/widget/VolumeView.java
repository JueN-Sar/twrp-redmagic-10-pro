package com.zte.mifavor.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.zte.extres.R;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class VolumeView extends View {
    private static int AUTO_DELAY = 40;
    private static final float DEFAULT_MAX_HIGH = 10.0f;
    private static final float DEFAULT_MIN_HIGH = 4.0f;
    private static final int LINE_COUNT = 7;
    public static final int MAX_VOLUME = 150;
    public static final int MINI_VOLUME = 80;
    private static final int MSG_UPDATE = 101;
    private static final String TAG = "VolumeView";
    private int halfHeight;
    private int halfVolume;
    private int halfVolumeWidth;
    private ArrayList<VolumeLine> lineList;
    private Handler mHandler;
    private int mHeight;
    private int mWidth;
    private int miniHalfVolume;
    private int nextHalfVolume;
    private int padding;
    private Paint paint;

    class VolumeLine {

        /* renamed from: a, reason: collision with root package name */
        private RectF f17847a;

        /* renamed from: b, reason: collision with root package name */
        private float f17848b;

        /* renamed from: c, reason: collision with root package name */
        private float f17849c;

        /* renamed from: d, reason: collision with root package name */
        private float f17850d;

        /* renamed from: e, reason: collision with root package name */
        private boolean f17851e;

        /* renamed from: f, reason: collision with root package name */
        private int f17852f;

        /* renamed from: g, reason: collision with root package name */
        private float f17853g;

        /* renamed from: h, reason: collision with root package name */
        private float f17854h;

        /* renamed from: i, reason: collision with root package name */
        private float f17855i;

        /* JADX INFO: Access modifiers changed from: private */
        public void d(Canvas canvas) {
            if (this.f17855i >= VolumeView.this.halfHeight) {
                this.f17855i = VolumeView.this.halfHeight;
            }
            float f2 = this.f17855i;
            float f3 = this.f17854h;
            if (f2 < f3) {
                this.f17855i = f3;
            }
            this.f17847a.top = VolumeView.this.halfHeight - this.f17855i;
            this.f17847a.bottom = VolumeView.this.halfHeight + this.f17855i;
            canvas.drawRoundRect(this.f17847a, VolumeView.this.halfVolumeWidth / 2, VolumeView.this.halfVolumeWidth / 2, VolumeView.this.paint);
        }

        public void b() {
            if (this.f17851e) {
                this.f17855i -= this.f17850d;
                int i2 = this.f17852f + 1;
                this.f17852f = i2;
                if (i2 == 5) {
                    this.f17852f = 0;
                    this.f17855i = this.f17854h;
                    this.f17851e = false;
                    return;
                }
                return;
            }
            this.f17855i += this.f17850d;
            int i3 = this.f17852f + 1;
            this.f17852f = i3;
            if (i3 == 5) {
                this.f17852f = 0;
                this.f17855i = this.f17853g;
                this.f17851e = true;
            }
        }

        public void c(int i2) {
            float f2 = i2;
            float f3 = (this.f17849c * f2) / VolumeView.DEFAULT_MAX_HIGH;
            this.f17854h = f3;
            float f4 = (f2 * this.f17848b) / VolumeView.DEFAULT_MAX_HIGH;
            this.f17853g = f4;
            this.f17850d = (f4 - f3) / 5.0f;
        }

        private VolumeLine(int i2, boolean z, float f2) {
            RectF rectF = new RectF();
            this.f17847a = rectF;
            this.f17852f = 0;
            this.f17851e = z;
            rectF.left = i2;
            rectF.right = i2 + VolumeView.this.halfVolumeWidth;
            this.f17848b = f2;
            this.f17849c = VolumeView.DEFAULT_MIN_HIGH;
            c(VolumeView.this.halfVolume);
            if (this.f17851e) {
                this.f17855i = this.f17853g;
            } else {
                this.f17855i = this.f17854h;
            }
        }
    }

    public VolumeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.lineList = new ArrayList<>(7);
        i(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.mHandler.removeMessages(101);
        this.mHandler.sendEmptyMessageDelayed(101, AUTO_DELAY);
        g();
        invalidate();
    }

    private void g() {
        for (int i2 = 0; i2 < this.lineList.size(); i2++) {
            this.lineList.get(i2).b();
        }
    }

    private void h(Canvas canvas) {
        for (int i2 = 0; i2 < this.lineList.size(); i2++) {
            this.lineList.get(i2).d(canvas);
        }
    }

    private void i(Context context) {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(context.getResources().getColor(R.color.common_controls_color));
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setAntiAlias(true);
        this.mHandler = new Handler() { // from class: com.zte.mifavor.widget.VolumeView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what != 101) {
                    return;
                }
                VolumeView.this.f();
            }
        };
    }

    private void j() {
        if (this.mHandler.hasMessages(101)) {
            return;
        }
        this.mHandler.sendEmptyMessageDelayed(101, 150L);
    }

    private void k() {
        this.mHandler.removeMessages(101);
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        j();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        k();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        h(canvas);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.mWidth = i2;
        this.mHeight = i3;
        int i6 = i3 / 2;
        this.halfHeight = i6;
        int i7 = i6 / 2;
        this.halfVolume = i7;
        this.nextHalfVolume = i7;
        this.halfVolumeWidth = Utils.b(getContext(), 3.5d);
        int paddingLeft = getPaddingLeft();
        this.padding = paddingLeft;
        int i8 = ((this.mWidth - (this.halfVolumeWidth * 7)) - (paddingLeft * 2)) / 6;
        this.lineList.add(new VolumeLine(paddingLeft, false, 6.0f));
        float f2 = 8.0f;
        this.lineList.add(new VolumeLine(this.halfVolumeWidth + paddingLeft + i8, true, f2));
        this.lineList.add(new VolumeLine((this.halfVolumeWidth * 2) + paddingLeft + (i8 * 2), false, f2));
        this.lineList.add(new VolumeLine((this.halfVolumeWidth * 3) + paddingLeft + (i8 * 3), true, 6.0f));
        float f3 = 8.0f;
        this.lineList.add(new VolumeLine((this.halfVolumeWidth * 4) + paddingLeft + (i8 * 4), false, f3));
        this.lineList.add(new VolumeLine((this.halfVolumeWidth * 5) + paddingLeft + (i8 * 5), true, f3));
        this.lineList.add(new VolumeLine(paddingLeft + (this.halfVolumeWidth * 6) + (i8 * 6), false, 6.0f));
        this.miniHalfVolume = ((this.mHeight * 80) / 150) / 2;
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i2) {
        super.onVisibilityChanged(view, i2);
        if (i2 == 0) {
            j();
        } else {
            k();
        }
    }

    public void setVolume(int i2) {
        Log.d(TAG, "setVolume " + i2);
        this.nextHalfVolume = ((this.mHeight * i2) / 150) / 2;
        for (int i3 = 0; i3 < this.lineList.size(); i3++) {
            this.lineList.get(i3).c(this.nextHalfVolume);
        }
        if (this.nextHalfVolume <= this.miniHalfVolume) {
            AUTO_DELAY = 60;
        } else {
            AUTO_DELAY = 45;
        }
    }
}
