package junior.abstractAndInterface1;

public class Android extends Electronics implements IChargeable {

    public Android(String model) {
        super(model);
    }

    @Override
    public void display() {
        System.out.println(getModel() + " 畫面大小為6.7吋");
    }

    @Override
    public void charge() {
        System.out.println(getModel() + " 正在使用 USB-C 進行充電...");
    }

}