package org.Business;

import java.util.*;

import org.Entities.TransferInfo;

public class TransferLog {
	
	protected static Map<Integer,List<TransferInfo>> transfers=new HashMap<Integer,List<TransferInfo>>();
	
	public static void logTransfer(TransferInfo tf) {
		if(transfers.containsKey(tf.getFromAccount().getAccNo())) {
			
			List<TransferInfo> list=transfers.get(tf.getFromAccount().getAccNo());
			list.add(tf);
		}
		else {
			List<TransferInfo> list=new ArrayList<TransferInfo>();
			list.add(tf);
			transfers.put(tf.getFromAccount().getAccNo(), list);
		}
		
	}
	
	public  static List<TransferInfo> getTransfers(TransferInfo tf) throws NoTansferLogException {
		
		List<TransferInfo> list=new ArrayList<TransferInfo>();
		if(transfers.containsKey(tf.getFromAccount().getAccNo())) {
			
			list=transfers.get(tf.getFromAccount().getAccNo());
		}
		else {
			throw new NoTansferLogException("No transfers are done");
		}
		return list;
	}
	
	
}
