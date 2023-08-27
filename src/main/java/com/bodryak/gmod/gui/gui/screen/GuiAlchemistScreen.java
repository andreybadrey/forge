package com.bodryak.gmod.gui.gui.screen;

import com.bodryak.gmod.gui.gui.menu.GuiAlchemistMenu;
import com.bodryak.gmod.item.ModItems;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class GuiAlchemistScreen extends AbstractContainerScreen<GuiAlchemistMenu> {
    private static final ResourceLocation texture = new ResourceLocation("gmod:textures/gui/stats_bg.png");
    private static final ResourceLocation topaz   = new ResourceLocation("gmod:textures/gui/topaz.png");
    private static final ResourceLocation topaz11   = new ResourceLocation("gmod:textures/gui/topaz11.png");
    private static final ResourceLocation topaz12   = new ResourceLocation("gmod:textures/gui/topaz12.png");


    public GuiAlchemistScreen(GuiAlchemistMenu container, Inventory inventory, Component component) {
        super(container, inventory, component);
        this.imageWidth = 400;
        this.imageHeight = 300;
    }

    @Override
    protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, texture);
        blit(ms, this.leftPos, this.topPos, 0, 0,  this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
        RenderSystem.disableBlend();
    }
    @Override
    public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(ms);
        super.render(ms, mouseX, mouseY, partialTicks);
        this.renderTooltip(ms, mouseX, mouseY);
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            assert this.minecraft != null;
            assert this.minecraft.player != null;
            this.minecraft.player.closeContainer();
            return true;
        }
        return super.keyPressed(key, b, c);
    }

    @Override
    public void containerTick() {
        super.containerTick();
    }

    @Override
    protected void renderLabels( PoseStack poseStack, int mouseX, int mouseY) {
    }

    @Override
    public void onClose() {
        super.onClose();
        Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
    }

    @Override
    public void init() {
        super.init();
        assert this.minecraft != null;
        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
        int i = 4;
        this.addRenderableWidget(new ImageButton(this.leftPos + i, this.topPos + 4, 32, 32, 0, 0, 0, topaz, 32, 32,
                e -> { }, (button, poseStack, x, y) -> renderTooltip(poseStack, new ItemStack(ModItems.TOPAZ_2.get()), x, y), Component.literal("ебать!")));
        i += 36;
        this.addRenderableWidget(new ImageButton(this.leftPos + i, this.topPos + 4, 32, 32, 0, 0, 0, topaz, 32, 32,
                e -> { }, (button, poseStack, x, y) -> renderTooltip(poseStack, new ItemStack(ModItems.TOPAZ_3.get()), x, y), Component.literal("ебать!")));
        i += 36;
        this.addRenderableWidget(new ImageButton(this.leftPos + i, this.topPos + 4, 32, 32, 0, 0, 0, topaz, 32, 32,
                e -> { }, (button, poseStack, x, y) -> renderTooltip(poseStack, new ItemStack(ModItems.TOPAZ_4.get()), x, y), Component.literal("ебать!")));
        i += 36;
        this.addRenderableWidget(new ImageButton(this.leftPos + i, this.topPos + 4, 32, 32, 0, 0, 0, topaz, 32, 32,
                e -> { }, (button, poseStack, x, y) -> renderTooltip(poseStack, new ItemStack(ModItems.TOPAZ_5.get()), x, y), Component.literal("ебать!")));
        i += 36;
        this.addRenderableWidget(new ImageButton(this.leftPos + i, this.topPos + 4, 32, 32, 0, 0, 0, topaz, 32, 32,
                e -> { }, (button, poseStack, x, y) -> renderTooltip(poseStack, new ItemStack(ModItems.TOPAZ_6.get()), x, y), Component.literal("ебать!")));
        i += 36;
        this.addRenderableWidget(new ImageButton(this.leftPos + i, this.topPos + 4, 32, 32, 0, 0, 0, topaz, 32, 32,
                e -> { }, (button, poseStack, x, y) -> renderTooltip(poseStack, new ItemStack(ModItems.TOPAZ_7.get()), x, y), Component.literal("ебать!")));
        i += 36;
        this.addRenderableWidget(new ImageButton(this.leftPos + i, this.topPos + 4, 32, 32, 0, 0, 0, topaz, 32, 32,
                e -> { }, (button, poseStack, x, y) -> renderTooltip(poseStack, new ItemStack(ModItems.TOPAZ_8.get()), x, y), Component.literal("ебать!")));
        i += 36;
        this.addRenderableWidget(new ImageButton(this.leftPos + i, this.topPos + 4, 32, 32, 0, 0, 0, topaz, 32, 32,
                e -> { }, (button, poseStack, x, y) -> renderTooltip(poseStack, new ItemStack(ModItems.TOPAZ_9.get()), x, y), Component.literal("ебать!")));
        i += 36;
        this.addRenderableWidget(new ImageButton(this.leftPos + i, this.topPos + 4, 32, 32, 0, 0, 0, topaz, 32, 32,
                e -> { }, (button, poseStack, x, y) -> renderTooltip(poseStack, new ItemStack(ModItems.TOPAZ_10.get()), x, y), Component.literal("ебать!")));
        i += 36;
        this.addRenderableWidget(new ImageButton(this.leftPos + i, this.topPos + 4, 32, 32, 0, 0, 0, topaz11, 32, 32,
                e -> { }, (button, poseStack, x, y) -> renderTooltip(poseStack, new ItemStack(ModItems.TOPAZ_11.get()), x, y), Component.literal("ебать!")));
        i += 36;
        this.addRenderableWidget(new ImageButton(this.leftPos + i, this.topPos + 4, 32, 32, 0, 0, 0, topaz12, 32, 32,
                e -> { }, (button, poseStack, x, y) -> renderTooltip(poseStack, new ItemStack(ModItems.TOPAZ_12.get()), x, y), Component.literal("ебать!")));
    }
}

/*
        this.addRenderableWidget(new Button(this.leftPos - 15, this.topPos -15, 15, 15, Component.literal("+"), e ->{
            minecraft.getWindow().setGuiScale(Minecraft.getInstance().getWindow().getGuiScale() + 0.1);
        }));
        this.addRenderableWidget(new Button(this.leftPos - 15, this.topPos +15, 15, 15, Component.literal("-"), e ->{
            minecraft.getWindow().setGuiScale(Minecraft.getInstance().getWindow().getGuiScale() - 0.1);
        }));
        this.addRenderableWidget(new Button(this.leftPos - 15, this.topPos -30, 15, 15, Component.literal("+"), e ->{
            minecraft.getWindow().setGuiScale(Minecraft.getInstance().getWindow().getGuiScale() + 0.01);
        }));
        this.addRenderableWidget(new Button(this.leftPos - 15, this.topPos +30, 15, 15, Component.literal("-"), e ->{
            minecraft.getWindow().setGuiScale(Minecraft.getInstance().getWindow().getGuiScale() - 0.01);
        }));*/
