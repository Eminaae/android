package ba.bitcamp.geoguizapp.android.geoquizapp;

/**
 * Created by USER on 24.10.2015.
 */
public class Question {
    private int mTextResId;//question text, it is int because variable will hold resoutce Id of string resource for question
    private boolean mAnswerTrue; //question answer

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
