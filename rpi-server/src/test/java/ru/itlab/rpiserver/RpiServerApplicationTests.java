package ru.itlab.rpiserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itlab.rpiserver.service.InnovationService;

@SpringBootTest
class RpiServerApplicationTests {

	@Autowired
	private InnovationService innovationService;

	@Test
	void contextLoads() {
		//innovationService.deleteFiles("111");
	}

}
