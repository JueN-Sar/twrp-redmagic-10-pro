package cn.nubia.gameassist.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cn.nubia.gameassist.R;

/* loaded from: classes.dex */
public class RotationFrameLayout extends FrameLayout {
    private View mClickView;
    private View mShowView;

    public RotationFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final <T extends View> T getClickView() {
        return (T) this.mClickView;
    }

    public final <T extends View> T getShowView() {
        return (T) this.mShowView;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mShowView = findViewById(R.id.show_view);
        this.mClickView = findViewById(R.id.click_view);
        this.mShowView.setRotation(90.0f);
    }

    public RotationFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public RotationFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }
}
