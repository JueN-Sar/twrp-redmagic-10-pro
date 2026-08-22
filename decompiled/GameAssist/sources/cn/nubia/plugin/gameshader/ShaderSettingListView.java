package cn.nubia.plugin.gameshader;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ShaderSettingListView extends ListView {
    private static final String TAG = "GameShaderMgr";
    protected ShaderListAdapter mAdapter;
    private AdapterView.OnItemClickListener mItemClickListener;

    public class ShaderListAdapter extends BaseAdapter {

        /* renamed from: c, reason: collision with root package name */
        private ArrayList f8494c;

        /* renamed from: h, reason: collision with root package name */
        public int f8495h;

        public ShaderListAdapter(ArrayList arrayList) {
            new ArrayList();
            this.f8495h = 0;
            this.f8494c = arrayList;
        }

        public void a(ArrayList arrayList) {
            this.f8494c = arrayList;
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            ArrayList arrayList = this.f8494c;
            if (arrayList != null) {
                return arrayList.size();
            }
            return 0;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            ArrayList arrayList = this.f8494c;
            if (arrayList != null) {
                return arrayList.get(i2);
            }
            return null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = InflaterHelper.g(R.layout.plugin_shader_setting_item_view, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.f8497a = (ImageView) view.findViewById(R.id.plugin_shader_setting_item_img);
                viewHolder.f8498b = (TextView) view.findViewById(R.id.plugin_shader_setting_item_title);
                viewHolder.f8499c = (TextView) view.findViewById(R.id.plugin_shader_setting_item_description);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.f8498b.setText(((ShaderSettingItemData) this.f8494c.get(i2)).f8490c);
            String str = ((ShaderSettingItemData) this.f8494c.get(i2)).f8491d;
            if (TextUtils.isEmpty(str)) {
                viewHolder.f8499c.setVisibility(8);
            } else {
                viewHolder.f8499c.setVisibility(0);
            }
            viewHolder.f8499c.setText(str);
            viewHolder.f8497a.setImageDrawable(((ShaderSettingItemData) this.f8494c.get(i2)).f8492e);
            if (this.f8495h == i2) {
                viewHolder.f8498b.setTextColor(ShaderSettingListView.this.getResources().getColor(R.color.gameshader_setting_title_select));
                viewHolder.f8499c.setTextColor(ShaderSettingListView.this.getResources().getColor(R.color.gameshader_setting_description_select));
                viewHolder.f8497a.setBackgroundResource(R.drawable.shader_round_bg);
            } else {
                viewHolder.f8498b.setTextColor(ShaderSettingListView.this.getResources().getColor(R.color.gameshader_setting_title_normal));
                viewHolder.f8499c.setTextColor(ShaderSettingListView.this.getResources().getColor(R.color.gameshader_setting_description_normal));
                viewHolder.f8497a.setBackgroundResource(0);
            }
            return view;
        }
    }

    private class ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        ImageView f8497a;

        /* renamed from: b, reason: collision with root package name */
        TextView f8498b;

        /* renamed from: c, reason: collision with root package name */
        TextView f8499c;

        private ViewHolder(ShaderSettingListView shaderSettingListView) {
        }
    }

    public ShaderSettingListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mItemClickListener = new AdapterView.OnItemClickListener() { // from class: cn.nubia.plugin.gameshader.ShaderSettingListView.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
                ShaderSettingListView.this.b(view, j2);
            }
        };
    }

    public void a() {
        GaLog.e(TAG, "init list");
        ShaderListAdapter shaderListAdapter = new ShaderListAdapter(null);
        this.mAdapter = shaderListAdapter;
        setAdapter((ListAdapter) shaderListAdapter);
        setOnItemClickListener(this.mItemClickListener);
    }

    protected void b(View view, long j2) {
        GaLog.e(TAG, "onItemClick id=" + j2);
        int i2 = (int) j2;
        ShaderMgr.t().q(((ShaderSettingItemData) this.mAdapter.getItem(i2)).f8488a);
        ShaderListAdapter shaderListAdapter = this.mAdapter;
        shaderListAdapter.f8495h = i2;
        shaderListAdapter.notifyDataSetChanged();
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.widget.ListView, android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setSelectedPosition(int i2) {
        this.mAdapter.f8495h = i2;
        setSelection(i2);
    }

    public ShaderSettingListView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mItemClickListener = new AdapterView.OnItemClickListener() { // from class: cn.nubia.plugin.gameshader.ShaderSettingListView.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView adapterView, View view, int i22, long j2) {
                ShaderSettingListView.this.b(view, j2);
            }
        };
    }
}
