import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Garmonic {
    private int countOfGarmonics;
    private int limitFrequency;
    private int countOfDiscreteCalls;
    private double[] sygnalsForResultingGarmonic;

    public Garmonic(int countOfGarmonics, int limitFrequency, int countOfDescreteCalls) {
        this.countOfGarmonics = countOfGarmonics;
        this.limitFrequency = limitFrequency;
        this.countOfDiscreteCalls = countOfDescreteCalls;
        this.sygnalsForResultingGarmonic = new double[countOfDescreteCalls];
    }

    public List<Number> calculateSygnalsForResultingGarmonic(){
        Random r = new Random();
        double A = r.nextDouble();
        double fi = r.nextDouble()*Math.PI;
        for (int i = 0; i < countOfGarmonics; i++) {
            for (int j = 0; j < countOfDiscreteCalls; j++) {
                sygnalsForResultingGarmonic[j] += A * Math.sin(1.*limitFrequency*(i+1)/countOfGarmonics*j + fi);//
            }
        }
        return Arrays.stream(sygnalsForResultingGarmonic).boxed().collect(Collectors.toList());
    }

    public double calculateMathExpectation(){
        double sum = 0 ;
        for (double sygnal : sygnalsForResultingGarmonic) {
            sum+=sygnal;
        }
        return sum/sygnalsForResultingGarmonic.length;
    }

    public double calculateDispersion(){
        double sum = 0;
        double mathExpectation = calculateMathExpectation();
        for (double sygnal : sygnalsForResultingGarmonic) {
            sum += Math.pow(sygnal - mathExpectation, 2);
        }
        return sum/(sygnalsForResultingGarmonic.length - 1);
    }


    public List<Number> calculateAutoCorrelation(){
        return calculateMutualCorrelation(this);
    }

    public List<Number> calculateMutualCorrelation(Garmonic otherGarmonic){
        int N = getCountOfDiscreteCalls();
        double[] correlation_arr = new double[N/2];
        double mathExp1 = calculateMathExpectation();
        double mathExp2 = otherGarmonic.calculateMathExpectation();

        for (int tau = 0; tau < N/2; tau++){
            double correlation = 0;
            for (int t = 0; t < N/2; t++){
                correlation += (getSygnalsForResultingGarmonic()[t] - mathExp1)*(otherGarmonic.getSygnalsForResultingGarmonic()[t+tau] - mathExp2);
            }
            correlation_arr[tau] = correlation/(N-1);
        }
        return Arrays.stream(correlation_arr).boxed().collect(Collectors.toList());
    }
    public int getCountOfDiscreteCalls() {
        return countOfDiscreteCalls;
    }

    public double[] getSygnalsForResultingGarmonic() {
        return sygnalsForResultingGarmonic;
    }
}
