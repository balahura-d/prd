package ua.nure.balagura.SummaryTask4.db.entity;

public enum Condition {

	IS_USED,
	NEW,
	WAS_IN_USE,
	MINOR_DEFECTS,
	DAMAGED,
	TO_WRITE_OFF;
	
	public static Condition getCondition(Auto auto) {
		int conditionId = auto.getConditionId();
		return Condition.values()[conditionId];
	}

	public String getName() {
		return name().toLowerCase();
	}
}
