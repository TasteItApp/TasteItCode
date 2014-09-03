package com.tasteit.app;

import org.json.JSONObject;

import com.tasteit.app.dto.Usuario;
import com.tasteit.app.helpers.AlertDialogManager;
import com.tasteit.app.helpers.HttpClientHelper;
import com.tasteit.app.helpers.SessionManager;

import android.text.TextUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;

	// Values for email and password at the time of the login attempt.
	private String mEmail;
	private String mPassword;

	// UI references.
	private EditText etEmail;
	private EditText etPassword;
	
	SessionManager session;
	
	AlertDialogManager alert = new AlertDialogManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);
		
		session = new SessionManager(getApplicationContext());
		
		if(session.pref.getInt("TasteItPreferences", 0) == 0){
			Intent i = new Intent(this, MainActivity.class);
			startActivity(i);
			finish();
		}

		// Set up the login form.
		etEmail = (EditText) findViewById(R.id.email);
		etEmail.setText(mEmail);

		etPassword = (EditText) findViewById(R.id.password);
		etPassword
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView textView, int id,
							KeyEvent keyEvent) {
						if (id == R.id.login || id == EditorInfo.IME_NULL) {
							attemptLogin();
							return true;
						}
						return false;
					}
				});

		findViewById(R.id.sign_in_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptLogin();
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		etEmail.setError(null);
		etPassword.setError(null);

		// Store values at the time of the login attempt.
		mEmail = etEmail.getText().toString();
		mPassword = etPassword.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(mPassword)) {
			etPassword.setError(getString(R.string.error_field_required));
			focusView = etPassword;
			cancel = true;
		} else if (mPassword.length() < 4) {
			etPassword.setError(getString(R.string.error_invalid_password));
			focusView = etPassword;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(mEmail)) {
			etEmail.setError(getString(R.string.error_field_required));
			focusView = etEmail;
			cancel = true;
		} else if (!mEmail.contains("@")) {
			etEmail.setError(getString(R.string.error_invalid_email));
			focusView = etEmail;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			mAuthTask = new UserLoginTask();
			mAuthTask.execute((Void) null);
		}
	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserLoginTask extends AsyncTask<Void, Void, Usuario> {
		@Override
		protected Usuario doInBackground(Void... params) {
			// TODO: attempt authentication against a network service.
			//android.os.Debug.waitForDebugger();
			Usuario usuario = null;

			try {
				// Agregar codigo para accesar a la App
				JSONObject usuarioJson = HttpClientHelper.getJSONFromUrl("GetUsuarioJSON?correoElectronico=" + mEmail + "&password=" + mPassword);
				//JSONArray usuarioJson = HttpClientHelper.GET("GetUsuarioJSON?correoElectronico=" + mEmail + "&password=" + mPassword);
				
				if(usuarioJson != null){
					
					usuario = new Usuario();
					usuario.setId(usuarioJson.getJSONObject("GetUsuarioResult").getInt("Id") == 0 
							? 0 : usuarioJson.getJSONObject("GetUsuarioResult").getInt("Id"));
					usuario.setNombre(usuarioJson.getJSONObject("GetUsuarioResult").getString("Nombre") == "" 
							? "" : usuarioJson.getJSONObject("GetUsuarioResult").getString("Nombre"));
					usuario.setApellidoPaterno(usuarioJson.getJSONObject("GetUsuarioResult").getString("ApellidoPaterno") == "" 
							? "" : usuarioJson.getJSONObject("GetUsuarioResult").getString("ApellidoPaterno"));
					usuario.setApellidoMaterno(usuarioJson.getJSONObject("GetUsuarioResult").getString("ApellidoMaterno") == "" 
							? "" : usuarioJson.getJSONObject("GetUsuarioResult").getString("ApellidoMaterno"));
					usuario.setNickName(usuarioJson.getJSONObject("GetUsuarioResult").getString("NickName") == "" 
							? "" : usuarioJson.getJSONObject("GetUsuarioResult").getString("NickName"));
					usuario.setCorreoElectronico(usuarioJson.getJSONObject("GetUsuarioResult").getString("CorreoElectronico") == "" 
							? "" : usuarioJson.getJSONObject("GetUsuarioResult").getString("CorreoElectronico"));
					usuario.setFechaAlta(usuarioJson.getJSONObject("GetUsuarioResult").getString("FechaAlta") == "" 
							? "" : usuarioJson.getJSONObject("GetUsuarioResult").getString("FechaAlta"));
					usuario.setUltimoInicioSesion(usuarioJson.getJSONObject("GetUsuarioResult").getString("UltimoInicioSesion") == "" 
							? "" : usuarioJson.getJSONObject("GetUsuarioResult").getString("UltimoInicioSesion"));
					usuario.setPassword(usuarioJson.getJSONObject("GetUsuarioResult").getString("Password") == "" 
							? "" : usuarioJson.getJSONObject("GetUsuarioResult").getString("Password"));
					
					return usuario;
				}
				
				//Thread.sleep(2000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO: register the new account here.
			return usuario;
		}

		@Override
		protected void onPostExecute(Usuario usuario) {
			mAuthTask = null;
			//Utilizar SesionManager para guardar la sesion del usuario
			//Pasar a la actividad principal y llenar la lista de los restaurantes
			//mejor clasificados de acuerdo a la ubicacion del usuario
			
			if(usuario != null){
				session.createLoginSession(usuario.getNombre(), usuario.getCorreoElectronico());
				
				// Staring MainActivity
				Intent i = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(i);
				finish();
			}else{
				alert.showAlertDialog(LoginActivity.this, "Login failed..", "Username/Password is incorrect", false);
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
		}
	}
	
	//Evento del click para pasar al registro de usuario
//	public void RegistrateOnClick(View view){
//		Intent i = new Intent(this, UserActivity.class);
//		this.startActivity(i);
//	}
}
