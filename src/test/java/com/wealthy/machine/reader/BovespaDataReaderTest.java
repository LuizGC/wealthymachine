package com.wealthy.machine.reader;

import com.wealthy.machine.quote.BovespaStockDailyQuote;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class BovespaDataReaderTest {

    private static String ZIP_FILE_PATH = "http://www.b3.com.br/data/files/9C/F3/01/C4/297BE410F816C9E492D828A8/SeriesHistoricas_DemoCotacoesHistoricas12022003.zip";

    @Test
    public void readDataWithSuccess() {
        BovespaDataReader reader = new BovespaDataReader(ZIP_FILE_PATH);
        Stream<BovespaStockDailyQuote> stream = reader.stream();
        assertEquals("Test if all quotes are read correctly.", 551, stream.count());
    }

    @Test(expected = RuntimeException.class)
    public void invalidUrl() {
        BovespaDataReader reader = new BovespaDataReader("https://www.uol.com.br/");
        reader.stream();
    }
}