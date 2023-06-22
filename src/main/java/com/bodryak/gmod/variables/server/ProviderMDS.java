package com.bodryak.gmod.variables.server;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ProviderMDS implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<MDS> MOB_DATA = CapabilityManager.get(new CapabilityToken<MDS>() { });

    private MDS data = null;
    private final LazyOptional<MDS> optional = LazyOptional.of(this::createMobData);

    private MDS createMobData() {
        if (this.data == null) {
            this.data = new MDS();
        }
        return this.data;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == MOB_DATA) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createMobData().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createMobData().loadNBTData(nbt);
    }
}
