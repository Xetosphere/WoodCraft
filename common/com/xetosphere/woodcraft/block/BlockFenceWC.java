package com.xetosphere.woodcraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

public class BlockFenceWC extends BlockFence {

	public BlockFenceWC(int id, String plankTexture, Material material) {

		super(id, plankTexture, material);
		setHardness(2.0F);
		setResistance(5.0F);
	}

	public boolean canConnectFenceTo(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {

		int l = par1IBlockAccess.getBlockId(par2, par3, par4);

		if (l != this.blockID && l != Block.fenceGate.blockID && l != WCBlocks.fenceGateSpruce.blockID && l != WCBlocks.fenceGateBirch.blockID && l != WCBlocks.fenceGateJungle.blockID) {
			Block block = Block.blocksList[l];
			return block != null && block.blockMaterial.isOpaque() && block.renderAsNormalBlock() ? block.blockMaterial != Material.pumpkin : false;
		} else {
			return true;
		}
	}

}
