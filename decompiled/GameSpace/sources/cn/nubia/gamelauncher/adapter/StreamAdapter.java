package cn.nubia.gamelauncher.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.helper.CardHelper;
import cn.nubia.gamelauncher.view.HandheldItemLayout;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class StreamAdapter extends RecyclerView.Adapter {
    private static final String TAG = "Handheld";
    CardHelper mCardHelper;
    private final Context mContext;
    ArrayList<StreamBean> mList = new ArrayList<>();

    class HandheldHolder extends RecyclerView.ViewHolder {
        public ImageView mBanner;
        public HandheldItemLayout mBorder;
        public View mContentView;
        public TextView mName;
        public int position;

        public HandheldHolder(View view, View view2, int i) {
            super(view);
            this.mBorder = (HandheldItemLayout) view.findViewById(R.id.handheld_border);
            this.mName = (TextView) view.findViewById(R.id.handheld_stream_name);
            this.mBorder.setFocusable(true);
            this.mBorder.setFocusableInTouchMode(true);
            setOnFocusChangeListener();
            this.mContentView = view2;
            this.mBorder.setContentView(view2);
            this.mBanner = (ImageView) view2.findViewById(R.id.stream_banner);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onTouchBorder(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 1) {
                return;
            }
            this.mBorder.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.adapter.StreamAdapter.HandheldHolder.3
                @Override // java.lang.Runnable
                public void run() {
                    if (HandheldHolder.this.mBorder.hasFocus()) {
                        StreamAdapter.this.clickItem(HandheldHolder.this.position);
                    }
                }
            }, 467L);
        }

        public void setOnFocusChangeListener() {
            this.mBorder.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: cn.nubia.gamelauncher.adapter.StreamAdapter.HandheldHolder.1
                @Override // android.view.View.OnFocusChangeListener
                public void onFocusChange(View view, boolean z) {
                    HandheldHolder.this.mBorder.setSelect(z, HandheldHolder.this.mName, true);
                    HandheldHolder.this.mName.setAlpha(z ? 1.0f : 0.0f);
                }
            });
            this.mBorder.setOnTouchListener(new View.OnTouchListener() { // from class: cn.nubia.gamelauncher.adapter.StreamAdapter.HandheldHolder.2
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    HandheldHolder.this.onTouchBorder(motionEvent);
                    return false;
                }
            });
        }
    }

    private class StreamBean {
        int imageId;
        int nameId;

        public StreamBean(int i, int i2) {
            this.imageId = i;
            this.nameId = i2;
        }
    }

    public StreamAdapter(Context context, CardHelper cardHelper) {
        this.mContext = context;
        this.mCardHelper = cardHelper;
        createList();
        Log.d("Handheld", "---->HostGameAdapter() mList.size() : " + this.mList.size());
    }

    private void addItemClickListener(final int i, View view) {
        view.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.adapter.StreamAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                StreamAdapter.this.clickItem(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickItem(int i) {
        if (i == 0) {
            CommonUtil.startStreamPlay(this.mContext);
        } else {
            CommonUtil.startPcPlay(this.mContext);
        }
    }

    private void createList() {
        this.mList.add(new StreamBean(R.mipmap.handheld_stream, R.string.large_game_by_stream_title));
        this.mList.add(new StreamBean(R.mipmap.handheld_pc, R.string.large_game_by_pc_title));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        StreamBean streamBean = this.mList.get(i);
        HandheldHolder handheldHolder = (HandheldHolder) viewHolder;
        handheldHolder.position = i;
        handheldHolder.mBorder.setSquare(false);
        handheldHolder.mBanner.setBackgroundResource(streamBean.imageId);
        handheldHolder.mName.setAlpha(handheldHolder.mBorder.hasFocus() ? 1.0f : 0.0f);
        handheldHolder.mName.setText(streamBean.nameId);
        handheldHolder.mBanner.setVisibility(0);
        addItemClickListener(i, handheldHolder.mBorder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new HandheldHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.handheld_stream_layout, viewGroup, false), LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.handheld_stream_image, viewGroup, false), i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof HandheldHolder) {
            ((HandheldHolder) viewHolder).mName.setText("");
        }
        super.onViewRecycled(viewHolder);
    }
}
