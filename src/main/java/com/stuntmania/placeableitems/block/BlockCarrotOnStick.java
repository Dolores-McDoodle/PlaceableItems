package com.stuntmania.placeableitems.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.tileentity.TECarrotOnStick;

public class BlockCarrotOnStick extends BlockPlaceableItems {
	
	public BlockCarrotOnStick() {
		super(Material.wood);
		this.setBlockBounds(0F, 0F, 0F, 1F, 0.15F, 1F);
		this.setBlockTextureName(PlaceableItems.MODID + ":destroy/carrot_on_stick");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TECarrotOnStick();
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Items.carrot_on_a_stick;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(Items.carrot_on_a_stick);
	}
}
