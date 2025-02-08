import java.io.*;

public class ComparaFlujos {
    private static long leeFicheroByte(InputStream in) throws IOException {
        long inicio = System.currentTimeMillis();
        while (in.read() != -1) {
            // Leer byte por byte
        }
        long fin = System.currentTimeMillis();
        return fin - inicio;
    }

    private static long leeFicheroBloques(InputStream in, int tam) throws IOException {
        long inicio = System.currentTimeMillis();
        byte[] buffer = new byte[tam];
        while (in.read(buffer, 0, tam) != -1) {
            // Leer bloques de tamaño `tam`
        }
        long fin = System.currentTimeMillis();
        return fin - inicio;
    }

    public static void main(String[] args) {
        String filePath = "fichero.bin"; // Ajusta la ruta si es necesario
        try {
            // Lectura byte a byte con FileInputStream
            try (InputStream fis = new FileInputStream(filePath)) {
                long tiempoByte = leeFicheroByte(fis);
                System.out.println("FileInputStream - Byte a byte: " + tiempoByte + " ms");
            }

            // Lectura byte a byte con BufferedInputStream
            try (InputStream bis = new BufferedInputStream(new FileInputStream(filePath))) {
                long tiempoBufferedByte = leeFicheroByte(bis);
                System.out.println("BufferedInputStream - Byte a byte: " + tiempoBufferedByte + " ms");
            }

            // Lectura en bloques con FileInputStream
            int[] tamanos = {512, 4096, 16384, 32768};
            for (int tam : tamanos) {
                try (InputStream fis = new FileInputStream(filePath)) {
                    long tiempoBloque = leeFicheroBloques(fis, tam);
                    System.out.println("FileInputStream - Bloques de " + tam + ": " + tiempoBloque + " ms");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
