package test;

import com.complexible.common.csv.*;
import org.junit.jupiter.api.Test;

import org.openrdf.model.BNode;
import org.openrdf.rio.ParserConfig;
import org.openrdf.rio.helpers.BasicParserSettings;

import static org.junit.jupiter.api.Assertions.*;

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
	@Test
	public void TestNot_FAIL_ON_UNKNOWN_DATATYPES_ConfigureSet(){
		ParserConfig config;
		config = Template.getParserConfig();
		boolean failToStart = config.get(BasicParserSettings.FAIL_ON_UNKNOWN_DATATYPES);
		assertEquals(false, failToStart);
	}

	@Test
	public void TestNot_FAIL_ON_UNKNOWN_LANGUAGES_ConfigureSet(){
		ParserConfig config;
		config = Template.getParserConfig();
		boolean failToStart = config.get(BasicParserSettings.FAIL_ON_UNKNOWN_LANGUAGES);
		assertEquals(false, failToStart);
	}

	@Test
	public void TestNot_VERIFY_DATATYPE_VALUES_ConfigureSet(){
		ParserConfig config;
		config = Template.getParserConfig();
		boolean failToStart = config.get(BasicParserSettings.VERIFY_DATATYPE_VALUES);
		assertEquals(false, failToStart);
	}

	@Test
	public void TestNot_VERIFY_LANGUAGE_TAGS_ConfigureSet(){
		ParserConfig config;
		config = Template.getParserConfig();
		boolean failToStart = config.get(BasicParserSettings.VERIFY_LANGUAGE_TAGS);
		assertEquals(false, failToStart);
	}

	@Test
	public void TestNot_VERIFY_RELATIVE_URIS_ConfigureSet(){
		ParserConfig config;
		config = Template.getParserConfig();
		boolean failToStart = config.get(BasicParserSettings.VERIFY_RELATIVE_URIS);
		assertEquals(false, failToStart);
	}

	@Test
	public void TestRowNumberProvider(){
		RowNumberProvider rowNumberProvider = new RowNumberProvider();
		String[] string = {"aa", "bb"};
		assertEquals("2", rowNumberProvider.provide(2, string));
	}

	@Test
	public void TestRowValueProvider(){
		RowValueProvider rowValueProvider = new RowValueProvider(1);
		String[] string = {"aa", "bb", "cc"};
		assertEquals("bb", rowValueProvider.provide(2, string));
	}

	@Test
	public void TestUUIDProviderForDifferentRowIndexes(){
		UUIDProvider uuidProvider = new UUIDProvider();
		String[] string = {"aa", "bb", "cc"};
		String id1 = uuidProvider.provide(1, string);
		String id2 = uuidProvider.provide(2, string);
		assertEquals(false, id1.equals(id2));
	}

	@Test
	public void TestUUIDProviderForSameRowIndex(){
		UUIDProvider uuidProvider = new UUIDProvider();
		String[] string = {"aa", "bb", "cc"};
		String id1 = uuidProvider.provide(1, string);
		String id2 = uuidProvider.provide(1, string);
		assertEquals(true, id1.equals(id2));
	}

	@Test
	public void TestBNodeGeneratorForDifferentRowIndexes(){
		BNodeGenerator bNodeGenerator = new BNodeGenerator();
		String[] string = {"aa", "bb", "cc"};
		BNode bNode1 = bNodeGenerator.generate(1, string);
		BNode bNode2 = bNodeGenerator.generate(2, string);
		assertEquals(false, bNode1.equals(bNode2));
	}

	@Test
	public void TestBNodeGeneratorForSameRowIndex(){
		BNodeGenerator bNodeGenerator = new BNodeGenerator();
		String[] string = {"aa", "bb", "cc"};
		BNode bNode1 = bNodeGenerator.generate(2, string);
		BNode bNode2 = bNodeGenerator.generate(2, string);
		assertEquals(true, bNode1.equals(bNode2));
	}
}
