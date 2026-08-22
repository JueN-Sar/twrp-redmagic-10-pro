package cn.nubia.componentcenter.service;

import android.graphics.Rect;
import androidx.annotation.VisibleForTesting;
import java.util.List;

/* loaded from: classes.dex */
public interface AI4DVibrateComService {
    @VisibleForTesting
    boolean aI4DVibrateThreadAlive();

    @VisibleForTesting
    void closePlugin(String str);

    @VisibleForTesting
    boolean gameLabHasDetected();

    @VisibleForTesting
    Rect getAttack();

    @VisibleForTesting
    List<Rect> getSmallSkillList();

    @VisibleForTesting
    Rect getUltimateSkill();

    @VisibleForTesting
    boolean hasGaming();

    @VisibleForTesting
    void openPlugin(String str);

    @VisibleForTesting
    boolean yoloHasDetected();
}
