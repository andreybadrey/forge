package com.bodryak.gmod.event;

import com.bodryak.gmod.GmodMod;
import com.bodryak.gmod.network.ModMessages;
import com.bodryak.gmod.network.s2c.playerdata.PDSyncS2CPacket;
import com.bodryak.gmod.variables.server.MDS;
import com.bodryak.gmod.variables.server.PDS;
import com.bodryak.gmod.variables.server.ProviderMDS;
import com.bodryak.gmod.variables.server.ProviderPDS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

public class CapsEvents {
    @Mod.EventBusSubscriber(modid = GmodMod.MODID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
            if(event.getObject() instanceof Player) {
                if(!event.getObject().getCapability(ProviderPDS.PLAYER_DATA).isPresent()) {
                    event.addCapability(new ResourceLocation(GmodMod.MODID, "stat"), new ProviderPDS());
                }
            }
            if(event.getObject() instanceof LivingEntity && !(event.getObject() instanceof Player)){
                if(!event.getObject().getCapability(ProviderMDS.MOB_DATA).isPresent()){
                    event.addCapability(new ResourceLocation(GmodMod.MODID, "mob_data"), new ProviderMDS());
                }
            }
        }
        @SubscribeEvent
        public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
            event.register(PDS.class);
            event.register(MDS.class);
        }


        @SubscribeEvent
        public static void onPlayerJoin(EntityJoinLevelEvent event) {
            if (!event.getLevel().isClientSide()) {
                if (event.getEntity() instanceof ServerPlayer player) {
                    Map<String, Long> toSync = new HashMap<String, Long>();
                    player.getCapability(ProviderPDS.PLAYER_DATA).ifPresent(stats -> {
                        toSync.put("lvl", stats.getLvl());
                        toSync.put("exp", stats.getExp());
                        toSync.put("nlv", stats.getNlv());

                        toSync.put("hp", stats.getHp());
                        toSync.put("max_hp", stats.getMax_hp());

                        toSync.put("mana", stats.getMana());
                        toSync.put("max_mana", stats.getMax_mana());

                        toSync.put("ch_point", (long) stats.getCh_point());

                        toSync.put("phis_attack", (long) stats.getPhis_attack());
                        toSync.put("mag_attack", (long) stats.getMag_attack());

                        toSync.put("stamina", (long) stats.getStamina());
                        toSync.put("strength", (long) stats.getStrength());
                        toSync.put("intellect", (long) stats.getIntellect());
                        toSync.put("dexterity", (long) stats.getDexterity());

                        toSync.put("phis_def", stats.getPhis_def());
                        toSync.put("mag_def", stats.getMag_def());

                        toSync.put("fire_def", stats.getFire_def());
                        toSync.put("water_def", stats.getWater_def());
                        toSync.put("tree_def", stats.getTree_def());
                        toSync.put("eth_def", stats.getEth_def());
                        toSync.put("metal_def", stats.getMetal_def());

                        toSync.put("critical_chance", stats.getCritical_chance());
                        toSync.put("critical_damage", stats.getCritical_damage());
                        toSync.put("accuracy", stats.getAccuracy());
                        toSync.put("avoidance", stats.getAvoidance());

                        ModMessages.sendToPlayer(new PDSyncS2CPacket(toSync), player);
                    });
                }
            }
        }


        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            if(event.side == LogicalSide.SERVER){
                if(event.phase == TickEvent.Phase.END){
                    Map<String, Long> toSync = new HashMap<String, Long>();
                    event.player.getCapability(ProviderPDS.PLAYER_DATA).ifPresent(stats -> {
                        if(stats.regenHP()){
                            toSync.put("hp", stats.getHp());
                        }
                        if(stats.regenMP()){
                            toSync.put("mana", stats.getMana());
                        }
                        if(!toSync.isEmpty()){
                            ModMessages.sendToPlayer(new PDSyncS2CPacket(toSync), (ServerPlayer) event.player);
                        }
                    });
                }
            }
        }
    }
}
