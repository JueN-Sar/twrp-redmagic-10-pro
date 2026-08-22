package cn.nubia.gamelauncher.adapter;

import android.content.pm.ShortcutInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.commoninterface.IOnAppAddedListener;
import cn.nubia.gamelauncher.commoninterface.OnSelectedCountChangeListener;
import cn.nubia.gamelauncher.controller.AppListController;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class HasAddAdapter extends RecyclerView.Adapter {
    private boolean isHostMode;
    String mAddedGameStr;
    private ArrayList<AppListItemBean> mList;
    String mNotAddedGameStr;
    private int mAddAppCount = -1;
    private int mType = 0;
    private IOnAppAddedListener mListener = null;
    private OnSelectedCountChangeListener mSelectCountListener = null;
    private TextView mSelectedTextView = null;
    private TextView mNotSelectedTextView = null;

    class HasAddViewHolder extends RecyclerView.ViewHolder {
        public ImageView appIcon;
        public TextView appName;
        private View bottomLine;
        public View container;
        public RelativeLayout itemBackColor;
        public TextView mJoinSwitch;
        public TextView mRemoveSwitch;
        public ImageView radioButton;
        public ImageView switchBtn;
        public TextView titleName;

        public HasAddViewHolder(View view) {
            super(view);
            this.container = view;
            this.itemBackColor = (RelativeLayout) view.findViewById(R.id.back_color);
            this.titleName = (TextView) view.findViewById(R.id.title_name);
            this.appIcon = (ImageView) view.findViewById(R.id.app_icon);
            this.appName = (TextView) view.findViewById(R.id.game_space_app_name);
            this.switchBtn = (ImageView) view.findViewById(R.id.app_switch);
            this.mJoinSwitch = (TextView) view.findViewById(R.id.join_switch);
            this.mRemoveSwitch = (TextView) view.findViewById(R.id.remove_switch);
            this.radioButton = (ImageView) view.findViewById(R.id.app_radio_btn);
            this.bottomLine = view.findViewById(R.id.bottom_line);
        }
    }

    private int getCheckedCount() {
        ArrayList<AppListItemBean> arrayList = this.mList;
        int i = 0;
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<AppListItemBean> it = this.mList.iterator();
            while (it.hasNext()) {
                if (it.next().isSelect()) {
                    i++;
                }
            }
        }
        return i;
    }

    private void updateTitleView() {
        TextView textView = this.mSelectedTextView;
        if (textView != null) {
            textView.setText(getCheckedCount() + this.mAddedGameStr);
        }
        TextView textView2 = this.mNotSelectedTextView;
        if (textView2 != null) {
            textView2.setText((this.mList.size() - getCheckedCount()) + this.mNotAddedGameStr);
        }
    }

    void doRadioBtnClick(ImageView imageView, int i) {
        this.mList.get(i).setSelect(!this.mList.get(i).isSelect());
        Log.i(AppListController.TAG, "doRadioBtnClick() bean == " + this.mList.get(i));
        imageView.setImageResource(this.mList.get(i).select ? R.mipmap.radio_select_button_press : R.mipmap.radio_select_button);
        OnSelectedCountChangeListener onSelectedCountChangeListener = this.mSelectCountListener;
        if (onSelectedCountChangeListener != null) {
            onSelectedCountChangeListener.onSelectedCountChangeListener(getCheckedCount());
        }
    }

    void doSwitchBtnClick(ImageView imageView, int i) {
        AppListItemBean appListItemBean = (AppListItemBean) imageView.getTag();
        Log.i(AppListController.TAG, "doSwitchBtnClick() bean == " + appListItemBean);
        IOnAppAddedListener iOnAppAddedListener = this.mListener;
        if (iOnAppAddedListener != null) {
            String componentName = appListItemBean.getComponentName();
            ShortcutInfo shortcutInfo = appListItemBean.getShortcutInfo();
            AppListItemBean appListItemBean2 = this.mList.get(i);
            boolean z = !this.mList.get(i).select;
            appListItemBean2.select = z;
            iOnAppAddedListener.onAppAddedCallback(componentName, shortcutInfo, z);
            imageView.setImageResource(this.mList.get(i).select ? R.mipmap.switch_button_press : R.mipmap.switch_button);
            updateTitleView();
        }
    }

    void doSwitchTextColorChange(HasAddViewHolder hasAddViewHolder, int i) {
        if (this.mList.get(i).select) {
            hasAddViewHolder.mRemoveSwitch.setAlpha(1.0f);
            hasAddViewHolder.mJoinSwitch.setAlpha(0.5f);
        } else {
            hasAddViewHolder.mRemoveSwitch.setAlpha(0.5f);
            hasAddViewHolder.mJoinSwitch.setAlpha(1.0f);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    public void isHostMode(boolean z) {
        this.isHostMode = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        int size;
        final HasAddViewHolder hasAddViewHolder = (HasAddViewHolder) viewHolder;
        if (this.mType == 0) {
            if (i == 0 || i == this.mAddAppCount) {
                hasAddViewHolder.titleName.setVisibility(0);
                this.mAddedGameStr = hasAddViewHolder.titleName.getContext().getString(R.string.added_games);
                this.mNotAddedGameStr = hasAddViewHolder.titleName.getContext().getString(R.string.not_added_games);
                StringBuilder sb = new StringBuilder();
                if (i != 0 || (size = this.mAddAppCount) <= 0) {
                    size = this.mList.size() - this.mAddAppCount;
                }
                hasAddViewHolder.titleName.setText(sb.append(size).append((i != 0 || this.mAddAppCount <= 0) ? this.mNotAddedGameStr : this.mAddedGameStr).toString());
                if (i == 0) {
                    this.mSelectedTextView = hasAddViewHolder.titleName;
                } else {
                    this.mNotSelectedTextView = hasAddViewHolder.titleName;
                }
            } else {
                hasAddViewHolder.titleName.setVisibility(8);
            }
            int i2 = this.mAddAppCount;
            if (i2 <= 0 || i != i2 - 1) {
                hasAddViewHolder.bottomLine.setVisibility(8);
            } else {
                hasAddViewHolder.bottomLine.setVisibility(0);
            }
            hasAddViewHolder.radioButton.setVisibility(8);
            hasAddViewHolder.switchBtn.setVisibility(0);
            hasAddViewHolder.switchBtn.setImageResource(this.mList.get(i).select ? R.mipmap.switch_button_press : R.mipmap.switch_button);
            doSwitchTextColorChange(hasAddViewHolder, i);
            hasAddViewHolder.switchBtn.setTag(this.mList.get(i));
        } else {
            hasAddViewHolder.radioButton.setVisibility(0);
            hasAddViewHolder.switchBtn.setVisibility(8);
            hasAddViewHolder.radioButton.setTag(this.mList.get(i));
            hasAddViewHolder.radioButton.setImageResource(this.mList.get(i).select ? R.mipmap.radio_select_button_press : R.mipmap.radio_select_button);
            hasAddViewHolder.radioButton.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.adapter.HasAddAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    HasAddAdapter.this.doRadioBtnClick((ImageView) view, i);
                }
            });
            if (i == this.mList.size() - 1) {
                hasAddViewHolder.bottomLine.setVisibility(0);
            } else {
                hasAddViewHolder.bottomLine.setVisibility(8);
            }
        }
        hasAddViewHolder.switchBtn.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.adapter.HasAddAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HasAddAdapter.this.doSwitchBtnClick((ImageView) view, i);
                HasAddAdapter.this.doSwitchTextColorChange(hasAddViewHolder, i);
            }
        });
        hasAddViewHolder.container.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.adapter.HasAddAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Log.i(AppListController.TAG, "hasAddViewHolder.container onClick position == " + i);
                if (HasAddAdapter.this.mType != 0) {
                    HasAddAdapter.this.doRadioBtnClick(hasAddViewHolder.radioButton, i);
                } else {
                    HasAddAdapter.this.doSwitchBtnClick(hasAddViewHolder.switchBtn, i);
                    HasAddAdapter.this.doSwitchTextColorChange(hasAddViewHolder, i);
                }
            }
        });
        if (this.isHostMode) {
            hasAddViewHolder.container.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: cn.nubia.gamelauncher.adapter.HasAddAdapter.4
                @Override // android.view.View.OnFocusChangeListener
                public void onFocusChange(View view, boolean z) {
                    if (z) {
                        hasAddViewHolder.itemBackColor.setBackgroundResource(R.color.host_addapp_item_focus);
                    } else {
                        hasAddViewHolder.itemBackColor.setBackgroundResource(R.color.host_addapp_item_no_focus);
                    }
                }
            });
        }
        hasAddViewHolder.appName.setText(this.mList.get(i).name);
        hasAddViewHolder.appIcon.setImageBitmap(this.mList.get(i).getIcon());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new HasAddViewHolder(this.isHostMode ? LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.host_app_add_item_layout, viewGroup, false) : LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.app_add_item_layout, viewGroup, false));
    }

    public void setDataList(ArrayList<AppListItemBean> arrayList) {
        this.mList = arrayList;
        if (this.mType != 1 || arrayList == null || arrayList.isEmpty()) {
            return;
        }
        Iterator<AppListItemBean> it = this.mList.iterator();
        while (it.hasNext()) {
            it.next().setSelect(false);
        }
    }

    public void setHasAddCount(int i) {
        this.mAddAppCount = i;
    }

    public void setOnAppAddedListener(IOnAppAddedListener iOnAppAddedListener) {
        this.mListener = iOnAppAddedListener;
    }

    public void setOnSelectCountChangeListener(OnSelectedCountChangeListener onSelectedCountChangeListener) {
        this.mSelectCountListener = onSelectedCountChangeListener;
    }

    public void setType(int i) {
        this.mType = i;
    }
}
