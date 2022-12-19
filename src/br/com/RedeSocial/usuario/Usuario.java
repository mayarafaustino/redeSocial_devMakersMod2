package br.com.RedeSocial.usuario;

import br.com.RedeSocial.post.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuario {

    private String login;
    private String nome;
    private String senha;
    private List<Post> posts;
    private int totalPosts = 0;

    public Usuario(String login, String nome, String senha) {
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        this.posts = new ArrayList<>();
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Post> getPosts() {
        return this.posts;
    }

    public boolean senhaValidada(String senha) {
        return this.senha.equals(senha);
    }

    public String escreverPublicacao() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("O que deseja postar?");
        return scanner.nextLine();
    }

}
