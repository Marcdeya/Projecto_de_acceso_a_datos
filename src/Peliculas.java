    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.List;
    import javax.xml.parsers.DocumentBuilder;
    import javax.xml.parsers.DocumentBuilderFactory;
    import org.w3c.dom.Document;
    import org.w3c.dom.Element;
    import org.w3c.dom.NodeList;
    public class Peliculas {
        public static class Pelicula {

            private int id;
            private String titulo;
            private String director;
            private int año;
            private String genero;

            public String getTitulo() {
                return titulo;
            }
            public String getGenero() {
                return genero;
            }
            public int getId() {
                return id;
            }

            public String getDirector() {
                return director;
            }

            public int getAño() {
                return año;
            }

            public Pelicula(int id, String titulo, String director, int año, String genero) {
                this.id = id;
                this.titulo = titulo;
                this.director = director;
                this.año = año;
                this.genero = genero;
            }

            @Override
            public String toString() {
                return id + " | " + titulo + " | " + director + " | " + año + " | " + genero;
            }
        }

        public static List<Pelicula> leerPeliculas(String nombreFichero) {
            List<Pelicula> peliculas = new ArrayList<>();

            try {
                FileReader fr = new FileReader(nombreFichero);
                BufferedReader br = new BufferedReader(fr);

                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.trim().isEmpty()) {
                        continue;
                    }

                    String[] datos = linea.split("\\|");
                    System.out.println("Línea leída: " + linea);

                    int id = Integer.parseInt(datos[0]);
                    String titulo = datos[1];
                    String director = datos[2];
                    int año = Integer.parseInt(datos[3]);
                    String genero = datos[4];


                    Pelicula pelicula = new Pelicula(id, titulo, director, año, genero);
                    peliculas.add(pelicula);
                }

                br.close();

            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
            }

            return peliculas;

        }
        public static List<Pelicula> importarXML(String nombreFichero) {

            List<Pelicula> peliculas = new ArrayList<>();

            try {

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();

                Document documento = builder.parse(nombreFichero);

                documento.getDocumentElement().normalize();

                NodeList listaPeliculas = documento.getElementsByTagName("pelicula");

                for (int i = 0; i < listaPeliculas.getLength(); i++) {

                    Element peliculaXML = (Element) listaPeliculas.item(i);

                    int id = Integer.parseInt(
                            peliculaXML.getElementsByTagName("id").item(0).getTextContent()
                    );

                    String titulo = peliculaXML
                            .getElementsByTagName("titulo")
                            .item(0)
                            .getTextContent();

                    String director = peliculaXML
                            .getElementsByTagName("director")
                            .item(0)
                            .getTextContent();

                    int año = Integer.parseInt(
                            peliculaXML.getElementsByTagName("año").item(0).getTextContent()
                    );

                    String genero = peliculaXML
                            .getElementsByTagName("genero")
                            .item(0)
                            .getTextContent();

                    Pelicula pelicula = new Pelicula(
                            id,
                            titulo,
                            director,
                            año,
                            genero
                    );

                    peliculas.add(pelicula);
                }

            } catch (Exception e) {

                System.out.println("Error al importar el XML: " + e.getMessage());
            }

            return peliculas;
        }


    }
