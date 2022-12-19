package br.com.RedeSocial.redeSocial;

import br.com.RedeSocial.exceptions.InvalidPasswordException;
import br.com.RedeSocial.exceptions.UserNotFoundException;
import br.com.RedeSocial.post.Post;
import br.com.RedeSocial.usuario.Usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class RedeSocial {

    private List<Usuario> usuarios;
    private static final RedeSocial INSTANCE = new RedeSocial(new ArrayList<>());

    private RedeSocial(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public static RedeSocial getInstance() {
        return INSTANCE;
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        boolean fechar = false;
        do {
            System.out.println("");
            System.out.println("");
            System.out.println("=================== socialBite ==================");
            System.out.println("== A rede social do desenvolvedor introvertido ==");
            System.out.println("");
            System.out.println("O que deseja fazer agora?");
            System.out.println("'E' - para entrar");
            System.out.println("'C' - para cadastrar-se");
            System.out.println("'F' - para fechar");
            System.out.println("=================================================");

            String opcao = scanner.nextLine().toUpperCase();

            switch (opcao) {
                case "C": {
                    cadastrarUsuario();
                    break;
                }
                case "E": {
                    try {
                        exibirMenuUsuario(fazerLogin());
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
                    System.out.println("Você não digitou uma opção válida. =/  Tente novamente.");
                }
            }
        } while (!fechar);

        scanner.close();
    }

    private void cadastrarUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("Qual será seu login?");
        String login = scanner.nextLine();
        if (verificaSeStringEhVazia(login)) return;
        for (Usuario usuario : usuarios) {
            if (usuario != null && login.equalsIgnoreCase(usuario.getLogin())) {
                System.out.println("Ops, o login escolhido já existe! Tente novamente com um outro login.");
                return;
            }
        }

        System.out.println("Digite seu nome:");
        String nome = scanner.nextLine();
        if (verificaSeStringEhVazia(nome)) return;

        System.out.println("Digite sua senha:");
        String senha = scanner.nextLine();
        if (verificaSeStringEhVazia(senha)) return;

        usuarios.add(new Usuario(login, nome, senha));
        System.out.println("Usuário(a) " + login + " cadastrado(a) com sucesso!");
    }

    private Usuario fazerLogin() throws UserNotFoundException, InvalidPasswordException {
        Scanner scanner = new Scanner(System.in);
        //pede o login pro br.com.RedeSocial.usuario
        System.out.println("");
        System.out.println("Digite seu login:");
        String login = scanner.nextLine();

        //procura login digitado e, se achar, armazena o indice dele em indiceUsuario
        Usuario usuarioLogado = null;
        for (Usuario usuario : usuarios) {
            if (login.equalsIgnoreCase(usuario.getLogin())) {
                int indiceUsuario = usuarios.indexOf(usuario);
                usuarioLogado = usuarios.get(indiceUsuario);
                break;
            }
        }

        //caso não ache o login digitado:
        if (usuarioLogado == null) {
            throw new UserNotFoundException();
        }

        //pede senha pro br.com.RedeSocial.usuario
        System.out.println("Digite sua senha:");
        String senha = scanner.nextLine();
        //verifica se a senha bate com a senha do br.com.RedeSocial.usuario:
        if (!usuarioLogado.senhaValidada(senha)) {
            throw new InvalidPasswordException();
        }
        //se td deu certo, retorna o indice do br.com.RedeSocial.usuario que fez login com sucesso
        return usuarioLogado;
    }

    private void exibirMenuUsuario(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;
        do {
            System.out.println("");
            System.out.println("====== Bem-vindo(a), " + usuario.getNome() + "! ======");
            System.out.println("");
            System.out.println(usuario.getNome() + ", o que deseja fazer agora?");
            System.out.println("'P' - para fazer um br.com.RedeSocial.post");
            System.out.println("'T' - para ver sua Timeline");
            System.out.println("'S' - para sair e voltar ao menu inicial");
            System.out.println("=================================================");
            String opcao = scanner.nextLine().toUpperCase();

            switch (opcao) {
                case "P": {
                    postar(usuario);
                    break;
                }
                case "T": {
                    exibirTimelineUsuario(usuario);
                    break;
                }
                case "S": {
                    sair = true;
                    break;
                }
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (!sair);
    }

    private void exibirTimelineUsuario(Usuario usuario) {
        System.out.println("");
        System.out.println("=========== Minhas postagens: ===========");
        System.out.println("");
        for (Post post : usuario.getPosts()) {
            if (post != null) {
                System.out.printf("%s às %s - '%s' ", post.getData(), post.getHora(), post.getConteudo());
                System.out.println("");
            }
        }
        System.out.println("");
        System.out.println("==========================================");
    }

    private void postar(Usuario usuario) {
        String conteudo = usuario.escreverPublicacao();
        if (verificaSeStringEhVazia(conteudo)) return;

        Calendar calendar = Calendar.getInstance();
        usuario.getPosts().add(new Post(formatarData(calendar), formatarHora(calendar), conteudo));
        System.out.println("Postagem concluída!");
    }

    private boolean verificaSeStringEhVazia(String string) {
        if (string.isBlank()) {
            System.out.println("O campo não pode estar em branco!");
            return true;
        }
        return false;
    }

    private String formatarData(Calendar calendar) {
        SimpleDateFormat dataFormatter = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = dataFormatter.format(calendar.getTime());
        return dataFormatada;
    }

    private String formatarHora(Calendar calendar) {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        String horaFormatada = timeFormatter.format(calendar.getTime());
        return horaFormatada;
    }

}
