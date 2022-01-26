package org.Entities;

public abstract class IDGenerator {
	
	private static int no=1000;
	
	public static int generateId() {
		return no++;
	}
}
