package hr.fer.zemris.java.hw08.shell.commands.nameGenerating;

public class RegexNameBuilder implements NameBuilder {

	private int group;
	private int length;

	public RegexNameBuilder(int group) {
		this(group, 0);
	}

	public RegexNameBuilder(int group, int length) {
		this.group = group;
		this.length = length;
	}

	@Override
	public void execute(NameBuilderInfo info) {
		StringBuilder builder = new StringBuilder();

		if (info.getGroup(group).length() < 3) {
			int i = 3 - info.getGroup(group).length();

			while (i-- > 0) {
				builder.append("0");
			}
		}

		info.getStringBuilder().append(builder.toString()).append(info.getGroup(group));
	}

}
