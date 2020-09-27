import org.junit.Assert;
import org.junit.Test;
import view.tilemap.TileMap;
import view.units.PlayerView;

public class AnimationTest {

    @Test
    public void idleAnimationTest() {
        TileMap tileMap = new TileMap("/tileset/tileset_16pxNEW.png",
                "/map/map1.txt", true);
        PlayerView player = new PlayerView(tileMap, null);
        player.setPosition(16, 16);

        player.update();

        Assert.assertEquals(0, player.getCurrentAction());
    }

    @Test
    public void walkingAnimationTest() {
        TileMap tileMap = new TileMap("/tileset/tileset_16pxNEW.png",
                "/map/map1.txt", true);
        PlayerView player = new PlayerView(tileMap, null);
        player.setPosition(16, 16);
        player.setRight(true);

        player.update();

        Assert.assertEquals(1, player.getCurrentAction());
    }

    @Test
    public void deathAnimationTest() {
        TileMap tileMap = new TileMap("/tileset/tileset_16pxNEW.png",
                "/map/killer.txt", true);
        PlayerView player = new PlayerView(tileMap, null);
        player.setPosition(16, 16);
        player.setVector(0.1, 0.1);

        player.update();
        player.checkDeath();
        player.update();

        Assert.assertEquals(2, player.getCurrentAction());
    }
}
