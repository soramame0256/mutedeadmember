package com.github.soramame0256.mutedeadmember;

import com.github.soramame0256.mutedeadmember.commands.FeatureToggleCommand;
import com.github.soramame0256.mutedeadmember.commands.MasterToggleCommand;
import com.github.soramame0256.mutedeadmember.listener.EventListener;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = MuteDeadMember.MOD_ID,
        name = MuteDeadMember.MOD_NAME,
        version = MuteDeadMember.VERSION
)
public class MuteDeadMember {

    public static final String MOD_ID = "mutedeadmember";
    public static final String MOD_NAME = "MuteDeadMember";
    public static final String VERSION = "1.0-SNAPSHOT";
    public static final String MOD_PREFIX = "§e[MuteDeadMember]";
    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static MuteDeadMember INSTANCE;
    public static boolean isEnabled = true;
    public static boolean isFeatureEnabled = false;
    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        INSTANCE = this;
        MinecraftForge.EVENT_BUS.register(new EventListener());
        ClientCommandHandler.instance.registerCommand(new FeatureToggleCommand());
        ClientCommandHandler.instance.registerCommand(new MasterToggleCommand());
    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }
    public static String getUnformattedText(String text){
        return text.replace("§a","")
                .replace("§b","")
                .replace("§c","")
                .replace("§d","")
                .replace("§e","")
                .replace("§f","")
                .replace("§o","")
                .replace("§m","")
                .replace("§n","")
                .replace("§r","")
                .replace("§l","")
                .replace("§k","")
                .replace("§0","")
                .replace("§1","")
                .replace("§2","")
                .replace("§3","")
                .replace("§4","")
                .replace("§5","")
                .replace("§6","")
                .replace("§7","")
                .replace("§8","")
                .replace("§9","");
    }
}
