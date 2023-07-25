package merchants.guide.rules;

import merchants.guide.RomanNumeral;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class NoSubtractionRule extends SubtractionRule {

	public NoSubtractionRule(final RomanNumeral subtrahend) {
		super(subtrahend, Collections.emptySet());
	}

}
