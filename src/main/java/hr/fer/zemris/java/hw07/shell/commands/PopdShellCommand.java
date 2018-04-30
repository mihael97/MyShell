package hr.fer.zemris.java.hw07.shell.commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;

/**
 * Razred koji implementira metodu koja uzima zadnju vrijednost sa stoga i
 * stavlja za trenutni direktorij
 * 
 * @author Mihael
 *
 */
public class PopdShellCommand implements ShellCommand {

	/**
	 * Ime naredbe
	 */
	private static String name = "popd";
	/**
	 * Opis naredbe
	 */
	private static List<String> description = new ArrayList<>();

	/**
	 * Zadani konstruktor
	 */
	public PopdShellCommand() {
		description.add("Methods for new workspace sets value poped from stack");
	}

	/**
	 * Metoda postavlja prvu vrijednost sa stoga za trenutni radni direktorij
	 * 
	 * @param env
	 *            - ljuska
	 * @param arguments
	 *            - argumenti(ne koristi se)
	 * 
	 * @return {@link ShellStatus} za prekid programa
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		try {
			@SuppressWarnings("unchecked")
			Stack<Path> stack = (Stack<Path>) env.getSharedData(Functions.CDSTACK);

			if (stack == null) {
				throw new IllegalArgumentException("Stack is null!");
			}

			if (stack.size() == 0) {
				throw new IllegalArgumentException("Stack is empty!");
			}

			Path path = stack.pop();

			if (Files.isDirectory(path)) {
				env.setCurrentDirectory(path);
				env.setSharedData(Functions.CDSTACK, stack);
			} else {
				throw new IllegalArgumentException("Path doesn't exist or it's not directory!");
			}

		} catch (ClassCastException e) {
			System.err.println("Object cannot be casted to Stack ");
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}

		return ShellStatus.CONTINUE;
	}

	/**
	 * Metoda vrća ime naredbe
	 * 
	 * @return String ime naredbe
	 */
	@Override
	public String getCommandName() {
		return name;
	}

	/**
	 * Metoda vraća opis naredbe
	 * 
	 * @return opis naredbe
	 */
	@Override
	public List<String> getCommandDescription() {
		return description;
	}

}
