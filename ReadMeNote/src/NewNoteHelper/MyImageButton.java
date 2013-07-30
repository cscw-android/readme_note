package NewNoteHelper;

import android.widget.LinearLayout;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

public class MyImageButton extends LinearLayout {
	public ImageView mImage;
	public TextView mText;

	public MyImageButton(Context context) {
		super(context);
		mImage = new ImageView(context);
		mImage.setPadding(0, 0, 0, 0);
		mText = new TextView(context);
		mText.setGravity(android.view.Gravity.CENTER_HORIZONTAL);
		mText.setPadding(0, 0, 0, 0);
		setClickable(true);
		setFocusable(true);
		setBackgroundResource(android.R.drawable.btn_default);
		setOrientation(LinearLayout.VERTICAL);
		addView(mImage);
		addView(mText);
	}
}