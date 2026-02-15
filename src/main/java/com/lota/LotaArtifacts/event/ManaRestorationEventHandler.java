package com.lota.LotaArtifacts.event;

import com.lota.LotaArtifacts.item.ModItems;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.CuriosApi;

@Mod.EventBusSubscriber(modid = "lotaartifacts", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ManaRestorationEventHandler {

    @SubscribeEvent
    public static void onShieldBlock(ShieldBlockEvent event) {
        if (event.getEntity().level().isClientSide) {
            return;
        }

        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
            handler.findFirstCurio(ModItems.MANA_RESTORATION_NECKLACE.get()).ifPresent(slotResult -> {
                MobEffect instantManaEffect = ForgeRegistries.MOB_EFFECTS
                        .getValue(new net.minecraft.resources.ResourceLocation("irons_spellbooks", "instant_mana"));

                if (instantManaEffect != null) {
                    player.addEffect(new MobEffectInstance(instantManaEffect, 1, 0));
                }
            });
        });
    }
}
