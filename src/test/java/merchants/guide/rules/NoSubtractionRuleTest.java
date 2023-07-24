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

public class NoSubtractionRuleTest {

	private static Stream<Arguments> testCases() {
		return Stream.of(
			Arguments.of("VX", RomanNumeral.V, false),
			Arguments.of("XV", RomanNumeral.V, true),
			Arguments.of("MCMXLIV", RomanNumeral.V, true)
		);
	}
	@ParameterizedTest
	@MethodSource("testCases")
	void noSubtractionRuleTest(final String input, final RomanNumeral numeral, final boolean expected) {
		final IRule rule = new NoSubtractionRule(numeral);

		assertEquals(expected, rule.check(input));
	}

}
