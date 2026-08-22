package cn.nubia.gameassist.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import com.zte.gameassist.common.EventListener;
import com.zte.gameassist.common.EventListenerMgr;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class NubiaButtonView extends LinearLayout {
    private final boolean Debug;
    private final String TAG;
    private ImageView imageView;
    private EventListener mListener;
    private boolean mTextViewHasWindowFocus;
    private TextView textView;

    public NubiaButtonView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void b() {
        this.textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        this.textView.setSingleLine(true);
        this.textView.setSelected(true);
        this.textView.setFocusable(true);
        this.textView.setFocusableInTouchMode(true);
        this.textView.setMarqueeRepeatLimit(5);
    }

    public void a() {
        boolean z = GameAssistWindowManager.W;
        if (this.mTextViewHasWindowFocus == z) {
            return;
        }
        this.mTextViewHasWindowFocus = z;
        this.textView.onWindowFocusChanged(z);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        EventListenerMgr.b(this.mListener, 3);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventListenerMgr.i(this.mListener);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        b();
    }

    public void setHighlight(boolean z) {
        if (this.Debug) {
            GaLog.e("NubiaButtonView", "setHighlight " + z + " " + this);
        }
        this.textView.setTextColor(getResources().getColor(z ? R.color.nubia_left_panel_button_lable_highlight : R.color.nubia_left_panel_button_lable));
        this.imageView.setSelected(z);
    }

    public void setText(int i2) {
        this.textView.setText(i2);
    }

    public NubiaButtonView(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public NubiaButtonView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.TAG = "NubiaButtonView";
        this.Debug = "userdebug".equals(Build.TYPE);
        this.mTextViewHasWindowFocus = true;
        this.mListener = new EventListener() { // from class: cn.nubia.gameassist.view.NubiaButtonView.1
            @Override // com.zte.gameassist.common.EventListener
            public void a(int i4, Object... objArr) {
                NubiaButtonView.this.a();
            }
        };
        View g2 = InflaterHelper.g(R.layout.nubia_button_layout, this, true);
        this.imageView = (ImageView) g2.findViewById(R.id.img);
        this.textView = (TextView) g2.findViewById(R.id.tv);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.nubiabuttonview);
        this.textView.setText(obtainStyledAttributes.getString(R.styleable.nubiabuttonview_button_text));
        this.imageView.setBackground(obtainStyledAttributes.getDrawable(R.styleable.nubiabuttonview_button_background));
        obtainStyledAttributes.recycle();
    }
}
