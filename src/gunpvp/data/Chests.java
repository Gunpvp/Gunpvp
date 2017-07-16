package gunpvp.data;

import gunpvp.util.Database;

/**
 * Created by Thomas Langs on 16.07.2017.
 * used for ChestSystem
 */
public class Chests {

    private int normal, rare, special, op;
    private String uuid;

    protected Chests(String uuid,int normal, int rare, int special, int op) {
        this.normal = normal;
        this.rare = rare;
        this.special = special;
        this.op = op;
        this.uuid=uuid;
    }

    public void editNormal(int normal){
        this.normal+=normal;
        Database.execute("UPDATE `GUNPVP_CHESTS` SET `NORMAL` = "+this.normal+" WHERE `UUID` = '"+this.uuid+"'");
    }

    public void editRare(int rare){
        this.rare+=rare;
        Database.execute("UPDATE `GUNPVP_CHESTS` SET `RARE` = "+this.rare+" WHERE `UUID` = '"+this.uuid+"'");
    }

    public void editSpecial(int special){
        this.special+=special;
        Database.execute("UPDATE `GUNPVP_CHESTS` SET `SPECIAL` = "+this.special+" WHERE `UUID` = '"+this.uuid+"'");
    }

    public void editOp(int op){
        this.op+=op;
        Database.execute("UPDATE `GUNPVP_CHESTS` SET `OP` = "+this.op+" WHERE `UUID` = '"+this.uuid+"'");
    }

    public int getNormal() {
        return normal;
    }

    public int getRare() {
        return rare;
    }

    public int getSpecial() {
        return special;
    }

    public int getOp() {
        return op;
    }
}
