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

public class BaseArtifactItem extends Item implements ICurioItem {
    protected final String slot;
    protected final int tooltipLines;

    public BaseArtifactItem(Properties properties, String slot, int tooltipLines) {
        super(properties);
        this.slot = slot;
        this.tooltipLines = tooltipLines;
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return slotContext.identifier().equals(slot);
    }

    @Override
    public java.util.List<net.minecraft.network.chat.Component> getAttributesTooltip(
            java.util.List<net.minecraft.network.chat.Component> tooltips, ItemStack stack) {
        return new java.util.ArrayList<>();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        String baseKey = this.getDescriptionId();

        tooltip.add(Component.literal(""));
        tooltip.add(Component.translatable(baseKey + ".tooltip.title")
                .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD));

        for (int i = 1; i <= tooltipLines; i++) {
            String lineKey = baseKey + ".tooltip.line" + i;
            tooltip.add(Component.translatable(lineKey).withStyle(ChatFormatting.GRAY));
        }

        if (baseKey.contains("lich_amulet")) {
            tooltip.add(Component.translatable(baseKey + ".tooltip.line1_2").withStyle(ChatFormatting.GRAY));
        }

        tooltip.add(Component.literal(""));
        tooltip.add(Component.translatable(baseKey + ".tooltip.slot")
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
    }
}
