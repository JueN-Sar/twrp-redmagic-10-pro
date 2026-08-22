package cn.nubia.plug;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.nubia.gamelauncher.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class PlugAdapter extends BaseAdapter {
    private Context mContext;
    private int mLayoutRes;
    private List<PlugData> mPlugList;
    private int mReversePosition;
    private int mSelectedPosition;

    private class PlugViewHolder {
        private ImageView head;
        private LinearLayout root;
        private TextView title;

        private PlugViewHolder() {
        }
    }

    public PlugAdapter(Context context, int i, List<PlugData> list) {
        new ArrayList();
        this.mSelectedPosition = 0;
        this.mReversePosition = 0;
        this.mContext = context;
        this.mLayoutRes = i;
        this.mPlugList = list;
    }

    private void notifyTrack(int i) {
        PlugTrackManager.uploadPlugName(this.mContext.getString(this.mPlugList.get(i).getTrackId()));
    }

    private void setNormalGradient(LinearLayout linearLayout, PlugData plugData) {
        GradientDrawable gradientDrawable = (GradientDrawable) linearLayout.getBackground();
        gradientDrawable.mutate();
        String[] normalColorString = plugData.getNormalColorString();
        gradientDrawable.setColors(new int[]{Color.parseColor(normalColorString[0]), Color.parseColor(normalColorString[1])});
        linearLayout.setBackground(gradientDrawable);
    }

    private void setSelectGradient(LinearLayout linearLayout, PlugData plugData, int i) {
        GradientDrawable gradientDrawable = (GradientDrawable) linearLayout.getBackground();
        gradientDrawable.mutate();
        String[] selectorColors = plugData.getSelectorColors(i);
        gradientDrawable.setColors(new int[]{Color.parseColor(selectorColors[0]), Color.parseColor(selectorColors[1])});
        linearLayout.setBackground(gradientDrawable);
    }

    public void clearData() {
        List<PlugData> list = this.mPlugList;
        if (list != null) {
            list.clear();
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mPlugList.size();
    }

    @Override // android.widget.Adapter
    public PlugData getItem(int i) {
        return this.mPlugList.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public int getReversePosition() {
        return this.mReversePosition;
    }

    public int getSelectedPosition() {
        return this.mSelectedPosition;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        PlugViewHolder plugViewHolder;
        PlugData item = getItem(i);
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(this.mLayoutRes, viewGroup, false);
            plugViewHolder = new PlugViewHolder();
            plugViewHolder.root = (LinearLayout) view.findViewById(R.id.root);
            plugViewHolder.head = (ImageView) view.findViewById(R.id.head);
            plugViewHolder.title = (TextView) view.findViewById(R.id.title);
            view.setTag(plugViewHolder);
        } else {
            plugViewHolder = (PlugViewHolder) view.getTag();
        }
        plugViewHolder.title.setText(item.getTitleId());
        if (i == this.mSelectedPosition) {
            plugViewHolder.head.setBackgroundResource(item.getDrawables()[0]);
            plugViewHolder.title.setAlpha(0.9f);
            setSelectGradient(plugViewHolder.root, item, i);
        } else {
            plugViewHolder.head.setBackgroundResource(item.getDrawables()[1]);
            plugViewHolder.title.setAlpha(0.6f);
            setNormalGradient(plugViewHolder.root, item);
        }
        return view;
    }

    public void notifyItemChanged(int i) {
        this.mSelectedPosition = i;
        this.mReversePosition = getCount() - this.mSelectedPosition;
        notifyTrack(i);
    }

    public void updateViewNormal(View view, int i, int i2) {
        if (view == null) {
            return;
        }
        PlugData item = getItem(i2);
        PlugViewHolder plugViewHolder = (PlugViewHolder) view.getTag();
        plugViewHolder.head.setBackgroundResource(item.getDrawables()[1]);
        plugViewHolder.title.setAlpha(0.6f);
        setNormalGradient(plugViewHolder.root, item);
    }

    public void updateViewSelected(View view, int i) {
        if (view == null) {
            return;
        }
        PlugData item = getItem(i);
        PlugViewHolder plugViewHolder = (PlugViewHolder) view.getTag();
        plugViewHolder.head.setBackgroundResource(item.getDrawables()[0]);
        plugViewHolder.title.setAlpha(0.9f);
        setSelectGradient(plugViewHolder.root, item, i);
    }
}
