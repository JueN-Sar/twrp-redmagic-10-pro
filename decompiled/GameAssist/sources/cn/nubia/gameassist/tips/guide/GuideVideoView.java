package cn.nubia.gameassist.tips.guide;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Surface;
import cn.nubia.gameassist.R;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.ZteFeatureWrapper;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes.dex */
public class GuideVideoView extends GLSurfaceView implements GLSurfaceView.Renderer, SurfaceTexture.OnFrameAvailableListener, MediaPlayer.OnVideoSizeChangedListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
    private static final String DEF_VIDEO_NAME = "game_assist_guide";
    private static final String TAG = "LaunchTips";
    private final String FRAGMENT_SHADER;
    private final String VERTEX_SHADER;
    private Callback mCallback;
    private Runnable mCheckPlayRun;
    private Handler mHandler;
    private int mMatrixLocation;
    private MediaPlayer mMediaPlayer;
    private final long[] mPauseTimes;
    private int mPlayIndex;
    private boolean mPlayerPrepared;
    private int mPositionLocation;
    private int mProgramId;
    private final float[] mProjectionMatrix;
    private int mSTMMatrixHandle;
    private final float[] mSTMatrix;
    private int mSurfaceHeight;
    private SurfaceTexture mSurfaceTexture;
    private int mSurfaceWidth;
    private int mTextureCoordLocation;
    private int mTextureSamplerLocation;
    private FloatBuffer mTextureVertexBuffer;
    private boolean mUpdateSurface;
    private FloatBuffer mVertexBuffer;
    private TextRenderer mVideoText;
    private int mVideotextureId;
    private final float[] textureVertexData;
    private final float[] vertexData;

    public interface Callback {
        void a();

        void b();

        void c();

        void onError(int i2, int i3);
    }

    public class DrawText implements TextRenderer.Callback {

        /* renamed from: a, reason: collision with root package name */
        private final Path f7546a;

        /* renamed from: b, reason: collision with root package name */
        private final Point f7547b;

        /* renamed from: c, reason: collision with root package name */
        private int f7548c;

        /* renamed from: d, reason: collision with root package name */
        private int f7549d;

        /* renamed from: e, reason: collision with root package name */
        private int f7550e;

        /* renamed from: f, reason: collision with root package name */
        private String f7551f;

        public DrawText() {
            Path path = new Path();
            this.f7546a = path;
            this.f7547b = new Point();
            path.moveTo(0.0f, 0.0f);
            path.lineTo(550.0f, 0.0f);
            path.lineTo(950.0f, 250.0f);
            path.lineTo(1700.0f, 250.0f);
            path.lineTo(2350.0f, 0.0f);
            path.lineTo(4350.0f, 0.0f);
            path.lineTo(4800.0f, 250.0f);
            path.lineTo(4999.0f, 250.0f);
            path.lineTo(5000.0f, 0.0f);
        }

        private int b(String str, int i2) {
            if (str != null && i2 < str.length()) {
                for (int i3 = i2 + 1; i3 < str.length(); i3++) {
                    if (str.charAt(i3) == ' ') {
                        return i3;
                    }
                }
            }
            return i2;
        }

        private float c(Path path, float f2) {
            float[] approximate = path.approximate(1.0f);
            int length = approximate.length / 3;
            float f3 = 0.0f;
            int i2 = 0;
            int i3 = 0;
            float f4 = 0.0f;
            while (i2 < length) {
                int i4 = i3 + 2;
                float f5 = approximate[i3 + 1];
                i3 += 3;
                float f6 = approximate[i4];
                if (f4 < f2 && f2 <= f5) {
                    return f3 + ((f6 - f3) * ((f2 - f4) / (f5 - f4)));
                }
                i2++;
                f4 = f5;
                f3 = f6;
            }
            return f3;
        }

        private String d(int i2) {
            return i2 < 3350 ? GuideVideoView.this.getResources().getString(R.string.nubia_game_assist_guide_first) : GuideVideoView.this.getResources().getString(R.string.nubia_game_assist_guide_second);
        }

        private int e(int i2) {
            return (int) c(this.f7546a, i2);
        }

        private Point f(int i2) {
            return new Point(675, i2 < 3350 ? 212 : 237);
        }

        private int g(int i2) {
            return i2 < 3350 ? 36 : 24;
        }

        @Override // cn.nubia.gameassist.tips.guide.GuideVideoView.TextRenderer.Callback
        public void a(Canvas canvas, int i2, int i3, Paint paint) {
            int b2;
            int max = Math.max(GuideVideoView.this.mMediaPlayer != null ? GuideVideoView.this.mMediaPlayer.getCurrentPosition() : 0, this.f7548c);
            this.f7548c = max;
            int e2 = e(max);
            Point f2 = f(this.f7548c);
            int g2 = g(this.f7548c);
            String d2 = d(this.f7548c);
            if (this.f7549d == e2 && this.f7547b.equals(f2) && this.f7550e == g2 && TextUtils.equals(d2, this.f7551f)) {
                return;
            }
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            this.f7549d = e2;
            this.f7547b.set(f2.x, f2.y);
            this.f7550e = g2;
            this.f7551f = d2;
            paint.setAlpha(this.f7549d);
            paint.setTextSize(this.f7550e);
            Rect rect = new Rect();
            String str = this.f7551f;
            paint.getTextBounds(str, 0, str.length(), rect);
            if (rect.width() < (this.f7548c < 3350 ? 0.5d : 0.3d) * 1350.0d) {
                String str2 = this.f7551f;
                Point point = this.f7547b;
                canvas.drawText(str2, point.x, point.y, paint);
                return;
            }
            if (this.f7551f.contains(",")) {
                b2 = this.f7551f.indexOf(",") + 1;
            } else {
                String str3 = this.f7551f;
                b2 = b(str3, str3.length() / 2);
            }
            String str4 = this.f7551f;
            Point point2 = this.f7547b;
            canvas.drawText(str4, 0, b2, point2.x, point2.y - (this.f7550e * 1.3f), paint);
            String str5 = this.f7551f;
            int length = str5.length();
            Point point3 = this.f7547b;
            canvas.drawText(str5, b2, length, point3.x, point3.y, paint);
        }
    }

    public final class TextRenderer implements GLSurfaceView.Renderer {

        /* renamed from: c, reason: collision with root package name */
        private int f7553c;

        /* renamed from: h, reason: collision with root package name */
        private int f7554h;

        /* renamed from: i, reason: collision with root package name */
        private FloatBuffer f7555i;

        /* renamed from: j, reason: collision with root package name */
        private FloatBuffer f7556j;

        /* renamed from: k, reason: collision with root package name */
        private Bitmap f7557k;

        /* renamed from: l, reason: collision with root package name */
        private Canvas f7558l;

        /* renamed from: m, reason: collision with root package name */
        private Paint f7559m;

        /* renamed from: n, reason: collision with root package name */
        private final float[] f7560n = {-1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f};

        /* renamed from: o, reason: collision with root package name */
        private final float[] f7561o = {0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f};

        /* renamed from: p, reason: collision with root package name */
        private final Callback f7562p;

        private interface Callback {
            void a(Canvas canvas, int i2, int i3, Paint paint);
        }

        public TextRenderer(GuideVideoView guideVideoView, Callback callback) {
            this.f7562p = callback;
        }

        private int a(int i2, int i3) {
            int[] iArr = new int[1];
            GLES20.glGenTextures(1, iArr, 0);
            int i4 = iArr[0];
            GLES20.glBindTexture(3553, i4);
            GLES20.glTexImage2D(3553, 0, 6408, i2, i3, 0, 6408, 5121, null);
            GLES20.glTexParameteri(3553, 10241, 9729);
            GLES20.glTexParameteri(3553, 10240, 9729);
            return i4;
        }

        private int b(int i2, String str) {
            int glCreateShader = GLES20.glCreateShader(i2);
            GLES20.glShaderSource(glCreateShader, str);
            GLES20.glCompileShader(glCreateShader);
            return glCreateShader;
        }

        private void e() {
            Bitmap bitmap = this.f7557k;
            if (bitmap != null && !bitmap.isRecycled()) {
                this.f7557k.recycle();
            }
            this.f7557k = null;
            this.f7558l = null;
            this.f7559m = null;
        }

        private void f(int i2, int i3) {
            this.f7557k = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
            this.f7558l = new Canvas(this.f7557k);
            Paint paint = new Paint(1);
            this.f7559m = paint;
            paint.setColor(-1);
            this.f7559m.setTextSize(48.0f);
            this.f7559m.setTextAlign(Paint.Align.CENTER);
            this.f7559m.setStrokeWidth(1.0f);
            this.f7559m.setStyle(Paint.Style.FILL_AND_STROKE);
        }

        private void g() {
            Callback callback = this.f7562p;
            if (callback != null) {
                callback.a(this.f7558l, 1350, 608, this.f7559m);
            }
            GLES20.glBindTexture(3553, this.f7553c);
            GLUtils.texSubImage2D(3553, 0, 0, 0, this.f7557k);
        }

        public void c() {
            f(1350, 608);
        }

        public void d() {
            e();
        }

        @Override // android.opengl.GLSurfaceView.Renderer
        public void onDrawFrame(GL10 gl10) {
            GLES20.glEnable(3042);
            GLES20.glBlendFunc(770, 771);
            g();
            GLES20.glUseProgram(this.f7554h);
            int glGetAttribLocation = GLES20.glGetAttribLocation(this.f7554h, "a_Position");
            int glGetAttribLocation2 = GLES20.glGetAttribLocation(this.f7554h, "a_TexCoord");
            int glGetUniformLocation = GLES20.glGetUniformLocation(this.f7554h, "u_Texture");
            GLES20.glEnableVertexAttribArray(glGetAttribLocation);
            GLES20.glVertexAttribPointer(glGetAttribLocation, 2, 5126, false, 0, (Buffer) this.f7555i);
            GLES20.glEnableVertexAttribArray(glGetAttribLocation2);
            GLES20.glVertexAttribPointer(glGetAttribLocation2, 2, 5126, false, 0, (Buffer) this.f7556j);
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, this.f7553c);
            GLES20.glUniform1i(glGetUniformLocation, 0);
            GLES20.glDrawArrays(6, 0, 4);
            GLES20.glDisableVertexAttribArray(glGetAttribLocation);
            GLES20.glDisableVertexAttribArray(glGetAttribLocation2);
            GLES20.glDisable(3042);
        }

        @Override // android.opengl.GLSurfaceView.Renderer
        public void onSurfaceChanged(GL10 gl10, int i2, int i3) {
            GLES20.glViewport(0, 0, i2, i3);
        }

        @Override // android.opengl.GLSurfaceView.Renderer
        public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
            this.f7553c = a(1350, 608);
            g();
            int b2 = b(35633, "attribute vec4 a_Position;\nattribute vec2 a_TexCoord;\nvarying vec2 v_TexCoord;\nvoid main() {\n  gl_Position = a_Position;\n  v_TexCoord = a_TexCoord;\n}");
            int b3 = b(35632, "precision mediump float;\nuniform sampler2D u_Texture;\nvarying vec2 v_TexCoord;\nvoid main() {\n  gl_FragColor = texture2D(u_Texture, v_TexCoord);\n}");
            int glCreateProgram = GLES20.glCreateProgram();
            this.f7554h = glCreateProgram;
            GLES20.glAttachShader(glCreateProgram, b2);
            GLES20.glAttachShader(this.f7554h, b3);
            GLES20.glLinkProgram(this.f7554h);
            FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(this.f7560n.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
            this.f7555i = asFloatBuffer;
            asFloatBuffer.put(this.f7560n).position(0);
            FloatBuffer asFloatBuffer2 = ByteBuffer.allocateDirect(this.f7561o.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
            this.f7556j = asFloatBuffer2;
            asFloatBuffer2.put(this.f7561o).position(0);
        }
    }

    public GuideVideoView(Context context, Callback callback) {
        super(context);
        this.VERTEX_SHADER = "attribute vec4 aPosition;\nattribute vec4 aTexCoord;\nvarying vec2 vTexCoord;\nvarying vec2 vmTexCoord;\nuniform mat4 uMatrix;\nuniform mat4 uSTMatrix;\nvoid main() {\n    vTexCoord = (uMatrix * aTexCoord).xy;\n    vmTexCoord = (uMatrix * aTexCoord).xy;\n    gl_Position = uMatrix*aPosition;\n}";
        this.FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTexCoord;\nvarying vec2 vmTexCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n    vec4 videoColor1 = texture2D(sTexture, vTexCoord);\n    vec4 videoColor2 = texture2D(sTexture, vTexCoord+vec2(0,0.5));\n    float alpha = (videoColor2.r + videoColor2.g + videoColor2.b) / 3.0;\n    gl_FragColor=vec4(videoColor1.rgb*alpha, alpha);\n}\n";
        this.vertexData = new float[]{1.0f, -1.0f, 0.0f, -1.0f, -1.0f, 0.0f, 1.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f};
        this.textureVertexData = new float[]{1.0f, 0.5f, 0.0f, 0.5f, 1.0f, 0.0f, 0.0f, 0.0f};
        this.mPauseTimes = new long[]{1689};
        this.mSTMatrix = new float[16];
        this.mProjectionMatrix = new float[16];
        this.mHandler = new Handler();
        this.mCheckPlayRun = new Runnable() { // from class: cn.nubia.gameassist.tips.guide.GuideVideoView.1
            @Override // java.lang.Runnable
            public void run() {
                GuideVideoView.this.mHandler.removeCallbacks(this);
                GuideVideoView.this.d();
            }
        };
        this.mVideoText = new TextRenderer(this, new DrawText());
        this.mCallback = callback;
        setEGLContextClientVersion(2);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        getHolder().setFormat(-2);
        setZOrderOnTop(true);
        setRenderer(this);
        setRenderMode(1);
        this.mPlayerPrepared = false;
        e();
        synchronized (this) {
            this.mUpdateSurface = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer == null || !mediaPlayer.isPlaying() || this.mPlayIndex >= this.mPauseTimes.length) {
            return;
        }
        long currentPosition = this.mMediaPlayer.getCurrentPosition();
        long j2 = this.mPauseTimes[this.mPlayIndex];
        if (j2 > currentPosition) {
            this.mHandler.postDelayed(this.mCheckPlayRun, j2 - currentPosition);
            return;
        }
        this.mMediaPlayer.pause();
        this.mPlayIndex++;
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.c();
        }
    }

    private void e() {
        FloatBuffer put = ByteBuffer.allocateDirect(this.vertexData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(this.vertexData);
        this.mVertexBuffer = put;
        put.position(0);
        FloatBuffer put2 = ByteBuffer.allocateDirect(this.textureVertexData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(this.textureVertexData);
        this.mTextureVertexBuffer = put2;
        put2.position(0);
    }

    private void f() {
        AssetFileDescriptor openFd;
        try {
            if (this.mMediaPlayer == null) {
                this.mMediaPlayer = new MediaPlayer();
                String str = "guide/" + ZteFeatureWrapper.get("GAME_ASSIST_GUIDE_VIDEO_NAME", DEF_VIDEO_NAME) + ".mp4";
                try {
                    openFd = getContext().getAssets().openFd(str);
                    GaLog.j("LaunchTips", "open primary video path: " + str);
                } catch (IOException unused) {
                    GaLog.j("LaunchTips", "Failed to open primary video path: " + str);
                    openFd = getContext().getAssets().openFd("guide/game_assist_guide.mp4");
                }
                this.mMediaPlayer.setDataSource(openFd);
                this.mMediaPlayer.setAudioStreamType(3);
                this.mMediaPlayer.setLooping(false);
                this.mMediaPlayer.setOnCompletionListener(this);
                this.mMediaPlayer.setOnVideoSizeChangedListener(this);
                this.mMediaPlayer.setOnErrorListener(this);
                this.mMediaPlayer.setOnPreparedListener(this);
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            Callback callback = this.mCallback;
            if (callback != null) {
                callback.onError(0, 0);
            }
        }
    }

    private void i(int i2, int i3) {
        float f2 = this.mSurfaceWidth / this.mSurfaceHeight;
        float f3 = i2 / i3;
        if (f3 > f2) {
            Matrix.orthoM(this.mProjectionMatrix, 0, -1.0f, 1.0f, (-f3) / f2, f3 / f2, -1.0f, 1.0f);
        } else {
            Matrix.orthoM(this.mProjectionMatrix, 0, (-f2) / f3, f2 / f3, -1.0f, 1.0f, -1.0f, 1.0f);
        }
    }

    public void g() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            if (!mediaPlayer.isPlaying()) {
                h();
                return;
            }
            int i2 = this.mPlayIndex;
            long[] jArr = this.mPauseTimes;
            if (i2 < jArr.length) {
                this.mMediaPlayer.seekTo((int) jArr[i2]);
                this.mPlayIndex++;
                d();
                return;
            }
        }
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.b();
        }
    }

    public MediaPlayer getmMediaPlayer() {
        return this.mMediaPlayer;
    }

    public void h() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
        d();
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.a();
        }
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mVideoText.c();
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.b();
        }
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mVideoText.d();
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.mMediaPlayer.release();
        }
        this.mMediaPlayer = null;
        SurfaceTexture surfaceTexture = this.mSurfaceTexture;
        if (surfaceTexture != null) {
            surfaceTexture.release();
        }
        this.mSurfaceTexture = null;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(16640);
        synchronized (this) {
            try {
                if (this.mUpdateSurface) {
                    this.mSurfaceTexture.updateTexImage();
                    this.mSurfaceTexture.getTransformMatrix(this.mSTMatrix);
                    this.mUpdateSurface = false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        GLES20.glUseProgram(this.mProgramId);
        GLES20.glUniformMatrix4fv(this.mMatrixLocation, 1, false, this.mProjectionMatrix, 0);
        GLES20.glUniformMatrix4fv(this.mSTMMatrixHandle, 1, false, this.mSTMatrix, 0);
        this.mVertexBuffer.position(0);
        GLES20.glEnableVertexAttribArray(this.mPositionLocation);
        GLES20.glVertexAttribPointer(this.mPositionLocation, 3, 5126, false, 12, (Buffer) this.mVertexBuffer);
        this.mTextureVertexBuffer.position(0);
        GLES20.glEnableVertexAttribArray(this.mTextureCoordLocation);
        GLES20.glVertexAttribPointer(this.mTextureCoordLocation, 2, 5126, false, 8, (Buffer) this.mTextureVertexBuffer);
        GLES20.glBindTexture(36197, this.mVideotextureId);
        GLES20.glViewport(0, 0, this.mSurfaceWidth, this.mSurfaceHeight);
        GLES20.glDrawArrays(5, 0, 4);
        try {
            this.mVideoText.onDrawFrame(gl10);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
        Callback callback = this.mCallback;
        if (callback == null) {
            return true;
        }
        callback.onError(i2, i3);
        return true;
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public synchronized void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.mUpdateSurface = true;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        h();
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i2, int i3) {
        GaLog.a("LaunchTips", "onSurfaceChanged: " + i2 + " " + i3);
        this.mSurfaceWidth = i2;
        this.mSurfaceHeight = i3;
        this.mVideoText.onSurfaceChanged(gl10, i2, i3);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        f();
        int b2 = ShaderUtil.b("attribute vec4 aPosition;\nattribute vec4 aTexCoord;\nvarying vec2 vTexCoord;\nvarying vec2 vmTexCoord;\nuniform mat4 uMatrix;\nuniform mat4 uSTMatrix;\nvoid main() {\n    vTexCoord = (uMatrix * aTexCoord).xy;\n    vmTexCoord = (uMatrix * aTexCoord).xy;\n    gl_Position = uMatrix*aPosition;\n}", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTexCoord;\nvarying vec2 vmTexCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n    vec4 videoColor1 = texture2D(sTexture, vTexCoord);\n    vec4 videoColor2 = texture2D(sTexture, vTexCoord+vec2(0,0.5));\n    float alpha = (videoColor2.r + videoColor2.g + videoColor2.b) / 3.0;\n    gl_FragColor=vec4(videoColor1.rgb*alpha, alpha);\n}\n");
        this.mProgramId = b2;
        this.mPositionLocation = GLES20.glGetAttribLocation(b2, "aPosition");
        this.mMatrixLocation = GLES20.glGetUniformLocation(this.mProgramId, "uMatrix");
        this.mSTMMatrixHandle = GLES20.glGetUniformLocation(this.mProgramId, "uSTMatrix");
        this.mTextureSamplerLocation = GLES20.glGetUniformLocation(this.mProgramId, "sTexture");
        this.mTextureCoordLocation = GLES20.glGetAttribLocation(this.mProgramId, "aTexCoord");
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        int i2 = iArr[0];
        this.mVideotextureId = i2;
        GLES20.glBindTexture(36197, i2);
        ShaderUtil.a("glBindTexture mTextureID");
        GLES20.glTexParameterf(36197, 10241, 9728.0f);
        GLES20.glTexParameterf(36197, 10240, 9729.0f);
        GLES20.glActiveTexture(33985);
        GLES20.glUniform1i(this.mTextureSamplerLocation, 0);
        SurfaceTexture surfaceTexture = new SurfaceTexture(this.mVideotextureId);
        this.mSurfaceTexture = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(this);
        Surface surface = new Surface(this.mSurfaceTexture);
        this.mMediaPlayer.setSurface(surface);
        surface.release();
        if (!this.mPlayerPrepared) {
            try {
                this.mMediaPlayer.prepare();
                this.mPlayerPrepared = true;
            } catch (IOException unused) {
                GaLog.b("LaunchTips", "media player prepare failed");
            }
        }
        this.mVideoText.onSurfaceCreated(gl10, eGLConfig);
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i3) {
        GaLog.a("LaunchTips", "onVideoSizeChanged: " + i2 + " " + i3);
        i(i2, i3 / 2);
    }
}
