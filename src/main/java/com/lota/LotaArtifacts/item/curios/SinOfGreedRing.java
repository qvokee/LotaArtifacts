package com.lota.LotaArtifacts.item.curios;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class SinOfGreedRing extends BaseArtifactItem {
        private static final UUID PENALTY_UUID = UUID.fromString("9a8b7c6d-5e4f-3a2b-1c0d-9e8f7a6b5c4d");

        public SinOfGreedRing(Properties properties) {
                super(properties, "ring", 2);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
                LivingEntity living = slotContext.entity();
                living.setHealth(Math.min(living.getHealth(), living.getMaxHealth()));
        }

        @Override
        public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
                        ItemStack stack) {
                Multimap<Attribute, AttributeModifier> modifiers = super.getAttributeModifiers(slotContext, uuid,
                                stack);

                modifiers.put(Attributes.ATTACK_DAMAGE,
                                new AttributeModifier(PENALTY_UUID, "Greed Damage Penalty", -0.30,
                                                AttributeModifier.Operation.MULTIPLY_TOTAL));
                modifiers.put(Attributes.MAX_HEALTH, new AttributeModifier(PENALTY_UUID, "Greed Health Penalty", -0.30,
                                AttributeModifier.Operation.MULTIPLY_TOTAL));
                modifiers.put(Attributes.ARMOR, new AttributeModifier(PENALTY_UUID, "Greed Armor Penalty", -0.30,
                                AttributeModifier.Operation.MULTIPLY_TOTAL));

                return modifiers;
        }
}
