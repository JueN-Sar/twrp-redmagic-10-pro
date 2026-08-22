package androidx.appcompat.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import com.zte.shared.wrapper.WindowManagerWrapper;

@RestrictTo
/* loaded from: classes.dex */
public class ActionBarContextView extends AbsActionBarView {
    private View mClose;
    private View mCloseButton;
    private int mCloseItemLayout;
    private View mCustomView;
    private CharSequence mSubtitle;
    private int mSubtitleStyleRes;
    private TextView mSubtitleView;
    private CharSequence mTitle;
    private LinearLayout mTitleLayout;
    private boolean mTitleOptional;
    private int mTitleStyleRes;
    private TextView mTitleView;

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    private void j() {
        if (this.mTitleLayout == null) {
            LayoutInflater.from(getContext()).inflate(R.layout.abc_action_bar_title_item, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.mTitleLayout = linearLayout;
            this.mTitleView = (TextView) linearLayout.findViewById(R.id.action_bar_title);
            this.mSubtitleView = (TextView) this.mTitleLayout.findViewById(R.id.action_bar_subtitle);
            if (this.mTitleStyleRes != 0) {
                this.mTitleView.setTextAppearance(getContext(), this.mTitleStyleRes);
            }
            if (this.mSubtitleStyleRes != 0) {
                this.mSubtitleView.setTextAppearance(getContext(), this.mSubtitleStyleRes);
            }
        }
        this.mTitleView.setText(this.mTitle);
        this.mSubtitleView.setText(this.mSubtitle);
        boolean z = !TextUtils.isEmpty(this.mTitle);
        boolean z2 = !TextUtils.isEmpty(this.mSubtitle);
        this.mSubtitleView.setVisibility(z2 ? 0 : 8);
        this.mTitleLayout.setVisibility((z || z2) ? 0 : 8);
        if (this.mTitleLayout.getParent() == null) {
            addView(this.mTitleLayout);
        }
    }

    @Override // androidx.appcompat.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ ViewPropertyAnimatorCompat f(int i2, long j2) {
        return super.f(i2, j2);
    }

    @Override // androidx.appcompat.widget.AbsActionBarView
    public boolean g() {
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        if (actionMenuPresenter != null) {
            return actionMenuPresenter.I();
        }
        return false;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // androidx.appcompat.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    @Override // androidx.appcompat.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public void h() {
        if (this.mClose == null) {
            l();
        }
    }

