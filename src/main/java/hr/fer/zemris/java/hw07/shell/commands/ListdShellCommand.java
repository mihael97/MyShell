package hr.fer.zemris.java.hw07.shell.commands;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;

/**
 * Razred koji implementira naredbu za ispis bivših radnih direktorija
 * 
 * @author ime
 *
 */
public class ListdShellCommand implements ShellCommand {

	/**
	 * Ime naredbe
	 */
	private final static String name = "listd";
	/**
	 * Opis naredbe
	 */
	private final static List<String> description = new ArrayList<>();

	/**
	 * Zadani program
	 */
	public ListdShellCommand() {
		description.add("Command prints all previous workspaces");
	}

	/**
	 * Metoda ispisuje sve zadnje direkotorije,počevši od najnovijeg
	 * 
	 * @param env
	 *            - ljuska
	 * @param arguments
	 *            - argumenti
	 * @return {@link ShellStatus} za nastavak programa
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		try {
			@SuppressWarnings("unchecked")
			Stack<Path> stack = (Stack<Path>) env.getSharedData(Functions.CDSTACK);

			if (stack == null) {
				throw new IllegalArgumentException("Stack doesn't exist!");
			}

			if (stack.size() == 0)
				env.write("There is not any stored directory!");

			for (int i = stack.size() - 1; i >= 0; i--) {
				env.writeln(stack.get(i).toString());
			}

		} catch (ClassCastException e) {
			System.err.println("Object cannot be casted to stack!");
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}

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
		return description;
	}

}
