import java.util.Scanner;
import java.util.TreeSet;

public class Main
{
    static TreeSet<String> emailList = new TreeSet<>();

    public static void main(String[] args)
    {
        System.out.println("Вводите команды с правильным синтаксисом:\nLIST - посмотреть список e-mail адресов\n" +
                "ADD 'e-mail адрес' - добавить новый email адрес в список");

        String[] text = getScanner();

        while (!text[0].equals("EXIT"))
        {
            if (text[0].equals("ADD") && text[1] != null && text[2] == null)
            {
                if (!emailList.contains(text[1]))
                {
                    addEmail(text[1]);
                    System.out.println("Вы добавили новый e-mail");
                }
                else
                {
                    System.out.println("Такой e-mail в списке уже есть, введите другой");
                }
            }
            else if (text[0].equals("LIST") && text[1] == null && text[2] == null)
            {
                if (emailList.size() <= 0)
                {
                    System.out.println("В списке пусто, выводить нечего");
                }
                else
                {
                    System.out.println(getEmailList());
                }
            }
            else
            {
                System.out.println("Ваш синтаксис не верен!!!");
            }

            text = getScanner();
        }
    }

    // Считываем то что написали и парсим
    public static String[] getScanner()
    {
        Scanner scanner = new Scanner(System.in);
        String textRead = scanner.nextLine();

        // ADD
        int aIndex = textRead.indexOf('A');                 // A
        int firstDIndex = textRead.indexOf('D');            // D
        int secondDIndex = firstDIndex + 1;                 // D
        int spaceADDIndex = textRead.indexOf(' ');          // SPACE

        // LIST
        int firstLLISTIndex = textRead.indexOf('L');        // L
        int firstILISTIndex = textRead.indexOf('I');        // I
        int firstSLISTIndex = textRead.indexOf('S');        // S
        int firstTLISTIndex = textRead.indexOf('T');        // T

        // Пробелы
        int firstSpaceIndex = textRead.indexOf(' ');

        // Какие есть команды
        boolean isADD = false;

        // Создаем массив который возвращает метод getScanner
        String[] textComplete = new String[3];

        // Временная переменная
        textComplete[0] = " ";
        String temp = " ";

        // Сейчас будет код который будет проверять первое слово и записывать его в первый элемент массива
        // Ищем первое слово и проверяем что это точно команда ADD
        if (aIndex == 0 && firstDIndex == 1 && secondDIndex == 2 && spaceADDIndex == 3)
        {
            char[] add = {textRead.charAt(aIndex), textRead.charAt(firstDIndex), textRead.charAt(secondDIndex)};
            temp = String.valueOf(add);
            isADD = true;
            textComplete[0] = temp;
        }
        // LIST
        else if (firstLLISTIndex == 0 && firstILISTIndex == 1 && firstSLISTIndex == 2 && firstTLISTIndex == 3)
        {
            char[] list = {textRead.charAt(firstLLISTIndex), textRead.charAt(firstILISTIndex),
                    textRead.charAt(firstSLISTIndex), textRead.charAt(firstTLISTIndex)
            };
            temp = String.valueOf(list);
            textComplete[0] = temp;
        }

        // После того когда мы проверили команду, разносим строчки по массиву
        if (isADD)
        {
            boolean isValidEmail;

            temp = textRead.substring(firstSpaceIndex).trim();
            isValidEmail = temp.matches("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");

            if (isValidEmail)
            {
                textComplete[1] = temp;
            }
        }

        return textComplete;
    }

    // Считываем весь список email адресов
    public static TreeSet<String> getEmailList()
    {
        return emailList;
    }

    // Добавляем новый email
    public static void addEmail(String email)
    {
        emailList.add(email);
    }
}