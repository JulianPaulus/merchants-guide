package merchants.guide.parsing.statements;

import merchants.guide.parsing.AliasMapper;
import merchants.guide.parsing.ParsingRegistry;
import merchants.guide.parsing.StatementParsingException;

import java.util.regex.Pattern;

public class AliasStatement extends AbstractStatement {

	private static final Pattern pattern = Pattern.compile("\\w+\\s+is\\s+[A-Z]");

	@Override
	public void parseInternal(final ParsingRegistry registry, final String line) throws StatementParsingException {
		final String[] split = line.split("\\s+is\\s+");

		if (split.length != 2) {
			throw new StatementParsingException("AliasStatement requires 2 parameters, but received " + split.length);
		}

		registry.registerAliasMapper(new AliasMapper(split[0], split[1]));
	}

	@Override
	public boolean matches(final String line) {
		return pattern.matcher(line).find();
	}
}
