package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.complexible.common.csv.CSV2RDF;

public class CSV2RDFtest {

	@Test
	public void TesttoCharSingleCharacter() {
		CSV2RDF csv2rdf = new CSV2RDF();
		assertEquals('q', csv2rdf.toChar("q"));
	}
	
	@Test
	public void TesttoCharExceptionThrown() {
		CSV2RDF csv2rdf = new CSV2RDF();
		Exception exception = assertThrows(IllegalArgumentException.class, () -> csv2rdf.toChar("qwerty"));
		
		assertEquals("Expecting a single character but got qwerty", exception.getMessage());
	}
}
