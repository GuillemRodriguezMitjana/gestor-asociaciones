package excepciones;

public class FileNotFoundException extends Exception {
    private String filePath;

    // Constructor que acepta un mensaje y la ruta del archivo
    public FileNotFoundException(String message, String filePath) {
        super(message);  // Llama al constructor de Exception con el mensaje
        this.filePath = filePath;
    }

    // Getter para obtener la ruta del archivo
    public String getFilePath() {
        return filePath;
    }
}
