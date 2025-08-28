package com.amenscraft.noftbteams.mixin;

import com.mojang.brigadier.ParseResults;
import com.amenscraft.noftbteams.NoFTBTeamsMod;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Commands.class)
public class CommandsMixin {

    @Inject(method = "performCommand", at = @At("HEAD"), cancellable = true)
    private void noftbteams$blockFTBTeams(ParseResults<CommandSourceStack> parse, String input, CallbackInfoReturnable<Integer> cir) {
        if (input == null) return;
        String cmd = input.trim().toLowerCase();
        if (!(cmd.equals("ftbteams") || cmd.startsWith("ftbteams "))) return;

        CommandSourceStack source = parse.getContext().getSource();
        if (source.getEntity() instanceof ServerPlayer player) {
            boolean allowed = Permissions.check(player, NoFTBTeamsMod.BYPASS_NODE, 2);
            if (!allowed) {
                player.sendSystemMessage(Component.literal("Команды FTB Teams отключены на этом сервере."));
                cir.setReturnValue(1);
                cir.cancel();
            }
        }
    }
}