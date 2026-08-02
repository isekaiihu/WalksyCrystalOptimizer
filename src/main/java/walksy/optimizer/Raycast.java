package walksy.optimizer;

import net.minecraft.client.Minecraft;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class Raycast {

    public static BlockHitResult cast(Minecraft client, double range) {
        Vec3 start = client.player.getEyePosition();
        Vec3 look = client.player.getViewVector(1.0F);
        Vec3 end = start.add(look.scale(range));

        return client.level.clip(new ClipContext(start, end, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, client.player));
    }
}
