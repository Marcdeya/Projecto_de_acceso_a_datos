import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.io.FileWriter;
import java.io.IOException;
void main() {

    List<Peliculas.Pelicula> peliculas = Peliculas.leerPeliculas("peliculas.txt");

    Scanner sc = new Scanner(System.in);
    int opcion;

    do {
        System.out.println("\n===== CATÁLOGO =====");
        System.out.println("1. Mostrar todas");
        System.out.println("2. Buscar por género");
        System.out.println("3. Buscar por título");
        System.out.println("4. Exportar a XML");
        System.out.println("5. Importar XML");
        System.out.println("0. Salir");
        System.out.print("Elige una opcion: ");

        opcion = sc.nextInt();
        sc.nextLine();
        switch (opcion) {
            case 1:


                for (Peliculas.Pelicula pelicula : peliculas) {
                    System.out.println(pelicula.getTitulo());
                }
                break;



            case 2:

                Set<String> generos = new LinkedHashSet<>();

                for (Peliculas.Pelicula pelicula : peliculas) {
                    generos.add(pelicula.getGenero());
                }

                int numero = 1;

                for (String genero : generos) {
                    System.out.println(numero + ". " + genero);
                    numero++;
                }

                System.out.print("Selecciona un género: ");
                int opcionGenero = sc.nextInt();
                sc.nextLine();

                List<String> listaGeneros = new ArrayList<>(generos);

                String generoSeleccionado = listaGeneros.get(opcionGenero - 1);

                System.out.println("Has seleccionado: " + generoSeleccionado);
                for (Peliculas.Pelicula pelicula : peliculas) {

                    if (pelicula.getGenero().equalsIgnoreCase(generoSeleccionado)) {
                        System.out.println(pelicula);
                    }
                }

                break;


            case 3:

                System.out.print("Introduce el título o parte del título: ");
                String busqueda = sc.nextLine();

                boolean encontrado = false;

                for (Peliculas.Pelicula pelicula : peliculas) {

                    if (pelicula.getTitulo().toLowerCase().contains(busqueda.toLowerCase())) {

                        System.out.println(pelicula);

                        encontrado = true;
                    }
                }

                if (!encontrado) {
                    System.out.println("No se han encontrado películas.");
                }

                break;

            case 4 :


                try {

                    FileWriter fw = new FileWriter("peliculas.xml");

                    fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                    fw.write("<peliculas>\n");

                    for (Peliculas.Pelicula pelicula : peliculas) {

                        fw.write("<pelicula>\n");
                        fw.write("<id>" + pelicula.getId() + "</id>\n");
                        fw.write("<titulo>" + pelicula.getTitulo() + "</titulo>\n");
                        fw.write("<director>" + pelicula.getDirector() + "</director>\n");
                        fw.write("<año>" + pelicula.getAño() + "</año>\n");
                        fw.write("<genero>" + pelicula.getGenero() + "</genero>\n");
                        fw.write("</pelicula>\n");
                    }

                    fw.write("</peliculas>\n");

                    fw.close();

                    System.out.println("Películas exportadas correctamente a peliculas.xml");

                } catch (IOException e) {

                    System.out.println("Error al crear el archivo XML: " + e.getMessage());
                }

                break;

            case 5:

                List<Peliculas.Pelicula> peliculasXML =
                        Peliculas.importarXML("peliculas.xml");

                System.out.println("\n===== PELÍCULAS IMPORTADAS DEL XML =====");

                for (Peliculas.Pelicula pelicula : peliculasXML) {
                    System.out.println(pelicula);
                }

                break;


                        case 0:

                            break;
                            default:
                                System.out.println("Opcion no valida.");
        }

    }while (opcion != 0);
    sc.close();


}
