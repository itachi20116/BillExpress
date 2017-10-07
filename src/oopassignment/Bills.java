package oopassignment;

import java.util.*;

public class Bills {

    ArrayList<WaterBill> water;
    ArrayList<GasBill> gas;
    ArrayList<PhoneBill> phone;
    ArrayList<ElectricityBill> electricity;

    Bills() {
        this.initBills();
    }

    public void newWaterBill() {
        water.add(0, new WaterBill());
    }

    public void newGasBill() {
        gas.add(0, new GasBill());
    }

    public void newPhoneBill() {
        phone.add(0, new PhoneBill());
    }

    public void newElectricityBill() {
        electricity.add(0, new ElectricityBill());
    }

    public void initBills() {
        water = new ArrayList();
        gas = new ArrayList();
        phone = new ArrayList();
        electricity = new ArrayList();
    }
}
