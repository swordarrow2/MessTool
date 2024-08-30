package com.meng.tools.app;

public enum FileSavePath {
    BARCODE("barcode/normal/"),
    BUS("bus/"),
    AWESOME_QR("barcode/awesome/"),
    GIF_AWESOME_QR("barcode/awesome_gif/"),
    GIF("gif/"),
    PIXIV_DYNAMIC("pixiv/dynamic/"),
    PIXIV_ANIMATE("pixiv/animate/"),
    PIXIV_ZIP("pixiv/zip/"),
    GRAY_8_PICTURE("gray_image/"),
    CONVERT_VIDEO("convert_video/"),
    CONVERT_AUDIO("convert_audio/"),
    CONVERT_IMAGE("convert_image/"),
    TTS_VOICE("tts/"),
    DATABASE("database/"),
    ELECTRONIC("eletronic/"),
    //  chat("chat"),
    CHAT_IMAGE("chat/image/"),
    CHAT_SCRIPT("chat/script/"),
    CHAT_CHARACTER("chat/character/"),
    RPG_DECRYPT("rpg_decrypt/"),
    CACHE("cache/"),
    LOG("crash_log/");


    private String path;

    FileSavePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
