package cn.nubia.gameassist.tips.launch;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.meditationmode.MeditationController;
import cn.nubia.gameassist.tips.GameAssistLaunchTips;
import cn.nubia.gameassist.tips.launch.TipsMessage;
import com.zte.distbus.basetransfer.DistBusKeys;
import com.zte.gameassist.common.GameCheck;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.shared.wrapper.ContextWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class TipsMessage extends TipsBase {

    /* renamed from: h, reason: collision with root package name */
    private Paint f7600h;

    /* renamed from: i, reason: collision with root package name */
    private RectF f7601i;

    /* renamed from: j, reason: collision with root package name */
    private Bitmap f7602j;

    /* renamed from: k, reason: collision with root package name */
    private Bitmap f7603k;

    /* renamed from: l, reason: collision with root package name */
    private Bitmap f7604l;

    /* renamed from: m, reason: collision with root package name */
    private final Rect f7605m;

    /* renamed from: n, reason: collision with root package name */
    private final RectF f7606n;

    public static final class NetworkAcceleration extends ContentObserver {

        /* renamed from: f, reason: collision with root package name */
        private static volatile NetworkAcceleration f7607f;

        /* renamed from: a, reason: collision with root package name */
        private final Context f7608a;

        /* renamed from: b, reason: collision with root package name */
        private final Handler f7609b;

        /* renamed from: c, reason: collision with root package name */
        private final ContentResolver f7610c;

        /* renamed from: d, reason: collision with root package name */
        private final List f7611d;

        /* renamed from: e, reason: collision with root package name */
        private boolean f7612e;

        private NetworkAcceleration(Context context, Handler handler) {
            super(handler);
            this.f7611d = new ArrayList();
            this.f7608a = context;
            this.f7609b = handler;
            this.f7610c = context.getContentResolver();
        }

        public static NetworkAcceleration b() {
            if (f7607f == null) {
                synchronized (NetworkAcceleration.class) {
                    try {
                        if (f7607f == null) {
                            f7607f = new NetworkAcceleration(ContextWrapper.getContext(), new Handler(Looper.getMainLooper()));
                        }
                    } finally {
                    }
                }
            }
            return f7607f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void f() {
            String string = Settings.Global.getString(this.f7610c, "network_acceleration_app_label_white_list");
            if (string == null || string.length() <= 0) {
                return;
            }
            String[] split = string.split("\\|");
            ArrayList arrayList = new ArrayList();
            for (String str : split) {
                if (str != null && str.length() > 0) {
                    arrayList.add(str.strip());
                }
            }
            synchronized (this) {
                this.f7611d.clear();
                this.f7611d.addAll(arrayList);
            }
        }

        public void c() {
            if (this.f7612e) {
                return;
            }
            this.f7612e = true;
            f();
            this.f7610c.registerContentObserver(Settings.Global.getUriFor("network_acceleration_app_label_white_list"), false, this);
        }

        public boolean d() {
            return Settings.Global.getInt(this.f7608a.getContentResolver(), "db_name_game_network_acceleration_sdk", 0) > 0;
        }

        public boolean e() {
            boolean contains;
            if (!d()) {
                return false;
            }
            try {
                GameCheck.GameAppInfo d2 = GameCheck.d(SystemMgr.w, SystemMgr.A);
                if (d2 != null) {
                    String a2 = d2.a();
                    synchronized (this) {
                        contains = this.f7611d.contains(a2);
                    }
                    return contains;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return false;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            super.onChange(z);
            this.f7609b.post(new Runnable() { // from class: i.c
                @Override // java.lang.Runnable
                public final void run() {
                    TipsMessage.NetworkAcceleration.this.f();
                }
            });
        }
    }

    public TipsMessage(GameAssistLaunchTips gameAssistLaunchTips, Context context) {
        super(gameAssistLaunchTips, context, context.getResources());
        this.f7605m = new Rect();
        this.f7606n = new RectF();
        this.f7601i = new RectF();
        Paint paint = new Paint();
        this.f7600h = paint;
        paint.setStyle(Paint.Style.FILL);
    }

    private void o(ArrayList arrayList) {
        GameAssistLaunchTips gameAssistLaunchTips = this.f7571a;
        if (gameAssistLaunchTips == null || !gameAssistLaunchTips.p()) {
            return;
        }
        arrayList.clear();
        arrayList.add(new Pair("tips/solarcore.png", g(R.string.cube_message_ai)));
        arrayList.add(new Pair("tips/solarcore.png", g(R.string.cube_message_memory)));
        arrayList.add(new Pair("tips/solarcore.png", g(R.string.cube_message_net)));
        arrayList.add(new Pair("tips/solarcore.png", g(R.string.cube_message_screen_adjust)));
    }

    private RectF p(int i2) {
        float m2 = m(672.0f) * 0.5f;
        float n2 = n(331.0f) * 0.5f;
        float[] fArr = this.f7574d;
        float m3 = m(fArr != null ? fArr[i2] : this.f7571a.getMinPixels() / 2);
        float f2 = m3 - m2;
        float f3 = m3 + m2;
        float[] fArr2 = this.f7575e;
        float n3 = n(fArr2 != null ? fArr2[i2] : 630.0f);
        this.f7601i.set(f2, n3 - n2, f3, n3 + n2);
        return this.f7601i;
    }

    private void r() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        boolean t = t(this.f7571a.getGamePackage());
        sb.append(t ? "F_" : "f_");
        if (ZteFeature.supportFan() && t) {
            arrayList.add(new Pair("tips/fan.png", g(R.string.game_assist_tips_fan)));
        }
        sb.append("C_");
        arrayList.add(new Pair("tips/clear.png", g(R.string.game_assist_tips_clean)));
        sb.append("T_");
        arrayList.add(new Pair("tips/turbo.png", g(R.string.game_assist_tips_capability)));
        boolean e2 = NetworkAcceleration.b().e();
        sb.append(e2 ? "N_" : "n_");
        if (e2) {
            arrayList.add(new Pair("tips/wifi_sp.png", g(R.string.game_assist_tips_network)));
        }
        boolean z = 3 <= q();
        sb.append(z ? "M_" : "m_");
        if (z) {
            arrayList.add(new Pair("tips/message_no.png", g(R.string.game_assist_tips_notifycation)));
        }
        boolean z2 = Settings.Global.getInt(this.f7572b.getContentResolver(), "cc_game_mis_operate", 1) == 1;
        sb.append(z2 ? "A_" : "a_");
        if (z2) {
            arrayList.add(new Pair("tips/touch_no.png", g(R.string.game_assist_tips_mis_operate)));
        }
        boolean z3 = !MeditationController.s().u();
        sb.append(z3 ? "P_" : "p_");
        if (z3) {
            arrayList.add(new Pair("tips/phone_no.png", g(R.string.game_assist_tips_telephone)));
        }
        boolean u = u(d());
        sb.append(u ? "R_" : "r_");
        if (u) {
            arrayList.add(new Pair("tips/showtime.png", g(ZteFeature.isRedMagicProduct() ? R.string.game_assist_tips_red_magic_time : R.string.game_assist_tips_wonderful_time)));
        }
        sb.append("E_");
        arrayList.add(new Pair("tips/showtime.png", g(R.string.game_assist_tips_ai_cube)));
        boolean supportLiquidCooling = ZteFeature.supportLiquidCooling();
        sb.append(supportLiquidCooling ? "C" : "c_");
        if (supportLiquidCooling) {
            boolean s2 = s(this.f7571a.getGamePackage());
            sb.append(supportLiquidCooling ? "A" : DistBusKeys.KEY_WIFI_ENABLE);
            boolean z4 = Settings.System.getInt(this.f7572b.getContentResolver(), "liquid_cooling_off_on", 0) > 0;
            sb.append(supportLiquidCooling ? "N_" : "n_");
            if (supportLiquidCooling && (s2 || z4)) {
                arrayList.add(new Pair("tips/showtime.png", g(R.string.game_assist_tips_liquid_cooling)));
            }
        }
        Log.d("LaunchTips", " msg ---- " + sb.toString());
        o(arrayList);
        Paint paint = new Paint();
        paint.setShader(null);
        paint.setAntiAlias(true);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(this.f7572b.getResources().getDisplayMetrics().density * 12.0f);
        paint.setColor(Color.pack(-1));
        Rect rect = new Rect();
        int n2 = (int) n(95.0f);
        int n3 = (int) n(5.0f);
        int n4 = (int) n(23.0f);
        int n5 = (int) n(23.0f);
        int m2 = (int) m(672.0f);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            paint.getTextBounds((String) ((Pair) arrayList.get(i2)).second, 0, ((String) ((Pair) arrayList.get(i2)).second).length(), rect);
            int width = rect.width() + n3 + 1;
            if (m2 < width) {
                m2 = width;
            }
        }
        int m3 = (int) m(m2);
        int size = (arrayList.size() * n2) + n4 + n5;
        int n6 = (int) n(331.0f);
        Bitmap bitmap = this.f7602j;
        if (bitmap == null || bitmap.isRecycled()) {
            this.f7602j = Bitmap.createBitmap(m3, size, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(this.f7602j);
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            try {
                paint.getTextBounds((String) ((Pair) arrayList.get(i3)).second, 0, ((String) ((Pair) arrayList.get(i3)).second).length(), rect);
                float height = (rect.height() + n2) / 2;
                float f2 = this.f7571a.n() ? n3 : m3 / 2;
                float f3 = (n2 * i3) + height + n4;
                paint.setTextAlign(this.f7571a.n() ? Paint.Align.LEFT : Paint.Align.CENTER);
                canvas.drawText((String) ((Pair) arrayList.get(i3)).second, f2, f3, paint);
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        Bitmap bitmap2 = this.f7603k;
        if (bitmap2 == null || bitmap2.isRecycled()) {
            Paint paint2 = new Paint();
            this.f7603k = Bitmap.createBitmap(m3, n6, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(this.f7603k);
            paint2.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, this.f7603k.getHeight(), new int[]{0, -1, -1, 0}, new float[]{0.0f, 0.09033232f, 0.9096677f, 1.0f}, Shader.TileMode.CLAMP));
            canvas2.drawRect(0.0f, 0.0f, this.f7603k.getWidth(), this.f7603k.getHeight(), paint2);
        }
        this.f7605m.set(0, 0, this.f7603k.getWidth(), this.f7603k.getHeight());
        Bitmap bitmap3 = this.f7604l;
        if (bitmap3 == null || bitmap3.isRecycled()) {
            this.f7604l = Bitmap.createBitmap(m3, n6, Bitmap.Config.ARGB_8888);
        }
    }

    private boolean s(String str) {
        Cursor cursor = null;
        try {
            try {
                cursor = this.f7572b.getContentResolver().query(Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider/appadd?notify=true"), new String[]{"autoOpenLiquid"}, "component LIKE '" + str + "%' ", null, null);
                int i2 = (cursor == null || !cursor.moveToFirst()) ? 0 : cursor.getInt(cursor.getColumnIndex("autoOpenFan"));
                if (cursor != null) {
                    cursor.close();
                }
                return i2 == 1;
            } catch (Exception e2) {
                Log.e("LaunchTips", " getCurPkgAutoOpenCoolStatus error : " + e2.toString());
                if (cursor == null) {
                    return false;
                }
                cursor.close();
                return false;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private boolean t(String str) {
        Cursor cursor = null;
        try {
            try {
                cursor = this.f7572b.getContentResolver().query(Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider/appadd?notify=true"), new String[]{"autoOpenFan"}, "component LIKE '" + str + "%' ", null, null);
                int i2 = (cursor == null || !cursor.moveToFirst()) ? 0 : cursor.getInt(cursor.getColumnIndex("autoOpenFan"));
                if (cursor != null) {
                    cursor.close();
                }
                return i2 == 1;
            } catch (Exception e2) {
                Log.e("LaunchTips", " getCurPkgAutoOpenFanStatus error : " + e2.toString());
                if (cursor == null) {
                    return false;
                }
                cursor.close();
                return false;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private boolean u(String str) {
        return TextUtils.equals(str, "com.tencent.tmgp.sgame") ? Settings.Global.getInt(this.f7572b.getContentResolver(), "persist_sys_nubia_redmagic_time_switch_wzry", 0) == 1 : TextUtils.equals(str, "com.tencent.tmgp.pubgmhd") ? Settings.Global.getInt(this.f7572b.getContentResolver(), "persist_sys_nubia_redmagic_time_switch_hpjy", 0) == 1 : TextUtils.equals(str, "com.tencent.ig") ? Settings.Global.getInt(this.f7572b.getContentResolver(), "persist_sys_nubia_redmagic_time_switch_pubg", 0) == 1 : TextUtils.equals(str, "com.tencent.lolm") ? Settings.Global.getInt(this.f7572b.getContentResolver(), "persist_sys_nubia_redmagic_time_switch_lol", 0) == 1 : TextUtils.equals(str, "com.tencent.tmgp.cf") && Settings.Global.getInt(this.f7572b.getContentResolver(), "persist_sys_nubia_redmagic_time_switch_cf", 0) == 1;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public boolean a(Canvas canvas, long j2, long j3, int i2, float f2) {
        float f3;
        int save = canvas.save();
        if (42 <= i2) {
            RectF p2 = p(i2);
            float f4 = this.f7577g[i2];
            this.f7600h.setAlpha((int) this.f7576f[i2]);
            canvas.scale(f4, f4, this.f7571a.getFixWidth() / 2, this.f7571a.getFixHeight() / 2);
            if (67 > i2 || this.f7602j.getHeight() <= p2.height()) {
                f3 = 0.0f;
            } else {
                f3 = ((i2 + f2) - 67.0f) * 10.0f;
                float height = this.f7602j.getHeight() - p2.height();
                if (f3 > height) {
                    f3 = height;
                }
            }
            Canvas canvas2 = new Canvas(this.f7604l);
            canvas2.drawColor(0, PorterDuff.Mode.CLEAR);
            canvas2.translate(0.0f, -f3);
            canvas2.drawBitmap(this.f7602j, 0.0f, 0.0f, this.f7600h);
            this.f7600h.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas2.drawBitmap(this.f7603k, 0.0f, f3, this.f7600h);
            this.f7600h.setXfermode(null);
            canvas.drawBitmap(this.f7604l, (int) p2.left, (int) p2.top, (Paint) null);
        }
        canvas.restoreToCount(save);
        return true;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public Path b() {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.lineTo(53.0f, 0.0f);
        path.cubicTo(62.5f, 0.0f, 53.95f, 255.0f, 72.0f, 255.0f);
        path.lineTo(113.0f, 255.0f);
        path.cubicTo(119.0f, 255.0f, 113.6f, 51.0f, 125.0f, 51.0f);
        return path;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public Path f() {
        Path path = new Path();
        path.moveTo(0.0f, 1.0f);
        path.lineTo(113.0f, 1.0f);
        path.cubicTo(114.5f, 1.0f, 113.15f, 1.1f, 116.0f, 1.1f);
        path.cubicTo(120.5f, 1.1f, 116.45f, 0.72f, 125.0f, 0.72f);
        return path;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public Path h() {
        if (!this.f7571a.n()) {
            float minPixels = this.f7571a.getMinPixels() - 672;
            Path path = new Path();
            path.moveTo(0.0f, minPixels);
            path.lineTo(125.0f, minPixels);
            return null;
        }
        Path path2 = new Path();
        float m2 = m(1248.0f);
        float m3 = m(1566.0f);
        path2.moveTo(0.0f, m2);
        path2.lineTo(53.0f, m2);
        path2.cubicTo(56.78f, m2, 58.22f, m3, 62.0f, m3);
        path2.lineTo(125.0f, m3);
        return path2;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public Path i() {
        if (this.f7571a.n()) {
            return null;
        }
        Path path = new Path();
        float n2 = n(1450.0f);
        float n3 = n(1702.0f);
        path.moveTo(0.0f, n2);
        path.lineTo(53.0f, n2);
        path.cubicTo(56.78f, n2, 58.22f, n3, 62.0f, n3);
        path.lineTo(125.0f, n3);
        return path;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    public void k() {
        Bitmap bitmap = this.f7602j;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.f7602j.recycle();
            this.f7602j = null;
        }
        Bitmap bitmap2 = this.f7603k;
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            this.f7603k.recycle();
            this.f7603k = null;
        }
        Bitmap bitmap3 = this.f7604l;
        if (bitmap3 == null || bitmap3.isRecycled()) {
            return;
        }
        this.f7604l.recycle();
        this.f7604l = null;
    }

    @Override // cn.nubia.gameassist.tips.launch.TipsBase
    protected void l() {
        r();
        super.l();
    }

    public int q() {
        return Settings.Global.getInt(this.f7572b.getContentResolver(), "gsc_meditation_level", 0);
    }
}
