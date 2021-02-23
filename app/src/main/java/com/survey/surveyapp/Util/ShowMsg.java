package com.survey.surveyapp.Util;


import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.survey.surveyapp.R;
import com.survey.surveyapp.SplashActivity;


public class ShowMsg {

		static String TAG = "ShowMsg";

	static ProgressBar progressBar = null;
    public static void startProgressBar(Activity activity) {
		ViewGroup layout = (ViewGroup) activity.findViewById(android.R.id.content).getRootView();
		RelativeLayout relativeLayout = new RelativeLayout(activity);
		progressBar = new ProgressBar(activity,null,android.R.attr.progressBarStyleLarge);
		progressBar.setIndeterminate(true);
		progressBar.setVisibility(View.VISIBLE);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(150,150);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		relativeLayout.addView(progressBar,params);
		layout.addView(relativeLayout);
		layout.setVisibility(View.VISIBLE);
    }

	public static void stopProgressBar(Activity activity) {
		if(progressBar != null){
			progressBar.setVisibility(View.GONE);
		}
	}


	static ProgressDialog progressDialog = null;
	public static void startProgressDialog(Activity activity) {
		View rootView = activity.getWindow().getDecorView();
		progressDialog = new ProgressDialog(rootView.getContext(), ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
		progressDialog.setMessage("Loading...");
		progressDialog.setProgressStyle(progressDialog.THEME_HOLO_DARK);
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(false);
		progressDialog.show();
	}

	public static void stopProgressDialog(Activity activity) {
		if(progressDialog != null){
			progressDialog.dismiss();
		}
	}

	public void createDialogNewTitleANDDescription(final Activity splash, final String title, final String msg) {
		Builder builder = new Builder(splash);
		builder.setTitle(title);
		builder.setMessage(Html.fromHtml(msg))
				.setCancelable(false)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.dismiss();
					}
				});
//				.setNegativeButton("No", new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int id) {
//						dialog.cancel();
//					}
//				});
		AlertDialog alert = builder.create();
		alert.show();
	}





	public static void showSessionDialog(final Activity mcNavigation) {
		Builder builder = new Builder(mcNavigation);
		builder.setTitle(mcNavigation.getString(R.string.app_name));
		builder.setMessage("Your current session has expired. Please login again to continue.")
				.setCancelable(false)
				.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.dismiss();
						SavePref pref = new SavePref();

						pref.SavePref(mcNavigation);
						pref.clear();

						//onLogoutClick(mcNavigation);
					}
				});
		AlertDialog alert = builder.create();
		alert.setCancelable(false);
		alert.show();
	}










	public void createDialogOpenClass(final Activity splash, final String string) {
		// TODO Auto-generated method stub

		InputMethodManager imm = (InputMethodManager) splash.getSystemService(Activity.INPUT_METHOD_SERVICE);
		View view = splash.getCurrentFocus();
		if (view == null) {
			view = new View(splash);
		}
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


		splash.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
//				 Builder dialog = new Builder(splash, AlertDialog.THEME_HOLO_LIGHT);
//				 dialog.setTitle("Brain At Work");
//				 dialog.setMessage(string);
//				 dialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// TODO Auto-generated method stub
//						dialog.dismiss();
//					}
//				});
//				 dialog.show();


				Builder builder = new Builder(splash);
				builder.setTitle(splash.getString(R.string.app_name));
				builder.setMessage(""+string)
						.setCancelable(false)
						.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.dismiss();
								Intent i = new Intent(splash, SplashActivity.class);
								i.putExtra("keyPosition" , 0);
								splash.startActivity(i);
								splash.finishAffinity();
								splash.finish();
							}
						});
//				.setNegativeButton("No", new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int id) {
//						dialog.cancel();
//					}
//				});
				AlertDialog alert = builder.create();
				alert.show();
			}
		});
	}






