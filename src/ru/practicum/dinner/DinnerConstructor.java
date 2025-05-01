package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DinnerConstructor {
    Random random = new Random();

    // меню (список всех типов блюд и самих блюд)
    HashMap<String, ArrayList<String>> menu = new HashMap<>();
    ArrayList<String> dishes = new ArrayList<>();

    // переменная служит для запоминания пользовательского ввода в конструкторе комбинаций блюд
    ArrayList<String> swapForCombo = new ArrayList<>();

    /* Логика добавления отдельного метода checkType():
     * метод возвращает булево значение, которое можно использовать для других методов,
     * чтобы не прописывать код проверки наличия типа блюд в каждом отдельном методе.
     */
    public boolean checkType(String dishType) {
        return menu.containsKey(dishType);
    }

    /* Метод для добавления новых типов и новых блюд;
     * нет проверки добавления в список уже существующих блюд, поскольку в ТЗ нет чёткого указания,
     * что блюда в своих типах должны быть уникальными / не должно быть повторяющихся блюд в разных типах;
     * в связи с этим реализована только проверка существования типов блюд.
     */
    public void mapDish(String dishType, String dishName) {
        if (!(checkType(dishType))) {
            dishes = new ArrayList<>();
        }
        dishes.add(dishName);
        menu.put(dishType, dishes);
        // информационная строка для подтверждения, что блюдо добавлено.
        System.out.println("блюдо " + dishName + " добавлено к типу блюд " + dishType);
    }

    /* Основной метод генерации комбинаций блюд из указываемых пользователем типов блюд.*/
    public void constructCombos(int numberOfCombos) {

        ArrayList<ArrayList<String>> combos = new ArrayList<>();
        System.out.println(swapForCombo);
        for (int i = 0; i < numberOfCombos; i++) {
            ArrayList<String> combo = new ArrayList<>();
            for (String menuKey : swapForCombo) {
                dishes = menu.get(menuKey);
                int index = random.nextInt(dishes.size());
                String dish = dishes.get(index);
                System.out.println(dish);
                combo.add(dish);
                System.out.println(combo);
            }
            combos.add(combo);
            System.out.println(combos);
        }
        for (int i = 0; i < combos.size(); i++) {
            System.out.println("Комбо " + (i + 1));
            System.out.println(combos.get(i));

        }
    }

    /* Дополнительный метод для вывода всех существующих на данный момент типов блюд и названий блюд.*/
    public void printMenu() {
        for (String type : menu.keySet()) {
            System.out.println("тип блюда " + type + " содержит:");
            for (String dish : menu.get(type)) {
                System.out.println("  - " + dish);
            }
        }
    }

}
