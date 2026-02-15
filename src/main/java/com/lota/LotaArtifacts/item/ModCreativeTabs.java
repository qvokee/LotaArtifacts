package com.lota.LotaArtifacts.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, "lotaartifacts");

    public static final RegistryObject<CreativeModeTab> LOTA_ARTIFACTS_TAB = CREATIVE_MODE_TABS.register(
            "lota_artifacts_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.EXPLOSIVE_FLAME_RING.get()))
                    .title(Component.translatable("creativetab.lotaartifacts.lota_artifacts_tab"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.EXPLOSIVE_FLAME_RING.get());
                        output.accept(ModItems.THUNDER_STRENGTH_RING.get());
                        output.accept(ModItems.MANA_RESTORATION_NECKLACE.get());
                        output.accept(ModItems.ARMOR_PIERCING_GLOVE.get());
                        output.accept(ModItems.REGEN_ON_POISON_GLOVE.get());
                        output.accept(ModItems.MAGIC_RESIST_NECKLACE.get());
                        output.accept(ModItems.IMPACT_GLOVE.get());
                        output.accept(ModItems.BERSERKER_NECKLACE.get());
                        output.accept(ModItems.VAMPIRE_RING.get());
                        output.accept(ModItems.GUARDIAN_NECKLACE.get());
                        output.accept(ModItems.LOOT_RING.get());
                        output.accept(ModItems.FIRE_QUIVER.get());
                        output.accept(ModItems.POISON_QUIVER.get());
                        output.accept(ModItems.HASTE_QUIVER.get());
                        output.accept(ModItems.CRUSHER_QUIVER.get());
                        output.accept(ModItems.HEALTH_QUIVER.get());
                        output.accept(ModItems.ORDER_QUIVER.get());
                        output.accept(ModItems.HATRED_QUIVER.get());
                        output.accept(ModItems.CRITICAL_ELEVATION_RING.get());
                        output.accept(ModItems.ORVIUS_UNBREAKABILITY_RING.get());
                        output.accept(ModItems.POISONOUS_BITE_RING.get());
                        output.accept(ModItems.FROZEN_IN_ICE_AMULET.get());
                        output.accept(ModItems.NIGHTMARE_SLAYER_RING.get());
                        output.accept(ModItems.HIDDEN_POTENTIAL_RING.get());
                        output.accept(ModItems.DEATH_CULT_RING.get());
                        output.accept(ModItems.GIFT_AND_CURSE_AMULET.get());
                        output.accept(ModItems.EARTHQUAKE_RING.get());
                        output.accept(ModItems.NEXAGON_RING.get());
                        output.accept(ModItems.VENOMOUS_HATRED_RING.get());
                        output.accept(ModItems.ASURA_RING.get());
                        output.accept(ModItems.SHATTER_STRENGTHENING_GAUNTLET.get());
                        output.accept(ModItems.MAGIC_REFLECTION_AMULET.get());
                        output.accept(ModItems.LICH_AMULET.get());
                        output.accept(ModItems.MANA_ABSORPTION_AMULET.get());
                        output.accept(ModItems.LIVING_CURSE_AMULET.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
