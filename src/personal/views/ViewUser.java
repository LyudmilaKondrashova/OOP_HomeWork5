package personal.views;

import personal.controllers.UserController;
import personal.model.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run() {
        Commands com = Commands.NONE;

        while (true) {
            String command = prompt("Введите команду: NONE, READ, CREATE, UPDATE, LIST, DELETE, EXIT\n");
            com = Commands.valueOf(command.toUpperCase());

            if (com == Commands.EXIT) return;
            String readNumber;
            try {
                switch (com) {
                    case CREATE:
                        System.out.println("Введите данные пользователя:");
                        String firstName = prompt("Имя: ");
                        String lastName = prompt("Фамилия: ");
                        String phone = prompt("Номер телефона: ");
                        readNumber = prompt("В какой файл произвести запись: 1 - user.txt, 2 - newUsers.txt: ");
                        if (readNumber.equals("1")) {
                            userController.saveUser(new User(firstName, lastName, phone));
                            System.out.println("Пользователь записан в файл user.txt!");
                        } else if (readNumber.equals("2")) {
                            userController.saveNewUser(new User(firstName, lastName, phone));
                            System.out.println("Пользователь записан в файл newUsers.txt!");
                        } else {
                            System.out.println("Команда указана неправильно!");
                        }
                        break;
                    case READ:
                        String id = prompt("Идентификатор пользователя: ");
                        readNumber = prompt("Из какого файла произвести чтение: 1 - user.txt, 2 - newUsers.txt: ");
                        if (readNumber.equals("1")) {
                            User user = userController.readUser(id);
                            System.out.println(user);
                        } else if (readNumber.equals("2")) {
                            User user = userController.readNewUser(id);
                            System.out.println(user);
                        } else {
                            System.out.println("Команда указана неправильно!");
                        }
                        break;
                    case LIST:
                        readNumber = prompt("Из какого файла произвести чтение: 1 - user.txt, 2 - newUsers.txt: ");
                        if (readNumber.equals("1")) {
                            List<User> lst = userController.readList();
                            lst.forEach(i -> System.out.println(i + "\n"));
                        } else if (readNumber.equals("2")) {
                            List<User> lst = userController.readNewList();
                            lst.forEach(i -> System.out.println(i + "\n"));
                        } else {
                            System.out.println("Команда указана неправильно!");
                        }
                        break;
                    case UPDATE:
                        String idUs = prompt("Введите идентификатор id редактируемого контакта: ");
                        readNumber = prompt("В каком файле произвести обновление данных: 1 - user.txt, 2 - newUsers.txt: ");
                        if (readNumber.equals("1")) {
                            userController.idPresenceValidation(idUs);
                            userController.updateUser(idUs, createUser());
                            System.out.println("Пользователь обновлен в файле user.txt!");
                        } else if (readNumber.equals("2")) {
                            userController.idNewPresenceValidation(idUs);
                            userController.updateNewUser(idUs, createUser());
                            System.out.println("Пользователь обновлен в файле newUsers.txt!");
                        } else {
                            System.out.println("Команда указана неправильно!");
                        }
                        break;
                    case DELETE:
                        String idUser = prompt("Введите идентификатор id удаляемого контакта: ");
                        readNumber = prompt("Из какого файла произвести удаление данных: 1 - user.txt, 2 - newUsers.txt: ");
                        if (readNumber.equals("1")) {
                            userController.idPresenceValidation(idUser);
                            userController.deleteUser(idUser);
                            System.out.println("Пользователь удален из файла user.txt!");
                        } else if (readNumber.equals("2")) {
                            userController.idNewPresenceValidation(idUser);
                            userController.deleteNewUser(idUser);
                            System.out.println("Пользователь удален из файла newUsers.txt!");
                        }
                        else {
                            System.out.println("Команда указана неправильно!");
                        }
                        break;
                }
            } catch (Exception e) {
                System.out.println("Oopsie!\n" + e.getMessage());
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private User createUser() {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        User newUser = new User(firstName, lastName, phone);
        return newUser;
    }
}
