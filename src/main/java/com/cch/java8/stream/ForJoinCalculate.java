package com.cch.java8.stream;

import java.util.concurrent.RecursiveTask;

/**
 * Created by cch
 * 2018-04-30 23:34.
 */

public class ForJoinCalculate extends RecursiveTask<Long> {
    private static final long serialVersionUID=1L;
    private long start;
    private long end;

    public ForJoinCalculate() {
    }

    public ForJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public static final  long THRESHOLD =10000;
    @Override
    protected Long compute() {
        long length = end -start;
        if(length<THRESHOLD){
            long sum =0;
            for(long i=start;i<end;i++){
                sum+=i;
            }
            return sum;
        }else {
            long middle = (start+end)/2;
            ForJoinCalculate left = new ForJoinCalculate(start,middle);
            left.fork();
            ForJoinCalculate right = new ForJoinCalculate(middle+1,end);
            right.fork();
            return left.join()+right.join();
        }

    }
}
