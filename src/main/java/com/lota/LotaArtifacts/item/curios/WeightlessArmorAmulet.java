package com.lota.LotaArtifacts.item.curios;

import com.google.common.collect.Multimap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class WeightlessArmorAmulet extends BaseArtifactItem {
    private final float weightReduction;
    private final float armorPenalty;
    private static final UUID WEIGHT_UUID = UUID.fromString("6a7b8c9d-0e1f-2a3b-4c5d-6e7f8a9b0c1d");
    private static final UUID ARMOR_UUID = UUID.fromString("7b8c9d0e-1f2a-3b4c-5d6e-7f8a9b0c1d2e");

    public WeightlessArmorAmulet(Properties properties, float weightReduction, float armorPenalty, int tooltipLines) {
        super(properties, "necklace", tooltipLines);
        this.weightReduction = weightReduction;
        this.armorPenalty = armorPenalty;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
            ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = super.getAttributeModifiers(slotContext, uuid,
                stack);

        try {
            Attribute weight = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("epicfight", "weight"));
            if (weight != null) {
                modifiers.put(weight, new AttributeModifier(WEIGHT_UUID, "Weight Reduction", -weightReduction,
                        AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
        } catch (Exception e) {
        }

        if (armorPenalty > 0) {
            modifiers.put(Attributes.ARMOR, new AttributeModifier(ARMOR_UUID, "Armor Penalty", -armorPenalty,
                    AttributeModifier.Operation.MULTIPLY_TOTAL));
        }

        return modifiers;
    }
}
