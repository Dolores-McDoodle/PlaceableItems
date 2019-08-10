package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.init.PlaceableItemsMap;
import me.ferdz.placeableitems.material.PlaceableItemsMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PlaceableItemsBlock extends Block {
    private static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_0_15;

    public PlaceableItemsBlock() {
        super(Block.Properties.create(PlaceableItemsMaterials.PLACEABLE_ITEMS_MATERIAL));
        this.setDefaultState(this.stateContainer.getBaseState().with(ROTATION, 0));
    }

    public PlaceableItemsBlock register(String name, Item... items) {
        for(Item i : items)
            PlaceableItemsMap.instance().put(i, this, name);
        return this;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT; // Enables transparency & non full block models
    }

    @SuppressWarnings("deprecation") // This is fine to override
    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos.offset(Direction.DOWN)).isSolid();
    }

    /// Returning 1 here will disable shadow
    @SuppressWarnings("deprecation") // This is fine to override
    @Override
    @OnlyIn(Dist.CLIENT)
    public float func_220080_a(BlockState state, IBlockReader blockReader, BlockPos pos) {
        return 1.0F;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        // Calculates the angle & maps it to the rotation state
        return this.getDefaultState().with(ROTATION, MathHelper.floor((double) (context.getPlacementYaw() * 16.0F / 360.0F) + 0.5D) & 15);
    }

    @SuppressWarnings("deprecation") // This is fine to override
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(ROTATION, rot.rotate(state.get(ROTATION), 16));
    }

    @SuppressWarnings("deprecation") // This is fine to override
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.with(ROTATION, mirrorIn.mirrorRotation(state.get(ROTATION), 16));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ROTATION);
    }
}
