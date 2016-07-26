package top.wuhaojie.installer;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.File;

import top.wuhaojie.installerlibrary.AutoInstaller;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("请稍候");

        findViewById(R.id.btn_install).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AutoInstaller installer = AutoInstaller.getDefault(MainActivity.this);
                installer.install(Environment
                        .getExternalStorageDirectory()
                        .getAbsolutePath() + File.separator + "Download" + File.separator + "test.apk");
//                installer.installFromUrl("http://p.gdown.baidu.com/" +
//                        "d040bdf21a9eda7be607b39022cf06cfed6b692253801e59306625b31e7b37d95058875eaf8505cd8d30887cd27e7517e7141da0434c2e39fca952e8ff2dd80f40139cb82e79dd56d42b583522956c1028dc3ed96691cd0be58e452fbdf6357e8a7d5404686e5860259e5a06e524a25817fc0cc05014012d83a4a89d1917e89defbf7616e76b72f4fc36d60f96416cb7ff5b6d1f05accc2bbc8d98ebb3a9cf91468dd6d34d3cec17f0b6a90ff6192339fe603c56a3224b5085a740428548c4d8c6898b68b185470de07945882fb9071d2de66679f8de251f7366208b3fe0bcb10fdbd26d36c5e755e5ffb671b9db0cc091e1dc3c1ee21dac8c3dea53544967ed13809f8c37646b6b825674906ecec075057a22378e0c9a835e537ac73fa6235116bd55ac1121546520bd64d174b72d647b57ae7ffaecc91889d5bfcb464e8f88abf25ca31f4f2325aef3e2b6e6a62b24");
                installer.setOnStateChangedListener(new AutoInstaller.OnStateChangedListener() {
                    @Override
                    public void onStart() {
                        progressDialog.show();
                    }

                    @Override
                    public void onComplete() {
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onNeed2OpenService() {
                        Toast.makeText(MainActivity.this, "请打开辅助功能服务", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
