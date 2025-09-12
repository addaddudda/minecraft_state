package commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import practice.staetplugin.Staetplugin;

import java.util.ArrayList;
import java.util.List;

public class statecommand extends JavaPlugin implements CommandExecutor {

    public static class state implements CommandExecutor{
        @Override
        public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
            Player p = (Player)sender;
            Inventory box = Bukkit.createInventory(null, 45, "stat");


            ItemStack sword = new ItemStack(Material.BOOK);
            ItemMeta sword_meta = sword.getItemMeta();
            sword_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            sword_meta.setDisplayName("공격력 강화");
            List<String> sword_lore = new ArrayList<>();
            sword_lore.add("기본 공격력을 강화합니다");
            sword_meta.setLore(sword_lore);
            sword.setItemMeta(sword_meta);

            ItemStack shield = new ItemStack(Material.BOOK);
            ItemMeta shield_meta = shield.getItemMeta();
            shield_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            shield_meta.setDisplayName("방어력 강화");
            List<String> shield_lore = new ArrayList<>();
            shield_lore.add("기본 방어력을 강화합니다.");
            shield_meta.setLore(shield_lore);
            shield.setItemMeta(shield_meta);

            ItemStack boots = new ItemStack(Material.BOOK);
            ItemMeta boots_meta = boots.getItemMeta();
            boots_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            boots_meta.setDisplayName("이동속도 강화");
            List<String> boots_lore = new ArrayList<>();
            boots_lore.add("가본 이동속도를 강화합니다.");
            boots_meta.setLore(boots_lore);
            boots.setItemMeta(boots_meta);

            ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta glass_meta = glass.getItemMeta();
            glass_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            glass_meta.setDisplayName(" ");
            glass_meta.setLore(null);
            glass.setItemMeta(glass_meta);

            ItemStack netherStar = new ItemStack(Material.NETHER_STAR);
            ItemMeta netherStar_meta = netherStar.getItemMeta();
            netherStar_meta.setDisplayName("스텟 포인트");
            List<String> netherStar_lore = new ArrayList<>();
            netherStar_lore.add("현재 스텟 포인트:" + Staetplugin.stat_point);
            netherStar_meta.setLore(netherStar_lore);
            netherStar.setItemMeta(netherStar_meta);
            if(args.length == 0){
                for(int i = 0; i < box.getSize(); i++){
                    box.setItem(i, glass);
                }
                box.setItem(0, netherStar);
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
