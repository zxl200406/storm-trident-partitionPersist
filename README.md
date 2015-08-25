###必读
把大数进行分片，根据数据中某个字段分组


Origin_Stream.partitionAggregate(new Fields("a","b") , new Test(), 
				new Fields("A1","B1")).partitionPersist(new LocationDBFactory(), new Fields("A1","B1"), new LocationUpdater());

//Test只是一个本地聚合,减少数据emit发射数量
//更新操作在LocationUpdater类中的updateState方法并回调DB类的setBulk 来添加每批次的数据到自定义数据结构中。
