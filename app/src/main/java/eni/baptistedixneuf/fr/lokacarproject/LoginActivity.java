package eni.baptistedixneuf.fr.lokacarproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

    private TextView motDePasse;
    private Button bouttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bouttonLogin = (Button) findViewById(R.id.activity_login_button);
        motDePasse = (TextView) findViewById(R.id.editTextMotDePasse);
        motDePasse.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            LoginActivity.this.bouttonLogin.performClick();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        bouttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(motDePasse.getText().toString().equals("1234")) {
                    Intent detailIntent = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(detailIntent);
                }else{
                    Toast.makeText(LoginActivity.this, "Mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
