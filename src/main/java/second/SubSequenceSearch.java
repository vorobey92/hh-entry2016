package second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 2. Бесконечная последовательность
 * Created by ia.vorobev on 24.09.2016.
 */
public class SubSequenceSearch {

    private int[] rightEntries;
    private String mask;

    private StringBuilder infSequence;

    private long currentNumber;
    private int truncatedIndex;

    /**
     * @param mask string representation of positive number
     * @throws IllegalArgumentException if mask is not a number
     *                                  or if larger than 50
     *                                  or if number <0
     */
    public SubSequenceSearch(String mask) throws IllegalArgumentException {
        checkMask(mask);
        this.mask = mask;
        int maskLength = mask.length();

        this.infSequence = getInitialSequence(maskLength * 2);

        int dictPower = 10;
        rightEntries = new int[dictPower];

        for (int i = 0; i < dictPower; i++) {
            rightEntries[i] = -1;
        }
        for (int j = 0; j < maskLength; j++) {
            rightEntries[Character.getNumericValue(mask.charAt(j))] = j;
        }
    }

    /**
     * @return index of mask's first entry in infinitive sequence of natural numbers
     */
    public long find() {
        int maskLength = mask.length();
        int skip;

        for (int i = 0; true; i += skip) {
            skip = 0;

            //append
            if ((i + maskLength) > infSequence.length()) {
                increaseSequenceLength(infSequence.length() * 2);
            }

            //обрезаем последовательность на случай, если искомая маска очень далеко
            // чтобы избежать java.lang.OutOfMemoryError
            if (i > (infSequence.length() / 2) + 1 + maskLength) {
                truncateSequence();
                i = 0;
            }

            for (int j = maskLength - 1; j >= 0; j--) {
                if (mask.charAt(j) != infSequence.charAt(i + j)) {
                    skip = j - rightEntries[Character.getNumericValue(infSequence.charAt(i + j))];
                    if (skip < 1) {
                        skip = 1;
                    }
                    break;
                }
            }
            if (skip == 0) {
                return (i + 1) + truncatedIndex;
            }
        }
    }

    /**
     * Для прекращения ввода данных нужно ввести пустую строку.
     */
    public static void main(String[] args) throws IOException {
        List<SubSequenceSearch> maskList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while (!"".equals(line = reader.readLine())) {
                maskList.add(new SubSequenceSearch(line));
            }
        }
        maskList.forEach(s -> System.out.println(s.find()));
    }

    private StringBuilder getInitialSequence(int length) {
        StringBuilder sb = new StringBuilder(length);
        long i;
        for (i = 1; i <= length; i++) {
            sb.append(i);
        }
        currentNumber = i;
        return sb;
    }

    private void increaseSequenceLength(int newLength) {
        long oldLength = infSequence.length();
        if (newLength < oldLength) {
            return;
        }
        for (long i = oldLength; i < newLength; i++) {
            infSequence.append(currentNumber++);
        }
    }

    private void checkMask(String mask) {
        if (mask == null || mask.isEmpty()) {
            throw new IllegalArgumentException("Empty string");
        }
        if (mask.length() > 50) {
            throw new IllegalArgumentException("Too large string");
        }

        BigInteger intMask;
        try {
            intMask = new BigInteger(mask);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Not a number. Stacktrace: " + e);
        }
        if (intMask.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Negative number " + intMask);
        }
    }

    private void truncateSequence() {
        int numbersToTruncate = infSequence.length() / 2;
        infSequence.delete(0, numbersToTruncate);
        truncatedIndex += numbersToTruncate;
    }
}