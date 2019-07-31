package com.opinionbureau

import android.app.Activity
import android.app.ActivityManager
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import io.github.inflationx.viewpump.ViewPumpContextWrapper


/**
 * Created by day on 07/04/19.
 */

class BaseActivity : AppCompatActivity() {
    internal var previously_selected_layout = 0
    private var progressDialog: ProgressDialog? = null
    private var currentToast: Toast? = null


    val isOnline: Boolean
        get() {
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);*/
        val DEFAULT_MESSAGE = "Please wait..."
        progressDialog = ProgressDialog(this, R.style.MyThemeDD)
        //progressDialog.setMessage(DEFAULT_MESSAGE);

        //hideSoftKeyboard();
    }


    fun isAppRunning(context: Context, packageName: String): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val procInfos = activityManager.runningAppProcesses
        if (procInfos != null) {
            for (processInfo in procInfos) {
                if (processInfo.processName == packageName) {
                    return true
                }
            }
        }
        return false
    }

    @JvmOverloads
    fun showProgress(message: String, isCancelable: Boolean = false) {
        if (!isFinishing) {
            //progressDialog.setMessage("");
            progressDialog!!.isIndeterminate = true
            //here is the trick:
            //progressDialog.setIndeterminateDrawable(getResources().getDrawable(R.drawable.spin, null));
            progressDialog!!.setCancelable(isCancelable)
            progressDialog!!.show()
        }

    }

    fun showProgressDialog(message: String) {
        showProgress(message)
    }

    fun dismissProgress() {
        try {
            if (progressDialog != null) {
                if (progressDialog!!.isShowing)
                    progressDialog!!.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun setView(view: View) {
        (view.parent as View).isSelected = true
        val v1 = findViewById<View>(previously_selected_layout)
        if (v1 != null) {
            v1.isSelected = false
        }
        previously_selected_layout = (view.parent as View).id
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(base))
    }

    fun popFragment(): Boolean {
        Log.e("test", "pop fragment: " + supportFragmentManager.backStackEntryCount)
        var isPop = false
        if (supportFragmentManager.backStackEntryCount > 0) {
            isPop = true
            // getChildFragmentManager().executePendingTransactions();
            supportFragmentManager.popBackStack()
            //  getChildFragmentManager().popBackStackImmediate();
            //getChildFragmentManager().
        }
        return isPop
    }


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
        } else {
            super.onBackPressed()
        }
    }


    /**
     * method to hide keyboard
     */
    fun hideKeyboard(context: Context, view: View) {
        try {
            val methodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            methodManager.hideSoftInputFromWindow(view.windowToken, 0)
        } catch (e: Exception) {
            Log.v("Exception", e.message)
        }

    }

    /**
     * method to hide keyboard
     */
    fun hideKeyboard() {
        if (currentFocus != null && currentFocus!!.windowToken != null) {
            val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    /**
     * method to show keyboard
     */
    fun showKeyboard(context: Context) {
        val methodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        methodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun showDialog(activity: Activity, title: String, msg: String) {
        val dialog = Dialog(activity)
        //  dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setTitle(title)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.alertdialog)

        val title1 = dialog.findViewById<TextView>(R.id.title)
        title1.text = title

        val text = dialog.findViewById<TextView>(R.id.text_dialog)
        text.text = msg

        val dialogButton = dialog.findViewById<Button>(R.id.btn_dialog)
        dialogButton.setOnClickListener { dialog.dismiss() }
        if (!dialog.isShowing)
            dialog.show()
    }

    fun showBackDialog(activity: Activity, title: String, msg: String) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.alertdialog)

        val title1 = dialog.findViewById<TextView>(R.id.title)
        title1.text = title

        val text = dialog.findViewById<TextView>(R.id.text_dialog)
        text.text = msg

        val dialogButton = dialog.findViewById<Button>(R.id.btn_dialog)
        dialogButton.setOnClickListener {
            dialog.dismiss()
            // startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            //finish();
        }
        if (!dialog.isShowing)
            dialog.show()
    }

    fun Toast(activity: Activity, message: Int) {
        currentToast = Toast.makeText(activity, message, Toast.LENGTH_SHORT)
        currentToast!!.show()
    }


    fun showLoadingLayout() {
        showProgressDialog("Please wait...")

    }

    fun hideLoadingLayout() {
        dismissProgress()
    }

    fun showError(response: String) {
        if (!TextUtils.isEmpty(response))
            Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
    }
}