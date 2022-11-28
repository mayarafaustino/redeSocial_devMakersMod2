public class InvalidPasswordException extends RuntimeException {

    String getMensagem() {
        return "A senha digitada não corresponde a senha cadastrada.";
    }
}
