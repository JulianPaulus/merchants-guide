package merchants.guide.rules;

import merchants.guide.RomanNumeral;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxCountRuleTest {

	private static Stream<Arguments> testCases() {
		return Stream.of(
			Arguments.of("CX", RomanNumeral.X, 1, true),
			Arguments.of("CXX", RomanNumeral.X, 1, false),
			Arguments.of("CCX", RomanNumeral.X, 1, true),
			Arguments.of("", RomanNumeral.X, 1, true)
		);
	}
	@ParameterizedTest
	@MethodSource("testCases")
	void maxCountRuleTest(final String input, final RomanNumeral numeral, final int maxCount, final boolean expected) {
		final IRule rule = new MaxCountRule(numeral, maxCount);

		assertEquals(expected, rule.check(input));
	}

}
