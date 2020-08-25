package sample;

public class Card {
    private String id;
    private String pin;
    private double balance;

    public Card(String pin) {
        this.pin = pin;
        id = "id" + Math.random()*1000;
        balance = Double.parseDouble(String.format("%.2f", Math.random()*10000));
    }

    public String getId() {
        return id;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
