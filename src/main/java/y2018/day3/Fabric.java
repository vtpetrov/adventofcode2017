package y2018.day3;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class Fabric {

    private static final int FABRIC_SIZE = 1000;
    private String[][] data = new String[FABRIC_SIZE][FABRIC_SIZE];
    private int overlaps = 0;

    Fabric() {
        for (String[] row : data) {
            Arrays.fill(row, ".");
        }
    }


    void drawClaim(Claim claim){
        System.out.println("drawing Claim => " + claim);

        for(int i = claim.getTopOffset(); i < (claim.getTopOffset() + claim.getHeight()); i++){
            for(int j = claim.getLeftOffset(); j < (claim.getLeftOffset() + claim.getWidth()); j++){
                if (data[i][j].equals(".")){
                    data[i][j] = String.valueOf(claim.getId());
                } else if (!data[i][j].equals("#")){
                    data[i][j] = "#";
                    overlaps++;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        for (String[] row : data) {
            for (String val : row) {
                sb.append(val).append(' ');
            }
            sb.append("|\n");
        }

        return sb.toString();
    }
}
