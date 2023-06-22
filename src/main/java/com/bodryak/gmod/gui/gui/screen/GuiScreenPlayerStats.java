package com.bodryak.gmod.gui.gui.screen;

import com.bodryak.gmod.gui.gui.menu.GuiMenuPlayerStats;
import com.bodryak.gmod.network.ModMessages;
import com.bodryak.gmod.network.c2s.PDSDexterityUpC2SPacket;
import com.bodryak.gmod.network.c2s.PDSStaminaUpC2SPacket;
import com.bodryak.gmod.network.c2s.PDSStrengthUpC2SPacket;
import com.bodryak.gmod.variables.client.PDC;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
public class GuiScreenPlayerStats extends AbstractContainerScreen<GuiMenuPlayerStats> {

    Button plus_stat;

    public GuiScreenPlayerStats(GuiMenuPlayerStats container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.imageWidth = (int) (800/scale);
        this.imageHeight = (int) (615/scale);

    }

    private static final ResourceLocation texture = new ResourceLocation("gmod:textures/gui/stats_bg.png");
    double scale = Minecraft.getInstance().getWindow().getGuiScale();
    Font font = Minecraft.getInstance().font;


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
        this.blit(ms, this.leftPos, this.topPos, 0, 0,  this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
        RenderSystem.disableBlend();

        GuiComponent.drawString(ms, font, Component.literal(Minecraft.getInstance().player.getName().getString()), this.leftPos +5 , this.topPos +5, -1);
        //lvl string
        GuiComponent.drawString(ms, font, Component.literal("LVL"), this.leftPos +5, this.topPos + 15, -1);
        GuiComponent.drawString(ms, font, Component.literal(String.valueOf(PDC.getLvl())), this.leftPos +135, this.topPos + 15, -1);
        //HP string
        GuiComponent.drawString(ms, font, Component.literal("HP"), this.leftPos +5, this.topPos + 25, -1);
        GuiComponent.drawString(ms, font, Component.literal(String.valueOf(PDC.getMax_hp())), this.leftPos +135, this.topPos + 25, -1);
        //MP string
        GuiComponent.drawString(ms, font, Component.literal("MP"), this.leftPos +5, this.topPos + 35, -1);
        GuiComponent.drawString(ms, font, Component.literal(String.valueOf(PDC.getMax_mana())), this.leftPos +135, this.topPos + 35, -1);
        //exp string
        GuiComponent.drawString(ms, font, Component.literal("EXP"), this.leftPos +5, this.topPos + 45, -1);
        GuiComponent.drawString(ms, font, Component.literal("N/A"), this.leftPos +135, this.topPos + 45, -1);

        //stamina string
        GuiComponent.drawString(ms, font, Component.literal("Выносливость"), this.leftPos +5, this.topPos + 65, -1);
        GuiComponent.drawString(ms, font, Component.literal(String.valueOf(PDC.getStamina())), this.leftPos +135, this.topPos + 65, -1);
        //strength string
        GuiComponent.drawString(ms, font, Component.literal("Сила"), this.leftPos +5, this.topPos + 75, -1);
        GuiComponent.drawString(ms, font, Component.literal(String.valueOf(PDC.getStrength())), this.leftPos +135, this.topPos + 75, -1);
        //dex string
        GuiComponent.drawString(ms, font, Component.literal("Ловкость"), this.leftPos +5, this.topPos + 85, -1);
        GuiComponent.drawString(ms, font, Component.literal(String.valueOf(PDC.getDexterity())), this.leftPos +135, this.topPos + 85, -1);
        //int string
        GuiComponent.drawString(ms, font, Component.literal("Интелект"), this.leftPos +5, this.topPos + 95, -1);
        GuiComponent.drawString(ms, font, Component.literal(String.valueOf(PDC.getIntellect())), this.leftPos +135, this.topPos + 95, -1);
        //character point string
        GuiComponent.drawString(ms, font, Component.literal("Доступно"), this.leftPos +5, this.topPos + 105, -1);
        GuiComponent.drawString(ms, font, Component.literal(String.valueOf(PDC.getCh_point())), this.leftPos +135, this.topPos + 105, -1);

        //phis atk string
        GuiComponent.drawString(ms, font, Component.literal("Физ атака"), this.leftPos +5, this.topPos + 125, -1);
        GuiComponent.drawString(ms, font, Component.literal(String.valueOf(PDC.getPhis_attack())), this.leftPos +135, this.topPos + 125, -1);
        //magic atk string
        GuiComponent.drawString(ms, font, Component.literal("Маг атака"), this.leftPos +5, this.topPos + 135, -1);
        GuiComponent.drawString(ms, font, Component.literal(String.valueOf(PDC.getMag_attack())), this.leftPos +135, this.topPos + 135, -1);
        //critical string
        GuiComponent.drawString(ms, font, Component.literal("Крит шанс"), this.leftPos +5, this.topPos + 145, -1);
        GuiComponent.drawString(ms, font, Component.literal((PDC.getCritical_chance()/100) + "%"), this.leftPos +135, this.topPos + 145, -1);
        //critical shot value string
        GuiComponent.drawString(ms, font, Component.literal("Крит урон"), this.leftPos +5, this.topPos + 155, -1);
        GuiComponent.drawString(ms, font, Component.literal(String.valueOf(PDC.getCritical_damage()) + "%"), this.leftPos +135, this.topPos + 155, -1);
        //atack speed string
        GuiComponent.drawString(ms, font, Component.literal("Скорость атаки"), this.leftPos +5, this.topPos + 165, -1);
        GuiComponent.drawString(ms, font, Component.literal("0"), this.leftPos +135, this.topPos + 165, -1);
        //cast speed string
        GuiComponent.drawString(ms, font, Component.literal("Скорость заклинаний"), this.leftPos +5, this.topPos + 175, -1);
        GuiComponent.drawString(ms, font, Component.literal("0"), this.leftPos +135, this.topPos + 175, -1);
        //меткость
        GuiComponent.drawString(ms, font, Component.literal("Меткость"), this.leftPos +5, this.topPos + 185, -1);
        GuiComponent.drawString(ms, font, Component.literal(String.valueOf(PDC.getAccuracy())), this.leftPos +135, this.topPos + 185, -1);
        //уклонение
        GuiComponent.drawString(ms, font, Component.literal("Уклонение"), this.leftPos +5, this.topPos + 195, -1);
        GuiComponent.drawString(ms, font, Component.literal(String.valueOf(PDC.getAvoidance())), this.leftPos +135, this.topPos + 195, -1);
        //показатель атаки
        GuiComponent.drawString(ms, font, Component.literal("Показатель атаки"), this.leftPos +5, this.topPos + 205, -1);
        GuiComponent.drawString(ms, font, Component.literal("0"), this.leftPos +135, this.topPos + 205, -1);

        //phis def string
        GuiComponent.drawString(ms, font, Component.literal("Физ защита"), this.leftPos +5, this.topPos + 225, -1);
        GuiComponent.drawString(ms, font, Component.literal(String.valueOf(Math.round((float) PDC.getPhis_def() /100))), this.leftPos +135, this.topPos + 225, -1);
        //magical def string
        GuiComponent.drawString(ms, font, Component.literal("Маг защита"), this.leftPos +5, this.topPos + 235, -1);
        GuiComponent.drawString(ms, font, Component.literal(String.valueOf(Math.round((float) PDC.getMag_def() /100))), this.leftPos +135, this.topPos + 235, -1);
        //метал
        GuiComponent.drawString(ms, font, Component.literal("Метал"), this.leftPos +5, this.topPos + 245, -1);
        GuiComponent.drawString(ms, font, Component.literal(String.valueOf(Math.round((float) PDC.getMetal_def() /100))), this.leftPos +135, this.topPos + 245, -1);
        //дерево
        GuiComponent.drawString(ms, font, Component.literal("Дерево"), this.leftPos +5, this.topPos + 255, -1);
        GuiComponent.drawString(ms, font, Component.literal(String.valueOf(Math.round((float) PDC.getTree_def() /100))), this.leftPos +135, this.topPos + 255, -1);
        //огонь
        GuiComponent.drawString(ms, font, Component.literal("Огонь"), this.leftPos +5, this.topPos + 265, -1);
        GuiComponent.drawString(ms, font, Component.literal(String.valueOf(Math.round((float) PDC.getFire_def() /100))), this.leftPos +135, this.topPos + 265, -1);
        //земля
        GuiComponent.drawString(ms, font, Component.literal("Земля"), this.leftPos +5, this.topPos + 275, -1);
        GuiComponent.drawString(ms, font, Component.literal(String.valueOf(Math.round((float) PDC.getEth_def() /100))), this.leftPos +135, this.topPos + 275, -1);
        //вода
        GuiComponent.drawString(ms, font, Component.literal("Вода"), this.leftPos +5, this.topPos + 285, -1);
        GuiComponent.drawString(ms, font, Component.literal(String.valueOf(Math.round((float) PDC.getWater_def() /100))), this.leftPos +135, this.topPos + 285, -1);
        //показатель атаки
        GuiComponent.drawString(ms, font, Component.literal("Показатель защиты"), this.leftPos +5, this.topPos + 295, -1);
        GuiComponent.drawString(ms, font, Component.literal(String.valueOf(Math.round(0.0))), this.leftPos +135, this.topPos + 295, -1);








        if(PDC.getCh_point() == 0){
            this.clearWidgets();
        }



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

        if(PDC.getCh_point() > 0){
            this.addRenderableWidget(new Button(this.leftPos + 150, this.topPos + 65, 8, 8, Component.literal("+"), e ->{
                ModMessages.sendToServer(new PDSStaminaUpC2SPacket());
            }));
            this.addRenderableWidget(new Button(this.leftPos + 150, this.topPos + 75, 8, 8, Component.literal("+"), e ->{
                ModMessages.sendToServer(new PDSStrengthUpC2SPacket());
            }));
            this.addRenderableWidget(new Button(this.leftPos + 150, this.topPos + 85, 8, 8, Component.literal("+"), e ->{
                ModMessages.sendToServer(new PDSDexterityUpC2SPacket());
            }));
            this.addRenderableWidget(new Button(this.leftPos + 150, this.topPos + 95, 8, 8, Component.literal("+"), e ->{}));
        }
    }

}
