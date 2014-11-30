package me.jezza.thaumicpipes.common.lib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static me.jezza.oc.api.configuration.Config.*;

public class CoreProperties {

    public static final String MOD_ID = "ThaumicPipes";
    public static final String MOD_NAME = "Thaumic Pipes";

    public static final String MOD_IDENTIFIER = MOD_ID + ":";

    public static final String CLIENT_PROXY_CLASS = "me.jezza.thaumicpipes.client.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "me.jezza.thaumicpipes.CommonProxy";

    public static Logger logger = LogManager.getLogger("ThaumicPipes");

    public static final String DEPENDENCIES = "required-after:OmnisCore;required-after:Thaumcraft;after:ThaumicTinkerer";

    public static final int PIPE_ANIMATION_SIZE = 18;

    @ConfigBoolean(category = "Gameplay", comment = "They aren't Thaumcraft related, just some useful commands.")
    public static boolean COMMANDS = false;

    @ConfigInteger(category = "Gameplay", minValue = 0, maxValue = 2, comment = "0 - Simple crafting recipe inside the arcane crafting table.\n1 - Infusion recipe.\n2 - Really hard infusion recipe, as in really hard.\nIn fact, it's probably easier to write your own mod when it's on this.\nYou WILL not succeed.\n")
    public static int DIFFICULTY_LEVEL = 0;

}
