package com.zte.mifavor.alphavideo;

import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;
import android.view.Surface;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes2.dex */
public class AlphaVideoPlayer implements GLSurfaceView.Renderer, MediaPlayer.OnCompletionListener, SurfaceTexture.OnFrameAvailableListener {

    /* renamed from: c, reason: collision with root package name */
    private SurfaceTexture f17065c;

    /* renamed from: h, reason: collision with root package name */
    private int f17066h;

    /* renamed from: j, reason: collision with root package name */
    private int f17068j;

    /* renamed from: k, reason: collision with root package name */
    private int f17069k;

    /* renamed from: l, reason: collision with root package name */
    private int f17070l;

    /* renamed from: m, reason: collision with root package name */
    private int f17071m;

    /* renamed from: n, reason: collision with root package name */
    private int f17072n;

    /* renamed from: o, reason: collision with root package name */
    private int f17073o;

    /* renamed from: q, reason: collision with root package name */
    private final float[] f17075q;

    /* renamed from: r, reason: collision with root package name */
    private final FloatBuffer f17076r;

    /* renamed from: s, reason: collision with root package name */
    private final FloatBuffer f17077s;
    private GLSurfaceView t;
    private MediaPlayer u;
    private IPlayerListener v;

    /* renamed from: i, reason: collision with root package name */
    private int f17067i = -1;

    /* renamed from: p, reason: collision with root package name */
    private boolean f17074p = false;

    public AlphaVideoPlayer(GLSurfaceView gLSurfaceView, IPlayerListener iPlayerListener) {
        float[] fArr = new float[16];
        this.f17075q = fArr;
        this.t = gLSurfaceView;
        this.v = iPlayerListener;
        gLSurfaceView.setEGLContextClientVersion(2);
        this.t.setRenderer(this);
        this.t.setRenderMode(0);
        float[] fArr2 = OpenGlUtils.f17082b;
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(fArr2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f17076r = asFloatBuffer;
        asFloatBuffer.put(fArr2).position(0);
        float[] fArr3 = OpenGlUtils.f17081a;
        FloatBuffer asFloatBuffer2 = ByteBuffer.allocateDirect(fArr3.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f17077s = asFloatBuffer2;
        asFloatBuffer2.put(fArr3).position(0);
        Matrix.setIdentityM(fArr, 0);
    }

    private void a(Surface surface) {
        try {
            this.u = new MediaPlayer();
            AssetFileDescriptor openFd = this.t.getResources().getAssets().openFd("open.mp4");
            this.u.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
            this.u.setSurface(surface);
            this.u.setOnCompletionListener(this);
            this.u.setLooping(false);
            this.u.prepare();
            this.u.start();
            this.v.a();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        this.v.b();
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        SurfaceTexture surfaceTexture;
        Log.i("AlphaVideoPlayer", "--onDrawFrame--");
        synchronized (this) {
            try {
                if (this.f17074p && (surfaceTexture = this.f17065c) != null) {
                    surfaceTexture.updateTexImage();
                    this.f17065c.getTransformMatrix(this.f17075q);
                    this.f17074p = false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClear(16640);
        GLES20.glViewport(0, 0, this.f17072n, this.f17073o);
        GLES20.glUseProgram(this.f17067i);
        OpenGlUtils.c("glUseProgram");
        GLES20.glVertexAttribPointer(this.f17069k, 2, 5126, false, 0, (Buffer) this.f17076r);
        GLES20.glEnableVertexAttribArray(this.f17069k);
        GLES20.glVertexAttribPointer(this.f17070l, 2, 5126, false, 0, (Buffer) this.f17077s);
        GLES20.glEnableVertexAttribArray(this.f17070l);
        OpenGlUtils.c("1111");
        GLES20.glUniformMatrix4fv(this.f17068j, 1, false, this.f17075q, 0);
        OpenGlUtils.c("2222");
        if (this.f17066h != -1) {
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(36197, this.f17066h);
            GLES20.glUniform1i(this.f17071m, 0);
        }
        OpenGlUtils.c("3333");
        GLES20.glDrawArrays(5, 0, 4);
        OpenGlUtils.c("4444");
        GLES20.glDisableVertexAttribArray(this.f17069k);
        GLES20.glDisableVertexAttribArray(this.f17070l);
        GLES20.glBindTexture(36197, 0);
        GLES20.glFinish();
        OpenGlUtils.c("5555");
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        synchronized (this) {
            this.f17074p = true;
            this.t.requestRender();
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i2, int i3) {
        this.f17072n = i2;
        this.f17073o = i3;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        this.f17067i = OpenGlUtils.e("uniform mat4 surfaceTransformMatrix;\nattribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 rgbTextureCoordinate;\nvarying vec2 alphaTextureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    float rgbXOrigin = 0.5;\n    float alphaXOrigin = 0.0;\n    float channelScale = 2.0;\n\n    float rgbX = inputTextureCoordinate.x / channelScale + rgbXOrigin;\n    float alphaX = inputTextureCoordinate.x / channelScale + alphaXOrigin;\n\n    vec4 positionInRgbTexture = vec4(rgbX, inputTextureCoordinate.y, inputTextureCoordinate.zw);\n    vec4 positionInAlphaTexture = vec4(alphaX, inputTextureCoordinate.y , inputTextureCoordinate.zw);\n\n    rgbTextureCoordinate = (surfaceTransformMatrix * positionInRgbTexture).xy;\n    alphaTextureCoordinate = (surfaceTransformMatrix * positionInAlphaTexture).xy;\n}", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 rgbTextureCoordinate;\nvarying vec2 alphaTextureCoordinate;\n\n uniform samplerExternalOES inputImageTexture;\n \n void main()\n {\n   vec4 rgbColor = texture2D(inputImageTexture, rgbTextureCoordinate);\n   float alphaColor = texture2D(inputImageTexture, alphaTextureCoordinate).g;\n\n   gl_FragColor = vec4(rgbColor.rgb * alphaColor, alphaColor);\n }");
        OpenGlUtils.c("loadProgram");
        this.f17068j = GLES20.glGetUniformLocation(this.f17067i, "surfaceTransformMatrix");
        OpenGlUtils.c("glGetUniformLocation surfaceTransformMatrix");
        this.f17069k = GLES20.glGetAttribLocation(this.f17067i, "position");
        OpenGlUtils.c("glGetUniformLocation position");
        this.f17070l = GLES20.glGetAttribLocation(this.f17067i, "inputTextureCoordinate");
        OpenGlUtils.c("glGetUniformLocation inputTextureCoordinate");
        this.f17071m = GLES20.glGetUniformLocation(this.f17067i, "inputImageTexture");
        OpenGlUtils.c("glGetUniformLocation inputImageTexture");
        Log.i("AlphaVideoPlayer", "--onSurfaceCreated--glProgId : " + this.f17067i + "--" + this.f17068j + "--" + this.f17069k + "--" + this.f17070l + "--" + this.f17071m);
        this.f17066h = OpenGlUtils.d();
        OpenGlUtils.c("createTexture");
        StringBuilder sb = new StringBuilder();
        sb.append("--onSurfaceCreated--texture : ");
        sb.append(this.f17066h);
        Log.i("AlphaVideoPlayer", sb.toString());
        SurfaceTexture surfaceTexture = new SurfaceTexture(this.f17066h);
        this.f17065c = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(this);
        Surface surface = new Surface(this.f17065c);
        a(surface);
        surface.release();
        Log.i("AlphaVideoPlayer", "--onSurfaceCreated--finish");
    }
}
