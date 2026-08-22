package cn.nubia.componentsdk.pay;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class BaseFootviewAdapter<E> extends BaseAdapter {

    /* renamed from: c, reason: collision with root package name */
    protected Context f5913c;

    /* renamed from: h, reason: collision with root package name */
    protected ListView f5914h;

    /* renamed from: i, reason: collision with root package name */
    protected LayoutInflater f5915i;

    /* renamed from: j, reason: collision with root package name */
    protected LinearLayout f5916j;

    /* renamed from: k, reason: collision with root package name */
    protected Button f5917k;

    /* renamed from: l, reason: collision with root package name */
    protected ArrayList f5918l = new ArrayList();

    /* renamed from: m, reason: collision with root package name */
    protected ArrayList f5919m = null;

    /* renamed from: n, reason: collision with root package name */
    protected boolean f5920n = true;

    public BaseFootviewAdapter(Context context, ListView listView) {
        this.f5913c = context;
        this.f5914h = listView;
        this.f5915i = LayoutInflater.from(context);
        Resources resources = context.getResources();
        String packageName = context.getPackageName();
        int identifier = resources.getIdentifier("paychannel_footer", "layout", packageName);
        int identifier2 = resources.getIdentifier("btn_loadmore", VirtualHandleWrapper.KEY_ID, packageName);
        LinearLayout linearLayout = (LinearLayout) this.f5915i.inflate(identifier, (ViewGroup) null);
        this.f5916j = linearLayout;
        Button button = (Button) linearLayout.findViewById(identifier2);
        this.f5917k = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.componentsdk.pay.BaseFootviewAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseFootviewAdapter.this.b();
            }
        });
        listView.addFooterView(this.f5916j, null, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void b() {
        this.f5916j.setVisibility(8);
        this.f5918l.clear();
        if (this.f5920n) {
            this.f5920n = false;
            this.f5918l.addAll(this.f5919m);
            this.f5917k.setVisibility(8);
        } else {
            this.f5920n = true;
            for (int i2 = 0; i2 < 3; i2++) {
                this.f5918l.add(this.f5919m.get(i2));
            }
            this.f5917k.setText(this.f5913c.getResources().getIdentifier("paychannel_load_tip", "string", this.f5913c.getPackageName()));
        }
        notifyDataSetChanged();
        d(this.f5914h);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void c(ArrayList arrayList) {
        this.f5919m = arrayList;
        this.f5918l.clear();
        if (arrayList != null) {
            if (arrayList.size() <= 3) {
                this.f5916j.setVisibility(8);
                this.f5918l.addAll(arrayList);
            } else {
                this.f5916j.setVisibility(0);
                for (int i2 = 0; i2 < 3; i2++) {
                    this.f5918l.add(arrayList.get(i2));
                }
            }
        }
        notifyDataSetChanged();
        d(this.f5914h);
    }

    protected void d(ListView listView) {
        ListAdapter adapter;
        if (listView == null || (adapter = listView.getAdapter()) == null) {
            return;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < adapter.getCount(); i3++) {
            View view = adapter.getView(i3, null, listView);
            view.measure(0, 0);
            i2 += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height = i2 + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(layoutParams);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        ArrayList arrayList = this.f5918l;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        ArrayList arrayList = this.f5918l;
        if (arrayList != null) {
            return arrayList.get(i2);
        }
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }
}
