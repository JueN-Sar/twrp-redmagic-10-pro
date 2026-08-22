package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.R;
import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes.dex */
public abstract class ConstraintHelper extends View {
    protected static final String CHILD_TAG = "CONSTRAINT_LAYOUT_HELPER_CHILD";
    protected int mCount;
    protected Helper mHelperWidget;
    protected int[] mIds;
    protected HashMap<Integer, String> mMap;
    protected String mReferenceIds;
    protected String mReferenceTags;
    protected boolean mUseViewMeasure;
    private View[] mViews;
    protected Context myContext;

    public ConstraintHelper(Context context) {
        super(context);
        this.mIds = new int[32];
        this.mUseViewMeasure = false;
        this.mViews = null;
        this.mMap = new HashMap<>();
        this.myContext = context;
        o(null);
    }

    private void e(String str) {
        if (str == null || str.length() == 0 || this.myContext == null) {
            return;
        }
        String trim = str.trim();
        int m2 = m(trim);
        if (m2 != 0) {
            this.mMap.put(Integer.valueOf(m2), trim);
            f(m2);
            return;
        }
        Log.w("ConstraintHelper", "Could not find id of \"" + trim + "\"");
    }

    private void f(int i2) {
        if (i2 == getId()) {
            return;
        }
        int i3 = this.mCount + 1;
        int[] iArr = this.mIds;
        if (i3 > iArr.length) {
            this.mIds = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.mIds;
        int i4 = this.mCount;
        iArr2[i4] = i2;
        this.mCount = i4 + 1;
    }

    private void g(String str) {
        if (str == null || str.length() == 0 || this.myContext == null) {
            return;
        }
        String trim = str.trim();
        ConstraintLayout constraintLayout = getParent() instanceof ConstraintLayout ? (ConstraintLayout) getParent() : null;
        if (constraintLayout == null) {
            Log.w("ConstraintHelper", "Parent not a ConstraintLayout");
            return;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if ((layoutParams instanceof ConstraintLayout.LayoutParams) && trim.equals(((ConstraintLayout.LayoutParams) layoutParams).c0)) {
                if (childAt.getId() == -1) {
                    Log.w("ConstraintHelper", "to use ConstraintTag view " + childAt.getClass().getSimpleName() + " must have an ID");
                } else {
                    f(childAt.getId());
                }
            }
        }
    }

    private int[] k(String str) {
        String[] split = str.split(",");
        int[] iArr = new int[split.length];
        int i2 = 0;
        for (String str2 : split) {
            int m2 = m(str2.trim());
            if (m2 != 0) {
                iArr[i2] = m2;
                i2++;
            }
        }
        return i2 != split.length ? Arrays.copyOf(iArr, i2) : iArr;
    }

    private int l(ConstraintLayout constraintLayout, String str) {
        Resources resources;
        String str2;
        if (str == null || constraintLayout == null || (resources = this.myContext.getResources()) == null) {
            return 0;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            if (childAt.getId() != -1) {
                try {
                    str2 = resources.getResourceEntryName(childAt.getId());
                } catch (Resources.NotFoundException unused) {
                    str2 = null;
                }
                if (str.equals(str2)) {
                    return childAt.getId();
                }
            }
        }
        return 0;
    }

    private int m(String str) {
        ConstraintLayout constraintLayout = getParent() instanceof ConstraintLayout ? (ConstraintLayout) getParent() : null;
        int i2 = 0;
        if (isInEditMode() && constraintLayout != null) {
            Object o2 = constraintLayout.o(0, str);
            if (o2 instanceof Integer) {
                i2 = ((Integer) o2).intValue();
            }
        }
        if (i2 == 0 && constraintLayout != null) {
            i2 = l(constraintLayout, str);
        }
        if (i2 == 0) {
            try {
                i2 = R.id.class.getField(str).getInt(null);
            } catch (Exception unused) {
            }
        }
        return i2 == 0 ? this.myContext.getResources().getIdentifier(str, VirtualHandleWrapper.KEY_ID, this.myContext.getPackageName()) : i2;
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.mIds, this.mCount);
    }

