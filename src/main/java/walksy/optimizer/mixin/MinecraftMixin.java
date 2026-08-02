package walksy.optimizer.mixin;

import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import walksy.optimizer.WalksyCrystalOptimizer;

@Mixin(net.minecraft.client.Minecraft.class)
public class MinecraftMixin {

    @Redirect(method = "startUseItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;useItemOn(Lnet/minecraft/client/player/LocalPlayer;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/BlockHitResult;)Lnet/minecraft/world/InteractionResult;"))
    private InteractionResult redirectUseItemOn(MultiPlayerGameMode gameMode, LocalPlayer player, InteractionHand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.getItem() == Items.END_CRYSTAL && WalksyCrystalOptimizer.getOptimizer().stopItemUse(itemStack)) {
            // Our own tick loop (Optimizer#processCrystalPlacement) is already sending the
            // interaction packet for this click; suppress vanilla's own call so it doesn't
            // duplicate it or fall through to the generic-use tail of startUseItem().
            return InteractionResult.FAIL;
        }
        return gameMode.useItemOn(player, hand, hit);
    }
}
