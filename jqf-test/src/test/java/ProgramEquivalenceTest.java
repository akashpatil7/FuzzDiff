import classes.*;
import classes.Program1;
import classes.Program2;
import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.generator.Size;
import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import generators.*;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import benchmarks.Ell.rc.Eq.*;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@RunWith(JQF.class)
public class ProgramEquivalenceTest {

    private static Class<?> original;
    private static Class<?> refactored;
    private static String method;

    @BeforeClass
    public static void setup() throws ClassNotFoundException {

        original = Class.forName("classes."+System.getProperty("original"));
        refactored = Class.forName("classes."+System.getProperty("refactored"));
        method = System.getProperty("methodName");
        System.out.println("Original: "+ original);
        System.out.println("Refactored: "+ refactored);
        System.out.println("Method: "+ method);
    }

    @Fuzz
    public void fuzzTestForEquivalence(int[] param) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        Object program1 = original.getDeclaredConstructor().newInstance();
        Object program2 = refactored.getDeclaredConstructor().newInstance();

        //Create a clone of the object, do the first call
        Object clone1 = SerializationUtils.clone(param);
        Method originalMethod = original.getDeclaredMethod(method, int[].class);
        Object x = originalMethod.invoke(program1, (Object) clone1);

        //Create another clone for program 2 for second call
        Object clone2 = SerializationUtils.clone(param);
        Method refactoredMethod = refactored.getDeclaredMethod(method, int[].class);
        Object y = refactoredMethod.invoke(program2, (Object) clone2);

        //Check if the clones are equal
        Assert.assertTrue("The state of the fuzz object must be same after method execution", EqualsBuilder.reflectionEquals(clone1,clone2));

        //Check if final output of the clones is same only if test method is not void
        if(!originalMethod.getReturnType().equals(Void.TYPE)){
            assertEquals("Final output must be same", x, y);
        }

        if(program1.getClass().getDeclaredFields().length != 0 || program2.getClass().getDeclaredFields().length != 0) {
            List<Field> fields1 = Arrays.asList(program1.getClass().getDeclaredFields());
            List<Field> fields2 = Arrays.asList(program2.getClass().getDeclaredFields());

            assertEquals("Number of global fields must be same", fields1.size(), fields2.size());

            List<Object> values1 = new ArrayList<>();
            List<Object> values2 = new ArrayList<>();

            for (Field f : fields1) {
                values1.add(f.get(program1));
            }

            for (Field f : fields2) {
                values2.add(f.get(program2));
            }

            assertEquals("Global fields must be same", values1, values2);
        }
    }

    @Test
    public void fuzzTestForEquivalenceWithNoArgs() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object program1 = original.getDeclaredConstructor().newInstance();
        Object program2 = refactored.getDeclaredConstructor().newInstance();

        Method originalMethod = original.getDeclaredMethod(method);
        Object x = originalMethod.invoke(program1);
        //int x = program1.largest(arr1);

        Method refactoredMethod = refactored.getDeclaredMethod(method);
        Object y = refactoredMethod.invoke(program2);

        //Check if final output of the clones is same only if test method is not void
        if(!original.getDeclaredMethod(method).getReturnType().equals(Void.TYPE)){
            assertEquals("Final output must be same", x, y);
        }

        if(program1.getClass().getDeclaredFields().length != 0 || program2.getClass().getDeclaredFields().length != 0) {
            List<Field> fields1 = Arrays.asList(program1.getClass().getDeclaredFields());
            List<Field> fields2 = Arrays.asList(program2.getClass().getDeclaredFields());

            assertEquals("Number of global fields must be same", fields1.size(), fields2.size());

            List<Object> values1 = new ArrayList<>();
            List<Object> values2 = new ArrayList<>();

            for (Field f : fields1) {
                values1.add(f.get(program1));
            }

            for (Field f : fields2) {
                values2.add(f.get(program2));
            }

            assertEquals("Global fields must be same", values1, values2);
        }
    }

}
