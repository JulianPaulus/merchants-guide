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

public class SubtractionRuleTest {

	private static Stream<Arguments> testCases() {
		return Stream.of(
			Arguments.of("CXL", RomanNumeral.X, new HashSet<>(Arrays.asList(RomanNumeral.L, RomanNumeral.C)), true),
			Arguments.of("XM", RomanNumeral.X, new HashSet<>(Arrays.asList(RomanNumeral.L, RomanNumeral.C)), false),
			Arguments.of("XC", RomanNumeral.X, new HashSet<>(Arrays.asList(RomanNumeral.L, RomanNumeral.C)), true),
			Arguments.of("XD", RomanNumeral.X, new HashSet<>(Arrays.asList(RomanNumeral.L, RomanNumeral.C)), false),
			Arguments.of("VX", RomanNumeral.X, new HashSet<>(Arrays.asList(RomanNumeral.L, RomanNumeral.C)), true),
			Arguments.of("", RomanNumeral.X, new HashSet<>(Arrays.asList(RomanNumeral.L, RomanNumeral.C)), true)
		);
	}
	@ParameterizedTest
	@MethodSource("testCases")
	void subtractionRuleTest(final String input, final RomanNumeral numeral, final Set<RomanNumeral> minuends, final boolean expected) {
		final IRule rule = new SubtractionRule(numeral, minuends);

		assertEquals(expected, rule.check(input));
	}

}
