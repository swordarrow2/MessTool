package com.meng.drawer.gui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Pixmap.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.*;
import com.meng.drawer.anm.*;
import com.meng.drawer.anm.beans.*;
import com.meng.tools.*;

public class PicScreen extends ScreenAdapter {
    private PicMain gameMain;
    private Stage stage;
    private FitViewport fitViewport;
    private AnmBean anmBean;
    private InstructionRectangle[] rectangles;

    @Override
    public void show() {
        anmBean = new AnmFile("th16_cirno.anm").anmBeans.get(0);
        int[] rgbaArray;
        switch (anmBean.thtx.format) {
            case 1:
                rgbaArray = BitmapHelper.argb8888ToRgba8888(anmBean.thtx.data);
                break;
            case 3:
                rgbaArray = BitmapHelper.rgb565ToRgba8888(anmBean.thtx.data);
                break;
            case 5:
                rgbaArray = BitmapHelper.argb4444ToRgba8888(anmBean.thtx.data);
                break;
            case 7:
                rgbaArray = BitmapHelper.gray8ToRgba8888(anmBean.thtx.data);
                break;
            default:
                throw new RuntimeException("pic:" + anmBean.picName + " unexpect value:" + anmBean.thtx.format);
        }
        PicMain.width = anmBean.thtx.w;
        PicMain.height = anmBean.thtx.h;
        fitViewport = new FitViewport(PicMain.width, PicMain.height);
        stage = new Stage(fitViewport, gameMain.spriteBatch);
        Pixmap p = new Pixmap(anmBean.thtx.w, anmBean.thtx.h, Format.RGBA8888);
        for (int i = 0; i < rgbaArray.length; i++) {
            p.drawPixel(i % anmBean.thtx.w, i / anmBean.thtx.w, rgbaArray[i]);
        }
        Image bg = new Image(new Texture(p));
        bg.setBounds(0, 0, anmBean.thtx.w, anmBean.thtx.h);
        stage.addActor(bg);
//        FmtFile fmtFile=new FmtFile("th10.fmt");
//        WavFile wavFile=new WavFile("", fmtFile);
        rectangles = new InstructionRectangle[anmBean.sprites.length];
        Sprite[] sprites = anmBean.sprites;
        int i = 0;
        for (Sprite sprite : sprites) {
            InstructionRectangle rectangle = new InstructionRectangle(sprite.x, anmBean.thtx.h - sprite.y, sprite.w, sprite.h);
            stage.addActor(rectangle);
            rectangles[i++] = rectangle;
        }

        super.show();
    }

    public PicScreen(PicMain game) {
        gameMain = game;
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        fitViewport.update(width, height);
    }

    @Override
    public void render(float delta) {
        stage.draw();
        gameMain.spriteBatch.begin();

        for (Sprite sprite : anmBean.sprites) {
            gameMain.bitmapFont.draw(gameMain.spriteBatch, sprite.id + "", sprite.x, anmBean.thtx.h - sprite.y);
        }
        gameMain.spriteBatch.end();
        super.render(delta);
    }

    @Override
    public void hide() {
        super.hide();
    }
}
