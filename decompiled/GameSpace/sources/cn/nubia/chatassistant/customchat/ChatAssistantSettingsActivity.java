package cn.nubia.chatassistant.customchat;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.media3.common.C;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.chatassistant.adapter.ChatAssistantSettingContentAdapter;
import cn.nubia.chatassistant.adapter.ChatAssistantSettingTitleAdapter;
import cn.nubia.chatassistant.bean.ChatAssistantVoiceBean;
import cn.nubia.chatassistant.bean.ChatAssistantVoicePackBean;
import cn.nubia.chatassistant.db.ChatAssistantBean;
import cn.nubia.chatassistant.db.DBManager;
import cn.nubia.chatassistant.db.DBOpenHelper;
import cn.nubia.chatassistant.util.AssetsUtils;
import cn.nubia.chatassistant.util.FileUtils;
import cn.nubia.chatassistant.util.LogUtils;
import cn.nubia.chatassistant.util.ReportUtils;
import cn.nubia.chatassistant.util.ToastUtils;
import cn.nubia.gamelauncher.R;
import cn.nubia.globalsearch.GlobalSearchConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class ChatAssistantSettingsActivity extends Activity implements ChatAssistantSettingTitleAdapter.OnVoicePackSelectedListener, View.OnClickListener, ChatAssistantSettingTitleAdapter.OnDragStartListener {
    public static int ASSETS_FILE_COUNT = 3;
    public static final int DELETE_VOICE_PACK_REQUEST_CODE = 901;
    public static final int DELETE_VOICE_REQUEST_CODE = 902;
    private static final String MAGIC = "魔姬";
    public static final int REQUEST_CODE = 900;
    private static final String TAG = "ChatAssistantSettingsActivity";
    private ImageView mAddVoicePackButton;
    private ChatAssistantSettingContentAdapter mChatAssistantSettingContentAdapter;
    private ChatAssistantSettingTitleAdapter mChatAssistantSettingTitleAdapter;
    private View mRecorderFinishLayout;
    private Handler mRecycleViewRefreshHandler;
    private ImageView mUploadButton;
    private RecyclerView mVoicePackRecycler;
    private RecyclerView mVoiceRecycler;
    private int voiceSize = 0;
    private int voicePackSize = 0;
    private List<ChatAssistantVoicePackBean> mChatAssistantVoicePackBeanList = new ArrayList();
    private List<ChatAssistantVoiceBean> mChatAssistantVoiceBeanList = new ArrayList();
    private ChatAssistantVoicePackBean chatAssistantVoicePackBean = new ChatAssistantVoicePackBean();
    private Comparator<ChatAssistantVoicePackBean> comparator = new Comparator<ChatAssistantVoicePackBean>() { // from class: cn.nubia.chatassistant.customchat.ChatAssistantSettingsActivity.1
        @Override // java.util.Comparator
        public int compare(ChatAssistantVoicePackBean chatAssistantVoicePackBean, ChatAssistantVoicePackBean chatAssistantVoicePackBean2) {
            if (chatAssistantVoicePackBean.getPosition() > chatAssistantVoicePackBean2.getPosition()) {
                return 1;
            }
            return chatAssistantVoicePackBean.getPosition() < chatAssistantVoicePackBean2.getPosition() ? -1 : 0;
        }
    };
    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() { // from class: cn.nubia.chatassistant.customchat.ChatAssistantSettingsActivity.5
        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            LogUtils.i(ChatAssistantSettingsActivity.TAG, "clearView: ");
            viewHolder.itemView.setBackgroundColor(0);
            ChatAssistantSettingsActivity.this.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(recyclerView.getLayoutManager() instanceof GridLayoutManager ? 15 : recyclerView.getLayoutManager() instanceof LinearLayoutManager ? 3 : 0, 0);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean isLongPressDragEnabled() {
            return false;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
            int adapterPosition = viewHolder.getAdapterPosition();
            int adapterPosition2 = viewHolder2.getAdapterPosition();
            LogUtils.i(ChatAssistantSettingsActivity.TAG, "onMove toPosition: " + adapterPosition2 + "  fromPosition: " + adapterPosition);
            Settings.Global.putInt(ChatAssistantSettingsActivity.this.getContentResolver(), "played_voice_pack", 0);
            Settings.Global.putInt(ChatAssistantSettingsActivity.this.getContentResolver(), "played_voice", 0);
            Settings.Global.putInt(ChatAssistantSettingsActivity.this.getContentResolver(), "played_voice_pack_temp", 0);
            Settings.Global.putInt(ChatAssistantSettingsActivity.this.getContentResolver(), "played_voice_temp", 0);
            ChatAssistantSettingsActivity.this.updateVoicePackPosition(adapterPosition, adapterPosition2);
            ChatAssistantSettingsActivity.this.mChatAssistantSettingTitleAdapter.notifyItemMoved(adapterPosition, adapterPosition2);
            return true;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
            if (i != 0) {
                ((Vibrator) ChatAssistantSettingsActivity.this.getSystemService("vibrator")).vibrate(70L);
            }
            super.onSelectedChanged(viewHolder, i);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        }
    });

    private void deleteAllVoiceForVoicePackage(String str) {
        Cursor cursor = null;
        try {
            Cursor queryAllData = DBManager.getInstance(getApplicationContext()).queryAllData();
            if (queryAllData == null) {
                if (queryAllData != null) {
                    queryAllData.close();
                    return;
                }
                return;
            }
            while (queryAllData.moveToNext()) {
                String string = queryAllData.getString(queryAllData.getColumnIndex(DBOpenHelper.VOICE_PACK_NAME));
                if (TextUtils.isEmpty(string)) {
                    LogUtils.i(TAG, "deleteAllVoiceForVoicePackage voicePackName is null");
                } else if (string.equals(str)) {
                    File file = new File(queryAllData.getString(queryAllData.getColumnIndex(DBOpenHelper.VOICE_FILE_PATH)));
                    if (file.exists()) {
                        LogUtils.i(TAG, "deleteAllVoiceForVoicePackage deleteFilePath: " + file);
                        file.delete();
                    }
                }
            }
            if (queryAllData != null) {
                queryAllData.close();
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    private void initData() {
        this.mRecycleViewRefreshHandler = new Handler();
        queryAllVoicePackData();
        Collections.sort(this.mChatAssistantVoicePackBeanList, this.comparator);
        ChatAssistantSettingTitleAdapter chatAssistantSettingTitleAdapter = new ChatAssistantSettingTitleAdapter(this, this.mChatAssistantVoicePackBeanList, this);
        this.mChatAssistantSettingTitleAdapter = chatAssistantSettingTitleAdapter;
        this.mVoicePackRecycler.setAdapter(chatAssistantSettingTitleAdapter);
        this.itemTouchHelper.attachToRecyclerView(this.mVoicePackRecycler);
        this.mChatAssistantSettingTitleAdapter.setOnVoicePackSelectedListener(this);
        this.mChatAssistantSettingTitleAdapter.setOnDragStartListener(this);
        ChatAssistantSettingContentAdapter chatAssistantSettingContentAdapter = new ChatAssistantSettingContentAdapter(this, this.mChatAssistantVoiceBeanList, this);
        this.mChatAssistantSettingContentAdapter = chatAssistantSettingContentAdapter;
        this.mVoiceRecycler.setAdapter(chatAssistantSettingContentAdapter);
    }

    private void initView() {
        this.mRecorderFinishLayout = findViewById(R.id.rl_recorder_finish_layout);
        this.mUploadButton = (ImageView) findViewById(R.id.iv_upload_voice);
        this.mAddVoicePackButton = (ImageView) findViewById(R.id.iv_add_voice_pack);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_voice_pack);
        this.mVoicePackRecycler = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.rv_voice);
        this.mVoiceRecycler = recyclerView2;
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
    }

    private boolean isUpdateVoicePackData() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDataSetChanged() {
        this.mRecycleViewRefreshHandler.post(new Runnable() { // from class: cn.nubia.chatassistant.customchat.ChatAssistantSettingsActivity.6
            @Override // java.lang.Runnable
            public void run() {
                ChatAssistantSettingsActivity.this.mChatAssistantSettingTitleAdapter.refreshDate(ChatAssistantSettingsActivity.this.queryAllVoicePackDataToRefresh());
            }
        });
    }

    private void queryAllData() {
        Cursor queryAllData = DBManager.getInstance(getApplicationContext()).queryAllData();
        if (queryAllData != null) {
            if (queryAllData.getCount() <= 0) {
                findViewById(R.id.gt_loading_text).setVisibility(0);
                this.mVoicePackRecycler.postDelayed(new Runnable() { // from class: cn.nubia.chatassistant.customchat.ChatAssistantSettingsActivity.2
                    @Override // java.lang.Runnable
                    public void run() {
                        ChatAssistantSettingsActivity.this.findViewById(R.id.gt_loading_text).setVisibility(8);
                    }
                }, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
            }
            queryAllData.close();
        }
    }

    private void queryAllVoiceData(String str) {
        this.voiceSize = 0;
        Cursor cursor = null;
        try {
            Cursor queryDataByVoicePackName = DBManager.getInstance(getApplicationContext()).queryDataByVoicePackName(str);
            if (queryDataByVoicePackName == null) {
                if (queryDataByVoicePackName != null) {
                    queryDataByVoicePackName.close();
                    return;
                }
                return;
            }
            while (queryDataByVoicePackName.moveToNext()) {
                String string = queryDataByVoicePackName.getString(queryDataByVoicePackName.getColumnIndex(DBOpenHelper.VOICE_FILE_NAME));
                ChatAssistantVoiceBean chatAssistantVoiceBean = new ChatAssistantVoiceBean();
                chatAssistantVoiceBean.setSystemDefault(Integer.valueOf(queryDataByVoicePackName.getString(queryDataByVoicePackName.getColumnIndex(DBOpenHelper.VOICE_SYSTEM))).intValue() == 0);
                chatAssistantVoiceBean.setVoiceFileName(queryDataByVoicePackName.getString(queryDataByVoicePackName.getColumnIndex(DBOpenHelper.VOICE_FILE_NAME)));
                chatAssistantVoiceBean.setVoiceFilePath(queryDataByVoicePackName.getString(queryDataByVoicePackName.getColumnIndex(DBOpenHelper.VOICE_FILE_PATH)));
                if (!TextUtils.isEmpty(string)) {
                    this.mChatAssistantVoiceBeanList.add(chatAssistantVoiceBean);
                    this.voiceSize++;
                }
            }
            this.mUploadButton.setAlpha(this.voiceSize < 10 ? 1.0f : 0.5f);
            if (queryDataByVoicePackName != null) {
                queryDataByVoicePackName.close();
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    private void queryAllVoiceDataToRefresh() {
        this.mChatAssistantVoiceBeanList.clear();
        queryAllVoiceData(this.chatAssistantVoicePackBean.getVoicePackName());
    }

    private void queryAllVoicePackData() {
        this.voicePackSize = 0;
        Cursor cursor = null;
        try {
            Cursor queryAllVoicePackData = DBManager.getInstance(getApplicationContext()).queryAllVoicePackData();
            if (queryAllVoicePackData == null) {
                if (queryAllVoicePackData != null) {
                    queryAllVoicePackData.close();
                    return;
                }
                return;
            }
            Settings.Global.putInt(getContentResolver(), "voice_pack_quantity", queryAllVoicePackData.getCount());
            String str = "";
            while (queryAllVoicePackData.moveToNext()) {
                str = queryAllVoicePackData.getString(queryAllVoicePackData.getColumnIndex(DBOpenHelper.VOICE_PACK_NAME));
                ChatAssistantVoicePackBean chatAssistantVoicePackBean = new ChatAssistantVoicePackBean(str);
                chatAssistantVoicePackBean.setShow(Integer.valueOf(queryAllVoicePackData.getString(queryAllVoicePackData.getColumnIndex(DBOpenHelper.VOICE_PACK_SHOW))).intValue() == 0);
                chatAssistantVoicePackBean.setSystemDefault(Integer.valueOf(queryAllVoicePackData.getString(queryAllVoicePackData.getColumnIndex(DBOpenHelper.VOICE_PACK_SYSTEM))).intValue() == 0);
                chatAssistantVoicePackBean.setPosition(Integer.valueOf(queryAllVoicePackData.getString(queryAllVoicePackData.getColumnIndex(DBOpenHelper.VOICE_PACK_POSITION))).intValue());
                this.mChatAssistantVoicePackBeanList.add(chatAssistantVoicePackBean);
                this.voicePackSize++;
            }
            List<ChatAssistantVoicePackBean> list = this.mChatAssistantVoicePackBeanList;
            if (list != null) {
                this.chatAssistantVoicePackBean = list.get(0);
            }
            this.mAddVoicePackButton.setAlpha(this.voicePackSize < 10 ? 1.0f : 0.5f);
            queryAllVoiceData(str);
            if (queryAllVoicePackData != null) {
                queryAllVoicePackData.close();
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    private List<ChatAssistantVoicePackBean> queryAllVoicePackDataToAdd() {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = DBManager.getInstance(this).queryAllVoicePackData();
            LogUtils.i(TAG, "chatAssistantVoicePackBeanList cursor: " + cursor);
            if (cursor == null) {
                return arrayList;
            }
            Settings.Global.putInt(getContentResolver(), "voice_pack_quantity", cursor.getCount());
            while (cursor.moveToNext()) {
                ChatAssistantVoicePackBean chatAssistantVoicePackBean = new ChatAssistantVoicePackBean(cursor.getString(cursor.getColumnIndex(DBOpenHelper.VOICE_PACK_NAME)));
                boolean z = true;
                chatAssistantVoicePackBean.setShow(Integer.valueOf(cursor.getString(cursor.getColumnIndex(DBOpenHelper.VOICE_PACK_SHOW))).intValue() == 0);
                if (Integer.valueOf(cursor.getString(cursor.getColumnIndex(DBOpenHelper.VOICE_PACK_SYSTEM))).intValue() != 0) {
                    z = false;
                }
                chatAssistantVoicePackBean.setSystemDefault(z);
                chatAssistantVoicePackBean.setPosition(Integer.valueOf(cursor.getString(cursor.getColumnIndex(DBOpenHelper.VOICE_PACK_POSITION))).intValue());
                arrayList.add(chatAssistantVoicePackBean);
            }
            if (cursor != null) {
                cursor.close();
            }
            Comparator<ChatAssistantVoicePackBean> comparator = new Comparator<ChatAssistantVoicePackBean>() { // from class: cn.nubia.chatassistant.customchat.ChatAssistantSettingsActivity.3
                @Override // java.util.Comparator
                public int compare(ChatAssistantVoicePackBean chatAssistantVoicePackBean2, ChatAssistantVoicePackBean chatAssistantVoicePackBean3) {
                    if (chatAssistantVoicePackBean2.getPosition() > chatAssistantVoicePackBean3.getPosition()) {
                        return 1;
                    }
                    return chatAssistantVoicePackBean2.getPosition() < chatAssistantVoicePackBean3.getPosition() ? -1 : 0;
                }
            };
            LogUtils.i(TAG, "chatAssistantVoicePackBeanList: " + arrayList.size());
            Collections.sort(arrayList, comparator);
            return arrayList;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<ChatAssistantVoicePackBean> queryAllVoicePackDataToRefresh() {
        this.voicePackSize = 0;
        this.mChatAssistantVoicePackBeanList.clear();
        Cursor cursor = null;
        try {
            Cursor queryAllVoicePackData = DBManager.getInstance(getApplicationContext()).queryAllVoicePackData();
            if (queryAllVoicePackData == null) {
                List<ChatAssistantVoicePackBean> list = this.mChatAssistantVoicePackBeanList;
                if (queryAllVoicePackData != null) {
                    queryAllVoicePackData.close();
                }
                return list;
            }
            Settings.Global.putInt(getContentResolver(), "voice_pack_quantity", queryAllVoicePackData.getCount());
            while (queryAllVoicePackData.moveToNext()) {
                ChatAssistantVoicePackBean chatAssistantVoicePackBean = new ChatAssistantVoicePackBean(queryAllVoicePackData.getString(queryAllVoicePackData.getColumnIndex(DBOpenHelper.VOICE_PACK_NAME)));
                chatAssistantVoicePackBean.setShow(Integer.valueOf(queryAllVoicePackData.getString(queryAllVoicePackData.getColumnIndex(DBOpenHelper.VOICE_PACK_SHOW))).intValue() == 0);
                chatAssistantVoicePackBean.setSystemDefault(Integer.valueOf(queryAllVoicePackData.getString(queryAllVoicePackData.getColumnIndex(DBOpenHelper.VOICE_PACK_SYSTEM))).intValue() == 0);
                chatAssistantVoicePackBean.setPosition(Integer.valueOf(queryAllVoicePackData.getString(queryAllVoicePackData.getColumnIndex(DBOpenHelper.VOICE_PACK_POSITION))).intValue());
                this.mChatAssistantVoicePackBeanList.add(chatAssistantVoicePackBean);
                this.voicePackSize++;
            }
            Collections.sort(this.mChatAssistantVoicePackBeanList, this.comparator);
            this.mAddVoicePackButton.setAlpha(this.voicePackSize < 10 ? 1.0f : 0.5f);
            if (queryAllVoicePackData != null) {
                queryAllVoicePackData.close();
            }
            return this.mChatAssistantVoicePackBeanList;
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    private void setShortEdges() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        getWindow().setAttributes(attributes);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateVoicePackPosition(int i, int i2) {
        if (i < i2) {
            int i3 = i;
            while (i3 < i2) {
                int i4 = i3 + 1;
                Collections.swap(this.mChatAssistantVoicePackBeanList, i3, i4);
                i3 = i4;
            }
        } else {
            for (int i5 = i; i5 > i2; i5--) {
                Collections.swap(this.mChatAssistantVoicePackBeanList, i5, i5 - 1);
            }
        }
        Cursor queryDataByPosition = DBManager.getInstance(getApplicationContext()).queryDataByPosition(i2);
        String str = "";
        String str2 = "";
        while (queryDataByPosition.moveToNext()) {
            str2 = queryDataByPosition.getString(queryDataByPosition.getColumnIndex(DBOpenHelper.VOICE_PACK_NAME));
        }
        queryDataByPosition.close();
        Cursor queryDataByPosition2 = DBManager.getInstance(getApplicationContext()).queryDataByPosition(i);
        while (queryDataByPosition2.moveToNext()) {
            str = queryDataByPosition2.getString(queryDataByPosition2.getColumnIndex(DBOpenHelper.VOICE_PACK_NAME));
        }
        queryDataByPosition2.close();
        ChatAssistantBean chatAssistantBean = new ChatAssistantBean();
        chatAssistantBean.voicePackPosition = i;
        chatAssistantBean.voicePackName = str2;
        DBManager.getInstance(getApplicationContext()).updateVoicePackPosition(chatAssistantBean);
        ChatAssistantBean chatAssistantBean2 = new ChatAssistantBean();
        chatAssistantBean2.voicePackPosition = i2;
        chatAssistantBean2.voicePackName = str;
        DBManager.getInstance(getApplicationContext()).updateVoicePackPosition(chatAssistantBean2);
    }

    private void updateVoicePackPositionForDelete(String str) {
        Cursor cursor = null;
        try {
            Cursor queryAllVoicePackData = DBManager.getInstance(getApplicationContext()).queryAllVoicePackData();
            if (queryAllVoicePackData == null) {
                if (queryAllVoicePackData != null) {
                    queryAllVoicePackData.close();
                    return;
                }
                return;
            }
            int i = 0;
            while (queryAllVoicePackData.moveToNext()) {
                if (queryAllVoicePackData.getString(queryAllVoicePackData.getColumnIndex(DBOpenHelper.VOICE_PACK_NAME)).equals(str)) {
                    i = Integer.valueOf(queryAllVoicePackData.getString(queryAllVoicePackData.getColumnIndex(DBOpenHelper.VOICE_PACK_POSITION))).intValue();
                }
            }
            Collections.sort(this.mChatAssistantVoicePackBeanList, this.comparator);
            for (int i2 = 0; i2 < this.mChatAssistantVoicePackBeanList.size(); i2++) {
                if (i2 >= i && i2 < this.mChatAssistantVoicePackBeanList.size() - 1) {
                    updateVoicePackPosition(i2, i2 + 1);
                }
            }
            if (queryAllVoicePackData != null) {
                queryAllVoicePackData.close();
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    public void addScenes(View view) {
        if (this.voiceSize >= 10) {
            ToastUtils.showToast(getApplicationContext(), getResources().getString(R.string.add_voice_more), 0);
        } else {
            startActivityForResult(new Intent(this, (Class<?>) AddRecorderActivityDialog.class), REQUEST_CODE);
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        try {
            FileUtils.deleteNoRenameFile(getApplicationContext(), "noRename");
            LogUtils.i(TAG, "requestCode : " + i + " , resultCode : " + i2);
            if (900 == i && i2 == -1) {
                ChatAssistantVoiceBean chatAssistantVoiceBean = (ChatAssistantVoiceBean) intent.getSerializableExtra("chatAssistantVoiceBean");
                for (int i3 = 0; i3 < this.mChatAssistantVoiceBeanList.size(); i3++) {
                    if (chatAssistantVoiceBean.getVoiceFileName().equals(this.mChatAssistantVoiceBeanList.get(i3).getVoiceFileName())) {
                        ToastUtils.showToast(getApplicationContext(), getResources().getString(R.string.add_voice_error), 0);
                        return;
                    }
                }
                if (chatAssistantVoiceBean != null) {
                    ChatAssistantBean chatAssistantBean = new ChatAssistantBean();
                    chatAssistantBean.voicePackName = this.chatAssistantVoicePackBean.getVoicePackName();
                    chatAssistantBean.voiceFileName = chatAssistantVoiceBean.getVoiceFileName();
                    chatAssistantBean.voiceFilePath = chatAssistantVoiceBean.getVoiceFilePath();
                    chatAssistantBean.voiceFileTime = (int) chatAssistantVoiceBean.getTime();
                    chatAssistantBean.voicePackPosition = this.chatAssistantVoicePackBean.getPosition();
                    chatAssistantBean.voiceSystem = 1;
                    chatAssistantVoiceBean.setSystemDefault(false);
                    DBManager.getInstance(getApplicationContext()).insertEventToDb(chatAssistantBean);
                    ReportUtils.onReportChatAssistantAddOrDelete(getApplicationContext(), GlobalSearchConstants.ADD);
                    this.mChatAssistantSettingContentAdapter.addDate(chatAssistantVoiceBean);
                    this.voiceSize++;
                    this.mRecorderFinishLayout.setVisibility(0);
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mRecorderFinishLayout, (Property<View, Float>) View.ALPHA, 1.0f, 0.0f);
                    ofFloat.setDuration(1000L);
                    ofFloat.start();
                    this.mRecorderFinishLayout.postDelayed(new Runnable() { // from class: cn.nubia.chatassistant.customchat.ChatAssistantSettingsActivity.4
                        @Override // java.lang.Runnable
                        public void run() {
                            ChatAssistantSettingsActivity.this.mRecorderFinishLayout.setVisibility(8);
                        }
                    }, 1000L);
                }
            } else if (901 == i && i2 == -1) {
                String stringExtra = intent.getStringExtra("voicePackName");
                LogUtils.i(TAG, "delete voicePackName: " + stringExtra);
                if (!TextUtils.isEmpty(stringExtra)) {
                    updateVoicePackPositionForDelete(stringExtra);
                    deleteAllVoiceForVoicePackage(stringExtra);
                    DBManager.getInstance(getApplicationContext()).deleteByVoicePackName(stringExtra);
                    ReportUtils.onReportChatAssistantAddOrDelete(getApplicationContext(), GlobalSearchConstants.DELETE);
                    notifyDataSetChanged();
                }
            } else if (902 == i && i2 == -1) {
                String stringExtra2 = intent.getStringExtra("voiceFileName");
                LogUtils.i(TAG, "delete voiceFileName: " + stringExtra2);
                if (!TextUtils.isEmpty(stringExtra2)) {
                    DBManager.getInstance(getApplicationContext()).deleteByVoiceFileName(stringExtra2);
                    ReportUtils.onReportChatAssistantAddOrDelete(getApplicationContext(), GlobalSearchConstants.DELETE);
                    FileUtils.deleteNoRenameFile(this, stringExtra2);
                    queryAllVoiceDataToRefresh();
                    this.mChatAssistantSettingContentAdapter.refreshDate(this.mChatAssistantVoiceBeanList);
                }
            }
            this.mUploadButton.setAlpha(this.voiceSize < 10 ? 1.0f : 0.5f);
            this.mAddVoicePackButton.setAlpha(this.voicePackSize < 10 ? 1.0f : 0.5f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAddVoicePack(View view) {
        if (this.voicePackSize >= 10) {
            ToastUtils.showToast(getApplicationContext(), getResources().getString(R.string.add_voice_pack_more), 0);
        } else {
            startActivityForResult(new Intent(this, (Class<?>) AddVoicePackActivityDialog.class), REQUEST_CODE);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setShortEdges();
        requestWindowFeature(1);
        getWindow().addFlags(263968);
        setContentView(R.layout.activity_custom_chat_assistant);
        LogUtils.e(TAG, "queryAllVoicePackDataToRefresh: " + queryAllVoicePackDataToAdd().size());
        if (queryAllVoicePackDataToAdd().size() == 0) {
            setChatAssistantVoiceData(AssetsUtils.getFistTitle(this), AssetsUtils.getContentItemTitle(this));
            Settings.Global.putInt(getContentResolver(), "played_voice_pack", 0);
            Settings.Global.putInt(getContentResolver(), "played_voice", 0);
            Settings.Global.putInt(getContentResolver(), "played_voice_pack_temp", 0);
            Settings.Global.putInt(getContentResolver(), "played_voice_temp", 0);
        }
        if (isUpdateVoicePackData()) {
            AssetsUtils.updateChatAssistantVoicePack(this);
        }
        initView();
        initData();
        queryAllData();
    }

    public void onPageBack(View view) {
        finish();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        notifyDataSetChanged();
    }

    @Override // cn.nubia.chatassistant.adapter.ChatAssistantSettingTitleAdapter.OnDragStartListener
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        this.itemTouchHelper.startDrag(viewHolder);
    }

    @Override // cn.nubia.chatassistant.adapter.ChatAssistantSettingTitleAdapter.OnVoicePackSelectedListener
    public void onUpdateVoicePackState(ChatAssistantVoicePackBean chatAssistantVoicePackBean) {
        ChatAssistantBean chatAssistantBean = new ChatAssistantBean();
        chatAssistantBean.voicePackShow = !chatAssistantVoicePackBean.isShow() ? 1 : 0;
        chatAssistantBean.voicePackName = chatAssistantVoicePackBean.getVoicePackName();
        DBManager.getInstance(getApplicationContext()).updateVoicePackState(chatAssistantBean);
    }

    @Override // cn.nubia.chatassistant.adapter.ChatAssistantSettingTitleAdapter.OnVoicePackSelectedListener
    public void onVoicePackItemSelected(ChatAssistantVoicePackBean chatAssistantVoicePackBean) {
        this.mChatAssistantVoiceBeanList.clear();
        this.voiceSize = 0;
        this.chatAssistantVoicePackBean = chatAssistantVoicePackBean;
        Cursor queryDataByVoicePackName = DBManager.getInstance(getApplicationContext()).queryDataByVoicePackName(chatAssistantVoicePackBean.getVoicePackName());
        if (queryDataByVoicePackName == null) {
            return;
        }
        while (queryDataByVoicePackName.moveToNext()) {
            try {
                String string = queryDataByVoicePackName.getString(queryDataByVoicePackName.getColumnIndex(DBOpenHelper.VOICE_FILE_NAME));
                ChatAssistantVoiceBean chatAssistantVoiceBean = new ChatAssistantVoiceBean();
                chatAssistantVoiceBean.setSystemDefault(Integer.valueOf(queryDataByVoicePackName.getString(queryDataByVoicePackName.getColumnIndex(DBOpenHelper.VOICE_SYSTEM))).intValue() == 0);
                chatAssistantVoiceBean.setVoiceFileName(queryDataByVoicePackName.getString(queryDataByVoicePackName.getColumnIndex(DBOpenHelper.VOICE_FILE_NAME)));
                chatAssistantVoiceBean.setVoiceFilePath(queryDataByVoicePackName.getString(queryDataByVoicePackName.getColumnIndex(DBOpenHelper.VOICE_FILE_PATH)));
                if (!TextUtils.isEmpty(string)) {
                    this.mChatAssistantVoiceBeanList.add(chatAssistantVoiceBean);
                    this.voiceSize++;
                }
            } finally {
                if (queryDataByVoicePackName != null) {
                    queryDataByVoicePackName.close();
                }
            }
        }
        this.mUploadButton.setAlpha(this.voicePackSize < 10 ? 1.0f : 0.5f);
        this.mChatAssistantSettingContentAdapter.refreshDate(this.mChatAssistantVoiceBeanList);
    }

    public void setChatAssistantVoiceData(List<String> list, List<List> list2) {
        if (list == null || list2 == null) {
            LogUtils.e(TAG, "data is null!");
            return;
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < list.size()) {
            ChatAssistantBean chatAssistantBean = new ChatAssistantBean();
            chatAssistantBean.voicePackPosition = i;
            String[] split = list.get(i).split("_", 2);
            if (split[1].equals("redmagic")) {
                split[1] = "红魔姬";
            } else if (split[1].equals("king")) {
                split[1] = "王者风";
            } else if (split[1].equals("peace")) {
                split[1] = "和平风";
            }
            arrayList.add(split[1]);
            List<Map<String, Object>> itemTitle = AssetsUtils.getItemTitle(i, AssetsUtils.getFistTitle(this).get(i), i < ASSETS_FILE_COUNT, this);
            for (int i2 = 0; i2 < itemTitle.size(); i2++) {
                chatAssistantBean.voicePackName = (String) arrayList.get(i);
                chatAssistantBean.voiceFileName = (String) itemTitle.get(i2).get("title");
                chatAssistantBean.voiceFilePath = (String) itemTitle.get(i2).get("path");
                DBManager.getInstance(getApplicationContext()).insertEventToDb(chatAssistantBean);
            }
            i++;
        }
    }
}
