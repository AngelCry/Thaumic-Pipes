package me.jezza.thaumicpipes;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import me.jezza.oc.client.CreativeTabSimple;
import me.jezza.thaumicpipes.common.ModBlocks;
import me.jezza.thaumicpipes.common.ModItems;
import me.jezza.thaumicpipes.common.core.command.CommandAirBlock;
import me.jezza.thaumicpipes.common.core.command.CommandAreaRemove;
import me.jezza.thaumicpipes.common.core.command.CommandAreaScan;
import me.jezza.thaumicpipes.common.core.config.ConfigHandler;
import me.jezza.thaumicpipes.common.multipart.MultiPartFactory;
import me.jezza.thaumicpipes.common.research.ModRecipes;
import me.jezza.thaumicpipes.common.research.ModResearch;
import net.minecraft.creativetab.CreativeTabs;
import thaumcraft.common.Thaumcraft;

import static me.jezza.thaumicpipes.common.lib.CoreProperties.*;

@Mod(modid = MOD_ID, name = MOD_NAME, dependencies = DEPENDENCIES)
public class ThaumicPipes {

    @Instance(MOD_ID)
    public static ThaumicPipes instance;

    public static CreativeTabs creativeTab = new CreativeTabSimple(MOD_ID, ModItems.thaumicPipe);

    @SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = SERVER_PROXY_CLASS)
    public static CommonProxy proxy;
    public static thaumcraft.common.CommonProxy tcProxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        tcProxy = Thaumcraft.proxy;
        ConfigHandler.init(event.getSuggestedConfigurationFile());

        ModBlocks.init();
        ModItems.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        new MultiPartFactory().init();
        proxy.initServerSide();
        proxy.initClientSide();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ModRecipes.init();
        ModResearch.init();
    }

    @EventHandler
    public void initServer(FMLServerStartingEvent event) {
        if (COMMANDS) {
            new CommandAirBlock("delete", "<dimID> <x> <y> <z>").register();
            new CommandAreaRemove("removeArea", "<dimID> <x1> <y1> <z1> <x2> <y2> <z2>").register();
            new CommandAreaScan("removeBlock", "<dimID> <x1> <y1> <z1> <x2> <y2> <z2> <id> <meta>").register();
        }
    }
}
