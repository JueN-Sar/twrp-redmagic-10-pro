package cn.nubia.gamelauncher.gamecontrolpanel;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;

/* loaded from: classes.dex */
public class MarqueeTextView extends TextView {
    private static final int MARQUEE_REPEAT_COUNT = -1;
    private static final String TAG = "MarqueeTextView";
    private H mH;
    private boolean mHasFocus;

    private class H extends Handler {
        private static final int STATE_CHANGED = 1;

        public H() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
        }
    }

    public MarqueeTextView(Context context) {
        super(context);
        this.mH = new H();
    }

    public MarqueeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mH = new H();
    }

    public MarqueeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mH = new H();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWindowShow() {
        onWindowFocusChanged(GameControlDialog.mDialogIsShowing || this.mHasFocus);
    }

    private void setMarqueeAttr() {
        setSingleLine(true);
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setMarqueeRepeatLimit(2);
        setSelected(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        onWindowFocusChanged(false);
        H h = this.mH;
        if (h != null) {
            h.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.MarqueeTextView.1
                @Override // java.lang.Runnable
                public void run() {
                    if (!GameControlDialog.mDialogIsShowing) {
                        LogUtil.i("MarqueeTextView", " onWindowShow isShowing = " + GameControlDialog.mDialogIsShowing + " ;; mHasFocus = " + MarqueeTextView.this.mHasFocus);
                        MarqueeTextView.this.mHasFocus = true;
                    }
                    MarqueeTextView.this.onWindowShow();
                }
            }, 500L);
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

    public void setHasFocus(boolean z) {
        this.mHasFocus = z;
    }
}
