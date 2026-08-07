package org;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String serverIP = "77.234.196.15";
        System.out.println("Клиент запущен.");

        try (DatagramSocket clientSocket = new DatagramSocket(); Scanner scanner = new Scanner(System.in)) {
            InetAddress serverAddress = InetAddress.getByName(serverIP);

            while (true) {
                System.out.println("Введите сообщение: ");
                String message = scanner.nextLine();

                byte[] sendData = message.getBytes(StandardCharsets.UTF_8);

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 54533);

                clientSocket.send(sendPacket);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
