package junior.abstractAndInterface2.interfaceExample;

public class ILinePay implements IPayment {

    @Override
    public void pay(int amount) {
        System.out.println("正在跳轉至 LinePay 應用程式...");
        System.out.println("LinePay 支付成功，金額: " + amount);
    }

    public void validateLinePayAmount(int amount) {
        System.out.println("驗證LinePay 金額");
        if (amount >= 0) {
            System.out.println("LinePay 金額正確");
        } else {
            System.out.println("LinePay 金額錯誤");
        }
    }

}
