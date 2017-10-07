package oopassignment;

public class CustomerAccount {

    private String customerName;
    private int customerNo;
    public Bills bills;

    CustomerAccount(String name, int customerNo) {
        setCustomerInfo(name, customerNo);
        bills = new Bills();
    }

    public void setCustomerInfo(String name, int customerNo) {
        this.customerName = name;
        this.customerNo = customerNo;
    }

    public String getName() {
        return this.customerName;
    }

    public int getId() {
        return this.customerNo;
    }

    public void getcustomerInfo() {
        System.out.println("Customer ID : " + getId());
        System.out.println("Customer Name : " + getName());
        // System.out.println("Customer have " + "types of bills");
        System.out.println("Electricity BIlls : " + bills.electricity.size());
        if (bills.electricity.size() > 0) {
            System.out.println("Electricity BIlls Balamce: $" + bills.electricity.get(0).getBalance());
        }
        System.out.println("Gas BIlls : " + bills.gas.size());
        if (bills.gas.size() > 0) {
            System.out.println("Gas BIlls Balamce: $" + bills.gas.get(0).getBalance());
        }
        System.out.println("Phone BIlls : " + bills.phone.size());
        if (bills.phone.size() > 0) {
            System.out.println("Phone BIlls Balamce: $" + bills.phone.get(0).getBalance());
        }
        System.out.println("Water BIlls : " + bills.water.size());
        if (bills.water.size() > 0) {
            System.out.println("Water BIlls Balamce: $" + bills.water.get(0).getBalance());
        }
    }
}
