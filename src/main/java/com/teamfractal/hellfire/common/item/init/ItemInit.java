package com.teamfractal.hellfire.common.item.init;

import com.teamfractal.hellfire.common.item.impl.FireballLauncherItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.teamfractal.hellfire.common.Hellfire.N;

public class ItemInit {
    public static final Item fireballLauncher = new FireballLauncherItem();

    public static void register(){
        Registry.register(Registry.ITEM, new Identifier(N, "fireball_launcher"), fireballLauncher);
    }
}
