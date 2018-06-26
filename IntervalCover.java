import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Tomas Polacek on 04-Feb-18.
 * TDDD95 - Lab1: Interval Cover
 */

public class IntervalCover {

    public static void main(String[] args) {

        Kattio kattio = new Kattio(System.in);

        while (kattio.hasMoreTokens()) {

            //Interval to be covered
            Interval coverInterval = new Interval(kattio.getDouble(), kattio.getDouble(), -1);

            //Intervals to cover
            int intervalsAmount = kattio.getInt();
            List<Interval> intervals = new ArrayList<>();
            for (int i = 0; i < intervalsAmount; i++) {
                intervals.add(new Interval(kattio.getDouble(), kattio.getDouble(), i));
            }

            //Solve the coverage
            List<Integer> cover = cover(coverInterval, intervals);
            if (cover.isEmpty()) {
                kattio.println("impossible");
            } else {
                kattio.println(cover.size());
                for (int i : cover) {
                    kattio.print(i + " ");
                }
                kattio.println();
            }
        }

        kattio.close();

    }

    /**
     * Find the intervals to cover the given interval if possible
     *
     * @param coverInterval
     * @param intervals
     * @return list with the interval indexes which cover the interval
     */
    private static List<Integer> cover(Interval coverInterval, List<Interval> intervals) {

        //Sort intervals according to bottom value
        intervals = intervals
                .stream()
                .sorted(Comparator.comparingDouble(Interval::getBottom))
                .collect(Collectors.toList());

        List<Integer> coverageList = new ArrayList<>();
        double upperBound = coverInterval.getTop();

        while (upperBound > coverInterval.getBottom() || coverInterval.isOneElement()) {

            int boundCheck = -1;

            for (int i = 0; i < intervals.size(); i++) {
                if (intervals.get(i).getTop() < upperBound) {
                    continue;
                }
                //No interval combination can cover the whole interval to be covered
                if (intervals.get(i).getBottom() > upperBound) {
                    return new ArrayList<>();
                }
                boundCheck = i;
                upperBound = intervals.get(i).getBottom();
                break;
            }

            if (boundCheck != -1) {
                coverageList.add(intervals.get(boundCheck).getIndex());
                intervals.remove(boundCheck);
            } else {
                //All intervals have bigger bottom value
                return new ArrayList<>();
            }

            if (coverInterval.isOneElement()) {
                break;
            }

        }

        if (upperBound > coverInterval.getBottom()) {
            return new ArrayList<>();
        }

        return coverageList;
    }

}

//Interval representation
class Interval {

    private double bottom;
    private double top;
    private int index;

    Interval(double bottom, double top, int index) {
        this.bottom = bottom;
        this.top = top;
        this.index = index;
    }

    public double getBottom() {
        return bottom;
    }

    public double getTop() {
        return top;
    }

    public int getIndex() {
        return index;
    }

    public boolean isOneElement() {
        return bottom == top;
    }

}
