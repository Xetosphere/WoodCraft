package com.xetosphere.woodcraft.configuration;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;

import com.xetosphere.woodcraft.lib.BlockIDs;
import com.xetosphere.woodcraft.lib.ItemIDs;
import com.xetosphere.woodcraft.lib.Reference;
import com.xetosphere.woodcraft.lib.Strings;

import cpw.mods.fml.common.FMLLog;

public class ConfigurationHandler {

	public static Configuration config;

	public static void init(File configFile) {

		config = new Configuration(configFile);

		try {

			config.load();

			BlockIDs.FENCE_SPRUCE = config.getBlock(Configuration.CATEGORY_BLOCK, Strings.FENCE_SPRUCE_NAME, BlockIDs.FENCE_SPRUCE_DEFAULT).getInt(BlockIDs.FENCE_SPRUCE_DEFAULT);
			BlockIDs.FENCE_BIRCH = config.getBlock(Configuration.CATEGORY_BLOCK, Strings.FENCE_BIRCH_NAME, BlockIDs.FENCE_BIRCH_DEFAULT).getInt(BlockIDs.FENCE_BIRCH_DEFAULT);
			BlockIDs.FENCE_JUNGLE = config.getBlock(Configuration.CATEGORY_BLOCK, Strings.FENCE_JUNGLE_NAME, BlockIDs.FENCE_JUNGLE_DEFAULT).getInt(BlockIDs.FENCE_JUNGLE_DEFAULT);
			BlockIDs.FENCE_GATE_SPRUCE = config.getBlock(Configuration.CATEGORY_BLOCK, Strings.FENCE_GATE_SPRUCE_NAME, BlockIDs.FENCE_GATE_SPRUCE_DEFAULT).getInt(BlockIDs.FENCE_GATE_SPRUCE_DEFAULT);
			BlockIDs.FENCE_GATE_BIRCH = config.getBlock(Configuration.CATEGORY_BLOCK, Strings.FENCE_GATE_BIRCH_NAME, BlockIDs.FENCE_GATE_BIRCH_DEFAULT).getInt(BlockIDs.FENCE_GATE_BIRCH_DEFAULT);
			BlockIDs.FENCE_GATE_JUNGLE = config.getBlock(Configuration.CATEGORY_BLOCK, Strings.FENCE_GATE_JUNGLE_NAME, BlockIDs.FENCE_GATE_JUNGLE_DEFAULT).getInt(BlockIDs.FENCE_GATE_JUNGLE_DEFAULT);
			BlockIDs.WOODCUTTER = config.getBlock(Configuration.CATEGORY_BLOCK, Strings.WOODCUTTER_NAME, BlockIDs.WOODCUTTER_DEFAULT).getInt(BlockIDs.WOODCUTTER_DEFAULT);

			ItemIDs.STICK = config.getItem(Configuration.CATEGORY_ITEM, Strings.STICK_NAME, ItemIDs.STICK_DEFAULT).getInt(ItemIDs.STICK_DEFAULT);
			ItemIDs.PLANK = config.getItem(Configuration.CATEGORY_ITEM, Strings.PLANK_NAME, ItemIDs.PLANK_DEFAULT).getInt(ItemIDs.PLANK_DEFAULT);

		} catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, Reference.MOD_ID + " had a problem loading its configuration file.");
		} finally {
			config.save();
		}
	}
}
