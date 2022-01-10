package com.teamfractal.hellfire.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.IntValue FIREBALL_EXPLOSION_POWER = BUILDER.comment("Fireball Properties Setting","How big should the power of the fireball's explosion be?")
            .defineInRange("explosion_power",1,0,5);
    public static final ForgeConfigSpec.DoubleValue FIREBALL_INITIAL_VELOCITY = BUILDER.comment("What should the fireball's initial launching speed be?")
            .defineInRange("fireball_speed",5,0.1,25);
    public static final ForgeConfigSpec.IntValue FIREBALL_LAUNCHER_CD = BUILDER.comment("How long should the fireball launcher's cool down time be?","The unit is tick. If the value is 20, the CD time would be 20 ticks(1 sec)")
            .defineInRange("cd_time",20,1,Integer.MAX_VALUE);
    public static final ForgeConfigSpec CONFIG = BUILDER.build();
}
