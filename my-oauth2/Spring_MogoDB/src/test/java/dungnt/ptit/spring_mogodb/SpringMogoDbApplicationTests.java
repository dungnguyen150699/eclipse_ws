package dungnt.ptit.spring_mogodb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;
import java.util.UUID;

@SpringBootTest
class SpringMogoDbApplicationTests {

	@Test
	void contextLoads() {
	}

	public static void main(String[] args) {
		SecureRandom ng = new SecureRandom();

		byte[] randomBytes = new byte[16];
		ng.nextBytes(randomBytes);
		randomBytes[6]  &= 0x0f;  /* clear version        */
		randomBytes[6]  |= 0x40;  /* set to version 4     */
		randomBytes[8]  &= 0x3f;  /* clear variant        */
		randomBytes[8]  |= 0x80;  /* set to IETF variant  */
//		UUID uuid = new UUID(randomBytes);
		System.out.println(randomBytes);
		System.out.println(randomBytes.toString());
	}

}
