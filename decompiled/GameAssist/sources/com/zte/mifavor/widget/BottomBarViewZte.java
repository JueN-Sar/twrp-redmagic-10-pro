package com.zte.mifavor.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.zte.extres.R;

/* loaded from: classes2.dex */
public class BottomBarViewZte extends LinearLayout {
    public static final int LEFT_BUTTON = 0;
    public static final int RIGHT_BUTTON = 1;
    private static final String TAG = "BottomBarZte";
    public ViewGroup mButtonContainer1;
    public ViewGroup mButtonContainer2;
    public TextView mOperateTextView1;
    public TextView mOperateTextView2;
    public ImageView mOperateView1;
    public ImageView mOperateView2;

    public BottomBarViewZte(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(getContext()).inflate(R.layout.operations_bottombar2, (ViewGroup) this, true);
    }

    private void a() {
        this.mButtonContainer1 = (ViewGroup) findViewById(R.id.container1);
        this.mButtonContainer2 = (ViewGroup) findViewById(R.id.container2);
        this.mOperateView1 = (ImageView) findViewById(R.id.operateView1);
        this.mOperateView2 = (ImageView) findViewById(R.id.operateView2);
        this.mOperateTextView1 = (TextView) findViewById(R.id.operateTextView1);
        this.mOperateTextView2 = (TextView) findViewById(R.id.operateTextView2);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.bottombar);
        Drawable background = linearLayout.getBackground();
        float elevation = linearLayout.getElevation();
        setBackground(background);
        setElevation(elevation);
        linearLayout.setBackground(null);
        b();
    }

    private void b() {
        if (!Utils.o(getContext())) {
            Log.d(TAG, "it is not in Indicator Mode.");
            return;
        }
        View findViewById = findViewById(R.id.indicator_area);
        if (findViewById == null) {
            Log.d(TAG, "indicator Area is null.");
        } else {
            Log.d(TAG, "show indicator Area.");
            findViewById.setVisibility(0);
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        a();
    }

    public BottomBarViewZte(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        LayoutInflater.from(getContext()).inflate(R.layout.operations_bottombar2, (ViewGroup) this, true);
    }
}
