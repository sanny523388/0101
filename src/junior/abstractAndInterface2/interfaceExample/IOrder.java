package junior.abstractAndInterface2.interfaceExample;

public class IOrder {
    private ILinePay linePay;
    private ICreditCard creditCard;

    public IOrder(ILinePay linePay) {
        this.linePay = linePay;
    }

    public IOrder(ICreditCard creditCard) {
        this.creditCard = creditCard;
    }

    // LinePay呼叫pay()
    public void createOrderLinePay(int amount) {
        System.out.println("建立訂單(LinePay)");
        this.linePay.pay(amount);
    }

    // 信用卡呼叫pay()
    public void createOrderCreditCard(int amount) {
        System.out.println("建立訂單(CreditCard)");
        this.creditCard.pay(amount);
    }

}
