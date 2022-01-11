package com.teamfractal.hellfire.client.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.BooleanValue AMMO_TIP_MESSAGE = BUILDER.comment("Should we send a message to notify insufficient fireball?")
            .define("insufficient_tip_message", true);
    public static final ForgeConfigSpec.BooleanValue AMMO_TIP_BUTTON_CLICK_SOUND = BUILDER.comment("Should we play a client-side button-clicked sound to notify insufficient fireball?")
            .define("insufficient_tip_sound", true);
    public static final ForgeConfigSpec CONFIG = BUILDER.build();
}
