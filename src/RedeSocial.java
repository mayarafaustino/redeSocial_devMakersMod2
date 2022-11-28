import java.util.Scanner;

public class RedeSocial {

    static Scanner scanner = new Scanner(System.in);
    static Usuario[] listaDePerfis = new Usuario[1000];
    static int totalUsuarios = 0;

    public static void main(String[] args) {

        boolean fechar = false;
        do {
            System.out.println("");
            System.out.println("");
            System.out.println("=================== socialBite ==================");
            System.out.println("== A rede social do desenvolvedor introvertido ==");
            System.out.println("");
            System.out.println("O que deseja fazer agora?");
            System.out.println("Digite 'E' para entrar");
            System.out.println("Digite 'C' para cadastra-se");
            System.out.println("Digite 'F' para fechar");

            String opcao = scanner.nextLine().toUpperCase();

            switch (opcao) {
                case "C": {
                    cadastrarUsuario();
                    break;
                }
                case "E": {
                    try {
                        int indiceUsuario = fazerLogin();
                        listaDePerfis[indiceUsuario].exibirMenuUsuario(scanner);
                    } catch (UserNotFoundException e) {
                        System.out.println(e.getMensagem());
                    } catch (InvalidPasswordException e) {
                        System.out.println(e.getMensagem());
                    }
                    break;
                }
                case "F": {
                    System.out.println("Tchau tchau, até a próxima!");
                    fechar = true;
                    break;
                }
                default: {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } while (!fechar);
    }

    static void cadastrarUsuario() {
        System.out.println("");
        System.out.println("Digite seu login:");
        String login = scanner.nextLine();
        if (login.isBlank()) {
            System.out.println("login inválido");
            return;
        }
        for (Usuario usuario : listaDePerfis) {
            if (usuario != null && login.equalsIgnoreCase(usuario.login)) {
                System.out.println("Ops, o login escolhido já existe! Tente novamente com um outro login.");
                return;
            }
        }
        System.out.println("Digite seu nome:");
        String nome = scanner.nextLine();
        if (nome.isBlank()) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }
        System.out.println("Digite sua senha:");
        String senha = scanner.nextLine();
        if (senha.isBlank()) {
            System.out.println("Senha não pode ser vazia!");
            return;
        }

        listaDePerfis[totalUsuarios] = new Usuario(login, nome, senha);
        totalUsuarios++;
        System.out.println("Usuário(a) " + login + " cadastrado(a) com sucesso!");
    }

    static int fazerLogin() throws UserNotFoundException, InvalidPasswordException {
        System.out.println("");
        System.out.println("Digite seu login:");
        String login = scanner.nextLine();
        int indiceUsuario = -1;
        for (int i = 0; i < listaDePerfis.length; i++) {
            if (listaDePerfis[i] != null && login.equalsIgnoreCase(listaDePerfis[i].login)) {
                indiceUsuario = i;
                break;
            }
        }
        if (indiceUsuario == -1) {
            throw new UserNotFoundException();
        }
        System.out.println("Digite sua senha:");
        String senha =  scanner.nextLine();
        if (!senha.equals(listaDePerfis[indiceUsuario].senha)) {
            throw new InvalidPasswordException();
        }
        return indiceUsuario;
    }
}
