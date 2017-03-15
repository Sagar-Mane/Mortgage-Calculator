package bananatechnologies.mortgagecalculator;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * @author Sagar Mane
 * @version 1.0
 */

public class CalculationViewFragment extends android.support.v4.app.Fragment{

    private static final String TAG = "Sagar_Logs";

    private String title;
    private int page;
    EditText prop_type;
    EditText address;
    EditText city;
    EditText state;
    EditText zipcode;
    EditText loan_amount;
    EditText down_payment;
    EditText apr;
    EditText terms;

    double Loan_amount;
    double Apr;
    Float sagar;
    Button save;
    Button reset;
    Button calculate;

    public static CalculationViewFragment newInstance(int page, String title) {

        CalculationViewFragment fragmentFirst = new CalculationViewFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.calculation_view_fragment,container,false);

        //all input fields
        prop_type=(EditText)rootview.findViewById(R.id.property_type);
        address=(EditText) rootview.findViewById(R.id.address);
        city=(EditText)rootview.findViewById(R.id.city);
        state=(EditText)rootview.findViewById(R.id.state);
        zipcode=(EditText)rootview.findViewById(R.id.zipcode);
        loan_amount=(EditText)rootview.findViewById(R.id.loan_amount);
        down_payment=(EditText)rootview.findViewById(R.id.down_payment);
        apr=(EditText)rootview.findViewById(R.id.apr);
        terms=(EditText)rootview.findViewById(R.id.terms);


        try{
            Loan_amount=Double.parseDouble(loan_amount.getText().toString());
            Apr=Double.parseDouble(apr.getText().toString());
            sagar=Float.parseFloat(loan_amount.getText().toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }



        //all buttons

        save=(Button) rootview.findViewById(R.id.save_button);
        reset=(Button) rootview.findViewById(R.id.reset_button);
        calculate=(Button) rootview.findViewById(R.id.calculate_button);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.print("Calculate button clicked");
                calculate_mortgage();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    save_data(prop_type.getText().toString(),address.getText().toString(),city.getText().toString(),
                            loan_amount.getText().toString(),apr.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Reset button clicked ");
            }
        });

        return rootview;
    }
    public void calculate_mortgage(){
        Log.i(TAG,"Calculate pressed "+prop_type.getText());
    }

    /**
     * Used to Save Property data. It also calculates latitude and longitude from the address string.
     * @param type  (Property type)
     * @param prop_address
     * @param city
     * @param loan_amount
     * @param apr
     * @throws JSONException
     */
    public void save_data(String type,String prop_address, String city, String loan_amount, String apr) throws JSONException {
        Log.i(TAG,"Save button pressed ");
        String patta="101 E San Fernando St";
        Geocoder coder=new Geocoder(getContext());
        List<Address> address;
        double lat=0,lng=0;
        try {
            address=coder.getFromLocationName(patta,5);
            if(address==null){
                Log.i(TAG,"Address is null");
            }
            Address location=address.get(0);
            lat=location.getLatitude();
            lng=location.getLongitude();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        Log.i(TAG,"Location found");
        Log.i(TAG,"Latitude= "+lat+"Longitude= "+lng);
        PropertyData newProperty=new PropertyData(type,prop_address,city,loan_amount,apr,lat,lng);
        newProperty.toJSON();

    }

}
