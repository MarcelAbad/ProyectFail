import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    private BibliotecaService servicio;
    private static final Scanner scanner = new Scanner(System.in);

    public BibliotecaApp() {
        servicio = new BibliotecaService();
    }

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        app.ejecutarMenu();
    }

    private void ejecutarMenu() {

        int opcion = -1;
        while (opcion != 0) {
            imprimirMenu();

            // validación simple de enter
            while (!scanner.hasNextInt()) {
                System.out.println("Debe ingresar un número.");
                scanner.next();
            }

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarLibroDesdeConsola();
                    break;
                case 2:
                    registrarUsuarioDesdeConsola();
                    break;
                case 3:
                    prestarLibroDesdeConsola();
                    break;
                case 4:
                    devolverLibroDesdeConsola();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
        scanner.close();
    }

    private void imprimirMenu() {
        System.out.println("\n=== GESTIÓN BIBLIOTECA ===");
        System.out.println("1. Registrar libro");
        System.out.println("2. Registrar usuario");
        System.out.println("3. Prestar libro");
        System.out.println("4. Devolver libro");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
    }

    private void registrarLibroDesdeConsola() {

        scanner.nextLine();

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        System.out.print("Año publicación: ");
        int anioPublicacion = scanner.nextInt();

        System.out.print("Ejemplares totales: ");
        int total = scanner.nextInt();

        System.out.print("Ejemplares disponibles: ");
        int disponibles = scanner.nextInt();

        ArrayList<Integer> ejemplaresTotales = new ArrayList<>();
        ejemplaresTotales.add(total);

        ArrayList<Integer> ejemplaresDisponibles = new ArrayList<>();
        ejemplaresDisponibles.add(disponibles);

        Libro libro = new Libro(isbn, titulo, autor, anioPublicacion,
                ejemplaresTotales, ejemplaresDisponibles);

        servicio.registrarLibro(libro);

        System.out.println("Libro registrado con éxito.");
    }

    private void registrarUsuarioDesdeConsola() {

        System.out.print("ID usuario: ");
        String id = scanner.next();
        scanner.nextLine();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        Usuario usuario = new Usuario(id, nombre);
        servicio.registrarUsuario(usuario);

        System.out.println("Usuario registrado con éxito.");
    }

    private void prestarLibroDesdeConsola() {
        System.out.print("ID usuario: ");
        String id = scanner.nextLine();
        scanner.nextLine();
        System.out.print("ISBN libro: ");
        String isbn = scanner.nextLine();

        servicio.prestarLibro(id, isbn);
    }

    private void devolverLibroDesdeConsola() {
        System.out.print("ID usuario: ");
        String id = scanner.nextLine();
        scanner.nextLine();
        System.out.print("ISBN libro: ");
        String isbn = scanner.nextLine();

        servicio.devolverLibro(id, isbn);
    }
}
