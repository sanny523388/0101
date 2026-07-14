package junior.abstractAndInterface2.abstractExample;

public class ACreditCard extends APayment {
    public ACreditCard() {
        super("信用卡", 0.03);
    }

    @Override
    public void processPayment(int amount, int fee) {
        System.out.println("正在進行信用卡授權驗證...");
        System.out.println("信用卡扣款成功，金額: " + amount + "，手續費: " + fee);
    }

}
