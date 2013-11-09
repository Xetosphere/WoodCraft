package com.xetosphere.woodcraft.recipe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import com.xetosphere.woodcraft.item.WCItems;

public class WoodCutterRecipes {

	private static final WoodCutterRecipes cutterBase = new WoodCutterRecipes();

	@SuppressWarnings("rawtypes")
	private Map cuttingList = new HashMap();
	private HashMap<List<Integer>, ItemStack> metaCuttingList = new HashMap<List<Integer>, ItemStack>();

	public static final WoodCutterRecipes crushing() {

		return cutterBase;
	}

	private WoodCutterRecipes() {
		
		addCutting(Block.wood.blockID, 0, new ItemStack(WCItems.plank, 16, 0));
		addCutting(Block.wood.blockID, 1, new ItemStack(WCItems.plank, 16, 1));
		addCutting(Block.wood.blockID, 2, new ItemStack(WCItems.plank, 16, 2));
		addCutting(Block.wood.blockID, 3, new ItemStack(WCItems.plank, 16, 3));
	}

	@SuppressWarnings("unchecked")
	public void addCutting(int par1, ItemStack par2ItemStack) {

		this.cuttingList.put(Integer.valueOf(par1), par2ItemStack);
	}

	@Deprecated
	public ItemStack getCuttingResult(int par1) {

		return (ItemStack) this.cuttingList.get(Integer.valueOf(par1));
	}

	@SuppressWarnings("rawtypes")
	public Map getCuttingList() {

		return this.cuttingList;
	}

	public void addCutting(int itemID, int metadata, ItemStack itemstack) {

		metaCuttingList.put(Arrays.asList(itemID, metadata), itemstack);
	}

	public ItemStack getCuttingResult(ItemStack item) {

		if (item == null) {
			return null;
		}

		ItemStack ret = (ItemStack) metaCuttingList.get(Arrays.asList(item.itemID, item.getItemDamage()));

		if (ret != null) {
			return ret;
		}

		return (ItemStack) cuttingList.get(Integer.valueOf(item.itemID));
	}

	public Map<List<Integer>, ItemStack> getMetaCuttingList() {

		return metaCuttingList;
	}

}
