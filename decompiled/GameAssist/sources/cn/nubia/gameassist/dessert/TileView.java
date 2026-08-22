package cn.nubia.gameassist.dessert;

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
import androidx.annotation.VisibleForTesting;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.theme.Theme;
import cn.nubia.gameassist.theme.ThemeController;
import cn.nubia.gameassist.theme.ThemeDrawable;
import cn.nubia.gameassist.theme.ThemeWidget;
import cn.nubia.gameassist.utils.CommonUtil;
import cn.nubia.gameassist.utils.RecycleWatch;
import com.zte.gameassist.common.DumpController;
import com.zte.gameassist.common.EventListener;
import com.zte.gameassist.common.EventListenerMgr;
import com.zte.gameassist.utils.GaLog;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class TileView extends RelativeLayout implements DumpController.Dump, ThemeWidget {
    private static final boolean DEBUG = !"user".equals(Build.TYPE);
    private static final String TAG = "TileView";
    private final float TILE_TEXT_ALPHA;
    private ImageView mBadge;
    protected final Context mContext;
    private final H mHandler;
    private ImageView mIcon;
    private TextView mLabel;
    private boolean mLabelHasWindowFocus;
    private EventListener mListener;

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
                TileView.this.b((QSTile.State) message.obj);
            }
        }
    }

    private class TileDrawable extends ThemeDrawable {
        @Override // cn.nubia.gameassist.theme.ThemeDrawable, cn.nubia.gameassist.theme.ThemeWidget
        public void d(Theme theme) {
            super.d(theme);
            if (this.f7497k != null) {
                if (this.f7494h == null) {
                    this.f7494h = TileView.this.mContext.getResources().getDrawable(this.f7497k.d(1));
                }
                this.f7493c = TileView.this.mContext.getResources().getDrawable(this.f7497k.d(TileView.this.mIcon.isSelected() ? 2 : 1));
                setColorFilter(this.f7497k.f7435b);
            }
        }

        private TileDrawable() {
        }
    }

    public TileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTileDrawable = new TileDrawable();
        this.TILE_TEXT_ALPHA = 0.85f;
        this.mHandler = new H();
        this.mLabelHasWindowFocus = true;
        this.mListener = new EventListener() { // from class: cn.nubia.gameassist.dessert.TileView.1
            @Override // com.zte.gameassist.common.EventListener
            public void a(int i2, Object... objArr) {
                TileView.this.h();
            }
        };
        this.mContext = context;
        this.mTypeface = Typeface.create("nubiafont-medium", 0);
        RecycleWatch.j(this, 50);
    }

    protected void b(QSTile.State state) {
        if (DEBUG) {
            GaLog.e(TAG, "handleStateChanged " + state);
        }
        this.mState = state;
        setIcon(state);
        this.mLabel.setText(state.f6169c);
        if (state.f6178l) {
            this.mLabel.setTextColor(this.mContext.getColor(state.f6175i ? R.color.plugin_tile_label_highlight : R.color.game_tile_label_normal));
        } else {
            this.mLabel.setTextColor(this.mContext.getColor(state.f6175i ? R.color.game_tile_label_highlight : R.color.game_tile_label_normal));
        }
    }

    @Override // com.zte.gameassist.common.DumpController.Dump
    public void c(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (printWriter != null) {
            printWriter.print(" TileView:");
            TextView textView = this.mLabel;
            if (textView != null) {
                printWriter.print(textView.getText().toString());
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

    public void f(View.OnLongClickListener onLongClickListener) {
        setOnLongClickListener(onLongClickListener);
    }

    public void g(QSTile.State state) {
        if (DEBUG) {
            GaLog.e(TAG, "onStateChanged " + state);
        }
        this.mHandler.obtainMessage(1, state).sendToTarget();
    }

    @VisibleForTesting
    public QSTile.State getState() {
        return this.mState;
    }

    public void h() {
        boolean z = GameAssistWindowManager.W;
        if (this.mLabelHasWindowFocus == z) {
            return;
        }
        this.mLabelHasWindowFocus = z;
        this.mLabel.onWindowFocusChanged(z);
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
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
        ImageView imageView = (ImageView) findViewById(R.id.tile_icon);
        this.mIcon = imageView;
        imageView.setBackground(this.mTileDrawable);
        this.mBadge = (ImageView) findViewById(R.id.tile_icon_badge);
        TextView textView = (TextView) findViewById(R.id.tile_lable);
        this.mLabel = textView;
        textView.setTypeface(this.mTypeface);
        this.mLabel.setElegantTextHeight(true);
        this.mLabel.setSingleLine(true);
        this.mLabel.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        this.mLabel.setSelected(true);
        this.mLabel.setFocusable(true);
        this.mLabel.setMarqueeRepeatLimit(5);
        this.mLabel.setAlpha(0.85f);
        this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.dessert.TileView.2
            @Override // java.lang.Runnable
            public void run() {
                TileView.this.h();
            }
        }, 500L);
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
