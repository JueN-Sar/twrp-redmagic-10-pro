package cn.nubia.gamelauncher.gamecontrolpanel.controlcenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.RadioButton;
import cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog;

/* loaded from: classes.dex */
public class MarqueeRadioButton extends RadioButton {
    private H mH;

    private class H extends Handler {
        private static final int STATE_CHANGED = 1;

        public H() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
        }
    }

    public MarqueeRadioButton(Context context) {
        super(context);
        this.mH = new H();
    }

    public MarqueeRadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mH = new H();
    }

    public MarqueeRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mH = new H();
    }

    public MarqueeRadioButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mH = new H();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWindowShow() {
        onWindowFocusChanged(GameControlDialog.mDialogIsShowing);
    }

    private void setMarqueeAttr() {
        setSingleLine(true);
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setMarqueeRepeatLimit(5);
        setSelected(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        H h = this.mH;
        if (h != null) {
            h.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.controlcenter.MarqueeRadioButton.1
                @Override // java.lang.Runnable
                public void run() {
                    MarqueeRadioButton.this.onWindowShow();
                }
            }, 1000L);
        }
    }

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        H h = this.mH;
        if (h != null) {
            h.removeCallbacks(null);
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        setMarqueeAttr();
    }
}
