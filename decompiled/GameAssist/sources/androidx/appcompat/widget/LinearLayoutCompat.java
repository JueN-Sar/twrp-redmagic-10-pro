package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import android.widget.LinearLayout;
import androidx.annotation.GravityInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;
import com.google.android.material.card.MaterialCardView;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashSet;
import java.util.Set;
import java.util.function.IntFunction;

/* loaded from: classes.dex */
public class LinearLayoutCompat extends ViewGroup {
    private static final String ACCESSIBILITY_CLASS_NAME = "androidx.appcompat.widget.LinearLayoutCompat";
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface DividerMode {
    }

    @RequiresApi
    @RestrictTo
    public final class InspectionCompanion implements android.view.inspector.InspectionCompanion<LinearLayoutCompat> {

        /* renamed from: a, reason: collision with root package name */
        private boolean f897a;

        /* renamed from: b, reason: collision with root package name */
        private int f898b;

        /* renamed from: c, reason: collision with root package name */
        private int f899c;

        /* renamed from: d, reason: collision with root package name */
        private int f900d;

        /* renamed from: e, reason: collision with root package name */
        private int f901e;

        /* renamed from: f, reason: collision with root package name */
        private int f902f;

        /* renamed from: g, reason: collision with root package name */
        private int f903g;

        /* renamed from: h, reason: collision with root package name */
        private int f904h;

        /* renamed from: i, reason: collision with root package name */
        private int f905i;

        /* renamed from: j, reason: collision with root package name */
        private int f906j;

        @Override // android.view.inspector.InspectionCompanion
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void readProperties(LinearLayoutCompat linearLayoutCompat, PropertyReader propertyReader) {
            if (!this.f897a) {
                throw new InspectionCompanion.UninitializedPropertyMapException();
            }
            propertyReader.readBoolean(this.f898b, linearLayoutCompat.r());
            propertyReader.readInt(this.f899c, linearLayoutCompat.getBaselineAlignedChildIndex());
            propertyReader.readGravity(this.f900d, linearLayoutCompat.getGravity());
            propertyReader.readIntEnum(this.f901e, linearLayoutCompat.getOrientation());
            propertyReader.readFloat(this.f902f, linearLayoutCompat.getWeightSum());
            propertyReader.readObject(this.f903g, linearLayoutCompat.getDividerDrawable());
            propertyReader.readInt(this.f904h, linearLayoutCompat.getDividerPadding());
            propertyReader.readBoolean(this.f905i, linearLayoutCompat.s());
            propertyReader.readIntFlag(this.f906j, linearLayoutCompat.getShowDividers());
        }

        @Override // android.view.inspector.InspectionCompanion
        public void mapProperties(PropertyMapper propertyMapper) {
            this.f898b = propertyMapper.mapBoolean("baselineAligned", R.attr.baselineAligned);
            this.f899c = propertyMapper.mapInt("baselineAlignedChildIndex", R.attr.baselineAlignedChildIndex);
            this.f900d = propertyMapper.mapGravity("gravity", R.attr.gravity);
            this.f901e = propertyMapper.mapIntEnum("orientation", R.attr.orientation, new IntFunction<String>() { // from class: androidx.appcompat.widget.LinearLayoutCompat.InspectionCompanion.1
                @Override // java.util.function.IntFunction
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public String apply(int i2) {
                    return i2 != 0 ? i2 != 1 ? String.valueOf(i2) : "vertical" : "horizontal";
                }
            });
            this.f902f = propertyMapper.mapFloat("weightSum", R.attr.weightSum);
            this.f903g = propertyMapper.mapObject("divider", androidx.appcompat.R.attr.divider);
            this.f904h = propertyMapper.mapInt("dividerPadding", androidx.appcompat.R.attr.dividerPadding);
            this.f905i = propertyMapper.mapBoolean("measureWithLargestChild", androidx.appcompat.R.attr.measureWithLargestChild);
            this.f906j = propertyMapper.mapIntFlag("showDividers", androidx.appcompat.R.attr.showDividers, new IntFunction<Set<String>>() { // from class: androidx.appcompat.widget.LinearLayoutCompat.InspectionCompanion.2
                @Override // java.util.function.IntFunction
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public Set apply(int i2) {
                    HashSet hashSet = new HashSet();
                    if (i2 == 0) {
                        hashSet.add("none");
                    }
                    if (i2 == 1) {
                        hashSet.add("beginning");
                    }
                    if (i2 == 2) {
                        hashSet.add("middle");
                    }
                    if (i2 == 4) {
                        hashSet.add("end");
                    }
                    return hashSet;
                }
            });
            this.f897a = true;
        }
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface OrientationMode {
    }

