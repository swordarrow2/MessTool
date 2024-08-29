package com.meng.tools.app;

public enum FunctionSavePath {
    barcode("barcode/normal"),
    bus("bus"),
    awesomeQR("barcode/awesome"),
    gifAwesomeQR("barcode/awesome_gif"),
    gif("gif"),
    pixivDynamic("pixiv/dynamic"),
    pixivAnimate("pixiv/animate"),
    pixivZIP("pixiv/zip"),
    gray8picture("gray_image"),
    convertVideo("convert_video"),
    convertAudio("convert_audio"),
    convertImage("convert_image"),
    TtsVoice("tts"),
    database("database"),
    electronic("eletronic"),
//  chat("chat"),
    chat_image("chat/image"),
    chat_script("chat/script"),
    chat_character("chat/character"),
    cache("cache"),
    log("crash_log");

    
    private String path;

    FunctionSavePath(String path){
        this.path = path;
    }
    
    public String getPath() {
        return path;
    }
}
