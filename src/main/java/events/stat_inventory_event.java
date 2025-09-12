package events;

import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import practice.staetplugin.Staetplugin;

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
        if(ic.getView().getTitle().equals("stat")){
            ic.setCancelled(true);
            if(Staetplugin.stat_point > 0) {
                if (ic.getRawSlot() == 20) {
                    attack.setBaseValue(attack.getBaseValue() + increase);
                    p.sendMessage("공격력이" + increase + "만큼 올랐습니다!");
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    Staetplugin.stat_point--;
                } else if (ic.getRawSlot() == 22) {
                    shield.setBaseValue(shield.getBaseValue() + shield_increase);
                    p.sendMessage("방어력이" + shield_increase + "만큼 올랐습니다!");
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    Staetplugin.stat_point--;
                } else if (ic.getRawSlot() == 24) {
                    if (Staetplugin.speed_check < 5) {
                        speed.setBaseValue(speed.getBaseValue() + 0.01);
                        p.sendMessage("속도가" + 0.01 + "만큼 올랐습니다!");
                        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                        Staetplugin.speed_check += 1;
                        Staetplugin.stat_point--;
                    } else {
                        p.sendMessage("스피드 만랩 입니다!");
                    }
                }
                ItemStack netherStar = ic.getInventory().getItem(0);
                ItemMeta netherStar_meta = netherStar.getItemMeta();
                List<String> netherStar_lore = new ArrayList<>();
                netherStar_lore.add("현재 스텟 포인트:" + Staetplugin.stat_point);
                netherStar_meta.setLore(netherStar_lore);
                netherStar.setItemMeta(netherStar_meta);
            }else{
                p.sendMessage("스텟 포인트가 부족합니다.");

            }

        }
    }
}
