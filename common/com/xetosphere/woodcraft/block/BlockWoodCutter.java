package com.xetosphere.woodcraft.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.xetosphere.woodcraft.WoodCraft;
import com.xetosphere.woodcraft.lib.GuiIDs;
import com.xetosphere.woodcraft.lib.Strings;
import com.xetosphere.woodcraft.tileentity.TileWoodCutter;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWoodCutter extends BlockWC {

	@SideOnly(Side.CLIENT)
	private Icon iconTop;

	@SideOnly(Side.CLIENT)
	private Icon iconBottom;

	private Random random = new Random();

	public BlockWoodCutter(int id) {

		super(id, Material.ground);
		this.setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.WOODCUTTER_NAME);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setHardness(1F);
		this.setResistance(12F);
	}

	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {

		return side == 1 ? this.iconTop : side == 0 ? this.iconBottom : blockIcon;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		this.blockIcon = iconRegister.registerIcon(Strings.RESOURCE_PREFIX + Strings.WOODCUTTER_NAME + "_side");
		this.iconTop = iconRegister.registerIcon(Strings.RESOURCE_PREFIX + Strings.WOODCUTTER_NAME + "_top");
		this.iconBottom = iconRegister.registerIcon(Strings.RESOURCE_PREFIX + Strings.WOODCUTTER_NAME + "_bottom");
	}

	public String getUnlocalizedName() {

		StringBuilder unlocalizedName = new StringBuilder();

		unlocalizedName.append("tile.");
		unlocalizedName.append(Strings.RESOURCE_PREFIX);
		unlocalizedName.append(Strings.WOODCUTTER_NAME);

		return unlocalizedName.toString();
	}

	public TileEntity createNewTileEntity(World world) {

		return new TileWoodCutter();
	}

	public void breakBlock(World world, int x, int y, int z, int id, int meta) {

		dropInventory(world, x, y, z);
		super.breakBlock(world, x, y, z, id, meta);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

		if (player.isSneaking()) {
			return false;
		} else {
			if (!world.isRemote) {

				TileWoodCutter tileWoodCutter = (TileWoodCutter) world.getBlockTileEntity(x, y, z);

				if (tileWoodCutter != null) {
					player.openGui(WoodCraft.instance, GuiIDs.WOODCUTTER, world, x, y, z);
				}
			}
			return true;
		}
	}

	private void dropInventory(World world, int x, int y, int z) {

		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

		if (!(tileEntity instanceof IInventory)) {
			return;
		}

		IInventory inventory = (IInventory) tileEntity;

		for (int i = 0; i < inventory.getSizeInventory(); i++) {

			ItemStack itemStack = inventory.getStackInSlot(i);

			if (itemStack != null && itemStack.stackSize > 0) {

				float dX = random.nextFloat() * 0.8F + 0.1F;
				float dY = random.nextFloat() * 0.8F + 0.1F;
				float dZ = random.nextFloat() * 0.8F + 0.1F;

				EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, new ItemStack(itemStack.itemID, itemStack.stackSize, itemStack.getItemDamage()));

				if (itemStack.hasTagCompound()) {
					entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
				}

				float factor = 0.05F;
				entityItem.motionX = random.nextGaussian() * factor;
				entityItem.motionY = random.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = random.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				itemStack.stackSize = 0;
			}
		}
	}

}
