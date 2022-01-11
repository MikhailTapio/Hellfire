package com.teamfractal.hellfire.common;

import com.teamfractal.hellfire.common.config.Configuration;
import com.teamfractal.hellfire.common.item.init.ItemInit;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;


public class Hellfire implements ModInitializer {
    public static final String N = "hellfire";
    public static Configuration CONFIG;

    @Override
    public void onInitialize() {
        AutoConfig.register(Configuration.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(Configuration.class).getConfig();
        ItemInit.register();
    }
}
