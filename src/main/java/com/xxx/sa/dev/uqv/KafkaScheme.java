package com.xxx.sa.dev.uqv;

import java.util.List;
import java.io.UnsupportedEncodingException;

import backtype.storm.spout.Scheme;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class KafkaScheme implements Scheme {

	private static final long serialVersionUID = 3099163355168166022L;
public static final String KAFKA_SCHEME_KEY = "msg";

  public List<Object> deserialize(byte[] bytes) {
    return new Values(deserializeString(bytes));
  }
  
  public static String deserializeString(byte[] string) {
    try {
      return new String(string, "UTF-8");
    }catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  public Fields getOutputFields() {
    return new Fields(KAFKA_SCHEME_KEY);
  }
}