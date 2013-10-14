package com.xetosphere.woodcraft.block;

import net.minecraft.block.BlockFenceGate;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFenceGateWC extends BlockFenceGate {

	public static final String[] GATE_NAMES = { "Spruce", "Birch", "Jungle" };

	public BlockFenceGateWC(int id) {

		super(id);
		setHardness(2.0F);
		setResistance(5.0F);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		if (this.blockID == WCBlocks.fenceGateSpruce.blockID) {
			blockIcon = iconRegister.registerIcon("planks_spruce");
		}

		if (this.blockID == WCBlocks.fenceGateBirch.blockID) {
			blockIcon = iconRegister.registerIcon("planks_birch");
		}

		if (this.blockID == WCBlocks.fenceGateJungle.blockID) {
			blockIcon = iconRegister.registerIcon("planks_jungle");
		}
	}

	@SideOnly(Side.CLIENT)
	public Icon getIcon(int id, int meta) {

		return blockIcon;
	}

}
