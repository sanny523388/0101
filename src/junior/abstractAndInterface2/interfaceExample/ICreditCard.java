package junior.abstractAndInterface2.interfaceExample;

public class ICreditCard implements IPayment {

    @Override
    public void pay(int amount) {
        System.out.println("正在進行信用卡授權驗證...");
        System.out.println("信用卡扣款成功，金額: " + amount);
    }

    public void validateCeditCardAmount(int amount) {
        System.out.println("驗證信用卡 金額");
        if (amount < 0) {
            System.out.println("信用卡 金額錯誤");
            return;
        }

        System.out.println("信用卡 金額正確");

    }
}
