package com.example.basictack;

import android.app.Dialog;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basictack.usualFun.DensityUtil;
import com.example.basictack.usualFun.ScreenInfoUtils;

public class MainActivity extends AppCompatActivity {
    private ImageButton mAddTaskBtn;
    private EditText mNewTask_ET;
    private Dialog mBottomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenInfoUtils.fullScreen(this);
        setContentView(R.layout.activity_main);
        this.initSidebar();

        mAddTaskBtn = this.<ImageButton>findViewById(R.id.Add_task_btn);
        mAddTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task_input_windows();
            }
        });
    }

    /**
     * 处理点击了“+”btn之后的事件：弹出dialog，弹出输入法，获取EditView内容，处理内容
     */
    private void task_input_windows() {
        show_input_dialog();

        //↓弹出dialog后自动弹出软键盘并聚焦到editView
        //**这里不能用this而是用了bottom，用this的话下面的mNewTask_ET.requestFocus();会显示mNewTask_ET是空指针
        mNewTask_ET = mBottomDialog.findViewById(R.id.new_task_ET);
        mNewTask_ET.requestFocus();
        mBottomDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(mNewTask_ET, 0);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        mNewTask_ET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || //非原生输入法回车键监听
                        (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())){
                    //隐藏软键盘,关闭dialog
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    mBottomDialog.dismiss();

                    String edit_content =  mNewTask_ET.getText().toString();
                    Toast.makeText(MainActivity.this,edit_content,Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }
    /**
     * find 一个dialog并且弹出,设置好dialog的宽度和圆角
     */
    private void show_input_dialog(){
        mBottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.input_new_task_dialog, null);
        mBottomDialog.setContentView(contentView);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) contentView.getLayoutParams();
        params.width = getResources().getDisplayMetrics().widthPixels - DensityUtil.dp2px(this, 26f);
        params.bottomMargin = DensityUtil.dp2px(this, 18f);
        contentView.setLayoutParams(params);
        mBottomDialog.getWindow().setGravity(Gravity.BOTTOM);//dialog底部弹出
        mBottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        mBottomDialog.show();
    }
    /**
     * 初始化侧边栏
     */
    private void initSidebar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        NavigationView navigationview = (NavigationView) findViewById(R.id.navigation_view);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);//将toolbar与ActionBar关联
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, 0, 0);
        drawer.setDrawerListener(toggle);//初始化状态
        toggle.syncState();

//        setStatusBar.setColor(MainActivity.this);

        /*---------------------------添加头布局和尾布局-----------------------------*/
        //获取xml头布局view
        View headerView = navigationview.getHeaderView(0);
        //添加头布局的另外一种方式
        //View headview=navigationview.inflateHeaderView(R.layout.navigationview_header);

        //寻找头部里面的控件
        ImageView imageView = headerView.findViewById(R.id.iv_head);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "点击了头像", Toast.LENGTH_LONG).show();
            }
        });
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        });
        ColorStateList csl = (ColorStateList) getResources().getColorStateList(R.color.nav_menu_text_color);
        //设置item的条目颜色
        navigationview.setItemTextColor(csl);
        //去掉默认颜色显示原来颜色  设置为null显示本来图片的颜色
        navigationview.setItemIconTintList(csl);

        //设置条目点击监听
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //点击哪个按钮
                Toast.makeText(getApplicationContext(), menuItem.getTitle(), Toast.LENGTH_LONG).show();
                //设置哪个按钮被选中
//                menuItem.setChecked(true);
                //关闭侧边栏
//                drawer.closeDrawers();
                return false;
            }
        });
    }

}
