package cn.nubia.tgk.widget;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gamelauncher.R;
import cn.nubia.tgk.data.TgkData;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class TgkShowCaseListViewAdapter extends RecyclerView.Adapter {
    private static final String TAG = "TgkCaseListViewAdapter";
    private Context mContext;
    ArrayList<TgkData> mImportList;
    private onDataChangeListener mListener;
    ArrayList<TgkData> mPresetList;
    private int mLastSelectPosition = -1;
    private int mSelectPosition = -1;

    class TgkCaseHolder extends RecyclerView.ViewHolder {
        protected TextView tv;

        public TgkCaseHolder(View view) {
            super(view);
            TextView textView = (TextView) view.findViewById(R.id.tgk_case_list_title);
            this.tv = textView;
            textView.setSelected(true);
            view.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.tgk.widget.TgkShowCaseListViewAdapter.TgkCaseHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    Log.d(TgkShowCaseListViewAdapter.TAG, "in onClick mSelectPosition=" + TgkShowCaseListViewAdapter.this.mSelectPosition + ", mLastSelectPosition=" + TgkShowCaseListViewAdapter.this.mLastSelectPosition);
                    TgkShowCaseListViewAdapter.this.mSelectPosition = TgkCaseHolder.this.getAdapterPosition();
                    Log.d(TgkShowCaseListViewAdapter.TAG, "in onClick getAdapterPosition=" + TgkShowCaseListViewAdapter.this.mSelectPosition);
                    if (TgkShowCaseListViewAdapter.this.mLastSelectPosition != TgkShowCaseListViewAdapter.this.mSelectPosition) {
                        if (TgkShowCaseListViewAdapter.this.mLastSelectPosition > -1) {
                            TgkShowCaseListViewAdapter.this.setSelected(TgkShowCaseListViewAdapter.this.getList(), TgkShowCaseListViewAdapter.this.mLastSelectPosition, false);
                            TgkShowCaseListViewAdapter.this.notifyItemChanged(TgkShowCaseListViewAdapter.this.mLastSelectPosition);
                        }
                        TgkShowCaseListViewAdapter.this.setSelected(TgkShowCaseListViewAdapter.this.getList(), TgkShowCaseListViewAdapter.this.mSelectPosition, true);
                        TgkShowCaseListViewAdapter.this.mLastSelectPosition = TgkShowCaseListViewAdapter.this.mSelectPosition;
                    }
                    TgkShowCaseListViewAdapter.this.notifyItemChanged(TgkShowCaseListViewAdapter.this.mSelectPosition);
                }
            });
        }

        public void onBindViewHolder(int i) {
            String str = ((TgkData) TgkShowCaseListViewAdapter.this.getList().get(i)).showName;
            int i2 = ((TgkData) TgkShowCaseListViewAdapter.this.getList().get(i)).state;
            this.tv.setText(str);
            if ((i2 & 1) <= 0) {
                TgkShowCaseListViewAdapter.this.setViewSelected(this.tv, false);
                return;
            }
            TgkShowCaseListViewAdapter.this.setViewSelected(this.tv, true);
            if (TgkShowCaseListViewAdapter.this.mSelectPosition == -1) {
                TgkShowCaseListViewAdapter.this.mSelectPosition = i;
            }
            if (TgkShowCaseListViewAdapter.this.mLastSelectPosition == -1) {
                TgkShowCaseListViewAdapter.this.mLastSelectPosition = i;
            }
        }
    }

    public interface onDataChangeListener {
        void onChanged(int i, int i2);

        void onTgkDataChanged(int i, TgkData tgkData);
    }

    public TgkShowCaseListViewAdapter(Context context, ArrayList arrayList, ArrayList arrayList2) {
        this.mContext = context;
        this.mPresetList = arrayList;
        this.mImportList = arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<TgkData> getList() {
        ArrayList<TgkData> arrayList = new ArrayList<>();
        arrayList.addAll(this.mPresetList);
        ArrayList<TgkData> arrayList2 = this.mImportList;
        if (arrayList2 != null) {
            arrayList.addAll(arrayList2);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelected(ArrayList<TgkData> arrayList, int i, boolean z) {
        int i2;
        if (!z) {
            int i3 = arrayList.get(i).state;
            arrayList.get(i).state = i3 & (-2);
            this.mListener.onTgkDataChanged((i3 & 4) <= 0 ? 1 : 0, arrayList.get(i));
            return;
        }
        int i4 = arrayList.get(i).state | 1;
        arrayList.get(i).state = i4;
        if ((i4 & 4) > 0) {
            i2 = i;
        } else {
            i2 = i - 5;
            r0 = 1;
        }
        this.mListener.onChanged(r0, i2);
        this.mListener.onTgkDataChanged(r0, arrayList.get(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setViewSelected(TextView textView, boolean z) {
        if (z) {
            textView.setTextColor(this.mContext.getResources().getColor(R.color.tgk_text_red));
        } else {
            textView.setTextColor(this.mContext.getResources().getColor(R.color.tgk_text_press));
        }
    }

    public void changeTable(int i) {
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return getList().size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((TgkCaseHolder) viewHolder).onBindViewHolder(i);
    }

    public void onCaseDeleted() {
        this.mLastSelectPosition = 0;
        notifyDataSetChanged();
    }

    public void onCaseRenamed() {
        notifyItemChanged(this.mSelectPosition);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public TgkCaseHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new TgkCaseHolder(LayoutInflater.from(this.mContext).inflate(R.layout.tgk_show_case_list_item_layout, viewGroup, false));
    }

    public void setListener(onDataChangeListener ondatachangelistener) {
        this.mListener = ondatachangelistener;
    }
}
