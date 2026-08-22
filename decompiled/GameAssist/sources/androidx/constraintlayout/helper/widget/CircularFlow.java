package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R;
import androidx.constraintlayout.widget.VirtualLayout;
import java.util.Arrays;

/* loaded from: classes.dex */
public class CircularFlow extends VirtualLayout {
    private static final String TAG = "CircularFlow";
    private static float sDefaultAngle;
    private static int sDefaultRadius;
    private float[] mAngles;
    ConstraintLayout mContainer;
    private int mCountAngle;
    private int mCountRadius;
    private int[] mRadius;
    private String mReferenceAngles;
    private Float mReferenceDefaultAngle;
    private Integer mReferenceDefaultRadius;
    private String mReferenceRadius;
    int mViewCenter;

    public CircularFlow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void A() {
        this.mContainer = (ConstraintLayout) getParent();
        for (int i2 = 0; i2 < this.mCount; i2++) {
            View q2 = this.mContainer.q(this.mIds[i2]);
            if (q2 != null) {
                int i3 = sDefaultRadius;
                float f2 = sDefaultAngle;
                int[] iArr = this.mRadius;
                if (iArr == null || i2 >= iArr.length) {
                    Integer num = this.mReferenceDefaultRadius;
                    if (num == null || num.intValue() == -1) {
                        Log.e(TAG, "Added radius to view with id: " + this.mMap.get(Integer.valueOf(q2.getId())));
                    } else {
                        this.mCountRadius++;
                        if (this.mRadius == null) {
                            this.mRadius = new int[1];
                        }
                        int[] radius = getRadius();
                        this.mRadius = radius;
                        radius[this.mCountRadius - 1] = i3;
                    }
                } else {
                    i3 = iArr[i2];
                }
                float[] fArr = this.mAngles;
                if (fArr == null || i2 >= fArr.length) {
                    Float f3 = this.mReferenceDefaultAngle;
                    if (f3 == null || f3.floatValue() == -1.0f) {
                        Log.e(TAG, "Added angle to view with id: " + this.mMap.get(Integer.valueOf(q2.getId())));
                    } else {
                        this.mCountAngle++;
                        if (this.mAngles == null) {
                            this.mAngles = new float[1];
                        }
                        float[] angles = getAngles();
                        this.mAngles = angles;
                        angles[this.mCountAngle - 1] = f2;
                    }
                } else {
                    f2 = fArr[i2];
                }
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) q2.getLayoutParams();
                layoutParams.f2445r = f2;
                layoutParams.f2443p = this.mViewCenter;
                layoutParams.f2444q = i3;
                q2.setLayoutParams(layoutParams);
            }
        }
        h();
    }

    private void setAngles(String str) {
        if (str == null) {
            return;
        }
        int i2 = 0;
        this.mCountAngle = 0;
        while (true) {
            int indexOf = str.indexOf(44, i2);
            if (indexOf == -1) {
                y(str.substring(i2).trim());
                return;
            } else {
                y(str.substring(i2, indexOf).trim());
                i2 = indexOf + 1;
            }
        }
    }

    private void setRadius(String str) {
        if (str == null) {
            return;
        }
        int i2 = 0;
        this.mCountRadius = 0;
        while (true) {
            int indexOf = str.indexOf(44, i2);
            if (indexOf == -1) {
                z(str.substring(i2).trim());
                return;
            } else {
                z(str.substring(i2, indexOf).trim());
                i2 = indexOf + 1;
            }
        }
    }

    private void y(String str) {
        float[] fArr;
        if (str == null || str.length() == 0 || this.myContext == null || (fArr = this.mAngles) == null) {
            return;
        }
        if (this.mCountAngle + 1 > fArr.length) {
            this.mAngles = Arrays.copyOf(fArr, fArr.length + 1);
        }
        this.mAngles[this.mCountAngle] = Integer.parseInt(str);
        this.mCountAngle++;
    }

    private void z(String str) {
        int[] iArr;
        if (str == null || str.length() == 0 || this.myContext == null || (iArr = this.mRadius) == null) {
            return;
        }
        if (this.mCountRadius + 1 > iArr.length) {
            this.mRadius = Arrays.copyOf(iArr, iArr.length + 1);
        }
        this.mRadius[this.mCountRadius] = (int) (Integer.parseInt(str) * this.myContext.getResources().getDisplayMetrics().density);
        this.mCountRadius++;
    }

    public float[] getAngles() {
        return Arrays.copyOf(this.mAngles, this.mCountAngle);
    }

    public int[] getRadius() {
        return Arrays.copyOf(this.mRadius, this.mCountRadius);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    protected void o(AttributeSet attributeSet) {
        super.o(attributeSet);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.ConstraintLayout_Layout_circularflow_viewCenter) {
                    this.mViewCenter = obtainStyledAttributes.getResourceId(index, 0);
                } else if (index == R.styleable.ConstraintLayout_Layout_circularflow_angles) {
                    String string = obtainStyledAttributes.getString(index);
                    this.mReferenceAngles = string;
                    setAngles(string);
                } else if (index == R.styleable.ConstraintLayout_Layout_circularflow_radiusInDP) {
                    String string2 = obtainStyledAttributes.getString(index);
                    this.mReferenceRadius = string2;
                    setRadius(string2);
                } else if (index == R.styleable.ConstraintLayout_Layout_circularflow_defaultAngle) {
                    Float valueOf = Float.valueOf(obtainStyledAttributes.getFloat(index, sDefaultAngle));
                    this.mReferenceDefaultAngle = valueOf;
                    setDefaultAngle(valueOf.floatValue());
                } else if (index == R.styleable.ConstraintLayout_Layout_circularflow_defaultRadius) {
                    Integer valueOf2 = Integer.valueOf(obtainStyledAttributes.getDimensionPixelSize(index, sDefaultRadius));
                    this.mReferenceDefaultRadius = valueOf2;
                    setDefaultRadius(valueOf2.intValue());
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.mReferenceAngles;
        if (str != null) {
            this.mAngles = new float[1];
            setAngles(str);
        }
        String str2 = this.mReferenceRadius;
        if (str2 != null) {
            this.mRadius = new int[1];
            setRadius(str2);
        }
        Float f2 = this.mReferenceDefaultAngle;
        if (f2 != null) {
            setDefaultAngle(f2.floatValue());
        }
        Integer num = this.mReferenceDefaultRadius;
        if (num != null) {
            setDefaultRadius(num.intValue());
        }
        A();
    }

    public void setDefaultAngle(float f2) {
        sDefaultAngle = f2;
    }

    public void setDefaultRadius(int i2) {
        sDefaultRadius = i2;
    }

    public CircularFlow(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
