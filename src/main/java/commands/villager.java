package commands;

import net.kyori.adventure.text.NBTComponent;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

public class villager extends JavaPlugin implements CommandExecutor{

    public static class villager_create implements CommandExecutor{
        @Override
        public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
            if (!(sender instanceof Player)) return false;
            Player p = (Player)sender;
            if(args.length < 1) {
                p.sendMessage("/villager_create 주민이름");
            }else if(args.length == 1){
                Location loc = p.getLocation();
                Villager villager = (Villager)loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
                villager.setCustomName(args[0]);
                villager.setProfession(Villager.Profession.NONE);
                villager.setVillagerType(Villager.Type.PLAINS);
                villager.setCustomNameVisible(true);
                villager.setAI(false);
                villager.setSilent(true);
            }else{
                p.sendMessage("명령어 이상");
            }
            return false;
        }
    }

}
