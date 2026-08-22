package cn.nubia.gamelauncher.wallpaper;

import android.content.Context;
import android.opengl.GLES20;
import java.nio.Buffer;
import java.nio.FloatBuffer;

/* loaded from: classes.dex */
public class Rect {
    private int mPositionHandle;
    private int mProgram;
    public FloatBuffer mTexCoorBuffer;
    private int mTexCoorHandle;
    public FloatBuffer mVertexBuffer;
    public final float[] vertices = {-1.0f, 1.0f, 0.0f, -1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f};
    public final float[] texCoors = {0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f};

    public Rect(Context context) {
        initData();
        initShader(context);
    }

    private void initData() {
        this.mVertexBuffer = MemUtil.makeFloatBuffer(this.vertices);
        this.mTexCoorBuffer = MemUtil.makeFloatBuffer(this.texCoors);
    }

    private void initShader(Context context) {
        int createProgram = ShaderUtil.createProgram(ShaderUtil.loadFromAssetsFile("particle_vertex.sh", context.getResources()), ShaderUtil.loadFromAssetsFile("particle_frag.sh", context.getResources()));
        this.mProgram = createProgram;
        this.mPositionHandle = GLES20.glGetAttribLocation(createProgram, "aPosition");
        this.mTexCoorHandle = GLES20.glGetAttribLocation(this.mProgram, "aTexCoor");
    }

    public void drawSelf(int i) {
        GLES20.glUseProgram(this.mProgram);
        GLES20.glVertexAttribPointer(this.mPositionHandle, 3, 5126, false, 12, (Buffer) this.mVertexBuffer);
        GLES20.glEnableVertexAttribArray(this.mPositionHandle);
        GLES20.glVertexAttribPointer(this.mTexCoorHandle, 2, 5126, false, 8, (Buffer) this.mTexCoorBuffer);
        GLES20.glEnableVertexAttribArray(this.mTexCoorHandle);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i);
        GLES20.glDrawArrays(4, 0, 6);
    }
}
