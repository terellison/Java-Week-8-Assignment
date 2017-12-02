package cop2251.fall17.week8.ellison;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Set;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestNameHelper
{
	static NameHelper h = new NameHelper();
	

	@BeforeClass
	public static void oneTimeSetUp() throws FileNotFoundException  
    {
		System.out.println("loading");
		h.load();
    }
	
	@Test
	public void testNameFoundCase()
	{
		assertTrue(h.isNamePresent("Emma", "F"));
		assertTrue(h.isNamePresent("EMMA", "F"));
		assertTrue(h.isNamePresent("emma", "F"));
		assertTrue(h.isNamePresent("EmMa", "F"));
	}	

	@Test
	public void testNameFound()
	{
		assertTrue(h.isNamePresent("Zyus", "M"));
		assertTrue(h.isNamePresent("Zymirah", "F"));
		assertTrue(h.isNamePresent("Zyshawn", "M"));
		assertTrue(h.isNamePresent("Zeno", "M"));
	}	
	
	@Test
	public void testNameNOTFound()
	{
		assertFalse(h.isNamePresent("batman", "M"));
	}	

	@Test
	public void testYears()
	{
		TreeSet<String> years = (TreeSet<String>)h.getYears();
		assertEquals("1900", years.first());
		assertEquals("2015", years.last());
		assertEquals(116,years.size());
	}
	
	@Test
	public void testRank()
	{
		assertEquals(19511, h.getRank("2015", "noah", "M"));
		assertEquals(5, h.getRank("2015", "zyus", "M"));
		assertEquals(5304, h.getRank("1900", "margaret", "F"));
	}
}
