package cn.nubia.gameassist.plugin.sort;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.ActivityWindow;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class PluginSortWindow extends ActivityWindow implements View.OnClickListener {
    private static final String TAG = "PluginSortWindow";
    private static TileOrderChangedCallback mCallback;
    private Context mContext;
    private String mPackageName;
    private PluginCustomeAdapter mTileAdapter;
    private final ArrayList<PluginInfo> mTiles;

    public static class TileOrderChanged {
        public void a(TileOrderChangedCallback tileOrderChangedCallback) {
            PluginSortWindow.mCallback = tileOrderChangedCallback;
        }
    }

    public PluginSortWindow(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void n(String str) {
        GaLog.a(TAG, str);
        d();
    }

    private void o(Context context) {
        this.mContext = context;
        this.mPackageName = SystemMgr.v();
        List r2 = Utils.r(this.mContext);
        if (r2 == null || r2.isEmpty()) {
            n("TileHost is invalid");
            return;
        }
        Iterator it = r2.iterator();
        while (it.hasNext()) {
            this.mTiles.add(new PluginInfo(this.mContext, (String) it.next()));
        }
        GaLog.a(TAG, "PackageName " + this.mPackageName);
    }

    private void p() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.custom_list);
        PluginCustomeAdapter pluginCustomeAdapter = new PluginCustomeAdapter(this.mContext, recyclerView, this.mTiles);
        this.mTileAdapter = pluginCustomeAdapter;
        recyclerView.setAdapter(pluginCustomeAdapter);
        this.mTileAdapter.S().attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
    }

    private void q() {
        findViewById(R.id.cancel_button).setOnClickListener(this);
        findViewById(R.id.confirm_button).setOnClickListener(this);
        p();
    }

    private void r() {
        StringBuilder sb = new StringBuilder();
        int size = this.mTiles.size();
        for (int i2 = 0; i2 < size; i2++) {
            sb.append(this.mTiles.get(i2).e());
            if (i2 != size - 1) {
                sb.append(",");
            }
        }
        GaLog.a(TAG, "tileList " + ((Object) sb));
        SharedPreferencesUtil.k(this.mContext).O(this.mPackageName, sb.toString());
        TileOrderChangedCallback tileOrderChangedCallback = mCallback;
        if (tileOrderChangedCallback != null) {
            tileOrderChangedCallback.b();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.confirm_button) {
            r();
        }
        d();
    }

    @Override // com.zte.gameassist.common.ActivityWindow, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mTiles.clear();
        SharedPreferencesUtil.k(this.mContext).g0(this.mPackageName, 0);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        q();
    }

    public PluginSortWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mTiles = new ArrayList<>();
        o(context);
    }
}
