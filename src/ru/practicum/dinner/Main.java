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
                    System.out.println("Завершение работы программы.");
                    System.out.println("Всего доброго!");
                    System.out.printf("%s", "=".repeat(42));
                    return;
                // не указано в ТЗ, добавлено для удобства тестирования
                case "9":
                    dc.swapForCombo = new ArrayList<>();
                    System.out.println("Предыдущие результаты работы команды \"2\" были успешно удалены.");
                    break;
                case "0":
                    dc.printMenu();
                    break;
                //
                default:
                    System.out.println("К сожалению, такой команды нет. Повторите ввод команды.");
                    break;
            }
            System.out.printf("%s", "=".repeat(42));
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
        // дополнительные опции:
        System.out.println("9 - Сброс добавленных типов блюд, которые были введены при работе команды \"2\"");
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

        if (dc.menu.isEmpty()) {
            System.out.println("В списке блюд нет добавленных позиций. Генерация меню невозможна.");
            System.out.println("Просьба добавить в список блюд позиции, которые вы желали бы увидеть в нашем меню.");
        } else {

            System.out.println("Введите количество наборов, которые нужно сгенерировать:");
            int numberOfCombos = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Вводите типы блюд, разделяя символом переноса строки (enter). " +
                               "Для завершения ввода введите пустую строку.");
            System.out.println("Если наборы блюд были сгенерированы ранее и вы хотите добавить к ним новое блюдо, " +
                               "то введите новые типы блюд, которые вы хотели бы добавить к уже существующим; ");
            System.out.println("либо введите пустую строку чтобы использовать раннее введённые типы блюд без добавления новых.");
            String nextItem = scanner.nextLine();

            //реализуйте ввод типов блюд
            while (!nextItem.isEmpty()) {
                if (dc.checkType(nextItem)) {
                    dc.swapForCombo.add(nextItem);
                } else {
                    System.out.println("В списке доступных типов блюд нет типа " + nextItem);
                    System.out.println("Вводите типы блюд, разделяя символом переноса строки (enter). " +
                                       "Для завершения ввода введите пустую строку.");
                }
                nextItem = scanner.nextLine();
            }

            // сгенерируйте комбинации блюд и выведите на экран
            dc.constructCombos(numberOfCombos);

            /* ~После генерации комбинаций блюд обнуляется список ранее введённых блюд;~
             * ~в противном случае, при повторном запуске команды "2" новые комбинации добавляются к уже существующим.~
             */
            /* Сброс списка вынесен в отдельную кнопку меню, если требуется сгенерировать новые комбинации с нуля.
             * Добавлено текстовое предупреждение, если требуется использовать уже введенные наборы блюд
             * с добавлением нового типа.
             */
            // dc.swapForCombo = new ArrayList<>();
            }
        }
}

/*P.S.
* Комментарий для Александра Ф.
* Добрый день!
* Спасибо за оперативно проведённое код-ревью!
* Надеюсь, мне удалось исправить все замечания.
* */
