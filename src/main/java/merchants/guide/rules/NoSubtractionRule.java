package merchants.guide.rules;

import merchants.guide.RomanNumeral;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class NoSubtractionRule implements IRule {

	private final Set<String> testStrings;

	private final String name;

	public NoSubtractionRule(final RomanNumeral subtrahend) {
		testStrings = Arrays.stream(RomanNumeral.values())
			.filter(numeral -> !numeral.smallerThan(subtrahend))
			.map(minuend -> subtrahend.toString() + minuend.toString())
			.collect(Collectors.toSet());

		this.name = "NoSubtractionRule(" + subtrahend + ")";
	}

	@Override
	public boolean check(final String input) {
		return testStrings.stream().noneMatch(input::contains);
	}

	@Override
	public String getName() {
		return null;
	}
}
