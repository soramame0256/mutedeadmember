package com.github.soramame0256.mutedeadmember.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.List;

public class DebugCommand extends CommandBase {
    @Override
    public String getName() {
        return "mutedeaddebug";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public List<String> getAliases() {
        return new ArrayList<String>(){
            {
                add("mdd");
            }
        };
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/mutedeaddebug";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        int size = args.length;
        if(size == 1){
            if(args[0].equals("serverbrand")){
                sender.sendMessage(new TextComponentString(Minecraft.getMinecraft().player.getServerBrand()));
            }
        }
    }
}
