package cn.nubia.gamelauncher.gamecontrolpanel.superresolution;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ItemAdapter extends RecyclerView.Adapter {
    private static final String TAG = "ItemAdapter";
    private Context mContext;
    private ArrayList<ItemData> mDataList;
    private int selectedItemPosition = 0;

    class MyHolder extends RecyclerView.ViewHolder {
        private CheckBox mCheckBox;
        private TextView mDescriptionView;

        public MyHolder(View view) {
            super(view);
            this.mCheckBox = (CheckBox) view.findViewById(R.id.select_box);
            this.mDescriptionView = (TextView) view.findViewById(R.id.description_view);
        }
    }

    public ItemAdapter(Context context) {
        this.mContext = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<ItemData> arrayList = this.mDataList;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public int getSelectedItemPosition() {
        return this.selectedItemPosition;
    }

    /* renamed from: lambda$onBindViewHolder$0$cn-nubia-gamelauncher-gamecontrolpanel-superresolution-ItemAdapter, reason: not valid java name */
    /* synthetic */ void m301x3bb1872f(int i, ItemData itemData, View view) {
        LogUtil.i(TAG, " setOnClickListener -- selectedItemPosition = " + this.selectedItemPosition + " ;; position = " + i);
        if (this.selectedItemPosition != i) {
            itemData.setSelected(true);
            this.mDataList.get(this.selectedItemPosition).setSelected(false);
            notifyItemChanged(i);
            notifyItemChanged(this.selectedItemPosition);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        viewHolder.itemView.getLayoutParams();
        if (viewHolder instanceof MyHolder) {
            final ItemData itemData = this.mDataList.get(i);
            MyHolder myHolder = (MyHolder) viewHolder;
            myHolder.mCheckBox.setOnClickListener(null);
            myHolder.mCheckBox.setChecked(this.mDataList.get(i).isSelected());
            if (this.mDataList.get(i).isSelected()) {
                this.selectedItemPosition = i;
                myHolder.mCheckBox.setEnabled(false);
            } else {
                myHolder.mCheckBox.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.superresolution.ItemAdapter$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ItemAdapter.this.m301x3bb1872f(i, itemData, view);
                    }
                });
            }
            myHolder.mDescriptionView.setText(this.mDataList.get(i).getDescription());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(this.mContext).inflate(R.layout.super_resolution_settings_item_layout, (ViewGroup) null));
    }

    public void setDataList(ArrayList<ItemData> arrayList) {
        this.mDataList = arrayList;
    }
}
