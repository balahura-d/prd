package ua.nure.balagura.practice4;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.balagura.practice4.Part2;

public class Part2Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public final void testMain() {
//		assertEquals(true, true);
//	}

	@Test
	public final void testSort() {
		int[] arrayToSort = {4,3,5,2,6,1,7};
		int[] expecteds = {1,2,3,4,5,6,7};
		Part2.sort(arrayToSort);
		assertArrayEquals(expecteds, arrayToSort);
	}

//	@Test
//	public final void testCreateArray() {
//		assertEquals(true, true);
//	}
//
//	@Test
//	public final void testToArray() {
//		assertEquals(true, true);
//	}

}
