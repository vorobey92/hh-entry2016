package first;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ia.vorobev on 27.09.2016.
 */
public class TropicIslandTest {

    @Test
    public void countWaterVolume() throws Exception {
        int[][] test = {
                {5, 3, 4, 5},
                {6, 2, 1, 4},
                {3, 1, 1, 3},
                {8, 5, 4, 3}
        };
        TropicIsland ti = new TropicIsland(test);
        assertEquals(7, ti.countWaterVolume());
    }

    @Test
    public void countWaterVolume2() throws Exception {
        int[][] test = {
                {5, 3, 4, 5},
                {6, 2, 1, 4},
                {3, 1, 1, 2},
                {8, 5, 4, 3}
        };
        TropicIsland ti = new TropicIsland(test);
        assertEquals(3, ti.countWaterVolume());
    }

    @Test
    public void countWaterVolume3() throws Exception {
        int[][] test = {
                {4, 5, 4},
                {3, 1, 5},
                {5, 4, 1},
        };
        TropicIsland ti = new TropicIsland(test);
        assertEquals(2, ti.countWaterVolume());
    }

    @Test
    public void countWaterVolume4() throws Exception {
        int[][] test = {
                {2, 2, 2},
                {2, 1, 2},
                {2, 1, 2},
                {2, 1, 2},
        };
        TropicIsland ti = new TropicIsland(test);
        assertEquals(0, ti.countWaterVolume());
    }

    @Test
    public void countWaterVolume5() throws Exception {
        int[][] test = {
                {4, 5, 4},
                {3, 6, 5},
                {5, 4, 1},
        };
        TropicIsland ti = new TropicIsland(test);
        assertEquals(0, ti.countWaterVolume());
    }

    @Test
    public void countWaterVolume6() throws Exception {
        int[][] test = {
                {5, 5, 5, 4, 3, 2, 1},
                {5, 6, 6, 4, 3, 2, 1},
                {5, 6, 1, 4, 3, 2, 1},
                {4, 4, 4, 4, 3, 2, 1},
                {3, 3, 3, 3, 3, 2, 1},
                {2, 2, 2, 2, 2, 2, 1},
                {1, 1, 1, 1, 1, 1, 1},
        };
        TropicIsland ti = new TropicIsland(test);
        assertEquals(3, ti.countWaterVolume());
    }

    @Test
    public void countWaterVolume7() throws Exception {
        int[][] test = {
                {21, 22, 23, 24, 25},
                {20, 7, 8, 1, 10},
                {19, 8, 1, 1, 11},
                {18, 1, 1, 1, 12},
                {17, 16, 15, 14, 13},
        };
        TropicIsland ti = new TropicIsland(test);
        assertEquals(61, ti.countWaterVolume());
    }

    @Test
    public void countWaterVolume8() throws Exception {
        int[][] test = {
                {21, 22, 23, 24, 25},
                {20, 7, 8, 9, 10},
                {19, 6, 1, 2, 11},
                {18, 5, 4, 3, 1},
                {17, 16, 15, 14, 13},
        };
        TropicIsland ti = new TropicIsland(test);
        assertEquals(3, ti.countWaterVolume());
    }

    @Test
    public void countWaterVolume9() throws Exception {
        int[][] test = {
                {1, 1, 2, 2, 2, 1, 1},
                {1, 1, 2, 1, 2, 1, 1},
                {2, 2, 2, 1, 2, 2, 2},
                {2, 1, 1, 1, 1, 1, 2},
                {2, 2, 2, 1, 2, 2, 2},
                {1, 1, 2, 1, 2, 1, 1},
                {1, 1, 2, 2, 2, 1, 1}
        };
        TropicIsland ti = new TropicIsland(test);
        assertEquals(9, ti.countWaterVolume());
    }

    @Test
    public void countWaterVolume10() throws Exception {
        int[][] test = {
                {1, 1, 7, 1, 7, 1},
                {1, 7, 1, 7, 6, 7},
                {7, 3, 2, 4, 5, 7},
                {7, 7, 7, 7, 7, 7},
        };
        TropicIsland ti = new TropicIsland(test);
        assertEquals(21, ti.countWaterVolume());
    }

    @Test
    public void countWaterVolume11() throws Exception {
        int[][] test = {
                {2, 2, 2, 2, 2, 2, 2},
                {2, 1, 2, 1, 2, 1, 2},
                {2, 2, 2, 2, 2, 2, 2},
                {2, 1, 2, 1, 2, 1, 2},
                {2, 2, 2, 2, 2, 2, 2},
                {2, 1, 2, 1, 2, 1, 2},
                {2, 2, 2, 2, 2, 2, 2}
        };
        TropicIsland ti = new TropicIsland(test);
        assertEquals(9, ti.countWaterVolume());
    }

    @Test
    public void countWaterVolume12() {
        int[][] test = {
                {1000, 1000, 1000},
                {1000, 1, 1000},
                {1000, 1000, 1000},
        };
        TropicIsland ti = new TropicIsland(test);
        assertEquals(999, ti.countWaterVolume());
    }
}