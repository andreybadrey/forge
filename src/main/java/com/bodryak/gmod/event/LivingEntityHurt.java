package com.bodryak.gmod.event;

import com.bodryak.gmod.network.ModMessages;
import com.bodryak.gmod.network.s2c.mobdata.MDSyncS2CPacket;
import com.bodryak.gmod.network.s2c.playerdata.PDSyncS2CPacket;
import com.bodryak.gmod.variables.server.ProviderMDS;
import com.bodryak.gmod.variables.server.ProviderPDS;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Mod.EventBusSubscriber
public class LivingEntityHurt {
    @SubscribeEvent
    public static void onEntityAttacked(LivingHurtEvent event) {
        if(!event.getEntity().getLevel().isClientSide()){
            if(event.getEntity() instanceof ServerPlayer player){
                Map<String, Long> toSync = new HashMap<String, Long>();
                player.getCapability(ProviderPDS.PLAYER_DATA).ifPresent(stats -> {
                    float damage = 0.0f;
                    if (event.getSource().isFall()){
                        if(event.getAmount() < 10.0f){
                            damage = event.getAmount() * 2;
                        } else if (event.getAmount() >= 10.f && event.getAmount() < 30.f) {
                            damage = (event.getAmount()/10) * event.getAmount() * 2;
                        } else if (event.getAmount() >= 30.f) {
                            damage = stats.getHp();
                        }
                    }
                    if (event.getSource().getEntity() instanceof LivingEntity) {
                        damage = 10.0f - (Math.round((float) stats.getPhis_def() / 100));
                        if(damage < 1){
                            damage = 1;
                        }
                    }
                    stats.playerHurt(Math.round(damage));
                    toSync.put("hp", stats.getHp());
                    ModMessages.sendToPlayer(new PDSyncS2CPacket(toSync), player);
                    if(stats.getHp() <= 0){
                        event.setAmount(1000f);
                    }else {
                       // event.setAmount(0f);
                    }
                });
            }
            if(event.getSource().getEntity() instanceof ServerPlayer player){
                AtomicInteger damage = new AtomicInteger();
                player.getCapability(ProviderPDS.PLAYER_DATA).ifPresent(pds -> {
                    damage.set(pds.getPhis_attack());
                });
                event.getEntity().getCapability(ProviderMDS.MOB_DATA).ifPresent(mob -> {
                    mob.hurt(damage);
                    ModMessages.sendToClients(new MDSyncS2CPacket(mob.getHp(), event.getEntity().getUUID(), event.getEntity().getOnPos()));
                    if(mob.getHp() <= 0){
                        event.setAmount(1000f);
                    }else {
                        event.setAmount(0f);
                    }

                });

            }
        }
    }
}
