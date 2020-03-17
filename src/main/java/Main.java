import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Garmonic garmonic1 = new Garmonic(12,900,256);
        Garmonic garmonic2 = new Garmonic(12,900,256);
        List<Number> xData1 = initializationXData(garmonic1.getCountOfDiscreteCalls());


        /*XYChart chart1 = new XYChartBuilder()
                .width(1200)
                .height(800)
                .title("x(t)")
                .xAxisTitle("t")
                .yAxisTitle("x")
                .build();

        chart1.addSeries("x(t)", xData1 ,garmonic1.calculateSygnalsForResultingGarmonic());

        List<Number> mathExpectation = Collections.nCopies(garmonic1.getCountOfDiscreteCalls(), garmonic1.calculateMathExpectation());
        List<Number> dispersion = Collections.nCopies(garmonic1.getCountOfDiscreteCalls(), garmonic1.calculateDispersion() + garmonic1.calculateMathExpectation());

        chart1.addSeries("MathExpectation", xData1 , mathExpectation);
        chart1.addSeries("Dispersion", xData1, dispersion);
*/
        XYChart chart2 = new XYChartBuilder()
                .width(1200)
                .height(800)
                .title("Rxx")
                .xAxisTitle("t")
                .yAxisTitle("Rxx")
                .build();


        garmonic1.calculateSygnalsForResultingGarmonic();
        garmonic2.calculateSygnalsForResultingGarmonic();
        List<Number> mutualCorrelation = garmonic2.calculateMutualCorrelation(garmonic1);
        List<Number> autoCorrelation = garmonic2.calculateAutoCorrelation();

        List<Number> xData2 = initializationXData(mutualCorrelation.size());

        chart2.addSeries("Mutual Correlation", xData2, mutualCorrelation);
        chart2.addSeries("Auto Correlation", xData2, autoCorrelation);

//        new SwingWrapper<>(chart1).displayChart();
        new SwingWrapper<>(chart2).displayChart();

    }

    private static List<Number> initializationXData(int countOfDiscreteCalls) {
        if(countOfDiscreteCalls<0)
            return null;
        List<Number> res = new ArrayList<>();
        for (int i = 0; i < countOfDiscreteCalls; i++) {
            res.add(i);
        }
        return res;
    }


}
