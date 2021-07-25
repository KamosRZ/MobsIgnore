package bg.hollyweed.mobsignore.commands;

import bg.hollyweed.mobsignore.MobsIgnore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommandExecutor implements CommandExecutor {
    private final MobsIgnore plugin;
    private final String RELOAD_MESSAGE = "Plugin and configuration have been reloaded!";
    public ReloadCommandExecutor(MobsIgnore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plugin.onReload();
        sender.sendMessage(ChatColor.GREEN + RELOAD_MESSAGE);
        return true;
    }
}
