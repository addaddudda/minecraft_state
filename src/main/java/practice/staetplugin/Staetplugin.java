package practice.staetplugin;

import commands.statecommand;
import commands.villager;
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
    int speed_check = 0;
    public static int stat_point = 0;
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("stat").setExecutor(new statecommand.state());
        getCommand("villager_create").setExecutor(new villager.villager_create());
        getCommand("reload_stat").setExecutor(this);
    }

    @Override
    public void onDisable() {

    }
    @EventHandler
    public void box_interacted(InventoryClickEvent ic){
        Player p = (Player) ic.getWhoClicked();
        AttributeInstance attack = p.getAttribute(Attribute.ATTACK_DAMAGE);
        AttributeInstance shield = p.getAttribute(Attribute.ARMOR);
        AttributeInstance speed = p.getAttribute(Attribute.MOVEMENT_SPEED);
        int lvl = p.getLevel();
        double increase;
        double shield_increase;
        double speed_increase = 0.00;

        if(lvl <= 3) increase = 3.0;
        else if(lvl <= 20) increase = 2.0;
        else if(lvl <= 30) increase = 1.0;
        else increase = 0.1;
        if(lvl <= 3) shield_increase = 3.0;
        else if(lvl <= 20) shield_increase = 4.0;
        else if(lvl <= 30) shield_increase = 3.0;
        else shield_increase = 0.1;
        if(ic.getView().getTitle().equals("state")){
            ic.setCancelled(true);
            if(ic.getRawSlot() == 20){
                attack.setBaseValue(attack.getBaseValue() + increase);
                p.sendMessage("공격력이" + increase +"만큼 올랐습니다!");
            }
            else if(ic.getRawSlot() == 22){
                shield.setBaseValue(shield.getBaseValue() + shield_increase);
                p.sendMessage("방어력이" + shield_increase +"만큼 올랐습니다!");
            }
            else if(ic.getRawSlot() == 24){
                if(speed_check < 5){
                    speed.setBaseValue(speed.getBaseValue() + 0.01);
                    p.sendMessage("속도가" + 0.01 +"만큼 올랐습니다!");
                    speed_check+=1;
                }
                else{
                    p.sendMessage("스피드 만랩 입니다!");
                }
            }
        }
    }
    @EventHandler
    public void levelup(PlayerLevelChangeEvent event){
        Player p = event.getPlayer();
        stat_point+=3;
        p.sendMessage("§e스텟 포인트가 3만큼 올랐습니다!");
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
    }/*
    @EventHandler
    public void damaged_villager(EntityDamageEvent event){
        if(event.getEntity() instanceof Villager){
            Villager villager = (Villager) event.getEntity();
            if("전직".equals(villager.getCustomName())){
                event.setCancelled(true);
            }
        }
    }*/
    @EventHandler
    public void interacted_villager(PlayerInteractAtEntityEvent event){
        Player p = event.getPlayer();
        if (event.getRightClicked().getCustomName().equals("전직")) {
            TextComponent tanker = new TextComponent("[탱커] 방어력 스텟이 다른 직업들보다 더 높게 올라간다.");
            tanker.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/choosedjob tanker"));
            TextComponent assassin = new TextComponent("[암살자] 속도 스텟이 다른 직업들보다 더 높게 올라간다.");
            assassin.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/choosedjob tanker"));
            TextComponent attacker = new TextComponent("[딜러] 공격력 스텟이 다른 직업들보다 더 높게 올라간다.");
            attacker.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/choosedjob tanker"));
            p.spigot().sendMessage(tanker);
            p.spigot().sendMessage(assassin);
            p.spigot().sendMessage(attacker);

        }
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player p = (Player)commandSender;
        AttributeInstance attack = p.getAttribute(Attribute.ATTACK_DAMAGE);
        AttributeInstance shield = p.getAttribute(Attribute.ARMOR);
        AttributeInstance speed = p.getAttribute(Attribute.MOVEMENT_SPEED);
        speed_check = 0;
        attack.setBaseValue(1.0);
        shield.setBaseValue(0);
        speed.setBaseValue(0.1);

        return true;
    }
}
