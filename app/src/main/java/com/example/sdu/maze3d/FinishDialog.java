package com.example.sdu.maze3d;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * 通关显示的提示框
 */
public class FinishDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private boolean isPassAll;
    //声明自定义的接口
    private ClickListenerInterface clickListenerInterface;

    public FinishDialog(Context context, boolean isPassAll) {
        super(context);
        this.context = context;
        this.isPassAll = isPassAll;
    }

    public FinishDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected FinishDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        if(isPassAll) {
            view = inflater.inflate(R.layout.dialog_passall, null);
            Button btn_return = (Button) view.findViewById(R.id.btn_return);
            btn_return.setOnClickListener(this);
        }
        else {
            view = inflater.inflate(R.layout.dialog_finish, null);
            Button yes = (Button) view.findViewById(R.id.btn_yes);
            Button no = (Button) view.findViewById(R.id.btn_no);
            yes.setOnClickListener(this);
            no.setOnClickListener(this);
        }
        setContentView(view);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        // 高度设置为屏幕的0.6
        //lp.height =(int) (d.heightPixels * 0.6);
        dialogWindow.setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        clickListenerInterface.click(v.getId());
    }

    //自定义接口
    public interface ClickListenerInterface {
        public void click(int id);
    }

    //接口的回调方法，以供实现接口调用
    public void setOnCallbackLister(ClickListenerInterface clickListenerInterface) {
        //控件点击事件使用我们的定义的接口中的方法，将点击的控件id传递进去。以待我们在activity中回调接口，取得点击的是哪一个控件。
        //方便传值
        this.clickListenerInterface = clickListenerInterface;
    }
}
