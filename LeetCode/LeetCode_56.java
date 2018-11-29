package LeetCode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LeetCode_56 {

    //(1,3) (2,6) (8,4)(4,5)


    public static void main(String[] args){
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1,3));
        intervals.add(new Interval(2,6));
        intervals.add(new Interval(8,10));
        intervals.add(new Interval(15,18));
        List<Interval> result = new LeetCode_56().merge(intervals);
        for (Interval interval : result){
            System.out.print("(" + interval.start + "," + interval.end + "),");
        }
    }



    public static class Interval{
        int start;
        int end;
        Interval(){
            start = 0;
            end = 0;
        }
        Interval(int s,int e){
            start = s;
            end = e;
        }
    }

    public List<Interval> merge(List<Interval> intervals){
        if (intervals.size() <=1){
            return intervals;
        }
        intervals.sort(Comparator.comparingInt(o -> o.start));
        List<Interval> result = new ArrayList<>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        for (Interval interval : intervals){
            if (interval.start < end){
                end = Math.max(interval.end,end);
            }else{
                result.add(new Interval(start,end));
                start = interval.start;
                end = interval.end;
            }
        }
        result.add(new Interval(start,end));
        return result;
    }
}
