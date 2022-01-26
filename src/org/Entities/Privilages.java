package org.Entities;
import java.util.*;

import org.Business.InvalidPrivilageException;

public class Privilages {
	
	Map<Privilge,Double> map=new HashMap<Privilge,Double>();
	
	private static Privilages instance;
	
	private Privilages() {
		
		this.map.put(Privilge.PREMIUM, 100000.0);
		this.map.put(Privilge.GOLD, 50000.0);
		this.map.put(Privilge.SILVER, 25000.0);
	}
	
	public Double getPrivilages(Privilge p) throws InvalidPrivilageException {
		double value=0;
		if(this.map.get(p) == null) {
			throw new InvalidPrivilageException("Previlege is not found");
		}
		else {
			value=this.map.get(p);
		}
		return value;
	}
	
	public static Privilages getInstance() {
		if(instance==null) {
			instance=new Privilages();
		}
		return instance;
	}
}
