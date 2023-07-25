package merchants.guide.parsing.statements;

import merchants.guide.parsing.ParsingRegistry;
import merchants.guide.parsing.StatementParsingException;

public abstract class TranslatingStatement extends AbstractStatement {

	@Override
	public void parse(ParsingRegistry parsingRegistry, String line) throws StatementParsingException {
		if (parsingRegistry.getAliasMappers().isEmpty()) {
			throw new StatementParsingException("TranslatingStatement requires mappers to function.");
		}

		super.parse(parsingRegistry, line);
	}
}
