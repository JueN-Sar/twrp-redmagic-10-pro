package com.zte.mifavor.widget;

import android.R;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

/* loaded from: classes2.dex */
public class EditTextZTE extends EditText {
    private static final int[] STATE_FOCUSED = {R.attr.state_focused};
    private static final int[] STATE_DEFAULT = {-16842908};

    public EditTextZTE(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.editTextStyle);
    }

    public void a(int i2, int i3) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable drawable = getResources().getDrawable(com.zte.extres.R.drawable.textfield_default);
        Drawable drawable2 = getResources().getDrawable(com.zte.extres.R.drawable.textfield_actived);
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        drawable.setTintMode(mode);
        drawable2.setTintMode(mode);
        drawable.setTint(i3);
        stateListDrawable.addState(STATE_DEFAULT, drawable);
        drawable2.setTint(i2);
        drawable2.setAlpha(255);
        stateListDrawable.addState(STATE_FOCUSED, drawable2);
        Drawable background = getBackground();
        if (!(background instanceof InsetDrawable)) {
            setBackgroundDrawable(stateListDrawable);
            return;
        }
        InsetDrawable insetDrawable = (InsetDrawable) background;
        insetDrawable.setDrawable(stateListDrawable);
        setBackgroundDrawable(insetDrawable);
    }

    @Override // android.widget.TextView
    public void setError(CharSequence charSequence, Drawable drawable) {
        super.setError(null, null);
        a(getResources().getColor(com.zte.extres.R.color.mfv_common_tf_wrong), getResources().getColor(com.zte.extres.R.color.mfv_common_tf));
    }

    public EditTextZTE(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public EditTextZTE(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        int color = context.getResources().getColor(com.zte.extres.R.color.mfv_common_tf_fc);
        int color2 = context.getResources().getColor(com.zte.extres.R.color.mfv_common_tf);
        Log.e("EditTextZTE", "color =" + color);
        a(color, color2);
        if (Utils.f17815b) {
            setForceDarkAllowed(false);
        }
    }
}
