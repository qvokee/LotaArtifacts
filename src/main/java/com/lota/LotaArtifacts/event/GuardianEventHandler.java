package com.lota.LotaArtifacts.event;

import com.lota.LotaArtifacts.item.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.List;

@Mod.EventBusSubscriber(modid = "lotaartifacts", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GuardianEventHandler {

    private static final int COOLDOWN_TICKS = 1800;

    private static final int REGEN_DURATION_TICKS = 140;

    private static final int REGEN_AMPLIFIER = 2;

    private static final double EFFECT_RADIUS = 10.0;

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        if (player.level().isClientSide()) {
            return;
        }

        float currentHealth = player.getHealth();
        float damage = event.getAmount();
        float maxHealth = player.getMaxHealth();
        float healthAfterDamage = currentHealth - damage;

        boolean wasAboveHalf = currentHealth > (maxHealth * 0.5);
        boolean isBelowHalf = healthAfterDamage <= (maxHealth * 0.5);

        if (!(wasAboveHalf && isBelowHalf)) {
            return;
        }

        boolean hasNecklace = CuriosApi.getCuriosInventory(player)
                .resolve()
                .flatMap(curiosHandler -> curiosHandler.findFirstCurio(ModItems.GUARDIAN_NECKLACE.get()))
                .map(slotResult -> {
                    ItemStack stack = slotResult.stack();
                    return !player.getCooldowns().isOnCooldown(stack.getItem());
                })
                .orElse(false);

        if (!hasNecklace) {
            return;
        }

        player.getCooldowns().addCooldown(ModItems.GUARDIAN_NECKLACE.get(), COOLDOWN_TICKS);

        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, REGEN_DURATION_TICKS, REGEN_AMPLIFIER));

        AABB searchBox = player.getBoundingBox().inflate(EFFECT_RADIUS);
        List<LivingEntity> nearbyEntities = player.level().getEntitiesOfClass(
                LivingEntity.class,
                searchBox,
                entity -> entity != player && entity.isAlive());

        for (LivingEntity entity : nearbyEntities) {
            if (entity instanceof Player || isGuardEntity(entity)) {
                entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, REGEN_DURATION_TICKS, REGEN_AMPLIFIER));
            }
        }
    }

    private static boolean isGuardEntity(LivingEntity entity) {
        String entityId = net.minecraftforge.registries.ForgeRegistries.ENTITY_TYPES.getKey(entity.getType())
                .toString();
        return entityId.equals("guardvillagers:guard");
    }
}
