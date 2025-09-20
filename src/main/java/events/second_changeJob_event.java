package events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class second_changeJob_event implements Listener {
    @EventHandler
    public void secondVillager_clicked(PlayerInteractAtEntityEvent event){
        Player p = (Player) event.getPlayer();
        Inventory second_changeJob = Bukkit.createInventory(null, 27, "second_changeJob");
        if(event.getRightClicked() instanceof Villager villager){
            if(villager.getCustomName().contains("2차전직") && p.getLevel() >= 50){
                ItemStack swordMan = new ItemStack(Material.ENCHANTED_BOOK);
                ItemMeta swordMan_meta = swordMan.getItemMeta();
                swordMan_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                swordMan_meta.setDisplayName("검사");
                List<String> swordMan_lore = new ArrayList<>();
                swordMan_lore.add("검사로 2차 전직하며, 검사관련 스킬을 얻을수 있습니다.");
                swordMan_meta.setLore(swordMan_lore);
                swordMan.setItemMeta(swordMan_meta);

                ItemStack wizard = new ItemStack(Material.ENCHANTED_BOOK);
                ItemMeta wizard_meta = wizard.getItemMeta();
                wizard_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                wizard_meta.setDisplayName("마법사");
                List<String> wizard_lore = new ArrayList<>();
                wizard_lore.add("마법사로 2차 전직하며, 마법관련 스킬을 얻을수 있습니다.");
                wizard_meta.setLore(wizard_lore);
                wizard.setItemMeta(wizard_meta);

                ItemStack healer = new ItemStack(Material.ENCHANTED_BOOK);
                ItemMeta healer_meta = healer.getItemMeta();
                healer_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                healer_meta.setDisplayName("힐러");
                List<String> healer_lore = new ArrayList<>();
                healer_lore.add("힐러로 2차 전직하며, 힐관련 스킬을 얻을수 있습니다.(힐러의 기본공격은 팀원에겐 힐이, 적에겐 딜이 들어갑니다)");
                healer_meta.setLore(healer_lore);
                healer.setItemMeta(healer_meta);

                ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                ItemMeta glass_meta = glass.getItemMeta();
                glass_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                glass_meta.setDisplayName(" ");
                glass_meta.setLore(null);
                glass.setItemMeta(glass_meta);

                for (int i = 0; i < second_changeJob.getSize(); i++) {
                    second_changeJob.setItem(i, glass);
                }
                second_changeJob.setItem(11, swordMan);
                second_changeJob.setItem(13, wizard);
                second_changeJob.setItem(15, healer);
                p.openInventory(second_changeJob);
            }
            else if(p.getLevel() < 50){
                p.sendMessage("레벨이 50보다 커야합니다.");
            }
        }
    }
    @EventHandler
    public void second_changeJob_clicked(InventoryClickEvent event){
        if(event.getClickedInventory() == event.getView().getTopInventory() && event.getView().getTitle().equals("second_changeJob")){
            event.setCancelled(true);

        }
    }
}
