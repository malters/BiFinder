package combi.bifinder;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BiFinderMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bi_finder_main);
        
    	Button btnOperadores = (Button) findViewById(R.id.button1);

    	btnOperadores.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent actividad = new Intent(BiFinderMainActivity.this,
						BiFinderOperadoresActivity.class);
				startActivity(actividad);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_bi_finder_main, menu);
        return true;
    }
    
}
