package classes;

import generators.Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

    public class Program3 {

        public int max_num = Integer.MIN_VALUE;

        public int largest(int[] arr){

            // Initialize an ArrayList
            List<Integer> list=new ArrayList<>();

            Main.testMethod1(max_num);
            Main.testMethod2(max_num);

            // Add all array elements to the list
            for (int j : arr) {
                list.add(j);
            }

            max_num = Collections.max(list);
            return Collections.max(list);
        }
    }

