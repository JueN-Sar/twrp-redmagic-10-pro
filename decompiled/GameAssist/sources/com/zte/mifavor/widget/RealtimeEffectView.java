package com.zte.mifavor.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.zte.extres.R;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class RealtimeEffectView extends View {
    private static boolean DEBUG = false;
    private static final String DISPLAY_GAUSSIAN_BLUR = "display_gaussian_blur";
    private static int RENDERING_COUNT = 0;
    private static final String TAG = "RealtimeEffectView";
    private static boolean mIsSDK31Above = true;
    private View mAppDecorView;
    private int mBackgroundColor;

    @Nullable
    private Bitmap mBitmapToBlur;
    private float mBlurRadius;

    @Nullable
    private Bitmap mBlurredBitmap;

    @Nullable
    private Canvas mBlurringCanvas;

    @Nullable
    private View mDecorView;
    private int mDefaultOverlayColor;
    private boolean mDifferentRoot;
    private float mDownsampleFactor;
    private int mHorizontalSpacing;
    private boolean mIsRendering;
    private boolean mIsWorking;
    private long mLastPreDrawTime;
    private int mOverlayColor;
    private Paint mPaint;

    @Nullable
    private Bitmap mPreBitmap;
    private Canvas mPreCanvas;
    private float mPreDiffRate;
    private long mPreDrawIntervalTime;
    private Paint mPrePaint;
    private final Rect mRectDst;
    private final Rect mRectSrc;
    private int mVerticalSpacing;

    @NonNull
    private final ViewTreeObserver.OnPreDrawListener preDrawListener;

    @SuppressLint({"ResourceType"})
    public RealtimeEffectView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDefaultOverlayColor = -1;
        this.mBitmapToBlur = null;
        this.mBlurredBitmap = null;
        this.mBlurringCanvas = null;
        this.mRectSrc = new Rect();
        this.mRectDst = new Rect();
        this.mPreBitmap = null;
        this.mPreDiffRate = 0.0f;
        this.mIsWorking = true;
        this.mPreDrawIntervalTime = 0L;
        this.mLastPreDrawTime = 0L;
        this.preDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: com.zte.mifavor.widget.RealtimeEffectView.1
            /* JADX WARN: Code restructure failed: missing block: B:47:0x01bd, code lost:
            
                if (r13.f17741c.mBlurringCanvas != null) goto L51;
             */
            /* JADX WARN: Code restructure failed: missing block: B:48:0x01bf, code lost:
            
                r13.f17741c.mBlurringCanvas.restoreToCount(r0);
             */
            /* JADX WARN: Code restructure failed: missing block: B:50:0x01ea, code lost:
            
                if (r13.f17741c.mBitmapToBlur == null) goto L59;
             */
            /* JADX WARN: Code restructure failed: missing block: B:51:0x01ec, code lost:
            
                r13 = r13.f17741c;
                r13.u(r13.mBitmapToBlur);
             */
            /* JADX WARN: Code restructure failed: missing block: B:53:0x01fa, code lost:
            
                if (com.zte.mifavor.widget.RealtimeEffectView.DEBUG == false) goto L69;
             */
            /* JADX WARN: Code restructure failed: missing block: B:54:0x01fc, code lost:
            
                android.util.Log.w(com.zte.mifavor.widget.RealtimeEffectView.TAG, "onPreDraw error, mBitmapToBlur is null");
             */
            /* JADX WARN: Code restructure failed: missing block: B:60:0x01e1, code lost:
            
                if (r13.f17741c.mBlurringCanvas == null) goto L56;
             */
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public boolean onPreDraw() {
                /*
                    Method dump skipped, instructions count: 588
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.zte.mifavor.widget.RealtimeEffectView.AnonymousClass1.onPreDraw():boolean");
            }
        };
        this.mHorizontalSpacing = 10;
        this.mVerticalSpacing = 7;
        this.mAppDecorView = null;
        if (Utils.f17815b) {
            setForceDarkAllowed(false);
        }
        int color = context.getResources().getColor(R.color.mfv_effect_view_overlay);
        try {
            Drawable drawable = context.getResources().getDrawable(R.drawable.bottombar_tab_bg);
            Log.d(TAG, "Realtime Effect View in, use drawable bottombar_tab_bg, bottombarDrawable=" + drawable);
            if (drawable instanceof BitmapDrawable) {
                this.mDefaultOverlayColor = o(R.drawable.bottombar_tab_bg);
                Log.d(TAG, "Realtime Effect View in, use BitmapDrawable bottombar_tab_bg. mDefaultOverlayColor=" + this.mDefaultOverlayColor);
            } else if (drawable instanceof ColorDrawable) {
                this.mDefaultOverlayColor = ((ColorDrawable) drawable).getColor();
                Log.d(TAG, "Realtime Effect View in, use ColorDrawable bottombar_tab_bg. mDefaultOverlayColor=" + this.mDefaultOverlayColor);
            }
            int i2 = this.mDefaultOverlayColor;
            if (i2 == color || i2 == -1) {
                this.mDefaultOverlayColor = context.getResources().getColor(R.color.bottombar_tab_bg);
                Log.d(TAG, "Realtime Effect View in, use color bottombar_tab_bg. mDefaultOverlayColor=" + this.mDefaultOverlayColor);
            }
        } catch (Exception e2) {
            this.mDefaultOverlayColor = color;
            Log.e(TAG, "Realtime Effect View in, use mDefaultOverlayColor=" + this.mDefaultOverlayColor + ", e = " + e2.toString());
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RealtimeEffectView);
        this.mBlurRadius = obtainStyledAttributes.getDimension(R.styleable.RealtimeEffectView_realtimeBlurRadius, TypedValue.applyDimension(0, 37.0f, context.getResources().getDisplayMetrics()));
        this.mDownsampleFactor = obtainStyledAttributes.getFloat(R.styleable.RealtimeEffectView_realtimeDownsampleFactor, 5.0f);
        this.mOverlayColor = obtainStyledAttributes.getColor(R.styleable.RealtimeEffectView_realtimeOverlayColor, this.mDefaultOverlayColor);
        this.mBackgroundColor = obtainStyledAttributes.getColor(R.styleable.RealtimeEffectView_realtimeBackgroundColor, context.getResources().getColor(R.color.mfvc_bottom_tab_bg_color_disable_anim));
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (displayMetrics != null && (displayMetrics.widthPixels > 2600 || displayMetrics.heightPixels > 2600)) {
            this.mBlurRadius = 30.0f;
        }
        obtainStyledAttributes.recycle();
        this.mPaint = new Paint();
        this.mPrePaint = new Paint();
        if (Utils.f17815b) {
            setForceDarkAllowed(false);
        }
        Log.d(TAG, "Realtime Effect View out. mBlurRadius=" + this.mBlurRadius + ", mOverlayColor=" + this.mOverlayColor + ", mBackgroundColor=" + this.mBackgroundColor);
    }

    private boolean p(Context context) {
        int i2 = Settings.Global.getInt(context.getContentResolver(), DISPLAY_GAUSSIAN_BLUR, 0);
        Log.d(TAG, "is Display Gaussian Blur. isOpened = " + i2);
        return i2 == 0;
    }

    private boolean q(Bitmap bitmap, Bitmap bitmap2) {
        float f2;
        if (bitmap == null || bitmap2 == null) {
            if (DEBUG) {
                Log.e(TAG, "is Equals, b1 or b2 is null.");
            }
            return false;
        }
        if (bitmap.getWidth() == bitmap2.getWidth() && bitmap.getHeight() == bitmap2.getHeight()) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (i2 < width) {
                int i5 = 0;
                while (i5 < height) {
                    if (bitmap.getPixel(i2, i5) != bitmap2.getPixel(i2, i5)) {
                        i3++;
                    } else {
                        i4++;
                    }
                    i5 += this.mVerticalSpacing;
                }
                i2 += this.mHorizontalSpacing;
            }
            if (i3 != 0 || i4 != 0) {
                int i6 = i3 + i4;
                float f3 = i3 / i6;
                float abs = Math.abs(f3 - this.mPreDiffRate);
                if (abs < 1.0E-7d) {
                    f2 = abs;
                } else {
                    if (i3 >= i6 * 0.01d) {
                        if (DEBUG) {
                            Log.d(TAG, "is Equals, different bitmap. differentCount=" + i3 + ", sameCount=" + i4 + ", mPreDiffRate=" + this.mPreDiffRate + ", differentRate=" + f3 + ", Rate = " + abs);
                        }
                        this.mPreDiffRate = f3;
                        return false;
                    }
                    f2 = abs;
                }
                if (DEBUG) {
                    Log.d(TAG, "is Equals, similar bitmap. differentCount=" + i3 + ", sameCount=" + i4 + ", mPreDiffRate=" + this.mPreDiffRate + ", differentRate=" + f3 + ", Rate = " + f2);
                }
                this.mPreDiffRate = f3;
                return true;
            }
        }
        if (!DEBUG) {
            return false;
        }
        Log.w(TAG, "is Equals, width and height is different.");
        return false;
    }

    private boolean r() {
        String h2 = Utils.h();
        boolean z = h2 == null || !(h2.contains("PQ82A31") || h2.contains("PQ82A61"));
        Log.d(TAG, "is Support Gaussian Blur. product=" + h2 + ", isSupport=" + z);
        return z;
    }

    private void t() {
        Bitmap bitmap = this.mBitmapToBlur;
        if (bitmap != null) {
            bitmap.recycle();
            this.mBitmapToBlur = null;
        }
        Bitmap bitmap2 = this.mBlurredBitmap;
        if (bitmap2 != null) {
            bitmap2.recycle();
            this.mBlurredBitmap = null;
        }
        Bitmap bitmap3 = this.mPreBitmap;
        if (bitmap3 != null) {
            bitmap3.recycle();
            this.mPreBitmap = null;
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        try {
            if (this.mIsWorking) {
                if (this.mIsRendering) {
                    if (DEBUG) {
                        Log.d(TAG, "draw don't draw views above me.");
                    }
                } else if (RENDERING_COUNT <= 0) {
                    super.draw(canvas);
                } else if (DEBUG) {
                    Log.d(TAG, "draw Doesn't support blurview overlap on another blurview., RENDERING_COUNT = " + RENDERING_COUNT);
                }
            } else if (DEBUG) {
                Log.d(TAG, "draw mIsWorking = " + this.mIsWorking);
            }
        } catch (Exception e2) {
            Log.w(TAG, "draw super error, e = ", e2);
        }
    }

    @Nullable
    protected View getActivityDecorView() {
        Context context = getContext();
        for (int i2 = 0; i2 < 4 && context != null && !(context instanceof Activity) && (context instanceof ContextWrapper); i2++) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (context instanceof Activity) {
            return ((Activity) context).getWindow().getDecorView();
        }
        View view = this.mAppDecorView;
        if (view != null) {
            return view;
        }
        return null;
    }

    public int getDefaultOverlayColor() {
        return this.mDefaultOverlayColor;
    }

    public boolean getIsWorking() {
        return this.mIsWorking;
    }

    public long getPreDrawIntervalTime() {
        return this.mPreDrawIntervalTime;
    }

    @NonNull
    public ViewTreeObserver.OnPreDrawListener getPreDrawListener() {
        Log.d(TAG, "get Pre Draw Listener = " + this.preDrawListener);
        return this.preDrawListener;
    }

    @Override // android.view.View
    public boolean isClickable() {
        return true;
    }

    @Override // android.view.View
    public boolean isLongClickable() {
        return true;
    }

    protected void n(Canvas canvas, Bitmap bitmap, int i2) {
        if (bitmap != null) {
            this.mRectSrc.right = bitmap.getWidth();
            this.mRectSrc.bottom = bitmap.getHeight();
            this.mRectDst.right = getWidth();
            this.mRectDst.bottom = getHeight();
            canvas.drawBitmap(bitmap, this.mRectSrc, this.mRectDst, (Paint) null);
        }
        this.mPaint.setColor(i2);
        canvas.drawRect(this.mRectDst, this.mPaint);
        if (DEBUG) {
            Log.d(TAG, "draw Blurred Bitmap out.");
        }
    }

    public int o(int i2) {
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), i2);
        if (decodeResource == null) {
            return -1;
        }
        int width = decodeResource.getWidth();
        int height = decodeResource.getHeight();
        int i3 = width * height;
        int[] iArr = new int[i3];
        decodeResource.getPixels(iArr, 0, width, 0, 0, width, height);
        HashMap hashMap = new HashMap();
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = iArr[i5] & 16777215;
            if (hashMap.containsKey(Integer.valueOf(i6))) {
                hashMap.put(Integer.valueOf(i6), Integer.valueOf(((Integer) hashMap.get(Integer.valueOf(i6))).intValue() + 1));
            } else {
                hashMap.put(Integer.valueOf(i6), 1);
            }
        }
        int i7 = 0;
        for (Map.Entry entry : hashMap.entrySet()) {
            int intValue = ((Integer) entry.getKey()).intValue();
            int intValue2 = ((Integer) entry.getValue()).intValue();
            if (intValue2 > i7) {
                i7 = intValue2;
                i4 = intValue;
            }
        }
        return (-352321536) | i4;
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!r()) {
            Log.d(TAG, "on Attached To Window, does not support Gaussian Blur, mBackgroundColor=" + this.mBackgroundColor);
            setBackgroundColor(this.mBackgroundColor);
            return;
        }
        if (!p(getContext())) {
            Log.d(TAG, "on Attached To Window, display_gaussian_blur is 0..., mBackgroundColor=" + this.mBackgroundColor);
            setBackgroundColor(this.mBackgroundColor);
            return;
        }
        if (!Utils.n()) {
            Log.d(TAG, "on Attached To Window, myos_feature_complex_animation is false and do nothing, mBackgroundColor=" + this.mBackgroundColor);
            setBackgroundColor(this.mBackgroundColor);
            return;
        }
        if (Utils.p()) {
            Log.d(TAG, "on Attached To Window, myos_feature_low_end_phone is true and do nothing, mBackgroundColor=" + this.mBackgroundColor);
            setBackgroundColor(this.mBackgroundColor);
            return;
        }
        View view = this.mDecorView;
        if (view == null) {
            view = getActivityDecorView();
        }
        this.mDecorView = view;
        Log.d(TAG, "on Attached To Window in. mDecorView = " + this.mDecorView + ", mIsSDK31Above=" + mIsSDK31Above);
        if (this.mDecorView != null) {
            if (mIsSDK31Above) {
                try {
                    Log.d(TAG, "on Attached To Window. remove On Pre Draw Listener, mDecorView = " + this.mDecorView);
                    this.mDecorView.getViewTreeObserver().removeOnPreDrawListener(this.preDrawListener);
                } catch (Exception e2) {
                    Log.w(TAG, "on Attached To Window. remove On Pre Draw Listener error, e=" + e2);
                }
                Log.d(TAG, "on Attached To Window. add OnPre Draw Listener mDecorView = " + this.mDecorView);
                this.mDecorView.getViewTreeObserver().addOnPreDrawListener(this.preDrawListener);
            } else {
                setAlpha(0.0f);
            }
            boolean z = this.mDecorView.getRootView() != getRootView();
            this.mDifferentRoot = z;
            if (z) {
                this.mDecorView.postInvalidate();
            }
        } else {
            this.mDifferentRoot = false;
        }
        Log.d(TAG, "on Attached To Window out. mDifferentRoot = " + this.mDifferentRoot);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        if (!r()) {
            Log.d(TAG, "on Detached From Window, does not support Gaussian Blur.");
            return;
        }
        if (!p(getContext())) {
            Log.d(TAG, "on Detached From Window, display_gaussian_blur is 0.");
            return;
        }
        if (!Utils.n()) {
            super.onDetachedFromWindow();
            Log.d(TAG, "on Detached From Window, myos_feature_complex_animation is false and do nothing.");
            return;
        }
        if (Utils.p()) {
            super.onDetachedFromWindow();
            Log.d(TAG, "on Detached From Window, myos_feature_low_end_phone is true and do nothing.");
            return;
        }
        Log.d(TAG, "on Detached From Window in. mIsSDK31Above = " + mIsSDK31Above);
        if (this.mDecorView != null && mIsSDK31Above) {
            try {
                Log.d(TAG, "on Detached From Window. remove On Pre Draw Listener, mDecorView = " + this.mDecorView);
                this.mDecorView.getViewTreeObserver().removeOnPreDrawListener(this.preDrawListener);
            } catch (Exception e2) {
                Log.w(TAG, "remove On Pre Draw Listener error, e=" + e2);
            }
        }
        t();
        super.onDetachedFromWindow();
        Log.d(TAG, "on Detached From Window out.");
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.mIsWorking) {
            if (DEBUG) {
                Log.d(TAG, "on Draw mIsWorking = " + this.mIsWorking);
                return;
            }
            return;
        }
        if (!mIsSDK31Above) {
            if (DEBUG) {
                Log.d(TAG, "on Draw isSDK31below.");
            }
        } else if (this.mBlurredBitmap != null) {
            if (DEBUG) {
                Log.d(TAG, "on Draw draw Blurred Bitmap.");
            }
            n(canvas, this.mBlurredBitmap, this.mOverlayColor);
        }
    }

    protected boolean s() {
        if (!mIsSDK31Above) {
            if (DEBUG) {
                Log.d(TAG, " return false in prepare isSDK31below. ");
            }
            return false;
        }
        if (this.mBlurRadius == 0.0f) {
            t();
            Log.w(TAG, "prepare out. mBlurRadius = " + this.mBlurRadius);
            return false;
        }
        int width = getWidth();
        int height = getHeight();
        int max = Math.max(1, (int) (width / this.mDownsampleFactor));
        int max2 = Math.max(1, (int) (height / this.mDownsampleFactor));
        if (DEBUG) {
            Log.w(TAG, "prepare in. width=" + width + ", height=" + height + ", scaledWidth=" + max + ", scaledHeight=" + max2);
        }
        if (this.mBlurringCanvas == null || this.mBlurredBitmap == null || this.mPreBitmap == null) {
            t();
            try {
                try {
                    Bitmap.Config config = Bitmap.Config.ARGB_8888;
                    Bitmap createBitmap = Bitmap.createBitmap(max, max2, config);
                    this.mBitmapToBlur = createBitmap;
                    if (createBitmap == null) {
                        if (DEBUG) {
                            Log.e(TAG, "prepare mBitmapToBlur is null.");
                        }
                        return false;
                    }
                    this.mBlurringCanvas = new Canvas(this.mBitmapToBlur);
                    Bitmap createBitmap2 = Bitmap.createBitmap(max, max2, config);
                    this.mBlurredBitmap = createBitmap2;
                    if (createBitmap2 == null) {
                        if (DEBUG) {
                            Log.e(TAG, "prepare mBlurredBitmap is null.");
                        }
                        return false;
                    }
                    Bitmap createBitmap3 = Bitmap.createBitmap(this.mBitmapToBlur.getWidth(), this.mBitmapToBlur.getHeight(), this.mBitmapToBlur.getConfig());
                    this.mPreBitmap = createBitmap3;
                    if (createBitmap3 == null) {
                        if (DEBUG) {
                            Log.e(TAG, "prepare mPreBitmap is null.");
                        }
                        return false;
                    }
                    this.mPreCanvas = new Canvas(this.mPreBitmap);
                } catch (OutOfMemoryError e2) {
                    Log.e(TAG, "prepare error. e = ", e2);
                    t();
                    if (DEBUG) {
                        Log.d(TAG, "prepare init out error. return false.");
                    }
                    return false;
                }
            } finally {
                t();
            }
        }
        if (DEBUG) {
            Log.d(TAG, "prepare init out. return true.");
        }
        return true;
    }

    public void setBlurRadius(float f2) {
        Log.d(TAG, "setBlurRadius in.");
        double abs = Math.abs(this.mBlurRadius) - Math.abs(f2);
        if (abs < -0.1d || abs > 0.1d) {
            this.mBlurRadius = f2;
            invalidate();
        }
    }

    public void setDEBUG(boolean z) {
        DEBUG = z;
    }

    public void setDecorView(View view) {
        Log.d(TAG, "setDecorView in. view=" + view + ", mDecorView=" + this.mDecorView);
        try {
            Log.d(TAG, "setDecorView. remove OnPre Draw Listener. mDecorView = " + this.mDecorView);
            this.mDecorView.getViewTreeObserver().removeOnPreDrawListener(this.preDrawListener);
        } catch (Exception e2) {
            Log.w(TAG, "setDecorView. remove On Pre Draw Listener error, e=" + e2);
        }
        this.mAppDecorView = view;
        this.mDecorView = view;
        Log.d(TAG, "setDecorView. mDecorView=" + this.mDecorView + ", mAppDecorView=" + this.mAppDecorView + ", mIsSDK31Above=" + mIsSDK31Above);
        if (this.mDecorView != null) {
            if (mIsSDK31Above) {
                Log.d(TAG, "setDecorView. add OnPre Draw Listener mDecorView = " + this.mDecorView);
                this.mDecorView.getViewTreeObserver().addOnPreDrawListener(this.preDrawListener);
            } else {
                setAlpha(0.0f);
            }
            boolean z = this.mDecorView.getRootView() != getRootView();
            this.mDifferentRoot = z;
            if (z) {
                this.mDecorView.postInvalidate();
            }
        } else {
            this.mDifferentRoot = false;
        }
        Log.d(TAG, "setDecorView out. mDifferentRoot=" + this.mDifferentRoot);
    }

    public void setDownsampleFactor(float f2) {
        if (f2 <= 0.0f) {
            Log.w(TAG, "Downsample factor must be greater than 0.");
            return;
        }
        double abs = Math.abs(f2) - Math.abs(this.mDownsampleFactor);
        if (abs < -0.1d || abs > 0.1d) {
            this.mDownsampleFactor = f2;
            t();
            invalidate();
        }
    }

    public void setIsWorking(boolean z) {
        this.mIsWorking = z;
    }

    public void setOverlayColor(int i2) {
        if (DEBUG) {
            Log.d(TAG, "setOverlayColor in. color=" + i2 + ", mOverlayColor=" + this.mOverlayColor);
        }
        if (this.mOverlayColor != i2) {
            this.mOverlayColor = i2;
            invalidate();
        }
    }

    public void setPreDrawIntervalTime(long j2) {
        this.mPreDrawIntervalTime = j2;
    }

    protected void u(Bitmap bitmap) {
        if (!mIsSDK31Above) {
            if (DEBUG) {
                Log.d(TAG, "render Effect isSDK31below.");
                return;
            }
            return;
        }
        if (bitmap == null) {
            if (DEBUG) {
                Log.e(TAG, "render Effect, bitmapToBlur is null.");
                return;
            }
            return;
        }
        if (!q(this.mPreBitmap, bitmap)) {
            BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
            if (DEBUG) {
                Log.d(TAG, "render Effect, setBackground mBlurRadius = " + this.mBlurRadius);
            }
            setBackground(bitmapDrawable);
            float f2 = this.mBlurRadius;
            setRenderEffect(RenderEffect.createBlurEffect(f2, f2, Shader.TileMode.MIRROR));
        } else if (DEBUG) {
            Log.w(TAG, "render Effect, similar bitmap and do nothing.");
        }
        this.mPreCanvas.drawBitmap(bitmap, new Matrix(), this.mPrePaint);
    }
}
