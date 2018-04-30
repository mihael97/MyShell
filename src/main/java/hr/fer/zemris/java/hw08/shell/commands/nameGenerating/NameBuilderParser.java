package hr.fer.zemris.java.hw08.shell.commands.nameGenerating;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NameBuilderParser {

	private char[] expression;
	private List<NameBuilder> list;
	private int index;

	public NameBuilderParser(String expression) {
		this.expression = Objects.requireNonNull(expression).toCharArray();
		list = new ArrayList<>();
		index = 0;
		parse();
	}

	private void parse() {
		try {
			while ((index + 1) < expression.length) {

				if (expression[index] == '$') {
					index += 2;
					expressionReader();
				} else {
					regexReader();
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Expression is not closed! ");
		} catch (NumberFormatException e) {
			System.err.println("Argument cannot be parsed to Number!");
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}

	private void expressionReader() {

		StringBuilder builder = new StringBuilder();
		while (index < expression.length && expression[index] != '}') {
			if (Character.isDigit(expression[index]) || expression[index] == ',') {
				builder.append(expression[index]);
			} else if (!Character.isWhitespace(expression[index])) {
				throw new IllegalArgumentException(
						"Unsupported character inside expression \'" + expression[index] + "\'!");
			}

			index++;
		}

		String[] array = builder.toString().split(",");

		index++;

		if (array.length == 1) {
			list.add(new RegexNameBuilder(Integer.parseInt(array[0])));
		} else if (array.length == 2) {
			list.add(new RegexNameBuilder(Integer.parseInt(array[0]), Integer.parseInt(array[1])));
		} else {
			throw new IllegalArgumentException("Too many arguments!");
		}
	}

	private void regexReader() {

		StringBuilder builder = new StringBuilder();

		while (index < expression.length && expression[index] != '$' && (index + 1 < expression.length)
				&& expression[index + 1] != '{') {

			if (expression[index] == '$')
				throw new IllegalArgumentException("After '$' there must be '{'");

			builder.append(expression[index]);

			index++;

		}

		list.add(new ConstantStringNameBuilder(builder.toString()));
	}

	public NameBuilder getNameBuilder() {
		return new ExecutorNameBuilder(list);
	}
}
