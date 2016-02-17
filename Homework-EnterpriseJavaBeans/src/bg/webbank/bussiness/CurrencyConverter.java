package bg.webbank.bussiness;

import java.math.BigDecimal;

public final class CurrencyConverter {

	ExchangeRateLoader rateLoader = new ExchangeRateLoader();

	public BigDecimal convert(BigDecimal value, String fromCurrency, String toCurrency) {
		double rate;
		BigDecimal result;

		switch (fromCurrency) {
		case "BGN":
			switch (toCurrency) {
			case "EUR":
				rate = rateLoader.getRate("BGN", "EUR");
				result = value.multiply(new BigDecimal(rate));

				return result;
			case "USD":
				rate = rateLoader.getRate("BGN", "USD");
				result = value.multiply(new BigDecimal(rate));

				return result;
			}

		case "EUR":
			switch (toCurrency) {
			case "BGN":
				rate = rateLoader.getRate("EUR", "BGN");
				result = value.multiply(new BigDecimal(rate));

				return result;
			case "USD":
				rate = rateLoader.getRate("EUR", "USD");
				result = value.multiply(new BigDecimal(rate));

				return result;
			}

		case "USD":
			switch (toCurrency) {
			case "EUR":
				rate = rateLoader.getRate("USD", "EUR");
				result = value.multiply(new BigDecimal(rate));

				return result;
			case "BGN":
				rate = rateLoader.getRate("USD", "BGN");
				result = value.multiply(new BigDecimal(rate));

				return result;
			}

		default:
			return value;
		}
	}
}
