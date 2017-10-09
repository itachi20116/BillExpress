package oopassignment;

import java.io.*;
import java.util.*;

public class BillExpress {

    static ArrayList<CustomerAccount> inactiveCustomerList;
    static ArrayList<CustomerAccount> currentCustomerList;
    static int choice;
    static int customerIDCount = 1;

    /*switch case like a telephone auto answer system*/
    public static void main(String[] args) throws Exception {
        boolean runProgram = true;
        boolean customeAccountManagement = true;
        boolean customerBillManagement = true;
        boolean haveBillInput = true;
        boolean selection = true;

        int index;

        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(input);

        //alternative method for input repeat inforamtion. e.g. Phone Long Distance Call record.
        /*  FileReader file = new FileReader("C:/abc.txt");
        BufferedReader fileBuffer = new BufferedReader(file);
        
        String phoneRecord = fileBuffer.readLine();
        int callCount = 0;
        while (phoneRecord != null) {
            phoneRecord = fileBuffer.readLine();
            PhoneBill.setLongCallTime(phoneRecord);
            callCount++;
        }*/
        currentCustomerList = new ArrayList();
        inactiveCustomerList = new ArrayList();

        while (runProgram) {
            System.out.println("BillExpress-Bill Management System");
            System.out.println("Select Operation :");
            System.out.println("[1] - Customer account Section.");
            System.out.println("[2] - Customer Bills Section.");
            System.out.println("[0] - Exit the program.");
            choice = checkInputInteger(buffer.readLine());
            switch (choice) {
                case 0: {
                    runProgram = false;
                    break;
                }
                case 1: {
                    customeAccountManagement = true;
                    while (customeAccountManagement) {
                        System.out.println("Customer account management");
                        System.out.println("Select Operation :");
                        System.out.println("[1] - Create Customer Account.");
                        System.out.println("[2] - Remove Customer Account.");
                        System.out.println("[3] - All Account Info");
                        System.out.println("[0] - Back to previous Menu");
                        choice = checkInputInteger(buffer.readLine());
                        switch (choice) {
                            case 0: {
                                customeAccountManagement = false;
                                break;
                            }
                            case 1: {
                                System.out.print("New Customer Name : ");
                                String name = buffer.readLine();
                                setNewCustomerinfo(name);
                                break;
                            }
                            case 2: {
                                currentCustomerList.forEach(customer -> {
                                    System.out.println("Custome ID : " + customer.getId() + " Customer Name: " + customer.getName());
                                });
                                System.out.println("Select the customer by Customer ID you want to remove");
                                choice = checkInputInteger(buffer.readLine());
                                while (checkHaveCustomer(choice) == -1) {
                                    System.out.println("No Such Customer!");
                                    currentCustomerList.forEach(customer -> {
                                        System.out.println("Custome ID : " + customer.getId() + " Customer Name: " + customer.getName());
                                    });
                                    System.out.println("Select the customer by Customer ID you want to remove");
                                    choice = checkInputInteger(buffer.readLine());
                                }
                                index = checkHaveCustomer(choice);
                                removeActiveCustomer(index);
                                break;
                            }
                            case 3: {
                                currentCustomerList.forEach(customer -> {
                                    customer.getcustomerInfo();
                                });
                                break;
                            }
                            default: {
                                System.out.println("Wrong Input!");
                                System.out.println("Only accept [1] , [2] , [3] or [0]");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 2: {
                    customerBillManagement = true;
                    while (customerBillManagement) {
                        System.out.println("Customer bill management");
                        System.out.println("Select Operation :");
                        System.out.println("[1] - Create new bill.");
                        System.out.println("[2] - Get customer's bills Information.");
                        System.out.println("[3] - Make payment.");
                        System.out.println("[0] - Back to previous Menu");
                        choice = checkInputInteger(buffer.readLine());
                        switch (choice) {
                            case 0: {
                                customerBillManagement = false;
                                break;
                            }
                            case 1: {
                                haveBillInput = true;
                                currentCustomerList.forEach(customer -> {
                                    System.out.println("Custome ID : " + customer.getId() + " Customer Name: " + customer.getName());
                                });
                                System.out.println("Select the customer by Customer ID");
                                choice = checkInputInteger(buffer.readLine());
                                while (checkHaveCustomer(choice) == -1) {
                                    System.out.println("No Such Customer!");
                                    currentCustomerList.forEach(customer -> {
                                        System.out.println("Custome ID : " + customer.getId() + " Customer Name: " + customer.getName());
                                    });
                                    System.out.println("Select the customer by Customer ID");
                                    choice = checkInputInteger(buffer.readLine());
                                }
                                index = checkHaveCustomer(choice);

                                // for (int i = 0; i < currentCustomerList.size(); i++) {
                                //    if (currentCustomerList.get(i).getId() == choice) {
                                selection = true;
                                while (selection) {
                                    System.out.println("Which type of bill to be add in " + currentCustomerList.get(index).getName() + " account ?");
                                    System.out.println("input: [1] for Electricity Bill  [2] for Gas Bill  [3] for Phone Bill  [4] for Water Bill  [0] for end bill input");
                                    choice = checkInputInteger(buffer.readLine());
                                    switch (choice) {
                                        case 1: {
                                            newElectricityBillInfo(buffer, index);
                                            while (haveBillInput) {
                                                System.out.println("Is another Electricity Bill have to input?");
                                                System.out.println("Input: [1] Yes  [2] No");
                                                choice = checkInputInteger(buffer.readLine());
                                                if (choice == 1) {
                                                    newElectricityBillInfo(buffer, index);
                                                } else {
                                                    haveBillInput = false;
                                                }
                                            }
                                            break;
                                        }
                                        case 2: {
                                            newGasBillInfo(buffer, index);
                                            while (haveBillInput) {
                                                System.out.println("Is another Electricity Bill have to input?");
                                                System.out.println("Input: [1] yes [2] No");
                                                choice = checkInputInteger(buffer.readLine());
                                                if (choice == 1) {
                                                    newGasBillInfo(buffer, index);
                                                } else {
                                                    haveBillInput = false;
                                                }
                                            }
                                            break;
                                        }
                                        case 3: {
                                            newPhoneBillInfo(buffer, index);
                                            while (haveBillInput) {
                                                System.out.println("Is another Electricity Bill have to input?");
                                                System.out.println("Input: [1] yes [2] No");
                                                choice = checkInputInteger(buffer.readLine());
                                                if (choice == 1) {
                                                    newPhoneBillInfo(buffer, index);
                                                } else {
                                                    haveBillInput = false;
                                                }
                                            }
                                            break;
                                        }
                                        case 4: {
                                            newWaterBillInfo(buffer, index);
                                            while (haveBillInput) {
                                                System.out.println("Is another Electricity Bill have to input?");
                                                System.out.println("Input: [1] yes [2] No");
                                                choice = checkInputInteger(buffer.readLine());
                                                if (choice == 1) {
                                                    newWaterBillInfo(buffer, index);
                                                } else {
                                                    haveBillInput = false;
                                                }
                                            }
                                            break;
                                        }
                                        case 0: {
                                            selection = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Wrong input!");
                                            System.out.println("Only accept [1] , [2] , [3] , [4] or [0]");
                                            break;
                                        }
                                    }
                                }
                                //   }
                                // }
                                break;
                            }
                            case 2: {
                                currentCustomerList.forEach(customer -> {
                                    System.out.println("Custome ID : " + customer.getId() + " Customer Name: " + customer.getName());
                                });
                                System.out.println("Select the customer by Customer ID");
                                choice = checkInputInteger(buffer.readLine());
                                while (checkHaveCustomer(choice) == -1) {
                                    System.out.println("No Such Customer!");
                                    currentCustomerList.forEach(customer -> {
                                        System.out.println("Custome ID : " + customer.getId() + " Customer Name: " + customer.getName());
                                    });
                                    System.out.println("Select the customer by Customer ID");
                                    choice = checkInputInteger(buffer.readLine());
                                }
                                index = checkHaveCustomer(choice);
                                // for (int i = 0; i < currentCustomerList.size(); i++) {
                                // if (currentCustomerList.get(i).getId() == choice) {
                                currentCustomerList.get(index).getcustomerInfo();
                                selection = true;
                                while (selection) {
                                    System.out.println("Which type of bills you want to get?");
                                    System.out.println("input: [1] for Electricity Bills  [2] for Gas Bills  [3] for Phone Bills  [4] for Water Bills  [5] for all avaliabile bills  [0] Go Back");
                                    choice = checkInputInteger(buffer.readLine());
                                    switch (choice) {
                                        case 0: {
                                            selection = false;
                                            break;
                                        }
                                        case 1: {
                                            for (int j = 0; j < currentCustomerList.get(index).bills.electricity.size(); j++) {
                                                System.out.println(j + "th Electricity Bill: ");
                                                currentCustomerList.get(index).bills.electricity.get(j).getDetails();
                                            }
                                            break;
                                        }
                                        case 2: {
                                            for (int j = 0; j < currentCustomerList.get(index).bills.gas.size(); j++) {
                                                System.out.println(j + "th Gas Bill: ");
                                                currentCustomerList.get(index).bills.gas.get(j).getDetails();
                                            }
                                            break;
                                        }
                                        case 3: {
                                            for (int j = 0; j < currentCustomerList.get(index).bills.phone.size(); j++) {
                                                System.out.println(j + "th Phone Bill: ");
                                                currentCustomerList.get(index).bills.phone.get(j).getDetails();
                                            }
                                            break;
                                        }
                                        case 4: {
                                            for (int j = 0; j < currentCustomerList.get(index).bills.water.size(); j++) {
                                                System.out.println(j + "th Water Bill: ");
                                                currentCustomerList.get(index).bills.water.get(j).getDetails();
                                            }
                                            break;
                                        }
                                        case 5: {
                                            for (int j = 0; j < currentCustomerList.get(index).bills.electricity.size(); j++) {
                                                System.out.println(j + "th Electricity Bill: ");
                                                currentCustomerList.get(index).bills.electricity.get(j).getDetails();
                                            }
                                            for (int j = 0; j < currentCustomerList.get(index).bills.gas.size(); j++) {
                                                System.out.println(j + "th Gas Bill: ");
                                                currentCustomerList.get(index).bills.gas.get(j).getDetails();
                                            }
                                            for (int j = 0; j < currentCustomerList.get(index).bills.phone.size(); j++) {
                                                System.out.println(j + "th Phone Bill: ");
                                                currentCustomerList.get(index).bills.phone.get(j).getDetails();
                                            }
                                            for (int j = 0; j < currentCustomerList.get(index).bills.water.size(); j++) {
                                                System.out.println(j + "th Water Bill: ");
                                                currentCustomerList.get(index).bills.water.get(j).getDetails();
                                            }
                                            selection = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Wrong input!");
                                            System.out.println("Only accept [1] , [2] , [3] , [4] or [5]");
                                            break;
                                        }
                                    }
                                    //      }
                                    //  }
                                }
                                break;
                            }
                            case 3: {
                                currentCustomerList.forEach(customer -> {
                                    System.out.println("Custome ID : " + customer.getId() + " Customer Name: " + customer.getName());
                                });
                                System.out.println("Select the customer by Customer ID");
                                choice = checkInputInteger(buffer.readLine());
                                while (checkHaveCustomer(choice) == -1) {
                                    System.out.println("No Such Customer!");
                                    currentCustomerList.forEach(customer -> {
                                        System.out.println("Custome ID : " + customer.getId() + " Customer Name: " + customer.getName());
                                    });
                                    System.out.println("Select the customer by Customer ID");
                                    choice = checkInputInteger(buffer.readLine());
                                }
                                index = checkHaveCustomer(choice);
                                // for (int i = 0; i < currentCustomerList.size(); i++) {
                                //   if (currentCustomerList.get(i).getId() == choice) {
                                currentCustomerList.get(index).getcustomerInfo();
                                selection = true;
                                while (selection) {
                                    System.out.println("Which type of bills you want to get?");

                                    System.out.println("input: [1] for Electricity Bills  [2] for Gas Bills  [3] for Phone Bills  [4] for Water Bills  [0] Go Back");
                                    choice = checkInputInteger(buffer.readLine());
                                    switch (choice) {
                                        case 1:
                                        case 2:
                                        case 3:
                                        case 4: {
                                            makePayment(buffer, index, choice);
                                            break;
                                        }
                                        case 0: {
                                            selection = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Wrong input!");
                                            System.out.println("Only accept [1] , [2] , [3] , [4] or [0]");
                                        }
                                    }
                                }
                                //    }
                                //  }
                                break;
                            }
                            default: {
                                System.out.println("Wrong Input!");
                                System.out.println("Only accept [1] , [2] , [3] or [0]");
                                break;
                            }
                        }
                    }
                    break;
                }
                default: {
                    System.out.println("Wrong Input!");
                    System.out.println("Only accept [1] , [2] or [0]");
                    break;
                }
            }
        }
        /*setNewCustomerinfo(buffer);
        newWaterBillInfo(buffer, 0);
        currentCustomerList.get(0).getcustomerInfo();
        makePayment(buffer, 0, 1);*/
    }

    /*main method*/

 /*add new customer to active list, assigning user id*/
    public static void setNewCustomerinfo(String name) {
        currentCustomerList.add(new CustomerAccount(name, customerIDCount));
        customerIDCount += 1;
    }

    /*add new customer to active list, assigning user id*/

 /*move the active user accounts from active list to inactive list*/
    public static void removeActiveCustomer(int customerId) {
        Iterator<CustomerAccount> i = currentCustomerList.iterator();
        while (i.hasNext()) {
            CustomerAccount Customer = i.next(); // must be called before you can call i.remove()
            if (Customer.getId() == customerId) {
                i.remove();
                inactiveCustomerList.add(Customer);
            }
        }
        /* currentCustomerList.forEach(customer -> {
            if (customer.getId() == customerId) {
                inactiveCustomerList.add(customer);
                currentCustomerList.remove(customer);
            }
        });*/
    }

    /*move the active user accounts from active list to inactive list*/

 /*payment for different bills using switch*/
    public static void makePayment(BufferedReader buffer, int customerIndex, int i) throws Exception {
        double payment;
        /*System.out.println("Which bill is you paying for : ");
        System.out.println("[1] for Water Bill, [2] for Gas Bill, [3] for Phone Bill, [4] for Electricity Bill");

        int billType = checkInputInteger(buffer.readLine());
        while (billType < 1 || billType > 4) {
            System.out.println("Wrong Input!");
            System.out.println("[1] for Water Bill, [2] for Gas Bill, [3] for Phone Bill, [4] for Electricity Bill");
            billType = checkInputInteger(buffer.readLine());
        }*/
        switch (i) {
            case 1: {
                WaterBill water = currentCustomerList.get(customerIndex).bills.water.get(0);
                System.out.println("How much would you want to pay : ");
                payment = checkInputDouble(buffer.readLine());
                while (water.makePayment(payment)) {
                    System.out.println("Wrong Input!");
                    System.out.println("Please input a positive value of your payment : ");
                    payment = checkInputDouble(buffer.readLine());
                }
                System.out.println("Thank for your payment.");
                System.out.println("The Balance of the bill : $" + water.getBalance());
                System.out.println("The amount will be carry forward to next period of bill.");

                break;
            }
            case 2: {
                GasBill gas = currentCustomerList.get(customerIndex).bills.gas.get(0);
                System.out.println("How much would you want to pay : ");
                payment = checkInputDouble(buffer.readLine());
                while (gas.makePayment(payment)) {
                    System.out.println("Wrong Input!");
                    System.out.println("Please input a positive value of your payment : ");
                    payment = checkInputDouble(buffer.readLine());
                }
                System.out.println("Thank for your payment.");
                System.out.println("The Balance of the bill : $" + gas.getBalance());
                System.out.println("The amount will be carry forward to next period of bill.");
                break;
            }
            case 3: {
                PhoneBill phone = currentCustomerList.get(customerIndex).bills.phone.get(0);
                System.out.println("How much would you want to pay : ");
                payment = checkInputDouble(buffer.readLine());
                while (phone.makePayment(payment)) {
                    System.out.println("Wrong Input!");
                    System.out.println("Please input a positive value of your payment : ");
                    payment = checkInputDouble(buffer.readLine());
                }
                System.out.println("Thank for your payment.");
                System.out.println("The Balance of the bill : $" + phone.getBalance());
                System.out.println("The amount will be carry forward to next period of bill.");
                break;
            }
            case 4: {
                ElectricityBill electricity = currentCustomerList.get(customerIndex).bills.electricity.get(0);
                System.out.println("How much would you want to pay : ");
                payment = checkInputDouble(buffer.readLine());
                while (electricity.makePayment(payment)) {
                    System.out.println("Wrong Input!");
                    System.out.println("Please input a positive value of your payment : ");
                    payment = checkInputDouble(buffer.readLine());
                }
                System.out.println("Thank for your payment.");
                System.out.println("The Balance of the bill : $" + electricity.getBalance());
                System.out.println("The amount will be carry forward to next period of bill.");
                break;
            }
        }
    }

    /*payment for different bills using switch*/

 /*Methods for garding user input nessary info, adding bills */
    public static void newElectricityBillInfo(BufferedReader buffer, int customerIndex) throws Exception {
        double usage;
        int date;
        int region;
        ElectricityBill current, previous;
        CustomerAccount customer;

        customer = currentCustomerList.get(customerIndex);
        customer.bills.newElectricityBill();
        current = customer.bills.electricity.get(0);

        if (customer.bills.electricity.size() > 1) {
            previous = customer.bills.electricity.get(1);
            current.setBalance(previous.getBalance());
            current.setPeriod(previous.getPeriod());
            current.setRegion(previous.getRegion());
        } else {
            System.out.println("Input the Period of the Electricity Bill in days : ");
            date = checkInputInteger(buffer.readLine());
            while (current.setPeriod(date)) {
                System.out.println("Wrong Input!");
                System.out.println("Input a positive value of days : ");
                date = checkInputInteger(buffer.readLine());
            }
            System.out.println("Input 1 , 2 or 3 for your region,");
            region = checkInputInteger(buffer.readLine());
            while (current.setRegion(region)) {
                System.out.println("Wrong Input!");
                System.out.println("Only accept 1 , 2 or 3 : ");
                region = checkInputInteger(buffer.readLine());
            }
        }
        System.out.println("Input the Electricity Used in kW: ");
        usage = checkInputDouble(buffer.readLine());
        while (current.setUsage(usage)) {
            System.out.println("Wrong Input!");
            System.out.println("Input a positive value of usage : ");
            usage = checkInputDouble(buffer.readLine());
        }
        current.calBillTotal();
        current.getDetails();

    }

    /*Methods for garding user input nessary info, adding bills */
    public static void newGasBillInfo(BufferedReader buffer, int customerIndex) throws Exception {
        double usage;
        int date;
        int type;
        GasBill current, previous;
        CustomerAccount customer;

        customer = currentCustomerList.get(customerIndex);
        customer.bills.newGasBill();
        current = customer.bills.gas.get(0);

        if (customer.bills.gas.size() > 1) {
            previous = customer.bills.gas.get(1);
            current.setBalance(previous.getBalance());
            current.setPeriod(previous.getPeriod());
            current.setType(previous.getType());
        } else {
            System.out.println("Input the Period of the Gas Bill in days : ");
            date = checkInputInteger(buffer.readLine());
            while (current.setPeriod(date)) {
                System.out.println("Wrong Input!");
                System.out.println("Input a positive value of days : ");
                date = checkInputInteger(buffer.readLine());
            }
            System.out.println("Input [1] or [2] for the usage type,");
            System.out.println("[1] for commercial use and [2] for residental use : ");
            type = checkInputInteger(buffer.readLine());
            while (current.setType(type)) {
                System.out.println("Wrong Input!");
                System.out.println("Only accept [1] or [2] : ");
                type = checkInputInteger(buffer.readLine());
            }
        }
        System.out.println("Input the Gas Used in kJ: ");
        usage = checkInputDouble(buffer.readLine());
        while (current.setUsage(usage)) {
            System.out.println("Wrong Input!");
            System.out.println("Input a positive value of usage : ");
            usage = checkInputDouble(buffer.readLine());
        }
        current.calBillTotal();
        current.getDetails();
    }

    /*Methods for garding user input nessary info, adding bills */
    public static void newPhoneBillInfo(BufferedReader buffer, int customerIndex) throws Exception {
        int localCall;
        int mobileCall;
        int mobileTime;
        int networkFeatureCount;
        boolean newLongCall;
        int longCallCount;
        PhoneBill current, previous;
        CustomerAccount customer;

        customer = currentCustomerList.get(customerIndex);
        customer.bills.newPhoneBill();
        current = customer.bills.phone.get(0);

        if (customer.bills.phone.size() > 1) {
            previous = customer.bills.phone.get(1);
            current.setBalance(previous.getBalance());
        }

        System.out.println("Input the number of times of Local Call : ");
        localCall = checkInputInteger(buffer.readLine());
        while (current.setLocalCallCount(localCall)) {
            System.out.println("Wrong Input!");
            System.out.println("Input a positive value : ");
            localCall = checkInputInteger(buffer.readLine());
        }

        System.out.println("Input the number of times of Mobile Call : ");
        mobileCall = checkInputInteger(buffer.readLine());
        while (current.setMobileCall(mobileCall)) {
            System.out.println("Wrong Input!");
            System.out.println("Input a positive value : ");
            mobileCall = checkInputInteger(buffer.readLine());
        }

        System.out.println("Input total minutes for Mobile Call : ");
        mobileTime = checkInputInteger(buffer.readLine());
        while (current.setMobileCallTime(mobileTime)) {
            System.out.println("Wrong Input!");
            System.out.println("Input a positive value : ");
            mobileTime = checkInputInteger(buffer.readLine());
        }

        System.out.println("Input the number of times of Network Feature used : ");
        networkFeatureCount = checkInputInteger(buffer.readLine());
        while (current.setNetFeatCount(networkFeatureCount)) {
            System.out.println("Wrong Input!");
            System.out.println("Input a positive value : ");
            networkFeatureCount = checkInputInteger(buffer.readLine());
        }

        System.out.println("How many times the Long Distance Call made?");
        longCallCount = checkInputInteger(buffer.readLine());
        for (int i = 0; i <= longCallCount; ++i) {
            System.out.println("Input the " + i + " th Long Distance Call time");
            current.setLongCallTime(checkInputInteger(buffer.readLine()));
        }
        current.calBillTotal();
        current.getDetails();

    }

    /*Methods for garding user input nessary info, adding bills */
    public static void newWaterBillInfo(BufferedReader buffer, int customerIndex) throws Exception {
        double usage;
        int date;
        int type;
        String isDrought;
        WaterBill current, previous;
        CustomerAccount customer;

        customer = currentCustomerList.get(customerIndex);
        customer.bills.newWaterBill();
        current = customer.bills.water.get(0);

        if (customer.bills.water.size() > 1) {
            /* if the old water bills here, just auto set the basic info*/
            previous = customer.bills.water.get(1);
            current.setPeriod(previous.getPeriod());
            current.setType(previous.getType());
            current.setBalance(previous.getBalance());
        } else {
            /*For setting the basic data of the new bill 
        while loop for checking correct input*/
            System.out.println("Input the Period of the Water Bill in days : ");
            date = checkInputInteger(buffer.readLine());
            while (current.setPeriod(date)) {
                System.out.println("Wrong Input!");
                System.out.println("Input a positive value of days : ");
                date = checkInputInteger(buffer.readLine());
            }

            System.out.println("Input [1] or [2] for the usage type,");
            System.out.println("[1] for commercial use and [2] for residental use : ");
            type = checkInputInteger(buffer.readLine());
            while (current.setType(type)) {
                System.out.println("Wrong Input!");
                System.out.println("Only accept [1] or [2] : ");
                type = checkInputInteger(buffer.readLine());
            }
        }
        System.out.println("Is drought period? : [Y/N] ");
        isDrought = buffer.readLine();
        while (current.setDrought(isDrought)) {
            System.out.println("Wrong Input!");
            System.out.println("Only Accept [y] or [n]");
            isDrought = buffer.readLine();
        }
        System.out.println("Input the Water Used in kL: ");
        usage = checkInputDouble(buffer.readLine());
        while (current.setUsage(usage)) {
            System.out.println("Wrong Input!");
            System.out.println("Input a positive value of usage : ");
            usage = checkInputDouble(buffer.readLine());
        }
        current.calBillTotal();
        current.getDetails();
    }

    /*Methods for garding user input nessary info, adding bills */

 /*loop the List of customer and find the right one.*/
    public static int checkHaveCustomer(int choice) {
        for (int i = 0; i < currentCustomerList.size(); i++) {
            if (currentCustomerList.get(i).getId() == choice) {
                return i;
            } else {
                return -1;
            }
        }
        return -1;
    }

    /*loop the List of customer and find the right one.*/

 /* Handling the input while it cannot parse to int or double*/
    public static double checkInputDouble(String dobuleInput) {
        double result;
        try {
            result = Double.parseDouble(dobuleInput);
            return result;
        } catch (Exception e) {
            return -1.0;
        }
    }

    public static int checkInputInteger(String intInput) {
        int result;
        try {
            result = Integer.parseInt(intInput);
            return result;
        } catch (Exception e) {
            return -1;
        }
    }
    /* Handling the input while it cannot parse to int or double*/
}
