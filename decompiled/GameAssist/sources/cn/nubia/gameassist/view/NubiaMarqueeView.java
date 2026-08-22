package cn.nubia.gameassist.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import com.zte.gameassist.common.EventListener;
import com.zte.gameassist.common.EventListenerMgr;

/* loaded from: classes.dex */
public class NubiaMarqueeView extends TextView {
    private final float TILE_TEXT_ALPHA;
    private boolean mLabelHasWindowFocus;
    private EventListener mListener;

    public NubiaMarqueeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TILE_TEXT_ALPHA = 0.85f;
        this.mLabelHasWindowFocus = true;
        this.mListener = new EventListener() { // from class: cn.nubia.gameassist.view.NubiaMarqueeView.1
            @Override // com.zte.gameassist.common.EventListener
            public void a(int i2, Object... objArr) {
                NubiaMarqueeView.this.a();
            }
        };
    }

    public void a() {
        boolean z = GameAssistWindowManager.W;
        if (this.mLabelHasWindowFocus == z) {
            return;
        }
        this.mLabelHasWindowFocus = z;
        onWindowFocusChanged(z);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        EventListenerMgr.b(this.mListener, 3);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventListenerMgr.i(this.mListener);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        setElegantTextHeight(true);
        setSingleLine(true);
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setSelected(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setMarqueeRepeatLimit(5);
        setAlpha(0.85f);
        setClickable(false);
    }

    public NubiaMarqueeView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.TILE_TEXT_ALPHA = 0.85f;
        this.mLabelHasWindowFocus = true;
        this.mListener = new EventListener() { // from class: cn.nubia.gameassist.view.NubiaMarqueeView.1
            @Override // com.zte.gameassist.common.EventListener
            public void a(int i22, Object... objArr) {
                NubiaMarqueeView.this.a();
            }
        };
    }

    public NubiaMarqueeView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.TILE_TEXT_ALPHA = 0.85f;
        this.mLabelHasWindowFocus = true;
        this.mListener = new EventListener() { // from class: cn.nubia.gameassist.view.NubiaMarqueeView.1
            @Override // com.zte.gameassist.common.EventListener
            public void a(int i22, Object... objArr) {
                NubiaMarqueeView.this.a();
            }
        };
    }
}
