package org;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) throws SocketException {
        System.out.println("Сервер запускается");

        try (DatagramSocket serverSocket = new DatagramSocket(42182)) {
            System.out.println("Сервер слушает порт: 42182");

            byte[] recieveBuffer = new byte[1024];

            while (true) {
                DatagramPacket recievePacket = new DatagramPacket(recieveBuffer, recieveBuffer.length);

                serverSocket.receive(recievePacket);

                String message = new String(recievePacket.getData(), 0, recievePacket.getLength(), StandardCharsets.UTF_8);

                System.out.println("[ " + recievePacket.getAddress() + " ]: " + message);
            }
        } catch (Exception e) {
            System.err.println("Ошибка на сервере: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
