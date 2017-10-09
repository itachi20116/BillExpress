package oopassignment;

public class WaterBill {

    private int datePeriod;
    private boolean isDrought;
    private double usage;
    private final String billType = "Water Bill";
    private double billTotal;
    private double openBalance;
    private double closeBalance;
    private int type;
    private String typeName;
    private double limit;
    private double overUse = 0;
    private double chargeRate;

    WaterBill() {
        this.usage = 0;
        this.billTotal = 0;
        this.closeBalance = 0;
        this.openBalance = 0;
    }

    public void setBalance(double balanceBF) {
        this.openBalance = balanceBF;
    }

    public boolean setDrought(String YN) {
        if ("y".equals(YN.toLowerCase())) {
            this.isDrought = true;
            return false;
        } else if ("n".equals(YN.toLowerCase())) {
            this.isDrought = false;
            return false;
        } else {
            return true;
        }
    }

    public boolean setPeriod(int date) {
        if (date > 0) {
            this.datePeriod = date;
            return false;
        } else {
            return true;
        }
    }

    public boolean setType(int type) {
        if (type == 1) {
            this.type = type;
            this.typeName = "Commercial";
            this.limit = 200;
            this.chargeRate = 0.7;
            return false;
        } else if (type == 2) {
            this.type = type;
            this.typeName = "Residental";
            this.limit = 50;
            this.chargeRate = 0.55;
            return false;
        } else {
            return true;
        }
    }

    public boolean setUsage(double kL) {
        if (kL > 0) {
            this.usage = kL;
            return false;
        } else {
            return true;
        }
    }

    public void calBillTotal() {
        if (!this.isDrought) {
            this.billTotal = usage * this.chargeRate;
            this.closeBalance = this.openBalance + this.billTotal;
        } else {
            if (this.usage > this.limit) {
                this.overUse = this.usage - this.limit;
                this.billTotal = usage * this.chargeRate + this.overUse / 10.0 * 3;
                this.closeBalance = this.openBalance = this.billTotal;
            } else {
                this.billTotal = usage * this.chargeRate;
                this.closeBalance = this.openBalance + this.billTotal;
            }
        }
    }

    public boolean makePayment(double payment) {
        if (payment > 0) {
            this.closeBalance = this.openBalance + this.billTotal - payment;
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

    public double getChargeRate() {
        return this.chargeRate;
    }

    public void getDetails() {
        System.out.println(this.getTypeDetail());
        System.out.println("Period : " + this.getPeriod() + " days");
        System.out.println("Total Used : " + this.getUsage() + " kL ");
        if (this.overUse > 0) {
            System.out.println("Over Used: " + getOverUse() + " kL");
        }
        System.out.println("Charge at : " + this.getChargeRate());
        System.out.println("Bill Total :$" + this.getBillTotal());
        /* if (this.closeBalance > 0) {
            System.out.println("Debit : $" + this.getBalance());
        } else if (this.closeBalance < 0) {
            System.out.println("Credit : $" + this.getBalance());
        } else {
            System.out.println("Blanace : $" + this.getBalance());
        }*/
    }

    private double getOverUse() {
        return this.overUse;
    }

    public int getPeriod() {
        return this.datePeriod;
    }

    public int getType() {
        return this.type;
    }

    public String getTypeDetail() {
        return this.typeName + " " + this.billType;
    }

    public double getUsage() {
        return this.usage;
    }

}
