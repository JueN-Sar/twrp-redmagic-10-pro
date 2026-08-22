package cn.nubia.gamecenter.settings.barrageMessage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gamecenter.settings.R;
import java.util.List;

/* loaded from: classes.dex */
public class BarrageMessageSourceAdapter extends RecyclerView.Adapter<SourceViewHolder> {
    private Context mContext;
    private List<AppBean> mInfoList;
    OnItemClickListener mListener;
    PackageManager mPackageManager;

    public interface OnItemClickListener {
        void onItemClick(View view, int i);
    }

    class SourceViewHolder extends RecyclerView.ViewHolder {
        private ImageView mCheckView;
        private View mItemView;
        private TextView mLabelView;

        public SourceViewHolder(View view) {
            super(view);
            this.mItemView = view.findViewById(R.id.item_root);
            this.mLabelView = (TextView) view.findViewById(R.id.label);
            this.mCheckView = (ImageView) view.findViewById(R.id.checkbox);
            this.mItemView.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.barrageMessage.BarrageMessageSourceAdapter.SourceViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (BarrageMessageSourceAdapter.this.mListener != null) {
                        boolean z = !((Boolean) SourceViewHolder.this.mItemView.getTag()).booleanValue();
                        int i = z ? R.drawable.function_toggle_on : R.drawable.function_toggle_off;
                        SourceViewHolder.this.mItemView.setTag(Boolean.valueOf(z));
                        SourceViewHolder.this.mCheckView.setImageResource(i);
                        BarrageMessageSourceAdapter.this.mListener.onItemClick(view2, SourceViewHolder.this.getLayoutPosition());
                    }
                }
            });
        }
    }

    public BarrageMessageSourceAdapter(Context context, List<AppBean> list) {
        this.mInfoList = list;
        this.mContext = context;
        this.mPackageManager = context.getPackageManager();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mInfoList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(SourceViewHolder sourceViewHolder, int i) {
        AppBean appBean = this.mInfoList.get(i);
        sourceViewHolder.mLabelView.setText(appBean.getLabel());
        int i2 = appBean.isChecked() ? R.drawable.function_toggle_on : R.drawable.function_toggle_off;
        sourceViewHolder.mItemView.setTag(Boolean.valueOf(appBean.isChecked()));
        sourceViewHolder.mCheckView.setImageResource(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public SourceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SourceViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gcs_barrage_message_source_item, viewGroup, false));
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mListener = onItemClickListener;
    }
}
