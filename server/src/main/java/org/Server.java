package org;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) {
        int port = 54533;
        System.out.println("Сервер запускается...");

        // Привязываем сокет ко всем сетевым интерфейсам (0.0.0.0)
        try (DatagramSocket serverSocket = new DatagramSocket(new InetSocketAddress("0.0.0.0", port))) {
            System.out.println("Сервер успешно запущен и слушает порт: " + port);

            while (true) {
                // Создаем новый буфер на каждое сообщение
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                // Ожидаем пакет
                serverSocket.receive(receivePacket);

                String message = new String(
                        receivePacket.getData(),
                        0,
                        receivePacket.getLength(),
                        StandardCharsets.UTF_8
                );

                System.out.println("[" + receivePacket.getSocketAddress() + "]: " + message);
            }
        } catch (Exception e) {
            System.err.println("Ошибка на сервере: " + e.getMessage());
            e.printStackTrace();
        }
    }
}