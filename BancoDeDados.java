class Pessoa {
    private String nome;
    private String sobrenome;
    private float peso;
    private float altura;
    private int idade;

    public Pessoa(String nome, String sobrenome, float peso, float altura, int idade) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.peso = peso;
        this.altura = altura;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getNomeCompleto() {
        return nome + " " + sobrenome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public float getIMC() {
        return peso / (altura * altura);
    }
}

public class BancoDeDados {
    private Pessoa[] pessoas;
    
    public BancoDeDados() {
        pessoas = new Pessoa[5];
    }

    public void cadastrarPessoa(Pessoa pessoa) {
        for (int i = 0; i < pessoas.length; i++) {
            if (pessoas[i] == null) {
                pessoas[i] = pessoa;
                break;
            }
        }
    }

    public Pessoa[] getPessoas() {
        return pessoas;
    }
}
