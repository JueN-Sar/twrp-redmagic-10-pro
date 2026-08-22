package cn.nubia.chatassistant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.nubia.gamelauncher.R;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class ChatAssistantContentAdapter extends BaseAdapter {
    public static final String KEY_FILE_NAME = "title";
    public static final String KEY_FILE_PATH = "path";
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Map<String, Object>> mList;

    private final class ViewHolder {
        public String dataPatch;
        public TextView title;

        private ViewHolder() {
        }
    }

    public ChatAssistantContentAdapter(Context context, List<Map<String, Object>> list) {
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
            view2 = this.mLayoutInflater.inflate(R.layout.chat_assistant_content_item, (ViewGroup) null);
            viewHolder.title = (TextView) view2.findViewById(R.id.chat_assistant_content_title);
            view2.setTag(viewHolder);
        } else {
            view2 = view;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText((String) this.mList.get(i).get("title"));
        viewHolder.dataPatch = (String) this.mList.get(i).get("path");
        return view2;
    }
}
