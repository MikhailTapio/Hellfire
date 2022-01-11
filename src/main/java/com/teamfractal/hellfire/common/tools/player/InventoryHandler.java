package com.teamfractal.hellfire.common.tools.player;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.collection.DefaultedList;

public class InventoryHandler {
    public static ItemStack searchFor(PlayerEntity entity, Item item, int startSlot){
        final DefaultedList<ItemStack> itemList = entity.getInventory().main;
        if(startSlot < itemList.size()){
            final ItemStack stackToTest = itemList.get(startSlot);
            return (stackToTest.getItem() == item)? stackToTest : searchFor(entity,item,startSlot + 1);
        }else{
            return ItemStack.EMPTY;
        }
    }

    public static void consumeStack(ItemStack stack, PlayerEntity entity){
        if(entity instanceof ServerPlayerEntity player){
            if (stack.isDamageable()) {
                if (stack.damage(1, player.getRandom(), player)) {
                    stack.decrement(1);
                    stack.setDamage(0);
                    if (stack.isEmpty()) player.getInventory().removeOne(stack);
                }
            } else {
                stack.decrement(1);
                if (stack.isEmpty()) player.getInventory().removeOne(stack);
            }
        }
    }
}
