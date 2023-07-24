package merchants.guide.rules;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OnlyOneSubtractionRuleTest {

	private static Stream<Arguments> testCases() {
		return Stream.of(
			Arguments.of("IVX", false),
			Arguments.of("XVI", true),
			Arguments.of("IV", true)
		);
	}
	@ParameterizedTest
	@MethodSource("testCases")
	void onlyOneSubtractionRuleTest(final String input, final boolean expected) {
		final IRule rule = new OnlyOneSubtractionRule();

		assertEquals(expected, rule.check(input));
	}

}
