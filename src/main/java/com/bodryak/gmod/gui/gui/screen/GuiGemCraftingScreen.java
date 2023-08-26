package com.bodryak.gmod.gui.gui.screen;

import com.bodryak.gmod.gui.gui.menu.GuiGemCraftingMenu;
import com.bodryak.gmod.item.ModItems;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.Minecraft;


import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

    public class GuiGemCraftingScreen extends AbstractContainerScreen<GuiGemCraftingMenu> {
        private final static HashMap<String, Object> guistate = GuiGemCraftingMenu.guistate;
        private final Level world;
        private final int x, y, z;
        private final Player entity;

        private int item;

        public GuiGemCraftingScreen(GuiGemCraftingMenu container, Inventory inventory, Component text) {
            super(container, inventory, text);
            this.world = container.world;
            this.x = container.x;
            this.y = container.y;
            this.z = container.z;
            this.entity = container.entity;
            this.imageWidth = 800;
            this.imageHeight = 640;
        }

        private static final ResourceLocation texture = new ResourceLocation("gmod:textures/gui/gem_craft_bg.png");
        private static final ResourceLocation slot    = new ResourceLocation("gmod:textures/gui/gem_slot.png");
        private static final ResourceLocation slot1    = new ResourceLocation("gmod:textures/gui/gem_slot1.png");
        private static final ResourceLocation slot2    = new ResourceLocation("gmod:textures/gui/gem_slot2.png");

        @Override
        public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
            //this.renderBackground(ms);
            super.render(ms, mouseX, mouseY, partialTicks);
            this.renderTooltip(ms, mouseX, mouseY);
        }

        @Override
        protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
            RenderSystem.setShaderColor(1, 1, 1, 1);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShaderTexture(0, texture);
            blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
            RenderSystem.setShaderTexture(0, slot);
            blit(ms, this.leftPos + this.menu.gem_slots[0][0] -1, this.topPos + this.menu.gem_slots[0][1] -1, 0, 0, 18, 18, 18, 18);
            if(this.menu.getSlot(0).getItem().getItem() == ModItems.TOPAZ_2.get()) {
                RenderSystem.setShaderTexture(0, slot2);
                blit(ms, this.leftPos + this.menu.gem_slots[132][0] -1, this.topPos + this.menu.gem_slots[132][1] -1, 0, 0, 18, 18, 18, 18);
                blit(ms, this.leftPos + this.menu.gem_slots[133][0] -1, this.topPos + this.menu.gem_slots[133][1] -1, 0, 0, 18, 18, 18, 18);
                RenderSystem.setShaderTexture(0, slot1);
                blit(ms, this.leftPos + this.menu.gem_slots[131][0] -1, this.topPos + this.menu.gem_slots[131][1] -1, 0, 0, 18, 18, 18, 18);
                blit(ms, this.leftPos + this.menu.gem_slots[110][0] -1, this.topPos + this.menu.gem_slots[110][1] -1, 0, 0, 18, 18, 18, 18);
                blit(ms, this.leftPos + this.menu.gem_slots[153][0] -1, this.topPos + this.menu.gem_slots[153][1] -1, 0, 0, 18, 18, 18, 18);
                blit(ms, this.leftPos + this.menu.gem_slots[112][0] -1, this.topPos + this.menu.gem_slots[112][1] -1, 0, 0, 18, 18, 18, 18);
                blit(ms, this.leftPos + this.menu.gem_slots[134][0] -1, this.topPos + this.menu.gem_slots[134][1] -1, 0, 0, 18, 18, 18, 18);
                blit(ms, this.leftPos + this.menu.gem_slots[155][0] -1, this.topPos + this.menu.gem_slots[155][1] -1, 0, 0, 18, 18, 18, 18);

            }
            if(this.menu.getSlot(0).getItem().getItem() == ModItems.TOPAZ_1.get()) {
                RenderSystem.setShaderTexture(0, slot1);
                blit(ms, this.leftPos + this.menu.gem_slots[132][0] -1, this.topPos + this.menu.gem_slots[132][1] -1, 0, 0, 18, 18, 18, 18);
                blit(ms, this.leftPos + this.menu.gem_slots[111][0] -1, this.topPos + this.menu.gem_slots[111][1] -1, 0, 0, 18, 18, 18, 18);
                blit(ms, this.leftPos + this.menu.gem_slots[133][0] -1, this.topPos + this.menu.gem_slots[133][1] -1, 0, 0, 18, 18, 18, 18);
                blit(ms, this.leftPos + this.menu.gem_slots[154][0] -1, this.topPos + this.menu.gem_slots[154][1] -1, 0, 0, 18, 18, 18, 18);
            }

            RenderSystem.disableBlend();
        }

        @Override
        public boolean keyPressed(int key, int b, int c) {
            if (key == 256) {
                this.minecraft.player.closeContainer();
                return true;
            }
            return super.keyPressed(key, b, c);
        }

        @Override
        public void containerTick() {
            super.containerTick();
            if(this.entity.containerMenu.getSlot(0).getItem().getItem() == ModItems.TOPAZ_1.get() && item == 0) {
                item = 1;
                System.out.println("gem");

                //init();
            }
            if(this.entity.containerMenu.getSlot(0).getItem().getItem() == Items.AIR && item != 0) {
                item = 0;
                System.out.println("air");
                this.init();
            }

        }

        @Override
        protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
        }

        @Override
        public void onClose() {
            super.onClose();
            Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
        }

        @Override
        public void init() {
            super.init();
            this.minecraft.keyboardHandler.setSendRepeatsToGui(true);

            this.clearWidgets();
        }
    }
