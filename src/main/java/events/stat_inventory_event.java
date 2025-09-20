package events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import plugin_main.Staetplugin;

import java.util.ArrayList;
import java.util.List;

public class stat_inventory_event implements Listener {
    public void check_speed_check(){
        if(Staetplugin.speed_check_toOther == true){
            Staetplugin.speed_check = 0;
            Staetplugin.speed_check_toOther = false;
        }
    }

    @EventHandler
    public void box_interacted(InventoryClickEvent ic){
        Player p = (Player) ic.getWhoClicked();
        AttributeInstance attack = p.getAttribute(Attribute.ATTACK_DAMAGE);
        AttributeInstance shield = p.getAttribute(Attribute.ARMOR);
        AttributeInstance speed = p.getAttribute(Attribute.MOVEMENT_SPEED);
        int lvl = p.getLevel();
        if(ic.getView().getTitle().equals("stat")){
            ic.setCancelled(true);
            if(ic.getRawSlot() == 20 || ic.getRawSlot() == 22 || ic.getRawSlot() == 24){
                if(Staetplugin.stat_point > 0) {
                    if (ic.getRawSlot() == 20) {
                        attack.setBaseValue(attack.getBaseValue() + 3);
                        p.sendMessage("공격력이" + 3 + "만큼 올랐습니다!");
                        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                        Staetplugin.stat_point--;
                    } else if (ic.getRawSlot() == 22) {
                        shield.setBaseValue(shield.getBaseValue() + 3);
                        p.sendMessage("방어력이" + 3 + "만큼 올랐습니다!");
                        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                        Staetplugin.stat_point--;
                    } else if (ic.getRawSlot() == 24) {
                        if (Staetplugin.speed_check < 5) {
                            speed.setBaseValue(speed.getBaseValue() + 0.01);
                            p.sendMessage("속도가" + 0.01 + "만큼 올랐습니다!");
                            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            Staetplugin.speed_check += 1;
                            Staetplugin.stat_point--;
                        }else {
                            p.sendMessage("스피드 만랩 입니다!");
                        }
                    }
                }else{
                    p.sendMessage("스텟 포인트가 부족합니다.");
                }
                ItemStack netherStar = ic.getInventory().getItem(0);
                ItemMeta netherStar_meta = netherStar.getItemMeta();
                List<String> netherStar_lore = new ArrayList<>();
                netherStar_lore.add("현재 스텟 포인트:" + Staetplugin.stat_point);
                netherStar_meta.setLore(netherStar_lore);
                netherStar.setItemMeta(netherStar_meta);
            }else{

            }

        }
    }
    @EventHandler
    public void openStat(PlayerSwapHandItemsEvent event){
        Inventory box = Bukkit.createInventory(null, 45, "stat");
        Player p = event.getPlayer();
        if(p.isSneaking()){
            event.setCancelled(true);
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
            for(int i = 0; i < box.getSize(); i++){
                    box.setItem(i, glass);
                }
                box.setItem(0, netherStar);
                box.setItem(20, sword);
                box.setItem(22, shield);
                box.setItem(24, boots);
                p.openInventory(box);
            }
        }
}
