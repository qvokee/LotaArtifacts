package com.lota.LotaArtifacts.item.curios;

import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class CrusherQuiver extends BaseQuiverItem {

        private static final UUID IMPACT_UUID = UUID.fromString("4c5d6e7f-8a9b-0c1d-2e3f-4a5b6c7d8e9f");
        private static final UUID ATTACK_SPEED_UUID = UUID.fromString("5d6e7f8a-9b0c-1d2e-3f4a-5b6c7d8e9f0a");

        public CrusherQuiver(Properties properties) {
                super(properties);
        }

        @Override
        public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
                        ItemStack stack) {
                Multimap<Attribute, AttributeModifier> modifiers = super.getAttributeModifiers(slotContext, uuid,
                                stack);

                try {
                        Attribute impact = ForgeRegistries.ATTRIBUTES
                                        .getValue(new ResourceLocation("epicfight", "impact"));
                        if (impact != null) {
                                modifiers.put(impact, new AttributeModifier(IMPACT_UUID,
                                                "Crusher Quiver Impact", 3.0, AttributeModifier.Operation.ADDITION));
                        }
                } catch (Exception e) {
                }

                modifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_UUID,
                                "Crusher Quiver Slow", -0.25, AttributeModifier.Operation.MULTIPLY_TOTAL));

                return modifiers;
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                super.appendHoverText(stack, level, tooltip, flag);

                tooltip.add(Component.literal(""));
                tooltip.add(Component.translatable("item.lotaartifacts.crusher_quiver.tooltip.title")
                                .withStyle(ChatFormatting.DARK_RED, ChatFormatting.BOLD));
                tooltip.add(Component.translatable("item.lotaartifacts.crusher_quiver.tooltip.line1")
                                .withStyle(ChatFormatting.GRAY));
                tooltip.add(Component.translatable("item.lotaartifacts.crusher_quiver.tooltip.line2")
                                .withStyle(ChatFormatting.RED));
                tooltip.add(Component.literal(""));
                tooltip.add(Component.translatable("item.lotaartifacts.crusher_quiver.tooltip.slot")
                                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        }
}
