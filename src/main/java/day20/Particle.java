package day20;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Particle {

    Point position;
    Point velocity;
    Point acceleration;

    static Particle parse(String inputLine) {


        String pStr = inputLine.substring(3, inputLine.indexOf(">, "));
        inputLine = inputLine.substring(inputLine.indexOf(pStr) + pStr.length() + 3, inputLine.length());

        String vStr = inputLine.substring(3, inputLine.indexOf(">, "));
        inputLine = inputLine.substring(inputLine.indexOf(vStr) + vStr.length() + 3, inputLine.length());

        String aStr = inputLine.substring(3, inputLine.indexOf(">"));

        Point p = new Point(pStr);
        Point v = new Point(vStr);
        Point a = new Point(aStr);

        return new Particle(p, v, a);
    }
}
