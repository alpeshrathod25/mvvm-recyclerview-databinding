package com.universetelecom.mvvm_users.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.universetelecom.mvvm_users.R;
import com.universetelecom.mvvm_users.databinding.ActivityUsersBinding;
import com.universetelecom.mvvm_users.utils.Constant;
import com.universetelecom.mvvm_users.view.adapter.UserAdapter;
import com.universetelecom.mvvm_users.viewModel.UserActivityViewModel;

import java.util.Observable;
import java.util.Observer;

public class UsersActivity extends AppCompatActivity implements Observer {

    private ActivityUsersBinding userActivityBinding;
    private UserActivityViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userViewModel = new UserActivityViewModel(this);


        userActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_users);
        userActivityBinding.setUserViewModel(userViewModel);
        /*added by alpesh*/
        /*userActivityBinding.listUser.setLayoutManager(new LinearLayoutManager(this));*/

        setSupportActionBar(userActivityBinding.toolbar);
        setUpListOfUsersView(userActivityBinding.listUser);
        setUpObserver(userViewModel);
    }


    // set up the list of user with recycler view
    private void setUpListOfUsersView(RecyclerView listUser) {
        UserAdapter userAdapter = new UserAdapter();
        listUser.setAdapter(userAdapter);
        listUser.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setUpObserver(Observable observable) {
        observable.addObserver(this);
    }


    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof UserActivityViewModel) {
            UserAdapter userAdapter = (UserAdapter) userActivityBinding.listUser.getAdapter();
            UserActivityViewModel userViewModel = (UserActivityViewModel) o;
            userAdapter.setUserList(userViewModel.getUserList());
            //userViewModel.updateUserDataList(userViewModel.getUserList());
        }
    }

    private void startActivityActionView() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.PROJECT_URL)));
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_github) {
            startActivityActionView();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    @Override protected void onDestroy() {
        super.onDestroy();
        userViewModel.reset();
    }

    /*public ClickHandler<ItemUserViewModel> clickHandler()
    {
        return new ClickHandler<ItemUserViewModel>()
        {
            @Override
            public void onClick(ItemUserViewModel user)
            {
                Toast.makeText(UsersActivity.this, user.getFullName() + " " + user.getEmail(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    public LongClickHandler<ItemUserViewModel> longClickHandler()
    {
        return new LongClickHandler<ItemUserViewModel>()
        {
            @Override
            public void onLongClick(ItemUserViewModel user)
            {
                Toast.makeText(UsersActivity.this, "LONG CLICK: " + user.getFullName() + " " + user.getFullName(), Toast.LENGTH_SHORT).show();
            }
        };
    }
    public ItemBinder<ItemUserViewModel> itemViewBinder()
    {
        return new CompositeItemBinder<ItemUserViewModel>(
                new UserBinder(BR.user, R.layout.item_user)
        );
    }*/

}
