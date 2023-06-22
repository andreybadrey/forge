package com.bodryak.gmod.network.s2c.mobdata;

import com.bodryak.gmod.variables.server.ProviderMDS;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MDSyncS2CPacket {
    private final long hp;
    private final UUID entity;
    private BlockPos pos;


    public MDSyncS2CPacket(long hp, UUID entity, BlockPos pos) {
        this.hp = hp;
        this.entity = entity;
        this.pos = pos;
    }

    public MDSyncS2CPacket(FriendlyByteBuf buf){
        this.hp = buf.readLong();
        this.entity = buf.readUUID();
        this.pos = buf.readBlockPos();
    }


    public void toBytes(FriendlyByteBuf buf){
        buf.writeLong(this.hp);
        buf.writeUUID(this.entity);
        buf.writeBlockPos(this.pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            assert Minecraft.getInstance().player != null;
            Level world = Minecraft.getInstance().player.level;
            final Vec3 _center = new Vec3(this.pos.getX(), this.pos.getY(), this.pos.getZ());
            List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(4 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
            for (Entity entityiterator : _entfound) {
                if (entityiterator.getUUID().toString().equals(this.entity.toString())){

                    entityiterator.getCapability(ProviderMDS.MOB_DATA).ifPresent(m -> {
                        m.setHp(this.hp);
                    });
                }
            }
        });
        return true;
    }
}
