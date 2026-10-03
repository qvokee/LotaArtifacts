package com.lota.LotaArtifacts.event;

import com.lota.LotaArtifacts.item.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Random;

@Mod.EventBusSubscriber(modid = "lotaartifacts", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RegenOnPoisonEventHandler {

    private static final Random random = new Random();
    private static final float PROC_CHANCE = 0.20f;

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() == null) {
            return;
        }

        if (!(event.getSource().getEntity() instanceof Player player)) {
            return;
        }

        LivingEntity target = event.getEntity();

        if (!target.hasEffect(MobEffects.POISON)) {
            return;
        }

        boolean hasGlove = CuriosApi.getCuriosInventory(player)
                .resolve()
                .flatMap(curiosHandler -> curiosHandler.findFirstCurio(ModItems.REGEN_ON_POISON_GLOVE.get()))
                .isPresent();

        if (!hasGlove) {
            return;
        }

        if (random.nextFloat() <= PROC_CHANCE) {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 2));
        }
    }
}
