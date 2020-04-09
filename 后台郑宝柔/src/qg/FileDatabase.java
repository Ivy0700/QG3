package Javalearning;

public class FileDatabase {
    private int id;
    private String path;
    private int insertLine;
    private String data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getInsertLine() {
        return insertLine;
    }

    public void setInsertLine(int insertLine) {
        this.insertLine = insertLine;
    }

    public String getData() {
        return data;
    }

    public void setData(String insert) {
        this.data = insert;
    }
}
