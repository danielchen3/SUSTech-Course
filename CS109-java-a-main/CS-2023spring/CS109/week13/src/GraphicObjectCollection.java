import java.util.*;
import java.util.ArrayList;
import java.util.Collections;

public class GraphicObjectCollection implements ObjectCollection {
    private List<GraphicObject> graphicObjects = new ArrayList<>();

    public void addObject(ObjectColor objectColor, double... parameters) {
        if (parameters.length == 1) {
            Sphere sphere = new Sphere(objectColor, parameters[0]);
            graphicObjects.add(sphere);
        }
        if (parameters.length == 2) {
            Cone cone = new Cone(objectColor, parameters[0], parameters[1]);
            graphicObjects.add(cone);
        }
        if (parameters.length == 3) {
            Cuboid cuboid = new Cuboid(objectColor, parameters[0], parameters[1], parameters[2]);
            graphicObjects.add(cuboid);
        }
    }

    public List<String> getObjectInfo() {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < getObjectCount(); i++) {
            if (!graphicObjects.get(i).equals(null))
                ans.add(graphicObjects.get(i).toString());
        }
        return ans;
    }

    @Override
    public int getObjectCount() {
        return graphicObjects.size();
    }

    @Override
    public List<String> getCountsByColor() {
        //RED, YELLOW, BLUE, GREEN, BLACK, WHITE;
        int[] color = new int[6];
        for (int i = 0; i < getObjectCount(); i++) {
            if (graphicObjects.get(i).getColor().equals(ObjectColor.RED)) color[0]++;
            if (graphicObjects.get(i).getColor().equals(ObjectColor.YELLOW)) color[1]++;
            if (graphicObjects.get(i).getColor().equals(ObjectColor.BLUE)) color[2]++;
            if (graphicObjects.get(i).getColor().equals(ObjectColor.GREEN)) color[3]++;
            if (graphicObjects.get(i).getColor().equals(ObjectColor.BLACK)) color[4]++;
            if (graphicObjects.get(i).getColor().equals(ObjectColor.WHITE)) color[5]++;
        }
        List<String> ans = new ArrayList<>();
        if (color[0] > 0) ans.add("RED " + color[0]);
        if (color[1] > 0) ans.add("YELLOW " + color[1]);
        if (color[2] > 0) ans.add("BLUE " + color[2]);
        if (color[3] > 0) ans.add("GREEN " + color[3]);
        if (color[4] > 0) ans.add("BLACK " + color[4]);
        if (color[5] > 0) ans.add("WHITE " + color[5]);
        return ans;
    }

    @Override
    public List<String> getObjectByVolume() {
        List<String> ans = new ArrayList<>();
        if (getObjectCount() == 1) ans.add(graphicObjects.get(0).toString());
        else {
            boolean flag = false;
            while (!flag) {
                flag = true;
                for (int i = 0; i < getObjectCount() - 1; i++) {
                    int j = i + 1;
                    if (graphicObjects.get(i).volume() > graphicObjects.get(j).volume()) {
                        Collections.swap(graphicObjects, i, j);
                    }
                }
                for (int i = 0; i < getObjectCount() - 1; i++) {
                    int j = i + 1;
                    if (graphicObjects.get(i).volume() > graphicObjects.get(j).volume()) {
                        flag = false;
                        break;
                    }
                }
            }
            for (int i = 0; i < getObjectCount(); i++)
                ans.add(graphicObjects.get(i).toString());
        }
        return ans;
    }

    @Override
    public List<String> getObjectsBySurface() {
        List<String> ans = new ArrayList<>();
        if (getObjectCount() == 1) ans.add(graphicObjects.get(0).toString());
        else {
            boolean flag = false;
            while (!flag) {
                flag = true;
                for (int i = 0; i < getObjectCount() - 1; i++) {
                    int j = i + 1;
                    if (graphicObjects.get(i).surfaceMeanSize() > graphicObjects.get(j).surfaceMeanSize()) {
                        Collections.swap(graphicObjects, i, j);
                    }
                }
                for (int i = 0; i < getObjectCount() - 1; i++) {
                    int j = i + 1;
                    if (graphicObjects.get(i).surfaceMeanSize() > graphicObjects.get(j).surfaceMeanSize()) {
                        flag = false;
                        break;
                    }
                }
            }
            for (int i = 0; i < getObjectCount(); i++)
                ans.add(graphicObjects.get(i).toString());
        }
        return ans;
    }

    @Override
    public double getWaterInjected(double area, double height) {
        double sum = 0;
        for (int i = 0; i < graphicObjects.size(); i++) {
            sum += graphicObjects.get(i).volume();
        }
        return area * height - sum;
    }
}
