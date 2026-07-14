package junior.abstractAndInterface2;

import junior.abstractAndInterface2.abstractExample.ACreditCard;
import junior.abstractAndInterface2.abstractExample.ALinePay;
import junior.abstractAndInterface2.abstractExample.AOrder;
import junior.abstractAndInterface2.interfaceExample.ICreditCard;
import junior.abstractAndInterface2.interfaceExample.ILinePay;
import junior.abstractAndInterface2.interfaceExample.IOrder;

public class Demo {
    public static void main(String[] args) {

        /*
         * 課後練習: 請練習此範例至少三次
         * 1. 先建立抽象類別:APayment
         * 兩個屬性: paymaneName(付款名稱), feeRate(手續費率)
         * 
         * 建構子: public APayment(String paymaneName, double feeRate)
         * 
         * 共同方法: public void pay(int amount)
         * 
         * 抽象方法: public abstract void processPayment(int amount, int fee)
         *
         * 私有方法: private String validateAmount(int amount)
         * private int calculateFee(int amount)
         * private void printPaymentResult(int amount, double fee)
         * 
         * 2. 建立付款方式的一般類別且都要繼承APayment:
         * 信用卡: ACreditCard
         * LinePay: ALinePay
         * 
         * 需要建構子: 記得內容也要用super
         * 
         * 需要實做出繼承的抽象方法: public void processPayment(int amount, int fee)
         *
         * 3. 建立一般類別AOrder
         * 建構子: 付款的物件傳入(物件注入)
         * 
         * 建立公開發方法: createOrderLinePay(int amount)
         * createOrderCreditCard(int amount)
         * 
         * 公開方法的內容為: 印出"建立訂單(付款名稱)"
         * 呼叫付款物件的pay方法
         * 
         * 4. 在程式進入點內 實體化AOrder 類別且呼叫 createOrderLinePay or createOrderCreditCard 方法
         * 
         */

        // 範例互動網站:
        // https://edward071123.github.io/gealent_050701/src/junior/abstractAndInterface2/object-call-visualizer.html

        // 如果上面都練習後已經清楚整個流程的呼叫可多練習以下問題
        // 在interfaceExample內修改 ICreditCard & ILinePay的內容
        // 增加各自的驗證跟手續費計算方法
        // 最後印出明細的內容
        // 在pay()內呼叫

        // 使用LinePay付款(ALinePay) 物件(object)先比喻成模組(module)
        ALinePay linePayPayment = new ALinePay();
        // new 就是類別(class)已經被生產出來 變成物件(object)
        AOrder order1 = new AOrder(linePayPayment);
        order1.createOrderLinePay(1000);

        System.out.println("=================================================");
        // 課堂練習 AOrder order2 使用信用卡付款(ACreditCard)
        ACreditCard creditCardPayment = new ACreditCard();
        AOrder order2 = new AOrder(creditCardPayment);
        order2.createOrderCreditCard(2000);

        System.out.println("=================================================");
        AOrder order3 = new AOrder(creditCardPayment);
        order3.createOrderCreditCard(-2000);

        System.out.println("====================以下為interface範例=============================");
        ILinePay iLinePay = new ILinePay();
        IOrder order4 = new IOrder(iLinePay);
        order4.createOrderLinePay(1000);

        System.out.println("=================================================");
        ICreditCard iCreidCard = new ICreditCard();
        IOrder order5 = new IOrder(iCreidCard);
        order5.createOrderCreditCard(3000);

    }
}