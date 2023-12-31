import Exceptions.InvalidRatingException;

import java.sql.Timestamp;

public class Rating implements Comparable{
    Rate rate;
    Member rater;
    // Movie ratee;
    Timestamp time;

    public Rating(Rate rate, Member rater) {
        this.rate = rate;
        this.rater = rater;
        this.time = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public int compareTo(Object rating2) {
        Rating rating = (Rating)rating2;
        return rateToNum(this.rate) - rateToNum(rating.rate);
    }

    public static Rate numToRate(int num) throws InvalidRatingException{
        switch (num){
            case 1:
                return Rate.ONE;
            case 2:
                return Rate.TWO;
            case 3:
                return Rate.THREE;
            case 4:
                return Rate.FOUR;
            case 5:
                return Rate.FIVE;
            case 6:
                return Rate.SIX;
            case 7:
                return Rate.SEVEN;
            case 8:
                return Rate.EIGHT;
            case 9:
                return Rate.NINE;
            case 10:
                return Rate.TEN;
            default:
                throw new InvalidRatingException();
        }
    }
    public static int rateToNum(Rate rate){
        switch (rate){
            case ONE -> {
                return 1;
            }
            case TWO -> {
                return 2;
            }
            case THREE -> {
                return 3;
            }
            case FOUR -> {
                return 4;
            }
            case FIVE -> {
                return 5;
            }
            case SIX -> {
                return 6;
            }
            case SEVEN -> {
                return 7;
            }
            case EIGHT -> {
                return 8;
            }
            case NINE -> {
                return 9;
            }
            case TEN -> {
                return 10;
            }
            default -> {
                return 0;
            }

        }
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public Member getRater() {
        return rater;
    }

    public void setRater(Member rater) {
        this.rater = rater;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
