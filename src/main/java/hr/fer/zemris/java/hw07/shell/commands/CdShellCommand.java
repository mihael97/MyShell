package hr.fer.zemris.java.hw07.shell.commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;

/**
 * Razred koji implementira metodu za mjenjanje lokacije radnog direktorija
 * 
 * @author Mihael
 *
 */
public class CdShellCommand implements ShellCommand {

	/**
	 * Ime naredbe
	 */
	private final static String name = "cd";
	/**
	 * Opis naredbe
	 */
	private final static List<String> description = new ArrayList<>();

	/**
	 * Zadani kontruktor
	 */
	public CdShellCommand() {
		description.add("Command which sets current directory to another location");
	}

	/**
	 * Metoda koja izvodi naredbu za mjenjanje radnog direktorija
	 * 
	 * @param env
	 *            - ljuska
	 * @param arguments
	 *            - argumenti
	 * 
	 * @return {@link ShellStatu} za nastavak
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		try {
			Path path = Paths.get(Functions.split(arguments, 1)[0]);
			path.resolve(env.getCurrentDirectory());
			if (Files.isDirectory(path)) {
				env.setCurrentDirectory(path);
			} else {
				System.err.println("Given path: \'" + path.toString() + "\' is not a directory!");
			}
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}

		return ShellStatus.CONTINUE;
	}

	/**
	 * Metoda koja vraća naziv naredbe
	 * 
	 * @return naziv naredbe
	 */
	@Override
	public String getCommandName() {
		return name;
	}

	/**
	 * Metoda koja vraća opis naredbe
	 * 
	 * @return opis naredbe
	 */
	@Override
	public List<String> getCommandDescription() {
		return description;
	}

}
