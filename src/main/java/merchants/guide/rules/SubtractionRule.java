package merchants.guide.rules;

import merchants.guide.RomanNumeral;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SubtractionRule implements IRule {

	private final Set<String> testStrings;

	private final String name;

	public SubtractionRule(final RomanNumeral subtrahend, final Set<RomanNumeral> minuends) {
		testStrings = Arrays.stream(RomanNumeral.values())
			.filter(numeral -> !minuends.contains(numeral) && numeral != subtrahend)
			.map(minuend -> subtrahend.toString() + minuend.toString())
			.collect(Collectors.toSet());

		this.name = "SubtractionRule(" + subtrahend + ")";
	}

	@Override
	public boolean check(final String input) {
		return testStrings.stream().noneMatch(input::contains);
	}

	@Override
	public String getName() {
		return name;
	}
}
