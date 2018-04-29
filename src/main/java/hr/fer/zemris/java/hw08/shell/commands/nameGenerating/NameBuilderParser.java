package hr.fer.zemris.java.hw08.shell.commands.nameGenerating;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NameBuilderParser {

	char[] expression;
	ExecutorNameBuilder executor;

	public NameBuilderParser(String expression) {
		this.expression = Objects.requireNonNull(expression).toCharArray();
		parse();
	}

	private void parse() {
		List<NameBuilder> list = new ArrayList<>();

		int index = 0;

		while (index < expression.length) {
			StringBuilder builder = new StringBuilder();

			while (expression[index] != '$') {
				builder.append(expression[index++]);
			}
			
			
			
		}

	}

	public NameBuilder getNameBuilder() {
		return executor;
	}
}
