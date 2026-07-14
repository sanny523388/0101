package junior.abstractAndInterface1;

public abstract class Electronics {
    private String model;

    public Electronics(String model) {
        this.model = model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return this.model;
    }

    public void powerOn() {
        System.out.println(getModel() + " 正在開機...");
    }

    public void powerOff() {
        System.out.println(getModel() + " 正在關機...");
    }

    // 抽象方法: 顯示螢幕
    public abstract void display();

}
