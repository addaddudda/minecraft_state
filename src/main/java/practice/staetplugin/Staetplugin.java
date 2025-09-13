package practice.staetplugin;

import commands.villager;
import events.changeJob_event;
import events.stat_inventory_event;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Staetplugin extends JavaPlugin implements Listener, CommandExecutor {
    public static int speed_check = 0;
    public static int stat_point = 0;
    public static boolean speed_check_toOther = false;
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new changeJob_event(), this);
        getServer().getPluginManager().registerEvents(new stat_inventory_event(), this);
        getCommand("villager_create").setExecutor(new villager.villager_create());
        getCommand("reload_stat").setExecutor(this);
    }

    @Override
    public void onDisable() {

    }
    @EventHandler
    public void levelup(PlayerLevelChangeEvent event){
        Player p = event.getPlayer();
        int oldLvl = event.getOldLevel();
        int newLvl = event.getNewLevel();
        if(oldLvl < newLvl){
            stat_point+=3;
            p.sendMessage("§e스텟 포인트가 3만큼 올랐습니다!");
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        }
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player p = (Player)commandSender;
        AttributeInstance attack = p.getAttribute(Attribute.ATTACK_DAMAGE);
        AttributeInstance shield = p.getAttribute(Attribute.ARMOR);
        AttributeInstance speed = p.getAttribute(Attribute.MOVEMENT_SPEED);
        Staetplugin.speed_check_toOther = true;
        Staetplugin.speed_check = 0;
        attack.setBaseValue(1.0);
        shield.setBaseValue(0);
        speed.setBaseValue(0.1);

        return true;
    }
}
