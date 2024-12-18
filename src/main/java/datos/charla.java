package accion;

public class Charla extends Accion {
    // Atributos específicos de Charla
    private String fechaCharla; // Fecha en la que se realizará la charla
    private List<Miembro> miembrosImpartidores; // Lista de miembros que imparten la charla (máximo 3)
    private int numAsistentes; // Número de asistentes a la charla
    private List<Integer> valoraciones; // Lista de valoraciones de los asistentes (escala [0-10])

    // Constructor
    public Charla(String codigo, String titulo, Miembro responsable, String fechaCharla) {
        super(codigo, titulo, responsable);
        this.fechaCharla = fechaCharla;
        this.miembrosImpartidores = new ArrayList<>();
        this.valoraciones = new ArrayList<>();
        this.numAsistentes = 0;
    }

    // Métodos específicos de Charla

    // Agregar un miembro impartidor (máximo 3)
    public void agregarImpartidor(Miembro miembro) {
        if (miembrosImpartidores.size() < 3) {
            miembrosImpartidores.add(miembro);
        } else {
            System.out.println("No se pueden agregar más de 3 miembros como impartidores.");
        }
    }

    public List<Miembro> getMiembrosImpartidores() {
        return miembrosImpartidores;
    }

    // Incrementar el número de asistentes
    public void incrementarAsistentes() {
        numAsistentes++;
    }

    public int getNumAsistentes() {
        return numAsistentes;
    }

    // Agregar una valoración (entre 0 y 10)
    public void agregarValoracion(int valoracion) {
        if (valoracion >= 0 && valoracion <= 10) {
            valoraciones.add(valoracion);
        } else {
            System.out.println("La valoración debe estar entre 0 y 10.");
        }
    }

    // Obtener la lista de valoraciones
    public List<Integer> getValoraciones() {
        return valoraciones;
    }

    // Calcular la media de las valoraciones
    public double obtenerPromedioValoraciones() {
        if (valoraciones.isEmpty()) {
            return 0.0;
        }
        return valoraciones.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    // Getters y setters
    public String getFechaCharla() {
        return fechaCharla;
    }

    public void setFechaCharla(String fechaCharla) {
        this.fechaCharla = fechaCharla;
    }

    @Override
    public String toString() {
        return "Charla [codigo=" + getCodigo() + ", titulo=" + getTitulo() +
                ", fechaCharla=" + fechaCharla +
                ", impartidores=" + miembrosImpartidores +
                ", asistentes=" + numAsistentes +
                ", promedioValoraciones=" + obtenerPromedioValoraciones() + "]";
    }
}