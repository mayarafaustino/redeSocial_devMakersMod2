package br.com.RedeSocial.exceptions;

public class UserNotFoundException extends Exception {

    public String getMensagem() {
        return "O login de usuário digitado não existe.";
    }
}
