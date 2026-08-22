package androidx.constraintlayout.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.Metrics;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import com.google.android.gms.common.api.Api;
import com.google.mlkit.common.MlKitException;
import com.zte.distbus.basetransfer.Status;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ConstraintLayout extends ViewGroup {
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_DRAW_CONSTRAINTS = false;
    public static final int DESIGN_INFO_ID = 0;
    private static final boolean OPTIMIZE_HEIGHT_CHANGE = false;
    private static final String TAG = "ConstraintLayout";
    private static final boolean USE_CONSTRAINTS_HELPER = true;
    public static final String VERSION = "ConstraintLayout-2.2.0-alpha04";
    private static SharedValues sSharedValues;
    SparseArray<View> mChildrenByIds;
    private ArrayList<ConstraintHelper> mConstraintHelpers;
    protected ConstraintLayoutStates mConstraintLayoutSpec;
    private ConstraintSet mConstraintSet;
    private int mConstraintSetId;
    private HashMap<String, Integer> mDesignIds;
    protected boolean mDirtyHierarchy;
    private int mLastMeasureHeight;
    int mLastMeasureHeightMode;
    int mLastMeasureHeightSize;
    private int mLastMeasureWidth;
    int mLastMeasureWidthMode;
    int mLastMeasureWidthSize;
    protected ConstraintWidgetContainer mLayoutWidget;
    private int mMaxHeight;
    private int mMaxWidth;
    Measurer mMeasurer;
    private Metrics mMetrics;
    private int mMinHeight;
    private int mMinWidth;
    private ArrayList<ValueModifier> mModifiers;
    private int mOnMeasureHeightMeasureSpec;
    private int mOnMeasureWidthMeasureSpec;
    private int mOptimizationLevel;
    private SparseArray<ConstraintWidget> mTempMapIdToWidget;

    /* renamed from: androidx.constraintlayout.widget.ConstraintLayout$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2427a;

        static {
            int[] iArr = new int[ConstraintWidget.DimensionBehaviour.values().length];
            f2427a = iArr;
            try {
                iArr[ConstraintWidget.DimensionBehaviour.FIXED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2427a[ConstraintWidget.DimensionBehaviour.WRAP_CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2427a[ConstraintWidget.DimensionBehaviour.MATCH_PARENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2427a[ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    class Measurer implements BasicMeasure.Measurer {

        /* renamed from: a, reason: collision with root package name */
        ConstraintLayout f2448a;

        /* renamed from: b, reason: collision with root package name */
        int f2449b;

        /* renamed from: c, reason: collision with root package name */
        int f2450c;

        /* renamed from: d, reason: collision with root package name */
        int f2451d;

        /* renamed from: e, reason: collision with root package name */
        int f2452e;

        /* renamed from: f, reason: collision with root package name */
        int f2453f;

        /* renamed from: g, reason: collision with root package name */
        int f2454g;

        Measurer(ConstraintLayout constraintLayout) {
            this.f2448a = constraintLayout;
        }

        private boolean d(int i2, int i3, int i4) {
            if (i2 == i3) {
                return ConstraintLayout.USE_CONSTRAINTS_HELPER;
            }
            int mode = View.MeasureSpec.getMode(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            int size = View.MeasureSpec.getSize(i3);
            if (mode2 == 1073741824 && ((mode == Integer.MIN_VALUE || mode == 0) && i4 == size)) {
                return ConstraintLayout.USE_CONSTRAINTS_HELPER;
            }
            return false;
        }

        @Override // androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measurer
        public final void a() {
            int childCount = this.f2448a.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = this.f2448a.getChildAt(i2);
                if (childAt instanceof Placeholder) {
                    ((Placeholder) childAt).b(this.f2448a);
                }
            }
            int size = this.f2448a.mConstraintHelpers.size();
            if (size > 0) {
                for (int i3 = 0; i3 < size; i3++) {
                    ((ConstraintHelper) this.f2448a.mConstraintHelpers.get(i3)).s(this.f2448a);
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:160:0x01d4  */
        /* JADX WARN: Removed duplicated region for block: B:161:0x01cf  */
        /* JADX WARN: Removed duplicated region for block: B:186:0x0146  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00dc  */
        /* JADX WARN: Removed duplicated region for block: B:59:0x01cd  */
        /* JADX WARN: Removed duplicated region for block: B:61:0x01d2  */
        /* JADX WARN: Removed duplicated region for block: B:81:0x0204 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:82:0x0205  */
        @Override // androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measurer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void b(androidx.constraintlayout.core.widgets.ConstraintWidget r21, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure r22) {
            /*
                Method dump skipped, instructions count: 786
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.Measurer.b(androidx.constraintlayout.core.widgets.ConstraintWidget, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure):void");
        }

        public void c(int i2, int i3, int i4, int i5, int i6, int i7) {
            this.f2449b = i4;
            this.f2450c = i5;
            this.f2451d = i6;
            this.f2452e = i7;
            this.f2453f = i2;
            this.f2454g = i3;
        }
    }

    public interface ValueModifier {
        boolean a(int i2, int i3, int i4, View view, LayoutParams layoutParams);
    }

    public ConstraintLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mChildrenByIds = new SparseArray<>();
        this.mConstraintHelpers = new ArrayList<>(4);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        this.mMaxHeight = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
        this.mOptimizationLevel = 257;
        this.mConstraintSet = null;
        this.mConstraintLayoutSpec = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap<>();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
        this.mTempMapIdToWidget = new SparseArray<>();
        this.mMeasurer = new Measurer(this);
        this.mOnMeasureWidthMeasureSpec = 0;
        this.mOnMeasureHeightMeasureSpec = 0;
        s(attributeSet, 0, 0);
    }

    private void B(ConstraintWidget constraintWidget, LayoutParams layoutParams, SparseArray sparseArray, int i2, ConstraintAnchor.Type type) {
        View view = this.mChildrenByIds.get(i2);
        ConstraintWidget constraintWidget2 = (ConstraintWidget) sparseArray.get(i2);
        if (constraintWidget2 == null || view == null || !(view.getLayoutParams() instanceof LayoutParams)) {
            return;
        }
        layoutParams.g0 = USE_CONSTRAINTS_HELPER;
        ConstraintAnchor.Type type2 = ConstraintAnchor.Type.BASELINE;
        if (type == type2) {
            LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
            layoutParams2.g0 = USE_CONSTRAINTS_HELPER;
            layoutParams2.v0.P0(USE_CONSTRAINTS_HELPER);
        }
        constraintWidget.q(type2).b(constraintWidget2.q(type), layoutParams.D, layoutParams.C, USE_CONSTRAINTS_HELPER);
        constraintWidget.P0(USE_CONSTRAINTS_HELPER);
        constraintWidget.q(ConstraintAnchor.Type.TOP).q();
        constraintWidget.q(ConstraintAnchor.Type.BOTTOM).q();
    }

    private boolean C() {
        int childCount = getChildCount();
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            if (getChildAt(i2).isLayoutRequested()) {
                z = USE_CONSTRAINTS_HELPER;
                break;
            }
            i2++;
        }
        if (z) {
            y();
        }
        return z;
    }

    private int getPaddingWidth() {
        int max = Math.max(0, getPaddingLeft()) + Math.max(0, getPaddingRight());
        int max2 = Math.max(0, getPaddingStart()) + Math.max(0, getPaddingEnd());
        return max2 > 0 ? max2 : max;
    }

    public static SharedValues getSharedValues() {
        if (sSharedValues == null) {
            sSharedValues = new SharedValues();
        }
        return sSharedValues;
    }

    private ConstraintWidget p(int i2) {
        if (i2 == 0) {
            return this.mLayoutWidget;
        }
        View view = this.mChildrenByIds.get(i2);
        if (view == null && (view = findViewById(i2)) != null && view != this && view.getParent() == this) {
            onViewAdded(view);
        }
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).v0;
    }

    private void s(AttributeSet attributeSet, int i2, int i3) {
        this.mLayoutWidget.G0(this);
        this.mLayoutWidget.c2(this.mMeasurer);
        this.mChildrenByIds.put(getId(), this);
        this.mConstraintSet = null;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout, i2, i3);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i4 = 0; i4 < indexCount; i4++) {
                int index = obtainStyledAttributes.getIndex(i4);
                if (index == R.styleable.ConstraintLayout_Layout_android_minWidth) {
                    this.mMinWidth = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMinWidth);
                } else if (index == R.styleable.ConstraintLayout_Layout_android_minHeight) {
                    this.mMinHeight = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMinHeight);
                } else if (index == R.styleable.ConstraintLayout_Layout_android_maxWidth) {
                    this.mMaxWidth = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxWidth);
                } else if (index == R.styleable.ConstraintLayout_Layout_android_maxHeight) {
                    this.mMaxHeight = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxHeight);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.mOptimizationLevel = obtainStyledAttributes.getInt(index, this.mOptimizationLevel);
                } else if (index == R.styleable.ConstraintLayout_Layout_layoutDescription) {
                    int resourceId = obtainStyledAttributes.getResourceId(index, 0);
                    if (resourceId != 0) {
                        try {
                            v(resourceId);
                        } catch (Resources.NotFoundException unused) {
                            this.mConstraintLayoutSpec = null;
                        }
                    }
                } else if (index == R.styleable.ConstraintLayout_Layout_constraintSet) {
                    int resourceId2 = obtainStyledAttributes.getResourceId(index, 0);
                    try {
                        ConstraintSet constraintSet = new ConstraintSet();
                        this.mConstraintSet = constraintSet;
                        constraintSet.C(getContext(), resourceId2);
                    } catch (Resources.NotFoundException unused2) {
                        this.mConstraintSet = null;
                    }
                    this.mConstraintSetId = resourceId2;
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.mLayoutWidget.d2(this.mOptimizationLevel);
    }

    private void u() {
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
    }

    private void y() {
        boolean isInEditMode = isInEditMode();
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            ConstraintWidget r2 = r(getChildAt(i2));
            if (r2 != null) {
                r2.v0();
            }
        }
        if (isInEditMode) {
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                try {
                    String resourceName = getResources().getResourceName(childAt.getId());
                    z(0, resourceName, Integer.valueOf(childAt.getId()));
                    int indexOf = resourceName.indexOf(47);
                    if (indexOf != -1) {
                        resourceName = resourceName.substring(indexOf + 1);
                    }
                    p(childAt.getId()).H0(resourceName);
                } catch (Resources.NotFoundException unused) {
                }
            }
        }
        if (this.mConstraintSetId != -1) {
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt2 = getChildAt(i4);
                if (childAt2.getId() == this.mConstraintSetId && (childAt2 instanceof Constraints)) {
                    this.mConstraintSet = ((Constraints) childAt2).getConstraintSet();
                }
            }
        }
        ConstraintSet constraintSet = this.mConstraintSet;
        if (constraintSet != null) {
            constraintSet.k(this, USE_CONSTRAINTS_HELPER);
        }
        this.mLayoutWidget.A1();
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            for (int i5 = 0; i5 < size; i5++) {
                this.mConstraintHelpers.get(i5).v(this);
            }
        }
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt3 = getChildAt(i6);
            if (childAt3 instanceof Placeholder) {
                ((Placeholder) childAt3).c(this);
            }
        }
        this.mTempMapIdToWidget.clear();
        this.mTempMapIdToWidget.put(0, this.mLayoutWidget);
        this.mTempMapIdToWidget.put(getId(), this.mLayoutWidget);
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt4 = getChildAt(i7);
            this.mTempMapIdToWidget.put(childAt4.getId(), r(childAt4));
        }
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt5 = getChildAt(i8);
            ConstraintWidget r3 = r(childAt5);
            if (r3 != null) {
                LayoutParams layoutParams = (LayoutParams) childAt5.getLayoutParams();
                this.mLayoutWidget.a(r3);
                k(isInEditMode, childAt5, r3, layoutParams, this.mTempMapIdToWidget);
            }
        }
    }

    protected void A(ConstraintWidgetContainer constraintWidgetContainer, int i2, int i3, int i4, int i5) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        Measurer measurer = this.mMeasurer;
        int i6 = measurer.f2452e;
        int i7 = measurer.f2451d;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
        int childCount = getChildCount();
        if (i2 == Integer.MIN_VALUE) {
            dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (childCount == 0) {
                i3 = Math.max(0, this.mMinWidth);
            }
        } else if (i2 == 0) {
            dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (childCount == 0) {
                i3 = Math.max(0, this.mMinWidth);
            }
            i3 = 0;
        } else if (i2 != 1073741824) {
            dimensionBehaviour = dimensionBehaviour2;
            i3 = 0;
        } else {
            i3 = Math.min(this.mMaxWidth - i7, i3);
            dimensionBehaviour = dimensionBehaviour2;
        }
        if (i4 == Integer.MIN_VALUE) {
            dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (childCount == 0) {
                i5 = Math.max(0, this.mMinHeight);
            }
        } else if (i4 != 0) {
            if (i4 == 1073741824) {
                i5 = Math.min(this.mMaxHeight - i6, i5);
            }
            i5 = 0;
        } else {
            dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (childCount == 0) {
                i5 = Math.max(0, this.mMinHeight);
            }
            i5 = 0;
        }
        if (i3 != constraintWidgetContainer.Y() || i5 != constraintWidgetContainer.z()) {
            constraintWidgetContainer.U1();
        }
        constraintWidgetContainer.r1(0);
        constraintWidgetContainer.s1(0);
        constraintWidgetContainer.c1(this.mMaxWidth - i7);
        constraintWidgetContainer.b1(this.mMaxHeight - i6);
        constraintWidgetContainer.f1(0);
        constraintWidgetContainer.e1(0);
        constraintWidgetContainer.U0(dimensionBehaviour);
        constraintWidgetContainer.p1(i3);
        constraintWidgetContainer.l1(dimensionBehaviour2);
        constraintWidgetContainer.Q0(i5);
        constraintWidgetContainer.f1(this.mMinWidth - i7);
        constraintWidgetContainer.e1(this.mMinHeight - i6);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        Object tag;
        int size;
        ArrayList<ConstraintHelper> arrayList = this.mConstraintHelpers;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            for (int i2 = 0; i2 < size; i2++) {
                this.mConstraintHelpers.get(i2).t(this);
            }
        }
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            float width = getWidth();
            float height = getHeight();
            int childCount = getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() != 8 && (tag = childAt.getTag()) != null && (tag instanceof String)) {
                    String[] split = ((String) tag).split(",");
                    if (split.length == 4) {
                        int parseInt = Integer.parseInt(split[0]);
                        int parseInt2 = Integer.parseInt(split[1]);
                        int parseInt3 = Integer.parseInt(split[2]);
                        int i4 = (int) ((parseInt / 1080.0f) * width);
                        int i5 = (int) ((parseInt2 / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(-65536);
                        float f2 = i4;
                        float f3 = i5;
                        float f4 = i4 + ((int) ((parseInt3 / 1080.0f) * width));
                        canvas.drawLine(f2, f3, f4, f3, paint);
                        float parseInt4 = i5 + ((int) ((Integer.parseInt(split[3]) / 1920.0f) * height));
                        canvas.drawLine(f4, f3, f4, parseInt4, paint);
                        canvas.drawLine(f4, parseInt4, f2, parseInt4, paint);
                        canvas.drawLine(f2, parseInt4, f2, f3, paint);
                        paint.setColor(-16711936);
                        canvas.drawLine(f2, f3, f4, parseInt4, paint);
                        canvas.drawLine(f2, parseInt4, f4, f3, paint);
                    }
                }
            }
        }
    }

    @Override // android.view.View
    public void forceLayout() {
        u();
        super.forceLayout();
    }

    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getOptimizationLevel() {
        return this.mLayoutWidget.Q1();
    }

    public String getSceneString() {
        int id;
        StringBuilder sb = new StringBuilder();
        if (this.mLayoutWidget.f1983o == null) {
            int id2 = getId();
            if (id2 != -1) {
                this.mLayoutWidget.f1983o = getContext().getResources().getResourceEntryName(id2);
            } else {
                this.mLayoutWidget.f1983o = "parent";
            }
        }
        if (this.mLayoutWidget.v() == null) {
            ConstraintWidgetContainer constraintWidgetContainer = this.mLayoutWidget;
            constraintWidgetContainer.H0(constraintWidgetContainer.f1983o);
            Log.v(TAG, " setDebugName " + this.mLayoutWidget.v());
        }
        Iterator it = this.mLayoutWidget.x1().iterator();
        while (it.hasNext()) {
            ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
            View view = (View) constraintWidget.u();
            if (view != null) {
                if (constraintWidget.f1983o == null && (id = view.getId()) != -1) {
                    constraintWidget.f1983o = getContext().getResources().getResourceEntryName(id);
                }
                if (constraintWidget.v() == null) {
                    constraintWidget.H0(constraintWidget.f1983o);
                    Log.v(TAG, " setDebugName " + constraintWidget.v());
                }
            }
        }
        this.mLayoutWidget.Q(sb);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void k(boolean z, View view, ConstraintWidget constraintWidget, LayoutParams layoutParams, SparseArray sparseArray) {
        ConstraintWidget constraintWidget2;
        ConstraintWidget constraintWidget3;
        ConstraintWidget constraintWidget4;
        ConstraintWidget constraintWidget5;
        int i2;
        layoutParams.c();
        layoutParams.w0 = false;
        constraintWidget.o1(view.getVisibility());
        if (layoutParams.j0) {
            constraintWidget.Y0(USE_CONSTRAINTS_HELPER);
            constraintWidget.o1(8);
        }
        constraintWidget.G0(view);
        if (view instanceof ConstraintHelper) {
            ((ConstraintHelper) view).q(constraintWidget, this.mLayoutWidget.W1());
        }
        if (layoutParams.h0) {
            androidx.constraintlayout.core.widgets.Guideline guideline = (androidx.constraintlayout.core.widgets.Guideline) constraintWidget;
            int i3 = layoutParams.s0;
            int i4 = layoutParams.t0;
            float f2 = layoutParams.u0;
            if (f2 != -1.0f) {
                guideline.E1(f2);
                return;
            } else if (i3 != -1) {
                guideline.C1(i3);
                return;
            } else {
                if (i4 != -1) {
                    guideline.D1(i4);
                    return;
                }
                return;
            }
        }
        int i5 = layoutParams.l0;
        int i6 = layoutParams.m0;
        int i7 = layoutParams.n0;
        int i8 = layoutParams.o0;
        int i9 = layoutParams.p0;
        int i10 = layoutParams.q0;
        float f3 = layoutParams.r0;
        int i11 = layoutParams.f2443p;
        if (i11 != -1) {
            ConstraintWidget constraintWidget6 = (ConstraintWidget) sparseArray.get(i11);
            if (constraintWidget6 != null) {
                constraintWidget.m(constraintWidget6, layoutParams.f2445r, layoutParams.f2444q);
            }
        } else {
            if (i5 != -1) {
                ConstraintWidget constraintWidget7 = (ConstraintWidget) sparseArray.get(i5);
                if (constraintWidget7 != null) {
                    ConstraintAnchor.Type type = ConstraintAnchor.Type.LEFT;
                    constraintWidget.g0(type, constraintWidget7, type, ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, i9);
                }
            } else if (i6 != -1 && (constraintWidget2 = (ConstraintWidget) sparseArray.get(i6)) != null) {
                constraintWidget.g0(ConstraintAnchor.Type.LEFT, constraintWidget2, ConstraintAnchor.Type.RIGHT, ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, i9);
            }
            if (i7 != -1) {
                ConstraintWidget constraintWidget8 = (ConstraintWidget) sparseArray.get(i7);
                if (constraintWidget8 != null) {
                    constraintWidget.g0(ConstraintAnchor.Type.RIGHT, constraintWidget8, ConstraintAnchor.Type.LEFT, ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, i10);
                }
            } else if (i8 != -1 && (constraintWidget3 = (ConstraintWidget) sparseArray.get(i8)) != null) {
                ConstraintAnchor.Type type2 = ConstraintAnchor.Type.RIGHT;
                constraintWidget.g0(type2, constraintWidget3, type2, ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, i10);
            }
            int i12 = layoutParams.f2436i;
            if (i12 != -1) {
                ConstraintWidget constraintWidget9 = (ConstraintWidget) sparseArray.get(i12);
                if (constraintWidget9 != null) {
                    ConstraintAnchor.Type type3 = ConstraintAnchor.Type.TOP;
                    constraintWidget.g0(type3, constraintWidget9, type3, ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, layoutParams.x);
                }
            } else {
                int i13 = layoutParams.f2437j;
                if (i13 != -1 && (constraintWidget4 = (ConstraintWidget) sparseArray.get(i13)) != null) {
                    constraintWidget.g0(ConstraintAnchor.Type.TOP, constraintWidget4, ConstraintAnchor.Type.BOTTOM, ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, layoutParams.x);
                }
            }
            int i14 = layoutParams.f2438k;
            if (i14 != -1) {
                ConstraintWidget constraintWidget10 = (ConstraintWidget) sparseArray.get(i14);
                if (constraintWidget10 != null) {
                    constraintWidget.g0(ConstraintAnchor.Type.BOTTOM, constraintWidget10, ConstraintAnchor.Type.TOP, ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin, layoutParams.z);
                }
            } else {
                int i15 = layoutParams.f2439l;
                if (i15 != -1 && (constraintWidget5 = (ConstraintWidget) sparseArray.get(i15)) != null) {
                    ConstraintAnchor.Type type4 = ConstraintAnchor.Type.BOTTOM;
                    constraintWidget.g0(type4, constraintWidget5, type4, ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin, layoutParams.z);
                }
            }
            int i16 = layoutParams.f2440m;
            if (i16 != -1) {
                B(constraintWidget, layoutParams, sparseArray, i16, ConstraintAnchor.Type.BASELINE);
            } else {
                int i17 = layoutParams.f2441n;
                if (i17 != -1) {
                    B(constraintWidget, layoutParams, sparseArray, i17, ConstraintAnchor.Type.TOP);
                } else {
                    int i18 = layoutParams.f2442o;
                    if (i18 != -1) {
                        B(constraintWidget, layoutParams, sparseArray, i18, ConstraintAnchor.Type.BOTTOM);
                    }
                }
            }
            if (f3 >= 0.0f) {
                constraintWidget.R0(f3);
            }
            float f4 = layoutParams.H;
            if (f4 >= 0.0f) {
                constraintWidget.i1(f4);
            }
        }
        if (z && ((i2 = layoutParams.X) != -1 || layoutParams.Y != -1)) {
            constraintWidget.g1(i2, layoutParams.Y);
        }
        if (layoutParams.e0) {
            constraintWidget.U0(ConstraintWidget.DimensionBehaviour.FIXED);
            constraintWidget.p1(((ViewGroup.MarginLayoutParams) layoutParams).width);
            if (((ViewGroup.MarginLayoutParams) layoutParams).width == -2) {
                constraintWidget.U0(ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
            }
        } else if (((ViewGroup.MarginLayoutParams) layoutParams).width == -1) {
            if (layoutParams.a0) {
                constraintWidget.U0(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
            } else {
                constraintWidget.U0(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
            }
            constraintWidget.q(ConstraintAnchor.Type.LEFT).f1966g = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
            constraintWidget.q(ConstraintAnchor.Type.RIGHT).f1966g = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        } else {
            constraintWidget.U0(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
            constraintWidget.p1(0);
        }
        if (layoutParams.f0) {
            constraintWidget.l1(ConstraintWidget.DimensionBehaviour.FIXED);
            constraintWidget.Q0(((ViewGroup.MarginLayoutParams) layoutParams).height);
            if (((ViewGroup.MarginLayoutParams) layoutParams).height == -2) {
                constraintWidget.l1(ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
            }
        } else if (((ViewGroup.MarginLayoutParams) layoutParams).height == -1) {
            if (layoutParams.b0) {
                constraintWidget.l1(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
            } else {
                constraintWidget.l1(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
            }
            constraintWidget.q(ConstraintAnchor.Type.TOP).f1966g = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
            constraintWidget.q(ConstraintAnchor.Type.BOTTOM).f1966g = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        } else {
            constraintWidget.l1(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
            constraintWidget.Q0(0);
        }
        constraintWidget.I0(layoutParams.I);
        constraintWidget.W0(layoutParams.L);
        constraintWidget.n1(layoutParams.M);
        constraintWidget.S0(layoutParams.N);
        constraintWidget.j1(layoutParams.O);
        constraintWidget.q1(layoutParams.d0);
        constraintWidget.V0(layoutParams.P, layoutParams.R, layoutParams.T, layoutParams.V);
        constraintWidget.m1(layoutParams.Q, layoutParams.S, layoutParams.U, layoutParams.W);
    }

    protected boolean l(int i2, int i3) {
        boolean z = false;
        if (this.mModifiers == null) {
            return false;
        }
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        Iterator<ValueModifier> it = this.mModifiers.iterator();
        while (it.hasNext()) {
            ValueModifier next = it.next();
            Iterator it2 = this.mLayoutWidget.x1().iterator();
            while (it2.hasNext()) {
                View view = (View) ((ConstraintWidget) it2.next()).u();
                z |= next.a(size, size2, view.getId(), view, (LayoutParams) view.getLayoutParams());
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: m, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public Object o(int i2, Object obj) {
        if (i2 != 0 || !(obj instanceof String)) {
            return null;
        }
        String str = (String) obj;
        HashMap<String, Integer> hashMap = this.mDesignIds;
        if (hashMap == null || !hashMap.containsKey(str)) {
            return null;
        }
        return this.mDesignIds.get(str);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        View content;
        Metrics metrics = this.mMetrics;
        if (metrics != null) {
            metrics.M++;
        }
        int childCount = getChildCount();
        boolean isInEditMode = isInEditMode();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            ConstraintWidget constraintWidget = layoutParams.v0;
            if ((childAt.getVisibility() != 8 || layoutParams.h0 || layoutParams.i0 || layoutParams.k0 || isInEditMode) && !layoutParams.j0) {
                int Z = constraintWidget.Z();
                int a0 = constraintWidget.a0();
                int Y = constraintWidget.Y() + Z;
                int z2 = constraintWidget.z() + a0;
                childAt.layout(Z, a0, Y, z2);
                if ((childAt instanceof Placeholder) && (content = ((Placeholder) childAt).getContent()) != null) {
                    content.setVisibility(0);
                    content.layout(Z, a0, Y, z2);
                }
            }
        }
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            for (int i7 = 0; i7 < size; i7++) {
                this.mConstraintHelpers.get(i7).r(this);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        long j2;
        if (this.mMetrics != null) {
            j2 = System.nanoTime();
            this.mMetrics.P = getChildCount();
            this.mMetrics.Q++;
        } else {
            j2 = 0;
        }
        boolean l2 = this.mDirtyHierarchy | l(i2, i3);
        this.mDirtyHierarchy = l2;
        if (!l2) {
            int childCount = getChildCount();
            int i4 = 0;
            while (true) {
                if (i4 >= childCount) {
                    break;
                }
                if (getChildAt(i4).isLayoutRequested()) {
                    this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
                    break;
                }
                i4++;
            }
        }
        this.mOnMeasureWidthMeasureSpec = i2;
        this.mOnMeasureHeightMeasureSpec = i3;
        this.mLayoutWidget.f2(t());
        if (this.mDirtyHierarchy) {
            this.mDirtyHierarchy = false;
            if (C()) {
                this.mLayoutWidget.h2();
            }
        }
        this.mLayoutWidget.O1(this.mMetrics);
        x(this.mLayoutWidget, this.mOptimizationLevel, i2, i3);
        w(i2, i3, this.mLayoutWidget.Y(), this.mLayoutWidget.z(), this.mLayoutWidget.X1(), this.mLayoutWidget.V1());
        Metrics metrics = this.mMetrics;
        if (metrics != null) {
            metrics.O += System.nanoTime() - j2;
        }
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        ConstraintWidget r2 = r(view);
        if ((view instanceof Guideline) && !(r2 instanceof androidx.constraintlayout.core.widgets.Guideline)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            androidx.constraintlayout.core.widgets.Guideline guideline = new androidx.constraintlayout.core.widgets.Guideline();
            layoutParams.v0 = guideline;
            layoutParams.h0 = USE_CONSTRAINTS_HELPER;
            guideline.F1(layoutParams.Z);
        }
        if (view instanceof ConstraintHelper) {
            ConstraintHelper constraintHelper = (ConstraintHelper) view;
            constraintHelper.w();
            ((LayoutParams) view.getLayoutParams()).i0 = USE_CONSTRAINTS_HELPER;
            if (!this.mConstraintHelpers.contains(constraintHelper)) {
                this.mConstraintHelpers.add(constraintHelper);
            }
        }
        this.mChildrenByIds.put(view.getId(), view);
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        this.mChildrenByIds.remove(view.getId());
        this.mLayoutWidget.z1(r(view));
        this.mConstraintHelpers.remove(view);
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
    }

    public View q(int i2) {
        return this.mChildrenByIds.get(i2);
    }

    public final ConstraintWidget r(View view) {
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            return null;
        }
        if (view.getLayoutParams() instanceof LayoutParams) {
            return ((LayoutParams) view.getLayoutParams()).v0;
        }
        view.setLayoutParams(generateLayoutParams(view.getLayoutParams()));
        if (view.getLayoutParams() instanceof LayoutParams) {
            return ((LayoutParams) view.getLayoutParams()).v0;
        }
        return null;
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        u();
        super.requestLayout();
    }

    public void setConstraintSet(ConstraintSet constraintSet) {
        this.mConstraintSet = constraintSet;
    }

    @Override // android.view.View
    public void setId(int i2) {
        this.mChildrenByIds.remove(getId());
        super.setId(i2);
        this.mChildrenByIds.put(getId(), this);
    }

    public void setMaxHeight(int i2) {
        if (i2 == this.mMaxHeight) {
            return;
        }
        this.mMaxHeight = i2;
        requestLayout();
    }

    public void setMaxWidth(int i2) {
        if (i2 == this.mMaxWidth) {
            return;
        }
        this.mMaxWidth = i2;
        requestLayout();
    }

    public void setMinHeight(int i2) {
        if (i2 == this.mMinHeight) {
            return;
        }
        this.mMinHeight = i2;
        requestLayout();
    }

    public void setMinWidth(int i2) {
        if (i2 == this.mMinWidth) {
            return;
        }
        this.mMinWidth = i2;
        requestLayout();
    }

    public void setOnConstraintsChanged(ConstraintsChangedListener constraintsChangedListener) {
        ConstraintLayoutStates constraintLayoutStates = this.mConstraintLayoutSpec;
        if (constraintLayoutStates != null) {
            constraintLayoutStates.c(constraintsChangedListener);
        }
    }

    public void setOptimizationLevel(int i2) {
        this.mOptimizationLevel = i2;
        this.mLayoutWidget.d2(i2);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean t() {
        if ((getContext().getApplicationInfo().flags & WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_NOT_MAGNIFIABLE) == 0 || 1 != getLayoutDirection()) {
            return false;
        }
        return USE_CONSTRAINTS_HELPER;
    }

    protected void v(int i2) {
        this.mConstraintLayoutSpec = new ConstraintLayoutStates(getContext(), this, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void w(int i2, int i3, int i4, int i5, boolean z, boolean z2) {
        Measurer measurer = this.mMeasurer;
        int i6 = measurer.f2452e;
        int resolveSizeAndState = ViewGroup.resolveSizeAndState(i4 + measurer.f2451d, i2, 0);
        int resolveSizeAndState2 = ViewGroup.resolveSizeAndState(i5 + i6, i3, 0) & 16777215;
        int min = Math.min(this.mMaxWidth, resolveSizeAndState & 16777215);
        int min2 = Math.min(this.mMaxHeight, resolveSizeAndState2);
        if (z) {
            min |= WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_COLOR_SPACE_AGNOSTIC;
        }
        if (z2) {
            min2 |= WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_COLOR_SPACE_AGNOSTIC;
        }
        setMeasuredDimension(min, min2);
        this.mLastMeasureWidth = min;
        this.mLastMeasureHeight = min2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void x(ConstraintWidgetContainer constraintWidgetContainer, int i2, int i3, int i4) {
        int mode = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i3);
        int mode2 = View.MeasureSpec.getMode(i4);
        int size2 = View.MeasureSpec.getSize(i4);
        int max = Math.max(0, getPaddingTop());
        int max2 = Math.max(0, getPaddingBottom());
        int i5 = max + max2;
        int paddingWidth = getPaddingWidth();
        this.mMeasurer.c(i3, i4, max, max2, paddingWidth, i5);
        int max3 = Math.max(0, getPaddingStart());
        int max4 = Math.max(0, getPaddingEnd());
        if (max3 <= 0 && max4 <= 0) {
            max4 = Math.max(0, getPaddingLeft());
        } else if (!t()) {
            max4 = max3;
        }
        int i6 = size - paddingWidth;
        int i7 = size2 - i5;
        A(constraintWidgetContainer, mode, i6, mode2, i7);
        constraintWidgetContainer.Y1(i2, mode, i6, mode2, i7, this.mLastMeasureWidth, this.mLastMeasureHeight, max4, max);
    }

    public void z(int i2, Object obj, Object obj2) {
        if (i2 == 0 && (obj instanceof String) && (obj2 instanceof Integer)) {
            if (this.mDesignIds == null) {
                this.mDesignIds = new HashMap<>();
            }
            String str = (String) obj;
            int indexOf = str.indexOf("/");
            if (indexOf != -1) {
                str = str.substring(indexOf + 1);
            }
            Integer num = (Integer) obj2;
            num.intValue();
            this.mDesignIds.put(str, num);
        }
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public ConstraintLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mChildrenByIds = new SparseArray<>();
        this.mConstraintHelpers = new ArrayList<>(4);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        this.mMaxHeight = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
        this.mOptimizationLevel = 257;
        this.mConstraintSet = null;
        this.mConstraintLayoutSpec = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap<>();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
        this.mTempMapIdToWidget = new SparseArray<>();
        this.mMeasurer = new Measurer(this);
        this.mOnMeasureWidthMeasureSpec = 0;
        this.mOnMeasureHeightMeasureSpec = 0;
        s(attributeSet, i2, 0);
    }

    @TargetApi(Status.ERROR_STREAM_REMOTE_FAILED)
    public ConstraintLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mChildrenByIds = new SparseArray<>();
        this.mConstraintHelpers = new ArrayList<>(4);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        this.mMaxHeight = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
        this.mOptimizationLevel = 257;
        this.mConstraintSet = null;
        this.mConstraintLayoutSpec = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap<>();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
        this.mTempMapIdToWidget = new SparseArray<>();
        this.mMeasurer = new Measurer(this);
        this.mOnMeasureWidthMeasureSpec = 0;
        this.mOnMeasureHeightMeasureSpec = 0;
        s(attributeSet, i2, i3);
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int A;
        public int B;
        public int C;
        public int D;
        boolean E;
        boolean F;
        public float G;
        public float H;
        public String I;
        float J;
        int K;
        public float L;
        public float M;
        public int N;
        public int O;
        public int P;
        public int Q;
        public int R;
        public int S;
        public int T;
        public int U;
        public float V;
        public float W;
        public int X;
        public int Y;
        public int Z;

        /* renamed from: a, reason: collision with root package name */
        public int f2428a;
        public boolean a0;

        /* renamed from: b, reason: collision with root package name */
        public int f2429b;
        public boolean b0;

        /* renamed from: c, reason: collision with root package name */
        public float f2430c;
        public String c0;

        /* renamed from: d, reason: collision with root package name */
        public boolean f2431d;
        public int d0;

        /* renamed from: e, reason: collision with root package name */
        public int f2432e;
        boolean e0;

        /* renamed from: f, reason: collision with root package name */
        public int f2433f;
        boolean f0;

        /* renamed from: g, reason: collision with root package name */
        public int f2434g;
        boolean g0;

        /* renamed from: h, reason: collision with root package name */
        public int f2435h;
        boolean h0;

        /* renamed from: i, reason: collision with root package name */
        public int f2436i;
        boolean i0;

        /* renamed from: j, reason: collision with root package name */
        public int f2437j;
        boolean j0;

        /* renamed from: k, reason: collision with root package name */
        public int f2438k;
        boolean k0;

        /* renamed from: l, reason: collision with root package name */
        public int f2439l;
        int l0;

        /* renamed from: m, reason: collision with root package name */
        public int f2440m;
        int m0;

        /* renamed from: n, reason: collision with root package name */
        public int f2441n;
        int n0;

        /* renamed from: o, reason: collision with root package name */
        public int f2442o;
        int o0;

        /* renamed from: p, reason: collision with root package name */
        public int f2443p;
        int p0;

        /* renamed from: q, reason: collision with root package name */
        public int f2444q;
        int q0;

        /* renamed from: r, reason: collision with root package name */
        public float f2445r;
        float r0;

        /* renamed from: s, reason: collision with root package name */
        public int f2446s;
        int s0;
        public int t;
        int t0;
        public int u;
        float u0;
        public int v;
        ConstraintWidget v0;
        public int w;
        public boolean w0;
        public int x;
        public int y;
        public int z;

        private static class Table {

            /* renamed from: a, reason: collision with root package name */
            public static final SparseIntArray f2447a;

            static {
                SparseIntArray sparseIntArray = new SparseIntArray();
                f2447a = sparseIntArray;
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth, 64);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight, 65);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_toTopOf, 52);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBottomOf, 53);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_guidelineUseRtl, 67);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_android_orientation, 1);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginBaseline, 55);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_marginBaseline, 54);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintTag, 51);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_wrapBehaviorInParent, 66);
            }
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f2428a = -1;
            this.f2429b = -1;
            this.f2430c = -1.0f;
            this.f2431d = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.f2432e = -1;
            this.f2433f = -1;
            this.f2434g = -1;
            this.f2435h = -1;
            this.f2436i = -1;
            this.f2437j = -1;
            this.f2438k = -1;
            this.f2439l = -1;
            this.f2440m = -1;
            this.f2441n = -1;
            this.f2442o = -1;
            this.f2443p = -1;
            this.f2444q = 0;
            this.f2445r = 0.0f;
            this.f2446s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = Integer.MIN_VALUE;
            this.x = Integer.MIN_VALUE;
            this.y = Integer.MIN_VALUE;
            this.z = Integer.MIN_VALUE;
            this.A = Integer.MIN_VALUE;
            this.B = Integer.MIN_VALUE;
            this.C = Integer.MIN_VALUE;
            this.D = 0;
            this.E = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.F = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.G = 0.5f;
            this.H = 0.5f;
            this.I = null;
            this.J = 0.0f;
            this.K = 1;
            this.L = -1.0f;
            this.M = -1.0f;
            this.N = 0;
            this.O = 0;
            this.P = 0;
            this.Q = 0;
            this.R = 0;
            this.S = 0;
            this.T = 0;
            this.U = 0;
            this.V = 1.0f;
            this.W = 1.0f;
            this.X = -1;
            this.Y = -1;
            this.Z = -1;
            this.a0 = false;
            this.b0 = false;
            this.c0 = null;
            this.d0 = 0;
            this.e0 = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.f0 = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.g0 = false;
            this.h0 = false;
            this.i0 = false;
            this.j0 = false;
            this.k0 = false;
            this.l0 = -1;
            this.m0 = -1;
            this.n0 = -1;
            this.o0 = -1;
            this.p0 = Integer.MIN_VALUE;
            this.q0 = Integer.MIN_VALUE;
            this.r0 = 0.5f;
            this.v0 = new ConstraintWidget();
            this.w0 = false;
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                ((ViewGroup.MarginLayoutParams) this).leftMargin = marginLayoutParams.leftMargin;
                ((ViewGroup.MarginLayoutParams) this).rightMargin = marginLayoutParams.rightMargin;
                ((ViewGroup.MarginLayoutParams) this).topMargin = marginLayoutParams.topMargin;
                ((ViewGroup.MarginLayoutParams) this).bottomMargin = marginLayoutParams.bottomMargin;
                setMarginStart(marginLayoutParams.getMarginStart());
                setMarginEnd(marginLayoutParams.getMarginEnd());
            }
            if (layoutParams instanceof LayoutParams) {
                LayoutParams layoutParams2 = (LayoutParams) layoutParams;
                this.f2428a = layoutParams2.f2428a;
                this.f2429b = layoutParams2.f2429b;
                this.f2430c = layoutParams2.f2430c;
                this.f2431d = layoutParams2.f2431d;
                this.f2432e = layoutParams2.f2432e;
                this.f2433f = layoutParams2.f2433f;
                this.f2434g = layoutParams2.f2434g;
                this.f2435h = layoutParams2.f2435h;
                this.f2436i = layoutParams2.f2436i;
                this.f2437j = layoutParams2.f2437j;
                this.f2438k = layoutParams2.f2438k;
                this.f2439l = layoutParams2.f2439l;
                this.f2440m = layoutParams2.f2440m;
                this.f2441n = layoutParams2.f2441n;
                this.f2442o = layoutParams2.f2442o;
                this.f2443p = layoutParams2.f2443p;
                this.f2444q = layoutParams2.f2444q;
                this.f2445r = layoutParams2.f2445r;
                this.f2446s = layoutParams2.f2446s;
                this.t = layoutParams2.t;
                this.u = layoutParams2.u;
                this.v = layoutParams2.v;
                this.w = layoutParams2.w;
                this.x = layoutParams2.x;
                this.y = layoutParams2.y;
                this.z = layoutParams2.z;
                this.A = layoutParams2.A;
                this.B = layoutParams2.B;
                this.C = layoutParams2.C;
                this.D = layoutParams2.D;
                this.G = layoutParams2.G;
                this.H = layoutParams2.H;
                this.I = layoutParams2.I;
                this.J = layoutParams2.J;
                this.K = layoutParams2.K;
                this.L = layoutParams2.L;
                this.M = layoutParams2.M;
                this.N = layoutParams2.N;
                this.O = layoutParams2.O;
                this.a0 = layoutParams2.a0;
                this.b0 = layoutParams2.b0;
                this.P = layoutParams2.P;
                this.Q = layoutParams2.Q;
                this.R = layoutParams2.R;
                this.T = layoutParams2.T;
                this.S = layoutParams2.S;
                this.U = layoutParams2.U;
                this.V = layoutParams2.V;
                this.W = layoutParams2.W;
                this.X = layoutParams2.X;
                this.Y = layoutParams2.Y;
                this.Z = layoutParams2.Z;
                this.e0 = layoutParams2.e0;
                this.f0 = layoutParams2.f0;
                this.g0 = layoutParams2.g0;
                this.h0 = layoutParams2.h0;
                this.l0 = layoutParams2.l0;
                this.m0 = layoutParams2.m0;
                this.n0 = layoutParams2.n0;
                this.o0 = layoutParams2.o0;
                this.p0 = layoutParams2.p0;
                this.q0 = layoutParams2.q0;
                this.r0 = layoutParams2.r0;
                this.c0 = layoutParams2.c0;
                this.d0 = layoutParams2.d0;
                this.v0 = layoutParams2.v0;
                this.E = layoutParams2.E;
                this.F = layoutParams2.F;
            }
        }

        public String a() {
            return this.c0;
        }

        public ConstraintWidget b() {
            return this.v0;
        }

        public void c() {
            this.h0 = false;
            this.e0 = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.f0 = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            int i2 = ((ViewGroup.MarginLayoutParams) this).width;
            if (i2 == -2 && this.a0) {
                this.e0 = false;
                if (this.P == 0) {
                    this.P = 1;
                }
            }
            int i3 = ((ViewGroup.MarginLayoutParams) this).height;
            if (i3 == -2 && this.b0) {
                this.f0 = false;
                if (this.Q == 0) {
                    this.Q = 1;
                }
            }
            if (i2 == 0 || i2 == -1) {
                this.e0 = false;
                if (i2 == 0 && this.P == 1) {
                    ((ViewGroup.MarginLayoutParams) this).width = -2;
                    this.a0 = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                }
            }
            if (i3 == 0 || i3 == -1) {
                this.f0 = false;
                if (i3 == 0 && this.Q == 1) {
                    ((ViewGroup.MarginLayoutParams) this).height = -2;
                    this.b0 = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                }
            }
            if (this.f2430c == -1.0f && this.f2428a == -1 && this.f2429b == -1) {
                return;
            }
            this.h0 = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.e0 = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.f0 = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            if (!(this.v0 instanceof androidx.constraintlayout.core.widgets.Guideline)) {
                this.v0 = new androidx.constraintlayout.core.widgets.Guideline();
            }
            ((androidx.constraintlayout.core.widgets.Guideline) this.v0).F1(this.Z);
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x004a  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0051  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0058  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x005e  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0064  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x007a  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x0082  */
        @Override // android.view.ViewGroup.MarginLayoutParams, android.view.ViewGroup.LayoutParams
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void resolveLayoutDirection(int r11) {
            /*
                Method dump skipped, instructions count: 259
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.resolveLayoutDirection(int):void");
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f2428a = -1;
            this.f2429b = -1;
            this.f2430c = -1.0f;
            this.f2431d = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.f2432e = -1;
            this.f2433f = -1;
            this.f2434g = -1;
            this.f2435h = -1;
            this.f2436i = -1;
            this.f2437j = -1;
            this.f2438k = -1;
            this.f2439l = -1;
            this.f2440m = -1;
            this.f2441n = -1;
            this.f2442o = -1;
            this.f2443p = -1;
            this.f2444q = 0;
            this.f2445r = 0.0f;
            this.f2446s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = Integer.MIN_VALUE;
            this.x = Integer.MIN_VALUE;
            this.y = Integer.MIN_VALUE;
            this.z = Integer.MIN_VALUE;
            this.A = Integer.MIN_VALUE;
            this.B = Integer.MIN_VALUE;
            this.C = Integer.MIN_VALUE;
            this.D = 0;
            this.E = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.F = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.G = 0.5f;
            this.H = 0.5f;
            this.I = null;
            this.J = 0.0f;
            this.K = 1;
            this.L = -1.0f;
            this.M = -1.0f;
            this.N = 0;
            this.O = 0;
            this.P = 0;
            this.Q = 0;
            this.R = 0;
            this.S = 0;
            this.T = 0;
            this.U = 0;
            this.V = 1.0f;
            this.W = 1.0f;
            this.X = -1;
            this.Y = -1;
            this.Z = -1;
            this.a0 = false;
            this.b0 = false;
            this.c0 = null;
            this.d0 = 0;
            this.e0 = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.f0 = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.g0 = false;
            this.h0 = false;
            this.i0 = false;
            this.j0 = false;
            this.k0 = false;
            this.l0 = -1;
            this.m0 = -1;
            this.n0 = -1;
            this.o0 = -1;
            this.p0 = Integer.MIN_VALUE;
            this.q0 = Integer.MIN_VALUE;
            this.r0 = 0.5f;
            this.v0 = new ConstraintWidget();
            this.w0 = false;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                int i3 = Table.f2447a.get(index);
                switch (i3) {
                    case 1:
                        this.Z = obtainStyledAttributes.getInt(index, this.Z);
                        break;
                    case 2:
                        int resourceId = obtainStyledAttributes.getResourceId(index, this.f2443p);
                        this.f2443p = resourceId;
                        if (resourceId == -1) {
                            this.f2443p = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        this.f2444q = obtainStyledAttributes.getDimensionPixelSize(index, this.f2444q);
                        break;
                    case 4:
                        float f2 = obtainStyledAttributes.getFloat(index, this.f2445r) % 360.0f;
                        this.f2445r = f2;
                        if (f2 < 0.0f) {
                            this.f2445r = (360.0f - f2) % 360.0f;
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        this.f2428a = obtainStyledAttributes.getDimensionPixelOffset(index, this.f2428a);
                        break;
                    case 6:
                        this.f2429b = obtainStyledAttributes.getDimensionPixelOffset(index, this.f2429b);
                        break;
                    case 7:
                        this.f2430c = obtainStyledAttributes.getFloat(index, this.f2430c);
                        break;
                    case 8:
                        int resourceId2 = obtainStyledAttributes.getResourceId(index, this.f2432e);
                        this.f2432e = resourceId2;
                        if (resourceId2 == -1) {
                            this.f2432e = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        int resourceId3 = obtainStyledAttributes.getResourceId(index, this.f2433f);
                        this.f2433f = resourceId3;
                        if (resourceId3 == -1) {
                            this.f2433f = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        int resourceId4 = obtainStyledAttributes.getResourceId(index, this.f2434g);
                        this.f2434g = resourceId4;
                        if (resourceId4 == -1) {
                            this.f2434g = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        int resourceId5 = obtainStyledAttributes.getResourceId(index, this.f2435h);
                        this.f2435h = resourceId5;
                        if (resourceId5 == -1) {
                            this.f2435h = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        int resourceId6 = obtainStyledAttributes.getResourceId(index, this.f2436i);
                        this.f2436i = resourceId6;
                        if (resourceId6 == -1) {
                            this.f2436i = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        int resourceId7 = obtainStyledAttributes.getResourceId(index, this.f2437j);
                        this.f2437j = resourceId7;
                        if (resourceId7 == -1) {
                            this.f2437j = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        int resourceId8 = obtainStyledAttributes.getResourceId(index, this.f2438k);
                        this.f2438k = resourceId8;
                        if (resourceId8 == -1) {
                            this.f2438k = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        int resourceId9 = obtainStyledAttributes.getResourceId(index, this.f2439l);
                        this.f2439l = resourceId9;
                        if (resourceId9 == -1) {
                            this.f2439l = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        int resourceId10 = obtainStyledAttributes.getResourceId(index, this.f2440m);
                        this.f2440m = resourceId10;
                        if (resourceId10 == -1) {
                            this.f2440m = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case MlKitException.NETWORK_ISSUE /* 17 */:
                        int resourceId11 = obtainStyledAttributes.getResourceId(index, this.f2446s);
                        this.f2446s = resourceId11;
                        if (resourceId11 == -1) {
                            this.f2446s = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case MlKitException.UNSUPPORTED /* 18 */:
                        int resourceId12 = obtainStyledAttributes.getResourceId(index, this.t);
                        this.t = resourceId12;
                        if (resourceId12 == -1) {
                            this.t = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 19:
                        int resourceId13 = obtainStyledAttributes.getResourceId(index, this.u);
                        this.u = resourceId13;
                        if (resourceId13 == -1) {
                            this.u = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 20:
                        int resourceId14 = obtainStyledAttributes.getResourceId(index, this.v);
                        this.v = resourceId14;
                        if (resourceId14 == -1) {
                            this.v = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case Status.ERROR_STREAM_REMOTE_FAILED /* 21 */:
                        this.w = obtainStyledAttributes.getDimensionPixelSize(index, this.w);
                        break;
                    case 22:
                        this.x = obtainStyledAttributes.getDimensionPixelSize(index, this.x);
                        break;
                    case 23:
                        this.y = obtainStyledAttributes.getDimensionPixelSize(index, this.y);
                        break;
                    case 24:
                        this.z = obtainStyledAttributes.getDimensionPixelSize(index, this.z);
                        break;
                    case 25:
                        this.A = obtainStyledAttributes.getDimensionPixelSize(index, this.A);
                        break;
                    case 26:
                        this.B = obtainStyledAttributes.getDimensionPixelSize(index, this.B);
                        break;
                    case 27:
                        this.a0 = obtainStyledAttributes.getBoolean(index, this.a0);
                        break;
                    case 28:
                        this.b0 = obtainStyledAttributes.getBoolean(index, this.b0);
                        break;
                    case 29:
                        this.G = obtainStyledAttributes.getFloat(index, this.G);
                        break;
                    case 30:
                        this.H = obtainStyledAttributes.getFloat(index, this.H);
                        break;
                    case 31:
                        int i4 = obtainStyledAttributes.getInt(index, 0);
                        this.P = i4;
                        if (i4 == 1) {
                            Log.e(ConstraintLayout.TAG, "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                            break;
                        } else {
                            break;
                        }
                    case 32:
                        int i5 = obtainStyledAttributes.getInt(index, 0);
                        this.Q = i5;
                        if (i5 == 1) {
                            Log.e(ConstraintLayout.TAG, "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
                            break;
                        } else {
                            break;
                        }
                    case 33:
                        try {
                            this.R = obtainStyledAttributes.getDimensionPixelSize(index, this.R);
                            break;
                        } catch (Exception unused) {
                            if (obtainStyledAttributes.getInt(index, this.R) == -2) {
                                this.R = -2;
                                break;
                            } else {
                                break;
                            }
                        }
                    case 34:
                        try {
                            this.T = obtainStyledAttributes.getDimensionPixelSize(index, this.T);
                            break;
                        } catch (Exception unused2) {
                            if (obtainStyledAttributes.getInt(index, this.T) == -2) {
                                this.T = -2;
                                break;
                            } else {
                                break;
                            }
                        }
                    case 35:
                        this.V = Math.max(0.0f, obtainStyledAttributes.getFloat(index, this.V));
                        this.P = 2;
                        break;
                    case 36:
                        try {
                            this.S = obtainStyledAttributes.getDimensionPixelSize(index, this.S);
                            break;
                        } catch (Exception unused3) {
                            if (obtainStyledAttributes.getInt(index, this.S) == -2) {
                                this.S = -2;
                                break;
                            } else {
                                break;
                            }
                        }
                    case 37:
                        try {
                            this.U = obtainStyledAttributes.getDimensionPixelSize(index, this.U);
                            break;
                        } catch (Exception unused4) {
                            if (obtainStyledAttributes.getInt(index, this.U) == -2) {
                                this.U = -2;
                                break;
                            } else {
                                break;
                            }
                        }
                    case 38:
                        this.W = Math.max(0.0f, obtainStyledAttributes.getFloat(index, this.W));
                        this.Q = 2;
                        break;
                    default:
                        switch (i3) {
                            case 44:
                                ConstraintSet.H(this, obtainStyledAttributes.getString(index));
                                break;
                            case 45:
                                this.L = obtainStyledAttributes.getFloat(index, this.L);
                                break;
                            case 46:
                                this.M = obtainStyledAttributes.getFloat(index, this.M);
                                break;
                            case 47:
                                this.N = obtainStyledAttributes.getInt(index, 0);
                                break;
                            case 48:
                                this.O = obtainStyledAttributes.getInt(index, 0);
                                break;
                            case 49:
                                this.X = obtainStyledAttributes.getDimensionPixelOffset(index, this.X);
                                break;
                            case 50:
                                this.Y = obtainStyledAttributes.getDimensionPixelOffset(index, this.Y);
                                break;
                            case 51:
                                this.c0 = obtainStyledAttributes.getString(index);
                                break;
                            case 52:
                                int resourceId15 = obtainStyledAttributes.getResourceId(index, this.f2441n);
                                this.f2441n = resourceId15;
                                if (resourceId15 == -1) {
                                    this.f2441n = obtainStyledAttributes.getInt(index, -1);
                                    break;
                                } else {
                                    break;
                                }
                            case 53:
                                int resourceId16 = obtainStyledAttributes.getResourceId(index, this.f2442o);
                                this.f2442o = resourceId16;
                                if (resourceId16 == -1) {
                                    this.f2442o = obtainStyledAttributes.getInt(index, -1);
                                    break;
                                } else {
                                    break;
                                }
                            case 54:
                                this.D = obtainStyledAttributes.getDimensionPixelSize(index, this.D);
                                break;
                            case 55:
                                this.C = obtainStyledAttributes.getDimensionPixelSize(index, this.C);
                                break;
                            default:
                                switch (i3) {
                                    case 64:
                                        ConstraintSet.F(this, obtainStyledAttributes, index, 0);
                                        this.E = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                                        break;
                                    case 65:
                                        ConstraintSet.F(this, obtainStyledAttributes, index, 1);
                                        this.F = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                                        break;
                                    case 66:
                                        this.d0 = obtainStyledAttributes.getInt(index, this.d0);
                                        break;
                                    case 67:
                                        this.f2431d = obtainStyledAttributes.getBoolean(index, this.f2431d);
                                        break;
                                }
                        }
                }
            }
            obtainStyledAttributes.recycle();
            c();
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
            this.f2428a = -1;
            this.f2429b = -1;
            this.f2430c = -1.0f;
            this.f2431d = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.f2432e = -1;
            this.f2433f = -1;
            this.f2434g = -1;
            this.f2435h = -1;
            this.f2436i = -1;
            this.f2437j = -1;
            this.f2438k = -1;
            this.f2439l = -1;
            this.f2440m = -1;
            this.f2441n = -1;
            this.f2442o = -1;
            this.f2443p = -1;
            this.f2444q = 0;
            this.f2445r = 0.0f;
            this.f2446s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = Integer.MIN_VALUE;
            this.x = Integer.MIN_VALUE;
            this.y = Integer.MIN_VALUE;
            this.z = Integer.MIN_VALUE;
            this.A = Integer.MIN_VALUE;
            this.B = Integer.MIN_VALUE;
            this.C = Integer.MIN_VALUE;
            this.D = 0;
            this.E = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.F = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.G = 0.5f;
            this.H = 0.5f;
            this.I = null;
            this.J = 0.0f;
            this.K = 1;
            this.L = -1.0f;
            this.M = -1.0f;
            this.N = 0;
            this.O = 0;
            this.P = 0;
            this.Q = 0;
            this.R = 0;
            this.S = 0;
            this.T = 0;
            this.U = 0;
            this.V = 1.0f;
            this.W = 1.0f;
            this.X = -1;
            this.Y = -1;
            this.Z = -1;
            this.a0 = false;
            this.b0 = false;
            this.c0 = null;
            this.d0 = 0;
            this.e0 = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.f0 = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.g0 = false;
            this.h0 = false;
            this.i0 = false;
            this.j0 = false;
            this.k0 = false;
            this.l0 = -1;
            this.m0 = -1;
            this.n0 = -1;
            this.o0 = -1;
            this.p0 = Integer.MIN_VALUE;
            this.q0 = Integer.MIN_VALUE;
            this.r0 = 0.5f;
            this.v0 = new ConstraintWidget();
            this.w0 = false;
        }
    }
}
