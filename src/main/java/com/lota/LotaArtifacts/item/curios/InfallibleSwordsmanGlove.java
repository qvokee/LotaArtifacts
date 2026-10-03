package com.lota.LotaArtifacts.item.curios;

import com.google.common.collect.Multimap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class InfallibleSwordsmanGlove extends BaseArtifactItem {
        private static final UUID DAMAGE_UUID = UUID.fromString("3f4e5d6c-7b8a-9012-3456-7890abcdef01");
        private static final UUID PENALTY_UUID = UUID.fromString("4a5b6c7d-8e9f-0123-4567-89abcdef0123");

        public InfallibleSwordsmanGlove(Properties properties) {
                super(properties, "hands", 2);
        }

        @Override
        public java.util.List<net.minecraft.network.chat.Component> getAttributesTooltip(
                        java.util.List<net.minecraft.network.chat.Component> tooltips, ItemStack stack) {
                return new java.util.ArrayList<>();
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

                modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(DAMAGE_UUID, "Total Damage Bonus", 0.10,
                                AttributeModifier.Operation.MULTIPLY_TOTAL));
                modifiers.put(Attributes.MAX_HEALTH, new AttributeModifier(PENALTY_UUID, "Health Penalty", -0.10,
                                AttributeModifier.Operation.MULTIPLY_TOTAL));
                modifiers.put(Attributes.ARMOR, new AttributeModifier(PENALTY_UUID, "Armor Penalty", -0.10,
                                AttributeModifier.Operation.MULTIPLY_TOTAL));

                try {
                        Attribute stunArmor = ForgeRegistries.ATTRIBUTES
                                        .getValue(new ResourceLocation("epicfight", "stun_armor"));
                        if (stunArmor != null) {
                                modifiers.put(stunArmor,
                                                new AttributeModifier(PENALTY_UUID, "Stun Armor Penalty", -0.20,
                                                                AttributeModifier.Operation.MULTIPLY_TOTAL));
                        }
                } catch (Exception e) {
                }

                return modifiers;
        }
}
