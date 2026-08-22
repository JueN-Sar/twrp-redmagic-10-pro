package com.google.android.material.divider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;

/* loaded from: classes.dex */
public class MaterialDividerItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: i, reason: collision with root package name */
    private static final int f14561i = R.style.Widget_MaterialComponents_MaterialDivider;

    /* renamed from: a, reason: collision with root package name */
    private Drawable f14562a;

    /* renamed from: b, reason: collision with root package name */
    private int f14563b;

    /* renamed from: c, reason: collision with root package name */
    private int f14564c;

    /* renamed from: d, reason: collision with root package name */
    private int f14565d;

    /* renamed from: e, reason: collision with root package name */
    private int f14566e;

    /* renamed from: f, reason: collision with root package name */
    private int f14567f;

    /* renamed from: g, reason: collision with root package name */
    private boolean f14568g;

    /* renamed from: h, reason: collision with root package name */
    private final Rect f14569h;

    public MaterialDividerItemDecoration(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        this(context, attributeSet, R.attr.materialDividerStyle, i2);
    }

    private void c(Canvas canvas, RecyclerView recyclerView) {
        int height;
        int i2;
        int i3;
        int i4;
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            i2 = recyclerView.getPaddingTop();
            height = recyclerView.getHeight() - recyclerView.getPaddingBottom();
            canvas.clipRect(recyclerView.getPaddingLeft(), i2, recyclerView.getWidth() - recyclerView.getPaddingRight(), height);
        } else {
            height = recyclerView.getHeight();
            i2 = 0;
        }
        int i5 = i2 + this.f14566e;
        int i6 = height - this.f14567f;
        boolean p2 = ViewUtils.p(recyclerView);
        int childCount = recyclerView.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = recyclerView.getChildAt(i7);
            if (h(recyclerView, childAt)) {
                recyclerView.getLayoutManager().V(childAt, this.f14569h);
                int round = Math.round(childAt.getTranslationX());
                if (p2) {
                    i4 = this.f14569h.left + round;
                    i3 = this.f14563b + i4;
                } else {
                    i3 = round + this.f14569h.right;
                    i4 = i3 - this.f14563b;
                }
                this.f14562a.setBounds(i4, i5, i3, i6);
                this.f14562a.setAlpha(Math.round(childAt.getAlpha() * 255.0f));
                this.f14562a.draw(canvas);
            }
        }
        canvas.restore();
    }

    private void d(Canvas canvas, RecyclerView recyclerView) {
        int width;
        int i2;
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            i2 = recyclerView.getPaddingLeft();
            width = recyclerView.getWidth() - recyclerView.getPaddingRight();
            canvas.clipRect(i2, recyclerView.getPaddingTop(), width, recyclerView.getHeight() - recyclerView.getPaddingBottom());
        } else {
            width = recyclerView.getWidth();
            i2 = 0;
        }
        boolean p2 = ViewUtils.p(recyclerView);
        int i3 = i2 + (p2 ? this.f14567f : this.f14566e);
        int i4 = width - (p2 ? this.f14566e : this.f14567f);
        int childCount = recyclerView.getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = recyclerView.getChildAt(i5);
            if (h(recyclerView, childAt)) {
                recyclerView.getLayoutManager().V(childAt, this.f14569h);
                int round = this.f14569h.bottom + Math.round(childAt.getTranslationY());
                this.f14562a.setBounds(i3, round - this.f14563b, i4, round);
                this.f14562a.setAlpha(Math.round(childAt.getAlpha() * 255.0f));
                this.f14562a.draw(canvas);
            }
        }
        canvas.restore();
    }

    private boolean h(RecyclerView recyclerView, View view) {
        int f0 = recyclerView.f0(view);
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        boolean z = adapter != null && f0 == adapter.m() - 1;
        if (f0 != -1) {
            return (!z || this.f14568g) && g(f0, adapter);
        }
        return false;
    }

    public void e(int i2) {
        this.f14564c = i2;
        Drawable r2 = DrawableCompat.r(this.f14562a);
        this.f14562a = r2;
        DrawableCompat.n(r2, i2);
    }

    public void f(int i2) {
        if (i2 == 0 || i2 == 1) {
            this.f14565d = i2;
            return;
        }
        throw new IllegalArgumentException("Invalid orientation: " + i2 + ". It should be either HORIZONTAL or VERTICAL");
    }

    protected boolean g(int i2, RecyclerView.Adapter adapter) {
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.set(0, 0, 0, 0);
        if (h(recyclerView, view)) {
            if (this.f14565d == 1) {
                rect.bottom = this.f14563b;
            } else if (ViewUtils.p(recyclerView)) {
                rect.left = this.f14563b;
            } else {
                rect.right = this.f14563b;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        if (recyclerView.getLayoutManager() == null) {
            return;
        }
        if (this.f14565d == 1) {
            d(canvas, recyclerView);
        } else {
            c(canvas, recyclerView);
        }
    }

    public MaterialDividerItemDecoration(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        this.f14569h = new Rect();
        TypedArray i4 = ThemeEnforcement.i(context, attributeSet, R.styleable.MaterialDivider, i2, f14561i, new int[0]);
        this.f14564c = MaterialResources.a(context, i4, R.styleable.MaterialDivider_dividerColor).getDefaultColor();
        this.f14563b = i4.getDimensionPixelSize(R.styleable.MaterialDivider_dividerThickness, context.getResources().getDimensionPixelSize(R.dimen.material_divider_thickness));
        this.f14566e = i4.getDimensionPixelOffset(R.styleable.MaterialDivider_dividerInsetStart, 0);
        this.f14567f = i4.getDimensionPixelOffset(R.styleable.MaterialDivider_dividerInsetEnd, 0);
        this.f14568g = i4.getBoolean(R.styleable.MaterialDivider_lastItemDecorated, true);
        i4.recycle();
        this.f14562a = new ShapeDrawable();
        e(this.f14564c);
        f(i3);
    }
}
