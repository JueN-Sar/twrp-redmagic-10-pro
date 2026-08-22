package cn.nubia.gamelauncher.recycler;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Trace;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.util.BitmapUtils;
import cn.nubia.common.view.ProgressView;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.bean.NeoIconDownloadInfo;
import cn.nubia.gamelauncher.commoninterface.NeoGameDBColumns;
import cn.nubia.gamelauncher.controller.NeoDownloadManager;
import cn.nubia.gamelauncher.helper.Controller;
import cn.nubia.gamelauncher.helper.LobbySoundPoolHelper;
import cn.nubia.gamelauncher.model.NeoDownloadHelper;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.util.GameCenterHelper;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import cn.nubia.gamelauncher.view.SelectedScaleView;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class FullListAdapter extends RecyclerView.Adapter {
    private static final String TAG = "Full";
    private Context mContext;
    CopyOnWriteArrayList<AppListItemBean> mList;
    AppClickListener mListener;
    PropertyManager mProperty;
    int mSelectedPosition = 0;
    private HashMap<Integer, WeakReference<FullGameHolder>> mNeoDownloadIconMap = new HashMap<>();

    public interface AppClickListener {
        void onSelectedItemChanged(int i, int i2);
    }

    public class FullGameHolder extends RecyclerView.ViewHolder {
        public ImageView mBg;
        TextView mDays;
        public ImageView mFrame;
        public ImageView mIcon;
        public TextView mName;
        public ProgressView mProgressView;
        TextView mSize;
        TextView mTime;
        public SelectedScaleView mView;
        public ImageView mVip;

        public FullGameHolder(View view) {
            super(view);
            this.mView = (SelectedScaleView) view;
            this.mBg = (ImageView) view.findViewById(R.id.item_selected_frame);
            this.mIcon = (ImageView) view.findViewById(R.id.icon);
            this.mVip = (ImageView) view.findViewById(R.id.pure_vip);
            this.mName = (TextView) view.findViewById(R.id.full_item_game_name);
            this.mFrame = (ImageView) view.findViewById(R.id.game_selected_frame);
            this.mProgressView = (ProgressView) view.findViewById(R.id.icon_progress);
            this.mTime = (TextView) view.findViewById(R.id.property_time_value);
            this.mSize = (TextView) view.findViewById(R.id.property_size_value);
            this.mDays = (TextView) view.findViewById(R.id.property_days_value);
        }
    }

    public FullListAdapter(Context context, CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList, AppClickListener appClickListener) {
        this.mContext = context;
        this.mList = copyOnWriteArrayList;
        this.mListener = appClickListener;
        this.mProperty = new PropertyManager(this.mContext);
        LogUtil.d(TAG, "---->FullListAdapter() mList.size() : " + this.mList.size());
    }

    private void bindDownloadGame(FullGameHolder fullGameHolder, int i, AppListItemBean appListItemBean) {
        if (fullGameHolder == null || appListItemBean == null) {
            return;
        }
        NeoIconDownloadInfo downloadInfo = appListItemBean.getDownloadInfo();
        this.mNeoDownloadIconMap.put(Integer.valueOf(downloadInfo.appId), new WeakReference<>(fullGameHolder));
        fullGameHolder.mIcon.setTag(Integer.valueOf(downloadInfo.appId));
        fullGameHolder.mIcon.setImageBitmap(downloadInfo.mCropIcon);
        fullGameHolder.mProgressView.setVisibility(0);
        LogUtil.d("progress", "bindDownloadGame() state : " + downloadInfo.status);
        fullGameHolder.mProgressView.setProgress(downloadInfo.progress, NeoGameDBColumns.STATUS_DOWNLOADING.equals(downloadInfo.status));
    }

    private void bindGame(FullGameHolder fullGameHolder, final int i) {
        AppListItemBean appListItemBean = this.mList.get(i);
        boolean z = !appListItemBean.isShortcut();
        Bitmap icon = appListItemBean.getIcon();
        ImageView imageView = fullGameHolder.mIcon;
        if (!appListItemBean.isAddItem()) {
            icon = BitmapUtils.bitmapRound(icon, 34.0f);
        }
        imageView.setImageBitmap(icon);
        fullGameHolder.mView.setSupportProperty(z);
        fullGameHolder.mView.setType(appListItemBean.isAddItem() ? 1 : i - this.mSelectedPosition);
        fullGameHolder.mVip.setVisibility(appListItemBean.isVip ? 0 : 8);
        LogUtil.d(TAG, "---->bindGame() item : " + appListItemBean.getName() + ", position : " + i + ", selected : " + this.mSelectedPosition + ", supportProperty : " + z);
        fullGameHolder.mName.setText(appListItemBean.getName());
        if (!appListItemBean.isAddItem() && i - this.mSelectedPosition == 0) {
            this.mProperty.updateProperty(appListItemBean, fullGameHolder.mView);
        }
        if (appListItemBean.isDownloadItem()) {
            bindDownloadGame(fullGameHolder, i, appListItemBean);
        } else {
            fullGameHolder.mProgressView.setVisibility(8);
        }
        (Controller.getInstance().isPureMode() ? fullGameHolder.mView : fullGameHolder.mBg).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.recycler.FullListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FullListAdapter.this.clickItem(i);
            }
        });
        fullGameHolder.mVip.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.recycler.FullListAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameCenterHelper.startVip(FullListAdapter.this.mContext);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickItem(int i) {
        AppListItemBean appListItemBean = this.mList.get(i);
        if (appListItemBean.isAddItem()) {
            clickManagerGame();
            return;
        }
        if (i != this.mSelectedPosition) {
            LogUtil.d(TAG, "---->onClick() and the selected Position changed  " + this.mSelectedPosition + " -> " + i);
            this.mListener.onSelectedItemChanged(this.mSelectedPosition, i);
            this.mSelectedPosition = i;
        } else if (appListItemBean.isDownloadItem()) {
            LogUtil.d(NeoDownloadHelper.TAG, "onClick() - doClick() status : " + appListItemBean.getDownloadInfo().status);
            NeoDownloadManager.getInstance().doClick(appListItemBean.getDownloadInfo());
        }
    }

    private void clickManagerGame() {
        LogUtil.i(TAG, "clickManagerGame()");
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setComponent(CommonUtil.createComponentName("cn.nubia.gamelauncher,cn.nubia.gamelauncher.activity.AppAddActivity"));
        this.mContext.startActivity(intent);
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "gamespace_management_game");
        LobbySoundPoolHelper.getInstance().play();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    public FullGameHolder getValue(int i) {
        WeakReference<FullGameHolder> weakReference = this.mNeoDownloadIconMap.get(Integer.valueOf(i));
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Log.d("pure", "---->onBindViewHolder() position : " + i);
        Trace.beginSection("onBindViewHolder");
        bindGame((FullGameHolder) viewHolder, i);
        Trace.endSection();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.d("pure", "---->onCreateViewHolder() mList.size() : " + Controller.getInstance().isPureMode());
        return new FullGameHolder(LayoutInflater.from(viewGroup.getContext()).inflate(Controller.getInstance().isPureMode() ? R.layout.game_item_full_pure : R.layout.game_item_full, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder != null && (viewHolder instanceof FullGameHolder)) {
            FullGameHolder fullGameHolder = (FullGameHolder) viewHolder;
            fullGameHolder.mFrame.setVisibility(8);
            fullGameHolder.mView.setSelected(false);
            fullGameHolder.mView.setSupportProperty(false);
        }
        super.onViewRecycled(viewHolder);
    }

    public void updateNeoDownloadIcon(AppListItemBean appListItemBean, FullGameHolder fullGameHolder) {
        NeoIconDownloadInfo downloadInfo = appListItemBean.getDownloadInfo();
        if (downloadInfo == null) {
            return;
        }
        if (fullGameHolder == null) {
            fullGameHolder = getValue(downloadInfo.appId);
        }
        if (fullGameHolder == null) {
            return;
        }
        ImageView imageView = fullGameHolder.mIcon;
        fullGameHolder.mIcon.setImageBitmap(downloadInfo.mCropIcon);
        if (imageView.getTag() == null || !imageView.getTag().equals(Integer.valueOf(downloadInfo.appId))) {
            return;
        }
        fullGameHolder.mProgressView.setProgress(downloadInfo.progress, NeoGameDBColumns.STATUS_DOWNLOADING.equals(downloadInfo.status));
    }

    public void updateSelectedPosition(int i) {
        this.mSelectedPosition = i;
    }
}
