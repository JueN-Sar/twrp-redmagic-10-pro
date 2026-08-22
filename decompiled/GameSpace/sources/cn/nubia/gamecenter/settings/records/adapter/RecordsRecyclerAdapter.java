package cn.nubia.gamecenter.settings.records.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.records.adapter.RecordsGridAdapter;
import cn.nubia.gamecenter.settings.records.bean.HighlightsFile;
import cn.nubia.gamecenter.settings.records.utils.DateUtils;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamecenter.settings.records.view.RecordsGridView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
public class RecordsRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String TAG = "RecordsActivity";
    private RecordsGridAdapter gridAdapter;
    private Context mContext;
    private LinkedHashMap<String, ArrayList<HighlightsFile>> mData;
    private ArrayList<HighlightsFile> mFilesList;
    private ArrayList<String> mList;
    private OnDataChangeListener mOnDataChangeListener;
    private HashMap<String, Integer> mPreViewHashMap;
    private int mWidth;
    private int normal_bottom;
    private int normal_height;
    private int normal_width;

    public interface OnDataChangeListener {
        void onDataChanged(String str, HighlightsFile highlightsFile);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView dateView;
        private RecordsGridView gridView;

        public ViewHolder(View view) {
            super(view);
            this.dateView = (TextView) view.findViewById(R.id.gcs_game_high_light_recycler_item_date);
            this.gridView = (RecordsGridView) view.findViewById(R.id.gcs_game_high_light_recycler_item_grid);
        }
    }

    public RecordsRecyclerAdapter(Context context, LinkedHashMap<String, ArrayList<HighlightsFile>> linkedHashMap, ArrayList<String> arrayList, HashMap<String, Integer> hashMap, int i) {
        this.normal_width = HighLightsUtils.NORMAL_ITEM_WIDTH;
        this.normal_height = 180;
        this.normal_bottom = 12;
        this.mContext = context;
        this.mData = linkedHashMap;
        this.mList = arrayList;
        this.mPreViewHashMap = hashMap;
        this.mWidth = i;
        this.normal_height = (int) (context.getResources().getDimension(R.dimen.gcs_game_high_light_grid_item_height) + this.mContext.getResources().getDimension(R.dimen.gcs_game_high_light_recycler_item_grid_vertical_spacing));
        this.normal_width = (int) this.mContext.getResources().getDimension(R.dimen.gcs_game_high_light_grid_item_width);
        this.normal_bottom = (int) (this.mContext.getResources().getDimension(R.dimen.gcs_game_high_light_recycler_item_grid_vertical_spacing) * 2.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int calculateGridViewHeight(int i) {
        int i2;
        int i3;
        int column = getColumn();
        if (i % column != 0) {
            i2 = ((i / column) + 1) * this.normal_height;
            i3 = this.normal_bottom;
        } else {
            i2 = (i / column) * this.normal_height;
            i3 = this.normal_bottom;
        }
        return i2 + i3;
    }

    private int getColumn() {
        return ((this.mWidth * 2244) / HighLightsUtils.NORMAL_WIDTH) / this.normal_width;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mData.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        if (this.mList.get(i).equals(DateUtils.format(System.currentTimeMillis(), true))) {
            viewHolder.dateView.setText(R.string.gcs_summary_header_panel_day);
        } else {
            viewHolder.dateView.setText(this.mList.get(i));
        }
        RecordsGridAdapter recordsGridAdapter = new RecordsGridAdapter(this.mContext, this.mData.get(this.mList.get(i)), this.mPreViewHashMap);
        this.gridAdapter = recordsGridAdapter;
        recordsGridAdapter.setCallBack(new RecordsGridAdapter.CallBack() { // from class: cn.nubia.gamecenter.settings.records.adapter.RecordsRecyclerAdapter.1
            @Override // cn.nubia.gamecenter.settings.records.adapter.RecordsGridAdapter.CallBack
            public void refreshData(boolean z, boolean z2, HighlightsFile highlightsFile) {
                int adapterPosition = viewHolder.getAdapterPosition();
                if (z) {
                    if (adapterPosition != -1) {
                        RecordsRecyclerAdapter.this.mData.remove(RecordsRecyclerAdapter.this.mList.get(adapterPosition));
                        RecordsRecyclerAdapter.this.mOnDataChangeListener.onDataChanged((String) RecordsRecyclerAdapter.this.mList.get(adapterPosition), highlightsFile);
                        RecordsRecyclerAdapter.this.mList.remove(adapterPosition);
                        RecordsRecyclerAdapter.this.notifyItemRemoved(adapterPosition);
                        return;
                    }
                    return;
                }
                if (adapterPosition != -1) {
                    RecordsRecyclerAdapter.this.mOnDataChangeListener.onDataChanged((String) RecordsRecyclerAdapter.this.mList.get(adapterPosition), highlightsFile);
                    ArrayList arrayList = (ArrayList) RecordsRecyclerAdapter.this.mData.get(RecordsRecyclerAdapter.this.mList.get(adapterPosition));
                    if (arrayList != null) {
                        arrayList.remove(highlightsFile);
                        if (z2) {
                            viewHolder.gridView.getLayoutParams().height = RecordsRecyclerAdapter.this.calculateGridViewHeight(arrayList.size());
                            RecordsRecyclerAdapter.this.notifyItemChanged(adapterPosition);
                        }
                    }
                }
            }
        });
        ArrayList<HighlightsFile> arrayList = this.mData.get(this.mList.get(i));
        this.mFilesList = arrayList;
        if (arrayList != null) {
            viewHolder.gridView.getLayoutParams().height = calculateGridViewHeight(this.mFilesList.size());
            viewHolder.gridView.setNumColumns(getColumn());
            viewHolder.gridView.setAdapter((ListAdapter) this.gridAdapter);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = this.mContext;
        if (context == null) {
            return null;
        }
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.gcs_gamecenter_fragment_record_recycler_item_layout, viewGroup, false));
    }

    public void setDataAndList(LinkedHashMap<String, ArrayList<HighlightsFile>> linkedHashMap, ArrayList<String> arrayList, HashMap<String, Integer> hashMap) {
        this.mData = linkedHashMap;
        this.mList = arrayList;
        this.mPreViewHashMap = hashMap;
        this.normal_height = (int) (this.mContext.getResources().getDimension(R.dimen.gcs_game_high_light_grid_item_height) + this.mContext.getResources().getDimension(R.dimen.gcs_game_high_light_recycler_item_grid_vertical_spacing));
        this.normal_width = (int) this.mContext.getResources().getDimension(R.dimen.gcs_game_high_light_grid_item_width);
        this.normal_bottom = (int) (this.mContext.getResources().getDimension(R.dimen.gcs_game_high_light_recycler_item_grid_vertical_spacing) * 2.0f);
    }

    public void setOnDataChangeListener(OnDataChangeListener onDataChangeListener) {
        this.mOnDataChangeListener = onDataChangeListener;
    }
}
