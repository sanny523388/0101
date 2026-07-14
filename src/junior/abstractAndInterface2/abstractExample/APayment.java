package junior.abstractAndInterface2.abstractExample;

public abstract class APayment {
    // 共用的屬性: 所有付款方式都有名稱(paymaneName)跟手續費率(feeRate)
    // 屬性用到finale關鍵字: 只能設定一次, 通常在宣告時或是在建構子賦值, 無需寫setter
    // private final String paymaneName = "aa";
    // private final double feeRate = 1.6;
    private final String paymaneName;
    private final double feeRate;

    // 建構子賦值
    public APayment(String paymaneName, double feeRate) {
        this.paymaneName = paymaneName;
        this.feeRate = feeRate;
    }

    // getter
    public double getFeeRate() {
        return this.feeRate;
    }

    public String getPaymaneName() {
        return this.paymaneName;
    }

    // 付款總流程
    public void pay(int amount) {
        // 1. 驗證付款金額
        String validateAmountMessage = validateAmount(amount);
        if (!validateAmountMessage.isEmpty()) {
            // 有回傳訊息表示驗證失敗
            System.out.println(validateAmountMessage);
            System.out.println(getPaymaneName() + "付款失敗");
        } else {
            // 2. 計算手續費
            int fee = calculateFee(amount);
            System.out.println("開始使用" + getPaymaneName() + "付款");

            // 3.付款流程: 串接金流
            // 各家都又各家的串接標準，呼叫子類別(ALinePay, ACreditCard)的實作金流方法
            processPayment(amount, fee);

            // 4. 印出明細
            printPaymentResult(amount, fee);
        }

    }

    // private: 私有, 只能在自己類別內呼叫
    // 驗證付款金額
    private String validateAmount(int amount) {
        if (amount <= 0) {
            return "付款金額必須大於0";
        } else {
            return "";
        }
    }

    // 計算手續費
    private int calculateFee(int amount) {
        // 轉型介紹
        // String to int
        // String t1 = "20";
        // int t2 = Integer.parseInt(t1);

        // java 的強制轉型
        // 原本是 double 強制轉 int => double to int
        return (int) (amount * getFeeRate());
        // amount = 金額, getFeeRate = 手續費率

    }

    // 定義一個抽象方法為付款的流程:串接金流
    public abstract void processPayment(int amount, int fee);

    // 印出明細
    private void printPaymentResult(int amount, double fee) {
        System.out.println("付款金額: " + amount);
        System.out.println("手續費: " + fee);
        System.out.println(getPaymaneName() + "付款完成");
    }

}
