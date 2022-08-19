import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class AdditionalChecksTest {

    @Test
    public void testEquivalence() {

        File tempFile = new File("target/fuzz-results/ProgramEquivalenceTest/fuzzTestForEquivalence/failures/id_000000");
        if(tempFile.exists()) {
            Process p = null;
            try {
                p = Runtime.getRuntime().
                        exec("C:/Maven/apache-maven-3.8.3/bin/mvn.cmd jqf:repro -Dclass=ProgramEquivalenceTest -Dmethod=fuzzTestForEquivalence -Dinput=target/fuzz-results/ProgramEquivalenceTest/fuzzTestForEquivalence/failures -DprintArgs");
                p.waitFor();
                copy(p.getInputStream(), System.out);
            } catch (IOException | InterruptedException e) {
                System.err.println("Error on exec() method");
                e.printStackTrace();
            }
        }

        assertFalse("Two equivalent programs must not have test failures",tempFile.exists());

    }

//    @Test
//    public void testSameNumberOfCalls(){
//        BufferedReader br = null;
//
//        List<String> classStackTrace = new ArrayList<>();
//
//        try{
//            br = new BufferedReader(new FileReader("coverage.txt"));
//            String contentLine = br.readLine();
//            while(contentLine != null){
//                //System.out.println(contentLine);
//                Pattern p = Pattern.compile("\\((-|\\d*)\\).classes");
//                Matcher m = p.matcher(contentLine);
//                if (m.find()){
//                    if(contentLine.contains("-->")) {
//                        String[] arr = contentLine.split("-->");
//                        classStackTrace.add(arr[1]);
//                    }
//                }
//                contentLine = br.readLine();
//            }
//
//            System.out.println(classStackTrace);
//
//            assertEquals("The test method should have same number of calls", 0, classStackTrace.size() % 2);
//
//            br.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    public void testCallingBehaviour(){
        BufferedReader br = null;

        List<String> classStackTrace = new ArrayList<>();

        try{
            br = new BufferedReader(new FileReader("coverage.txt"));
            String contentLine = br.readLine();
            while(contentLine != null){
                //System.out.println(contentLine);
                Pattern p = Pattern.compile("\\((-|\\d*)\\).classes");
                Matcher m = p.matcher(contentLine);
                if (m.find()){
                    if(contentLine.contains("-->")) {
                        String[] arr = contentLine.split("-->");
                        classStackTrace.add(arr[1]);
                    }
                }
                contentLine = br.readLine();
            }

            assertEquals("The test method should have same number of calls", 0, classStackTrace.size() % 2);

            List<String> slice1 = new ArrayList(classStackTrace.subList(0, classStackTrace.size()/2));
            System.out.println("Order of calls in original method: " + slice1);

            List<String> slice2 = new ArrayList(classStackTrace.subList(classStackTrace.size()/2, classStackTrace.size()));
            System.out.println("Order of calls in refactored method: " + slice2);

            assertEquals("The order of calls must be same", slice1, slice2);

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void testNumberOfArguments(){
//        BufferedReader br = null;
//
//        List<String> testStackTrace = new ArrayList<>();
//
//        try{
//            br = new BufferedReader(new FileReader("coverage.txt"));
//            String contentLine = br.readLine();
//            while(contentLine != null){
//                //System.out.println(contentLine);
//                if(contentLine.contains("ProgramEquivalenceTest#fuzzTestForEquivalence()") &&
//                contentLine.contains("-->")){
//                    String[] arr = contentLine.split("-->");
//                    testStackTrace.add(arr[1]);
//                }
//                contentLine =  br.readLine();
//            }
//
//            //System.out.println(testStackTrace);
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
//            //System.out.println(noOfArgs);
//            if(noOfArgs.size() > 2){
//                System.out.println("Invalid scenario");
//            } else{
//                assertEquals("Arguments must be same", noOfArgs.get(0), noOfArgs.get(1));
//            }
//            br.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    static void copy(InputStream in, OutputStream out) throws IOException {
        while (true) {
            int c = in.read();
            if (c == -1)
                break;
            out.write((char) c);
        }
    }
}
