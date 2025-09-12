package practice.staetplugin;

import commands.statecommand;
import commands.villager;
import events.stat_inventory_event;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;
import net.md_5.bungee.api.chat.TextComponent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class Staetplugin extends JavaPlugin implements Listener, CommandExecutor {
    public static int speed_check = 0;
    public static int stat_point = 0;
    public static boolean speed_check_toOther = false;
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new stat_inventory_event(), this);
        getCommand("stat").setExecutor(new statecommand.state());
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
