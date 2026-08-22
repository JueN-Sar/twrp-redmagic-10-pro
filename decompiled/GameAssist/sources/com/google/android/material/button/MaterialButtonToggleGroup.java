package com.google.android.material.button;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ToggleButton;
import androidx.annotation.BoolRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/* loaded from: classes.dex */
public class MaterialButtonToggleGroup extends LinearLayout {
    private static final int DEF_STYLE_RES = R.style.Widget_MaterialComponents_MaterialButtonToggleGroup;
    private static final String LOG_TAG = "MButtonToggleGroup";
    private Set<Integer> checkedIds;
    private Integer[] childOrder;
    private final Comparator<MaterialButton> childOrderComparator;

    @IdRes
    private final int defaultCheckId;
    private final LinkedHashSet<OnButtonCheckedListener> onButtonCheckedListeners;
    private final List<CornerData> originalCornerData;
    private final PressedStateTracker pressedStateTracker;
    private boolean selectionRequired;
    private boolean singleSelection;
    private boolean skipCheckedStateTracker;

    private static class CornerData {

        /* renamed from: e, reason: collision with root package name */
        private static final CornerSize f14099e = new AbsoluteCornerSize(0.0f);

        /* renamed from: a, reason: collision with root package name */
        CornerSize f14100a;

        /* renamed from: b, reason: collision with root package name */
        CornerSize f14101b;

        /* renamed from: c, reason: collision with root package name */
        CornerSize f14102c;

        /* renamed from: d, reason: collision with root package name */
        CornerSize f14103d;

        CornerData(CornerSize cornerSize, CornerSize cornerSize2, CornerSize cornerSize3, CornerSize cornerSize4) {
            this.f14100a = cornerSize;
            this.f14101b = cornerSize3;
            this.f14102c = cornerSize4;
            this.f14103d = cornerSize2;
        }

        public static CornerData a(CornerData cornerData) {
            CornerSize cornerSize = f14099e;
            return new CornerData(cornerSize, cornerData.f14103d, cornerSize, cornerData.f14102c);
        }

        public static CornerData b(CornerData cornerData, View view) {
            return ViewUtils.p(view) ? c(cornerData) : d(cornerData);
        }

        public static CornerData c(CornerData cornerData) {
            CornerSize cornerSize = cornerData.f14100a;
            CornerSize cornerSize2 = cornerData.f14103d;
            CornerSize cornerSize3 = f14099e;
            return new CornerData(cornerSize, cornerSize2, cornerSize3, cornerSize3);
        }

        public static CornerData d(CornerData cornerData) {
            CornerSize cornerSize = f14099e;
            return new CornerData(cornerSize, cornerSize, cornerData.f14101b, cornerData.f14102c);
        }

        public static CornerData e(CornerData cornerData, View view) {
            return ViewUtils.p(view) ? d(cornerData) : c(cornerData);
        }

        public static CornerData f(CornerData cornerData) {
            CornerSize cornerSize = cornerData.f14100a;
            CornerSize cornerSize2 = f14099e;
            return new CornerData(cornerSize, cornerSize2, cornerData.f14101b, cornerSize2);
        }
    }

    public interface OnButtonCheckedListener {
        void a(MaterialButtonToggleGroup materialButtonToggleGroup, int i2, boolean z);
    }

    private class PressedStateTracker implements MaterialButton.OnPressedChangeListener {
        private PressedStateTracker() {
        }

        @Override // com.google.android.material.button.MaterialButton.OnPressedChangeListener
        public void a(MaterialButton materialButton, boolean z) {
            MaterialButtonToggleGroup.this.invalidate();
        }
    }