    public void i(final ActionMode actionMode) {
        View view = this.mClose;
        if (view == null) {
            View inflate = LayoutInflater.from(getContext()).inflate(this.mCloseItemLayout, (ViewGroup) this, false);
            this.mClose = inflate;
            addView(inflate);
        } else if (view.getParent() == null) {
            addView(this.mClose);
        }
        View findViewById = this.mClose.findViewById(R.id.action_mode_close_button);
        this.mCloseButton = findViewById;
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: androidx.appcompat.widget.ActionBarContextView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                actionMode.c();
            }
        });
        MenuBuilder menuBuilder = (MenuBuilder) actionMode.e();
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.w();
        }
        ActionMenuPresenter actionMenuPresenter2 = new ActionMenuPresenter(getContext());
        this.mActionMenuPresenter = actionMenuPresenter2;
        actionMenuPresenter2.H(true);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        menuBuilder.c(this.mActionMenuPresenter, this.mPopupContext);
        ActionMenuView actionMenuView = (ActionMenuView) this.mActionMenuPresenter.m(this);
        this.mMenuView = actionMenuView;
        actionMenuView.setBackground(null);
        addView(this.mMenuView, layoutParams);
    }

    public boolean k() {
        return this.mTitleOptional;
    }

    public void l() {
        removeAllViews();
        this.mCustomView = null;
        this.mMenuView = null;
        this.mActionMenuPresenter = null;
        View view = this.mCloseButton;
        if (view != null) {
            view.setOnClickListener(null);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.z();
            this.mActionMenuPresenter.A();
        }
    }

    @Override // androidx.appcompat.widget.AbsActionBarView, android.view.View
    public /* bridge */ /* synthetic */ boolean onHoverEvent(MotionEvent motionEvent) {
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        boolean b2 = ViewUtils.b(this);
        int paddingRight = b2 ? (i4 - i2) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i5 - i3) - getPaddingTop()) - getPaddingBottom();
        View view = this.mClose;
        if (view != null && view.getVisibility() != 8) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mClose.getLayoutParams();
            int i6 = b2 ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i7 = b2 ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int d2 = AbsActionBarView.d(paddingRight, i6, b2);
            paddingRight = AbsActionBarView.d(d2 + e(this.mClose, d2, paddingTop, paddingTop2, b2), i7, b2);
        }
        int i8 = paddingRight;
        LinearLayout linearLayout = this.mTitleLayout;
        if (linearLayout != null && this.mCustomView == null && linearLayout.getVisibility() != 8) {
            i8 += e(this.mTitleLayout, i8, paddingTop, paddingTop2, b2);
        }
        int i9 = i8;
        View view2 = this.mCustomView;
        if (view2 != null) {
            e(view2, i9, paddingTop, paddingTop2, b2);
        }
        int paddingLeft = b2 ? getPaddingLeft() : (i4 - i2) - getPaddingRight();
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView != null) {
            e(actionMenuView, paddingLeft, paddingTop, paddingTop2, !b2);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int i4 = WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME;
        if (mode != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_width=\"match_parent\" (or fill_parent)");
        }
        if (View.MeasureSpec.getMode(i3) == 0) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_height=\"wrap_content\"");
        }
        int size = View.MeasureSpec.getSize(i2);
        int i5 = this.mContentHeight;
        if (i5 <= 0) {
            i5 = View.MeasureSpec.getSize(i3);
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int i6 = i5 - paddingTop;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i6, Integer.MIN_VALUE);
        View view = this.mClose;
        if (view != null) {
            int c2 = c(view, paddingLeft, makeMeasureSpec, 0);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mClose.getLayoutParams();
            paddingLeft = c2 - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
        }
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView != null && actionMenuView.getParent() == this) {
            paddingLeft = c(this.mMenuView, paddingLeft, makeMeasureSpec, 0);
        }
        LinearLayout linearLayout = this.mTitleLayout;
        if (linearLayout != null && this.mCustomView == null) {
            if (this.mTitleOptional) {
                this.mTitleLayout.measure(View.MeasureSpec.makeMeasureSpec(0, 0), makeMeasureSpec);
                int measuredWidth = this.mTitleLayout.getMeasuredWidth();
                boolean z = measuredWidth <= paddingLeft;
                if (z) {
                    paddingLeft -= measuredWidth;
                }
                this.mTitleLayout.setVisibility(z ? 0 : 8);
            } else {
                paddingLeft = c(linearLayout, paddingLeft, makeMeasureSpec, 0);
            }
        }
        View view2 = this.mCustomView;
        if (view2 != null) {
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            int i7 = layoutParams.width;
            int i8 = i7 != -2 ? 1073741824 : Integer.MIN_VALUE;
            if (i7 >= 0) {
                paddingLeft = Math.min(i7, paddingLeft);
            }
            int i9 = layoutParams.height;
            if (i9 == -2) {
                i4 = Integer.MIN_VALUE;
            }
            if (i9 >= 0) {
                i6 = Math.min(i9, i6);
            }
            this.mCustomView.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i8), View.MeasureSpec.makeMeasureSpec(i6, i4));
        }
        if (this.mContentHeight > 0) {
            setMeasuredDimension(size, i5);
            return;
        }
        int childCount = getChildCount();
        int i10 = 0;
        for (int i11 = 0; i11 < childCount; i11++) {
            int measuredHeight = getChildAt(i11).getMeasuredHeight() + paddingTop;
            if (measuredHeight > i10) {
                i10 = measuredHeight;
            }
        }
        setMeasuredDimension(size, i10);
    }

    @Override // androidx.appcompat.widget.AbsActionBarView, android.view.View
    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    @Override // androidx.appcompat.widget.AbsActionBarView
    public void setContentHeight(int i2) {
        this.mContentHeight = i2;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.mCustomView;
        if (view2 != null) {
            removeView(view2);
        }
        this.mCustomView = view;
        if (view != null && (linearLayout = this.mTitleLayout) != null) {
            removeView(linearLayout);
            this.mTitleLayout = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.mSubtitle = charSequence;
        j();
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        j();
        ViewCompat.l0(this, charSequence);
    }

    public void setTitleOptional(boolean z) {
        if (z != this.mTitleOptional) {
            requestLayout();
        }
        this.mTitleOptional = z;
    }

    @Override // androidx.appcompat.widget.AbsActionBarView, android.view.View
    public /* bridge */ /* synthetic */ void setVisibility(int i2) {
        super.setVisibility(i2);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public ActionBarContextView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.actionModeStyle);
    }

    public ActionBarContextView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        TintTypedArray v = TintTypedArray.v(context, attributeSet, R.styleable.ActionMode, i2, 0);
        setBackground(v.g(R.styleable.ActionMode_background));
        this.mTitleStyleRes = v.n(R.styleable.ActionMode_titleTextStyle, 0);
        this.mSubtitleStyleRes = v.n(R.styleable.ActionMode_subtitleTextStyle, 0);
        this.mContentHeight = v.m(R.styleable.ActionMode_height, 0);
        this.mCloseItemLayout = v.n(R.styleable.ActionMode_closeItemLayout, R.layout.abc_action_mode_close_item_material);
        v.x();
    }
}
