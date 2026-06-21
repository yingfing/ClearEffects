package com.example.cleareffects.config;

import com.example.cleareffects.ClearEffectsMod;
import java.io.*;
import java.util.Properties;

public class ModConfig {
    public static boolean disableBlindness = true;
    public static boolean disableDarkness = true;
    public static boolean disableNausea = true;

    private static final String CONFIG_FILE = "config/cleareffects.properties";

    public static void load() {
        Properties props = new Properties();
        File file = new File(CONFIG_FILE);
        if (file.exists()) {
            try (FileInputStream in = new FileInputStream(file)) {
                props.load(in);
                disableBlindness = Boolean.parseBoolean(props.getProperty("disableBlindness", "true"));
                disableDarkness = Boolean.parseBoolean(props.getProperty("disableDarkness", "true"));
                disableNausea = Boolean.parseBoolean(props.getProperty("disableNausea", "true"));
            } catch (IOException e) {
                ClearEffectsMod.LOGGER.error("Failed to load config", e);
            }
        } else {
            save();
        }
    }

    public static void save() {
        Properties props = new Properties();
        props.setProperty("disableBlindness", String.valueOf(disableBlindness));
        props.setProperty("disableDarkness", String.valueOf(disableDarkness));
        props.setProperty("disableNausea", String.valueOf(disableNausea));
        File file = new File(CONFIG_FILE);
        file.getParentFile().mkdirs();
        try (FileOutputStream out = new FileOutputStream(file)) {
            props.store(out, "ClearEffects Config - true = disable shader");
        } catch (IOException e) {
            ClearEffectsMod.LOGGER.error("Failed to save config", e);
        }
    }
}
