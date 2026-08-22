package cn.nubia.gamecenter.settings.widget.particle;

import android.content.Context;
import android.opengl.GLES20;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/* loaded from: classes.dex */
public class ParticleSystem implements IParticleSystem {
    private static final int PARTICLE_MIN_RADIUS = 2;
    private static final int PARTICLE_MXA_NUM = 100;
    private static final boolean PARTICLE_SAME_RADIUS = true;
    private static final float PARTICLE_SCALE_Y = 0.4f;
    private static final float PARTICLE_STEP_X = 0.3f;
    private ByteBuffer mAbb;
    private ByteBuffer mCbb;
    private float mCenterX;
    private float mCenterY;
    private Context mContext;
    private float mDensity;
    private ByteBuffer mIbb;
    private int mLastTime;
    private ParticleShaderProgram mShaderProgram;
    private ByteBuffer mUcb;
    private ByteBuffer mVbb;
    private float m_distance;
    private ArrayList<Particle> mParticles = new ArrayList<>();
    private ArrayList<Particle> mDeadParticles = new ArrayList<>();
    private Random mRandom = new Random(System.currentTimeMillis());
    private int mColor = 0;

    public ParticleSystem(Context context) {
        this.mContext = context;
        this.mDensity = context.getResources().getDisplayMetrics().density;
        this.m_distance = this.mContext.getResources().getDimensionPixelSize(R.dimen.gcs_fun_particle_left_right_distance);
        initBuffer();
        initShader();
    }

    private Particle createParticle() {
        float f = this.mDensity * 2.0f;
        boolean nextBoolean = this.mRandom.nextBoolean();
        Particle particle = new Particle();
        particle.mAlpha = this.mRandom.nextBoolean() ? 1.0f : 0.6f;
        particle.mRadius = 2 * this.mDensity;
        particle.mY = this.mCenterY - (((1.0f - (this.mRandom.nextFloat() * 2.0f)) * this.mCenterY) * PARTICLE_SCALE_Y);
        if (Math.abs(particle.mY - this.mCenterY) > this.mCenterY * 0.120000005f) {
            float abs = Math.abs(particle.mY - this.mCenterY);
            float f2 = this.mCenterY;
            particle.mMaxAlpha = 1.0f - ((abs - (0.120000005f * f2)) / (0.28f * f2));
            particle.mMaxLifeTime = this.mRandom.nextInt(HighLightsUtils.RESET_DELAY_TIME) + 200;
        } else {
            particle.mMaxAlpha = 1.0f;
            particle.mMaxLifeTime = this.mRandom.nextInt(600) + 300;
        }
        if (nextBoolean) {
            particle.mX = (this.mCenterX - f) - (this.m_distance / 2.0f);
            particle.mVx = (-this.mRandom.nextFloat()) * PARTICLE_STEP_X;
            particle.mVy = (1.0f - (this.mRandom.nextFloat() * 2.0f)) * 0.6f * particle.mVx;
            particle.mVz = 0.0f;
        } else {
            particle.mX = this.mCenterX + f + (this.m_distance / 2.0f);
            particle.mVx = this.mRandom.nextFloat() * PARTICLE_STEP_X;
            particle.mVy = (1.0f - (this.mRandom.nextFloat() * 2.0f)) * 0.6f * particle.mVx;
            particle.mVz = 0.0f;
        }
        particle.mAx = (-particle.mVx) / particle.mMaxLifeTime;
        particle.mAy = (-particle.mVy) / particle.mMaxLifeTime;
        particle.mAz = 0.0f;
        particle.mIsAlive = true;
        return particle;
    }

    private void initBuffer() {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(9600);
        this.mVbb = allocateDirect;
        allocateDirect.order(ByteOrder.nativeOrder());
        ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(6400);
        this.mCbb = allocateDirect2;
        allocateDirect2.order(ByteOrder.nativeOrder());
        ByteBuffer allocateDirect3 = ByteBuffer.allocateDirect(HighLightsUtils.NORMAL_WIDTH);
        this.mIbb = allocateDirect3;
        allocateDirect3.order(ByteOrder.nativeOrder());
        ByteBuffer allocateDirect4 = ByteBuffer.allocateDirect(6400);
        this.mAbb = allocateDirect4;
        allocateDirect4.order(ByteOrder.nativeOrder());
        ByteBuffer allocateDirect5 = ByteBuffer.allocateDirect(16);
        this.mUcb = allocateDirect5;
        allocateDirect5.order(ByteOrder.nativeOrder());
    }

    private void initShader() {
        this.mShaderProgram = new ParticleShaderProgram(ShaderUtil.createProgram(ShaderUtil.loadFromAssetsFile("particle_vertex.sh", this.mContext.getResources()), ShaderUtil.loadFromAssetsFile("particle_frag.sh", this.mContext.getResources())));
    }

    public void clearAllParticles() {
        ArrayList<Particle> arrayList = this.mParticles;
        if (arrayList != null) {
            arrayList.clear();
        }
        ArrayList<Particle> arrayList2 = this.mDeadParticles;
        if (arrayList2 != null) {
            arrayList2.clear();
        }
    }

