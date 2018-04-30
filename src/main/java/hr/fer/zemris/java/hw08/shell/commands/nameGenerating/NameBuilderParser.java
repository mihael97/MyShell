package hr.fer.zemris.java.hw08.shell.commands.nameGenerating;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Razred predstavlja parser za regularne izraze koji predstavljaju načine
 * preimenovanja
 * 
 * @author Mihael
 *
 */
public class NameBuilderParser {

	/**
	 * Izraz u obliku Chara
	 */
	private char[] expression;
	/**
	 * Lista svih dijelova izraza
	 */
	private List<NameBuilder> list;
	/**
	 * Trenutna pozicija u polju
	 */
	private int index;

	/**
	 * Konsturktor za parser imena datoteke
	 * 
	 * @param expression
	 *            - izraz
	 * 
	 * @throws NullPointerException
	 *             - ako je izraz null
	 */
	public NameBuilderParser(String expression) {
		this.expression = Objects.requireNonNull(expression).toCharArray();
		list = new ArrayList<>();
		index = 0;
		parse();
	}

	/**
	 * Metod parsira dani niz
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             - ako se indexom pristupi nepostojećoj poziciji
	 * @throws NumberFormatException
	 *             - ako se jedan od brojeva ne može pretvoriti
	 * @throws IllegalArgumentException
	 *             - ostale iznimke
	 */
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

	/**
	 * Metoda koja čita dijelove izraza koji se ponavljaju
	 */
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

	/**
	 * Metoda koja čita tekst van regularnih izraza
	 */
	private void regexReader() {

		StringBuilder builder = new StringBuilder();

		while (index < expression.length) {

			if (expression[index] == '$' && expression[index + 1] != '{') {
				throw new IllegalArgumentException("After '$' there must be '{'");
			} else if (expression[index] == '$')
				break;

			builder.append(expression[index]);

			index++;

		}

		list.add(new ConstantStringNameBuilder(builder.toString()));
	}

	/**
	 * Metoda vraća zadnji stvoreni {@link NameBuilder} sa svom djecom
	 * 
	 * @return {@link NameBuilder}
	 */
	public NameBuilder getNameBuilder() {
		return new ExecutorNameBuilder(list);
	}
}
