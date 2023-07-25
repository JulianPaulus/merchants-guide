package merchants.guide.parsing;

import merchants.guide.parsing.statements.AliasStatement;
import merchants.guide.parsing.statements.ConversionQuestionStatement;
import merchants.guide.parsing.statements.ConversionStatement;
import merchants.guide.parsing.statements.IStatement;
import merchants.guide.parsing.statements.QuestionStatement;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StatementFactory {

	public static Set<IStatement> getStatements() {
		final Set<IStatement> statements = new HashSet<>(Arrays.asList(
			new AliasStatement(),
			new ConversionStatement(),
			new QuestionStatement(),
			new ConversionQuestionStatement()
		));

		return statements;
	}

}
