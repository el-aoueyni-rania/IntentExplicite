package com.example.intentexplicite

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val LOG_TAG = MainActivity::class.java.simpleName
    companion object {
        val EXTRA_MESSAGE = "com.example.android.twoactivities.extra.MESSAGE"
        val TEXT_REQUEST = 1
    }
    lateinit var mMessageEditText: EditText
    lateinit var mReplyHeadTextView: TextView
    lateinit var mReplyTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMessageEditText = findViewById(R.id.editText_main);
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);
    }



    fun launchSecondActivity(view: View?) {
        Log.d(LOG_TAG, "Button clicked!")
        val message = mMessageEditText.text.toString()
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(EXTRA_MESSAGE, message);
        resultLauncher.launch(intent)
       // startActivityForResult(intent, TEXT_REQUEST);
       // startActivity(intent)
    }


    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK){
            val reply = result.data?.getStringExtra(SecondActivity.EXTRA_REPLY)
            mReplyHeadTextView.setVisibility(View.VISIBLE);
            mReplyTextView.setText(reply);
            mReplyTextView.setVisibility(View.VISIBLE);
        }
    }

}