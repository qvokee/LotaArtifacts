package com.lota.LotaArtifacts.item.curios;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class OverTensionRing extends BaseArtifactItem {
    private static final UUID PENALTY_UUID = UUID.fromString("9f012345-6789-abcd-ef01-23456789abcd");

    public OverTensionRing(Properties properties) {
        super(properties, "ring", 2);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
            ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = super.getAttributeModifiers(slotContext, uuid,
                stack);

        modifiers.put(Attributes.ARMOR, new AttributeModifier(PENALTY_UUID, "Over Tension Armor Penalty", -0.40,
                AttributeModifier.Operation.MULTIPLY_TOTAL));

        return modifiers;
    }
}
