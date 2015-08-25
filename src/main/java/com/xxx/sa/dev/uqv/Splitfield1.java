package com.xxx.sa.dev.uqv;

import org.apache.commons.lang.exception.ExceptionUtils;

import backtype.storm.tuple.Values;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xxx.sa.dev.variable.DATA;

import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

public class Splitfield1 extends BaseFunction {


	private static final long serialVersionUID = 8428147000997859495L;
	@Override
	public void execute(TridentTuple input, TridentCollector collector) {

		String name=input.getString(0);
		int age=input.getInteger(1);
		String title=input.getString(2);
		String tel=input.getString(3);

		collector.emit(new Values(name,age,title,tel));

	}






}