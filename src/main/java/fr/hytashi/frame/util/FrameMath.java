package fr.hytashi.frame.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FrameMath {

    /**
     * Checks whether a point is to the left/on/to the right of a line
     *
     * @param line1 a point on the line
     * @param line2 another point on the line
     * @param point the point to test
     * @return >0 if the point is to the left of the line<br>
     * 0 if the point is on the line<br>
     * < 0 if the point is to the right of the line
     */
    public int isLeft(int[] line1, int[] line2, int[] point) {
        return ((line2[0] - line1[0]) * (point[1] - line1[1]) - (point[0] - line1[0]) * (line2[1] - line1[1]));
    }

}
