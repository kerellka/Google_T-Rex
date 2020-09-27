import org.junit.Test;
import view.tilemap.TileMap;

import org.junit.Assert;
import view.units.CactusView;
import view.units.PterodactylView;
import view.units.UnitView;

import java.util.LinkedList;
import java.util.List;

public class TileMapTest {

    @Test
    public void loadMapTestCorrectFile() {
        TileMap tileMap = new TileMap("/tileset/tileset_16pxNEW.png",
                "/map/map1.txt", true);
        int[][] actualMap = tileMap.getMap().getMatrix();
        int[][] expectedMap =
                {       {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0 ,0 ,0 ,0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {22, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {23, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {23, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {23, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {23, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 52, 0, 0, 0, 0, 0, 0, 0, 0},
                        {23, 13, 0, 0, 0, 0, 32, 0, 0, 14, 24, 25, 25, 26, 15, 0, 0, 0, 0, 0, 0},
                        {23, 22, 22, 22, 22, 22, 22, 22, 22, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
                };

        Assert.assertArrayEquals(expectedMap, actualMap);
    }

    @Test
    public void loadMapTestIncorrectFile() {
        boolean isIncorrect;
        try {
            new TileMap("/tileset/tileset_16pxNEW.png",
                    "/map/incorrectMap.txt", true);
            isIncorrect = false;
        } catch (Exception e) {
            isIncorrect = true;
        }
        Assert.assertFalse(isIncorrect);
    }

    @Test
    public void unitPositionTest() {
        List<UnitView> actualList = new LinkedList<>();
        TileMap tileMap = new TileMap("/tileset/tileset_16pxNEW.png",
                "/map/unitPos.txt", true);
        tileMap.setUnitPosition(null, actualList);

        List<UnitView> expectedList = new LinkedList<>();
        CactusView cactus = new CactusView(tileMap);
        cactus.setPosition(16, 32);
        PterodactylView ptero1 = new PterodactylView(tileMap);
        ptero1.setPosition(16, 16);
        PterodactylView ptero2 = new PterodactylView(tileMap);
        ptero2.setPosition(48, 16);
        expectedList.add(cactus);
        expectedList.add(ptero1);
        expectedList.add(ptero2);

        Assert.assertEquals(expectedList, actualList);
    }
}
