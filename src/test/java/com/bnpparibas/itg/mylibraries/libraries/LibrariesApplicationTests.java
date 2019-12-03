package com.bnpparibas.itg.mylibraries.libraries;

import com.bnpparibas.itg.mylibraries.libraries.domain.library.Address;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.Director;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.Library;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.Type;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibrariesApplicationTests {

	@Test
	public void entities_should_be_equal_if_same_identity() {
		Address address = new Address(12,"rue Alberty",75000,"Paris");

		Director director = new Director("surname", "name");

		Library l1 = new Library("1", "B1", Type.ASSOCIATIVE, address, director, null);
		Library l2 = new Library("1", "B2",Type.NATIONAL, address, director, null);

		Assert.assertEquals(l1, l2);
	}

	@Test
	public void value_objets_should_be_equal_if_same_properties() {
		Director d1 = new Director("surname1", "name1");
		Director d2 = new Director("surname1", "name1");

		Assert.assertEquals(d1, d2);
	}

}
