import java.util.Arrays;
import java.util.Scanner;

/*
 * Aluno: Lucas Garrafielo
 * Matrícula 20220070609
*/
public class Programa {
    public static BancoDeDados bancoDeDados;

    public static boolean checarVazio() {
        Pessoa[] pessoas = bancoDeDados.getPessoas();

        if (pessoas[0] == null) {
            System.out.println("Não há pessoas cadastradas");
            return true;
        }

        return false;
    }

    public static void cadastrarPessoas(Scanner scanner) {
        for (int i = 0; i < 5; i++) {
            scanner.nextLine();

            System.out.print("\nDigite o nome da " + Integer.toString(i + 1) + " pessoa: ");
            String nome = scanner.nextLine();

            System.out.print("Digite o sobrenome da " + Integer.toString(i + 1) + " pessoa: ");
            String sobrenome = scanner.nextLine();

            System.out.print("Digite o peso da " + Integer.toString(i + 1) + " pessoa: ");
            float peso = scanner.nextFloat();

            System.out.print("Digite a altura da " + Integer.toString(i + 1) + " pessoa: ");
            float altura = scanner.nextFloat();

            System.out.print("Digite a idade da " + Integer.toString(i + 1) + " pessoa: ");
            int idade = scanner.nextInt();

            Pessoa pessoa = new Pessoa(nome, sobrenome, peso, altura, idade);

            bancoDeDados.cadastrarPessoa(pessoa);
        }
    }

    public static void listarPessoas() {
        if (checarVazio()) {
            return;
        }

        Pessoa[] pessoas = bancoDeDados.getPessoas();

        System.out.println("\nOs usuários cadastrados são:");

        for (int i = 0; i < pessoas.length; i++) {
            System.out.println(
                    "Nome: " + pessoas[i].getNomeCompleto() +
                            ", peso: " + pessoas[i].getPeso() +
                            ", altura: " + pessoas[i].getAltura() +
                            ", idade: " + pessoas[i].getIdade());
        }
    }

    public static void mediaDosDados() {
        if (checarVazio()) {
            return;
        }

        Pessoa[] pessoas = bancoDeDados.getPessoas();

        // Variáveis soma para cálculo das médias
        float somaDasIdades = 0;
        float somaDasAlturas = 0;
        float somaDosPesos = 0;
        float somaDosIMCs = 0;
        int quantidadeDePessoas = 0;

        for (int i = 0; i < pessoas.length; i++) {
            somaDasIdades += pessoas[i].getIdade();
            somaDasAlturas += pessoas[i].getAltura();
            somaDosPesos += pessoas[i].getPeso();
            somaDosIMCs += pessoas[i].getIMC();
            quantidadeDePessoas++;
        }

        System.out.println("Média das idades: " + somaDasIdades / quantidadeDePessoas);
        System.out.println("Média das alturas: " + somaDasAlturas / quantidadeDePessoas);
        System.out.println("Média dos pesos: " + somaDosPesos / quantidadeDePessoas);
        System.out.println("Média dos IMCs: " + somaDosIMCs / quantidadeDePessoas);
    }

    // Será usado 2x, então é melhor criar uma função
    public static String getTipoIMC(float imc) {
        String tipoIMC = "";

        if (imc < 18.5f) {
            tipoIMC = "abaixo do peso";
        } else if (imc >= 18.5f && imc < 25f) {
            tipoIMC = "peso ideal (parabéns)";
        } else if (imc >= 25f && imc < 30f) {
            tipoIMC = "levemente acima do peso";
        } else if (imc >= 30f && imc < 35f) {
            tipoIMC = "obesidade I";
        } else if (imc >= 35f && imc < 40f) {
            tipoIMC = "obesidade II (severa)";
        } else if (imc >= 40f) {
            tipoIMC = "obesidade III (mórbida)";
        }

        return tipoIMC;
    }

    public static void listarIMCs() {
        if (checarVazio()) {
            return;
        }

        Pessoa[] pessoas = bancoDeDados.getPessoas();

        System.out.println("\nOs IMCs dos usuários cadastrados são:");

        // Fazer loop por todas as pessoas e listar seu IMC e a classificação do seu IMC.
        for (int i = 0; i < pessoas.length; i++) {
            float imc = pessoas[i].getIMC();
            String tipoIMC = getTipoIMC(imc);
            System.out.println(pessoas[i].getNomeCompleto() + ", " + pessoas[i].getIMC() + ", " + tipoIMC);
        }
    }

    public static void listarIdades() {
        if (checarVazio()) {
            return;
        }

        Pessoa[] pessoas = bancoDeDados.getPessoas();

        System.out.println("\nAs idades dos usuários cadastrados são:");

        // Guardar a mais nova e mais velha pessoa como sendo a primeira da lista.
        // E atualizar caso ache uma mais velha/mais nova.
        Pessoa pessoaMaisVelha = pessoas[0];
        Pessoa pessoaMaisNova = pessoas[0];

        for (int i = 0; i < pessoas.length; i++) {
            if (pessoas[i].getIdade() > pessoaMaisVelha.getIdade()) {
                pessoaMaisVelha = pessoas[i];
            }

            if (pessoas[i].getIdade() < pessoaMaisNova.getIdade()) {
                pessoaMaisNova = pessoas[i];
            }

            System.out.println(pessoas[i].getNomeCompleto() + " - " + pessoas[i].getIdade() + " anos");
        }

        System.out.println(
                "\nPessoa mais velha: " + pessoaMaisVelha.getNomeCompleto() + " - " + pessoaMaisVelha.getIdade()
                        + " anos");
        System.out
                .println("Pessoa mais nova: " + pessoaMaisNova.getNomeCompleto() + " - " + pessoaMaisNova.getIdade()
                        + " anos");
    }

