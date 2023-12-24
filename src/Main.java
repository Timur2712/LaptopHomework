import java.util.*;
import java.util.stream.Collectors;

class Laptop {
    String brand;
    int ram;
    int hdd;
    String os;
    String color;

    public Laptop(String brand, int ram, int hdd, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + brand + '\'' +
                ", ram=" + ram +
                ", hdd=" + hdd +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Dell", 16, 512, "Windows", "Black"));
        laptops.add(new Laptop("Apple", 8, 256, "MacOS", "Silver"));
        laptops.add(new Laptop("Lenovo", 32, 1024, "Windows", "Gray"));
        laptops.add(new Laptop("Gigabyte", 16, 512, "Windows", "Black"));
        laptops.add(new Laptop("Asus", 8, 256, "Windows", "Silver"));



        Map<String, Object> filters = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите критерии для фильтрации:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        System.out.println("0 - Завершить выбор");

        int choice;
        while (true) {
            choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }
            switch (choice) {
                case 1:
                    System.out.println("Минимальный объем ОЗУ?");
                    System.out.println("8 , 16, 32");
                    filters.put("ram", scanner.nextInt());
                    System.out.println("Выберите следующий параметр!");
                    break;
                case 2:
                    System.out.println("Минимальный объем ЖД?");
                    System.out.println("256, 512, 1024");
                    filters.put("hdd", scanner.nextInt());
                    System.out.println("Выберите следующий параметр!");
                    break;
                case 3:
                    System.out.println("Операционная система?");
                    System.out.println("MacOS, windows");
                    filters.put("os", scanner.next().toUpperCase());
                    System.out.println("Выберите следующий параметр!");
                    break;
                case 4:
                    System.out.println("Цвет?");
                    System.out.println("Black, Gray, Silver");
                    filters.put("color", scanner.next().toUpperCase());
                    System.out.println("Для поиска с учетом критерий введите 0");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        Set<Laptop> filteredLaptops = laptops.stream()
                .filter(laptop -> filters.getOrDefault("ram", 0) instanceof Integer && laptop.ram >= (int) filters.getOrDefault("ram", 0))
                .filter(laptop -> filters.getOrDefault("hdd", 0) instanceof Integer && laptop.hdd >= (int) filters.getOrDefault("hdd", 0))
                .filter(laptop -> filters.getOrDefault("os", "").equals("") || laptop.os.equalsIgnoreCase((String) filters.getOrDefault("os", "")))
                .filter(laptop -> filters.getOrDefault("color", "").equals("") || laptop.color.equalsIgnoreCase((String) filters.getOrDefault("color", "")))
                .collect(Collectors.toSet());

        System.out.println("Отфильтрованные ноутбуки:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }
}