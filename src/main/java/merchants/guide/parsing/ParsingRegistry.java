package merchants.guide.parsing;

import merchants.guide.RomanToDecimalConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParsingRegistry {

	private final Set<AliasMapper> aliasMappers = new HashSet<>();
	private final Map<Unit, UnitConverter> unitConverters = new HashMap<>();
	private final List<String> answers = new ArrayList<>();

	private final RomanToDecimalConverter romanToDecimalConverter;

	public ParsingRegistry(final RomanToDecimalConverter romanToDecimalConverter) {
		this.romanToDecimalConverter = romanToDecimalConverter;
	}

	public void registerAliasMapper(final AliasMapper mapper) {
		aliasMappers.add(mapper);
	}

	public void registerUnitConverters(final UnitConverter unitConverter) {
		unitConverters.put(unitConverter.getFromUnit(), unitConverter);
	}

	public void registerAnswer(final String answer) {
		this.answers.add(answer);
	}

	public Set<AliasMapper> getAliasMappers() {
		return aliasMappers;
	}

	public Map<Unit, UnitConverter> getUnitConverters() {
		return unitConverters;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public RomanToDecimalConverter getRomanToDecimalConverter() {
		return romanToDecimalConverter;
	}
}
