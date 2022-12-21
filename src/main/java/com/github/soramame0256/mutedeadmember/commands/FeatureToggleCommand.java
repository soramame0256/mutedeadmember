package com.github.soramame0256.mutedeadmember.commands;

import com.github.soramame0256.mutedeadmember.MuteDeadMember;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.List;

import static com.github.soramame0256.mutedeadmember.MuteDeadMember.MOD_PREFIX;
import static com.github.soramame0256.mutedeadmember.MuteDeadMember.isFeatureEnabled;

public class FeatureToggleCommand extends CommandBase {

    @Override
    public String getName() {
        return "mutedeadfeaturetoggle";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public List<String> getAliases() {
        return new ArrayList<String>() {
            {
                add("mdftoggle");
                add("mdft");
            }
        };
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/mdftoggle";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        sender.sendMessage(new TextComponentString(MOD_PREFIX + " §bToggled to " + (isFeatureEnabled = !isFeatureEnabled)));
    }
}
