package com.bodryak.gmod.util;

import com.bodryak.gmod.GmodMod;
import com.bodryak.gmod.network.ModMessages;
import com.bodryak.gmod.network.c2s.MobSyncC2SPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

//@Mod.EventBusSubscriber(modid = GmodMod.MODID)
public class SyncScaner {
    static Map<UUID, BlockPos> scaner = new HashMap<UUID, BlockPos>();
    static Map<UUID, BlockPos> synced = new HashMap<UUID, BlockPos>();
    static int timer = 100;
    //@SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.CLIENT) {
            if (timer>0){
                timer--;
            } else if (timer<0) {
                timer = 0;
            }
            // TODO: 21.08.2023 добавить мультипоток в синхронизацию
            if (event.phase == TickEvent.Phase.START) {
                assert Minecraft.getInstance().player != null;
                Level world = Minecraft.getInstance().player.level;
                BlockPos pos = Minecraft.getInstance().player.getOnPos();
                final Vec3 _center = new Vec3(pos.getX(), pos.getY(), pos.getZ());
                List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(40 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
                for (Entity iterator : _entfound){
                    if (!(iterator instanceof LocalPlayer) && iterator instanceof LivingEntity){
                        scaner.put(iterator.getUUID(), iterator.getOnPos());
                    }
                }
                if(!scaner.isEmpty()){
                    //System.out.println("Обнаружены сущности " + scaner);
                    if (synced.isEmpty()) {
                        Map<UUID, BlockPos> buffer = new HashMap<UUID, BlockPos>(scaner);
                        for (Map.Entry<UUID, BlockPos> map : buffer.entrySet()) {
                            //System.out.println("Синхронизация сущности " + map.getKey());
                            ModMessages.sendToServer(new MobSyncC2SPacket(map.getKey(), map.getValue()));
                            synced.put(map.getKey(), map.getValue());
                            scaner.remove(map.getKey());
                        }
                    } else {
                        Map<UUID, BlockPos> buffer = new HashMap<UUID, BlockPos>(synced);
                        for (Map.Entry<UUID, BlockPos> map : buffer.entrySet()) {
                            //System.out.println("Проверка сущности " + map.getKey());
                            if (scaner.get(map.getKey()) == null) {
                                //System.out.println("Сущность не существует");
                                synced.remove(map.getKey());
                            }
                        }
                        Map<UUID, BlockPos> buffer2 = new HashMap<UUID, BlockPos>(scaner);
                        for (Map.Entry<UUID, BlockPos> map : buffer2.entrySet()) {
                            if (synced.get(map.getKey()) == null) {
                                //System.out.println("Сущность не синхронизирована ");
                                ModMessages.sendToServer(new MobSyncC2SPacket(map.getKey(), map.getValue()));
                                synced.put(map.getKey(), map.getValue());
                                scaner.remove(map.getKey());
                            } else {
                                //System.out.println("Сущность синхронизирована");
                                scaner.remove(map.getKey());
                            }
                        }

                    }
                } else {
                    synced.clear();
                }
            }

        }
    }
    //@SubscribeEvent
    public static void onPlayerJoin(EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide() && event.getEntity() instanceof LocalPlayer) {
            timer = 100;
            synced.clear();
            scaner.clear();
        }
    }
}
