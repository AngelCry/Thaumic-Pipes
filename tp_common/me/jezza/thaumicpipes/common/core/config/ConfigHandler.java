package me.jezza.thaumicpipes.common.core.config;

import java.io.File;

import me.jezza.thaumicpipes.common.core.TPLogger;
import me.jezza.thaumicpipes.common.lib.Reference;
import me.jezza.thaumicpipes.common.lib.Strings;
import me.jezza.thaumicpipes.common.lib.TextureMaps;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.FMLCommonHandler;

public class ConfigHandler {

    private static Configuration config;

    private static final String MISC = "Gameplay";

    public static void init(File file) {
        config = new Configuration(file);

        try {
            config.load();

            TPLogger.info("Configs started to load");

            getConstants();

            TPLogger.info("Configs loaded successfully");

        } catch (Exception e) {

        } finally {
            config.save();
        }
    }

    public static void getConstants() {
        Reference.COMMANDS = config.get(MISC, "commands", Reference.COMMANDS, "Should I implement some useful commands.\nThey aren't thaumcraft related, just some useful commands.").getBoolean(false);

        TextureMaps.THAUMIC_TEXTURE_INDEX = config.get(MISC, Strings.THAUMIC_PIPE_TEXTURE, TextureMaps.THAUMIC_TEXTURE_INDEX, "0 - Steam punk style texture.\n1 - Old thaumcraft style texture.\n2 - The default texture.").getInt();
    }

    public static int getID(String path, int defaultID) {
        return config.get("IDs", path, defaultID).getInt();
    }

    public static int getID(String path, int defaultID, String comment) {
        return config.get("IDs", path, defaultID, comment).getInt();
    }
}
