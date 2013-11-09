package com.xetosphere.woodcraft.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.xetosphere.woodcraft.lib.Strings;
import com.xetosphere.woodcraft.recipe.WoodCutterRecipes;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileWoodCutter extends TileWC implements IInventory {

	private ItemStack[] inventory;

	public static final int INVENTORY_SIZE = 3;

	public static final int INPUT_INVENTORY_INDEX = 0;
	public static final int FUEL_INVENTORY_INDEX = 1;
	public static final int OUTPUT_INVENTORY_INDEX = 2;

	public int cutterCuttingTime;
	public int currentItemCutTime;
	public int cutterCuttedTime2;

	public TileWoodCutter() {

		inventory = new ItemStack[INVENTORY_SIZE];
	}

	public int getSizeInventory() {

		return inventory.length;
	}

	public ItemStack getStackInSlot(int slot) {

		return inventory[slot];
	}

	public ItemStack decrStackSize(int slot, int ammount) {

		ItemStack itemStack = getStackInSlot(slot);
		if (itemStack != null) {
			if (itemStack.stackSize <= ammount) {
				setInventorySlotContents(slot, null);
			} else {
				itemStack = itemStack.splitStack(ammount);
				if (itemStack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}
			}
		}

		return itemStack;
	}

	public ItemStack getStackInSlotOnClosing(int slot) {

		ItemStack itemStack = getStackInSlot(slot);
		if (itemStack != null) {
			setInventorySlotContents(slot, null);
		}

		return itemStack;
	}

	public void setInventorySlotContents(int slot, ItemStack itemStack) {

		inventory[slot] = itemStack;
		if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
			itemStack.stackSize = getInventoryStackLimit();
		}
	}

	public String getInvName() {

		return this.hasCustomName() ? this.getCustomName() : Strings.CONTAINER_WOODCUTTER_NAME;
	}

	public int getInventoryStackLimit() {

		return 64;
	}

	public void openChest() {

	}

	public void closeChest() {

	}

	public void readFromNBT(NBTTagCompound nbtTagCompound) {

		super.readFromNBT(nbtTagCompound);

		NBTTagList tagList = nbtTagCompound.getTagList("Items");
		inventory = new ItemStack[this.getSizeInventory()];
		for (int i = 0; i < tagList.tagCount(); ++i) {
			NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
			byte slot = tagCompound.getByte("Slot");
			if (slot >= 0 && slot < inventory.length) {
				inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
			}

			this.cutterCuttingTime = nbtTagCompound.getShort("CrushTime");
			this.cutterCuttedTime2 = nbtTagCompound.getShort("TimeSpent");
		}
	}

	public void writeToNBT(NBTTagCompound nbtTagCompound) {

		super.writeToNBT(nbtTagCompound);

		nbtTagCompound.setShort("CrushTime", (short) this.cutterCuttingTime);
		nbtTagCompound.setShort("TimeSpent", (short) this.cutterCuttedTime2);

		NBTTagList tagList = new NBTTagList();
		for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex) {
			if (inventory[currentIndex] != null) {
				NBTTagCompound tagCompound = new NBTTagCompound();
				tagCompound.setByte("Slot", (byte) currentIndex);
				inventory[currentIndex].writeToNBT(tagCompound);
				tagList.appendTag(tagCompound);
			}
		}

		nbtTagCompound.setTag("Items", tagList);
	}

	public boolean isInvNameLocalized() {

		return this.hasCustomName();
	}

	public boolean isItemValidForSlot(int i, ItemStack itemStack) {

		return true;
	}

	public String toString() {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(super.toString());

		stringBuilder.append("TileAuraCrusher Data - ");
		for (int i = 0; i < inventory.length; i++) {
			if (i != 0) {
				stringBuilder.append(", ");
			}

			if (inventory[i] != null) {
				stringBuilder.append(String.format("inventory[%d]: %s", i, inventory[i].toString()));
			} else {
				stringBuilder.append(String.format("inventory[%d]: empty", i));
			}
		}

		stringBuilder.append("\n");

		return stringBuilder.toString();
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressTimeScaled(int par1) {

		return this.cutterCuttedTime2 * par1 / 200;
	}

	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int par1) {

		if (this.currentItemCutTime == 0) {
			this.currentItemCutTime = 200;
		}

		return this.cutterCuttingTime * par1 / this.currentItemCutTime;
	}

	public boolean isBurning() {

		return this.cutterCuttingTime > 0;
	}

	public void updateEntity() {

		boolean flag = this.cutterCuttingTime > 0;
		boolean flag1 = false;

		if (this.cutterCuttingTime > 0) {
			--this.cutterCuttingTime;
		}

		if (!this.worldObj.isRemote) {
			if (this.cutterCuttingTime == 0 && this.canSmelt()) {
				this.currentItemCutTime = this.cutterCuttingTime = getItemBurnTime(this.inventory[FUEL_INVENTORY_INDEX]);
				if (this.cutterCuttingTime > 0) {
					flag1 = true;
					if (this.inventory[FUEL_INVENTORY_INDEX] != null) {
						--this.inventory[FUEL_INVENTORY_INDEX].stackSize;
						if (this.inventory[FUEL_INVENTORY_INDEX].stackSize == 0) {
							this.inventory[FUEL_INVENTORY_INDEX] = this.inventory[FUEL_INVENTORY_INDEX].getItem().getContainerItemStack(inventory[FUEL_INVENTORY_INDEX]);
						}
					}
				}
			}

			if (this.isBurning() && this.canSmelt()) {
				++this.cutterCuttedTime2;

				if (this.cutterCuttedTime2 == 200) {
					this.cutterCuttedTime2 = 0;
					this.smeltItem();
					flag1 = true;
				}

			} else {

				this.cutterCuttedTime2 = 0;

			}

			if (flag != this.cutterCuttingTime > 0) {
				flag1 = true;
			}

		}

		if (flag1) {
			this.onInventoryChanged();
		}
	}

	private boolean canSmelt() {

		if (this.inventory[INPUT_INVENTORY_INDEX] == null) {

			return false;

		} else {

			ItemStack itemstack = WoodCutterRecipes.crushing().getCuttingResult(this.inventory[INPUT_INVENTORY_INDEX]);

			if (itemstack == null) return false;
			if (this.inventory[OUTPUT_INVENTORY_INDEX] == null) return true;
			if (!this.inventory[OUTPUT_INVENTORY_INDEX].isItemEqual(itemstack)) return false;
			int result = inventory[OUTPUT_INVENTORY_INDEX].stackSize + itemstack.stackSize;

			return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
		}
	}

	public void smeltItem() {

		if (this.canSmelt()) {

			ItemStack itemstack = WoodCutterRecipes.crushing().getCuttingResult(this.inventory[INPUT_INVENTORY_INDEX]);

			if (this.inventory[OUTPUT_INVENTORY_INDEX] == null) {

				this.inventory[OUTPUT_INVENTORY_INDEX] = itemstack.copy();

			} else if (this.inventory[OUTPUT_INVENTORY_INDEX].isItemEqual(itemstack)) {

				inventory[OUTPUT_INVENTORY_INDEX].stackSize += itemstack.stackSize;

			}

			--this.inventory[INPUT_INVENTORY_INDEX].stackSize;

			if (this.inventory[INPUT_INVENTORY_INDEX].stackSize <= 0) {
				this.inventory[INPUT_INVENTORY_INDEX] = null;
			}
		}
	}

	public static int getItemBurnTime(ItemStack itemStack) {

		if (itemStack == null) {
			return 0;

		} else {

			int i = itemStack.getItem().itemID;
			Item item = itemStack.getItem();

			if (itemStack.getItem() instanceof ItemBlock && Block.blocksList[i] != null) {
				Block block = Block.blocksList[i];

				if (block == Block.woodSingleSlab) {
					return 75;
				}

				if (block.blockMaterial == Material.wood) {
					return 150;
				}

				if (block == Block.coalBlock) {
					return 8000;
				}
			}

			if (item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("WOOD")) return 100;
			if (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().equals("WOOD")) return 100;
			if (item instanceof ItemHoe && ((ItemHoe) item).getMaterialName().equals("WOOD")) return 100;

			if (i == Item.stick.itemID) return 50;
			if (i == Item.coal.itemID) return 800;
			if (i == Item.bucketLava.itemID) return 10000;
			if (i == Block.sapling.blockID) return 50;
			if (i == Item.blazeRod.itemID) return 1200;

			return GameRegistry.getFuelValue(itemStack);
		}
	}

	public static boolean isItemFuel(ItemStack itemStack) {

		return getItemBurnTime(itemStack) > 0;
	}

}
