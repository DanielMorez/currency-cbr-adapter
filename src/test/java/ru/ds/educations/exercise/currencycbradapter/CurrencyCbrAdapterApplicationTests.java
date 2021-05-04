package ru.ds.educations.exercise.currencycbradapter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ds.educations.exercise.currencycbradapter.cbr.service.CbrService;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class CurrencyCbrAdapterApplicationTests {

	@Autowired
	private CbrService cbrService;

	@Test
	public void getCursOnDateTest() {
		String result = "{\"onDate\":\"2021-04-04\",\"rates\":[{\"currency\":\"AUD\",\"curs\":57.9451},{\"currency\":\"AZN\",\"curs\":44.7754},{\"currency\":\"GBP\",\"curs\":105.2856},{\"currency\":\"AMD\",\"curs\":14.3602},{\"currency\":\"BYN\",\"curs\":28.8288},{\"currency\":\"BGN\",\"curs\":45.8191},{\"currency\":\"BRL\",\"curs\":13.3254},{\"currency\":\"HUF\",\"curs\":24.8362},{\"currency\":\"HKD\",\"curs\":97.8260},{\"currency\":\"DKK\",\"curs\":12.0484},{\"currency\":\"USD\",\"curs\":76.0734},{\"currency\":\"EUR\",\"curs\":89.5916},{\"currency\":\"INR\",\"curs\":10.3791},{\"currency\":\"KZT\",\"curs\":17.8177},{\"currency\":\"CAD\",\"curs\":60.5632},{\"currency\":\"KGS\",\"curs\":89.6806},{\"currency\":\"CNY\",\"curs\":11.5950},{\"currency\":\"MDL\",\"curs\":42.1226},{\"currency\":\"NOK\",\"curs\":89.2409},{\"currency\":\"PLN\",\"curs\":19.5366},{\"currency\":\"RON\",\"curs\":18.2540},{\"currency\":\"XDR\",\"curs\":107.7671},{\"currency\":\"SGD\",\"curs\":56.6107},{\"currency\":\"TJS\",\"curs\":66.7193},{\"currency\":\"TRY\",\"curs\":94.6669},{\"currency\":\"TMT\",\"curs\":21.7664},{\"currency\":\"UZS\",\"curs\":72.6410},{\"currency\":\"UAH\",\"curs\":27.2008},{\"currency\":\"CZK\",\"curs\":34.3447},{\"currency\":\"SEK\",\"curs\":87.2782},{\"currency\":\"CHF\",\"curs\":80.8260},{\"currency\":\"ZAR\",\"curs\":51.9623},{\"currency\":\"KRW\",\"curs\":67.4631},{\"currency\":\"JPY\",\"curs\":68.8291}]}";
		String onDate = "{\"onDate\":\"2021-04-04\"}";
		String cursOnDate = cbrService.getCursOnDate(onDate);
		System.out.println(cursOnDate);
		assertThat(cursOnDate.equals(result));
	}



}
