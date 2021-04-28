package Principal;

import Cerebro.RedeNeural;
import Utils.Matriz;


public class Main {

    public static void main(String[] args) {

        RedeNeural redeNeural1 = new RedeNeural(1, 3,2);

        float[][] testeEntrada = {{2}};
        float[][] testeEsperado = {{1}, {0}};

        redeNeural1.treino(testeEntrada, testeEsperado);

    }


}
