package czy.site.model;

public enum ProcessType {

	Level1("Level1", 1), Level2("Level2", 2), Level3("Level3", 3);
	private String name;
	private int index;

	private ProcessType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	// 普通方法
	public static String getName(int index) {
		for (ProcessType c : ProcessType.values()) {
			if (c.getIndex() == index) {
				return c.name;
			}
		}
		return null;
	}

	public static ProcessType getEnum(int index) {
		for (ProcessType c : ProcessType.values()) {
			if (c.getIndex() == index) {
				return c;
			}
		}
		return Level1;
	}

	public String getName() {
		return name;
	}

	public int getIndex() {
		return index;
	}
}
