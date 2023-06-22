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

public class ProviderPDS implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PDS> PLAYER_DATA = CapabilityManager.get(new CapabilityToken<PDS>() { });

    private PDS data = null;
    private final LazyOptional<PDS> optional = LazyOptional.of(this::createPlayerData);

    private PDS createPlayerData() {
        if(this.data == null){
            this.data = new PDS();
        }
        return this.data;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_DATA) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerData().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerData().loadNBTData(nbt);
    }
}
