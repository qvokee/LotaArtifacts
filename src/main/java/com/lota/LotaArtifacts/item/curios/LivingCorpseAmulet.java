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

public class LivingCorpseAmulet extends BaseArtifactItem {
        private static final UUID CORPSE_MOD_UUID = UUID.fromString("01234567-89ab-cdef-0123-456789abcdef");

        public LivingCorpseAmulet(Properties properties) {
                super(properties, "necklace", 2);
        }

        @Override
        public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
                        ItemStack stack) {
                Multimap<Attribute, AttributeModifier> modifiers = super.getAttributeModifiers(slotContext, uuid,
                                stack);

                modifiers.put(Attributes.MAX_HEALTH,
                                new AttributeModifier(CORPSE_MOD_UUID, "Living Corpse HP Bonus", 14.0,
                                                AttributeModifier.Operation.ADDITION));
                modifiers.put(Attributes.ARMOR,
                                new AttributeModifier(CORPSE_MOD_UUID, "Living Corpse Armor Penalty", -0.15,
                                                AttributeModifier.Operation.MULTIPLY_TOTAL));
                modifiers.put(Attributes.ATTACK_DAMAGE,
                                new AttributeModifier(CORPSE_MOD_UUID, "Living Corpse Damage Penalty",
                                                -3.5, AttributeModifier.Operation.ADDITION));

                try {
                        Attribute impact = ForgeRegistries.ATTRIBUTES
                                        .getValue(new ResourceLocation("epicfight", "impact"));
                        if (impact != null) {
                                modifiers.put(impact,
                                                new AttributeModifier(CORPSE_MOD_UUID, "Living Corpse Impact Penalty",
                                                                -1.0,
                                                                AttributeModifier.Operation.MULTIPLY_TOTAL));
                        }
                } catch (Exception e) {
                }

                return modifiers;
        }
}
