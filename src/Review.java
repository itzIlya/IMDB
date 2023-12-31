import Exceptions.DuplicateReviewOrRatingInMovieException;
import Exceptions.GuestAccessRefusedException;

import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.function.DoubleUnaryOperator;

public class Review extends Rating implements  ReportReceiver{
    private String title;
    private boolean spoilerAlert;
    private String body;
    private ArrayList<Thumb> thumbs;

    public Review(Rate rate, Member rater,String title, boolean spoilerAlert, String body) {
        super( rate,  rater);
        this.title = title;
        this.spoilerAlert = spoilerAlert;
        this.body = body;
        this.thumbs = new ArrayList<Thumb>();
    }

    public boolean addNewThumb(Thumb thumb) throws DuplicateReviewOrRatingInMovieException {
        for(Thumb t: thumbs){
            if (thumb.getThumber().equals(t.getThumber())){
                throw new DuplicateReviewOrRatingInMovieException();
            }
        }
        if (thumb!=null){
            thumbs.add(thumb);
            return true;
        }
        return false;

    }

    public String getTitle() {
        return title;
    }

    public boolean isSpoilerAlert() {
        return spoilerAlert;
    }

    public String getBody() {
        return body;
    }

    public ArrayList<Thumb> getThumbs() {
        return thumbs;
    }
    public int getThumbsUp(){
        int count=0;
        for (Thumb t : thumbs){
            if(t.thumbDir == ThumbDir.UP){
                count++;
            }
        }
        return count;
    }
    public int getThumbsDown(){
        int count=0;
        for (Thumb t : thumbs){
            if(t.thumbDir == ThumbDir.DOWN){
                count++;
            }
        }
        return count;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setSpoilerAlert(boolean spoilerAlert) {
        this.spoilerAlert = spoilerAlert;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setThumbs(ArrayList<Thumb> thumbs) {
        this.thumbs = thumbs;
    }



    @Override
    public Report reportThis(Object flag, Member sender, String text) {
        ReviewReport report = new ReviewReport(sender,this,text,(ReviewReportFlag)flag);
        return new ReviewReport(sender,this,text,(ReviewReportFlag)flag);
    }


}
