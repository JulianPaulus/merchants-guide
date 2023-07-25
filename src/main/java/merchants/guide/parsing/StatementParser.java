package merchants.guide.parsing;

import merchants.guide.RomanToDecimalConverter;
import merchants.guide.parsing.statements.IStatement;

import java.util.Optional;
import java.util.Set;

public class StatementParser {

	private final Set<IStatement> statements;

	public StatementParser(final Set<IStatement> statements) {
		this.statements = statements;
	}

	public ParsingRegistry parse(final RomanToDecimalConverter romanToDecimalConverter, final String[] lines) {

		final ParsingRegistry parsingRegistry = new ParsingRegistry(romanToDecimalConverter);

		for (final String line : lines) {
			Optional<IStatement> statement = statements.stream().filter(stmt -> stmt.matches(line)).findFirst();

			if (statement.isPresent()) {
				try {
					statement.get().parse(parsingRegistry, line);
				} catch (final StatementParsingException | IllegalArgumentException e) {
					parsingRegistry.registerAnswer(e.getMessage());
				}
			} else {
				parsingRegistry.registerAnswer("I have no idea what you are talking about");
			}
		}

		return parsingRegistry;
	}

}
