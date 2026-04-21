package streams;

import java.util.ArrayList;
import java.util.List;

public class MoveZerosToRight {

    public static void main(String[] args) {
        int[] integers = new int[]{-1,0,3,4,0,4,2,-2,0,9,-9,6,0,3};
        System.out.println("Zeros to the right : " + moveZerosToRight(integers));
    }

    private static List<Integer> moveZerosToRight(int[] integers) {
//        int zeroIndex = -1;
        int nonZeroIndex = 0;

        // Move non-zero elements to the front
        for (int i = 0; i < integers.length; i++) {
            if (integers[i] != 0) {
                swap(i, nonZeroIndex, integers);
                nonZeroIndex++;
            }
        }
//        boolean zeroFound = false;
//
//        while (j < integers.length && i < integers.length) {
//            if (integers[j] == 0) {
//                zeroIndex = j;
//                zeroFound = true;
//            } else if (integers[j] != 0) {
//                nonZeroIndex = j;
//            }
//            if (zeroFound && nonZeroIndex != -1 && zeroIndex < nonZeroIndex) {
//                swap(nonZeroIndex, zeroIndex, integers);
//                zeroFound = false;
//                zeroIndex = -1;
//            }
//            j++;
//
//        }


//        for(int k= 0; k<integers.length;k++) {//0,0,0,0,1,2  //1,0,0,0,0,2
//            if (integers[k] == 0 && !zeroFound) {
//                zeroIndex = k;
//                zeroFound = true;
//
//            }
//            else if (zeroFound && integers[k] != 0 && zeroIndex !=-1) {
//                swap(k,zeroIndex, integers);
//                zeroFound = false;
//                zeroIndex =-1;
//            }
//        }
        List<Integer> result = new ArrayList<>();
        for (int num : integers) {
            result.add(num);
        }
        return result;
    }

    private static void swap(int k, int zeroIndex, int[] integers) {
            int temp = integers[k];
            integers[k] = integers[zeroIndex];
            integers[zeroIndex] = temp;
    }
}
