package classes;

import generators.Main;

import java.util.Arrays;

public class Program2 {

    public int max_num = Integer.MIN_VALUE;

    public int largest(int[] arr) {

        Arrays.sort(arr);
        Main.testMethod2(max_num);
        Main.testMethod1(max_num);
        max_num = arr[arr.length-1];
        return arr[arr.length - 1];
    }
}
