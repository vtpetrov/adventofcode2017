package day20;

import lombok.Data;

@Data
public class Point {

    int x;
    int y;
    int z;
    int distanceToCenter;

    public Point(String input) {
        String[] inputArr = input.split(",");
        this.x = Integer.parseInt(inputArr[0]);
        this.y = Integer.parseInt(inputArr[1]);
        this.z = Integer.parseInt(inputArr[2]);

        this.distanceToCenter = Math.abs(x) + Math.abs(y) + Math.abs(z);
    }
}
