package de.fhaugsburg.games.boardgames.scrabble.management;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class GridPointTest {
	private GridPoint gridPoint1;
	private GridPoint gridPoint2;
	private GridPoint gridPoint3;
	private GridPoint gridPoint4;
	private GridPoint gridPoint5;
	private GridPoint gridPoint6;
	@SuppressWarnings("unused")
	private ManhattenDirection manhattenDirection;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("setUpBeforeClass");	
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("tearDownAfterClass");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		gridPoint1 = new GridPoint(2, 5);
		gridPoint2 = new GridPoint(2, 5);
		gridPoint3 = new GridPoint(-2, -5);
		gridPoint4 = new GridPoint(4, 10);
		gridPoint5 = new GridPoint(0, 0);
		gridPoint6 = new GridPoint(1, 5);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testEquals() {
		assertTrue(gridPoint1.equals(gridPoint2));
	}

	@Test
	public void testNeg() {
		assertTrue(gridPoint1.neg().equals(gridPoint3));
	}
	
	@Test
	public void testPlus() {
		assertTrue(gridPoint1.plus(gridPoint1).equals(gridPoint4));
	}
	
	@Test
	public void testMinus() {
		assertTrue(gridPoint1.minus(gridPoint1).equals(gridPoint5));
	}
	
	@Test
	public void testCompareTo() {
		assertTrue(gridPoint1.compareTo(gridPoint6) == 0);
	}
	
}