package Utils;

import Cerebro.RedeNeural;

public class Matriz {

    private int rows;
    private int cols;
    private float[][] data;

    //Matriz aleatoria com numeros de 0 a 10
    public Matriz(int rows, int cols) {

        this.rows = rows;
        this.cols = cols;
        this.data = new float[rows][cols];

        mapMatrizAleatorio(data);

    }

    //transformar array em um objeto matriz
    public Matriz(float[][] matriz) {
        this.data = matriz;
    }

    // Matriz aleatoria para pesos e bias
    public Matriz(int rows, int cols, String componente) {
        this.rows = rows;
        this.cols = cols;
        this.data = new float[rows][cols];
        System.out.println(componente);
        mapMatrizRandomica(data);
        System.out.println("--------------");
    }

    //Popula matriz aleatorio
    public void mapMatrizAleatorio(float[][] matriz) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matriz[i][j] = (float) (Math.floor(Math.random() * 10));
            }
        }
        data = matriz;
        printMatriz(matriz, rows, cols);
    }

    public void mapMatrizRandomica(float[][] matriz) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = (float) Math.random() * 2 - 1;
            }
        }
        data = matriz;
        printMatriz(matriz, rows, cols);
    }

    //Soma Matriz
    public static float[][] somaMatriz(Matriz matrizA, Matriz matrizB) {
        float[][] matrizResult = new float[matrizA.data.length][matrizB.data[0].length];
        for (int i = 0; i < matrizA.data.length; i++) {
            for (int j = 0; j < matrizA.data[0].length; j++) {
                matrizResult[i][j] = matrizA.data[i][j] + matrizB.data[i][j];
            }
        }
        System.out.print("Resultado matriz soma: ");
        printMatriz(matrizResult, matrizA.data.length, matrizB.data[0].length);
        return matrizResult;
    }

    //sub Matriz
    public static float[][] subMatriz(Matriz matrizA, Matriz matrizB) {
        float[][] matrizResult = new float[matrizA.data.length][matrizB.data[0].length];
        for (int i = 0; i < matrizA.data.length; i++) {
            for (int j = 0; j < matrizA.data[0].length; j++) {
                matrizResult[i][j] = matrizA.data[i][j] - matrizB.data[i][j];
            }
        }
        System.out.print("Resultado matriz soma: ");
        printMatriz(matrizResult, matrizA.data.length, matrizB.data[0].length);
        return matrizResult;
    }

    // Multiplica matriz
    public static float[][] multiplicaMatriz(Matriz matrizA, Matriz matrizB) {
        float[][] matrizResult = new float[matrizA.data.length][matrizB.data[0].length];
        for (int rows = 0; rows < matrizA.data.length; rows++) {
            for (int cols = 0; cols < matrizB.data[0].length; cols++) {
                float acum = 0;
                for (int i = 0; i < matrizB.data.length; i++) {
                    acum = acum + matrizA.data[rows][i] * matrizB.data[i][cols];
                    matrizResult[rows][cols] = acum;
                }

            }
        }
        System.out.print("Resultado matriz multiplicação: ");
        printMatriz(matrizResult, matrizA.data.length, matrizB.data[0].length);
        return matrizResult;
    }

    //Multplicar Matriz elemento por elemento (hadamard)
    public static float[][] hadamardMatriz(Matriz matrizA, Matriz matrizB) {
        float[][] matrizResult = new float[matrizA.data.length][matrizB.data[0].length];
        for (int i = 0; i < matrizA.data.length; i++) {
            for (int j = 0; j < matrizA.data[0].length; j++) {
                matrizResult[i][j] = matrizA.data[i][j] * matrizB.data[i][j];
            }
        }
        System.out.print("Resultado matriz hadamard: ");
        printMatriz(matrizResult, matrizA.data.length, matrizB.data[0].length);
        return matrizResult;
    }

    //Escalar matriz
    public static float[][] escalarMatriz(Matriz matrizA, float escalar) {
        float[][] matrizResult = new float[matrizA.data.length][matrizA.data[0].length];
        for (int i = 0; i < matrizA.data.length; i++) {
            for (int j = 0; j < matrizA.data[0].length; j++) {
                matrizResult[i][j] = matrizA.data[i][j] * escalar;
            }
        }
        System.out.print("Resultado matriz escalar: ");
        printMatriz(matrizResult, matrizA.data.length, matrizA.data[0].length);
        return matrizResult;
    }

    //Transpor matriz
    public static float[][] transporMatriz(Matriz matriz) {
        float[][] matrizResult = new float[matriz.data[0].length][matriz.data.length];
        for (int i = 0; i < matriz.data.length; i++) {
            for (int j = 0; j < matriz.data[0].length; j++) {
                matrizResult[j][i] = matriz.data[i][j];
            }
        }
        printMatriz(matrizResult, matriz.data[0].length, matriz.data.length);
        return matrizResult;
    }


    //Imprime Matriz
    public static void printMatriz(float[][] matriz, int rows, int cols) {
        System.out.println(" ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    public static void printMatriz2(float[][] matriz) {
        System.out.println(" ");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    public static float[][] funcaoEmCadaElementoMatriz(float[][] matriz) {
        float[][] matrizResult = new float[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                matrizResult[i][j] = (float) RedeNeural.sigmoid(matriz[i][j]);
            }
        }
        System.out.print("Resultado aplicação da função: ");
        printMatriz2(matrizResult);
        return matrizResult;
    }

    public static float[][] derivadaSigmoidEmCadaElementoMatriz(float[][] matriz) {
        float[][] matrizResult = new float[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                matrizResult[i][j] = (float) RedeNeural.derivadaSigmoid(matriz[i][j]);
            }
        }
        System.out.print("Resultado aplicação da função: ");
        printMatriz2(matrizResult);
        return matrizResult;
    }

    //Getters e Setters
    public float[][] getData() {
        return data;
    }


}