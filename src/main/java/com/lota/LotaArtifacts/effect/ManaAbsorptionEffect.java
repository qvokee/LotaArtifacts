package com.lota.LotaArtifacts.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class ManaAbsorptionEffect extends MobEffect {
    public ManaAbsorptionEffect(MobEffectCategory category, int color) {
        super(category, color);
        try {
            net.minecraft.world.entity.ai.attributes.Attribute cdReduction = ForgeRegistries.ATTRIBUTES
                    .getValue(new net.minecraft.resources.ResourceLocation("irons_spellbooks", "cooldown_reduction"));
            if (cdReduction != null) {
                this.addAttributeModifier(cdReduction, "b2a1c0d3-4567-89ab-cdef-0123456789ab", 2.0,
                        AttributeModifier.Operation.ADDITION);
            }
        } catch (Exception ignored) {
        }
    }
}
