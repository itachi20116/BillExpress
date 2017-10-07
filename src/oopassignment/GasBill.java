package oopassignment;

public class GasBill {

    private double openBalance;
    private double closeBalance;
    private double billTotal;
    private int type;
    private String typeName;
    private final String billType = "Gas Bill";
    private double chargeRate;
    private double overUseChargeRate;
    private int datePeriod;
    private double usage;
    private double overUse = 0;
    private double serviceCharge;
    private double usageCharge;

    GasBill() {

        this.openBalance = 0;
        this.billTotal = 0;
    }

    public void setBalance(double BalanceBF) {
        this.openBalance = BalanceBF;
    }

    public boolean setPeriod(int date) {
        if (date < 0) {
            return true;
        } else {
            this.datePeriod = date;
            return false;
        }
    }

    public boolean setType(int type) {
        if (type == 1) {
            this.type = type;
            this.typeName = "Commercial Use";
            this.chargeRate = 1.083;
            return false;
        } else if (type == 2) {
            this.type = type;
            this.typeName = "Residential Use";
            this.chargeRate = 1.021;
            this.overUseChargeRate = 1.037;
            return false;
        } else {
            return true;
        }
    }

    public boolean setUsage(double kJ) {
        if (kJ < 0) {
            return true;
        } else {
            this.usage = kJ;
            return false;
        }
    }

    public void calBillTotal() {
        calServiceCharge();
        calUsageCharge();
        this.billTotal = this.usageCharge + this.serviceCharge;
        this.closeBalance = this.openBalance = this.billTotal;
    }

    public void calServiceCharge() {
        this.serviceCharge = this.datePeriod * 0.42;
    }

    public void calUsageCharge() {
        if (this.type == 1) {
            this.usageCharge = this.usage * this.chargeRate;
        } else {
            if (this.usage > 500) {
                this.overUse = this.usage - 500;
                this.usageCharge = this.overUse * this.overUseChargeRate + this.usage * this.chargeRate;
            } else {
                this.usageCharge = this.usage * this.chargeRate;
            }
        }
    }

    public boolean makePayment(double payment) {
        if (payment < 0) {
            return true;
        } else {
            this.closeBalance = this.openBalance + this.billTotal - payment;
            return false;
        }
    }

    public double getBalance() {
        return this.closeBalance;
    }

    public double getBillTotal() {
        return this.billTotal;
    }

    public void getDetails() {
        System.out.println(getTypeDetail());
        System.out.println("Period : " + this.getPeriod() + " days $0.42 per day");
        System.out.println("Total Used : " + getUsage() + " kJ");
        if (this.overUse > 0) {
            System.out.println("Over Used: " + getOverUse() + " kJ");
            System.out.println("Charge first 500kJ at : $" + this.chargeRate + " over 500kJ : $" + this.overUseChargeRate);
        } else {
            System.out.println("Charge at : $" + this.chargeRate);
        }
        System.out.println("Service Charge: $" + this.getServiceCharge());
        System.out.println("Usage Charge: $" + this.getUsageCharge());
        System.out.println("Bill Total: $" + this.getBillTotal());
    }

    public double getOverUse() {
        return this.overUse;
    }

    public int getPeriod() {
        return this.datePeriod;
    }

    public double getServiceCharge() {
        return this.serviceCharge;
    }

    public int getType() {
        return this.type;
    }

    public String getTypeDetail() {
        return ("Bill Type: " + this.billType + " " + this.typeName);
    }

    public double getUsageCharge() {
        return this.usageCharge;
    }

    public double getUsage() {
        return this.usage;
    }

}
