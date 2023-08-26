package com.bodryak.gmod.gui.gui.menu;

import com.bodryak.gmod.gui.gui.init.Menus;
import com.bodryak.gmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber
public class GuiGemCraftingMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {

    public final static HashMap<String, Object> guistate = new HashMap<>();
    public final Level world;
    public final Player entity;
    public int x, y, z;
    private IItemHandler internal;
    private final Map<Integer, Slot> customSlots = new HashMap<>();
    private boolean bound = false;
    FriendlyByteBuf data;

    public int [][] gem_slots = new int[265][2];
    boolean field = false;
    boolean hasResult = false;

    public GuiGemCraftingMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        super(Menus.GUI_GEM_CRAFTING.get(), id);
        this.data = extraData;
        this.entity = inv.player;
        this.world = inv.player.level;
        this.internal = new ItemStackHandler(266);
        BlockPos pos = null;

        for (int i=0; i<gem_slots.length; i++){
            gem_slots[i] = new int[2];
        }

        gem_slots[0][0] = 400 - 9;
        gem_slots[0][1] = 277 - 9;

        gem_slots[1][0] = 400 - 9;
        gem_slots[1][1] = gem_slots[0][1] - (18 * 11);
        int line = 2;
        int sc = 3;
        int pre_linex = gem_slots[1][0];
        for (int i = 2; i < 265; i++) {
            if (line <= 12) {
                pre_linex -= 18;
                gem_slots[i][0] = pre_linex;
                gem_slots[i][1] = gem_slots[i - 1][1] + 18;
                i++;
                for (int j = 2; j <= sc; j++) {
                    if (i < 265 && i != 133) {
                        gem_slots[i][0] = gem_slots[i - 1][0] + 18;
                        gem_slots[i][1] = gem_slots[i - 1][1];
                        if (j != sc) {
                            i++;
                        }
                    }else if (i == 133){
                        gem_slots[i][0] = gem_slots[0][0] + 18;
                        gem_slots[i][1] = gem_slots[0][1];
                        if (j != sc) {
                            i++;
                        }
                        j++;
                    }
                }
                if(line != 12) {
                    sc += 2;
                }else {
                    sc -= 2;
                }
            }

            if (line > 12){
                pre_linex += 18;
                gem_slots[i][0] = pre_linex;
                gem_slots[i][1] = gem_slots[i - 1][1] + 18;
                i++;
                for (int j = 2; j <= sc; j++) {
                    if (i < 265 && i != 133) {
                        gem_slots[i][0] = gem_slots[i - 1][0] + 18;
                        gem_slots[i][1] = gem_slots[i - 1][1];
                        if (j != sc) {
                            i++;
                        }
                    }else if (i == 133){
                        gem_slots[i][0] = gem_slots[0][0] + 18;
                        gem_slots[i][1] = gem_slots[0][1];
                        if (j != sc) {
                            i++;
                        }
                        j++;
                    }
                }
                sc -= 2;
            }
            line++;
        }

