package com.archtypestudios.inforail.widgets;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class MarqueeLayout extends ViewGroup {

	private static final int VERTICAL_SPACING = 10;
	private static final int HORIZONTAL_SPACING = 10;
	private static final String TAG = MarqueeLayout.class.getName();
	private int line_width;
	private List<View> views; 
	private Timer timer;

	private int scrollX = 0;

	public MarqueeLayout(Context context)
	{
	    super(context);
	}
	private Handler handler;
	private int index = 0;
	private int childCount;
	public MarqueeLayout(Context context, AttributeSet attrs)
	{
	    super(context, attrs);
	    handler = new Handler();
	    timer = new Timer();
	    timer.schedule(new TimerTask() {
		
	        @Override
	        public void run() {
	            handler.post(new Runnable() {
	                @Override
	                public void run() {
	                    requestLayout();
	                }
	            });
	        }
	    }, 1000, 200);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
	    assert (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.UNSPECIFIED);
	    if(views == null) {
	        views = new ArrayList<View>();
	        childCount = getChildCount();
	        for(int i = 0; i < childCount; i++) {
	            views.add(getChildAt(i));
	        }

	    }
	    final int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
	    int height = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();
	    final int count = getChildCount();
	    int line_height = 0;

	    int xpos = getPaddingLeft();
	    int ypos = getPaddingTop();

	    int childHeightMeasureSpec;
	    if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST)
	    {
	        childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
	    }
	    else
	    {
	        childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
	    }

	    for (int i = 0; i < count; i++)
	    {
	        final View child = getChildAt(i);
	        if (child.getVisibility() != GONE)
	        {
	            child.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST), childHeightMeasureSpec);
	            final int childw = child.getMeasuredWidth();
	            line_height = Math.max(line_height, child.getMeasuredHeight() + VERTICAL_SPACING);

	            xpos += childw + HORIZONTAL_SPACING;
	        }
	    }
	    this.line_width = xpos;

	    setMeasuredDimension(width, height);
	}

	@Override
	protected ViewGroup.LayoutParams generateDefaultLayoutParams()
	{
	    return new LayoutParams(1, 1); 
	}

	@Override
	protected boolean checkLayoutParams(ViewGroup.LayoutParams p)
	{
	    if (p instanceof LayoutParams)
	    {
	        return true;
	    }
	    return false;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
	    Log.d(TAG, "onLayout called");
	    int count = getChildCount();
	    final int width = r - l;

	    scrollX -= 20;
	    if(line_width + scrollX < 0) {
	        scrollX = 0;
	    }
	    int i = 0;
	    while(count > 0) {
	        View c = getChildAt(i);
	        if(c == null) {
	            break;
	        }
	        int w = c.getMeasuredWidth();
	        Log.d(TAG, "scrollX : " + scrollX + " width : " + w);
	        if(scrollX < -w) {
	            this.removeViewAt(0);
	            scrollX += w;
	        } else {
	            break;
	        }
	        i++;
	        count--;
	    }
	    count = getChildCount();
	    int xpos = getPaddingLeft() + scrollX;
	    int ypos = getPaddingTop();
	    for (i = 0; i < count; i++)
	    {
	        final View child = getChildAt(i);
	        if (child.getVisibility() != GONE)
	        {
	            final int childw = child.getMeasuredWidth();
	            final int childh = child.getMeasuredHeight();
	            child.layout(xpos, ypos, xpos + childw, ypos + childh);
	            xpos += childw + HORIZONTAL_SPACING;
	        }
	    }
	    while(xpos < getWidth()) {
	        View v = views.get(index % childCount);
	        addView(v);
	        xpos += v.getMeasuredWidth();
	        index++;
	    }
	}
}
