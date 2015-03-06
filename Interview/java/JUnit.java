Basic:

1 create a class to be tested 

public class MessageUtil {

    private String message;

    public MessageUtil(String message) {
        this.message = message;
    }

    public String printMessage() {
        System.out.println(message);
        return message;
    }
}



2 create Test Case Class

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestJunit {
    String message = "Hello World";
    MessageUtil messageUtil = new MessageUtil(message);

    public void testPrintMessage() {
        assertEquals(message, messageUtil.printMessage());
    }
}



//You can also create a test suite class to bundle several test cases class together
//Test suite means bundle a few unit test cases and run it together. In JUnit, both @RunWith and @Suite annotation are used to run the suite test.

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
    TestJunit1.class,
    TestJunit2.class
})
public class JunitTestSuite {

}




3 create Test Runner Class

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(Stringp[] args) {
        Result result = JUnitCore.runClasses(TestJunit.class);
        for(Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}


Annotation: Annotations are like meta-tags that you can add to you code and apply them to methods or in class. 

These annotation in JUnit gives us information about test methods , which methods are going to run before & after 

test methods, which methods run before & after all the methods, which methods or class will be ignore during execution.


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class ExecutionProcedureJunit {
    
   //execute only once, in the starting 
   @BeforeClass
   public static void beforeClass() {
      System.out.println("in before class");
   }

   //execute only once, in the end
   @AfterClass
   public static void  afterClass() {
      System.out.println("in after class");
   }

   //execute for each test, before executing test
   @Before
   public void before() {
      System.out.println("in before");
   }
    
   //execute for each test, after executing test
   @After
   public void after() {
      System.out.println("in after");
   }
    
   //test case 1
   @Test
   public void testCase1() {
      System.out.println("in test case 1");
   }

   //test case 2
   @Test
   public void testCase2() {
      System.out.println("in test case 2");
   }
}

Verify the output.

in before class
in before
in test case 1
in after
in before
in test case 2
in after
in after class




@ignore



@Test(timeout=1000)



@Test(expected = ArithmeticException.class)






A Unit Test Case is a part of code which ensures that the another part of code (method) works as expected. To achieve those desired results 

quickly, test framework is required .JUnit is perfect unit test framework for java programming language.

A formal written test-case is characterized by a known input and by an expected output, which is worked out before the test is executed. 

The known input should test a precondition and the expected output should test a postcondition.

There must be at least two test cases for each requirement: one positive test and one negative test. If a requirement has sub-requirements, 

each sub-requirement must have at least two test cases as positive and negative.







