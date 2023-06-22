package com.bodryak.gmod.network.c2s;

import com.bodryak.gmod.network.ModMessages;
import com.bodryak.gmod.network.s2c.playerdata.PDSyncS2CPacket;
import com.bodryak.gmod.variables.server.ProviderPDS;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class PDSDexterityUpC2SPacket {
    public PDSDexterityUpC2SPacket() {

    }
    public PDSDexterityUpC2SPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        Map<String, Long> toSync = new HashMap<String, Long>();
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            context.getSender().getCapability(ProviderPDS.PLAYER_DATA).ifPresent(pds -> {
                pds.dexterityUp();
                toSync.put("ch_point", (long) pds.getCh_point());
                toSync.put("dexterity", (long) pds.getDexterity());
                toSync.put("accuracy", pds.getAccuracy());
                toSync.put("avoidance", pds.getAvoidance());
                toSync.put("critical_chance", pds.getCritical_chance());
                ModMessages.sendToPlayer(new PDSyncS2CPacket(toSync), context.getSender());
            });
        });
        return true;
    }
}
