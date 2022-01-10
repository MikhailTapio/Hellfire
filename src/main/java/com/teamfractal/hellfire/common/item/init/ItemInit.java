package com.teamfractal.hellfire.common.item.init;

import com.teamfractal.hellfire.common.item.impl.FireballLauncherItem;
import com.teamfractal.hellfire.common.util.RegistryHandler;

public class ItemInit {

    public static void register(){
        RegistryHandler.Items.register("fireball_launcher", FireballLauncherItem::new);
    }
}
