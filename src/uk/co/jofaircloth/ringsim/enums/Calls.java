package uk.co.jofaircloth.ringsim.enums;

public enum Calls {
	PLAIN	(""),
	BOB		("-"),
	SINGLE	("S");
	
	private String displayName;
	
	Calls(String displayName) {
		this.displayName = displayName;
	}
	
	@Override
	public String toString() {
		return displayName;
	}
}
