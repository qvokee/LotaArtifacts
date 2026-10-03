package com.lota.LotaArtifacts.event;

import com.lota.LotaArtifacts.item.ModItems;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mod.EventBusSubscriber(modid = "lotaartifacts", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LootRingEventHandler {

    @SubscribeEvent
    public static void onExperienceDrop(LivingExperienceDropEvent event) {
        if (event.getAttackingPlayer() == null) {
            return;
        }

        Player player = event.getAttackingPlayer();

        boolean hasRing = CuriosApi.getCuriosInventory(player)
                .resolve()
                .flatMap(curiosHandler -> curiosHandler.findFirstCurio(ModItems.LOOT_RING.get()))
                .isPresent();

        if (hasRing) {
            event.setDroppedExperience(event.getDroppedExperience() * 2);
        }
    }

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) {
            return;
        }

        boolean hasRing = CuriosApi.getCuriosInventory(player)
                .resolve()
                .flatMap(curiosHandler -> curiosHandler.findFirstCurio(ModItems.LOOT_RING.get()))
                .isPresent();

        if (hasRing) {
            Collection<ItemEntity> drops = event.getDrops();
            List<ItemEntity> extraDrops = new ArrayList<>();

            for (ItemEntity drop : drops) {
                ItemStack stack = drop.getItem().copy();
                ItemEntity newDrop = new ItemEntity(drop.level(), drop.getX(), drop.getY(), drop.getZ(), stack);
                newDrop.setDeltaMovement(drop.getDeltaMovement());
                newDrop.setPickUpDelay(10);
                extraDrops.add(newDrop);
            }

            drops.addAll(extraDrops);
        }
    }

    @SubscribeEvent
    public static void onContainerOpen(net.minecraftforge.event.entity.player.PlayerContainerEvent.Open event) {
        if (!(event.getContainer() instanceof net.minecraft.world.inventory.MerchantMenu merchantMenu)) {
            return;
        }

        Player player = event.getEntity();
        if (player.level().isClientSide()) {
            return;
        }

        boolean hasRing = CuriosApi.getCuriosInventory(player)
                .resolve()
                .flatMap(curiosHandler -> curiosHandler.findFirstCurio(ModItems.LOOT_RING.get()))
                .isPresent();

        if (hasRing) {
            net.minecraft.world.item.trading.MerchantOffers offers = merchantMenu.getOffers();
            for (net.minecraft.world.item.trading.MerchantOffer offer : offers) {
                ItemStack costA = offer.getCostA();
                int currentCount = costA.getCount();
                int targetCount = Math.max(1, currentCount / 2);
                int diff = targetCount - currentCount;

                offer.addToSpecialPriceDiff(diff);
            }
        }
    }
}
