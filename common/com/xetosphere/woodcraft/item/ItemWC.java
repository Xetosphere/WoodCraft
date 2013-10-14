package com.xetosphere.woodcraft.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.xetosphere.woodcraft.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWC extends Item {

	public ItemWC(int id) {

		super(id - Reference.SHIFTED_ID_RANGE_CORRECTION);
		setCreativeTab(CreativeTabs.tabMaterials);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		itemIcon = iconRegister.registerIcon(getUnlocalizedName().substring(this.getUnlocalizedName().indexOf('.') + 1));
	}

}
