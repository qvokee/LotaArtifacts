package com.lota.LotaArtifacts.item.curios;

import com.google.common.collect.Multimap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class MeldrinRing extends BaseArtifactItem {
    private static final UUID SPELL_BONUS_UUID = UUID.fromString("6c7d8e9f-0123-4567-89ab-cdef01234567");

    public MeldrinRing(Properties properties) {
        super(properties, "ring", 2);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
            ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = super.getAttributeModifiers(slotContext, uuid,
                stack);

        try {
            Attribute spellPower = ForgeRegistries.ATTRIBUTES
                    .getValue(ResourceLocation.tryParse("irons_spellbooks:spell_power"));
            if (spellPower != null) {
                modifiers.put(spellPower, new AttributeModifier(SPELL_BONUS_UUID, "Spell Power Bonus", 0.20,
                        AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
            Attribute castTimeReduction = ForgeRegistries.ATTRIBUTES
                    .getValue(ResourceLocation.tryParse("irons_spellbooks:cast_time_reduction"));
            if (castTimeReduction != null) {
                modifiers.put(castTimeReduction, new AttributeModifier(SPELL_BONUS_UUID, "Cast Time Penalty", -0.20,
                        AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
        } catch (Exception e) {
        }

        return modifiers;
    }
}
