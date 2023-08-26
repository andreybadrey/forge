package com.bodryak.gmod.gui.gui.screen;

import com.bodryak.gmod.gui.gui.menu.GuiGameShopMenu;
import com.bodryak.gmod.network.ModMessages;
import com.bodryak.gmod.network.c2s.ByeAppleC2SPacket;
import com.bodryak.gmod.network.c2s.GetBalanceC2SPacket;
import com.bodryak.gmod.network.c2s.PDSStaminaUpC2SPacket;
import com.bodryak.gmod.util.mysql.ModDBHandler;
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

import java.sql.SQLException;

public class GuiScreenGameShop extends AbstractContainerScreen<GuiGameShopMenu> {

    private static final ResourceLocation texture = new ResourceLocation("gmod:textures/gui/stats_bg.png");
    double scale = Minecraft.getInstance().getWindow().getGuiScale();
    Font font = Minecraft.getInstance().font;

    public static int balance;
    public static boolean isBlocked = true;
    private String selected = "none";
    private String sub_select = "none";

    private int frames;

    public GuiScreenGameShop(GuiGameShopMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.imageWidth = (int) (800/scale);
        this.imageHeight = (int) (615/scale);

    }

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

        GuiComponent.drawString(ms, font, Component.literal(String.valueOf(frames)), this.leftPos +5 , this.topPos +5, -1);
        GuiComponent.drawString(ms, font, Component.literal("Баланс: " + balance), this.leftPos +3 , this.topPos +297, -1);

