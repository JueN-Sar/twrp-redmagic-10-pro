package com.zte.mifavor.androidx.widget.swipe;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.zte.extres.R;
import java.util.List;

/* loaded from: classes2.dex */
public class SwipeMenuView extends LinearLayout implements View.OnClickListener {
    private static final boolean DEBUG = false;
    public static final int DEFAULT_SCROLLER_DURATION = 200;
    private static final String TAG = "Z#SwipeMenuView";
    private final int ITEM_HEIGHT;
    public final int ITEM_WIDTH;
    private Drawable mBackground;
    private int mChildCount;

    @Nullable
    private ValueAnimator mCloseAllAnim;

    @Nullable
    private ValueAnimator mCloseAnim;
    private boolean mIsCardDelete;

    @Nullable
    private OnItemMenuClickListener mItemClickListener;

    @Nullable
    private ValueAnimator mOpenAnim;

    @Nullable
    private RecyclerView.ViewHolder mViewHolder;

    /* renamed from: com.zte.mifavor.androidx.widget.swipe.SwipeMenuView$1, reason: invalid class name */
    class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ SwipeMenuView f17236c;

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f17236c.scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
            this.f17236c.invalidate();
        }
    }

    /* renamed from: com.zte.mifavor.androidx.widget.swipe.SwipeMenuView$2, reason: invalid class name */
    class AnonymousClass2 implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ SwipeMenuView f17237c;

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f17237c.scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
            this.f17237c.invalidate();
        }
    }

    /* renamed from: com.zte.mifavor.androidx.widget.swipe.SwipeMenuView$3, reason: invalid class name */
    class AnonymousClass3 implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ SwipeMenuView f17238c;

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f17238c.scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
            this.f17238c.invalidate();
        }
    }

    public SwipeMenuView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private ImageView a(SwipeMenuItem swipeMenuItem) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(swipeMenuItem.b());
        ColorStateList h2 = swipeMenuItem.h();
        if (h2 != null) {
            imageView.setColorFilter(h2.getDefaultColor());
        }
        return imageView;
    }

    private TextView c(SwipeMenuItem swipeMenuItem) {
        TextView textView = new TextView(getContext());
        String d2 = swipeMenuItem.d();
        if (d2 != null) {
            textView.setText(d2);
            textView.setGravity(17);
        }
        int f2 = swipeMenuItem.f();
        if (f2 > 0) {
            textView.setTextSize(2, f2);
        }
        ColorStateList h2 = swipeMenuItem.h();
        if (h2 != null) {
            textView.setTextColor(h2);
        }
        int e2 = swipeMenuItem.e();
        if (e2 != 0) {
            TextViewCompat.p(textView, e2);
        }
        Typeface g2 = swipeMenuItem.g();
        if (g2 != null) {
            textView.setTypeface(g2);
        }
        return textView;
    }

    public void b(RecyclerView.ViewHolder viewHolder, SwipeMenu swipeMenu, OnItemMenuClickListener onItemMenuClickListener) {
        removeAllViews();
        this.mViewHolder = viewHolder;
        this.mItemClickListener = onItemMenuClickListener;
        List a2 = swipeMenu.a();
        this.mChildCount = a2.size();
        for (int i2 = 0; i2 < this.mChildCount; i2++) {
            SwipeMenuItem swipeMenuItem = (SwipeMenuItem) a2.get(i2);
            if (i2 == 0 && 1 == this.mChildCount) {
                this.mIsCardDelete = swipeMenuItem.c();
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.ITEM_WIDTH, -1);
            layoutParams.weight = swipeMenuItem.i();
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setId(i2);
            linearLayout.setGravity(17);
            linearLayout.setOrientation(1);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setOnClickListener(this);
            addView(linearLayout);
            linearLayout.setTag(new SwipeMenuBridge(i2));
            if (i2 != 0 || 1 != this.mChildCount) {
                ViewCompat.m0(linearLayout, swipeMenuItem.a());
            } else if (this.mIsCardDelete) {
                this.mBackground = swipeMenuItem.a();
                setGravity(5);
            } else {
                setBackground(swipeMenuItem.a());
            }
            if (swipeMenuItem.b() != null) {
                linearLayout.addView(a(swipeMenuItem));
            }
            if (!TextUtils.isEmpty(swipeMenuItem.d())) {
                linearLayout.addView(c(swipeMenuItem));
            }
        }
    }

    @Override // android.view.View
    public Drawable getBackground() {
        return this.mBackground;
    }

    @Override // android.view.ViewGroup
    public int getChildCount() {
        return this.mChildCount;
    }

    public int getITEM_WIDTH() {
        return this.ITEM_WIDTH;
    }

    public boolean getIsCardDelete() {
        return this.mIsCardDelete;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        View view2;
        if (this.mItemClickListener == null || this.mViewHolder == null || view == null || view.getParent() == null || (view2 = (View) view.getParent().getParent()) == null || !(view2 instanceof SwipeMenuLayout)) {
            return;
        }
        this.mItemClickListener.a(((FrameLayout) view2.findViewById(R.id.swipe_content)).getChildAt(0), (SwipeMenuBridge) view.getTag(), this.mViewHolder.k());
    }

    public void setIsCardDelete(boolean z) {
        this.mIsCardDelete = z;
    }

    public SwipeMenuView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.ITEM_WIDTH = getResources().getDimensionPixelSize(R.dimen.dp_64);
        this.mViewHolder = null;
        this.mItemClickListener = null;
        this.mOpenAnim = null;
        this.mCloseAnim = null;
        this.mCloseAllAnim = null;
        this.ITEM_HEIGHT = -1;
        this.mChildCount = 0;
        this.mBackground = null;
        this.mIsCardDelete = false;
        setGravity(3);
    }
}
