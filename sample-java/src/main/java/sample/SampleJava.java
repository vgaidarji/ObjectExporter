package sample;

public class SampleJava {

    public static void main(String[] args) {
        int x = 2;
        x = 3;

        Person p = new Person("Veaceslav", "Gaidarji",
                new Address("Chisinau", "bd. Stefan cel Mare", 001), 17);
        p.age = 18;

    }

    static class Person {
        String firstName;
        String lastName;
        Address address;
        int age;

        Person(String firstName, String lastName, Address address, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.age = age;
        }
    }

    static class Address {
        String city;
        String street;
        Integer building;

        Address(String city, String street, Integer building) {
            this.city = city;
            this.street = street;
            this.building = building;
        }
    }
}
