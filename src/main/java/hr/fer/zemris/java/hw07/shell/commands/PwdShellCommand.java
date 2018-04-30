package hr.fer.zemris.java.hw07.shell.commands;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;

/**
 * Razred koji implementira metodu za ispis trenutnog radnog direktorija
 * 
 * @author Mihael
 *
 */
public class PwdShellCommand implements ShellCommand {

	/**
	 * Naziv naredbe
	 */
	private static final String name = "pwd";
	/**
	 * Opis naredbe
	 */
	private static final List<String> list = new ArrayList<>();

	/**
	 * Zadani konstruktor
	 */
	public PwdShellCommand() {
		list.add("Command prints current workspace");
	}

	/**
	 * Metoda ispisuje trenutni radni direktorij
	 * 
	 * @param env
	 *            - referenca na ljusku
	 * @param arguments
	 *            - path do datoteke(obavezno) i charset(opcionalno)
	 * 
	 * @return {@link ShellStatus} za nastavak programa
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		env.writeln(env.getCurrentDirectory().toString());

		return ShellStatus.CONTINUE;
	}

	/**
	 * Metoda koja vraća ime naredbe
	 * 
	 * @return ime naredbe
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
		return list;
	}

}
