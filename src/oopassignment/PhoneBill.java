package oopassignment;

import java.util.*;

public class PhoneBill {

    private double openBalance;
    private double closeBalance;
    private double billTotal;
    private final String billType = "Phone Bill";
    private int localCallCount;
    private int longDistanceCallCount;
    private int longDistanceCallTime;
    private int mobileCallCount;
    private int mobuleCallTime;
    private int networkFeatCount;
    private ArrayList<Integer> longDistanceCallRecord = new ArrayList<Integer>();
    private int index = 0;
    private double localCallCharge;
    private double mobileCallCharge;
    private double networkFeatureCharge;
    private double longDistanceCallCharge;

    PhoneBill() {
        this.openBalance = 0;
        this.billTotal = 0;
        this.localCallCount = 0;
        this.longDistanceCallCount = 0;
        this.longDistanceCallTime = 0;
        this.mobileCallCount = 0;
        this.mobuleCallTime = 0;
        this.networkFeatCount = 0;
    }

    public void setBalance(double BalanceBF) {
        this.openBalance = BalanceBF;
    }

    public boolean setLocalCallCount(int count) {
        if (count > 0) {
            this.localCallCount = count;
            return false;
        } else {
            return true;
        }
    }

    public boolean setLongCallTime(int time) {
        if (time > 0) {
            this.longDistanceCallRecord.add(time);
            this.longDistanceCallCount++;
            return false;
        } else {
            return true;
        }
    }

    public boolean setMobileCall(int count) {
        if (count > 0) {
            this.mobileCallCount = count;
            return false;
        } else {
            return true;
        }
    }

    public boolean setMobileCallTime(int time) {
        if (time > 0) {
            this.mobuleCallTime = this.mobuleCallTime = time;
            return false;
        } else {
            return true;
        }
    }

    public boolean setNetFeatCount(int count) {
        if (count > 0) {
            this.networkFeatCount = count;
            return false;
        } else {
            return true;
        }
    }

    public void calLocalCallCharge() {
        localCallCharge = 0.25 * this.localCallCount;
    }

    public void calLongDistanceCallCahrge() {
        this.longDistanceCallRecord.forEach(call -> {
            if (call < 5) {
                call = 5;
            }
            this.longDistanceCallTime = call + this.longDistanceCallTime;
        });
        this.longDistanceCallCharge = this.longDistanceCallCount * 0.17 + 0.3 * this.longDistanceCallTime;
    }

    public void calMobileCallCharge() {
        this.mobileCallCharge = this.mobileCallCount * 0.26 + this.mobuleCallTime * 0.43;
    }

    public void calNetworkFeatureCharge() {
        this.networkFeatureCharge = this.networkFeatCount * 0.15;
    }

    public void calBillTotal() {
        calLocalCallCharge();
        calLongDistanceCallCahrge();
        calMobileCallCharge();
        calNetworkFeatureCharge();
        this.billTotal = this.localCallCharge + this.longDistanceCallCharge + mobileCallCharge + networkFeatureCharge;
    this.closeBalance = this.openBalance = this.billTotal;
    }

    public boolean makePayment(double payment) {
        if (payment > 0) {
            this.closeBalance = this.billTotal + this.openBalance - payment;
            this.billTotal = 0;
            return false;
        } else {
            return true;
        }
    }

    public double getBalance() {
        return this.closeBalance;
    }

    public double getBillTotal() {
        return this.billTotal;
    }

    public void getDetails() {
        System.out.println("Bill Type: " + this.getType() + "Monthly Bill");
        System.out.println("Usage detail");
        System.out.println(" -Local Call" + getLocalCallCount() + " times");
        System.out.println("  Charge $0.25 per call. Total charge : $" + this.getLocalCallCharge());
        System.out.println();
        System.out.println(" -Long Distance Call " + this.longDistanceCallCount + " times");
        getLongDistanceCallRecord().forEach(call -> {
            System.out.print(index + "th call : ");
            System.out.println(call + "minutes");
            index++;
        });
        System.out.println("  Charge $0.17 per call, $0.3 per minutes. Minimum charge time per call is 5 minutes.");
        System.out.println("  Total charge : $" + this.getLongDistanceCallCharge());
        System.out.println();
        System.out.println(" -Mobile Call" + getMobileCallCount() + " times. Total " + getMobileCallTime() + "minutes.");
        System.out.println("  Charge $0.26 per call, $0.43 per minutes. Total charge : $" + this.getMobileCallCharge());
        System.out.println();
        System.out.println(" -Network Feature" + getNetworkFeatureCount() + " times");
        System.out.println("  Charge $0.15 per use. Total charge : $" + this.getNetworkFeatureCharge());
        System.out.println("Bill Total: " + this.getBillTotal());
    }

    public int getLocalCallCount() {
        return this.localCallCount;
    }

    public double getLocalCallCharge() {
        return this.localCallCharge;
    }

    public ArrayList getLongDistanceCallRecord() {
        return this.longDistanceCallRecord;
    }

    public double getLongDistanceCallCharge() {
        return this.longDistanceCallCharge;
    }

    public int getMobileCallCount() {
        return this.mobileCallCount;
    }

    public int getMobileCallTime() {
        return this.mobuleCallTime;
    }

    public double getMobileCallCharge() {
        return this.mobileCallCharge;
    }

    public int getNetworkFeatureCount() {
        return this.networkFeatCount;
    }

    public double getNetworkFeatureCharge() {
        return this.networkFeatureCharge;
    }

    public String getType() {
        return this.billType;
    }

}
