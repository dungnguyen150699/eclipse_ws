package com.java8.springboot.spring.redis;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

// https://www.baeldung.com/jedis-java-redis-client-library
// https://www.tutorialspoint.com/redis/redis_java.htm
public class connect_and_test {
	
	private static Jedis jedis ; 
	
	public static void init_Jedis() {
		jedis = new Jedis("192.168.1.101",6379);
		jedis.auth("1234");
	}
	
	public static String jedis_String() {
		
		jedis.set("hello", "test_redis");
		String cachedResponse = jedis.get("hello");
		System.out.println(cachedResponse);
		return cachedResponse;
	}
	
//	public static <T> List<T> jedis_List(List<T> jedis_List){
//		 List<T> j_list = new ArrayList();
//		 jedis.lpu
//		 
//		 return j_list;
//	}

	
	
	/* 2 cái này không cần
	 * public final JedisPoolConfig poolConfig = buildPoolConfig();
	 * JedisPool jedisPool = new JedisPool(poolConfig, "localhost");
	 */
	// cái này dùng để tạo luồng an toàn cho từng thành viên
	private static JedisPool buildPool() {
	    final JedisPoolConfig poolConfig = new JedisPoolConfig();
	    poolConfig.setMaxTotal(128);
	    // Thời gian nhàn dỗi
	    poolConfig.setMaxIdle(128);
	    poolConfig.setMinIdle(16);
	    // Mấy cái test
	    poolConfig.setTestOnBorrow(true);
	    poolConfig.setTestOnReturn(true);
	    poolConfig.setTestWhileIdle(true);
	    // Thời gian trục xuất khỏi redis
	    poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
	    poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
	    poolConfig.setNumTestsPerEvictionRun(3);
	    
	    poolConfig.setBlockWhenExhausted(true);
	    // thời gian được tính bằng giây ?
	    return new JedisPool(poolConfig,"192.168.1.101",6379,2000,"1234");
	}
	
	/* add authen redis with password Cty làm */
//    @Bean
//    JedisPool pool() {
//        log.info("Redis server: {}:{}", StringHelper.isNullOrEmpty(System.getenv("REDIS_NAME")) ? redisHost : System.getenv("REDIS_NAME"), this.redisPort);
//        JedisPoolConfig POOL_CONFIG = new JedisPoolConfig();
//        String REDIS_HOST = StringHelper.isNullOrEmpty(System.getenv("REDIS_NAME")) ? redisHost : System.getenv("REDIS_NAME");
//        Integer REDIS_PORT = redisPort;
//        String REDIS_PASSWORD = redisPassword;
//        if (REDIS_PASSWORD == null) {
//            return new JedisPool(REDIS_HOST, REDIS_PORT);
//        }
//        return new JedisPool(POOL_CONFIG, REDIS_HOST, REDIS_PORT, 2000, HashHelper.aesDecrypt(REDIS_PASSWORD, aesSecretKey));
//    }
	
	public static void jedis_list(Jedis jedis) {
		//store data in redis list 
	      jedis.lpush("tutorial-list", "Redis1"); 
	      jedis.lpush("tutorial-list", "Mongodb2"); 
	      jedis.lpush("tutorial-list", "Mysql3"); 
	      // Get the stored data and print it max 2 element
	      // Thông thường khoong ai dùng list mà dùng map cái này còn không có thứ tự nữa
	      List<String> list = jedis.lrange("tutorial-list", 0 ,5); 
	      
	      for(int i = 0; i<list.size(); i++) { 
	         System.out.println("Stored string in redis:: "+list.get(i)); 
	      } 
	}
	
	public static void main(String[] args) {
		// jedis waring other can get infor
//		init_Jedis();
//		jedis_list(jedis);
		// jedis safe only y can get infor
		try(Jedis jedis = buildPool().getResource()){
			jedis_list(jedis);
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
