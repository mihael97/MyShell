package hr.fer.zemris.java.hw08.shell.commands.nameGenerating;

import java.util.Objects;

public class ConstantStringNameBuilder implements NameBuilder {

	private String string;

	public ConstantStringNameBuilder(String string) {
		this.string = Objects.requireNonNull(string);
	}

	@Override
	public void execute(NameBuilderInfo info) {
		info.getStringBuilder().append(string);
	}

}
