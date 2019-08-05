package world.fluff;

import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class FluffScoreboard {
    private DBConnection db = null;
    private Scoreboard sb = null;
    public FluffScoreboard(DBConnection db, Scoreboard sb) {
        this.sb = sb;
        this.db = db;
        try {
            createHealthObjective();
        }
        catch(Exception e) {
            //
        }
    }

    private void createHealthObjective() {
        // Player health
        if(sb.getObjective("world.fluff.health") == null) {
            sb.registerNewObjective("world.fluff.health", "health", ChatColor.RED+"\u2665");
            sb.getObjective("world.fluff.health").setDisplaySlot(DisplaySlot.BELOW_NAME);
        }
    }

    public Scoreboard getScoreboard() {
        return sb;
    }

    public void kill() {
        // do nothing, yet
    }
}