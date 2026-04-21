package dsa.dp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FibonacciDP {
    public static void main(String[] args) {
        int n = 10;
        System.out.println("Series : " + generateFibonacciSeries(n));
        System.out.println("nth element : " + nthFibonacciIterative(10));
        System.out.println("Sum : " + sumFibonacciSeries(n));
    }

    private static List<Integer> generateFibonacciSeries(int n) {
        List<Integer> series = new ArrayList<>();

        if (n <= 0) {
            return series;
        }

        series.add(0);
        if (n == 1) {
            return series;
        }

        series.add(1);
        for (int i = 2; i < n; i++) {
            int next = series.get(i - 1) + series.get(i - 2);
            series.add(next);
        }

        return series;
    }

    private static int sumFibonacciSeries(int n) {

        if(n<=1) {
//            System.out.println();
            return n;
        }
        return sumFibonacciSeries(n-1) + sumFibonacciSeries(n-2);
    }
//    private static List<Long> generateFibonacciSeries(int n) {
//        List<Long> series = new ArrayList<>();
//
//        if (n <= 0) {
//            return series;
//        }
//
//        series.add(0L);
//        if (n == 1) {
//            return series;
//        }
//
//        series.add(1L);
//        for (int i = 2; i < n; i++) {
//            long next = series.get(i - 1) + series.get(i - 2);
//            series.add(next);
//        }
//
//        return series;
//    }

    private static long nthFibonacciIterative(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }
        if (n > 92) {
            throw new IllegalArgumentException("n must be <= 92 for long without overflow");
        }
        if (n <= 1) {
            return n;
        }

        long prev = 0;
        long curr = 1;
        for (int i = 2; i <= n; i++) {
            long next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }

    private static BigInteger nthFibonacciFastDoubling(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }
        return fastDoublingPair(n)[0];
    }

    // Returns [F(n), F(n+1)] using fast doubling.
    private static BigInteger[] fastDoublingPair(int n) {
        if (n == 0) {
            return new BigInteger[]{BigInteger.ZERO, BigInteger.ONE};
        }

        BigInteger[] pair = fastDoublingPair(n / 2);
        BigInteger a = pair[0]; // F(k)
        BigInteger b = pair[1]; // F(k+1)

        BigInteger c = a.multiply(b.shiftLeft(1).subtract(a)); // F(2k)
        BigInteger d = a.multiply(a).add(b.multiply(b)); // F(2k+1)

        if ((n & 1) == 0) {
            return new BigInteger[]{c, d};
        }
        return new BigInteger[]{d, c.add(d)};
    }
}
