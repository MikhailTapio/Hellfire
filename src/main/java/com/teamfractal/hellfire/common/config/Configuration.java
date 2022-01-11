package com.teamfractal.hellfire.common.config;

import com.teamfractal.hellfire.common.Hellfire;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = Hellfire.N)
public class Configuration implements ConfigData {
    @Comment("How long should the fireball launcher's cool-down time be?. (Default: 20 ticks = 1 sec)")
    public int FIREBALL_LAUNCHER_CD = 20;
    @Comment("How big should the power of the fireball's explosion be?. (Default: 1)")
    public int FIREBALL_EXPLOSION_POWER = 1;
    @Comment("What should the fireball's initial launching speed be?. (Default: 5.0)")
    public float FIREBALL_INITIAL_VELOCITY = 5.0F;
}