//	public Dialog createCustomProgressbarDialog(Activity mainActivity) {
//		final Dialog dialog = new Dialog(mainActivity);
//		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//		dialog.setContentView(R.layout.progress_bar_dialog_1);
//		dialog.show();
//		return dialog;
//	}


	TextView textView = null;
	public void showErrorTextAll(Activity activity, String message) {
		InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
		View view = activity.getCurrentFocus();
		if (view == null) {
			view = new View(activity);
		}
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

		//Snackbar.make(mainActivity.getWindow().getDecorView().getRootView(), message, Snackbar.LENGTH_LONG).show();

		ViewGroup layout = (ViewGroup) activity.findViewById(android.R.id.content).getRootView();
		RelativeLayout relativeLayout = new RelativeLayout(activity);
		textView = new TextView(activity);
		textView.setText(message);
		textView.setVisibility(View.VISIBLE);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

		//RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.list_spinner_layout);
		//RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.list_spinner_layout);
		//relativeLayout.setPadding(10, 100, 10, 0);

		//params.setPadding(50,50,50,50);
		params.topMargin = 100;
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		relativeLayout.addView(textView,params);
		layout.addView(relativeLayout);
		layout.setVisibility(View.VISIBLE);

	}


	public void hideErrorTextAll(){
		if(textView != null){
			textView.setVisibility(View.GONE);
		}

	}




	public void createSnackbar(Activity mainActivity, String message) {
		InputMethodManager imm = (InputMethodManager) mainActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
		View view = mainActivity.getCurrentFocus();
		if (view == null) {
			view = new View(mainActivity);
		}
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

		Snackbar.make(mainActivity.getWindow().getDecorView().getRootView(), message, Snackbar.LENGTH_LONG).show();
		//Snackbar.make(mainActivity.getWindow().getDecorView().getRootView(), message, Snackbar.LENGTH_LONG).show();
	}




	public void createSnackbarWithButton(final Activity mainActivity, String message) {
		InputMethodManager imm = (InputMethodManager) mainActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
		View view = mainActivity.getCurrentFocus();
		if (view == null) {
			view = new View(mainActivity);
		}
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

		Snackbar.make(mainActivity.getWindow().getDecorView().getRootView(), message, Snackbar.LENGTH_LONG)
				.setAction("CLOSE", new View.OnClickListener() {
					@Override
					public void onClick(View view) {

					}
				})
				.setActionTextColor(mainActivity.getResources().getColor(android.R.color.holo_red_light ))
				.show();
	}



	public void createToast(final Activity splash, final String string) {
		// TODO Auto-generated method stub
		InputMethodManager imm = (InputMethodManager) splash.getSystemService(Activity.INPUT_METHOD_SERVICE);
		View view = splash.getCurrentFocus();
		if (view == null) {
			view = new View(splash);
		}
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		splash.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Toast.makeText(splash, string, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	
	

	public void createDialog(final Activity splash, final String string) {
		// TODO Auto-generated method stub

		InputMethodManager imm = (InputMethodManager) splash.getSystemService(Activity.INPUT_METHOD_SERVICE);
		View view = splash.getCurrentFocus();
		if (view == null) {
			view = new View(splash);
		}
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


		splash.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
//				 Builder dialog = new Builder(splash, AlertDialog.THEME_HOLO_LIGHT);
//				 dialog.setTitle("Brain At Work");
//				 dialog.setMessage(string);
//				 dialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// TODO Auto-generated method stub
//						dialog.dismiss();
//					}
//				});
//				 dialog.show();


				Builder builder = new Builder(splash);
				builder.setTitle(splash.getString(R.string.app_name));
				builder.setMessage(""+string)
						.setCancelable(false)
						.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.dismiss();
							}
						});
//				.setNegativeButton("No", new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int id) {
//						dialog.cancel();
//					}
//				});
				AlertDialog alert = builder.create();
				alert.show();
			}
		});
	}





	public void createDialogNew(final Activity splash, final String string) {
		Builder builder = new Builder(splash);
		builder.setTitle(splash.getString(R.string.app_name));
		builder.setMessage(""+string)
				.setCancelable(false)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.dismiss();
					}
				});
//				.setNegativeButton("No", new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int id) {
//						dialog.cancel();
//					}
//				});
		AlertDialog alert = builder.create();
		alert.show();
	}


//	public void createShortDialog(final Activity splash, final String string) {
//		// TODO Auto-generated method stub
//		splash.runOnUiThread(new Runnable() {
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
////				final Builder dialog = new Builder(splash, AlertDialog.THEME_HOLO_LIGHT);
//				final Dialog dialog = new Dialog(splash);
//				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//				dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//				dialog.setContentView(R.layout.custom_dialog_design);
//				TextView txt = (TextView) dialog.findViewById(R.id.textView3);
//				txt.setText(string);
//
//
//
//// 	dialog.setMessage(string);
//
////				dialog.setMessage(string);
////				dialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
////
////					@Override
////					public void onClick(DialogInterface dialog, int which) {
////						// TODO Auto-generated method stub
////						dialog.dismiss();
////					}
////				});
//
//				dialog.show();
//
//			}
//		});
//
//	}





	public void createDialogCancelable(final Activity splash, final String string) {
		// TODO Auto-generated method stub
		splash.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				 Builder dialog = new Builder(splash, AlertDialog.THEME_HOLO_LIGHT);
				 dialog.setTitle(splash.getString(R.string.app_name));
				 dialog.setMessage(string);
				 dialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
				
				 dialog.setCancelable(false);
				 dialog.show();
				
			}
		});
		
	}
	
	
	

	public void createDialogForgotPassword(final Activity splash, final String string) {
		// TODO Auto-generated method stub
		splash.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				 Builder dialog = new Builder(splash, AlertDialog.THEME_HOLO_LIGHT);
				 dialog.setTitle(splash.getString(R.string.app_name));
				 dialog.setMessage(string);
				 dialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
						splash.finish();
					}
				});
				 dialog.show();
			}
		});
		
	}











