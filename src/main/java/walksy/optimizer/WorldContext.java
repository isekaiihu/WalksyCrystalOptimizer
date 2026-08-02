package walksy.optimizer;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class WorldContext {

    public static boolean isObsidianOrBedrock(Level world, BlockPos pos) {
        Block block = world.getBlockState(pos).getBlock();
        return block == Blocks.OBSIDIAN || block == Blocks.BEDROCK;
    }

    public static boolean canPlaceCrystal(Level world, BlockPos block) {
        if (!isObsidianOrBedrock(world, block)) {
            return false;
        }
        BlockPos up = block.above();
        if (!world.isEmptyBlock(up)) {
            return false;
        }
        AABB box = new AABB(up.getX(), up.getY(), up.getZ(), up.getX() + 1.0, up.getY() + 2.0, up.getZ() + 1.0);
        List<Entity> entities = world.getEntities((Entity) null, box, entity -> true);
        return entities.isEmpty();
    }

    public static boolean isBlock(Level world, BlockPos pos, Block... blocks) {
        BlockState state = world.getBlockState(pos);
        for (Block block : blocks) {
            if (state.getBlock() == block) {
                return true;
            }
        }
        return false;
    }
}
