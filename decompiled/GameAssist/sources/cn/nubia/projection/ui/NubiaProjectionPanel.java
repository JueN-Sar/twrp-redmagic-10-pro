package cn.nubia.projection.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import cn.nubia.projection.ProjectionUIController;
import cn.nubia.projection.R;
import cn.nubia.projection.util.PLog;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class NubiaProjectionPanel extends FrameLayout implements View.OnClickListener {
    protected CircleImageView mExpandedAppIcon;
    private ProjectionWindowView mHost;
    private ImageView mProjectionIcon;
    private ProjectionUIController mUIControl;

    public NubiaProjectionPanel(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void h() {
        this.mProjectionIcon = (ImageView) findViewById(R.id.iv_projection_icon);
        CircleImageView circleImageView = (CircleImageView) findViewById(R.id.iv_expanded_app_icon);
        this.mExpandedAppIcon = circleImageView;
        circleImageView.setVisibility(8);
        this.mProjectionIcon.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(boolean z, ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        if (z) {
            floatValue = -floatValue;
        }
        setTranslationX(floatValue);
        setAlpha(valueAnimator.getAnimatedFraction());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(boolean z, ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        if (z) {
            floatValue = -floatValue;
        }
        setTranslationX(floatValue);
        setAlpha(1.0f - valueAnimator.getAnimatedFraction());
    }

    public void e(PrintWriter printWriter, String[] strArr) {
        printWriter.println("Nubia NubiaProjectionPanel Status:");
        printWriter.println("    TranslationX:" + getTranslationX());
        printWriter.println("    Visibility:" + getVisibility());
    }

    public void f() {
        if (this.mUIControl.v0()) {
            this.mExpandedAppIcon.setVisibility(8);
            return;
        }
        this.mProjectionIcon.setImageDrawable(ContextCompat.e(getContext(), R.drawable.projection_view_normal_click_bg));
        if (this.mUIControl.t0()) {
            this.mExpandedAppIcon.setVisibility(0);
        } else {
            this.mExpandedAppIcon.setVisibility(8);
        }
    }

    public void g() {
        f();
    }

    public void k() {
        final boolean F = this.mHost.F();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(this.mHost.getWidth(), 0.0f);
        ofFloat.setDuration(250L);
        ofFloat.setStartDelay(200L);
        ofFloat.setInterpolator(ProjectionWindowView.FAST_OUT_SLOW_IN);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.projection.ui.o
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                NubiaProjectionPanel.this.i(F, valueAnimator);
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: cn.nubia.projection.ui.NubiaProjectionPanel.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator, boolean z) {
                NubiaProjectionPanel.this.mHost.setAnimationPlaying(false);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator, boolean z) {
                NubiaProjectionPanel.this.mHost.setAnimationPlaying(true);
                NubiaProjectionPanel.this.setVisibility(0);
            }
        });
        ofFloat.start();
    }

    public void l() {
        this.mHost.setAnimationPlaying(true);
        final boolean F = this.mHost.F();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, this.mHost.getWidth());
        ofFloat.setDuration(250L);
        ofFloat.setInterpolator(ProjectionWindowView.FAST_OUT_SLOW_IN);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.projection.ui.n
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                NubiaProjectionPanel.this.j(F, valueAnimator);
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: cn.nubia.projection.ui.NubiaProjectionPanel.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator, boolean z) {
                NubiaProjectionPanel.this.mHost.setAnimationPlaying(false);
                NubiaProjectionPanel.this.setVisibility(8);
                NubiaProjectionPanel.this.mHost.W();
                NubiaProjectionPanel.this.mUIControl.T0();
            }
        });
        ofFloat.start();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        this.mHost.a0();
        if (id == R.id.iv_projection_icon) {
            this.mHost.r();
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        h();
    }

    public void setExpandedAppIcon(Drawable drawable) {
        if (drawable != null) {
            this.mExpandedAppIcon.setImageDrawable(drawable);
        } else {
            PLog.a("setAppIcon null");
        }
    }

    public void setProjectionUIControl(ProjectionUIController projectionUIController) {
        this.mUIControl = projectionUIController;
    }

    public void setupHost(ProjectionWindowView projectionWindowView) {
        this.mHost = projectionWindowView;
    }

    public NubiaProjectionPanel(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public NubiaProjectionPanel(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }
}
