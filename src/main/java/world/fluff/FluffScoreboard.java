package world.fluff;

import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

public class FluffScoreboard {
    private Scoreboard sb;
    public FluffScoreboard(Scoreboard sb) {
        this.sb = sb;
        createHealthObjective();
    }

    private void createHealthObjective() {
        // Player health
        sb.registerNewObjective("health", "health", ChatColor.RED+"\u2665");
		sb.getObjective("health").setDisplaySlot(DisplaySlot.BELOW_NAME);
    }

    public Scoreboard getScoreboard() {
        return this.sb;
    }

    public void kill() {
        sb.getObjective("health").unregister();
    }
}