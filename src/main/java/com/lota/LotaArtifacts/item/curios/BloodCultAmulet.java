package com.lota.LotaArtifacts.item.curios;

import com.google.common.collect.Multimap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class BloodCultAmulet extends BaseArtifactItem {
    private static final UUID BLOOD_BONUS_UUID = UUID.fromString("5b6c7d8e-9f01-2345-6789-abcdef012345");

    public BloodCultAmulet(Properties properties) {
        super(properties, "necklace", 2);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
            ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = super.getAttributeModifiers(slotContext, uuid,
                stack);

        try {
            Attribute bloodPower = ForgeRegistries.ATTRIBUTES
                    .getValue(new ResourceLocation("irons_spellbooks", "blood_spell_power"));
            if (bloodPower != null) {
                modifiers.put(bloodPower, new AttributeModifier(BLOOD_BONUS_UUID, "Blood Spell Power Bonus", 0.15,
                        AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
            Attribute bloodResist = ForgeRegistries.ATTRIBUTES
                    .getValue(new ResourceLocation("irons_spellbooks", "blood_spell_resist"));
            if (bloodResist != null) {
                modifiers.put(bloodResist, new AttributeModifier(BLOOD_BONUS_UUID, "Blood Spell Resist Bonus", 0.30,
                        AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
        } catch (Exception e) {
        }

        return modifiers;
    }
}
