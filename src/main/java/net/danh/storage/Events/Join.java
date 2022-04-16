package net.danh.storage.Events;

import net.danh.storage.Manager.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static net.danh.storage.Manager.Data.*;
import static net.danh.storage.Manager.Files.getconfigfile;

public class Join implements Listener {

    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent e) {
        Player p = e.getPlayer();
        setautoSmelt(p, autoSmeltData(p));
        setautoPick(p, autoPickData(p));
        for (String item : Objects.requireNonNull(getconfigfile().getConfigurationSection("Blocks.")).getKeys(false)) {
            Data.setMaxStorage(p, item, getMaxStorageData(p, item));
            Data.setStorage(p, item, getStorageData(p, item));
        }
    }
}