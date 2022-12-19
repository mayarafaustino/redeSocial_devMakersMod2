package br.com.RedeSocial.exceptions;

public class InvalidPasswordException extends Exception {

    public String getMensagem() {
        return "A senha digitada não corresponde a senha cadastrada.";
    }
}
