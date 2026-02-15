package com.lota.LotaArtifacts.item.curios;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ExplosiveFlameRing extends Item implements ICurioItem {

    private static final float PROC_CHANCE = 0.20f;

    private static final double SPREAD_RADIUS = 5.0;

    private static final int FIRE_DURATION_TICKS = 100;

    private static final float BONUS_DAMAGE_MULTIPLIER = 0.50f;

    private final Random random = new Random();

    public ExplosiveFlameRing(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return slotContext.identifier().equals("ring");
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
    }

    public float onAttackBurningTarget(LivingEntity attacker, LivingEntity target, float originalDamage) {
        if (random.nextFloat() > PROC_CHANCE) {
            return originalDamage;
        }

        Level level = attacker.level();
        if (level.isClientSide()) {
            return originalDamage;
        }

        spreadFireToNearbyEnemies(target, attacker);

        float bonusDamage = originalDamage * BONUS_DAMAGE_MULTIPLIER;
        return originalDamage + bonusDamage;
    }

    private void spreadFireToNearbyEnemies(LivingEntity center, LivingEntity attacker) {
        Level level = center.level();

        AABB searchBox = center.getBoundingBox().inflate(SPREAD_RADIUS);

        List<LivingEntity> nearbyEntities = level.getEntitiesOfClass(
                LivingEntity.class,
                searchBox,
                entity -> entity != center &&
                        entity != attacker &&
                        entity.isAlive() &&
                        !entity.fireImmune() &&
                        isValidTarget(entity, attacker));

        for (LivingEntity entity : nearbyEntities) {
            double distance = entity.distanceTo(center);
            if (distance <= SPREAD_RADIUS) {
                entity.setSecondsOnFire(FIRE_DURATION_TICKS / 20);
            }
        }
    }

    private boolean isValidTarget(LivingEntity entity, LivingEntity attacker) {
        if (entity instanceof Monster) {
            return true;
        }

        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        tooltip.add(Component.literal(""));
        tooltip.add(Component.translatable("item.lotaartifacts.explosive_flame_ring.tooltip.title")
                .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD));
        tooltip.add(Component.translatable("item.lotaartifacts.explosive_flame_ring.tooltip.line1")
                .withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("item.lotaartifacts.explosive_flame_ring.tooltip.line2")
                .withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal(""));
        tooltip.add(Component.translatable("item.lotaartifacts.explosive_flame_ring.tooltip.slot")
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
    }
}
