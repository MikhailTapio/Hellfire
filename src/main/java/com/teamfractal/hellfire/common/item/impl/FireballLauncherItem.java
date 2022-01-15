package com.teamfractal.hellfire.common.item.impl;

import com.teamfractal.hellfire.common.Hellfire;
import com.teamfractal.hellfire.common.tools.player.InventoryHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;


public class FireballLauncherItem extends Item {
    public FireballLauncherItem() {
        super(new Settings().group(ItemGroup.COMBAT).fireproof().maxCount(1).maxDamage(300).rarity(Rarity.UNCOMMON));
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        final Item projectile = Items.FIRE_CHARGE;
        final ItemStack stack = user.getStackInHand(hand);
        if(!(stack.getItem() instanceof FireballLauncherItem)){
            return TypedActionResult.fail(stack);
        }
        if(!world.isClient){
            final ItemStack heldBullet = RangedWeaponItem.getHeldProjectile(user, e -> e.getItem() == projectile);
            final ItemStack bullet = (!heldBullet.isEmpty())? heldBullet
                    : InventoryHandler.searchFor(user,projectile,0);
            if(user.getAbilities().creativeMode || !bullet.isEmpty()){
                fire(stack,bullet,user,world);
            }else{
                user.sendMessage(new TranslatableText("msg.hellfire.insufficient_fireball"),false);
                world.playSound(null, user.getBlockPos(), SoundEvents.UI_BUTTON_CLICK, SoundCategory.PLAYERS, 1, 1);
            }
        }
        return TypedActionResult.pass(stack);
    }

    public void fire(ItemStack weapon, ItemStack bullet, PlayerEntity player, World world){
        final float f = -MathHelper.sin(player.getYaw() * ((float)Math.PI / 180)) * MathHelper.cos(player.getPitch() * ((float)Math.PI / 180));
        final float g = -MathHelper.sin(player.getPitch() * ((float)Math.PI / 180));
        final float h = MathHelper.cos(player.getYaw() * ((float)Math.PI / 180)) * MathHelper.cos(player.getPitch() * ((float)Math.PI / 180));
        final FireballEntity projectile = new FireballEntity(world,player,f,g,h, Hellfire.CONFIG.FIREBALL_EXPLOSION_POWER);
        projectile.setNoGravity(true);
        projectile.setOwner(player);
        projectile.setPos(player.getX() + player.getRotationVector().x * 4.0D, player.getBodyY(0.5D) + 0.5D, player.getZ() + player.getRotationVector().z * 4.0D);
        projectile.setVelocity(player, player.getPitch(), player.getYaw(), 0F, Hellfire.CONFIG.FIREBALL_INITIAL_VELOCITY, 1.0F);
        world.spawnEntity(projectile);
        world.playSound(null, player.getBlockPos(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 1, 1);
        weapon.damage(1, player, e -> e.sendToolBreakStatus(player.getActiveHand()));
        if(!player.getAbilities().creativeMode){
            InventoryHandler.consumeStack(bullet,player);
        }
        player.getItemCooldownManager().set(weapon.getItem(), Hellfire.CONFIG.FIREBALL_LAUNCHER_CD);
    }
}
