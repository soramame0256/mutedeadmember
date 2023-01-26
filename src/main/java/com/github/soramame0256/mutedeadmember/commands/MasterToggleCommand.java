package com.github.soramame0256.mutedeadmember.commands;

import com.github.trcdeveloppers.TRCLib.util.JsonUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.List;

import static com.github.soramame0256.mutedeadmember.MuteDeadMember.*;

public class MasterToggleCommand extends CommandBase {

    @Override
    public String getName() {
        return "mutedeadmastertoggle";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
    @Override
    public List<String> getAliases() {
        return new ArrayList<String>() {
            {
                add("mdmtoggle");
                add("mdmt");
            }
        };
    }
    @Override
    public String getUsage(ICommandSender sender) {
        return "/mdmtoggle";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        sender.sendMessage(new TextComponentString(MOD_PREFIX + " §bMod handler toggled to " + (isEnabled = !isEnabled)));
    }
}
