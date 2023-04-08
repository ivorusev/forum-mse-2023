package com.edu.mse.forum;

import com.edu.mse.forum.testcontainers.ForumContainer;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
class ForumApplicationTests {

	@ClassRule
	public static PostgreSQLContainer postgreSQLContainer = ForumContainer.getInstance();

	@Test
	void contextLoads() {

	}

}
