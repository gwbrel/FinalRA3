import java.util.Random;

public class GeradorDados {
    public static void main(String[] args) {
        //
        Random random = new Random(52);

        //
        int[] tamanhosTabela = {1000, 10000, 100000}; //
        int[] tiposHash = {1, 2, 3}; //
        int[] tamanhosConjunto = {10000, 100000, 1000000}; //


        int tamanhoTamanhosTabela = tamanhoArray(tamanhosTabela);
        int tamanhoTiposHash = tamanhoArray(tiposHash);
        int tamanhoTamanhosConjunto = tamanhoArray(tamanhosConjunto);


        for (int i = 0; i < tamanhoTamanhosConjunto; i++) {
            int tamanhoConjunto = tamanhosConjunto[i];
            System.out.println("\n\nInserindo " + tamanhoConjunto + " registros...");

            for (int j = 0; j < tamanhoTamanhosTabela; j++) {
                int tamanhoTabela = tamanhosTabela[j];

                for (int k = 0; k < tamanhoTiposHash; k++) {
                    int tipoHash = tiposHash[k]; //
                    TabelaHash tabela = new TabelaHash(tamanhoTabela, tipoHash); //

                    // registrar o tempo inicial em milissegundos
                    long tempoInicioInsercao = System.currentTimeMillis();

                    // Inserir dados na tabela de hash
                    for (int m = 0; m < tamanhoConjunto; m++) {
                        String codigo = gerarCodigoAleatorio(random); //
                        tabela.inserir(new Registro(codigo)); //
                    }

                    //
                    long tempoFinalInsercao = System.currentTimeMillis();
                    long duracaoInsercao = tempoFinalInsercao - tempoInicioInsercao;

                    System.out.println("\nTabela de tamanho " + tamanhoTabela + " usando tipo de hash " + tipoHash);
                    System.out.println("Tempo de inserção: " + duracaoInsercao + " ms.");
                    System.out.println("Número de colisões: " + tabela.getNumeroColisoes());

                    //
                    for (int n = 0; n < 5; n++) {
                        String codigoBusca = gerarCodigoAleatorio(random); //
                        long tempoInicioBusca = System.currentTimeMillis(); //
                        Registro resultadoBusca = tabela.buscar(codigoBusca); //
                        long tempoFinalBusca = System.currentTimeMillis(); //

                        long duracaoBusca = tempoFinalBusca - tempoInicioBusca; //

                        //
                        if (resultadoBusca != null) {
                            System.out.println("Registro com código " + resultadoBusca.getCodigo() + " encontrado em " + duracaoBusca + " ms.");
                        } else {
                            System.out.println("Registro com código " + codigoBusca + " não encontrado em " + duracaoBusca + " ms.");
                        }
                    }
                }
            }
        }
    }

    //
    public static String gerarCodigoAleatorio(Random random) {
        char[] codigoArray = new char[9];
        // array para armazenar os digitos do codigo
        for (int i = 0; i < 9; i++) {
            codigoArray[i] = (char) ('0' + random.nextInt(10)); //
        }
        return new String(codigoArray);
    }

    //
    public static int tamanhoArray(int[] array) {
        int tamanho = 0;
        for (int i = 0; ; i++) {
            try {
                int valor = array[i];
                tamanho++;
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        return tamanho;
    }
}
