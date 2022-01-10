package com.teamfractal.hellfire.common.util;

import com.teamfractal.hellfire.common.Hellfire;
import com.teamfractal.hellfire.common.config.Config;
import com.teamfractal.hellfire.common.item.init.ItemInit;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {
    public static final DeferredRegister<Item> Items = DeferredRegister.create(ForgeRegistries.ITEMS, Hellfire.MODID);
    public static final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

    public static void register(){
        ItemInit.register();
        Items.register(eventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.CONFIG);
    }
}
