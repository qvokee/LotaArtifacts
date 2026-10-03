package com.lota.LotaArtifacts.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "lotaartifacts");

    public static final RegistryObject<CreativeModeTab> LOTA_ARTIFACTS_TAB = CREATIVE_MODE_TABS.register("lota_artifacts_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.EXPLOSIVE_FLAME_RING.get()))
                    .title(Component.translatable("creativetab.lotaartifacts.lota_artifacts_tab"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.EXPLOSIVE_FLAME_RING.get());
                        output.accept(ModItems.THUNDER_STRENGTH_RING.get());
                        output.accept(ModItems.MANA_RESTORATION_NECKLACE.get());
                        output.accept(ModItems.ARMOR_PIERCING_GLOVE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
