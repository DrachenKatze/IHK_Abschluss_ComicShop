package Tests;

import Model.Customer;

public class TestCustomer {


    public static void main(String[] args){

        Customer abhohler = new Customer("Sven");
        Customer lierern = new Customer("Franziska", "Seeblick 3", "Berlin", "franzi3@web,de", 160551, 030456789d);

        System.out.println("Abhohler: " + abhohler.getcName() + " " + abhohler.getCustomerID());
        System.out.println("Liefern: " + lierern.getcName() + " " + lierern.getCustomerID());

    }
}
