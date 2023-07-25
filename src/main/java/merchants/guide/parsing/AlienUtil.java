package merchants.guide.parsing;

import java.util.Arrays;

public class AlienUtil {

	private AlienUtil() {}

	public static String alienToRoman(final ParsingRegistry registry, String value) {
		for (final AliasMapper mapper : registry.getAliasMappers()) {
			value = mapper.map(value);
		}
		return value;
	}

	public static double parseAlienNumber(final ParsingRegistry parsingRegistry, final String[] fromSplit) {
		final String alienNumber = String.join("", Arrays.copyOfRange(fromSplit, 0, fromSplit.length - 1));
		final String romanNumber = AlienUtil.alienToRoman(parsingRegistry, alienNumber);
		return parsingRegistry.getRomanToDecimalConverter().convert(romanNumber);
	}

}
