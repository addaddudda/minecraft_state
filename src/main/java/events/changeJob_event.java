package events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import practice.staetplugin.Staetplugin;

import java.util.ArrayList;
import java.util.List;

public class changeJob_event implements Listener {
    private final Staetplugin plugin = Staetplugin.getPlugin(Staetplugin.class);

    @EventHandler
    public void changeJob_clicked(PlayerInteractAtEntityEvent event){
        Inventory changeJob = Bukkit.createInventory(null, 27, "changeJob");
        ItemStack tanker = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta tanker_meta = tanker.getItemMeta();
        tanker_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        tanker_meta.setDisplayName("탱커");
        List<String> tanker_lore = new ArrayList<>();
        tanker_lore.add("방어력 상승률이 더 높아집니다.");
        tanker_meta.setLore(tanker_lore);
        tanker.setItemMeta(tanker_meta);

        ItemStack attacker = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta attacker_meta = attacker.getItemMeta();
        attacker_meta.setDisplayName("딜러");
        attacker_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> attacker_lore = new ArrayList<>();
        attacker_lore.add("공격력의 상승률이 더 높아집니다.");
        attacker_meta.setLore(attacker_lore);
        attacker.setItemMeta(attacker_meta);

        changeJob.setItem(11, tanker);
        changeJob.setItem(15, attacker);
        Player p = event.getPlayer();
        if(event.getRightClicked().getCustomName().equals("전직")){
            p.sendMessage("자네.. 전직을 하러 온겐가");
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                p.sendMessage("선택해보게나");
            }, 15);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                p.openInventory(changeJob);
            }, 35);
        }
    }
}
