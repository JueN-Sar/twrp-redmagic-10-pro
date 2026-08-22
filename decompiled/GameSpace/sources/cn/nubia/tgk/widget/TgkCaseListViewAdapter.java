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
public class TgkCaseListViewAdapter extends RecyclerView.Adapter {
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
            view.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.tgk.widget.TgkCaseListViewAdapter.TgkCaseHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    Log.d(TgkCaseListViewAdapter.TAG, "in onClick mSelectPosition=" + TgkCaseListViewAdapter.this.mSelectPosition + ", mLastSelectPosition=" + TgkCaseListViewAdapter.this.mLastSelectPosition);
                    TgkCaseListViewAdapter.this.mSelectPosition = TgkCaseHolder.this.getAdapterPosition();
                    Log.d(TgkCaseListViewAdapter.TAG, "in onClick getAdapterPosition=" + TgkCaseListViewAdapter.this.mSelectPosition);
                    if (TgkCaseListViewAdapter.this.mLastSelectPosition != TgkCaseListViewAdapter.this.mSelectPosition) {
                        if (TgkCaseListViewAdapter.this.mLastSelectPosition > -1) {
                            TgkCaseListViewAdapter.this.setSelected(TgkCaseListViewAdapter.this.getList(), TgkCaseListViewAdapter.this.mLastSelectPosition, false);
                            TgkCaseListViewAdapter.this.notifyItemChanged(TgkCaseListViewAdapter.this.mLastSelectPosition);
                        }
                        TgkCaseListViewAdapter.this.setSelected(TgkCaseListViewAdapter.this.getList(), TgkCaseListViewAdapter.this.mSelectPosition, true);
                        TgkCaseListViewAdapter.this.mLastSelectPosition = TgkCaseListViewAdapter.this.mSelectPosition;
                    }
                    TgkCaseListViewAdapter.this.notifyItemChanged(TgkCaseListViewAdapter.this.mSelectPosition);
                }
            });
        }

        public void onBindViewHolder(int i) {
            String str = ((TgkData) TgkCaseListViewAdapter.this.getList().get(i)).showName;
            int i2 = ((TgkData) TgkCaseListViewAdapter.this.getList().get(i)).state;
            this.tv.setText(str);
            if ((i2 & 1) <= 0) {
                TgkCaseListViewAdapter.this.setViewSelected(this.tv, false);
                return;
            }
            TgkCaseListViewAdapter.this.setViewSelected(this.tv, true);
            if (TgkCaseListViewAdapter.this.mSelectPosition == -1) {
                TgkCaseListViewAdapter.this.mSelectPosition = i;
            }
            if (TgkCaseListViewAdapter.this.mLastSelectPosition == -1) {
                TgkCaseListViewAdapter.this.mLastSelectPosition = i;
            }
        }
    }

    public interface onDataChangeListener {
        void onChanged(int i, int i2);
    }

    public TgkCaseListViewAdapter(Context context, ArrayList arrayList, ArrayList arrayList2) {
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
        if (!z) {
            arrayList.get(i).state &= -2;
            return;
        }
        int i2 = 1;
        int i3 = arrayList.get(i).state | 1;
        arrayList.get(i).state = i3;
        if ((i3 & 4) > 0) {
            i2 = 0;
        } else {
            i -= 5;
        }
        this.mListener.onChanged(i2, i);
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
        return new TgkCaseHolder(LayoutInflater.from(this.mContext).inflate(R.layout.tgk_case_list_radiobutton_layout, viewGroup, false));
    }

    public void setListener(onDataChangeListener ondatachangelistener) {
        this.mListener = ondatachangelistener;
    }
}
