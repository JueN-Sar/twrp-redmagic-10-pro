package cn.nubia.chatassistant;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import cn.nubia.chatassistant.customchat.ChatAssistantSettingsActivity;
import cn.nubia.chatassistant.util.LogUtils;
import cn.nubia.gamelauncher.R;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class ChatAssistantView extends FrameLayout {
    private static final String TAG = "ChatAssistantView";
    private int mAssetsFileCount;
    private ImageView mChatAssistantNormalBtn;
    private ImageView mChatAssistantSettingsBtn;
    private List<List> mContentList;
    private ListView mContentLv;
    private Context mContext;
    private OnViewCloseListener mListener;
    private ImageView mSendApplauseMessageBtn;
    private ImageView mSendSlowlyMessageBtn;
    private ImageView mSendThreeMessageBtn;
    private int mTabType;
    private ListView mTitleLv;
    private List<String> systemVoicePackList;

    public interface OnViewCloseListener {
        void onClickItemToViewClose(String str, int i, String str2);

        void onOutsideToViewClose();
    }

    public ChatAssistantView(Context context) {
        this(context, null);
        this.mContext = context;
    }

    public ChatAssistantView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAssetsFileCount = 3;
        this.mTabType = 0;
        this.systemVoicePackList = Arrays.asList("和平风", "王者风", "红魔姬");
        this.mContext = context;
    }

    private void initView(final List<String> list, List<List> list2) {
        LayoutInflater.from(this.mContext).inflate(R.layout.chat_assistant_view, this);
        this.mTitleLv = (ListView) findViewById(R.id.chat_assistant_first_titles);
        this.mContentLv = (ListView) findViewById(R.id.chat_assistant_contents);
        ImageView imageView = (ImageView) findViewById(R.id.bt_chat_assistant_normal);
        this.mChatAssistantNormalBtn = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.ChatAssistantView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtils.i(ChatAssistantView.TAG, "mSendThreeMessageBtn is click!");
                Settings.Global.putInt(ChatAssistantView.this.mContext.getContentResolver(), "play_mode_send_three_message", 1);
                Settings.Global.putInt(ChatAssistantView.this.mContext.getContentResolver(), "play_mode_send_slowly_message", 1);
                Settings.Global.putInt(ChatAssistantView.this.mContext.getContentResolver(), "play_mode_send_applause_message", 1);
                Settings.Global.putInt(ChatAssistantView.this.mContext.getContentResolver(), "play_mode_send_normal_message", 0);
                ChatAssistantView.this.updateSpecialEffect();
            }
        });
        ImageView imageView2 = (ImageView) findViewById(R.id.bt_send_three_message);
        this.mSendThreeMessageBtn = imageView2;
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.ChatAssistantView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtils.i(ChatAssistantView.TAG, "mSendThreeMessageBtn is click!");
                Settings.Global.putInt(ChatAssistantView.this.mContext.getContentResolver(), "play_mode_send_three_message", 0);
                Settings.Global.putInt(ChatAssistantView.this.mContext.getContentResolver(), "play_mode_send_slowly_message", 1);
                Settings.Global.putInt(ChatAssistantView.this.mContext.getContentResolver(), "play_mode_send_applause_message", 1);
                Settings.Global.putInt(ChatAssistantView.this.mContext.getContentResolver(), "play_mode_send_normal_message", 1);
                ChatAssistantView.this.updateSpecialEffect();
            }
        });
        ImageView imageView3 = (ImageView) findViewById(R.id.bt_send_slowly_message);
        this.mSendSlowlyMessageBtn = imageView3;
        imageView3.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.ChatAssistantView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtils.i(ChatAssistantView.TAG, "mSendSlowlyMessageBtn is click!");
                Settings.Global.putInt(ChatAssistantView.this.mContext.getContentResolver(), "play_mode_send_three_message", 1);
                Settings.Global.putInt(ChatAssistantView.this.mContext.getContentResolver(), "play_mode_send_slowly_message", 0);
                Settings.Global.putInt(ChatAssistantView.this.mContext.getContentResolver(), "play_mode_send_applause_message", 1);
                Settings.Global.putInt(ChatAssistantView.this.mContext.getContentResolver(), "play_mode_send_normal_message", 1);
                ChatAssistantView.this.updateSpecialEffect();
            }
        });
        ImageView imageView4 = (ImageView) findViewById(R.id.bt_send_applause_message);
        this.mSendApplauseMessageBtn = imageView4;
        imageView4.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.ChatAssistantView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtils.i(ChatAssistantView.TAG, "mSendEchoesMessageBtn is click!");
                Settings.Global.putInt(ChatAssistantView.this.mContext.getContentResolver(), "play_mode_send_three_message", 1);
                Settings.Global.putInt(ChatAssistantView.this.mContext.getContentResolver(), "play_mode_send_slowly_message", 1);
                Settings.Global.putInt(ChatAssistantView.this.mContext.getContentResolver(), "play_mode_send_applause_message", 0);
                Settings.Global.putInt(ChatAssistantView.this.mContext.getContentResolver(), "play_mode_send_normal_message", 1);
                ChatAssistantView.this.updateSpecialEffect();
            }
        });
        ImageView imageView5 = (ImageView) findViewById(R.id.bt_chat_assistant_settings);
        this.mChatAssistantSettingsBtn = imageView5;
        imageView5.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.ChatAssistantView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtils.i(ChatAssistantView.TAG, "mChatAssistantSettingsBtn is click!");
                Intent intent = new Intent("cn.nubia.chatassistant.customchat.ChatAssistantSettingsActivity");
                intent.setClass(ChatAssistantView.this.mContext, ChatAssistantSettingsActivity.class);
                intent.addFlags(268435456);
                ChatAssistantView.this.mContext.startActivity(intent);
            }
        });
        final ChatAssistantTitleAdapter chatAssistantTitleAdapter = new ChatAssistantTitleAdapter(this.mContext, list);
        this.mTitleLv.setAdapter((ListAdapter) chatAssistantTitleAdapter);
        this.mTitleLv.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: cn.nubia.chatassistant.ChatAssistantView.6
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                LogUtils.e(ChatAssistantView.TAG, "mTitleLv is click!  " + ((String) list.get(i)) + ChatAssistantView.this.systemVoicePackList.contains(list.get(i)));
                chatAssistantTitleAdapter.setSelectedItem(i);
                chatAssistantTitleAdapter.notifyDataSetInvalidated();
                ChatAssistantView chatAssistantView = ChatAssistantView.this;
                chatAssistantView.mTabType = !chatAssistantView.systemVoicePackList.contains(list.get(i)) ? 1 : 0;
                ChatAssistantView.this.updateContentListView(i);
                Settings.Global.putInt(ChatAssistantView.this.mContext.getContentResolver(), "played_voice_pack_temp", i);
            }
        });
        this.mContentLv.setAdapter((ListAdapter) new ChatAssistantContentAdapter(this.mContext, list2.get(0)));
        this.mContentLv.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: cn.nubia.chatassistant.ChatAssistantView.7
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                HashMap hashMap = (HashMap) ChatAssistantView.this.mContentLv.getAdapter().getItem(i);
                if (ChatAssistantView.this.mListener != null) {
                    LogUtils.i(ChatAssistantView.TAG, "mContentLv mTabType: " + ChatAssistantView.this.mTabType);
                    ChatAssistantView.this.mListener.onClickItemToViewClose((String) hashMap.get("path"), ChatAssistantView.this.mTabType, (String) hashMap.get("title"));
                    Settings.Global.putInt(ChatAssistantView.this.mContext.getContentResolver(), "played_voice_temp", i);
                }
            }
        });
        int i = Settings.Global.getInt(this.mContext.getContentResolver(), "played_voice_pack", 0);
        this.mTitleLv.setSelection(i);
        chatAssistantTitleAdapter.setSelectedItem(i);
        chatAssistantTitleAdapter.notifyDataSetInvalidated();
        this.mTabType = !this.systemVoicePackList.contains(list.get(i)) ? 1 : 0;
        updateContentListView(i);
        this.mContentLv.setSelection(Settings.Global.getInt(this.mContext.getContentResolver(), "played_voice", 0));
        updateSpecialEffect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateContentListView(int i) {
        LogUtils.e(TAG, "updateContentListView index=" + i);
        this.mContentLv.setAdapter((ListAdapter) new ChatAssistantContentAdapter(this.mContext, this.mContentList.get(i)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSpecialEffect() {
        this.mChatAssistantNormalBtn.setBackgroundResource(Settings.Global.getInt(this.mContext.getContentResolver(), "play_mode_send_normal_message", 0) == 0 ? R.drawable.chat_assistant_play_selected : R.drawable.chat_assistant_play_not_selected);
        this.mSendThreeMessageBtn.setBackgroundResource(Settings.Global.getInt(this.mContext.getContentResolver(), "play_mode_send_three_message", 1) == 0 ? R.drawable.chat_assistant_three_play_selected : R.drawable.chat_assistant_three_play_not_selected);
        this.mSendSlowlyMessageBtn.setBackgroundResource(Settings.Global.getInt(this.mContext.getContentResolver(), "play_mode_send_slowly_message", 1) == 0 ? R.drawable.chat_assistant_slowly_play_selected : R.drawable.chat_assistant_slowly_play_not_selected);
        this.mSendApplauseMessageBtn.setBackgroundResource(Settings.Global.getInt(this.mContext.getContentResolver(), "play_mode_send_applause_message", 1) == 0 ? R.drawable.chat_assistant_applause_play_selected : R.drawable.chat_assistant_applause_play_not_selected);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 4) {
            LogUtils.i(TAG, "ACTION_OUTSIDE in");
            OnViewCloseListener onViewCloseListener = this.mListener;
            if (onViewCloseListener != null) {
                onViewCloseListener.onOutsideToViewClose();
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setCloseListener(OnViewCloseListener onViewCloseListener) {
        this.mListener = onViewCloseListener;
    }

    public void setData(List<String> list, List<List> list2, int i) {
        this.mAssetsFileCount = i;
        if (list == null || list2 == null) {
            LogUtils.e(TAG, "data is null!");
            return;
        }
        this.mContentList = list2;
        this.mTabType = !this.systemVoicePackList.contains(list.get(0)) ? 1 : 0;
        initView(list, list2);
    }
}
