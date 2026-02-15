package com.lota.LotaArtifacts.curios;

import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

public class CuriosSlotRegistry {

        public static void registerSlots(InterModEnqueueEvent event) {
                InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE,
                                () -> SlotTypePreset.RING.getMessageBuilder().size(2).build());

                InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE,
                                () -> SlotTypePreset.NECKLACE.getMessageBuilder().size(1).build());

                InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE,
                                () -> SlotTypePreset.HANDS.getMessageBuilder().size(1).build());

                InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE,
                                () -> SlotTypePreset.BACK.getMessageBuilder().size(1).build());
        }
}
