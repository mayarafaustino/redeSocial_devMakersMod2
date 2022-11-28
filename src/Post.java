
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Post {

    String data;
    String hora;
    String conteudo;

    public Post(String data, String hora, String conteudo) {
        this.data = data;
        this.hora = hora;
        this.conteudo = conteudo;
    }

    static String getDataFormatada(Calendar calendar) {
        SimpleDateFormat dataFormatter = new SimpleDateFormat("dd/MM/yyyy");
        String data = dataFormatter.format(calendar.getTime());
        return data;
    }

    static String getHoraFormatada(Calendar calendar) {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        String hora = timeFormatter.format(calendar.getTime());
        return hora;
    }
}
