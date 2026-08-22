package cn.nubia.gamelauncher.gamecontrolpanel.virtual;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.virtual.db.AppGameHandleItem;
import java.util.List;

/* loaded from: classes.dex */
public class VirtualTypeAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<AppGameHandleItem> mItemDatas;
    private ICustomizePositionListener mPositionListener;

    public interface ICustomizePositionListener {
        void onCustomizePosition(String str, int i, String str2);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;

        public ViewHolder(View view) {
            super(view);
        }
    }

    public VirtualTypeAdapter(Context context, List<AppGameHandleItem> list) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mItemDatas = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<AppGameHandleItem> list = this.mItemDatas;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.mTitle.setText(this.mItemDatas.get(i).getTitle());
        if (this.mItemDatas.get(i).getCurrentConfig() == 1) {
            viewHolder.mTitle.setSelected(true);
            viewHolder.mTitle.setAlpha(1.0f);
            this.mPositionListener.onCustomizePosition("visible", i, "");
        } else {
            viewHolder.mTitle.setSelected(false);
            viewHolder.mTitle.setAlpha(0.5f);
        }
        viewHolder.mTitle.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.virtual.VirtualTypeAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (VirtualTypeAdapter.this.mPositionListener != null) {
                    VirtualTypeAdapter.this.mPositionListener.onCustomizePosition("use", i, "");
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = this.mInflater.inflate(R.layout.virtual_type_recycler_item_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        viewHolder.mTitle = (TextView) inflate.findViewById(R.id.nubia_setting_customize_title);
        return viewHolder;
    }

    public void setOnPositionListener(ICustomizePositionListener iCustomizePositionListener) {
        this.mPositionListener = iCustomizePositionListener;
    }
}
