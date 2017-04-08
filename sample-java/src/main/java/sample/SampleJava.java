package sample;

public class SampleJava {

    public static void main(String[] args) {
        int x = 2;
        x = 3;

        Person p = new Person("Veaceslav", "Gaidarji",
                new Address("Chisinau", "bd. Stefan cel Mare", 1), 17);
        p.age = 18;

        Boolean b1 = Boolean.FALSE;
        Byte b2 = Byte.MAX_VALUE;
        String s = "sds";
        Short sh = (short) 2;
        Character c = 's';
        Integer i2 = 2;
        Long l = 2L;
        Float f = 2.f;
        Double d = 2.d;
        Integer[] i = new Integer[] {1, 2, 3};

        Person person = new Person();
        person.setFirstName("Veaceslav");
        person.setLastName("Gaidarji");
        person.setAge(17);
        Address address = new Address();
        address.setCity("Chisinau");
        address.setStreet("bd. Stefan cel Mare");
        address.setBuilding(1);
        person.setAddress(address);
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

        Person() {

        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setAddress(Address address) {
            this.address = address;
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

        Address() {

        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public void setBuilding(int building) {
            this.building = building;
        }
    }
}
