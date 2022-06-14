package net.danh.storage.Events;

import net.danh.storage.Manager.Data;
import net.danh.storage.Manager.Files;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static net.danh.storage.Manager.Data.*;
import static net.danh.storage.Manager.Files.*;

public class Join implements Listener {
    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent e) {
        Player p = e.getPlayer();
        setautoSmelt(p, Files.getconfigfile().getBoolean("Auto.Smelt"));
        setautoPick(p, Files.getconfigfile().getBoolean("Auto.Pickup"));
        for (String item : Objects.requireNonNull(getconfigfile().getConfigurationSection("Blocks.")).getKeys(false)) {
            Data.setMaxStorage(p, item, getMaxStorageData(p, item));
            Data.setStorage(p, item, getStorageData(p, item));
        }
        if (!(getPlayers() == null)) {
            if (!getPlayers().contains(p.getName())) {
                addPlayers(p);
            }
        } else {
            List<String> ps = Collections.singletonList(p.getName());
            getdatafile().set("All_players", ps);
            savedata();
        }
    }
}