package mx.edu.cobaev.preparate;

import android.app.Activity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link homeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homeFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters


    int pagina = 0;

    Boolean primera = false;
    Boolean gotomove = false;
    // TODO: Rename and change types of parameters
    private Activity activity;
    private View root;
    //private List<Gaceta> gacetas;
    private ProgressDialog dialog;

    public static  String idPago = "0";
    LayoutInflater minflater;
    LinearLayout padre;


    public homeFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static homeFragment newInstance() {
        homeFragment fragment = new homeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        minflater = inflater;
        root = inflater.inflate(R.layout.fragment_home, container, false);
        activity = getActivity();

        //padre = root.findViewById(R.id.padre);

        dialog = new ProgressDialog(activity);
        dialog.setMessage("Cargando...");
        dialog.setCancelable(false);




        return root;
    }





}
