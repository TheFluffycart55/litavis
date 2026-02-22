package net.thefluffycart.litavis.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import net.thefluffycart.litavis.block.entity.LitavisBrushableBlockEntity;
import org.jetbrains.annotations.Nullable;

public class SuspiciousRedSandBlock extends BrushableBlock
{
    public SuspiciousRedSandBlock(Block baseBlock, Settings settings, SoundEvent brushingSound, SoundEvent brushingCompleteSound)
    {
        super(baseBlock, brushingCompleteSound, brushingSound, settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state)
    {
        return new LitavisBrushableBlockEntity(pos, state);
    }
}