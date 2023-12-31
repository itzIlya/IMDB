import Exceptions.GuestAccessRefusedException;

import java.util.ArrayList;
import java.util.UUID;

public class TriviaQuoteGoof {
    Member writer;
    TriviaQuoteGoofType triviaQuoteGoofType;
    String Text;
    ArrayList<Thumb> thumbs;
    UUID uuid;

    public TriviaQuoteGoof(Member writer , TriviaQuoteGoofType triviaQuoteGoofType, String text) throws GuestAccessRefusedException {
        if (writer.getAccessLevel().equals(AccessLevel.GUEST)){
            throw new GuestAccessRefusedException();
        }
        this.writer = writer;
        this.triviaQuoteGoofType = triviaQuoteGoofType;
        Text = text;
        this.thumbs = new ArrayList<>();
        this.uuid=UUID.randomUUID();
    }

    public TriviaQuoteGoofType getTriviaQuoteGoofType() {
        return triviaQuoteGoofType;
    }

    public void setTriviaQuoteGoofType(TriviaQuoteGoofType triviaQuoteGoofType) {
        this.triviaQuoteGoofType = triviaQuoteGoofType;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public ArrayList<Thumb> getThumbs() {
        return thumbs;
    }

    public void setThumbs(ArrayList<Thumb> thumbs) {
        this.thumbs = thumbs;
    }
}
