package net.danh.storage.Commands;

import net.danh.storage.Manager.Data;
import net.danh.storage.Manager.Files;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static net.danh.storage.Manager.Data.*;
import static net.danh.storage.Manager.Files.*;
import static net.danh.storage.Manager.Items.RemoveItems;
import static net.danh.storage.Manager.Items.SellItems;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (label.equalsIgnoreCase("APick")) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    setautoPick(Objects.requireNonNull(((Player) sender).getPlayer()), !autoPick(((Player) sender).getPlayer()));
                }
            }
        }
        if (label.equalsIgnoreCase("ASmelt")) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    if (sender.hasPermission("storage.asmelt")) {
                        setautoSmelt(Objects.requireNonNull(((Player) sender).getPlayer()), !autoSmelt(((Player) sender).getPlayer()));
                    }
                }
            }
        }
        if (label.equalsIgnoreCase("Storage")) {
            if (args.length == 0) {
                for (String user : getlanguagefile().getStringList("Help_User")) {
                    sender.sendMessage(colorize(user));
                }
                if (sender.hasPermission("Storage.admin")) {
                    for (String user : getlanguagefile().getStringList("Help_Admin")) {
                        sender.sendMessage(colorize(user));
                    }
                }
                return true;
            }
            if (args.length == 1) {
                if (sender.hasPermission("Storage.admin")) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        reloadfiles();
                        sender.sendMessage(colorize("&aDone"));
                    }
                }
            }
            if (args.length == 3) {
                if (sender instanceof Player) {
                    if (args[0].equalsIgnoreCase("sell")) {
                        if (Material.getMaterial(args[1]) != null) {
                            if (Integer.parseInt(args[2]) > 0) {
                                SellItems(((Player) sender).getPlayer(), args[1], Integer.parseInt(args[2]));
                            } else if (Integer.parseInt(args[2]) == 0) {
                                SellItems(((Player) sender).getPlayer(), args[1], getStorage(Objects.requireNonNull(((Player) sender).getPlayer()), args[1]));
                            }
                        }
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("take")) {
                        if (Material.getMaterial(args[1]) != null) {
                            if (Integer.parseInt(args[2]) > 0) {
                                RemoveItems(((Player) sender).getPlayer(), args[1], Integer.parseInt(args[2]));
                            } else if (Integer.parseInt(args[2]) == 0) {
                                RemoveItems(((Player) sender).getPlayer(), args[1], getStorage(Objects.requireNonNull(((Player) sender).getPlayer()), args[1]));
                            }
                        }
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("add")) {
                        if (Material.getMaterial(args[1]) != null) {
                            if (Objects.requireNonNull(((Player) sender).getPlayer()).getItemInHand().getType() == Material.getMaterial(args[1])) {
                                if (Integer.parseInt(args[2]) <= ((Player) sender).getPlayer().getItemInHand().getAmount()) {
                                    Data.addStorage(((Player) sender).getPlayer(), args[1], Integer.parseInt(args[2]));
                                    ((Player) sender).getPlayer().getItemInHand().setAmount(Integer.parseInt(args[2]));
                                } else {
                                    sender.sendMessage(Files.colorize(Files.getlanguagefile().getString("Not_Enough")));
                                }
                            } else {
                                sender.sendMessage(Files.colorize(Files.getlanguagefile().getString("Not_Correct_Item")));
                            }
                        }
                        return true;
                    }
                }
            }
            if (args.length == 5) {
                if (args[0].equalsIgnoreCase("storage")) {
                    if (Material.getMaterial(args[3]) != null) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                setStorage(Objects.requireNonNull(Bukkit.getPlayer(args[2])), args[3], Integer.parseInt(args[4]));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                addStorage(Objects.requireNonNull(Bukkit.getPlayer(args[2])), args[3], Integer.parseInt(args[4]));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                removeStorage(Objects.requireNonNull(Bukkit.getPlayer(args[2])), args[3], Integer.parseInt(args[4]));
                            }
                        }
                    }
                    return true;
                }
                if (args[0].equalsIgnoreCase("maxstorage")) {
                    if (Material.getMaterial(args[3]) != null) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                setMaxStorage(Objects.requireNonNull(Bukkit.getPlayer(args[2])), args[3], Integer.parseInt(args[4]));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                addMaxStorage(Objects.requireNonNull(Bukkit.getPlayer(args[2])), args[3], Integer.parseInt(args[4]));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                removeMaxStorage(Objects.requireNonNull(Bukkit.getPlayer(args[2])), args[3], Integer.parseInt(args[4]));
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
