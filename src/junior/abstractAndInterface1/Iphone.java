package junior.abstractAndInterface1;

public class Iphone extends Electronics implements IChargeable {
    public Iphone(String model) {
        super(model);
    }

    @Override
    public void display() {
        System.out.println(getModel() + " 畫面大小為6.1吋");
    }

    @Override
    public void charge() {
        System.out.println(getModel() + " 正在使用 Lightning 進行充電...");
    }

}