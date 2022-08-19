package generators;

import classes.Program1;
import classes.Program2;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        Process p = null;
        try{
            p = Runtime.getRuntime().
                    exec("C:/Maven/apache-maven-3.8.3/bin/mvn.cmd test -Dtest=AdditionalChecksTest");

        } catch(IOException e){
            System.err.println("Error on exec() method");
            e.printStackTrace();
        }

        String output = copy(p.getInputStream());

        if(output.contains("BUILD SUCCESS")){
            System.out.println("The two programs are equivalent");
        }
        else{
            System.out.println("The two programs are not equivalent");
            System.out.println(output);
        }




//        BufferedReader br = null;
//
//        List<String> testStackTrace = new ArrayList<>();
//
//        try{
//            br = new BufferedReader(new FileReader("coverage.txt"));
//            String contentLine = br.readLine();
//            while(contentLine != null){
//                //System.out.println(contentLine);
//                if(contentLine.contains("ProgramEquivalenceTest#testFinalOutput()")){
//                    String[] arr = contentLine.split("-->");
//                    testStackTrace.add(arr[1]);
//                }
//                contentLine = br.readLine();
//            }
//
//            List<String> noOfArgs = new ArrayList<>();
//            for(String s: testStackTrace) {
//                Matcher m = Pattern.compile("\\((.*?)\\)").matcher(s);
//                while (m.find()) {
//                    if(!m.group(1).isEmpty()){
//                        noOfArgs.add(m.group(1));
//                    }
//                }
//            }
//
//            if(noOfArgs.size() > 2){
//                System.out.println("Invalid scenario");
//            } else{
//                if(noOfArgs.get(0).equals(noOfArgs.get(1))){
//                   System.out.println("Same number of arguments");
//                }
//                else{
//                    System.out.println("Unequal number of arguments");
//                }
//            }
//
////            for(String s: testStackTrace){
////                if(contentLine.contains("/classes"))
////            }
//
//            //System.out.println(testStackTrace);
//
//            br.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    static String copy(InputStream in) throws IOException {
        String sysout = "";
        while (true) {
            int c = in.read();
            if (c == -1)
                break;
            //out.write((char) c);
            sysout = sysout.concat(Character.toString((char) c));
        }
        return sysout;
    }

    public static void testMethod1(int max){
    }

    public static void testMethod2(int max){
    }

    public static boolean isYes(String in) {
        if (in.contains("no"))
            return false;
        else if (in.startsWith("y"))
            return true;
        else
            return false;
    }

    public static Method[] getAccessibleMethods(Class clazz) {
        List<Method> result = new ArrayList<Method>();
        while (clazz != null) {
            for (Method method : clazz.getDeclaredMethods()) {
                int modifiers = method.getModifiers();
                if (Modifier.isPublic(modifiers) || Modifier.isProtected(modifiers)) {
                    result.add(method);
                }
            }
            clazz = clazz.getSuperclass();
        }
        return result.toArray(new Method[result.size()]);
    }

    public static boolean isPersonAbove18(Person person){

        //String s = Program1.doSomething(person);
        //int i = Program2.isPersonAbove18(person);

        return person.getAge() > 18;
    }

    public static int findMaxPhone(Person person){
        int [] arr = person.getPhones();
        //Initialize max with first element of array.
        int max = arr[0];
        //Loop through the array
        for(int i = 0; i < arr.length; i++) {
            //Compare elements of array with max
            if(arr[i] > max)
                max = arr[i];
        }
        //String s = Program1.doSomething(person);
        //int i = Program2.isPersonAbove18(person);
        return max;
    }

    public static boolean isPersonAbove19(Person person){
        return person.getAge() > 19;
    }

    public class Stack{
        static final int MAX = 1000;
        int top;
        int a[] = new int[MAX];
    }

}
