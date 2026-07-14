package senior.borrowGiveBackSystem.librarySystemMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    // 從 MySQL 查詢所有書籍資料
    //
    // 使用「分段 SQL」：
    // 1. 先查 books 資料表，取得每一本書的基本資料與 category_id
    // 2. 再用每一本書的 category_id 去 categories 資料表查分類資料
    // 3. 最後把 books + categories 的資料組合成 Java 的 Book 物件
    public List<Book> loadBooks() throws Exception {
        List<Book> books = new ArrayList<>();

        // 第一段 SQL：只查 books 資料表
        // category_id 是分類的編號，等一下會拿它去查 categories 資料表
        String bookSql = "SELECT id, title, author, available, borrowUser, category_id FROM books ORDER BY id";

        // 第二段 SQL：依照 category_id 查 categories 資料表
        // ? 是 PreparedStatement 的參數位置，後面會用 ps.setInt(1, categoryId) 塞值
        String categorySql = "SELECT name, sub_name FROM categories WHERE id = ?";

        try (
                // 建立 MySQL 連線，連線資訊放在同一個 package 的 DBConnection.java
                Connection connection = new DBConnection().getConnection();

                // bookPs 負責執行第一段 SQL：查 books
                PreparedStatement bookPs = connection.prepareStatement(bookSql);

                // categoryPs 負責執行第二段 SQL：查 categories
                // 這個 PreparedStatement 會在 while 迴圈裡重複使用
                PreparedStatement categoryPs = connection.prepareStatement(categorySql);

                // executeQuery() 會執行 SELECT，查詢結果會放在 ResultSet 裡
                ResultSet bookRs = bookPs.executeQuery();) {

            // bookRs.next() 會一筆一筆往下讀 books 的查詢結果
            while (bookRs.next()) {
                // 從目前這本書取出 category_id
                int categoryId = bookRs.getInt("category_id");

                // 用 category_id 去 categories 資料表查出分類名稱與子分類
                CategoryInfo categoryInfo = findCategory(categoryPs, categoryId);

                // 把 books 的資料與 categories 的資料組合成 Book / ProgrammingBook / NovelBook
                books.add(toBook(bookRs, categoryInfo));
            }
        } catch (Exception e) {
            throw new Exception("查詢 books 資料表失敗: " + e.getMessage());
        }

        return books;
    }

    // 將目前所有書籍的借閱狀態寫回 MySQL
    //
    // 借書或還書時，LibraryService 會先修改記憶體中的 Book 物件：
    // - available：true 代表可借，false 代表已借出
    // - borrowUser：借閱人姓名，沒有人借時使用 "NULL"
    //
    // 這個方法會把修改後的狀態更新回 books 資料表。
    public void saveBooks(List<Book> books) throws Exception {
        // 只更新借還書會改變的欄位，不更新 title、author、category_id
        String sql = "UPDATE books SET available = ?, borrowUser = ? WHERE id = ?";

        try (
                Connection connection = new DBConnection().getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);) {
            for (Book oneBook : books) {
                // 第 1 個 ? 對應 available 欄位
                ps.setBoolean(1, oneBook.isAvailable());

                // 第 2 個 ? 對應 borrowUser 欄位
                ps.setString(2, oneBook.getBorrowUser());

                // 第 3 個 ? 對應 WHERE id = ?，指定要更新哪一本書
                ps.setInt(3, Integer.parseInt(oneBook.getNumber()));

                // addBatch() 先把這次 UPDATE 加到批次清單
                // 迴圈跑完後再一次 executeBatch()，不用每本書都立刻送出 SQL
                ps.addBatch();
            }

            // 一次執行上面累積的多筆 UPDATE
            ps.executeBatch();
        } catch (Exception e) {
            throw new Exception("更新 books 資料表失敗: " + e.getMessage());
        }
    }

    // 根據 category_id 查詢分類資料
    //
    // 參數 ps 是外面已經建立好的 PreparedStatement：
    // SELECT name, sub_name FROM categories WHERE id = ?
    //
    // 這樣寫可以重複使用同一個 PreparedStatement，不用每本書都重新 prepare SQL。
    private CategoryInfo findCategory(PreparedStatement ps, int categoryId) throws Exception {
        // 把 categoryId 塞到 SQL 的第一個 ? 裡
        ps.setInt(1, categoryId);

        // 執行 categories 查詢
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                // 找得到分類時，回傳分類名稱 name 與子分類 sub_name
                return new CategoryInfo(rs.getString("name"), rs.getString("sub_name"));
            }
        }

        // 如果 books.category_id 找不到對應的 categories.id，
        // 就回傳空字串，讓後面可以建立一般 Book 物件，不讓程式直接中斷。
        return new CategoryInfo("", "");
    }

    // 把資料庫查到的一筆書籍資料轉成 Java 物件
    //
    // rs 來源：books 資料表
    // categoryInfo 來源：categories 資料表
    //
    // 如果分類是程式書，就建立 ProgrammingBook
    // 如果分類是小說，就建立 NovelBook
    // 其他分類就建立一般 Book
    private Book toBook(ResultSet rs, CategoryInfo categoryInfo) throws Exception {
        // books.id 是 int，原本 Book 類別的 number 是 String，所以這裡轉成字串
        String number = String.valueOf(rs.getInt("id"));
        String title = rs.getString("title");
        String author = rs.getString("author");
        boolean available = rs.getBoolean("available");
        String borrowUser = rs.getString("borrowUser");

        // categories.name 用來判斷書籍大分類，例如 P、N、程式書、小說
        String categoryName = categoryInfo.name;

        // categories.sub_name 用來放細分類資訊
        // 程式書可當成語言，例如 Java、Python
        // 小說可當成分類，例如 奇幻、歷史
        String categorySubName = categoryInfo.subName;

        // 如果資料庫 borrowUser 是 null 或空字串，統一轉成原本系統使用的 "NULL"
        if (borrowUser == null || borrowUser.isEmpty()) {
            borrowUser = "NULL";
        }

        // 如果分類沒有 sub_name，避免畫面印出 null
        if (categorySubName == null) {
            categorySubName = "";
        }

        // 依照 categories.name 判斷要建立哪一種 Book 子類別
        if (isProgrammingCategory(categoryName)) {
            return new ProgrammingBook(number, title, author, available, borrowUser, categorySubName);
        }
        if (isNovelCategory(categoryName)) {
            return new NovelBook(number, title, author, available, borrowUser, categorySubName);
        }

        return new Book(number, title, author, available, borrowUser);
    }

    // 判斷 categories.name 是否代表「程式書」
    // 這裡多支援幾種寫法，讓資料庫可以放 P、Programming、程式書、程式類。
    private boolean isProgrammingCategory(String categoryName) {

        if (categoryName.equalsIgnoreCase("P")) {
            return true;
        }

        if (categoryName.equalsIgnoreCase("Programming")) {
            return true;
        }

        if (categoryName.equalsIgnoreCase("ProgrammingBook")) {
            return true;
        }

        if (categoryName.equals("程式書")) {
            return true;
        }

        if (categoryName.equals("程式類")) {
            return true;
        }

        return false;
    }

    // 判斷 categories.name 是否代表「小說」
    // 這裡多支援幾種寫法，讓資料庫可以放 N、Novel、小說、小說類。
    private boolean isNovelCategory(String categoryName) {

        if (categoryName.equalsIgnoreCase("N")) {
            return true;
        }

        if (categoryName.equalsIgnoreCase("Novel")) {
            return true;
        }

        if (categoryName.equalsIgnoreCase("NovelBook")) {
            return true;
        }

        if (categoryName.equals("小說")) {
            return true;
        }

        if (categoryName.equals("小說類")) {
            return true;
        }

        return false;
    }

}
