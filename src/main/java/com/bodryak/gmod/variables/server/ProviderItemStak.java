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

public class ProviderItemStak implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<IDS> ITEM_DATA = CapabilityManager.get(new CapabilityToken<IDS>() { });

    private IDS data = null;
    private final LazyOptional<IDS> optional = LazyOptional.of(this::createItemData);

    private IDS createItemData() {
        if (this.data == null) {
            this.data = new IDS();
        }
        return this.data;
    }



    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ITEM_DATA) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createItemData().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createItemData().loadNBTData(nbt);
    }
}
