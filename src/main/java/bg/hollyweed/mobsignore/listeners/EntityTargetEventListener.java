package bg.hollyweed.mobsignore.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public final class EntityTargetEventListener implements Listener {

    private final String IGNORE_PERMISSION = "mobsignore.ignore";

    @EventHandler
    public void onEntityTarget(EntityTargetEvent event) {
        Entity target = event.getTarget();
        if (target == null) return;
        if (target.hasPermission(IGNORE_PERMISSION)) {
            event.setCancelled(true);
        }
    }
}
