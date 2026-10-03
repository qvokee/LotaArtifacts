package com.lota.LotaArtifacts.item.curios;

import com.google.common.collect.Multimap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class HeavyStepRing extends BaseArtifactItem {
    private static final UUID HEAVY_STEP_UUID = UUID.fromString("12345678-90ab-cdef-1234-567890abcdef");

    public HeavyStepRing(Properties properties) {
        super(properties, "ring", 2);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
            ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = super.getAttributeModifiers(slotContext, uuid,
                stack);

        try {
            Attribute impact = ForgeRegistries.ATTRIBUTES.getValue(ResourceLocation.tryParse("epicfight:impact"));
            Attribute weight = ForgeRegistries.ATTRIBUTES.getValue(ResourceLocation.tryParse("epicfight:weight"));

            if (impact != null) {
                modifiers.put(impact, new AttributeModifier(HEAVY_STEP_UUID, "Heavy Impact Bonus", 1.5,
                        AttributeModifier.Operation.ADDITION));
            }
            if (weight != null) {
                modifiers.put(weight, new AttributeModifier(HEAVY_STEP_UUID, "Heavy Weight Bonus", 20.0,
                        AttributeModifier.Operation.ADDITION));
            }
        } catch (Exception e) {
        }

        return modifiers;
    }
}
