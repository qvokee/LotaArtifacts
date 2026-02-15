package com.lota.LotaArtifacts.item.curios;

import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
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

public class ArmorPiercingGlove extends Item implements ICurioItem {

    public ArmorPiercingGlove(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return slotContext.identifier().equals("hands");
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
            ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = ICurioItem.super.getAttributeModifiers(slotContext, uuid,
                stack);

        try {
            Attribute armorNegation = ForgeRegistries.ATTRIBUTES
                    .getValue(new ResourceLocation("epicfight", "armor_negation"));
            if (armorNegation != null) {
                modifiers.put(armorNegation,
                        new AttributeModifier(UUID.fromString("e5c3b2a1-0000-0000-0000-000000000000"),
                                "Armor Piercing Glove Bonus", 100.0, AttributeModifier.Operation.ADDITION));
            }
        } catch (Exception e) {
        }

        return modifiers;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        tooltip.add(Component.literal(""));
        tooltip.add(Component.translatable("item.lotaartifacts.armor_piercing_glove.tooltip.title")
                .withStyle(ChatFormatting.RED, ChatFormatting.BOLD));
        tooltip.add(Component.translatable("item.lotaartifacts.armor_piercing_glove.tooltip.line1")
                .withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("item.lotaartifacts.armor_piercing_glove.tooltip.line2_1")
                .withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("item.lotaartifacts.armor_piercing_glove.tooltip.line2_2")
                .withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal(""));
        tooltip.add(Component.translatable("item.lotaartifacts.armor_piercing_glove.tooltip.slot")
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
    }
}
