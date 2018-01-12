package app.com.IxigoTest;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import app.com.Adapter.ProvidersAdapter;
import app.com.Extras.Utility;
import app.com.model.Appendix;
import app.com.model.FaresData;
import app.com.model.Flights;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 * Created by Yash on 12/1/18.
 */

public class FlightDetails extends AppCompatActivity implements View.OnClickListener
{
    @BindView(R.id.bookProvider) Button bookProvider;
    @BindView(R.id.flightJourney) TextView flightJourney;
    @BindView(R.id.flightDate) TextView flightDate;
    @BindView(R.id.flightLocation) TextView flightLocation;
    @BindView(R.id.flightClass) TextView flightClass;
    @BindView(R.id.flightOriginTime) TextView flightOriginTime;
    @BindView(R.id.flightOriginDay) TextView flightOriginDay;
    @BindView(R.id.flightDestTime) TextView flightDestTime;
    @BindView(R.id.flightDestDay) TextView flightDestDay;
    @BindView(R.id.providersRclv) RecyclerView providersRclv;

    private ProvidersAdapter providersAdapter;
    private Flights flightsData;
    private Appendix appendix;
    private boolean showProvider = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight_details);

        ButterKnife.bind(this);

        ArrayList<Flights> flights = getIntent().getParcelableArrayListExtra("flights");

        int indexPosition = getIntent().getIntExtra("indexPosition", 0);

        appendix = (Appendix) getIntent().getSerializableExtra("appendix");

        flightsData = flights.get(indexPosition);

        String journey = flights.get(0).getOriginCode() +this.getResources().getString(R.string.dash)+ flights.get(0).getDestinationCode();

        flightJourney.setText(journey);

        flightDate.setText(Utility.getCurrentDate());

        String location = appendix.getAirports().get(flightsData.getOriginCode())+" "+this.getResources().getString(R.string.dash)
                +" "+ appendix.getAirports().get(flightsData.getDestinationCode());

        flightLocation.setText(location);

        String flightType = appendix.getAirlines().get(flightsData.getAirlineCode())+" "+this.getResources().getString(R.string.dash)
                +" "+ flightsData.getFlightClass();

        flightClass.setText(flightType);

        String originTime = flights.get(0).getOriginCode()+" "+ Utility.convertTimeStampToTime(flightsData.getDepartureTime());

        flightOriginTime.setText(originTime);

        flightOriginDay.setText(Utility.getCurrentDate());

        String destTime = flights.get(0).getDestinationCode()+" "+ Utility.convertTimeStampToTime(flightsData.getArrivalTime());

        flightDestTime.setText(destTime);

        flightDestDay.setText(Utility.getCurrentDate());

        if(flightsData.getFareArrayList().size() > 2)
        {
            showProvider = false;
        }
        else
        {
            showProvider = true;
            bookProvider.setVisibility(View.GONE);
        }

        providersAdapter = new ProvidersAdapter(this, (ArrayList<FaresData>) flightsData.getFareArrayList(),appendix,showProvider);

        providersRclv.setLayoutManager(new LinearLayoutManager(this));

        providersRclv.setAdapter(providersAdapter);
    }

    @Override
    @OnClick({R.id.backButton, R.id.bookProvider,R.id.Portrait,R.id.Landscape})
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.backButton:

                moveToPreviousScreen();

                break;

            case R.id.bookProvider:

                if(!showProvider)
                {
                    bookProvider.setText(FlightDetails.this.getResources().getString(R.string.viewLess));
                    showProvider = true;
                }
                else
                {
                    bookProvider.setText(FlightDetails.this.getResources().getString(R.string.viewAll));
                    showProvider = false;
                }

                providersAdapter.updateProvider((ArrayList<FaresData>)flightsData.getFareArrayList(),appendix,showProvider);

                break;

            case R.id.Portrait:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;

            case R.id.Landscape:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;
        }
    }

    @Override
    public void onBackPressed()
    {
        moveToPreviousScreen();
    }

    private void moveToPreviousScreen()
    {
        finish();
    }
}
