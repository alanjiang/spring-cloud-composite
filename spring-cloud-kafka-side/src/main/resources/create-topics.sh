#cd $APACHE_KAFKA_HOME





#/Users/pengjiang/softwares/kafka_2.13-2.6.0/bin/


先启动zookeeper

/Users/pengjiang/softwares/kafka_2.13-2.6.0/bin/zookeeper-server-start.sh -daemon /Users/pengjiang/softwares/kafka_2.13-2.6.0/config/zookeeper.properties

再启动 kafka-server

/Users/pengjiang/softwares/kafka_2.13-2.6.0/bin/kafka-server-start.sh -daemon /Users/pengjiang/softwares/kafka_2.13-2.6.0/config/server.properties






 /Users/pengjiang/softwares/kafka_2.13-2.6.0/bin/kafka-topics.sh --create --zookeeper localhost:2181  --replication-factor 1 --partitions 3 --topic SpringKafkaTopic