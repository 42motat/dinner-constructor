package ru.practicum.dinner;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static DinnerConstructor dc;
    static Scanner scanner;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    return;
                // не указано в ТЗ, добавлено для удобства тестирования
                case "0":
                    dc.printMenu();
                    break;
            }

            System.out.printf("%n%s", "=".repeat(42));
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
        // дополнительные опции
        System.out.println("0 - Вывод существующих категорий и их содержание");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();

        // добавьте новое блюдо
        dc.mapDish(dishType, dishName);
    }

    private static void generateDishCombo() {
        System.out.println("Начинаем конструировать обед...");

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). " +
                           "Для завершения ввода введите пустую строку");
        String nextItem = scanner.nextLine();

        //реализуйте ввод типов блюд
        while (!nextItem.isEmpty()) {
            if (dc.checkType(nextItem)) {
                dc.swapForCombo.add(nextItem);
            } else {
                System.out.println("В списке доступных типов блюд нет типа " + nextItem);
                System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). " +
                                   "Для завершения ввода введите пустую строку");
            }
            nextItem = scanner.nextLine();
        }

        // сгенерируйте комбинации блюд и выведите на экран
        dc.constructCombos(numberOfCombos);
        /* После генерации комбинаций блюд обнуляется список ранее введённых блюд;
         * в противном случае, при повторном запуске команды "2" новые комбинации добавляются к уже существующим.
         */
        dc.swapForCombo = new ArrayList<>();
    }
}
