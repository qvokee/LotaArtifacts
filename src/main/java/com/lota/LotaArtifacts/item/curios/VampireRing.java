package com.lota.LotaArtifacts.item.curios;

import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class VampireRing extends Item implements ICurioItem {

        private static final UUID ARMOR_UUID = UUID.fromString("6f7a8b9c-0d1e-2f3a-4b5c-6d7e8f9a0b1c");

        public VampireRing(Properties properties) {
                super(properties);
        }

        @Override
        public java.util.List<Component> getAttributesTooltip(java.util.List<Component> tooltips, ItemStack stack) {
                return new java.util.ArrayList<>();
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
                return slotContext.identifier().equals("ring");
        }

        @Override
        public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
                        ItemStack stack) {
                Multimap<Attribute, AttributeModifier> modifiers = ICurioItem.super.getAttributeModifiers(slotContext,
                                uuid,
                                stack);

                modifiers.put(Attributes.ARMOR, new AttributeModifier(ARMOR_UUID,
                                "Vampire Ring Armor Penalty", -0.50, AttributeModifier.Operation.MULTIPLY_TOTAL));

                return modifiers;
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                super.appendHoverText(stack, level, tooltip, flag);

                tooltip.add(Component.literal(""));
                tooltip.add(Component.translatable("item.lotaartifacts.vampire_ring.tooltip.title")
                                .withStyle(ChatFormatting.DARK_PURPLE, ChatFormatting.BOLD));
                tooltip.add(Component.translatable("item.lotaartifacts.vampire_ring.tooltip.line1")
                                .withStyle(ChatFormatting.GRAY));
                tooltip.add(Component.translatable("item.lotaartifacts.vampire_ring.tooltip.line2")
                                .withStyle(ChatFormatting.RED));
                tooltip.add(Component.literal(""));
                tooltip.add(Component.translatable("item.lotaartifacts.vampire_ring.tooltip.slot")
                                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        }
}
