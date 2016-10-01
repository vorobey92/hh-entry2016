package second;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ia.vorobev on 27.09.2016.
 */
public class SubSequenceSearchTest {

    @Test
    public void findZeroDigitTest() throws Exception {
        SubSequenceSearch sss = new SubSequenceSearch("0");
        assertEquals(11, sss.find());
    }

    @Test
    public void findSequenceOf111Test() {
        SubSequenceSearch sss = new SubSequenceSearch("111");
        assertEquals(12, sss.find());
    }

    @Test
    public void findSequenceOf50CharactersMaskTest() {
        SubSequenceSearch sss = new SubSequenceSearch("12345678910111213141516171819202122232425262728293");
        assertEquals(1, sss.find());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionIfMaskIsNegativeNumberTest() {
        SubSequenceSearch sss = new SubSequenceSearch("-1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionIfMaskTooLarge() {
        SubSequenceSearch sss = new SubSequenceSearch("123456789101112131415161718192021222324252627282930");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionIfMaskIsEmpty() {
        SubSequenceSearch sss = new SubSequenceSearch("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionIfMaskIsNull() {
        SubSequenceSearch sss = new SubSequenceSearch(null);
    }
}