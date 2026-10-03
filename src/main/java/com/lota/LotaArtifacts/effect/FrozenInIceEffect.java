package com.lota.LotaArtifacts.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class FrozenInIceEffect extends MobEffect {
    public FrozenInIceEffect(MobEffectCategory category, int color) {
        super(category, color);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "e1d2c3b4-a5f6-47b8-c9d0-e1f2a3b4c5d6", -1.0,
                AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "d1c2b3a4-0a1b-2c3d-4e5f-6a7b8c9d0e1f", -0.6,
                AttributeModifier.Operation.MULTIPLY_TOTAL);

        try {
            net.minecraft.world.entity.ai.attributes.Attribute stunArmor = net.minecraftforge.registries.ForgeRegistries.ATTRIBUTES
                    .getValue(new net.minecraft.resources.ResourceLocation("epicfight", "stun_armor"));
            if (stunArmor != null) {
                this.addAttributeModifier(stunArmor, "f2e3d4c5-b6a7-48c9-d0e1-f2a3b4c5d6e7", 20.0,
                        AttributeModifier.Operation.ADDITION);
            }
        } catch (Exception e) {
        }
    }
}
