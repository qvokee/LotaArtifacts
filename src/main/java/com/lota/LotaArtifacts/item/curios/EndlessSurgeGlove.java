package com.lota.LotaArtifacts.item.curios;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class EndlessSurgeGlove extends BaseArtifactItem {

    public EndlessSurgeGlove(Properties properties) {
        super(properties, "hands", 2);
    }

    @Override
    public java.util.List<net.minecraft.network.chat.Component> getAttributesTooltip(
            java.util.List<net.minecraft.network.chat.Component> tooltips, ItemStack stack) {
        return new java.util.ArrayList<>();
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (slotContext.entity() instanceof ServerPlayer sp) {
            sp.server.getCommands().performPrefixedCommand(sp.createCommandSourceStack().withSuppressedOutput(),
                    "epicfight skill add " + sp.getGameProfile().getName() + " passive3 wom:dopamine");
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof ServerPlayer sp) {
            sp.server.getCommands().performPrefixedCommand(sp.createCommandSourceStack().withSuppressedOutput(),
                    "epicfight skill remove " + sp.getGameProfile().getName() + " passive3");
        }
    }
}
