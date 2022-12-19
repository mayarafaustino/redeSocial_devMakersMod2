package br.com.RedeSocial.exceptions;

public class UserNotFoundException extends RuntimeException {

    public String getMensagem() {
        return "O login de usuário digitado não existe.";
    }
}
