package senior.borrowGiveBackSystem.librarySystemMysql;

// 用來暫存 categories 資料表查到的結果
public class CategoryInfo {
    // categories.name：大分類名稱，例如 P、N、程式書、小說
    public String name;

    // categories.sub_name：子分類，例如 Java、Python、奇幻、歷史
    public String subName;

    public CategoryInfo(String name, String subName) {
        this.name = name;
        this.subName = subName;
    }
}
