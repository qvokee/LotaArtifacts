package com.lota.LotaArtifacts.item.curios;

import com.google.common.collect.Multimap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class DualWieldGlove extends BaseArtifactItem {
    private static final UUID OFFHAND_BONUS_UUID = UUID.fromString("7d8e9f01-2345-6789-abcd-ef0123456789");

    public DualWieldGlove(Properties properties) {
        super(properties, "hands", 2);
    }

    @Override
    public java.util.List<net.minecraft.network.chat.Component> getAttributesTooltip(
            java.util.List<net.minecraft.network.chat.Component> tooltips, ItemStack stack) {
        return new java.util.ArrayList<>();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
            ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = super.getAttributeModifiers(slotContext, uuid,
                stack);

        try {
            Attribute offhandImpact = ForgeRegistries.ATTRIBUTES
                    .getValue(new ResourceLocation("epicfight", "offhand_impact"));
            if (offhandImpact != null) {
                modifiers.put(offhandImpact, new AttributeModifier(OFFHAND_BONUS_UUID, "Offhand Impact Bonus", 2.0,
                        AttributeModifier.Operation.ADDITION));
            }
            Attribute offhandArmorNegation = ForgeRegistries.ATTRIBUTES
                    .getValue(new ResourceLocation("epicfight", "offhand_armor_negation"));
            if (offhandArmorNegation != null) {
                modifiers.put(offhandArmorNegation, new AttributeModifier(OFFHAND_BONUS_UUID,
                        "Offhand Armor Negation Bonus", 5.0, AttributeModifier.Operation.ADDITION));
            }
        } catch (Exception e) {
        }

        return modifiers;
    }
}
