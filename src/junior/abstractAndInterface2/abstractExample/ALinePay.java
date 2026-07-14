package junior.abstractAndInterface2.abstractExample;

public class ALinePay extends APayment {
    public ALinePay() {
        super("LinePay", 0.01);
    }

    @Override
    public void processPayment(int amount, int fee) {
        System.out.println("正在跳轉至 LinePay 應用程式...");
        System.out.println("LinePay 支付成功，金額: " + amount + "，手續費: " + fee);
    }

}
