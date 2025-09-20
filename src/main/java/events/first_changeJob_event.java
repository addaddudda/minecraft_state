package events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import plugin_main.Staetplugin;

import java.util.*;

public class first_changeJob_event implements Listener {
    private final Staetplugin plugin = Staetplugin.getPlugin(Staetplugin.class);
    public static Map<UUID, String> playerJobs = new HashMap<>();
    @EventHandler
    public void firstJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        if(!p.hasPlayedBefore()) {
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

            ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta glass_meta = glass.getItemMeta();
            glass_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            glass_meta.setDisplayName(" ");
            glass_meta.setLore(null);
            glass.setItemMeta(glass_meta);

            for (int i = 0; i < changeJob.getSize(); i++) {
                changeJob.setItem(i, glass);
            }
            changeJob.setItem(11, tanker);
            changeJob.setItem(15, attacker);

            p.sendMessage("서버에 처음 들어오셨군요!");
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                p.sendMessage("먼저 전직을 해볼까요?");
            }, 15);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                p.openInventory(changeJob);
            }, 35);
        }
    }
    @EventHandler
    public void clicked_firstChangeJop(InventoryClickEvent event){
        Player p = (Player) event.getWhoClicked();
        if(event.getClickedInventory() == event.getView().getTopInventory() && event.getView().getTitle().equals("changeJob")){
            event.setCancelled(true);
            if(event.getRawSlot() == 11){
                playerJobs.put(p.getUniqueId(), "탱커");
                p.sendMessage("1차 전직 직업이 탱커로 설정되었습니다!");
                p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
                p.closeInventory();
            }
            else if(event.getRawSlot() == 15){
                playerJobs.put(p.getUniqueId(), "딜러");
                p.sendMessage("1차 전직 직업이 딜러로 설정되었습니다!");
                p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
                p.closeInventory();
            }
        }
    }
}
