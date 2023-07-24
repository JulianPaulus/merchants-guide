package merchants.guide.rules;

import merchants.guide.RomanNumeral;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SubtractionRule implements IRule {

	private final Set<String> testStrings;

	public SubtractionRule(final RomanNumeral subtrahend, final Set<RomanNumeral> minuends) {
		testStrings = Arrays.stream(RomanNumeral.values())
			.filter(numeral -> !minuends.contains(numeral))
			.map(minuend -> subtrahend.toString() + minuend.toString())
			.collect(Collectors.toSet());
	}

	@Override
	public boolean check(final String input) {
		return testStrings.stream().noneMatch(input::contains);
	}
}
