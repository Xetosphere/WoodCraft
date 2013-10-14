package com.xetosphere.woodcraft.block;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

import com.xetosphere.woodcraft.item.WCItems;
import com.xetosphere.woodcraft.lib.BlockIDs;
import com.xetosphere.woodcraft.lib.Strings;

import cpw.mods.fml.common.registry.GameRegistry;

public class WCBlocks {

	public static Block fenceSpruce;
	public static Block fenceBirch;
	public static Block fenceJungle;

	public static Block fenceGateSpruce;
	public static Block fenceGateBirch;
	public static Block fenceGateJungle;

	public static Block woodCutter;

	public static void init() {

		fenceSpruce = new BlockFenceWC(BlockIDs.FENCE_SPRUCE, "planks_spruce", Material.wood).setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.FENCE_SPRUCE_NAME);
		fenceBirch = new BlockFenceWC(BlockIDs.FENCE_BIRCH, "planks_birch", Material.wood).setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.FENCE_BIRCH_NAME);
		fenceJungle = new BlockFenceWC(BlockIDs.FENCE_JUNGLE, "planks_jungle", Material.wood).setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.FENCE_JUNGLE_NAME);

		fenceGateSpruce = new BlockFenceGateWC(BlockIDs.FENCE_GATE_SPRUCE).setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.FENCE_GATE_SPRUCE_NAME);
		fenceGateBirch = new BlockFenceGateWC(BlockIDs.FENCE_GATE_BIRCH).setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.FENCE_GATE_BIRCH_NAME);
		fenceGateJungle = new BlockFenceGateWC(BlockIDs.FENCE_GATE_JUNGLE).setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.FENCE_GATE_JUNGLE_NAME);

		woodCutter = new BlockWoodCutter(BlockIDs.WOODCUTTER);

		GameRegistry.registerBlock(fenceSpruce, Strings.FENCE_SPRUCE_NAME);
		GameRegistry.registerBlock(fenceBirch, Strings.FENCE_BIRCH_NAME);
		GameRegistry.registerBlock(fenceJungle, Strings.FENCE_JUNGLE_NAME);
		GameRegistry.registerBlock(fenceGateSpruce, Strings.FENCE_GATE_SPRUCE_NAME);
		GameRegistry.registerBlock(fenceGateBirch, Strings.FENCE_GATE_BIRCH_NAME);
		GameRegistry.registerBlock(fenceGateJungle, Strings.FENCE_GATE_JUNGLE_NAME);
		GameRegistry.registerBlock(woodCutter, Strings.WOODCUTTER_NAME);

		getRidOfRecipes();
		addRecipes();
	}

	private static void addRecipes() {

		GameRegistry.addRecipe(new ItemStack(WCBlocks.fenceSpruce, 2), new Object[] { "xxx", "xxx", Character.valueOf('x'), new ItemStack(WCItems.stick, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(WCBlocks.fenceBirch, 2), new Object[] { "xxx", "xxx", Character.valueOf('x'), new ItemStack(WCItems.stick, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(WCBlocks.fenceJungle, 2), new Object[] { "xxx", "xxx", Character.valueOf('x'), new ItemStack(WCItems.stick, 1, 2) });

		GameRegistry.addRecipe(new ItemStack(Block.fenceGate), new Object[] { "xyx", "xyx", Character.valueOf('x'), Item.stick, Character.valueOf('y'), new ItemStack(Block.planks, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(WCBlocks.fenceGateSpruce), new Object[] { "xyx", "xyx", Character.valueOf('x'), new ItemStack(WCItems.stick, 1, 0), Character.valueOf('y'), new ItemStack(Block.planks, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(WCBlocks.fenceGateBirch), new Object[] { "xyx", "xyx", Character.valueOf('x'), new ItemStack(WCItems.stick, 1, 1), Character.valueOf('y'), new ItemStack(Block.planks, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(WCBlocks.fenceGateJungle), new Object[] { "xyx", "xyx", Character.valueOf('x'), new ItemStack(WCItems.stick, 1, 2), Character.valueOf('y'), new ItemStack(Block.planks, 1, 3) });

		GameRegistry.addRecipe(new ItemStack(Block.planks, 1, 0), new Object[] { "xx", "xx", Character.valueOf('x'), new ItemStack(WCItems.plank, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(Block.planks, 1, 1), new Object[] { "xx", "xx", Character.valueOf('x'), new ItemStack(WCItems.plank, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(Block.planks, 1, 2), new Object[] { "xx", "xx", Character.valueOf('x'), new ItemStack(WCItems.plank, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(Block.planks, 1, 3), new Object[] { "xx", "xx", Character.valueOf('x'), new ItemStack(WCItems.plank, 1, 3) });
	}

	private static void getRidOfRecipes() {

		removeRecipes(new ItemStack(Block.fenceGate));
	}

	private static void removeRecipes(ItemStack resultItem) {

		ItemStack recipeResult = null;
		ArrayList<?> recipes = (ArrayList<?>) CraftingManager.getInstance().getRecipeList();

		for (int scan = 0; scan < recipes.size(); scan++) {

			IRecipe tmpRecipe = (IRecipe) recipes.get(scan);
			recipeResult = tmpRecipe.getRecipeOutput();

			if (recipeResult != null) {

				if (recipeResult.itemID == resultItem.itemID && recipeResult.getItemDamage() == resultItem.getItemDamage()) {
					System.out.println("Removed Recipe: " + recipes.get(scan) + " -> " + recipeResult);
					recipes.remove(scan);
					scan--;
				}
			}
		}

	}
}
