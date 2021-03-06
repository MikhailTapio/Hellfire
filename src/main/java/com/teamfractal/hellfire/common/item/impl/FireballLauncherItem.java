package com.teamfractal.hellfire.common.item.impl;

import com.teamfractal.hellfire.client.config.ClientConfig;
import com.teamfractal.hellfire.common.config.CommonConfig;
import com.teamfractal.hellfire.common.tools.player.InventoryHandler;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

import static net.minecraft.Util.NIL_UUID;

public class FireballLauncherItem extends Item {

    public FireballLauncherItem() {
        super(new Properties().tab(CreativeModeTab.TAB_COMBAT).rarity(Rarity.UNCOMMON).stacksTo(1).durability(300).fireResistant());
    }

    @Nonnull
    @Override
    public UseAnim getUseAnimation(@Nonnull ItemStack itemStack){
        return UseAnim.NONE;
    }

    @Override
    public int getUseDuration(@Nonnull ItemStack itemstack) {
        return 72000;
    }
    @Nonnull
    @Override
    public InteractionResultHolder<ItemStack> use(@Nonnull Level world, Player player, @Nonnull InteractionHand hand){
        final Item projectile = Items.FIRE_CHARGE;
        final ItemStack stack = player.getItemInHand(hand);
        if(!(stack.getItem() instanceof FireballLauncherItem)){
            return InteractionResultHolder.fail(stack);
        }
        final ItemStack heldBullet = ProjectileWeaponItem.getHeldProjectile(player,e -> e.getItem() == projectile);
        final ItemStack bullet = (!heldBullet.isEmpty())? heldBullet
                : InventoryHandler.searchFor(player,projectile,0);
        if(player.getAbilities().instabuild || !bullet.isEmpty()){
            if(!world.isClientSide) fire(stack,bullet,player,world);
        }else{
            if (ClientConfig.AMMO_TIP_MESSAGE.get() && world.isClientSide)
                player.sendMessage(new TranslatableComponent("msg.hellfire.insufficient_fireball"),NIL_UUID);
            if (CommonConfig.AMMO_TIP_BUTTON_CLICK_SOUND.get() && !world.isClientSide)
                world.playSound(null, player.blockPosition(), SoundEvents.WOODEN_BUTTON_CLICK_ON, SoundSource.PLAYERS, 1, 1);
            if (ClientConfig.AMMO_TIP_BUTTON_CLICK_SOUND.get() && world.isClientSide && !CommonConfig.AMMO_TIP_BUTTON_CLICK_SOUND.get())
                player.playSound(SoundEvents.WOODEN_BUTTON_CLICK_ON,1,1);
        }

        return InteractionResultHolder.pass(stack);
    }

    public void fire(ItemStack weapon, ItemStack bullet, Player player, Level world){
        final float f = (float) (-Math.sin(player.getYRot() * ((float)Math.PI / 180)) * Math.cos(player.getXRot() * ((float)Math.PI / 180)));
        final float g = (float) -Math.sin(player.getXRot() * ((float)Math.PI / 180));
        final float h = (float) (Math.cos(player.getYRot() * ((float)Math.PI / 180)) * Math.cos(player.getXRot() * ((float)Math.PI / 180)));
        final LargeFireball projectile = new LargeFireball(world,player,f,g,h, CommonConfig.FIREBALL_EXPLOSION_POWER.get());
        projectile.setNoGravity(true);
        projectile.setOwner(player);
        //todo:make the fireball's generation location real, considering the interaction hand.
        projectile.setPos(player.getX() + player.getDeltaMovement().x * 4.0D, player.getY(0.5D) + 0.5D, player.getZ() + player.getDeltaMovement().z * 4.0D);
        projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0F, CommonConfig.FIREBALL_INITIAL_VELOCITY.get().floatValue(), 1.0F);
        world.addFreshEntity(projectile);
        world.playSound(null, player.blockPosition(), SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 1, 1);
        weapon.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(player.getUsedItemHand()));
        if(!player.getAbilities().instabuild){
            InventoryHandler.consumeStack(bullet,player);
        }
        player.getCooldowns().addCooldown(weapon.getItem(), CommonConfig.FIREBALL_LAUNCHER_CD.get());
    }
}
