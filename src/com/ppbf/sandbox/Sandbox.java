package com.ppbf.sandbox;

import com.ppbf.solutions.models.Bet;
import com.ppbf.solutions.models.Event;
import com.ppbf.solutions.models.Market;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Sandbox implements Comparator<Bet> {

	// Ex1: Given a List of lines on the file;
	// Given a marketId;
	// Write a function that finds the corresponding value of marketId in the list.
	// TIP: print the whole line
	
	
	public static List<Bet> ex1(List<String> lines, long marketId) {
		List<Bet> result = new ArrayList<>();
		for (String linha : lines) {
			String[] linhas = linha.split(";");
			long markId = Long.parseLong(linhas[3]);
			if (markId == marketId) {

				long id = Long.parseLong(linhas[3]);
				Market market1 = new Market(linhas[2], id, new BigDecimal(linhas[4]));
				Event event = new Event(linhas[1], market1);
				Bet bet1 = new Bet(linhas[0], event);
				result.add(bet1);
			}
		}

		System.out.println(result);

		return result;
	}
	
	//parse List<String> bets in List<Bet>
	public static List<Bet> parseBets(List<String> lines)
	{
		List<Bet> result = new ArrayList<>();
		for (String sl : lines) {
			String[] splittedLines = sl.split(";");
			long markId = Long.parseLong(splittedLines[3]);
			Market market = new Market(splittedLines[2], markId, new BigDecimal(splittedLines[4]));
			Event event = new Event(splittedLines[1], market);
			Bet bet = new Bet(splittedLines[0], event);
			result.add(bet);

		}
		return result;
		
	}

	// Ex2: Given a List of lines on the file;
	// Write a function that sorts the competitions by odd value (ascending).
	// TIP: sort the whole line
	public static List<Bet> ex2(List<String> lines) {
		
		List<Bet> result = parseBets(lines);
		Collections.sort(result,

				new Comparator<Bet>() {

					@Override
					public int compare(Bet o1, Bet o2) {
						return o1.event.market.odds.compareTo(o2.event.market.odds);
					}

				});

		System.out.println(result);
		return result;
	}

	// Ex3: Given a List of lines on the file;
	// Given the total money;
	// While you still have money available:
	// Write a function that lets you bet on one or more markets.
	//
	// TIP: use validateAndUpdateTotalMoney to validate all data and update
	// totalMoney.
	// use addMarketAndStateToMap to return a Map with the marketId and the stake.
	public static BigDecimal validateAndUpdateTotalMoney(List<String> lines, BigDecimal totalMoney, long marketId,
			BigDecimal stake) {
		
		List<Bet> result = parseBets(lines);
		

		if (stake.compareTo(totalMoney) < 0) {

			for (Bet b : result) {
				if (b.event.market.id == marketId) {
					totalMoney = totalMoney.subtract(stake);
				}

			}

		}
		return totalMoney;
	}

	public static Map<Long, BigDecimal> addMarketAndStateToMap(Map<Long, BigDecimal> bets, long marketId,
			BigDecimal stake) {
		
		if (bets.containsKey(marketId))
		{
			stake=stake.add(bets.get(marketId));
		}
		bets.put(marketId, stake);
		return bets;
	}

	// Ex3_2: Given a List of lines on the file;
	// Given a List of bets (List of marketId and the stake of the bet);
	// Write a function that calculates the possible profit for each bet
	// TIP: return a list of profit values
	public static List<BigDecimal> ex3_2(List<String> lines, Map<Long, BigDecimal> bets) {
		
		List<Bet> result = parseBets(lines);
		List<BigDecimal> profitList = new ArrayList<>();
		
		for (Bet b : result) {
			if (bets.containsKey(b.event.market.id)) {
				profitList.add(bets.get(b.event.market.id).multiply((b.event.market.odds.subtract(new BigDecimal(1)))));
			}
		}
		return profitList;
	}
	
	//sum profit
	public static BigDecimal sum(List<BigDecimal> decimalList)
	{
		BigDecimal sum = new BigDecimal(0.0);
		for (BigDecimal num : decimalList)
		{
			sum = sum.add(num);
		}
		return sum;
	}

	@Override
	public int compare(Bet o1, Bet o2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
