package cn.nubia.screensaver.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cn.nubia.gameassist.R;
import cn.nubia.screensaver.GameScreensaverManager;
import cn.nubia.screensaver.bean.MomentBean;
import cn.nubia.screensaver.view.MomentVideoView;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class MomentBanner extends FrameLayout {
    private static final int PIC_PLAY_DURATION = 5000;
    private static final String TAG = "MomentBanner";
    private static final int VIDEO_PLAY_DURATION = 15000;
    private final int mBlurRadius;
    private final int mContentHeight;
    private final int mContentWidth;
    private final Handler mHandler;
    private View mHideView;
    private final List<MomentBean> mMoments;
    private PageChangeListener mPageChangeListener;
    private Paint mPaint;
    private boolean mPause;
    private ValueAnimator mScrollAnimator;
    private final int mShadeWidth;
    private int mShowIndex;
    private View mShowView;
    private final Runnable mTimerRunnable;

    public interface PageChangeListener {
        void a(String str);
    }

    public MomentBanner(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMoments = new ArrayList();
        this.mPause = true;
        this.mTimerRunnable = new Runnable() { // from class: cn.nubia.screensaver.view.MomentBanner.3
            @Override // java.lang.Runnable
            public void run() {
                GaLog.a(MomentBanner.TAG, "banner time runnable " + MomentBanner.this.mPause);
                if (MomentBanner.this.mPause || MomentBanner.this.getChildCount() <= 1) {
                    return;
                }
                MomentBanner.this.t(0.0f, r2.mContentWidth);
            }
        };
        this.mHandler = GameScreensaverManager.L().C();
        this.mBlurRadius = context.getResources().getDimensionPixelOffset(R.dimen.moment_card_shade_blur_size);
        this.mShadeWidth = context.getResources().getDimensionPixelOffset(R.dimen.moment_card_shade_width);
        this.mContentWidth = context.getResources().getDimensionPixelOffset(R.dimen.moment_card_content_width);
        this.mContentHeight = context.getResources().getDimensionPixelOffset(R.dimen.moment_card_content_height);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.mShowIndex = n(this.mShowIndex);
        if (getChildAt(0) != null) {
            removeViewAt(0);
        }
        h(this.mMoments.get(n(this.mShowIndex)));
        v();
        q();
        PageChangeListener pageChangeListener = this.mPageChangeListener;
        if (pageChangeListener != null) {
            pageChangeListener.a(this.mMoments.get(this.mShowIndex).a());
        }
        this.mScrollAnimator = null;
        u();
    }

    private FrameLayout.LayoutParams getChildLayoutParams() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.mContentWidth, this.mContentHeight);
        layoutParams.gravity = 17;
        int i2 = this.mShadeWidth;
        layoutParams.leftMargin = i2;
        layoutParams.topMargin = i2;
        layoutParams.rightMargin = i2;
        layoutParams.bottomMargin = i2;
        return layoutParams;
    }

    private int getDelayTime() {
        return k() ? VIDEO_PLAY_DURATION : PIC_PLAY_DURATION;
    }

    private void h(MomentBean momentBean) {
        if (momentBean.d()) {
            MomentVideoView momentVideoView = new MomentVideoView(getContext());
            momentVideoView.setPath(momentBean.b());
            momentVideoView.setTag(momentBean.a());
            momentVideoView.e(new MomentVideoView.OnCompletionListener() { // from class: cn.nubia.screensaver.view.c
                @Override // cn.nubia.screensaver.view.MomentVideoView.OnCompletionListener
                public final void onCompletion(MediaPlayer mediaPlayer) {
                    MomentBanner.this.m(mediaPlayer);
                }
            });
            addView(momentVideoView, getChildLayoutParams());
            return;
        }
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageBitmap(BitmapFactory.decodeFile(momentBean.b()));
        imageView.setTag(momentBean.a());
        addView(imageView, getChildLayoutParams());
    }

    private void i() {
        ValueAnimator valueAnimator = this.mScrollAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.mScrollAnimator = null;
        }
    }

    private void j() {
        removeAllViews();
        this.mShowIndex = 0;
        if (this.mMoments.isEmpty()) {
            return;
        }
        if (this.mMoments.size() == 1) {
            h(this.mMoments.get(this.mShowIndex));
        } else {
            h(this.mMoments.get(this.mShowIndex));
            h(this.mMoments.get(this.mShowIndex + 1));
        }
        v();
        q();
        PageChangeListener pageChangeListener = this.mPageChangeListener;
        if (pageChangeListener != null) {
            pageChangeListener.a(this.mMoments.get(this.mShowIndex).a());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean k() {
        return getChildAt(0) instanceof MomentVideoView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(MediaPlayer mediaPlayer) {
        if (l() || this.mPause) {
            return;
        }
        this.mHandler.removeCallbacks(this.mTimerRunnable);
        this.mHandler.post(this.mTimerRunnable);
    }

    private int n(int i2) {
        int i3 = i2 + 1;
        if (i3 < this.mMoments.size()) {
            return i3;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(float f2) {
        View view = this.mShowView;
        if (view != null) {
            view.setTranslationX(-f2);
        }
        View view2 = this.mHideView;
        if (view2 != null) {
            view2.setTranslationX(this.mContentWidth - f2);
        }
    }

    private void q() {
        if (k()) {
            ((MomentVideoView) getChildAt(0)).m();
        }
    }

    private void u() {
        this.mHandler.removeCallbacks(this.mTimerRunnable);
        if (getChildCount() > 1) {
            this.mHandler.postDelayed(this.mTimerRunnable, getDelayTime());
        }
    }

    public boolean l() {
        ValueAnimator valueAnimator = this.mScrollAnimator;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    public void o() {
        r();
        removeAllViews();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i2 = this.mBlurRadius;
        canvas.drawRect(i2, i2, getWidth() - this.mBlurRadius, getHeight() - this.mBlurRadius, this.mPaint);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setColor(Color.parseColor("#FF65FFF4"));
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setAntiAlias(true);
        try {
            this.mPaint.setMaskFilter(new BlurMaskFilter(this.mBlurRadius, BlurMaskFilter.Blur.OUTER));
        } catch (Exception e2) {
            GaLog.a(TAG, "create blur mask filter error " + e2);
        }
    }

    public void r() {
        this.mPause = true;
        this.mHandler.removeCallbacks(this.mTimerRunnable);
        i();
        if (k()) {
            ((MomentVideoView) getChildAt(0)).k();
        }
    }

    public void s() {
        this.mPause = false;
        u();
        if (k()) {
            ((MomentVideoView) getChildAt(0)).m();
        }
    }

    public void setData(List<MomentBean> list) {
        if (list == null || list.isEmpty() || !this.mMoments.isEmpty()) {
            return;
        }
        this.mMoments.addAll(list);
        j();
    }

    public void setPageChangeListener(PageChangeListener pageChangeListener) {
        this.mPageChangeListener = pageChangeListener;
    }

    public void t(float f2, float f3) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f2, f3);
        this.mScrollAnimator = ofFloat;
        ofFloat.setDuration(200L);
        this.mScrollAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.screensaver.view.MomentBanner.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                MomentBanner.this.p(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        this.mScrollAnimator.addListener(new AnimatorListenerAdapter() { // from class: cn.nubia.screensaver.view.MomentBanner.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                MomentBanner.this.g();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                if (MomentBanner.this.k()) {
                    ((MomentVideoView) MomentBanner.this.getChildAt(0)).k();
                }
            }
        });
        this.mScrollAnimator.start();
    }

    public void v() {
        if (getChildCount() > 1) {
            this.mShowView = getChildAt(0);
            this.mHideView = getChildAt(1);
            this.mShowView.setTranslationX(0.0f);
            this.mHideView.setTranslationX(this.mContentWidth + this.mShadeWidth);
        }
    }
}
