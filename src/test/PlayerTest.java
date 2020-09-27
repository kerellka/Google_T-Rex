import org.junit.Assert;
import org.junit.Test;
import view.tilemap.TileMap;
import view.units.PlayerView;
import view.units.UnitView;

import java.util.LinkedList;
import java.util.List;

public class PlayerTest {

    private final TileMap tileMap = new TileMap("/tileset/tileset_16pxNEW.png",
            "/map/killer.txt", true);
    private final PlayerView player = new PlayerView(tileMap, null);


    @Test
    public void playerDeathBySpikesTest() {
        player.setPosition(16, 16);
        player.setVector(0.1, 0.1);

        player.update();

        Assert.assertTrue(player.checkDeath());
    }

    @Test
    public void playerHitTest() {
        List<UnitView> enemies = new LinkedList<>();
        tileMap.setUnitPosition(null, enemies);

        player.setPosition(48, 0);

        player.checkAttack(enemies);

        Assert.assertEquals(3, player.getModel().getHealth());
    }

    @Test
    public void playerEggPickUpTest() {
        player.setPosition(48, 48);
        player.setVector(0.1, 0.1);

        player.update();

        Assert.assertEquals(1, player.getModel().getEggCount());
    }

    @Test
    public void playerFallingTest() {
        player.setPosition(32, 16);
        player.setVector(0.1, 0.1);

        player.update();

        Assert.assertTrue(player.isFalling());
    }


}
