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

    private float learningRate = 0.1f;


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


    }


    public void treino(float[][] entrada, float[][] esperado) {
        //Entrada -> Oculta
        Matriz matrizEntradas = new Matriz(entrada);
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

        //Backpropagation

        //Saida para oculta corrigida
        //Erro saida
        Matriz resultadoEsperado = new Matriz(esperado); //transforma resultado esperado em matriz
        System.out.println("ERRO SAIDA \n");
        float[][] erroSaida = Matriz.subMatriz(resultadoEsperado, resultadoFinalValoresSaida); //subtrai o erro da saida
        Matriz erroSaida2 = new Matriz(erroSaida);
        System.out.println("DERIVADA SIGMOID \n");
        float[][] derivadaSigmoid = Matriz.derivadaSigmoidEmCadaElementoMatriz(resultadoValoresSaida4); //faz o calculo do resultado da derivada simoid em cada elemento da saida
        Matriz derivadaSigmoid2 = new Matriz(derivadaSigmoid);
        System.out.println("CAMADA OCULTA TRANSPOSTA \n");
        float[][] oculta_transposta = Matriz.transporMatriz(resultadoFinalValoresOculta); //transpos a cama oculta
        Matriz oculta_transposta2 = new Matriz(oculta_transposta);
        //Gradient
        float[][] gradient = Matriz.hadamardMatriz(erroSaida2, derivadaSigmoid2); //gradient erro * derivada sigmoid saida
        Matriz gradient2 = new Matriz(gradient);
        gradient = Matriz.escalarMatriz(gradient2, learningRate); //Multiploica pelo lr
        Matriz gradient3 = new Matriz(gradient);

        float[][] deltaPesosOcultaSaida = Matriz.multiplicaMatriz(gradient3, oculta_transposta2); //Multiplica o gradient pela transpota da oculta (Deltas W)
        Matriz deltaPesosOcultaSaida2 = new Matriz(deltaPesosOcultaSaida);
        float[][] pesos_oculta_saida2 = Matriz.somaMatriz(pesos_oculta_saida, deltaPesosOcultaSaida2); //soma pesos com os deltas pesos
        Matriz pesos_oculta_saida3 = new Matriz(pesos_oculta_saida2);
        this.pesos_oculta_saida = pesos_oculta_saida3; //substitui pelos novos pesos

        //Oculta para entrada corrigida
        float[][] pesos_oculta_saida_transposta = Matriz.transporMatriz(pesos_oculta_saida); //transpos a matriz de pesos da oculta para saida
        Matriz pesos_oculta_saida_transposta2 = new Matriz(pesos_oculta_saida_transposta);
        System.out.println("ERRO OCULTA \n");
        float[][] erroOculta = Matriz.multiplicaMatriz(pesos_oculta_saida_transposta2, erroSaida2); // multiplica para termos o erro da oculta
        Matriz erroOculta2 = new Matriz(erroOculta);
        System.out.println("DERIVADA SIGMOID \n");
        float [][] derivadaOculta = Matriz.derivadaSigmoidEmCadaElementoMatriz(resultadoFinalValoresOculta.getData()); //faz o calculo do resultado da derivada simoid em cada elemento da oculta
        Matriz derivadaOculta2 = new Matriz(derivadaOculta);
        System.out.println("ENTRADA TRANSPOSTA \n");
        float [][] entradaTransposta = Matriz.transporMatriz(matrizEntradas); //Transpos entrada
        Matriz entradaTransposta2 = new Matriz(entradaTransposta);
        //GRADIENT
        float [][] gradienteOculta = Matriz.hadamardMatriz(erroOculta2, derivadaOculta2); //erro oculta * derivada oculta
        Matriz gradienteOculta2 = new Matriz(gradienteOculta);
        gradienteOculta = Matriz.escalarMatriz(gradienteOculta2, learningRate); //Multiploica pelo lr
        Matriz gradienteOculta3 = new Matriz(gradienteOculta);
        float [][] deltaPesosEntradaOculta = Matriz.multiplicaMatriz(gradienteOculta3, entradaTransposta2); //Multiplica o gradient pela transpota da entrada (Deltas W)
        Matriz deltaPesosEntradaOculta2 = new Matriz(deltaPesosEntradaOculta);
        float [][] pesos_entrada_oculta2 = Matriz.somaMatriz(pesos_entrada_oculta, deltaPesosEntradaOculta2); //soma pesos com os deltas pesos
        Matriz pesos_entrada_oculta3 = new Matriz(pesos_entrada_oculta2);
        this.pesos_entrada_oculta = pesos_entrada_oculta3;//substitui os pesos

    }

    public static double sigmoid(float x) {
        return 1 / (1 + Math.exp(-x));
    }

    public static double derivadaSigmoid(float x) {
        return x * (1 - x);
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


}
