package angrymobs;

import angrymobs.blocks.Blocks;
import angrymobs.items.Items;
import angrymobs.lib.ModInfo;
import angrymobs.lib.Recipes;
import angrymobs.mobs.entities.Entities;
import angrymobs.proxies.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION)
@NetworkMod(channels = { ModInfo.CHANNEL }, clientSideRequired = true,
		serverSideRequired = true)
public class AngryMobsMod {
	
	@SidedProxy(clientSide = ModInfo.PROXY_LOCATION + ".ClientProxy",
			serverSide = ModInfo.PROXY_LOCATION
					+ ".CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		proxy.initRenderers();
		proxy.initSounds();
		Entities.init();
		Items.init();
		Blocks.init();
		Recipes.init();
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		
	}
}