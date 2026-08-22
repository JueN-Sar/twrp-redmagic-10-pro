package cn.nubia.gamelauncher.wallpaper;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/* loaded from: classes.dex */
public class LiveWallpaper extends FrameLayout {
    public LiveWallpaper(Context context) {
        this(context, null);
    }

    public LiveWallpaper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        loadLiveWallpaper();
    }

    private void loadLiveWallpaper() {
        addView(new LiveWallpaperView(getContext()), 0);
    }
}
