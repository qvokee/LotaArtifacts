package com.lota.LotaArtifacts.event;

import com.lota.LotaArtifacts.effect.ModEffects;
import com.lota.LotaArtifacts.item.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

@Mod.EventBusSubscriber(modid = "lotaartifacts", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ArmorPiercingEventHandler {

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player attacker) {
            if (attacker.level().isClientSide) {
                return;
            }

            CuriosApi.getCuriosInventory(attacker).ifPresent(handler -> {
                handler.findFirstCurio(ModItems.ARMOR_PIERCING_GLOVE.get()).ifPresent(slotResult -> {
                    if (!attacker.hasEffect(ModEffects.GLOVE_COOLDOWN.get())) {
                        attacker.addEffect(new MobEffectInstance(ModEffects.GLOVE_COOLDOWN.get(), 100, 0));
                    }
                });
            });
        }
    }
}
