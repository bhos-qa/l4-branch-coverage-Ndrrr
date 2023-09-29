## Line Coverage
### Line coverage is a measure of how many lines of code are executed when running the test suite. It is the simplest form of coverage, and is the most commonly used metric. If a test suite has 100 lines of code, and 80 lines are executed when running the test suite, then the line coverage is 80%.
## Branch Coverage
### Branch coverage is a measure of how many branches(for example, if/else cases) are executed when running the test suite.
## Predicate Coverage
### Predicate coverage is a measure of how many predicates are executed when running the test suite.
In the given example, testing case when a is 1, b and c are some random number, only one-third of predicate will be covered.
For branch coverage half of the branches only will be covered.
However, only one line will be uncovered.

```java
public class Test {
    public void fun1(int a, int b, int c) {
        if (a == 1 || b == 2 || c == 3) {
            fun2();
        } else {
            fun2();
        }
    }
    
    public void fun2() {
        System.out.println("f2");
        // some other code
    }
}
```