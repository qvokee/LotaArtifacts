package com.lota.LotaArtifacts.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class CriticalElevationEffect extends MobEffect {
    public CriticalElevationEffect(MobEffectCategory category, int color) {
        super(category, color);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "f4b2a1c0-5678-49ab-bcde-f0123456789a", 0.15,
                AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, "a1b2c3d4-e5f6-47a8-b9c0-d1e2f3a4b5c6", 0.10,
                AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
