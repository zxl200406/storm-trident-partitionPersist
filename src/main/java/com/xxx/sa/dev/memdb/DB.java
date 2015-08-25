package com.xxx.sa.dev.memdb;


import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import storm.trident.state.State;
import clojure.lang.IFn.D;

import com.alibaba.fastjson.JSON;
import com.xxx.sa.dev.common.Common;
import com.xxx.sa.dev.variable.DATA;



public class DB implements State  {
	private static final long serialVersionUID = -5694732161515374879L;

	public Map<String,Integer> map=new HashMap<String,Integer>();
	public void setBulk(Map<String,Integer> map) {
		// get locations in bulk	
		for(Entry<String, Integer> entry: map.entrySet()){
			String key=entry.getKey();
			if(this.map.containsKey(key)){
				this.map.put(key, this.map.get(key)+map.get(key));
			}else{
				this.map.put(key, entry.getValue());
			}
		}		
		System.out.println("-------");
		for(Entry<String, Integer> entry: this.map.entrySet()){
			String Key=entry.getKey();
			Integer Value=entry.getValue();
			System.out.println(Key+"|"+Value);
		}
	}
	

	

	@Override
	public void beginCommit(Long arg0) {
		// TODO Auto-generated method stub
		System.out.println("beginCommit--"+arg0);
		
	}

	@Override
	public void commit(Long arg0) {
		// TODO Auto-generated method stub
		System.out.println("commit--"+arg0);
		
	}
	


}