package cn.nubia.multisubscreen.view;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.theme.Theme;
import cn.nubia.gameassist.theme.ThemeDrawable;
import cn.nubia.gameassist.theme.ThemeWidget;
import cn.nubia.gameassist.utils.CommonUtil;
import cn.nubia.gameassist.utils.RecycleWatch;
import cn.nubia.multisubscreen.mgr.MultiSubScreenThemeMgr;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class MultiSubScreenTileView extends RelativeLayout implements ThemeWidget {
    private static final boolean DEBUG = !"user".equals(Build.TYPE);
    private static final String TAG = "MultiSubScreen_MultiSubScreenTileView";
    private final float TILE_TEXT_ALPHA;
    private ImageView mBadge;
    protected final Context mContext;
    private final H mHandler;
    private ImageView mIcon;
    private TextView mLabel;
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
                MultiSubScreenTileView.this.b((QSTile.State) message.obj);
            }
        }
    }

    private class TileDrawable extends ThemeDrawable {
        @Override // cn.nubia.gameassist.theme.ThemeDrawable, cn.nubia.gameassist.theme.ThemeWidget
        public void d(Theme theme) {
            super.d(theme);
            if (this.f7497k != null) {
                if (this.f7494h == null) {
                    this.f7494h = MultiSubScreenTileView.this.mContext.getResources().getDrawable(this.f7497k.d(1));
                }
                this.f7493c = MultiSubScreenTileView.this.mContext.getResources().getDrawable(this.f7497k.d(MultiSubScreenTileView.this.mIcon.isSelected() ? 2 : 1));
                setColorFilter(this.f7497k.f7435b);
            }
        }

        private TileDrawable() {
        }
    }

    public MultiSubScreenTileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTileDrawable = new TileDrawable();
        this.TILE_TEXT_ALPHA = 0.85f;
        this.mHandler = new H();
        this.mContext = context;
        this.mTypeface = Typeface.create("nubiafont-medium", 0);
        RecycleWatch.j(this, 50);
    }

    protected void b(QSTile.State state) {
        if (DEBUG) {
            GaLog.e(TAG, "handleStateChanged " + state);
        }
        setIcon(state);
        this.mLabel.setText(state.f6169c);
        if (state.f6178l) {
            this.mLabel.setTextColor(this.mContext.getColor(state.f6175i ? R.color.plugin_tile_label_highlight : R.color.game_tile_label_normal));
        } else {
            this.mLabel.setTextColor(this.mContext.getColor(state.f6175i ? R.color.game_tile_label_highlight : R.color.game_tile_label_normal));
        }
    }

    public void c(View.OnClickListener onClickListener) {
        setOnClickListener(onClickListener);
    }

    @Override // cn.nubia.gameassist.theme.ThemeWidget
    public void d(Theme theme) {
        this.mTheme = theme;
        GaLog.a(TAG, "onThemeChanged theme = " + theme);
        this.mTileDrawable.d(theme);
    }

    public void e(QSTile.State state) {
        if (DEBUG) {
            GaLog.e(TAG, "onStateChanged " + state);
        }
        this.mHandler.obtainMessage(1, state).sendToTarget();
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        GaLog.a(TAG, "onAttachedToWindow");
        MultiSubScreenThemeMgr.e().b(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        GaLog.a(TAG, "onDetachedFromWindow");
        MultiSubScreenThemeMgr.e().g(this);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        ImageView imageView = (ImageView) findViewById(R.id.multi_sub_screen_tile_icon);
        this.mIcon = imageView;
        imageView.setBackground(this.mTileDrawable);
        this.mBadge = (ImageView) findViewById(R.id.multi_sub_screen_tile_icon_badge);
        TextView textView = (TextView) findViewById(R.id.multi_sub_screen_tile_lable);
        this.mLabel = textView;
        textView.setTypeface(this.mTypeface);
        this.mLabel.setElegantTextHeight(true);
        this.mLabel.setSingleLine(true);
        this.mLabel.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        this.mLabel.setSelected(true);
        this.mLabel.setFocusable(true);
        this.mLabel.setMarqueeRepeatLimit(5);
        this.mLabel.setAlpha(0.85f);
    }

    public void setBadgeVisibility(boolean z) {
        if (z) {
            this.mBadge.setVisibility(0);
        } else {
            this.mBadge.setVisibility(8);
        }
    }

    protected void setIcon(QSTile.State state) {
        this.mIcon.getOverlay().clear();
        QSTile.Icon icon = state.f6168b;
        Drawable a2 = icon != null ? icon.a(this.mContext) : null;
        this.mIcon.setSelected(state.f6175i);
        this.mIcon.setImageDrawable(a2);
        d(this.mTheme);
    }

    public void setLabelLayoutParams(int i2) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mLabel.getLayoutParams();
        layoutParams.width = CommonUtil.a(this.mContext, i2);
        this.mLabel.setLayoutParams(layoutParams);
    }
}
