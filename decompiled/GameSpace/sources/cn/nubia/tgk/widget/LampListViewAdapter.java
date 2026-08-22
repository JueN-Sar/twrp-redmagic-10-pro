package cn.nubia.tgk.widget;

import android.content.ContentResolver;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gamelauncher.R;
import cn.nubia.tgk.TgkHelper;
import cn.nubia.tgk.util.TgkLampHelper;

/* loaded from: classes2.dex */
public class LampListViewAdapter extends RecyclerView.Adapter {
    private static final String TAG = "LampListViewAdapter";
    private onDataChangeListener listener;
    private Context mContext;
    private int mLastSelectPosition = -1;
    private int mSelectPosition = -1;
    private boolean newCase;
    private String packageName;
    private ContentResolver resolver;

    class LameCaseHolder extends RecyclerView.ViewHolder {
        protected TextView tv;

        public LameCaseHolder(View view) {
            super(view);
            TextView textView = (TextView) view.findViewById(R.id.tgk_case_list_title);
            this.tv = textView;
            textView.setSelected(true);
            view.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.tgk.widget.LampListViewAdapter.LameCaseHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    LampListViewAdapter.this.mSelectPosition = LameCaseHolder.this.getAdapterPosition();
                    if (LampListViewAdapter.this.newCase) {
                        TgkHelper.insertLampCase(LampListViewAdapter.this.resolver, LampListViewAdapter.this.mSelectPosition, LampListViewAdapter.this.packageName);
                    } else {
                        TgkHelper.updateLampCase(LampListViewAdapter.this.resolver, LampListViewAdapter.this.mSelectPosition, LampListViewAdapter.this.packageName);
                    }
                    Log.i(LampListViewAdapter.TAG, "requestColorfulLight mSelectPosition = " + LampListViewAdapter.this.mSelectPosition);
                    LampListViewAdapter.this.listener.onChanged(LampListViewAdapter.this.mSelectPosition);
                }
            });
        }

        public void onBindViewHolder(int i) {
            Log.i(LampListViewAdapter.TAG, "onBindViewHolder lampCaseName =" + TgkLampHelper.getLampCaseNameForIndex(i));
            this.tv.setText(TgkLampHelper.getLampCaseNameForIndex(i));
            if (LampListViewAdapter.this.mLastSelectPosition == i) {
                LampListViewAdapter.this.setViewSelected(this.tv, true);
            } else {
                LampListViewAdapter.this.setViewSelected(this.tv, false);
            }
        }
    }

    public interface onDataChangeListener {
        void onChanged(int i);
    }

    public LampListViewAdapter(Context context) {
        this.mContext = context;
        this.resolver = context.getContentResolver();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setViewSelected(TextView textView, boolean z) {
        if (z) {
            textView.setTextColor(this.mContext.getResources().getColor(R.color.tgk_text_red));
        } else {
            textView.setTextColor(this.mContext.getResources().getColor(R.color.tgk_text_press));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return TgkLampHelper.getLampCaseNamesSize();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((LameCaseHolder) viewHolder).onBindViewHolder(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public LameCaseHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new LameCaseHolder(LayoutInflater.from(this.mContext).inflate(R.layout.tgk_case_list_radiobutton_layout, viewGroup, false));
    }

    public void setListener(onDataChangeListener ondatachangelistener) {
        this.listener = ondatachangelistener;
    }

    public void setPackage(String str) {
        this.packageName = str;
    }

    public void setSelectState(int i) {
        this.mLastSelectPosition = i;
        if (i != -1) {
            this.newCase = false;
        } else {
            this.mLastSelectPosition = 1;
            this.newCase = true;
        }
    }
}
