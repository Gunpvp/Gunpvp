package gunpvp.chest_lottery;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.shampaggon.crackshot.CSUtility;

import gunpvp.data.Chests;
import gunpvp.data.DataManager;
import gunpvp.listener.Listener;

/**
 * Created by Thomas Langs on 16.07.2017.
 */
public class LuckyPack extends Listener{

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (p.getItemInHand() != null&&p.getItemInHand().getType()==Material.TRAPPED_CHEST) {
                openPackView(p);
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getCurrentItem().getType()==Material.CHEST){
            e.setCancelled(true);
            String displayName=e.getCurrentItem().getItemMeta().getDisplayName();
            buy(displayName,e);
        }
    }

    /**
     *
     * @param type 1-4 standing for chest
     */
    public static void addChest(int type, Player p){
        Chests chests = DataManager.getData(p).getChests();
        switch(type){
            case 1:
                chests.editNormal(1);
                break;
            case 2:
                chests.editRare(1);
                break;
            case 3:
                chests.editSpecial(1);
                break;
            case 4:
                chests.editOp(1);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static void openPackView(Player p){

        Inventory luckyPackView = Bukkit.createInventory(null, 27, "§8Lucky Packs");
        Chests chests = DataManager.getData(p).getChests();
        ItemStack normalPack=new ItemStack(Material.CHEST,chests.getNormal());
        ItemMeta normalMeta= normalPack.getItemMeta();
        normalMeta.setDisplayName("Normal Pack");
        normalPack.setItemMeta(normalMeta);
        ItemStack rarePack=new ItemStack(Material.CHEST, chests.getRare());
        ItemMeta rareMeta= rarePack.getItemMeta();
        rareMeta.setDisplayName("Rare Pack");
        rarePack.setItemMeta(rareMeta);
        ItemStack specialPack=new ItemStack(Material.CHEST,chests.getSpecial());
        ItemMeta specialMeta= specialPack.getItemMeta();
        specialMeta.setDisplayName("Special Pack");
        specialPack.setItemMeta(specialMeta);
        ItemStack opPack=new ItemStack(Material.CHEST,chests.getOp());
        ItemMeta opMeta= opPack.getItemMeta();
        opMeta.setDisplayName("OP Pack");
        opPack.setItemMeta(opMeta);

        luckyPackView.setItem(10,normalPack);
        luckyPackView.setItem(12,rarePack);
        luckyPackView.setItem(14,specialPack);
        luckyPackView.setItem(16,opPack);

        p.openInventory(luckyPackView);

    }

    private static boolean buy(String displayName, InventoryClickEvent e){
        CSUtility csu=new CSUtility();
        Player p = (Player) e.getWhoClicked();
        Boolean success=false;
        Chests playerPacks= DataManager.getData(p).getChests();
        switch(displayName){
            case "Normal Pack":
                if(playerPacks.getNormal()>0){
                    Random r=new Random();
                    int n=r.nextInt(10);
                    if(n>4&&n<7){
                        csu.giveWeapon(p,"AT4",1);
                        ItemStack rocket=new ItemStack(Material.POTATO_ITEM,6);
                        ItemMeta rocketMeta=rocket.getItemMeta();
                        rocketMeta.setDisplayName("§2§lAT4-Rocket");
                        rocket.setItemMeta(rocketMeta);
                        p.getInventory().addItem(rocket);
                        p.updateInventory();
                    }else{
                        csu.giveWeapon(p,"BLENDGRANATE",5);
                    }
                    playerPacks.editNormal(-1);

                }
                success=playerPacks.getNormal()>0;
                break;
            case "Rare Pack":
                success=playerPacks.getRare()>0;
                break;
            case "Special Pack":
                success=playerPacks.getSpecial()>0;
                break;
            case "OP Pack":
                success=playerPacks.getOp()>0;
                break;
        }
        p.closeInventory();
        p.sendMessage(e.getCurrentItem().getItemMeta().getDisplayName() + " Erfolgreich: "+ success);
        return success;
    }

}