//
//	public void createDialogNewTitleColor(final Activity splash, final String string) {
//		// TODO Auto-generated method stub
//		splash.runOnUiThread(new Runnable() {
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(splash, R.style.MaterialNavyTheme);
//				builder.setTitle(splash.getString(R.string.app_name));
//				builder.setMessage(string);
////				final EditText input = new EditText(splash);
////				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
////						LinearLayout.LayoutParams.MATCH_PARENT,
////						LinearLayout.LayoutParams.MATCH_PARENT);
////				input.setLayoutParams(lp);
////				builder.setView(input);
//				builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// TODO Auto-generated method stub
//						dialog.dismiss();
////						splash.finish();
//					}
//				});
//				builder.show();
//			}
//		});
//	}
//


//	public void showLoginCustomDialog(Dialog dialog) {
//		// TODO Auto-generated method stub
//		
//		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//
//		dialog.setContentView(R.layout.custom_dialog_progressbar);
//		dialog.setTitle("Pakly Partner!");
//		
//		
//		//dialog.show();
//		
//		
//	}
	
	
//
//	public void showLoginCustomMessageDialog(final Activity splash, String string) {
//		// TODO Auto-generated method stub
//		final Dialog dialog = new Dialog(splash, R.style.translucentDialog);
//
//		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
////		splash.getWindow().setBackgroundDrawable(
////	            new ColorDrawable(android.graphics.Color.TRANSPARENT));
//		dialog.setContentView(R.layout.custom_message_dialog);
////		dialog.setTitle("Forgot Password!");
//
//		TextView textView = (TextView)dialog.findViewById(R.id.textView1654656);
////		textView.setText(string);
//		Button button = (Button)dialog.findViewById(R.id.button111);
//
//		button.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				dialog.dismiss();
//			}
//		});
//
//
//		dialog.show();
//
//
//	}
//
//
//
	
	
//
//
//	public void showLoginCustomMessageDialog2(final Activity splash, String string) {
//		// TODO Auto-generated method stub
//		final Dialog dialog = new Dialog(splash, R.style.translucentDialog);
//
//		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
////		splash.getWindow().setBackgroundDrawable(
////	            new ColorDrawable(android.graphics.Color.TRANSPARENT));
//		dialog.setContentView(R.layout.custom_message_dialog_2);
////		dialog.setTitle("Forgot Password!");
//
//		TextView textView = (TextView)dialog.findViewById(R.id.textView1654656);
//		textView.setText(string);
//		Button buttonOk = (Button)dialog.findViewById(R.id.button111);
//		Button buttonCancel = (Button)dialog.findViewById(R.id.button222);
//		buttonOk.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				dialog.dismiss();
//			}
//		});
//
//		buttonCancel.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				dialog.dismiss();
//			}
//		});
//
//
//		dialog.show();
//
//
//	}
//
//
//
	
	
	public void turnGPSOn(Activity activity){
	    String provider = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

	    if(!provider.contains("gps")){ //if gps is disabled
	        final Intent poke = new Intent();
	        poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider"); 
	        poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
	        poke.setData(Uri.parse("3")); 
	        activity.sendBroadcast(poke);
	    }
	}

	public void turnGPSOff(Activity activity){
	    String provider = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

	    if(provider.contains("gps")){ //if gps is enabled
	        final Intent poke = new Intent();
	        poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
	        poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
	        poke.setData(Uri.parse("3")); 
	        activity.sendBroadcast(poke);
	    }
	}


	

//
//	@SuppressLint("NewApi")
//    public ProgressDialog createProgressDialog(Activity loginScreen) {
//		// TODO Auto-generated method stub
//		final ProgressDialog dialog = new ProgressDialog(loginScreen, ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
////		dialog.setProgressStyle(dialog.THEME_HOLO_DARK);
////		dialog.setProgressStyle(dialog.THEME_HOLO_DARK);
//		dialog.setMessage("Loading...");
////		dialog.show();
////        dialog.setIndeterminate(true);
//        //here is the trick:
//        dialog.setIndeterminateDrawable(loginScreen.getResources().getDrawable(R.drawable.logoapp, null));
//        return dialog;
//	}


	
	public ProgressDialog createProgressDialogWithMsg(Activity loginScreen,String msg) {
		// TODO Auto-generated method stub
		final ProgressDialog dialog = new ProgressDialog(loginScreen, ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
//		dialog.setProgressStyle(dialog.THEME_HOLO_DARK);
		dialog.setMessage(""+msg);
		dialog.show();
		
		return dialog;
	}





//	public Dialog createCustomProgressbarDialog(Activity mainActivity) {
//		final Dialog dialog = new Dialog(mainActivity);
//		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//		dialog.setContentView(R.layout.progress_bar_dialog);
//
//		ImageView imageView = (ImageView) dialog.findViewById(R.id.progressBar2);
//		GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);
//		Glide.with(mainActivity).load(R.drawable.ajax_loader).into(imageViewTarget);
//
////		ProgressBar progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar2);
////		progressBar.setScaleX(1f);
////		progressBar.setScaleY(1f);
//		//progressBar.strokeWidth(8f);
//
//
//		dialog.show();
//		return dialog;
//	}








}
