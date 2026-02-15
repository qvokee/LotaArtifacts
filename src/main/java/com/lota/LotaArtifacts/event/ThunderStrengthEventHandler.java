package com.lota.LotaArtifacts.event;

import com.lota.LotaArtifacts.item.ModItems;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.List;

@Mod.EventBusSubscriber(modid = "lotaartifacts", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ThunderStrengthEventHandler {

    private static final double TRIGGER_RADIUS = 7.0;

    @SubscribeEvent
    public static void onEntityStruckByLightning(EntityStruckByLightningEvent event) {
        if (event.getEntity().level().isClientSide) {
            return;
        }

        if (!(event.getEntity() instanceof LivingEntity struckEntity)) {
            return;
        }

        applyRingEffect(struckEntity);
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        if (event.getEntity().level().isClientSide) {
            return;
        }

        com.mojang.logging.LogUtils.getLogger().info("DEBUG DAMAGE: Source ID: " + event.getSource().getMsgId());
        event.getSource().typeHolder().unwrapKey().ifPresent(
                key -> com.mojang.logging.LogUtils.getLogger().info("DEBUG DAMAGE: Type Key: " + key.location()));

        if (event.getSource().is(DamageTypeTags.IS_LIGHTNING) || event.getSource().getMsgId().equals("lightning_bolt")
                || event.getSource().getMsgId().contains("lightning")) {
            applyRingEffect(event.getEntity());
        }
    }

    private static void applyRingEffect(LivingEntity struckEntity) {
        Level level = struckEntity.level();

        AABB searchArea = struckEntity.getBoundingBox().inflate(TRIGGER_RADIUS);
        List<Player> players = level.getEntitiesOfClass(Player.class, searchArea);

        for (Player player : players) {
            if (player == struckEntity) {
                continue;
            }

            if (player.distanceTo(struckEntity) <= TRIGGER_RADIUS) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    handler.findFirstCurio(ModItems.THUNDER_STRENGTH_RING.get()).ifPresent(slotResult -> {
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1));
                    });
                });
            }
        }
    }
}
