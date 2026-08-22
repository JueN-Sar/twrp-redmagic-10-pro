package cn.nubia.gamecenter.settings.widget.particle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import cn.nubia.gamecenter.settings.R;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes.dex */
public class ParticleRenderer implements GLSurfaceView.Renderer {
    private static final int ANIM_DURATION = 1300;
    private Context mContext;
    private int mHeight;
    private ParticleSystem mParticleSystem;
    private int mParticleTextureId;
    private int mWidth;
    private final Object mSynchronized = new Object();
    private int mColor = 0;

    public ParticleRenderer(Context context) {
        this.mContext = context;
    }

    private int createTexture(int i) {
        Bitmap decodeResource = BitmapFactory.decodeResource(this.mContext.getResources(), i);
        int addTexture = OpenGLUtil.addTexture(decodeResource);
        decodeResource.recycle();
        return addTexture;
    }

    public void clearParticles() {
        synchronized (this.mSynchronized) {
            ParticleSystem particleSystem = this.mParticleSystem;
            if (particleSystem != null) {
                particleSystem.clearAllParticles();
            }
        }
    }

    public int getAnimDuration() {
        return ANIM_DURATION;
    }

    public void initParticles() {
        synchronized (this.mSynchronized) {
            ParticleSystem particleSystem = this.mParticleSystem;
            if (particleSystem != null) {
                particleSystem.initParticles(this.mWidth / 2, this.mHeight / 2);
            }
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(16640);
        MatrixState.pushMatrix();
        MatrixState.setInitStack();
        synchronized (this.mSynchronized) {
            this.mParticleSystem.draw(this.mParticleTextureId);
        }
        MatrixState.popMatrix();
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        this.mParticleSystem.setColor(this.mColor);
        if (i == this.mWidth && i2 == this.mHeight) {
            return;
        }
        this.mWidth = i;
        this.mHeight = i2;
        float f = i / 2.0f;
        float f2 = i2 / 2.0f;
        GLES20.glViewport(0, 0, i, i2);
        MatrixState.setCamera(f, f2, -1.0f, f, f2, 0.0f, 0.0f, -1.0f, 0.0f);
        MatrixState.setProjectOrtho(-f, f, -f2, f2, -1.0f, 1.0f);
        synchronized (this.mSynchronized) {
            this.mParticleSystem.initParticles(this.mWidth / 2, this.mHeight / 2);
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        GLES20.glDisable(3024);
        GLES20.glDisable(2929);
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        this.mParticleTextureId = createTexture(R.drawable.gcs_particle);
        synchronized (this.mSynchronized) {
            this.mParticleSystem = new ParticleSystem(this.mContext);
        }
    }

    public void setColor(int i) {
        this.mColor = i;
    }

    public void update(int i) {
        synchronized (this.mSynchronized) {
            ParticleSystem particleSystem = this.mParticleSystem;
            if (particleSystem != null) {
                particleSystem.update(i);
            }
        }
    }
}
