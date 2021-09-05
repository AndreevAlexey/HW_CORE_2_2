package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        // генерация данных
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        // количество несовершеннолетних
        System.out.println("\n**** количество несовершеннолетних ****");
        Long kidsCnt = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(kidsCnt);
        //список призывников
        System.out.println("\n**** список призывников ****");
        List<String> soldiers = persons.stream()
                .filter(x -> x.getAge() >= 18 && x.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        //soldiers.forEach(System.out::println);
        // список работоспособных
        System.out.println("\n**** список работоспособных ****");
        Comparator<Person> comparator = Comparator.comparing(Person::getFamily);
        List<Person> highLvlWorkers =
        persons.stream()
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> x.getSex() == Sex.MAN && x.getAge() < 65 || x.getSex() == Sex.WOMAN && x.getAge() < 60)
                .sorted(comparator)
                .collect(Collectors.toList());
        //highLvlWorkers.forEach(System.out::println);
    }
}
