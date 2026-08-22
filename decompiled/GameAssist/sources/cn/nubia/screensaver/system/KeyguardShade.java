package cn.nubia.screensaver.system;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RuntimeShader;
import android.graphics.Shader;
import android.hardware.HardwareBuffer;
import androidx.annotation.RequiresApi;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import java.io.InputStream;

@RequiresApi
/* loaded from: classes.dex */
public class KeyguardShade extends RuntimeShader {

    /* renamed from: a, reason: collision with root package name */
    private Bitmap f9159a;

    /* renamed from: b, reason: collision with root package name */
    private BitmapShader f9160b;

    /* renamed from: c, reason: collision with root package name */
    private Paint f9161c;

    public KeyguardShade(Context context, Bitmap bitmap) {
        super(b(context, "shader/keyguard_blurs.agsl"));
        this.f9161c = new Paint();
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.f9159a = bitmap;
        Bitmap bitmap2 = this.f9159a;
        Shader.TileMode tileMode = Shader.TileMode.DECAL;
        BitmapShader bitmapShader = new BitmapShader(bitmap2, tileMode, tileMode);
        this.f9160b = bitmapShader;
        setInputShader("keyguard", bitmapShader);
        setIntUniform("tablet", ZteFeature.isTabletProduct() ? 1 : 0);
        this.f9161c.setShader(this);
    }

    public static String b(Context context, String str) {
        try {
            InputStream open = context.getAssets().open(str);
            byte[] bArr = new byte[102400];
            int read = open.read(bArr);
            open.close();
            return new String(bArr, 0, read, "utf-8");
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public void a(Canvas canvas, Rect rect, float f2, float f3) {
        Bitmap bitmap;
        if (!canvas.isHardwareAccelerated() || (bitmap = this.f9159a) == null || bitmap.isRecycled()) {
            return;
        }
        setFloatUniform("blurs", f2);
        setFloatUniform("progress", f3);
        setFloatUniform("alpha", 1.0f);
        setFloatUniform("src", 0.0f, 0.0f, this.f9159a.getWidth(), this.f9159a.getHeight());
        setFloatUniform("dst", rect.left, rect.top, rect.right, rect.bottom);
        canvas.drawRect(rect, this.f9161c);
        if (GaLog.f17035c) {
            canvas.drawBitmap(this.f9159a, 0.0f, 0.0f, (Paint) null);
        }
    }

    public void c() {
        Bitmap bitmap = this.f9159a;
        if (bitmap != null && !bitmap.isRecycled()) {
            HardwareBuffer hardwareBuffer = this.f9159a.getHardwareBuffer();
            if (hardwareBuffer != null && !hardwareBuffer.isClosed()) {
                hardwareBuffer.isClosed();
            }
            this.f9159a.recycle();
        }
        this.f9159a = null;
    }
}
