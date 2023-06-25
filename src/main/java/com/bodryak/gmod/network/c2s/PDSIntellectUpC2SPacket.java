package com.bodryak.gmod.network.c2s;

import com.bodryak.gmod.network.ModMessages;
import com.bodryak.gmod.network.s2c.playerdata.PDSyncS2CPacket;
import com.bodryak.gmod.variables.server.ProviderPDS;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class PDSIntellectUpC2SPacket {
    public PDSIntellectUpC2SPacket() {

    }
    public PDSIntellectUpC2SPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        Map<String, Long> toSync = new HashMap<String, Long>();
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            context.getSender().getCapability(ProviderPDS.PLAYER_DATA).ifPresent(pds -> {
                pds.intellectUp();
                toSync.put("ch_point", (long) pds.getCh_point());
                toSync.put("intellect", (long) pds.getIntellect());
                toSync.put("mag_attack", (long) pds.getMag_attack());
                toSync.put("fire_def", pds.getFire_def());
                toSync.put("tree_def", pds.getTree_def());
                toSync.put("water_def", pds.getWater_def());
                toSync.put("eth_def", pds.getEth_def());
                toSync.put("metal_def", pds.getMetal_def());
                toSync.put("mag_def", pds.getMag_def());
                ModMessages.sendToPlayer(new PDSyncS2CPacket(toSync), context.getSender());
            });
        });
        return true;
    }
}
