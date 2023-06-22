package com.bodryak.gmod.variables.server;

import net.minecraft.nbt.CompoundTag;

import java.util.concurrent.atomic.AtomicInteger;

public class MDS {
    private long hp = 10;
    private long max_hp = 10;

    public long getMax_hp() {
        return max_hp;
    }

    public void setMax_hp(long max_hp) {
        this.max_hp = max_hp;
    }

    public long getHp() {
        return hp;
    }

    public void setHp(long hp) {
        this.hp = hp;
    }

    public void hurt(AtomicInteger v){
        this.hp = this.hp - v.get();
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putLong("hp", hp);
        nbt.putLong("max_hp", max_hp);
    }

    public void loadNBTData(CompoundTag nbt) {
        hp = nbt.getLong("hp");
        max_hp = nbt.getLong("max_hp");
    }
}
