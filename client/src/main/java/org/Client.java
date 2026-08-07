package org;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String serverIP = "localhost";
        System.out.println("Клиент запущен.");

        try (DatagramSocket clientSocket = new DatagramSocket(); Scanner scanner = new Scanner(System.in)) {
            InetAddress serverAddress = InetAddress.getByName(serverIP);

            while (true) {
                System.out.println("Введите сообщение: ");
                String message = scanner.nextLine();

                byte[] sendData = message.getBytes(StandardCharsets.UTF_8);

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 42182);

                clientSocket.send(sendPacket);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
