package com.github.soramame0256.mutedeadmember.listener;

import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.soramame0256.mutedeadmember.MuteDeadMember.*;

public class EventListener {
    public static List<String> deadPlayers = new ArrayList<>();
    private final Minecraft mc = Minecraft.getMinecraft();
    private static final String GLOBAL_CHAT_REGEX = "\\[[0-9]+/[0-9]+/\\w+/.*][ \\[.*\\]]? (?<username>.*): .*";

    private static final String PARTY_CHAT_REGEX = "\\[(?<username>[a-zA-Z0-9_]+)] .*";
    private static final String DIRECT_CHAT_REGEX = "\\[(?<username>[a-zA-Z0-9_]+) ➤ [a-zA-Z0-9_]+] .*";
    private static final String SERVER_BRAND_REGEX = "(?<serverproxy>.+) <- (?<serverapplication>.+)";
    private final Pattern GLOBAL_CHAT_PATTERN;
    private final Pattern PARTY_CHAT_PATTERN;
    private final Pattern DIRECT_CHAT_PATTERN;
    private final Pattern SERVER_BRAND_PATTERN;
    public EventListener(){
        GLOBAL_CHAT_PATTERN = Pattern.compile(GLOBAL_CHAT_REGEX);
        PARTY_CHAT_PATTERN = Pattern.compile(PARTY_CHAT_REGEX);
        DIRECT_CHAT_PATTERN = Pattern.compile(DIRECT_CHAT_REGEX);
        SERVER_BRAND_PATTERN = Pattern.compile(SERVER_BRAND_REGEX);
    }
    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent e){
        if(!isFeatureEnabled || e.getType() != ChatType.CHAT) return;
        String sender = null;
        String msg = getUnformattedText(e.getMessage().getFormattedText());
        Matcher g = GLOBAL_CHAT_PATTERN.matcher(msg);
        Matcher p = PARTY_CHAT_PATTERN.matcher(msg);
        Matcher d = DIRECT_CHAT_PATTERN.matcher(msg);
        if (g.matches()){
            sender = g.group("username");
        }else if(p.matches()){
            sender = p.group("username");
        }else if(d.matches()){
            sender = d.group("username");
        }
        if(sender != null) {
            for (String nm : deadPlayers) {
                if (sender.startsWith(nm)) {
                    e.setCanceled(true);
                }
            }
        }
    }
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onTick(TickEvent.ClientTickEvent e){
        if(e.phase == TickEvent.Phase.END && isFeatureEnabled){
            ScoreObjective so;
            if(mc.player!=null && mc.world != null && (so=mc.world.getScoreboard().getObjectiveInDisplaySlot(1))!=null){
                List<String> texts = new ArrayList<>();
                AtomicBoolean inParty = new AtomicBoolean(false);
                so.getScoreboard().getScores().forEach((sc) -> {
                    String line = getUnformattedText(sc.getPlayerName());
                    texts.add(line);
                    if (line.contains("Party: [")){
                        inParty.set(true);
                    }
                }
                );
                deadPlayers.clear();
                if(!inParty.get()) return;
                for(String s : texts){
                    if(s.contains("||0||")){
                        deadPlayers.add(getPlayerNameFromSidebarText(s));
                    }
                }
            }
        }else {
            if(isEnabled && mc.player != null) {
                if (!isFeatureEnabled && getServerBrand().getV().equalsIgnoreCase("wynn")) {
                    mc.player.sendMessage(new TextComponentString(MOD_PREFIX + " §bFeature automatically enabled!"));
                    isFeatureEnabled = true;
                }
                if (isFeatureEnabled && !getServerBrand().getV().equalsIgnoreCase("wynn")) {
                    System.out.println("Feature automatically disabled.");
                    isFeatureEnabled = false;
                }
            }
        }
    }
    private Pair<String, String> getServerBrand(){
        Matcher m = SERVER_BRAND_PATTERN.matcher(mc.player.getServerBrand());
        return m.find() ? new Pair<>(m.group("serverproxy"), m.group("serverapplication")) : new Pair<>("null","null");
    }
    private String getPlayerNameFromSidebarText(String name){
        int i =-1;
        char before = '1';
        boolean isName = false;
        StringBuilder ret = new StringBuilder();
        while (name.length()>++i){
            if(!isName){
                isName = Character.isAlphabetic(name.charAt(i));
            }
            if(isName) {
                if(before == ' ' && name.charAt(i) == '['){
                    break;
                }
                ret.append(name.charAt(i));
                before = name.charAt(i);
            }
        }
        return ret.toString().trim();
    }
    public static class Pair<T,V> {
        private T t;
        private V v;
        public Pair(T t, V v){
            this.t = t;
            this.v = v;
        }
        public T getT(){
            return t;
        }
        public V getV(){
            return v;
        }
    }
}
