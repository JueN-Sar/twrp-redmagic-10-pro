package cn.nubia.screensaver.card;

import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import com.zte.gameassist.common.SystemMgr;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class PlayTodayCard extends BaseCard {
    public static final Uri J = Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider/appadd?notify=false");
    private ImageView A;
    private ImageView B;
    private ImageView C;
    private View D;
    private View E;
    private View F;
    private TextView G;
    private int H;
    private final List I = new ArrayList();
    private LinearLayout v;
    private LinearLayout w;
    private TextView x;
    private TextView y;
    private TextView z;

    private List A(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor query = context.getContentResolver().query(J, null, null, null, null);
            try {
                int columnIndex = query.getColumnIndex("component");
                query.moveToPosition(-1);
                while (query.moveToNext()) {
                    String string = query.getString(columnIndex);
                    if (!TextUtils.isEmpty(string)) {
                        arrayList.add(string.substring(0, string.indexOf(44)));
                    }
                }
                query.close();
            } finally {
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    private Map B(Context context) {
        return E(context, A(context), C(), System.currentTimeMillis());
    }

    private long C() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 0, 0, 0);
        return (calendar.getTimeInMillis() / 100000) * 100000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int D(Map.Entry entry, Map.Entry entry2) {
        return ((Long) entry2.getValue()).compareTo((Long) entry.getValue());
    }

    private Map E(Context context, List list, long j2, long j3) {
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getApplicationContext().getSystemService("usagestats");
        HashMap hashMap = new HashMap();
        try {
            Class cls = Long.TYPE;
            Method declaredMethod = UsageStatsManager.class.getDeclaredMethod("queryNbStatsTime", List.class, cls, cls);
            declaredMethod.setAccessible(true);
            return (Map) declaredMethod.invoke(usageStatsManager, list, Long.valueOf(j2), Long.valueOf(j3));
        } catch (Exception e2) {
            e2.printStackTrace();
            return hashMap;
        }
    }

    private float F(TextView textView, View view, ImageView imageView, String str, long j2, float f2) {
        BigDecimal bigDecimal = new BigDecimal(j2);
        BigDecimal bigDecimal2 = new BigDecimal(f2);
        RoundingMode roundingMode = RoundingMode.HALF_UP;
        int intValue = bigDecimal.divide(bigDecimal2, 1, roundingMode).intValue();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = intValue;
        view.setLayoutParams(layoutParams);
        float floatValue = new BigDecimal(j2).divide(new BigDecimal(3600000), 1, roundingMode).floatValue();
        if (floatValue == 0.0d) {
            floatValue = 0.1f;
        }
        textView.setText(String.valueOf(floatValue));
        try {
            PackageManager packageManager = this.f8994h.getPackageManager();
            imageView.setImageDrawable(packageManager.getApplicationInfo(SystemMgr.A(str), 128).loadIcon(packageManager));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return floatValue;
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void a(View view) {
        this.v = (LinearLayout) view.findViewById(R.id.ll_play_duration_2);
        this.w = (LinearLayout) view.findViewById(R.id.ll_play_duration_3);
        this.x = (TextView) view.findViewById(R.id.tv_play_duration_1);
        this.y = (TextView) view.findViewById(R.id.tv_play_duration_2);
        this.z = (TextView) view.findViewById(R.id.tv_play_duration_3);
        this.A = (ImageView) view.findViewById(R.id.iv_play_app_icon_1);
        this.B = (ImageView) view.findViewById(R.id.iv_play_app_icon_2);
        this.C = (ImageView) view.findViewById(R.id.iv_play_app_icon_3);
        this.D = view.findViewById(R.id.bg_play_duration_bar_1);
        this.E = view.findViewById(R.id.bg_play_duration_bar_2);
        this.F = view.findViewById(R.id.bg_play_duration_bar_3);
        this.G = (TextView) view.findViewById(R.id.tv_play_total_duration);
        this.v.setVisibility(8);
        this.w.setVisibility(8);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected String b() {
        return this.f8994h.getString(R.string.play_today_card_title);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected int[] d() {
        return new int[]{R.layout.card_play_today};
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void e() {
        super.e();
        this.H = this.f8994h.getResources().getDimensionPixelOffset(R.dimen.play_today_card_duration_bar_top_height);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    public void f() {
        Map B = B(this.f8994h);
        this.I.clear();
        this.I.addAll(B.entrySet());
        this.I.sort(new Comparator() { // from class: cn.nubia.screensaver.card.c
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int D;
                D = PlayTodayCard.D((Map.Entry) obj, (Map.Entry) obj2);
                return D;
            }
        });
        s(this.I.isEmpty());
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void w(ViewGroup viewGroup, boolean z) {
        float F;
        super.w(viewGroup, z);
        float f2 = 0.0f;
        float f3 = 1.0f;
        for (int i2 = 0; i2 < Math.min(this.I.size(), 3); i2++) {
            Map.Entry entry = (Map.Entry) this.I.get(i2);
            if (i2 == 0) {
                f3 = new BigDecimal(((Long) entry.getValue()).longValue()).divide(new BigDecimal(this.H), 1, RoundingMode.HALF_UP).floatValue();
                F = F(this.x, this.D, this.A, (String) entry.getKey(), ((Long) entry.getValue()).longValue(), f3);
            } else if (i2 == 1) {
                this.v.setVisibility(0);
                F = F(this.y, this.E, this.B, (String) entry.getKey(), ((Long) entry.getValue()).longValue(), f3);
            } else if (i2 == 2) {
                this.v.setVisibility(0);
                this.w.setVisibility(0);
                F = F(this.z, this.F, this.C, (String) entry.getKey(), ((Long) entry.getValue()).longValue(), f3);
            }
            f2 += F;
        }
        this.G.setText(this.f8994h.getString(R.string.play_today_card_total_duration, Float.valueOf(f2)));
    }
}
