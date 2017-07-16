package gunpvp.chest_lottery;

import gunpvp.listener.Listener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Thomas Langs on 16.07.2017.
 */
public class LuckyPack extends Listener{

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (p.getItemInHand() != null&&p.getItemInHand().getType()==Material.TRAPPED_CHEST) {

            }
        }
    }

    private static void openPackView(Player p){

        Inventory luckyPackView = Bukkit.createInventory(null, 27, "§b§lLucky Packs");

        ItemStack normalPack=new ItemStack(Material.REDSTONE_BLOCK,1);
        ItemMeta normalMeta= normalPack.getItemMeta();
        normalMeta.setDisplayName("Normal Pack");
        normalPack.setItemMeta(normalMeta);
        ItemStack rarePack=new ItemStack(Material.IRON_BLOCK,1);
        ItemMeta rareMeta= rarePack.getItemMeta();
        rareMeta.setDisplayName("Rare Pack");
        rarePack.setItemMeta(rareMeta);
        ItemStack specialPack=new ItemStack(Material.GOLD_BLOCK,1);
        ItemMeta specialMeta= specialPack.getItemMeta();
        specialMeta.setDisplayName("Special Pack");
        specialPack.setItemMeta(specialMeta);
        ItemStack opPack=new ItemStack(Material.DIAMOND_BLOCK,1);
        ItemMeta opMeta= opPack.getItemMeta();
        opMeta.setDisplayName("OP Pack");
        opPack.setItemMeta(opMeta);

        luckyPackView.setItem(10,normalPack);
        luckyPackView.setItem(12,rarePack);
        luckyPackView.setItem(14,specialPack);
        luckyPackView.setItem(16,opPack);

        p.openInventory(luckyPackView);

    }

}
