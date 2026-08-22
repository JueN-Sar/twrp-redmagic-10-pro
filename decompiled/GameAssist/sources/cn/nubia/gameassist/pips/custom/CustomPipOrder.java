package cn.nubia.gameassist.pips.custom;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.install.InstallListener;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.pips.PipInfo;
import cn.nubia.gameassist.pips.panel.PipViewController;
import cn.nubia.gameassist.view.PipOrderRecycleView;
import com.zte.gameassist.BaseApplication;
import com.zte.gameassist.common.ActivityWindow;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class CustomPipOrder extends ActivityWindow implements View.OnClickListener, InstallListener {
    private final Runnable mCheckScrollBarShowRunnable;
    private long mLastScrollTime;
    private View mScrollbar;
    private PipOrderAdapter mTileAdapter;
    private final List<PipInfo> mTiles;
    private PipViewController mViewController;

    public CustomPipOrder(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void p() {
        GameAssistApplication.j();
        PipViewController pipViewController = (PipViewController) GameAssistWindowManager.O(BaseApplication.a()).T(PipViewController.class);
        this.mViewController = pipViewController;
        List o0 = pipViewController.o0();
        if (!o0.isEmpty()) {
            this.mTiles.addAll(o0);
        } else {
            GaLog.a("ActivityWindow", "pip list empty");
            d();
        }
    }

    private void q() {
        PipOrderRecycleView pipOrderRecycleView = (PipOrderRecycleView) findViewById(R.id.custom_list);
        PipOrderAdapter pipOrderAdapter = new PipOrderAdapter(getContext(), pipOrderRecycleView, this.mTiles);
        this.mTileAdapter = pipOrderAdapter;
        pipOrderAdapter.Y(this.mViewController);
        pipOrderRecycleView.setAdapter(this.mTileAdapter);
        this.mTileAdapter.V().attachToRecyclerView(pipOrderRecycleView);
        this.mViewController.j0(this.mTileAdapter);
        PipOrderLayoutManager pipOrderLayoutManager = new PipOrderLayoutManager(getContext());
        pipOrderLayoutManager.e2(this.mScrollbar);
        pipOrderLayoutManager.d2(pipOrderRecycleView);
        pipOrderLayoutManager.c2(this.mTileAdapter);
        pipOrderRecycleView.setLayoutManager(pipOrderLayoutManager);
        pipOrderRecycleView.l(new RecyclerView.OnScrollListener() { // from class: cn.nubia.gameassist.pips.custom.CustomPipOrder.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void b(RecyclerView recyclerView, int i2, int i3) {
                super.b(recyclerView, i2, i3);
                if (CustomPipOrder.this.mScrollbar.getVisibility() != 0) {
                    CustomPipOrder.this.mScrollbar.setVisibility(0);
                    CustomPipOrder.this.o();
                }
                CustomPipOrder.this.mLastScrollTime = System.currentTimeMillis();
            }
        });
    }

    private void r() {
        this.mScrollbar = findViewById(R.id.horizontal_scrollbar);
        findViewById(R.id.cancel_button).setOnClickListener(this);
        findViewById(R.id.confirm_button).setOnClickListener(this);
        q();
    }

    @Override // cn.nubia.gameassist.install.InstallListener
    public void f(String str) {
        this.mViewController.f(str);
    }

    public void o() {
        if (System.currentTimeMillis() - this.mLastScrollTime > 500) {
            this.mScrollbar.setVisibility(4);
        } else {
            this.mHandler.postDelayed(this.mCheckScrollBarShowRunnable, 1000L);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.confirm_button) {
            List U = this.mTileAdapter.U();
            ArrayList arrayList = new ArrayList();
            Iterator it = U.iterator();
            while (it.hasNext()) {
                arrayList.add(((PipInfo) it.next()).f7155b);
            }
            String join = TextUtils.join(",", arrayList);
            GaLog.a("ActivityWindow", "save local pip " + join);
            SharedPreferencesUtil.k(getContext()).N(join);
            s(arrayList);
        }
        d();
    }

    @Override // com.zte.gameassist.common.ActivityWindow, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mHandler.removeCallbacks(this.mCheckScrollBarShowRunnable);
        this.mTiles.clear();
        this.mViewController.G0(this.mTileAdapter);
        GameAssistApplication.j().v(this);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        r();
        GameAssistApplication.j().f(this);
    }

    public void s(List list) {
        StringBuilder sb = new StringBuilder();
        if (list.size() > 0) {
            sb.append("app_name1=");
            sb.append(NubiaTrackManager.o(getContext(), (String) list.get(0)));
            sb.append(",");
            sb.append("app_package_name1=");
            sb.append((String) list.get(0));
            sb.append(";");
        }
        if (list.size() > 1) {
            sb.append("app_name2=");
            sb.append(NubiaTrackManager.o(getContext(), (String) list.get(1)));
            sb.append(",");
            sb.append("app_package_name2=");
            sb.append((String) list.get(1));
            sb.append(";");
        }
        if (list.size() > 2) {
            sb.append("app_name3=");
            sb.append(NubiaTrackManager.o(getContext(), (String) list.get(2)));
            sb.append(",");
            sb.append("app_package_name3=");
            sb.append((String) list.get(2));
            sb.append(";");
        }
        GaLog.a("ActivityWindow", "sendEvent:" + sb.toString());
        NubiaTrackManager.p().B("game_assistant_permanent_pip_window", "game_assistant_permanent_pip_window", sb.toString());
    }

    @Override // cn.nubia.gameassist.install.InstallListener
    public void x(String str) {
        this.mViewController.x(str);
    }

    public CustomPipOrder(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mTiles = new ArrayList();
        this.mCheckScrollBarShowRunnable = new Runnable() { // from class: cn.nubia.gameassist.pips.custom.a
            @Override // java.lang.Runnable
            public final void run() {
                CustomPipOrder.this.o();
            }
        };
        p();
    }
}
