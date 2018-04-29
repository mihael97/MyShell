package hr.fer.zemris.java.hw07.shell.commands;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;

/**
 * Razred koji implementira naredbu za brisanje bivšeg radnog direktorija
 * 
 * @author Mihael
 *
 */
public class DropdShellCommand implements ShellCommand {

	/**
	 * Ime naredbe
	 */
	private final static String name = "charsets";
	/**
	 * Opis naredbe
	 */
	private final static List<String> description = new ArrayList<>();

	/**
	 * Zadani program
	 */
	public DropdShellCommand() {
		description.add("Method deletes ex workspace");
	}

	/**
	 * Metoda koja izvodi naredbu brisanje bivšeg radnog direktorija
	 * 
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
				throw new IllegalArgumentException("Stack is empty!");

			stack.pop();
			env.setSharedData(Functions.CDSTACK, stack);
		} catch (ClassCastException e) {
			System.err.println("Object cannot be cast to stack!");
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
