package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etUsername = findViewById(R.id.editTextDate);
        etPassword = findViewById(R.id.editTextTextPassword2);
        btnLogin = findViewById(R.id.button);

        requestQueue = Volley.newRequestQueue(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                // Crear un objeto JSON con las credenciales
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("username", username);
                    jsonObject.put("password", password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // URL de tu API para el LOGIN
                String url = "URL_DE_TU_API_O_SERVICIO";

                // Realizar la solicitud HTTP con Volley
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Manejar la respuesta del servidor en caso de éxito
                                try {
                                    boolean loginSuccess = response.getBoolean("success");
                                    if (loginSuccess) {
                                        // LOGIN exitoso, hacer algo aquí, como navegar a la siguiente pantalla
                                        Toast.makeText(Login.this, "LOGIN exitoso", Toast.LENGTH_SHORT).show();
                                    } else {
                                        // LOGIN fallido, mostrar mensaje de error
                                        Toast.makeText(Login.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Manejar el error de la solicitud HTTP
                                Toast.makeText(Login.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                            }
                        });

                // Agregar la solicitud a la cola de solicitudes de Volley
                requestQueue.add(request);
            }
        });
    }
}
