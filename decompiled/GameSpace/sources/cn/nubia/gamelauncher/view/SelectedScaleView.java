package cn.nubia.gamelauncher.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.common.view.ProgressView;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.anim.AnimBean;
import cn.nubia.gamelauncher.anim.AnimHelper;
import cn.nubia.gamelauncher.helper.Controller;

/* loaded from: classes.dex */
public class SelectedScaleView extends ConstraintLayout {
    private final int TYPE_SELECTED;
    AnimatorSet mAnimatorSet;
    ImageView mBg;
    TextView mDays;
    ImageView mFrame;
    ImageView mIcon;
    boolean mIsSelected;
    boolean mIsSupportProperty;
    TextView mName;
    ProgressView mProgress;
    TextView mSize;
    TextView mTime;
    ImageView mVip;

    public SelectedScaleView(Context context) {
        this(context, null);
    }

    public SelectedScaleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TYPE_SELECTED = 0;
        this.mIsSelected = false;
        this.mIsSupportProperty = false;
    }

    private void doScale() {
        if (CommonUtil.isSlenderPhone()) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                childAt.setScaleX(0.95f);
                childAt.setScaleY(0.95f);
            }
        }
    }

    private void initChild() {
        this.mIcon = (ImageView) findViewById(R.id.icon);
        this.mBg = (ImageView) findViewById(R.id.item_selected_frame);
        this.mFrame = (ImageView) findViewById(R.id.game_selected_frame);
        this.mProgress = (ProgressView) findViewById(R.id.icon_progress);
        this.mVip = (ImageView) findViewById(R.id.pure_vip);
        this.mName = (TextView) findViewById(R.id.full_item_game_name);
        this.mTime = (TextView) findViewById(R.id.property_time_value);
        this.mSize = (TextView) findViewById(R.id.property_size_value);
        this.mDays = (TextView) findViewById(R.id.property_days_value);
    }

    private boolean isPure() {
        return Controller.getInstance().isPureMode();
    }

    private void updateParams(boolean z) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);
        int i = z ? 18 : 0;
        constraintSet.setMargin(this.mIcon.getId(), 1, i);
        constraintSet.setMargin(this.mIcon.getId(), 2, i);
        constraintSet.setMargin(this.mIcon.getId(), 3, i - 1);
        constraintSet.setMargin(this.mIcon.getId(), 4, i + 1);
        int i2 = z ? 15 : 30;
        constraintSet.setMargin(this.mFrame.getId(), 1, z ? 29 : 23);
        constraintSet.setMargin(this.mFrame.getId(), 3, i2);
        constraintSet.setMargin(this.mFrame.getId(), 4, i2);
        constraintSet.applyTo(this);
    }

    public void doAnimator() {
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        ObjectAnimator createPropertyAnim = AnimHelper.createPropertyAnim(this.mBg, 300, new AnimBean(View.TRANSLATION_X, isPure() ? -120.0f : -200.0f, 0.0f), new AnimBean(View.SCALE_X, 0.2f, 1.0f));
        AnimBean animBean = new AnimBean(View.TRANSLATION_X, 200.0f, 0.0f);
        AnimBean animBean2 = new AnimBean(View.ALPHA, 0.0f, 1.0f);
        ObjectAnimator createPropertyAnim2 = AnimHelper.createPropertyAnim(this.mName, 300, animBean, animBean2);
        ObjectAnimator createPropertyAnim3 = AnimHelper.createPropertyAnim(this.mTime, 300, animBean, animBean2);
        ObjectAnimator createPropertyAnim4 = AnimHelper.createPropertyAnim(this.mSize, 300, animBean, animBean2);
        ObjectAnimator createPropertyAnim5 = AnimHelper.createPropertyAnim(this.mDays, 300, animBean, animBean2);
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.mAnimatorSet = animatorSet2;
        animatorSet2.play(createPropertyAnim2).with(createPropertyAnim).with(createPropertyAnim3).with(createPropertyAnim4).with(createPropertyAnim5);
        this.mAnimatorSet.start();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        initChild();
        doScale();
    }

    public void onSelected(boolean z) {
        boolean z2 = z && this.mIsSupportProperty;
        if (z) {
            doAnimator();
        }
        this.mDays.setVisibility(z2 ? 0 : 8);
        this.mTime.setVisibility(z2 ? 0 : 8);
        this.mSize.setVisibility(z2 ? 0 : 8);
        this.mName.setVisibility((!isPure() || z) ? 0 : 8);
        this.mBg.setVisibility((!isPure() || z) ? 0 : 8);
        this.mVip.setAlpha((isPure() && z) ? 1.0f : 0.0f);
        this.mName.setTranslationY(z2 ? -getResources().getDimensionPixelOffset(R.dimen.game_name_trans_y) : 0.0f);
        this.mFrame.setVisibility(z ? 0 : 4);
        if (!isPure()) {
            this.mBg.setBackgroundResource(z ? R.mipmap.game_lobby_item_selected : R.mipmap.game_lobby_item_unselected);
            return;
        }
        this.mBg.setBackgroundResource(R.drawable.pure_item_selected);
        this.mBg.setVisibility(z ? 0 : 8);
        updateParams(z);
        this.mFrame.setBackgroundResource(z ? R.drawable.pure_selected_frame : R.drawable.fram_unselector_for_icon);
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        if (this.mIsSelected == z) {
            return;
        }
        this.mIsSelected = z;
        onSelected(z);
    }

    public void setSupportProperty(boolean z) {
        this.mIsSupportProperty = z;
    }

    public void setType(int i) {
        onSelected(i == 0);
    }
}
