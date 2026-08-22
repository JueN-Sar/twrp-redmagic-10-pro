package cn.nubia.gameassist.plugin.panel;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.theme.Theme;
import cn.nubia.gameassist.theme.ThemeController;
import cn.nubia.gameassist.theme.ThemeDrawable;
import cn.nubia.gameassist.theme.ThemeWidget;
import cn.nubia.gameassist.utils.RecycleWatch;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.DumpController;
import com.zte.gameassist.common.EventListener;
import com.zte.gameassist.common.EventListenerMgr;
import com.zte.gameassist.utils.GaLog;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;

/* loaded from: classes.dex */
public class PluginTileView extends LinearLayout implements DumpController.Dump, ThemeWidget {
    private static final boolean DEBUG = !"user".equals(Build.TYPE);
    private static final String TAG = "PluginTileView";
    private final float TILE_TEXT_ALPHA;
    private CardAttrRectangle mAdvancedCardAttrRectangle;
    private TextView mAdvancedTitle;
    protected final Context mContext;
    private TextView mCustomeTitle;
    private CardAttrRectangle mDifficultCardAttrRectangle;
    private TextView mDifficultTitle;
    private final H mHandler;
    private ImageView mIcon;
    private TextView mIntroduction;
    private TextView mLabel;
    private boolean mLabelHasWindowFocus;
    private EventListener mListener;
    private CardAttrRectangle mOperateCardAttrRectangle;
    private TextView mOperateTitle;
    private CardAttrRectangle mPolicyCardAttrRectangle;
    private TextView mPolicyTitle;
    private ImageView mSettings;

    @VisibleForTesting
    private QSTile.State mState;
    private Theme mTheme;
    private TileDrawable mTileDrawable;
    private Typeface mTypeface;

