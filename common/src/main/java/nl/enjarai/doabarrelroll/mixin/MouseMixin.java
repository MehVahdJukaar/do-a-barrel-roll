package nl.enjarai.doabarrelroll.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.player.LocalPlayer;
import nl.enjarai.doabarrelroll.DoABarrelRollClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MouseHandler.class)
public abstract class MouseMixin {

    // redirect mouse handling to our own code
    @WrapWithCondition(
            method = "updateMouse",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/network/ClientPlayerEntity;changeLookDirection(DD)V"
            )
    )
    private boolean doABarrelRoll$changeLookDirection(LocalPlayer player, double cursorDeltaX, double cursorDeltaY) {
        return DoABarrelRollClient.updateMouse(player, cursorDeltaX, cursorDeltaY);
    }
}
