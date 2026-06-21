package com.example.cleareffects;

import com.example.cleareffects.config.ModConfig;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClearEffectsMod implements ModInitializer {
    public static final String MOD_ID = "cleareffects";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModConfig.load();
        LOGGER.info("ClearEffects initialized! Disabled shaders: " +
            (ModConfig.disableBlindness ? "Blindness " : "") +
            (ModConfig.disableDarkness ? "Darkness " : "") +
            (ModConfig.disableNausea ? "Nausea" : ""));
    }
}
