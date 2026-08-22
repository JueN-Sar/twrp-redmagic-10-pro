package com.zte.aivibrate.scene;

/* loaded from: classes.dex */
public enum VibrateSceneState {
    ATTACK("attack"),
    SMALL_SKILL("small skill"),
    ULTIMATE_SKILL("ultimate skill"),
    LONG_PRESS_SKILL("long press skill"),
    KILL_ENEMY("kill enemy"),
    CONTINUOUS_KILL("continuous kill"),
    WHEN_KILLED("when kill"),
    VICTORY("victory"),
    DEFEAT("defeat"),
    ENEMY_DEAD_ALL("enemy dead all"),
    GAME_START("game start"),
    GAME_END("game end"),
    YS_ATTACK("ys attack"),
    YS_LONG_PRESS_SKILL("ys long press skill"),
    YS_SMALL_SKILL("ys small skill"),
    YS_ULTIMATE_SKILL("ys ultimate skill"),
    YS_ENTER_GAMING("ys enter gaming"),
    YS_EXIT_GAMING("ys exit gaming"),
    YS_FLYING("ys flying"),
    YS_RUNNING("ys running"),
    YS_PERSON_DIED("ys person died"),
    YS_FIVE_START("ys five start");

    private final String description;

    VibrateSceneState(String str) {
        this.description = str;
    }

    public String d() {
        return this.description;
    }
}
