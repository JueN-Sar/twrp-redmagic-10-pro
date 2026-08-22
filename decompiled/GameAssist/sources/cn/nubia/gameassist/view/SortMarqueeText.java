package cn.nubia.gameassist.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/* loaded from: classes.dex */
public class SortMarqueeText extends TextView {
    public SortMarqueeText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        if (!isSingleLine()) {
            setSingleLine(true);
        }
        TextUtils.TruncateAt ellipsize = getEllipsize();
        TextUtils.TruncateAt truncateAt = TextUtils.TruncateAt.MARQUEE;
        if (ellipsize != truncateAt) {
            setEllipsize(truncateAt);
        }
        setMarqueeRepeatLimit(-1);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        if (i2 == 0) {
            setSelected(true);
        } else {
            setSelected(false);
        }
    }

    public SortMarqueeText(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }

    public SortMarqueeText(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        a();
    }
}
