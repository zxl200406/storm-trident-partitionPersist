package com.xxx.sa.dev.uqv;





import com.xxx.sa.dev.location.Test;
import com.xxx.sa.dev.memdb.LocationDBFactory;
import com.xxx.sa.dev.memdb.LocationUpdater;

import storm.trident.TridentTopology;
import storm.trident.testing.FixedBatchSpout;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.StormTopology;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;


public class TopologyApp 
{


	private StormTopology buildTopology() {
		// TODO Auto-generated method stub
		FixedBatchSpout spout = new FixedBatchSpout(new Fields("msg"), 4, 
				new Values("{\"name\":\"jason\",\"age\":20,\"title\":\"op\",\"tel\":\"15810005758\"}"),
				new Values("{\"name\":\"john\",\"age\":20,\"title\":\"rd\",\"tel\":\"13254568412\"}"),
				new Values("{\"name\":\"joe\",\"age\":21,\"title\":\"rd\",\"tel\":\"18756427\"}"),
				new Values("{\"name\":\"nancy\",\"age\":22,\"title\":\"hr\",\"tel\":\"13698756254\"}"),
				new Values("{\"name\":\"lucy\",\"age\":23,\"title\":\"op\",\"tel\":\"132547524787\"}"),
				new Values("{\"name\":\"ken\",\"age\":24,\"title\":\"op\",\"tel\":\"1552368742554\"}"),
				new Values("{\"name\":\"hawk\",\"age\":20,\"title\":\"ceo\",\"tel\":\"159456852356\"}"),
				new Values("{\"name\":\"rocky\",\"age\":22,\"title\":\"cto\",\"tel\":\"1325687456\"}"),
				new Values("{\"name\":\"chris\",\"age\":23,\"title\":\"cfo\",\"tel\":\"13756425896\"}"),
				new Values("{\"name\":\"jason\",\"age\":20,\"title\":\"coo\",\"tel\":\"13565478952\"}"),
				new Values("{\"name\":\"leo\",\"age\":21,\"title\":\"cxo\",\"tel\":\"13452687922\"}"));
		
	//	spout.setCycle(true);

		TridentTopology topology = new TridentTopology();

		storm.trident.Stream Origin_Stream=topology.newStream("kafkaSpoutId",spout)
				.parallelismHint(3)
				.shuffle().parallelismHint(3)
				.each(new Fields("msg"), new Splitfield(), 
						new Fields("A","B","C","D")).parallelismHint(3)
						.project(new Fields("A","B","C","D")).parallelismHint(3).partitionBy(new Fields("A")).each(new Fields("A","B","C","D"), new Splitfield1(),new Fields("a","b","c","d")).parallelismHint(3);


		/*,地区现在的定义，只是省
		 * 1.地区
		 * 2.地区+IP
		 * 3.地区+运营商
		 * 4.地区+运营商+IP
		 * ​5.IP
		 * 6.运营商
		 * 7.运营商+IP
		 */

		Origin_Stream.partitionAggregate(new Fields("a","b") , new Test(), 
				new Fields("A1","B1")).partitionPersist(new LocationDBFactory(), new Fields("A1","B1"), new LocationUpdater());


		return topology.build();
		
		
		
	}
	public static void main( String[] args ) 
	{
		
		TopologyApp MyTopology=new TopologyApp();
		Config config = new Config();
		
	
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("kafka-storm", config, MyTopology.buildTopology());


	}

}
