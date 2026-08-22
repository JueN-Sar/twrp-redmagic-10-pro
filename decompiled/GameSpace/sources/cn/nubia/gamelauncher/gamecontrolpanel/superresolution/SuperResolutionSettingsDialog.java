package cn.nubia.gamelauncher.gamecontrolpanel.superresolution;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes.dex */
public class SuperResolutionSettingsDialog extends Dialog implements View.OnClickListener {
    private static String[] SUPPORT_ITEM = null;
    private static final String TAG = "SuperResolutionSettingsDialog";
    private Context mContext;
    private String mCurPkgName;
    private ArrayList<ItemData> mDataList;
    private ItemAdapter mItemAdapter;
    private RestartAppWarningDialogCallBack mRestartAppWarningDialogCallBack;
    private Integer mSwitchStatus;
    private RecyclerView recyclerView;
    private View rootView;

    public SuperResolutionSettingsDialog(Context context, String str, Integer num, RestartAppWarningDialogCallBack restartAppWarningDialogCallBack) {
        super(context, R.style.DualScreenMapDialog);
        this.mContext = context;
        this.mCurPkgName = str;
        this.mSwitchStatus = num;
        this.mRestartAppWarningDialogCallBack = restartAppWarningDialogCallBack;
        initData();
        initView();
    }

    private HashMap createHashMap(String str) {
        String str2;
        HashMap hashMap = new HashMap();
        int i = 0;
        int i2 = 0;
        while (true) {
            String[] strArr = SUPPORT_ITEM;
            if (i >= strArr.length) {
                str2 = null;
                break;
            }
            str2 = strArr[i];
            if (str == str2) {
                i2 = Integer.valueOf(i + 1);
                break;
            }
            i++;
        }
        hashMap.put(str2, i2);
        return hashMap;
    }

    private String createSuperGear(String str) {
        str.hashCode();
        return (str.equals(SuperResolutionHelper.SUPER_GEAR_1116) || !str.equals(SuperResolutionHelper.SUPER_GEAR_1440)) ? SuperResolutionHelper.SUPER_GEAR_1116 : SuperResolutionHelper.SUPER_GEAR_1440;
    }

    private boolean getCheckedStatus(String str) {
        if (this.mSwitchStatus != null) {
            boolean z = false;
            for (int i = 0; i < SUPPORT_ITEM.length; i++) {
                if (i == this.mSwitchStatus.intValue() - 1 && str == SUPPORT_ITEM[i]) {
                    z = true;
                }
            }
            return z;
        }
        int i2 = 0;
        while (true) {
            String[] strArr = SUPPORT_ITEM;
            if (i2 >= strArr.length) {
                return false;
            }
            if (i2 == 0 && str == strArr[0]) {
                return true;
            }
            i2++;
        }
    }

    private int getDescriptionResId(String str) {
        str.hashCode();
        return (str.equals(SuperResolutionHelper.SUPER_GEAR_1116) || !str.equals(SuperResolutionHelper.SUPER_GEAR_1440)) ? R.string.super_resolution_1116_description : R.string.super_resolution_1440_description;
    }

    private void initData() {
        ArrayList<ItemData> arrayList = new ArrayList<>();
        this.mDataList = arrayList;
        arrayList.clear();
        String[] supportResolutionGear = SuperResolutionHelper.getSupportResolutionGear(this.mCurPkgName);
        SUPPORT_ITEM = supportResolutionGear;
        for (String str : supportResolutionGear) {
            ItemData itemData = new ItemData();
            itemData.setSelected(getCheckedStatus(str));
            itemData.setHashMap(createHashMap(str));
            itemData.setSuperGear(str);
            itemData.setDescription(getContext().getString(getDescriptionResId(str)));
            this.mDataList.add(itemData);
        }
    }

