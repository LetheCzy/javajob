package czy.site.model;

public enum JobStatus {
	None("初始", 1), Running("运行中", 2), ShutDown("停止", 3), Suspend("暂停", 4);
	private String name;
	private int index;

	private JobStatus(String name, int index) {
		this.name = name;
		this.index = index;
	}

	// 普通方法
	public static String getName(int index) {
		for (JobStatus c : JobStatus.values()) {
			if (c.getIndex() == index) {
				return c.name;
			}
		}
		return null;
	}

	public static JobStatus getEnum(int index) {
		for (JobStatus c : JobStatus.values()) {
			if (c.getIndex() == index) {
				return c;
			}
		}
		return None;
	}
	
	public String getName() {
		return name;
	}

	public int getIndex() {
		return index;
	}
}
