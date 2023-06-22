package com.bodryak.gmod.network.c2s;

import com.bodryak.gmod.network.ModMessages;
import com.bodryak.gmod.network.s2c.playerdata.PDSyncS2CPacket;
import com.bodryak.gmod.variables.server.PDS;
import com.bodryak.gmod.variables.server.ProviderPDS;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class PDSStaminaUpC2SPacket {
    public PDSStaminaUpC2SPacket() {

    }
    public PDSStaminaUpC2SPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        Map<String, Long> toSync = new HashMap<String, Long>();
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            context.getSender().getCapability(ProviderPDS.PLAYER_DATA).ifPresent(pds -> {
                pds.staminaUp();
                toSync.put("ch_point", (long) pds.getCh_point());
                toSync.put("stamina", (long) pds.getStamina());
                toSync.put("max_hp", pds.getMax_hp());
                toSync.put("phis_def", pds.getPhis_def());
                toSync.put("mag_def", pds.getMag_def());
                toSync.put("fire_def", pds.getFire_def());
                toSync.put("water_def", pds.getWater_def());
                toSync.put("tree_def", pds.getTree_def());
                toSync.put("eth_def", pds.getEth_def());
                toSync.put("metal_def", pds.getMetal_def());
                ModMessages.sendToPlayer(new PDSyncS2CPacket(toSync), context.getSender());
            });
        });
        return true;
    }
}
