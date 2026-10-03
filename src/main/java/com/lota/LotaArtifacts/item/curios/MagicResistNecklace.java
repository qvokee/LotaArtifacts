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

public class MagicResistNecklace extends Item implements ICurioItem {

    private static final UUID ATTRIBUTE_UUID = UUID.fromString("7f8a9b1c-2d3e-4f5a-6b7c-8d9e0f1a2b3c");

    public MagicResistNecklace(Properties properties) {
        super(properties);
    }

    @Override
    public java.util.List<net.minecraft.network.chat.Component> getAttributesTooltip(
            java.util.List<net.minecraft.network.chat.Component> tooltips, ItemStack stack) {
        return new java.util.ArrayList<>();
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return slotContext.identifier().equals("necklace");
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
            ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = ICurioItem.super.getAttributeModifiers(slotContext, uuid,
                stack);

        try {
            Attribute spellResist = ForgeRegistries.ATTRIBUTES
                    .getValue(new ResourceLocation("irons_spellbooks", "spell_resist"));
            if (spellResist != null) {
                modifiers.put(spellResist, new AttributeModifier(ATTRIBUTE_UUID,
                        "Magic Resist Necklace Bonus", 0.25, AttributeModifier.Operation.ADDITION));
            }
        } catch (Exception e) {
        }

        return modifiers;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        tooltip.add(Component.literal(""));
        tooltip.add(Component.translatable("item.lotaartifacts.magic_resist_necklace.tooltip.title")
                .withStyle(ChatFormatting.LIGHT_PURPLE, ChatFormatting.BOLD));
        tooltip.add(Component.translatable("item.lotaartifacts.magic_resist_necklace.tooltip.line1")
                .withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal(""));
        tooltip.add(Component.translatable("item.lotaartifacts.magic_resist_necklace.tooltip.slot")
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
    }
}
