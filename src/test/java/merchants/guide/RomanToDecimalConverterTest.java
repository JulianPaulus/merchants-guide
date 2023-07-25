package merchants.guide;

import merchants.guide.rules.IRule;
import merchants.guide.rules.MaxCountRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RomanToDecimalConverterTest {

	private static Stream<Arguments> testCases() {
		return Stream.of(
			Arguments.of("MCMIII", 1903), // spec test
			Arguments.of("mcmiii", 1903), // lowercase test
			Arguments.of("IX", 9),
			Arguments.of("MCMXLIV", 1944),
			Arguments.of("MMVI", 2006)
		);
	}
	@ParameterizedTest
	@MethodSource("testCases")
	void romanToDecimalTest(final String input, final int expected) {
		final RomanToDecimalConverter converter = new RomanToDecimalConverter(RulesFactory.getRules());

		assertEquals(expected, converter.convert(input));
	}

	@Test
	void shouldValidateRules() {
		final Set<IRule> rules = new HashSet<>(List.of(new MaxCountRule(RomanNumeral.I, 3)));

		final RomanToDecimalConverter converter = new RomanToDecimalConverter(rules);

		assertThrows(
			IllegalArgumentException.class,
			() -> converter.convert("IIII")
		);
	}

}
