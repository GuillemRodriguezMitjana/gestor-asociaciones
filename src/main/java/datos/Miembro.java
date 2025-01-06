    package datos;

    import excepciones.ExcepcionListaAsociacionLlena;
    import excepciones.ExcepcionMaximoAsociaciones;
import listas.ListaAsociaciones;

    public abstract class Miembro {

        private String alias;
        private String correo;
        private String fechaAlta;
        private String fechaBaja;
        private ListaAsociaciones asociaciones;

        public Miembro(String alias, String correo) {
            this.alias = alias;
            this.correo = correo;
            this.fechaAlta = null;
            this.fechaBaja = null;
            this.asociaciones = new ListaAsociaciones(50);
        }

        // Getters
        public String getAlias() {
            return alias;
        }

        public String getCorreo() {
            return correo;
        }

        public String getFechaAlta() {
            return fechaAlta;
        }

        public String getFechaBaja() {
            return fechaBaja;
        }

        public ListaAsociaciones getAsociaciones() {
            return asociaciones;
        }

        // Setters
        public void setAlias(String alias) {
            this.alias = alias;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public void setFechaAlta(String fechaAlta) {
            this.fechaAlta = fechaAlta;
        }

        public void setFechaBaja(String fechaBaja) {
            this.fechaBaja = fechaBaja;
        }

        public boolean estaActivo() {
            return fechaBaja == null;
        }

        public void agregarAsociacion(Asociacion asociacion) throws ExcepcionMaximoAsociaciones, ExcepcionListaAsociacionLlena {
            if (asociaciones.getNElem() >= 3) {
                throw new ExcepcionMaximoAsociaciones("El miembro no puede pertenecer a más de 3 asociaciones.");
            }
            asociaciones.agregarAsociacion(asociacion);
        }

        @Override
        public String toString() {
        String resultado = "Miembro [Alias: " + alias + 
                        ", Correo: " + correo + 
                        ", Fecha Alta: ";
        if (fechaAlta != null) {
            resultado += fechaAlta;
        } else {
            resultado += "No registrada";
        }

        resultado += ", Fecha Baja: ";
        if (fechaBaja != null) {
            resultado += fechaBaja;
        } else {
            resultado += "Activo";
        }

        resultado += ", Asociaciones: ";
        if (asociaciones.getNElem() > 0) {
            resultado += asociaciones.toString();
        } else {
            resultado += "Ninguna";
        }

        resultado += "]";
        return resultado;
    }

}
