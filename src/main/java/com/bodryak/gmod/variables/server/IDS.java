package com.bodryak.gmod.variables.server;

import net.minecraft.nbt.CompoundTag;

public class IDS {
    private int damage = 1;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("damage", damage);
    }

    public void loadNBTData(CompoundTag nbt) {
        damage = nbt.getInt("damage");
    }

}
