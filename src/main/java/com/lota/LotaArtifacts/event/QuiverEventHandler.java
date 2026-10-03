package com.lota.LotaArtifacts.event;

import com.lota.LotaArtifacts.item.ModItems;
import com.lota.LotaArtifacts.LotaArtifacts;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingGetProjectileEvent;
import java.util.Random;
import com.lota.LotaArtifacts.item.curios.BaseQuiverItem;

@Mod.EventBusSubscriber(modid = LotaArtifacts.MOD_ID)
public class QuiverEventHandler {

    private static final Random random = new Random();

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) {
            return;
        }

        LivingEntity target = event.getEntity();

        if (isEquipped(player, ModItems.FIRE_QUIVER.get())) {
            if (target.isOnFire()) {
                event.setAmount(event.getAmount() * 1.20f);
            }
        }

        if (isEquipped(player, ModItems.POISON_QUIVER.get())) {
            if (target.hasEffect(MobEffects.POISON)) {
                event.setAmount(event.getAmount() * 1.20f);
            }
        }

        if (isEquipped(player, ModItems.HATRED_QUIVER.get())) {
            float hpPercent = player.getHealth() / player.getMaxHealth();
            if (hpPercent <= 0.40f) {
                event.setAmount(event.getAmount() * 1.30f);
            }
        }

        if (isEquipped(player, ModItems.HEALTH_QUIVER.get())) {
            if (random.nextFloat() < 0.05f) {
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20, 2));
            }
        }

        if (isEquipped(player, ModItems.ORDER_QUIVER.get())) {
            if (random.nextFloat() < 0.10f) {
                try {
                    net.minecraft.world.effect.MobEffect guided = net.minecraftforge.registries.ForgeRegistries.MOB_EFFECTS
                            .getValue(new net.minecraft.resources.ResourceLocation("irons_spellbooks", "guided"));
                    if (guided != null) {
                        target.addEffect(new MobEffectInstance(guided, 100, 0));
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    private static boolean isEquipped(LivingEntity entity, net.minecraft.world.item.Item item) {
        return CuriosApi.getCuriosHelper().findFirstCurio(entity, item).isPresent();
    }

    @SubscribeEvent
    public static void onGetProjectile(LivingGetProjectileEvent event) {
        if (!(event.getEntity() instanceof Player player))
            return;

        ItemStack quiver = findQuiver(player);
        if (quiver.isEmpty())
            return;

        ItemStack arrow = BaseQuiverItem.getFirstArrow(quiver);
        if (!arrow.isEmpty()) {
            event.setProjectileItemStack(arrow.copy());
        }
    }

    @SubscribeEvent
    public static void onStopUsingItem(LivingEntityUseItemEvent.Stop event) {
        if (!(event.getEntity() instanceof Player player))
            return;

        ItemStack usedItem = event.getItem();
        if (!(usedItem.getItem() instanceof BowItem))
            return;

        int useDuration = event.getDuration();
        int power = 72000 - useDuration;
        float velocity = BowItem.getPowerForTime(power);

        if (velocity < 0.1)
            return;

        ItemStack quiver = findQuiver(player);
        if (quiver.isEmpty())
            return;

        boolean isInfinite = player.getAbilities().instabuild ||
                (usedItem.getItem() instanceof BowItem
                        && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, usedItem) > 0);

        BaseQuiverItem.consumeArrow(quiver, isInfinite);
    }

    private static ItemStack findQuiver(LivingEntity entity) {
        return CuriosApi.getCuriosHelper().findFirstCurio(entity, (stack) -> stack.getItem() instanceof BaseQuiverItem)
                .map(slotResult -> slotResult.stack())
                .orElse(ItemStack.EMPTY);
    }
}
