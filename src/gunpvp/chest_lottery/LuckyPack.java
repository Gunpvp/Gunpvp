package gunpvp.chest_lottery;

import com.shampaggon.crackshot.CSUtility;
import gunpvp.data.Chests;
import gunpvp.data.DataManager;
import gunpvp.listener.Listener;
import gunpvp.util.ArmorManager;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Thomas Langs on 16.07.2017.
 */
public class LuckyPack extends Listener{


    private static final String[] commonWeaponPack = {"csu:M16", "csu:GL1", "csu:RPK"};
    private static final String[] throwablePack = {"csu:C4"};
    private static final String[] armorPack = {"arm:DIAMOND_ARMOR", "arm:IRON_ARMOR", "arm:GOLD_ARMOR", "arm:LEATHER_ARMOR"};
    private static final String[] rareWeaponPack = {"arm:DIAMOND_ARMOR", "csu:AT4", "csu:C4"};


    private static final String errorPreMessage = "§cDu hast kein ";
    private static final String[] packs = {"Common Weapon Pack", "Throwable Pack", "Armor Pack", "Rare Weapon Pack"};
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
        if (e.getCurrentItem() != null && (e.getCurrentItem().getType() == standardMaterial ||
                e.getCurrentItem().getType() == errorMaterial)) {
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
                        standardMaterial, packCount[i], ""));
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

        Player p = (Player) e.getWhoClicked();

        String displayName = e.getCurrentItem().getItemMeta().getDisplayName();

        Chests packs = DataManager.getData(p).getChests();

        ItemStack[] items = null;

        switch(displayName){
            case "§2Common Weapon Pack":
                if (packs.getNormal() > 0) {
                    items = selectWonPriceAndCreateItemStackArr(commonWeaponPack);
                    packs.editNormal(-1);
                }
                break;

            case "§2Throwable Pack":
                if (packs.getRare() > 0) {
                    items = selectWonPriceAndCreateItemStackArr(throwablePack);
                    packs.editRare(-1);
                }
                break;

            case "§2Armor Pack":
                if (packs.getSpecial() > 0) {
                    items = selectWonPriceAndCreateItemStackArr(armorPack);
                    packs.editSpecial(-1);
                }
                break;

            case "§2Rare Weapon Pack":
                if (packs.getOp() > 0) {
                    items = selectWonPriceAndCreateItemStackArr(rareWeaponPack);
                    packs.editOp(-1);
                }
                break;
        }
        if (items != null) {
            String prev = "";
            for (ItemStack stack : items) {
                if (!stack.getItemMeta().getDisplayName().equals(prev)) {
                    prev = stack.getItemMeta().getDisplayName();
                    p.sendMessage("§2" + prev + " erhalten");
                }
                p.getInventory().addItem(stack);
            }
        }

        p.updateInventory();
        p.closeInventory();
    }

    /**
     * random logic that decides what items you get
     *
     * @param packLootDefinition the String[] where the items are defined
     * @return ItemStack[]
     */
    private static ItemStack[] selectWonPriceAndCreateItemStackArr(String[] packLootDefinition) {

        int numberOfPossibleItems = packLootDefinition.length - 1;
        //check to prevent an to high int
        if (numberOfPossibleItems > 31) {
            throw new IllegalArgumentException("packLootDefinition is larger than 31");
        }

        Random r = new Random();
        double indicator = 0;
        double possibilities = (int) Math.pow(Math.E, numberOfPossibleItems);
        double randomNumber = r.nextDouble() * possibilities;
        for (indicator = 1; indicator < randomNumber; indicator *= Math.E) ;

        indicator = Math.log(indicator);

        return createItemStackArr(packLootDefinition[(int) indicator]);

    }

    /**
     * logic that generates the items out of the itemCode
     *
     * @param itemStackIdentifier the code to generate the items from
     * @return ItemStack[]
     */
    private static ItemStack[] createItemStackArr(String itemStackIdentifier) {
        ItemStack[] items;
        List<ItemStack> itemList = new ArrayList<>();
        if (itemStackIdentifier != null && !itemStackIdentifier.equals("")) {
            String[] itemArray = itemStackIdentifier.split(",");
            for (String item : itemArray) {
                if (item.startsWith("csu:")) {
                    itemList.add(csu.generateWeapon(item.split(":")[1]));
                } else if (item.startsWith("arm:")) {
                    ItemStack[] armors = (ArmorManager.getArmorItemStackArrByMaterial(item.split(":")[1]));
                    for (ItemStack armor : armors) {
                        itemList.add(armor);
                    }
                }
            }
        }

        items = new ItemStack[itemList.size()];

        for (int i = 0; i < itemList.size(); i++) {
            items[i] = itemList.get(i);
        }

        return items;
    }

}
