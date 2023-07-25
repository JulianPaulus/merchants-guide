package merchants.guide.parsing.statements;

import merchants.guide.parsing.AlienUtil;
import merchants.guide.parsing.ParsingRegistry;
import merchants.guide.parsing.Unit;
import merchants.guide.parsing.UnitConverter;

import java.util.regex.Pattern;

public class ConversionStatement extends TranslatingStatement {
	private static final Pattern pattern = Pattern.compile("(?:\\w+\\s+){2,}is\\s+[0-9]+\\s+\\w+");

	@Override
	public void parseInternal(final ParsingRegistry parsingRegistry, final String line) {
		final String[] mainSplit = line.split("\\s+is\\s+");

		final String[] from = mainSplit[0].split("\\s+");
		final Unit fromUnit = parseUnit(from);

		final double fromValue = AlienUtil.parseAlienNumber(parsingRegistry, from);

		final String[] to = mainSplit[mainSplit.length - 1].split("\\s+");
		final double toValue = Double.parseDouble(to[0]);

		parsingRegistry.registerUnitConverters(new UnitConverter(fromUnit, toValue / fromValue));
	}

	private static Unit parseUnit(final String[] from) {
		final String unitStr = from[from.length - 1];
		return Unit.valueOf(unitStr.toUpperCase());
	}

	@Override
	public boolean matches(final String line) {
		return pattern.matcher(line).find();
	}
}
