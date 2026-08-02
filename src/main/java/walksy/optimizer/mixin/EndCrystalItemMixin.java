package walksy.optimizer.mixin;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.EndCrystalItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import walksy.optimizer.WorldContext;


@Mixin({EndCrystalItem.class})
public class EndCrystalItemMixin {

    @Inject(method = {"useOn"}, at = {@At("HEAD")}, cancellable = true)
    private void modifyDecrementAmount(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack stack = context.getItemInHand();
        if (stack.getItem() == Items.END_CRYSTAL) {
            if (WorldContext.isObsidianOrBedrock(context.getLevel(), context.getClickedPos())) {
                if (WorldContext.canPlaceCrystal(context.getLevel(), context.getClickedPos())) {
                    context.getItemInHand().grow(1);
                }
            }
        }
    }
}
