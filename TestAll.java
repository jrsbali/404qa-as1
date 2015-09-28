package com.poker;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	TestPokerRound.class,
	TestPokerOutcomes.class,
	TestPokerDrawOutcomes.class
})



public class TestAll {

}
