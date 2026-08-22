package cn.nubia.chatassistant.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import cn.nubia.chatassistant.bean.ChatAssistantVoiceBean;
import cn.nubia.chatassistant.util.MusicManagerUtils;

/* loaded from: classes.dex */
public abstract class BaseFragment extends Fragment {
    private View mViewRoot;
    private ObjectAnimator objectAnimator;
    private int resourceId;

    public static BaseFragment newInstance(Class<? extends BaseFragment> cls, ChatAssistantVoiceBean chatAssistantVoiceBean) {
        BaseFragment newInstance;
        BaseFragment baseFragment = null;
        try {
            newInstance = cls.newInstance();
        } catch (IllegalAccessException e) {
            e = e;
        } catch (Exception e2) {
            e = e2;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putSerializable("chatAssistantVoiceBean", chatAssistantVoiceBean);
            newInstance.setArguments(bundle);
            return newInstance;
        } catch (IllegalAccessException e3) {
            e = e3;
            baseFragment = newInstance;
            e.printStackTrace();
            return baseFragment;
        } catch (Exception e4) {
            e = e4;
            baseFragment = newInstance;
            e.printStackTrace();
            return baseFragment;
        }
    }

    public <T extends View> T findViewById(int i) {
        return (T) this.mViewRoot.findViewById(i);
    }

    public abstract void initView();

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mViewRoot = layoutInflater.inflate(this.resourceId, viewGroup, false);
        initView();
        return this.mViewRoot;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        MusicManagerUtils.getInstance().stopPlay();
    }

    public void setLayout(int i) {
        this.resourceId = i;
    }

    public void startAnimation(View view, int i) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, 1.0f, 0.0f, 1.0f);
        this.objectAnimator = ofFloat;
        ofFloat.setDuration(1000L);
        this.objectAnimator.setRepeatCount(i);
        this.objectAnimator.setRepeatMode(2);
        this.objectAnimator.start();
    }
}
