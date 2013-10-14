package com.xetosphere.woodcraft;

import java.io.File;

import com.xetosphere.woodcraft.block.WCBlocks;
import com.xetosphere.woodcraft.configuration.ConfigurationHandler;
import com.xetosphere.woodcraft.core.proxy.CommonProxy;
import com.xetosphere.woodcraft.item.WCItems;
import com.xetosphere.woodcraft.lib.Reference;
import com.xetosphere.woodcraft.network.PacketHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(channels = { Reference.CHANNEL_NAME }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class WoodCraft {

	@Instance(Reference.MOD_ID)
	public static WoodCraft instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {

		ConfigurationHandler.init(new File(e.getModConfigurationDirectory().getAbsolutePath() + File.separator + Reference.MOD_ID + File.separator + Reference.MOD_ID + ".cfg"));

		WCItems.init();

		WCBlocks.init();
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {

		// Register the GUI Handler.
		NetworkRegistry.instance().registerGuiHandler(instance, proxy);

		// Initialize modification tile entities.
		proxy.registerTileEntities();

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {

	}
}
