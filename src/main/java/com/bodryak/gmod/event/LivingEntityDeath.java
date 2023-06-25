package com.bodryak.gmod.event;

import com.bodryak.gmod.network.ModMessages;
import com.bodryak.gmod.network.s2c.playerdata.PDSyncS2CPacket;
import com.bodryak.gmod.variables.server.ProviderPDS;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber
public class LivingEntityDeath {
    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if(!event.getEntity().getLevel().isClientSide()) {
            if(event.getSource().getEntity() instanceof ServerPlayer player) {
                Map<String, Long> toSync = new HashMap<String, Long>();
                player.getCapability(ProviderPDS.PLAYER_DATA).ifPresent(stats -> {
                    stats.setExp(100);
                    //ModMessages.sendToPlayer(new PDSyncS2CPacket(stats.getExp()), player);
                    while (stats.getExp() > stats.getNlv()){
                        stats.lvlUp();
                        //ModMessages.sendToPlayer(new PDSyncS2CPacket(stats.getExp()), player);
                        toSync.put("lvl", stats.getLvl());
                        toSync.put("nlv", stats.getNlv());
                        toSync.put("ch_point", (long) stats.getCh_point());
                        toSync.put("max_hp", stats.getMax_hp());
                        toSync.put("max_mana", stats.getMax_mana());
                        toSync.put("phis_attack", (long) stats.getPhis_attack());
                        toSync.put("mag_attack", (long) stats.getMag_attack());
                    }
                    toSync.put("exp", stats.getExp());
                    ModMessages.sendToPlayer(new PDSyncS2CPacket(toSync), player);
                });
            }
        }
    }
}
