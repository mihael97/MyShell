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
	private static StringBuilder stringBuilder;

	/**
	 * Konstruktor koji inicijalizira novog izvođača
	 * 
	 * @param list
	 *            - lista djece,članova
	 * 
	 * @throws NullPointerException
	 *             - je lista <code>null</code>
	 */
	public ExecutorNameBuilder(List<NameBuilder> list) {
		this.list = Objects.requireNonNull(list);
		ExecutorNameBuilder.stringBuilder = new StringBuilder();
	}

	/**
	 * Metoda izvršava metodu tako da pozvove metodu xecutr na svom spomenutom
	 * mjestu
	 * 
	 * @param info
	 *            - početne informacije - direktorij
	 * @throws NullPointerException
	 *             - ako je argument <code>null</code>
	 */
	@Override
	public void execute(NameBuilderInfo info) {
		Objects.requireNonNull(info);
		list.forEach(e -> e.execute(info));

		stringBuilder = new StringBuilder(info.getStringBuilder());
	}

	/**
	 * Metoda koja računa vraća do sada napisna sadržaj
	 * 
	 * @return string builder sa do sada napisanim sadržajem
	 */
	public static Object getStringBuilder() {
		return stringBuilder;
	}
}
