package com.bodryak.gmod.network.c2s;

import com.bodryak.gmod.network.ModMessages;
import com.bodryak.gmod.network.s2c.mobdata.MDSyncS2CPacket;
import com.bodryak.gmod.network.s2c.playerdata.PDSyncS2CPacket;
import com.bodryak.gmod.variables.server.ProviderMDS;
import com.bodryak.gmod.variables.server.ProviderPDS;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MobSyncC2SPacket {
    private UUID uuid;
    private BlockPos pos;

    public MobSyncC2SPacket(UUID v, BlockPos pos) {
        this.uuid = v;
        this.pos = pos;
    }
    public MobSyncC2SPacket(FriendlyByteBuf buf) {
        this.uuid = buf.readUUID();
        this.pos = buf.readBlockPos();
    }
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUUID(this.uuid);
        buf.writeBlockPos(this.pos);
    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        ServerPlayer player = context.getSender();
        assert player != null;
        Level level = player.getLevel();
        context.enqueueWork(() -> {
            System.out.println("Запрос на синхронизацию сущности " + this.uuid);
            final Vec3 _center = new Vec3(this.pos.getX(), this.pos.getY(), this.pos.getZ());
            List<Entity> _entfound = level.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(40 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
            for (Entity entityiterator : _entfound) {
                if (entityiterator.getUUID().toString().equals(this.uuid.toString())) {
                    entityiterator.getCapability(ProviderMDS.MOB_DATA).ifPresent(mds -> {
                        ModMessages.sendToPlayer(new MDSyncS2CPacket(mds.getHp(), this.uuid, this.pos), player);
                    });
                }
            }

        });
        return true;
    }
}
