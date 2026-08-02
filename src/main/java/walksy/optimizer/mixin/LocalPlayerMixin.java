package walksy.optimizer.mixin;

import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import walksy.optimizer.WalksyCrystalOptimizer;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/AbstractClientPlayer;tick()V"), method = "tick()V")
    private void tick(CallbackInfo ci) {
        WalksyCrystalOptimizer.getOptimizer().tick();
    }
}
