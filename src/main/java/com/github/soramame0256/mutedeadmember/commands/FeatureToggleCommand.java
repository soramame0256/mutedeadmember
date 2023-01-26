package com.github.soramame0256.mutedeadmember.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.List;

import static com.github.soramame0256.mutedeadmember.MuteDeadMember.*;

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
        if(isEnabled) {
            sender.sendMessage(new TextComponentString(MOD_PREFIX + " §bMod handler automatically disabled. it can be enabled by /mdmtoggle"));
            isEnabled = false;
        }
        sender.sendMessage(new TextComponentString(MOD_PREFIX + " §bManually toggled to " + (isFeatureEnabled = !isFeatureEnabled)));
    }
}
