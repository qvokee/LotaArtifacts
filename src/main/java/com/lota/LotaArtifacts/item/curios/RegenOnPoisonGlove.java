package com.lota.LotaArtifacts.item.curios;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;

public class RegenOnPoisonGlove extends Item implements ICurioItem {

    public RegenOnPoisonGlove(Properties properties) {
        super(properties);
    }

    @Override
    public java.util.List<net.minecraft.network.chat.Component> getAttributesTooltip(
            java.util.List<net.minecraft.network.chat.Component> tooltips, ItemStack stack) {
        return new java.util.ArrayList<>();
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return slotContext.identifier().equals("hands");
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        tooltip.add(Component.literal(""));
        tooltip.add(Component.translatable("item.lotaartifacts.regen_on_poison_glove.tooltip.title")
                .withStyle(ChatFormatting.GREEN, ChatFormatting.BOLD));
        tooltip.add(Component.translatable("item.lotaartifacts.regen_on_poison_glove.tooltip.line1")
                .withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("item.lotaartifacts.regen_on_poison_glove.tooltip.line2")
                .withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal(""));
        tooltip.add(Component.translatable("item.lotaartifacts.regen_on_poison_glove.tooltip.slot")
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
    }
}
