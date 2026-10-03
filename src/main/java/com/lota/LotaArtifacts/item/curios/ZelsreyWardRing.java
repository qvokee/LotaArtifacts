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

public class ZelsreyWardRing extends BaseArtifactItem {
    private static final UUID ZELSREY_UUID = UUID.fromString("23456789-0abc-def0-1234-567890abcdef");

    public ZelsreyWardRing(Properties properties) {
        super(properties, "ring", 2);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
            ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = super.getAttributeModifiers(slotContext, uuid,
                stack);

        modifiers.put(Attributes.ATTACK_DAMAGE,
                new AttributeModifier(ZELSREY_UUID, "Zelsrey Damage Bonus", 0.5, AttributeModifier.Operation.ADDITION));
        modifiers.put(Attributes.ARMOR,
                new AttributeModifier(ZELSREY_UUID, "Zelsrey Armor Bonus", 2.0, AttributeModifier.Operation.ADDITION));

        try {
            Attribute stunArmor = ForgeRegistries.ATTRIBUTES
                    .getValue(ResourceLocation.tryParse("epicfight:stun_armor"));
            if (stunArmor != null) {
                modifiers.put(stunArmor, new AttributeModifier(ZELSREY_UUID, "Zelsrey Stun Armor Bonus", 4.0,
                        AttributeModifier.Operation.ADDITION));
            }
        } catch (Exception e) {
        }

        return modifiers;
    }
}
