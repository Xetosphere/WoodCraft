package com.xetosphere.woodcraft.core.proxy;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import com.xetosphere.woodcraft.tileentity.TileWC;

import cpw.mods.fml.client.FMLClientHandler;

public class ClientProxy extends CommonProxy {

	public void registerTileEntities() {

		super.registerTileEntities();
	}

	public void handleTileEntityPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName) {

		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getBlockTileEntity(x, y, z);

		if (tileEntity != null) {
			if (tileEntity instanceof TileWC) {
				((TileWC) tileEntity).setOrientation(orientation);
				((TileWC) tileEntity).setState(state);
				((TileWC) tileEntity).setCustomName(customName);
			}
		}
	}

	public void handleTileWithItemPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName, int itemID, int metaData, int stackSize, int color) {

		this.handleTileEntityPacket(x, y, z, orientation, state, customName);
	}
}
