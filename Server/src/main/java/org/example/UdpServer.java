package org.example;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class UdpServer {
    // Порт, на котором слушаем. Можно передать аргументом: java -jar server.jar 9999
    private static final int DEFAULT_PORT = 54533;
    private static final int BUFFER_SIZE = 4096;

    public static void main(String[] args) {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_PORT;

        try (DatagramSocket socket = new DatagramSocket(null)) {
            // Разрешаем повторное использование адреса (полезно при перезапуске)
            socket.setReuseAddress(true);
            socket.bind(new InetSocketAddress(port));

            System.out.println("UDP сервер запущен на порту " + port);
            System.out.println("Ожидаю сообщений... (Ctrl+C для остановки)");

            byte[] buffer = new byte[BUFFER_SIZE];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet); // блокирующий вызов

                String received = new String(
                        packet.getData(),
                        packet.getOffset(),
                        packet.getLength(),
                        StandardCharsets.UTF_8
                );

                String sender = packet.getAddress().getHostAddress() + ":" + packet.getPort();
                System.out.println("[" + sender + "] " + received);
            }
        } catch (Exception e) {
            System.err.println("Ошибка сервера: " + e.getMessage());
            e.printStackTrace();
        }
    }
}