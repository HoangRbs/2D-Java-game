package TestingBullshit;

import java.util.ArrayList;

public class ArrayListCopy {
    public static void main(String args[])
    {
        ArrayList<Car> m_cars = new ArrayList<Car>();
        ArrayList<Car> bullshit_cars = new ArrayList<Car>();

        Car a_car = new Car("BMW");

        m_cars.add(a_car);
        bullshit_cars.add(a_car);


        //a_car.name = "lamborghini";

        bullshit_cars.get(0).name = "labmorghini";
        bullshit_cars.remove(0);

        System.out.println(a_car.name);

        System.out.println(m_cars.get(0).name);
    }

    public static class Car
    {
        public Car(String name)
        {
            this.name = name;
        }

        public String name;
    }
}