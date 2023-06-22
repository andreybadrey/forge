package com.bodryak.gmod.network.s2c.playerdata;

import com.bodryak.gmod.variables.client.PDC;
import com.google.common.collect.Maps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.Map;
import java.util.function.Supplier;

public class PDSyncS2CPacket {
    private final Map<String, Long> toSync ;

    public PDSyncS2CPacket(Map<String, Long> map) {
        this.toSync = map;
    }

    public PDSyncS2CPacket(FriendlyByteBuf buf) {
        this.toSync = buf.readMap(Maps::newHashMapWithExpectedSize,FriendlyByteBuf::readUtf, FriendlyByteBuf::readLong);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeMap(this.toSync, FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeLong);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            if (this.toSync.get("lvl") != null){
                PDC.setLvl(this.toSync.get("lvl"));
            }
            if (this.toSync.get("exp") != null){
                PDC.setExp(this.toSync.get("exp"));
            }
            if (this.toSync.get("nlv") != null){
                PDC.setNlv(this.toSync.get("nlv"));
            }
            if (this.toSync.get("hp") != null){
                PDC.setHp(this.toSync.get("hp"));
            }
            if (this.toSync.get("max_hp") != null){
                PDC.setMax_hp(this.toSync.get("max_hp"));
            }
            if (this.toSync.get("mana") != null){
                PDC.setMana(this.toSync.get("mana"));
            }
            if (this.toSync.get("max_mana") != null) {
                PDC.setMax_mana(this.toSync.get("max_mana"));
            }
            if (this.toSync.get("ch_point") != null) {
                PDC.setCh_point(Math.toIntExact(this.toSync.get("ch_point")));
            }
            if (this.toSync.get("phis_attack") != null) {
                PDC.setPhis_attack(Math.toIntExact(this.toSync.get("phis_attack")));
            }
            if (this.toSync.get("mag_attack") != null) {
                PDC.setMag_attack(Math.toIntExact(this.toSync.get("mag_attack")));
            }
            if (this.toSync.get("stamina") != null) {
                PDC.setStamina(Math.toIntExact(this.toSync.get("stamina")));
            }
            if (this.toSync.get("strength") != null) {
                PDC.setStrength(Math.toIntExact(this.toSync.get("strength")));
            }
            if (this.toSync.get("intellect") != null) {
                PDC.setIntellect(Math.toIntExact(this.toSync.get("intellect")));
            }
            if (this.toSync.get("dexterity") != null) {
                PDC.setDexterity(Math.toIntExact(this.toSync.get("dexterity")));
            }
            if (this.toSync.get("phis_def") != null) {
                PDC.setPhis_def(this.toSync.get("phis_def"));
            }
            if (this.toSync.get("mag_def") != null) {
                PDC.setMag_def(this.toSync.get("mag_def"));
            }
            if (this.toSync.get("fire_def") != null) {
                PDC.setFire_def(this.toSync.get("fire_def"));
            }
            if (this.toSync.get("water_def") != null) {
                PDC.setWater_def(this.toSync.get("water_def"));
            }
            if (this.toSync.get("tree_def") != null) {
                PDC.setTree_def(this.toSync.get("tree_def"));
            }
            if (this.toSync.get("eth_def") != null) {
                PDC.setEth_def(this.toSync.get("eth_def"));
            }
            if (this.toSync.get("metal_def") != null) {
                PDC.setMetal_def(this.toSync.get("metal_def"));
            }
            if (this.toSync.get("critical_chance") != null) {
                PDC.setCritical_chance(this.toSync.get("critical_chance"));
            }
            if (this.toSync.get("critical_damage") != null) {
                PDC.setCritical_damage(this.toSync.get("critical_damage"));
            }
            if (this.toSync.get("accuracy") != null) {
                PDC.setAccuracy(this.toSync.get("accuracy"));
            }
            if (this.toSync.get("avoidance") != null) {
                PDC.setAvoidance(this.toSync.get("avoidance"));
            }
        });
        return true;
    }
}