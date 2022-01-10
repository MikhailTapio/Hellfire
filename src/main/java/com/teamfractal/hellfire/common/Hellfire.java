package com.teamfractal.hellfire.common;

import com.teamfractal.hellfire.common.util.RegistryHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Hellfire.MODID)
public class Hellfire
{
    public static final String MODID = "hellfire";
    public static final String MODNAME = "Hellfire:Fireball launcher";
    public static final Logger LOGGER = LogManager.getLogger();

    public Hellfire() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        RegistryHandler.register();
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("Loading {}, burn them out!", Hellfire.MODNAME);
    }

}
