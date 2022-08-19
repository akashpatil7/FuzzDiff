package classes;

import generators.Main;

    public class Program1 {
        public int max_num = Integer.MIN_VALUE;

        public int largest(int[] arr){

            int max = arr[0];

            // Mock call invocations for demonstration purposes
            Main.testMethod1(max);
            Main.testMethod2(max);

            // Traverse array elements from second and
            // compare every element with current max
            for (int j : arr) {
                if (j > max) {
                    max = j;
                }
                max_num = max;
            }
            return max;
        }
    }


