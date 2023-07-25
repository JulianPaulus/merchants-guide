package merchants.guide.parsing.statements;

import merchants.guide.parsing.ParsingRegistry;
import merchants.guide.parsing.StatementParsingException;

public abstract class AbstractStatement implements IStatement {

	@Override
	public void parse(final ParsingRegistry parsingRegistry, final String line) throws StatementParsingException {
		if (!matches(line)) {
			throw new StatementParsingException("Statement must match to be parsed!");
		}
		parseInternal(parsingRegistry, line);
	}

	protected abstract void parseInternal(final ParsingRegistry parsingRegistry, final String line)
		throws StatementParsingException;
}
