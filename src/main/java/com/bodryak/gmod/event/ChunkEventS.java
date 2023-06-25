package com.bodryak.gmod.event;

import com.bodryak.gmod.network.ModMessages;
import com.bodryak.gmod.network.s2c.mobdata.MDSyncS2CPacket;
import com.bodryak.gmod.variables.server.ProviderMDS;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.level.ChunkWatchEvent;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class ChunkEventS {
    @SubscribeEvent
    public static void chunkLoad(ChunkWatchEvent.Watch event) {/*
        if(!event.getLevel().isClientSide()){
            List<Entity> _entfound = event.getChunk().getLevel().getEntitiesOfClass(Entity.class, new AABB(event.getChunk().getPos().getMinBlockX(), -64, event.getChunk().getPos().getMinBlockZ(),
                    event.getChunk().getPos().getMaxBlockX(), 319, event.getChunk().getPos().getMaxBlockZ()));
            for (Entity entityiterator : _entfound) {
                System.out.println("Sync " + entityiterator.getUUID() + " for " + event.getPlayer());
                if (entityiterator instanceof ServerPlayer) {
                    System.out.println("Обнаружен игрок");
                }
                else {
                    entityiterator.getCapability(ProviderMDS.MOB_DATA).ifPresent(mds -> {
                        System.out.println("Отправка " + mds.getHp());
                        ModMessages.sendToClients(new MDSyncS2CPacket(mds.getHp(), entityiterator.getUUID(), entityiterator.getOnPos()));
                    });
                }
            }

        }*/

    }

}
