package cn.nubia.common.view;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;
import cn.nubia.common.helper.ExoPlayerManager;

/* loaded from: classes.dex */
public class DynamicView extends PlayerView implements Player.Listener {
    private final String TAG;
    boolean mIsMute;
    ExoPlayerManager mManager;
    int mRepeatMode;
    Uri mUri;

    public DynamicView(Context context) {
        this(context, null);
    }

    public DynamicView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DynamicView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = "Dynamic";
        this.mRepeatMode = 0;
        this.mIsMute = true;
        initManager(context);
    }

    private void init() {
        if (this.mUri == null) {
            Log.w("Dynamic", "initPlayer() error, the uri can not be null !");
            return;
        }
        Log.d("Dynamic", "init()  this : " + this);
        ExoPlayer player = this.mManager.getPlayer();
        this.mManager.preparePlayer(this.mUri, this.mIsMute, this.mRepeatMode);
        setPlayer(player);
        setResizeMode(4);
        setUseController(false);
    }

    private void initManager(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
        this.mManager = ExoPlayerManager.getInstance();
        Log.d("Dynamic", "initManager() this : " + this);
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        Log.d("Dynamic", "onVisibilityChanged() visibility : " + i + ", this : " + this);
        if (this.mUri == null) {
            return;
        }
        if (i == 8 || i == 4) {
            this.mManager.pause(toString());
        } else {
            this.mManager.start(toString());
        }
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        Log.d("Dynamic", "onWindowVisibilityChanged() visibility : " + i);
    }

    public void release() {
        this.mManager.releasePlayer(toString());
    }

    public void setMute(boolean z) {
        this.mIsMute = z;
    }

    public void setRepeated(boolean z) {
        this.mRepeatMode = z ? 1 : 0;
        this.mManager.setRepeatMode(z ? 1 : 0);
    }

    public void setUri(Uri uri) {
        this.mUri = uri;
        init();
    }

    public void setUri(String str) {
        if (str == null) {
            return;
        }
        setUri(Uri.parse(str));
    }

    public void start() {
        try {
            ExoPlayerManager exoPlayerManager = this.mManager;
            if (exoPlayerManager != null) {
                exoPlayerManager.start(toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
