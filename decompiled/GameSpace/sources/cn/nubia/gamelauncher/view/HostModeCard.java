package cn.nubia.gamelauncher.view;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.recycler.Anim3DHelper;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* loaded from: classes.dex */
public class HostModeCard extends ConstraintLayout {
    ObjectAnimator mAnimator;
    ImageView mBg;
    int mFocusCount;
    TextView mGameName;
    TextView mManagerGame;
    int mRecentPadding;
    ImageView mTotalLogo;
    TextView mTotalTime;

    public HostModeCard(Context context) {
        this(context, null);
    }

    public HostModeCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRecentPadding = 3;
        this.mFocusCount = 0;
    }

    private void initChild() {
        this.mManagerGame = (TextView) findViewById(R.id.host_game_manager_game);
        this.mGameName = (TextView) findViewById(R.id.host_game_name);
        this.mTotalTime = (TextView) findViewById(R.id.host_game_total_time);
        this.mTotalLogo = (ImageView) findViewById(R.id.host_game_total_logo);
        this.mBg = (ImageView) findViewById(R.id.host_game_item_bg);
    }

    private void onFocusEnter() {
        int i = this.mFocusCount + 1;
        this.mFocusCount = i;
        if (1 < i) {
            return;
        }
        doAnimator(this, false);
        onHoverEnter();
    }

    private void onFocusExit() {
        int i = this.mFocusCount - 1;
        this.mFocusCount = i;
        if (i > 0) {
            return;
        }
        doAnimator(this, true);
        onHoverExit();
    }

    private void onHoverEnter() {
        if (isNotGameCard()) {
            setBackgroundResource(R.drawable.host_mode_hover_frame);
            int i = this.mRecentPadding;
            setPadding(i, i, i, i);
        } else {
            this.mManagerGame.setTextColor(Color.argb(217, 0, 0, 0));
            this.mGameName.setTextColor(Color.argb(217, 0, 0, 0));
            this.mTotalTime.setTextColor(Color.argb(WorkQueueKt.MASK, 0, 0, 0));
            this.mBg.setImageResource(R.mipmap.host_game_item_focus_bg);
            this.mTotalLogo.setImageResource(R.mipmap.host_game_total_logo_focus);
        }
    }

    private void onHoverExit() {
        if (isNotGameCard()) {
            setBackgroundColor(0);
            setPadding(0, 0, 0, 0);
            return;
        }
        this.mManagerGame.setTextColor(getResources().getColor(R.color.guide_title_color));
        this.mGameName.setTextColor(getResources().getColor(R.color.guide_title_color));
        this.mTotalTime.setTextColor(getResources().getColor(R.color.host_game_total_time_color));
        this.mBg.setImageResource(R.mipmap.host_game_item_bg);
        this.mTotalLogo.setImageResource(R.mipmap.host_game_total_logo);
    }

    public void doAnimator(View view, boolean z) {
        ObjectAnimator objectAnimator = this.mAnimator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        float scaleX = z ? view.getScaleX() : 1.0f;
        float f = z ? 1.0f : 1.05f;
        long j = z ? 100L : 200L;
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat((Property<?, Float>) View.SCALE_X, scaleX, f), PropertyValuesHolder.ofFloat((Property<?, Float>) View.SCALE_Y, scaleX, f));
        this.mAnimator = ofPropertyValuesHolder;
        ofPropertyValuesHolder.setInterpolator(Anim3DHelper.PATH_INTERPOLATOR_CARD_REBOUND);
        this.mAnimator.setDuration(j);
        this.mAnimator.start();
    }

    boolean isNotGameCard() {
        return this.mManagerGame == null || this.mGameName == null || this.mBg == null || this.mTotalTime == null || this.mTotalLogo == null;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        initChild();
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (z) {
            onFocusEnter();
        } else {
            onFocusExit();
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            onFocusEnter();
            return false;
        }
        if (actionMasked != 10) {
            return false;
        }
        onFocusExit();
        return false;
    }
}
