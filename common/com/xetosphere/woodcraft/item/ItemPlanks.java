package com.xetosphere.woodcraft.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;

import com.xetosphere.woodcraft.lib.Reference;
import com.xetosphere.woodcraft.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPlanks extends ItemWC {

	public static final String[] PLANK_NAMES = new String[] { "Oak", "Spruce", "Birch", "Jungle" };

	@SideOnly(Side.CLIENT)
	private Icon[] plankIcons;

	public ItemPlanks(int id) {

		super(id);
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	public String getUnlocalizedName(ItemStack itemStack) {

		StringBuilder unlocalizedName = new StringBuilder();
		int meta = MathHelper.clamp_int(itemStack.getItemDamage(), 0, PLANK_NAMES.length);

		unlocalizedName.append("item.");
		unlocalizedName.append(Strings.RESOURCE_PREFIX);
		unlocalizedName.append(Strings.PLANK_NAME);
		unlocalizedName.append(PLANK_NAMES[meta]);

		return unlocalizedName.toString();
	}

	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int meta) {

		int i = MathHelper.clamp_int(meta, 0, PLANK_NAMES.length);
		return this.plankIcons[i];
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		plankIcons = new Icon[PLANK_NAMES.length];

		for (int i = 0; i < PLANK_NAMES.length; ++i) {
			plankIcons[i] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + Strings.PLANK_NAME + PLANK_NAMES[i]);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs tab, List list) {

		for (int i = 0; i < PLANK_NAMES.length; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}
}
