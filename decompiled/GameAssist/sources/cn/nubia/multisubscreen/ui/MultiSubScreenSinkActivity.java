package cn.nubia.multisubscreen.ui;

import android.R;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.window.OnBackInvokedCallback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gameassist.dessert.tiles.ChargeSeparationTiles;
import cn.nubia.multisubscreen.CastRole;
import cn.nubia.multisubscreen.DessertAdapter;
import cn.nubia.multisubscreen.data.BatchData;
import cn.nubia.multisubscreen.mgr.ConnectCodeMgr;
import cn.nubia.multisubscreen.mgr.MultiSubScreenThemeMgr;
import cn.nubia.multisubscreen.secondary.NumericalDataParser;
import cn.nubia.multisubscreen.secondary.SecDeviceDataMgr;
import cn.nubia.multisubscreen.secondary.SlideViewCtrl;
import cn.nubia.multisubscreen.tiles.MultiSubScreenDessertTile;
import cn.nubia.multisubscreen.tiles.MultiSubScreenTileHost;
import cn.nubia.multisubscreen.utils.LockScreenHelper;
import cn.nubia.multisubscreen.utils.MultiSubScreenNotiMsgUtils;
import cn.nubia.multisubscreen.utils.MultiSubScreenTileUtils;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import cn.nubia.multisubscreen.view.SinkDisplayView;
import cn.nubia.multisubscreen.view.SinkTitleView;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MultiSubScreenSinkActivity extends MultiSubScreenBaseActivity implements View.OnClickListener, SecDeviceDataMgr.DataChangeListener {

    /* renamed from: i, reason: collision with root package name */
    private DessertAdapter f8117i;

    /* renamed from: j, reason: collision with root package name */
    private RecyclerView f8118j;

    /* renamed from: k, reason: collision with root package name */
    private DessertAdapter f8119k;

    /* renamed from: l, reason: collision with root package name */
    private RecyclerView f8120l;

    /* renamed from: m, reason: collision with root package name */
    private MultiSubScreenTileHost f8121m;

    /* renamed from: n, reason: collision with root package name */
    private SecDeviceDataMgr f8122n;

    /* renamed from: o, reason: collision with root package name */
    private SinkTitleView f8123o;

    /* renamed from: p, reason: collision with root package name */
    private SlideViewCtrl f8124p;

    /* renamed from: q, reason: collision with root package name */
    private SinkDisplayView f8125q;

    /* renamed from: r, reason: collision with root package name */
    private View f8126r;

    /* renamed from: s, reason: collision with root package name */
    private ViewGroup f8127s;
    private MultiSubScreenThemeMgr t;
    private boolean u = false;
    private final BroadcastReceiver v = new BroadcastReceiver() { // from class: cn.nubia.multisubscreen.ui.MultiSubScreenSinkActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("com.zte.multi.subscreen.ACTION_CLOSE_ALT".equalsIgnoreCase(intent.getAction())) {
                MultiSubScreenSinkActivity.this.finishAndRemoveTask();
            }
        }
    };
    private OnBackInvokedCallback w = new OnBackInvokedCallback() { // from class: cn.nubia.multisubscreen.ui.MultiSubScreenSinkActivity.2
        @Override // android.window.OnBackInvokedCallback
        public void onBackInvoked() {
            GaLog.e("MultiSubScreenSinkActivity", "mOnBackInvokedCallback onBackInvoked showDisconnectDialog!");
            MultiSubScreenSinkActivity.this.q();
        }
    };

    private void m() {
        this.f8127s = (ViewGroup) findViewById(R.id.content);
        SinkTitleView sinkTitleView = (SinkTitleView) findViewById(cn.nubia.gameassist.R.id.sink_title_view);
        this.f8123o = sinkTitleView;
        sinkTitleView.setOnDisconnectClickListener(this);
        this.f8118j = (RecyclerView) findViewById(cn.nubia.gameassist.R.id.multi_sub_screen_left_dessert);
        DessertAdapter dessertAdapter = new DessertAdapter(this);
        this.f8117i = dessertAdapter;
        this.f8118j.setAdapter(dessertAdapter);
        this.f8118j.setLayoutManager(new LinearLayoutManager(this, 0, false));
        r(null);
        this.f8120l = (RecyclerView) findViewById(cn.nubia.gameassist.R.id.multi_sub_screen_right_dessert);
        DessertAdapter dessertAdapter2 = new DessertAdapter(this);
        this.f8119k = dessertAdapter2;
        this.f8120l.setAdapter(dessertAdapter2);
        this.f8120l.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.f8126r = findViewById(cn.nubia.gameassist.R.id.slide_region);
        this.f8124p.h((RecyclerView) findViewById(cn.nubia.gameassist.R.id.slide_layout));
        SinkDisplayView sinkDisplayView = (SinkDisplayView) findViewById(cn.nubia.gameassist.R.id.display_region);
        this.f8125q = sinkDisplayView;
        sinkDisplayView.setOnZoomListener(new SinkDisplayView.OnZoomListener() { // from class: cn.nubia.multisubscreen.ui.MultiSubScreenSinkActivity.3
            @Override // cn.nubia.multisubscreen.view.SinkDisplayView.OnZoomListener
            public void a() {
                MultiSubScreenSinkActivity.this.f8123o.setVisibility(8);
                MultiSubScreenSinkActivity.this.f8118j.setVisibility(8);
                MultiSubScreenSinkActivity.this.f8120l.setVisibility(8);
                MultiSubScreenSinkActivity.this.f8126r.setVisibility(8);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) MultiSubScreenSinkActivity.this.f8125q.getLayoutParams();
                int dimensionPixelSize = MultiSubScreenSinkActivity.this.getResources().getDimensionPixelSize(cn.nubia.gameassist.R.dimen.sink_display_margin_horizontal_large);
                marginLayoutParams.leftMargin = dimensionPixelSize;
                marginLayoutParams.rightMargin = dimensionPixelSize;
                int dimensionPixelSize2 = MultiSubScreenSinkActivity.this.getResources().getDimensionPixelSize(cn.nubia.gameassist.R.dimen.sink_display_margin_vertical_large);
                marginLayoutParams.topMargin = dimensionPixelSize2;
                marginLayoutParams.bottomMargin = dimensionPixelSize2;
                MultiSubScreenSinkActivity.this.f8125q.setLayoutParams(marginLayoutParams);
            }

            @Override // cn.nubia.multisubscreen.view.SinkDisplayView.OnZoomListener
            public void b() {
                MultiSubScreenSinkActivity.this.f8123o.setVisibility(0);
                MultiSubScreenSinkActivity.this.f8118j.setVisibility(0);
                MultiSubScreenSinkActivity.this.f8120l.setVisibility(0);
                MultiSubScreenSinkActivity.this.f8126r.setVisibility(0);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) MultiSubScreenSinkActivity.this.f8125q.getLayoutParams();
                marginLayoutParams.leftMargin = MultiSubScreenSinkActivity.this.getResources().getDimensionPixelSize(cn.nubia.gameassist.R.dimen.sink_display_margin_start);
                marginLayoutParams.rightMargin = MultiSubScreenSinkActivity.this.getResources().getDimensionPixelSize(cn.nubia.gameassist.R.dimen.sink_display_margin_end);
                marginLayoutParams.topMargin = 0;
                marginLayoutParams.bottomMargin = 0;
                MultiSubScreenSinkActivity.this.f8125q.setLayoutParams(marginLayoutParams);
            }
        });
        s(null);
    }

    private void o(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONArray optJSONArray = jSONObject.optJSONArray("numerical");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    arrayList.add(optJSONArray.getString(i2));
                }
                this.f8123o.setKeys(arrayList);
                this.f8125q.setKeys(arrayList);
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("slide");
            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                ArrayList arrayList2 = new ArrayList();
                for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                    arrayList2.add(optJSONArray2.getString(i3));
                }
                this.f8124p.n(arrayList2);
            }
            JSONArray optJSONArray3 = jSONObject.optJSONArray("dessert");
            if (optJSONArray3 != null && optJSONArray3.length() > 0) {
                ArrayList arrayList3 = new ArrayList();
                for (int i4 = 0; i4 < optJSONArray3.length(); i4++) {
                    arrayList3.add(optJSONArray3.getString(i4));
                }
                r(arrayList3);
            }
            JSONArray optJSONArray4 = jSONObject.optJSONArray("right_dessert");
            if (optJSONArray4 == null || optJSONArray4.length() <= 0) {
                s(null);
            } else {
                ArrayList arrayList4 = new ArrayList();
                for (int i5 = 0; i5 < optJSONArray4.length(); i5++) {
                    arrayList4.add(optJSONArray4.getString(i5));
                }
                s(arrayList4);
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("value_region");
            if (optJSONObject != null) {
                p(optJSONObject);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void p(JSONObject jSONObject) {
        JSONArray optJSONArray;
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            next.hashCode();
            if (next.equals("performance_mode") && (optJSONArray = jSONObject.optJSONArray(next)) != null) {
                int[] iArr = new int[optJSONArray.length()];
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    try {
                        iArr[i2] = optJSONArray.getInt(i2);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
                this.f8124p.q(next, iArr);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        Intent intent = new Intent(this, (Class<?>) ChooseDeviceAlertAty.class);
        intent.putExtra("IS_SHOW_DISCONNECT_DIALOG", true);
        startActivity(intent);
    }

    private void r(List list) {
        ArrayList arrayList = new ArrayList(MultiSubScreenTileUtils.m(this, this.f8121m, list, list == null));
        GaLog.e("MultiSubScreenSinkActivity", "updateDessertTiles tiles = " + arrayList + " " + list);
        this.f8117i.S(arrayList);
    }

    private void s(List list) {
        if (list == null || list.isEmpty()) {
            this.f8120l.setVisibility(8);
            this.f8125q.v(false);
            return;
        }
        ArrayList arrayList = new ArrayList(MultiSubScreenTileUtils.k(this, this.f8121m, list, false));
        GaLog.e("MultiSubScreenSinkActivity", "updateRightDessertTiles tiles = " + arrayList + " " + list);
        this.f8120l.setVisibility(0);
        this.f8119k.S(arrayList);
        this.f8125q.v(true);
    }

    @Override // cn.nubia.multisubscreen.secondary.SecDeviceDataMgr.DataChangeListener
    public void b(String str) {
        o(str);
    }

    @Override // cn.nubia.multisubscreen.secondary.SecDeviceDataMgr.DataChangeListener
    public void c(BatchData batchData) {
        String str;
        boolean z;
        for (String str2 : batchData.getKeys()) {
            str = batchData.get(str2);
            str2.hashCode();
            z = true;
            switch (str2) {
                case "notification_mode":
                case "competition_light":
                case "volume":
                case "brightness":
                case "fan_mode":
                    this.f8124p.l(str2, str);
                    break;
                case "multi_sub_screen_notification_msg_content":
                    GaLog.e("MultiSubScreenSinkActivity", "onDeviceDataChange MULTI_SUB_SCREEN_NOTIFICATION_MSG_CONTENT value = " + str);
                    this.f8125q.w(str);
                    break;
                case "fan":
                    boolean equals = "1".equals(str);
                    MultiSubScreenDessertTile g2 = MultiSubScreenTileUtils.g(str2);
                    if (g2 != null) {
                        g2.z0(equals);
                    }
                    this.f8124p.m(equals);
                    this.f8125q.setFanOn(equals);
                    break;
                case "wifi":
                case "performance_monitor":
                case "notification_msg":
                case "mis_operate":
                    MultiSubScreenDessertTile g3 = MultiSubScreenTileUtils.g(str2);
                    if (g3 != null) {
                        g3.z0("1".equals(str));
                        break;
                    } else {
                        break;
                    }
                case "charge_separation":
                    MultiSubScreenDessertTile g4 = MultiSubScreenTileUtils.g(str2);
                    if (g4 != null) {
                        if (!"1".equals(str) && 3 != Integer.parseInt(str)) {
                            z = false;
                        }
                        g4.z0(z);
                    }
                    int parseInt = Integer.parseInt(str);
                    GaLog.e("MultiSubScreenSinkActivity", "onDeviceDataChange charge_separation result = " + parseInt);
                    if (parseInt == 4) {
                        Toast.makeText(this, ChargeSeparationTiles.z0(this, cn.nubia.gameassist.R.string.main_screen_charge_separation_disconnect_charge_warning_text), 0).show();
                        break;
                    } else if (parseInt == 5) {
                        Toast.makeText(this, ChargeSeparationTiles.z0(this, cn.nubia.gameassist.R.string.main_screen_charge_separation_battery_low20_warning_text), 0).show();
                        break;
                    } else if (parseInt == 3) {
                        Toast.makeText(this, ChargeSeparationTiles.z0(this, cn.nubia.gameassist.R.string.main_screen_charge_separation_open_warning_text), 0).show();
                        break;
                    } else if (parseInt == 2) {
                        Toast.makeText(this, ChargeSeparationTiles.z0(this, cn.nubia.gameassist.R.string.main_screen_charge_separation_close_warning_text), 0).show();
                        break;
                    } else {
                        break;
                    }
                    break;
                case "performance_mode":
                    try {
                        int i2 = new JSONArray(str).getInt(0);
                        this.f8124p.l(str2, str);
                        this.t.h(i2);
                        this.f8125q.setPerformanceMode(i2);
                        this.f8124p.o(!MultiSubScreenUtils.f8186p);
                        break;
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                        break;
                    }
                case "multi_sub_screen_remove_notification_msg":
                    GaLog.e("MultiSubScreenSinkActivity", "onDeviceDataChange MULTI_SUB_SCREEN_REMOVE_NOTIFICATION_MSG value = " + str);
                    this.f8125q.o(str);
                    break;
            }
        }
    }

    @Override // cn.nubia.multisubscreen.secondary.SecDeviceDataMgr.DataChangeListener
    public void d(BatchData batchData) {
        NumericalDataParser.a(batchData, this.f8123o, this.f8125q);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == cn.nubia.gameassist.R.id.sink_disconnect) {
            q();
        }
    }

    @Override // cn.nubia.multisubscreen.ui.MultiSubScreenBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(cn.nubia.gameassist.R.layout.multi_sub_screen_sink_activity);
        this.f8121m = new MultiSubScreenTileHost(this);
        this.f8124p = new SlideViewCtrl(this);
        m();
        this.t = MultiSubScreenThemeMgr.e();
        SecDeviceDataMgr f2 = SecDeviceDataMgr.f();
        this.f8122n = f2;
        f2.m();
        this.f8122n.l(this);
        String h2 = SecDeviceDataMgr.f().h();
        if (!TextUtils.isEmpty(h2)) {
            o(h2);
        }
        if (this.u) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.zte.multi.subscreen.ACTION_CLOSE_ALT");
        registerReceiver(this.v, intentFilter, 2);
        this.u = true;
    }

    @Override // cn.nubia.multisubscreen.ui.MultiSubScreenBaseActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        GaLog.e("MultiSubScreenSinkActivity", "MultiSubScreenSinkActivity onDestroy");
        this.f8117i.R();
        this.f8122n.y(this);
        if (MultiSubScreenUtils.f8184n) {
            MultiSubScreenUtils.f8184n = false;
        } else {
            MultiSubScreenUtils.D(false);
        }
        ConnectCodeMgr.h().x("SINK_REQUIRED_DISCONNECT_CODE");
        if (this.u) {
            unregisterReceiver(this.v);
            this.u = false;
        }
        this.f8124p.k();
        MultiSubScreenTileUtils.a();
        MultiSubScreenNotiMsgUtils.g();
        MultiSubScreenUtils.F(CastRole.UN_KNOW);
    }

    @Override // cn.nubia.multisubscreen.ui.MultiSubScreenBaseActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        ConnectCodeMgr.h().x("SINK_NOTIFY_SOURCE_IN_BG");
        LockScreenHelper.a().c();
        getOnBackInvokedDispatcher().unregisterOnBackInvokedCallback(this.w);
    }

    @Override // cn.nubia.multisubscreen.ui.MultiSubScreenBaseActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        ConnectCodeMgr.h().x("SINK_NOTIFY_SOURCE_IN_FG");
        LockScreenHelper.a().b();
        getOnBackInvokedDispatcher().registerOnBackInvokedCallback(0, this.w);
    }
}
