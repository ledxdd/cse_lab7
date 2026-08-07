package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UdpClient {
    // Адрес и порт сервера. Можно передать аргументами:
    // java -jar client.jar 192.168.1.10 9999
    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 54533;

    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : DEFAULT_HOST;
        int port = args.length > 1 ? Integer.parseInt(args[1]) : DEFAULT_PORT;

        try (DatagramSocket socket = new DatagramSocket();
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {

            InetAddress address = InetAddress.getByName(host);
            System.out.println("Подключено к " + host + ":" + port);
            System.out.println("Введите сообщение и нажмите Enter (пустая строка = выход):");

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    System.out.println("Выход.");
                    break;
                }

                byte[] data = line.getBytes(StandardCharsets.UTF_8);
                DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
                socket.send(packet);
                System.out.println("Отправлено: " + line);
            }

        } catch (Exception e) {
            System.err.println("Ошибка клиента: " + e.getMessage());
            e.printStackTrace();
        }
    }
}