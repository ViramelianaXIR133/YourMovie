package id.sch.smktelkom_mlg.privateassignment.xirpl133.yourmovie;

/**
 * Created by Vira Meliana on 5/13/2017.
 */

public class Main3ListItem {
    private String imageUrl;
    private String head;
    private String desc;

    public Main3ListItem(String imageUrl, String head, String desc) {
        this.imageUrl = imageUrl;
        this.head = head;
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }
}

