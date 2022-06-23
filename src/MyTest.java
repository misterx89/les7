public class MyTest {
    @BeforeSuite
    public static void test2() {
        System.out.println("Test 2 BeforeSuite");
    }

    @Test
    public static void test1() {
        System.out.println("Test 1");
    }

    @Test
    public static void test7() {
        System.out.println("Test 7");
    }

    @Test (priority = 2)
    public static void test3() {
        System.out.println("Test 3 priority = 2");
    }

    @Test (priority = 5)
    public static void test5() {
        System.out.println("Test 5 priority = 5");
    }

    @AfterSuite
    public static void test6() {
        System.out.println("Test 6 AfterSuite");
    }
}
