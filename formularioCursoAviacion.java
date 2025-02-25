import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class formularioCursoAviacion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nombre = "", apellido = "", dni = "", correo = "", lugNac = "", idiomas = "", tipoAvion = "";
        int edad = 0, estudios = 0, licencia = 0, opcion = 0, horasVuelo = 0;
        boolean experienciaComoPiloto = false;
        final int EDAD_MINIMA = 25;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("CURSO_AVIACION.txt"))) {
            // Encabezados
            writer.write("Formulario de Inscripción para el Curso de Aviación - Fuerza Aérea Argentina\n");
            writer.write("================================================================\n");

            // Primera Etapa: Datos Personales
            writer.write("Primera Etapa: Datos Personales\n");
            System.out.println("Ingrese sus datos personales:");

            nombre = leerString(scanner, "Nombre: ", writer);
            apellido = leerString(scanner, "Apellido: ", writer);
            lugNac = leerString(scanner, "Lugar de nacimiento: ", writer);
            dni = leerDNI(scanner, writer);
            correo = leerCorreo(scanner, writer);
            edad = leerEdad(scanner, writer, EDAD_MINIMA);
            estudios = leerEstudios(scanner, writer);

            if (!nombre.isEmpty() && !apellido.isEmpty() && !lugNac.isEmpty() && !dni.isEmpty() && !correo.isEmpty() && edad != 0) {
                System.out.println("Formulario completo. Todos los datos han sido ingresados.");
            } else {
                System.out.println("Formulario incompleto. Por favor, complete todos los campos.");
                System.out.println("Lamentablemente, no puede continuar con la inscripción.");
                writer.write("Nivel de estudios: No válido.\n");
                return; // Termina la ejecución si el formulario está incompleto
            }

            // Segunda Etapa: Información Profesional
            writer.newLine();
            writer.write("Segunda Etapa: Información Profesional\n");
            System.out.println("\nSegunda Etapa: Información Profesional");

            licencia = leerLicencia(scanner, writer);
            experienciaComoPiloto = leerExperienciaPiloto(scanner, writer);

            if (experienciaComoPiloto) {
                horasVuelo = leerHorasVuelo(scanner, writer);
            }

            tipoAvion = leerString(scanner, "4. ¿En qué tipo de avión tiene experiencia?\n   Ejemplos: Cessna 172, Boeing 737, Airbus A320, etc.\nIngrese el tipo de avión: ", writer);
            idiomas = leerString(scanner, "5. ¿Qué idiomas domina? (Separe los idiomas con comas, por ejemplo: Inglés, Francés, Alemán): ", writer);

            // Resumen de la información
            writer.newLine();
            writer.write("Resumen de la información:\n");
            writer.write("Nombre: " + nombre + "\n");
            writer.write("Apellido: " + apellido + "\n");
            writer.write("Lugar de nacimiento: " + lugNac + "\n");
            writer.write("DNI: " + dni + "\n");
            writer.write("Correo electrónico: " + correo + "\n");
            writer.write("Edad: " + edad + "\n");

        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Métodos para leer y validar datos
    public static String leerString(Scanner scanner, String mensaje, BufferedWriter writer) throws IOException {
        System.out.print(mensaje);
        String input = scanner.nextLine();
        writer.write(mensaje + input + "\n");
        return input;
    }

    public static String leerDNI(Scanner scanner, BufferedWriter writer) throws IOException {
        String dni;
        while (true) {
            System.out.print("Ingrese su DNI (solo números): ");
            dni = scanner.nextLine();
            if (dni.matches("\\d+")) { // Usando regex para validar solo números
                writer.write("DNI: " + dni + "\n");
                return dni;
            } else {
                System.out.println("DNI inválido. Por favor, ingrese solo números.");
            }
        }
    }

    public static String leerCorreo(Scanner scanner, BufferedWriter writer) throws IOException {
        String correo;
        while (true) {
            System.out.print("Ingrese su correo electrónico: ");
            correo = scanner.nextLine();
            if (correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) { // Usando regex para validar correo
                writer.write("Correo electrónico: " + correo + "\n");
                return correo;
            } else {
                System.out.println("Correo electrónico inválido. Por favor, ingrese un correo válido.");
            }
        }
    }

    public static int leerEdad(Scanner scanner, BufferedWriter writer, int edadMinima) throws IOException {
        int edad;
        while (true) {
            System.out.print("Edad: ");
            if (scanner.hasNextInt()) {
                edad = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea
                writer.write("Edad: " + edad + "\n");
                if (edad >= edadMinima) {
                    System.out.println("Usted cumple con la edad mínima para ingresar.");
                } else {
                    System.out.println("Usted no cumple con la edad mínima requerida.");
                }
                return edad;
            } else {
                System.out.println("Edad inválida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }
    }

    public static int leerEstudios(Scanner scanner, BufferedWriter writer) throws IOException {
        int estudios;
        while (true) {
            System.out.println("Ingrese el número del nivel de estudio completo que tiene:");
            System.out.println("1. Secundario.");
            System.out.println("2. Terciario.");
            System.out.println("3. Universitario.");
            if (scanner.hasNextInt()) {
                estudios = scanner.nextInt();
                scanner.nextLine();
                switch (estudios) {
                    case 1:
                        System.out.println("Ingresará con el nivel mínimo.");
                        break;
                    case 2:
                        System.out.println("Ingresará con un buen nivel.");
                        break;
                    case 3:
                        System.out.println("Ingresará con un excelente nivel. BIENVENIDO.");
                        break;
                    default:
                        System.out.println("Usted no tiene el nivel mínimo de estudio requerido para este curso.");
                        break;
                }
                writer.write("Nivel de estudios: " + estudios + "\n");
                return estudios;
            } else {
                System.out.println("Opción inválida. Por favor, ingrese un número.");
                scanner.nextLine();
            }
        }
    }

    public static int leerLicencia(Scanner scanner, BufferedWriter writer) throws IOException {
        int licencia;
        while (true) {
            System.out.println("1. ¿Qué tipo de licencia de piloto posee?");
            System.out.println("    Opción 1: Ninguna.");
            System.out.println("    Opción 2: Licencia de Piloto Privado (PPL).");
            System.out.println("    Opción 3: Licencia de Piloto Comercial (CPL).");
            System.out.println("    Opción 4: Licencia de Piloto de Transporte de Línea Aérea (ATPL).");
            System.out.print("Ingrese el número de la opción que corresponda: ");
            if (scanner.hasNextInt()) {
                licencia = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea
                writer.write("Tipo de licencia: " + licencia + "\n");
                switch (licencia) {
                    case 1:
                        System.out.println("Licencia: Ninguna.");
                        return licencia;
                    case 2:
                        System.out.println("Licencia: Piloto Privado (PPL).");
                        return licencia;
                    case 3:
                        System.out.println("Licencia: Piloto Comercial (CPL).");
                        return licencia;
                    case 4:
                        System.out.println("Licencia: Piloto de Transporte de Línea Aérea (ATPL).");
                        return licencia;
                    default:
                        System.out.println("Opción inválida. Por favor, ingrese una opción válida (1, 2, 3 o 4).");
                        writer.write("Tipo de licencia: Inválida.\n");
                        break;
                }
            } else {
                System.out.println("Opción inválida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }
    }

    public static boolean leerExperienciaPiloto(Scanner scanner, BufferedWriter writer) throws IOException {
        int opcion;
        while (true) {
            System.out.println("2. ¿Tiene experiencia como piloto?");
            System.out.println("    Opción 1: Sí.");
            System.out.println("    Opción 2: No.");
            System.out.print("Ingrese el número de la opción que corresponda: ");
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea
                writer.write("Experiencia como piloto: " + opcion + "\n");
                if (opcion == 1) {
                    System.out.println("Experiencia como piloto: Sí.");
                    return true;
                } else if (opcion == 2) {
                    System.out.println("Experiencia como piloto: No.");
                    return false;
                } else {
                    System.out.println("Opción inválida. Por favor, ingrese una opción válida (1 o 2).");
                    writer.write("Experiencia como piloto: Inválida.\n");
                }
            } else {
                System.out.println("Opción inválida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }
    }

    public static int leerHorasVuelo(Scanner scanner, BufferedWriter writer) throws IOException {
        int horasVuelo;
        while (true) {
            System.out.println("3. ¿Cuántas horas de vuelo tiene registradas?");
            System.out.println("    Opción 1: Menos de 10 horas.");
            System.out.println("    Opción 2: Entre 10 y 20 horas.");
            System.out.println("    Opción 3: Más de 20 horas.");
            System.out.print("Ingrese el número de la opción que corresponda: ");
            if (scanner.hasNextInt()) {
                horasVuelo = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea
                writer.write("Horas de vuelo: " + horasVuelo + "\n");
                switch (horasVuelo) {
                    case 1:
                        System.out.println("Horas de vuelo: Menos de 10 horas.");
                        return horasVuelo;
                    case 2:
                        System.out.println("Horas de vuelo: Entre 10 y 20 horas.");
                        return horasVuelo;
                    case 3:
                        System.out.println("Horas de vuelo: Más de 20 horas.");
                        return horasVuelo;
                    default:
                        System.out.println("Opción inválida. Por favor, ingrese una opción válida (1, 2 o 3).");
                        writer.write("Horas de vuelo: Inválidas.\n");
                        break;
                }
            } else {
                System.out.println("Opción inválida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }
    }

    class Usuario {
        private String nombre;
        private String apellido;
        private String lugNac;
        private String dni;
        private String correo;
        private int edad;
        private String tipoAvion;
        private String idiomas;
    }

}