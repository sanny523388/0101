package senior.borrowGiveBackSystem.librarySystem;

public class ProgrammingBook extends Book {
    private String language;

    public ProgrammingBook(String number, String title, String author, boolean available, String borrowUser,
            String language) {
        super(number, title, author, available, borrowUser);
        this.language = language;
    }

    @Override
    public String getType() {
        return "P";
    }

    @Override
    public String getExtraInfo() {
        return this.language;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\n"
                + "類型：程式書\n"
                + "語言：" + getExtraInfo();
    }
}
