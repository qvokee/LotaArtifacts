package com.lota.LotaArtifacts.event;

import com.lota.LotaArtifacts.item.ModItems;
import com.lota.LotaArtifacts.item.curios.ExplosiveFlameRing;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = "lotaartifacts", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ExplosiveFlameEventHandler {

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() == null) {
            return;
        }

        if (!(event.getSource().getEntity() instanceof Player player)) {
            return;
        }

        LivingEntity target = event.getEntity();

        if (!target.isOnFire()) {
            return;
        }

        Optional<SlotResult> ringSlot = CuriosApi.getCuriosInventory(player)
                .resolve()
                .flatMap(curiosHandler -> curiosHandler.findFirstCurio(ModItems.EXPLOSIVE_FLAME_RING.get()));
        
        if (ringSlot.isEmpty()) {
            return;
        }

        ItemStack ringStack = ringSlot.get().stack();

        if (!(ringStack.getItem() instanceof ExplosiveFlameRing ring)) {
            return;
        }

        float originalDamage = event.getAmount();
        float newDamage = ring.onAttackBurningTarget(player, target, originalDamage);

        if (newDamage != originalDamage) {
            event.setAmount(newDamage);
        }
    }
}
