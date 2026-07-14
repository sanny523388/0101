package junior.collectionInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Demo {
    public static void main(String[] args) {
        /*
         * Collection（集合）可以一次存放很多筆資料。
         * 跟陣列相對比:
         * 陣列需要先劃出倉庫大小
         * 集合則像是有彈性的袋子
         */

        // ==================== 1. List ====================
        /*
         * 1. 有順序。
         * 2. 有索引，索引從 0 開始。
         * 3. 可以保存重複資料。
         * 
         * 可能的使用情境：
         * 1. 購物車商品，商品可能重複。
         * 2. 學生點名名單，需要保留順序。
         * 3. 播放清單，需要按照加入順序播放。
         * 
         * 常用方法：
         * add() 新增資料
         * get() 依照索引取得資料
         * set() 修改資料
         * remove() 刪除資料
         * size() 取得資料筆數
         * clear() 清空資料
         */

        // List<String> fruits = new ArrayList<String>();
        List<String> fruits = new ArrayList<>();
        System.out.println("=====List0=====");
        System.out.println("List全部: " + fruits);
        System.out.println("List 目前長度: " + fruits.size());

        fruits.add("apple");
        fruits.add("banana");
        System.out.println("=====List1=====");
        System.out.println("List全部: " + fruits);
        System.out.println("List index 0: " + fruits.get(0));
        System.out.println("List index 1: " + fruits.get(1));
        System.out.println("List 目前長度: " + fruits.size());

        fruits.add("banana");
        System.out.println("=====List2=====");
        System.out.println("List全部: " + fruits);
        System.out.println("List 目前長度: " + fruits.size());

        fruits.remove("banana");
        System.out.println("=====List3=====");
        System.out.println("List全部: " + fruits);
        System.out.println("List 目前長度: " + fruits.size());

        fruits.set(0, "orange");
        System.out.println("=====List4=====");
        System.out.println("List全部: " + fruits);
        System.out.println("List 目前長度: " + fruits.size());

        fruits.add("apple");
        for (String one : fruits) {
            System.out.println("foreach: " + one);
        }

        fruits.clear();
        System.out.println("=====List5=====");
        System.out.println("List全部: " + fruits);
        System.out.println("List 目前長度: " + fruits.size());

        System.out.println("===============================================");
        // ==================== 2. Set ====================
        /*
         * Set 的特性：
         * 1. 不會保存重複資料。
         * 2. 沒有索引，不能使用 get(0)。
         * 
         * 可能的使用情境：
         * 1. 會員帳號，每個帳號不能重複。
         * 2. 商品標籤，不需要出現相同標籤。
         * 3. 活動簽到名單，同一個人只能簽到一次。
         * 
         * 常用方法：
         * add() 新增資料
         * contains() 判斷是否包含資料
         * remove() 刪除資料
         * size() 取得資料筆數
         * clear() 清空資料
         */
        // Set<String> studentNames = new HashSet<String>();
        Set<String> studentNames = new HashSet<>();
        studentNames.add("小明");
        studentNames.add("小華");
        studentNames.add("小明");
        studentNames.add("小華");

        System.out.println("=====Set0=====");
        System.out.println("Set全部: " + studentNames);
        System.out.println("Set 目前長度: " + studentNames.size());
        studentNames.remove("小華");

        System.out.println("=====Set1=====");
        System.out.println("Set全部: " + studentNames);
        System.out.println("Set 目前長度: " + studentNames.size());

        System.out.println("=====Set2=====");
        System.out.println("Set全部: " + studentNames);
        System.out.println("Set 目前長度: " + studentNames.size());
        System.out.println("Set 是否有小明： " + studentNames.contains("小明"));

        // ==================== 3. Map ====================
        /*
         * Map 使用 key 和 value 保存資料。
         * 可以透過 key 快速取得對應的 value。
         *
         * Map<String, String>：
         * 第一個 String 是 key 的型別。
         * 第二個 String 是 value 的型別。
         *
         * key 不可以重複，使用相同 key 會更新原本的 value。
         *
         * 可能的使用情境：
         * 1. 學號對應學生姓名。
         * 2. 商品編號對應商品名稱。
         * 3. 國家代碼對應國家名稱。
         *
         * 常用方法：
         * put() 新增或修改資料
         * get() 透過 key 取得 value
         * remove() 透過 key 刪除資料
         * containsKey() 判斷是否包含 key
         * keySet() 取得所有 key
         * size() 取得資料筆數
         * clear() 清空資料
         */
        // Map<String, String> students = new HashMap<String, String>();
        Map<String, String> students = new HashMap<>();

        students.put("S001", "小明");
        students.put("S002", "小華");
        students.put("S003", "小明");
        students.put("S001", "小明1"); // 因為key重複 所以此行不會新增

        System.out.println("================ Map ================");
        System.out.println("students: " + students);

        // 用key 取得 value => 用學號 取得 學生姓名
        System.out.println("S001的學生: " + students.get("S001"));
        System.out.println("S002的學生: " + students.get("S002"));

        System.out.println("students的key(學號)集合: " + students.keySet());
        students.remove("S002");

        for (String studentId : students.keySet()) {
            System.out.println("key: " + studentId);
            // 用key 取得 value
            System.out.println("value: " + students.get(studentId));
        }

        System.out.println("================ Generic ================");
        // ==================== 4. 泛型 Generic ====================
        /*
         * 沒有使用泛型時，可以直接寫：
         * List list = new ArrayList();
         *
         * 因為沒有指定型別，所以集合裡什麼資料都能放。
         * 但是取出資料時，Java 只知道它是 Object。
         * 要使用原本的型別，就必須自己進行強制轉型。
         */

        // 比對使用泛型的寫法
        // List<String> list = new ArrayList<>();

        List list = new ArrayList();
        list.add("apple");
        list.add(100);
        list.add(true);

        System.out.println(list);

        // 要使用原本型別，必須自己強制轉型。
        String fruit = (String) list.get(0);

        Integer number = (Integer) list.get(1); // 正確的轉型接回值
        // String number = (String) list.get(1); // 錯誤的轉型接回值

        Boolean flag = (Boolean) list.get(2);
        System.out.println("================ 未使用泛型 ================");
        System.out.println("fruit: " + fruit);
        System.out.println("number: " + number);
        System.out.println("flag: " + flag);

        /*
         * 為了解決混合型別與強制轉型問題，可以使用泛型。
         * List<String> list = new ArrayList<>();
         * <String> 這個就是泛型
         * 強制定義此List 只能保存String
         * 
         * List<String> 只能保存字串。
         * List<Integer> 只能保存整數。
         * List<Boolean> 只能保存布林值。
         * 
         * 泛型的好處：
         * 1. 編譯時就能阻止錯誤型別加入集合。
         * 2. 取出資料時不需要強制轉型。
         * 
         * 泛型不能直接使用基本型別：
         * 錯誤：List<int>
         * 正確：List<Integer>
         * 
         * 常用基本型別對應：
         * int -> Integer
         * double -> Double
         * boolean -> Boolean
         * char -> Character
         */
        System.out.println("================ 使用泛型 ================");
        List<Integer> numbers = new ArrayList<>();
        numbers.add(100);
        numbers.add(200);
        numbers.add(300);
        System.out.println("List-numbers: " + numbers);

        // ==================== 5. 自建泛型 class ====================
        System.out.println("================ 自建泛型 class：Box<T> ================");
        Box<String> box1 = new Box<>();
        box1.setValue("apple");
        System.out.println("String Box：" + box1.getValue());

        Box<Integer> box2 = new Box<>();
        box2.setValue(100);
        System.out.println("Integer Box：" + box2.getValue());

        Box<Boolean> box3 = new Box<>();
        box3.setValue(true);
        System.out.println("Boolean Box：" + box3.getValue());

        // ==================== 6. 自建多個泛型參數 class====================
        /*
         * 泛型也可以一次使用多個型別參數。
         *
         * Pair<K, V>
         *
         * K 代表 key 的型別。
         * V 代表 value 的型別。
         *
         * 這個概念很像 Map<K, V>。
         */
        System.out.println("================ 自建泛型 class：Pair<K, V> ================");
        Pair<String, Integer> pair1 = new Pair<>();
        pair1.setKey("S001");
        pair1.setValue(100);
        System.out.println("學生：" + pair1.getKey() + " 分數：" + pair1.getValue());

        Pair<String, String> pair2 = new Pair<>();
        pair2.setKey("S001");
        pair2.setValue("小明");
        System.out.println("學號：" + pair2.getKey() + " 姓名：" + pair2.getValue());
    }
}

// 自建泛型 class
// <Ｔ> 表示傳入的型別為任意型別
// Box<String> 裡面的 T 就是 String。
// Box<Integer> 裡面的 T 就是 Integer。
// Box<Boolean> 裡面的 T 就是 Boolean。
class Box<T> {
    private T value;

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }
}

// 自建泛型類別 Pair<K, V>
// K 代表 key 的型別。
// V 代表 value 的型別。
// Pair<String, Integer> K = String, V = Integer
// Pair<Integer, Integer> K = Integer, V = Integer
// Pair<String, String> K = String, V = String
class Pair<K, V> {
    private K key;
    private V value;

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }
}