package cn.nubia.gamelauncher.gamecontrolpanel;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.util.ToastUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class PlugAdapter extends BaseAdapter {
    private Context mContext;
    private Intent mIntent;
    private int mLayoutRes;
    private List<GamePlugData> mPlugList;
    private int mReversePosition;
    private int mSelectedPosition;

    private class PlugViewHolder {
        private MarqueeTextView content;
        private ImageView root;
        private MarqueeTextView use;

        private PlugViewHolder() {
        }
    }

    public PlugAdapter(Context context, int i, List<GamePlugData> list) {
        new ArrayList();
        this.mSelectedPosition = 0;
        this.mReversePosition = 0;
        this.mContext = context;
        this.mLayoutRes = i;
        this.mPlugList = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mPlugList.size();
    }

    @Override // android.widget.Adapter
    public GamePlugData getItem(int i) {
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
        final PlugViewHolder plugViewHolder;
        final GamePlugData item = getItem(i);
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(this.mLayoutRes, viewGroup, false);
            plugViewHolder = new PlugViewHolder();
            plugViewHolder.root = (ImageView) view.findViewById(R.id.root);
            plugViewHolder.content = (MarqueeTextView) view.findViewById(R.id.content);
            plugViewHolder.use = (MarqueeTextView) view.findViewById(R.id.use);
            view.setTag(plugViewHolder);
        } else {
            plugViewHolder = (PlugViewHolder) view.getTag();
        }
        plugViewHolder.content.setText(item.getContentId());
        plugViewHolder.root.setBackgroundResource(item.getDrawables());
        if (Settings.Global.getInt(this.mContext.getContentResolver(), item.getKey(), 1) == 0 || !item.isEnabled) {
            plugViewHolder.use.setSelected(false);
            plugViewHolder.root.setEnabled(false);
            plugViewHolder.use.setAlpha(0.5f);
            plugViewHolder.content.setAlpha(0.5f);
            plugViewHolder.use.setText(R.string.plug_btn_text);
        } else {
            plugViewHolder.use.setSelected(true);
            plugViewHolder.root.setEnabled(true);
            plugViewHolder.use.setAlpha(1.0f);
            plugViewHolder.content.setAlpha(1.0f);
            plugViewHolder.use.setText(R.string.plug_btn_texting);
        }
        if (item.isEnabled) {
            plugViewHolder.use.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.PlugAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (Settings.Global.getInt(PlugAdapter.this.mContext.getContentResolver(), item.getKey(), 1) == 0) {
                        Toast.makeText(PlugAdapter.this.mContext, R.string.plug_toast_texting, 0).show();
                        plugViewHolder.use.setSelected(true);
                        plugViewHolder.root.setEnabled(true);
                        plugViewHolder.use.setText(R.string.plug_btn_texting);
                        plugViewHolder.use.setAlpha(1.0f);
                        plugViewHolder.content.setAlpha(1.0f);
                        Settings.Global.putInt(PlugAdapter.this.mContext.getContentResolver(), item.getKey(), 1);
                        return;
                    }
                    plugViewHolder.use.setSelected(false);
                    plugViewHolder.root.setEnabled(false);
                    plugViewHolder.use.setText(R.string.plug_btn_text);
                    plugViewHolder.use.setAlpha(0.5f);
                    plugViewHolder.content.setAlpha(0.5f);
                    Settings.Global.putInt(PlugAdapter.this.mContext.getContentResolver(), item.getKey(), 0);
                    item.deactivatePluginFunction(PlugAdapter.this.mContext);
                }
            });
        } else {
            plugViewHolder.use.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.PlugAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ToastUtil.showGamemodeToast(PlugAdapter.this.mContext.getResources().getString(R.string.performance_low_red_magic_toast));
                }
            });
        }
        return view;
    }

    public void notifyItemChanged(int i) {
        this.mSelectedPosition = i;
        this.mReversePosition = getCount() - this.mSelectedPosition;
    }

    public void updateViewNormal(View view, int i) {
        if (view == null) {
            return;
        }
        ((PlugViewHolder) view.getTag()).root.setBackground(this.mContext.getDrawable(R.drawable.plug_normal_bg));
    }

    public void updateViewSelected(View view, int i) {
        if (view == null) {
            return;
        }
        ((PlugViewHolder) view.getTag()).root.setBackground(this.mContext.getDrawable(R.drawable.plug_root_selected_bg));
    }
}
