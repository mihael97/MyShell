package hr.fer.zemris.java.hw07.shell.commands;

import java.util.List;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;

public class PwdShellCommand implements ShellCommand {

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		env.writeln(env.getCurrentDirectory().toString());

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
