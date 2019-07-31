package world.fluff;
import org.bukkit.plugin.java.JavaPlugin;
import me.liec0dez.MinimapAPI.MinimapAPI;

/**
 * Hello world!
 *
 */
public class App extends JavaPlugin
{
    @Override
    public void onEnable() {
        getLogger().info("Hello, SpigotMC!");
        MinimapAPI.getInstance(); // test compilation
    }

    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }
}
