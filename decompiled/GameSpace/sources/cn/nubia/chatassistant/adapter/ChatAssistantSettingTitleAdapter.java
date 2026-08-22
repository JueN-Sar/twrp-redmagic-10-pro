package cn.nubia.chatassistant.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.chatassistant.bean.ChatAssistantVoicePackBean;
import cn.nubia.chatassistant.customchat.ChatAssistantSettingsActivity;
import cn.nubia.chatassistant.customchat.DeleteDialog;
import cn.nubia.chatassistant.db.DBManager;
import cn.nubia.chatassistant.db.DBOpenHelper;
import cn.nubia.chatassistant.util.LogUtils;
import cn.nubia.gamelauncher.R;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes.dex */
public class ChatAssistantSettingTitleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "ChatAssistantSettingTitleAdapter";
    private WeakReference<Activity> activityWeakReference;
    private Context mContext;
    private List<? extends ChatAssistantVoicePackBean> mList;
    private OnDragStartListener mOnDragStartListener;
    private OnVoicePackSelectedListener mOnVoicePackSelectedListener;
    private int mSelectPosition;
    String mSelectVoicePackName;

    public interface OnDragStartListener {
        void onStartDrag(RecyclerView.ViewHolder viewHolder);
    }

    public interface OnVoicePackSelectedListener {
        void onUpdateVoicePackState(ChatAssistantVoicePackBean chatAssistantVoicePackBean);

        void onVoicePackItemSelected(ChatAssistantVoicePackBean chatAssistantVoicePackBean);
    }

    public class VoicePackViewHolder extends RecyclerView.ViewHolder {
        private ImageView mChangeVoicePackPosition;
        private ImageView mDeleteVoicePack;
        private ImageView mHideVoicePack;
        private TextView mVoicePack;
        private View mVoicePackRootLayout;

        public VoicePackViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            this.mVoicePack = (TextView) view.findViewById(R.id.tv_voice_pack);
            this.mVoicePackRootLayout = view.findViewById(R.id.rl_voice_pack_item_layout);
            this.mChangeVoicePackPosition = (ImageView) view.findViewById(R.id.iv_change_voice_pack_position);
            this.mHideVoicePack = (ImageView) view.findViewById(R.id.iv_hide_voice_pack);
            this.mDeleteVoicePack = (ImageView) view.findViewById(R.id.iv_delete_voice_pack);
        }

        public void setData(final ChatAssistantVoicePackBean chatAssistantVoicePackBean, final int i) {
            this.mVoicePack.setText(chatAssistantVoicePackBean.getVoicePackName());
            if (i == ChatAssistantSettingTitleAdapter.this.mSelectPosition) {
                this.mVoicePackRootLayout.setBackgroundResource(R.drawable.chat_assistant_select);
                this.mDeleteVoicePack.setVisibility(0);
                this.mHideVoicePack.setVisibility(0);
                this.mDeleteVoicePack.setAlpha(1.0f);
                this.mHideVoicePack.setAlpha(1.0f);
                this.mChangeVoicePackPosition.setAlpha(1.0f);
                this.mVoicePack.setAlpha(1.0f);
            } else {
                this.mVoicePackRootLayout.setBackgroundResource(R.drawable.shape_chat_assistant_rectangle_voice_pack);
                this.mDeleteVoicePack.setVisibility(8);
                this.mHideVoicePack.setVisibility(8);
                this.mChangeVoicePackPosition.setAlpha(0.6f);
                this.mVoicePack.setAlpha(0.6f);
            }
            if (chatAssistantVoicePackBean.isSystemDefault()) {
                this.mDeleteVoicePack.setVisibility(8);
            }
            if (chatAssistantVoicePackBean.isShow()) {
                this.mHideVoicePack.setBackgroundResource(R.drawable.chat_assistant_show);
            } else {
                this.mHideVoicePack.setBackgroundResource(R.drawable.chat_assistant_hide);
            }
            this.mHideVoicePack.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.adapter.ChatAssistantSettingTitleAdapter.VoicePackViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtils.i(ChatAssistantSettingTitleAdapter.TAG, "hide voicePackName : " + chatAssistantVoicePackBean.getVoicePackName());
                    Cursor cursor = null;
                    try {
                        Cursor queryAllVoicePackData = DBManager.getInstance(ChatAssistantSettingTitleAdapter.this.mContext).queryAllVoicePackData();
                        if (queryAllVoicePackData == null) {
                            if (queryAllVoicePackData != null) {
                                queryAllVoicePackData.close();
                                return;
                            }
                            return;
                        }
                        Settings.Global.putInt(ChatAssistantSettingTitleAdapter.this.mContext.getContentResolver(), "played_voice_pack", 0);
                        Settings.Global.putInt(ChatAssistantSettingTitleAdapter.this.mContext.getContentResolver(), "played_voice", 0);
                        Settings.Global.putInt(ChatAssistantSettingTitleAdapter.this.mContext.getContentResolver(), "played_voice_pack_temp", 0);
                        Settings.Global.putInt(ChatAssistantSettingTitleAdapter.this.mContext.getContentResolver(), "played_voice_temp", 0);
                        int i2 = 0;
                        while (queryAllVoicePackData.moveToNext()) {
                            if (Integer.valueOf(queryAllVoicePackData.getString(queryAllVoicePackData.getColumnIndex(DBOpenHelper.VOICE_PACK_SHOW))).intValue() == 0) {
                                i2++;
                            }
                        }
                        if (!chatAssistantVoicePackBean.isShow()) {
                            VoicePackViewHolder.this.mHideVoicePack.setBackgroundResource(R.drawable.chat_assistant_show);
                            chatAssistantVoicePackBean.setShow(true);
                        } else {
                            if (i2 <= 1) {
                                LogUtils.i(ChatAssistantSettingTitleAdapter.TAG, "hide voicePackName showPackNameCount: " + i2);
                                Toast.makeText(ChatAssistantSettingTitleAdapter.this.mContext, ChatAssistantSettingTitleAdapter.this.mContext.getResources().getString(R.string.hint_voice_pack_only_one), 1).show();
                                if (queryAllVoicePackData != null) {
                                    queryAllVoicePackData.close();
                                    return;
                                }
                                return;
                            }
                            VoicePackViewHolder.this.mHideVoicePack.setBackgroundResource(R.drawable.chat_assistant_hide);
                            chatAssistantVoicePackBean.setShow(false);
                        }
                        if (ChatAssistantSettingTitleAdapter.this.mOnVoicePackSelectedListener != null) {
                            ChatAssistantSettingTitleAdapter.this.mOnVoicePackSelectedListener.onUpdateVoicePackState(chatAssistantVoicePackBean);
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
            });
            this.mDeleteVoicePack.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.adapter.ChatAssistantSettingTitleAdapter.VoicePackViewHolder.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtils.i(ChatAssistantSettingTitleAdapter.TAG, "delete voicePackName : " + chatAssistantVoicePackBean.getVoicePackName());
                    Cursor cursor = null;
                    try {
                        Cursor queryAllVoicePackData = DBManager.getInstance(ChatAssistantSettingTitleAdapter.this.mContext).queryAllVoicePackData();
                        if (queryAllVoicePackData == null) {
                            if (queryAllVoicePackData != null) {
                                queryAllVoicePackData.close();
                                return;
                            }
                            return;
                        }
                        int i2 = 0;
                        Settings.Global.putInt(ChatAssistantSettingTitleAdapter.this.mContext.getContentResolver(), "played_voice_pack", 0);
                        Settings.Global.putInt(ChatAssistantSettingTitleAdapter.this.mContext.getContentResolver(), "played_voice", 0);
                        Settings.Global.putInt(ChatAssistantSettingTitleAdapter.this.mContext.getContentResolver(), "played_voice_pack_temp", 0);
                        Settings.Global.putInt(ChatAssistantSettingTitleAdapter.this.mContext.getContentResolver(), "played_voice_temp", 0);
                        String str = "";
                        while (queryAllVoicePackData.moveToNext()) {
                            if (Integer.valueOf(queryAllVoicePackData.getString(queryAllVoicePackData.getColumnIndex(DBOpenHelper.VOICE_PACK_SHOW))).intValue() == 0) {
                                str = queryAllVoicePackData.getString(queryAllVoicePackData.getColumnIndex(DBOpenHelper.VOICE_PACK_NAME));
                                i2++;
                            }
                        }
                        if (i2 == 1 && chatAssistantVoicePackBean.getVoicePackName().equals(str)) {
                            Toast.makeText(ChatAssistantSettingTitleAdapter.this.mContext, ChatAssistantSettingTitleAdapter.this.mContext.getResources().getString(R.string.hint_voice_pack_only_one), 1).show();
                            if (queryAllVoicePackData != null) {
                                queryAllVoicePackData.close();
                                return;
                            }
                            return;
                        }
                        Intent intent = new Intent(ChatAssistantSettingTitleAdapter.this.mContext, (Class<?>) DeleteDialog.class);
                        intent.putExtra("text", ChatAssistantSettingTitleAdapter.this.mContext.getResources().getString(R.string.select_delete_voice_pack_text));
                        intent.putExtra("voicePackName", chatAssistantVoicePackBean.getVoicePackName());
                        ((Activity) ChatAssistantSettingTitleAdapter.this.activityWeakReference.get()).startActivityForResult(intent, ChatAssistantSettingsActivity.DELETE_VOICE_PACK_REQUEST_CODE);
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
            });
            this.mVoicePackRootLayout.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.adapter.ChatAssistantSettingTitleAdapter.VoicePackViewHolder.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (ChatAssistantSettingTitleAdapter.this.mSelectPosition != i) {
                        ChatAssistantSettingTitleAdapter.this.mSelectPosition = i;
                        ChatAssistantSettingTitleAdapter.this.mSelectVoicePackName = chatAssistantVoicePackBean.getVoicePackName();
                        ChatAssistantSettingTitleAdapter.this.notifyDataSetChanged();
                        if (ChatAssistantSettingTitleAdapter.this.mOnVoicePackSelectedListener != null) {
                            ChatAssistantSettingTitleAdapter.this.mOnVoicePackSelectedListener.onVoicePackItemSelected(chatAssistantVoicePackBean);
                        }
                    }
                }
            });
        }
    }

    public ChatAssistantSettingTitleAdapter(Context context, List<? extends ChatAssistantVoicePackBean> list, Activity activity) {
        this.mSelectPosition = 0;
        this.mSelectVoicePackName = "";
        LogUtils.d(TAG, "list size : " + list.size());
        this.mContext = context;
        this.mList = list;
        this.mSelectPosition = list.get(0).getPosition();
        this.mSelectVoicePackName = this.mList.get(0).getVoicePackName();
        this.activityWeakReference = new WeakReference<>(activity);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof VoicePackViewHolder) {
            VoicePackViewHolder voicePackViewHolder = (VoicePackViewHolder) viewHolder;
            voicePackViewHolder.setData(this.mList.get(i), i);
            voicePackViewHolder.mChangeVoicePackPosition.setOnTouchListener(new View.OnTouchListener() { // from class: cn.nubia.chatassistant.adapter.ChatAssistantSettingTitleAdapter.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (MotionEventCompat.getActionMasked(motionEvent) != 0) {
                        return false;
                    }
                    ChatAssistantSettingTitleAdapter.this.mOnDragStartListener.onStartDrag(viewHolder);
                    return true;
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new VoicePackViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.chat_assistant_title_recy_list, viewGroup, false));
    }

    public void refreshDate(List<? extends ChatAssistantVoicePackBean> list) {
        LogUtils.i(TAG, "refreshDate : " + list.size());
        this.mList = list;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getVoicePackName().equals(this.mSelectVoicePackName)) {
                this.mSelectPosition = i;
            }
        }
        if (this.mSelectPosition >= list.size()) {
            this.mSelectPosition = 0;
        }
        notifyDataSetChanged();
        if (this.mOnVoicePackSelectedListener == null || list == null || list.size() <= 0) {
            return;
        }
        this.mOnVoicePackSelectedListener.onVoicePackItemSelected(list.get(this.mSelectPosition));
    }

    public void setOnDragStartListener(OnDragStartListener onDragStartListener) {
        this.mOnDragStartListener = onDragStartListener;
    }

    public void setOnVoicePackSelectedListener(OnVoicePackSelectedListener onVoicePackSelectedListener) {
        this.mOnVoicePackSelectedListener = onVoicePackSelectedListener;
    }
}
