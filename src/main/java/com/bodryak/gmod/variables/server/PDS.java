package com.bodryak.gmod.variables.server;

import net.minecraft.nbt.CompoundTag;

public class PDS {
    private long lvl = 1;
    private long exp;
    private long nlv = 100;

    private int ch_point;

    private long hp;
    private long max_hp = 70;

    private long mana;
    private long max_mana = 70;

    private int phis_attack = 1;
    private int mag_attack =1;

    private float hp_buf;
    private float mp_buf;

    private int stamina = 5;
    private int strength = 5;
    private int intellect = 5;
    private int dexterity = 5;

    private long phis_def = 100;
    private long mag_def = 100;

    private long fire_def = 100;
    private long water_def = 100;
    private long tree_def = 100;
    private long eth_def = 100;
    private long metal_def = 100;

    private long critical_chance = 100;
    private long critical_damage = 120;
    private long accuracy = 1;
    private long avoidance = 1;

    public long getCritical_chance() {
        return critical_chance;
    }
    public void setCritical_chance(long critical_chance) {
        this.critical_chance = critical_chance;
    }
    public long getCritical_damage() {
        return critical_damage;
    }
    public void setCritical_damage(long critical_damage) {
        this.critical_damage = critical_damage;
    }
    public long getAccuracy() {
        return accuracy;
    }
    public void setAccuracy(long accuracy) {
        this.accuracy = accuracy;
    }
    public long getAvoidance() {
        return avoidance;
    }
    public void setAvoidance(long avoidance) {
        this.avoidance = avoidance;
    }
    public long getPhis_def() {
        return phis_def;
    }
    public void setPhis_def(long phis_def) {
        this.phis_def = phis_def;
    }
    public long getMag_def() {
        return mag_def;
    }
    public void setMag_def(long mag_def) {
        this.mag_def = mag_def;
    }
    public long getFire_def() {
        return fire_def;
    }
    public void setFire_def(long fire_def) {
        this.fire_def = fire_def;
    }
    public long getWater_def() {
        return water_def;
    }
    public void setWater_def(long water_def) {
        this.water_def = water_def;
    }
    public long getTree_def() {
        return tree_def;
    }
    public void setTree_def(long tree_def) {
        this.tree_def = tree_def;
    }
    public long getEth_def() {
        return eth_def;
    }
    public void setEth_def(long eth_def) {
        this.eth_def = eth_def;
    }
    public long getMetal_def() {
        return metal_def;
    }
    public void setMetal_def(long metal_def) {
        this.metal_def = metal_def;
    }
    public int getStamina() {
        return stamina;
    }
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }
    public int getStrength() {
        return strength;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public int getIntellect() {
        return intellect;
    }
    public void setIntellect(int intellect) {
        this.intellect = intellect;
    }
    public int getDexterity() {
        return dexterity;
    }
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }
    public int getPhis_attack() {
        return phis_attack;
    }
    public void setPhis_attack(int phis_attack) {
        this.phis_attack = phis_attack;
    }
    public int getMag_attack() {
        return mag_attack;
    }
    public void setMag_attack(int mag_attack) {
        this.mag_attack = mag_attack;
    }
    public int getCh_point() {
        return ch_point;
    }
    public void setCh_point(int ch_point) {
        this.ch_point = ch_point;
    }
    public long getHp() {
        return hp;
    }
    public void setHp(long hp) {
        this.hp = hp;
    }
    public long getMax_hp() {
        return max_hp;
    }
    public void setMax_hp(long max_hp) {
        this.max_hp = max_hp;
    }
    public long getMana() {
        return mana;
    }
    public void setMana(long mana) {
        this.mana = mana;
    }
    public long getMax_mana() {
        return max_mana;
    }
    public void setMax_mana(long max_mana) {
        this.max_mana = max_mana;
    }
    public long getLvl() {
        return lvl;
    }
    public void setLvl(long lvl) {
        this.lvl = lvl;
    }
    public long getNlv() {
        return nlv;
    }
    public void setNlv(long nlv) {
        this.nlv = nlv;
    }
    public void setExp(long exp) {
        this.exp += exp;
    }
    public long getExp() {
        return exp;
    }
    public void copyFrom(PDS source) {
        this.exp = source.exp;
    }

    public void lvlUp(){
        this.lvl++;
        exp -= nlv;
        nlv *= 1.15;
        ch_point += 5;
        max_hp += 30;
        max_mana +=30;
        phis_attack++;
        mag_attack++;
    }

    public void staminaUp() {
        if(ch_point > 0) {
            ch_point--;
            stamina++;
            max_hp += 15;
            phis_def += 30;
            mag_def += 30;
            fire_def += 30;
            water_def += 30;
            tree_def += 30;
            eth_def += 30;
            metal_def += 30;
        }
    }

    public void strengthUp() {
        if (ch_point > 0) {
            ch_point --;
            strength ++;
            phis_def += 30;
            phis_attack ++;
        }
    }

    public void dexterityUp() {
        if (ch_point > 0) {
            ch_point --;
            dexterity ++;
            accuracy += 10;
            avoidance += 10;
            critical_chance += 5;
        }
    }

    public boolean regenHP() {
        if(hp<max_hp){
            hp_buf += 0.1f;
        }else {
            return false;
        }
        if(hp_buf > 1){
            hp++;
            hp_buf=0;
            return true;
        }else {
            return false;
        }
    }
    public boolean regenMP() {
        if(mana<max_mana) {
            mp_buf += 0.1f;
        } else {
            return false;
        }
        if(mp_buf > 1) {
            mana++;
            mp_buf=0;
            return true;
        } else {
            return false;
        }
    }

    public void playerHurt(int v){
        hp -= v;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putLong("lvl", lvl);
        nbt.putLong("exp", exp);
        nbt.putLong("nlv", nlv);

        nbt.putInt("ch_point", ch_point);

        nbt.putLong("hp", hp);
        nbt.putLong("max_hp", max_hp);

        nbt.putLong("mana", mana);
        nbt.putLong("max_mana", max_mana);

        nbt.putInt("phis_attack", phis_attack);
        nbt.putInt("mag_attack", mag_attack);

        nbt.putInt("stamina", stamina);
        nbt.putInt("strength", strength);
        nbt.putInt("intellect", intellect);
        nbt.putInt("dexterity", dexterity);

        nbt.putLong("phis_def", phis_def);
        nbt.putLong("mag_def", mag_def);

        nbt.putLong("fire_def", fire_def);
        nbt.putLong("water_def", water_def);
        nbt.putLong("tree_def", tree_def);
        nbt.putLong("eth_def", eth_def);
        nbt.putLong("metal_def", metal_def);

        nbt.putLong("critical_chance", critical_chance);
        nbt.putLong("critical_damage", critical_damage);
        nbt.putLong("accuracy", accuracy);
        nbt.putLong("avoidance", avoidance);
    }

    public void loadNBTData(CompoundTag nbt) {
        lvl = nbt.getLong("lvl");
        exp = nbt.getLong("exp");
        nlv = nbt.getLong("nlv");

        ch_point = nbt.getInt("ch_point");

        hp = nbt.getLong("hp");
        max_hp = nbt.getLong("max_hp");

        mana = nbt.getLong("mana");
        max_mana = nbt.getLong("max_mana");

        phis_attack = nbt.getInt("phis_attack");
        mag_attack = nbt.getInt("mag_attack");

        stamina = nbt.getInt("stamina");
        strength = nbt.getInt("strength");
        intellect = nbt.getInt("intellect");
        dexterity = nbt.getInt("dexterity");

        phis_def = nbt.getLong("phis_def");
        mag_def = nbt.getLong("mag_def");

        fire_def = nbt.getLong("fire_def");
        water_def = nbt.getLong("water_def");
        tree_def = nbt.getLong("tree_def");
        eth_def = nbt.getLong("eth_def");
        metal_def = nbt.getLong("metal_def");

        critical_chance = nbt.getLong("critical_chance");
        critical_damage = nbt.getLong("critical_damage");
        accuracy = nbt.getLong("accuracy");
        avoidance = nbt.getLong("avoidance");
    }
}
