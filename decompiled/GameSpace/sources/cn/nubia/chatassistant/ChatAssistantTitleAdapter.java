package cn.nubia.chatassistant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.nubia.gamelauncher.R;
import java.util.List;

/* loaded from: classes.dex */
public class ChatAssistantTitleAdapter extends BaseAdapter {
    public static final String KEY_FIST_NAME = "first_name";
    public static final String KEY_TAB_NUB = "tab_nub";
    private static final String TAG = "ChatAssistantTitleAdapter";
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<String> mList;
    private int mSelectItemId = 0;

    private final class ViewHolder {
        public TextView title;

        private ViewHolder() {
        }
    }

    public ChatAssistantTitleAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mList.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.mList.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view2 = this.mLayoutInflater.inflate(R.layout.chat_assistant_title_item, (ViewGroup) null);
            viewHolder.title = (TextView) view2.findViewById(R.id.chat_assistant_first_title);
            view2.setTag(viewHolder);
        } else {
            view2 = view;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(this.mList.get(i));
        if (i == this.mSelectItemId) {
            view2.setBackgroundResource(R.drawable.chat_title_bg);
            viewHolder.title.setTextColor(this.mContext.getResources().getColor(R.color.white, null));
        } else {
            view2.setBackgroundResource(0);
            viewHolder.title.setTextColor(this.mContext.getResources().getColor(R.color.gyro_sen_text_color, null));
        }
        return view2;
    }

    public void setSelectedItem(int i) {
        this.mSelectItemId = i;
    }
}
