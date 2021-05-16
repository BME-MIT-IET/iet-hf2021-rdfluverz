// Copyright (c) 2014, Clark & Parsia, LLC. <http://www.clarkparsia.com>

package com.complexible.common.csv;

import au.com.bytecode.opencsv.CSVReader;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.io.Files;
import io.airlift.command.*;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFWriter;
import org.openrdf.rio.Rio;

import java.io.File;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * Converts a CSV file to RDF based on a given template
 *
 * @author Evren Sirin
 */
@Command(name = "convert", description = "Runs the conversion.")
public class CSV2RDF implements Runnable {
    private static final Charset INPUT_CHARSET = Charset.defaultCharset();
    private static final Charset OUTPUT_CHARSET = Charsets.UTF_8;

    @Option(name = "--no-header", arity = 0, description = "If csv file does not contain a header row")
    boolean noHeader = false;

    @Option(name = {"-s", "--separator"}, description = "Seperator character used in the csv file or ',' by default.")
    String separator = String.valueOf(CSVReader.DEFAULT_SEPARATOR);

    @Option(name = {"-q", "--quote"}, description = "Quote character used in the csv file or '\"' by default.")
    String quote = String.valueOf(CSVReader.DEFAULT_QUOTE_CHARACTER);

    @Option(name = {"-e", "--escape"}, description = "Escape character used in the csv file or '\\' by default.")
    String escape = String.valueOf(CSVReader.DEFAULT_ESCAPE_CHARACTER);

    @Arguments(required = true, description = "File arguments. The extension of template file and output file determines the RDF format that will be used for them (.ttl = Turtle, .nt = N-Triples, .rdf = RDF/XML)", title = {
            "templateFile", "csvFile", "outputFile"})
    public List<String> files;

    public void run() {
        Preconditions.checkArgument(files.size() >= 3, "Missing arguments");
        Preconditions.checkArgument(files.size() <= 3, "Too many arguments");

        File templateFile = new File(files.get(0));
        File inputFile = new File(files.get(1));
        File outputFile = new File(files.get(2));
        System.out.println("CSV to RDF conversion started...");
        System.out.println("Template: " + templateFile);
        System.out.println("Input   : " + inputFile);
        System.out.println("Output  : " + outputFile);

        int inputRows;
        int outputTriples;

        try {
            Reader in = Files.newReader(inputFile, INPUT_CHARSET);
            CSVReader reader = new CSVReader(in, toChar(separator), toChar(quote), toChar(escape));
            String[] row = reader.readNext();

            Preconditions.checkNotNull(row, "Input file is empty!");

            Writer out = Files.newWriter(outputFile, OUTPUT_CHARSET);
            RDFWriter writer = Rio.createWriter(RDFFormat.forFileName(outputFile.getName(), RDFFormat.TURTLE), out);

            Template template = new Template(Arrays.asList(row), templateFile, writer, noHeader);

            if (noHeader) {
                template.generate(row, writer);
            }

            while ((row = reader.readNext()) != null) {
                template.generate(row, writer);
            }

            writer.endRDF();

            inputRows = template.getInputRows();
            outputTriples = template.getOutputTriples();

            reader.close();
            in.close();
            out.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Converted %,d rows to %,d triples%n", inputRows, outputTriples);
    }

    public static char toChar(String value) {
        Preconditions.checkArgument(value.length() == 1, "Expecting a single character but got %s", value);
        return value.charAt(0);
    }

    public static void main(String[] args) throws Exception {
        try {
            Cli.<Runnable>builder("csv2rdf").withDescription("Converts a CSV file to RDF based on a given template")
                    .withDefaultCommand(CSV2RDF.class).withCommand(CSV2RDF.class).withCommand(Help.class)
                    .build().parse(args).run();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

