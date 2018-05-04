package hr.fer.zemris.java.hw08.shell.commands.nameGenerating;

import java.util.Objects;

/**
 * 
 * Razred koji implementira strukturu koja je moguće sadrži redni broj grupe i
 * minimalnu duljinu niza
 * 
 * @author Mihael
 *
 */
public class RegexNameBuilder implements NameBuilder {

	/**
	 * Redni broj grupe
	 */
	private int group;
	/**
	 * Minimalna duljina niza
	 */
	private int length;

	/**
	 * Konstruktor koji prima naziv grupe
	 * 
	 * @param group
	 *            - naziv grupe
	 */
	public RegexNameBuilder(int group) {
		this(group, 0);
	}

	/**
	 * Konstuktor koji prima minimalnu duljinu niza kao i grupu unutar regularnog
	 * izraza
	 * 
	 * @param group
	 *            - grupa
	 * @param length
	 *            - minimalna duljina
	 */
	public RegexNameBuilder(int group, int length) {
		this.group = group;
		this.length = length;
	}

	/**
	 * Metoda nad svim spremnljenim {@link NameBuilder} poziva metodu execute
	 * 
	 * @param info
	 *            - infomacije
	 * @throws NullPointerException
	 *             - ako je argument null
	 */
	@Override
	public void execute(NameBuilderInfo info) {
		Objects.requireNonNull(info);
		StringBuilder builder = new StringBuilder();

		if (info.getGroup(group).length() < length) {
			int i = length - info.getGroup(group).length();

			while (i-- > 0) {
				builder.append("0");
			}
		}

		info.getStringBuilder().append(builder.toString()).append(info.getGroup(group));
	}

}
