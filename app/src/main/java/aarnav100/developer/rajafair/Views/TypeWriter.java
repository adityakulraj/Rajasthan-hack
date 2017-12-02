package aarnav100.developer.rajafair.Views;

/**
 * Created by aarnavjindal on 02/12/17.
 */

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

public class TypeWriter extends android.support.v7.widget.AppCompatTextView {

    private String[] inputText;
    private long mDelay = 100;
    private int mIndex = 0;
    private int sIndex = 0;
    private Context context;

    public TypeWriter(Context context) {
        super(context);
        this.context = context;
        mHandler.postDelayed(characterAdder,mDelay);
    }

    public TypeWriter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mHandler.postDelayed(characterAdder,mDelay);
    }

    private Handler mHandler = new Handler();
    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            if(sIndex!=inputText.length) {
                setText(inputText[sIndex].substring(0, mIndex++));
                if (mIndex <= inputText[sIndex].length())
                    mHandler.postDelayed(characterAdder, mDelay);
                else {
                    sIndex++;
                    mIndex = 0;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mHandler.postDelayed(characterAdder, mDelay);
                        }
                    },1000);
                }
            }
            else{
                setVisibility(GONE);
            }
        }
    };

    public void animateText(String[] input){
        inputText = input;
        mIndex = 0;
        sIndex = 0;

        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder,mDelay);
    }

    public void setDelay(long millis){
        mDelay = millis;
    }
}