    public LinearLayoutCompat(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void h(int i2, int i3) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
        for (int i4 = 0; i4 < i2; i4++) {
            View p2 = p(i4);
            if (p2.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) p2.getLayoutParams();
                if (((LinearLayout.LayoutParams) layoutParams).height == -1) {
                    int i5 = ((LinearLayout.LayoutParams) layoutParams).width;
                    ((LinearLayout.LayoutParams) layoutParams).width = p2.getMeasuredWidth();
                    measureChildWithMargins(p2, i3, 0, makeMeasureSpec, 0);
                    ((LinearLayout.LayoutParams) layoutParams).width = i5;
                }
            }
        }
    }

    private void i(int i2, int i3) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
        for (int i4 = 0; i4 < i2; i4++) {
            View p2 = p(i4);
            if (p2.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) p2.getLayoutParams();
                if (((LinearLayout.LayoutParams) layoutParams).width == -1) {
                    int i5 = ((LinearLayout.LayoutParams) layoutParams).height;
                    ((LinearLayout.LayoutParams) layoutParams).height = p2.getMeasuredHeight();
                    measureChildWithMargins(p2, makeMeasureSpec, 0, i3, 0);
                    ((LinearLayout.LayoutParams) layoutParams).height = i5;
                }
            }
        }
    }

    private void z(View view, int i2, int i3, int i4, int i5) {
        view.layout(i2, i3, i4 + i2, i5 + i3);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    void d(Canvas canvas) {
        int right;
        int left;
        int i2;
        int virtualChildCount = getVirtualChildCount();
        boolean b2 = ViewUtils.b(this);
        for (int i3 = 0; i3 < virtualChildCount; i3++) {
            View p2 = p(i3);
            if (p2 != null && p2.getVisibility() != 8 && q(i3)) {
                LayoutParams layoutParams = (LayoutParams) p2.getLayoutParams();
                g(canvas, b2 ? p2.getRight() + ((LinearLayout.LayoutParams) layoutParams).rightMargin : (p2.getLeft() - ((LinearLayout.LayoutParams) layoutParams).leftMargin) - this.mDividerWidth);
            }
        }
        if (q(virtualChildCount)) {
            View p3 = p(virtualChildCount - 1);
            if (p3 != null) {
                LayoutParams layoutParams2 = (LayoutParams) p3.getLayoutParams();
                if (b2) {
                    left = p3.getLeft() - ((LinearLayout.LayoutParams) layoutParams2).leftMargin;
                    i2 = this.mDividerWidth;
                    right = left - i2;
                } else {
                    right = p3.getRight() + ((LinearLayout.LayoutParams) layoutParams2).rightMargin;
                }
            } else if (b2) {
                right = getPaddingLeft();
            } else {
                left = getWidth() - getPaddingRight();
                i2 = this.mDividerWidth;
                right = left - i2;
            }
            g(canvas, right);
        }
    }

    void e(Canvas canvas) {
        int virtualChildCount = getVirtualChildCount();
        for (int i2 = 0; i2 < virtualChildCount; i2++) {
            View p2 = p(i2);
            if (p2 != null && p2.getVisibility() != 8 && q(i2)) {
                f(canvas, (p2.getTop() - ((LinearLayout.LayoutParams) ((LayoutParams) p2.getLayoutParams())).topMargin) - this.mDividerHeight);
            }
        }
        if (q(virtualChildCount)) {
            View p3 = p(virtualChildCount - 1);
            f(canvas, p3 == null ? (getHeight() - getPaddingBottom()) - this.mDividerHeight : p3.getBottom() + ((LinearLayout.LayoutParams) ((LayoutParams) p3.getLayoutParams())).bottomMargin);
        }
    }

    void f(Canvas canvas, int i2) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i2, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i2);
        this.mDivider.draw(canvas);
    }

    void g(Canvas canvas, int i2) {
        this.mDivider.setBounds(i2, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i2, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    @Override // android.view.View
    public int getBaseline() {
        int i2;
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i3 = this.mBaselineAlignedChildIndex;
        if (childCount <= i3) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(i3);
        int baseline = childAt.getBaseline();
        if (baseline == -1) {
            if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int i4 = this.mBaselineChildTop;
        if (this.mOrientation == 1 && (i2 = this.mGravity & 112) != 48) {
            if (i2 == 16) {
                i4 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
            } else if (i2 == 80) {
                i4 = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
            }
        }
        return i4 + ((LinearLayout.LayoutParams) ((LayoutParams) childAt.getLayoutParams())).topMargin + baseline;
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    @RestrictTo
    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    @GravityInt
    public int getGravity() {
        return this.mGravity;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateDefaultLayoutParams() {
        int i2 = this.mOrientation;
        if (i2 == 0) {
            return new LayoutParams(-2, -2);
        }
        if (i2 == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    @Override // android.view.ViewGroup
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: l, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    int m(View view, int i2) {
        return 0;
    }

    int n(View view) {
        return 0;
    }

    int o(View view) {
        return 0;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mDivider == null) {
            return;
        }
        if (this.mOrientation == 1) {
            e(canvas);
        } else {
            d(canvas);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(ACCESSIBILITY_CLASS_NAME);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(ACCESSIBILITY_CLASS_NAME);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (this.mOrientation == 1) {
            u(i2, i3, i4, i5);
        } else {
            t(i2, i3, i4, i5);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        if (this.mOrientation == 1) {
            y(i2, i3);
        } else {
            w(i2, i3);
        }
    }

    View p(int i2) {
        return getChildAt(i2);
    }

    protected boolean q(int i2) {
        if (i2 == 0) {
            return (this.mShowDividers & 1) != 0;
        }
        if (i2 == getChildCount()) {
            return (this.mShowDividers & 4) != 0;
        }
        if ((this.mShowDividers & 2) == 0) {
            return false;
        }
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            if (getChildAt(i3).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    public boolean r() {
        return this.mBaselineAligned;
    }

    public boolean s() {
        return this.mUseLargestChild;
    }

    public void setBaselineAligned(boolean z) {
        this.mBaselineAligned = z;
    }

    public void setBaselineAlignedChildIndex(int i2) {
        if (i2 >= 0 && i2 < getChildCount()) {
            this.mBaselineAlignedChildIndex = i2;
            return;
        }
        throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.mDivider) {
            return;
        }
        this.mDivider = drawable;
        if (drawable != null) {
            this.mDividerWidth = drawable.getIntrinsicWidth();
            this.mDividerHeight = drawable.getIntrinsicHeight();
        } else {
            this.mDividerWidth = 0;
            this.mDividerHeight = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setDividerPadding(int i2) {
        this.mDividerPadding = i2;
    }

    public void setGravity(@GravityInt int i2) {
        if (this.mGravity != i2) {
            if ((8388615 & i2) == 0) {
                i2 |= 8388611;
            }
            if ((i2 & 112) == 0) {
                i2 |= 48;
            }
            this.mGravity = i2;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i2) {
        int i3 = i2 & 8388615;
        int i4 = this.mGravity;
        if ((8388615 & i4) != i3) {
            this.mGravity = i3 | ((-8388616) & i4);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.mUseLargestChild = z;
    }

    public void setOrientation(int i2) {
        if (this.mOrientation != i2) {
            this.mOrientation = i2;
            requestLayout();
        }
    }

    public void setShowDividers(int i2) {
        if (i2 != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = i2;
    }

    public void setVerticalGravity(int i2) {
        int i3 = i2 & 112;
        int i4 = this.mGravity;
        if ((i4 & 112) != i3) {
            this.mGravity = i3 | (i4 & (-113));
            requestLayout();
        }
    }

    public void setWeightSum(float f2) {
        this.mWeightSum = Math.max(0.0f, f2);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void t(int r25, int r26, int r27, int r28) {
        /*
            Method dump skipped, instructions count: 331
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.t(int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void u(int r18, int r19, int r20, int r21) {
        /*
            r17 = this;
            r6 = r17
            int r7 = r17.getPaddingLeft()
            int r0 = r20 - r18
            int r1 = r17.getPaddingRight()
            int r8 = r0 - r1
            int r0 = r0 - r7
            int r1 = r17.getPaddingRight()
            int r9 = r0 - r1
            int r10 = r17.getVirtualChildCount()
            int r0 = r6.mGravity
            r1 = r0 & 112(0x70, float:1.57E-43)
            r2 = 8388615(0x800007, float:1.1754953E-38)
            r11 = r0 & r2
            r0 = 16
            if (r1 == r0) goto L3b
            r0 = 80
            if (r1 == r0) goto L2f
            int r0 = r17.getPaddingTop()
            goto L47
        L2f:
            int r0 = r17.getPaddingTop()
            int r0 = r0 + r21
            int r0 = r0 - r19
            int r1 = r6.mTotalLength
            int r0 = r0 - r1
            goto L47
        L3b:
            int r0 = r17.getPaddingTop()
            int r1 = r21 - r19
            int r2 = r6.mTotalLength
            int r1 = r1 - r2
            int r1 = r1 / 2
            int r0 = r0 + r1
        L47:
            r1 = 0
            r12 = r1
        L49:
            if (r12 >= r10) goto Lcb
            android.view.View r13 = r6.p(r12)
            r14 = 1
            if (r13 != 0) goto L5a
            int r1 = r6.x(r12)
            int r0 = r0 + r1
        L57:
            r1 = r14
            goto Lc8
        L5a:
            int r1 = r13.getVisibility()
            r2 = 8
            if (r1 == r2) goto L57
            int r4 = r13.getMeasuredWidth()
            int r15 = r13.getMeasuredHeight()
            android.view.ViewGroup$LayoutParams r1 = r13.getLayoutParams()
            r5 = r1
            androidx.appcompat.widget.LinearLayoutCompat$LayoutParams r5 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r5
            int r1 = r5.gravity
            if (r1 >= 0) goto L76
            r1 = r11
        L76:
            int r2 = r17.getLayoutDirection()
            int r1 = androidx.core.view.GravityCompat.b(r1, r2)
            r1 = r1 & 7
            if (r1 == r14) goto L90
            r2 = 5
            if (r1 == r2) goto L8a
            int r1 = r5.leftMargin
            int r1 = r1 + r7
        L88:
            r2 = r1
            goto L9b
        L8a:
            int r1 = r8 - r4
            int r2 = r5.rightMargin
        L8e:
            int r1 = r1 - r2
            goto L88
        L90:
            int r1 = r9 - r4
            int r1 = r1 / 2
            int r1 = r1 + r7
            int r2 = r5.leftMargin
            int r1 = r1 + r2
            int r2 = r5.rightMargin
            goto L8e
        L9b:
            boolean r1 = r6.q(r12)
            if (r1 == 0) goto La4
            int r1 = r6.mDividerHeight
            int r0 = r0 + r1
        La4:
            int r1 = r5.topMargin
            int r16 = r0 + r1
            int r0 = r6.n(r13)
            int r3 = r16 + r0
            r0 = r17
            r1 = r13
            r14 = r5
            r5 = r15
            r0.z(r1, r2, r3, r4, r5)
            int r0 = r14.bottomMargin
            int r15 = r15 + r0
            int r0 = r6.o(r13)
            int r15 = r15 + r0
            int r16 = r16 + r15
            int r0 = r6.m(r13, r12)
            int r12 = r12 + r0
            r0 = r16
            r1 = 1
        Lc8:
            int r12 = r12 + r1
            goto L49
        Lcb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.u(int, int, int, int):void");
    }

    void v(View view, int i2, int i3, int i4, int i5, int i6) {
        measureChildWithMargins(view, i3, i4, i5, i6);
    }

    /* JADX WARN: Removed duplicated region for block: B:200:0x045b  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01d9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void w(int r40, int r41) {
        /*
            Method dump skipped, instructions count: 1293
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.w(int, int):void");
    }

    int x(int i2) {
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:156:0x031b, code lost:
    
        if (((android.widget.LinearLayout.LayoutParams) r14).width == (-1)) goto L147;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void y(int r34, int r35) {
        /*
            Method dump skipped, instructions count: 910
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.y(int, int):void");
    }

    public LinearLayoutCompat(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = MaterialCardView.CHECKED_ICON_GRAVITY_TOP_START;
        TintTypedArray v = TintTypedArray.v(context, attributeSet, androidx.appcompat.R.styleable.LinearLayoutCompat, i2, 0);
        ViewCompat.g0(this, context, androidx.appcompat.R.styleable.LinearLayoutCompat, attributeSet, v.r(), i2, 0);
        int k2 = v.k(androidx.appcompat.R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (k2 >= 0) {
            setOrientation(k2);
        }
        int k3 = v.k(androidx.appcompat.R.styleable.LinearLayoutCompat_android_gravity, -1);
        if (k3 >= 0) {
            setGravity(k3);
        }
        boolean a2 = v.a(androidx.appcompat.R.styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!a2) {
            setBaselineAligned(a2);
        }
        this.mWeightSum = v.i(androidx.appcompat.R.styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = v.k(androidx.appcompat.R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = v.a(androidx.appcompat.R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(v.g(androidx.appcompat.R.styleable.LinearLayoutCompat_divider));
        this.mShowDividers = v.k(androidx.appcompat.R.styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = v.f(androidx.appcompat.R.styleable.LinearLayoutCompat_dividerPadding, 0);
        v.x();
    }
}
