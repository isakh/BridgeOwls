package ws.isak.bridgeowls.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ws.isak.bridgeowls.R;
import ws.isak.bridgeowls.common.Shared;
import ws.isak.bridgeowls.common.UserData;
import ws.isak.bridgeowls.database.UserDataORM;
import ws.isak.bridgeowls.engine.ScreenController;
import ws.isak.bridgeowls.engine.ScreenController.Screen;

/*
 * This class contains the fragment that sets up the game.  It comprises two edit text fields with
 * corresponding submission buttons for the two cases where the user is registering for the first
 * time and where the user is logging back in having previously registered.
 *
 * @author isak
 */

public class UserSetupFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = "UserSetupFrag";
    public static String newUserName;
    public static String loginName;

    private Button registerNewUser;
    private Button loginExistingUser;

    /*
     * This onCreateView overrides the nullable method:public View onCreateView
     * (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
     * from android.support.v4.app.Fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "overriding onCreateView");
        View view = LayoutInflater.from(Shared.context).inflate(R.layout.user_setup_fragment, container, false);

        //create buttons for registration and login
        Log.d (TAG, "                       : create buttons for registration and login");
        registerNewUser = (Button) view.findViewById(R.id.user_setup_register_button);
        loginExistingUser = (Button) view.findViewById(R.id.user_setup_login_button);

        //prepare listeners on buttons
        Log.d (TAG, "                       : prepare listeners on buttons");
        registerNewUser.setOnClickListener(this);
        loginExistingUser.setOnClickListener(this);
        return view;
    }

    /*
     * this onClick implements the behavior for each of the buttons in the fragment, which is defined
     * in the interface for android.view.View.OnClickListener
     */
    @Override
    public void onClick(View v) {
        Log.d(TAG, "overriding onClick");
        switch (v.getId()) {
            case R.id.user_setup_register_button:
                Log.d (TAG, "       :register button selected");
                hideKeyboard();
                registerNewUser(v);
                break;
            case R.id.user_setup_login_button:
                Log.d (TAG, "       : login button selected");
                hideKeyboard();
                loginExistingUser(v);
                break;
        }
    }

    /*
     * Method createNewUser takes the current View, uses that information to call a method which
     * extracts the name field typed by the user, checks whether said name is unique and new, and if
     * so, creates a new UserData entity and starts to fill in the data fields
     */
    public void registerNewUser(View v) {
        Log.d(TAG, "method registerNewUser");
        newUserName = getNewUserName();
        if (CheckUserUnique(newUserName)) {
            Log.d(TAG, "                    : unique userName: instantiating new UserData and appending to UserData list");
            //get a new instance of UserData and set the Shared userData to that instance
            Shared.userData = UserData.getInstance();
            Shared.userData.setUserName(newUserName);
            Log.d (TAG, "                   : next screen is SELECT_GAME- we no longer want to populate new curUserData");

            Shared.userData.DebugUserData("Called From RegisterNewUser");

            //insert the new UserData into the database - then when games are finished and we are updating stars achieved use update instead of insert
            UserDataORM.insertUserData(Shared.context, Shared.userData);

            //move to the SELECT_GAME screen
            ScreenController.getInstance().openScreen(Screen.SELECT_GAME);  //was PRE_SURVEY but bypassing that now
        } else {
            Log.d (TAG, "                   : userName not unique: ***** ");
            Toast.makeText(Shared.context, "Please choose a name that is not already registered", Toast.LENGTH_LONG).show();
        }
    }

    /*
     * Method loginExistingUser takes the current View, uses that information to call a method which
     * extracts the name field typed by the user, checks whether said name is already in the database
     * of registered users and, if so, sets the pointer to Shared.userData to the retrieved UserData
     * and continues to the next screen.
     */
    public void loginExistingUser(View v) {
        Log.d(TAG, "method loginExistingUser");
        //TODO deal with checking for name uniqueness and set up new UserData in memory
        loginName = getLoginName();
        if (CheckUserExists(loginName)) {
            Log.d (TAG, "                   : preexistingUserName is true, setting current UserData to user's UserData");
            Shared.userData = UserDataORM.findUserDataByID(Shared.context, loginName);
            Log.d (TAG, " ******* Shared.userData @: " + Shared.userData);

            Shared.userData.DebugUserData("Called From LoginExistingUser");

            //load screen for next step
            Log.d (TAG, "                   : existing user: next screen is SELECT_GAME");
            ScreenController.getInstance().openScreen(Screen.SELECT_GAME);
        } else {
            //TODO error - get user to re-enter name
            Toast.makeText(Shared.context, "The User Name you have entered is not registered", Toast.LENGTH_LONG).show();
        }
    }

    private String getNewUserName() {
        final EditText nameField = (EditText) getActivity().findViewById(R.id.user_setup_register_input);
        //Log.d(TAG, "method getNewUserName: address of nameField: " + nameField);
        String name = nameField.getText().toString();
        Log.d(TAG, "method getNewUserName: name: " + name);
        return name;
    }

    private String getLoginName() {
        final EditText nameField = (EditText) getActivity().findViewById(R.id.user_setup_login_input);
        String name = nameField.getText().toString();
        Log.d(TAG, "method getLoginName: name: " + name);
        return name;
    }

    private boolean CheckUserExists(String userName) {
        Log.d(TAG, "method CheckUserExists: called from loginExistingUser");
        boolean userExists = UserDataORM.isUserNameInDB(Shared.context, userName);  //true if userName in DB
        Log.d (TAG, "method CheckUserExists: UserDataORM.isUserNameInDB: " + userExists);
        return userExists;
    }

    private boolean CheckUserUnique(String userName) {
        Log.d(TAG, "method CheckUserUnique");
        boolean userUnique = !UserDataORM.isUserNameInDB(Shared.context, userName); //true if userName not in DB
        Log.d (TAG, "method CheckUserUnique: !UserDataORM.isUserNameInDB: " + userUnique);
        return userUnique;
    }

    private static void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) Shared.context.getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View viewInFocus = Shared.activity.getCurrentFocus();
        if (viewInFocus == null)
            return;

        inputManager.hideSoftInputFromWindow(viewInFocus.getWindowToken(), 0);
    }
}
