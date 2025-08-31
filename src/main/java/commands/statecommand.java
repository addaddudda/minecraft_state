package commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import practice.staetplugin.Staetplugin;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class statecommand extends JavaPlugin implements CommandExecutor {

    public static class state implements CommandExecutor{
        @Override
        public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
            Player p = (Player)sender;
            Inventory box = Bukkit.createInventory(null, 45, "state");

            ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta sword_meta = sword.getItemMeta();
            sword_meta.setDisplayName("공격력 강화");
            List<String> sword_lore = new ArrayList<>();
            sword_lore.add("기본 공격력을 강화합니다");
            sword_meta.setLore(sword_lore);
            sword.setItemMeta(sword_meta);

            ItemStack shield = new ItemStack(Material.SHIELD);
            ItemMeta shield_meta = shield.getItemMeta();
            shield_meta.setDisplayName("방어력 강화");
            List<String> shield_lore = new ArrayList<>();
            shield_lore.add("기본 방어력을 강화합니다.");
            shield_meta.setLore(shield_lore);
            shield.setItemMeta(shield_meta);

            ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
            ItemMeta boots_meta = boots.getItemMeta();
            boots_meta.setDisplayName("이동속도 강화");
            List<String> boots_lore = new ArrayList<>();
            boots_lore.add("가본 이동속도를 강화합니다.");
            boots.setItemMeta(boots_meta);
            if(args.length == 0){
                box.setItem(20, sword);
                box.setItem(22, shield);
                box.setItem(24, boots);
                p.openInventory(box);
            }
            else{
                p.sendMessage("명령어가 이상합니다");
            }
            return true;
        }
    }
}
