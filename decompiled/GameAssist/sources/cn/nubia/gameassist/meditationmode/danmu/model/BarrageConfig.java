package cn.nubia.gameassist.meditationmode.danmu.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.meditationmode.danmu.painter.BaseBarragePainter;
import cn.nubia.gameassist.meditationmode.danmu.util.BarrageLog;

/* loaded from: classes.dex */
public class BarrageConfig {

    /* renamed from: n, reason: collision with root package name */
    private static final String f6660n = "BarrageConfig";

    /* renamed from: a, reason: collision with root package name */
    private int f6661a;

    /* renamed from: b, reason: collision with root package name */
    private int f6662b;

    /* renamed from: c, reason: collision with root package name */
    private int f6663c;

    /* renamed from: d, reason: collision with root package name */
    private int f6664d;

    /* renamed from: e, reason: collision with root package name */
    private int f6665e;

    /* renamed from: f, reason: collision with root package name */
    private int f6666f;

    /* renamed from: g, reason: collision with root package name */
    private int f6667g;

    /* renamed from: h, reason: collision with root package name */
    private int f6668h;

    /* renamed from: i, reason: collision with root package name */
    private int f6669i;

    /* renamed from: j, reason: collision with root package name */
    private int f6670j;

    /* renamed from: k, reason: collision with root package name */
    private int f6671k;

    /* renamed from: l, reason: collision with root package name */
    private int f6672l;

    /* renamed from: m, reason: collision with root package name */
    private BaseBarragePainter f6673m;

    public BarrageConfig(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            BarrageLog.b(f6660n, "barrage not style config");
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BarrageConfig, 0, R.style.BarrageDefaultStyle);
        this.f6661a = obtainStyledAttributes.getInteger(R.styleable.BarrageConfig_max_count_in_screen, 0);
        this.f6662b = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BarrageConfig_channel_height, 0);
        this.f6664d = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BarrageConfig_app_icon_size, 0);
        this.f6665e = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BarrageConfig_reply_icon_size, 0);
        this.f6666f = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BarrageConfig_reply_icon_margin_start, 0);
        this.f6667g = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BarrageConfig_divider_size, 0);
        this.f6668h = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BarrageConfig_divider_height, 0);
        this.f6669i = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BarrageConfig_text_margin_start, 0);
        this.f6670j = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BarrageConfig_text_margin_end, 0);
        this.f6671k = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BarrageConfig_barrage_space, 0);
        this.f6663c = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BarrageConfig_padding_top, 0);
        this.f6672l = obtainStyledAttributes.getInteger(R.styleable.BarrageConfig_max_ems, 0);
        obtainStyledAttributes.recycle();
    }

    public int a() {
        return this.f6664d;
    }

    public BaseBarragePainter b() {
        BaseBarragePainter baseBarragePainter = this.f6673m;
        if (baseBarragePainter != null) {
            return baseBarragePainter;
        }
        throw new SecurityException("barrage painter not null");
    }

    public int c() {
        return this.f6671k;
    }

    public int d() {
        return this.f6662b;
    }

    public int e() {
        return this.f6668h;
    }

    public int f() {
        return this.f6667g;
    }

    public int g() {
        return this.f6661a;
    }

    public int h() {
        return this.f6663c;
    }

    public int i() {
        return this.f6666f;
    }

    public int j() {
        return this.f6665e;
    }

    public int k() {
        return this.f6670j;
    }

    public int l() {
        return this.f6669i;
    }

    public void m(BaseBarragePainter baseBarragePainter) {
        this.f6673m = baseBarragePainter;
    }
}
