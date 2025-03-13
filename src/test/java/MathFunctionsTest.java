import net.jqwik.api.*;
import net.jqwik.api.constraints.Positive;
import org.example.MathFunctions;
import org.junit.jupiter.api.Assertions;

public class MathFunctionsTest {
    @Property
    void testMultiplyByTwoIsCorrect(@ForAll int number) {
        int result = MathFunctions.multiplyByTwo(number);
        Assertions.assertEquals(number * 2, result, "A função deve sempre retornar o dobro do número");
    }

    @Property
    void testMultiplyByTwoKeepsEvenNumbersEven(@ForAll("evenNumbers") int evenNumber) {
        int result = MathFunctions.multiplyByTwo(evenNumber);
        Assertions.assertEquals(0, result % 2, "O dobro de um número par deve ser par");
    }

    @Provide
    Arbitrary<Integer> evenNumbers() {
        return Arbitraries.integers().filter(n -> n % 2 == 0);
    }
}
