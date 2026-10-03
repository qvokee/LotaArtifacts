package com.lota.LotaArtifacts.item.curios;

import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ImpactGlove extends Item implements ICurioItem {

        private static final UUID IMPACT_UUID = UUID.fromString("1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d");
        private static final UUID ATTACK_SPEED_UUID = UUID.fromString("2b3c4d5e-6f7a-8b9c-0d1e-2f3a4b5c6d7e");

        public ImpactGlove(Properties properties) {
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
        public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
                        ItemStack stack) {
                Multimap<Attribute, AttributeModifier> modifiers = ICurioItem.super.getAttributeModifiers(slotContext,
                                uuid,
                                stack);

                try {
                        Attribute impact = ForgeRegistries.ATTRIBUTES
                                        .getValue(new ResourceLocation("epicfight", "impact"));
                        if (impact != null) {
                                modifiers.put(impact, new AttributeModifier(IMPACT_UUID,
                                                "Impact Glove Bonus", 2.0, AttributeModifier.Operation.ADDITION));
                        }
                } catch (Exception e) {
                }

                modifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_UUID,
                                "Impact Glove Slow", -0.20, AttributeModifier.Operation.MULTIPLY_TOTAL));

                return modifiers;
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                super.appendHoverText(stack, level, tooltip, flag);

                tooltip.add(Component.literal(""));
                tooltip.add(Component.translatable("item.lotaartifacts.impact_glove.tooltip.title")
                                .withStyle(ChatFormatting.DARK_RED, ChatFormatting.BOLD));
                tooltip.add(Component.translatable("item.lotaartifacts.impact_glove.tooltip.line1")
                                .withStyle(ChatFormatting.GRAY));
                tooltip.add(Component.translatable("item.lotaartifacts.impact_glove.tooltip.line2")
                                .withStyle(ChatFormatting.RED));
                tooltip.add(Component.literal(""));
                tooltip.add(Component.translatable("item.lotaartifacts.impact_glove.tooltip.slot")
                                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        }
}
