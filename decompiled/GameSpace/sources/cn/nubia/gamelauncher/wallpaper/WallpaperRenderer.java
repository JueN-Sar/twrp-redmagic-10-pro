package cn.nubia.gamelauncher.wallpaper;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.Surface;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes.dex */
public class WallpaperRenderer implements GLSurfaceView.Renderer {
    public static final String TAG = "GSWallpaper";
    private int GL_TEXTURE_EXTERNAL_OES = 36197;
    Context mContext;
    private MediaPlayer mPlayer;
    private Rect mRect;
    private SurfaceTexture mSurface;
    private int mTextureID;

    public WallpaperRenderer(Context context, MediaPlayer mediaPlayer) {
        this.mContext = context;
        this.mPlayer = mediaPlayer;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        GLES20.glClear(16640);
        this.mSurface.updateTexImage();
        this.mRect.drawSelf(this.mTextureID);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        Log.i("GSWallpaper", "onSurfaceChanged() width : " + i + ", height : " + i2);
        gl10.glViewport(0, 0, i, i2);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        Log.i("GSWallpaper", "onSurfaceCreated()");
        GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        GLES20.glEnable(2929);
        GLES20.glDisable(2884);
        this.mRect = new Rect(this.mContext);
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        int i = iArr[0];
        this.mTextureID = i;
        GLES20.glBindTexture(this.GL_TEXTURE_EXTERNAL_OES, i);
        ShaderUtil.checkGlError("glBindTexture mTextureID");
        GLES20.glTexParameterf(this.GL_TEXTURE_EXTERNAL_OES, 10241, 9728.0f);
        GLES20.glTexParameterf(this.GL_TEXTURE_EXTERNAL_OES, 10240, 9729.0f);
        GLES20.glTexParameteri(this.GL_TEXTURE_EXTERNAL_OES, 10242, 33071);
        GLES20.glTexParameteri(this.GL_TEXTURE_EXTERNAL_OES, 10243, 33071);
        ShaderUtil.checkGlError("glTexParameteri mTextureID");
        this.mSurface = new SurfaceTexture(this.mTextureID);
        Surface surface = new Surface(this.mSurface);
        this.mPlayer.setSurface(surface);
        surface.release();
        this.mPlayer.prepareAsync();
    }

    public void release() {
        SurfaceTexture surfaceTexture = this.mSurface;
        if (surfaceTexture != null) {
            surfaceTexture.release();
        }
    }
}
