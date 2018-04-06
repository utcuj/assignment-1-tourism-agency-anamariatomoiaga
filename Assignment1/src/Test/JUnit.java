package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bll.ClientBLL;
import model.Client;

class JUnit {

	@Test
	void test() {
		Client c = new Client("Popescu Maria",154,"2960312245050","Bucuresti");
		int	rez=ClientBLL.insertClient(c);
		System.out.println(rez);
		assertTrue(rez != -1);
	}

}
