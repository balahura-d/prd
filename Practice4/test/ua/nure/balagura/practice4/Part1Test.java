package ua.nure.balagura.practice4;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import ua.nure.balagura.practice4.Part1;

public class Part1Test {

	int wordLengthFrom = 4;
 
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
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

	@Test
	public void testCapitalize_from_aa_bbbb_ccc2_to_aa_BBBB_CCC2() {
		String input = "aa bbbb ccc2";
		assertEquals("aa BBBB CCC2", Part1.capitalize(input, wordLengthFrom));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCapitalized_withNullString() {
		String input = null;
		Part1.capitalize(input, wordLengthFrom);
	}
	

}
