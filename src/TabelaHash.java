public class TabelaHash {
    private No[] tabela;
    private int tamanho;
    private int numeroColisoes;
    private int tipoHash; //

    public TabelaHash(int tamanho, int tipoHash) {
        this.tamanho = tamanho; //
        this.tabela = new No[tamanho]; //
        this.numeroColisoes = 0; //
        this.tipoHash = tipoHash; //
    }

    private int hash(String chave) { //
        int codigo = Integer.parseInt(chave);

        switch (tipoHash) {
            case 1: // Resto da divisão
                return codigo % tamanho;
            case 2: // Multiplicação
                double A = 0.6180339887; //
                return (int) (tamanho * (codigo * A % 1));
            case 3: // Dobramento
                int part1 = codigo / 1000;
                int part2 = codigo % 1000;
                return (part1 + part2) % tamanho;
            default:
                return 0; //
        }
    }


    public void inserir(Registro registro) {
        int indice = hash(registro.getCodigo()); //
        No novoNo = new No(registro); //

        if (tabela[indice] == null) { //
            tabela[indice] = novoNo; //
        } else {
            numeroColisoes++; //
            No atual = tabela[indice];

            //
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novoNo; //
        }
    }

    public Registro buscar(String codigo) {
        int indice = hash(codigo);
        No atual = tabela[indice];

        while (atual != null) {
            if (atual.registro.getCodigo().equals(codigo)) { //
                return atual.registro; //
            }
            atual = atual.proximo;
        }

        return null;
    }

    public int getNumeroColisoes() {
        return numeroColisoes;
    }
}
