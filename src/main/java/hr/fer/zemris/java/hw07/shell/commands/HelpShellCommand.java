package hr.fer.zemris.java.hw07.shell.commands;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;

/**
 * Razred predstavlja naredbu za ispisavanje opisa određene naredbe
 * 
 * @author Mihael
 *
 */
public class HelpShellCommand implements ShellCommand {
	/**
	 * Ime metode
	 */
	private static final String name = "help";
	/**
	 * Opis metode
	 */
	private static final List<String> description = new ArrayList<>();

	/**
	 * Zadani konstruktor
	 */
	public HelpShellCommand() {
		description.add("If command is called without arguments,all supported commands will be printed. "
				+ "Otherwise,decription of specific command will be shown");
	}

	/**
	 * Ako je naredba pozvana s jednim argumentom ispisuje sve naredbe,inače
	 * ispisuje jednu naredbu zadanu drugim argumentom s opisom
	 * 
	 * @return {@link ShellStatus} za nastavak
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		if (arguments.trim().equals("")) {
			for (String key : env.commands().keySet()) {
				env.write(key);
			}
		} else {
			switch (arguments.trim()) {
			case "charsets":
				env.write(new CharsetsShellCommand().getCommandName() + " - "
						+ new CharsetsShellCommand().getCommandDescription().get(0));
				break;
			case "copy":
				env.write(new CopyShellCommand().getCommandName() + " - "
						+ new CopyShellCommand().getCommandDescription().get(0));
				break;
			case "exit":
				env.write(new ExitShellCommand().getCommandName() + " - "
						+ new ExitShellCommand().getCommandDescription().get(0));
				break;
			case "help":
				env.write(getCommandName() + " - " + getCommandDescription().get(0));
				break;
			case "ls":
				env.write(new LsShellCommand().getCommandName() + " - "
						+ new LsShellCommand().getCommandDescription().get(0));
				break;
			case "mkdir":
				env.write(new MkdirShellCommand().getCommandName() + " - "
						+ new MkdirShellCommand().getCommandDescription().get(0));
				break;
			case "cd":
				env.write(new CdShellCommand().getCommandName() + " - "
						+ new CdShellCommand().getCommandDescription().get(0));

				break;
			case "cptree":
				env.write(new CpTreeShellCommand().getCommandName() + " - "
						+ new CpTreeShellCommand().getCommandDescription().get(0));

				break;
			case "listd":
				env.write(new ListdShellCommand().getCommandName() + " - "
						+ new ListdShellCommand().getCommandDescription().get(0));

				break;
			case "dropd":
				env.write(new DropdShellCommand().getCommandName() + " - "
						+ new DropdShellCommand().getCommandDescription().get(0));

				break;
			case "massrename":
				env.write(new MassrenameShellCommand().getCommandName() + " - "
						+ new MassrenameShellCommand().getCommandDescription().get(0));

				break;
			case "popd":
				env.write(new PopdShellCommand().getCommandName() + " - "
						+ new PopdShellCommand().getCommandDescription().get(0));

				break;
			case "pushd":
				env.write(new PushHdShellCommand().getCommandName() + " - "
						+ new PushHdShellCommand().getCommandDescription().get(0));

				break;
			case "pwd":
				env.write(new PwdShellCommand().getCommandName() + " - "
						+ new PwdShellCommand().getCommandDescription().get(0));

				break;
			case "rmtree":
				env.write(new RmtreeShellCommand().getCommandName() + " - "
						+ new RmtreeShellCommand().getCommandDescription().get(0));

				break;
			default:
				System.err.println("Command is not supported!");
			}
		}

		return ShellStatus.CONTINUE;
	}

	/**
	 * Vraća ime naredbe
	 * 
	 * @return ime naredbe
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
