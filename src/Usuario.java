import java.util.Calendar;
import java.util.Scanner;

public class Usuario {

    String login;
    String nome;
    String senha;
    Post[] posts = new Post[1000];
    int totalPosts = 0;

    public Usuario(String login, String nome, String senha) {
        this.login = login;
        this.nome = nome;
        this.senha = senha;
    }

    void exibirMenuUsuario(Scanner scanner) {
        boolean sair = false;
        System.out.println("");
        System.out.println("Bem-vindo(a), " + this.nome + "! ");
        do {
            System.out.println("");
            System.out.println(this.nome + ", o que deseja fazer agora?");
            System.out.println("Digite 'P' para fazer um post");
            System.out.println("Digite 'T' para ver sua Timeline");
            System.out.println("Digite 'S' para sair e voltar ao menu inicial");
            String opcao = scanner.nextLine().toUpperCase();

            switch (opcao) {
                case "P": {
                    this.postar(scanner);
                    break;
                }
                case "T": {
                    this.exibirTimeline();
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

    void postar(Scanner scanner) {
        System.out.println("");
        System.out.println("O que deseja postar?");
        String conteudo = scanner.nextLine();
        Calendar calendar = Calendar.getInstance();

        this.posts[this.totalPosts] = new Post(Post.getDataFormatada(calendar), Post.getHoraFormatada(calendar), conteudo);
        this.totalPosts++;

        System.out.println("Postagem concluída!");
    }

    void exibirTimeline() {
        System.out.println("");
        System.out.println("Minhas postagens: ");
        for (Post post : this.posts) {
            if (post != null) {
                System.out.printf("%s às %s - '%s' ", post.data, post.hora, post.conteudo);
                System.out.println("");
            }
        }
    }

}
