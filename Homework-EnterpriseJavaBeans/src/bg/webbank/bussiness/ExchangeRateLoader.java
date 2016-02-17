package bg.webbank.bussiness;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public final class ExchangeRateLoader {

	public double getRate(String fromCurrency, String toCurrency) {
		try {
			URL url = new URL("http://quote.yahoo.com/d/quotes.csv?f=l1&s=" + fromCurrency + toCurrency + "=X");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line = reader.readLine();
			if (line.length() > 0) {
				return Double.parseDouble(line);
			}
			reader.close();
		} catch (IOException | NumberFormatException e) {
			System.out.println(e.getMessage());
		}

		return 0;
	}
}
