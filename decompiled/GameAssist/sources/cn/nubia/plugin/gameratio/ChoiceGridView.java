package cn.nubia.plugin.gameratio;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import cn.nubia.gameassist.R;
import cn.nubia.plugin.gameratio.ChoiceGridView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ChoiceGridView extends GridView {
    private OptionAdapter mOptionAdapter;
    private List<OptionItem> mOptionItems;

    /* JADX INFO: Access modifiers changed from: private */
    static class OptionAdapter extends BaseAdapter {

        /* renamed from: c, reason: collision with root package name */
        private List f8340c;

        /* renamed from: h, reason: collision with root package name */
        private int f8341h = 0;

        /* renamed from: i, reason: collision with root package name */
        private Context f8342i;

        /* renamed from: j, reason: collision with root package name */
        private boolean f8343j;

        public OptionAdapter(Context context) {
            this.f8342i = context;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void d(OptionItem optionItem, CompoundButton compoundButton, boolean z) {
            if (this.f8343j) {
                return;
            }
            if (z) {
                this.f8341h = optionItem.a();
            }
            notifyDataSetInvalidated();
        }

        public int b() {
            return this.f8341h;
        }

        @Override // android.widget.Adapter
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public OptionItem getItem(int i2) {
            return (OptionItem) this.f8340c.get(i2);
        }

        public void e(List list, int i2) {
            this.f8340c = list;
            this.f8341h = i2;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.f8340c.size();
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(this.f8342i).inflate(R.layout.gameratio_option, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.f8346a = (RadioButton) view.findViewById(R.id.option);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            final OptionItem item = getItem(i2);
            viewHolder.f8346a.setText(item.b());
            this.f8343j = true;
            viewHolder.f8346a.setChecked(this.f8341h == item.a());
            this.f8343j = false;
            viewHolder.f8346a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: cn.nubia.plugin.gameratio.a
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    ChoiceGridView.OptionAdapter.this.d(item, compoundButton, z);
                }
            });
            return view;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class OptionItem {

        /* renamed from: a, reason: collision with root package name */
        private int f8344a;

        /* renamed from: b, reason: collision with root package name */
        private int f8345b;

        public OptionItem(int i2, int i3) {
            this.f8344a = i2;
            this.f8345b = i3;
        }

        public int a() {
            return this.f8344a;
        }

        public int b() {
            return this.f8345b;
        }
    }

    private static class ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        public RadioButton f8346a;

        private ViewHolder() {
        }
    }

    public ChoiceGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOptionItems = new ArrayList();
        a();
    }

    private void a() {
        if (this.mOptionAdapter == null) {
            this.mOptionAdapter = new OptionAdapter(getContext());
        }
    }

    public void b(int[] iArr, int[] iArr2, int i2) {
        if (iArr.length != iArr2.length) {
            return;
        }
        this.mOptionItems.clear();
        for (int i3 = 0; i3 < iArr.length; i3++) {
            this.mOptionItems.add(new OptionItem(iArr[i3], iArr2[i3]));
        }
        this.mOptionAdapter.e(this.mOptionItems, i2);
        setAdapter((ListAdapter) this.mOptionAdapter);
    }

    public int getCheckedId() {
        return this.mOptionAdapter.b();
    }

    public ChoiceGridView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mOptionItems = new ArrayList();
        a();
    }

    public ChoiceGridView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mOptionItems = new ArrayList();
        a();
    }
}
