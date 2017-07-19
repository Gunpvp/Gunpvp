package gunpvp.chest_lottery;

import java.util.Random;

import gunpvp.util.Items;
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

    private static final String errorPreMessage = "§cDu hast kein ";
    private static final String[] packs={"Normal Pack","Rare Pack","Special Pack","OP Pack"};
    private static final Inventory luckyPackView = Bukkit.createInventory(null, 27, "§8Lucky Packs");
    private static final Material standardMaterial= Material.CHEST;
    private static final Material errorMaterial = Material.FIREWORK_CHARGE;
    private static final CSUtility csu=new CSUtility();

    /**
     * opens the Pack Inventory when user click trapped chest
     * @param e PlayerInteractEvent
     */
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (p.getInventory().getItemInMainHand() != null&&p.getInventory().getItemInMainHand().
                    getType()==Material.TRAPPED_CHEST) {
                openPackView(p);
            }
        }
    }

    /**
     * prevents items in PAck inv from beeing moved by player
     * and calls method buy with InventoryClickEvent as e
     * @param e InventoryClickEvent
     */
    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getCurrentItem() != null && (e.getCurrentItem().getType()==Material.CHEST||
                e.getCurrentItem().getItemMeta().getDisplayName().startsWith(errorPreMessage))){
            e.setCancelled(true);
            String displayName=e.getCurrentItem().getItemMeta().getDisplayName();
            buy(e);
        }
    }

    /**
     * adds one chest of the selected type to the db
     * @param type int(1-4) represents the 4 chestTypes
     * @param p Player
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

    /**
     * decides which items top display in the buy inv
     * @param p Player
     */

    private static void openPackView(Player p){
        Chests chests = DataManager.getData(p).getChests();
        int[] packCount = {chests.getNormal(),chests.getRare(),chests.getSpecial(),chests.getOp()};


        for(int i=0;i< packs.length;i++){
            if(packCount[i]==0){
                luckyPackView.setItem(10+2*i,Items.generate(errorPreMessage+packs[i],
                        errorMaterial, 1,""));
            }else if(packCount[i]>0){
                luckyPackView.setItem(10+2*i,Items.generate("§2"+packs[i],
                        standardMaterial, 1,""));
            }

        }

        p.openInventory(luckyPackView);

    }

    /**
     *  logic to get Items from Packs
     * @param e InventoryClickEvent
     * @return
     */
    private static void buy(InventoryClickEvent e){
        String displayName=e.getCurrentItem().getItemMeta().getDisplayName();
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
                break;
            case "Rare Pack":
                break;
            case "Special Pack":
                break;
            case "OP Pack":
                break;
        }
        p.closeInventory();
    }

}
