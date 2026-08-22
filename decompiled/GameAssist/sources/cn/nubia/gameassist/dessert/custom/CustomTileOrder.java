package cn.nubia.gameassist.dessert.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.dessert.TileInfo;
import cn.nubia.gameassist.dessert.TilesManager;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.ActivityWindow;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class CustomTileOrder extends ActivityWindow implements View.OnClickListener {

    @VisibleForTesting
    public static boolean mHasLauncheredForTest = false;
    private RecyclerView mRecyclerView;
    private TileAdapter mTileAdapter;
    private final List<TileInfo> mTiles;
    private TilesManager mTilesManager;

    public CustomTileOrder(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void m() {
        this.mTilesManager = TilesManager.j();
        p();
    }

    private void n() {
        this.mRecyclerView = (RecyclerView) findViewById(R.id.custom_list);
        TileAdapter tileAdapter = new TileAdapter(getContext(), this.mRecyclerView, this.mTiles);
        this.mTileAdapter = tileAdapter;
        this.mTilesManager.d(tileAdapter);
        this.mRecyclerView.setAdapter(this.mTileAdapter);
        this.mTileAdapter.S().attachToRecyclerView(this.mRecyclerView);
        this.mRecyclerView.setLayoutManager(new CustomizeLayoutManager(getContext(), this.mRecyclerView));
    }

    private void o() {
        findViewById(R.id.cancel_button).setOnClickListener(this);
        findViewById(R.id.confirm_button).setOnClickListener(this);
        n();
    }

    private void p() {
        this.mTiles.clear();
        this.mTiles.addAll(this.mTilesManager.k());
        Utils.V(this.mTiles);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.confirm_button) {
            this.mTilesManager.s(this.mTiles);
            this.mTilesManager.p();
            this.mTilesManager.f();
        }
        d();
    }

    @Override // com.zte.gameassist.common.ActivityWindow, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mTilesManager.r(this.mTileAdapter);
        this.mTiles.clear();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mTilesManager.p();
        o();
        mHasLauncheredForTest = true;
    }

    public CustomTileOrder(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mTiles = new ArrayList();
        m();
    }
}
