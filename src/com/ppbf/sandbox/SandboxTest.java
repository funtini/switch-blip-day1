package com.ppbf.sandbox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.ppbf.solutions.models.Bet;
import com.ppbf.solutions.models.Event;
import com.ppbf.solutions.models.Market;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class SandboxTest {

    private final List<String> lines = new ArrayList<>();

    @Before
    public void setup() throws Exception {
        lines.add("Champions League;Liverpool vs Porto;Liverpool Wins;1;3.0");
        lines.add("Champions League;Liverpool vs Porto;Liverpool Wins;2;1.0");
        lines.add("Champions League;Liverpool vs Porto;Draw;2;2.0");
    }

    @Test
    public void ex1() throws Exception {
        Bet ex1 = new Bet("Champions League",
            new Event("Liverpool vs Porto",
                new Market("Liverpool Wins", 2, new BigDecimal("1.0"))));

        Bet ex2 = new Bet("Champions League",
            new Event("Liverpool vs Porto",
                new Market("Draw", 2, new BigDecimal("2.0"))));

        List<Bet> expected = new ArrayList<>();
        expected.add(ex1);
        expected.add(ex2);

        List<Bet> actual = Sandbox.ex1(lines, 2);
        assertEquals(actual, expected);
    }

    @Test
    public void ex2() throws Exception {
        Bet ex1 = new Bet("Champions League",
            new Event("Liverpool vs Porto",
                new Market("Liverpool Wins", 2, new BigDecimal("1.0"))));

        Bet ex2 = new Bet("Champions League",
            new Event("Liverpool vs Porto",
                new Market("Draw", 2, new BigDecimal("2.0"))));

        Bet ex3 = new Bet("Champions League",
            new Event("Liverpool vs Porto",
                new Market("Liverpool Wins", 1, new BigDecimal("3.0"))));

        List<Bet> expected = new ArrayList<>();
        expected.add(ex3);
        expected.add(ex2);
        expected.add(ex1);

        List<Bet> actual = Sandbox.ex2(lines);
        assertEquals(actual, expected);
    }

    @Test
    public void ex3_1_1() throws Exception {
        BigDecimal totalCash = Sandbox
            .validateAndUpdateTotalMoney(lines, new BigDecimal("20.0"), 4, new BigDecimal("20.0"));
        BigDecimal expectedTotalCash = new BigDecimal("20.0");
        assertEquals(totalCash, expectedTotalCash);

        totalCash = Sandbox
            .validateAndUpdateTotalMoney(lines, new BigDecimal("20.0"), 1, new BigDecimal("20.0"));
        expectedTotalCash = new BigDecimal("0.0");
        assertEquals(totalCash, expectedTotalCash);
    }

    @Test
    public void ex3_1_2() throws Exception {
        assertTrue(false);
    }

    @Test
    public void ex3_2() throws Exception {
        assertTrue(false);
    }
}
