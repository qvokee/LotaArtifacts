package com.lota.LotaArtifacts.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class DarkMarkEffect extends MobEffect {
    public DarkMarkEffect(MobEffectCategory category, int color) {
        super(category, color);
        this.addAttributeModifier(Attributes.ARMOR, "5d7a315e-5b6d-49d7-832c-352b2f6c91d8", -0.5,
                AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "e9f73a3c-b3a1-4e7a-9a0e-f3e04897a582", 0.5,
                AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
