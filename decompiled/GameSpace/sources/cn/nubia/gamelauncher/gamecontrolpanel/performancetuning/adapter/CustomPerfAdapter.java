package cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.CustomPerfProfileManager;
import cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.CustomPerfAdapter;
import cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.ui.CustomPerfDialog;
import cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.ui.CustomPerfSeekBar;
import cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.ui.SelectorListDialog;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import cn.nubia.gamelauncher.util.ToastUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class CustomPerfAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_BOTTOM_BG = 2131231046;
    private static final int ITEM_CENTER_BG = 2131231047;
    private static final String PAYLOAD_HEADER_RESET = "headerReset";
    private static final String PAYLOAD_SELECTED_NAME = "selectorName";
    private static final String TAG = "CustomPerfAdapter";
    private final Context mContext;
    private List<AdapterItem> mItemList;
    private final LayoutInflater mLayoutInflater;

    private static class BottomViewHolder extends RecyclerView.ViewHolder {
        public BottomViewHolder(View view) {
            super(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class HeaderHolder extends RecyclerView.ViewHolder {
        private final ImageView mBtnDelete;
        private final ImageView mBtnDetailHelp;
        private final ImageView mBtnEdit;
        private final ImageView mBtnReset;
        private final ImageView mBtnSave;
        private final ConstraintLayout mBtnSelectPlan;
        private final ImageView mIvSelectPlan;
        private final TextView mTvSelectName;

        public HeaderHolder(View view) {
            super(view);
            this.mBtnDetailHelp = (ImageView) view.findViewById(R.id.detail_help_bt);
            this.mBtnEdit = (ImageView) view.findViewById(R.id.edit_custom_parameter_bt);
            this.mBtnDelete = (ImageView) view.findViewById(R.id.delete_custom_parameter_bt);
            this.mBtnSave = (ImageView) view.findViewById(R.id.save_as_custom_parameter_bt);
            this.mBtnReset = (ImageView) view.findViewById(R.id.reset_custom_parameter_bt);
            this.mBtnSelectPlan = (ConstraintLayout) view.findViewById(R.id.btn_plan_selector);
            this.mTvSelectName = (TextView) view.findViewById(R.id.tv_plan_selector);
            this.mIvSelectPlan = (ImageView) view.findViewById(R.id.iv_plan_selector);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class SeekbarHolder extends RecyclerView.ViewHolder {
        private final ImageView mBtnRest;
        private final CustomPerfSeekBar mSeekBar;
        private final TextView mTvCategoryName;

        public SeekbarHolder(View view) {
            super(view);
            this.mTvCategoryName = (TextView) view.findViewById(R.id.category_view);
            this.mBtnRest = (ImageView) view.findViewById(R.id.reset_bt);
            this.mSeekBar = (CustomPerfSeekBar) view.findViewById(R.id.seek_bar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void changeResetVisible(String str, String str2, String str3, String str4) {
            if (TextUtils.equals(str, str2) && TextUtils.equals(str3, str4)) {
                this.mBtnRest.setVisibility(8);
            } else {
                this.mBtnRest.setVisibility(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class SelectorHolder extends RecyclerView.ViewHolder {
        private final ImageView mBtnReset;
        private final ConstraintLayout mBtnSelect;
        private final ImageView mIvSelect;
        private final TextView mTvCategoryName;
        private final TextView mTvSelectValue;

        public SelectorHolder(View view) {
            super(view);
            this.mTvCategoryName = (TextView) view.findViewById(R.id.category_view);
            this.mBtnReset = (ImageView) view.findViewById(R.id.reset_bt);
            this.mBtnSelect = (ConstraintLayout) view.findViewById(R.id.btn_item_selector);
            this.mTvSelectValue = (TextView) view.findViewById(R.id.tv_item_selector);
            this.mIvSelect = (ImageView) view.findViewById(R.id.iv_item_selector);
        }

        public void changeResetVisible(String str, String str2) {
            if (TextUtils.equals(str, str2)) {
                this.mBtnReset.setVisibility(8);
            } else {
                this.mBtnReset.setVisibility(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class SwitchHolder extends RecyclerView.ViewHolder {
        public ImageView mBtnReset;
        public ImageView mBtnSwitch;
        public View mSegmentLine;
        public TextView mTvName;

        public SwitchHolder(View view) {
            super(view);
            this.mTvName = (TextView) view.findViewById(R.id.category_view);
            this.mBtnReset = (ImageView) view.findViewById(R.id.reset_bt);
            this.mBtnSwitch = (ImageView) view.findViewById(R.id.switch_bt);
            this.mSegmentLine = view.findViewById(R.id.segment_line);
        }

        public void changeResetVisible(String str, String str2) {
            if (TextUtils.equals(str, str2)) {
                this.mBtnReset.setVisibility(8);
            } else {
                this.mBtnReset.setVisibility(0);
            }
        }

        public void changeSwitch(boolean z) {
            if (z) {
                this.mBtnSwitch.setImageResource(R.drawable.function_toggle_on);
            } else {
                this.mBtnSwitch.setImageResource(R.drawable.function_toggle_off);
            }
        }
    }

    private static class TitleHolder extends RecyclerView.ViewHolder {
        public TextView mTitleView;

        public TitleHolder(View view) {
            super(view);
            this.mTitleView = (TextView) view.findViewById(R.id.group_title_view);
        }
    }

    public CustomPerfAdapter(Context context, List<AdapterItem> list) {
        this.mContext = context;
        this.mItemList = list;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    private void bindHeaderHolder(HeaderHolder headerHolder, int i, AdapterHeadItem adapterHeadItem, List<Object> list) {
        if (list.contains(PAYLOAD_HEADER_RESET)) {
            headerHolder.mBtnReset.setEnabled(adapterHeadItem.mSupportReset);
        }
    }

    private void bindHeaderHolder(final HeaderHolder headerHolder, AdapterItem adapterItem) {
        final AdapterHeadItem adapterHeadItem = (AdapterHeadItem) adapterItem;
        if (CustomPerfProfileManager.getInstance().isNormalProfile(adapterHeadItem.mValue)) {
            headerHolder.mBtnEdit.setEnabled(false);
            headerHolder.mBtnDelete.setEnabled(false);
        } else {
            headerHolder.mBtnEdit.setEnabled(true);
            headerHolder.mBtnDelete.setEnabled(true);
        }
        headerHolder.mBtnReset.setEnabled(adapterHeadItem.mSupportReset);
        headerHolder.mTvSelectName.setText(adapterHeadItem.mName);
        headerHolder.mBtnDetailHelp.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.CustomPerfAdapter$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomPerfAdapter.this.m283x80374f9f(view);
            }
        });
        headerHolder.mBtnSelectPlan.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.CustomPerfAdapter$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomPerfAdapter.this.m284xc836adfe(headerHolder, adapterHeadItem, view);
            }
        });
        headerHolder.mBtnEdit.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.CustomPerfAdapter$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomPerfAdapter.this.m285x10360c5d(adapterHeadItem, view);
            }
        });
        headerHolder.mBtnDelete.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.CustomPerfAdapter$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomPerfAdapter.this.m286x58356abc(adapterHeadItem, view);
            }
        });
        headerHolder.mBtnSave.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.CustomPerfAdapter$$ExternalSyntheticLambda15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomPerfAdapter.this.m287xa034c91b(adapterHeadItem, view);
            }
        });
        headerHolder.mBtnReset.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.CustomPerfAdapter$$ExternalSyntheticLambda16
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomPerfAdapter.this.m288xe834277a(adapterHeadItem, view);
            }
        });
    }

    private void bindSeekbarHolder(final SeekbarHolder seekbarHolder, final AdapterItem adapterItem) {
        seekbarHolder.mTvCategoryName.setText(adapterItem.mName);
        seekbarHolder.mSeekBar.setDataList(adapterItem.mValueList);
        seekbarHolder.mSeekBar.selectValue(adapterItem.mValue, adapterItem.mValue2);
        seekbarHolder.mSeekBar.supportSelectScope(adapterItem.mIsScoped);
        seekbarHolder.changeResetVisible(adapterItem.mNormalValue, adapterItem.mValue, adapterItem.mNormalValue2, adapterItem.mValue2);
        seekbarHolder.mSeekBar.setChangeListener(new CustomPerfSeekBar.ChangeListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.CustomPerfAdapter$$ExternalSyntheticLambda3
            @Override // cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.ui.CustomPerfSeekBar.ChangeListener
            public final void onChange(String str, String str2) {
                CustomPerfAdapter.this.m289xd9b3a764(adapterItem, seekbarHolder, str, str2);
            }
        });
        seekbarHolder.mBtnRest.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.CustomPerfAdapter$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomPerfAdapter.this.m290x21b305c3(adapterItem, seekbarHolder, view);
            }
        });
        if (adapterItem.mGroupLastItem) {
            seekbarHolder.itemView.setBackgroundResource(R.drawable.custom_perf_item_bottom_bg);
        } else {
            seekbarHolder.itemView.setBackgroundResource(R.drawable.custom_perf_item_center_bg);
        }
    }

    private void bindSelectorHolder(final SelectorHolder selectorHolder, final int i, final AdapterItem adapterItem) {
        selectorHolder.mTvCategoryName.setText(adapterItem.mName);
        selectorHolder.mTvSelectValue.setText(adapterItem.mValue);
        selectorHolder.changeResetVisible(adapterItem.mNormalValue, adapterItem.mValue);
        selectorHolder.mBtnSelect.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.CustomPerfAdapter$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomPerfAdapter.this.m291x37fbbeda(selectorHolder, i, adapterItem, view);
            }
        });
        selectorHolder.mBtnReset.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.CustomPerfAdapter$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomPerfAdapter.this.m292x7ffb1d39(adapterItem, selectorHolder, view);
            }
        });
        if (adapterItem.mGroupLastItem) {
            selectorHolder.itemView.setBackgroundResource(R.drawable.custom_perf_item_bottom_bg);
        } else {
            selectorHolder.itemView.setBackgroundResource(R.drawable.custom_perf_item_center_bg);
        }
    }

    private void bindSelectorHolder(SelectorHolder selectorHolder, int i, AdapterItem adapterItem, List<Object> list) {
        if (list.contains(PAYLOAD_SELECTED_NAME)) {
            selectorHolder.mTvSelectValue.setText(adapterItem.mValue);
        }
    }

    private void bindSwitchHolder(final SwitchHolder switchHolder, final AdapterItem adapterItem) {
        switchHolder.mTvName.setText(adapterItem.mName);
        switchHolder.changeSwitch(TextUtils.equals(adapterItem.mValue, "1"));
        switchHolder.changeResetVisible(adapterItem.mNormalValue, adapterItem.mValue);
        switchHolder.mBtnSwitch.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.CustomPerfAdapter$$ExternalSyntheticLambda17
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomPerfAdapter.this.m293x1bc0fdb3(adapterItem, switchHolder, view);
            }
        });
        switchHolder.mBtnReset.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.CustomPerfAdapter$$ExternalSyntheticLambda18
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomPerfAdapter.this.m294x63c05c12(adapterItem, switchHolder, view);
            }
        });
        if (adapterItem.mGroupLastItem) {
            switchHolder.itemView.setBackgroundResource(R.drawable.custom_perf_item_bottom_bg);
            switchHolder.mSegmentLine.setVisibility(8);
        } else {
            switchHolder.itemView.setBackgroundResource(R.drawable.custom_perf_item_center_bg);
            switchHolder.mSegmentLine.setVisibility(0);
        }
    }

    private void bindTitleHolder(TitleHolder titleHolder, AdapterItem adapterItem) {
        titleHolder.mTitleView.setText(adapterItem.mName);
    }

    private void checkSupportReset() {
        boolean z;
        Iterator<AdapterItem> it = this.mItemList.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            AdapterItem next = it.next();
            z = true;
            if (next.mSettingType != 1 && next.mSettingType != 2 && next.mSettingType != 6 && (!TextUtils.equals(next.mNormalValue, next.mValue) || !TextUtils.equals(next.mNormalValue2, next.mValue2))) {
                break;
            }
        }
        AdapterItem adapterItem = this.mItemList.get(0);
        if (adapterItem instanceof AdapterHeadItem) {
            AdapterHeadItem adapterHeadItem = (AdapterHeadItem) adapterItem;
            if (z != adapterHeadItem.mSupportReset) {
                adapterHeadItem.mSupportReset = z;
                notifyItemChanged(0, PAYLOAD_HEADER_RESET);
            }
        }
    }

    private void debugDataList() {
        Iterator<AdapterItem> it = this.mItemList.iterator();
        while (it.hasNext()) {
            LogUtil.i(TAG, "adapterItem = " + it.next());
        }
    }

    private void showDeleteDialog(final AdapterItem adapterItem) {
        CustomPerfDialog.getInstance().showDeleteDialog(this.mContext, new CustomPerfDialog.ConfirmListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.CustomPerfAdapter$$ExternalSyntheticLambda19
            @Override // cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.ui.CustomPerfDialog.ConfirmListener
            public final void onConfirm(String str) {
                CustomPerfAdapter.this.m295x672bd63c(adapterItem, str);
            }
        });
    }

    private void showPlanSelectorDialog(final HeaderHolder headerHolder, AdapterItem adapterItem) {
        if (adapterItem.mValueList == null) {
            return;
        }
        Resources resources = this.mContext.getResources();
        SelectorListDialog selectorListDialog = new SelectorListDialog(this.mContext);
        selectorListDialog.setTextViewLayout(R.layout.custom_perf_dialog_plan_selector_item);
        selectorListDialog.setBackground(R.drawable.custom_perf_plan_selector_bg);
        selectorListDialog.setNormalTextColor(R.color.custom_perf_plan_selector_normal_color);
        selectorListDialog.setSelectedTextColor(R.color.custom_perf_plan_selector_selected_color);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < adapterItem.mValueList.size(); i++) {
            SelectorListDialog.SelectorItem selectorItem = new SelectorListDialog.SelectorItem();
            if (TextUtils.equals(adapterItem.mName, adapterItem.mValueList.get(i))) {
                selectorItem.mSelected = true;
            }
            selectorItem.mText = adapterItem.mValueList.get(i);
            arrayList.add(selectorItem);
        }
        selectorListDialog.setDataList(arrayList);
        selectorListDialog.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.CustomPerfAdapter$$ExternalSyntheticLambda5
            @Override // android.widget.PopupWindow.OnDismissListener
            public final void onDismiss() {
                CustomPerfAdapter.HeaderHolder.this.mIvSelectPlan.setImageResource(R.drawable.tgk_case_show_item_down);
            }
        });
        selectorListDialog.setSelectItemListener(new SelectorListDialog.SelectItemListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.CustomPerfAdapter$$ExternalSyntheticLambda6
            @Override // cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.ui.SelectorListDialog.SelectItemListener
            public final void onSelectedItem(String str) {
                CustomPerfAdapter.this.m296x15e95261(str);
            }
        });
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.custom_mode_plan_selector_item_height) * (arrayList.size() + 1);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.custom_mode_plan_selector_list_height);
        if (dimensionPixelSize > dimensionPixelSize2) {
            dimensionPixelSize = dimensionPixelSize2;
        }
        selectorListDialog.showPopupWindow(headerHolder.mBtnSelectPlan, dimensionPixelSize);
        headerHolder.mIvSelectPlan.setImageResource(R.drawable.tgk_case_show_item_up);
    }

    private void showRenameDialog(final AdapterItem adapterItem) {
        CustomPerfDialog.getInstance().showRenameDialog(this.mContext, adapterItem.mName, adapterItem.mValueList, new CustomPerfDialog.ConfirmListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.CustomPerfAdapter$$ExternalSyntheticLambda0
            @Override // cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.ui.CustomPerfDialog.ConfirmListener
            public final void onConfirm(String str) {
                CustomPerfAdapter.this.m297x850d7a6a(adapterItem, str);
            }
        });
    }

    private void showResetDialog(final AdapterItem adapterItem) {
        CustomPerfDialog.getInstance().showResetDialog(this.mContext, new CustomPerfDialog.ConfirmListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.CustomPerfAdapter$$ExternalSyntheticLambda10
            @Override // cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.ui.CustomPerfDialog.ConfirmListener
            public final void onConfirm(String str) {
                CustomPerfAdapter.this.m298xb1c33e57(adapterItem, str);
            }
        });
    }

    private void showSaveAsDialog(final AdapterItem adapterItem) {
        final CustomPerfProfileManager customPerfProfileManager = CustomPerfProfileManager.getInstance();
        final int idleSerial = customPerfProfileManager.getIdleSerial();
        if (idleSerial == -1) {
            ToastUtil.showGamemodeToast(this.mContext.getString(R.string.custom_perf_save_as_max));
        } else {
            CustomPerfDialog.getInstance().showSaveAsDialog(this.mContext, customPerfProfileManager.copyNewName(adapterItem.mName), adapterItem.mValueList, new CustomPerfDialog.ConfirmListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.CustomPerfAdapter$$ExternalSyntheticLambda9
                @Override // cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.ui.CustomPerfDialog.ConfirmListener
                public final void onConfirm(String str) {
                    CustomPerfAdapter.this.m299xff3c32c(adapterItem, idleSerial, customPerfProfileManager, str);
                }
            });
        }
    }

    private void showSettingItemSelector(final SelectorHolder selectorHolder, final int i, final AdapterItem adapterItem) {
        if (adapterItem.mValueList == null) {
            return;
        }
        Resources resources = this.mContext.getResources();
        SelectorListDialog selectorListDialog = new SelectorListDialog(this.mContext);
        selectorListDialog.setTextViewLayout(R.layout.custom_perf_dialog_selector_item);
        selectorListDialog.setBackground(R.drawable.custom_perf_item_selector_bg);
        selectorListDialog.setNormalTextColor(R.color.custom_perf_item_selector_normal_color);
        selectorListDialog.setSelectedTextColor(R.color.custom_perf_item_selector_selected_color);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < adapterItem.mValueList.size(); i2++) {
            SelectorListDialog.SelectorItem selectorItem = new SelectorListDialog.SelectorItem();
            if (TextUtils.equals(adapterItem.mValueList.get(i2), adapterItem.mValue)) {
                selectorItem.mSelected = true;
            }
            selectorItem.mText = adapterItem.mValueList.get(i2);
            arrayList.add(selectorItem);
        }
        selectorListDialog.setDataList(arrayList);
        selectorListDialog.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.CustomPerfAdapter$$ExternalSyntheticLambda1
            @Override // android.widget.PopupWindow.OnDismissListener
            public final void onDismiss() {
                CustomPerfAdapter.SelectorHolder.this.mIvSelect.setImageResource(R.drawable.tgk_case_show_item_down);
            }
        });
        selectorListDialog.setSelectItemListener(new SelectorListDialog.SelectItemListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.CustomPerfAdapter$$ExternalSyntheticLambda2
            @Override // cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.ui.SelectorListDialog.SelectItemListener
            public final void onSelectedItem(String str) {
                CustomPerfAdapter.this.m300xa33bd41a(adapterItem, selectorHolder, i, str);
            }
        });
        selectorListDialog.showPopupWindow(selectorHolder.mBtnSelect, resources.getDimensionPixelSize(R.dimen.custom_mode_plan_selector_list_height));
        selectorHolder.mIvSelect.setImageResource(R.drawable.tgk_case_show_item_up);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mItemList.size();
    }

    public List<AdapterItem> getItemList() {
        return this.mItemList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.mItemList.get(i).mSettingType;
    }

    /* renamed from: lambda$bindHeaderHolder$0$cn-nubia-gamelauncher-gamecontrolpanel-performancetuning-adapter-CustomPerfAdapter, reason: not valid java name */
    /* synthetic */ void m283x80374f9f(View view) {
        CustomPerfDialog.getInstance().showHelpDetailDialog(this.mContext);
    }

    /* renamed from: lambda$bindHeaderHolder$1$cn-nubia-gamelauncher-gamecontrolpanel-performancetuning-adapter-CustomPerfAdapter, reason: not valid java name */
    /* synthetic */ void m284xc836adfe(HeaderHolder headerHolder, AdapterHeadItem adapterHeadItem, View view) {
        showPlanSelectorDialog(headerHolder, adapterHeadItem);
    }

    /* renamed from: lambda$bindHeaderHolder$2$cn-nubia-gamelauncher-gamecontrolpanel-performancetuning-adapter-CustomPerfAdapter, reason: not valid java name */
    /* synthetic */ void m285x10360c5d(AdapterHeadItem adapterHeadItem, View view) {
        showRenameDialog(adapterHeadItem);
    }

    /* renamed from: lambda$bindHeaderHolder$3$cn-nubia-gamelauncher-gamecontrolpanel-performancetuning-adapter-CustomPerfAdapter, reason: not valid java name */
    /* synthetic */ void m286x58356abc(AdapterHeadItem adapterHeadItem, View view) {
        showDeleteDialog(adapterHeadItem);
    }

    /* renamed from: lambda$bindHeaderHolder$4$cn-nubia-gamelauncher-gamecontrolpanel-performancetuning-adapter-CustomPerfAdapter, reason: not valid java name */
    /* synthetic */ void m287xa034c91b(AdapterHeadItem adapterHeadItem, View view) {
        showSaveAsDialog(adapterHeadItem);
    }

    /* renamed from: lambda$bindHeaderHolder$5$cn-nubia-gamelauncher-gamecontrolpanel-performancetuning-adapter-CustomPerfAdapter, reason: not valid java name */
    /* synthetic */ void m288xe834277a(AdapterHeadItem adapterHeadItem, View view) {
        showResetDialog(adapterHeadItem);
    }

    /* renamed from: lambda$bindSeekbarHolder$14$cn-nubia-gamelauncher-gamecontrolpanel-performancetuning-adapter-CustomPerfAdapter, reason: not valid java name */
    /* synthetic */ void m289xd9b3a764(AdapterItem adapterItem, SeekbarHolder seekbarHolder, String str, String str2) {
        adapterItem.mValue = str;
        adapterItem.mValue2 = str2;
        seekbarHolder.changeResetVisible(adapterItem.mNormalValue, adapterItem.mValue, adapterItem.mNormalValue2, adapterItem.mValue2);
        checkSupportReset();
    }

    /* renamed from: lambda$bindSeekbarHolder$15$cn-nubia-gamelauncher-gamecontrolpanel-performancetuning-adapter-CustomPerfAdapter, reason: not valid java name */
    /* synthetic */ void m290x21b305c3(AdapterItem adapterItem, SeekbarHolder seekbarHolder, View view) {
        adapterItem.mValue = adapterItem.mNormalValue;
        adapterItem.mValue2 = adapterItem.mNormalValue2;
        seekbarHolder.mSeekBar.selectValue(adapterItem.mValue, adapterItem.mValue2);
        seekbarHolder.mBtnRest.setVisibility(8);
        checkSupportReset();
    }

    /* renamed from: lambda$bindSelectorHolder$16$cn-nubia-gamelauncher-gamecontrolpanel-performancetuning-adapter-CustomPerfAdapter, reason: not valid java name */
    /* synthetic */ void m291x37fbbeda(SelectorHolder selectorHolder, int i, AdapterItem adapterItem, View view) {
        showSettingItemSelector(selectorHolder, i, adapterItem);
    }

    /* renamed from: lambda$bindSelectorHolder$17$cn-nubia-gamelauncher-gamecontrolpanel-performancetuning-adapter-CustomPerfAdapter, reason: not valid java name */
    /* synthetic */ void m292x7ffb1d39(AdapterItem adapterItem, SelectorHolder selectorHolder, View view) {
        adapterItem.mValue = adapterItem.mNormalValue;
        selectorHolder.mTvSelectValue.setText(adapterItem.mValue);
        selectorHolder.mBtnReset.setVisibility(8);
        checkSupportReset();
    }

    /* renamed from: lambda$bindSwitchHolder$12$cn-nubia-gamelauncher-gamecontrolpanel-performancetuning-adapter-CustomPerfAdapter, reason: not valid java name */
    /* synthetic */ void m293x1bc0fdb3(AdapterItem adapterItem, SwitchHolder switchHolder, View view) {
        if (TextUtils.equals(adapterItem.mValue, "1")) {
            adapterItem.mValue = "0";
        } else {
            adapterItem.mValue = "1";
        }
        switchHolder.changeSwitch(TextUtils.equals(adapterItem.mValue, "1"));
        switchHolder.changeResetVisible(adapterItem.mNormalValue, adapterItem.mValue);
        checkSupportReset();
    }

    /* renamed from: lambda$bindSwitchHolder$13$cn-nubia-gamelauncher-gamecontrolpanel-performancetuning-adapter-CustomPerfAdapter, reason: not valid java name */
    /* synthetic */ void m294x63c05c12(AdapterItem adapterItem, SwitchHolder switchHolder, View view) {
        adapterItem.mValue = adapterItem.mNormalValue;
        switchHolder.changeSwitch(TextUtils.equals(adapterItem.mValue, "1"));
        switchHolder.mBtnReset.setVisibility(8);
        checkSupportReset();
    }

    /* renamed from: lambda$showDeleteDialog$9$cn-nubia-gamelauncher-gamecontrolpanel-performancetuning-adapter-CustomPerfAdapter, reason: not valid java name */
    /* synthetic */ void m295x672bd63c(AdapterItem adapterItem, String str) {
        CustomPerfProfileManager customPerfProfileManager = CustomPerfProfileManager.getInstance();
        customPerfProfileManager.deleteProfile(Integer.parseInt(adapterItem.mValue));
        customPerfProfileManager.applyProfile(1);
        updateItemList(customPerfProfileManager.convert2AdapterItem(1));
    }

    /* renamed from: lambda$showPlanSelectorDialog$7$cn-nubia-gamelauncher-gamecontrolpanel-performancetuning-adapter-CustomPerfAdapter, reason: not valid java name */
    /* synthetic */ void m296x15e95261(String str) {
        CustomPerfProfileManager customPerfProfileManager = CustomPerfProfileManager.getInstance();
        customPerfProfileManager.saveProfile(customPerfProfileManager.convert2Profile(this.mItemList));
        int serialByName = customPerfProfileManager.getSerialByName(str);
        updateItemList(customPerfProfileManager.convert2AdapterItem(serialByName));
        customPerfProfileManager.applyProfile(serialByName);
    }

    /* renamed from: lambda$showRenameDialog$8$cn-nubia-gamelauncher-gamecontrolpanel-performancetuning-adapter-CustomPerfAdapter, reason: not valid java name */
    /* synthetic */ void m297x850d7a6a(AdapterItem adapterItem, String str) {
        CustomPerfProfileManager customPerfProfileManager = CustomPerfProfileManager.getInstance();
        int indexOf = adapterItem.mValueList.indexOf(adapterItem.mName);
        adapterItem.mValueList.remove(adapterItem.mName);
        adapterItem.mValueList.add(indexOf, str);
        adapterItem.mName = str;
        notifyItemChanged(0);
        customPerfProfileManager.renameProfile(Integer.parseInt(adapterItem.mValue), str);
    }

    /* renamed from: lambda$showResetDialog$11$cn-nubia-gamelauncher-gamecontrolpanel-performancetuning-adapter-CustomPerfAdapter, reason: not valid java name */
    /* synthetic */ void m298xb1c33e57(AdapterItem adapterItem, String str) {
        CustomPerfProfileManager customPerfProfileManager = CustomPerfProfileManager.getInstance();
        int parseInt = Integer.parseInt(adapterItem.mValue);
        customPerfProfileManager.resetProfile(parseInt);
        updateItemList(customPerfProfileManager.convert2AdapterItem(parseInt));
    }

    /* renamed from: lambda$showSaveAsDialog$10$cn-nubia-gamelauncher-gamecontrolpanel-performancetuning-adapter-CustomPerfAdapter, reason: not valid java name */
    /* synthetic */ void m299xff3c32c(AdapterItem adapterItem, int i, CustomPerfProfileManager customPerfProfileManager, String str) {
        adapterItem.mName = str;
        adapterItem.mValue = String.valueOf(i);
        adapterItem.mValueList.add(str);
        notifyItemChanged(0);
        customPerfProfileManager.saveProfile(customPerfProfileManager.convert2Profile(this.mItemList));
    }

    /* renamed from: lambda$showSettingItemSelector$19$cn-nubia-gamelauncher-gamecontrolpanel-performancetuning-adapter-CustomPerfAdapter, reason: not valid java name */
    /* synthetic */ void m300xa33bd41a(AdapterItem adapterItem, SelectorHolder selectorHolder, int i, String str) {
        adapterItem.mValue = str;
        selectorHolder.changeResetVisible(adapterItem.mNormalValue, adapterItem.mValue);
        checkSupportReset();
        notifyItemChanged(i, PAYLOAD_SELECTED_NAME);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AdapterItem adapterItem = this.mItemList.get(i);
        if (viewHolder instanceof HeaderHolder) {
            bindHeaderHolder((HeaderHolder) viewHolder, adapterItem);
            return;
        }
        if (viewHolder instanceof TitleHolder) {
            bindTitleHolder((TitleHolder) viewHolder, adapterItem);
            return;
        }
        if (viewHolder instanceof SwitchHolder) {
            bindSwitchHolder((SwitchHolder) viewHolder, adapterItem);
        } else if (viewHolder instanceof SeekbarHolder) {
            bindSeekbarHolder((SeekbarHolder) viewHolder, adapterItem);
        } else if (viewHolder instanceof SelectorHolder) {
            bindSelectorHolder((SelectorHolder) viewHolder, i, adapterItem);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List<Object> list) {
        if (list.isEmpty()) {
            super.onBindViewHolder(viewHolder, i, list);
            return;
        }
        AdapterItem adapterItem = this.mItemList.get(i);
        if (viewHolder instanceof HeaderHolder) {
            bindHeaderHolder((HeaderHolder) viewHolder, i, (AdapterHeadItem) adapterItem, list);
        } else if (viewHolder instanceof SelectorHolder) {
            bindSelectorHolder((SelectorHolder) viewHolder, i, adapterItem, list);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new HeaderHolder(this.mLayoutInflater.inflate(R.layout.custom_perf_header_layout, viewGroup, false));
        }
        if (i == 2) {
            return new TitleHolder(this.mLayoutInflater.inflate(R.layout.custom_perf_group_title_layout, viewGroup, false));
        }
        if (i == 3) {
            return new SwitchHolder(this.mLayoutInflater.inflate(R.layout.custom_perf_switch_layout, viewGroup, false));
        }
        if (i == 4) {
            return new SeekbarHolder(this.mLayoutInflater.inflate(R.layout.custom_perf_seekbar_layout, viewGroup, false));
        }
        if (i == 5) {
            return new SelectorHolder(this.mLayoutInflater.inflate(R.layout.custom_perf_selector_layout, viewGroup, false));
        }
        if (i == 6) {
            return new BottomViewHolder(this.mLayoutInflater.inflate(R.layout.custom_perf_bottom_layout, viewGroup, false));
        }
        return null;
    }

    public void updateItemList(List<AdapterItem> list) {
        this.mItemList = list;
        notifyDataSetChanged();
    }
}
