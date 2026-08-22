package cn.nubia.gamelauncher.adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.bean.ScoreOneBean;
import cn.nubia.gamelauncher.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class ScoreWinAdapter extends RecyclerView.Adapter {
    private static final String TAG = "ScoreWinAdapter";
    private static final String WIN_RATE_URI = "content://cn.zte.gamefloat.db.winratepredication/data/0";
    private Context mContext;
    private IWindRateListener mListener;
    private boolean mSupportWinRate;
    private int mLastWindrate_postion = -1;
    private Map<String, Boolean> winDataCache = new HashMap();
    private ArrayList<ScoreOneBean> scoreOneLists = new ArrayList<>();

    public interface IWindRateListener {
        void onHideWinRateView();

        void onShowWinRateView(List<Float> list, boolean z);
    }

    class WinInfoViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_win_lost;
        public ImageView iv_win_rate;
        public TextView tv_assist;
        public TextView tv_date;
        public TextView tv_death;
        public TextView tv_kill;

        public WinInfoViewHolder(View view) {
            super(view);
            this.iv_win_lost = (ImageView) view.findViewById(R.id.iv_win_lost);
            this.iv_win_rate = (ImageView) view.findViewById(R.id.iv_win_rate);
            this.tv_date = (TextView) view.findViewById(R.id.tv_date);
            this.tv_kill = (TextView) view.findViewById(R.id.tv_kill);
            this.tv_death = (TextView) view.findViewById(R.id.tv_death);
            this.tv_assist = (TextView) view.findViewById(R.id.tv_assist);
        }
    }

    public ScoreWinAdapter(Context context, IWindRateListener iWindRateListener) {
        this.mContext = context;
        this.mListener = iWindRateListener;
    }

    public void clearList() {
        this.scoreOneLists.clear();
        this.mLastWindrate_postion = -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.scoreOneLists.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        WinInfoViewHolder winInfoViewHolder = (WinInfoViewHolder) viewHolder;
        if (this.scoreOneLists.get(i).isWin()) {
            winInfoViewHolder.iv_win_lost.setImageResource(R.mipmap.score_record_win);
        } else {
            winInfoViewHolder.iv_win_lost.setImageResource(R.mipmap.score_record_fail);
        }
        ScoreOneBean scoreOneBean = this.scoreOneLists.get(i);
        String valueOf = String.valueOf(scoreOneBean.getLongStartTime());
        if (!this.winDataCache.containsKey(valueOf)) {
            Cursor query = this.mContext.getContentResolver().query(Uri.parse(WIN_RATE_URI), null, null, new String[]{valueOf, scoreOneBean.getPackageName()}, null);
            if (query == null || !query.moveToFirst()) {
                this.winDataCache.put(valueOf, Boolean.FALSE);
            } else {
                this.winDataCache.put(valueOf, query.getCount() > 1 ? Boolean.TRUE : Boolean.FALSE);
            }
            LogUtil.i(TAG, "query winRate time = " + valueOf + " result = " + this.winDataCache.get(valueOf));
            if (query != null) {
                query.close();
            }
        }
        boolean equals = Boolean.TRUE.equals(this.winDataCache.get(valueOf));
        if (!equals) {
            winInfoViewHolder.iv_win_rate.setImageDrawable(this.mContext.getDrawable(R.drawable.winrate_disable));
        } else if (this.mLastWindrate_postion != i) {
            winInfoViewHolder.iv_win_rate.setImageDrawable(this.mContext.getDrawable(R.drawable.winrate_enable));
        } else if (this.scoreOneLists.get(i).isWin()) {
            winInfoViewHolder.iv_win_rate.setImageDrawable(this.mContext.getDrawable(R.mipmap.winrate_win_select));
        } else {
            winInfoViewHolder.iv_win_rate.setImageDrawable(this.mContext.getDrawable(R.mipmap.winrate_lost_select));
        }
        winInfoViewHolder.tv_date.setText(this.scoreOneLists.get(i).getStartTime());
        winInfoViewHolder.tv_kill.setText(this.scoreOneLists.get(i).getKill());
        winInfoViewHolder.tv_death.setText(this.scoreOneLists.get(i).getDead());
        winInfoViewHolder.tv_assist.setText(this.scoreOneLists.get(i).getAssit());
        winInfoViewHolder.iv_win_rate.setVisibility(this.mSupportWinRate ? 0 : 8);
        if (this.mSupportWinRate && equals) {
            winInfoViewHolder.iv_win_rate.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.adapter.ScoreWinAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (ScoreWinAdapter.this.mLastWindrate_postion == i) {
                        if (ScoreWinAdapter.this.mListener != null) {
                            ScoreWinAdapter.this.mLastWindrate_postion = -1;
                            ScoreWinAdapter.this.mListener.onHideWinRateView();
                            return;
                        }
                        return;
                    }
                    Uri parse = Uri.parse(ScoreWinAdapter.WIN_RATE_URI);
                    ScoreOneBean scoreOneBean2 = (ScoreOneBean) ScoreWinAdapter.this.scoreOneLists.get(i);
                    Cursor cursor = null;
                    try {
                        try {
                            cursor = ScoreWinAdapter.this.mContext.getContentResolver().query(parse, null, null, new String[]{String.valueOf(scoreOneBean2.getLongStartTime()), scoreOneBean2.getPackageName()}, null);
                            if (cursor != null && cursor.moveToFirst()) {
                                ArrayList arrayList = new ArrayList();
                                do {
                                    int columnIndex = cursor.getColumnIndex("startTime");
                                    int columnIndex2 = cursor.getColumnIndex("predication");
                                    String string = cursor.getString(columnIndex);
                                    float f = cursor.getFloat(columnIndex2);
                                    Log.d(ScoreWinAdapter.TAG, "startTime = " + string + ", value = " + f);
                                    arrayList.add(Float.valueOf(f));
                                } while (cursor.moveToNext());
                                if (ScoreWinAdapter.this.mListener != null) {
                                    ScoreWinAdapter.this.mLastWindrate_postion = i;
                                    ScoreWinAdapter.this.mListener.onShowWinRateView(arrayList, ((ScoreOneBean) ScoreWinAdapter.this.scoreOneLists.get(i)).isWin());
                                }
                            }
                            if (cursor == null) {
                                return;
                            }
                        } catch (Exception e) {
                            Log.e(ScoreWinAdapter.TAG, "getWinRateCursor: ", e);
                            if (cursor == null) {
                                return;
                            }
                        }
                        cursor.close();
                    } catch (Throwable th) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new WinInfoViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.score_win_list_item_layout, viewGroup, false));
    }

    public void setList(ArrayList<ScoreOneBean> arrayList) {
        this.scoreOneLists = arrayList;
    }

    public void setSupportWinRate(boolean z) {
        this.mSupportWinRate = z;
    }
}