    @Override // cn.nubia.gamecenter.settings.widget.particle.IParticleSystem
    public void draw(int i) {
        FloatBuffer asFloatBuffer = this.mVbb.asFloatBuffer();
        FloatBuffer asFloatBuffer2 = this.mCbb.asFloatBuffer();
        FloatBuffer asFloatBuffer3 = this.mAbb.asFloatBuffer();
        ShortBuffer asShortBuffer = this.mIbb.asShortBuffer();
        FloatBuffer asFloatBuffer4 = this.mUcb.asFloatBuffer();
        if (i == 0) {
            return;
        }
        int size = this.mParticles.size();
        for (int i2 = 0; i2 < size && i2 < 100; i2++) {
            Particle particle = this.mParticles.get(i2);
            float f = particle.mRadius;
            asFloatBuffer.put(particle.mX - f);
            asFloatBuffer.put(particle.mY - f);
            asFloatBuffer.put(particle.mZ);
            asFloatBuffer.put(particle.mX - f);
            asFloatBuffer.put(particle.mY + f);
            asFloatBuffer.put(particle.mZ);
            asFloatBuffer.put(particle.mX + f);
            asFloatBuffer.put(particle.mY - f);
            asFloatBuffer.put(particle.mZ);
            asFloatBuffer.put(particle.mX + f);
            asFloatBuffer.put(particle.mY + f);
            asFloatBuffer.put(particle.mZ);
            asFloatBuffer2.put(0.0f);
            asFloatBuffer2.put(0.0f);
            asFloatBuffer2.put(0.0f);
            asFloatBuffer2.put(1.0f);
            asFloatBuffer2.put(1.0f);
            asFloatBuffer2.put(0.0f);
            asFloatBuffer2.put(1.0f);
            asFloatBuffer2.put(1.0f);
            int i3 = i2 * 4;
            asShortBuffer.put((short) i3);
            short s = (short) (i3 + 1);
            asShortBuffer.put(s);
            short s2 = (short) (i3 + 2);
            asShortBuffer.put(s2);
            asShortBuffer.put(s2);
            asShortBuffer.put(s);
            asShortBuffer.put((short) (i3 + 3));
            float f2 = particle.mAlpha;
            asFloatBuffer3.put(f2);
            asFloatBuffer3.put(f2);
            asFloatBuffer3.put(f2);
            asFloatBuffer3.put(f2);
        }
        asFloatBuffer.position(0);
        asFloatBuffer2.position(0);
        asShortBuffer.position(0);
        asFloatBuffer3.position(0);
        GLES20.glUseProgram(this.mShaderProgram.mId);
        GLES20.glUniformMatrix4fv(this.mShaderProgram.mMVPMatrixHandle, 1, false, MatrixState.getMVPMatrix(), 0);
        GLES20.glVertexAttribPointer(this.mShaderProgram.mVertexHandle, 3, 5126, false, 12, (Buffer) asFloatBuffer);
        GLES20.glVertexAttribPointer(this.mShaderProgram.mTexCoorHandle, 2, 5126, false, 8, (Buffer) asFloatBuffer2);
        GLES20.glVertexAttribPointer(this.mShaderProgram.mAlphaHandle, 1, 5126, false, 4, (Buffer) asFloatBuffer3);
        int i4 = this.mColor;
        asFloatBuffer4.put(((16711680 & i4) >> 16) / 255.0f);
        asFloatBuffer4.put(((65280 & i4) >> 8) / 255.0f);
        asFloatBuffer4.put((i4 & 255) / 255.0f);
        asFloatBuffer4.put((((-16777216) & i4) >> 24) / 255.0f);
        asFloatBuffer4.position(0);
        GLES20.glUniform4fv(this.mShaderProgram.mColorHandle, 1, asFloatBuffer4);
        GLES20.glEnableVertexAttribArray(this.mShaderProgram.mVertexHandle);
        GLES20.glEnableVertexAttribArray(this.mShaderProgram.mTexCoorHandle);
        GLES20.glEnableVertexAttribArray(this.mShaderProgram.mAlphaHandle);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i);
        GLES20.glEnable(3042);
        GLES20.glBlendFunc(1, 771);
        GLES20.glDrawElements(4, size * 6, 5123, asShortBuffer);
        GLES20.glDisable(3042);
    }

    @Override // cn.nubia.gamecenter.settings.widget.particle.IParticleSystem
    public void emit(int i) {
        this.mParticles.clear();
        for (int i2 = 0; i2 < i; i2++) {
            this.mParticles.add(createParticle());
        }
    }

    public void initParticles(float f, float f2) {
        this.mLastTime = 0;
        this.mCenterX = f;
        this.mCenterY = f2;
        initBuffer();
        emit(100);
    }

    public void setColor(int i) {
        this.mColor = i;
    }

    @Override // cn.nubia.gamecenter.settings.widget.particle.IParticleSystem
    public void update(int i) {
        Iterator<Particle> it = this.mParticles.iterator();
        while (it.hasNext()) {
            Particle next = it.next();
            next.update(i - this.mLastTime);
            if (!next.mIsAlive) {
                this.mDeadParticles.add(next);
            }
        }
        Iterator<Particle> it2 = this.mDeadParticles.iterator();
        while (it2.hasNext()) {
            this.mParticles.remove(it2.next());
        }
        this.mDeadParticles.clear();
        this.mLastTime = i;
    }
}
