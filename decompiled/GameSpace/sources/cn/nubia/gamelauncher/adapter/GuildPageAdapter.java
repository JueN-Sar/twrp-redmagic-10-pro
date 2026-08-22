package cn.nubia.gamelauncher.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.guide.GuideBean;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class GuildPageAdapter extends RecyclerView.Adapter {
    GuidePageHolder mHasAddViewHolder;
    ArrayList<GuideBean> mList;
    private Runnable mRunnable;

    class GuidePageHolder extends RecyclerView.ViewHolder {
        public TextView explain;
        public ImageView logo;
        public ImageView slidLeft;
        public ImageView slidMid;
        public ImageView slidRight;
        public Button startBtn;
        public TextView title;

        private GuidePageHolder(View view) {
            super(view);
            this.logo = (ImageView) view.findViewById(R.id.guide_logo);
            this.title = (TextView) view.findViewById(R.id.guide_title);
            this.explain = (TextView) view.findViewById(R.id.guide_explain);
            this.slidLeft = (ImageView) view.findViewById(R.id.guide_slid_left);
            this.slidMid = (ImageView) view.findViewById(R.id.guide_slid_mid);
            this.slidRight = (ImageView) view.findViewById(R.id.guide_slid_right);
            if (GameSpaceConfig.supportBase()) {
                this.slidRight.setVisibility(0);
            } else {
                this.slidRight.setVisibility(8);
            }
            this.startBtn = (Button) view.findViewById(R.id.guide_start);
        }
    }

    public GuildPageAdapter(ArrayList<GuideBean> arrayList, Runnable runnable) {
        this.mRunnable = runnable;
        this.mList = arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        GuidePageHolder guidePageHolder = (GuidePageHolder) viewHolder;
        this.mHasAddViewHolder = guidePageHolder;
        guidePageHolder.logo.setBackgroundResource(this.mList.get(i).getLogoId());
        this.mHasAddViewHolder.explain.setText(this.mList.get(i).getExplain());
        this.mHasAddViewHolder.title.setText(this.mList.get(i).getTitle());
        this.mHasAddViewHolder.startBtn.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.adapter.GuildPageAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (GuildPageAdapter.this.mRunnable != null) {
                    GuildPageAdapter.this.mRunnable.run();
                }
                GuildPageAdapter.this.mHasAddViewHolder.itemView.setVisibility(8);
            }
        });
        if (i != this.mList.size() - 1) {
            this.mHasAddViewHolder.startBtn.setVisibility(4);
        } else {
            this.mHasAddViewHolder.startBtn.setVisibility(0);
            this.mHasAddViewHolder.slidLeft.setVisibility(8);
            this.mHasAddViewHolder.slidMid.setVisibility(8);
            this.mHasAddViewHolder.slidRight.setVisibility(8);
        }
        if (i == 0) {
            this.mHasAddViewHolder.slidLeft.setBackgroundResource(R.mipmap.guild_page_slide_light);
            this.mHasAddViewHolder.slidMid.setBackgroundResource(R.mipmap.guild_page_slide_default);
            this.mHasAddViewHolder.slidRight.setBackgroundResource(R.mipmap.guild_page_slide_default);
            this.mHasAddViewHolder.startBtn.setVisibility(4);
        } else if (i == 1) {
            this.mHasAddViewHolder.slidLeft.setBackgroundResource(R.mipmap.guild_page_slide_default);
            this.mHasAddViewHolder.slidMid.setBackgroundResource(R.mipmap.guild_page_slide_light);
            this.mHasAddViewHolder.slidRight.setBackgroundResource(R.mipmap.guild_page_slide_default);
        } else if (i == 2) {
            this.mHasAddViewHolder.slidLeft.setBackgroundResource(R.mipmap.guild_page_slide_default);
            this.mHasAddViewHolder.slidMid.setBackgroundResource(R.mipmap.guild_page_slide_default);
            this.mHasAddViewHolder.slidRight.setBackgroundResource(R.mipmap.guild_page_slide_light);
        }
        if (CommonUtil.isSlenderPhone()) {
            this.mHasAddViewHolder.startBtn.setScaleX(0.9f);
            this.mHasAddViewHolder.startBtn.setScaleY(0.9f);
            this.mHasAddViewHolder.startBtn.setTranslationY(10.0f);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new GuidePageHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.guild_page_item_layout, viewGroup, false));
    }
}
