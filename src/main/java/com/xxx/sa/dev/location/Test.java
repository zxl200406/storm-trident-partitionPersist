package com.xxx.sa.dev.location;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import backtype.storm.task.OutputCollector;
import backtype.storm.tuple.Values;

import com.alibaba.fastjson.JSON;
import com.xxx.sa.dev.common.Common;
import com.xxx.sa.dev.variable.DATA;

import storm.trident.operation.Aggregator;
import storm.trident.operation.TridentCollector;
import storm.trident.operation.TridentOperationContext;
import storm.trident.tuple.TridentTuple;

public class Test implements Aggregator<Map<String, Integer>> {
	private static final long serialVersionUID = -5141558506999420908L;


	public void aggregate(Map<String, Integer> map,TridentTuple tuple, TridentCollector collector) {
		
		
		String key=tuple.getString(0);

		
		//如果这个map存在，key就累加
		if(map.containsKey(key)){
			Integer tmp=map.get(key);
			map.put(key, ++tmp);

		}else{
			map.put(key, 1);
		}
		

	}



	public void complete(Map<String, Integer> map,TridentCollector collector) {
		if (map.size() > 0) {

			for(Entry<String, Integer> entry : map.entrySet()){
				System.out.println("Thread.id="+Thread.currentThread().getId()+"|"+entry.getKey()+"|"+entry.getValue());
				collector.emit(new Values(entry.getKey(),entry.getValue()));
			}
			
			map.clear();
		} 
	}


	public Map<String, Integer> init(Object batchId,TridentCollector collector) {
		return new HashMap<String, Integer>();
	}

	public void cleanup() {
	}

	public void prepare(Map map, TridentOperationContext tridentOperationContext) {
		System.setProperty("java.security.krb5.conf",
		System.getProperty("user.dir") + "/krb5.conf");
	}

}
