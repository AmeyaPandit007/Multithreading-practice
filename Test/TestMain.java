package Test;

import java.util.*;
import java.util.stream.Collectors;

public class TestMain
{
    public static void main(String[] args) {
        List<Employee> list1 = new ArrayList<>();
        list1.add(new Employee("Ameya",22,"M"));
        list1.add(new Employee("Aparna",56,"F"));
        list1.add(new Employee("Ashok",63,"M"));
        list1.add(new Employee("Manasi",30,"F"));
        list1.add(new Employee("Anuja",32,"F"));

        System.out.println("List ------"+ list1);

//        list1.forEach(employee -> {
//            if(employee.getGender().equalsIgnoreCase("M")) {
//                employee.setSalutation("Mr.");
//            } else if (employee.getGender().equalsIgnoreCase("F")) {
//                employee.setSalutation("Mrs.");
//            }
//        });

        list1 = list1.stream().map(employee -> {
            if ("M".equalsIgnoreCase(employee.getGender())) {
                employee.setSalutation("Mr.");
            } else if (employee.getGender().equalsIgnoreCase("F")) {
                employee.setSalutation("Mrs.");
            }
            return employee;
        }).collect(Collectors.toList());

        System.out.println("List ------"+list1);
    }
}
