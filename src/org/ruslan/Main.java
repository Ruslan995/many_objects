package org.ruslan;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static class NameRandomizer {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> surnames = new ArrayList<>();
        Random random = new Random();

        public NameRandomizer() {
            names.add("Pasha");
            names.add("Lena");
            names.add("Anna");

            surnames.add("Tihonov");
            surnames.add("Bobova");
            surnames.add("Golovach");
        }

        public String generateName() {
            return names.get(random.nextInt(names.size()));
        }

        public String generateSurname() {
            return surnames.get(random.nextInt(surnames.size()));
        }
    }

    public static class Client {
        String name;
        String surname;
        int money;

        public Client(String name, String surname, int money) {
            this.name = name;
            this.surname = surname;
            this.money = money;
        }

        public Client() {
            Random random = new Random();
            NameRandomizer nameRandomizer = new NameRandomizer();
            name = nameRandomizer.generateName();
            surname = nameRandomizer.generateSurname();
            this.money = random.nextInt(1000000) + 10000;
        }
    }

    public static class Account {
        Client client;
        int money;

        public Account(Client client) {
            this.client = client;
            this.money = 0;
        }

        public void transferMoney(int money) {
            this.money += money;
        }

    }

    public static class Bank {
        ArrayList<Account> accounts;
        double commision;

        public Bank(double commision) {
            accounts = new ArrayList<>();
            this.commision = commision;
        }

        public void createAccount(Client client) {
            Account account = new Account(client);
            accounts.add(account);
            // accounts.add(new Account(client));
        }

        public void transferToAccount(Client client, int money) {
            for (Account e : accounts) {
                if (e.client == client) {
                    e.transferMoney(money);
                    client.money -= (money + (money * commision));
                }
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Client> clients = new ArrayList<>();
        Bank sberbank = new Bank(0.15);
        for (int i = 0; i < 100; i++) {
            Client c = new Client();
            clients.add(c);
            sberbank.createAccount(c);

        }

        for (Client c : clients) {
            System.out.println(c.name + " " + c.surname + " with money " + c.money + " wants to transfer money");
            sberbank.transferToAccount(c, 10000);
            System.out.println("Now he has " + c.money);
        }
    }
}
