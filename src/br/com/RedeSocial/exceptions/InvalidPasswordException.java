package br.com.RedeSocial.exceptions;

public class InvalidPasswordException extends RuntimeException {
    //todo mudar para extender de exception e não runtime?
    public String getMensagem() {
        return "A senha digitada não corresponde a senha cadastrada.";
    }
}
