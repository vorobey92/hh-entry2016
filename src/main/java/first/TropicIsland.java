package first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. Tropic остров
 * Created by ia.vorobev on 01.10.2016.
 */
public class TropicIsland {

    private int[][] islandField;
    private int totalWaterVolume;

    public TropicIsland(int[][] islandField) {
        this.islandField = islandField;
    }

    public int countWaterVolume() {
        totalWaterVolume = 0;
        // ищем только во внутренней части острова
        for (int i = 1; i < islandField.length - 1; i++) {
            for (int j = 1; j < islandField[0].length - 1; j++) {
                if (isProbablyLowland(i, j)) {
                    Point initialPoint = new Point(i, j);
                    breadthFirstWaterCount(initialPoint); // подсчет объема воды методом "поиска в ширину"
                }
            }
        }
        return totalWaterVolume;
    }

    public static void main(String[] args) throws IOException {
        List<TropicIsland> islands = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int islandsCount = Integer.parseInt(reader.readLine());
            for (int i = 0; i < islandsCount; i++) {
                String[] matrixParameters = reader.readLine().split("\\s");
                int rows = Integer.parseInt(matrixParameters[0]);
                int columns = Integer.parseInt(matrixParameters[1]);
                int[][] island = new int[rows][columns];
                for (int j = 0; j < rows; j++) {
                    String[] matrixValues = reader.readLine().split("\\s");
                    for (int k = 0; k < columns; k++) {
                        island[j][k] = Integer.parseInt(matrixValues[k]);
                    }
                }
                islands.add(new TropicIsland(island));
            }
        }
        
        islands.forEach(s -> System.out.println(s.countWaterVolume()));
    }

    private void breadthFirstWaterCount(Point initialPoint) {
        SortedSet<Integer> highGroundsValues = new TreeSet<>();
        List<Point> lowlandsCoordinates = new ArrayList<>();
        lowlandsCoordinates.add(initialPoint);
        while (true) {
            for (int k = 0; k < lowlandsCoordinates.size(); k++) {
                Point p = lowlandsCoordinates.get(k);
                if (p.isVisited()) {
                    continue;
                }
                p.setVisited(true);
                if (isIslandCorner(p.getI(), p.getJ())) {
                    return;
                }
                highGroundsValues.addAll(getGreaterNeighborsThan(p, islandField[initialPoint.getI()][initialPoint.getJ()]));
                addLessOrEqualNeighborsThan(p, islandField[initialPoint.getI()][initialPoint.getJ()], lowlandsCoordinates);
            }

            final int newWaterLevel = highGroundsValues.first();
            fillLowlandsWithWater(lowlandsCoordinates, newWaterLevel);

            initialPoint.setVisited(false);
            lowlandsCoordinates = new ArrayList<>();
            highGroundsValues = new TreeSet<>();
            lowlandsCoordinates.add(initialPoint);
        }
    }

    private void fillLowlandsWithWater(List<Point> lowlandsCoordinates, int newWaterLevel) {
        for (Point p : lowlandsCoordinates) {
            totalWaterVolume += newWaterLevel - islandField[p.getI()][p.getJ()];
            islandField[p.getI()][p.getJ()] = newWaterLevel;
        }
    }

    private List<Point> addLessOrEqualNeighborsThan(Point p, int comparedVal, List<Point> toAddList) {
        int i = p.getI();
        int j = p.getJ();
        Point pointToAdd;
        if (comparedVal >= islandField[i - 1][j]
                && !toAddList.contains(pointToAdd = new Point(i - 1, j))) {
            toAddList.add(pointToAdd);
        }
        if (comparedVal >= islandField[i][j + 1]
                && !toAddList.contains(pointToAdd = new Point(i, j + 1))) {
            toAddList.add(pointToAdd);
        }
        if (comparedVal >= islandField[i + 1][j]
                && !toAddList.contains(pointToAdd = new Point(i + 1, j))) {
            toAddList.add(pointToAdd);
        }
        if (comparedVal >= islandField[i][j - 1]
                && !toAddList.contains(pointToAdd = new Point(i, j - 1))) {
            toAddList.add(pointToAdd);
        }
        return toAddList;
    }


    private List<Integer> getGreaterNeighborsThan(Point p, int comparedVal) {
        List<Integer> greaterNeighbors = new ArrayList<>();
        int i = p.getI();
        int j = p.getJ();
        if (islandField[i][j] < islandField[i - 1][j]
                && islandField[i - 1][j] > comparedVal) {
            greaterNeighbors.add(islandField[i - 1][j]);
        }
        if (islandField[i][j] < islandField[i][j + 1]
                && islandField[i][j + 1] > comparedVal) {
            greaterNeighbors.add(islandField[i][j + 1]);
        }
        if (islandField[i][j] < islandField[i + 1][j]
                && islandField[i + 1][j] > comparedVal) {
            greaterNeighbors.add(islandField[i + 1][j]);
        }
        if (islandField[i][j] < islandField[i][j - 1]
                && islandField[i][j - 1] > comparedVal) {
            greaterNeighbors.add(islandField[i][j - 1]);
        }
        return greaterNeighbors;
    }

    private boolean isProbablyLowland(int i, int j) {
        return islandField[i][j] < islandField[i - 1][j]
                && islandField[i][j] < islandField[i][j - 1];
    }

    private boolean isIslandCorner(int i, int j) {
        return i == 0 || i == (islandField.length - 1)
                || j == 0 || j == (islandField[0].length - 1);
    }

    private static class Point {
        private final int i;
        private final int j;
        private boolean visited;

        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        int getI() {
            return i;
        }

        int getJ() {
            return j;
        }

        boolean isVisited() {
            return visited;
        }

        void setVisited(boolean visited) {
            this.visited = visited;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            return i == point.i && j == point.j;

        }

        @Override
        public int hashCode() {
            int result = i;
            result = 31 * result + j;
            return result;
        }
    }
}
