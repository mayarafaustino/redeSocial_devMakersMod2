public class UserNotFoundException extends RuntimeException {

    String getMensagem() {
        return "O login de usuário digitado não existe.";
    }
}
