package com.xetosphere.woodcraft.core.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.xetosphere.woodcraft.client.gui.inventory.GuiWoodCutter;
import com.xetosphere.woodcraft.inventory.ContainerWoodCutter;
import com.xetosphere.woodcraft.lib.GuiIDs;
import com.xetosphere.woodcraft.lib.Strings;
import com.xetosphere.woodcraft.tileentity.TileWoodCutter;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy implements IGuiHandler {

	public void registerTileEntities() {

		GameRegistry.registerTileEntity(TileWoodCutter.class, Strings.WOODCUTTER_NAME);
	}

	public void handleTileEntityPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName) {

	}

	public void handleTileWithItemPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName, int itemID, int metaData, int stackSize, int color) {

	}

	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		if (ID == GuiIDs.WOODCUTTER) {

			TileWoodCutter tileCutter = (TileWoodCutter) world.getBlockTileEntity(x, y, z);
			return new ContainerWoodCutter(player.inventory, tileCutter);
		}

		return null;
	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		if (ID == GuiIDs.WOODCUTTER) {

			TileWoodCutter cutter = (TileWoodCutter) world.getBlockTileEntity(x, y, z);
			return new GuiWoodCutter(player.inventory, cutter);
		}

		return null;
	}

}
