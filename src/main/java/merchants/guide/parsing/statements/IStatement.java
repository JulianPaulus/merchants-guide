package merchants.guide.parsing.statements;

import merchants.guide.parsing.ParsingRegistry;
import merchants.guide.parsing.StatementParsingException;

public interface IStatement {

	void parse(final ParsingRegistry parsingRegistry, final String line) throws StatementParsingException;

	boolean matches(final String line);

}
