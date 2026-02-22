package net.thefluffycart.litavis.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.util.math.BlockPos;


public class LitavisBrushableBlockEntity extends BrushableBlockEntity
{
    public LitavisBrushableBlockEntity(BlockPos pos, BlockState state)
    {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType()
    {
        return LitavisBlockEntityType.SUSPICIOUS_RED_SAND;
    }

    @Override
    public boolean supports(BlockState state)
    {
        return LitavisBlockEntityType.SUSPICIOUS_RED_SAND.supports(state);
    }
}