    private void initView() {
        LogUtil.i(TAG, "  initView");
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.nubia_super_resolution_settings_layout, (ViewGroup) null);
        this.rootView = inflate;
        inflate.findViewById(R.id.close_View).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.superresolution.SuperResolutionSettingsDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SuperResolutionSettingsDialog.this.onClick(view);
            }
        });
        RecyclerView recyclerView = (RecyclerView) this.rootView.findViewById(R.id.recycler_view);
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        ItemAdapter itemAdapter = new ItemAdapter(this.mContext);
        this.mItemAdapter = itemAdapter;
        itemAdapter.setDataList(this.mDataList);
        this.recyclerView.setAdapter(this.mItemAdapter);
        this.rootView.findViewById(R.id.cancel_view).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.superresolution.SuperResolutionSettingsDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SuperResolutionSettingsDialog.this.m308x374f701d(view);
            }
        });
        this.rootView.findViewById(R.id.positive_view).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.superresolution.SuperResolutionSettingsDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SuperResolutionSettingsDialog.this.m309x7cf0b2bc(view);
            }
        });
        setContentView(this.rootView);
        setAttributes();
    }

    private void saveData() {
        int selectedItemPosition = this.mItemAdapter.getSelectedItemPosition();
        LogUtil.i(TAG, " saveData itemPosition : " + selectedItemPosition);
        String superGear = this.mDataList.get(selectedItemPosition).getSuperGear();
        LogUtil.i(TAG, " saveData superGear : " + superGear + " ;; map = " + this.mDataList.get(selectedItemPosition).getHashMap());
        int intValue = this.mDataList.get(selectedItemPosition).getHashMap() != null ? ((Integer) this.mDataList.get(selectedItemPosition).getHashMap().get(superGear)).intValue() : 0;
        LogUtil.i(TAG, " saveData superGearValue : " + intValue);
        SuperResolutionHelper.openSuperResolution(this.mContext, this.mCurPkgName, intValue);
    }

    private void setAttributes() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        attributes.width = this.mContext.getResources().getDimensionPixelSize(R.dimen.super_resolution_dialog_width_size);
        attributes.height = this.mContext.getResources().getDimensionPixelSize(R.dimen.super_resolution_dialog_height_size);
        attributes.type = 2038;
        attributes.gravity = GravityCompat.END;
        attributes.x = this.mContext.getResources().getDimensionPixelSize(R.dimen.super_resolution_dialog_padding_x_size);
        attributes.flags = 8;
        attributes.screenOrientation = 6;
        attributes.setTitle("SuperResolutionSettingsPanel");
        window.setAttributes(attributes);
        window.getDecorView().setSystemUiVisibility(5638);
        window.setBackgroundDrawable(new ColorDrawable(0));
    }

    /* renamed from: lambda$initView$0$cn-nubia-gamelauncher-gamecontrolpanel-superresolution-SuperResolutionSettingsDialog, reason: not valid java name */
    /* synthetic */ void m308x374f701d(View view) {
        LogUtil.i(TAG, " click cancel_view ");
        Settings.Global.putInt(getContext().getContentResolver(), "game_mode_floating_window_show", 0);
        dismiss();
    }

    /* renamed from: lambda$initView$1$cn-nubia-gamelauncher-gamecontrolpanel-superresolution-SuperResolutionSettingsDialog, reason: not valid java name */
    /* synthetic */ void m309x7cf0b2bc(View view) {
        LogUtil.i(TAG, " click positive_view ");
        saveData();
        this.mRestartAppWarningDialogCallBack.showRestartAppWarningDialog(this.mCurPkgName, this.mDataList.get(this.mItemAdapter.getSelectedItemPosition()).getDescription(), true);
        Settings.Global.putInt(getContext().getContentResolver(), "game_mode_floating_window_show", 0);
        dismiss();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.close_View) {
            return;
        }
        Settings.Global.putInt(getContext().getContentResolver(), "game_mode_floating_window_show", 0);
        dismiss();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.Dialog
    protected void onStop() {
        super.onStop();
    }

    public void showDialog() {
        Settings.Global.putInt(getContext().getContentResolver(), "game_mode_floating_window_show", 1);
        show();
    }
}
