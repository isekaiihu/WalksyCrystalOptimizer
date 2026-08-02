package walksy.optimizer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
//? if <26.2 {
/*import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.monster.Slime;
*///?} else {
import net.minecraft.world.entity.monster.cubemob.MagmaCube;
import net.minecraft.world.entity.monster.cubemob.Slime;
//?}
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class Optimizer {
    private final Minecraft client = Minecraft.getInstance();
    private int hitCount = 0;
    private int breakingBlockTick = 0;

    public void tick() {
        if (this.client.player == null || this.client.level == null) {
            return;
        }
        this.handleBlockBreakingState();
        if (this.breakingBlockTick > 2) {
            return;
        }
        if (!client.options.keyUse.isDown()) {
            this.hitCount = 0;
        }
        if (this.hitCount >= this.getPacketLimit()) {
            return;
        }
        this.processEntityRemoval();
        this.processCrystalPlacement();
    }

    private void handleBlockBreakingState() {
        if (this.client.options.keyAttack.isDown()) {
            this.breakingBlockTick++;
        } else {
            this.breakingBlockTick = 0;
        }
    }

    private void processEntityRemoval() {
        if (!this.client.options.keyAttack.isDown()) {
            return;
        }

        Entity target = this.getValidTarget(this.client.hitResult);
        if (target != null) {
            if (this.hitCount >= 1) {
                target.setRemoved(Entity.RemovalReason.KILLED);
            }
            this.hitCount++;
        }
    }

    private void processCrystalPlacement() {
        if (!this.client.options.keyUse.isDown()) {
            return;
        }
        if (this.client.player.getMainHandItem().getItem() != Items.END_CRYSTAL) {
            return;
        }
        BlockHitResult hit = Raycast.cast(client, 4.5);
        if (hit == null) {
            return;
        }
        BlockPos pos = hit.getBlockPos();
        if (WorldContext.isObsidianOrBedrock(this.client.level, pos)) {
            this.client.gameMode.useItemOn(client.player, InteractionHand.MAIN_HAND, hit);

            if (WorldContext.canPlaceCrystal(client.level, pos)) {
                this.client.player.swing(InteractionHand.MAIN_HAND);
            }
        }
    }

    private Entity getValidTarget(HitResult hitResult) {
        if (hitResult instanceof EntityHitResult entityHit) {
            Entity e = entityHit.getEntity();
            if (e instanceof EndCrystal || e instanceof Slime || e instanceof MagmaCube) {
                return e;
            }
        }
        return null;
    }

    public boolean stopItemUse(ItemStack stack) {
        if (stack.getItem() != Items.END_CRYSTAL) {
            return false;
        }
        return this.hitCount < this.getPacketLimit();
    }

    //I don't even remember what this does...
    private int getPacketLimit() {
        int ping = this.getPing();
        return (ping < 50) ? 1 : 2;
    }

    //probably taken from meteor
    private int getPing() {
        if (client.getConnection() == null || this.client.player == null) {
            return 0;
        }
        PlayerInfo entry = client.getConnection().getPlayerInfo(client.player.getUUID());
        return entry != null ? entry.getLatency() : 0;
    }
}
