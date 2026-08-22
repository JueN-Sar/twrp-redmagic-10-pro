package cn.nubia.gameassist.dessert.custom;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSState;
import cn.nubia.gameassist.view.NubiaMarqueeView;
import com.zte.gameassist.common.InflaterHelper;
import java.util.Objects;

/* loaded from: classes.dex */
public class CustomizeTileView extends FrameLayout {
    public static final int OVERLAY_HEIGHT = 20;
    public static final int OVERLAY_OFFSET = 10;
    public static final int OVERLAY_WIDTH = 20;
    private ImageView mAppIcon;
    private NubiaMarqueeView mAppLabel;
    private ImageView mBg;
    private Context mContext;
    private float mDensity;

    public CustomizeTileView(Context context, float f2) {
        super(context);
        this.mContext = context;
        InflaterHelper.f(R.layout.qs_customize_tile_frame, this);
        this.mAppLabel = (NubiaMarqueeView) findViewById(R.id.label);
        this.mAppIcon = (ImageView) findViewById(R.id.icon);
        this.mBg = (ImageView) findViewById(R.id.bg);
        this.mDensity = f2;
    }

    public TextView getAppLabel() {
        return this.mAppLabel;
    }

    public void setAppLabel(CharSequence charSequence) {
        if (Objects.equals(charSequence, this.mAppLabel.getText())) {
            return;
        }
        this.mAppLabel.setText(charSequence);
    }

    public void setIcon(QSState.Icon icon) {
        this.mAppIcon.getOverlay().clear();
        if (icon instanceof QSState.DrawableIcon) {
            this.mAppIcon.setBackground(((QSState.DrawableIcon) icon).a(this.mContext));
            this.mBg.setVisibility(0);
        } else if (!(icon instanceof QSState.ResourceIcon)) {
            this.mBg.setVisibility(8);
        } else {
            this.mAppIcon.setBackgroundResource(((QSState.ResourceIcon) icon).b());
            this.mBg.setVisibility(0);
        }
    }

    public void setOverLay(QSState.Icon icon) {
        if (icon instanceof QSState.DrawableIcon) {
            this.mAppIcon.getOverlay().clear();
            Drawable a2 = ((QSState.DrawableIcon) icon).a(this.mContext);
            a2.setTint(this.mContext.getColor(R.color.usedefine_third_icon_color));
            float f2 = this.mDensity;
            Rect rect = new Rect(0, 0, ((int) f2) * 20, ((int) f2) * 20);
            float f3 = this.mDensity;
            rect.offset(((int) f3) * 10, ((int) f3) * 10);
            a2.setBounds(rect);
            this.mAppIcon.getOverlay().add(a2);
        }
    }

    public void setShowAppLabel(boolean z) {
        this.mAppLabel.setVisibility(z ? 0 : 4);
    }

    public void setIcon(int i2) {
        this.mAppIcon.setBackgroundResource(i2);
    }
}
