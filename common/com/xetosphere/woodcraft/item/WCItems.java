package com.xetosphere.woodcraft.item;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

import com.xetosphere.woodcraft.lib.ItemIDs;

import cpw.mods.fml.common.registry.GameRegistry;

public class WCItems {

	public static Item stick;
	public static Item plank;

	public static void init() {

		stick = new ItemStick(ItemIDs.STICK);
		plank = new ItemPlanks(ItemIDs.PLANK);

		getRidOfRecipes();

		addRecipes();
	}

	private static void addRecipes() {

		GameRegistry.addRecipe(new ItemStack(Item.stick, 4), new Object[] { "x", "x", Character.valueOf('x'), new ItemStack(Block.planks, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(WCItems.stick, 4, 0), new Object[] { "x", "x", Character.valueOf('x'), new ItemStack(Block.planks, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(WCItems.stick, 4, 1), new Object[] { "x", "x", Character.valueOf('x'), new ItemStack(Block.planks, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(WCItems.stick, 4, 2), new Object[] { "x", "x", Character.valueOf('x'), new ItemStack(Block.planks, 1, 3) });
		
		GameRegistry.addRecipe(new ItemStack(Item.stick, 6), new Object[] { "  x", " x ", "x  ", Character.valueOf('x'), new ItemStack(WCItems.plank, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(WCItems.stick, 6, 0), new Object[] { "  x", " x ", "x  ", Character.valueOf('x'), new ItemStack(WCItems.plank, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(WCItems.stick, 6, 1), new Object[] { "  x", " x ", "x  ", Character.valueOf('x'), new ItemStack(WCItems.plank, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(WCItems.stick, 6, 2), new Object[] { "  x", " x ", "x  ", Character.valueOf('x'), new ItemStack(WCItems.plank, 1, 3) });

		for (int i = 0; i < ItemStick.STICK_NAMES.length; i++) {
			GameRegistry.addRecipe(new ItemStack(Item.swordWood), new Object[] { "x", "x", "s", Character.valueOf('x'), Block.planks, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Item.pickaxeWood), new Object[] { "xxx", " s ", " s ", Character.valueOf('x'), Block.planks, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Item.axeWood), new Object[] { "xx", "sx", "s ", Character.valueOf('x'), Block.planks, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Item.shovelWood), new Object[] { "x", "s", "s", Character.valueOf('x'), Block.planks, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Item.hoeWood), new Object[] { "xx", "s ", "s ", Character.valueOf('x'), Block.planks, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });

			GameRegistry.addRecipe(new ItemStack(Item.swordStone), new Object[] { "x", "x", "s", Character.valueOf('x'), Block.cobblestone, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Item.pickaxeStone), new Object[] { "xxx", " s ", " s ", Character.valueOf('x'), Block.cobblestone, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Item.axeStone), new Object[] { "xx", "xs", " s", Character.valueOf('x'), Block.cobblestone, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Item.shovelStone), new Object[] { "x", "s", "s", Character.valueOf('x'), Block.cobblestone, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Item.hoeStone), new Object[] { "xx", " s", " s", Character.valueOf('x'), Block.cobblestone, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });

			GameRegistry.addRecipe(new ItemStack(Item.swordIron), new Object[] { "x", "x", "s", Character.valueOf('x'), Item.ingotIron, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Item.pickaxeIron), new Object[] { "xxx", " s ", " s ", Character.valueOf('x'), Item.ingotIron, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Item.axeIron), new Object[] { "xx", "xs", " s", Character.valueOf('x'), Item.ingotIron, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Item.shovelIron), new Object[] { "x", "s", "s", Character.valueOf('x'), Item.ingotIron, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Item.hoeIron), new Object[] { "xx", " s", " s", Character.valueOf('x'), Item.ingotIron, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });

			GameRegistry.addRecipe(new ItemStack(Item.swordGold), new Object[] { "x", "x", "s", Character.valueOf('x'), Item.ingotIron, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Item.pickaxeGold), new Object[] { "xxx", " s ", " s ", Character.valueOf('x'), Item.ingotIron, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Item.axeGold), new Object[] { "xx", "xs", " s", Character.valueOf('x'), Item.ingotIron, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Item.shovelGold), new Object[] { "x", "s", "s", Character.valueOf('x'), Item.ingotIron, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Item.hoeGold), new Object[] { "xx", " s", " s", Character.valueOf('x'), Item.ingotIron, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });

			GameRegistry.addRecipe(new ItemStack(Item.swordDiamond), new Object[] { "x", "x", "s", Character.valueOf('x'), Item.ingotIron, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Item.pickaxeDiamond), new Object[] { "xxx", " s ", " s ", Character.valueOf('x'), Item.ingotIron, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Item.axeDiamond), new Object[] { "xx", "xs", " s", Character.valueOf('x'), Item.ingotIron, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Item.shovelDiamond), new Object[] { "x", "s", "s", Character.valueOf('x'), Item.ingotIron, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
			GameRegistry.addRecipe(new ItemStack(Item.hoeDiamond), new Object[] { "xx", " s", " s", Character.valueOf('x'), Item.ingotIron, Character.valueOf('s'), new ItemStack(WCItems.stick, 1, i) });
		}
	}

	private static void getRidOfRecipes() {

		removeRecipes(new ItemStack(Item.stick));
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
