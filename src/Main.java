package src;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Main {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		try {
			File inputFile = new File("countries.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			SAXHandler userhandler = new SAXHandler();
			saxParser.parse(inputFile, userhandler);
			Graph g = userhandler.getGraph();
			g.calculerItineraireMinimisantNombreDeFrontieres("DZA", "BFA", "output.xml");
			g.calculerItineraireMinimisantPopulationTotale("DZA", "BFA", "output2.xml");
			g.calculerItineraireMinimisantNombreDeFrontieresEtPopulation("DZA", "BFA", "output3.xml");

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println(System.currentTimeMillis() - start);
	}
}
