package cn.nubia.plugin.gameshader;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/* loaded from: classes.dex */
public class ShaderWindowView extends RelativeLayout {
    private static final String TAG = "GameShaderMgr";
    private boolean mDynamic;

    public ShaderWindowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDynamic = false;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mDynamic) {
            invalidate();
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setDynamic(boolean z) {
        this.mDynamic = z;
        invalidate();
    }

    public ShaderWindowView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mDynamic = false;
    }
}