    public static void listarAlturas() {
        if (checarVazio()) {
            return;
        }

        Pessoa[] pessoas = bancoDeDados.getPessoas();

        float[] alturas = new float[pessoas.length];

        // Criar um array alturas que pode ser ordenado.
        for (int i = 0; i < pessoas.length; i++) {
            alturas[i] = pessoas[i].getAltura();
        }

        // Ordenar o array de alturas
        Arrays.sort(alturas);

        Pessoa[] pessoasOrdemCrescente = new Pessoa[pessoas.length];

        // Criar um array de pessoas temporário (deletaremos os elementos deste array quando acharmos a pessoa que possui uma altura x)
        Pessoa[] pessoasTemp = new Pessoa[pessoas.length];

        // Popular o array
        for (int i = 0; i < pessoas.length; i++) {
            pessoasTemp[i] = pessoas[i];
        }

        // Ir por cada altura ordenada e checar qual pessoa no array de pessoas possui essa altura.
        for (int i = 0; i < alturas.length; i++) {
            for (int j = 0; j < pessoasTemp.length; j++) {
                if (pessoasTemp[j] != null && alturas[i] == pessoasTemp[j].getAltura()) {
                    pessoasOrdemCrescente[i] = pessoasTemp[j];

                    // Deletar a pessoa do array para que, caso duas pessoas tenham a mesma altura,
                    // Ambas sejam identificadas, e não só a primeira que for encontrada.
                    pessoasTemp[j] = null;
                }
            }
        }

        System.out.println("\nAs alturas dos usuários cadastrados são:");

        // Mesma lógica da idade.
        Pessoa pessoaMaisAlta = pessoas[0];
        Pessoa pessoaMaisBaixa = pessoas[0];

        for (int i = 0; i < pessoas.length; i++) {
            if (pessoas[i].getAltura() > pessoaMaisAlta.getAltura()) {
                pessoaMaisAlta = pessoas[i];
            }

            if (pessoas[i].getAltura() < pessoaMaisBaixa.getAltura()) {
                pessoaMaisBaixa = pessoas[i];
            }

            System.out.println(pessoas[i].getNomeCompleto() + " - " + pessoas[i].getAltura() + " metros");
        }

        System.out.println(
                "\nPessoa mais alta: " + pessoaMaisAlta.getNomeCompleto() + " - " + pessoaMaisAlta.getAltura()
                        + " metros");
        System.out.println(
                "Pessoa mais baixa: " + pessoaMaisBaixa.getNomeCompleto() + " - " + pessoaMaisBaixa.getAltura()
                        + " metros");
    }

    public static void buscarPessoa(Scanner scanner) {
        System.out.print("Digite o sobrenome que deseja procurar: ");
        scanner.nextLine();
        String sobrenome = scanner.nextLine();

        Pessoa[] pessoas = bancoDeDados.getPessoas();

        boolean encontrado = false;

        // Ir por cada pessoa e checar se o sobrenome é igual ao digitado.
        for (int i = 0; i < pessoas.length; i++) {
            if (pessoas[i].getSobrenome().equalsIgnoreCase(sobrenome)) {
                encontrado = true;

                System.out.println("\nUsuário encontrado:");
                System.out.println("Nome: " + pessoas[i].getNomeCompleto() + ", Idade: " + pessoas[i].getIdade()
                        + ", Altura: "
                        + pessoas[i].getAltura() + ", Peso: " + pessoas[i].getPeso()
                        + ", IMC: " + pessoas[i].getIMC() + ", Classificação IMC: " + getTipoIMC(pessoas[i].getIMC()));
            }
        }

        if (!encontrado) {
            System.out.println("\nNenhum usuário encontrado com o sobrenome " + sobrenome);
        }
    }

    public static void main(String[] args) {
        boolean querSair = false;

        bancoDeDados = new BancoDeDados();
        System.out.println("\nBem-vindo ao programa. Escolha uma das opções abaixo:");

        // Valores de teste para não precisar cadastrar manualmente.
        /* bancoDeDados.cadastrarPessoa(new Pessoa("João", "Silva", 102, 1.80f, 20));
        bancoDeDados.cadastrarPessoa(new Pessoa("Maria", "Cunha", 51, 1.60f, 19));
        bancoDeDados.cadastrarPessoa(new Pessoa("José", "Pereira", 44, 1.70f, 35));
        bancoDeDados.cadastrarPessoa(new Pessoa("Joana", "Fernandes", 82, 1.65f, 56));
        bancoDeDados.cadastrarPessoa(new Pessoa("Joaquim", "Moura", 75, 1.75f, 75)); */

        Scanner scanner = new Scanner(System.in);

        while (!querSair) {
            System.out.println("\n1 - Cadastro de pessoas");
            System.out.println("2 - Lista de pessoas cadastradas");
            System.out.println("3 - Análise da média dos dados");
            System.out.println("4 - Valores do IMC");
            System.out.println("5 - Valores das idades");
            System.out.println("6 - Valores das alturas");
            System.out.println("7 - Dado da pessoa");
            System.out.println("8 - Sair");
            System.out.print("Sua opção: ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    cadastrarPessoas(scanner);
                    break;
                case 2:
                    listarPessoas();
                    break;
                case 3:
                    mediaDosDados();
                    break;
                case 4:
                    listarIMCs();
                    break;
                case 5:
                    listarIdades();
                    break;
                case 6:
                    listarAlturas();
                    break;
                case 7:
                    buscarPessoa(scanner);
                    break;
                case 8:
                    System.out.println("\nAdeus!");
                    querSair = true;
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
        scanner.close();
    }
}