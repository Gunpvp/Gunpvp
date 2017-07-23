package gunpvp.commands;

import gunpvp.chest_lottery.LuckyPack;
import gunpvp.data.Chests;
import gunpvp.data.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LuckyPackCommand extends Command {

    private static final String[] packNames = {"normal", "rare", "special", "op"};

    protected LuckyPackCommand() {
        super("luckypacks");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {

        String lowerCaseCMD = command.getName().toLowerCase();

        if (commandSender.isOp()) {
            if (lowerCaseCMD.equals("luckypacks") || lowerCaseCMD.equals("lp") || lowerCaseCMD.equals("packs")) {
                if (args.length == 2 && args[0].equalsIgnoreCase("show")) {
                    Player p = null;
                    if ((p = Bukkit.getPlayer(args[1])) != null) {
                        Chests packs = DataManager.getData(p).getChests();
                        commandSender.sendMessage("§8[§2Gunpvp§8] §2 Player: " + p.getName() + " " +
                                LuckyPack.packs[0] + ": " + packs.getNormal() + " " + LuckyPack.packs[1] + ": " + packs.getRare() +
                                " " + LuckyPack.packs[2] + ": " + packs.getSpecial() + " " + LuckyPack.packs[3] + ": " + packs.getOp());
                        return true;
                    } else {
                        commandSender.sendMessage("§8[§2Gunpvp§8] §cSpieler nicht gefunden");
                    }
                } else if (args.length == 4 && args[0].equalsIgnoreCase("edit")) {
                    Player p = null;
                    int editValue = 0;
                    if ((p = Bukkit.getPlayer(args[1])) != null) {
                        Chests packs = DataManager.getData(p).getChests();
                        try {
                            editValue = Integer.parseInt(args[3]);
                            switch (args[2].toLowerCase()) {
                                case "normal":
                                    packs.editNormal(editValue);
                                    commandSender.sendMessage("§8[§2Gunpvp§8] §2 Der User " + p.getName() + " hat jetzt " +
                                            packs.getNormal() + " " + LuckyPack.packs[0]);
                                    break;
                                case "rare":
                                    packs.editRare(editValue);
                                    commandSender.sendMessage("§8[§2Gunpvp§8] §2 Der User " + p.getName() + " hat jetzt " +
                                            packs.getRare() + " " + LuckyPack.packs[1]);
                                    break;
                                case "special":
                                    packs.editSpecial(editValue);
                                    commandSender.sendMessage("§8[§2Gunpvp§8] §2 Der User " + p.getName() + " hat jetzt " +
                                            packs.getSpecial() + " " + LuckyPack.packs[2]);
                                    break;
                                case "op":
                                    packs.editOp(editValue);
                                    commandSender.sendMessage("§8[§2Gunpvp§8] §2 Der User " + p.getName() + " hat jetzt " +
                                            packs.getOp() + " " + LuckyPack.packs[3]);
                                    break;
                                default:
                                    commandSender.sendMessage("§8[§2Gunpvp§8] §cChestType: " + args[2] + " nicht gefunden");
                                    break;
                            }
                        } catch (NumberFormatException e) {
                            commandSender.sendMessage("§8[§2Gunpvp§8] §cWert " + args[3] + " ist nicht zulässig");
                        }
                    } else {
                        commandSender.sendMessage("§8[§2Gunpvp§8] §cSpieler nicht gefunden");
                    }
                } else {
                    showUsage(commandSender);
                }
            }
        } else {
            commandSender.sendMessage("§8[§2Gunpvp§8] §cCommand nicht gefunden!");
        }

        return false;
    }

    private static void showUsage(CommandSender commandSender) {
        commandSender.sendMessage("§8[§2Gunpvp§8] §c Ungueltiger Command");
        commandSender.sendMessage("§8[§2Gunpvp§8] §c Usage: luckypacks show [Spielername]");
        commandSender.sendMessage("§8[§2Gunpvp§8] §c Usage: luckypacks edit [Spielername] [Chesttype] [EditValue]");
        commandSender.sendMessage("§8[§2Gunpvp§8] §c Valid ChestTypes: normal,rare,special,op");
    }

}
