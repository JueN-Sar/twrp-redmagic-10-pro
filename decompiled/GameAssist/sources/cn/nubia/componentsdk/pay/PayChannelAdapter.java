package cn.nubia.componentsdk.pay;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.zte.shared.wrapper.VirtualHandleWrapper;

/* loaded from: classes.dex */
public class PayChannelAdapter extends BaseFootviewAdapter<PayChannel> {

    /* renamed from: o, reason: collision with root package name */
    private SparseBooleanArray f6013o;

    private static class ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f6014a;

        /* renamed from: b, reason: collision with root package name */
        private TextView f6015b;

        /* renamed from: c, reason: collision with root package name */
        private ImageView f6016c;

        /* renamed from: d, reason: collision with root package name */
        private TextView f6017d;

        private ViewHolder() {
        }
    }

    public PayChannelAdapter(Context context, ListView listView) {
        super(context, listView);
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        this.f6013o = sparseBooleanArray;
        sparseBooleanArray.put(0, true);
    }

    private void e(int i2, View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        PayChannel payChannel = (PayChannel) getItem(i2);
        if (payChannel == null) {
            return;
        }
        int c2 = Util.c(this.f5913c, "ali_pay", "drawable");
        int c3 = Util.c(this.f5913c, "weixin_pay", "drawable");
        int c4 = Util.c(this.f5913c, "yfb_pay", "drawable");
        int c5 = Util.c(this.f5913c, "qq_pay", "drawable");
        int c6 = Util.c(this.f5913c, "bank_pay", "drawable");
        int c7 = Util.c(this.f5913c, "selected", "drawable");
        int c8 = Util.c(this.f5913c, "un_selected", "drawable");
        if ("AliPhonePay".equals(payChannel.d())) {
            viewHolder.f6014a.setImageResource(c2);
        } else if ("WeiXinAppPay".equals(payChannel.d())) {
            viewHolder.f6014a.setImageResource(c3);
        } else if ("SuningWapPay".equals(payChannel.d())) {
            viewHolder.f6014a.setImageResource(c4);
        } else if ("QqWalletPay".equals(payChannel.d())) {
            viewHolder.f6014a.setImageResource(c5);
        } else {
            viewHolder.f6014a.setImageResource(c6);
        }
        viewHolder.f6015b.setText(payChannel.b());
        if (this.f6013o.get(i2)) {
            viewHolder.f6016c.setImageResource(c7);
        } else {
            viewHolder.f6016c.setImageResource(c8);
        }
        if (TextUtils.isEmpty(payChannel.a())) {
            viewHolder.f6017d.setText("");
        } else {
            viewHolder.f6017d.setText(payChannel.a());
        }
    }

    public void f(int i2) {
        int size = this.f6013o.size();
        for (int i3 = 0; i3 < size; i3++) {
            SparseBooleanArray sparseBooleanArray = this.f6013o;
            sparseBooleanArray.put(sparseBooleanArray.keyAt(i3), false);
        }
        this.f6013o.put(i2, true);
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        if (view == null) {
            ViewHolder viewHolder = new ViewHolder();
            View inflate = this.f5915i.inflate(Util.c(this.f5913c, "paychannel_item", "layout"), (ViewGroup) null);
            int c2 = Util.c(this.f5913c, "paychannel_icon", VirtualHandleWrapper.KEY_ID);
            int c3 = Util.c(this.f5913c, "paychannel_name", VirtualHandleWrapper.KEY_ID);
            int c4 = Util.c(this.f5913c, "paychannel_sel", VirtualHandleWrapper.KEY_ID);
            int c5 = Util.c(this.f5913c, "paychannel_tip", VirtualHandleWrapper.KEY_ID);
            viewHolder.f6014a = (ImageView) inflate.findViewById(c2);
            viewHolder.f6015b = (TextView) inflate.findViewById(c3);
            viewHolder.f6016c = (ImageView) inflate.findViewById(c4);
            viewHolder.f6017d = (TextView) inflate.findViewById(c5);
            inflate.setTag(viewHolder);
            view = inflate;
        }
        e(i2, view);
        return view;
    }
}
