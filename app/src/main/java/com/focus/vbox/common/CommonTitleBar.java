package com.focus.vbox.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.focus.vbox.R;
import com.focus.vbox.utils.UIUtils;

public class CommonTitleBar extends LinearLayout {

	private View mBackBtn;
	private ImageView mImgBack;
	private TextView mTvTitle;
	private TextView mTvSetting;
	private ImageView mImgSetting;
	private ImageView mRedPoint;
//	private View mRoot;
	private View mShadow;
	private View mStatusBar;

	private String mTitleText;
	private SETTING_TYPE mSettingType = SETTING_TYPE.SETTING_TYPE_TEXT;
	private LinearLayout mTitleArea;

	public CommonTitleBar(Context context) {
		super(context);
		init();
	}

	public CommonTitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		mTitleText = UIUtils.getTextFromAttrs(context, attrs);
		init();
	}

	private void init() {
		final Context context = getContext();
		inflate(context, R.layout.common_title_bar, this);
		mTitleArea = (LinearLayout) findViewById(R.id.common_ll_middle);
		mBackBtn = findViewById(R.id.common_ll_left);
		mImgBack = (ImageView) findViewById(R.id.common_img_back);
		mTvTitle = (TextView) findViewById(R.id.common_tv_title);
		mTvSetting = (TextView) findViewById(R.id.common_tv_setting);
		mImgSetting = (ImageView) findViewById(R.id.common_img_setting);
		mRedPoint = (ImageView) findViewById(R.id.common_red_point);
//		mRoot = findViewById(R.id.common_titlebar_root);
		mShadow = findViewById(R.id.common_title_bar_shadow);
		mStatusBar = findViewById(R.id.common_titlebar_status_bar);
//        mRoot.setBackgroundResource(R.drawable.navigation_top_bg);
//        if (isInEditMode()) {
//            if (mRoot != null) {
//                mRoot.setBackgroundColor(0xF5F5F5);
//            }
//        } else {
//            mRoot.setBackgroundColor(getResources().getColor(R.color.common_grey_color1));
//        }
		if (!TextUtils.isEmpty(mTitleText)) {
			setTitle(mTitleText);
		}
		if (context instanceof Activity) {
			setOnBackListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					((Activity) context).finish();
				}
			});
		}
		initImmersion();
	}

	public void showRedDot(){
		findViewById(R.id.iv_red_dot).setVisibility(View.VISIBLE);
	}

	public void hideRedDot(){
		findViewById(R.id.iv_red_dot).setVisibility(View.GONE);
	}
	private void initImmersion() {
		if (android.os.Build.VERSION.SDK_INT > 18) {
//			LayoutParams lbParams = (LayoutParams) mStatusBar.getLayoutParams();
//			lbParams.height = AppEnv.STATUS_BAR_HEIGHT;
//			mStatusBar.setLayoutParams(lbParams);
		}
	}

	public TextView getTitleView() {
		return mTvTitle;
	}

	/**
	 * 设置标题居中（默认跟随左边返回箭头）
	 */
	public void setTitleHorizontalCenter() {
		mTitleArea.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.CENTER_VERTICAL);
	}

	/**
	 * 设置背景为透明
	 */
	public void setBackgroundTransparent() {
//        mRoot.setBackgroundColor(0);
		mShadow.setVisibility(View.VISIBLE);
	}

	public void setRedPointVisibility(int visibility) {
		mRedPoint.setVisibility(visibility);
	}

	private void setSettingType(SETTING_TYPE type) {
		mSettingType = type;
	}

	public void setRedPointResource(int resId) {
		mRedPoint.setImageResource(resId);
	}

	public void setRedPointDrawable(Drawable drawable) {
		mRedPoint.setImageDrawable(drawable);
	}

	public void setMiddleView(View v) {
		setView(R.id.common_ll_middle, v);
	}

	public void setMiddleView(int layoutResId) {
		setView(R.id.common_ll_middle, layoutResId);
	}

	public void setLeftView(View v) {
		setView(R.id.common_ll_left, v);
	}

	public void setLeftView(int layoutResId) {
		setView(R.id.common_ll_left, layoutResId);
	}

	public void setRightView(View v) {
		setView(R.id.common_ll_right, v);
	}

	public void setRightView(int layoutResId) {
		setView(R.id.common_ll_right, layoutResId);
	}

	private void setView(int rootId, View v) {
		ViewGroup ll = (ViewGroup) findViewById(rootId);
		ll.removeAllViews();
		ll.addView(v);
	}

	private void setView(int rootId, int layoutId) {
		ViewGroup ll = (ViewGroup) findViewById(rootId);
		ll.removeAllViews();
		inflate(getContext(), layoutId, ll);
	}

	public ImageView getBackImageView() {
		return mImgBack;
	}

	public View getRightButton() {
		switch (mSettingType) {
			case SETTING_TYPE_TEXT:
				return mTvSetting;
			case SETTING_TYPE_IMG:
				return mImgSetting;
		}
		return null;
	}

	public void setTitle(CharSequence title) {
		if (mTvTitle != null) {
			mTvTitle.setText(title);
		}
	}

	public void setTitle(int title) {
		mTvTitle.setText(title);
	}

	public void setSettingVisible(boolean visible) {
		mImgSetting.setVisibility(View.GONE);
		mTvSetting.setVisibility(View.GONE);
		if (visible) {
			switch (mSettingType) {
				case SETTING_TYPE_TEXT:
					mTvSetting.setVisibility(View.VISIBLE);
					break;
				case SETTING_TYPE_IMG:
					mImgSetting.setVisibility(View.VISIBLE);
					break;
			}
		}
	}

	public void setBackVisible(boolean visible) {
		mImgBack.setVisibility(visible ? View.VISIBLE : View.GONE);
	}

	public void setSettingTxt(int resId) {
		setSettingType(SETTING_TYPE.SETTING_TYPE_TEXT);
		setSettingVisible(true);
		mTvSetting.setText(resId);
	}

	public void setSettingTxt(CharSequence title) {
		setSettingType(SETTING_TYPE.SETTING_TYPE_TEXT);
		setSettingVisible(true);
		mTvSetting.setText(title);
	}

	public void setSettingImg(int resId) {
		setSettingType(SETTING_TYPE.SETTING_TYPE_IMG);
		setSettingVisible(true);
		mImgSetting.setImageResource(resId);
	}

	public void setSettingImg(Drawable drawable) {
		setSettingType(SETTING_TYPE.SETTING_TYPE_IMG);
		setSettingVisible(true);
		mImgSetting.setImageDrawable(drawable);
	}

	/**
	 * 设置左侧按钮图片(默认是返回)
	 * @param resId
     */
	public void setBackImg(int resId) {
		mImgBack.setImageResource(resId);
	}

	public void setOnBackListener(OnClickListener l) {
//		mImgBack.setOnClickListener(l);
		mBackBtn.setOnClickListener(l);  //扩大点击范围
	}

	public void setOnSettingListener(OnClickListener l) {
		switch (mSettingType) {
			case SETTING_TYPE_TEXT:
				mTvSetting.setOnClickListener(l);
				break;
			case SETTING_TYPE_IMG:
				mImgSetting.setOnClickListener(l);
				break;
		}
	}

	public void setOnButtonListener(OnClickListener l) {
		setOnBackListener(l);
		setOnSettingListener(l);
	}
	public void setOnMoreImgListener(OnClickListener l) {
		setOnBackListener(l);
		mTvSetting.setVisibility(View.GONE);
		mImgSetting.setVisibility(View.VISIBLE);
		mImgSetting.setOnClickListener(l);
	}

	public void setOnDoneTxTListener(OnClickListener l) {
		setOnBackListener(l);
		mTvSetting.setVisibility(View.VISIBLE);
		mImgSetting.setVisibility(View.GONE);
		mTvSetting.setOnClickListener(l);
	}

	public int getLeftButtonId() {
		return getBackImageView().getId();
	}

	public int getRightButtonId() {
		View v = getRightButton();
		return v != null ? v.getId() : 0;
	}

	private enum SETTING_TYPE {
		/**
		 * 有文字的设置按钮
		 */
		SETTING_TYPE_TEXT,
		/**
		 * 图标设置按钮
		 */
		SETTING_TYPE_IMG
	}

	/**
	 * 设置titleBar的背景色
	 * @param color
     */
	public void setBackgroundColorOfTitleBar(int color) {
		findViewById(R.id.common_titlebar_status_bar).setBackgroundColor(color);
		findViewById(R.id.common_content).setBackgroundColor(color);
	}
}
