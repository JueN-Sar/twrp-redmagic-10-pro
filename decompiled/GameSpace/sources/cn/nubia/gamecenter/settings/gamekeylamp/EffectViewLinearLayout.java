package cn.nubia.gamecenter.settings.gamekeylamp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import cn.nubia.gamecenter.settings.R;

/* loaded from: classes.dex */
public class EffectViewLinearLayout extends LinearLayout {
    private Context mContext;
    private View mEffectBackground;
    private EffectRedrawView mRedrawView;
    private EffectSelectView mSelectView;
    private int[] mSummarys;
    private TextView mTextView;
    private int textDefaultColor;
    private int textSelectColor;

    public EffectViewLinearLayout(Context context) {
        this(context, null);
    }

    public EffectViewLinearLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EffectViewLinearLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public EffectViewLinearLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mContext = context;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.gcs_colorfullight_effect_view, (ViewGroup) this, true);
        this.textDefaultColor = ContextCompat.getColor(this.mContext, R.color.gamespace_summary_color);
        this.textSelectColor = ContextCompat.getColor(this.mContext, R.color.colorful_light_text);
        this.mSummarys = new int[]{R.string.lamp_mode_light_with_music, R.string.lamp_mode_all_bright, R.string.lamp_mode_breath, R.string.lamp_mode_flashing, R.string.lamp_mode_scintillation, R.string.lamp_mode_flow, R.string.color_mode_Ripple, R.string.color_mode_Echo, R.string.color_mode_Hopping, R.string.color_mode_Flashing, R.string.color_mode_fl_to_fl};
    }

    public void animal(boolean z) {
        if (z) {
            this.mRedrawView.start();
        } else {
            this.mRedrawView.stop();
        }
    }

    public boolean hasSelectView(View view) {
        return this.mSelectView.equals(view);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mEffectBackground = findViewById(R.id.redraw_background);
        this.mRedrawView = (EffectRedrawView) findViewById(R.id.redraw_view);
        this.mTextView = (TextView) findViewById(R.id.text_view);
        this.mSelectView = (EffectSelectView) findViewById(R.id.effect_select);
    }

    public void setAnimalVisibility(int i) {
        this.mRedrawView.setVisibility(i);
    }

    public void setClickListener(View.OnClickListener onClickListener) {
        this.mSelectView.setOnClickListener(onClickListener);
    }

    public void setEffectType(Effect effect) {
        this.mTextView.setText(effect.name);
        this.mRedrawView.setEffectType(effect.getEffectId());
    }

    public void setPaintColor(int[] iArr) {
        this.mRedrawView.setPaintColor(iArr);
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        this.mSelectView.setSelected(z);
        if (z) {
            this.mEffectBackground.setVisibility(0);
            this.mTextView.setTextColor(this.textSelectColor);
            setAnimalVisibility(0);
        } else {
            this.mEffectBackground.setVisibility(8);
            this.mTextView.setTextColor(this.textDefaultColor);
            setAnimalVisibility(8);
        }
        animal(z);
    }
}