    public MaterialButtonToggleGroup(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialButtonToggleGroupStyle);
    }

    private void c() {
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        if (firstVisibleChildIndex == -1) {
            return;
        }
        for (int i2 = firstVisibleChildIndex + 1; i2 < getChildCount(); i2++) {
            MaterialButton i3 = i(i2);
            int min = Math.min(i3.getStrokeWidth(), i(i2 - 1).getStrokeWidth());
            LinearLayout.LayoutParams d2 = d(i3);
            if (getOrientation() == 0) {
                MarginLayoutParamsCompat.c(d2, 0);
                MarginLayoutParamsCompat.d(d2, -min);
                d2.topMargin = 0;
            } else {
                d2.bottomMargin = 0;
                d2.topMargin = -min;
                MarginLayoutParamsCompat.d(d2, 0);
            }
            i3.setLayoutParams(d2);
        }
        o(firstVisibleChildIndex);
    }

    private LinearLayout.LayoutParams d(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        return layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : new LinearLayout.LayoutParams(layoutParams.width, layoutParams.height);
    }

    private void f(int i2, boolean z) {
        if (i2 == -1) {
            Log.e(LOG_TAG, "Button ID is not valid: " + i2);
            return;
        }
        HashSet hashSet = new HashSet(this.checkedIds);
        if (z && !hashSet.contains(Integer.valueOf(i2))) {
            if (this.singleSelection && !hashSet.isEmpty()) {
                hashSet.clear();
            }
            hashSet.add(Integer.valueOf(i2));
        } else {
            if (z || !hashSet.contains(Integer.valueOf(i2))) {
                return;
            }
            if (!this.selectionRequired || hashSet.size() > 1) {
                hashSet.remove(Integer.valueOf(i2));
            }
        }
        r(hashSet);
    }

    private int getFirstVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (l(i2)) {
                return i2;
            }
        }
        return -1;
    }

    private int getLastVisibleChildIndex() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            if (l(childCount)) {
                return childCount;
            }
        }
        return -1;
    }

    private int getVisibleButtonCount() {
        int i2 = 0;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            if ((getChildAt(i3) instanceof MaterialButton) && l(i3)) {
                i2++;
            }
        }
        return i2;
    }

    private void h(int i2, boolean z) {
        Iterator<OnButtonCheckedListener> it = this.onButtonCheckedListeners.iterator();
        while (it.hasNext()) {
            it.next().a(this, i2, z);
        }
    }

    private MaterialButton i(int i2) {
        return (MaterialButton) getChildAt(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int j(View view) {
        if (!(view instanceof MaterialButton)) {
            return -1;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            if (getChildAt(i3) == view) {
                return i2;
            }
            if ((getChildAt(i3) instanceof MaterialButton) && l(i3)) {
                i2++;
            }
        }
        return -1;
    }

    private CornerData k(int i2, int i3, int i4) {
        CornerData cornerData = this.originalCornerData.get(i2);
        if (i3 == i4) {
            return cornerData;
        }
        boolean z = getOrientation() == 0;
        if (i2 == i3) {
            return z ? CornerData.e(cornerData, this) : CornerData.f(cornerData);
        }
        if (i2 == i4) {
            return z ? CornerData.b(cornerData, this) : CornerData.a(cornerData);
        }
        return null;
    }

    private boolean l(int i2) {
        return getChildAt(i2).getVisibility() != 8;
    }

    private void o(int i2) {
        if (getChildCount() == 0 || i2 == -1) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) i(i2).getLayoutParams();
        if (getOrientation() == 1) {
            layoutParams.topMargin = 0;
            layoutParams.bottomMargin = 0;
        } else {
            MarginLayoutParamsCompat.c(layoutParams, 0);
            MarginLayoutParamsCompat.d(layoutParams, 0);
            layoutParams.leftMargin = 0;
            layoutParams.rightMargin = 0;
        }
    }

    private void p(int i2, boolean z) {
        View findViewById = findViewById(i2);
        if (findViewById instanceof MaterialButton) {
            this.skipCheckedStateTracker = true;
            ((MaterialButton) findViewById).setChecked(z);
            this.skipCheckedStateTracker = false;
        }
    }

    private static void q(ShapeAppearanceModel.Builder builder, CornerData cornerData) {
        if (cornerData == null) {
            builder.o(0.0f);
        } else {
            builder.F(cornerData.f14100a).w(cornerData.f14103d).J(cornerData.f14101b).A(cornerData.f14102c);
        }
    }

    private void r(Set set) {
        Set<Integer> set2 = this.checkedIds;
        this.checkedIds = new HashSet(set);
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            int id = i(i2).getId();
            p(id, set.contains(Integer.valueOf(id)));
            if (set2.contains(Integer.valueOf(id)) != set.contains(Integer.valueOf(id))) {
                h(id, set.contains(Integer.valueOf(id)));
            }
        }
        invalidate();
    }

    private void s() {
        TreeMap treeMap = new TreeMap(this.childOrderComparator);
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            treeMap.put(i(i2), Integer.valueOf(i2));
        }
        this.childOrder = (Integer[]) treeMap.values().toArray(new Integer[0]);
    }

    private void setGeneratedIdIfNeeded(@NonNull MaterialButton materialButton) {
        if (materialButton.getId() == -1) {
            materialButton.setId(ViewCompat.i());
        }
    }

    private void setupButtonChild(@NonNull MaterialButton materialButton) {
        materialButton.setMaxLines(1);
        materialButton.setEllipsize(TextUtils.TruncateAt.END);
        materialButton.setCheckable(true);
        materialButton.setOnPressedChangeListenerInternal(this.pressedStateTracker);
        materialButton.setShouldDrawSurfaceColorStroke(true);
    }

    private void t() {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            i(i2).setA11yClassName((this.singleSelection ? RadioButton.class : ToggleButton.class).getName());
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof MaterialButton)) {
            Log.e(LOG_TAG, "Child views must be of type MaterialButton.");
            return;
        }
        super.addView(view, i2, layoutParams);
        MaterialButton materialButton = (MaterialButton) view;
        setGeneratedIdIfNeeded(materialButton);
        setupButtonChild(materialButton);
        f(materialButton.getId(), materialButton.isChecked());
        ShapeAppearanceModel shapeAppearanceModel = materialButton.getShapeAppearanceModel();
        this.originalCornerData.add(new CornerData(shapeAppearanceModel.r(), shapeAppearanceModel.j(), shapeAppearanceModel.t(), shapeAppearanceModel.l()));
        materialButton.setEnabled(isEnabled());
        ViewCompat.i0(materialButton, new AccessibilityDelegateCompat() { // from class: com.google.android.material.button.MaterialButtonToggleGroup.2
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void g(View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.g(view2, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.k0(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.a(0, 1, MaterialButtonToggleGroup.this.j(view2), 1, false, ((MaterialButton) view2).isChecked()));
            }
        });
    }

    public void b(OnButtonCheckedListener onButtonCheckedListener) {
        this.onButtonCheckedListeners.add(onButtonCheckedListener);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        s();
        super.dispatchDraw(canvas);
    }

    public void e(int i2) {
        f(i2, true);
    }

    public void g() {
        r(new HashSet());
    }

    @IdRes
    public int getCheckedButtonId() {
        if (!this.singleSelection || this.checkedIds.isEmpty()) {
            return -1;
        }
        return this.checkedIds.iterator().next().intValue();
    }

    @NonNull
    public List<Integer> getCheckedButtonIds() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            int id = i(i2).getId();
            if (this.checkedIds.contains(Integer.valueOf(id))) {
                arrayList.add(Integer.valueOf(id));
            }
        }
        return arrayList;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i2, int i3) {
        Integer[] numArr = this.childOrder;
        if (numArr != null && i3 < numArr.length) {
            return numArr[i3].intValue();
        }
        Log.w(LOG_TAG, "Child order wasn't updated");
        return i3;
    }

    public boolean m() {
        return this.singleSelection;
    }

    void n(MaterialButton materialButton, boolean z) {
        if (this.skipCheckedStateTracker) {
            return;
        }
        f(materialButton.getId(), z);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        int i2 = this.defaultCheckId;
        if (i2 != -1) {
            r(Collections.singleton(Integer.valueOf(i2)));
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        AccessibilityNodeInfoCompat.O0(accessibilityNodeInfo).j0(AccessibilityNodeInfoCompat.CollectionInfoCompat.b(1, getVisibleButtonCount(), false, m() ? 1 : 2));
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        updateChildShapes();
        c();
        super.onMeasure(i2, i3);
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view instanceof MaterialButton) {
            ((MaterialButton) view).setOnPressedChangeListenerInternal(null);
        }
        int indexOfChild = indexOfChild(view);
        if (indexOfChild >= 0) {
            this.originalCornerData.remove(indexOfChild);
        }
        updateChildShapes();
        c();
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            i(i2).setEnabled(z);
        }
    }

    public void setSelectionRequired(boolean z) {
        this.selectionRequired = z;
    }

    public void setSingleSelection(boolean z) {
        if (this.singleSelection != z) {
            this.singleSelection = z;
            g();
        }
        t();
    }

    @VisibleForTesting
    void updateChildShapes() {
        int childCount = getChildCount();
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        int lastVisibleChildIndex = getLastVisibleChildIndex();
        for (int i2 = 0; i2 < childCount; i2++) {
            MaterialButton i3 = i(i2);
            if (i3.getVisibility() != 8) {
                ShapeAppearanceModel.Builder v = i3.getShapeAppearanceModel().v();
                q(v, k(i2, firstVisibleChildIndex, lastVisibleChildIndex));
                i3.setShapeAppearanceModel(v.m());
            }
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MaterialButtonToggleGroup(@androidx.annotation.NonNull android.content.Context r7, @androidx.annotation.Nullable android.util.AttributeSet r8, int r9) {
        /*
            r6 = this;
            int r4 = com.google.android.material.button.MaterialButtonToggleGroup.DEF_STYLE_RES
            android.content.Context r7 = com.google.android.material.theme.overlay.MaterialThemeOverlay.c(r7, r8, r9, r4)
            r6.<init>(r7, r8, r9)
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r6.originalCornerData = r7
            com.google.android.material.button.MaterialButtonToggleGroup$PressedStateTracker r7 = new com.google.android.material.button.MaterialButtonToggleGroup$PressedStateTracker
            r0 = 0
            r7.<init>()
            r6.pressedStateTracker = r7
            java.util.LinkedHashSet r7 = new java.util.LinkedHashSet
            r7.<init>()
            r6.onButtonCheckedListeners = r7
            com.google.android.material.button.MaterialButtonToggleGroup$1 r7 = new com.google.android.material.button.MaterialButtonToggleGroup$1
            r7.<init>()
            r6.childOrderComparator = r7
            r7 = 0
            r6.skipCheckedStateTracker = r7
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            r6.checkedIds = r0
            android.content.Context r0 = r6.getContext()
            int[] r2 = com.google.android.material.R.styleable.MaterialButtonToggleGroup
            int[] r5 = new int[r7]
            r1 = r8
            r3 = r9
            android.content.res.TypedArray r8 = com.google.android.material.internal.ThemeEnforcement.i(r0, r1, r2, r3, r4, r5)
            int r9 = com.google.android.material.R.styleable.MaterialButtonToggleGroup_singleSelection
            boolean r9 = r8.getBoolean(r9, r7)
            r6.setSingleSelection(r9)
            int r9 = com.google.android.material.R.styleable.MaterialButtonToggleGroup_checkedButton
            r0 = -1
            int r9 = r8.getResourceId(r9, r0)
            r6.defaultCheckId = r9
            int r9 = com.google.android.material.R.styleable.MaterialButtonToggleGroup_selectionRequired
            boolean r7 = r8.getBoolean(r9, r7)
            r6.selectionRequired = r7
            r7 = 1
            r6.setChildrenDrawingOrderEnabled(r7)
            int r9 = com.google.android.material.R.styleable.MaterialButtonToggleGroup_android_enabled
            boolean r9 = r8.getBoolean(r9, r7)
            r6.setEnabled(r9)
            r8.recycle()
            androidx.core.view.ViewCompat.s0(r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.button.MaterialButtonToggleGroup.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setSingleSelection(@BoolRes int i2) {
        setSingleSelection(getResources().getBoolean(i2));
    }
}
