package cn.nubia.gamelauncher.gamecontrolpanel;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.GuidePageAdapter;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.FunctionAllocationHelper;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class GuideListDialog extends Dialog implements GuidePageAdapter.Callback {
    private static final String DB_GUIDE = "settings_gcs_game_guide";
    private Context mContext;
    private RecyclerView mGuideList;

    public GuideListDialog(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public GuideListDialog(Context context, int i) {
        super(context, i);
        this.mContext = context;
        initView();
    }

    private void confirmGuideShowed(boolean z) {
        Settings.Global.putInt(getContext().getContentResolver(), DB_GUIDE, !z ? 0 : 1);
    }

    private void initView() {
        setContentView(R.layout.guide_list_layout);
        setAttributes();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.guide_page_list);
        this.mGuideList = recyclerView;
        if (recyclerView == null) {
            return;
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext);
        linearLayoutManager.setOrientation(0);
        this.mGuideList.setLayoutManager(linearLayoutManager);
        new PagerSnapHelper().attachToRecyclerView(this.mGuideList);
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(R.mipmap.game_guide_1));
        arrayList.add(Integer.valueOf(R.mipmap.game_guide_2));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(Integer.valueOf(FunctionAllocationHelper.getInstance().isRedMagicDevice() ? R.string.gcs_game_guide_1 : R.string.gcs_game_guide_1_no_gamekey));
        arrayList2.add(Integer.valueOf(FunctionAllocationHelper.getInstance().isRedMagicDevice() ? R.string.gcs_game_guide_2 : R.string.gcs_game_guide_2_no_gamekey));
        this.mGuideList.setAdapter(new GuidePageAdapter(this, arrayList, arrayList2));
    }

    private void setAttributes() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        attributes.flags = 8;
        window.setAttributes(attributes);
        window.getDecorView().setSystemUiVisibility(5638);
        window.setBackgroundDrawable(new ColorDrawable(0));
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GuidePageAdapter.Callback
    public void onConfirm() {
        dismiss();
        confirmGuideShowed(true);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }
}
