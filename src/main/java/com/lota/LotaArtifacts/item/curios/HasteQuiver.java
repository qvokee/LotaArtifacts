package com.lota.LotaArtifacts.item.curios;

import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class HasteQuiver extends BaseQuiverItem {

        private static final UUID ATTACK_SPEED_UUID = UUID.fromString("3b4c5d6e-7f8a-9b0c-1d2e-3f4a5b6c7d8e");

        public HasteQuiver(Properties properties) {
                super(properties);
        }

        @Override
        public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
                        ItemStack stack) {
                Multimap<Attribute, AttributeModifier> modifiers = super.getAttributeModifiers(slotContext,
                                uuid,
                                stack);

                modifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_UUID,
                                "Haste Quiver Bonus", 0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));

                return modifiers;
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                super.appendHoverText(stack, level, tooltip, flag);

                tooltip.add(Component.literal(""));
                tooltip.add(Component.translatable("item.lotaartifacts.haste_quiver.tooltip.title")
                                .withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD));
                tooltip.add(Component.translatable("item.lotaartifacts.haste_quiver.tooltip.line1")
                                .withStyle(ChatFormatting.GRAY));
                tooltip.add(Component.literal(""));
                tooltip.add(Component.translatable("item.lotaartifacts.haste_quiver.tooltip.slot")
                                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        }
}
