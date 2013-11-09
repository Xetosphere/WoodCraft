package com.xetosphere.woodcraft.block;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;

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

		for (int i = 0; i < 3; i++) {
			GameRegistry.addRecipe(new ItemStack(Block.rail, 16), new Object[] { "i i", "isi", "i i", Character.valueOf('i'), Item.ingotIron, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Block.railActivator, 6), new Object[] { "isi", "iri", "isi", Character.valueOf('i'), Item.ingotIron, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i), Character.valueOf('r'), Block.torchRedstoneActive });
			GameRegistry.addRecipe(new ItemStack(Block.railPowered, 6), new Object[] { "i i", "isi", "iri", Character.valueOf('i'), Item.ingotGold, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i), Character.valueOf('r'), Item.redstone });
			GameRegistry.addRecipe(new ItemStack(Block.torchRedstoneActive), new Object[] { "c", "s", Character.valueOf('c'), Item.redstone, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Block.lever), new Object[] { "s", "b", Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i), Character.valueOf('b'), Block.cobblestone });
			GameRegistry.addRecipe(new ItemStack(Block.torchWood, 4), new Object[] { "c", "s", Character.valueOf('c'), new ItemStack(Item.coal, 1, 0), Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Block.torchWood, 4), new Object[] { "c", "s", Character.valueOf('c'), new ItemStack(Item.coal, 1, 1), Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Block.tripWireSource, 2), new Object[] { "i", "s", "p", Character.valueOf('p'), new ItemStack(Block.planks, 1, i), Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i), Character.valueOf('i'), Item.ingotIron });
			GameRegistry.addRecipe(new ItemStack(Item.arrow, 4), new Object[] { "f", "s", "e", Character.valueOf('e'), Item.feather, Character.valueOf('f'), Item.flint, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Item.itemFrame), new Object[] { "sss", "sws", "sss", Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i), Character.valueOf('w'), Item.leather });
			GameRegistry.addRecipe(new ItemStack(Item.painting), new Object[] { "sss", "sws", "sss", Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i), Character.valueOf('w'), new ItemStack(Block.cloth, 1, OreDictionary.WILDCARD_VALUE) });
			GameRegistry.addRecipe(new ItemStack(Item.fishingRod), new Object[] { "  s", " sl", "s l", Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i), Character.valueOf('l'), Item.silk });
			GameRegistry.addRecipe(new ItemStack(Item.sign, 3), new Object[] { "www", "www", " s ", Character.valueOf('w'), new ItemStack(Block.planks, 1, i), Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Block.ladder, 3), new Object[] { "s s", "sss", "s s", Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Item.bow), new Object[] { " ws", "w s", " ws", Character.valueOf('w'), new ItemStack(WCItems.stick, 1, i), Character.valueOf('s'), Item.silk });
		}

		GameRegistry.addRecipe(new ItemStack(Block.planks, 1, 0), new Object[] { "xx", "xx", Character.valueOf('x'), new ItemStack(WCItems.plank, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(Block.planks, 1, 1), new Object[] { "xx", "xx", Character.valueOf('x'), new ItemStack(WCItems.plank, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(Block.planks, 1, 2), new Object[] { "xx", "xx", Character.valueOf('x'), new ItemStack(WCItems.plank, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(Block.planks, 1, 3), new Object[] { "xx", "xx", Character.valueOf('x'), new ItemStack(WCItems.plank, 1, 3) });

		GameRegistry.addRecipe(new ItemStack(WCBlocks.woodCutter), new Object[] { "WIW", "WRW", "WWW", Character.valueOf('W'), Block.planks, Character.valueOf('I'), Item.ingotIron, Character.valueOf('R'), Item.redstone });
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
