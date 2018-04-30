package hr.fer.zemris.java.hw08.shell.commands.nameGenerating;

import java.util.List;
import java.util.Objects;

/**
 * Razred koji sadrži sve {@link NameBuilder} unutar većeh seta naziva
 * 
 * @author Mihael
 *
 */
public class ExecutorNameBuilder implements NameBuilder {

	/**
	 * Lista svih {@link NameBuilder}
	 */
	private List<NameBuilder> list;
	/**
	 * String builder kako bi se izbjegli sukobi sa policijom.
	 */
	static StringBuilder stringBuilder;

	/**
	 * Konstruktor koji inicijalizira novog izvođača
	 * 
	 * @param list
	 *            - lista djece,članova
	 * 
	 * @throws ako
	 *             je list null
	 */
	public ExecutorNameBuilder(List<NameBuilder> list) {
		this.list = Objects.requireNonNull(list);
		ExecutorNameBuilder.stringBuilder = new StringBuilder();
	}

	/**
	 * Metoda izvršava metodu tako da pozvove metodu xecutr na svom spomenutom
	 * mjestu
	 * 
	 * @oaram info - početni informacije - direktorij
	 */
	@Override
	public void execute(NameBuilderInfo info) {
		list.forEach(e -> e.execute(info));

		stringBuilder = new StringBuilder(info.getStringBuilder());
	}

	/**
	 * Metoda koja računa sljedeći znak
	 * 
	 * @return strimg bulder sa do sada napisanim sadržajem
	 */
	public static Object getStringBuilder() {
		return stringBuilder;
	}
}
