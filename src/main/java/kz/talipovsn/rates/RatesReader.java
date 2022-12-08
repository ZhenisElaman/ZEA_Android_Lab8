package kz.talipovsn.rates;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// СОЗДАТЕЛЬ КОТИРОВОК ВАЛЮТ
public class RatesReader {

    private static final String BASE_URL = "https://finance.kz/companies/s/government"; // Адрес с котировками

    // Парсинг котировок из формата html web-страницы банка, при ошибке доступа возвращаем null
    public static String getRatesData() {
        StringBuilder data = new StringBuilder();
        try {
            Document doc = Jsoup.connect(BASE_URL).timeout(5000).get(); // Создание документа JSOUP из html
            data.append("Банки и финансовые компании:\n"); // Считываем заголовок страницы
            data.append("\n");

            Elements y = doc.select("#__nuxt");
            Elements x = y.select("div.company-item");
            Elements c = x.select("a.company-title");
            System.out.println("###########" + c.size());

            for (int i = 0; i < c.size(); i++) {
                Element e2 = c.get(i);
                data.append("Version: " + e2.text() + "\n");
            }
            data.append("\n");

            /*data.append(String.format("%12s %12s %12s %12s %12s\n", "PM2.5 - это твердые микрочастицы " +
                            "и мельчайшие капельки жидкости (10 нм - 2,5 мкм в диаметре)\n",
                    "PM10 - это любые твердые частицы в воздухе диаметром 10 микрометров или меньше\n",
                    "NO2 - Оксид азота\n", "SO2 - Оксид серы\n", "CO - Монооксид углерода\n").trim());*/

        } catch (Exception ignored) {
            return null; // При ошибке доступа возвращаем null
        }
        return data.toString().trim(); // Возвращаем результат
    }
}