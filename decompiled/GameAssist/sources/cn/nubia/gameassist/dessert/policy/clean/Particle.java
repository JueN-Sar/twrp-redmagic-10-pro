package cn.nubia.gameassist.dessert.policy.clean;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.Random;

/* loaded from: classes.dex */
public class Particle {

    /* renamed from: a, reason: collision with root package name */
    private float f6342a;

    /* renamed from: b, reason: collision with root package name */
    private float f6343b;

    /* renamed from: c, reason: collision with root package name */
    private float f6344c;

    /* renamed from: d, reason: collision with root package name */
    private float f6345d;

    /* renamed from: e, reason: collision with root package name */
    private float f6346e;

    /* renamed from: f, reason: collision with root package name */
    private float f6347f;

    /* renamed from: g, reason: collision with root package name */
    private Random f6348g = new Random();

    public Particle(float f2, float f3, float f4) {
        this.f6342a = f2;
        this.f6343b = f3;
        this.f6344c = f4;
        this.f6346e = (f2 - f3) + r0.nextInt((int) f3);
        this.f6345d = this.f6342a + ((this.f6348g.nextBoolean() ? 1 : -1) * ((float) Math.sqrt(Math.pow(this.f6343b, 2.0d) - Math.pow(this.f6342a - this.f6346e, 2.0d))));
        this.f6347f = (this.f6348g.nextFloat() / 5.0f) + 0.8f;
        this.f6346e -= this.f6348g.nextInt(10);
    }

    public void a(Canvas canvas, Paint paint) {
        this.f6346e -= this.f6348g.nextFloat();
        this.f6347f = Math.max(this.f6347f - 0.02f, 0.0f);
        if (this.f6346e < this.f6342a - this.f6344c) {
            this.f6347f = 0.0f;
        }
        int alpha = paint.getAlpha();
        paint.setAlpha((int) (this.f6347f * alpha));
        canvas.drawCircle(this.f6345d, this.f6346e, this.f6348g.nextBoolean() ? 2.0f : 1.0f, paint);
        paint.setAlpha(alpha);
    }
}
