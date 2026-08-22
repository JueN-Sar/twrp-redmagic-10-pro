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
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class VoiceInputView extends View {
    private static final float DEFAULT_MAX_HIGH_RATE = 1.0f;
    private static final float DEFAULT_MIN_HIGH_RATE = 0.4f;
    private static final int LINE_COUNT = 7;
    public static final int MAX_VOLUME = 150;
    private static final int MSG_UPDATE = 0;
    private static final String TAG = "VoiceInputView";
    private static final int UPDATE_TIME_MS = 40;
    private static final boolean[] mDirections = {false, true, false, true, false, true, false};
    private static final float[] mMaxHeightRates = {0.6f, 0.8f, 0.8f, 0.6f, 0.8f, 0.8f, 0.6f};
    private int mAmplitude;
    private Handler mHandler;
    private int mHeight;
    private ArrayList<VolumeLine> mLineList;
    private Paint mPaint;
    private int mVolumeLineWidth;
    private int mWidth;

    class VolumeLine {

        /* renamed from: a, reason: collision with root package name */
        private RectF f17824a;

        /* renamed from: b, reason: collision with root package name */
        private float f17825b;

        /* renamed from: c, reason: collision with root package name */
        private float f17826c;

        /* renamed from: d, reason: collision with root package name */
        private float f17827d;

        /* renamed from: e, reason: collision with root package name */
        private float f17828e;

        /* renamed from: f, reason: collision with root package name */
        private float f17829f;

        /* renamed from: g, reason: collision with root package name */
        private boolean f17830g;

        /* renamed from: h, reason: collision with root package name */
        private int f17831h;

        /* renamed from: i, reason: collision with root package name */
        private float f17832i;

        /* JADX INFO: Access modifiers changed from: private */
        public void b(Canvas canvas) {
            this.f17824a.top = (VoiceInputView.this.mHeight / 2) - this.f17832i;
            this.f17824a.bottom = (VoiceInputView.this.mHeight / 2) + this.f17832i;
            canvas.drawRoundRect(this.f17824a, VoiceInputView.this.mVolumeLineWidth / 2, VoiceInputView.this.mVolumeLineWidth / 2, VoiceInputView.this.mPaint);
        }

        public void c(int i2) {
            float f2 = i2;
            float f3 = this.f17825b * f2;
            this.f17828e = f3;
            float f4 = f2 * this.f17826c;
            this.f17827d = f4;
            this.f17829f = (f4 - f3) / 5.0f;
        }

        public void d() {
            if (this.f17830g) {
                this.f17832i -= this.f17829f;
                int i2 = this.f17831h + 1;
                this.f17831h = i2;
                if (i2 == 5) {
                    this.f17831h = 0;
                    this.f17832i = this.f17828e;
                    this.f17830g = false;
                    return;
                }
                return;
            }
            this.f17832i += this.f17829f;
            int i3 = this.f17831h + 1;
            this.f17831h = i3;
            if (i3 == 5) {
                this.f17831h = 0;
                this.f17832i = this.f17827d;
                this.f17830g = true;
            }
        }

        private VolumeLine(int i2, boolean z, float f2) {
            RectF rectF = new RectF();
            this.f17824a = rectF;
            this.f17830g = z;
            rectF.left = i2;
            rectF.right = i2 + VoiceInputView.this.mVolumeLineWidth;
            this.f17825b = VoiceInputView.DEFAULT_MIN_HIGH_RATE;
            this.f17826c = f2;
            c(VoiceInputView.this.mAmplitude);
            if (this.f17830g) {
                this.f17832i = this.f17827d;
            } else {
                this.f17832i = this.f17828e;
            }
        }
    }

    public VoiceInputView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLineList = new ArrayList<>(7);
        g(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.mHandler.removeMessages(0);
        this.mHandler.sendEmptyMessageDelayed(0, 40L);
        for (int i2 = 0; i2 < this.mLineList.size(); i2++) {
            this.mLineList.get(i2).d();
        }
        invalidate();
    }

    private void g(Context context) {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setColor(context.getResources().getColor(R.color.common_controls_color));
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setAntiAlias(true);
        this.mHandler = new Handler() { // from class: com.zte.mifavor.widget.VoiceInputView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what != 0) {
                    return;
                }
                VoiceInputView.this.f();
            }
        };
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(R.dimen.voice_input_view_preferred_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(R.dimen.voice_input_view_preferred_width);
    }

    private void h() {
        if (this.mHandler.hasMessages(0)) {
            return;
        }
        this.mHandler.sendEmptyMessageDelayed(0, 150L);
    }

    private void i() {
        this.mHandler.removeMessages(0);
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        h();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        i();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i2 = 0; i2 < this.mLineList.size(); i2++) {
            this.mLineList.get(i2).b(canvas);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (getLayoutParams().width == -2) {
            if (mode == Integer.MIN_VALUE) {
                size = Math.min(getPreferredWidth(), size);
            } else if (mode == 0) {
                size = getPreferredWidth();
            }
        }
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(getPreferredHeight(), size2);
        } else if (mode2 == 0) {
            size2 = getPreferredHeight();
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), View.MeasureSpec.makeMeasureSpec(size2, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME));
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.mWidth = i2;
        this.mHeight = i3;
        this.mAmplitude = i3 / 4;
        this.mVolumeLineWidth = Utils.b(getContext(), 3.5d);
        int paddingLeft = ((this.mWidth - (this.mVolumeLineWidth * 7)) - (getPaddingLeft() + getPaddingRight())) / 6;
        int paddingLeft2 = getPaddingLeft();
        for (int i6 = 0; i6 < 7; i6++) {
            this.mLineList.add(new VolumeLine(paddingLeft2 + ((this.mVolumeLineWidth + paddingLeft) * i6), mDirections[i6], mMaxHeightRates[i6]));
        }
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i2) {
        super.onVisibilityChanged(view, i2);
        if (i2 == 0) {
            h();
        } else {
            i();
        }
    }

    public void setVolume(int i2) {
        Log.d(TAG, "setVolume " + i2);
        if (i2 > 150) {
            i2 = 150;
        }
        this.mAmplitude = ((this.mHeight * i2) / 150) / 2;
        for (int i3 = 0; i3 < this.mLineList.size(); i3++) {
            this.mLineList.get(i3).c(this.mAmplitude);
        }
    }
}
