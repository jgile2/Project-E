package projecte.api.emc;

import projecte.util.Color;

public enum EmcValueType {

	MATTER(Color.WHITE.toString(), null), FUEL(Color.RED.toString(), null);

	private String prefix = "";
	private String suffix = "";

	private EmcValueType(String prefix, String suffix) {
		if (prefix != null)
			this.prefix = prefix;
		if (suffix != null)
			this.suffix = suffix;
	}

	private EmcValueType() {
	}

	public String getPrefix() {
		return prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	@Override
	public String toString() {
		return prefix + name().toUpperCase().substring(0, 1)
				+ name().toLowerCase().substring(1) + suffix;
	}

}
