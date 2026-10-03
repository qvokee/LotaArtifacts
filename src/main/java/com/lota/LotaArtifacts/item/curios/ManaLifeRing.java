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

public class ManaLifeRing extends BaseArtifactItem {
    private static final UUID MANA_PENALTY_UUID = UUID.fromString("1d2c3b4a-5e6f-7a8b-9c0d-1e2f3a4b5c6d");
    private static final UUID HEALTH_BONUS_UUID = UUID.fromString("2e3d4c5b-6a7b-8c9d-0e1f-2a3b4c5d6e7f");

    public ManaLifeRing(Properties properties) {
        super(properties, "ring", 2);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        LivingEntity living = slotContext.entity();
        living.setHealth(Math.min(living.getHealth(), living.getMaxHealth()));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
            ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = super.getAttributeModifiers(slotContext, uuid,
                stack);

        try {
            Attribute maxMana = ForgeRegistries.ATTRIBUTES
                    .getValue(ResourceLocation.tryParse("irons_spellbooks:max_mana"));
            if (maxMana != null) {
                modifiers.put(maxMana, new AttributeModifier(MANA_PENALTY_UUID, "Mana Penalty", -0.5,
                        AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
        } catch (Exception e) {
        }

        modifiers.put(Attributes.MAX_HEALTH, new AttributeModifier(HEALTH_BONUS_UUID, "Health Conversion Bonus", 6.0,
                AttributeModifier.Operation.ADDITION));

        return modifiers;
    }
}
