package com.bodryak.gmod.gui.overlay;

import com.bodryak.gmod.util.RayTrace;
import com.bodryak.gmod.variables.client.PDC;
import com.bodryak.gmod.variables.server.ProviderMDS;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;


@Mod.EventBusSubscriber({Dist.CLIENT})
public class HudOverlay {

    static final ResourceLocation TARGET_FRAME = new ResourceLocation("gmod:textures/gui/target_frame.png");
    static final ResourceLocation TARGET_BAR = new ResourceLocation("gmod:textures/gui/target_bar.png");
    static final ResourceLocation HUD = new ResourceLocation("gmod:textures/gui/hud.png");
    static final ResourceLocation EXP_BAR = new ResourceLocation("gmod:textures/gui/exp_bar.png");
    static final ResourceLocation EXP_F = new ResourceLocation("gmod:textures/gui/exp_bar_f.png");
    static final ResourceLocation HP_BAR = new ResourceLocation("gmod:textures/gui/hp_bar.png");
    static final ResourceLocation MP_BAR = new ResourceLocation("gmod:textures/gui/mp_bar.png");

    static final RayTrace TRACE = new RayTrace();

    static int target_bar_x = -70;
    static int target_hp;
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void eventHandler(RenderGuiEvent.Pre event) {




        Font font = Minecraft.getInstance().font;
        int w = event.getWindow().getGuiScaledWidth();
        int h = event.getWindow().getGuiScaledHeight();
        int x = 195;
        int max_w_1 = w-14-x;
        int all_w = max_w_1 +8;

        double point =  all_w * 0.01;
        double exp_p = PDC.getExp() / (PDC.getNlv() * 0.01);

        double exp_w = exp_p * point;
        double exp_f = 0;
        if (exp_w > max_w_1){
            exp_f = exp_w - max_w_1;
            exp_w = max_w_1;
        }

        double hp_w = (197*0.01) * (PDC.getHp() / (PDC.getMax_hp()*0.01));
        double mp_w = (159*0.01) * (PDC.getMana()/ (PDC.getMax_mana()*0.01));

        int target_bar_start = -70;
        int target_bar_x_final = 138;


        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        if (true) {
            RenderSystem.setShaderTexture(0, TARGET_FRAME);
            GuiComponent.blit(event.getPoseStack(), target_bar_x, 9, 0, 0, 247, 35, 247, 35);

            RenderSystem.setShaderTexture(0, TARGET_BAR);
            GuiComponent.blit(event.getPoseStack(), target_bar_x + 28, 14, 0, 0, target_hp, 25, 209, 25);

            RenderSystem.setShaderTexture(0, HUD);
            GuiComponent.blit(event.getPoseStack(), 0, 0, 0, 0, 203, 82, 203, 82);

            RenderSystem.setShaderTexture(0, EXP_BAR);
            GuiComponent.blit(event.getPoseStack(), 193, 0, 0, 0, w - 203, 10, 1980, 10);
            GuiComponent.blit(event.getPoseStack(), w - 13, 0, 1980 - 13, 0, 13, 10, 1980, 10);

            RenderSystem.setShaderTexture(0, EXP_F);
            GuiComponent.blit(event.getPoseStack(), x,            1,  0,       0,(int) Math.round(exp_w), 8, 1972, 8);
            GuiComponent.blit(event.getPoseStack(), w-14, 1,  1972-8,  0,(int) Math.round(exp_f), 8, 1972, 8);

            RenderSystem.setShaderTexture(0, HP_BAR);
            GuiComponent.blit(event.getPoseStack(), 1, 1, 0, 0, (int) Math.round(hp_w), 19, 197, 19);

            RenderSystem.setShaderTexture(0, MP_BAR);
            GuiComponent.blit(event.getPoseStack(), 1, 21, 0, 0, (int) Math.round(mp_w), 19, 159, 19);


            GuiComponent.drawString(event.getPoseStack(), font, "LVL: " + PDC.getLvl(), 5, 70, -1);

            font.draw(event.getPoseStack(), Component.literal("asdfasfafsaf").withStyle(), 10, 10, -1);
            LivingEntity target = TRACE.getEntityInCrosshair(1.0f, 20.0D);
            if (target != null) {
                if(target_bar_x < target_bar_x_final){
                    target_bar_x +=16;
                }
                target.getCapability(ProviderMDS.MOB_DATA).ifPresent(mds -> {
                    target_hp = (int) ((209*0.01) * (mds.getHp() / (mds.getMax_hp()*0.01)));
                    GuiComponent.drawString(event.getPoseStack(), font, String.valueOf(mds.getHp()), target_bar_x + 53, 25, -1);
                });
                    GuiComponent.drawString(event.getPoseStack(), font, target.getDisplayName(), target_bar_x + 53, 15, -1);
            } else {
                if(target_bar_x > target_bar_start){
                    target_bar_x -=16;
                }
            }

        }
        RenderSystem.depthMask(true);
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1, 1, 1, 1);
    }
}
