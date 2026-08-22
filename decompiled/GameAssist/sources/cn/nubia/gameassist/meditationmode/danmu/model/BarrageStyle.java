package cn.nubia.gameassist.meditationmode.danmu.model;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.meditationmode.danmu.BarrageFactory;
import cn.nubia.gameassist.meditationmode.danmu.util.BarrageLog;
import cn.nubia.systemwrapper.ActivityManagerWrapper;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class BarrageStyle {

    /* renamed from: n, reason: collision with root package name */
    private static final int[] f6680n = {6, 5, 4, 8, 7, 6};

    /* renamed from: o, reason: collision with root package name */
    private static final int[] f6681o = {BarrageFactory.a().getResources().getDimensionPixelSize(R.dimen.nubia_danmu_message_text_size_small), BarrageFactory.a().getResources().getDimensionPixelSize(R.dimen.nubia_danmu_message_text_size_medium), BarrageFactory.a().getResources().getDimensionPixelSize(R.dimen.nubia_danmu_message_text_size_large)};

    /* renamed from: p, reason: collision with root package name */
    private static final List f6682p = Arrays.asList("com.tencent.mm", "com.tencent.mobileqq", "com.whatsapp", "com.discord", "com.facebook.orca", "org.telegram.messenger");

    /* renamed from: q, reason: collision with root package name */
    private static final String f6683q = BarrageStyle.class.getSimpleName();

    /* renamed from: b, reason: collision with root package name */
    private int f6685b;

    /* renamed from: c, reason: collision with root package name */
    private int f6686c;

    /* renamed from: d, reason: collision with root package name */
    private int f6687d;

    /* renamed from: e, reason: collision with root package name */
    private Drawable f6688e;

    /* renamed from: f, reason: collision with root package name */
    private int f6689f;

    /* renamed from: g, reason: collision with root package name */
    private int f6690g;

    /* renamed from: h, reason: collision with root package name */
    private int f6691h;

    /* renamed from: i, reason: collision with root package name */
    public int f6692i;

    /* renamed from: j, reason: collision with root package name */
    private boolean f6693j;

    /* renamed from: l, reason: collision with root package name */
    private Rect f6695l;

    /* renamed from: m, reason: collision with root package name */
    private BarrageSettingsObserver f6696m;

    /* renamed from: a, reason: collision with root package name */
    private final List f6684a = new ArrayList();

    /* renamed from: k, reason: collision with root package name */
    private boolean f6694k = false;

    private static class BarrageSettingsObserver extends ContentObserver {

        /* renamed from: a, reason: collision with root package name */
        private final Uri f6697a;

        /* renamed from: b, reason: collision with root package name */
        private final Uri f6698b;

        /* renamed from: c, reason: collision with root package name */
        private final Uri f6699c;

        /* renamed from: d, reason: collision with root package name */
        private final Uri f6700d;

        /* renamed from: e, reason: collision with root package name */
        private final Uri f6701e;

        /* renamed from: f, reason: collision with root package name */
        private final Uri f6702f;

        /* renamed from: g, reason: collision with root package name */
        private final Uri f6703g;

        /* renamed from: h, reason: collision with root package name */
        private final ContentResolver f6704h;

        /* renamed from: i, reason: collision with root package name */
        private final BarrageStyle f6705i;

        public Uri a(String str) {
            return Settings.Global.getUriFor(str);
        }

        void b() {
            this.f6704h.registerContentObserver(this.f6697a, false, this);
            this.f6704h.registerContentObserver(this.f6698b, false, this);
            this.f6704h.registerContentObserver(this.f6699c, false, this);
            this.f6704h.registerContentObserver(this.f6700d, false, this);
            this.f6704h.registerContentObserver(this.f6701e, false, this);
            this.f6704h.registerContentObserver(this.f6702f, false, this);
            this.f6704h.registerContentObserver(this.f6703g, false, this);
            c(null);
        }

        public void c(Uri uri) {
            this.f6705i.s();
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            c(uri);
            this.f6705i.p();
        }

        private BarrageSettingsObserver(Handler handler, BarrageStyle barrageStyle) {
            super(handler);
            this.f6697a = a("gsc_barrage_message_quickreply");
            this.f6698b = a("gsc_barrage_message_bubble_type");
            this.f6699c = a("gsc_barrage_message_bubble_velocity");
            this.f6700d = a("gsc_barrage_message_bubble_typeface");
            this.f6701e = a("gsc_barrage_message_transparency");
            this.f6702f = a("gsc_barrage_message_location");
            this.f6703g = a("gsc_barrage_message_max_length");
            this.f6705i = barrageStyle;
            this.f6704h = BarrageFactory.a().getContentResolver();
        }
    }

    public interface SettingChangeCallback {
        void a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        BarrageLog.b(f6683q, "onSettingChange");
        Iterator it = this.f6684a.iterator();
        while (it.hasNext()) {
            ((SettingChangeCallback) it.next()).a();
        }
    }

    public void b(SettingChangeCallback settingChangeCallback) {
        if (settingChangeCallback == null || this.f6684a.contains(settingChangeCallback)) {
            return;
        }
        this.f6684a.add(settingChangeCallback);
    }

    public int c(ContentResolver contentResolver) {
        return (int) (new BigDecimal(255).divide(new BigDecimal(10), 3, RoundingMode.CEILING).floatValue() * Settings.Global.getInt(contentResolver, "gsc_barrage_message_transparency", 5));
    }

    public Drawable d(ContentResolver contentResolver) {
        int i2;
        int i3 = Settings.Global.getInt(contentResolver, "gsc_barrage_message_bubble_type", 0);
        if (i3 == 1) {
            this.f6693j = true;
            i2 = R.drawable.bg_danmu_mjfb;
            this.f6695l = new Rect(124, 30, 384, 130);
        } else if (i3 == 2) {
            this.f6693j = false;
            i2 = R.drawable.bg_danmu_klxq;
            this.f6695l = new Rect(41, 1, 179, 101);
        } else if (i3 == 3) {
            this.f6693j = false;
            i2 = R.drawable.bg_danmu_ecy;
            this.f6695l = new Rect(72, 45, 328, 115);
        } else if (i3 != 4) {
            this.f6693j = false;
            i2 = R.drawable.bg_danmu_default;
            this.f6695l = new Rect(46, 1, 296, 101);
        } else {
            this.f6693j = true;
            i2 = R.drawable.bg_danmu_super;
            this.f6695l = new Rect(59, 1, 319, 108);
        }
        this.f6687d = i2;
        return ContextCompat.e(BarrageFactory.a(), i2);
    }

    public int e(String str) {
        int i2;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        str.hashCode();
        switch (str) {
            case "org.telegram.messenger":
                return R.drawable.telegram;
            case "com.whatsapp":
                return R.drawable.whatsapp;
            case "com.android.messaging":
                if (!this.f6693j) {
                    i2 = R.drawable.msg;
                    break;
                } else {
                    i2 = R.drawable.msg_white;
                    break;
                }
            case "com.discord":
                return R.drawable.discord;
            case "com.tencent.mm":
                if (!this.f6693j) {
                    i2 = R.drawable.wechat;
                    break;
                } else {
                    i2 = R.drawable.wechat_white;
                    break;
                }
            case "com.tencent.mobileqq":
                if (!this.f6693j) {
                    i2 = R.drawable.qq;
                    break;
                } else {
                    i2 = R.drawable.qq_white;
                    break;
                }
            case "com.facebook.orca":
                return R.drawable.messenger;
            default:
                return 0;
        }
        return i2;
    }

    public int f(ContentResolver contentResolver) {
        int i2 = Settings.Global.getInt(contentResolver, "gsc_barrage_message_bubble_typeface", 1);
        int[] iArr = f6681o;
        return i2 < iArr.length ? iArr[i2] : iArr[2];
    }

    public int g() {
        return this.f6689f;
    }

    public Drawable h() {
        return this.f6688e;
    }

    public Rect i() {
        return this.f6695l;
    }

    public int j() {
        int i2 = BarrageFactory.c() ? 3 : 0;
        int i3 = this.f6686c + i2;
        int[] iArr = f6680n;
        return (i3 < iArr.length ? iArr[i3] : iArr[i2 + 1]) * 1000;
    }

    public int k() {
        return this.f6691h;
    }

    public int l() {
        return 15;
    }

    public int m() {
        return this.f6685b;
    }

    public boolean n() {
        return this.f6687d == R.drawable.bg_danmu_klxq;
    }

    public boolean o() {
        return this.f6694k;
    }

    public void q() {
        if (this.f6696m == null) {
            BarrageSettingsObserver barrageSettingsObserver = new BarrageSettingsObserver(BarrageFactory.b(), this);
            this.f6696m = barrageSettingsObserver;
            barrageSettingsObserver.b();
        }
    }

    public void r() {
        if (this.f6696m != null) {
            BarrageFactory.a().getContentResolver().unregisterContentObserver(this.f6696m);
            this.f6684a.clear();
            this.f6696m = null;
        }
    }

    public BarrageStyle s() {
        ContentResolver contentResolver = BarrageFactory.a().getContentResolver();
        this.f6685b = f(contentResolver);
        this.f6688e = d(contentResolver);
        this.f6686c = Settings.Global.getInt(contentResolver, "gsc_barrage_message_bubble_velocity", 1);
        this.f6689f = c(contentResolver);
        this.f6692i = Settings.Global.getInt(contentResolver, "gsc_barrage_message_quickreply", 1);
        this.f6690g = Settings.Global.getInt(contentResolver, "gsc_barrage_message_location", 0);
        this.f6691h = Settings.Global.getInt(contentResolver, "gsc_barrage_message_max_length", 54);
        BarrageLog.b(f6683q, "BarrageStyle:" + toString());
        return this;
    }

    public boolean t(String str, String str2) {
        boolean z;
        if (this.f6692i == 1) {
            if (ActivityManagerWrapper.b().a(TextUtils.isEmpty(str2) ? str : str2)) {
                z = true;
                this.f6694k = z;
                BarrageLog.b(f6683q, "updateReply, supportReply" + this.f6694k + " pkg:" + str + " targetPkgName:" + str2);
                return !TextUtils.isEmpty(str) && this.f6694k;
            }
        }
        z = false;
        this.f6694k = z;
        BarrageLog.b(f6683q, "updateReply, supportReply" + this.f6694k + " pkg:" + str + " targetPkgName:" + str2);
        if (TextUtils.isEmpty(str)) {
            return false;
        }
    }

    public String toString() {
        return "BarrageStyle{textSize=" + this.f6685b + ", duration=" + this.f6686c + ", bgStyleResId=" + this.f6687d + ", bgStyle=" + this.f6688e + ", alpha=" + this.f6689f + ", location=" + this.f6690g + ", length=" + this.f6691h + ", useWhiteIcon=" + this.f6693j + ", replyQuicklyValue=" + this.f6692i + ", supportReply=" + this.f6694k + '}';
    }
}
