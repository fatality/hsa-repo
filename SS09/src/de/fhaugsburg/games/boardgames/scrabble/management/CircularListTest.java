package de.fhaugsburg.games.boardgames.scrabble.management;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CircularListTest {
	
	private ScrabblePlayer scrabbleplayer1;
	private ScrabblePlayer scrabbleplayer2;
	@SuppressWarnings("unused")
	private ScrabblePlayer scrabbleplayer3;
	private CircularList<ScrabblePlayer> circularlist;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		System.out.println("setUpBeforeClass");	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception 
	{
		System.out.println("tearDownAfterClass");
	}

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception 
	{
		scrabbleplayer1 = new ScrabblePlayer();
		scrabbleplayer2 = new ScrabblePlayer();
		scrabbleplayer3 = new ScrabblePlayer();
		circularlist = new CircularList();
	}

	@After
	public void tearDown() throws Exception 
	{
	}
	
	@Test
	public void testAdd()
	{
		List<ScrabblePlayer> list = circularlist.getAll();
		circularlist.add(scrabbleplayer1);
		
		assertTrue(list.contains(scrabbleplayer1));
	}
	
	@Test
	public void testNext()
	{
		circularlist.add(scrabbleplayer1);
		circularlist.add(scrabbleplayer2);
		
		assertTrue(circularlist.next().equals(scrabbleplayer2));
	}
	
	@Test
	public void testReset()
	{
		circularlist.reset();
		
		assertTrue(circularlist.equals(circularlist));
	}
	
	@Test
	public void testPrev()
	{
		circularlist.add(scrabbleplayer1);
		circularlist.add(scrabbleplayer2);

		assertTrue(circularlist.prev().equals(scrabbleplayer2));
	}
	
	public void testGetAll()
	{
		List<ScrabblePlayer> list = circularlist.getAll();
		circularlist.add(scrabbleplayer1);
		circularlist.add(scrabbleplayer2);
		
		assertTrue(circularlist.getAll().equals(list));
	}

}