        int topPos = 35;
        for (int i = frames ; i > 0; i --) {
            drawFrame(ms, this.leftPos +90, this.topPos + topPos);
            i --;
            if(i != 0){
                drawFrame(ms, this.leftPos +90 +153, this.topPos + topPos);
            }
            topPos += 85;
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

        ModMessages.sendToServer(new GetBalanceC2SPacket());

        int butx = 60;
        int buty = 20;
        int bstep = 62;
        int startposx = 90;


        this.addRenderableWidget(new Button(this.leftPos + startposx, this.topPos + 3, butx, buty, Component.literal("Разное"), e ->{
            selected = "Разное";
            sub_select = "Новинки";
            frames = 6;
            this.clearWidgets();
            this.init();
        }));
        startposx += bstep;
        this.addRenderableWidget(new Button(this.leftPos + startposx, this.topPos + 3, butx, buty, Component.literal("VIP"), e ->{
            selected = "VIP";
            this.clearWidgets();
            this.init();
        }));
        startposx += bstep;
        this.addRenderableWidget(new Button(this.leftPos + startposx, this.topPos + 3, butx, buty, Component.literal("Стиль"), e ->{
            //действие
        }));
        startposx += bstep;
        this.addRenderableWidget(new Button(this.leftPos + startposx, this.topPos + 3, butx, buty, Component.literal("Персонаж"), e ->{
            //действие
        }));
        startposx += bstep;
        this.addRenderableWidget(new Button(this.leftPos + startposx, this.topPos + 3, butx, buty, Component.literal("Другое"), e ->{
            //действие
        }));

        int subbutx = 30;
        int subbuty = 10;
        int substep = 32;
        int substart = 90;

        if(selected.equals("none") || selected.equals("Разное")){
            this.addRenderableWidget(new Button(this.leftPos + substart, this.topPos + 25, subbutx, subbuty, Component.literal("Новинки"), e ->{
                sub_select = "Новинки";
                frames = 6;
                this.clearWidgets();
                this.init();

            }));
            substart += substep;
            this.addRenderableWidget(new Button(this.leftPos + substart, this.topPos + 25, subbutx, subbuty, Component.literal("Ресурсы"), e ->{
                sub_select = "Ресурсы";
                frames = 1;
                this.clearWidgets();
                this.init();
            }));
            substart += substep;
            this.addRenderableWidget(new Button(this.leftPos + substart, this.topPos + 25, subbutx, subbuty, Component.literal("Питомцы"), e ->{
                //действие
            }));
            substart += substep;
            this.addRenderableWidget(new Button(this.leftPos + substart, this.topPos + 25, subbutx, subbuty, Component.literal("Краски"), e ->{
                //действие
            }));
            substart += substep;
            this.addRenderableWidget(new Button(this.leftPos + substart, this.topPos + 25, subbutx, subbuty, Component.literal("Заточка"), e ->{
                //действие
            }));
            substart += substep;
            this.addRenderableWidget(new Button(this.leftPos + substart, this.topPos + 25, subbutx, subbuty, Component.literal("Книги"), e ->{
                //действие
            }));
            substart += substep;
            this.addRenderableWidget(new Button(this.leftPos + substart, this.topPos + 25, subbutx, subbuty, Component.literal("Свадьба"), e ->{
                //действие
            }));
            substart += substep;
            this.addRenderableWidget(new Button(this.leftPos + substart, this.topPos + 25, subbutx, subbuty, Component.literal("Дом"), e ->{
                //действие
            }));
            substart += substep;
            this.addRenderableWidget(new Button(this.leftPos + substart, this.topPos + 25, subbutx, subbuty, Component.literal("Все"), e ->{
                //действие
            }));
            substart = 90;
            if(sub_select.equals("none") || sub_select.equals("Новинки")) {
                frames = 6;
                int fbutposX = this.leftPos + 90;
                int fbutposY = this.topPos + 104;
                int fbutW = 40;
                int fbutH = 10;
                for(int i = frames; i > 0; i--){
                    this.addRenderableWidget(new Button(fbutposX, fbutposY, fbutW, fbutH, Component.literal("Дарить"), e ->{
                        //действие
                    }));
                    fbutposX += 42;
                    this.addRenderableWidget(new Button(fbutposX, fbutposY, fbutW, fbutH, Component.literal("Хочу"), e ->{
                        //действие
                    }));
                    fbutposX += 42;
                    this.addRenderableWidget(new Button(fbutposX, fbutposY, fbutW, fbutH, Component.literal("Купить"), e ->{
                        if(!isBlocked){
                            ModMessages.sendToServer(new ByeAppleC2SPacket());
                            isBlocked = true;
                        }

                    }));
                    fbutposX += 70;
                    i--;
                    if(i!=0) {
                        this.addRenderableWidget(new Button(fbutposX, fbutposY, fbutW, fbutH, Component.literal("Дарить"), e ->{
                            //действие
                        }));
                        fbutposX += 42;
                        this.addRenderableWidget(new Button(fbutposX, fbutposY, fbutW, fbutH, Component.literal("Хочу"), e ->{
                            //действие
                        }));
                        fbutposX += 42;
                        this.addRenderableWidget(new Button(fbutposX, fbutposY, fbutW, fbutH, Component.literal("Купить"), e ->{
                            //действие
                        }));
                        fbutposX = this.leftPos + 90;
                        fbutposY += 85;
                    }
                }
            }
            if(sub_select.equals("Ресурсы")){
            }
        }

        if(selected.equals("VIP")){
            this.addRenderableWidget(new Button(this.leftPos + substart, this.topPos + 25, subbutx, subbuty, Component.literal("Vip1"), e ->{

            }));
            substart += substep;
            this.addRenderableWidget(new Button(this.leftPos + substart, this.topPos + 25, subbutx, subbuty, Component.literal("Vip2"), e ->{
                //действие
            }));
            substart += substep;
            this.addRenderableWidget(new Button(this.leftPos + substart, this.topPos + 25, subbutx, subbuty, Component.literal("Vip3"), e ->{
                //действие
            }));
            substart += substep;
            this.addRenderableWidget(new Button(this.leftPos + substart, this.topPos + 25, subbutx, subbuty, Component.literal("Vip4"), e ->{
                //действие
            }));
            substart += substep;
            this.addRenderableWidget(new Button(this.leftPos + substart, this.topPos + 25, subbutx, subbuty, Component.literal("Vip5"), e ->{
                //действие
            }));
            substart += substep;
            this.addRenderableWidget(new Button(this.leftPos + substart, this.topPos + 25, subbutx, subbuty, Component.literal("Vip6"), e ->{
                //действие
            }));
            substart += substep;
            this.addRenderableWidget(new Button(this.leftPos + substart, this.topPos + 25, subbutx, subbuty, Component.literal("Vip7"), e ->{
                //действие
            }));
            substart += substep;
            this.addRenderableWidget(new Button(this.leftPos + substart, this.topPos + 25, subbutx, subbuty, Component.literal("Vip8"), e ->{
                //действие
            }));
            substart += substep;
            this.addRenderableWidget(new Button(this.leftPos + substart, this.topPos + 25, subbutx, subbuty, Component.literal("Vip9"), e ->{
                //действие
            }));
            substart = 90;
        }


    }

    public void drawFrame(PoseStack ms, int posX, int posY) {

        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, texture);
        this.blit(ms, posX, posY, 0, 0,  150, 80, 150, 80);
        RenderSystem.disableBlend();

        GuiComponent.drawString(ms, font, Component.literal("§lНазвание товара"), posX , posY, -1);
        GuiComponent.drawString(ms, font, Component.literal("§l120 §6§lЗолотых"), posX + 70, posY + 40, -1);
    }
}
