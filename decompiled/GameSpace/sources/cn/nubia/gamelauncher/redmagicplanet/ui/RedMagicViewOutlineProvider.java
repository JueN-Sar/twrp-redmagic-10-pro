package cn.nubia.gamelauncher.redmagicplanet.ui;

import android.content.Context;
import android.graphics.Outline;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewOutlineProvider;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.util.CommonUtil;

/* loaded from: classes.dex */
public class RedMagicViewOutlineProvider extends ViewOutlineProvider {
    private static final String TAG = "TextureVideoViewOutlineProvider";
    private float mRadius;

    public RedMagicViewOutlineProvider(float f) {
        this.mRadius = f;
    }

    private boolean isRightFixed(Context context) {
        return CommonUtil.isInternalVersion() || context.getResources().getBoolean(R.bool.red_magic_preview_video_width_is_fixed);
    }

    @Override // android.view.ViewOutlineProvider
    public void getOutline(View view, Outline outline) {
        Rect rect = new Rect();
        view.getLocalVisibleRect(rect);
        int i = rect.right - rect.left;
        if (isRightFixed(view.getContext())) {
            i = view.getContext().getResources().getDimensionPixelSize(R.dimen.red_magic_preview_video_width);
        }
        outline.setRoundRect(new Rect(0, rect.top, i, rect.bottom - rect.top), this.mRadius);
    }
}
