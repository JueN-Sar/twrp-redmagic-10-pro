package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R;
import androidx.constraintlayout.widget.VirtualLayout;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class Grid extends VirtualLayout {
    private static final boolean DEBUG_BOXES = false;
    public static final int HORIZONTAL = 0;
    private static final String TAG = "Grid";
    public static final int VERTICAL = 1;
    private int[] mBoxViewIds;
    private View[] mBoxViews;
    private int mColumns;
    private int mColumnsSet;
    ConstraintLayout mContainer;
    private float mHorizontalGaps;
    private final int mMaxColumns;
    private final int mMaxRows;
    private int mNextAvailableIndex;
    private int mOrientation;
    private boolean[][] mPositionMatrix;
    private int mRows;
    private int mRowsSet;
    Set<Integer> mSpanIds;
    private String mStrColumnWeights;
    private String mStrRowWeights;
    private String mStrSkips;
    private String mStrSpans;
    private boolean mUseRtl;
    private boolean mValidateInputs;
    private float mVerticalGaps;

    public Grid(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMaxRows = 50;
        this.mMaxColumns = 50;
        this.mNextAvailableIndex = 0;
        this.mSpanIds = new HashSet();
    }

    private void A(View view) {
        ConstraintLayout.LayoutParams N = N(view);
        N.L = -1.0f;
        N.f2433f = -1;
        N.f2432e = -1;
        N.f2434g = -1;
        N.f2435h = -1;
        ((ViewGroup.MarginLayoutParams) N).leftMargin = -1;
        view.setLayoutParams(N);
    }

    private void B(View view) {
        ConstraintLayout.LayoutParams N = N(view);
        N.M = -1.0f;
        N.f2437j = -1;
        N.f2436i = -1;
        N.f2438k = -1;
        N.f2439l = -1;
        ((ViewGroup.MarginLayoutParams) N).topMargin = -1;
        view.setLayoutParams(N);
    }

    private void C(View view, int i2, int i3, int i4, int i5) {
        ConstraintLayout.LayoutParams N = N(view);
        int[] iArr = this.mBoxViewIds;
        N.f2432e = iArr[i3];
        N.f2436i = iArr[i2];
        N.f2435h = iArr[(i3 + i5) - 1];
        N.f2439l = iArr[(i2 + i4) - 1];
        view.setLayoutParams(N);
    }

    private boolean D(boolean z) {
        int[][] O;
        int[][] O2;
        if (this.mContainer == null || this.mRows < 1 || this.mColumns < 1) {
            return false;
        }
        if (z) {
            for (int i2 = 0; i2 < this.mPositionMatrix.length; i2++) {
                int i3 = 0;
                while (true) {
                    boolean[][] zArr = this.mPositionMatrix;
                    if (i3 < zArr[0].length) {
                        zArr[i2][i3] = true;
                        i3++;
                    }
                }
            }
            this.mSpanIds.clear();
        }
        this.mNextAvailableIndex = 0;
        z();
        String str = this.mStrSkips;
        boolean G = (str == null || str.trim().isEmpty() || (O2 = O(this.mStrSkips)) == null) ? true : G(O2) & true;
        String str2 = this.mStrSpans;
        if (str2 != null && !str2.trim().isEmpty() && (O = O(this.mStrSpans)) != null) {
            G &= H(this.mIds, O);
        }
        return (G && y()) || !this.mValidateInputs;
    }

    private int E(int i2) {
        return this.mOrientation == 1 ? i2 / this.mRows : i2 % this.mColumns;
    }

    private int F(int i2) {
        return this.mOrientation == 1 ? i2 % this.mRows : i2 / this.mColumns;
    }

    private boolean G(int[][] iArr) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int F = F(iArr[i2][0]);
            int E = E(iArr[i2][0]);
            int[] iArr2 = iArr[i2];
            if (!J(F, E, iArr2[1], iArr2[2])) {
                return false;
            }
        }
        return true;
    }

    private boolean H(int[] iArr, int[][] iArr2) {
        View[] n2 = n(this.mContainer);
        for (int i2 = 0; i2 < iArr2.length; i2++) {
            int F = F(iArr2[i2][0]);
            int E = E(iArr2[i2][0]);
            int[] iArr3 = iArr2[i2];
            if (!J(F, E, iArr3[1], iArr3[2])) {
                return false;
            }
            View view = n2[i2];
            int[] iArr4 = iArr2[i2];
            C(view, F, E, iArr4[1], iArr4[2]);
            this.mSpanIds.add(Integer.valueOf(iArr[i2]));
        }
        return true;
    }

    private void I() {
        boolean[][] zArr = (boolean[][]) Array.newInstance((Class<?>) Boolean.TYPE, this.mRows, this.mColumns);
        this.mPositionMatrix = zArr;
        for (boolean[] zArr2 : zArr) {
            Arrays.fill(zArr2, true);
        }
    }

    private boolean J(int i2, int i3, int i4, int i5) {
        for (int i6 = i2; i6 < i2 + i4; i6++) {
            for (int i7 = i3; i7 < i3 + i5; i7++) {
                boolean[][] zArr = this.mPositionMatrix;
                if (i6 < zArr.length && i7 < zArr[0].length) {
                    boolean[] zArr2 = zArr[i6];
                    if (zArr2[i7]) {
                        zArr2[i7] = false;
                    }
                }
                return false;
            }
        }
        return true;
    }

    private boolean K(CharSequence charSequence) {
        return true;
    }

    private boolean L(String str) {
        return true;
    }

    private View M() {
        View view = new View(getContext());
        view.setId(View.generateViewId());
        view.setVisibility(4);
        this.mContainer.addView(view, new ConstraintLayout.LayoutParams(0, 0));
        return view;
    }

    private ConstraintLayout.LayoutParams N(View view) {
        return (ConstraintLayout.LayoutParams) view.getLayoutParams();
    }

    private int[][] O(String str) {
        if (!K(str)) {
            return null;
        }
        String[] split = str.split(",");
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, split.length, 3);
        for (int i2 = 0; i2 < split.length; i2++) {
            String[] split2 = split[i2].trim().split(":");
            String[] split3 = split2[1].split("x");
            iArr[i2][0] = Integer.parseInt(split2[0]);
            iArr[i2][1] = Integer.parseInt(split3[0]);
            iArr[i2][2] = Integer.parseInt(split3[1]);
        }
        return iArr;
    }

    private float[] P(int i2, String str) {
        float[] fArr = null;
        if (str != null && !str.trim().isEmpty()) {
            String[] split = str.split(",");
            if (split.length != i2) {
                return null;
            }
            fArr = new float[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                fArr[i3] = Float.parseFloat(split[i3].trim());
            }
        }
        return fArr;
    }

    private void Q() {
        int i2;
        int id = getId();
        int max = Math.max(this.mRows, this.mColumns);
        float[] P = P(this.mColumns, this.mStrColumnWeights);
        int i3 = 0;
        ConstraintLayout.LayoutParams N = N(this.mBoxViews[0]);
        if (this.mColumns == 1) {
            A(this.mBoxViews[0]);
            N.f2432e = id;
            N.f2435h = id;
            this.mBoxViews[0].setLayoutParams(N);
            return;
        }
        while (true) {
            i2 = this.mColumns;
            if (i3 >= i2) {
                break;
            }
            ConstraintLayout.LayoutParams N2 = N(this.mBoxViews[i3]);
            A(this.mBoxViews[i3]);
            if (P != null) {
                N2.L = P[i3];
            }
            if (i3 > 0) {
                N2.f2433f = this.mBoxViewIds[i3 - 1];
            } else {
                N2.f2432e = id;
            }
            if (i3 < this.mColumns - 1) {
                N2.f2434g = this.mBoxViewIds[i3 + 1];
            } else {
                N2.f2435h = id;
            }
            if (i3 > 0) {
                ((ViewGroup.MarginLayoutParams) N2).leftMargin = (int) this.mHorizontalGaps;
            }
            this.mBoxViews[i3].setLayoutParams(N2);
            i3++;
        }
        while (i2 < max) {
            ConstraintLayout.LayoutParams N3 = N(this.mBoxViews[i2]);
            A(this.mBoxViews[i2]);
            N3.f2432e = id;
            N3.f2435h = id;
            this.mBoxViews[i2].setLayoutParams(N3);
            i2++;
        }
    }

    private void R() {
        int i2;
        int id = getId();
        int max = Math.max(this.mRows, this.mColumns);
        float[] P = P(this.mRows, this.mStrRowWeights);
        int i3 = 0;
        if (this.mRows == 1) {
            ConstraintLayout.LayoutParams N = N(this.mBoxViews[0]);
            B(this.mBoxViews[0]);
            N.f2436i = id;
            N.f2439l = id;
            this.mBoxViews[0].setLayoutParams(N);
            return;
        }
        while (true) {
            i2 = this.mRows;
            if (i3 >= i2) {
                break;
            }
            ConstraintLayout.LayoutParams N2 = N(this.mBoxViews[i3]);
            B(this.mBoxViews[i3]);
            if (P != null) {
                N2.M = P[i3];
            }
            if (i3 > 0) {
                N2.f2437j = this.mBoxViewIds[i3 - 1];
            } else {
                N2.f2436i = id;
            }
            if (i3 < this.mRows - 1) {
                N2.f2438k = this.mBoxViewIds[i3 + 1];
            } else {
                N2.f2439l = id;
            }
            if (i3 > 0) {
                ((ViewGroup.MarginLayoutParams) N2).topMargin = (int) this.mHorizontalGaps;
            }
            this.mBoxViews[i3].setLayoutParams(N2);
            i3++;
        }
        while (i2 < max) {
            ConstraintLayout.LayoutParams N3 = N(this.mBoxViews[i2]);
            B(this.mBoxViews[i2]);
            N3.f2436i = id;
            N3.f2439l = id;
            this.mBoxViews[i2].setLayoutParams(N3);
            i2++;
        }
    }

    private void S() {
        int i2;
        int i3 = this.mRowsSet;
        if (i3 != 0 && (i2 = this.mColumnsSet) != 0) {
            this.mRows = i3;
            this.mColumns = i2;
            return;
        }
        int i4 = this.mColumnsSet;
        if (i4 > 0) {
            this.mColumns = i4;
            this.mRows = ((this.mCount + i4) - 1) / i4;
        } else if (i3 > 0) {
            this.mRows = i3;
            this.mColumns = ((this.mCount + i3) - 1) / i3;
        } else {
            int sqrt = (int) (Math.sqrt(this.mCount) + 1.5d);
            this.mRows = sqrt;
            this.mColumns = ((this.mCount + sqrt) - 1) / sqrt;
        }
    }

    private int getNextPosition() {
        boolean z = false;
        int i2 = 0;
        while (!z) {
            i2 = this.mNextAvailableIndex;
            if (i2 >= this.mRows * this.mColumns) {
                return -1;
            }
            int F = F(i2);
            int E = E(this.mNextAvailableIndex);
            boolean[] zArr = this.mPositionMatrix[F];
            if (zArr[E]) {
                zArr[E] = false;
                z = true;
            }
            this.mNextAvailableIndex++;
        }
        return i2;
    }

    private boolean y() {
        View[] n2 = n(this.mContainer);
        for (int i2 = 0; i2 < this.mCount; i2++) {
            if (!this.mSpanIds.contains(Integer.valueOf(this.mIds[i2]))) {
                int nextPosition = getNextPosition();
                int F = F(nextPosition);
                int E = E(nextPosition);
                if (nextPosition == -1) {
                    return false;
                }
                C(n2[i2], F, E, 1, 1);
            }
        }
        return true;
    }

    private void z() {
        int max = Math.max(this.mRows, this.mColumns);
        View[] viewArr = this.mBoxViews;
        int i2 = 0;
        if (viewArr == null) {
            this.mBoxViews = new View[max];
            int i3 = 0;
            while (true) {
                View[] viewArr2 = this.mBoxViews;
                if (i3 >= viewArr2.length) {
                    break;
                }
                viewArr2[i3] = M();
                i3++;
            }
        } else if (max != viewArr.length) {
            View[] viewArr3 = new View[max];
            for (int i4 = 0; i4 < max; i4++) {
                View[] viewArr4 = this.mBoxViews;
                if (i4 < viewArr4.length) {
                    viewArr3[i4] = viewArr4[i4];
                } else {
                    viewArr3[i4] = M();
                }
            }
            int i5 = max;
            while (true) {
                View[] viewArr5 = this.mBoxViews;
                if (i5 >= viewArr5.length) {
                    break;
                }
                this.mContainer.removeView(viewArr5[i5]);
                i5++;
            }
            this.mBoxViews = viewArr3;
        }
        this.mBoxViewIds = new int[max];
        while (true) {
            View[] viewArr6 = this.mBoxViews;
            if (i2 >= viewArr6.length) {
                R();
                Q();
                return;
            } else {
                this.mBoxViewIds[i2] = viewArr6[i2].getId();
                i2++;
            }
        }
    }

    public String getColumnWeights() {
        return this.mStrColumnWeights;
    }

    public int getColumns() {
        return this.mColumnsSet;
    }

    public float getHorizontalGaps() {
        return this.mHorizontalGaps;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public String getRowWeights() {
        return this.mStrRowWeights;
    }

    public int getRows() {
        return this.mRowsSet;
    }

    public String getSkips() {
        return this.mStrSkips;
    }

    public String getSpans() {
        return this.mStrSpans;
    }

    public float getVerticalGaps() {
        return this.mVerticalGaps;
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    protected void o(AttributeSet attributeSet) {
        super.o(attributeSet);
        this.mUseViewMeasure = true;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.Grid);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.Grid_grid_rows) {
                    this.mRowsSet = obtainStyledAttributes.getInteger(index, 0);
                } else if (index == R.styleable.Grid_grid_columns) {
                    this.mColumnsSet = obtainStyledAttributes.getInteger(index, 0);
                } else if (index == R.styleable.Grid_grid_spans) {
                    this.mStrSpans = obtainStyledAttributes.getString(index);
                } else if (index == R.styleable.Grid_grid_skips) {
                    this.mStrSkips = obtainStyledAttributes.getString(index);
                } else if (index == R.styleable.Grid_grid_rowWeights) {
                    this.mStrRowWeights = obtainStyledAttributes.getString(index);
                } else if (index == R.styleable.Grid_grid_columnWeights) {
                    this.mStrColumnWeights = obtainStyledAttributes.getString(index);
                } else if (index == R.styleable.Grid_grid_orientation) {
                    this.mOrientation = obtainStyledAttributes.getInt(index, 0);
                } else if (index == R.styleable.Grid_grid_horizontalGaps) {
                    this.mHorizontalGaps = obtainStyledAttributes.getDimension(index, 0.0f);
                } else if (index == R.styleable.Grid_grid_verticalGaps) {
                    this.mVerticalGaps = obtainStyledAttributes.getDimension(index, 0.0f);
                } else if (index == R.styleable.Grid_grid_validateInputs) {
                    this.mValidateInputs = obtainStyledAttributes.getBoolean(index, false);
                } else if (index == R.styleable.Grid_grid_useRtl) {
                    this.mUseRtl = obtainStyledAttributes.getBoolean(index, false);
                }
            }
            S();
            I();
            obtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mContainer = (ConstraintLayout) getParent();
        D(false);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isInEditMode()) {
            Paint paint = new Paint();
            paint.setColor(-65536);
            paint.setStyle(Paint.Style.STROKE);
            int top = getTop();
            int left = getLeft();
            int bottom = getBottom();
            int right = getRight();
            for (View view : this.mBoxViews) {
                int left2 = view.getLeft() - left;
                int top2 = view.getTop() - top;
                int right2 = view.getRight() - left;
                int bottom2 = view.getBottom() - top;
                canvas.drawRect(left2, 0.0f, right2, bottom - top, paint);
                canvas.drawRect(0.0f, top2, right - left, bottom2, paint);
            }
        }
    }

    public void setColumnWeights(String str) {
        if (L(str)) {
            String str2 = this.mStrColumnWeights;
            if (str2 == null || !str2.equals(str)) {
                this.mStrColumnWeights = str;
                D(true);
                invalidate();
            }
        }
    }

    public void setColumns(int i2) {
        if (i2 <= 50 && this.mColumnsSet != i2) {
            this.mColumnsSet = i2;
            S();
            I();
            D(false);
            invalidate();
        }
    }

    public void setHorizontalGaps(float f2) {
        if (f2 >= 0.0f && this.mHorizontalGaps != f2) {
            this.mHorizontalGaps = f2;
            D(true);
            invalidate();
        }
    }

    public void setOrientation(int i2) {
        if ((i2 == 0 || i2 == 1) && this.mOrientation != i2) {
            this.mOrientation = i2;
            D(true);
            invalidate();
        }
    }

    public void setRowWeights(String str) {
        if (L(str)) {
            String str2 = this.mStrRowWeights;
            if (str2 == null || !str2.equals(str)) {
                this.mStrRowWeights = str;
                D(true);
                invalidate();
            }
        }
    }

    public void setRows(int i2) {
        if (i2 <= 50 && this.mRowsSet != i2) {
            this.mRowsSet = i2;
            S();
            I();
            D(false);
            invalidate();
        }
    }

    public void setSkips(String str) {
        if (K(str)) {
            String str2 = this.mStrSkips;
            if (str2 == null || !str2.equals(str)) {
                this.mStrSkips = str;
                D(true);
                invalidate();
            }
        }
    }

    public void setSpans(CharSequence charSequence) {
        if (K(charSequence)) {
            String str = this.mStrSpans;
            if (str == null || !str.contentEquals(charSequence)) {
                this.mStrSpans = charSequence.toString();
                D(true);
                invalidate();
            }
        }
    }

    public void setVerticalGaps(float f2) {
        if (f2 >= 0.0f && this.mVerticalGaps != f2) {
            this.mVerticalGaps = f2;
            D(true);
            invalidate();
        }
    }

    public Grid(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mMaxRows = 50;
        this.mMaxColumns = 50;
        this.mNextAvailableIndex = 0;
        this.mSpanIds = new HashSet();
    }
}