    protected void h() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ConstraintLayout)) {
            return;
        }
        i((ConstraintLayout) parent);
    }

    protected void i(ConstraintLayout constraintLayout) {
        int visibility = getVisibility();
        float elevation = getElevation();
        for (int i2 = 0; i2 < this.mCount; i2++) {
            View q2 = constraintLayout.q(this.mIds[i2]);
            if (q2 != null) {
                q2.setVisibility(visibility);
                if (elevation > 0.0f) {
                    q2.setTranslationZ(q2.getTranslationZ() + elevation);
                }
            }
        }
    }

    protected void j(ConstraintLayout constraintLayout) {
    }

    protected View[] n(ConstraintLayout constraintLayout) {
        View[] viewArr = this.mViews;
        if (viewArr == null || viewArr.length != this.mCount) {
            this.mViews = new View[this.mCount];
        }
        for (int i2 = 0; i2 < this.mCount; i2++) {
            this.mViews[i2] = constraintLayout.q(this.mIds[i2]);
        }
        return this.mViews;
    }

    protected void o(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.ConstraintLayout_Layout_constraint_referenced_ids) {
                    String string = obtainStyledAttributes.getString(index);
                    this.mReferenceIds = string;
                    setIds(string);
                } else if (index == R.styleable.ConstraintLayout_Layout_constraint_referenced_tags) {
                    String string2 = obtainStyledAttributes.getString(index);
                    this.mReferenceTags = string2;
                    setReferenceTags(string2);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.mReferenceIds;
        if (str != null) {
            setIds(str);
        }
        String str2 = this.mReferenceTags;
        if (str2 != null) {
            setReferenceTags(str2);
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        if (this.mUseViewMeasure) {
            super.onMeasure(i2, i3);
        } else {
            setMeasuredDimension(0, 0);
        }
    }

    public void p(ConstraintSet.Constraint constraint, HelperWidget helperWidget, ConstraintLayout.LayoutParams layoutParams, SparseArray sparseArray) {
        ConstraintSet.Layout layout = constraint.f2491e;
        int[] iArr = layout.k0;
        if (iArr != null) {
            setReferencedIds(iArr);
        } else {
            String str = layout.l0;
            if (str != null) {
                if (str.length() > 0) {
                    ConstraintSet.Layout layout2 = constraint.f2491e;
                    layout2.k0 = k(layout2.l0);
                } else {
                    constraint.f2491e.k0 = null;
                }
            }
        }
        if (helperWidget == null) {
            return;
        }
        helperWidget.b();
        if (constraint.f2491e.k0 == null) {
            return;
        }
        int i2 = 0;
        while (true) {
            int[] iArr2 = constraint.f2491e.k0;
            if (i2 >= iArr2.length) {
                return;
            }
            ConstraintWidget constraintWidget = (ConstraintWidget) sparseArray.get(iArr2[i2]);
            if (constraintWidget != null) {
                helperWidget.a(constraintWidget);
            }
            i2++;
        }
    }

    public void q(ConstraintWidget constraintWidget, boolean z) {
    }

    public void r(ConstraintLayout constraintLayout) {
    }

    public void s(ConstraintLayout constraintLayout) {
    }

    protected void setIds(String str) {
        this.mReferenceIds = str;
        if (str == null) {
            return;
        }
        int i2 = 0;
        this.mCount = 0;
        while (true) {
            int indexOf = str.indexOf(44, i2);
            if (indexOf == -1) {
                e(str.substring(i2));
                return;
            } else {
                e(str.substring(i2, indexOf));
                i2 = indexOf + 1;
            }
        }
    }

    protected void setReferenceTags(String str) {
        this.mReferenceTags = str;
        if (str == null) {
            return;
        }
        int i2 = 0;
        this.mCount = 0;
        while (true) {
            int indexOf = str.indexOf(44, i2);
            if (indexOf == -1) {
                g(str.substring(i2));
                return;
            } else {
                g(str.substring(i2, indexOf));
                i2 = indexOf + 1;
            }
        }
    }

    public void setReferencedIds(int[] iArr) {
        this.mReferenceIds = null;
        this.mCount = 0;
        for (int i2 : iArr) {
            f(i2);
        }
    }

    @Override // android.view.View
    public void setTag(int i2, Object obj) {
        super.setTag(i2, obj);
        if (obj == null && this.mReferenceIds == null) {
            f(i2);
        }
    }

    public void t(ConstraintLayout constraintLayout) {
    }

    public void u(ConstraintWidgetContainer constraintWidgetContainer, Helper helper, SparseArray sparseArray) {
        helper.b();
        for (int i2 = 0; i2 < this.mCount; i2++) {
            helper.a((ConstraintWidget) sparseArray.get(this.mIds[i2]));
        }
    }

    public void v(ConstraintLayout constraintLayout) {
        String str;
        int l2;
        if (isInEditMode()) {
            setIds(this.mReferenceIds);
        }
        Helper helper = this.mHelperWidget;
        if (helper == null) {
            return;
        }
        helper.b();
        for (int i2 = 0; i2 < this.mCount; i2++) {
            int i3 = this.mIds[i2];
            View q2 = constraintLayout.q(i3);
            if (q2 == null && (l2 = l(constraintLayout, (str = this.mMap.get(Integer.valueOf(i3))))) != 0) {
                this.mIds[i2] = l2;
                this.mMap.put(Integer.valueOf(l2), str);
                q2 = constraintLayout.q(l2);
            }
            if (q2 != null) {
                this.mHelperWidget.a(constraintLayout.r(q2));
            }
        }
        this.mHelperWidget.c(constraintLayout.mLayoutWidget);
    }

    public void w() {
        if (this.mHelperWidget == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ((ConstraintLayout.LayoutParams) layoutParams).v0 = (ConstraintWidget) this.mHelperWidget;
        }
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIds = new int[32];
        this.mUseViewMeasure = false;
        this.mViews = null;
        this.mMap = new HashMap<>();
        this.myContext = context;
        o(attributeSet);
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mIds = new int[32];
        this.mUseViewMeasure = false;
        this.mViews = null;
        this.mMap = new HashMap<>();
        this.myContext = context;
        o(attributeSet);
    }
}
