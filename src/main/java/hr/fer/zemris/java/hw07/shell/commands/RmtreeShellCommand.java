package hr.fer.zemris.java.hw07.shell.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;

public class RmtreeShellCommand implements ShellCommand {

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		Path path = Paths.get(Functions.split(arguments, 1)[0]).resolve(env.getCurrentDirectory());

		if (Files.isDirectory(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}

		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getCommandDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
