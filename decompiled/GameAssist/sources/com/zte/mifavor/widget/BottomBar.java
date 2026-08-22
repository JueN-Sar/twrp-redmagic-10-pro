package com.zte.mifavor.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.zte.extres.R;
import com.zte.mifavor.utils.DisplayModeManager;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes2.dex */
public class BottomBar extends LinearLayout {
    public static final int BUTTON1 = 0;
    public static final int BUTTON2 = 1;
    public static final int BUTTON3 = 2;
    private static final int BUTTON_NUM = 2;
    private static final float[] BlankWeight = {5.0f, 10.0f};
    private static final float[] BlankWeightSinglePort = {5.0f, 3.3f};
    private static final float[] ButtonWeight = {90.0f, 43.3f, 27.8f};
    public static final int GRAVITY_END = 1;
    public static final int GRAVITY_INVALID = -1;
    public static final int GRAVITY_START = 0;
    public static final String TAG = "BottomBar";
    private LinearLayout[] mButtonContainers;
    private int mButtonGravity;
    private Button[] mButtons;
    private Context mContext;
    private ImageView[] mDividers;

    public BottomBar(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.bottomBarStyle);
    }

    private LinearLayout.LayoutParams a() {
        return new LinearLayout.LayoutParams(0, -2, 0.0f);
    }

    private LinearLayout.LayoutParams b() {
        return new LinearLayout.LayoutParams(-2, -2);
    }

    private void c() {
        int buttonCount = getButtonCount();
        if (buttonCount <= 0) {
            return;
        }
        float[] fArr = ((!DisplayModeManager.d(this.mContext) || DisplayModeManager.b(this.mContext) == 1) && (getResources().getConfiguration().orientation == 1)) ? BlankWeightSinglePort : BlankWeight;
        int i2 = 0;
        while (i2 < 2) {
            ((LinearLayout.LayoutParams) this.mButtonContainers[i2].getLayoutParams()).weight = ButtonWeight[buttonCount - 1];
            ((LinearLayout.LayoutParams) this.mDividers[i2].getLayoutParams()).weight = fArr[i2 == 0 ? (char) 0 : (char) 1];
            if (TextUtils.isEmpty(this.mButtons[i2].getText())) {
                this.mButtonContainers[i2].setVisibility(8);
                this.mDividers[i2].setVisibility(8);
            }
            i2++;
        }
        ((LinearLayout.LayoutParams) this.mDividers[2].getLayoutParams()).weight = fArr[0];
    }

    private boolean d(View view) {
        return view.getVisibility() != 8;
    }

    private int getButtonCount() {
        int i2 = 0;
        for (int i3 = 0; i3 < 2; i3++) {
            if (d(this.mButtonContainers[i3])) {
                i2++;
            }
        }
        return i2;
    }

    private int getScreenWidth() {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
            return displayMetrics.widthPixels;
        } catch (NullPointerException unused) {
            return -1;
        }
    }

    public int getButtonGravity() {
        return this.mButtonGravity;
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        if (this.mButtonGravity != -1 && DisplayModeManager.d(this.mContext)) {
            int size = View.MeasureSpec.getSize(i2);
            boolean c2 = DisplayModeManager.c(this.mContext);
            boolean z = getResources().getConfiguration().orientation == 2;
            int screenWidth = getScreenWidth();
            if (c2 && z && getButtonCount() % 2 == 1 && size > screenWidth / 2) {
                int buttonCount = screenWidth / (getButtonCount() + 1);
                if (this.mButtonGravity == 0) {
                    setPaddingRelative(0, getPaddingTop(), buttonCount, getPaddingBottom());
                } else {
                    setPaddingRelative(buttonCount, getPaddingTop(), 0, getPaddingBottom());
                }
            } else {
                setPaddingRelative(0, getPaddingTop(), 0, getPaddingBottom());
            }
        }
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.mfvc_bottom_height01);
        int mode = View.MeasureSpec.getMode(i3);
        if (mode == Integer.MIN_VALUE) {
            i3 = View.MeasureSpec.makeMeasureSpec(Math.min(dimensionPixelSize, View.MeasureSpec.getSize(i3)), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
        } else if (mode == 0) {
            i3 = View.MeasureSpec.makeMeasureSpec(dimensionPixelSize, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
        }
        super.onMeasure(i2, i3);
    }

    public void setButtonGravity(int i2) {
        this.mButtonGravity = i2;
        invalidate();
    }

    public void setButtonOnClickListener(View.OnClickListener onClickListener) {
        for (int i2 = 0; i2 < 2; i2++) {
            this.mButtons[i2].setOnClickListener(onClickListener);
        }
    }

    public BottomBar(Context context, @Nullable AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, R.style.BottomBarStyle);
    }

    public BottomBar(Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mDividers = new ImageView[3];
        this.mButtons = new Button[2];
        this.mButtonContainers = new LinearLayout[2];
        this.mContext = context;
        setBackground(context.getDrawable(R.drawable.bottombar_bg_card));
        setGravity(17);
        if (getPaddingTop() == 0 && getPaddingBottom() == 0) {
            setPaddingRelative(getPaddingStart(), getResources().getDimensionPixelOffset(R.dimen.bottombar_padding_top), getPaddingEnd(), getResources().getDimensionPixelOffset(R.dimen.bottombar_padding_bottom));
        }
        int[] iArr = {R.id.button1, R.id.button2, R.id.button3};
        for (int i4 = 0; i4 < 2; i4++) {
            this.mDividers[i4] = new ImageView(this.mContext);
            addView(this.mDividers[i4], a());
            this.mButtons[i4] = new Button(this.mContext);
            this.mButtons[i4].setId(iArr[i4]);
            this.mButtonContainers[i4] = new LinearLayout(this.mContext);
            this.mButtonContainers[i4].setGravity(17);
            this.mButtonContainers[i4].addView(this.mButtons[i4], b());
            addView(this.mButtonContainers[i4], a());
        }
        this.mDividers[2] = new ImageView(this.mContext);
        addView(this.mDividers[2], a());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BottomBar, i2, i3);
        int[] iArr2 = {R.styleable.BottomBar_text1, R.styleable.BottomBar_text2, R.styleable.BottomBar_text3};
        for (int i5 = 0; i5 < 2; i5++) {
            this.mButtons[i5].setText(obtainStyledAttributes.getText(iArr2[i5]));
        }
        int[] iArr3 = {R.styleable.BottomBar_enabled1, R.styleable.BottomBar_enabled2, R.styleable.BottomBar_enabled3};
        for (int i6 = 0; i6 < 2; i6++) {
            this.mButtons[i6].setEnabled(obtainStyledAttributes.getBoolean(iArr3[i6], true));
        }
        int[] iArr4 = {0, 4, 8};
        int[] iArr5 = {R.styleable.BottomBar_visibility1, R.styleable.BottomBar_visibility2, R.styleable.BottomBar_visibility3};
        for (int i7 = 0; i7 < 2; i7++) {
            this.mButtonContainers[i7].setVisibility(iArr4[obtainStyledAttributes.getInt(iArr5[i7], 0)]);
        }
        setButtonGravity(obtainStyledAttributes.getInt(R.styleable.BottomBar_gravity, -1));
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.BottomBar_android_textAppearance, R.style.BottomBarTextStyle);
        for (int i8 = 0; i8 < 2; i8++) {
            this.mButtons[i8].setTextAppearance(resourceId);
        }
        obtainStyledAttributes.recycle();
        c();
    }
}
