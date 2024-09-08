package com.meng.drawer;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Pixmap.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.meng.drawer.anm.*;
import com.meng.drawer.anm.beans.*;
import com.meng.drawer.anm.beans.Sprite;
import com.meng.tools.*;

import java.util.*;

public final class ResourcesManager {
    public static HashMap<String, Drawable> textures = new HashMap<String, Drawable>();
    public static HashMap<String, Drawable> flipedTextures = new HashMap<String, Drawable>();
    public static HashMap<Integer, Drawable> playerDrawabls = new HashMap<>();
    public static String pathBase = null;

    public static void Load() {
        loadAnm("th15_reisen.anm", "pl00");
        loadAnm("st06enm.anm", "chunhu");
        loadAnm("bullet.anm", "bullet");
        loadAnm("effect.anm", "effect");
    }

    private static void loadAnm(String anmName, String spriteName) {
        AnmFile thanm = new AnmFile(anmName);
        for (AnmBean anmPart : thanm.anmBeans) {
            int[] rgbaArray;
            switch (anmPart.thtx.format) {
                case 1:
                    rgbaArray = BitmapHelper.argb8888ToRgba8888(anmPart.thtx.data);
                    break;
                case 3:
                    rgbaArray = BitmapHelper.rgb565ToRgba8888(anmPart.thtx.data);
                    break;
                case 5:
                    rgbaArray = BitmapHelper.argb4444ToRgba8888(anmPart.thtx.data);
                    break;
                case 7:
                    rgbaArray = BitmapHelper.gray8ToRgba8888(anmPart.thtx.data);
                    break;
                default:
                    throw new RuntimeException("pic:" + anmPart.picName + " unexpect value:" + anmPart.thtx.format);
            }
            Pixmap pixmap = new Pixmap(anmPart.thtx.w, anmPart.thtx.h, Format.RGBA8888);
            for (int i = 0; i < rgbaArray.length; i++) {
                pixmap.drawPixel(i % anmPart.thtx.w, i / anmPart.thtx.w, rgbaArray[i]);
            }
            Texture texture = new Texture(pixmap);
            for (Sprite sprite : anmPart.sprites) {
                textures.put(spriteName + sprite.id, new TextureRegionDrawable(
                        new TextureRegion(texture, (int) sprite.x, (int) sprite.y, (int) sprite.w, (int) sprite.h)));
            }
        }
    }
}
