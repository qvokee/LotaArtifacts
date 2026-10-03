package com.lota.LotaArtifacts.item.curios;

import com.google.common.collect.Multimap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class DrunkMasterRing extends BaseArtifactItem {
    private static final UUID DRUNK_UUID = UUID.fromString("34567890-abcd-ef01-2345-67890abcdef1");

    public DrunkMasterRing(Properties properties) {
        super(properties, "ring", 2);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
            ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = super.getAttributeModifiers(slotContext, uuid,
                stack);

        try {
            Attribute stunArmor = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("epicfight", "stun_armor"));
            if (stunArmor != null) {
                modifiers.put(stunArmor, new AttributeModifier(DRUNK_UUID, "Drunk Master Stun Armor Penalty", -0.20,
                        AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
        } catch (Exception e) {
        }

        return modifiers;
    }
}
