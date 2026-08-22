package cn.nubia.gameassist.plugin.sort;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class SortListAdapter extends BaseAdapter {

    /* renamed from: c, reason: collision with root package name */
    private Context f7325c;

    /* renamed from: h, reason: collision with root package name */
    private List f7326h;

    class ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        TextView f7327a;

        ViewHolder(SortListAdapter sortListAdapter, View view) {
            this.f7327a = (TextView) view.findViewById(R.id.sort_mode_tv);
        }
    }

    public SortListAdapter(Context context) {
        this.f7325c = context;
        this.f7326h = Arrays.asList(context.getResources().getStringArray(R.array.plugin_sort_list));
    }

    @Override // android.widget.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String getItem(int i2) {
        return (String) this.f7326h.get(i2);
    }

    public int b() {
        return SharedPreferencesUtil.k(this.f7325c).z(Utils.j());
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f7326h.size();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = View.inflate(this.f7325c, R.layout.item_sort_popup, null);
            viewHolder = new ViewHolder(this, view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.f7327a.setText((CharSequence) this.f7326h.get(i2));
        if (i2 == b()) {
            viewHolder.f7327a.setTextColor(this.f7325c.getColor(R.color.plugin_custome_sort_item_tv_color));
        }
        return view;
    }
}
