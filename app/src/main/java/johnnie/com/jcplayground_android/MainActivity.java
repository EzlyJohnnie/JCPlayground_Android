package johnnie.com.jcplayground_android;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import johnnie.com.jccommon.BaseUI.JCBaseActivity;
import johnnie.com.jcplayground_android.ui.landing.MainHostFragment;

public class MainActivity extends JCBaseActivity {
//    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, MainHostFragment.newInstance(), KEY_MAIN_FRAGMENT)
                .commit();



//        editText = (EditText)findViewById(R.id.edit_text);
//        editText.addTextChangedListener(new TextWatcher() {
//            private String preStr = "";
//            private boolean shouldRespondChange = true;
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(!shouldRespondChange){
//                    shouldRespondChange = true;
//                    return;
//                }
//
//
//                JCEditTextDateUtils.JCTextIndex textIndex = JCEditTextDateUtils.strForDate(preStr, s.toString(), editText.getSelectionEnd());
//                shouldRespondChange = false;
//
//                editText.setText(textIndex.text);
//                editText.setSelection(textIndex.selectedIndexEnd);
//
//                preStr = textIndex.text;
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
    }
}
