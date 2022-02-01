package com.teamfractal.hellfire.common.util;

import com.teamfractal.hellfire.client.config.ClientConfig;
import com.teamfractal.hellfire.common.Hellfire;
import com.teamfractal.hellfire.common.config.CommonConfig;
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

    public static void register(){
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemInit.register();
        Items.register(eventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.CONFIG);
    }
}
