import java.sql.Timestamp;

public class Thumb {
    ThumbDir thumbDir;
    Member thumber;
    // Review review;
    Timestamp time;

    public ThumbDir getThumbDir() {
        return thumbDir;
    }

    public Member getThumber() {
        return thumber;
    }

    public Timestamp getTime() {
        return time;
    }

    public Thumb(ThumbDir thumbDir, Member thumber) {
        this.thumbDir = thumbDir;
        this.thumber = thumber;
        this.time = new Timestamp(System.currentTimeMillis());
    }
}
