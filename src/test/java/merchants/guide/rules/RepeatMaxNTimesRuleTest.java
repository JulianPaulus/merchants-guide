package merchants.guide.rules;

import merchants.guide.RomanNumeral;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepeatMaxNTimesRuleTest {

	private static Stream<Arguments> testCases() {
		return Stream.of(
			Arguments.of("XXX", RomanNumeral.X, 3, true),
			Arguments.of("XXXIX", RomanNumeral.X, 3, true),
			Arguments.of("XXXXIX", RomanNumeral.X, 3, false),
			Arguments.of("XXXXIX", RomanNumeral.I, 3, true),
			Arguments.of("XXXXIX", RomanNumeral.X, 4, true)
		);
	}
	@ParameterizedTest
	@MethodSource("testCases")
	void repeatMaxNTimesRuleTest(final String input, final RomanNumeral numeral, final int maxRepeats, final boolean expected) {
		final IRule rule = new RepeatMaxNTimesRule(numeral, maxRepeats);

		assertEquals(expected, rule.check(input));
	}

}
