cd /usr/redis/redis-3.0.7/src
echo start redis1
./redis-server ../redis.conf &
sleep 1
cd /usr/redis/redis2/src
echo start redis2
./redis-server ../redis.conf &
sleep 1
echo start server1
nohup java -classpath ./config/:../ShortUrlServer/target/ShortUrlServer-0.0.1-SNAPSHOT.jar com.youshusoft.start.StartServer 1 > server1.log >&1 &
sleep 1
echo start server2
nohup java -classpath ./config/:../ShortUrlServer/target/ShortUrlServer-0.0.1-SNAPSHOT.jar com.youshusoft.start.StartServer 2 > server2.log >&1 &
sleep 1
echo start server3
nohup java -classpath ./config/:../ShortUrlServer/target/ShortUrlServer-0.0.1-SNAPSHOT.jar com.youshusoft.start.StartServer 3 > server3.log >&1 &
sleep 1
echo start server4
nohup java -classpath ./config/:../ShortUrlServer/target/ShortUrlServer-0.0.1-SNAPSHOT.jar com.youshusoft.start.StartServer 4 > server4.log >&1 &