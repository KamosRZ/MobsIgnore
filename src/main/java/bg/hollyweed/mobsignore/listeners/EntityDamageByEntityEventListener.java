package bg.hollyweed.mobsignore.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public final class EntityDamageByEntityEventListener implements Listener {

    private final String SHIELD_PERMISSION = "mobsignore.shield";
    private final double damageModifier;

    public EntityDamageByEntityEventListener(double damageModifier) {
        this.damageModifier = damageModifier;
    }

    @EventHandler
    public void onDamageEvent(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity damaged = event.getEntity();
        //If it's a projectile from a player, skip damage adjustment.
        if (checkIfProjectileFromHumanEntity(damager)) return;
        //If damage is from player, skip damage adjustment (main functionality).
        if (!damager.getType().equals(EntityType.PLAYER)) {
            if (damaged.hasPermission(SHIELD_PERMISSION)) {
                event.setDamage(event.getDamage()/damageModifier);
            }
        }
    }

    private boolean checkIfProjectileFromHumanEntity(Entity damager) {
        if (damager instanceof Projectile) {
            return ((Projectile) damager).getShooter() instanceof HumanEntity;
        } else {
            return false;
        }
    }
}
