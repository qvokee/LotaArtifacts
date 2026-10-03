package com.lota.LotaArtifacts.item.curios;

import com.google.common.collect.Multimap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class TrueMageRing extends BaseArtifactItem {
    private static final UUID MAGE_BONUS_UUID = UUID.fromString("8e9f0123-4567-89ab-cdef-0123456789ab");

    public TrueMageRing(Properties properties) {
        super(properties, "ring", 2);
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
                modifiers.put(maxMana, new AttributeModifier(MAGE_BONUS_UUID, "Max Mana Bonus", 0.50,
                        AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
            Attribute stamina = ForgeRegistries.ATTRIBUTES.getValue(ResourceLocation.tryParse("epicfight:stamina"));
            if (stamina != null) {
                modifiers.put(stamina, new AttributeModifier(MAGE_BONUS_UUID, "Stamina Penalty", -0.60,
                        AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
        } catch (Exception e) {
        }

        return modifiers;
    }
}
