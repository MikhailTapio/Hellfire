package com.teamfractal.hellfire.common.tools.player;

import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class InventoryHandler {
    public static ItemStack searchFor(Player entity, Item item, int startSlot){
        final NonNullList<ItemStack> itemList = entity.getInventory().items;
        final int size = itemList.size();
        if (startSlot < size) {
            final ItemStack stackToTest = itemList.get(startSlot);
            return (stackToTest.getItem() == item) ? stackToTest : searchFor(entity, item, startSlot + 1);
        } else {
            return ItemStack.EMPTY;
        }
    }

    public static void consumeStack(ItemStack stack, Player entity){
        if(entity instanceof ServerPlayer player){
            if (stack.isDamageableItem()) {
                if (stack.hurt(1, player.getRandom(), player)) {
                    stack.shrink(1);
                    stack.setDamageValue(0);
                    if (stack.isEmpty()) player.getInventory().removeItem(stack);
                }
            } else {
                stack.shrink(1);
                if (stack.isEmpty()) player.getInventory().removeItem(stack);
            }
        }
    }
}
