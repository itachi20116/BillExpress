package oopassignment;

public class ElectricityBill {

    private double billTotal;
    private final String billType = "Electricty Bill";
    private String chargeRate;
    private double closeBalance;
    private int datePeriod;
    private double openBalance;
    private int region;
    private double serviceCharge;
    private double usageCharge;
    private double usage;

    ElectricityBill() {
        this.billTotal = 0;
        this.openBalance = 0;
        this.closeBalance = 0;
    }

    public void setBalance(double BalanceBF) {
        this.openBalance = BalanceBF;
    }

    public boolean setPeriod(int date) {
        if (date < 1) {
            return true;
        } else {
            this.datePeriod = date;
            return false;
        }
    }

    public boolean setRegion(int region) {
        if (region < 1 || region > 3) {
            return true;
        } else {
            this.region = region;
            return false;
        }
    }

    public boolean setUsage(double kWh) {
        if (kWh < 0) {
            return true;
        } else {
            this.usage = kWh;
            return false;
        }
    }

    public void calcServiceCharge() {
        this.serviceCharge = this.datePeriod * 0.59;
    }

    public void calcUsageCharge() {
        double usageCharge = 0.0;
        switch (this.region) {
            case 1: {
                this.chargeRate = "Frist 380kWh @$0.69, Remaining @$0.59";
                if (this.usage > 380) {
                    usageCharge = 380 * 0.69 + (this.usage - 380) * 0.59;
                } else {
                    usageCharge = this.usage * 0.69;
                }
                break;
            }
            case 2: {
                this.chargeRate = "Frist 450kWh @$0.65, Remaining @$0.60";
                if (this.usage > 450) {
                    usageCharge = 450 * 0.65 + (this.usage - 450) * 0.6;
                } else {
                    usageCharge = this.usage * 0.65;
                }
                break;
            }
            case 3: {
                this.chargeRate = "Frist 1000kWh @$0.76, Remaining @$0.52";
                if (this.usage > 1000) {
                    usageCharge = 1000 * 0.76 + (this.usage - 1000) * 0.52;
                } else {
                    usageCharge = this.usage * 0.76;
                }
                break;
            }
        }
        this.usageCharge = usageCharge;
    }

    public void calBillTotal() {
        calcServiceCharge();
        calcUsageCharge();
        this.billTotal = this.serviceCharge + this.usageCharge;
        this.closeBalance = this.openBalance + this.billTotal;
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

    public void getDetails() {
        System.out.println(this.getTypeDetail());
        System.out.println("Period : " + this.getPeriod() + " days $0.59 per day");
        System.out.println("Used: " + this.usage + "kWh ");
        System.out.println(this.chargeRate);
        System.out.println("Service Charge: $" + this.getServiceCharge());
        System.out.println("Usage Charge: $" + this.getUsageCharge());
        System.out.println("Bill Total: $" + this.getBillTotal());
        /* if (this.closeBalance > 0) {
            System.out.println("Debit : $" + this.getBalance());
        } else if (this.closeBalance < 0) {
            System.out.println("Credit : $" + this.getBalance());
        } else {
            System.out.println("Blanace : $" + this.getBalance());
        }*/
    }

    public int getPeriod() {
        return this.datePeriod;
    }

    public int getRegion() {
        return this.region;
    }

    public double getServiceCharge() {
        return this.serviceCharge;
    }

    public String getTypeDetail() {
        return ("Bill Type: " + this.billType + "in Region " + this.region);
    }

    public double getUsageCharge() {
        return this.usageCharge;
    }

}