        if (extraData != null) {
            pos = extraData.readBlockPos();
            this.x = pos.getX();
            this.y = pos.getY();
            this.z = pos.getZ();
        }
        if (pos != null) {
            if (extraData.readableBytes() == 1) { // bound to item
                byte hand = extraData.readByte();
                ItemStack itemstack;
                if (hand == 0)
                    itemstack = this.entity.getMainHandItem();
                else
                    itemstack = this.entity.getOffhandItem();
                itemstack.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                    this.internal = capability;
                    this.bound = true;
                });
            } else if (extraData.readableBytes() > 1) {
                extraData.readByte(); // drop padding
                Entity entity = world.getEntity(extraData.readVarInt());
                if (entity != null)
                    entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                        this.internal = capability;
                        this.bound = true;
                    });
            } else { // might be bound to block
                BlockEntity ent = inv.player.level.getBlockEntity(pos);
                if (ent != null) {
                    ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                        this.internal = capability;
                        this.bound = true;
                    });
                }
            }
        }

            this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, gem_slots[0][0], gem_slots[0][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return stack.getItem() == ModItems.TOPAZ_1.get() ||
                            stack.getItem() == ModItems.TOPAZ_2.get() ||
                            stack.getItem() == ModItems.TOPAZ_3.get() ||
                            stack.getItem() == ModItems.TOPAZ_4.get() ||
                            stack.getItem() == ModItems.TOPAZ_5.get() ||
                            stack.getItem() == ModItems.TOPAZ_6.get() ||
                            stack.getItem() == ModItems.TOPAZ_7.get() ||
                            stack.getItem() == ModItems.TOPAZ_8.get() ||
                            stack.getItem() == ModItems.TOPAZ_9.get() ||
                            stack.getItem() == ModItems.TOPAZ_10.get() ||
                            stack.getItem() == ModItems.TOPAZ_11.get() ||
                            stack.getItem() == ModItems.TOPAZ_12.get();
                }
                @Override
                public int getMaxStackSize()
                {
                    return 1;
                }
                @Override
                public int getMaxStackSize(@NotNull ItemStack stack)
                {
                    return 1;
                }

            }));
            this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, gem_slots[1][0], gem_slots[1][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(2, this.addSlot(new SlotItemHandler(internal, 2, gem_slots[2][0], gem_slots[2][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(3, this.addSlot(new SlotItemHandler(internal, 3, gem_slots[3][0], gem_slots[3][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(4, this.addSlot(new SlotItemHandler(internal, 4, gem_slots[4][0], gem_slots[4][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(5, this.addSlot(new SlotItemHandler(internal, 5, gem_slots[5][0], gem_slots[5][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(6, this.addSlot(new SlotItemHandler(internal, 6, gem_slots[6][0], gem_slots[6][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(7, this.addSlot(new SlotItemHandler(internal, 7, gem_slots[7][0], gem_slots[7][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(8, this.addSlot(new SlotItemHandler(internal, 8, gem_slots[8][0], gem_slots[8][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(9, this.addSlot(new SlotItemHandler(internal, 9, gem_slots[9][0], gem_slots[9][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(10, this.addSlot(new SlotItemHandler(internal, 10, gem_slots[10][0], gem_slots[10][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(11, this.addSlot(new SlotItemHandler(internal, 11, gem_slots[11][0], gem_slots[11][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(12, this.addSlot(new SlotItemHandler(internal, 12, gem_slots[12][0], gem_slots[12][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(13, this.addSlot(new SlotItemHandler(internal, 13, gem_slots[13][0], gem_slots[13][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(14, this.addSlot(new SlotItemHandler(internal, 14, gem_slots[14][0], gem_slots[14][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(15, this.addSlot(new SlotItemHandler(internal, 15, gem_slots[15][0], gem_slots[15][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(16, this.addSlot(new SlotItemHandler(internal, 16, gem_slots[16][0], gem_slots[16][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(17, this.addSlot(new SlotItemHandler(internal, 17, gem_slots[17][0], gem_slots[17][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(18, this.addSlot(new SlotItemHandler(internal, 18, gem_slots[18][0], gem_slots[18][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(19, this.addSlot(new SlotItemHandler(internal, 19, gem_slots[19][0], gem_slots[19][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(20, this.addSlot(new SlotItemHandler(internal, 20, gem_slots[20][0], gem_slots[20][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(21, this.addSlot(new SlotItemHandler(internal, 21, gem_slots[21][0], gem_slots[21][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(22, this.addSlot(new SlotItemHandler(internal, 22, gem_slots[22][0], gem_slots[22][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(23, this.addSlot(new SlotItemHandler(internal, 23, gem_slots[23][0], gem_slots[23][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(24, this.addSlot(new SlotItemHandler(internal, 24, gem_slots[24][0], gem_slots[24][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(25, this.addSlot(new SlotItemHandler(internal, 25, gem_slots[25][0], gem_slots[25][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(26, this.addSlot(new SlotItemHandler(internal, 26, gem_slots[26][0], gem_slots[26][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(27, this.addSlot(new SlotItemHandler(internal, 27, gem_slots[27][0], gem_slots[27][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(28, this.addSlot(new SlotItemHandler(internal, 28, gem_slots[28][0], gem_slots[28][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(29, this.addSlot(new SlotItemHandler(internal, 29, gem_slots[29][0], gem_slots[29][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(30, this.addSlot(new SlotItemHandler(internal, 30, gem_slots[30][0], gem_slots[30][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(31, this.addSlot(new SlotItemHandler(internal, 31, gem_slots[31][0], gem_slots[31][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(32, this.addSlot(new SlotItemHandler(internal, 32, gem_slots[32][0], gem_slots[32][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(33, this.addSlot(new SlotItemHandler(internal, 33, gem_slots[33][0], gem_slots[33][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(34, this.addSlot(new SlotItemHandler(internal, 34, gem_slots[34][0], gem_slots[34][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(35, this.addSlot(new SlotItemHandler(internal, 35, gem_slots[35][0], gem_slots[35][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(36, this.addSlot(new SlotItemHandler(internal, 36, gem_slots[36][0], gem_slots[36][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(37, this.addSlot(new SlotItemHandler(internal, 37, gem_slots[37][0], gem_slots[37][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(38, this.addSlot(new SlotItemHandler(internal, 38, gem_slots[38][0], gem_slots[38][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(39, this.addSlot(new SlotItemHandler(internal, 39, gem_slots[39][0], gem_slots[39][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(40, this.addSlot(new SlotItemHandler(internal, 40, gem_slots[40][0], gem_slots[40][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(41, this.addSlot(new SlotItemHandler(internal, 41, gem_slots[41][0], gem_slots[41][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(42, this.addSlot(new SlotItemHandler(internal, 42, gem_slots[42][0], gem_slots[42][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(43, this.addSlot(new SlotItemHandler(internal, 43, gem_slots[43][0], gem_slots[43][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(44, this.addSlot(new SlotItemHandler(internal, 44, gem_slots[44][0], gem_slots[44][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(45, this.addSlot(new SlotItemHandler(internal, 45, gem_slots[45][0], gem_slots[45][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(46, this.addSlot(new SlotItemHandler(internal, 46, gem_slots[46][0], gem_slots[46][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(47, this.addSlot(new SlotItemHandler(internal, 47, gem_slots[47][0], gem_slots[47][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(48, this.addSlot(new SlotItemHandler(internal, 48, gem_slots[48][0], gem_slots[48][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(49, this.addSlot(new SlotItemHandler(internal, 49, gem_slots[49][0], gem_slots[49][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(50, this.addSlot(new SlotItemHandler(internal, 50, gem_slots[50][0], gem_slots[50][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(51, this.addSlot(new SlotItemHandler(internal, 51, gem_slots[51][0], gem_slots[51][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(52, this.addSlot(new SlotItemHandler(internal, 52, gem_slots[52][0], gem_slots[52][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(53, this.addSlot(new SlotItemHandler(internal, 53, gem_slots[53][0], gem_slots[53][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(54, this.addSlot(new SlotItemHandler(internal, 54, gem_slots[54][0], gem_slots[54][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(55, this.addSlot(new SlotItemHandler(internal, 55, gem_slots[55][0], gem_slots[55][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(56, this.addSlot(new SlotItemHandler(internal, 56, gem_slots[56][0], gem_slots[56][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(57, this.addSlot(new SlotItemHandler(internal, 57, gem_slots[57][0], gem_slots[57][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(58, this.addSlot(new SlotItemHandler(internal, 58, gem_slots[58][0], gem_slots[58][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(59, this.addSlot(new SlotItemHandler(internal, 59, gem_slots[59][0], gem_slots[59][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(60, this.addSlot(new SlotItemHandler(internal, 60, gem_slots[60][0], gem_slots[60][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(61, this.addSlot(new SlotItemHandler(internal, 61, gem_slots[61][0], gem_slots[61][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(62, this.addSlot(new SlotItemHandler(internal, 62, gem_slots[62][0], gem_slots[62][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(63, this.addSlot(new SlotItemHandler(internal, 63, gem_slots[63][0], gem_slots[63][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(64, this.addSlot(new SlotItemHandler(internal, 64, gem_slots[64][0], gem_slots[64][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(65, this.addSlot(new SlotItemHandler(internal, 65, gem_slots[65][0], gem_slots[65][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(66, this.addSlot(new SlotItemHandler(internal, 66, gem_slots[66][0], gem_slots[66][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(67, this.addSlot(new SlotItemHandler(internal, 67, gem_slots[67][0], gem_slots[67][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(68, this.addSlot(new SlotItemHandler(internal, 68, gem_slots[68][0], gem_slots[68][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(69, this.addSlot(new SlotItemHandler(internal, 69, gem_slots[69][0], gem_slots[69][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(70, this.addSlot(new SlotItemHandler(internal, 70, gem_slots[70][0], gem_slots[70][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(71, this.addSlot(new SlotItemHandler(internal, 71, gem_slots[71][0], gem_slots[71][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(72, this.addSlot(new SlotItemHandler(internal, 72, gem_slots[72][0], gem_slots[72][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(73, this.addSlot(new SlotItemHandler(internal, 73, gem_slots[73][0], gem_slots[73][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(74, this.addSlot(new SlotItemHandler(internal, 74, gem_slots[74][0], gem_slots[74][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(75, this.addSlot(new SlotItemHandler(internal, 75, gem_slots[75][0], gem_slots[75][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(76, this.addSlot(new SlotItemHandler(internal, 76, gem_slots[76][0], gem_slots[76][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(77, this.addSlot(new SlotItemHandler(internal, 77, gem_slots[77][0], gem_slots[77][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(78, this.addSlot(new SlotItemHandler(internal, 78, gem_slots[78][0], gem_slots[78][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(79, this.addSlot(new SlotItemHandler(internal, 79, gem_slots[79][0], gem_slots[79][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(80, this.addSlot(new SlotItemHandler(internal, 80, gem_slots[80][0], gem_slots[80][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(81, this.addSlot(new SlotItemHandler(internal, 81, gem_slots[81][0], gem_slots[81][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(82, this.addSlot(new SlotItemHandler(internal, 82, gem_slots[82][0], gem_slots[82][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(83, this.addSlot(new SlotItemHandler(internal, 83, gem_slots[83][0], gem_slots[83][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(84, this.addSlot(new SlotItemHandler(internal, 84, gem_slots[84][0], gem_slots[84][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(85, this.addSlot(new SlotItemHandler(internal, 85, gem_slots[85][0], gem_slots[85][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(86, this.addSlot(new SlotItemHandler(internal, 86, gem_slots[86][0], gem_slots[86][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(87, this.addSlot(new SlotItemHandler(internal, 87, gem_slots[87][0], gem_slots[87][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(88, this.addSlot(new SlotItemHandler(internal, 88, gem_slots[88][0], gem_slots[88][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(89, this.addSlot(new SlotItemHandler(internal, 89, gem_slots[89][0], gem_slots[89][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(90, this.addSlot(new SlotItemHandler(internal, 90, gem_slots[90][0], gem_slots[90][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(91, this.addSlot(new SlotItemHandler(internal, 91, gem_slots[91][0], gem_slots[91][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(92, this.addSlot(new SlotItemHandler(internal, 92, gem_slots[92][0], gem_slots[92][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(93, this.addSlot(new SlotItemHandler(internal, 93, gem_slots[93][0], gem_slots[93][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(94, this.addSlot(new SlotItemHandler(internal, 94, gem_slots[94][0], gem_slots[94][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(95, this.addSlot(new SlotItemHandler(internal, 95, gem_slots[95][0], gem_slots[95][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(96, this.addSlot(new SlotItemHandler(internal, 96, gem_slots[96][0], gem_slots[96][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(97, this.addSlot(new SlotItemHandler(internal, 97, gem_slots[97][0], gem_slots[97][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(98, this.addSlot(new SlotItemHandler(internal, 98, gem_slots[98][0], gem_slots[98][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(99, this.addSlot(new SlotItemHandler(internal, 99, gem_slots[99][0], gem_slots[99][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(100, this.addSlot(new SlotItemHandler(internal, 100, gem_slots[100][0], gem_slots[100][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(101, this.addSlot(new SlotItemHandler(internal, 101, gem_slots[101][0], gem_slots[101][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(102, this.addSlot(new SlotItemHandler(internal, 102, gem_slots[102][0], gem_slots[102][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(103, this.addSlot(new SlotItemHandler(internal, 103, gem_slots[103][0], gem_slots[103][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(104, this.addSlot(new SlotItemHandler(internal, 104, gem_slots[104][0], gem_slots[104][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(105, this.addSlot(new SlotItemHandler(internal, 105, gem_slots[105][0], gem_slots[105][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(106, this.addSlot(new SlotItemHandler(internal, 106, gem_slots[106][0], gem_slots[106][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(107, this.addSlot(new SlotItemHandler(internal, 107, gem_slots[107][0], gem_slots[107][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(108, this.addSlot(new SlotItemHandler(internal, 108, gem_slots[108][0], gem_slots[108][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(109, this.addSlot(new SlotItemHandler(internal, 109, gem_slots[109][0], gem_slots[109][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(110, this.addSlot(new SlotItemHandler(internal, 110, gem_slots[110][0], gem_slots[110][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    if(customSlots.get(0).getItem().getItem() == ModItems.TOPAZ_2.get()) {
                        return stack.getItem() == ModItems.TOPAZ_1.get();
                    }
                    return false;
                }
                @Override
                public int getMaxStackSize() {
                    return 1;
                }
                @Override
                public int getMaxStackSize(@NotNull ItemStack stack) {
                    return 1;
                }

            }));

            this.customSlots.put(111, this.addSlot(new SlotItemHandler(internal, 111, gem_slots[111][0], gem_slots[111][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return customSlots.get(0).getItem().getItem() == ModItems.TOPAZ_1.get() &&
                            stack.getItem() == ModItems.TOPAZ_1.get();
                }
                @Override
                public int getMaxStackSize()
                {
                    return 1;
                }
                @Override
                public int getMaxStackSize(@NotNull ItemStack stack)
                {
                    return 1;
                }

            }));
            this.customSlots.put(112, this.addSlot(new SlotItemHandler(internal, 112, gem_slots[112][0], gem_slots[112][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    if(customSlots.get(0).getItem().getItem() == ModItems.TOPAZ_2.get()) {
                        return stack.getItem() == ModItems.TOPAZ_1.get();
                    }
                    return false;
                }
                @Override
                public int getMaxStackSize() {
                    return 1;
                }
                @Override
                public int getMaxStackSize(@NotNull ItemStack stack) {
                    return 1;
                }

            }));
            this.customSlots.put(113, this.addSlot(new SlotItemHandler(internal, 113, gem_slots[113][0], gem_slots[113][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(114, this.addSlot(new SlotItemHandler(internal, 114, gem_slots[114][0], gem_slots[114][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(115, this.addSlot(new SlotItemHandler(internal, 115, gem_slots[115][0], gem_slots[115][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(116, this.addSlot(new SlotItemHandler(internal, 116, gem_slots[116][0], gem_slots[116][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(117, this.addSlot(new SlotItemHandler(internal, 117, gem_slots[117][0], gem_slots[117][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(118, this.addSlot(new SlotItemHandler(internal, 118, gem_slots[118][0], gem_slots[118][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(119, this.addSlot(new SlotItemHandler(internal, 119, gem_slots[119][0], gem_slots[119][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(120, this.addSlot(new SlotItemHandler(internal, 120, gem_slots[120][0], gem_slots[120][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(121, this.addSlot(new SlotItemHandler(internal, 121, gem_slots[121][0], gem_slots[121][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(122, this.addSlot(new SlotItemHandler(internal, 122, gem_slots[122][0], gem_slots[122][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(123, this.addSlot(new SlotItemHandler(internal, 123, gem_slots[123][0], gem_slots[123][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(124, this.addSlot(new SlotItemHandler(internal, 124, gem_slots[124][0], gem_slots[124][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(125, this.addSlot(new SlotItemHandler(internal, 125, gem_slots[125][0], gem_slots[125][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(126, this.addSlot(new SlotItemHandler(internal, 126, gem_slots[126][0], gem_slots[126][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(127, this.addSlot(new SlotItemHandler(internal, 127, gem_slots[127][0], gem_slots[127][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(128, this.addSlot(new SlotItemHandler(internal, 128, gem_slots[128][0], gem_slots[128][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(129, this.addSlot(new SlotItemHandler(internal, 129, gem_slots[129][0], gem_slots[129][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(130, this.addSlot(new SlotItemHandler(internal, 130, gem_slots[130][0], gem_slots[130][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(131, this.addSlot(new SlotItemHandler(internal, 131, gem_slots[131][0], gem_slots[131][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    if(customSlots.get(0).getItem().getItem() == ModItems.TOPAZ_2.get()) {
                        return stack.getItem() == ModItems.TOPAZ_1.get();
                    }
                    return false;
                }
                @Override
                public int getMaxStackSize() {
                    return 1;
                }
                @Override
                public int getMaxStackSize(@NotNull ItemStack stack) {
                    return 1;
                }

            }));
            this.customSlots.put(132, this.addSlot(new SlotItemHandler(internal, 132, gem_slots[132][0], gem_slots[132][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    if(customSlots.get(0).getItem().getItem() == ModItems.TOPAZ_1.get()) {
                        return stack.getItem() == ModItems.TOPAZ_1.get();
                    }
                    if(customSlots.get(0).getItem().getItem() == ModItems.TOPAZ_2.get()) {
                        return stack.getItem() == ModItems.TOPAZ_2.get();
                    }
                    return false;
                }
                @Override
                public int getMaxStackSize() {
                    return 1;
                }
                @Override
                public int getMaxStackSize(@NotNull ItemStack stack) {
                    return 1;
                }

            }));
            this.customSlots.put(133, this.addSlot(new SlotItemHandler(internal, 133, gem_slots[133][0], gem_slots[133][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    if(customSlots.get(0).getItem().getItem() == ModItems.TOPAZ_1.get()) {
                        return stack.getItem() == ModItems.TOPAZ_1.get();
                    }
                    if(customSlots.get(0).getItem().getItem() == ModItems.TOPAZ_2.get()) {
                        return stack.getItem() == ModItems.TOPAZ_2.get();
                    }

                    return false;
                }
                @Override
                public int getMaxStackSize()
                {
                    return 1;
                }
                @Override
                public int getMaxStackSize(@NotNull ItemStack stack)
                {
                    return 1;
                }

            }));
            this.customSlots.put(134, this.addSlot(new SlotItemHandler(internal, 134, gem_slots[134][0], gem_slots[134][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    if(customSlots.get(0).getItem().getItem() == ModItems.TOPAZ_2.get()) {
                        return stack.getItem() == ModItems.TOPAZ_1.get();
                    }
                    return false;
                }
                @Override
                public int getMaxStackSize() {
                    return 1;
                }
                @Override
                public int getMaxStackSize(@NotNull ItemStack stack) {
                    return 1;
                }

            }));
            this.customSlots.put(135, this.addSlot(new SlotItemHandler(internal, 135, gem_slots[135][0], gem_slots[135][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(136, this.addSlot(new SlotItemHandler(internal, 136, gem_slots[136][0], gem_slots[136][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(137, this.addSlot(new SlotItemHandler(internal, 137, gem_slots[137][0], gem_slots[137][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(138, this.addSlot(new SlotItemHandler(internal, 138, gem_slots[138][0], gem_slots[138][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(139, this.addSlot(new SlotItemHandler(internal, 139, gem_slots[139][0], gem_slots[139][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(140, this.addSlot(new SlotItemHandler(internal, 140, gem_slots[140][0], gem_slots[140][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(141, this.addSlot(new SlotItemHandler(internal, 141, gem_slots[141][0], gem_slots[141][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(142, this.addSlot(new SlotItemHandler(internal, 142, gem_slots[142][0], gem_slots[142][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(143, this.addSlot(new SlotItemHandler(internal, 143, gem_slots[143][0], gem_slots[143][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(144, this.addSlot(new SlotItemHandler(internal, 144, gem_slots[144][0], gem_slots[144][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(145, this.addSlot(new SlotItemHandler(internal, 145, gem_slots[145][0], gem_slots[145][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(146, this.addSlot(new SlotItemHandler(internal, 146, gem_slots[146][0], gem_slots[146][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(147, this.addSlot(new SlotItemHandler(internal, 147, gem_slots[147][0], gem_slots[147][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(148, this.addSlot(new SlotItemHandler(internal, 148, gem_slots[148][0], gem_slots[148][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(149, this.addSlot(new SlotItemHandler(internal, 149, gem_slots[149][0], gem_slots[149][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(150, this.addSlot(new SlotItemHandler(internal, 150, gem_slots[150][0], gem_slots[150][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(151, this.addSlot(new SlotItemHandler(internal, 151, gem_slots[151][0], gem_slots[151][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(152, this.addSlot(new SlotItemHandler(internal, 152, gem_slots[152][0], gem_slots[152][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(153, this.addSlot(new SlotItemHandler(internal, 153, gem_slots[153][0], gem_slots[153][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    if(customSlots.get(0).getItem().getItem() == ModItems.TOPAZ_2.get()) {
                        return stack.getItem() == ModItems.TOPAZ_1.get();
                    }
                    return false;
                }
                @Override
                public int getMaxStackSize() {
                    return 1;
                }
                @Override
                public int getMaxStackSize(@NotNull ItemStack stack) {
                    return 1;
                }

            }));
            this.customSlots.put(154, this.addSlot(new SlotItemHandler(internal, 154, gem_slots[154][0], gem_slots[154][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return customSlots.get(0).getItem().getItem() == ModItems.TOPAZ_1.get() &&
                            stack.getItem() == ModItems.TOPAZ_1.get();
                }
                @Override
                public int getMaxStackSize()
                {
                    return 1;
                }
                @Override
                public int getMaxStackSize(@NotNull ItemStack stack)
                {
                    return 1;
                }

            }));
            this.customSlots.put(155, this.addSlot(new SlotItemHandler(internal, 155, gem_slots[155][0], gem_slots[155][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    if(customSlots.get(0).getItem().getItem() == ModItems.TOPAZ_2.get()) {
                        return stack.getItem() == ModItems.TOPAZ_1.get();
                    }
                    return false;
                }
                @Override
                public int getMaxStackSize() {
                    return 1;
                }
                @Override
                public int getMaxStackSize(@NotNull ItemStack stack) {
                    return 1;
                }

            }));
            this.customSlots.put(156, this.addSlot(new SlotItemHandler(internal, 156, gem_slots[156][0], gem_slots[156][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(157, this.addSlot(new SlotItemHandler(internal, 157, gem_slots[157][0], gem_slots[157][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(158, this.addSlot(new SlotItemHandler(internal, 158, gem_slots[158][0], gem_slots[158][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(159, this.addSlot(new SlotItemHandler(internal, 159, gem_slots[159][0], gem_slots[159][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(160, this.addSlot(new SlotItemHandler(internal, 160, gem_slots[160][0], gem_slots[160][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(161, this.addSlot(new SlotItemHandler(internal, 161, gem_slots[161][0], gem_slots[161][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(162, this.addSlot(new SlotItemHandler(internal, 162, gem_slots[162][0], gem_slots[162][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(163, this.addSlot(new SlotItemHandler(internal, 163, gem_slots[163][0], gem_slots[163][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(164, this.addSlot(new SlotItemHandler(internal, 164, gem_slots[164][0], gem_slots[164][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(165, this.addSlot(new SlotItemHandler(internal, 165, gem_slots[165][0], gem_slots[165][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(166, this.addSlot(new SlotItemHandler(internal, 166, gem_slots[166][0], gem_slots[166][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(167, this.addSlot(new SlotItemHandler(internal, 167, gem_slots[167][0], gem_slots[167][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(168, this.addSlot(new SlotItemHandler(internal, 168, gem_slots[168][0], gem_slots[168][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(169, this.addSlot(new SlotItemHandler(internal, 169, gem_slots[169][0], gem_slots[169][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(170, this.addSlot(new SlotItemHandler(internal, 170, gem_slots[170][0], gem_slots[170][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(171, this.addSlot(new SlotItemHandler(internal, 171, gem_slots[171][0], gem_slots[171][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(172, this.addSlot(new SlotItemHandler(internal, 172, gem_slots[172][0], gem_slots[172][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(173, this.addSlot(new SlotItemHandler(internal, 173, gem_slots[173][0], gem_slots[173][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(174, this.addSlot(new SlotItemHandler(internal, 174, gem_slots[174][0], gem_slots[174][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(175, this.addSlot(new SlotItemHandler(internal, 175, gem_slots[175][0], gem_slots[175][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(176, this.addSlot(new SlotItemHandler(internal, 176, gem_slots[176][0], gem_slots[176][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(177, this.addSlot(new SlotItemHandler(internal, 177, gem_slots[177][0], gem_slots[177][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(178, this.addSlot(new SlotItemHandler(internal, 178, gem_slots[178][0], gem_slots[178][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(179, this.addSlot(new SlotItemHandler(internal, 179, gem_slots[179][0], gem_slots[179][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(180, this.addSlot(new SlotItemHandler(internal, 180, gem_slots[180][0], gem_slots[180][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(181, this.addSlot(new SlotItemHandler(internal, 181, gem_slots[181][0], gem_slots[181][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(182, this.addSlot(new SlotItemHandler(internal, 182, gem_slots[182][0], gem_slots[182][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(183, this.addSlot(new SlotItemHandler(internal, 183, gem_slots[183][0], gem_slots[183][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(184, this.addSlot(new SlotItemHandler(internal, 184, gem_slots[184][0], gem_slots[184][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(185, this.addSlot(new SlotItemHandler(internal, 185, gem_slots[185][0], gem_slots[185][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(186, this.addSlot(new SlotItemHandler(internal, 186, gem_slots[186][0], gem_slots[186][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(187, this.addSlot(new SlotItemHandler(internal, 187, gem_slots[187][0], gem_slots[187][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(188, this.addSlot(new SlotItemHandler(internal, 188, gem_slots[188][0], gem_slots[188][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(189, this.addSlot(new SlotItemHandler(internal, 189, gem_slots[189][0], gem_slots[189][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(190, this.addSlot(new SlotItemHandler(internal, 190, gem_slots[190][0], gem_slots[190][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));


            this.customSlots.put(191, this.addSlot(new SlotItemHandler(internal, 191, gem_slots[191][0], gem_slots[191][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(192, this.addSlot(new SlotItemHandler(internal, 192, gem_slots[192][0], gem_slots[192][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(193, this.addSlot(new SlotItemHandler(internal, 193, gem_slots[193][0], gem_slots[193][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(194, this.addSlot(new SlotItemHandler(internal, 194, gem_slots[194][0], gem_slots[194][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(195, this.addSlot(new SlotItemHandler(internal, 195, gem_slots[195][0], gem_slots[195][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(196, this.addSlot(new SlotItemHandler(internal, 196, gem_slots[196][0], gem_slots[196][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(197, this.addSlot(new SlotItemHandler(internal, 197, gem_slots[197][0], gem_slots[197][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(198, this.addSlot(new SlotItemHandler(internal, 198, gem_slots[198][0], gem_slots[198][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(199, this.addSlot(new SlotItemHandler(internal, 199, gem_slots[199][0], gem_slots[199][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(200, this.addSlot(new SlotItemHandler(internal, 200, gem_slots[200][0], gem_slots[200][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(201, this.addSlot(new SlotItemHandler(internal, 201, gem_slots[201][0], gem_slots[201][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(202, this.addSlot(new SlotItemHandler(internal, 202, gem_slots[202][0], gem_slots[202][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(203, this.addSlot(new SlotItemHandler(internal, 203, gem_slots[203][0], gem_slots[203][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(204, this.addSlot(new SlotItemHandler(internal, 204, gem_slots[204][0], gem_slots[204][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(205, this.addSlot(new SlotItemHandler(internal, 205, gem_slots[205][0], gem_slots[205][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(206, this.addSlot(new SlotItemHandler(internal, 206, gem_slots[206][0], gem_slots[206][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(207, this.addSlot(new SlotItemHandler(internal, 207, gem_slots[207][0], gem_slots[207][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(208, this.addSlot(new SlotItemHandler(internal, 208, gem_slots[208][0], gem_slots[208][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(209, this.addSlot(new SlotItemHandler(internal, 209, gem_slots[209][0], gem_slots[209][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(210, this.addSlot(new SlotItemHandler(internal, 210, gem_slots[210][0], gem_slots[210][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(211, this.addSlot(new SlotItemHandler(internal, 211, gem_slots[211][0], gem_slots[211][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(212, this.addSlot(new SlotItemHandler(internal, 212, gem_slots[212][0], gem_slots[212][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(213, this.addSlot(new SlotItemHandler(internal, 213, gem_slots[213][0], gem_slots[213][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(214, this.addSlot(new SlotItemHandler(internal, 214, gem_slots[214][0], gem_slots[214][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(215, this.addSlot(new SlotItemHandler(internal, 215, gem_slots[215][0], gem_slots[215][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(216, this.addSlot(new SlotItemHandler(internal, 216, gem_slots[216][0], gem_slots[216][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(217, this.addSlot(new SlotItemHandler(internal, 217, gem_slots[217][0], gem_slots[217][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(218, this.addSlot(new SlotItemHandler(internal, 218, gem_slots[218][0], gem_slots[218][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(219, this.addSlot(new SlotItemHandler(internal, 219, gem_slots[219][0], gem_slots[219][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(220, this.addSlot(new SlotItemHandler(internal, 220, gem_slots[220][0], gem_slots[220][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(221, this.addSlot(new SlotItemHandler(internal, 221, gem_slots[221][0], gem_slots[221][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(222, this.addSlot(new SlotItemHandler(internal, 222, gem_slots[222][0], gem_slots[222][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(223, this.addSlot(new SlotItemHandler(internal, 223, gem_slots[223][0], gem_slots[223][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(224, this.addSlot(new SlotItemHandler(internal, 224, gem_slots[224][0], gem_slots[224][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(225, this.addSlot(new SlotItemHandler(internal, 225, gem_slots[225][0], gem_slots[225][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(226, this.addSlot(new SlotItemHandler(internal, 226, gem_slots[226][0], gem_slots[226][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(227, this.addSlot(new SlotItemHandler(internal, 227, gem_slots[227][0], gem_slots[227][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(228, this.addSlot(new SlotItemHandler(internal, 228, gem_slots[228][0], gem_slots[228][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(229, this.addSlot(new SlotItemHandler(internal, 229, gem_slots[229][0], gem_slots[229][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(230, this.addSlot(new SlotItemHandler(internal, 230, gem_slots[230][0], gem_slots[230][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(231, this.addSlot(new SlotItemHandler(internal, 231, gem_slots[231][0], gem_slots[231][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(232, this.addSlot(new SlotItemHandler(internal, 232, gem_slots[232][0], gem_slots[232][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(233, this.addSlot(new SlotItemHandler(internal, 233, gem_slots[233][0], gem_slots[233][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(234, this.addSlot(new SlotItemHandler(internal, 234, gem_slots[234][0], gem_slots[234][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(235, this.addSlot(new SlotItemHandler(internal, 235, gem_slots[235][0], gem_slots[235][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(236, this.addSlot(new SlotItemHandler(internal, 236, gem_slots[236][0], gem_slots[236][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(237, this.addSlot(new SlotItemHandler(internal, 237, gem_slots[237][0], gem_slots[237][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(238, this.addSlot(new SlotItemHandler(internal, 238, gem_slots[238][0], gem_slots[238][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(239, this.addSlot(new SlotItemHandler(internal, 239, gem_slots[239][0], gem_slots[239][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(240, this.addSlot(new SlotItemHandler(internal, 240, gem_slots[240][0], gem_slots[240][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(241, this.addSlot(new SlotItemHandler(internal, 241, gem_slots[241][0], gem_slots[241][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(242, this.addSlot(new SlotItemHandler(internal, 242, gem_slots[242][0], gem_slots[242][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(243, this.addSlot(new SlotItemHandler(internal, 243, gem_slots[243][0], gem_slots[243][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(244, this.addSlot(new SlotItemHandler(internal, 244, gem_slots[244][0], gem_slots[244][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(245, this.addSlot(new SlotItemHandler(internal, 245, gem_slots[245][0], gem_slots[245][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(246, this.addSlot(new SlotItemHandler(internal, 246, gem_slots[246][0], gem_slots[246][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(247, this.addSlot(new SlotItemHandler(internal, 247, gem_slots[247][0], gem_slots[247][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(248, this.addSlot(new SlotItemHandler(internal, 248, gem_slots[248][0], gem_slots[248][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(249, this.addSlot(new SlotItemHandler(internal, 249, gem_slots[249][0], gem_slots[249][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(250, this.addSlot(new SlotItemHandler(internal, 250, gem_slots[250][0], gem_slots[250][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(251, this.addSlot(new SlotItemHandler(internal, 251, gem_slots[251][0], gem_slots[251][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(252, this.addSlot(new SlotItemHandler(internal, 252, gem_slots[252][0], gem_slots[252][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(253, this.addSlot(new SlotItemHandler(internal, 253, gem_slots[253][0], gem_slots[253][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(254, this.addSlot(new SlotItemHandler(internal, 254, gem_slots[254][0], gem_slots[254][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(255, this.addSlot(new SlotItemHandler(internal, 255, gem_slots[255][0], gem_slots[255][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(256, this.addSlot(new SlotItemHandler(internal, 256, gem_slots[256][0], gem_slots[256][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(257, this.addSlot(new SlotItemHandler(internal, 257, gem_slots[257][0], gem_slots[257][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(258, this.addSlot(new SlotItemHandler(internal, 258, gem_slots[258][0], gem_slots[258][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(259, this.addSlot(new SlotItemHandler(internal, 259, gem_slots[259][0], gem_slots[259][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(260, this.addSlot(new SlotItemHandler(internal, 260, gem_slots[260][0], gem_slots[260][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));

            this.customSlots.put(261, this.addSlot(new SlotItemHandler(internal, 261, gem_slots[261][0], gem_slots[261][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(262, this.addSlot(new SlotItemHandler(internal, 262, gem_slots[262][0], gem_slots[262][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(263, this.addSlot(new SlotItemHandler(internal, 263, gem_slots[263][0], gem_slots[263][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(264, this.addSlot(new SlotItemHandler(internal, 264, gem_slots[264][0], gem_slots[264][1]) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));
            this.customSlots.put(265, this.addSlot(new SlotItemHandler(internal, 265, 0, 0) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }

            }));


        for (int si = 0; si < 3; ++si)
            for (int sj = 0; sj < 9; ++sj)
                this.addSlot(new Slot(inv, sj + (si + 1) * 9, 319 + sj * 18, 557 + si * 18));

        for (int si = 0; si < 9; ++si)
            this.addSlot(new Slot(inv, si, 319  + si * 18, 615));

    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return true;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < 4) {
                if (!this.moveItemStackTo(itemstack1, 4, this.slots.size(), true))
                    return ItemStack.EMPTY;
                slot.onQuickCraft(itemstack1, itemstack);
            } else if (!this.moveItemStackTo(itemstack1, 0, 4, false)) {
                if (index < 4 + 27) {
                    if (!this.moveItemStackTo(itemstack1, 4 + 27, this.slots.size(), true))
                        return ItemStack.EMPTY;
                } else {
                    if (!this.moveItemStackTo(itemstack1, 4, 4 + 27, false))
                        return ItemStack.EMPTY;
                }
                return ItemStack.EMPTY;
            }
            if (itemstack1.getCount() == 0)
                slot.set(ItemStack.EMPTY);
            else
                slot.setChanged();
            if (itemstack1.getCount() == itemstack.getCount())
                return ItemStack.EMPTY;
            slot.onTake(playerIn, itemstack1);
        }
        return itemstack;
    }

    @Override
    protected boolean moveItemStackTo(@NotNull ItemStack p_38904_, int p_38905_, int p_38906_, boolean p_38907_) {
        boolean flag = false;
        int i = p_38905_;
        if (p_38907_) {
            i = p_38906_ - 1;
        }
        if (p_38904_.isStackable()) {
            while (!p_38904_.isEmpty()) {
                if (p_38907_) {
                    if (i < p_38905_) {
                        break;
                    }
                } else if (i >= p_38906_) {
                    break;
                }
                Slot slot = this.slots.get(i);
                ItemStack itemstack = slot.getItem();
                if (slot.mayPlace(itemstack) && !itemstack.isEmpty() && ItemStack.isSameItemSameTags(p_38904_, itemstack)) {
                    int j = itemstack.getCount() + p_38904_.getCount();
                    int maxSize = Math.min(slot.getMaxStackSize(), p_38904_.getMaxStackSize());
                    if (j <= maxSize) {
                        p_38904_.setCount(0);
                        itemstack.setCount(j);
                        slot.set(itemstack);
                        flag = true;
                    } else if (itemstack.getCount() < maxSize) {
                        p_38904_.shrink(maxSize - itemstack.getCount());
                        itemstack.setCount(maxSize);
                        slot.set(itemstack);
                        flag = true;
                    }
                }
                if (p_38907_) {
                    --i;
                } else {
                    ++i;
                }
            }
        }
        if (!p_38904_.isEmpty()) {
            if (p_38907_) {
                i = p_38906_ - 1;
            } else {
                i = p_38905_;
            }
            while (true) {
                if (p_38907_) {
                    if (i < p_38905_) {
                        break;
                    }
                } else if (i >= p_38906_) {
                    break;
                }
                Slot slot1 = this.slots.get(i);
                ItemStack itemstack1 = slot1.getItem();
                if (itemstack1.isEmpty() && slot1.mayPlace(p_38904_)) {
                    if (p_38904_.getCount() > slot1.getMaxStackSize()) {
                        slot1.set(p_38904_.split(slot1.getMaxStackSize()));
                    } else {
                        slot1.set(p_38904_.split(p_38904_.getCount()));
                    }
                    slot1.setChanged();
                    flag = true;
                    break;
                }
                if (p_38907_) {
                    --i;
                } else {
                    ++i;
                }
            }
        }
        return flag;
    }

    @Override
    public void removed(@NotNull Player playerIn) {
        super.removed(playerIn);
        if (!bound && playerIn instanceof ServerPlayer serverPlayer) {
            if (!serverPlayer.isAlive() || serverPlayer.hasDisconnected()) {
                for (int j = 0; j < internal.getSlots(); ++j) {
                    playerIn.drop(internal.extractItem(j, internal.getStackInSlot(j).getCount(), false), false);
                }
            } else {
                for (int i = 0; i < internal.getSlots(); ++i) {
                    playerIn.getInventory().placeItemBackInInventory(internal.extractItem(i, internal.getStackInSlot(i).getCount(), false));
                }
            }
        }
    }

    public Map<Integer, Slot> get() {
        return customSlots;
    }

    @SubscribeEvent
    static public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player entity = event.player;

        if (event.phase == TickEvent.Phase.END && entity.containerMenu instanceof GuiGemCraftingMenu gui) {
            if (gui.customSlots.get(0).getItem().getItem() != Items.AIR && !gui.field){
                gui.field = true;
            }
            if (gui.customSlots.get(0).getItem().getItem() == Items.AIR && gui.field) {
                if(gui.customSlots.get(265).getItem().getItem() != Items.AIR) {
                    gui.customSlots.get(265).getItem().shrink(1);
                }
                gui.removed(entity);
                gui.field = false;
            }
            if(gui.field && !gui.hasResult) {
                if(gui.customSlots.get(0).getItem().getItem() == ModItems.TOPAZ_1.get()) {
                    if(gui.customSlots.get(132).getItem().getItem() == ModItems.TOPAZ_1.get() &&
                            gui.customSlots.get(133).getItem().getItem() == ModItems.TOPAZ_1.get() &&
                            gui.customSlots.get(111).getItem().getItem() == ModItems.TOPAZ_1.get() &&
                            gui.customSlots.get(154).getItem().getItem() == ModItems.TOPAZ_1.get()) {
                        if (gui.customSlots.get(265).getItem().getItem() == Items.AIR) {
                            gui.getSlot(265).set(new ItemStack(ModItems.TOPAZ_2.get()));
                            gui.hasResult = true;
                        }
                    }
                }
                if(gui.customSlots.get(0).getItem().getItem() == ModItems.TOPAZ_2.get()) {
                    if(gui.customSlots.get(132).getItem().getItem() == ModItems.TOPAZ_2.get() &&
                            gui.customSlots.get(133).getItem().getItem() == ModItems.TOPAZ_2.get()) {
                        if(gui.customSlots.get(110).getItem().getItem() == ModItems.TOPAZ_1.get() &&
                                gui.customSlots.get(112).getItem().getItem() == ModItems.TOPAZ_1.get() &&
                                gui.customSlots.get(131).getItem().getItem() == ModItems.TOPAZ_1.get() &&
                                gui.customSlots.get(134).getItem().getItem() == ModItems.TOPAZ_1.get() &&
                                gui.customSlots.get(153).getItem().getItem() == ModItems.TOPAZ_1.get() &&
                                gui.customSlots.get(155).getItem().getItem() == ModItems.TOPAZ_1.get()) {
                            if (gui.customSlots.get(265).getItem().getItem() == Items.AIR) {
                                gui.getSlot(265).set(new ItemStack(ModItems.TOPAZ_3.get()));
                                gui.hasResult = true;
                            }
                        }
                    }
                }
            }
            if(gui.hasResult && gui.customSlots.get(265).getItem().getItem() == Items.AIR) {
                for (int i = 0; i < 265; i++) {
                    if (gui.customSlots.get(i).getItem().getItem() != Items.AIR) {
                        gui.customSlots.get(i).getItem().shrink(1);
                    }
                }
                gui.hasResult = false;
            }
            if(gui.hasResult && gui.customSlots.get(265).getItem().getItem() != Items.AIR) {
                if(gui.customSlots.get(265).getItem().getItem() == ModItems.TOPAZ_2.get()) {
                    int [] arr = new int[] {0, 111, 132, 133, 154};
                    for (int j : arr) {
                        if (gui.customSlots.get(j).getItem().getItem() == Items.AIR) {
                            gui.customSlots.get(265).getItem().shrink(1);
                            gui.hasResult = false;
                        }
                    }
                }
            }
        }

    }

}
