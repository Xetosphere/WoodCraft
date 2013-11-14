package com.xetosphere.woodcraft.lib;

public class Strings {

	public static final String RESOURCE_PREFIX = Reference.MOD_ID.toLowerCase() + ":";

	/* Rule related constants */
	public static final String RULE = "Keep vanilla plank crafting?";
	
	/* NBT related constants */
	public static final String NBT_TE_STATE_KEY = "teState";
	public static final String NBT_TE_CUSTOM_NAME = "CustomName";
	public static final String NBT_TE_DIRECTION_KEY = "teDirection";

	/* Item related constants */
	public static final String STICK_NAME = "stick";
	public static final String PLANK_NAME = "plank";

	/* Block related constants */
	public static final String FENCE_SPRUCE_NAME = "fenceSpruce";
	public static final String FENCE_BIRCH_NAME = "fenceBirch";
	public static final String FENCE_JUNGLE_NAME = "fenceJungle";
	public static final String FENCE_GATE_SPRUCE_NAME = "fenceGateSpruce";
	public static final String FENCE_GATE_BIRCH_NAME = "fenceGateBirch";
	public static final String FENCE_GATE_JUNGLE_NAME = "fenceGateJungle";
	public static final String WOODCUTTER_NAME = "woodCutter";

	/* Tile Entity related constants */
	public static final String TE_WOODCUTTER_NAME = "tileWoodCutter";

	/* Container related constants */
	public static final String CONTAINER_INVENTORY = "container.inventory";
	public static final String CONTAINER_WOODCUTTER_NAME = "container." + WOODCUTTER_NAME;
}
