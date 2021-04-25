package Cerebro;

import Utils.Matriz;

public class RedeNeural {
    //nos
    private int nos_entrada;
    private int nos_oculta;
    private int nos_saida;
    //bias
    private Matriz bias_entrada_oculta;
    private Matriz bias_oculta_saida;
    //pesos
    private Matriz pesos_entrada_oculta;
    private Matriz pesos_oculta_saida;


    private float valorDesejado;

    public RedeNeural(int nos_entrada, int nos_oculta, int nos_saida) {
        //numero de nos
        this.nos_entrada = nos_entrada;
        this.nos_oculta = nos_oculta;
        this.nos_saida = nos_saida;

        //valor do bias
        this.bias_entrada_oculta = new Matriz(nos_oculta, 1, "matriz bias da entrada para oculta");
        this.bias_oculta_saida = new Matriz(nos_oculta, 1, "matriz bias da oculta para a saida");

        //valor pesos
        this.pesos_entrada_oculta = new Matriz(nos_oculta, nos_entrada, "matriz peso da entrada para oculta");
        this.pesos_oculta_saida = new Matriz(nos_saida, nos_oculta, "matriz peso da oculta para a saida");

        //Entrada
        float[][] input = {{2}};
        feedFoward(input);
    }

    public void feedFoward(float[][] input) {
        //Entrada -> Oculta
        Matriz matrizEntradas = new Matriz(input);
        //Multiplica entrada por pesos e somataria
        float[][] matrizValoresOculta = Matriz.multiplicaMatriz(pesos_entrada_oculta, matrizEntradas); //retorna float [][]
        Matriz matrizValoresOculta2 = new Matriz(matrizValoresOculta); //transforma em objetos
        //Soma com o bias
        float[][] matrizValoresOculta3 = Matriz.somaMatriz(matrizValoresOculta2, bias_entrada_oculta);//retorna float[][]
        //função de ativação em cada nó
        float[][] resultadoValoresOculta4 = Matriz.funcaoEmCadaElementoMatriz(matrizValoresOculta3);
        Matriz resultadoFinalValoresOculta = new Matriz(resultadoValoresOculta4);

        //Oculta -> Saida
        //Multiplica entrada por pesos e somataria
        float[][] matrizValoresSaida = Matriz.multiplicaMatriz(pesos_oculta_saida, resultadoFinalValoresOculta);
        Matriz matrizValoresSaida2 = new Matriz(matrizValoresSaida);
        //soma com bias
        float[][] matrizValoresSaida3 = Matriz.somaMatriz(matrizValoresSaida2, bias_oculta_saida);
        //função de ativação em cada nó
        float[][] resultadoValoresSaida4 = Matriz.funcaoEmCadaElementoMatriz(matrizValoresSaida3);
        Matriz resultadoFinalValoresSaida = new Matriz(resultadoValoresSaida4);
    }

    public static double sigmoid(float x) {
        return 1 / (1 + Math.exp(-x));
    }

    public static int funcaoDegral(float n) {
        if (n > 0) {
            return 1;
        } else if (n == 0) {
            return 0;
        } else if (n < 0) {
            return -1;
        }
        return 0;
    }

    public static float[][] recauculoPesos(float[][] entrada, Matriz peso, int valorDesejado, int resultadoDegrau) {

        float taxaAprendizado = 0.1f;
        //taxaAprendizado * (valor desejado - valor degrau) * entrada n [1][3]
        //matriz
        float[][] matrizResultante = new float[peso.getData().length][peso.getData()[0].length];//[3][1]
        System.out.println("Teste taxaAprendizado * (valor desejado - valor degrau) * entrada n");
        for (int i = 0; i < peso.getData().length; i++) {
            for (int j = 0; j < peso.getData()[0].length; j++) {
                matrizResultante[i][j] = (taxaAprendizado * (valorDesejado - resultadoDegrau)) * entrada[j][i];
                System.out.println(matrizResultante[i][j]);
            }
        }

        //DeltaPeso1 = taxaAprendizado * (valor desejado - valor degrau) * entrada n1
        //DeltaPeso2 = taxaAprendizado * (valor desejado - valor degrau) * entrada n2
        //DeltaPeso3 = taxaAprendizado * (valor desejado - valor degrau) * entrada n3

        //[DeltaPeso1, DeltaPeso2, DeltaPeso3]

        Matriz pesoDelta = new Matriz(matrizResultante);

        return Matriz.somaMatriz(peso, pesoDelta);

    }
}