    private class H extends Handler {
        public H() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1) {
                PluginTileView.this.b((QSTile.State) message.obj);
            }
        }
    }

    private class TileDrawable extends ThemeDrawable {
        @Override // cn.nubia.gameassist.theme.ThemeDrawable, cn.nubia.gameassist.theme.ThemeWidget
        public void d(Theme theme) {
            super.d(theme);
            if (this.f7497k != null) {
                int i2 = 1;
                if (this.f7494h == null) {
                    this.f7494h = PluginTileView.this.mContext.getResources().getDrawable(this.f7497k.g(1));
                }
                Resources resources = PluginTileView.this.mContext.getResources();
                Theme theme2 = this.f7497k;
                if (PluginTileView.this.mIcon != null && PluginTileView.this.mIcon.isSelected()) {
                    i2 = 2;
                }
                this.f7493c = resources.getDrawable(theme2.g(i2));
                setColorFilter(this.f7497k.f7435b);
            }
        }

        private TileDrawable() {
        }
    }

    public PluginTileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTileDrawable = new TileDrawable();
        this.TILE_TEXT_ALPHA = 0.85f;
        this.mHandler = new H();
        this.mLabelHasWindowFocus = true;
        this.mListener = new EventListener() { // from class: cn.nubia.gameassist.plugin.panel.PluginTileView.1
            @Override // com.zte.gameassist.common.EventListener
            public void a(int i2, Object... objArr) {
                PluginTileView.this.i();
            }
        };
        this.mContext = context;
        this.mTypeface = Typeface.create("nubiafont-medium", 0);
        RecycleWatch.j(this, 50);
    }

    private void g(CardAttrRectangle cardAttrRectangle, QSTile.State state) {
        if (cardAttrRectangle == null) {
            return;
        }
        cardAttrRectangle.getOverlay().clear();
        cardAttrRectangle.setSelected(state.f6175i);
        cardAttrRectangle.d(this.mTheme);
    }

    private void j(TextView textView, QSTile.State state) {
        if (textView != null) {
            textView.setTextColor(this.mContext.getColor(state.f6175i ? R.color.plugin_tile_label_highlight : R.color.game_tile_label_normal));
        }
    }

    private void setAttributeColorNum(String str) {
        int i2;
        int i3;
        int i4;
        if (this.mPolicyCardAttrRectangle == null || this.mOperateCardAttrRectangle == null || this.mAdvancedCardAttrRectangle == null || this.mDifficultCardAttrRectangle == null) {
            return;
        }
        int[] p2 = Utils.p(str);
        int i5 = 0;
        if (p2 != null) {
            GaLog.a(TAG, "setAttributeColorNum " + str + ": " + Arrays.toString(p2));
            i5 = p2[0];
            i2 = p2[1];
            i4 = p2[2];
            i3 = p2[3];
        } else {
            i2 = 0;
            i3 = 0;
            i4 = 0;
        }
        this.mPolicyCardAttrRectangle.setColorRectangleNum(i5);
        this.mOperateCardAttrRectangle.setColorRectangleNum(i2);
        this.mAdvancedCardAttrRectangle.setColorRectangleNum(i4);
        this.mDifficultCardAttrRectangle.setColorRectangleNum(i3);
    }

    private void setTextAttr(TextView textView) {
        if (textView != null) {
            textView.setTypeface(this.mTypeface);
            textView.setElegantTextHeight(true);
            textView.setSingleLine(true);
            textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            textView.setSelected(true);
            textView.setFocusable(true);
            textView.setFocusableInTouchMode(true);
            textView.setMarqueeRepeatLimit(5);
            textView.setAlpha(0.85f);
            textView.setClickable(false);
        }
    }

    protected void b(QSTile.State state) {
        if (DEBUG) {
            GaLog.e(TAG, "handleStateChanged " + state);
        }
        this.mState = state;
        setIcon(state);
        TextView textView = this.mLabel;
        if (textView != null) {
            textView.setText(state.f6169c);
        }
        setAttributeColorNum(state.f6177k);
        TextView textView2 = this.mIntroduction;
        if (textView2 != null) {
            textView2.setText(state.f6170d);
        }
        if (state.f6178l) {
            j(this.mLabel, state);
            j(this.mIntroduction, state);
            j(this.mCustomeTitle, state);
            j(this.mPolicyTitle, state);
            j(this.mOperateTitle, state);
            j(this.mAdvancedTitle, state);
            j(this.mDifficultTitle, state);
        } else {
            this.mLabel.setTextColor(this.mContext.getColor(state.f6175i ? R.color.game_tile_label_highlight : R.color.game_tile_label_normal));
        }
        setContentDescription(state.f6172f);
    }

    @Override // com.zte.gameassist.common.DumpController.Dump
    public void c(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (printWriter != null) {
            printWriter.print(" PluginTileView:");
            TextView textView = this.mLabel;
            if (textView != null) {
                printWriter.print(textView.getText().toString());
            }
            TextView textView2 = this.mIntroduction;
            if (textView2 != null) {
                printWriter.print(textView2.getText().toString());
            }
            printWriter.print(" getAlpha=" + getAlpha());
            printWriter.println(" getVisibility=" + getVisibility());
        }
    }

    @Override // cn.nubia.gameassist.theme.ThemeWidget
    public void d(Theme theme) {
        this.mTheme = theme;
        this.mTileDrawable.d(theme);
    }

    public void e(View.OnClickListener onClickListener) {
        setOnClickListener(onClickListener);
    }

    public void f(View.OnClickListener onClickListener) {
        ImageView imageView = this.mSettings;
        if (imageView != null) {
            imageView.setOnClickListener(onClickListener);
        }
    }

    @VisibleForTesting
    public QSTile.State getState() {
        return this.mState;
    }

    public void h(QSTile.State state) {
        if (DEBUG) {
            GaLog.e(TAG, "onStateChanged " + state);
        }
        this.mHandler.obtainMessage(1, state).sendToTarget();
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public void i() {
        boolean z = GameAssistWindowManager.W;
        if (this.mLabelHasWindowFocus == z) {
            return;
        }
        this.mLabelHasWindowFocus = z;
        TextView textView = this.mLabel;
        if (textView != null) {
            textView.onWindowFocusChanged(z);
        }
        TextView textView2 = this.mIntroduction;
        if (textView2 != null) {
            textView2.onWindowFocusChanged(z);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        EventListenerMgr.b(this.mListener, 3);
        ThemeController.m().h(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventListenerMgr.i(this.mListener);
        ThemeController.m().p(this);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mIcon = (ImageView) findViewById(R.id.tile_icon);
        findViewById(R.id.tile_layout).setBackground(this.mTileDrawable);
        this.mSettings = (ImageView) findViewById(R.id.tile_settings);
        TextView textView = (TextView) findViewById(R.id.tile_lable);
        this.mLabel = textView;
        setTextAttr(textView);
        this.mPolicyCardAttrRectangle = (CardAttrRectangle) findViewById(R.id.cardAttrRectangle_policy);
        this.mOperateCardAttrRectangle = (CardAttrRectangle) findViewById(R.id.cardAttrRectangle_operate);
        this.mAdvancedCardAttrRectangle = (CardAttrRectangle) findViewById(R.id.cardAttrRectangle_advanced);
        this.mDifficultCardAttrRectangle = (CardAttrRectangle) findViewById(R.id.cardAttrRectangle_difficult);
        this.mIntroduction = (TextView) findViewById(R.id.text_introduction);
        this.mCustomeTitle = (TextView) findViewById(R.id.title_custome);
        this.mPolicyTitle = (TextView) findViewById(R.id.title_policy);
        this.mOperateTitle = (TextView) findViewById(R.id.title_operate);
        this.mAdvancedTitle = (TextView) findViewById(R.id.title_advanced);
        this.mDifficultTitle = (TextView) findViewById(R.id.title_difficult);
        setTextAttr(this.mIntroduction);
        setTextAttr(this.mCustomeTitle);
        setTextAttr(this.mPolicyTitle);
        setTextAttr(this.mOperateTitle);
        setTextAttr(this.mAdvancedTitle);
        setTextAttr(this.mDifficultTitle);
        this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.panel.PluginTileView.2
            @Override // java.lang.Runnable
            public void run() {
                PluginTileView.this.i();
            }
        }, 500L);
    }

    protected void setIcon(QSTile.State state) {
        ImageView imageView = this.mIcon;
        if (imageView != null) {
            imageView.getOverlay().clear();
            QSTile.Icon icon = state.f6168b;
            this.mIcon.setImageDrawable(icon != null ? icon.a(this.mContext) : null);
            this.mIcon.setSelected(state.f6175i);
        }
        ImageView imageView2 = this.mSettings;
        if (imageView2 != null) {
            imageView2.getOverlay().clear();
            QSTile.Icon icon2 = state.f6171e;
            this.mSettings.setImageDrawable(icon2 != null ? icon2.a(this.mContext) : null);
            d(this.mTheme);
        }
        g(this.mPolicyCardAttrRectangle, state);
        g(this.mOperateCardAttrRectangle, state);
        g(this.mAdvancedCardAttrRectangle, state);
        g(this.mDifficultCardAttrRectangle, state);
    }

    @Override // android.view.View
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        setOnLongClickListener(onLongClickListener);
    }
}
