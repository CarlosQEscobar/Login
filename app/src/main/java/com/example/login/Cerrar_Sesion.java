package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class Cerrar_Sesion extends AppCompatActivity {

    private Button btnCerrarSesion;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerrar_sesion);
        requestQueue = Volley.newRequestQueue(this);

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // URL de tu API para el CERRAR SESIÓN
                String url = "URL_DE_TU_API_O_SERVICIO";

                // Realizar la solicitud HTTP con Volley
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Manejar la respuesta del servidor en caso de éxito
                                try {
                                    boolean logoutSuccess = response.getBoolean("success");
                                    if (logoutSuccess) {
                                        // CERRAR SESIÓN exitoso, hacer algo aquí, como navegar a la pantalla de inicio de sesión
                                        Toast.makeText(Cerrar_Sesion.this, "CERRAR SESIÓN exitoso", Toast.LENGTH_SHORT).show();
                                    } else {
                                        // CERRAR SESIÓN fallido, mostrar mensaje de error
                                        Toast.makeText(Cerrar_Sesion.this, "Error al CERRAR SESIÓN", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(Cerrar_Sesion.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                            }
                        });

                // Agregar la solicitud a la cola de solicitudes de Volley
                requestQueue.add(request);
            }
        });
    }
}