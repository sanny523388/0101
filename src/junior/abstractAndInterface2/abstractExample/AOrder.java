package junior.abstractAndInterface2.abstractExample;

public class AOrder {
    private ALinePay linePay;
    private ACreditCard creditCard;

    // 多載建構子注入傳入的物件, 初始化的時候就可以使用LinePay付款
    // 建構子注入傳入的物件, 初始化的時候就可以使用LinePay付款
    // 類別去使用另外一個物件(物件注入)
    public AOrder(ALinePay linePay) {
        this.linePay = linePay;
        // this.linePa = new ALinePay();
    }

    public AOrder(ACreditCard creditCard) {
        this.creditCard = creditCard;
        // this.creditCard = new ACreditCard(); 
        //this.creditCard = 屬性, new ACreditCard() = 傳遞進來的參數
    }
    
    // Line呼叫pay()
    public void createOrderLinePay(int amount) {
        System.out.println("建立訂單(LinePay)");
        this.linePay.pay(amount);
    }

    public void createOrderCreditCard(int amount) {
        System.out.println("建立訂單(CreditCard)");
        this.creditCard.pay(amount);
    }

}
